package mx.servis.admonticket.constant;

import mx.servis.admonticket.persistence.model.Status;

public final class StatusTicketConst {
	
	private StatusTicketConst() throws IllegalAccessException{
		throw new IllegalAccessException();
	}
	
	public static final Status OPEN = new Status(1l);
	public static final Status DEVELOPMENT = new Status(2l);
	public static final Status FINISHED = new Status(3l);
	public static final Status TEST = new Status(4l);
	public static final Status CLOSED = new Status(5l);
}
