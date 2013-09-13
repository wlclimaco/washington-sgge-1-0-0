package com.sensus.common.model;

/**
 * The Class Authority.
 */
public class Authority
{

	/** The id. */
	private Integer id;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new authority.
	 */
	public Authority()
	{
	}

	/**
	 * Instantiates a new authority.
	 * 
	 * @param id the id
	 */
	public Authority(Integer id, String name)
	{
		setId(id);
		setName(name);
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
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Authority [getId()=" + getId() + "]";
	}

}