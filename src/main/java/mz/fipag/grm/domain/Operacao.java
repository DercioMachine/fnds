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
@Table(name = "operacao")
public class Operacao extends AbstractEntity{

	@Column(name = "grm_stamp")
	private String grmStamp;
	
	@Column(name = "nr_cliente")
	private String nrCliente;
	
	@Column(name = "numero")
	private int numero;
	
	@Column(name = "data_grm")
	@DateTimeFormat(iso = ISO.DATE)
	private Date dataOcorrencia;
	
	@Column(name = "comunidade")
	private String comunidade;
	
	@Column(name = "projecto_operacao")
	private String projectoOperacao;

	@Column(name = "interno_externo")
	private String internoExterno;

	@Column(name = "resolucao")
	private String resolucao="P";
	
	@Column(name = "temporario")
	private boolean temporario;
	
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "validado")
	private boolean validado;
	
	@Column(name = "registado")
	private boolean registado;
	
	@Column(name = "assunto")
	private String assunto;
	
	@Column(name = "observacao")
	private String observacao;
	
	
	//@NotBlank(message = "O preenchimento do assunto é obrigatório.")
	//@Size(min = 10, message = "A Descrição deve conter pelo menos 10 caracteres.")
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "nome_utente")
	private String nomeUtente;
	
	@Column(name = "nome_representante_grupo")
	private String nomeDoRepresentanteDoGrupo;
	
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
	private String procedencia;
	
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
	@JoinColumn(name="tipoorigem")
	private Origem tipoorigem;
	
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
	@JoinColumn(name="categoriaid")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="tipoAlerta_id")
	private TipoAlerta tipoAlerta;

	
	@ManyToOne
	@JoinColumn(name="empreiteiro_id")
	private Empreiteiro empreiteiro;
	
	@ManyToOne
	@JoinColumn(name="assunto_id")
	private Assunto assunto2;

	public Origem getTipoorigem() {
		return tipoorigem;
	}

	public void setTipoorigem(Origem tipoorigem) {
		this.tipoorigem = tipoorigem;
	}

	public boolean isTemporario() {
		return temporario;
	}

	public void setTemporario(boolean temporario) {
		this.temporario = temporario;
	}

	public String getResolucao() {
		return resolucao;
	}

	public void setResolucao(String resolucao) {
		this.resolucao = resolucao;
	}

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
	
	public String getNrCliente() {
		return nrCliente;
	}

	public void setNrCliente(String nrCliente) {
		this.nrCliente = nrCliente;
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


	public LocalDate getDataResposta() {
		return dataResposta;
	}
	
	
	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
	

	public String getProjectoOperacao() {
		return projectoOperacao;
	}

	public void setProjectoOperacao(String projectoOperacao) {
		this.projectoOperacao = projectoOperacao;
	}

	public String getNomeDoRepresentanteDoGrupo() {
		return nomeDoRepresentanteDoGrupo;
	}

	public void setNomeDoRepresentanteDoGrupo(String nomeDoRepresentanteDoGrupo) {
		this.nomeDoRepresentanteDoGrupo = nomeDoRepresentanteDoGrupo;
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

	public TipoAlerta getTipoAlerta() {
		return tipoAlerta;
	}

	public void setTipoAlerta(TipoAlerta tipoAlerta) {
		this.tipoAlerta = tipoAlerta;
	}

	public boolean isValidado() {
		return validado;
	}

	public void setValidado(boolean validado) {
		this.validado = validado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isRegistado() {
		return registado;
	}

	public void setRegistado(boolean registado) {
		this.registado = registado;
	}

	public Empreiteiro getEmpreiteiro() {
		return empreiteiro;
	}

	public void setEmpreiteiro(Empreiteiro empreiteiro) {
		this.empreiteiro = empreiteiro;
	}

	public String getInternoExterno() {
		return internoExterno;
	}

	public void setInternoExterno(String internoExterno) {
		this.internoExterno = internoExterno;
	}

	public Assunto getAssunto2() {
		return assunto2;
	}

	public void setAssunto2(Assunto assunto2) {
		this.assunto2 = assunto2;
	}
	
}
