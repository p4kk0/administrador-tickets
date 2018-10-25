package mx.servis.admonticket.service;

import java.util.List;

import mx.servis.admonticket.dto.ProjectDTO;

public interface ProjectService {
	
	ProjectDTO create(ProjectDTO projectDTO);
	ProjectDTO update(ProjectDTO projectDTO);
	void delete(Long id);
	ProjectDTO findById(Long id);
	List<ProjectDTO> findAll();

}
