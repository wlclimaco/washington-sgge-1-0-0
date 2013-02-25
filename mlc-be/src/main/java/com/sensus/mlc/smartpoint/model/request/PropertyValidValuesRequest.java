package com.sensus.mlc.smartpoint.model.request;

import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class PropertyValidValuesRequest.
 */
/**
 * @author QATEmployee
 * 
 */
public class PropertyValidValuesRequest extends LightingControlRequest
{

	/** The properties. */
	private List<PropertyEnum> properties;

	/**
	 * Instantiates a new property valid values request.
	 */
	public PropertyValidValuesRequest()
	{
	}

	/**
	 * Instantiates a new property valid values request.
	 * 
	 * @param userContext the user context
	 */
	public PropertyValidValuesRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new property valid values request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public PropertyValidValuesRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	public List<PropertyEnum> getProperties()
	{
		return properties;
	}

	/**
	 * Sets the properties.
	 * 
	 * @param properties the new properties
	 */
	public void setProperties(List<PropertyEnum> properties)
	{
		this.properties = properties;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "PropertyValidValuesRequest [getProperties()=" + getProperties() + "]";
	}

}
