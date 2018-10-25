package mx.servis.admonticket.persistence.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "priorityProjection", types = { Priority .class })
public interface PriorityProjection {

	@Value("#{target.id}")
    Long getId();
    
    String getName();
}
