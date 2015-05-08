package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModel;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Telefone extends QATModel
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String ddd;

	/** The description. */
	private String numero;

	private String type;

	/**
	 * Default constructor.
	 */
	public Telefone()
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
	 * @return the ddd
	 */
	public String getDdd()
	{
		return ddd;
	}

	/**
	 * @param ddd the ddd to set
	 */
	public void setDdd(String ddd)
	{
		this.ddd = ddd;
	}

	/**
	 * @return the numero
	 */
	public String getNumero()
	{
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Telefone [getId()=" + getId() + ", getDdd()=" + getDdd() + ", getNumero()=" + getNumero()
				+ ", getType()=" + getType() + ", toString()=" + super.toString() + "]";
	}

}
