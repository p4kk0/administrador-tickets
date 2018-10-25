package mx.servis.admonticket.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import mx.servis.admonticket.dto.StoryAttachmentDTO;
import mx.servis.admonticket.persistence.model.StoryAttachment;

@Mapper(componentModel = "spring", uses = { StoryMapper.class })
public interface StoryAttachmentMapper extends EntityMapper<StoryAttachmentDTO, StoryAttachment> {

	@Mapping(source = "story.id", target = "storyId")
	StoryAttachmentDTO toDto(StoryAttachment storyAttachment);

	@Mapping(source = "storyId", target = "story")
	StoryAttachment toEntity(StoryAttachmentDTO storyAttachmentDTO);

	default StoryAttachment fromId(Long id) {
		if (id == null) {
			return null;
		}
		StoryAttachment storyAttachment = new StoryAttachment();
		storyAttachment.setId(id);
		return storyAttachment;
	}
}
