package mx.servis.admonticket.persistence.model;

import java.util.Date;
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
@Table(name = "project")
public class Project extends AbstractAuditingEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_project", unique = true, nullable = false)
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 200)
	@Column(length = 200, unique = true, nullable = false)
	private String name;
	
	@Column(name = "start_date")
	private Date startDate = null;
	
	@Column(name = "finish_date")
	private Date finishDate = null;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project_type", referencedColumnName="id_project_type")
	private ProjectType projectType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_business_unit", referencedColumnName = "id_business_unit", nullable = false)
	private BusinessUnit businessUnit;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	private List<Ticket> tickets;
	
	public Project() {
		super();
	}

	public Project(Long id) {
		super();
		this.id = id;
	}

	public Project(Long id, String name) {
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
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

}
