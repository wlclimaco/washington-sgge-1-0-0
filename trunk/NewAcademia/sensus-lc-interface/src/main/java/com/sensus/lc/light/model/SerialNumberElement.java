package com.sensus.lc.light.model;

import java.util.Map;

/**
 * The Model Object Status SerialNumberElement.
 * 
 */
public class SerialNumberElement
{
	/** The id. */
	private String name;

	/** The date. */
	private Integer beginIndex;

	/** The status exceptions. */
	private Integer endIndex;

	/** The property type. */
	private PropertyEnum propertyType;

	/**
	 * Gets the begin index.
	 * 
	 * @return the begin index
	 */
	public Integer getBeginIndex()
	{
		return beginIndex;
	}

	/**
	 * Sets the begin index.
	 * 
	 * @param beginIndex the new begin index
	 */
	public void setBeginIndex(Integer beginIndex)
	{
		this.beginIndex = beginIndex;
	}

	/**
	 * Gets the end index.
	 * 
	 * @return the end index
	 */
	public Integer getEndIndex()
	{
		return endIndex;
	}

	/**
	 * Sets the end index.
	 * 
	 * @param endIndex the new end index
	 */
	public void setEndIndex(Integer endIndex)
	{
		this.endIndex = endIndex;
	}

	/**
	 * Gets the property type.
	 * 
	 * @return the property type
	 */
	public PropertyEnum getPropertyType()
	{
		return propertyType;
	}

	/**
	 * Sets the property type.
	 * 
	 * @param propertyType the new property type
	 */
	public void setPropertyType(PropertyEnum propertyType)
	{
		this.propertyType = propertyType;
	}

	/** The validValues for this element. */
	private Map<String, String> validValues;

	/**
	 * Instantiates a new status message.
	 */
	public SerialNumberElement()
	{
	}

	/**
	 * Parses the element.
	 * 
	 * @param productNumber the product number
	 * @return the string
	 */
	public String parseElement(String productNumber)
	{
		String s = productNumber.substring(beginIndex, endIndex);
		return s;
	}

	/**
	 * Gets the value.
	 * 
	 * @param key the key
	 * @return the value
	 */
	public String getValue(String key)
	{
		// returns null if no value found for that key
		return getValidValues().get(key);
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

	/**
	 * Gets the valid values.
	 * 
	 * @return the valid values
	 */
	public Map<String, String> getValidValues()
	{
		return validValues;
	}

	/**
	 * Sets the valid values.
	 * 
	 * @param validValues the valid values
	 */
	public void setValidValues(Map<String, String> validValues)
	{
		this.validValues = validValues;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SerialNumberElement [getBeginIndex()=" + getBeginIndex() + ", getEndIndex()=" + getEndIndex()
				+ ", getPropertyType()=" + getPropertyType() + ", getName()=" + getName() + ", getValidValues()="
				+ getValidValues() + "]";
	}
}
