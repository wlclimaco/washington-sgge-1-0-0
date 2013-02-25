package com.sensus.mlc.smartpoint.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.smartpoint.model.PropertyValidValue;

/**
 * The Class PropertyValidValuesResponse.
 */
public class PropertyValidValuesResponse extends Response
{

	/** The property list with valid values. */
	private List<PropertyValidValue> propertyValidValues;

	/**
	 * Sets the property valid values.
	 * 
	 * @param propertyValidValues the new property valid values
	 */
	public void setPropertyValidValues(List<PropertyValidValue> propertyValidValues)
	{
		this.propertyValidValues = propertyValidValues;
	}

	/**
	 * Gets the property valid values.
	 * 
	 * @return the property valid values
	 */
	public List<PropertyValidValue> getPropertyValidValues()
	{
		return this.propertyValidValues;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PropertyValidValuesResponse [getPropertyValidValues()=" + this.getPropertyValidValues()
				+ ", getMessageIterator()=" + this.getMessageIterator() + ", getMessageList()=" + this.getMessageList()
				+ ", getMessageInfoList()=" + this.getMessageInfoList() + ", isOperationSuccess()="
				+ this.isOperationSuccess()
				+ "]";
	}
}
