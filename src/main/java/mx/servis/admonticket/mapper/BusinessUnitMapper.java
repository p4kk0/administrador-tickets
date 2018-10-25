package mx.servis.admonticket.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import mx.servis.admonticket.dto.BusinessUnitDTO;
import mx.servis.admonticket.persistence.model.BusinessUnit;

@Mapper(componentModel = "spring", uses = {
		ClientMapper.class,
		ProjectMapper.class
		})
public interface BusinessUnitMapper extends EntityMapper<BusinessUnitDTO, BusinessUnit> {
	
    @Mapping(source = "client.id", target = "clientId")
    BusinessUnitDTO toDto(BusinessUnit businessUnit);

    @Mapping(source = "clientId", target = "client")
    BusinessUnit toEntity(BusinessUnitDTO businessUnitDTO);
	
    default BusinessUnit fromId(Long id) {
        if (id == null) {
            return null;
        }
        BusinessUnit businessUnit = new BusinessUnit();
        businessUnit.setId(id);
        return businessUnit;
    }

}
