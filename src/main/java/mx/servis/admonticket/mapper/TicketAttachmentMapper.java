package mx.servis.admonticket.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import mx.servis.admonticket.dto.TicketAttachmentDTO;
import mx.servis.admonticket.persistence.model.TicketAttachment;

@Mapper(componentModel = "spring", uses = { TicketMapper.class })
public interface TicketAttachmentMapper  extends EntityMapper<TicketAttachmentDTO, TicketAttachment>{
	
	@Mapping(source = "ticket.id", target = "ticketId")
	TicketAttachmentDTO toDto(TicketAttachment ticketAttachment);

	@Mapping(source = "ticketId", target = "ticket")
	TicketAttachment toEntity(TicketAttachmentDTO ticketAttachmentDTO);

	default TicketAttachment fromId(Long id) {
		if (id == null) {
			return null;
		}
		TicketAttachment ticketAttachment = new TicketAttachment();
		ticketAttachment.setId(id);
		return ticketAttachment;
	}

}
