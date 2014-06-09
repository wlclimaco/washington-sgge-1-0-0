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
	private Integer fotoid;

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

	public Integer getFotoid()
	{
		return fotoid;
	}

	public Foto(Integer fotoid, String nome, String local)
	{
		super();
		this.fotoid = fotoid;
		this.nome = nome;
		this.local = local;
	}

	public void setFotoid(Integer fotoid)
	{
		this.fotoid = fotoid;
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

	public String getLocal()
	{
		return local;
	}

	/**
	 * Sets the description.
	 * 
	 * @param local the new description
	 */

	public void setLocal(String local)
	{
		this.local = local;
	}

	@Override
	public String toString()
	{
		return "Foto [getFotoid()=" + getFotoid() + ", getNome()=" + getNome() + ", getLocal()=" + getLocal()
				+ ", toString()=" + super.toString() + "]";
	}

}
