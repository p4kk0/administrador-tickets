package mx.servis.admonticket.mapper;

import org.mapstruct.Mapper;

import mx.servis.admonticket.dto.PriorityDTO;
import mx.servis.admonticket.persistence.model.Priority;

@Mapper(componentModel = "spring", uses = {})
public interface PriorityMapper extends EntityMapper<PriorityDTO, Priority>{
	
    default Priority fromId(Long id) {
        if (id == null) {
            return null;
        }
        Priority priority = new Priority();
        priority.setId(id);
        return priority;
    }

}
