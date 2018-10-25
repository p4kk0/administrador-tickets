package mx.servis.admonticket.security;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mx.servis.admonticket.persistence.model.User;
import mx.servis.admonticket.respository.UserRepository;
import mx.servis.admonticket.rest.error.UserNotActivatedException;

@Service
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
	        
	        Optional<User> userByLoginFromDatabase = userRepository.findOneWithRolesByLogin(login);
	        return userByLoginFromDatabase.map(user -> createSpringSecurityUser(login, user))
	            .orElseThrow(() -> new UsernameNotFoundException("El usuario " + login + " no esta registrado"));
	}
	
    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String login, User user) {
        if (!user.getActivated()) {
            throw new UserNotActivatedException("El usuario " + login + " no esta activo");
        }
        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
            user.getPassword(),
            grantedAuthorities);
    }

}
