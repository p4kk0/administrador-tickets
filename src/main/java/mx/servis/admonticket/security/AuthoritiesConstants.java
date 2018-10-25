package mx.servis.admonticket.security;

public final class AuthoritiesConstants {

	private AuthoritiesConstants() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
	
	public static final String ADMIN = "ROLE_ADMIN";

	public static final String GERENTE = "ROLE_GERENTE";
	
	public static final String LIDER = "ROLE_LIDER";

	public static final String ANONYMOUS = "ROLE_ANONYMOUS";


}
