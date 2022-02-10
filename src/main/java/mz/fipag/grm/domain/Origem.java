package mz.fipag.grm.domain;

import javax.persistence.*;

@Entity
@Table(name = "origem")
public class Origem extends AbstractEntity{

    @Column(name = "nome", nullable = false,length=120)
    private String nome;

    @Column(name = "designacao", nullable = false,length=120)
    private String designacao;

    @Column(name = "processo", nullable = false,length=120)
    private String processo;

    @Column(name = "tipo", nullable = false,length=120)
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getProcesso() {
        return processo;
    }

    public void setProcesso(String processo) {
        this.processo = processo;
    }
}
