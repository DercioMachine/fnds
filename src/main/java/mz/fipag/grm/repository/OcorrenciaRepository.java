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
    
    
    @Query(value="select count(*) from Ocorrencia", nativeQuery=true)
	public Object totalDeOcorrencias();
    
    @Query(value="select count(*) from Ocorrencia where tipo_ocorrencia_id=1", nativeQuery=true)
   	public Object totalDeReclamacoes();

    @Query(value="select count(*) from Ocorrencia where tipo_ocorrencia_id=3", nativeQuery=true)
	public Object totalDeSugestoes();
}
