package com.qat.samples.sysmgmt.produto.model;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.util.Util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "UniMed", propOrder = {"id", "sigla", "nome"})
public class UniMed extends Util
{

	/** The id. */
	private Integer id;

	/** The description. */
	private String nome;

	/** The price. */
	private String sigla;

	/**
	 * Instantiates a new bundle.
	 */
	public UniMed()
	{

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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * Sets the description.
	 * 
	 * @param nome the new description
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * Gets the price.
	 * 
	 * @return the price
	 */
	public String getSigla()
	{
		return sigla;
	}

	/**
	 * Sets the price.
	 * 
	 * @param sigla the new price
	 */
	public void setSigla(String sigla)
	{
		this.sigla = sigla;
	}

	/**
	 * Instantiates a new uni med.
	 * 
	 * @param id the id
	 * @param sigla the sigla
	 */
	public UniMed(Integer id, String sigla)
	{
		super();
		this.id = id;
		this.sigla = sigla;
	}

	/**
	 * Instantiates a new uni med.
	 * 
	 * @param id the id
	 * @param nome the nome
	 * @param sigla the sigla
	 */
	public UniMed(Integer id, String nome, String sigla)
	{
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
	}

	/**
	 * Instantiates a new uni med.
	 * 
	 * @param nome the nome
	 * @param sigla the sigla
	 */
	public UniMed(String nome, String sigla)
	{
		super();
		this.nome = nome;
		this.sigla = sigla;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.util.Util#toString()
	 */
	@Override
	public String toString()
	{
		return "UniMed [getId()=" + getId() + ", getNome()=" + getNome()
				+ ", getSigla()=" + getSigla() + ", toString()=" + super.toString() + "]";
	}

}
