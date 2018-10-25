package mx.servis.admonticket.rest.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mx.servis.admonticket.dto.AccountDTO;
import mx.servis.admonticket.dto.ChangePasswordDTO;
import mx.servis.admonticket.dto.UploadFileResponse;
import mx.servis.admonticket.dto.UserDTO;
import mx.servis.admonticket.rest.error.AppException;
import mx.servis.admonticket.rest.error.RestException;
import mx.servis.admonticket.security.SecurityUtils;
import mx.servis.admonticket.service.FileStorageService;
import mx.servis.admonticket.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUsers()  throws URISyntaxException{
		try {
			List<UserDTO> users = userService.findAll();
			return ResponseEntity.ok().body(users);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/users/authenticated")
	public ResponseEntity<UserDTO> getUserAuthenticated()  throws URISyntaxException{
		try {
			String login = SecurityUtils.getCurrentUserLogin().get();
			UserDTO user = userService.getUserByLoginWithReportTickets(login);
			return ResponseEntity.ok().body(user);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/users/{login}")
	public ResponseEntity<UserDTO> getUser(@PathVariable String login)  throws URISyntaxException{
		try {
//			UserDTO user = userService.getUserByLogin(login);
			UserDTO user = userService.getUserByLoginWithReportTickets(login);
			return ResponseEntity.ok().body(user);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PostMapping("/users")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody AccountDTO accountDTO, final HttpServletRequest request)  throws URISyntaxException{
		try {
			UserDTO newUserDTO = userService.registerUser( accountDTO, getAppUrl(request) );
			return ResponseEntity.created(new URI("/api/users/" + newUserDTO.getLogin())).body(newUserDTO);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/users")
	public ResponseEntity<UserDTO> editAccount(@Valid @RequestBody UserDTO userDTO)  throws URISyntaxException{
		try {
			UserDTO newUserDTO = userService.updateUser( userDTO );
			return ResponseEntity.ok().body(newUserDTO);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/users/{login}")
	public ResponseEntity<Void> deleteAccount(@PathVariable String login)  throws URISyntaxException{
		if(login == null || login.trim().isEmpty()) {
			throw new RestException("Se debe proporcionar el valor del usuario", HttpStatus.BAD_REQUEST);
		}
		try {
			userService.deleteUser( login );
			return ResponseEntity.ok().build();
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/users/password")
	public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO)  throws URISyntaxException{
		try {
			userService.changePassword(changePasswordDTO);
			return ResponseEntity.ok().build();
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    @PostMapping("/users/{id}/profile/image")
    public ResponseEntity<UploadFileResponse> updateImage(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
    	
    	try {
    		UploadFileResponse response = userService.updateImage(file, id);
    		return ResponseEntity.ok().body(response);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
	  @GetMapping("/users/profile/image/{fileName:.+}")
	  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		  logger.info("APP URL " + getAppUrl(request));
	      Resource resource = userService.loadProfileImage(fileName);
	
	      String contentType = null;
	      try {
	          contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	      } catch (IOException ex) {
	          logger.info("Could not determine file type.");
	      }
	
	      if(contentType == null) {
	          contentType = "application/octet-stream";
	      }
	
	      return ResponseEntity.ok()
	              .contentType(MediaType.parseMediaType(contentType))
	              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	              .body(resource);
	  }
	
    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
