package com.sensus.lc.process.model;

import com.sensus.common.model.SensusModel;
import com.sensus.lc.light.model.PropertyEnum;

/**
 * The Class LCActionParameter.
 */

@SuppressWarnings("serial")
public class LCActionParameter extends SensusModel
{

	/** The property. */
	private PropertyEnum property;

	/** The action value. */
	private String actionValue;

	/**
	 * Instantiates a new lC action parameter.
	 * 
	 * @param propertyParam the property param
	 */
	public LCActionParameter(PropertyEnum propertyParam)
	{
		property = propertyParam;
	}

	/**
	 * Instantiates a new lC action parameter.
	 * 
	 * @param propertyParam the property param
	 * @param value the value
	 */
	public LCActionParameter(PropertyEnum propertyParam, String value)
	{
		property = propertyParam;
		actionValue = value;
	}

	/**
	 * Instantiates a new lC action parameter.
	 */
	public LCActionParameter()
	{

	}

	/**
	 * Gets the property.
	 * 
	 * @return the property
	 */
	public PropertyEnum getProperty()
	{
		return property;
	}

	/**
	 * Sets the property.
	 * 
	 * @param property the new property
	 */
	public void setProperty(PropertyEnum property)
	{
		this.property = property;
	}

	/**
	 * Sets the property value.
	 * 
	 * @param propertyValue the new property value
	 */
	public void setPropertyValue(Integer propertyValue)
	{
		property = PropertyEnum.enumForValue(propertyValue);
	}

	/**
	 * Gets the property value.
	 * 
	 * @return the property value
	 */
	public Integer getPropertyValue()
	{
		return property.getValue();
	}

	/**
	 * Gets the action value.
	 * 
	 * @return the action value
	 */
	public String getActionValue()
	{
		return actionValue;
	}

	/**
	 * Sets the action value.
	 * 
	 * @param actionValue the new action value
	 */
	public void setActionValue(String actionValue)
	{
		this.actionValue = actionValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "LCActionParameter [getProperty()=" + getProperty() + ", getPropertyValue()=" + getPropertyValue()
				+ ", getActionValue()=" + getActionValue() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}

}
