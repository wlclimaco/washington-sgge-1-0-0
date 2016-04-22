package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Incidencia extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String codigo;

	private String texto;

	/**
	 * Default constructor.
	 */
	public Incidencia()
	{
		super();
	}

	public Incidencia(Integer id)
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

	public Incidencia(String codigo, String texto)
	{
		super();
		this.codigo = codigo;
		this.texto = texto;
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

	/**
	 * @return the codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	@Override
	public String toString()
	{
		return "Incidencia [getId()=" + getId() + ", getCodigo()=" + getCodigo() + ", toString()=" + super.toString()
				+ "]";
	}

}
