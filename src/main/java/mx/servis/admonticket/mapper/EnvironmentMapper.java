package mx.servis.admonticket.mapper;

import org.mapstruct.Mapper;

import mx.servis.admonticket.dto.EnvironmentDTO;
import mx.servis.admonticket.persistence.model.Environment;

@Mapper(componentModel = "spring", uses = {})
public interface EnvironmentMapper extends EntityMapper<EnvironmentDTO, Environment> {

    default Environment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Environment environment = new Environment();
        environment.setId(id);
        return environment;
    }

}
