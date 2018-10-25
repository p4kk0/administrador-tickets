package mx.servis.admonticket.rest.error;

public class AppException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public AppException(String mensaje) {
		super(mensaje);
	}
	
	public AppException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
