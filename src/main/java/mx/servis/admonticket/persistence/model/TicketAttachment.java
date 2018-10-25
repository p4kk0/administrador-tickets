package mx.servis.admonticket.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import mx.servis.admonticket.persistence.AbstractAuditingEntity;

@Entity
@Table(name = "ticket_attachment")
public class TicketAttachment extends AbstractAuditingEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id_ticket_attachment", unique = true, nullable = false)
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 200)
	@Column(length = 200, unique = true, nullable = false)
	private String name;
	
	@NotNull
	@Size(min = 1, max = 500)
	@Column(length = 500, unique = true, nullable = false)
	private String path;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket", referencedColumnName="id_ticket", nullable = false)
	private Ticket ticket;
	
	
	public TicketAttachment() {
		super();
	}

	public TicketAttachment(Long id) {
		super();
		this.id = id;
	}

	public TicketAttachment(Long id, String name) {
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
}
