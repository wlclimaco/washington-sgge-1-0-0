package com.sensus.lc.light.model;

import com.sensus.lc.base.model.Parameter;

/**
 * The Class LightParameter.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
@SuppressWarnings("serial")
public class LightParameter extends Parameter
{
	/** The property class. */
	private Integer propertyClass;

	/**
	 * Instantiates a new property.
	 */
	public LightParameter()
	{
	}

	/**
	 * Instantiates a new light parameter.
	 * 
	 * @param id the id
	 * @param labelKey the label key
	 */
	public LightParameter(Integer id, String labelKey)
	{
		super(id, labelKey);
	}

	/**
	 * Instantiates a new light parameter.
	 * 
	 * @param propertyEnum the property enum
	 * @param value the value
	 */
	public LightParameter(PropertyEnum propertyEnum, String value)
	{
		super(propertyEnum, value);
	}

	/**
	 * Gets the property class.
	 * 
	 * @return the property class
	 */
	public Integer getPropertyClass()
	{
		return propertyClass;
	}

	/**
	 * Sets the property class.
	 * 
	 * @param propertyClass the new property class
	 */
	public void setPropertyClass(Integer propertyClass)
	{
		this.propertyClass = propertyClass;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightParameter [propertyClass=" + propertyClass + "]";
	}

}
