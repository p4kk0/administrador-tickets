package mx.servis.admonticket.persistence.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "technologyProjection", types = { Technology .class })
public interface TechnologyProjection {
	
    @Value("#{target.id}")
    Long getId();
    
    String getName();
    
    String getCreatedBy();
    
    Date getCreatedDate();
    
    String getLastModifiedBy();
    
    Date getLastModifiedDate();

}
