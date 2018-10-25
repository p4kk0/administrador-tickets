package mx.servis.admonticket.respository.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.servis.admonticket.persistence.model.TechnologyProjection;
import mx.servis.admonticket.persistence.model.Technology;

@RepositoryRestResource(collectionResourceRel = "technologies", path = "technologies", excerptProjection = TechnologyProjection.class)
public interface TechnologyRepository extends PagingAndSortingRepository<Technology, Long>{

}
