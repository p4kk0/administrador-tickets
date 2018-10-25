package mx.servis.admonticket.persistence.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "businessUnitProjection", types = { BusinessUnit.class })
public interface BusinessUnitProjection {

	@Value("#{target.id}")
	Long getId();

	String getName();

	String getPhone();

	String getAddress();

	String getDescription();

	String getContactName();

	String getContactOfficePhone();

	String getContactCellPhone();

	@Value("#{target.getClient()}")
	Client getClient();

	String getCreatedBy();

	Date getCreatedDate();

	String getLastModifiedBy();

	Date getLastModifiedDate();

}
