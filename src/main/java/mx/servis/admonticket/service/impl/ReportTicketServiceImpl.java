package mx.servis.admonticket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.servis.admonticket.constant.StatusTicketConst;
import mx.servis.admonticket.dto.ReportTicketDTO;
import mx.servis.admonticket.persistence.model.User;
import mx.servis.admonticket.respository.TicketRepository;
import mx.servis.admonticket.service.ReportTicketService;

@Service
public class ReportTicketServiceImpl implements ReportTicketService{
	
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public ReportTicketDTO generateReport(User user) {
		ReportTicketDTO reportTicketDTO = new ReportTicketDTO();
		reportTicketDTO.setAssignedClosedTickets(ticketRepository.countByAsignedUserAndStatus(user, StatusTicketConst.CLOSED));
		reportTicketDTO.setAssignedDevelopmentTickets(ticketRepository.countByAsignedUserAndStatus(user, StatusTicketConst.DEVELOPMENT));
		reportTicketDTO.setAssignedFinishedTickets(ticketRepository.countByAsignedUserAndStatus(user, StatusTicketConst.FINISHED));
		reportTicketDTO.setAssignedOpenTicket(ticketRepository.countByAsignedUserAndStatus(user, StatusTicketConst.OPEN));
		reportTicketDTO.setAssignedTestTickets(ticketRepository.countByAsignedUserAndStatus(user, StatusTicketConst.TEST));
		reportTicketDTO.setAssignedTotalTickets(ticketRepository.countByAsignedUser(user));
		
		reportTicketDTO.setCreatedClosedTickets(ticketRepository.countByCreatedByAndStatus(user.getLogin(), StatusTicketConst.CLOSED));
		reportTicketDTO.setCreatedDevelopmentTickets(ticketRepository.countByCreatedByAndStatus(user.getLogin(), StatusTicketConst.DEVELOPMENT));
		reportTicketDTO.setCreatedFinishedTickets(ticketRepository.countByCreatedByAndStatus(user.getLogin(), StatusTicketConst.FINISHED));
		reportTicketDTO.setCreatedOpenTicket(ticketRepository.countByCreatedByAndStatus(user.getLogin(), StatusTicketConst.OPEN));
		reportTicketDTO.setCreatedTestTickets(ticketRepository.countByCreatedByAndStatus(user.getLogin(), StatusTicketConst.TEST));
		reportTicketDTO.setCreatedTotalTickets(ticketRepository.countByCreatedBy(user.getLogin()));
		
		reportTicketDTO.setInvolvedClosedTickets(ticketRepository.countByInvolvedUserAndStatus(user.getId(), StatusTicketConst.CLOSED.getId()));
		reportTicketDTO.setInvolvedDevelopmentTickets(ticketRepository.countByInvolvedUserAndStatus(user.getId(), StatusTicketConst.DEVELOPMENT.getId()));
		reportTicketDTO.setInvolvedFinishedTickets(ticketRepository.countByInvolvedUserAndStatus(user.getId(), StatusTicketConst.FINISHED.getId()));
		reportTicketDTO.setInvolvedOpenTicket(ticketRepository.countByInvolvedUserAndStatus(user.getId(), StatusTicketConst.OPEN.getId()));
		reportTicketDTO.setInvolvedTestTickets(ticketRepository.countByInvolvedUserAndStatus(user.getId(), StatusTicketConst.TEST.getId()));
		reportTicketDTO.setInvolvedTotalTickets( ticketRepository.countByInvolvedUser( user.getId() ) );
		
		return reportTicketDTO;
	}

}
