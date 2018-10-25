package mx.servis.admonticket.dto;

import java.io.Serializable;

import org.thymeleaf.context.Context;

public class MailDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String from;
	private String to;
	private String subject;
	private String template;
	private Context templateData;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Context getTemplateData() {
		return templateData;
	}

	public void setTemplateData(Context templateData) {
		this.templateData = templateData;
	}

}
