package mx.servis.admonticket.persistence.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "projectTypeProjection", types = { ProjectType .class })
public interface ProjectTypeProjection {
	
	@Value("#{target.id}")
    Long getId();
    
    String getName();

}
