package mx.servis.admonticket.persistence;

import java.io.Serializable;
//import java.time.Date;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mx.servis.admonticket.security.SecurityUtils;

@MappedSuperclass
@Audited
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditingEntity implements Serializable{
	
	  private static final long serialVersionUID = 1L;

	    @CreatedBy
	    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
	    @JsonIgnore
	    private String createdBy;

	    @CreatedDate
	    @Column(name = "created_date", nullable = false, updatable = false)
//	    @Column(name = "created_date")
	    @JsonIgnore
	    private Date createdDate = new Date();

	    @LastModifiedBy
	    @Column(name = "last_modified_by", length = 50)
	    @JsonIgnore
	    private String lastModifiedBy;

	    @LastModifiedDate
	    @Column(name = "last_modified_date")
	    @JsonIgnore
	    private Date lastModifiedDate = new Date();
	    
	    @PrePersist
	    public void onPrePersist() {
	        setCreatedDate(new Date());
	        setCreatedBy(SecurityUtils.getCurrentUserLogin().get());
	    }
	      
	    @PreUpdate
	    public void onPreUpdate() {
	        setLastModifiedDate(new Date());
	        setLastModifiedBy(SecurityUtils.getCurrentUserLogin().get());
	    }
	      
//	    @PreRemove
//	    public void onPreRemove() {
//	        audit("DELETE");
//	    }

	    public String getCreatedBy() {
	        return createdBy;
	    }

	    public void setCreatedBy(String createdBy) {
	        this.createdBy = createdBy;
	    }

	    public Date getCreatedDate() {
	        return createdDate;
	    }

	    public void setCreatedDate(Date createdDate) {
	        this.createdDate = createdDate;
	    }

	    public String getLastModifiedBy() {
	        return lastModifiedBy;
	    }

	    public void setLastModifiedBy(String lastModifiedBy) {
	        this.lastModifiedBy = lastModifiedBy;
	    }

	    public Date getLastModifiedDate() {
	        return lastModifiedDate;
	    }

	    public void setLastModifiedDate(Date lastModifiedDate) {
	        this.lastModifiedDate = lastModifiedDate;
	    }

}
