package mx.servis.admonticket.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import mx.servis.admonticket.dto.StoryDTO;
import mx.servis.admonticket.persistence.model.Story;

@Mapper(componentModel = "spring", uses = {
		UserMapper.class,
		StatusMapper.class,
		StoryAttachmentMapper.class,
		TicketMapper.class
		})
public interface StoryMapper extends EntityMapper<StoryDTO, Story>{
	
	 	@Mapping(source = "asignedUser.id", target = "asignedUserId")
	    @Mapping(source = "status.id", target = "statusId")
	    @Mapping(source = "ticket.id", target = "ticketId")
	 	StoryDTO toDto(Story story);
	    

	    @Mapping(source = "asignedUserId", target = "asignedUser")
	    @Mapping(source = "statusId", target = "status")
	    @Mapping(source = "ticketId", target = "ticket")
	    Story toEntity(StoryDTO storyDTO);
	    

	    default Story fromId(Long id) {
	        if (id == null) {
	            return null;
	        }
	        Story story = new Story();
	        story.setId(id);
	        return story;
	    }

}
