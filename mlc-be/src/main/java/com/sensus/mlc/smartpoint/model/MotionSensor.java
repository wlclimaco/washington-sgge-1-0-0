package com.sensus.mlc.smartpoint.model;

/**
 * * The Class MotionSensor.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
@SuppressWarnings("serial")
public class MotionSensor extends SmartPoint
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.model.SmartPoint#toString()
	 */
	@Override
	public String toString()
	{
		return "MotionSensor [getRniId()=" + getRniId() + ", getSmartPointId()=" + getSmartPointId()
				+ ", getSmartPointTypeEnum()=" + getSmartPointTypeEnum() + ", getSmartPointTypeValue()="
				+ getSmartPointTypeValue() + ", getOffSetSchedule()=" + getOffSetSchedule() + ", getModelAction()="
				+ getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}

}
