package mz.fipag.grm.repository;


import java.util.Date;
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
    

    @Query(value="select count(*) from ocorrencia where procedencia='Não' and year(created)= :ano", nativeQuery=true)
	public Object totalDeReclamacoesNaoProcedentes(@Param("ano") int ano);
    
    
    
    @Query(value="select count(*) from ocorrencia where resolucao = 'T' and year(created)= :ano", nativeQuery=true)
	public Object totalDeReclamacoesTerminadas(@Param("ano") int ano);
    
    
    @Query(value="select count(*) from ocorrencia where resolucao != 'T' and year(created)= :ano", nativeQuery=true)
    public Object totalDeReclamacoesEmResolucao(@Param("ano") int ano);

	/*
	 * @Query(value="select *, count(*) from ocorrencia  group by categoriaid",
	 * nativeQuery=true) public List<Ocorrencia> busqueTudoAgrupadoPorCategoria();
	 */
    
    @Query(value="select * from ocorrencia where estado!='Temporario' and year(created)= :ano", nativeQuery=true)
    public List<Ocorrencia> buscarOcorrenciasActuais(@Param("ano") int ano);

    @Query(value="select designacao,  count(*) from ocorrencia, projecto where ocorrencia.projecto_id=projecto.id group by projecto_id",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorProjecto();

	@Query(value="select designacao, count(*) from ocorrencia, categoria where ocorrencia.categoriaid=categoria.id group by categoriaid",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorCategoria();

	@Query(value="select designacao, count(*) from ocorrencia, cidade where ocorrencia.cidade_id=cidade.id group by cidade_id",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorCidade();

	@Query(value="select processo, count(*) from ocorrencia, origem where ocorrencia.tipoorigem=origem.id group by processo",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorTipoOrigem();

	@Query(value="select projecto_operacao, count(*) from ocorrencia group by projecto_operacao",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorEntidade();
	
	@Query(value="select interno_externo, count(*) from ocorrencia group by interno_externo;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorDefinicao();
	
	
	@Query(value="select monthname(created) as 'mes',  \r\n"
			+ "	(select count(*) from ocorrencia where resolucao = 'T' and procedencia = 'Sim' and estado='Validado' and estado!='Temporario') as 'Terminados',\r\n"
			+ "    (select count(*) from ocorrencia where resolucao != 'T'and procedencia = 'Sim' and estado='Validado' and estado!='Temporario' ) as 'Nao Terminadas',\r\n"
			+ "	(select count(*) from ocorrencia where procedencia != 'Não' and estado='Validado' and estado!='Temporario') as Improcedentes\r\n"
			+ "from ocorrencia group by monthname(created) order by ocorrencia.id ;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorMes();
	
	@Query(value="select designacao,  \r\n"
			+ "\r\n"
			+ "(select count(*) from ocorrencia where resolucao = 'T' and procedencia = 'Sim' and estado='Validado' and estado!='Temporario') as Terminados,\r\n"
			+ "(select count(*) from ocorrencia where resolucao != 'T' and procedencia = 'Sim' and estado='Validado' and estado!='Temporario' and resolucao='R') as NaoTerminada,\r\n"
			+ "(select count(*) from ocorrencia where procedencia != 'Não' and estado='Validado' and estado!='Temporario') as Improcedentes\r\n"
			+ "\r\n"
			+ "from ocorrencia, cidade where ocorrencia.cidade_id=cidade.id group by cidade_id;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorCidadeEstado();
	
	@Query(value="select provincia.designacao,\r\n"
			+ "cast(sum(if(resolucao='T',1,0))as unsigned) as terminado, \r\n"
			+ "cast(sum(if(resolucao!='T',1,0))as unsigned) as Nterminado,\r\n"
			+ "cast(sum(if(left(procedencia,1)='N',1,0))as unsigned) as Improcedente \r\n"
			+ "from ocorrencia, cidade, provincia where ocorrencia.cidade_id = cidade.id and cidade.provincia_id = provincia.id group by provincia.designacao;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorProvinciaEstado();
	
	
	@Query(value="select forma_comunicacao, count(*) from ocorrencia group by forma_comunicacao;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorCanalDeEntrada();
	
	@Query(value="select tipo_ocorrencia.designacao, count(*) from ocorrencia, tipo_ocorrencia where ocorrencia.tipo_ocorrencia_id=tipo_ocorrencia.id group by tipo_ocorrencia_id",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorTipoDeOcorrencia();
	
	
	

	@Query(value="select regiao.designacao, count(*) from ocorrencia, cidade, regiao where ocorrencia.cidade_id = cidade.id and cidade.regiao_id = regiao.id group by regiao.designacao",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorRegiao();
	
	@Query(value="select monthname(created) as mes,\r\n"
			+ "cast(sum(if(resolucao='T',1,0))as unsigned) as terminado, \r\n"
			+ "cast(sum(if(resolucao!='T',1,0))as unsigned) as Nterminado,\r\n"
			+ "cast(sum(if(left(procedencia,1)='N',1,0))as unsigned) as Improcedente \r\n"
			+ "from ocorrencia group by month(created)",nativeQuery=true)
	public List<Object[]> busqueTnTI();
	


    
	/*
	 * @Query(
	 * value="select designacao,  count(*) from ocorrencia, projecto where ocorrencia.projecto_id=projecto.id and year(created)= :ano group by projecto_id"
	 * , nativeQuery=true) public List<Ocorrencia>
	 * buscarOcorrenciasporPro(@Param("ano") int ano);
	 */
    
    
	/*
		Ocorrencias por User
	 */
	@Query(value="select * from ocorrencia, cidade  where ocorrencia.cidade_id = cidade.id and ocorrencia.cidade_id =:local", nativeQuery=true)
	List<Ocorrencia> buscarOcorrenciasPorUsuariosRegional(@Param("local") long local);

	@Query(value="select * from ocorrencia, cidade  where ocorrencia.cidade_id = cidade.id and cidade.regiao_id =:local", nativeQuery=true)
	List<Ocorrencia> buscarOcorrenciasPorUsuariosZonas(@Param("local") long local);
	
	
/* FIM */
	
	@Query(value="select designacao, count(*) from ocorrencia, cidade where ocorrencia.cidade_id=cidade.id "
			+ " and ocorrencia.created between =:datainicial and =:datafinal and ocorrencia.projecto_id=:projecto"
			+ "group by cidade_id",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorCidadeFiltro1(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") Long projecto);


}
