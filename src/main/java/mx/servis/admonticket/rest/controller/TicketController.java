package mx.servis.admonticket.rest.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.servis.admonticket.dto.TicketDTO;
import mx.servis.admonticket.rest.error.AppException;
import mx.servis.admonticket.rest.error.RestException;
import mx.servis.admonticket.security.AuthoritiesConstants;
import mx.servis.admonticket.security.SecurityUtils;
import mx.servis.admonticket.service.TicketService;

@RestController
@RequestMapping("/api")
public class TicketController {
		
	@Autowired
	private TicketService ticketService;
	
	
	@Transactional
	@GetMapping("/created-tickets")
	public ResponseEntity<List<TicketDTO>> getCreatedTickets()  throws URISyntaxException{
		try {
			List<TicketDTO> tickets = ticketService.findAllCreatedByMe();
			return ResponseEntity.ok().body(tickets);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@GetMapping("/assigned-tickets")
	public ResponseEntity<List<TicketDTO>> getAssignedTickets()  throws URISyntaxException{
		try {
			List<TicketDTO> tickets = ticketService.findAllAssigedToMe();
			return ResponseEntity.ok().body(tickets);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@GetMapping("/ivolved-tickets")
	public ResponseEntity<List<TicketDTO>> getInvolvedTickets()  throws URISyntaxException{
		try {
			List<TicketDTO> tickets = ticketService.findAllInvolved();
			return ResponseEntity.ok().body(tickets);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@Secured(value="ROLE_LIDER")
	@Transactional
	@GetMapping("/tickets")
	public ResponseEntity<List<TicketDTO>> getAll()  throws URISyntaxException{
		try {
			List<TicketDTO> tickets = null;
			
			if( SecurityUtils.isCurrentUserInRole( AuthoritiesConstants.ADMIN ) ) {
				tickets = ticketService.findAll();
			}else if( SecurityUtils.isCurrentUserInRole( AuthoritiesConstants.GERENTE ) ){
				tickets = ticketService.findAllByClientAndLoggedUser();
			}else if( SecurityUtils.isCurrentUserInRole( AuthoritiesConstants.LIDER ) ){
				tickets = ticketService.findAllInvolved();
			}else {
				tickets = Collections.emptyList();
			}
			
			return ResponseEntity.ok().body(tickets);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/tickets/{id}")
	public ResponseEntity<TicketDTO> getTicket(@PathVariable Long id)  throws URISyntaxException{
		try {
			TicketDTO ticket = ticketService.findTicketById(id);
			return ResponseEntity.ok().body(ticket);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PostMapping("/tickets")
	public ResponseEntity<TicketDTO> createTicket(@Valid @RequestBody TicketDTO ticketDTO)  throws URISyntaxException{
		try {
			TicketDTO newTicketDTO = ticketService.createTicket( ticketDTO );
			return ResponseEntity.created(new URI("/api/tickets/" + newTicketDTO.getId())).body(newTicketDTO);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/tickets")
	public ResponseEntity<TicketDTO> editAccount(@Valid @RequestBody TicketDTO ticketDTO)  throws URISyntaxException{
		try {
			TicketDTO updatedTicketDTO = ticketService.updateTicket( ticketDTO );
			return ResponseEntity.ok().body(updatedTicketDTO);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/tickets/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable Long id)  throws URISyntaxException{

		try {
			ticketService.deleteTicket(id);
			return ResponseEntity.ok().build();
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
