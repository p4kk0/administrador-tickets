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
@Table(name = "unit_time")
public class UnitTime extends AbstractAuditingEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_unit_time", unique = true, nullable = false)
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 20)
	@Column(length = 20, unique = true, nullable = false)
	private String name;
	
	public UnitTime() {
		super();
	}

	public UnitTime(Long id) {
		super();
		this.id = id;
	}

	public UnitTime(Long id, String name) {
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
