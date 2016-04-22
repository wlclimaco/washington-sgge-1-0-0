package com.qat.samples.sysmgmt.util.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Tabs extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer telaId;

	/** The type of an account. */
	private String text;

	/** The description. */
	private String description;

	/** The numero. */
	private String nome;

	/** The data. */
	private List<Field> fiels;

	/**
	 * Default constructor.
	 */
	public Tabs()
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
	 * Gets the text.
	 * 
	 * @return the text
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * Sets the text.
	 * 
	 * @param text the new text
	 */
	public void setText(String text)
	{
		this.text = text;
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
	 * @param description the new description
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
	 * Gets the fiels.
	 * 
	 * @return the fiels
	 */
	public List<Field> getFiels()
	{
		return fiels;
	}

	/**
	 * Sets the fiels.
	 * 
	 * @param fiels the new fiels
	 */
	public void setFiels(List<Field> fiels)
	{
		this.fiels = fiels;
	}

	/**
	 * @return the telaId
	 */
	public Integer getTelaId()
	{
		return telaId;
	}

	/**
	 * @param telaId the telaId to set
	 */
	public void setTelaId(Integer telaId)
	{
		this.telaId = telaId;
	}

	@Override
	public String toString()
	{
		return "Tabs [getId()=" + getId() + ", getText()=" + getText() + ", getDescription()=" + getDescription()
				+ ", getNome()=" + getNome() + ", getFiels()=" + getFiels() + ", getTelaId()=" + getTelaId()
				+ ", toString()=" + super.toString() + "]";
	}

}
