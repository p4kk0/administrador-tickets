package mx.servis.admonticket.persistence.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "schemaProjection", types = { Schema .class })
public interface SchemaProjection {

	@Value("#{target.id}")
    Long getId();
    
    String getName();
}
