package mx.servis.admonticket.respository.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.servis.admonticket.persistence.model.TicketAttachment;

@RepositoryRestResource(collectionResourceRel = "attachments", path = "attachments")
public interface AttachedRepository extends PagingAndSortingRepository<TicketAttachment, Long>{

}
