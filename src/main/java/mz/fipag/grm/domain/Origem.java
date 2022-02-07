package mz.fipag.grm.domain;

import javax.persistence.*;

@Entity
@Table(name = "origem")
public class Origem extends AbstractEntity{

    @Column(name = "nome", nullable = false,length=120)
    private String nome;

    @Column(name = "designacao", nullable = false,length=120)
    private String designacao;

    @ManyToOne
    @JoinColumn(name="processo_id")
    private Processo processo;

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

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }
}
