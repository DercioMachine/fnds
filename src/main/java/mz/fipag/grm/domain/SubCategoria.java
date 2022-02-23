package mz.fipag.grm.domain;

import javax.persistence.*;


@Entity
@Table(name = "subcategoria")
public class SubCategoria extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	@Column(name = "designacao", nullable = false,length=120,unique=true)
	private String designacao;

	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;


	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
