package mz.fipag.grm.domain;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "assunto")
public class Assunto extends AbstractEntity{

    @Column(name = "designacao", nullable = false,length=120)
    private String designacao;

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }


}
