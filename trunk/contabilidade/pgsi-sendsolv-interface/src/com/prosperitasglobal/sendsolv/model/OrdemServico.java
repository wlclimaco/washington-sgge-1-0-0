package com.prosperitasglobal.sendsolv.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class OrdemServico extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The description. */
	private String nome;

	/** The estado. */
	private Integer data;

	/** The bairro. */
	private OrdemServicotypes typeId;

	/** The numero. */
	private String assunto;

	/** The tipo endereco. */
	private List<OrdemServicoItens> ordemStatusList;

	/**
	 * Default constructor.
	 */
	public OrdemServico()
	{
		super();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the cd empresa.
	 *
	 * @return the cd empresa
	 */
	public Integer getCdEmpresa()
	{
		return cdEmpresa;
	}

	/**
	 * Sets the cd empresa.
	 *
	 * @param cdEmpresa the new cd empresa
	 */
	public void setCdEmpresa(Integer cdEmpresa)
	{
		this.cdEmpresa = cdEmpresa;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Integer getData()
	{
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Integer data)
	{
		this.data = data;
	}

	/**
	 * Gets the assunto.
	 *
	 * @return the assunto
	 */
	public String getAssunto()
	{
		return assunto;
	}

	/**
	 * Sets the assunto.
	 *
	 * @param assunto the new assunto
	 */
	public void setAssunto(String assunto)
	{
		this.assunto = assunto;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "OrdemServico [getId()=" + getId() + ", getCdEmpresa()=" + getCdEmpresa() + ", getNome()=" + getNome()
				+ ", getData()=" + getData() + ", getType()=" + getType() + ", getAssunto()=" + getAssunto()
				+ ", toString()=" + super.toString() + "]";
	}

}
