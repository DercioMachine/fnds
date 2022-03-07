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
    
    
    @Query(value="Select count(*) from ocorrencia, projecto\r\n"
    		+ "where ocorrencia.projecto_id=projecto.id and  estado!='Temporario' and year(ocorrencia.created)=(:ano) and\r\n"
    		+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
    		+ "IF(:radioButton='',1=1,\r\n"
    		+ "CASE\r\n"
    		+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
    		+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
    		+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
    		+ "END);", nativeQuery=true)
    public Object totalDeOcorrenciasFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);
    
    
    @Query(value="Select count(*) from ocorrencia, projecto\r\n"
			+ " where ocorrencia.projecto_id=projecto.id and  estado!='Temporario' and if (:projecto='', 1=1,projecto.designacao =(:projecto))\r\n"
			+ " and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal))",nativeQuery=true)
    public Object  totalDeOcorrenciasFiltro1(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);

    
    
    @Query(value="select count(*) from Ocorrencia where procedencia='Sim' and year(created)= :ano", nativeQuery=true)
   	public Object totalDeOcorrenciasProcedentes(@Param("ano") int ano);
    
    
    @Query(value="Select count(*) from ocorrencia, projecto\r\n"
    		+ "where ocorrencia.projecto_id=projecto.id and  procedencia='Sim' and year(ocorrencia.created)=(:ano) and\r\n"
    		+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
    		+ "IF(:radioButton='',1=1,\r\n"
    		+ "CASE\r\n"
    		+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
    		+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
    		+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
    		+ "END);", nativeQuery=true)
    
   	public Object totalDeOcorrenciasProcedentesFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);
    
    @Query(value="select count(*) from Ocorrencia where procedencia='Não' and estado='Validado' and year(created)= :ano", nativeQuery=true)
	public Object totalDeOcorrenciasImprocedentes(@Param("ano") int ano);
    
    
    @Query(value="Select count(*) from ocorrencia, projecto\r\n"
    		+ "where ocorrencia.projecto_id=projecto.id and  procedencia='Não' and estado='Validado' and year(ocorrencia.created)=(:ano) and\r\n"
    		+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
    		+ "IF(:radioButton='',1=1,\r\n"
    		+ "CASE\r\n"
    		+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
    		+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
    		+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
    		+ "END);", nativeQuery=true)
    public Object totalDeOcorrenciasImprocedentesFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);
    
    
    @Query(value="Select count(*) from ocorrencia, projecto\r\n"
			+ " where ocorrencia.projecto_id=projecto.id and  procedencia='Não' and estado='Validado' and if (:projecto='', 1=1,projecto.designacao =(:projecto))\r\n"
			+ " and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal))",nativeQuery=true)
    public Object  totalDeOcorrenciasImprocedentesFiltro1(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);
    
    
    @Query(value="select count(*) from ocorrencia where estado='Registado' and year(created)= :ano", nativeQuery=true)
   	public Object totalDeOcorrenciasPorValidar(@Param("ano") int ano);
    
    
    @Query(value="Select count(*) from ocorrencia, projecto\r\n"
    		+ "where ocorrencia.projecto_id=projecto.id and estado='Registado' and year(ocorrencia.created)=(:ano) and\r\n"
    		+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
    		+ "IF(:radioButton='',1=1,\r\n"
    		+ "CASE\r\n"
    		+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
    		+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
    		+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
    		+ "END);", nativeQuery=true)
    public Object totalDeOcorrenciasPorValidarFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);
    
    
    @Query(value="Select count(*) from ocorrencia, projecto\r\n"
			+ " where ocorrencia.projecto_id=projecto.id and  estado='Registado' and if (:projecto='', 1=1,projecto.designacao =(:projecto))\r\n"
			+ " and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal))",nativeQuery=true)
    public Object  totalDeOcorrenciasPorValidarFiltro1(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);
    
    
    
	/* FIM */
    
	/*--------------------------------------------------------------------------------------------------------*/
    
    /* QUERIES DAS PRIMEIRAS CARDS 
     * 
     * Todas as ocorrencias procedentes
     * */
    @Query(value="select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id=1 and year(created)= :ano", nativeQuery=true)
   	public Object totalDeReclamacoesProcedentes(@Param("ano") int ano);
    
    
    @Query(value="Select count(*) from ocorrencia, projecto\r\n"
    		+ "where ocorrencia.projecto_id=projecto.id and  procedencia='Sim' and tipo_ocorrencia_id=1 and year(ocorrencia.created)=(:ano) and\r\n"
    		+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
    		+ "IF(:radioButton='',1=1,\r\n"
    		+ "CASE\r\n"
    		+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
    		+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
    		+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
    		+ "END);", nativeQuery=true)
    
   	public Object totalDeReclamacoesProcedentesFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);
    
    
    @Query(value="select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id!=1 and year(created)= :ano", nativeQuery=true)
   	public Object totalDeOcorrenciasNaoReclamacoes(@Param("ano") int ano);
    
    @Query(value="Select count(*) from ocorrencia, projecto\r\n"
    		+ "where ocorrencia.projecto_id=projecto.id and procedencia='Sim' and tipo_ocorrencia_id!=1 and year(ocorrencia.created)=(:ano) and\r\n"
    		+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
    		+ "IF(:radioButton='',1=1,\r\n"
    		+ "CASE\r\n"
    		+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
    		+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
    		+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
    		+ "END);", nativeQuery=true)
    
   	public Object totalDeOcorrenciasNaoReclamacoesFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);
    
    
    

    @Query(value="select count(*) from ocorrencia where procedencia='Não' and year(created)= :ano", nativeQuery=true)
	public Object totalDeReclamacoesNaoProcedentes(@Param("ano") int ano);
    
    
    
    @Query(value="select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1 and year(created)= :ano", nativeQuery=true)
	public Object totalDeReclamacoesTerminadas(@Param("ano") int ano);
    
    @Query(value="Select count(*) from ocorrencia, projecto\r\n"
    		+ "where ocorrencia.projecto_id=projecto.id and resolucao = 'T' and tipo_ocorrencia_id=1 and year(ocorrencia.created)=(:ano) and\r\n"
    		+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
    		+ "IF(:radioButton='',1=1,\r\n"
    		+ "CASE\r\n"
    		+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
    		+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
    		+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
    		+ "END);", nativeQuery=true)
    
   	public Object totalDeReclamacoesTerminadasFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);
    
    
    
    @Query(value="select count(*) from ocorrencia where resolucao != 'T' and year(created)= :ano", nativeQuery=true)
    public Object totalDeReclamacoesEmResolucao(@Param("ano") int ano);

	/*
	 * @Query(value="select *, count(*) from ocorrencia  group by categoriaid",
	 * nativeQuery=true) public List<Ocorrencia> busqueTudoAgrupadoPorCategoria();
	 */
    
    @Query(value="select * from ocorrencia where estado!='Temporario' and year(created)= :ano", nativeQuery=true)
    public List<Ocorrencia> buscarOcorrenciasActuais(@Param("ano") int ano);

    @Query(value="select projecto.designacao as mes,\r\n"
    		+ "(select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1 and year(created)),\r\n"
    		+ "(select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id=1 and year(created)),\r\n"
    		+ "(select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id!=1 and year(created))\r\n"
    		+ "from ocorrencia, projecto where ocorrencia.projecto_id=projecto.id  group by projecto.designacao;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorProjecto();

	@Query(value="select designacao, count(*) from ocorrencia, categoria where tipo_ocorrencia_id=1 and procedencia='Sim' and ocorrencia.categoriaid=categoria.id group by categoriaid",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorCategoria();
	
	
	
	@Query(value="select forma_comunicacao, count(*) from ocorrencia where tipo_ocorrencia_id=1 and procedencia='Sim' group by forma_comunicacao;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorCanalDeEntrada();
	

	@Query(value="select designacao, count(*) from ocorrencia, provincia where tipo_ocorrencia_id=1 and procedencia='Sim' and ocorrencia.provincia_id=provincia.id group by provincia_id",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorCidade();

	@Query(value="select processo, count(*) from ocorrencia, origem where ocorrencia.tipoorigem=origem.id group by processo",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorTipoOrigem();

	@Query(value="select projecto_operacao, count(*) from ocorrencia group by projecto_operacao",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorEntidade();
	
	@Query(value="select interno_externo, count(*) from ocorrencia group by interno_externo;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorDefinicao();
	
	
	@Query(value="select monthname(created) as 'mes',  \r\n"
			+ "	(select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1) as 'Terminados',\r\n"
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
			+ "from ocorrencia, provincia where ocorrencia.provincia_id=provincia.id group by provincia_id;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorCidadeEstado();
	
	@Query(value="select provincia.designacao as mes,\r\n"
			+ "(select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1 and year(created)),\r\n"
			+ "(select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id=1 and year(created)),\r\n"
			+ "(select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id!=1 and year(created))\r\n"
			+ "from ocorrencia, provincia where ocorrencia.provincia_id=provincia.id group by provincia.designacao;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorProvinciaEstado();
	
	
	
	
	@Query(value="select tipo_ocorrencia.designacao, count(*) from ocorrencia, tipo_ocorrencia where ocorrencia.tipo_ocorrencia_id=tipo_ocorrencia.id group by tipo_ocorrencia_id",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorTipoDeOcorrencia();
	
	
	

	/*@Query(value="select regiao.designacao, count(*) from ocorrencia, cidade, regiao where ocorrencia.cidade_id = cidade.id and cidade.regiao_id = regiao.id group by regiao.designacao",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorRegiao();
	
	@Query(value="select monthname(created) as mes,\r\n"
			+ "cast(sum(if(resolucao='T',1,0))as unsigned) as terminado, \r\n"
			+ "cast(sum(if(resolucao!='T',1,0))as unsigned) as Nterminado,\r\n"
			+ "cast(sum(if(left(procedencia,1)='N',1,0))as unsigned) as Improcedente \r\n"
			+ "from ocorrencia group by month(created)",nativeQuery=true)
	public List<Object[]> busqueTnTI();*/
	

	@Query(value="select projecto.designacao, count(*) from ocorrencia, projecto where tipo_ocorrencia_id=1 and procedencia='Sim' and ocorrencia.projecto_id = projecto.id group by projecto.designacao",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorRegiao();
	
	@Query(value="select monthname(created) as mes,\r\n"
			+ "	(select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1 and year(created)),\r\n"
			+ "	(select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id=1 and year(created)),\r\n"
			+ "	(select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id!=1 and year(created))\r\n"
			+ "	from ocorrencia group by month(created);",nativeQuery=true)
	public List<Object[]> busqueTnTI();
	
	
    
	/*
	 * @Query(
	 * value="select designacao,  count(*) from ocorrencia, projecto where ocorrencia.projecto_id=projecto.id and year(created)= :ano group by projecto_id"
	 * , nativeQuery=true) public List<Ocorrencia>
	 * buscarOcorrenciasporPro(@Param("ano") int ano);
	 */
    
	
/* FIM */
	
	@Query(value="Select provincia.designacao,count(*) from ocorrencia,provincia,projecto\r\n"
			+ " where tipo_ocorrencia_id=1 and procedencia='Sim' and ocorrencia.provincia_id=provincia.id and ocorrencia.projecto_id=projecto.id and if (:projecto='', 1=1,projecto.designacao =(:projecto))\r\n"
			+ " and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal)) group by provincia.id",nativeQuery=true)
	
	public List<Object[]> busqueTudoAgrupadoPorCidadeFiltro1(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);

	
	 @Query(value="Select provincia.designacao,count(*) from ocorrencia,projecto, provincia \r\n"
	    		+ "where  ocorrencia.provincia_id=provincia.id and ocorrencia.projecto_id=projecto.id  and  procedencia='Sim' and year(ocorrencia.created)=(:ano) and\r\n"
	    		+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
	    		+ "IF(:radioButton='',1=1,\r\n"
	    		+ "CASE\r\n"
	    		+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
	    		+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
	    		+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
	    		+ "END) group by provincia.designacao;", nativeQuery=true)
	    
	 public List<Object[]> busqueTudoAgrupadoPorCidadeFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);
	
	
	 @Query(value="Select projecto.designacao,count(*) from ocorrencia,projecto \r\n"
	    		+ "where ocorrencia.projecto_id=projecto.id and procedencia='Sim' and year(ocorrencia.created)=(:ano) and\r\n"
	    		+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
	    		+ "IF(:radioButton='',1=1,\r\n"
	    		+ "CASE\r\n"
	    		+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
	    		+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
	    		+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
	    		+ "END) group by projecto.designacao;", nativeQuery=true)
	    
	 public List<Object[]> busqueTudoAgrupadoPorRegiaoFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);
	
	
	
	 
	 @Query(value="Select forma_comunicacao,count(*) from ocorrencia, projecto \r\n"
	    		+ "where ocorrencia.projecto_id=projecto.id and procedencia='Sim' and year(ocorrencia.created)=(:ano) and\r\n"
	    		+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
	    		+ "IF(:radioButton='',1=1,\r\n"
	    		+ "CASE\r\n"
	    		+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
	    		+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
	    		+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
	    		+ "END) group by forma_comunicacao;", nativeQuery=true)
	    
	 public List<Object[]> busqueTudoAgrupadoPorCanalEntradaFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);
	
	
	 @Query(value="Select categoria.designacao,count(*) from ocorrencia, projecto, categoria \r\n"
	    		+ "where ocorrencia.categoriaid=categoria.id and ocorrencia.projecto_id=projecto.id and procedencia='Sim' and year(ocorrencia.created)=(:ano) and\r\n"
	    		+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
	    		+ "IF(:radioButton='',1=1,\r\n"
	    		+ "CASE\r\n"
	    		+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
	    		+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
	    		+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
	    		+ "END) group by categoria.designacao;", nativeQuery=true)
	    
	 public List<Object[]> busqueTudoAgrupadoPorCategoriaFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);
	
	
	 
	 

	@Query(value="Select projecto.designacao,count(*) from ocorrencia,projecto\r\n"
			+ " where tipo_ocorrencia_id=1 and procedencia='Sim' and ocorrencia.projecto_id=projecto.id and if (:projecto='', 1=1,projecto.designacao =(:projecto))\r\n"
			+ " and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal)) group by projecto.id",nativeQuery=true)
	
	public List<Object[]> busqueTudoAgrupadoPorRegiaoFiltro1(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);

	
	
	@Query(value="Select forma_comunicacao,count(*) from ocorrencia,projecto\r\n"
			+ " where tipo_ocorrencia_id=1 and procedencia='Sim' and ocorrencia.projecto_id=projecto.id and if (:projecto='', 1=1,projecto.designacao =(:projecto))\r\n"
			+ " and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal)) group by forma_comunicacao",nativeQuery=true)
	
	public List<Object[]> busqueTudoAgrupadoPorCanalEntradaFiltro1(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);


	 
	 
	
	@Query(value="Select categoria.designacao,count(*) from ocorrencia,categoria,projecto\r\n"
			+ " where tipo_ocorrencia_id=1 and procedencia='Sim' and ocorrencia.categoriaid=categoria.id and ocorrencia.projecto_id=projecto.id and if (:projecto='', 1=1,projecto.designacao =(:projecto))\r\n"
			+ " and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal)) group by categoria.id",nativeQuery=true)
	
	public List<Object[]> busqueTudoAgrupadoPorCategoriaFiltro1(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);

	
	@Query(value="select count(*) from ocorrencia, projecto where procedencia='Sim' and ocorrencia.projecto_id=projecto.id and "
			+ " if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF((:datainicial)='' and (:datafinal)='',1=1, "
			+ " ocorrencia.created between (:datainicial) and (:datafinal))", nativeQuery=true)
   	public Object totalDeOcorrenciasProcedentesFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);
	
	
	@Query(value="select count(*) from ocorrencia, projecto where procedencia='Sim' and tipo_ocorrencia_id=1 and ocorrencia.projecto_id=projecto.id and "
			+ " if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF((:datainicial)='' and (:datafinal)='',1=1, "
			+ " ocorrencia.created between (:datainicial) and (:datafinal))", nativeQuery=true)
   	public Object totalDeReclamacoesProcedentesFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);
	
	@Query(value="select count(*) from ocorrencia, projecto where resolucao = 'T' and tipo_ocorrencia_id=1 and ocorrencia.projecto_id=projecto.id and "
			+ " if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF((:datainicial)='' and (:datafinal)='',1=1, "
			+ " ocorrencia.created between (:datainicial) and (:datafinal))", nativeQuery=true)
   	public Object totalDeReclamacoesTerminadasFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);
	
	
	@Query(value="select count(*) from ocorrencia, projecto where procedencia='Sim' and tipo_ocorrencia_id!=1 and ocorrencia.projecto_id=projecto.id and "
			+ " if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF((:datainicial)='' and (:datafinal)='',1=1, "
			+ " ocorrencia.created between (:datainicial) and (:datafinal))", nativeQuery=true)
   	public Object totalDeOcorrenciasNaoReclamacoesFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);
	
	
	@Query(value="select monthname(ocorrencia.created) as mes,\r\n"
			+ "	(select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1 and ocorrencia.projecto_id=projecto.id and if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal))),"
			+ "	(select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id=1 and ocorrencia.projecto_id=projecto.id and if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal))),"
			+ " (select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id!=1 and ocorrencia.projecto_id=projecto.id and if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal))) "
			+ " from ocorrencia, projecto group by month(ocorrencia.created);",nativeQuery=true)
	public List<Object[]> busqueTnTIFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);
	
	
	
	 @Query(value="Select monthname(ocorrencia.created),\r\n"
	 		+ "(select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1 and year(ocorrencia.created)=(:ano) and ocorrencia.projecto_id=projecto.id and if(:projecto='',1=1,projecto.designacao=(:projecto)) \r\n"
	 		+ "and IF(:radioButton='',1=1,\r\n"
	 		+ "CASE\r\n"
	 		+ "    WHEN :radioButton = 'S' THEN if(1=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
	 		+ "    WHEN :radioButton = 'T' THEN quarter(ocorrencia.created)=(:codSelected)\r\n"
	 		+ "    ELSE month(ocorrencia.created)=(:codSelected)\r\n"
	 		+ "END)),\r\n"
	 		+ "\r\n"
	 		+ "(select count(*) from ocorrencia where procedencia='Sim' and tipo_ocorrencia_id=1 and year(ocorrencia.created)=(:ano) and ocorrencia.projecto_id=projecto.id and if(:projecto='',1=1,projecto.designacao=(:projecto)) \r\n"
	 		+ "and IF(:radioButton='',1=1,\r\n"
	 		+ "CASE\r\n"
	 		+ "    WHEN :radioButton = 'S' THEN if(1=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
	 		+ "    WHEN :radioButton = 'T' THEN quarter(ocorrencia.created)=(:codSelected)\r\n"
	 		+ "    ELSE month(ocorrencia.created)=(:codSelected)\r\n"
	 		+ "END)),\r\n"
	 		+ "\r\n"
	 		+ "(select count(*) from ocorrencia where procedencia='Sim' and tipo_ocorrencia_id!=1 and year(ocorrencia.created)=(:ano) and ocorrencia.projecto_id=projecto.id and if(:projecto='',1=1,projecto.designacao=(:projecto)) \r\n"
	 		+ "and IF(:radioButton='',1=1,\r\n"
	 		+ "CASE\r\n"
	 		+ "    WHEN :radioButton = 'S' THEN if(1=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
	 		+ "    WHEN :radioButton = 'T' THEN quarter(ocorrencia.created)=(:codSelected)\r\n"
	 		+ "    ELSE month(ocorrencia.created)=(:codSelected)\r\n"
	 		+ "END))\r\n"
	 		+ "from ocorrencia, projecto group by month(ocorrencia.created);", nativeQuery=true)
	    
	 public List<Object[]> busqueTudoAgrupadoPorMesFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);
	
	 
	 @Query(value="Select provincia.designacao,\r\n"
		 		+ "(select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1 and year(ocorrencia.created)=(:ano) and ocorrencia.projecto_id=projecto.id and if(:projecto='',1=1,projecto.designacao=(:projecto)) \r\n"
		 		+ "and IF(:radioButton='',1=1,\r\n"
		 		+ "CASE\r\n"
		 		+ "    WHEN :radioButton = 'S' THEN if(1=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
		 		+ "    WHEN :radioButton = 'T' THEN quarter(ocorrencia.created)=(:codSelected)\r\n"
		 		+ "    ELSE month(ocorrencia.created)=(:codSelected)\r\n"
		 		+ "END)),\r\n"
		 		+ "\r\n"
		 		+ "(select count(*) from ocorrencia where procedencia='Sim' and tipo_ocorrencia_id=1 and year(ocorrencia.created)=(:ano) and ocorrencia.projecto_id=projecto.id and if(:projecto='',1=1,projecto.designacao=(:projecto)) \r\n"
		 		+ "and IF(:radioButton='',1=1,\r\n"
		 		+ "CASE\r\n"
		 		+ "    WHEN :radioButton = 'S' THEN if(1=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
		 		+ "    WHEN :radioButton = 'T' THEN quarter(ocorrencia.created)=(:codSelected)\r\n"
		 		+ "    ELSE month(ocorrencia.created)=(:codSelected)\r\n"
		 		+ "END)),\r\n"
		 		+ "\r\n"
		 		+ "(select count(*) from ocorrencia where procedencia='Sim' and tipo_ocorrencia_id!=1 and year(ocorrencia.created)=(:ano) and ocorrencia.projecto_id=projecto.id and if(:projecto='',1=1,projecto.designacao=(:projecto)) \r\n"
		 		+ "and IF(:radioButton='',1=1,\r\n"
		 		+ "CASE\r\n"
		 		+ "    WHEN :radioButton = 'S' THEN if(1=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
		 		+ "    WHEN :radioButton = 'T' THEN quarter(ocorrencia.created)=(:codSelected)\r\n"
		 		+ "    ELSE month(ocorrencia.created)=(:codSelected)\r\n"
		 		+ "END))\r\n"
		 		+ "from ocorrencia, projecto,  provincia where ocorrencia.provincia_id=provincia.id group by provincia.designacao;", nativeQuery=true)
		    
		 public List<Object[]> busqueTudoAgrupadoPorProvinciaFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);
		
	
		 
		 
		 @Query(value="Select projecto.designacao,\r\n"
			 		+ "(select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1 and year(ocorrencia.created)=(:ano) and ocorrencia.projecto_id=projecto.id and if(:projecto='',1=1,projecto.designacao=(:projecto)) \r\n"
			 		+ "and IF(:radioButton='',1=1,\r\n"
			 		+ "CASE\r\n"
			 		+ "    WHEN :radioButton = 'S' THEN if(1=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			 		+ "    WHEN :radioButton = 'T' THEN quarter(ocorrencia.created)=(:codSelected)\r\n"
			 		+ "    ELSE month(ocorrencia.created)=(:codSelected)\r\n"
			 		+ "END)),\r\n"
			 		+ "\r\n"
			 		+ "(select count(*) from ocorrencia where procedencia='Sim' and tipo_ocorrencia_id=1 and year(ocorrencia.created)=(:ano) and ocorrencia.projecto_id=projecto.id and if(:projecto='',1=1,projecto.designacao=(:projecto)) \r\n"
			 		+ "and IF(:radioButton='',1=1,\r\n"
			 		+ "CASE\r\n"
			 		+ "    WHEN :radioButton = 'S' THEN if(1=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			 		+ "    WHEN :radioButton = 'T' THEN quarter(ocorrencia.created)=(:codSelected)\r\n"
			 		+ "    ELSE month(ocorrencia.created)=(:codSelected)\r\n"
			 		+ "END)),\r\n"
			 		+ "\r\n"
			 		+ "(select count(*) from ocorrencia where procedencia='Sim' and tipo_ocorrencia_id!=1 and year(ocorrencia.created)=(:ano) and ocorrencia.projecto_id=projecto.id and if(:projecto='',1=1,projecto.designacao=(:projecto)) \r\n"
			 		+ "and IF(:radioButton='',1=1,\r\n"
			 		+ "CASE\r\n"
			 		+ "    WHEN :radioButton = 'S' THEN if(1=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			 		+ "    WHEN :radioButton = 'T' THEN quarter(ocorrencia.created)=(:codSelected)\r\n"
			 		+ "    ELSE month(ocorrencia.created)=(:codSelected)\r\n"
			 		+ "END))\r\n"
			 		+ "from ocorrencia, projecto group by projecto.designacao;", nativeQuery=true)
			    
			 public List<Object[]> busqueTudoAgrupadoPorProjectoFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);
			
		 
		 
	@Query(value="select provincia.designacao as pronvincia,\r\n"
			+ "	(select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1 and ocorrencia.projecto_id=projecto.id and if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal))),"
			+ "	(select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id=1 and ocorrencia.projecto_id=projecto.id and if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal))),"
			+ " (select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id!=1 and ocorrencia.projecto_id=projecto.id and if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal))) "
			+ " from ocorrencia, projecto, provincia where ocorrencia.provincia_id=provincia.id group by provincia.designacao;",nativeQuery=true)
	public List<Object[]> busqueProvinciaTnTPFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);
	
	@Query(value="select projecto.designacao as pronvincia,\r\n"
			+ "	(select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1 and ocorrencia.projecto_id=projecto.id and if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal))),"
			+ "	(select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id=1 and ocorrencia.projecto_id=projecto.id and if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal))),"
			+ " (select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id!=1 and ocorrencia.projecto_id=projecto.id and if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal))) "
			+ " from ocorrencia, projecto, provincia where ocorrencia.provincia_id=provincia.id group by projecto.designacao;",nativeQuery=true)
	public List<Object[]> busqueProjectoTnTPFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);
	
	
	


    
	/*
	 * @Query(
	 * value="select designacao,  count(*) from ocorrencia, projecto where ocorrencia.projecto_id=projecto.id and year(created)= :ano group by projecto_id"
	 * , nativeQuery=true) public List<Ocorrencia>
	 * buscarOcorrenciasporPro(@Param("ano") int ano);
	 */
    
    
	/*
		Ocorrencias por User
		
		
	 */
	@Query(value="select designacao, count(*) from ocorrencia, provincia where ocorrencia.provincia_id=provincia.id "
			+ "and ocorrencia.created between (:datainicial) and (:datafinal) "
			+ "group by provincia_id",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorCidadeFiltro1(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal);

	@Query(value="select * from ocorrencia, cidade  where ocorrencia.cidade_id = cidade.id and ocorrencia.cidade_id =:local", nativeQuery=true)
	List<Ocorrencia> buscarOcorrenciasPorUsuariosRegional(@Param("local") long local);

	@Query(value="select * from ocorrencia, cidade  where ocorrencia.cidade_id = cidade.id and cidade.regiao_id =:local", nativeQuery=true)
	List<Ocorrencia> buscarOcorrenciasPorUsuariosZonas(@Param("local") long local);
	

	@Query(value="select * from ocorrencia, provincia  where ocorrencia.provincia_id = provincia.id and ocorrencia.provincia_id =:provincial order by ocorrencia.id desc", nativeQuery=true)
	List<Ocorrencia> buscarOcorrenciasPorUsuariosProvincia(@Param("provincial") long provincial);
	
	
	
	
	 @Query(value="Select * from ocorrencia, projecto\r\n"
				+ " where ocorrencia.projecto_id=projecto.id and  estado!='Temporario' and if (:projecto='', 1=1,projecto.designacao =(:projecto))\r\n"
				+ " and IF((:datainicial)=NULL and (:datafinal)=NULL,1=1, ocorrencia.created between (:datainicial) and (:datafinal))",nativeQuery=true)
	  public List<Ocorrencia>  totalDeOcorrenciasPorDataseProjecto(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);

	
	
/* FIM */
	
	

}
