package mx.servis.admonticket.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.servis.admonticket.persistence.model.Project;
import mx.servis.admonticket.persistence.model.Ticket;

@Repository("projectRepository")
public interface ProjectRepository extends JpaRepository<Project, Long>{

}
