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
@Table(name = "story")
public class Story extends AbstractAuditingEntity{
	
	@Id
	@GeneratedValue
	@Column(name="id_story")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket", referencedColumnName="id_ticket")
	private Ticket ticket;
	
	@NotNull
	@Size(min = 1, max = 4000)
	@Column(length = 4000, columnDefinition = "text")
	private String comment;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName="id_user")
	private User asignedUser;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status", referencedColumnName="id_status")
	private Status status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "story")
	private List<StoryAttachment> attachments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getAsignedUser() {
		return asignedUser;
	}

	public void setAsignedUser(User asignedUser) {
		this.asignedUser = asignedUser;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<StoryAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<StoryAttachment> attachments) {
		this.attachments = attachments;
	}
	
	
	

}
