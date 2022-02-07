package mz.fipag.grm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "processo")
public class Processo extends AbstractEntity {

    @Column(name = "nome", nullable = false,length=120)
    private String nome;

    @Column(name = "descricao", nullable = false,length=120)
    private String descricao;

    @Column(name = "responsavel", nullable = false,length=120)
    private String responsavel;

    @Column(name = "tempo", nullable = false,length=120)
    private String tempo;

    @Column(name = "tipo", nullable = false,length=120)
    private String tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
