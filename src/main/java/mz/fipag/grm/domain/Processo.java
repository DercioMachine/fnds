package mz.fipag.grm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "processo")
public class Processo extends AbstractEntity {

    @Column(name = "nome", nullable = false,length=120)
    private String nome;

    @Column(name = "tempotratamento", nullable = false)
    private int tempotratamento;

    @Column(name = "tempoinvestigacao", nullable = false)
    private int tempoinvestigacao;

    @Column(name = "temporesolucao", nullable = false)
    private int temporesolucao;

    @Column(name = "temposign", nullable = false)
    private int temposign;

    @Column(name = "tempomonitoria", nullable = false)
    private int tempomonitoria;

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

    public int getTempoinvestigacao() {
        return tempoinvestigacao;
    }

    public void setTempoinvestigacao(int tempoinvestigacao) {
        this.tempoinvestigacao = tempoinvestigacao;
    }

    public int getTemporesolucao() {
        return temporesolucao;
    }

    public void setTemporesolucao(int temporesolucao) {
        this.temporesolucao = temporesolucao;
    }

    public int getTemposign() {
        return temposign;
    }

    public void setTemposign(int temposign) {
        this.temposign = temposign;
    }

    public int getTempomonitoria() {
        return tempomonitoria;
    }

    public void setTempomonitoria(int tempomonitoria) {
        this.tempomonitoria = tempomonitoria;
    }
}
