package mx.servis.admonticket.dto;

public class ResetPasswordDTO {
	
	private String resetKey;
	private String password;
	
	public String getResetKey() {
		return resetKey;
	}
	public void setResetKey(String resetKey) {
		this.resetKey = resetKey;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
