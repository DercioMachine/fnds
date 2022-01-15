package mz.fipag.grm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name = "ocorrencia")
public class Ocorrencia extends AbstractEntity{

	@Column(name = "grm_stamp", unique = true, nullable = false)
	private String grmStamp;
	
	@Column(name = "numero", unique = true, nullable = false)
	private int numero;
	
	@Column(name = "data_grm", nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private Date dataOcorrencia;
	
	@Column(name = "comunidade", unique = true, nullable = false)
	private String comunidade;
	
	@Column(name = "assento", unique = true, nullable = false)
	private String assento;
	
	@Column(name = "nome_utente", unique = true, nullable = false)
	private String nomeUtente;
	
	@Column(name = "sexo", unique = true, nullable = false)
	private String sexo;
	
	@Column(name = "faixa_etaria", unique = true, nullable = false)
	private String faixaEtaria;
	
	@Column(name = "contacto_utente", unique = true, nullable = false)
	private String contactoUtente;
	
	@Column(name = "email_utente", unique = true, nullable = false)
	private String emailUtente;
	
	@Column(name = "morada_utente", unique = true, nullable = false)
	private String moradaUtente;
	
	@Column(name = "nome_grupo", unique = true, nullable = false)
	private String nomeGrupo;
	
	@Column(name = "forma_comunicacao", unique = true, nullable = false)
	private String formaComunicacao;
	
	@Column(name = "numero_homens", unique = true, nullable = false)
	private int numeroHomens;
	
	@Column(name = "numero_mulheres", unique = true, nullable = false)
	private int numeroMulheres;
	
	@Column(name = "numero_mdias_registo", unique = true, nullable = false)
	private int numeroDiasRegisto;
	
	@Column(name = "origem", unique = true, nullable = false)
	private String origem;
	
	@Column(name = "data_classificacao", nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private Date dataClassificacao;
	
	@Column(name = "procedencia", nullable = false)
	private boolean procedencia;
	
	@Column(name = "data_resposta", nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private Date dataResposta;
	
	@Column(name = "forma_resposta", unique = true, nullable = false)
	private String formaResposta;
	
	@Column(name = "resposta", unique = true, nullable = false)
	private String resposta;
	
	@Column(name = "tipo-_correncia", unique = true, nullable = false)
	private String tipoOcorrencia;
	
	@ManyToOne
	@JoinColumn(name="distrito_id")
	private Distrito distrito;
	
	@ManyToOne
	@JoinColumn(name="posto_administrativo_id")
	private PostoAdministrativo postoAdministrativo;
	
	@ManyToOne
	@JoinColumn(name="tipo_queixa_id")
	private TipoQueixa tipoQueixa;
	
	@ManyToOne
	@JoinColumn(name="projecto_id")
	private Projecto projecto;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	
	
}
