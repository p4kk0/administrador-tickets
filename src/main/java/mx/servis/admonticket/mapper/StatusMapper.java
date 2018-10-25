package mx.servis.admonticket.mapper;

import org.mapstruct.Mapper;

import mx.servis.admonticket.dto.StatusDTO;
import mx.servis.admonticket.persistence.model.Status;

@Mapper(componentModel = "spring", uses = {})
public interface StatusMapper extends EntityMapper<StatusDTO, Status>{
	
    default Status fromId(Long id) {
        if (id == null) {
            return null;
        }
        Status status = new Status();
        status.setId(id);
        return status;
    }

}
