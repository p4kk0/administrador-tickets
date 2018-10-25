package mx.servis.admonticket.persistence.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mx.servis.admonticket.persistence.AbstractAuditingEntity;

@Entity
@Table(name = "user")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends AbstractAuditingEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_user")
	private Long id;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(length = 50, unique = true, nullable = false)
	private String login;

	@JsonIgnore
	@NotNull
	@Size(min = 60, max = 60)
	@Column(name = "password_hash", length = 60, nullable = false)
	private String password;

	@Size(max = 50)
	@Column(name = "first_name", length = 50)
	private String firstName;

	@Size(max = 50)
	@Column(name = "last_name", length = 50)
	private String lastName;
	
	@Size(max = 15)
	@Column(name = "cell_phone", length = 15)
	private String cellPhone;
	
	@Size(max = 15)
	@Column(name = "office_phone", length = 15)
	private String officePhone;

//	@Email
	@Size(min = 5, max = 254)
	@Column(length = 254, unique = true)
	private String email;

	@NotNull
	@Column(nullable = false)
	private boolean activated = false;

	@Size(min = 2, max = 6)
	@Column(name = "lang_key", length = 6)
	private String langKey;

	@Size(max = 256)
	@Column(name = "image_url", length = 256)
	private String imageUrl;

	@Size(max = 100)
	@Column(name = "activation_key", length = 100)
	@JsonIgnore
	private String activationKey;

	@Size(max = 100)
	@Column(name = "reset_key", length = 100)
	@JsonIgnore
	private String resetKey;

	@Column(name = "reset_date")
	private Date resetDate = null;
	
    @OneToOne
    @JoinColumn(name = "id_client_fk", nullable = true)
	private Client client;
    
    @OneToOne
    @JoinColumn(name = "id_business_unit_fk", nullable = true)
	private BusinessUnit businessUnit;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id_user") }, inverseJoinColumns = {
					@JoinColumn(name = "role_name", referencedColumnName = "name") })
//	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//	@BatchSize(size = 20)
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "asignedUser")
	private List<Ticket> asignedTickets;
	
	
	public User() {
		super();
	}

	public User(Long id) {
		super();
		this.id = id;
	}

	
	  public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getLogin() {
	        return login;
	    }

	    public void setLogin(String login) {
	        this.login = login;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }
	    
	    

	    public String getCellPhone() {
			return cellPhone;
		}

		public void setCellPhone(String cellPhone) {
			this.cellPhone = cellPhone;
		}

		public String getOfficePhone() {
			return officePhone;
		}

		public void setOfficePhone(String officePhone) {
			this.officePhone = officePhone;
		}

		public String getImageUrl() {
	        return imageUrl;
	    }

	    public void setImageUrl(String imageUrl) {
	        this.imageUrl = imageUrl;
	    }

	    public boolean getActivated() {
	        return activated;
	    }

	    public void setActivated(boolean activated) {
	        this.activated = activated;
	    }

	    public String getActivationKey() {
	        return activationKey;
	    }

	    public void setActivationKey(String activationKey) {
	        this.activationKey = activationKey;
	    }

	    public String getResetKey() {
	        return resetKey;
	    }

	    public void setResetKey(String resetKey) {
	        this.resetKey = resetKey;
	    }

	    public Date getResetDate() {
	        return resetDate;
	    }

	    public void setResetDate(Date resetDate) {
	        this.resetDate = resetDate;
	    }

	    public String getLangKey() {
	        return langKey;
	    }

	    public void setLangKey(String langKey) {
	        this.langKey = langKey;
	    }

	    public Set<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<Role> roles) {
	        this.roles = roles;
	    }

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}

		public BusinessUnit getBusinessUnit() {
			return businessUnit;
		}

		public void setBusinessUnit(BusinessUnit businessUnit) {
			this.businessUnit = businessUnit;
		}

		public List<Ticket> getAsignedTickets() {
			return asignedTickets;
		}

		public void setAsignedTickets(List<Ticket> asignedTickets) {
			this.asignedTickets = asignedTickets;
		}
	    
	    

}
