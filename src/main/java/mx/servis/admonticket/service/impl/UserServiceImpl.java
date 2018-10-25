package mx.servis.admonticket.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.context.Context;

import mx.servis.admonticket.constant.MailTemplateConst;
import mx.servis.admonticket.dto.AccountDTO;
import mx.servis.admonticket.dto.ActivateAccountDTO;
import mx.servis.admonticket.dto.ChangePasswordDTO;
import mx.servis.admonticket.dto.MailDTO;
import mx.servis.admonticket.dto.ResetPasswordDTO;
import mx.servis.admonticket.dto.UploadFileResponse;
import mx.servis.admonticket.dto.UserDTO;
import mx.servis.admonticket.mapper.UserMapper;
import mx.servis.admonticket.persistence.model.Role;
import mx.servis.admonticket.persistence.model.User;
import mx.servis.admonticket.respository.RoleRepository;
import mx.servis.admonticket.respository.UserRepository;
import mx.servis.admonticket.rest.error.AppException;
import mx.servis.admonticket.security.SecurityUtils;
import mx.servis.admonticket.service.FileStorageService;
import mx.servis.admonticket.service.MailService;
import mx.servis.admonticket.service.ReportTicketService;
import mx.servis.admonticket.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private ReportTicketService reportTicketService;
	
    @Autowired
    private FileStorageService fileStorageService;
    
    @Value( "${file.upload-dir}" )
    private String rootPathFile;
    
    public UserDTO getUserWithAuthorities() {
    	Optional<UserDTO> userDto =  SecurityUtils.getCurrentUserLogin()
        		.flatMap(userRepository::findOneWithAuthoritiesByLogin)
        		.map(userMapper::user2UserDTO);
    	
    	return userDto.isPresent() ? userDto.get() : new UserDTO();
    }

	@Override
	@Transactional
	public UserDTO registerUser(AccountDTO accountDTO, String appUrl) throws MessagingException {
		if( userRepository.findOneByLogin(accountDTO.getLogin()).isPresent() ) {
			throw new AppException("El usuario " + accountDTO.getLogin() + " ya existe");
		}
		
		if( userRepository.findOneByEmailIgnoreCase(accountDTO.getEmail()).isPresent() ) {
			throw new AppException("El usuario " + accountDTO.getEmail() + " ya existe");
		}
		
		User user = userMapper.userDTO2User(accountDTO);
		if (accountDTO.getRoles() != null) {
			Set<Role> roles = accountDTO.getRoles().stream()
					.map(roleRepository::getOne)
					.collect(Collectors.toSet());
			user.setRoles(roles);
		}
		String encryptedPassword = passwordEncoder.encode(UUID.randomUUID().toString());
		user.setPassword(encryptedPassword);
		user.setActivated(false);
		String activationToken = UUID.randomUUID().toString();
		user.setActivationKey(activationToken);
		user.setImageUrl(appUrl + "/api/users/profile/image/user-default.jpg");
		
		User newUser = userRepository.save(user);
		
		sendRegisterMail(newUser, appUrl, activationToken);
		
		return userMapper.user2UserDTO(newUser);
	}
	
	@Override
	public List<UserDTO> findAll() {
		return userRepository.findAll().stream()
		.map(userMapper::user2UserDTO)
		.collect(Collectors.toList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDTO updateUser(UserDTO userDTO) {
		Optional<User> userOpt = userRepository.findOneByLogin(userDTO.getLogin());
		if( !userOpt.isPresent() ) {
			throw new AppException("El usuario " + userDTO.getLogin() + " no esta registrado");
		}
		
		User user = userOpt.get();
		user.setEmail(userDTO.getEmail());
		user.setActivated(userDTO.isActivated());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setCellPhone(userDTO.getCellPhone());
		user.setOfficePhone(userDTO.getOfficePhone());
		if (userDTO.getRoles() != null) {
			Set<Role> roles = userDTO.getRoles().stream()
					.map(roleRepository::getOne)
					.collect(Collectors.toSet());
			user.setRoles(roles);
		}
		return userMapper.user2UserDTO(userRepository.save(user));
	}

	@Override
	public void deleteUser(String login) {
		Optional<User> userOpt = userRepository.findOneByLogin(login);
		if ( !userOpt.isPresent() ) {
			throw new AppException("El usuario " + login + " no esta registrado");
		}
		userRepository.delete(userOpt.get());
	}

	@Override
	public UserDTO getUserByLogin(String login) {
		Optional<User> userOpt = userRepository.findOneByLogin(login);
		if ( userOpt.isPresent() ) {
			return  userMapper.user2UserDTO(userOpt.get());
		}
		throw new AppException("No se encontro usuario con nombre: "  + login);
	}

	@Override
	public UserDTO getUserByMail(String email) {
		Optional<User> userOpt = userRepository.findOneByEmailIgnoreCase(email);
		if ( userOpt.isPresent() ) {
			return  userMapper.user2UserDTO(userOpt.get());
		}
		throw new AppException("No se encontro usuario con correo electronico: "  + email);
	}

	@Override
	public void resetPasswordInit(String login, String appUrl) throws MessagingException {
		Optional<User> userOpt = userRepository.findOneByLogin(login);
		if( !userOpt.isPresent() ) {
			throw new AppException("El usuario " + login + " no esta registrado");
		}
		
		String resetKey = UUID.randomUUID().toString();
		
		User user = userOpt.get();
		user.setResetKey(resetKey);
		user.setResetDate(new Date());
		sendResetPasswordMail(user, appUrl, resetKey);
		userRepository.save(user);
	}
	
	@Override
	public void resetPasswordFinish(ResetPasswordDTO resetPasswordDTO) {
		Optional<User> userOpt = userRepository.findOneByResetKey(resetPasswordDTO.getResetKey());
		if( !userOpt.isPresent() ) {
			throw new AppException("No se encontro ningun usuario para recuperación con token " + resetPasswordDTO.getResetKey() );
		}
		
		String newEncryptedPassword = passwordEncoder.encode(resetPasswordDTO.getPassword());
		User user = userOpt.get();
		user.setPassword(newEncryptedPassword);
		user.setResetDate(null);
		user.setResetKey(null);
		
		userRepository.save(user);
	}

	@Override
	public void changePassword(ChangePasswordDTO changePasswordDTO) {
		String currentUserLogin = SecurityUtils.getCurrentUserLogin().get();
		Optional<User> userOpt = userRepository.findOneByLogin(currentUserLogin);
		if( !userOpt.isPresent() ) {
			throw new AppException("No eres un usuario legueado");
		}
		
		User user = userOpt.get();
		
		if(!passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), user.getPassword())) {
			throw new AppException("El password actual no coincide con el del sistema");
		}
		user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
		userRepository.save(user);
	}
	
	private boolean existUserByEmail(final String email) {
        return userRepository.findOneByEmailIgnoreCase(email) != null;
    }
	
	private boolean existUserByLogin(final String login) {
        return userRepository.findOneByLogin(login) != null;
    }
	
	private void sendRegisterMail(User user, String appUrl, String activationToken) throws MessagingException {
		final String activateUrl = appUrl + "#/account-activation/" + activationToken;
		Context templateData = new Context();
		templateData.setVariable("nombre", user.getLogin());
		templateData.setVariable("activateUrl", activateUrl);
		
		MailDTO mailDTO = new MailDTO();
		mailDTO.setFrom("bi.prueba.infinita@gmail.com");
		mailDTO.setTo(user.getEmail());
		mailDTO.setSubject("Activación de cuenta para portal de tickets");
		mailDTO.setTemplate(MailTemplateConst.ACTIVATE_ACCOUNT);
		mailDTO.setTemplateData(templateData);
		
		mailService.sendMail(mailDTO);
	}
	
	private void sendResetPasswordMail(User user, String appUrl, String resetToken) throws MessagingException {
		final String resetUrl = appUrl + "#/recover-password-finish/" + resetToken;
		Context templateData = new Context();
		templateData.setVariable("nombre", user.getLogin());
		templateData.setVariable("resetUrl", resetUrl);
		
		MailDTO mailDTO = new MailDTO();
		mailDTO.setFrom("bi.prueba.infinita@gmail.com");
		mailDTO.setTo(user.getEmail());
		mailDTO.setSubject("Recuperación de contraseña para portal de tickets");
		mailDTO.setTemplate(MailTemplateConst.RESET_PASSWORD);
		mailDTO.setTemplateData(templateData);
		
		mailService.sendMail(mailDTO);
	}

	@Override
	public void activateAccount(ActivateAccountDTO activateAccountDTO) {
		Optional<User> userOpt = userRepository.findOneByActivationKey(activateAccountDTO.getActivateKey());
		if( !userOpt.isPresent() ) {
			throw new AppException("No existe token de activación " + activateAccountDTO.getActivateKey());
		}
		
		User user = userOpt.get();
		user.setPassword(passwordEncoder.encode(activateAccountDTO.getPassword()));
		user.setActivated(true);
		user.setActivationKey(null);
		
		userRepository.save(user);
		
	}

	@Override
	public UserDTO getUserByLoginWithReportTickets(String login) {
		Optional<User> userOpt = userRepository.findOneByLogin(login);
		if ( userOpt.isPresent() ) {
			UserDTO userDTO = new UserDTO();
			userDTO = userMapper.user2UserDTO(userOpt.get());
			userDTO.setReportTicket(reportTicketService.generateReport(userOpt.get()));
			
			return userDTO;
			
		}
		throw new AppException("No se encontro usuario con nombre: "  + login);
	}

	@Override
	public UploadFileResponse updateImage(MultipartFile file, Long id) {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
    	String fileImageName = UUID.randomUUID().toString();
    	String completeFileName = fileImageName + "." + extension;
    	
        String fileName = fileStorageService.storeFile(file, rootPathFile, completeFileName);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/users/profile/image/")
                .path(completeFileName)
                .toUriString();
        
        Optional<User> userOpt = userRepository.findById(id);
		if ( userOpt.isPresent() ) {
			User user = userOpt.get();
			user.setImageUrl(fileDownloadUri);
			userRepository.save(user);
		}

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
	}

	@Override
	public Resource loadProfileImage(String fileName) {
      Resource resource = fileStorageService.loadFileAsResource( rootPathFile, fileName);
      return resource;
      
	}
	

}
