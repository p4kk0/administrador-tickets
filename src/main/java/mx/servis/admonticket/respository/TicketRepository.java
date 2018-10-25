package mx.servis.admonticket.respository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.servis.admonticket.persistence.model.BusinessUnit;
import mx.servis.admonticket.persistence.model.Client;
import mx.servis.admonticket.persistence.model.Status;
import mx.servis.admonticket.persistence.model.Ticket;
import mx.servis.admonticket.persistence.model.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	
	long countByCreatedBy(String userCreator);
	long countByAsignedUser(User asignedUser);
	
	
	long countByAsignedUserAndStatus(User asignedUser, Status status);
	long countByCreatedByAndStatus(String userCreator, Status status);
	
	Stream<Ticket> findByCreatedBy(String login);
	
	Stream<Ticket> findByAsignedUser(User asignedUser);
	
	Stream<Ticket> findByProjectBusinessUnitClient(Client client);
	
	Stream<Ticket> findByProjectBusinessUnitClientId(Long idClient);
	
	@Query(value = "SELECT * FROM incidencias.ticket t where t.id_project in (SELECT p.id_project FROM incidencias.project p inner join incidencias.business_unit bu on p.id_business_unit = bu.id_business_unit inner join incidencias.client c on bu.id_client = c.id_client where c.id_client = ?1)", 
			nativeQuery = true)
	Stream<Ticket> findByClientId(Long idClient);
	
	@Query(value = "SELECT * FROM incidencias.ticket t where t.id_project in (SELECT p.id_project FROM incidencias.project p inner join incidencias.business_unit bu on p.id_business_unit = bu.id_business_unit where  bu.id_business_unit = ?1)", 
			nativeQuery = true)
	Stream<Ticket> findByBusinessUnitId(Long idBusinessUnit);
	
	@Query(value = "SELECT * FROM ticket t where t.id_ticket in (SELECT distinct(s.id_ticket) FROM incidencias.story s where s.id_user = ?1)", 
			nativeQuery = true)
	Stream<Ticket> findAllWithParticipationOfUser(Long IdUser);
	
	@Query(value = "SELECT count(*) FROM ticket t where t.id_ticket in (SELECT distinct(s.id_ticket) FROM incidencias.story s where s.id_user = ?1) and t.id_status = ?2", 
			nativeQuery = true)
	long countByInvolvedUserAndStatus(Long IdUser, Long Idstatus);
	
	@Query(value = "SELECT count(*) FROM ticket t where t.id_ticket in (SELECT distinct(s.id_ticket) FROM incidencias.story s where s.id_user = ?1)", 
			nativeQuery = true)
	long countByInvolvedUser(Long IdUser);
	

}
