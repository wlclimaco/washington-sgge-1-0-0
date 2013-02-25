package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class PropertyValidValue.
 */
@SuppressWarnings("serial")
public class PropertyValidValue extends SensusModel
{

	/** The id. */
	private Integer id;

	/** The values. */
	private String value;

	/** The property enum. */
	private PropertyEnum propertyEnum;

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

	/**
	 * Gets the property enum.
	 * 
	 * @return the property enum
	 */
	public PropertyEnum getPropertyEnum()
	{
		return propertyEnum;
	}

	/**
	 * Sets the property enum.
	 * 
	 * @param propertyEnum the new property enum
	 */
	public void setPropertyEnum(PropertyEnum propertyEnum)
	{
		this.propertyEnum = propertyEnum;
	}

	/**
	 * Sets the property enum value.
	 * 
	 * @param value the new property enum value
	 */
	public void setPropertyEnumValue(Integer valueInternal)
	{
		propertyEnum = PropertyEnum.enumForValue(valueInternal);
	}

	/**
	 * Gets the property enum value.
	 * 
	 * @return the property enum value
	 */
	public Integer getPropertyEnumValue()
	{
		return propertyEnum.getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "PropertyValidValue [getId()=" + getId() + ", getValue()=" + getValue() + ", getModelAction()="
				+ getModelAction() + ", getPropertyEnumValue()=" + getPropertyEnumValue() + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}

}
