package com.sensus.mlc.smartpoint.model;

import java.util.List;

import com.sensus.mlc.base.model.Parameter;

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

	/** The property valid values. */
	private List<PropertyValidValue> propertyValidValues;

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

	/**
	 * Gets the property valid values.
	 * 
	 * @return the property valid values
	 */
	public List<PropertyValidValue> getPropertyValidValues()
	{
		return propertyValidValues;
	}

	/**
	 * Sets the property valid values.
	 * 
	 * @param propertyValidValues the new property valid values
	 */
	public void setPropertyValidValues(List<PropertyValidValue> propertyValidValues)
	{
		this.propertyValidValues = propertyValidValues;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.Parameter#toString()
	 */
	@Override
	public String toString()
	{
		return "LightParameter [getPropertyClass()=" + getPropertyClass() + ", getPropertyValidValues()="
				+ getPropertyValidValues() + ", getId()=" + getId() + ", getPropertyEnum()=" + getPropertyEnum()
				+ ", getPropertyEnumValue()=" + getPropertyEnumValue() + ", getLabelKey()=" + getLabelKey()
				+ ", getValue()=" + getValue() + ", getDataTypeEnum()=" + getDataTypeEnum()
				+ ", getDataTypeEnumValue()=" + getDataTypeEnumValue() + ", getPropertyValidValueId()="
				+ getPropertyValidValueId() + ", getModelAction()=" + getModelAction() + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
