package mx.servis.admonticket.respository.rest;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.servis.admonticket.persistence.model.Story;
import mx.servis.admonticket.persistence.model.StoryProjection;
import mx.servis.admonticket.persistence.model.TechnologyProjection;

@RepositoryRestResource(collectionResourceRel = "stories", path = "stories", excerptProjection = StoryProjection.class)
public interface StoryRestRepository extends PagingAndSortingRepository<Story, Long>{
	
	List<Story> findByTicketId(@Param("id") Long id);

}
