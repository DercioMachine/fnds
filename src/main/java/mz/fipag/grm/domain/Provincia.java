package mz.fipag.grm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "provincia")
public class Provincia extends AbstractEntity{
	
	@Column(name = "designacao", nullable = false,length=120,unique=true)
	private String designacao;
	
	@Column(name = "codigo", nullable = false,length=120,unique=true)
	private String codigo;
	
	@Column(name = "situacao", nullable = false)
	private boolean situacao;

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
