package com.sensus.lc.light.model;

import com.sensus.common.model.SensusModel;

/**
 * Operational data value model object.
 */
@SuppressWarnings("serial")
public class OperationalDataValue extends SensusModel
{

	/**
	 * Attributes.
	 */
	private Double value;
	private OperationalDataTypeEnum operationalDataType;
	private Integer notificationHistoryId;

	/**
	 * @return the value
	 */
	public Double getValue()
	{
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Double value)
	{
		this.value = value;
	}

	/**
	 * @return the operationalDataType
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
		if (operationalDataType != null)
		{
			return operationalDataType.getValue();
		}
		return null;
	}

	/**
	 * Sets the operational data type value.
	 *
	 * @param valueParam the new operational data type value
	 */
	public void setOperationalDataTypeValue(Integer valueParam)
	{
		operationalDataType = OperationalDataTypeEnum.enumForValue(valueParam);
	}

	/**
	 * @param operationalDataType the operationalDataType to set
	 */
	public void setOperationalDataType(OperationalDataTypeEnum operationalDataType)
	{
		this.operationalDataType = operationalDataType;
	}

	/**
	 * @return the notificationHistoryId
	 */
	public Integer getNotificationHistoryId()
	{
		return notificationHistoryId;
	}

	/**
	 * @param notificationHistoryId the notificationHistoryId to set
	 */
	public void setNotificationHistoryId(Integer notificationHistoryId)
	{
		this.notificationHistoryId = notificationHistoryId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "OperationalDataValue [value=" + value + ", operationalDataType=" + operationalDataType
				+ ", notificationHistoryId=" + notificationHistoryId + "]";
	}

}
