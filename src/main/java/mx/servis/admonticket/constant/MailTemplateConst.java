package mx.servis.admonticket.constant;

public final class MailTemplateConst {

	private MailTemplateConst() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
	
	public static final String ACTIVATE_ACCOUNT = "mail/activate-account";
	public static final String RESET_PASSWORD = "mail/reset-password-account";
}
