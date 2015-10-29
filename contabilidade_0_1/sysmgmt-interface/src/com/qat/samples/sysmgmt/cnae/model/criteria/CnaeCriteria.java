package com.qat.samples.sysmgmt.cnae.model.criteria;

import java.io.Serializable;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class CnaeCriteria implements Serializable
{

	/** The member. */
	private String nome;

	/** The recipient id. */
	private Integer id;

	/** The location name. */
	private String cidade;

	/**
	 * The Constructor.
	 */
	public CnaeCriteria()
	{
		super();
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
	 * @return the cidade
	 */
	public String getCidade()
	{
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade)
	{
		this.cidade = cidade;
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

	@Override
	public String toString()
	{
		return "CnaeCriteria [getNome()=" + getNome() + ", getCidade()=" + getCidade() + ", getId()=" + getId()
				+ ", toString()=" + super.toString() + "]";
	}

}
