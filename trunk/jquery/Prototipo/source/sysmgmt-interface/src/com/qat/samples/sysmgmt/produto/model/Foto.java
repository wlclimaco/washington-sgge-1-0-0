package com.qat.samples.sysmgmt.produto.model;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.util.Util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Foto", propOrder = {"id", "nome", "local"})
public class Foto extends Util
{

	/** The id. */
	private Integer id;

	/** The code. */
	private String nome;

	/** The description. */
	private String local;

	/**
	 * Instantiates a new bundle.
	 */
	public Foto()
	{

	}

	/**
	 * Instantiates a new bundle.
	 * 
	 * @param id the id
	 */
	public Foto(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	@Override
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the id to set
	 */
	@Override
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * Sets the code.
	 * 
	 * @param nome the new code
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	@Override
	public String getLocal()
	{
		return local;
	}

	/**
	 * Sets the description.
	 * 
	 * @param local the new description
	 */
	@Override
	public void setLocal(String local)
	{
		this.local = local;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.QATModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Foto [getId()=" + getId() + ", getNome()=" + getNome() + ", getLocal()=" + getLocal() + ", toString()="
				+ super.toString() + "]";
	}

}
