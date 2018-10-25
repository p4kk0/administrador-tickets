package mx.servis.admonticket.respository.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.servis.admonticket.persistence.model.Priority;
import mx.servis.admonticket.persistence.model.PriorityProjection;

@RepositoryRestResource(collectionResourceRel = "priorities", path = "priorities", excerptProjection = PriorityProjection.class)
public interface PriorityRepository extends PagingAndSortingRepository<Priority, Long>{

}
