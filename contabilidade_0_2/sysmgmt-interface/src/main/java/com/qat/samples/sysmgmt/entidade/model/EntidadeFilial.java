package com.qat.samples.sysmgmt.entidade.model;

import java.util.List;

import com.qat.samples.sysmgmt.banco.model.BancoPessoa;
import com.qat.samples.sysmgmt.cnae.model.CnaeEmpresa;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.pessoa.model.Socio;
import com.qat.samples.sysmgmt.site.model.PlanoByEmpresa;
import com.qat.samples.sysmgmt.site.model.Site;
import com.qat.samples.sysmgmt.util.model.Documento;
import com.qat.samples.sysmgmt.util.model.Email;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.model.Telefone;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */
@SuppressWarnings("serial")
public class EntidadeFilial extends ModelCosmeDamiao {
	/** The SendSolv id for the account. */
	private Integer id;

	/** The status empresa. */
	private Integer statusEmpresa;

	/** The num func. */
	private Integer numFunc;

	/** The type of an account. */
	private String nome;

	/** The razao. */
	private String razao;

	/** The regime. */
	private Regime regime;

	/** The dt abertura. */
	private Long dtAbertura;

	/** The entidade id. */
	private Integer entidadeId;

	/** The tipo. */
	private Integer tipo;

	/** The tipo pessoa. */
	private Integer tipoPessoa;

	/** The cpf responsavel. */
	private String cpfResponsavel;

	/** The primeiro acesso. */
	private Integer primeiroAcesso;

	/** The entidade enum. */
	private EntidadeTypeEnum entidadeEnum;

	/** The configuracao. */
	private Configuracao configuracao;

	/** The enderecos. */
	private List<Endereco> enderecos;

	/** The documentos. */
	private List<Documento> documentos;

	/** The emails. */
	private List<Email> emails;

	/** The telefones. */
	private List<Telefone> telefones;

	/** The cnaes. */
	private List<CnaeEmpresa> cnaes;

	/** The usuarios. */
	private List<Usuario> usuarios;

	/** The bancos. */
	private List<BancoPessoa> bancos;

	/** The planos servicos. */
	private PlanoByEmpresa planosServicos;

	/** The socios. */
	private List<Socio> socios;

	/** The site list. */
	private List<Site> siteList;

	/** The responsavel. */
	private String responsavel;

	/** The notificacoes. */
	private List<NotificationPreferences> notificacoes;

	/** The permissao type enum. */
	private EmpresaTypeEnum permissaoTypeEnum;

	private List<Integer> emprIds;


	/**
	 * Gets the usuarios.
	 *
	 * @return the usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * Sets the usuarios.
	 *
	 * @param usuarios the new usuarios
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * Gets the bancos.
	 *
	 * @return the bancos
	 */
	public List<BancoPessoa> getBancos() {
		return bancos;
	}

	/**
	 * Sets the bancos.
	 *
	 * @param bancos the new bancos
	 */
	public void setBancos(List<BancoPessoa> bancos) {
		this.bancos = bancos;
	}

	/**
	 * Default constructor.
	 */
	public EntidadeFilial() {
		super();
	}

	/**
	 * Gets the permissao type enum value.
	 *
	 * @return the permissao type enum value
	 */
	public Integer getPermissaoTypeEnumValue() {
		if (permissaoTypeEnum != null) {
			return permissaoTypeEnum.getValue();
		}
		return null;
	}

	/**
	 * Sets the permissao type enum value.
	 *
	 * @param acaoTypeValue the new permissao type enum value
	 */
	public void setPermissaoTypeEnumValue(Integer acaoTypeValue) {
		permissaoTypeEnum = EmpresaTypeEnum.enumForValue(acaoTypeValue);
	}

	/**
	 * Gets the permissao type enum.
	 *
	 * @return the permissao type enum
	 */
	public EmpresaTypeEnum getPermissaoTypeEnum() {
		return permissaoTypeEnum;
	}

	/**
	 * Sets the permissao type enum.
	 *
	 * @param permissaoTypeEnum the new permissao type enum
	 */
	public void setPermissaoTypeEnum(EmpresaTypeEnum permissaoTypeEnum) {
		this.permissaoTypeEnum = permissaoTypeEnum;
	}

	/**
	 * Gets the entidade enum value.
	 *
	 * @return the entidade enum value
	 */
	public Integer getEntidadeEnumValue() {
		if (entidadeEnum != null) {
			return entidadeEnum.getValue();
		}
		return null;
	}

