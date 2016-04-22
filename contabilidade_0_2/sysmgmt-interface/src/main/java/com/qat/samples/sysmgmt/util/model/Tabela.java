package com.qat.samples.sysmgmt.util.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Tabela extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String nome;

	/** The description. */
	private String description;

	/** The atributos. */
	private List<Atributos> atributos;

	/**
	 * Default constructor.
	 */
	public Tabela()
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
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the nome.
	 * 
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * Sets the nome.
	 * 
	 * @param nome the new nome
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * Gets the atributos.
	 * 
	 * @return the atributos
	 */
	public List<Atributos> getAtributos()
	{
		return atributos;
	}

	/**
	 * Sets the atributos.
	 * 
	 * @param atributos the new atributos
	 */
	public void setAtributos(List<Atributos> atributos)
	{
		this.atributos = atributos;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Tabela [getId()=" + getId() + ", getDescription()=" + getDescription() + ", getNome()=" + getNome()
				+ ", getAtributos()=" + getAtributos() + ", toString()=" + super.toString() + "]";
	}

}
