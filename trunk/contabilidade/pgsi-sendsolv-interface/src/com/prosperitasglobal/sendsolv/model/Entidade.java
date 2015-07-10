package com.prosperitasglobal.sendsolv.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Entidade extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String nome;

	private Regime regime;

	private EntidadeTypeEnum entidadeEnum;

	private Configuracao configuracao;

	private List<Endereco> enderecos;

	private List<Documento> documentos;

	private List<Email> emails;

	private List<Telefone> Telefones;

	private List<Socio> socios;

	private List<Cnae> cnaes;

	private List<Filial> filialList;

	private List<Deposito> depositoList;

	/**
	 * Default constructor.
	 */
	public Entidade()
	{
		super();
	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * @return the enderecos
	 */
	public List<Endereco> getEnderecos()
	{
		return enderecos;
	}

	/**
	 * @param enderecos the enderecos to set
	 */
	public void setEnderecos(List<Endereco> enderecos)
	{
		this.enderecos = enderecos;
	}

	/**
	 * @return the documentos
	 */
	public List<Documento> getDocumentos()
	{
		return documentos;
	}

	/**
	 * @param documentos the documentos to set
	 */
	public void setDocumentos(List<Documento> documentos)
	{
		this.documentos = documentos;
	}

	/**
	 * @return the emails
	 */
	public List<Email> getEmails()
	{
		return emails;
	}

	/**
	 * @param emails the emails to set
	 */
	public void setEmails(List<Email> emails)
	{
		this.emails = emails;
	}

	/**
	 * @return the telefones
	 */
	public List<Telefone> getTelefones()
	{
		return Telefones;
	}

	/**
	 * @param telefones the telefones to set
	 */
	public void setTelefones(List<Telefone> telefones)
	{
		Telefones = telefones;
	}

	/**
	 * @return the socios
	 */
	public List<Socio> getSocios()
	{
		return socios;
	}

	/**
	 * @param socios the socios to set
	 */
	public void setSocios(List<Socio> socios)
	{
		this.socios = socios;
	}

	/**
	 * @return the cnaes
	 */
	public List<Cnae> getCnaes()
	{
		return cnaes;
	}

	/**
	 * @param cnaes the cnaes to set
	 */
	public void setCnaes(List<Cnae> cnaes)
	{
		this.cnaes = cnaes;
	}

	/**
	 * @return the regime
	 */
	public Regime getRegime()
	{
		return regime;
	}

	/**
	 * @param regime the regime to set
	 */
	public void setRegime(Regime regime)
	{
		this.regime = regime;
	}

	/**
	 * @return the entidadeEnum
	 */
	public EntidadeTypeEnum getEntidadeEnum()
	{
		return entidadeEnum;
	}

	/**
	 * @param entidadeEnum the entidadeEnum to set
	 */
	public void setEntidadeEnum(EntidadeTypeEnum entidadeEnum)
	{
		this.entidadeEnum = entidadeEnum;
	}

	/**
	 * @return the configuracao
	 */
	public Configuracao getConfiguracao()
	{
		return configuracao;
	}

	/**
	 * @param configuracao the configuracao to set
	 */
	public void setConfiguracao(Configuracao configuracao)
	{
		this.configuracao = configuracao;
	}

	/**
	 * @return the filialList
	 */
	public List<Filial> getFilialList()
	{
		return filialList;
	}

	/**
	 * @param filialList the filialList to set
	 */
	public void setFilialList(List<Filial> filialList)
	{
		this.filialList = filialList;
	}

	/**
	 * @return the depositoList
	 */
	public List<Deposito> getDepositoList()
	{
		return depositoList;
	}

	/**
	 * @param depositoList the depositoList to set
	 */
	public void setDepositoList(List<Deposito> depositoList)
	{
		this.depositoList = depositoList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Entidade [getId()=" + getId() + ", getNome()=" + getNome() + ", getEnderecos()=" + getEnderecos()
				+ ", getDocumentos()=" + getDocumentos() + ", getEmails()=" + getEmails() + ", getTelefones()="
				+ getTelefones() + ", getSocios()=" + getSocios() + ", getCnaes()=" + getCnaes() + ", getRegime()="
				+ getRegime() + ", getEntidadeEnum()=" + getEntidadeEnum() + ", getConfiguracao()=" + getConfiguracao()
				+ ", getFilialList()=" + getFilialList() + ", getDepositoList()=" + getDepositoList()
				+ ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()=" + getTypeValue()
				+ ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId() + ", getType()="
				+ getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum()
				+ ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId() + ", getSite()=" + getSite()
				+ ", getProcessId()=" + getProcessId() + ", getUserId()=" + getUserId() + ", toString()="
				+ super.toString() + ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
