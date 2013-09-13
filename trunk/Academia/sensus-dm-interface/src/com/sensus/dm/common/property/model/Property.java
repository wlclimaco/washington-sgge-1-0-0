package com.sensus.dm.common.property.model;

import com.sensus.common.model.SensusModel;

/**
 * The Model Object Property.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class Property extends SensusModel
{
	/** The provider type. */
	private String providerType;

	/** The provider id. */
	private String providerId;

	/** The property name. */
	private String propertyName;

	/** The property value. */
	private String propertyValue;

	/** The label key. */
	private String labelKey;

	/** The display order. */
	private Integer displayOrder;

	/**
	 * Instantiates a new property.
	 */
	public Property()
	{
	}

	/**
	 * Instantiates a new property.
	 * 
	 * @param providerIdParam the provider id
	 */
	public Property(String providerIdParam)
	{
		setProviderId(providerIdParam);
	}

	/**
	 * Instantiates a new property.
	 * 
	 * @param propertyNameIn the property name in
	 * @param propertyValueIn the property value in
	 */
	public Property(String propertyNameIn, String propertyValueIn)
	{
		setPropertyName(propertyNameIn);
		setPropertyValue(propertyValueIn);
	}

	/**
	 * Instantiates a new property.
	 * 
	 * @param propertyNameIn the property name in
	 * @param propertyValueIn the property value in
	 * @param displayOrderParam the display order
	 */
	public Property(String propertyNameIn, String propertyValueIn, Integer displayOrderParam)
	{
		this(propertyNameIn, propertyValueIn);

		setDisplayOrder(displayOrderParam);
	}

	/**
	 * Instantiates a new property.
	 * 
	 * @param paramProviderType the param provider type
	 * @param paramProviderId the param provider id
	 * @param paramPropertyName the param property name
	 * @param paramPropertyValue the param property value
	 * @param paramLabelKey the param label key
	 * @param paramDisplayOrder the param display order
	 */
	public Property(String paramProviderType, String paramProviderId, String paramPropertyName,
			String paramPropertyValue, String paramLabelKey, Integer paramDisplayOrder)
	{
		this(paramPropertyName, paramPropertyValue);

		setProviderType(paramProviderType);
		setProviderId(paramProviderId);
		setLabelKey(paramLabelKey);
		setDisplayOrder(paramDisplayOrder);
	}

	/**
	 * Gets the provider type.
	 * 
	 * @return the providerType
	 */
	public String getProviderType()
	{
		return providerType;
	}

	/**
	 * Sets the provider type.
	 * 
	 * @param providerType the providerType to set
	 */
	public void setProviderType(String providerType)
	{
		this.providerType = providerType;
	}

	/**
	 * Gets the provider id.
	 * 
	 * @return the providerId
	 */
	public String getProviderId()
	{
		return providerId;
	}

	/**
	 * Sets the provider id.
	 * 
	 * @param providerId the providerId to set
	 */
	public void setProviderId(String providerId)
	{
		this.providerId = providerId;
	}

	/**
	 * Gets the property name.
	 * 
	 * @return the propertyName
	 */
	public String getPropertyName()
	{
		return propertyName;
	}

	/**
	 * Sets the property name.
	 * 
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName)
	{
		this.propertyName = propertyName;
	}

	/**
	 * Gets the property value.
	 * 
	 * @return the propertyValue
	 */
	public String getPropertyValue()
	{
		return propertyValue;
	}

	/**
	 * Sets the property value.
	 * 
	 * @param propertyValue the propertyValue to set
	 */
	public void setPropertyValue(String propertyValue)
	{
		this.propertyValue = propertyValue;
	}

	/**
	 * Gets the label key.
	 * 
	 * @return the labelKey
	 */
	public String getLabelKey()
	{
		return labelKey;
	}

	/**
	 * Sets the label key.
	 * 
	 * @param labelKey the labelKey to set
	 */
	public void setLabelKey(String labelKey)
	{
		this.labelKey = labelKey;
	}

	/**
	 * Gets the display order.
	 * 
	 * @return the displayOrder
	 */
	public Integer getDisplayOrder()
	{
		return displayOrder;
	}

	/**
	 * Sets the display order.
	 * 
	 * @param displayOrder the displayOrder to set
	 */
	public void setDisplayOrder(Integer displayOrder)
	{
		this.displayOrder = displayOrder;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Property [getProviderType()=" + getProviderType() + ", getProviderId()=" + getProviderId()
				+ ", getPropertyName()=" + getPropertyName() + ", getPropertyValue()=" + getPropertyValue()
				+ ", getLabelKey()=" + getLabelKey() + ", getDisplayOrder()=" + getDisplayOrder()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
