package mx.servis.admonticket.respository.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.servis.admonticket.persistence.model.UnitTime;
import mx.servis.admonticket.persistence.model.UnitTimeProjection;

@RepositoryRestResource(collectionResourceRel = "unitsTime", path = "unitsTime", excerptProjection = UnitTimeProjection.class)
public interface UnitTimeRepository extends PagingAndSortingRepository<UnitTime, Long>{

}