	/**
	 * Sets the entidade enum value.
	 *
	 * @param acaoTypeValue the new entidade enum value
	 */
	public void setEntidadeEnumValue(Integer acaoTypeValue) {
		entidadeEnum = EntidadeTypeEnum.enumForValue(acaoTypeValue);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the enderecos.
	 *
	 * @return the enderecos
	 */
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	/**
	 * Sets the enderecos.
	 *
	 * @param enderecos            the enderecos to set
	 */
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	/**
	 * Gets the documentos.
	 *
	 * @return the documentos
	 */
	public List<Documento> getDocumentos() {
		return documentos;
	}

	/**
	 * Sets the documentos.
	 *
	 * @param documentos            the documentos to set
	 */
	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	/**
	 * Gets the emails.
	 *
	 * @return the emails
	 */
	public List<Email> getEmails() {
		return emails;
	}

	/**
	 * Sets the emails.
	 *
	 * @param emails            the emails to set
	 */
	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	/**
	 * Gets the telefones.
	 *
	 * @return the telefones
	 */
	public List<Telefone> getTelefones() {
		return telefones;
	}

	/**
	 * Sets the telefones.
	 *
	 * @param telefones            the telefones to set
	 */
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	/**
	 * Gets the cnaes.
	 *
	 * @return the cnaes
	 */
	public List<CnaeEmpresa> getCnaes() {
		return cnaes;
	}

	/**
	 * Sets the cnaes.
	 *
	 * @param cnaes            the cnaes to set
	 */
	public void setCnaes(List<CnaeEmpresa> cnaes) {
		this.cnaes = cnaes;
	}

	/**
	 * Gets the regime.
	 *
	 * @return the regime
	 */
	public Regime getRegime() {
		return regime;
	}

	/**
	 * Sets the regime.
	 *
	 * @param regime            the regime to set
	 */
	public void setRegime(Regime regime) {
		this.regime = regime;
	}

	/**
	 * Gets the entidade enum.
	 *
	 * @return the entidadeEnum
	 */
	public EntidadeTypeEnum getEntidadeEnum() {
		return entidadeEnum;
	}

	/**
	 * Sets the entidade enum.
	 *
	 * @param entidadeEnum            the entidadeEnum to set
	 */
	public void setEntidadeEnum(EntidadeTypeEnum entidadeEnum) {
		this.entidadeEnum = entidadeEnum;
	}

	/**
	 * Gets the configuracao.
	 *
	 * @return the configuracao
	 */
	public Configuracao getConfiguracao() {
		return configuracao;
	}

	/**
	 * Sets the configuracao.
	 *
	 * @param configuracao            the configuracao to set
	 */
	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}

	/**
	 * Gets the entidade id.
	 *
	 * @return the entidadeId
	 */
	public Integer getEntidadeId() {
		return entidadeId;
	}

	/**
	 * Sets the entidade id.
	 *
	 * @param entidadeId            the entidadeId to set
	 */
	public void setEntidadeId(Integer entidadeId) {
		this.entidadeId = entidadeId;
	}

	/**
	 * Gets the notificacoes.
	 *
	 * @return the notificacoes
	 */
	public List<NotificationPreferences> getNotificacoes() {
		return notificacoes;
	}

	/**
	 * Sets the notificacoes.
	 *
	 * @param notificacoes the new notificacoes
	 */
	public void setNotificacoes(List<NotificationPreferences> notificacoes) {
		this.notificacoes = notificacoes;
	}

	/**
	 * Gets the num func.
	 *
	 * @return the num func
	 */
	public Integer getNumFunc() {
		return numFunc;
	}

	/**
	 * Sets the num func.
	 *
	 * @param numFunc the new num func
	 */
	public void setNumFunc(Integer numFunc) {
		this.numFunc = numFunc;
	}

	/**
	 * Gets the planos servicos.
	 *
	 * @return the planos servicos
	 */
	public PlanoByEmpresa getPlanosServicos() {
		return planosServicos;
	}

	/**
	 * Sets the planos servicos.
	 *
	 * @param planosServicos the new planos servicos
	 */
	public void setPlanosServicos(PlanoByEmpresa planosServicos) {
		this.planosServicos = planosServicos;
	}

	/**
	 * Gets the socios.
	 *
	 * @return the socios
	 */
	public List<Socio> getSocios() {
		return socios;
	}

	/**
	 * Sets the socios.
	 *
	 * @param socios the new socios
	 */
	public void setSocios(List<Socio> socios) {
		this.socios = socios;
	}

	/**
	 * Gets the razao.
	 *
	 * @return the razao
	 */
	public String getRazao() {
		return razao;
	}

