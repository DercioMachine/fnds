package mz.fipag.grm.domain;

import javax.persistence.*;

@Entity
@Table(name = "regiao")
public class Regiao extends AbstractEntity{

    @Column(name = "designacao", nullable = false,length=120)
    private String designacao;

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }


}
