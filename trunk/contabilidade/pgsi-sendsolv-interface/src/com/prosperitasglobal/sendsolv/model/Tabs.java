package com.prosperitasglobal.sendsolv.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Tabs extends QATModel
{
	/** The SendSolv id for the account. */
	private Integer id;

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

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public List<Field> getFiels()
	{
		return fiels;
	}

	public void setFiels(List<Field> fiels)
	{
		this.fiels = fiels;
	}

}
