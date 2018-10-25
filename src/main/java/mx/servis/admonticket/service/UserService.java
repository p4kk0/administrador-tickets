package mx.servis.admonticket.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mx.servis.admonticket.dto.AccountDTO;
import mx.servis.admonticket.dto.ActivateAccountDTO;
import mx.servis.admonticket.dto.ChangePasswordDTO;
import mx.servis.admonticket.dto.ResetPasswordDTO;
import mx.servis.admonticket.dto.UploadFileResponse;
import mx.servis.admonticket.dto.UserDTO;

public interface UserService {

	public UserDTO registerUser(AccountDTO accountDTO, String appUrl) throws MessagingException;
	UserDTO updateUser(UserDTO userDTO);
	UserDTO getUserByLogin(String login);
	UserDTO getUserByMail(String email);
	UserDTO getUserByLoginWithReportTickets(String login);
    UserDTO getUserWithAuthorities();
	List<UserDTO> findAll();
	void resetPasswordInit(String login, String appUrl) throws MessagingException;
	void resetPasswordFinish(ResetPasswordDTO resetPasswordDTO);
	void deleteUser(String login);
	void changePassword(ChangePasswordDTO changePasswordDTO);
	void activateAccount(ActivateAccountDTO activateAccountDTO);
	UploadFileResponse updateImage(MultipartFile file,  Long id);
	Resource loadProfileImage(String fileName);
	
}
