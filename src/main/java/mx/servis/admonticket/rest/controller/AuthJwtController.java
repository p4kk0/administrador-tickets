package mx.servis.admonticket.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import mx.servis.admonticket.dto.LoginDto;
import mx.servis.admonticket.dto.UserDTO;
import mx.servis.admonticket.persistence.model.User;
import mx.servis.admonticket.rest.error.RestException;
import mx.servis.admonticket.security.jwt.JwtConfiguration;
import mx.servis.admonticket.security.jwt.JwtTokenProvider;
import mx.servis.admonticket.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthJwtController {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
//    @Autowired
//    private MessageSource messages;
	
	@GetMapping("/login")
	public ResponseEntity<String> login() {

        return new ResponseEntity<String>("Working!!!", HttpStatus.OK);
	}
	

	@PostMapping("/login")
	public ResponseEntity<JWTToken> login(@Valid @RequestBody LoginDto loginDto) {
		System.out.println("BACKEND AUTENTICACION");
		 UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
		 Authentication authentication = null;
		try {
			authentication = this.authenticationManager.authenticate(authenticationToken);
		} catch (AuthenticationException e) {
			System.out.println("Error de auteticacion" + e.getMessage());
			throw new RestException("Credenciales no validas", e, HttpStatus.UNAUTHORIZED);
		}
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (loginDto.isRememberMe() == null) ? false : loginDto.isRememberMe();
        String jwt = jwtTokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtConfiguration.AUTHORIZATION_HEADER, "Bearer " + jwt);
        System.out.println("TOKEN: " + jwt);
        UserDTO userDto = userService.getUserByLogin(loginDto.getUsername());
       
        return new ResponseEntity<>(new JWTToken(jwt, userDto), httpHeaders, HttpStatus.OK);
	}
	
	static class JWTToken {

        private String idToken;
        UserDTO user;

        JWTToken(String idToken, UserDTO userDTO) {
            this.idToken = idToken;
            this.user = userDTO;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }

		public UserDTO getUser() {
			return user;
		}

		public void setUser(UserDTO user) {
			this.user = user;
		}
        
        
    }

}
