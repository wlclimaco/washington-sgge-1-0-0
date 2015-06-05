package com.prosperitasglobal.sendsolv.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Agencia extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String nome;

	private List<Endereco> enderecos;
	private List<Email> emails;
	private List<Telefone> telefones;

	private String gerente;

	private String responsavelConta;

	private String numeroConta;

	public Agencia()
	{

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
		return telefones;
	}

	/**
	 * @param telefones the telefones to set
	 */
	public void setTelefones(List<Telefone> telefones)
	{
		this.telefones = telefones;
	}

	/**
	 * @return the gerente
	 */
	public String getGerente()
	{
		return gerente;
	}

	/**
	 * @param gerente the gerente to set
	 */
	public void setGerente(String gerente)
	{
		this.gerente = gerente;
	}

	/**
	 * @return the responsavelConta
	 */
	public String getResponsavelConta()
	{
		return responsavelConta;
	}

	/**
	 * @param responsavelConta the responsavelConta to set
	 */
	public void setResponsavelConta(String responsavelConta)
	{
		this.responsavelConta = responsavelConta;
	}

	/**
	 * @return the numeroConta
	 */
	public String getNumeroConta()
	{
		return numeroConta;
	}

	/**
	 * @param numeroConta the numeroConta to set
	 */
	public void setNumeroConta(String numeroConta)
	{
		this.numeroConta = numeroConta;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Agencia [getId()=" + getId() + ", getNome()=" + getNome() + ", getEnderecos()=" + getEnderecos()
				+ ", getEmails()=" + getEmails() + ", getTelefones()=" + getTelefones() + ", getGerente()="
				+ getGerente() + ", getResponsavelConta()=" + getResponsavelConta() + ", getNumeroConta()="
				+ getNumeroConta() + ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()="
				+ getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId()
				+ ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()="
				+ getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId()
				+ ", getSite()=" + getSite() + ", toString()=" + super.toString() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
