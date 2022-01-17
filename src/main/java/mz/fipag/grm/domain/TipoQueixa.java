package mz.fipag.grm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tipo_queixa")
public class TipoQueixa extends AbstractEntity{
	
	@Column(name = "codigo", nullable = false,length=120,unique=true)
	private String codigo;
	
	
	@Column(name = "designacao", nullable = false,length=120,unique=true)
	private String designacao;
	


	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
