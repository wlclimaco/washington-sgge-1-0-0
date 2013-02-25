package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class OperationalData.
 * 
 * @author - Alex Barros - QAT
 */
@SuppressWarnings("serial")
public class OperationalData extends SensusModel
{
	/** The id. */
	private Integer id;

	/** The label key. */
	private String labelKey;

	/** The value. */
	private Float value;

	/** The unitOfMeasurement. */
	private String unitOfMeasurement;

	/** The data type enum. */
	private OperationalDataTypeEnum operationalDataType;

	/**
	 * Instantiates a new property.
	 */
	public OperationalData()
	{
	}

	/**
	 * Instantiates a new operational data.
	 * 
	 * @param valueParam the value param
	 * @param operationalDataTypeParam the operational data type param
	 */
	public OperationalData(Float valueParam,
			OperationalDataTypeEnum operationalDataTypeParam)
	{
		setValue(valueParam);
		setOperationalDataType(operationalDataTypeParam);
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
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
	 * Gets the label key.
	 * 
	 * @return the label key
	 */
	public String getLabelKey()
	{
		return labelKey;
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
	public Float getValue()
	{
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value the new value
	 */
	public void setValue(Float value)
	{
		this.value = value;
	}

	/**
	 * Gets the unit of measurement.
	 * 
	 * @return the unit of measurement
	 */
	public String getUnitOfMeasurement()
	{
		return unitOfMeasurement;
	}

	/**
	 * Sets the unit of measurement.
	 * 
	 * @param unitOfMeasurement the new unit of measurement
	 */
	public void setUnitOfMeasurement(String unitOfMeasurement)
	{
		this.unitOfMeasurement = unitOfMeasurement;
	}

	/**
	 * Gets the operational data type.
	 * 
	 * @return the operational data type
	 */
	public OperationalDataTypeEnum getOperationalDataType()
	{
		return operationalDataType;
	}

	/**
	 * Sets the operational data type.
	 * 
	 * @param operationalDataTypeEnum the new operational data type
	 */
	public void setOperationalDataType(OperationalDataTypeEnum operationalDataTypeEnum)
	{
		operationalDataType = operationalDataTypeEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "OperationalData [getId()=" + getId() + ", getLabelKey()=" + getLabelKey() + ", getValue()="
				+ getValue() + ", getUnitOfMeasurement()=" + getUnitOfMeasurement() + ", getOperationalDataType()="
				+ getOperationalDataType() + ", getModelAction()=" + getModelAction() + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
