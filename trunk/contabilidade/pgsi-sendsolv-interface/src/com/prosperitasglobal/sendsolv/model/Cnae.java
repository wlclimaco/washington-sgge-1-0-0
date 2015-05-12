package com.prosperitasglobal.sendsolv.model;


/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Cnae extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The account number. */
	private String number;

	/** The type of an account. */
	private String name;

	/** The description. */
	private String description;

	/**
	 * Default constructor.
	 */
	public Cnae()
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
	 * Gets the number.
	 *
	 * @return the number
	 */
	public String getNumber()
	{
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number the number to set
	 */
	public void setNumber(String number)
	{
		this.number = number;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Cnae [getId()=" + getId() + ", getNumber()=" + getNumber() + ", getName()=" + getName()
				+ ", getDescription()=" + getDescription() + ", toString()=" + super.toString() + "]";
	}

}
