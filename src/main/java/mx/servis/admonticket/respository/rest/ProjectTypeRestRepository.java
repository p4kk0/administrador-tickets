package mx.servis.admonticket.respository.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.servis.admonticket.persistence.model.ProjectType;
import mx.servis.admonticket.persistence.model.ProjectTypeProjection;

@RepositoryRestResource(collectionResourceRel = "projectTypes", path = "projectTypes", excerptProjection = ProjectTypeProjection.class)
public interface ProjectTypeRestRepository extends PagingAndSortingRepository<ProjectType, Long>{

}
