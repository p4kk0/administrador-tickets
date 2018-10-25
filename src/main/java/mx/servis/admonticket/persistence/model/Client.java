package mx.servis.admonticket.persistence.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import mx.servis.admonticket.persistence.AbstractAuditingEntity;

@Entity
@Table(name = "client")
public class Client extends AbstractAuditingEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id_client", unique = true, nullable = false)
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 200)
	@Column(length = 200, unique = true, nullable = false)
	private String name;
	
//	@NotNull
	@Size(max = 200)
	@Column(length = 200)
	private String imageUrl;
	
	@Size(max = 15)
	@Column(length = 15)
	private String phone;
	
	@Size(max = 254)
	@Column(length = 254)
	private String address;
	
	@Size(max = 4000)
	@Column(length = 4000, columnDefinition = "text", nullable = true)
	private String description;
	
	@Size(max = 200)
	@Column(length = 200)
	private String contactName;
	
	@Size(max = 15)
	@Column(length = 15)
	private String contactOfficePhone;
	
	@Size(max = 15)
	@Column(length = 15)
	private String contactCellPhone;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "client")
	private List<BusinessUnit> businessUnits;
	
	public Client() {
		super();
	}

	public Client(Long id) {
		super();
		this.id = id;
	}

	public Client(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<BusinessUnit> getBusinessUnits() {
		return businessUnits;
	}

	public void setBusinessUnits(List<BusinessUnit> businessUnits) {
		this.businessUnits = businessUnits;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactOfficePhone() {
		return contactOfficePhone;
	}

	public void setContactOfficePhone(String contactOfficePhone) {
		this.contactOfficePhone = contactOfficePhone;
	}

	public String getContactCellPhone() {
		return contactCellPhone;
	}

	public void setContactCellPhone(String contactCellPhone) {
		this.contactCellPhone = contactCellPhone;
	}

	
	
}
