package com.sensus.lc.light.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class OperationalData.
 * 
 * @author - Alex Barros - QAT
 */
@SuppressWarnings("serial")
public class OperationalData extends SensusModel
{
	/** Attributes. */
	private Integer notificationHistoryId;
	private String labelKey;
	private Float value;
	private String unitOfMeasurement;
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
	 * Gets the notification history id.
	 * 
	 * @return the notification history id
	 */
	public Integer getNotificationHistoryId()
	{
		return notificationHistoryId;
	}

	/**
	 * Sets the notification history id.
	 * 
	 * @param notificationHistoryId the new notification history id
	 */
	public void setNotificationHistoryId(Integer notificationHistoryId)
	{
		this.notificationHistoryId = notificationHistoryId;
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
	 * Gets the operational data type value.
	 * 
	 * @return the operational data type value
	 */
	public Integer getOperationalDataTypeValue()
	{
		if (getOperationalDataType() == null)
		{
			return null;
		}

		return getOperationalDataType().getValue();
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

	/**
	 * Sets the operational data type value.
	 * 
	 * @param dataTypeValue the new operational data type value
	 */
	public void setOperationalDataTypeValue(Integer dataTypeValue)
	{
		operationalDataType = OperationalDataTypeEnum.enumForValue(dataTypeValue);
	}

	@Override
	public String toString()
	{
		return "OperationalData [getNotificationHistoryId()=" + getNotificationHistoryId() + ", getLabelKey()="
				+ getLabelKey() + ", getValue()=" + getValue() + ", getUnitOfMeasurement()=" + getUnitOfMeasurement()
				+ ", getOperationalDataType()=" + getOperationalDataType() + ", getOperationalDataTypeValue()="
				+ getOperationalDataTypeValue() + ", toString()=" + super.toString() + "]";
	}
}
