package mz.fipag.grm.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.Ocorrencia;

@Repository
public interface OcorrenciaRepository extends CrudRepository<Ocorrencia, Long> {

    @Query(value="select * from Ocorrencia where grm_stamp=:codigo", nativeQuery=true)
    public Ocorrencia findAllByCodigo(@Param("codigo") Long codigo);


}
