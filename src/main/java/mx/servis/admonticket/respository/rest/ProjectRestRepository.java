package mx.servis.admonticket.respository.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.servis.admonticket.persistence.model.Project;
import mx.servis.admonticket.persistence.model.ProjectProjection;

@RepositoryRestResource(collectionResourceRel = "projectss", path = "projectss", excerptProjection = ProjectProjection.class)
public interface ProjectRestRepository  extends PagingAndSortingRepository<Project, Long>{

}
