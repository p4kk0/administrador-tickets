package mx.servis.admonticket.rest.error;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;

	public RestException() {
		super();
	}

	public RestException(String message) {
		super(message);
	}

	public RestException(Throwable t) {
		super(t);
	}

	public RestException(String message, Throwable t) {
		super(message, t);
	}

	public RestException(String message, Throwable t, HttpStatus httpStatus) {
		super(message, t);
		this.httpStatus = httpStatus;
	}
	
	public RestException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
