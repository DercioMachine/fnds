package mz.fipag.grm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "processo")
public class Processo extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "nome")
    private String nome;

    @Column(name = "tempotratamento")
    private int tempotratamento;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempotratamento() {
        return tempotratamento;
    }

    public void setTempotratamento(int tempotratamento) {
        this.tempotratamento = tempotratamento;
    }

}
