package mx.servis.admonticket.respository.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.servis.admonticket.persistence.model.Schema;
import mx.servis.admonticket.persistence.model.SchemaProjection;

@RepositoryRestResource(collectionResourceRel = "schemas", path = "schemas", excerptProjection = SchemaProjection.class)
public interface SchemaRepository extends PagingAndSortingRepository<Schema, Long>{

}
