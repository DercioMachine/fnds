package mz.fipag.grm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "responsabilidade")
public class Responsabilidade extends AbstractEntity {

    @Column(name = "level", nullable = false,length=120,unique=true)
    private String level;


    @Column(name = "responsavel", nullable = false,length=120,unique=true)
    private String responsavel;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}
