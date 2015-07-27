package com.prosperitasglobal.sendsolv.model.criteria;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class ContatoCriteria implements ComumCriteria
{

	private Long data;

	/**
	 * The Constructor.
	 */
	public ContatoCriteria()
	{
		super();
	}

	/**
	 * Gets the emprId.
	 *
	 * @return the emprId
	 */
	public Integer getEmprId()
	{
		return emprId;
	}

	/**
	 * Sets the emprId.
	 *
	 * @param emprId the emprId to set
	 */
	public void setEmprId(Integer emprId)
	{
		this.emprId = emprId;
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
		return "ArquivoCriteria [getEmprId()=" + getEmprId() + ", getId()="
				+ getId() + ", getType()=" + getType() + ", getNumero()=" + getNumero() + ", toString()="
				+ super.toString() + "]";
	}

}
