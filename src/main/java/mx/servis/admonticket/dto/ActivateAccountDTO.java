package mx.servis.admonticket.dto;

import javax.validation.constraints.NotNull;

public class ActivateAccountDTO {

	@NotNull
	private String activateKey;

	@NotNull
	private String password;

	public String getActivateKey() {
		return activateKey;
	}

	public void setActivateKey(String activateKey) {
		this.activateKey = activateKey;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivateAccountDTO [activateKey=");
		builder.append(activateKey);
		builder.append(", password=");
		builder.append(password);
		builder.append("]");
		return builder.toString();
	}
	
	

}
