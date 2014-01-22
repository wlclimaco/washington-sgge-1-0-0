package com.sensus.lc.communication.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;

/**
 * AlertCommunicationConfig model object.
 */
@SuppressWarnings("serial")
public class AlertCommunication extends SensusModel
{

	/** The tenant id. */
	private Integer tenantId;

	/**
	 * Phase attributes.
	 */
	private Integer phaseIndicator;
	private Date phaseStartTime;
	private Date phaseEndTime;
	private Boolean successIndicator;

	/**
	 * Cycle attributes.
	 */
	private Date cycleStartTime;

	/**
	 * @return the tenantId
	 */
	public Integer getTenantId()
	{
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(Integer tenantId)
	{
		this.tenantId = tenantId;
	}

	/**
	 * @return the phaseIndicator
	 */
	public Integer getPhaseIndicator()
	{
		return phaseIndicator;
	}

	/**
	 * @param phaseIndicator the phaseIndicator to set
	 */
	public void setPhaseIndicator(Integer phaseIndicator)
	{
		this.phaseIndicator = phaseIndicator;
	}

	/**
	 * @return the phaseStartTime
	 */
	public Date getPhaseStartTime()
	{
		return phaseStartTime;
	}

	/**
	 * @param phaseStartTime the phaseStartTime to set
	 */
	public void setPhaseStartTime(Date phaseStartTime)
	{
		this.phaseStartTime = phaseStartTime;
	}

	/**
	 * @return the phaseEndTime
	 */
	public Date getPhaseEndTime()
	{
		return phaseEndTime;
	}

	/**
	 * @param phaseEndTime the phaseEndTime to set
	 */
	public void setPhaseEndTime(Date phaseEndTime)
	{
		this.phaseEndTime = phaseEndTime;
	}

	/**
	 * @return the successIndicator
	 */
	public Boolean getSuccessIndicator()
	{
		return successIndicator;
	}

	/**
	 * @param successIndicator the successIndicator to set
	 */
	public void setSuccessIndicator(Boolean successIndicator)
	{
		this.successIndicator = successIndicator;
	}

	/**
	 * @return the cycleStartTime
	 */
	public Date getCycleStartTime()
	{
		return cycleStartTime;
	}

	/**
	 * @param cycleStartTime the cycleStartTime to set
	 */
	public void setCycleStartTime(Date cycleStartTime)
	{
		this.cycleStartTime = cycleStartTime;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AlertCommunication [tenantId=" + tenantId + ", phaseIndicator=" + phaseIndicator + ", phaseStartTime="
				+ phaseStartTime + ", phaseEndTime=" + phaseEndTime + ", successIndicator=" + successIndicator
				+ ", cycleStartTime=" + cycleStartTime + "]";
	}

}
