package com.prosperitasglobal.sendsolv.model.criteria;

import java.io.Serializable;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class OrcamentoCriteria implements Serializable
{

	/** The business id. */
	private Integer empresa;

	/** The recipient id. */
	private Integer Id;

	/** The location name. */
	private String type;

	/** The organization name. */
	private String numero;

	/**
	 * The Constructor.
	 */
	public OrcamentoCriteria()
	{
		super();
	}

	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public Integer getEmpresa()
	{
		return empresa;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(Integer empresa)
	{
		this.empresa = empresa;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return Id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		Id = id;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero()
	{
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ArquivoCriteria [getEmpresa()=" + getEmpresa() + ", getId()="
				+ getId() + ", getType()=" + getType() + ", getNumero()=" + getNumero() + ", toString()="
				+ super.toString() + "]";
	}

}
