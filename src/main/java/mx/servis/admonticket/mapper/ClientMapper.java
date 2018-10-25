package mx.servis.admonticket.mapper;

import org.mapstruct.Mapper;

import mx.servis.admonticket.dto.ClientDTO;
import mx.servis.admonticket.persistence.model.Client;

@Mapper(componentModel = "spring", uses = { ProjectMapper.class,})
public interface ClientMapper extends EntityMapper<ClientDTO, Client>{
	
    default Client fromId(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }

}
