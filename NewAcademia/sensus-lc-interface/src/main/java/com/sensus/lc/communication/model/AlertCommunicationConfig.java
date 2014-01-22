package com.sensus.lc.communication.model;

import com.sensus.common.model.SensusModel;

/**
 * AlertCommunicationConfig model object.
 */
@SuppressWarnings("serial")
public class AlertCommunicationConfig extends SensusModel
{

	/** The tenant id. */
	private Integer tenantId;

	/** The phase. */
	private Integer phase;

	/** The elapsed time. */
	private Integer elapsedTime;

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
	 * @return the phase
	 */
	public Integer getPhase()
	{
		return phase;
	}

	/**
	 * @param phase the phase to set
	 */
	public void setPhase(Integer phase)
	{
		this.phase = phase;
	}

	/**
	 * @return the elapsedTime
	 */
	public Integer getElapsedTime()
	{
		return elapsedTime;
	}

	/**
	 * @param elapsedTime the elapsedTime to set
	 */
	public void setElapsedTime(Integer elapsedTime)
	{
		this.elapsedTime = elapsedTime;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AlertCommunicationConfig [tenantId=" + tenantId + ", phase=" + phase + ", elapsedTime=" + elapsedTime
				+ "]";
	}

}
