package com.prosperitasglobal.cbof.model;

import com.qat.framework.model.QATModel;

/**
 * The Class StateProvinceRegion.
 */
@SuppressWarnings("serial")
public class StateProvinceRegion extends QATModel
{

	/** The id. */
	private String id;

	/** The code. */
	private String code;

	/** The description. */
	private String description;

	/**
	 * The Constructor.
	 */
	public StateProvinceRegion()
	{
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * The Constructor.
	 *
	 * @param code the code
	 */
	public StateProvinceRegion(String code)
	{
		this.code = code;
	}

	/**
	 * The Constructor.
	 *
	 * @param code the code
	 * @param description the description
	 */
	public StateProvinceRegion(String code, String description)
	{
		this.code = code;
		this.description = description;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the code
	 */
	public void setCode(String code)
	{
		this.code = code;
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
	 * @param description the description
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
		return "StateProvinceRegion [getId()=" + getId() + ", getCode()=" + getCode() + ", getDescription()="
				+ getDescription() + ", toString()=" + super.toString() + "]";
	}

}