	/**
	 * Sets the razao.
	 *
	 * @param razao the new razao
	 */
	public void setRazao(String razao) {
		this.razao = razao;
	}

	/**
	 * Gets the status empresa.
	 *
	 * @return the status empresa
	 */
	public Integer getStatusEmpresa() {
		return statusEmpresa;
	}

	/**
	 * Sets the status empresa.
	 *
	 * @param statusEmpresa the new status empresa
	 */
	public void setStatusEmpresa(Integer statusEmpresa) {
		this.statusEmpresa = statusEmpresa;
	}

	/**
	 * Gets the dt abertura.
	 *
	 * @return the dt abertura
	 */
	public Long getDtAbertura() {
		return dtAbertura;
	}

	/**
	 * Sets the dt abertura.
	 *
	 * @param dtAbertura the new dt abertura
	 */
	public void setDtAbertura(Long dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	/**
	 * Gets the responsavel.
	 *
	 * @return the responsavel
	 */
	public String getResponsavel() {
		return responsavel;
	}

	/**
	 * Sets the responsavel.
	 *
	 * @param responsavel the new responsavel
	 */
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	/**
	 * Gets the site list.
	 *
	 * @return the site list
	 */
	public List<Site> getSiteList() {
		return siteList;
	}

	/**
	 * Sets the site list.
	 *
	 * @param siteList the new site list
	 */
	public void setSiteList(List<Site> siteList) {
		this.siteList = siteList;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public Integer getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the primeiro acesso.
	 *
	 * @return the primeiro acesso
	 */
	public Integer getPrimeiroAcesso() {
		return primeiroAcesso;
	}

	/**
	 * Sets the primeiro acesso.
	 *
	 * @param primeiroAcesso the new primeiro acesso
	 */
	public void setPrimeiroAcesso(Integer primeiroAcesso) {
		this.primeiroAcesso = primeiroAcesso;
	}

	/**
	 * Gets the tipo pessoa.
	 *
	 * @return the tipo pessoa
	 */
	public Integer getTipoPessoa() {
		return tipoPessoa;
	}

	/**
	 * Sets the tipo pessoa.
	 *
	 * @param tipoPessoa the new tipo pessoa
	 */
	public void setTipoPessoa(Integer tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	/**
	 * Gets the cpf responsavel.
	 *
	 * @return the cpf responsavel
	 */
	public String getCpfResponsavel() {
		return cpfResponsavel;
	}

	/**
	 * Sets the cpf responsavel.
	 *
	 * @param cpfResponsavel the new cpf responsavel
	 */
	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}


	public List<Integer> getEmprIds() {
		return emprIds;
	}

	public void setEmprIds(List<Integer> emprIds) {
		this.emprIds = emprIds;
	}

	@Override
	public String toString() {
		return "Entidade [getUsuarios()=" + getUsuarios() + ", getBancos()=" + getBancos()
				+ ", getPermissaoTypeEnumValue()=" + getPermissaoTypeEnumValue() + ", getPermissaoTypeEnum()="
				+ getPermissaoTypeEnum() + ", getEntidadeEnumValue()=" + getEntidadeEnumValue() + ", getId()=" + getId()
				+ ", getNome()=" + getNome() + ", getEnderecos()=" + getEnderecos() + ", getDocumentos()="
				+ getDocumentos() + ", getEmails()=" + getEmails() + ", getTelefones()=" + getTelefones()
				+ ", getCnaes()=" + getCnaes() + ", getRegime()=" + getRegime() + ", getEntidadeEnum()="
				+ getEntidadeEnum() + ", getConfiguracao()=" + getConfiguracao() + ", getEntidadeId()="
				+ getEntidadeId() + ", getNotificacoes()=" + getNotificacoes() + ", getNumFunc()=" + getNumFunc()
				+ ", getPlanosServicos()=" + getPlanosServicos() + ", getSocios()=" + getSocios() + ", getRazao()="
				+ getRazao() + ", getStatusEmpresa()=" + getStatusEmpresa() + ", getDtAbertura()=" + getDtAbertura()
				+ ", getResponsavel()=" + getResponsavel() + ", getSiteList()=" + getSiteList() + ", getTipo()="
				+ getTipo() + ", getPrimeiroAcesso()=" + getPrimeiroAcesso() + ", getTipoPessoa()=" + getTipoPessoa()
				+ ", getCpfResponsavel()=" + getCpfResponsavel() + ", getEmprIds()=" + getEmprIds() + ", toString()="
				+ super.toString() + "]";
	}

}
