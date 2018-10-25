package mx.servis.admonticket.service;

import javax.mail.MessagingException;

import mx.servis.admonticket.dto.MailDTO;

public interface MailService {
	
	void sendMail(MailDTO mailDTO) throws MessagingException;
	
}
