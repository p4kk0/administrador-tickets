package mx.servis.admonticket.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.servis.admonticket.persistence.model.Role;
import mx.servis.admonticket.respository.RoleRepository;
import mx.servis.admonticket.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void saveRole(String role) {
		roleRepository.save(new Role( role ));
	}

	@Override
	public List<String> getRoles() {
		return roleRepository.findAll().stream()
				.map(role -> role.getName())
				.collect(Collectors.toList());
	}

}
