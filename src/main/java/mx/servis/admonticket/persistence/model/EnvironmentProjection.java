package mx.servis.admonticket.persistence.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "environmentProjection", types = { Environment .class })
public interface EnvironmentProjection {
	
	@Value("#{target.id}")
    Long getId();
    
    String getName();
}
