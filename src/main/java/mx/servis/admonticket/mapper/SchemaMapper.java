package mx.servis.admonticket.mapper;

import org.mapstruct.Mapper;

import mx.servis.admonticket.dto.SchemaDTO;
import mx.servis.admonticket.persistence.model.Schema;

@Mapper(componentModel = "spring", uses = {})
public interface SchemaMapper extends EntityMapper<SchemaDTO, Schema>{

    default Schema fromId(Long id) {
        if (id == null) {
            return null;
        }
        Schema schema = new Schema();
        schema.setId(id);
        return schema;
    }
}
