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

	@Query(value="select distinct year(created) from ocorrencia order by created desc", nativeQuery=true)
	public List<Object[]> findAnos();

	@Query(value="select * from Ocorrencia where grm_stamp=:codigo", nativeQuery=true)
	public Ocorrencia findAllByCodigo(@Param("codigo") Long codigo);

	@Query(value="select * from Ocorrencia order by numero_ordem desc", nativeQuery=true)
	public List<Ocorrencia> BuscarOrdemDecrecente();

	@Query(value="select * from Ocorrencia, projectouser where ocorrencia.projecto_id=projectouser.projecto_id and ocorrencia.provincia_id=:id and projectouser.user_id=:id1", nativeQuery=true)
	List<Ocorrencia> buscarOcorrenciasPorUsuariosProvinciaProjecto(Long id, Long id1);

	/* QUERIES DAS PRIMEIRAS CARDS */
	@Query(value="select count(*) from ocorrencia where estado!='Temporario' and year(created)= :ano", nativeQuery=true)
	public Object totalDeOcorrencias(@Param("ano") int ano);


	@Query(value="Select count(*) from ocorrencia, projecto\r\n"
			+ "where ocorrencia.projecto_id=projecto.id and  estado!='Temporario' and year(ocorrencia.created)=(:ano) and\r\n"
			+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
			+ "IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "   WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
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


	@Query(value="Select count(*) from ocorrencia, projecto, provincia "
			+ "where ocorrencia.projecto_id=projecto.id and ocorrencia.provincia_id=provincia.id and  procedencia='Sim' and year(ocorrencia.created)=(:ano) and\r\n"
			+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and "
			+ "if(:provincia='',1=1,provincia.designacao=(:provincia)) and "
			+ "IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
			+ "    WHEN (:radioButton) = 'M' THEN month(ocorrencia.created)=:codSelected\r\n"
			+ "    WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
			+ "END);", nativeQuery=true)

	public Object totalDeOcorrenciasProcedentesFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("provincia") String provincia, @Param("ano") int ano);


	@Query(value="Select count(*) from ocorrencia, projecto, provincia "
			+ "where ocorrencia.projecto_id=projecto.id and ocorrencia.provincia_id=provincia.id and procedencia='Não' and estado='Validado' and year(ocorrencia.created)=(:ano) and\r\n"
			+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and "
			+ "if(:provincia='',1=1,provincia.designacao=(:provincia)) and "
			+ "IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
			+ "    WHEN (:radioButton) = 'M' THEN month(ocorrencia.created)=:codSelected\r\n"
			+ "    WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
			+ "END);", nativeQuery=true)

	public Object totalDeOcorrenciasImprocedentesFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("provincia") String provincia, @Param("ano") int ano);

	@Query(value="Select count(*) from ocorrencia, projecto, provincia "
			+ "where ocorrencia.projecto_id=projecto.id and ocorrencia.provincia_id=provincia.id and estado='Registado' and year(ocorrencia.created)=(:ano) and\r\n"
			+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and "
			+ "if(:provincia='',1=1,provincia.designacao=(:provincia)) and "
			+ "IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
			+ "    WHEN (:radioButton) = 'M' THEN month(ocorrencia.created)=:codSelected\r\n"
			+ "    WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
			+ "END);", nativeQuery=true)

	public Object totalDeOcorrenciasPorValidarFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("provincia") String provincia, @Param("ano") int ano);


	@Query(value="Select count(*) from ocorrencia, projecto, provincia "
			+ "where ocorrencia.projecto_id=projecto.id and ocorrencia.provincia_id=provincia.id and estado!='Temporario' and year(ocorrencia.created)=(:ano) and\r\n"
			+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and "
			+ "if(:provincia='',1=1,provincia.designacao=(:provincia)) and "
			+ "IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
			+ "    WHEN (:radioButton) = 'M' THEN month(ocorrencia.created)=:codSelected\r\n"
			+ "    WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
			+ "END);", nativeQuery=true)

	public Object totalDeOcorrenciasFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("provincia") String provincia, @Param("ano") int ano);



	@Query(value="select count(*) from Ocorrencia where procedencia='Não' and estado='Validado' and year(created)= :ano", nativeQuery=true)
	public Object totalDeOcorrenciasImprocedentes(@Param("ano") int ano);


	@Query(value="Select count(*) from ocorrencia, projecto\r\n"
			+ "where ocorrencia.projecto_id=projecto.id and  procedencia='Não' and estado='Validado' and year(ocorrencia.created)=(:ano) and\r\n"
			+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
			+ "IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "   WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
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
			+ "   WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
			+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
			+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
			+ "END);", nativeQuery=true)
	public Object totalDeOcorrenciasPorValidarFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);



	@Query(value="Select count(*) from ocorrencia, projecto, provincia "
			+ "where ocorrencia.projecto_id=projecto.id and ocorrencia.provincia_id=provincia.id and procedencia='Sim' and resolucao IN ('R','A') and tipo_ocorrencia_id=1 and year(ocorrencia.created)=(:ano) and\r\n"
			+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and "
			+ "if(:provincia='',1=1,provincia.designacao=(:provincia)) and "
			+ "IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "   WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
			+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
			+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
			+ "END);", nativeQuery=true)
	public Object totalDeReclamacoesEmAndamentoFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto,@Param("provincia") String provincia, @Param("ano") int ano);



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


	@Query(value="Select count(*) from ocorrencia, projecto, provincia "
			+ "where ocorrencia.projecto_id=projecto.id and ocorrencia.provincia_id=provincia.id and  procedencia='Sim' and tipo_ocorrencia_id=1 and year(ocorrencia.created)=(:ano) and\r\n"
			+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and "
			+ "if(:provincia='',1=1,provincia.designacao=(:provincia)) and "
			+ "IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
			+ "   WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
			+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
			+ "END);", nativeQuery=true)

	public Object totalDeReclamacoesProcedentesFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("provincia") String provincia, @Param("ano") int ano);


	@Query(value="select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id!=1 and year(created)= :ano", nativeQuery=true)
	public Object totalDeOcorrenciasNaoReclamacoes(@Param("ano") int ano);


	@Query(value="select count(*) from Ocorrencia where procedencia='Sim' and resolucao = 'V' and tipo_ocorrencia_id=1 and year(created)= :ano", nativeQuery=true)
	public Object totalDeOcorrenciasNaoReSolvidas(@Param("ano") int ano);



	@Query(value="Select count(*) from ocorrencia, projecto, provincia "
			+ "where ocorrencia.projecto_id=projecto.id and ocorrencia.provincia_id=provincia.id and procedencia='Sim' and tipo_ocorrencia_id!=1 and year(ocorrencia.created)=(:ano) and\r\n"
			+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and "
			+ "if(:provincia='',1=1,provincia.designacao=(:provincia)) and "
			+ "IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "   WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
			+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
			+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
			+ "END);", nativeQuery=true)

	public Object totalDeOcorrenciasNaoReclamacoesFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto,@Param("provincia") String provincia, @Param("ano") int ano);

	@Query(value="Select count(*) from ocorrencia, projecto, provincia "
			+ "where ocorrencia.projecto_id=projecto.id and ocorrencia.provincia_id=provincia.id and resolucao = 'V' and procedencia='Sim' and tipo_ocorrencia_id=1 and year(ocorrencia.created)=(:ano) and\r\n"
			+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and "
			+ "if(:provincia='',1=1,provincia.designacao=(:provincia)) and "
			+ "IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "   WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
			+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
			+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
			+ "END);", nativeQuery=true)
	public Object totalDeOcorrenciasNaoReSolvidas2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto,@Param("provincia") String provincia, @Param("ano") int ano);




	@Query(value="select count(*) from ocorrencia where procedencia='Não' and year(created)= :ano", nativeQuery=true)
	public Object totalDeReclamacoesNaoProcedentes(@Param("ano") int ano);



	@Query(value="select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1 and year(created)= :ano", nativeQuery=true)
	public Object totalDeReclamacoesTerminadas(@Param("ano") int ano);

	@Query(value="Select count(*) from ocorrencia, projecto, provincia "
			+ "where ocorrencia.projecto_id=projecto.id and ocorrencia.provincia_id=provincia.id and resolucao = 'T' and tipo_ocorrencia_id=1 and year(ocorrencia.created)=(:ano) and\r\n"
			+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and "
			+ "if(:provincia='',1=1,provincia.designacao=(:provincia)) and "
			+ "IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "   WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
			+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
			+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
			+ "END);", nativeQuery=true)

	public Object totalDeReclamacoesTerminadasFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("provincia") String provincia,  @Param("ano") int ano);



	@Query(value="select count(*) from ocorrencia where resolucao != 'T' and year(created)= :ano", nativeQuery=true)
	public Object totalDeReclamacoesEmResolucao(@Param("ano") int ano);

	@Query(value="select count(*) from ocorrencia where procedencia='Sim' and resolucao IN ('R','A') and tipo_ocorrencia_id=1 and year(created)= :ano", nativeQuery=true)
	public Object totalDeReclamacoesEmAndamento(@Param("ano") int ano);

	/*
	 * @Query(value="select *, count(*) from ocorrencia  group by categoriaid",
	 * nativeQuery=true) public List<Ocorrencia> busqueTudoAgrupadoPorCategoria();
	 */

	@Query(value="select * from ocorrencia where estado!='Temporario' and year(created)= :ano", nativeQuery=true)
	public List<Ocorrencia> buscarOcorrenciasActuais(@Param("ano") int ano);

	/*
	 * @Query(value="select projecto.designacao as mes,\r\n" +
	 * "(select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1 and year(created)= :ano),\r\n"
	 * +
	 * "(select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id=1 and year(created)= :ano),\r\n"
	 * +
	 * "(select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id!=1 and year(created)= :ano)\r\n"
	 * +
	 * "from ocorrencia, projecto where year(ocorrencia.created)= :ano and ocorrencia.projecto_id=projecto.id  group by projecto.designacao;"
	 * ,nativeQuery=true) public List<Object[]>
	 * busqueTudoAgrupadoPorProjecto(@Param("ano") int ano);
	 */

	@Query(value="SELECT P.designacao,\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'T' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RT',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao IN ('R','A') and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RA',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'V' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RN',\r\n"
			+ "   month(O.created) as nrmes\r\n"
			+ "FROM  ocorrencia O inner join projecto P on O.projecto_id=P.id where year(O.created) =:ano  GROUP BY P.designacao;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorProjecto(@Param("ano") int ano);

	@Query(value="select designacao, count(*) from ocorrencia, categoria where tipo_ocorrencia_id=1 and procedencia='Sim' and ocorrencia.categoriaid=categoria.id and year(ocorrencia.created)= :ano group by categoriaid",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorCategoria(@Param("ano") int ano);



	@Query(value="select forma_comunicacao, count(*) from ocorrencia where tipo_ocorrencia_id=1 and procedencia='Sim' and year(created)= :ano group by forma_comunicacao;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorCanalDeEntrada(@Param("ano") int ano);


	@Query(value="select designacao, count(*) from ocorrencia, provincia where tipo_ocorrencia_id=1 and procedencia='Sim' and ocorrencia.provincia_id=provincia.id group by provincia_id",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorCidade();

	@Query(value="select processo, count(*) from ocorrencia, origem where ocorrencia.tipoorigem=origem.id group by processo",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorTipoOrigem();

	@Query(value="select projecto_operacao, count(*) from ocorrencia group by projecto_operacao",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorEntidade();

	@Query(value="select interno_externo, count(*) from ocorrencia group by interno_externo;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorDefinicao();


	@Query(value="select monthname(created) as 'mes',  \r\n"
			+ " (select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1) as 'Terminados',\r\n"
			+ "    (select count(*) from ocorrencia where resolucao != 'T'and procedencia = 'Sim' and estado='Validado' and estado!='Temporario' ) as 'Nao Terminadas',\r\n"
			+ " (select count(*) from ocorrencia where procedencia != 'Não' and estado='Validado' and estado!='Temporario') as Improcedentes\r\n"
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

	/*
	 * @Query(value="select provincia.designacao as mes,\r\n" +
	 * "(select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1 and year(ocorrencia.created)= :ano),\r\n"
	 * +
	 * "(select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id=1 and year(ocorrencia.created)= :ano),\r\n"
	 * +
	 * "(select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id!=1 and year(ocorrencia.created)= :ano)\r\n"
	 * +
	 * "from ocorrencia, provincia where ocorrencia.provincia_id=provincia.id and year(ocorrencia.created)= :ano group by provincia.designacao;"
	 * ,nativeQuery=true) public List<Object[]>
	 * busqueTudoAgrupadoPorProvinciaEstado(@Param("ano") int ano);
	 */


	@Query(value="SELECT P.designacao,\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'T' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RT',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao IN ('R','A') and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RA',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'V' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RN',\r\n"
			+ "   month(O.created) as nrmes\r\n"
			+ "FROM  ocorrencia O inner join provincia P on O.provincia_id=P.id where year(O.created) =:ano  GROUP BY P.designacao;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorProvinciaEstado(@Param("ano") int ano);




	@Query(value="select tipo_ocorrencia.designacao, count(*) from ocorrencia, tipo_ocorrencia where ocorrencia.tipo_ocorrencia_id=tipo_ocorrencia.id group by tipo_ocorrencia_id",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorTipoDeOcorrencia();




/*@Query(value="select regiao.designacao, count(*) from ocorrencia, cidade, regiao where ocorrencia.cidade_id = cidade.id and cidade.regiao_id = regiao.id group by regiao.designacao",nativeQuery=true)
public List<Object[]> busqueTudoAgrupadoPorRegiao();

@Query(value="select monthname(created) as mes,\r\n"
+ "cast(sum(if(resolucao='T',1,0))as unsigned) as terminado, \r\n"
+ "cast(sum(if(resolucao!='T',1,0))as unsigned) as Nterminado,\r\n"
+ "cast(sum(if(left(procedencia,1)='N',1,0))as unsigned) as Improcedente \r\n"
+ "from ocorrencia group bfy month(created)",nativeQuery=true)
public List<Object[]> busqueTnTI();*/


	@Query(value="select projecto.designacao, count(*) from ocorrencia, projecto where tipo_ocorrencia_id=1 and procedencia='Sim' and ocorrencia.projecto_id = projecto.id and year(ocorrencia.created)= :ano group by projecto.designacao",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorRegiao(@Param("ano") int ano);

	/*
	 * @Query(value="select monthname(created) as mes,\r\n" +
	 * " (select count(*) from ocorrencia where resolucao = 'T' and tipo_ocorrencia_id=1 and year(created)= :ano),\r\n"
	 * +
	 * " (select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id=1 and year(created)= :ano),\r\n"
	 * +
	 * " (select count(*) from Ocorrencia where procedencia='Sim' and tipo_ocorrencia_id!=1 and year(created)= :ano)\r\n"
	 * + " from ocorrencia where year(created)= :ano  group by month(created);"
	 * ,nativeQuery=true) public List<Object[]> busqueTnTI(@Param("ano") int ano);
	 */
	@Query(value="Select IF(nrmes=1,'Janeiro',if(nrmes=2,'Fevereiro',if(nrmes=3,'Março',if(nrmes=4,'Abril',if(nrmes=5,'Maio',if(nrmes=6,'Junho',\r\n"
			+ "if(nrmes=7,'Julho',if(nrmes=8,'Agosto',if(nrmes=9,'Setembro',if(nrmes=10,'Outubro',if(nrmes=11,'Novembro','Dezembro'))))))))))) as mes,t.RT,t.RA,t.RN from  (\r\n"
			+ "SELECT\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'T' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RT',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao IN ('R','A') and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RA',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'V' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RN',\r\n"
			+ "   month(O.created) as nrmes\r\n"
			+ "FROM  ocorrencia O inner join projecto P on O.projecto_id=P.id\r\n"
			+ "   where year(O.created)= :ano  GROUP BY monthname(O.created)\r\n"
			+ ")t order by nrmes;",nativeQuery=true)
	public List<Object[]> busqueTnTI(@Param("ano") int ano);



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
			+ "   WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
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
			+ "   WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
			+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
			+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
			+ "END) group by projecto.designacao;", nativeQuery=true)

	public List<Object[]> busqueTudoAgrupadoPorRegiaoFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);




	@Query(value="Select forma_comunicacao,count(*) from ocorrencia, projecto \r\n"
			+ "where ocorrencia.projecto_id=projecto.id and procedencia='Sim' and ocorrencia.tipo_ocorrencia_id=1 and year(ocorrencia.created)=(:ano) and\r\n"
			+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
			+ "IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "   WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
			+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
			+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
			+ "END) group by forma_comunicacao;", nativeQuery=true)

	public List<Object[]> busqueTudoAgrupadoPorCanalEntradaFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("ano") int ano);


	@Query(value="Select categoria.designacao,count(*) from ocorrencia, projecto, categoria \r\n"
			+ "where ocorrencia.categoriaid=categoria.id and ocorrencia.projecto_id=projecto.id and ocorrencia.tipo_ocorrencia_id=1 and procedencia='Sim' and year(ocorrencia.created)=(:ano) and\r\n"
			+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
			+ "IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "   WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
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


	@Query(value="select count(*) from ocorrencia, projecto, provincia "
			+ " where ocorrencia.projecto_id=projecto.id and ocorrencia.provincia_id=provincia.id and procedencia='Sim'  and "
			+ "if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF (:provincia='', 1=1,provincia.designacao =(:provincia)) and IF((:datainicial)='' and (:datafinal)='',1=1, "
			+ "ocorrencia.created between (:datainicial) and (:datafinal));", nativeQuery=true)
	public Object totalDeOcorrenciasProcedentesFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto, @Param("provincia") String provincia);


	@Query(value="select count(*) from ocorrencia inner join projecto on ocorrencia.projecto_id=projecto.id inner join provincia on ocorrencia.provincia_id=provincia.id\r\n"
			+ "where estado!='Temporario' and\r\n"
			+ "if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF (:provincia='', 1=1,provincia.designacao =(:provincia)) and IF((:datainicial)='' and (:datafinal)='',1=1,\r\n"
			+ "ocorrencia.created between (:datainicial) and (:datafinal));", nativeQuery=true)
	public Object totalOcorrenciasFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto, @Param("provincia") String provincia);

	@Query(value="select count(*) from ocorrencia inner join projecto on ocorrencia.projecto_id=projecto.id inner join provincia on ocorrencia.provincia_id=provincia.id\r\n"
			+ "where procedencia='Não' and estado='Validado' and\r\n"
			+ "if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF (:provincia='', 1=1,provincia.designacao =(:provincia)) and IF((:datainicial)='' and (:datafinal)='',1=1,\r\n"
			+ "ocorrencia.created between (:datainicial) and (:datafinal));", nativeQuery=true)
	public Object totalDeOcorrenciasImprocedentesFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto, @Param("provincia") String provincia);


	@Query(value="select count(*) from ocorrencia inner join projecto on ocorrencia.projecto_id=projecto.id inner join provincia on ocorrencia.provincia_id=provincia.id\r\n"
			+ "where estado='Registado' and\r\n"
			+ "if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF (:provincia='', 1=1,provincia.designacao =(:provincia)) and IF((:datainicial)='' and (:datafinal)='',1=1,\r\n"
			+ "ocorrencia.created between (:datainicial) and (:datafinal));", nativeQuery=true)
	public Object totalDeOcorrenciasPorValidarFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto, @Param("provincia") String provincia);


	@Query(value="select count(*) from ocorrencia inner join projecto on ocorrencia.projecto_id=projecto.id inner join provincia on ocorrencia.provincia_id=provincia.id\r\n"
			+ "where procedencia='Sim' and tipo_ocorrencia_id=1 and\r\n"
			+ "if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF (:provincia='', 1=1,provincia.designacao =(:provincia)) and IF((:datainicial)='' and (:datafinal)='',1=1,\r\n"
			+ "ocorrencia.created between (:datainicial) and (:datafinal));", nativeQuery=true)
	public Object totalDeReclamacoesProcedentesFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto, @Param("provincia") String provincia);



	@Query(value="select count(*) from ocorrencia, projecto, provincia \r\n"
			+ "where tipo_ocorrencia_id=1 and ocorrencia.projecto_id=projecto.id and ocorrencia.provincia_id=provincia.id and resolucao = 'T' and\r\n"
			+ "if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF (:provincia='', 1=1,provincia.designacao =(:provincia)) and IF((:datainicial)='' and (:datafinal)='',1=1,\r\n"
			+ "ocorrencia.created between (:datainicial) and (:datafinal));", nativeQuery=true)
	public Object totalDeReclamacoesTerminadasFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto, @Param("provincia") String provincia);


	@Query(value="select count(*) from ocorrencia, projecto where resolucao IN ('R','A') and tipo_ocorrencia_id=1 and ocorrencia.projecto_id=projecto.id and "
			+ " if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF((:datainicial)='' and (:datafinal)='',1=1, "
			+ " ocorrencia.created between (:datainicial) and (:datafinal))", nativeQuery=true)
	public Object totalDeReclamacoesEmAndamentoFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);


	@Query(value="select count(*) from ocorrencia, projecto, provincia \r\n"
			+ "where procedencia='Sim' and ocorrencia.projecto_id=projecto.id and ocorrencia.provincia_id=provincia.id and resolucao IN ('R','A') and tipo_ocorrencia_id=1 and\r\n"
			+ "if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF (:provincia='', 1=1,provincia.designacao =(:provincia)) and IF((:datainicial)='' and (:datafinal)='',1=1,\r\n"
			+ "ocorrencia.created between (:datainicial) and (:datafinal));", nativeQuery=true)
	public Object totalDeReclamacoesEmAndamentoFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto, @Param("provincia") String provincia);


	@Query(value="select count(*) from ocorrencia inner join projecto on ocorrencia.projecto_id=projecto.id inner join provincia on ocorrencia.provincia_id=provincia.id\r\n"
			+ "where procedencia='Sim' and tipo_ocorrencia_id!=1 and\r\n"
			+ "if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF (:provincia='', 1=1,provincia.designacao =(:provincia)) and IF((:datainicial)='' and (:datafinal)='',1=1,\r\n"
			+ "ocorrencia.created between (:datainicial) and (:datafinal));", nativeQuery=true)
	public Object totalDeOcorrenciasNaoReclamacoesFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto, @Param("provincia") String provincia);




	@Query(value="\r\n"
			+ "Select IF(nrmes=1,'Janeiro',if(nrmes=2,'Fevereiro',if(nrmes=3,'Março',if(nrmes=4,'Abril',if(nrmes=5,'Maio',if(nrmes=6,'Junho',\r\n"
			+ "if(nrmes=7,'Julho',if(nrmes=8,'Agosto',if(nrmes=9,'Setembro',if(nrmes=10,'Outubro',if(nrmes=11,'Novembro','Dezembro'))))))))))) as mes,t.RT,t.RA,t.RN from  (\r\n"
			+ "SELECT\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'T' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RT',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao IN ('R','A') and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RA',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'V' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RN',\r\n"
			+ "   month(O.created) as nrmes\r\n"
			+ "FROM  ocorrencia O inner join projecto P on O.projecto_id=P.id inner join provincia Pr on O.provincia_id=Pr.id\r\n"
			+ "   where if (:projecto='', 1=1,P.designacao =(:projecto)) and IF (:provincia='', 1=1,Pr.designacao =(:provincia)) and IF((:datainicial)='' and (:datafinal)='',1=1,\r\n"
			+ "O.created between (:datainicial) and (:datafinal)) GROUP BY monthname(O.created)\r\n"
			+ ")t order by nrmes;",nativeQuery=true)
	public List<Object[]> busqueTnTIFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto, @Param("provincia") String provincia);



	@Query(value="Select IF(nrmes=1,'Janeiro',if(nrmes=2,'Fevereiro',if(nrmes=3,'Março',if(nrmes=4,'Abril',if(nrmes=5,'Maio',if(nrmes=6,'Junho',\r\n"
			+ "if(nrmes=7,'Julho',if(nrmes=8,'Agosto',if(nrmes=9,'Setembro',if(nrmes=10,'Outubro',if(nrmes=11,'Novembro','Dezembro'))))))))))) as mes,t.RT,t.RA,t.RN from  (\r\n"
			+ "SELECT\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'T' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RT',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao IN ('R','A') and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RA',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'V' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RN',\r\n"
			+ "   month(O.created) as nrmes\r\n"
			+ "FROM  ocorrencia O inner join projecto P on O.projecto_id=P.id inner join provincia Pr on O.provincia_id=Pr.id"
			+ "   where year(O.created)=(:ano) and if(:projecto='',1=1,P.designacao=(:projecto)) and if(:provincia='',1=1,Pr.designacao=(:provincia)) "
			+ "and IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "WHEN :radioButton = 'A' THEN year(O.created)=(:ano)\r\n"
			+ "WHEN :radioButton = 'S' THEN if(1=1,month(O.created)<=6,month(O.created)>6)\r\n"
			+ "WHEN :radioButton = 'T' THEN quarter(O.created)=(:codSelected)\r\n"
			+ "ELSE month(O.created)=(:codSelected)\r\n"
			+ "END)  GROUP BY monthname(O.created)\r\n"
			+ ")t order by nrmes;", nativeQuery=true)

	public List<Object[]> busqueTudoAgrupadoPorMesFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("provincia") String provincia, @Param("ano") int ano);


	@Query(value="SELECT Pr.designacao, \r\n"
			+ " SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'T' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RT',\r\n"
			+ " SUM(CASE WHEN O.procedencia='Sim' and resolucao IN ('R','A') and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RA',\r\n"
			+ " SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'V' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RN',\r\n"
			+ " month(O.created) as nrmes\r\n"
			+ " FROM  ocorrencia O inner join projecto P on O.projecto_id=P.id inner join provincia Pr on O.provincia_id=Pr.id\r\n"
			+ " where year(O.created)=(:ano) and if(:projecto='',1=1,P.designacao=(:projecto)) and if(:provincia='',1=1,Pr.designacao=(:provincia)) \r\n"
			+ " and IF(:radioButton='',1=1,\r\n"
			+ " CASE\r\n"
			+ " WHEN :radioButton = 'A' THEN year(O.created)=(:ano)\r\n"
			+ " WHEN :radioButton = 'S' THEN if(1=1,month(O.created)<=6,month(O.created)>6)\r\n"
			+ " WHEN :radioButton = 'T' THEN quarter(O.created)=(:codSelected)\r\n"
			+ " END)  GROUP BY Pr.designacao", nativeQuery=true)

	public List<Object[]> busqueTudoAgrupadoPorProvinciaFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("provincia") String provincia, @Param("ano") int ano);


	@Query(value="SELECT projecto.designacao,CONCAT(left(ltrim(rtrim(monthname(ocorrencia.created))),3),'/',RIGHT(year(ocorrencia.created),2)) as periodo, count(ocorrencia.id) as contado FROM ocorrencia INNER JOIN projecto ON ocorrencia.projecto_id = projecto.id WHERE year(ocorrencia.created)= :ano GROUP BY projecto.designacao order by ocorrencia.created",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorProjectoLinha(@Param("ano") int ano);

	@Query(value="SELECT P.designacao, \r\n"
			+ "SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'T' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RT',\r\n"
			+ "SUM(CASE WHEN O.procedencia='Sim' and resolucao IN ('R','A') and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RA',\r\n"
			+ "SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'V' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RN',\r\n"
			+ "month(O.created) as nrmes\r\n"
			+ "FROM  ocorrencia O inner join projecto P on O.projecto_id=P.id inner join provincia Pr on O.provincia_id=Pr.id\r\n"
			+ "where year(O.created)=(:ano) and if(:projecto='',1=1,P.designacao=(:projecto)) and if(:provincia='',1=1,Pr.designacao=(:provincia)) \r\n"
			+ "and IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "WHEN :radioButton = 'A' THEN year(O.created)=(:ano)\r\n"
			+ "WHEN :radioButton = 'S' THEN if(1=1,month(O.created)<=6,month(O.created)>6)\r\n"
			+ "WHEN :radioButton = 'T' THEN quarter(O.created)=(:codSelected)\r\n"
			+ "END)  GROUP BY P.designacao", nativeQuery=true)

	public List<Object[]> busqueTudoAgrupadoPorProjectoFiltro2(@Param("radioButton") String radioButton, @Param("codSelected") int codSelected, @Param("projecto") String projecto, @Param("provincia") String provincia, @Param("ano") int ano);



	@Query(value="\r\n"
			+ "SELECT Pr.designacao, "
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'T' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RT',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao IN ('R','A') and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RA',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'V' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RN',\r\n"
			+ "   month(O.created) as nrmes\r\n"
			+ "FROM  ocorrencia O inner join projecto P on O.projecto_id=P.id inner join provincia Pr on O.provincia_id=Pr.id\r\n"
			+ "   where if (:projecto='', 1=1,P.designacao =(:projecto)) and IF (:provincia='', 1=1,Pr.designacao =(:provincia)) and IF((:datainicial)='' and (:datafinal)='',1=1,\r\n"
			+ "O.created between (:datainicial) and (:datafinal)) GROUP BY Pr.designacao;",nativeQuery=true)
	public List<Object[]> busqueProvinciaTnTPFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto, @Param("provincia") String provincia);

	@Query(value="\r\n"
			+ "SELECT P.designacao, "
			+ "   SUM(CASE WHEN O.procedencia='Sim' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RS',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'T' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RT',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao IN ('R','A') and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RA',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'V' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RN',\r\n"
			+ "   month(O.created) as nrmes\r\n"
			+ "FROM  ocorrencia O inner join projecto P on O.projecto_id=P.id inner join provincia Pr on O.provincia_id=Pr.id\r\n"
			+ "   where if (:projecto='', 1=1,P.designacao =(:projecto)) and IF (:provincia='', 1=1,Pr.designacao =(:provincia)) and IF((:datainicial)='' and (:datafinal)='',1=1,\r\n"
			+ "O.created between (:datainicial) and (:datafinal)) GROUP BY Pr.designacao;",nativeQuery=true)
	public List<Object[]> busqueProjectoTnTPFiltro(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto, @Param("provincia") String provincia);


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


	@Query(value="select * from ocorrencia, provincia  where ocorrencia.provincia_id = provincia.id and ocorrencia.provincia_id =:provincial order by ocorrencia.numero_ordem desc", nativeQuery=true)
	List<Ocorrencia> buscarOcorrenciasPorUsuariosProvincia(@Param("provincial") long provincial);




	@Query(value="Select * from ocorrencia, projecto\r\n"
			+ " where ocorrencia.projecto_id=projecto.id and  estado!='Temporario' and if (:projecto='', 1=1,projecto.designacao =(:projecto))\r\n"
			+ " and IF((:datainicial)=NULL and (:datafinal)=NULL,1=1, ocorrencia.created between (:datainicial) and (:datafinal))",nativeQuery=true)
	public List<Ocorrencia>  totalDeOcorrenciasPorDataseProjecto(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto);

	@Query(value="select * from ocorrencia where estado='Validado' and year(created)= :ano", nativeQuery=true)
	public List<Ocorrencia> ocorrenciasCorrentes(@Param("ano") int ano);

	@Query(value="select *,c.designacao as provincia, r.designacao as categoria from ocorrencia o"
			+ " inner join projecto p "
			+ " inner join provincia c "
			+ " inner join categoria r"
			+ " inner join tipo_ocorrencia t "
			+ " where o.tipo_ocorrencia_id=t.id and o.projecto_id=p.id and o.provincia_id=c.id and o.categoriaid=r.id and o.estado='Validado' "
			+ " and o.created between (:datainicial) and (:datafinal) and if (:projecto='', 1=1,p.designacao =(:projecto)) and if (:categoria='', 1=1,r.designacao =(:categoria)) "
			+ " and if (:provincia='',1=1,c.designacao=(:provincia)) and if (:tipoOcorrencia='',1=1,t.designacao=(:tipoOcorrencia)) and if (:estado='',1=1,o.resolucao=(:estado))",nativeQuery=true)
	public List<Ocorrencia>  totalDeOcorrenciasPorDataseProjectoRelatorio(@Param("datainicial") Date datainicial, @Param("datafinal") Date datafinal, @Param("projecto") String projecto, @Param("provincia") String provincia, @Param("estado") String estado, @Param("tipoOcorrencia") String tipoOcorrencia, @Param("categoria") String categoria);


	@Query(value="select sexo, count(*) from ocorrencia where tipo_ocorrencia_id=1 and procedencia='Sim' and year(created)= :ano group by sexo;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorSexo(@Param("ano") int ano);

	@Query(value="select t.designacao, count(*) from ocorrencia o "
			+ " inner join tipo_ocorrencia t "
			+ " where o.tipo_ocorrencia_id=t.id and procedencia='Sim' and year(o.created)= :ano group by t.designacao;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoTipoDePreocupacao(@Param("ano") int ano);

	@Query(value="Select tipo_ocorrencia.designacao, count(*) from ocorrencia, projecto, tipo_ocorrencia, provincia \r\n"
			+ "where ocorrencia.tipo_ocorrencia_id=tipo_ocorrencia.id and ocorrencia.projecto_id=projecto.id and ocorrencia.provincia_id=provincia.id and procedencia='Sim' and year(ocorrencia.created)=(:ano) and\r\n"
			+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
			+ "if(:provincia='',1=1,provincia.designacao=(:provincia)) and\r\n"
			+ "IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "   WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
			+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
			+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
			+ "END) group by tipo_ocorrencia.designacao", nativeQuery=true)
	List<Object[]> busqueTudoAgrupadoPorPreocupacao2(String radioButton, int codSelected, String projecto, String provincia, int ano);

	@Query(value="Select sexo, count(*) from ocorrencia, projecto, provincia \r\n"
			+ "where ocorrencia.tipo_ocorrencia_id=1 and ocorrencia.projecto_id=projecto.id and ocorrencia.provincia_id=provincia.id and procedencia='Sim' and year(ocorrencia.created)=(:ano) and\r\n"
			+ "if(:projecto='',1=1,projecto.designacao=(:projecto)) and\r\n"
			+ "if(:provincia='',1=1,provincia.designacao=(:provincia)) and\r\n"
			+ "IF(:radioButton='',1=1,\r\n"
			+ "CASE\r\n"
			+ "   WHEN (:radioButton) = 'A' THEN year(ocorrencia.created)=(:ano)\r\n"
			+ "    WHEN (:radioButton) = 'S' THEN if(:codSelected=1,month(ocorrencia.created)<=6,month(ocorrencia.created)>6)\r\n"
			+ "    WHEN (:radioButton) = 'T' THEN quarter(ocorrencia.created)=:codSelected\r\n"
			+ "    ELSE month(ocorrencia.created)=:codSelected\r\n"
			+ "END) group by sexo", nativeQuery=true)
	List<Object[]> busqueTudoAgrupadoPorSexo2(String radioButton, int codSelected, String projecto, String provincia, int ano);


	@Query(value="select count(*) from ocorrencia, projecto, provincia where resolucao='V' and tipo_ocorrencia_id=1 and ocorrencia.provincia_id=provincia.id and ocorrencia.projecto_id=projecto.id and "
			+ " if (:provincia='', 1=1,provincia.designacao =(:provincia)) and if (:projecto='', 1=1,projecto.designacao =(:projecto)) and IF((:datainicial)='' and (:datafinal)='',1=1, "
			+ " ocorrencia.created between (:datainicial) and (:datafinal))", nativeQuery=true)
	Object totalDeOcorrenciasNaoReSolvidasDatas(Date datainicial, Date datafinal, String projecto, String provincia);


	@Query(value="Select tipo_ocorrencia.designacao,count(*) from ocorrencia,projecto,tipo_ocorrencia, provincia \r\n"
			+ " where procedencia='Sim' and ocorrencia.tipo_ocorrencia_id=tipo_ocorrencia.id and ocorrencia.provincia_id=provincia.id and ocorrencia.projecto_id=projecto.id and if (:provincia='', 1=1,provincia.designacao =(:provincia)) and if (:projecto='', 1=1,projecto.designacao =(:projecto))\r\n"
			+ " and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal)) group by tipo_ocorrencia.designacao",nativeQuery=true)
	List<Object[]> busqueTudoAgrupadoPorPreocupacao1(Date datainicial, Date datafinal, String projecto, String provincia);

	@Query(value="Select sexo,count(*) from ocorrencia,projecto, provincia \r\n"
			+ " where tipo_ocorrencia_id=1 and procedencia='Sim' and ocorrencia.provincia_id=provincia.id and ocorrencia.projecto_id=projecto.id and if (:provincia='', 1=1,provincia.designacao =(:provincia)) and if (:projecto='', 1=1,projecto.designacao =(:projecto))\r\n"
			+ " and IF((:datainicial)='' and (:datafinal)='',1=1, ocorrencia.created between (:datainicial) and (:datafinal)) group by sexo",nativeQuery=true)
	List<Object[]> busqueTudoAgrupadoPorSexo1(Date datainicial, Date datafinal, String projecto, String provincia);

	@Query(value="select Max(numero_ordem) from ocorrencia", nativeQuery=true)
	Object BuscarUltimoNumero();

	@Query(value="select * from ocorrencia order by ocorrencia.numero_ordem desc", nativeQuery=true)
	public List<Ocorrencia> findAllOrdenarPorNUmeroOrdem();

	/*@Query(value="SELECT P.designacao,\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'T' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RT',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao IN ('R','A') and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RA',\r\n"
			+ "   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'V' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RN',\r\n"
			+ "   month(O.created) as nrmes\r\n"
			+ "FROM  ocorrencia O inner join provincia P on O.provincia_id=P.id where year(O.created) =:ano  GROUP BY P.designacao;",nativeQuery=true)
	public List<Object[]> busqueTudoAgrupadoPorProvinciaEstado(@Param("ano") int ano);*/


	@Query(value="SELECT P.designacao,\n" +
			"SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'T' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RT',\n" +
			"SUM(CASE WHEN O.procedencia='Sim' and resolucao IN ('R','A') and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RA',\n" +
			"SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'V' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RN',\n" +
			"month(O.created) as nrmes\n" +
			"FROM  ocorrencia O \n" +
			"inner join provincia P on O.provincia_id=P.id \n" +
			"inner join tipo_ocorrencia on O.tipo_ocorrencia_id = tipo_ocorrencia.id\n" +
			"inner join projecto on O.projecto_id=projecto.id \n" +
			"inner join categoria on O.categoriaid = categoria.id\n" +
			"where O.tipo_ocorrencia_id=1 and O.procedencia='Sim'  \n" +
			"     and O.created between if(:datainicial='','2022-1-1',:datainicial) and if(:datafinal ='',CURRENT_DATE,:datafinal) \n" +
			"   \t and if(:tipoOcorrencia='',1=1, tipo_ocorrencia.designacao=:tipoOcorrencia)\n" +
			"   \t and if(:estado='',1=1,resolucao =:estado )\n" +
			"   \t and if(:projecto='',1=1,projecto.designacao=:projecto)\n" +
			"   \t and if(:provincia='',1=1, P.designacao =:provincia)\n" +
			"   \t and if(:categoria='',1=1,categoria.designacao=:categoria)\n" +
			"GROUP BY P.designacao;",nativeQuery=true)
    List<Object[]> busqueTudoAgrupadoPorProvinciaEstadoPesquisa(Date datainicial, Date datafinal, String tipoOcorrencia, String estado, String projecto, String provincia, String categoria);

	@Query(value="select forma_comunicacao,count(forma_comunicacao)\n" +
			"from ocorrencia \n" +
			"\t inner join tipo_ocorrencia on ocorrencia.tipo_ocorrencia_id = tipo_ocorrencia.id\n" +
			"     inner join projecto on ocorrencia.projecto_id=projecto.id \n" +
			"     inner join categoria on ocorrencia.categoriaid = categoria.id\n" +
			"     inner join provincia on ocorrencia.provincia_id=provincia.id\n" +
			"where ocorrencia.tipo_ocorrencia_id=1 and ocorrencia.procedencia='Sim' \n" +
			"     and ocorrencia.created between if(:datainicial='','2022-1-1',:datainicial) and if(:datafinal ='',CURRENT_DATE,:datafinal)" +
			"   \n" +
			"   \t and if(:tipoOcorrencia='',1=1, tipo_ocorrencia.designacao=:tipoOcorrencia)\n" +
			"   \t and if(:estado='',1=1,resolucao =:estado )\n" +
			"   \t and if(:projecto='',1=1,projecto.designacao=:projecto)\n" +
			"   \t and if(:provincia='',1=1, provincia.designacao =:provincia)\n" +
			"   \t and if(:categoria='',1=1,categoria.designacao=:categoria)\n" +
			"group by forma_comunicacao", nativeQuery=true)
	List<Object[]> busqueTudoAgrupadoPorCanalDeEntradaPesquisa(Date datainicial, Date datafinal, String tipoOcorrencia, String estado, String projecto, String provincia, String categoria);

	@Query(value="select sexo,count(sexo) \n" +
			"from ocorrencia  \n" +
			"inner join categoria on ocorrencia.categoriaid = categoria.id\n" +
			"inner join tipo_ocorrencia on ocorrencia.tipo_ocorrencia_id = tipo_ocorrencia.id\n" +
			"inner join projecto on ocorrencia.projecto_id=projecto.id \n" +
			"inner join provincia on ocorrencia.provincia_id=provincia.id\n" +
			"where sexo is not null and ocorrencia.tipo_ocorrencia_id=1 and procedencia='Sim'\n" +
			"\tand ocorrencia.created between if(:datainicial='','2022-1-1',:datainicial) and if(:datafinal ='',CURRENT_DATE,:datafinal) \n" +
			"\tand if(:tipoOcorrencia='',1=1, tipo_ocorrencia.designacao=:tipoOcorrencia)\n" +
			"\tand if(:estado='',1=1,resolucao =:estado )\n" +
			"\tand if(:projecto='',1=1,projecto.designacao=:projecto)\n" +
			"\tand if(:provincia='',1=1, provincia.designacao =:provincia)\n" +
			"\tand if(:categoria='',1=1,categoria.designacao=:categoria)\n" +
			"group by sexo", nativeQuery=true)
	List<Object[]> busqueTudoAgrupadoPorPeSexoPesquisa(Date datainicial, Date datafinal, String tipoOcorrencia, String estado, String projecto, String provincia, String categoria);

	@Query(value="select tipo_ocorrencia.designacao,count(tipo_ocorrencia_id)\n" +
			" from ocorrencia  \n" +
			" inner join categoria on ocorrencia.categoriaid = categoria.id\n" +
			"inner join tipo_ocorrencia on ocorrencia.tipo_ocorrencia_id = tipo_ocorrencia.id\n" +
			"inner join projecto on ocorrencia.projecto_id=projecto.id \n" +
			"inner join provincia on ocorrencia.provincia_id=provincia.id\n" +
			" where ocorrencia.tipo_ocorrencia_id=1 and ocorrencia.procedencia='Sim' \n" +
			"\tand ocorrencia.created between if(:datainicial='','2022-1-1',:datainicial) and if(:datafinal ='',CURRENT_DATE,:datafinal) \n" +
			"\tand if(:tipoOcorrencia='',1=1, tipo_ocorrencia.designacao=:tipoOcorrencia)\n" +
			"\tand if(:estado='',1=1,resolucao =:estado )\n" +
			"\tand if(:projecto='',1=1,projecto.designacao=:projecto)\n" +
			"\tand if(:provincia='',1=1, provincia.designacao =:provincia)\n" +
			"\tand if(:categoria='',1=1,categoria.designacao=:categoria)\n" +
			"group by tipo_ocorrencia_id", nativeQuery=true)
	List<Object[]> busqueTudoAgrupadoTipoDePreocupacaoPesquisa(Date datainicial, Date datafinal, String tipoOcorrencia, String estado, String projecto, String provincia, String categoria);

	@Query(value=" select categoria.designacao,categoriaid \n" +
			"from ocorrencia \n" +
			"inner join categoria on ocorrencia.categoriaid = categoria.id\n" +
			"inner join tipo_ocorrencia on ocorrencia.tipo_ocorrencia_id = tipo_ocorrencia.id\n" +
			"inner join projecto on ocorrencia.projecto_id=projecto.id \n" +
			"inner join provincia on ocorrencia.provincia_id=provincia.id\n" +
			"where ocorrencia.tipo_ocorrencia_id=1 and procedencia='Sim'\n" +
			"and ocorrencia.created between if(:datainicial='','2022-1-1',:datainicial) and if(:datafinal ='',CURRENT_DATE,:datafinal) \n" +
			"and if(:tipoOcorrencia='',1=1, tipo_ocorrencia.designacao=:tipoOcorrencia)\n" +
			"and if(:estado='',1=1,resolucao =:estado )\n" +
			"and if(:projecto='',1=1,projecto.designacao=:projecto)\n" +
			"and if(:provincia='',1=1, provincia.designacao =:provincia)\n" +
			"and if(:categoria='',1=1,categoria.designacao=:categoria)\n" +
			" group by categoriaid", nativeQuery=true)
	List<Object[]> busqueTudoAgrupadoPorCategoriaPesquisa(Date datainicial, Date datafinal, String tipoOcorrencia, String estado, String projecto, String provincia, String categoria);

	@Query(value="Select IF(nrmes=1,'Janeiro',if(nrmes=2,'Fevereiro',if(nrmes=3,'Março',if(nrmes=4,'Abril',if(nrmes=5,'Maio',if(nrmes=6,'Junho',if(nrmes=7,'Julho',if(nrmes=8,'Agosto',if(nrmes=9,'Setembro',if(nrmes=10,'Outubro',if(nrmes=11,'Novembro','Dezembro'))))))))))) as mes,t.RT,t.RA,t.RN from  (SELECT  SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'T' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RT',  SUM(CASE WHEN O.procedencia='Sim' and resolucao IN ('R','A') and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RA',   SUM(CASE WHEN O.procedencia='Sim' and resolucao = 'V' and O.tipo_ocorrencia_id=1 THEN 1 ELSE 0 END) AS 'RN',   month(O.created) as nrmes \n" +
			"FROM  ocorrencia O \n" +
			"inner join projecto P on O.projecto_id=P.id \n" +
			"inner join provincia Pr on O.provincia_id=Pr.id\n" +
			"inner join tipo_ocorrencia on O.tipo_ocorrencia_id = tipo_ocorrencia.id \n" +
			"inner join projecto on O.projecto_id=projecto.id \n" +
			"inner join categoria on O.categoriaid = categoria.id   \n" +
			"where O.tipo_ocorrencia_id=1 and O.procedencia='Sim'  and O.created between if(:datainicial='','2022-1-1',:datainicial) and if(:datafinal ='',CURRENT_DATE,:datafinal) and if(:tipoOcorrencia='',1=1, tipo_ocorrencia.designacao=:tipoOcorrencia) and if(:estado='',1=1,resolucao =:estado) and if(:projecto='',1=1,projecto.designacao=:projecto) and if(:provincia='',1=1, Pr.designacao =:provincia) and if(:categoria='',1=1,categoria.designacao=:categoria) GROUP BY monthname(O.created))t order by nrmes;", nativeQuery=true)
	List<Object[]> busqueTnTIPesquisa(Date datainicial, Date datafinal, String tipoOcorrencia, String estado, String projecto, String provincia, String categoria);




	/* FIM */



}

