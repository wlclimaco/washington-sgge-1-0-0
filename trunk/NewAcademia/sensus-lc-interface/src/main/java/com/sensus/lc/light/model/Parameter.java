package com.sensus.lc.light.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class Parameter.
 * 
 * @author - QAT Global
 */
@SuppressWarnings("serial")
public class Parameter extends SensusModel
{
	/** The id. */
	private Integer id;

	/** The propertyEnum. */
	private PropertyEnum propertyEnum;

	/** The label key. */
	private String labelKey;

	/** The value. */
	private String value;

	/** The data type enum. */
	private DataTypeEnum dataTypeEnum;

	/**
	 * Instantiates a new parameter.
	 */
	public Parameter()
	{
	}

	/**
	 * Instantiates a new parameter.
	 * 
	 * @param property the property enum
	 * @param parameterValue the value
	 */
	public Parameter(PropertyEnum property, String parameterValue)
	{
		setPropertyEnum(property);
		setValue(parameterValue);
	}

	/**
	 * Instantiates a new property.
	 * 
	 * @param idParam the id param
	 * @param valueParam the value param
	 */
	public Parameter(Integer idParam, String valueParam)
	{
		setId(idParam);
		setValue(valueParam);
		setPropertyEnumValue(idParam);
	}

	/**
	 * Instantiates a new parameter.
	 * 
	 * @param propertyEnumValue the property enum value
	 * @param labelKeyValue the label key value
	 * @param valueParam the value param
	 * @param dataTypeEnumParamValue the data type enum param value
	 */
	public Parameter(PropertyEnum propertyEnumValue, String labelKeyValue, String valueParam,
			DataTypeEnum dataTypeEnumParamValue)
	{
		this(propertyEnumValue, valueParam);
		setDataTypeEnum(dataTypeEnumParamValue);
		setLabelKey(labelKeyValue);
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the property enum.
	 * 
	 * @return the property enum
	 */
	public PropertyEnum getPropertyEnum()
	{
		return this.propertyEnum;
	}

	/**
	 * Sets the property enum.
	 * 
	 * @param propertyEnum the new property enum
	 */
	public void setPropertyEnum(PropertyEnum propertyEnum)
	{
		this.propertyEnum = propertyEnum;
	}

	/**
	 * Gets the property enum value.
	 * 
	 * @return the property enum value
	 */
	public Integer getPropertyEnumValue()
	{
		return this.propertyEnum.getValue();
	}

	/**
	 * Sets the property enum value.
	 * 
	 * @param valueParam the new property enum value
	 */
	public void setPropertyEnumValue(Integer valueParam)
	{
		this.propertyEnum = PropertyEnum.enumForValue(valueParam);
	}

	/**
	 * Gets the label key.
	 * 
	 * @return the label key
	 */
	public String getLabelKey()
	{
		return this.labelKey;
	}

	/**
	 * Sets the label key.
	 * 
	 * @param labelKey the new label key
	 */
	public void setLabelKey(String labelKey)
	{
		this.labelKey = labelKey;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue()
	{
		return this.value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value the new value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * Gets the data type enum.
	 * 
	 * @return the data type enum
	 */
	public DataTypeEnum getDataTypeEnum()
	{
		return this.dataTypeEnum;
	}

	/**
	 * Sets the data type enum.
	 * 
	 * @param dataTypeEnum the new data type enum
	 */
	public void setDataTypeEnum(DataTypeEnum dataTypeEnum)
	{
		this.dataTypeEnum = dataTypeEnum;
	}

	/**
	 * Gets the data type enum.
	 * 
	 * @return the data type enum
	 */
	public Integer getDataTypeEnumValue()
	{
		if (this.dataTypeEnum == null)
		{
			return null;
		}
		return this.dataTypeEnum.getValue();
	}

	/**
	 * Sets the data type enum.
	 * 
	 * @param dataTypeEnumValue the new data type enum value
	 */
	public void setDataTypeEnumValue(Integer dataTypeEnumValue)
	{
		this.dataTypeEnum = DataTypeEnum.enumForValue(dataTypeEnumValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Parameter [getId()=" + getId() + ", getPropertyEnum()=" + getPropertyEnum()
				+ ", getPropertyEnumValue()=" + getPropertyEnumValue() + ", getLabelKey()=" + getLabelKey()
				+ ", getValue()=" + getValue() + ", getDataTypeEnum()=" + getDataTypeEnum()
				+ ", getDataTypeEnumValue()=" + getDataTypeEnumValue() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}
}
