package mx.servis.admonticket.persistence.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "unitTimeProjection", types = { UnitTime .class })
public interface UnitTimeProjection {

	@Value("#{target.id}")
    Long getId();
    
    String getName();
}
