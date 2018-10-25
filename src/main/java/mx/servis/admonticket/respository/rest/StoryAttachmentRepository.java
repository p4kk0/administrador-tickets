package mx.servis.admonticket.respository.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.servis.admonticket.persistence.model.StoryAttachment;

@RepositoryRestResource(collectionResourceRel = "storyAttachment", path = "storyAttachment")
public interface StoryAttachmentRepository extends PagingAndSortingRepository<StoryAttachment, Long>{

}
