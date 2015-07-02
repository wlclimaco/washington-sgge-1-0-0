package com.prosperitasglobal.sendsolv.model;

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
	private String abreviado;

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
	 * Gets the abreviado.
	 *
	 * @return the abreviado
	 */
	public String getAbreviado()
	{
		return abreviado;
	}

	/**
	 * Sets the abreviado.
	 *
	 * @param abreviado the new abreviado
	 */
	public void setAbreviado(String abreviado)
	{
		this.abreviado = abreviado;
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

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.model.ModelCosmeDamiao#toString()
	 */
	@Override
	public String toString()
	{
		return "Cnae [getId()=" + getId() + ", getCodigo()=" + ", getAbreviado()=" + getAbreviado()
				+ ", getParentId()="
				+ getParentId() + ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType()
				+ ", getTabelaEnum()=" + getTabelaEnum() + ", toString()=" + super.toString() + "]";
	}

}
