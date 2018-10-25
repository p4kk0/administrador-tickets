package mx.servis.admonticket.rest.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.servis.admonticket.dto.ProjectDTO;
import mx.servis.admonticket.rest.error.AppException;
import mx.servis.admonticket.rest.error.RestException;
import mx.servis.admonticket.service.ProjectService;

@RestController
@RequestMapping("/api")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/projects")
	public ResponseEntity<List<ProjectDTO>> getAll()  throws URISyntaxException{
		try {
			
			List<ProjectDTO> projects = projectService.findAll();
			return ResponseEntity.ok().body(projects);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/projects/{id}")
	public ResponseEntity<ProjectDTO> getProject(@PathVariable Long id)  throws URISyntaxException{
		try {
			ProjectDTO project = projectService.findById(id);
			return ResponseEntity.ok().body(project);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PostMapping("/projects")
	public ResponseEntity<ProjectDTO> create(@Valid @RequestBody ProjectDTO projectDTO)  throws URISyntaxException{
		try {
			ProjectDTO newProjectDTO = projectService.create( projectDTO );
			return ResponseEntity.created(new URI("/api/projects/" + newProjectDTO.getId())).body(newProjectDTO);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/projects")
	public ResponseEntity<ProjectDTO> editAccount(@Valid @RequestBody ProjectDTO projectDTO)  throws URISyntaxException{
		try {
			ProjectDTO updatedProjectDTO = projectService.update( projectDTO );
			return ResponseEntity.ok().body(updatedProjectDTO);
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/projects/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable Long id)  throws URISyntaxException{

		try {
			projectService.delete(id);
			return ResponseEntity.ok().build();
		}catch (AppException e) {
			throw new RestException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RestException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
