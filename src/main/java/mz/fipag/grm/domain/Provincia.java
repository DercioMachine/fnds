package mz.fipag.grm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name = "provincia")
public class Provincia extends AbstractEntity{
	
	@Column(name = "designacao", nullable = false,length=120,unique=true)
	private String designacao;
	
	@Column(name = "situacao", nullable = false)
	private boolean situacao;


	@OneToMany(mappedBy = "provincia")
	private List<Distrito> distritos;

	public List<Distrito> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
	
	
}
