package mx.servis.admonticket.persistence.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "storyProjection", types = { Story .class })
public interface StoryProjection {
	
	@Value("#{target.id}")
    Long getId();
    
    String getComment();
    
    @Value("#{target.getAsignedUser().getLogin()}")
    String getAsigenedUser();
    
    @Value("#{target.getStatus().getName()}")
    String getStatus();
    
    String getCreatedBy();
    
    Date getCreatedDate();
    
    String getLastModifiedBy();
    
    Date getLastModifiedDate();
}
