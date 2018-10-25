package mx.servis.admonticket.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "role")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role {
	
    @NotNull
    @Size(max = 50)
    @Id
    @Column(length = 50)
    private String name;
    
    public Role() {
		super();
	}
    
    public Role(String name) {
		super();
		this.name = name;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return name;
	}
    
    
}
