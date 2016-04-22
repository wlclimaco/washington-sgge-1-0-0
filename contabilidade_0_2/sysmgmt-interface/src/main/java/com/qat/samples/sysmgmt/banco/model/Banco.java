package com.qat.samples.sysmgmt.banco.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Banco extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	private String nome;

	public Banco(Integer id)
	{
		super();
		this.id = id;
	}

	/**
	 * The Constructor.
	 */
	public Banco()
	{

	}

	public Banco(Integer id, String nome, PersistenceActionEnum modelAction)
	{
		super();
		this.id = id;
		this.nome = nome;
	}

	public Banco(int i, String string) {
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
	 * Sets the id.
	 *
	 * @param id the id
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

	@Override
	public String toString()
	{
		return "Banco [getId()=" + getId() + ", getNome()=" + getNome()
				+ ", toString()=" + super.toString() + "]";
	}

}