package mx.servis.admonticket.respository.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.servis.admonticket.persistence.model.Status;
import mx.servis.admonticket.persistence.model.StatusProjection;

@RepositoryRestResource(collectionResourceRel = "status", path = "status", excerptProjection = StatusProjection.class)
public interface StatusRepository extends PagingAndSortingRepository<Status, Long>{

}
