package mx.servis.admonticket.respository.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mx.servis.admonticket.persistence.model.Client;
import mx.servis.admonticket.persistence.model.ClientProjection;

@RepositoryRestResource(collectionResourceRel = "clients", path = "clients", excerptProjection = ClientProjection.class)
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>{

}
