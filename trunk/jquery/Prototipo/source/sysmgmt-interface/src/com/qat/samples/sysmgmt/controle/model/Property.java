package com.qat.samples.sysmgmt.controle.model;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.util.Util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Property", propOrder = {"property", "value"})
public class Property extends Util
{

	/** The id. */

	/** The code. */
	private String property;

	/** The description. */
	private String value;

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getProperty()
	{
		return property;
	}

	/**
	 * Sets the id.
	 * 
	 * @param property the new id
	 */
	public void setProperty(String property)
	{
		this.property = property;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Sets the description.
	 * 
	 * @param value the new description
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * Instantiates a new property.
	 * 
	 * @param property the property
	 * @param value the value
	 */
	public Property(String property, String value)
	{
		super();
		this.property = property;
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.util.Util#toString()
	 */
	@Override
	public String toString()
	{
		return "Property [getProperty()=" + getProperty() + ", getValue()=" + getValue() + ", toString()="
				+ super.toString() + "]";
	}

}
