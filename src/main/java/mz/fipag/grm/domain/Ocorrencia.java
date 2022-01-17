package mz.fipag.grm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "ocorrencia")
public class Ocorrencia extends AbstractEntity{

	@Column(name = "grm_stamp", nullable = true)
	private String grmStamp;
	
	@Column(name = "numero", nullable = true)
	private int numero;
	
	@Column(name = "data_grm", nullable = true)
	@DateTimeFormat(iso = ISO.DATE)
	private Date dataOcorrencia;
	
	@Column(name = "comunidade", nullable = false)
	private String comunidade;
	
	@Column(name = "assento", nullable = false)
	private String assento;
	
	@Column(name = "nome_utente",  nullable = false)
	private String nomeUtente;
	
	@Column(name = "sexo",  nullable = false)
	private String sexo;
	
	@Column(name = "faixa_etaria", nullable = false)
	private String faixaEtaria;
	
	@Column(name = "contacto_utente", nullable = false)
	private String contactoUtente;
	
	@Column(name = "email_utente", nullable = true)
	private String emailUtente;
	
	@Column(name = "morada_utente", nullable = true)
	private String moradaUtente;
	
	@Column(name = "nome_grupo", nullable = true)
	private String nomeGrupo;
	
	@Column(name = "forma_comunicacao", nullable = true)
	private String formaComunicacao;
	
	@Column(name = "numero_homens", nullable = true)
	private int numeroHomens;
	
	@Column(name = "numero_mulheres", nullable = true)
	private int numeroMulheres;
	
	@Column(name = "numero_mdias_registo", nullable = true)
	private int numeroDiasRegisto;
	
	@Column(name = "origem", nullable = true)
	private String origem;
	
	@Column(name = "data_classificacao", nullable = true)
	private Date dataClassificacao;
	
	@Column(name = "procedencia", nullable = true)
	private boolean procedencia;
	
	@Column(name = "data_resposta", nullable = true)
	private Date dataResposta;
	
	@Column(name = "forma_resposta", nullable = true)
	private String formaResposta;
	
	@Column(name = "resposta",  nullable = true)
	private String resposta;
	
	@Column(name = "tipo_correncia",nullable = true)
	private String tipoOcorrencia;
	
	@ManyToOne
	@JoinColumn(name="distrito_id", nullable = true)
	private Distrito distrito;
	
	@ManyToOne
	@JoinColumn(name="posto_id", nullable = true)
	private PostoAdministrativo postoAdministrativo;
	
	@ManyToOne
	@JoinColumn(name="queixa_id", nullable = true)
	private TipoQueixa tipoQueixa;
	
	@ManyToOne
	@JoinColumn(name="projecto_id", nullable = true)
	private Projecto projecto;
	
	@ManyToOne
	@JoinColumn(name="categoria_id", nullable = true)
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

	public String getAssento() {
		return assento;
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

	public Date getDataResposta() {
		return dataResposta;
	}

	public String getFormaResposta() {
		return formaResposta;
	}

	public String getResposta() {
		return resposta;
	}

	public String getTipoOcorrencia() {
		return tipoOcorrencia;
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

	public void setAssento(String assento) {
		this.assento = assento;
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

	public void setDataResposta(Date dataResposta) {
		this.dataResposta = dataResposta;
	}

	public void setFormaResposta(String formaResposta) {
		this.formaResposta = formaResposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public void setTipoOcorrencia(String tipoOcorrencia) {
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
}
