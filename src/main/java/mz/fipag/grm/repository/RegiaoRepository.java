package mz.fipag.grm.repository;

import mz.fipag.grm.domain.Origem;
import mz.fipag.grm.domain.Regiao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegiaoRepository extends CrudRepository<Regiao, Long> {

    @Query(value="select * from regiao where designacao = 'Nacional'", nativeQuery=true)
    Regiao findByDesignacao();

}
