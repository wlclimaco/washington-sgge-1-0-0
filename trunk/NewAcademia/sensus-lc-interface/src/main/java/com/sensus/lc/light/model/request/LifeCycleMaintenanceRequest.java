package com.sensus.lc.light.model.request;

import com.sensus.common.model.request.MaintenanceRequest;
import com.sensus.lc.light.model.LifeCycleStateEnum;

/**
 * Used for updating the Life Cycle State of a given Light instance.
 */
public class LifeCycleMaintenanceRequest extends MaintenanceRequest
{

	/** The light id. */
	private Integer lightId;

	/** The new life cycle state. */
	private LifeCycleStateEnum newLifeCycleState;

	/**
	 * Gets the new life cycle state.
	 *
	 * @return the new life cycle state
	 */
	public LifeCycleStateEnum getNewLifeCycleState()
	{
		return newLifeCycleState;
	}

	/**
	 * Sets the new life cycle state.
	 *
	 * @param newLifeCycleState the new new life cycle state
	 */
	public void setNewLifeCycleState(LifeCycleStateEnum newLifeCycleState)
	{
		this.newLifeCycleState = newLifeCycleState;
	}

	/**
	 * Gets the light id.
	 *
	 * @return the lightId
	 */
	public Integer getLightId()
	{
		return lightId;
	}

	/**
	 * Sets the light id.
	 *
	 * @param lightId the lightId to set
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LifeCycleMaintenanceRequest [getNewLifeCycleState()=" + getNewLifeCycleState() + ", getLightId()="
				+ getLightId() + ", toString()=" + super.toString() + "]";
	}
}
