package com.qat.samples.sysmgmt.produto.model;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.util.Util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Embalagem", propOrder = {"id", "unimed", "nome", "qnt", "produtos"})
public class Embalagem extends Util
{

	/** The id. */
	private Integer id;

	/** The description. */
	private String nome;

	/** The qnt. */
	private Integer qnt;

	/** The produtos. */
	private Integer produtos;

	/** The unimed. */
	private UniMed unimed;

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
	 * Gets the qnt.
	 * 
	 * @return the qnt
	 */
	public Integer getQnt()
	{
		return qnt;
	}

	/**
	 * Sets the qnt.
	 * 
	 * @param qnt the new qnt
	 */
	public void setQnt(Integer qnt)
	{
		this.qnt = qnt;
	}

	/**
	 * Gets the produtos.
	 * 
	 * @return the produtos
	 */
	public Integer getProdutos()
	{
		return produtos;
	}

	/**
	 * Sets the produtos.
	 * 
	 * @param produtos the new produtos
	 */
	public void setProdutos(Integer produtos)
	{
		this.produtos = produtos;
	}

	/**
	 * Gets the unimed.
	 * 
	 * @return the unimed
	 */
	public UniMed getUnimed()
	{
		return unimed;
	}

	/**
	 * Sets the unimed.
	 * 
	 * @param unimed the new unimed
	 */
	public void setUnimed(UniMed unimed)
	{
		this.unimed = unimed;
	}

	/**
	 * Instantiates a new bundle.
	 */
	public Embalagem()
	{

	}

	/**
	 * Instantiates a new embalagem.
	 * 
	 * @param nome the nome
	 * @param qnt the qnt
	 * @param unimed the unimed
	 */
	public Embalagem(String nome, Integer qnt, UniMed unimed)
	{
		super();
		this.nome = nome;
		this.qnt = qnt;
		this.unimed = unimed;
	}

	/**
	 * Instantiates a new embalagem.
	 * 
	 * @param nome the nome
	 * @param qnt the qnt
	 */
	public Embalagem(String nome, Integer qnt)
	{
		super();
		this.nome = nome;
		this.qnt = qnt;
	}

	/**
	 * To string.
	 * 
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "Embalagem [getId()=" + getId() + ", getNome()=" + getNome() + ", getQnt()=" + getQnt()
				+ ", getProdutos()=" + getProdutos() + ", getUnimed()=" + getUnimed() + ", toString()="
				+ super.toString() + "]";
	}

}
