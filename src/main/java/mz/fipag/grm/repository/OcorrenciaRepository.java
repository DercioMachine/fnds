package mz.fipag.grm.repository;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.Ocorrencia;

@Repository
public interface OcorrenciaRepository extends CrudRepository<Ocorrencia, Long> {

    @Query(value="select * from Ocorrencia where grm_stamp=:codigo", nativeQuery=true)
    public Ocorrencia findAllByCodigo(@Param("codigo") Long codigo);
    
	/* QUERIES DAS PRIMEIRAS CARDS */
    @Query(value="select count(*) from ocorrencia where estado!='Temporario' and year(created)= :ano", nativeQuery=true)
	public Object totalDeOcorrencias(@Param("ano") int ano);
    
    @Query(value="select count(*) from Ocorrencia where procedencia='Sim' and estado='Validado' and year(created)= :ano", nativeQuery=true)
   	public Object totalDeOcorrenciasProcedentes(@Param("ano") int ano);
    
    @Query(value="select count(*) from Ocorrencia where procedencia='Não' and estado='Validado' and year(created)= :ano", nativeQuery=true)
	public Object totalDeOcorrenciasImprocedentes(@Param("ano") int ano);
    
    @Query(value="select count(*) from ocorrencia where estado='Registado' and year(created)= :ano", nativeQuery=true)
   	public Object totalDeOcorrenciasPorValidar(@Param("ano") int ano);
    
    
	/* FIM */
    
	/*--------------------------------------------------------------------------------------------------------*/
    
    /* QUERIES DAS PRIMEIRAS CARDS 
     * 
     * Todas as ocorrencias procedentes
     * */
    @Query(value="select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id=1 and year(created)= :ano", nativeQuery=true)
   	public Object totalDeReclamacoesProcedentes(@Param("ano") int ano);
    

    @Query(value="select count(*) from ocorrencia where tipo_ocorrencia_id!=1 and procedencia='Sim' and estado='Validado' and year(created)= :ano", nativeQuery=true)
	public Object totalDeReclamacoesNaoProcedentes(@Param("ano") int ano);
    
    
    
    @Query(value="select count(*) from ocorrencia where procedencia='Sim' and tipo_ocorrencia_id=1 and resolucao = 'T' and year(created)= :ano", nativeQuery=true)
	public Object totalDeReclamacoesTerminadas(@Param("ano") int ano);
    
    
    @Query(value="select count(*) from ocorrencia where tipo_ocorrencia_id=1 and procedencia='Sim' and resolucao = 'R' and year(created)= :ano", nativeQuery=true)
    public Object totalDeReclamacoesEmResolucao(@Param("ano") int ano);

    @Query(value="select *, count(*) from ocorrencia  group by categoriaid", nativeQuery=true)
    public List<Ocorrencia> busqueTudoAgrupadoPorCategoria();
    
   
    
	/*
	 * @Query(value="select date_format(created, '%Y/%m') mes\r\n" +
	 * ", count(*) \r\n" + "from ocorrencia\r\n" +
	 * "where created > date_sub(now(), interval 6 month)\r\n" +
	 * "group by date_format(created, '%Y/%m')\r\n" +
	 * "order by date_format(created, '%Y/%m') desc;", nativeQuery=true) public
	 * Object totalDeOcorrenciasPorMes();
	 */
}
