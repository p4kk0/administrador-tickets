package mx.servis.admonticket.rest.controller;

//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.servis.admonticket.dto.AccountDTO;
import mx.servis.admonticket.dto.ActivateAccountDTO;
import mx.servis.admonticket.dto.ChangePasswordDTO;
import mx.servis.admonticket.dto.ResetPasswordDTO;
import mx.servis.admonticket.dto.UserDTO;
import mx.servis.admonticket.rest.error.AppException;
import mx.servis.admonticket.rest.error.RestException;
import mx.servis.admonticket.service.UserService;

@RestController
@RequestMapping("/api")
public class AccountController {
	
	Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/account")
    public UserDTO getAccount() {
        return userService.getUserWithAuthorities();
    }

	
	@PostMapping("/account")
	public ResponseEntity<UserDTO> createAccount(@Valid @RequestBody AccountDTO accountDTO, final HttpServletRequest request)  throws URISyntaxException{
		try {
			UserDTO newUserDTO = userService.registerUser( accountDTO , getAppUrl(request));
			return ResponseEntity.created(new URI("/api/account/" + newUserDTO.getLogin()))
					.body(newUserDTO);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/account")
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
	
	@DeleteMapping("/account/{login}")
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
	
	@PutMapping("/account/password")
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
	
	@PostMapping("/account/reset-password/init")
	public ResponseEntity<Void> resetPasswordInit(@RequestBody String login, final HttpServletRequest request)  throws URISyntaxException{
		try {
			userService.resetPasswordInit(login, getAppUrl( request ) );
			return ResponseEntity.ok().build();
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/account/reset-password/finish")
	public ResponseEntity<Void> resetPasswordFinish(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO)  throws URISyntaxException{
		try {
			userService.resetPasswordFinish(resetPasswordDTO);
			return ResponseEntity.ok().build();
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/account/activate")
	public ResponseEntity<Void> activateAccount(@Valid @RequestBody ActivateAccountDTO activateAccountDTO)  throws URISyntaxException{
		logger.info("INICIO DE ACTIVACION DE CUENTA");
		logger.info("INICIO DE ACTIVACION DE CUENTA ENTRADA " + activateAccountDTO.toString());
		try {
			userService.activateAccount(activateAccountDTO);
			return ResponseEntity.ok().build();
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
