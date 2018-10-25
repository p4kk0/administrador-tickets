package mx.servis.admonticket.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.servis.admonticket.persistence.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	String USERS_BY_LOGIN_CACHE = "usersByLogin";
	
    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByLogin(String login);
    
    Optional<User> findOneByActivationKey(String resetKey);
    
    Optional<User> findOneByResetKey(String resetKey);
	
    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneWithAuthoritiesById(Long id);
    
    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneWithAuthoritiesByLogin(String login);

    @EntityGraph(attributePaths = "roles")
//    @Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
    Optional<User> findOneWithRolesByLogin(String login);

}
