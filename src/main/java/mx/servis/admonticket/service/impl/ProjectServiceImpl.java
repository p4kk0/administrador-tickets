package mx.servis.admonticket.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.servis.admonticket.dto.ProjectDTO;
import mx.servis.admonticket.persistence.model.Project;
import mx.servis.admonticket.respository.ProjectRepository;
import mx.servis.admonticket.rest.error.AppException;
import mx.servis.admonticket.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProjectDTO create(ProjectDTO projectDTO) {
		Project ticket = modelMapper.map(projectDTO, Project.class);
		Project ticketNew = projectRepository.save( ticket );
		return modelMapper.map(ticketNew, ProjectDTO.class);
	}

	@Override
	public ProjectDTO update(ProjectDTO projectDTO) {
		Project ticket = modelMapper.map(projectDTO, Project.class);
		Project ticketNew = projectRepository.save( ticket );
		return modelMapper.map(ticketNew, ProjectDTO.class);
	}

	@Override
	public void delete(Long id) {
		Optional<Project> ticketOpt = projectRepository.findById( id );
		if( !ticketOpt.isPresent() ) {
			throw new AppException("El proyecto con " + id + " no existe en la base de datos");
		}
		projectRepository.deleteById( id );
		
	}

	@Override
	public ProjectDTO findById(Long id) {
		Optional<Project> ticketOpt = projectRepository.findById( id );
		if( !ticketOpt.isPresent() ) {
			throw new AppException("El proyecto con " + id + " no existe");
		}
		return modelMapper.map( ticketOpt.get(),  ProjectDTO.class);
	}

	@Override
	public List<ProjectDTO> findAll() {
		return projectRepository.findAll().stream()
				.map( ticket -> modelMapper.map(ticket, ProjectDTO.class))
				.collect(Collectors.toList());
	}

}
