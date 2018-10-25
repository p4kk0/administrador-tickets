package mx.servis.admonticket.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import mx.servis.admonticket.dto.TicketDTO;
import mx.servis.admonticket.persistence.model.Ticket;

@Mapper(componentModel = "spring", uses = {
		UserMapper.class,
		PriorityMapper.class,
		EnvironmentMapper.class,
		TechnologyMapper.class,
		StatusMapper.class,
		SchemaMapper.class,
		ProjectMapper.class,
		UnitTimeMapper.class,
		TicketAttachmentMapper.class,
		StoryMapper.class
		})
public interface TicketMapper extends EntityMapper<TicketDTO, Ticket>{
	
    @Mapping(source = "asignedUser.id", target = "asignedUserId")
    @Mapping(source = "priority.id", target = "priorityId")
    @Mapping(source = "environment.id", target = "environmentId")
    @Mapping(source = "technology.id", target = "technologyId")
    @Mapping(source = "status.id", target = "statusId")
    @Mapping(source = "schema.id", target = "schemaId")
    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "unitTime.id", target = "unitTimeId")
    TicketDTO toDto(Ticket ticket);
    

    @Mapping(source = "asignedUserId", target = "asignedUser")
    @Mapping(source = "priorityId", target = "priority")
    @Mapping(source = "environmentId", target = "environment")
    @Mapping(source = "technologyId", target = "technology")
    @Mapping(source = "statusId", target = "status")
    @Mapping(source = "schemaId", target = "schema")
    @Mapping(source = "projectId", target = "project")
    @Mapping(source = "unitTimeId", target = "unitTime")
    Ticket toEntity(TicketDTO ticketDTO);
    

    default Ticket fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ticket ticket = new Ticket();
        ticket.setId(id);
        return ticket;
    }

}
