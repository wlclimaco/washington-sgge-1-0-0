package com.qat.samples.sysmgmt.util.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Tela extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The description. */
	private String description;

	/** The numero. */
	private String help;

	/** The data. */
	private Long data;

	/** The estado. */
	private String desenvolvida;

	/** The tabs. */
	private List<Tabs> tabs;

	/**
	 * Default constructor.
	 */
	public Tela()
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
	 * Gets the help.
	 * 
	 * @return the help
	 */
	public String getHelp()
	{
		return help;
	}

	/**
	 * Sets the help.
	 * 
	 * @param help the new help
	 */
	public void setHelp(String help)
	{
		this.help = help;
	}

	/**
	 * Gets the data.
	 * 
	 * @return the data
	 */
	public Long getData()
	{
		return data;
	}

	/**
	 * Sets the data.
	 * 
	 * @param data the new data
	 */
	public void setData(Long data)
	{
		this.data = data;
	}

	/**
	 * Gets the desenvolvida.
	 * 
	 * @return the desenvolvida
	 */
	public String getDesenvolvida()
	{
		return desenvolvida;
	}

	/**
	 * Sets the desenvolvida.
	 * 
	 * @param desenvolvida the new desenvolvida
	 */
	public void setDesenvolvida(String desenvolvida)
	{
		this.desenvolvida = desenvolvida;
	}

	/**
	 * Gets the tabs.
	 * 
	 * @return the tabs
	 */
	public List<Tabs> getTabs()
	{
		return tabs;
	}

	/**
	 * Sets the tabs.
	 * 
	 * @param tabs the new tabs
	 */
	public void setTabs(List<Tabs> tabs)
	{
		this.tabs = tabs;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Tela [getId()=" + getId() + ", getType()=" + getType() + ", getDescription()=" + getDescription()
				+ ", getHelp()=" + getHelp() + ", getData()=" + getData() + ", getDesenvolvida()=" + getDesenvolvida()
				+ ", getTabs()=" + getTabs() + ", toString()=" + super.toString() + "]";
	}

}
