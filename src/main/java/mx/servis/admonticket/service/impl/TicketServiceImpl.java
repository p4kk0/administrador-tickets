package mx.servis.admonticket.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.servis.admonticket.dto.TicketDTO;
import mx.servis.admonticket.persistence.model.Status;
import mx.servis.admonticket.persistence.model.Story;
import mx.servis.admonticket.persistence.model.Ticket;
import mx.servis.admonticket.persistence.model.User;
import mx.servis.admonticket.respository.StoryRepository;
import mx.servis.admonticket.respository.TicketRepository;
import mx.servis.admonticket.respository.UserRepository;
import mx.servis.admonticket.rest.error.AppException;
import mx.servis.admonticket.security.SecurityUtils;
import mx.servis.admonticket.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService{
	
	private final static Long TICKET_STATUS_OPEN = 1l;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("storyRepository")
	private StoryRepository storyRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Transactional
	@Override
	public TicketDTO createTicket(TicketDTO ticketDTO) {
		
		if(ticketDTO.getReference() != null) {
			
			Optional<Ticket> ticketOpt = ticketRepository.findById( ticketDTO.getReference() );
			if(!ticketOpt.isPresent()) {
				throw new AppException("El ticket " + ticketDTO.getReference() + " no existe");
			}
			
		}
		
		User userLogged = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get()).get();
		ticketDTO.setStatusId(TICKET_STATUS_OPEN);
		ticketDTO.setAsignedUserId(userLogged.getId());
		Ticket ticket = modelMapper.map(ticketDTO, Ticket.class);
		Ticket ticketNew = ticketRepository.save( ticket );
		
		Story story = new Story();
		story.setTicket(ticketNew);
		story.setAsignedUser(new User(ticketDTO.getAsignedUserId()));
		story.setComment(ticketDTO.getComment());
		story.setStatus(new Status(ticketDTO.getStatusId()));
		storyRepository.saveAndFlush(story);
		
		return modelMapper.map(ticketNew, TicketDTO.class);
	}

	@Override
	public TicketDTO updateTicket(TicketDTO ticketDTO) {
		Optional<Ticket> ticketOpt = ticketRepository.findById( ticketDTO.getId() );
		if( !ticketOpt.isPresent() ) {
			throw new AppException("El ticket " + ticketDTO.getId() + " no existe en la base de datos");
		}
		
		Ticket ticket = ticketOpt.get();
		
		Story story = new Story();
		story.setTicket(ticket);
		story.setAsignedUser(new User(ticketDTO.getAsignedUserId()));
		story.setComment(ticketDTO.getNewComment());
		story.setStatus(new Status(ticketDTO.getStatusId()));
		storyRepository.saveAndFlush(story);
		
		ticket.setStatus(new Status(ticketDTO.getStatusId()));
		ticket.setAsignedUser(new User(ticketDTO.getAsignedUserId()));
//		ticket.setTime(ticketDTO.getTime());
		
		Ticket ticketUpdated = ticketRepository.save( ticket );
		return modelMapper.map(ticketUpdated, TicketDTO.class);
	}

	@Override
	public void deleteTicket(Long idTicket) {
		Optional<Ticket> ticketOpt = ticketRepository.findById( idTicket );
		if( !ticketOpt.isPresent() ) {
			throw new AppException("El ticket " + idTicket + " no existe en la base de datos");
		}
		ticketRepository.deleteById( idTicket );
	}

	@Override
	public TicketDTO findTicketById(Long idTicket) {
		Optional<Ticket> ticketOpt = ticketRepository.findById( idTicket );
		if( !ticketOpt.isPresent() ) {
			throw new AppException("El ticket " + idTicket + " no existe");
		}
		return modelMapper.map( ticketOpt.get(),  TicketDTO.class);
	}

	@Override
	public List<TicketDTO> findAll() {
		return ticketRepository.findAll().stream()
				.map( ticket -> modelMapper.map(ticket, TicketDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<TicketDTO> findAllByClientAndLoggedUser() {
		String login = SecurityUtils.getCurrentUserLogin().get();
		Optional<User> userOpt = userRepository.findOneByLogin(login);
//		return ticketRepository.findByProjectBusinessUnit(userOpt.get().getBusinessUnit())
//				.map( ticket -> modelMapper.map(ticket, TicketDTO.class))
//				.collect(Collectors.toList());
		
		return ticketRepository.findByProjectBusinessUnitClient(userOpt.get().getClient())
				.map( ticket -> modelMapper.map(ticket, TicketDTO.class))
				.collect(Collectors.toList());
		
//		return ticketRepository.findByProjectBusinessUnitClientId(userOpt.get().getClient().getId())
//				.map( ticket -> modelMapper.map(ticket, TicketDTO.class))
//				.collect(Collectors.toList());
		
//		return ticketRepository.findByClientId(userOpt.get().getClient().getId())
//				.map( ticket -> modelMapper.map(ticket, TicketDTO.class))
//				.collect(Collectors.toList());
	}
	
	@Override
	public List<TicketDTO> findAllCreatedByMe() {
		String login = SecurityUtils.getCurrentUserLogin().get();
		return ticketRepository.findByCreatedBy(login)
				.map( ticket -> modelMapper.map(ticket, TicketDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<TicketDTO> findAllInvolved() {
		String login = SecurityUtils.getCurrentUserLogin().get();
		Optional<User> userOpt = userRepository.findOneByLogin(login);
		return ticketRepository.findAllWithParticipationOfUser(userOpt.get().getId())
				.map( ticket -> modelMapper.map(ticket, TicketDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<TicketDTO> findAllAssigedToMe() {
		String login = SecurityUtils.getCurrentUserLogin().get();
		Optional<User> userOpt = userRepository.findOneByLogin(login);
		return ticketRepository.findByAsignedUser(userOpt.get())
				.map( ticket -> modelMapper.map(ticket, TicketDTO.class))
				.collect(Collectors.toList());
	}
	

}
