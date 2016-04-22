package com.qat.samples.sysmgmt.model;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.BaseModel;

/**
 * * The Model Object County.
 */
@SuppressWarnings("serial")
@XmlType(name = "County", propOrder = {"id", "description"})
public class County extends BaseModel
{
	private Integer id;
	private String description;

	/**
	 * Instantiates a new county.
	 */
	public County()
	{

	}

	/**
	 * Instantiates a new county.
	 * 
	 * @param identifier the id
	 * @param value the description
	 */
	public County(Integer identifier, String value)
	{
		setId(identifier);
		setDescription(value);
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
	 * @param identifier the new id
	 */
	public void setId(Integer identifier)
	{
		id = identifier;
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
	 * @param value the new description
	 */
	public void setDescription(String value)
	{
		description = value;
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "County [getDescription()=" + getDescription() + ", getId()=" + getId() + ", getModelAction()="
				+ getModelAction() + "]";
	}

}
