package mx.servis.admonticket.persistence.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import mx.servis.admonticket.persistence.AbstractAuditingEntity;

@Entity
@Table(name = "ticket")
public class Ticket extends AbstractAuditingEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id_ticket")
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 200)
	@Column(length = 200, nullable = false)
	private String name;
	
	@NotNull
	@Size(min = 1, max = 4000)
	@Column(length = 4000, columnDefinition = "text", nullable = false)
	private String comment;
	
//	@NotNull
//	@Size(min = 1, max = 4000)
//	@Column(length = 4000, columnDefinition = "text", nullable = true)
//	private String newComment;
	
	private Long reference;
	
	private Long time;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName="id_user")
	private User asignedUser;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_priority", referencedColumnName="id_priority")
	private Priority priority;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_environment", referencedColumnName="id_environment")
	private Environment environment;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_technology", referencedColumnName="id_technology")
	private Technology technology;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status", referencedColumnName="id_status")
	private Status status;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_schema_ticket", referencedColumnName="id_schema_ticket")
	private Schema schema;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project", referencedColumnName="id_project")
	private Project project;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unit_time", referencedColumnName="id_unit_time")
	private UnitTime unitTime;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ticket")
	private List<TicketAttachment> attachments;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ticket")
	private List<Story> stories;
	
	public Ticket() {
		super();
	}

	public Ticket(Long id) {
		super();
		this.id = id;
	}

	public Ticket(Long id, String name) {
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getReference() {
		return reference;
	}

	public void setReference(Long reference) {
		this.reference = reference;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public User getAsignedUser() {
		return asignedUser;
	}

	public void setAsignedUser(User asignedUser) {
		this.asignedUser = asignedUser;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public Technology getTechnology() {
		return technology;
	}

	public void setTechnology(Technology technology) {
		this.technology = technology;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public UnitTime getUnitTime() {
		return unitTime;
	}

	public void setUnitTime(UnitTime unitTime) {
		this.unitTime = unitTime;
	}

	public List<TicketAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<TicketAttachment> attachments) {
		this.attachments = attachments;
	}

	public List<Story> getStories() {
		return stories;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}
	
	

	
}
