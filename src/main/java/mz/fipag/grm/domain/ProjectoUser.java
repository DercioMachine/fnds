package mz.fipag.grm.domain;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "projectouser")
public class ProjectoUser extends AbstractEntity{

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name="projecto_id")
	private Projecto projecto;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Projecto getProjecto() {
		return projecto;
	}

	public void setProjecto(Projecto projecto) {
		this.projecto = projecto;
	}
}
