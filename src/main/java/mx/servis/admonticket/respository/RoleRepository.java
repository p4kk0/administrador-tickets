package mx.servis.admonticket.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.servis.admonticket.persistence.model.Role;


/**
 * Spring Data JPA repository for the Authority entity.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
