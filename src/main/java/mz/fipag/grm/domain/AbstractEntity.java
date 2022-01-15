package mz.fipag.grm.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractEntity implements Serializable  {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	private Date created;

	@Temporal(TemporalType.DATE)
	@Column(name = "updated", nullable = false)
	private Date updated;
	
	/*
	 * private Long userCreated;
	 * 
	 * private Long userUpdated;
	 */
	
	public AbstractEntity() {
		super();
		created = updated=new Date();
	}
	
	@PrePersist
	public void onCreate(){
		updated = created = new Date();
	}
	
	@PreUpdate
	public void onUpdate(){
		updated = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean hasNotId() {
		return id == null;
	}

	public boolean hasId() {
		return id != null;
	}
	
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	/*
	 * public Long getUserCreated() { return userCreated; }
	 * 
	 * public void setUserCreated(Long userCreated) { this.userCreated =
	 * userCreated; }
	 * 
	 * public Long getUserUpdated() { return userUpdated; }
	 * 
	 * public void setUserUpdated(Long userUpdated) { this.userUpdated =
	 * userUpdated; }
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Entidade %s id: %s", this.getClass().getName(), getId());
	}	
}
