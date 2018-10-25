package mx.servis.admonticket.persistence.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "statusProjection", types = { Status .class })
public interface StatusProjection {

	@Value("#{target.id}")
    Long getId();
    
    String getName();
}
