package com.sensus.dm.common.property.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.dm.common.property.model.Property;

/**
 * The Class PropertyResponse.
 * 
 * @author QAT Brazil.
 */
public class PropertyResponse extends Response
{

	/** The properties. */
	private List<Property> properties;

	/**
	 * @return the properties
	 */
	public List<Property> getProperties()
	{
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(List<Property> properties)
	{
		this.properties = properties;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PropertyResponse [properties=" + properties + ", getProperties()=" + getProperties() + "]";
	}

}
