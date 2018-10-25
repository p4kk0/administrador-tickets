package mx.servis.admonticket.persistence.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "projectProjection", types = { Project .class })
public interface ProjectProjection {
	
	@Value("#{target.id}")
    Long getId();
    
    String getName();
    
    @Value("#{target.getBusinessUnit()}")
    BusinessUnit getBusinessUnit();
    
    @Value("#{target.getBusinessUnit().getClient()}")
    Client getClient();
    
    String getCreatedBy();
    
    Date getCreatedDate();
    
    String getLastModifiedBy();
    
    Date getLastModifiedDate();

}
