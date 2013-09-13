package com.sensus.dm.elec.action.model;

import com.sensus.dm.common.action.model.ActionType;

/**
 * The Class DemandResponseAction.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class DemandResponseAction extends ElectricMeterAction
{
	/** The duration. */
	private Integer demandResponseDuration;

	/** The enrollment code. */
	private Integer enrollmentCode;

	/**
	 * Instantiates a new demand response action.
	 * 
	 * @param actionType the action type
	 */
	public DemandResponseAction(ActionType actionType)
	{
		super(actionType);
	}

	/**
	 * Gets the demand response duration.
	 * 
	 * @return the demand response duration
	 */
	public Integer getDemandResponseDuration()
	{
		return demandResponseDuration;
	}

	/**
	 * Sets the demand response duration.
	 * 
	 * @param demandResponseDuration the new demand response duration
	 */
	public void setDemandResponseDuration(Integer demandResponseDuration)
	{
		this.demandResponseDuration = demandResponseDuration;
	}

	/**
	 * Gets the enrollment code.
	 * 
	 * @return the enrollment code
	 */
	public Integer getEnrollmentCode()
	{
		return enrollmentCode;
	}

	/**
	 * Sets the enrollment code.
	 * 
	 * @param enrollmentCode the new enrollment code
	 */
	public void setEnrollmentCode(Integer enrollmentCode)
	{
		this.enrollmentCode = enrollmentCode;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.action.model.DMAction#toString()
	 */
	@Override
	public String toString()
	{
		return "DemandResponseAction [getDemandResponseDuration()=" + getDemandResponseDuration()
				+ ", getEnrollmentCode()=" + getEnrollmentCode() + ", toString()=" + super.toString() + "]";
	}
}
