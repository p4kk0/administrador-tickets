package mx.servis.admonticket.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import mx.servis.admonticket.dto.ProjectDTO;
import mx.servis.admonticket.persistence.model.Project;

@Mapper(componentModel = "spring", uses = {
		UnitTimeMapper.class,
		TicketMapper.class
		})
public interface ProjectMapper extends EntityMapper<ProjectDTO, Project>{
	
    @Mapping(source = "unitTime.id", target = "unitTimeId")
    ProjectDTO toDto(Project project);

    @Mapping(source = "unitTimeId", target = "unitTime")
    Project toEntity(ProjectDTO projectDTO);
	
    default Project fromId(Long id) {
        if (id == null) {
            return null;
        }
        Project project = new Project();
        project.setId(id);
        return project;
    }


}
