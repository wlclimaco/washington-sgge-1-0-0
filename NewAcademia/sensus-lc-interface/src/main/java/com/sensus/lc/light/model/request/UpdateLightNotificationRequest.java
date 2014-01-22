package com.sensus.lc.light.model.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.request.Request;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;

public class UpdateLightNotificationRequest extends Request
{
	/** Attributes. */
	private List<Light> lightList = new ArrayList<Light>();
	private LifeCycleStateEnum lifeCycleState;
	private Date messageDate;

	/**
	 * Gets the light list.
	 * 
	 * @return the light list
	 */
	public List<Light> getLightList()
	{
		return lightList;
	}

	/**
	 * Sets the light list.
	 * 
	 * @param lightList the new light list
	 */
	public void setLightList(List<Light> lightList)
	{
		this.lightList = lightList;
	}

	/**
	 * Gets the life cycle state.
	 * 
	 * @return the life cycle state
	 */
	public LifeCycleStateEnum getLifeCycleState()
	{
		return lifeCycleState;
	}

	/**
	 * Sets the life cycle state.
	 * 
	 * @param lifeCycleState the new life cycle state
	 */
	public void setLifeCycleState(LifeCycleStateEnum lifeCycleState)
	{
		this.lifeCycleState = lifeCycleState;
	}

	/**
	 * Gets the message date.
	 * 
	 * @return the message date
	 */
	public Date getMessageDate()
	{
		return messageDate;
	}

	/**
	 * Sets the message date.
	 * 
	 * @param messageDate the new message date
	 */
	public void setMessageDate(Date messageDate)
	{
		this.messageDate = messageDate;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UpdateLightNotificationRequest [getLightList()=" + getLightList() + ", getLifeCycleState()="
				+ getLifeCycleState() + ", getMessageDate()=" + getMessageDate() + ", toString()=" + super.toString()
				+ "]";
	}
}
