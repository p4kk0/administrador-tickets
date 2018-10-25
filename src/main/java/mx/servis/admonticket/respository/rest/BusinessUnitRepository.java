package mx.servis.admonticket.respository.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.servis.admonticket.persistence.model.BusinessUnit;
import mx.servis.admonticket.persistence.model.BusinessUnitProjection;

@RepositoryRestResource(collectionResourceRel = "businessUnits", path = "businessUnits", excerptProjection = BusinessUnitProjection.class)
public interface BusinessUnitRepository extends PagingAndSortingRepository<BusinessUnit, Long>{

}
