package mz.fipag.grm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cidade")
public class Cidade extends AbstractEntity{

	//private static final long serialVersionUID = 1L;

	@Column(name = "designacao", nullable = false,length=120,unique=true)
	private String designacao;	
	
	@ManyToOne
	@JoinColumn(name="provincia_id")
	private Provincia provincia;

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}
