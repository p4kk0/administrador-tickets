package mx.servis.admonticket.rest.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.servis.admonticket.dto.RoleDTO;
import mx.servis.admonticket.rest.error.AppException;
import mx.servis.admonticket.rest.error.RestException;
import mx.servis.admonticket.service.RoleService;

@RestController
@RequestMapping("/api")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/roles")
	public ResponseEntity<List<String>> getRoles() throws URISyntaxException {

		List<String> roles = roleService.getRoles();
		return ResponseEntity.ok().body(roles);
	}
	
	
	@PostMapping("/roles")
	public ResponseEntity<RoleDTO> registerRole(@Valid @RequestBody RoleDTO roleDTO)  throws URISyntaxException{
		
		System.out.println("Creando ROLE: " + roleDTO.getName());
		try {
			roleService.saveRole( roleDTO.getName() );
			return ResponseEntity.created(new URI("/api/roles/" )).body(roleDTO);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
