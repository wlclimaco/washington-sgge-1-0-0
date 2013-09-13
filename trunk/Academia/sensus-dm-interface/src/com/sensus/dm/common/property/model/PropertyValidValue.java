package com.sensus.dm.common.property.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class PropertyValidValue.
 * 
 * @author QAT Brazil.
 */
@SuppressWarnings("serial")
public class PropertyValidValue extends SensusModel
{

	/** The id. */
	private Integer id;

	/** The values. */
	private String value;

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
	 * Instantiates a new property valid value.
	 */
	public PropertyValidValue()
	{

	}

	/**
	 * Instantiates a new property valid value.
	 * 
	 * @param newId the new id
	 * @param newValue the new value
	 */
	public PropertyValidValue(Integer newId, String newValue)
	{
		setId(newId);
		setValue(newValue);
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
	 * Gets the values.
	 * 
	 * @return the values
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Sets the values.
	 * 
	 * @param value the new value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "PropertyValidValue [getId()=" + getId() + ", getValue()=" + getValue() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}

}
