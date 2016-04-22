package com.qat.samples.sysmgmt.site.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Links extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String nome;

	private String url;

	private Integer zIndex;

	public Links()
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

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public Integer getzIndex()
	{
		return zIndex;
	}

	public void setzIndex(Integer zIndex)
	{
		this.zIndex = zIndex;
	}

	@Override
	public String toString()
	{
		return "Links [getId()=" + getId() + ", getNome()=" + getNome() + ", getUrl()=" + getUrl() + ", getzIndex()="
				+ getzIndex() + ", toString()=" + super.toString() + "]";
	}

}
