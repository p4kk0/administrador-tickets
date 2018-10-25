package mx.servis.admonticket.service;

import java.util.List;

import mx.servis.admonticket.dto.TicketDTO;

public interface TicketService {
	
	TicketDTO createTicket(TicketDTO ticketDTO);
	TicketDTO updateTicket(TicketDTO ticketDTO);
	void deleteTicket(Long idTicket);
	TicketDTO findTicketById(Long idTicket);
	List<TicketDTO> findAll();
	List<TicketDTO> findAllCreatedByMe();
	List<TicketDTO> findAllInvolved();
	List<TicketDTO> findAllAssigedToMe();
	List<TicketDTO> findAllByClientAndLoggedUser();
}
