package mx.servis.admonticket.service;

import java.util.List;

public interface RoleService {
	
	void saveRole(String role);
	List<String> getRoles();

}
