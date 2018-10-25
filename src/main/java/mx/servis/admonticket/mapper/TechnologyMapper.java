package mx.servis.admonticket.mapper;

import org.mapstruct.Mapper;

import mx.servis.admonticket.dto.TechnologyDTO;
import mx.servis.admonticket.persistence.model.Technology;

@Mapper(componentModel = "spring", uses = {})
public interface TechnologyMapper extends EntityMapper<TechnologyDTO, Technology>{
	
    default Technology fromId(Long id) {
        if (id == null) {
            return null;
        }
        Technology technology = new Technology();
        technology.setId(id);
        return technology;
    }
}
