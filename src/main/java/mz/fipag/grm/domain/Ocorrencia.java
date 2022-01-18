package mz.fipag.grm.domain;

import java.time.LocalDate;
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

	@Column(name = "grm_stamp")
	private String grmStamp;
	
	@Column(name = "numero")
	private int numero;
	
	@Column(name = "data_grm")
	@DateTimeFormat(iso = ISO.DATE)
	private Date dataOcorrencia;
	
	@Column(name = "comunidade")
	private String comunidade;
	
	@Column(name = "assunto")
	private String assunto;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "nome_utente")
	private String nomeUtente;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Column(name = "faixa_etaria")
	private String faixaEtaria;
	
	@Column(name = "contacto_utente")
	private String contactoUtente;
	
	@Column(name = "email_utente")
	private String emailUtente;
	
	@Column(name = "morada_utente")
	private String moradaUtente;
	
	@Column(name = "nome_grupo")
	private String nomeGrupo;
	
	@Column(name = "forma_comunicacao")
	private String formaComunicacao;
	
	@Column(name = "numero_homens")
	private int numeroHomens;
	
	@Column(name = "numero_mulheres")
	private int numeroMulheres;
	
	@Column(name = "numero_mdias_registo")
	private int numeroDiasRegisto;
	
	@Column(name = "origem")
	private String origem;
	
	@Column(name = "data_classificacao")
	private Date dataClassificacao;
	
	@Column(name = "procedencia")
	private boolean procedencia;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_resposta", columnDefinition = "DATE")
	private LocalDate dataResposta;
	
	@Column(name = "forma_resposta")
	private String formaResposta;
	
	@Column(name = "resposta")
	private String resposta;
	
	
	@ManyToOne
	@JoinColumn(name="distrito_id")
	private Distrito distrito;
	
	@ManyToOne
	@JoinColumn(name="provincia_id")
	private Provincia provincia;
	
	@ManyToOne
	@JoinColumn(name="posto_id")
	private PostoAdministrativo postoAdministrativo;
	
	@ManyToOne
	@JoinColumn(name="tipoOcorrencia_id")
	private TipoOcorrencia tipoOcorrencia;
	
	@ManyToOne
	@JoinColumn(name="queixa_id")
	private TipoQueixa tipoQueixa;
	
	@ManyToOne
	@JoinColumn(name="projecto_id")
	private Projecto projecto;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;

	public String getGrmStamp() {
		return grmStamp;
	}

	public int getNumero() {
		return numero;
	}

	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}

	public String getComunidade() {
		return comunidade;
	}


	public String getNomeUtente() {
		return nomeUtente;
	}

	public String getSexo() {
		return sexo;
	}

	public String getFaixaEtaria() {
		return faixaEtaria;
	}

	public String getContactoUtente() {
		return contactoUtente;
	}

	public String getEmailUtente() {
		return emailUtente;
	}

	public String getMoradaUtente() {
		return moradaUtente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public String getFormaComunicacao() {
		return formaComunicacao;
	}

	public int getNumeroHomens() {
		return numeroHomens;
	}

	public int getNumeroMulheres() {
		return numeroMulheres;
	}

	public int getNumeroDiasRegisto() {
		return numeroDiasRegisto;
	}

	public String getOrigem() {
		return origem;
	}

	public Date getDataClassificacao() {
		return dataClassificacao;
	}

	public boolean isProcedencia() {
		return procedencia;
	}


	public String getFormaResposta() {
		return formaResposta;
	}

	public String getResposta() {
		return resposta;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public PostoAdministrativo getPostoAdministrativo() {
		return postoAdministrativo;
	}

	public TipoQueixa getTipoQueixa() {
		return tipoQueixa;
	}

	public Projecto getProjecto() {
		return projecto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setGrmStamp(String grmStamp) {
		this.grmStamp = grmStamp;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public void setComunidade(String comunidade) {
		this.comunidade = comunidade;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public void setContactoUtente(String contactoUtente) {
		this.contactoUtente = contactoUtente;
	}

	public void setEmailUtente(String emailUtente) {
		this.emailUtente = emailUtente;
	}

	public void setMoradaUtente(String moradaUtente) {
		this.moradaUtente = moradaUtente;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public void setFormaComunicacao(String formaComunicacao) {
		this.formaComunicacao = formaComunicacao;
	}

	public void setNumeroHomens(int numeroHomens) {
		this.numeroHomens = numeroHomens;
	}

	public void setNumeroMulheres(int numeroMulheres) {
		this.numeroMulheres = numeroMulheres;
	}

	public void setNumeroDiasRegisto(int numeroDiasRegisto) {
		this.numeroDiasRegisto = numeroDiasRegisto;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public void setDataClassificacao(Date dataClassificacao) {
		this.dataClassificacao = dataClassificacao;
	}

	public void setProcedencia(boolean procedencia) {
		this.procedencia = procedencia;
	}


	public LocalDate getDataResposta() {
		return dataResposta;
	}

	public void setDataResposta(LocalDate dataResposta) {
		this.dataResposta = dataResposta;
	}

	public void setFormaResposta(String formaResposta) {
		this.formaResposta = formaResposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}


	public TipoOcorrencia getTipoOcorrencia() {
		return tipoOcorrencia;
	}

	public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
		this.tipoOcorrencia = tipoOcorrencia;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public void setPostoAdministrativo(PostoAdministrativo postoAdministrativo) {
		this.postoAdministrativo = postoAdministrativo;
	}

	public void setTipoQueixa(TipoQueixa tipoQueixa) {
		this.tipoQueixa = tipoQueixa;
	}

	public void setProjecto(Projecto projecto) {
		this.projecto = projecto;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
}
