package mx.servis.admonticket.dto;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import mx.servis.admonticket.persistence.model.Client;

public class BusinessUnitDTO {
	
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 200)
	private String name;
	
	@Size(max = 15)
	private String phone;
	
	@Column(length = 254)
	private String address;
	
	@Size(max = 4000)
	private String description;
	
	@Size(max = 200)
	private String contactName;
	
	@Size(max = 15)
	private String contactOfficePhone;
	
	@Size(max = 15)
	private String contactCellPhone;
	
	private Client client;
	
	private List<ProjectDTO> projects;

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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<ProjectDTO> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectDTO> projects) {
		this.projects = projects;
	}

	
}
