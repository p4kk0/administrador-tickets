package mx.servis.admonticket.service;

import mx.servis.admonticket.dto.ReportTicketDTO;
import mx.servis.admonticket.persistence.model.User;

public interface ReportTicketService {
	
	ReportTicketDTO generateReport(User user);
}
