package mx.servis.admonticket.persistence.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "clientProjection", types = { Client .class })
public interface ClientProjection {
	
    @Value("#{target.id}")
    Long getId();
    
    String getName();

	String getPhone();

	String getAddress();

	String getDescription();

	String getContactName();

	String getContactOfficePhone();

	String getContactCellPhone();
    
    String getImageUrl();
    
    String getCreatedBy();
    
    Date getCreatedDate();
    
    String getLastModifiedBy();
    
    Date getLastModifiedDate();

}
