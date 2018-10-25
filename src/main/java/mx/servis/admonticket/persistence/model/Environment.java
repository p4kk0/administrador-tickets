package mx.servis.admonticket.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import mx.servis.admonticket.persistence.AbstractAuditingEntity;

@Entity
@Table(name = "environment")
public class Environment extends AbstractAuditingEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_environment", unique = true, nullable = false)
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(length = 50, unique = true, nullable = false)
	private String name;
	
	public Environment() {
		super();
	}

	public Environment(Long id) {
		super();
		this.id = id;
	}

	public Environment(Long id, String name) {
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

}
