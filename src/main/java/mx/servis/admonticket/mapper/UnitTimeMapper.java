package mx.servis.admonticket.mapper;

import org.mapstruct.Mapper;

import mx.servis.admonticket.dto.UnitTimeDTO;
import mx.servis.admonticket.persistence.model.UnitTime;

@Mapper(componentModel = "spring", uses = {})
public interface UnitTimeMapper extends EntityMapper<UnitTimeDTO, UnitTime>{

    default UnitTime fromId(Long id) {
        if (id == null) {
            return null;
        }
        UnitTime unitTime = new UnitTime();
        unitTime.setId(id);
        return unitTime;
    }
    
}
