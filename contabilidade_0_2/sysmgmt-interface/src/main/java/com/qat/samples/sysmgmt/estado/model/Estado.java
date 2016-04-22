package com.qat.samples.sysmgmt.estado.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Estado extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The account number. */
	private String nome;

	/** The abreviado. */
	private String abreviacao;

	/**
	 * Default constructor.
	 */
	public Estado()
	{
		super();
	}

	public Estado(Integer id)
	{
		super();
		this.id = id;
	}

	public Estado(int i, String string) {
		// TODO Auto-generated constructor stub
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
	 * @return the abreviacao
	 */
	public String getAbreviacao()
	{
		return abreviacao;
	}

	/**
	 * @param abreviacao the abreviacao to set
	 */
	public void setAbreviacao(String abreviacao)
	{
		this.abreviacao = abreviacao;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "Estado [getId()=" + getId() + ", getNome()=" + getNome() + ", getAbreviacao()=" + getAbreviacao()
				+ ", toString()=" + super.toString() + "]";
	}

}
