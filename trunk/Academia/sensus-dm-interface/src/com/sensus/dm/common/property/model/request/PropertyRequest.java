package com.sensus.dm.common.property.model.request;

import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class PropertyRequest.
 * 
 * @author QAT Brazil.
 */
public class PropertyRequest extends TenantRequest
{
	/** The property provider type. */
	private String propertyProviderType;

	/** The properties. */
	private List<Property> properties;

	/** If update an existing property or inserting a new one with same property_name. */
	private Boolean alwaysInsert;

	/** The property enum. */
	private PropertyEnum propertyEnum;

	/**
	 * Instantiates a new property request.
	 */
	public PropertyRequest()
	{
	}

	/**
	 * Instantiates a new property request.
	 * 
	 * @param userContext the user context
	 */
	public PropertyRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new property request.
	 * 
	 * @param userContext the user context
	 * @param tenantParam the tenant param
	 */
	public PropertyRequest(UserContext userContext, DMTenant tenantParam)
	{
		super(userContext, tenantParam);
	}

	/**
	 * Instantiates a new property request.
	 * 
	 * @param paramPropertyProviderType the param property provider type
	 * @param paramProperties the param properties
	 * @param paramAlwaysInsert the param always insert
	 */
	public PropertyRequest(String paramPropertyProviderType, List<Property> paramProperties, Boolean paramAlwaysInsert)
	{
		setPropertyProviderType(paramPropertyProviderType);
		setProperties(paramProperties);
		setAlwaysInsert(paramAlwaysInsert);
	}

	/**
	 * Gets the property provider type.
	 * 
	 * @return the property provider type
	 */
	public String getPropertyProviderType()
	{
		return propertyProviderType;
	}

	/**
	 * Sets the property provider type.
	 * 
	 * @param propertyProviderType the new property provider type
	 */
	public void setPropertyProviderType(String propertyProviderType)
	{
		this.propertyProviderType = propertyProviderType;
	}

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	public List<Property> getProperties()
	{
		return properties;
	}

	/**
	 * Sets the properties.
	 * 
	 * @param properties the new properties
	 */
	public void setProperties(List<Property> properties)
	{
		this.properties = properties;
	}

	/**
	 * Gets the always insert.
	 * 
	 * @return the always insert
	 */
	public Boolean getAlwaysInsert()
	{
		return alwaysInsert;
	}

	/**
	 * Sets the always insert.
	 * 
	 * @param alwaysInsert the new always insert
	 */
	public void setAlwaysInsert(Boolean alwaysInsert)
	{
		this.alwaysInsert = alwaysInsert;
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
	 * @param propertyEnumParam the new property enum
	 */
	public void setPropertyEnum(PropertyEnum propertyEnumParam)
	{
		propertyEnum = propertyEnumParam;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PropertyRequest [getPropertyProviderType()=" + getPropertyProviderType() + ", getProperties()="
				+ getProperties() + ", getAlwaysInsert()=" + getAlwaysInsert() + ", getPropertyEnum()="
				+ getPropertyEnum() + ", getIsMonitored()=" + getIsMonitored() + ", getRecentRequestMonitored()="
				+ getRecentRequestMonitored() + ", getTimeZone()=" + getTimeZone() + ", getDateFormat()="
				+ getDateFormat() + ", getFileName()=" + getFileName() + ", getProcessId()=" + getProcessId()
				+ ", getUnreachableIds()=" + getUnreachableIds() + ", getIdFileType()=" + getIdFileType()
				+ ", getUploadIds()=" + getUploadIds() + ", getServiceTypeEnum()=" + getServiceTypeEnum()
				+ ", getServiceTypeEnumValue()=" + getServiceTypeEnumValue() + ", getUserContext()=" + getUserContext()
				+ "]";
	}
}
