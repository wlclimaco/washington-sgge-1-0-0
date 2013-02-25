package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.schedule.model.OffsetSchedule;

/**
 * The Model Object SmartPoint.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
@SuppressWarnings("serial")
public class SmartPoint extends SensusModel
{
	/** The smartpoint id. */
	private Integer smartPointId;

	/** The rni id. */
	private Integer rniId;

	/** The smartpoint type enum. */
	private SmartPointTypeEnum smartPointTypeEnum;

	/** The offset schedule. */
	private OffsetSchedule offsetSchedule;

	/**
	 * Instantiates a new smart point.
	 */
	public SmartPoint()
	{
	}

	/**
	 * Instantiates a new smart point.
	 * 
	 * @param rniIdParam the rni id param
	 * @param smartPointTypeEnumParam the smart point type enum param
	 */
	public SmartPoint(Integer rniIdParam, SmartPointTypeEnum smartPointTypeEnumParam)
	{
		setRniId(rniIdParam);
		setSmartPointTypeEnum(smartPointTypeEnumParam);
	}

	/**
	 * Gets the rni id.
	 * 
	 * @return the rni id
	 */
	public Integer getRniId()
	{
		return rniId;
	}

	/**
	 * Sets the rni id.
	 * 
	 * @param rniIdInteger the new rni id
	 */
	public void setRniId(Integer rniIdInteger)
	{
		rniId = rniIdInteger;
	}

	/**
	 * Gets the smart point id.
	 * 
	 * @return the smart point id
	 */
	public Integer getSmartPointId()
	{
		return smartPointId;
	}

	/**
	 * Sets the smart point id.
	 * 
	 * @param smartPointId the new smart point id
	 */
	public void setSmartPointId(Integer smartPointId)
	{
		this.smartPointId = smartPointId;
	}

	/**
	 * Gets the smart point type enum.
	 * 
	 * @return the smart point type enum
	 */
	public SmartPointTypeEnum getSmartPointTypeEnum()
	{
		return smartPointTypeEnum;
	}

	/**
	 * Sets the smart point type enum.
	 * 
	 * @param smartPointTypeEnumObject the new smart point type enum
	 */
	public void setSmartPointTypeEnum(SmartPointTypeEnum smartPointTypeEnumObject)
	{
		smartPointTypeEnum = smartPointTypeEnumObject;
	}

	/**
	 * Gets the smartPoint type value.
	 * 
	 * @return the smartPoint Type value
	 */
	public Integer getSmartPointTypeValue()
	{
		return smartPointTypeEnum.getValue();
	}

	/**
	 * Sets the smarPoint type value.
	 * 
	 * @param value the new smart point type value
	 */
	public void setSmartPointTypeValue(Integer value)
	{
		smartPointTypeEnum = SmartPointTypeEnum.enumForValue(value);
	}

	/**
	 * Gets the off set schedule.
	 * 
	 * @return the off set schedule
	 */
	public OffsetSchedule getOffSetSchedule()
	{
		return offsetSchedule;
	}

	/**
	 * Sets the off set schedule.
	 * 
	 * @param offsetScheduleParam the new off set schedule
	 */
	public void setOffSetSchedule(OffsetSchedule offsetScheduleParam)
	{
		offsetSchedule = offsetScheduleParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "SmartPoint [getRniId()=" + getRniId() + ", getSmartPointId()=" + getSmartPointId()
				+ ", getSmartPointTypeEnum()=" + getSmartPointTypeEnum() + ", getSmartPointTypeValue()="
				+ getSmartPointTypeValue() + ", getOffSetSchedule()=" + getOffSetSchedule() + ", getModelAction()="
				+ getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}
}
