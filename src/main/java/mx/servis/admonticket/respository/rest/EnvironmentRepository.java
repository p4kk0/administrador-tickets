package mx.servis.admonticket.respository.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.servis.admonticket.persistence.model.Environment;
import mx.servis.admonticket.persistence.model.EnvironmentProjection;

@RepositoryRestResource(collectionResourceRel = "environments", path = "environments", excerptProjection = EnvironmentProjection.class)
public interface EnvironmentRepository extends PagingAndSortingRepository<Environment, Long>{

}
