package mx.servis.admonticket.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.gson.Gson;

public class LoginDto {

	@NotNull(message="Usuario es campo obligatorio")
	@Size(min = 1, max = 50)
	private String username;

	@NotNull(message="Contraseña es campo obligatorio")
	@Size(min = 2, max = 20)
	private String password;
	
	private Boolean rememberMe;
	
	public static void main(String[] args) {
		
		String insertarUsuario = "insert into user values(1, true, null, 'paco@gmail.com', 'Paco', null, null, 'Carrillo', 'paco', '$2a$10$NyrfE0ykSOSAg4JfsWXFJe9ismshS3kmLgJNEMJ0uHj5ldkc56LLq', null, null)\r\n" + 
				"select * from user";

		String password = "password";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		Gson gson = new Gson();
		System.out.println(gson.toJson(new LoginDto("Paco", hashedPassword, true)));
	}
	
	public LoginDto() {
		super();
	}

	public LoginDto(String username, String password, Boolean rememberMe) {
		super();
		this.username = username;
		this.password = password;
		this.rememberMe = rememberMe;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

}
