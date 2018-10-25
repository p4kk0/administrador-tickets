package mx.servis.admonticket.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import mx.servis.admonticket.constant.MailTemplateConst;
import mx.servis.admonticket.dto.MailDTO;
import mx.servis.admonticket.persistence.model.User;
import mx.servis.admonticket.rest.error.AppException;
import mx.servis.admonticket.service.MailService;

@Service
public class MailServiceImpl implements MailService{

	@Autowired
    private JavaMailSender sender;
	
	@Autowired
	private TemplateEngine templateEngine;
 
	@Override
	public void sendMail(MailDTO mailDTO) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	
//	    try {
	    	String builtTemplate = templateEngine.process(mailDTO.getTemplate(), mailDTO.getTemplateData());
	    	helper.setFrom(mailDTO.getFrom());
	        helper.setTo(mailDTO.getTo());
	        helper.setSubject(mailDTO.getSubject());
	        helper.setText(builtTemplate, true);
	        sender.send(message);
//	    } catch (MessagingException e) {
//	        throw new AppException(e.getMessage(), e);
//	    } catch (MailException e) {
//	    	throw new AppException(e.getMessage(), e);
//	    }
	}
	

}
