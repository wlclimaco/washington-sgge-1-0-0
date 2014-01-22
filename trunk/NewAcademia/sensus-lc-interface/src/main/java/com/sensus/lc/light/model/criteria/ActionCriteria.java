package com.sensus.lc.light.model.criteria;

import java.util.Date;

import com.sensus.lc.light.model.BlinkStatusEnum;
import com.sensus.lc.light.model.GetDataFromLightEnum;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.OverrideEnum;

/**
 * Criteria object used for Action parameters.
 * 
 * @author - Thiago QAT
 */
public class ActionCriteria
{

	/**
	 * Attributes.
	 */

	private Integer percentage;

	private BlinkStatusEnum blinkStatus;

	private OverrideEnum override;

	private Date overridePerDate;

	private LifeCycleStateEnum lifeCycleState;

	private Boolean isClearOverride;

	private Boolean lightInCommunicationFailure = false;

	private Boolean lightHistory = false;

	private GetDataFromLightEnum getDataFromLightEnum;

	/**
	 * @return the percentage
	 */
	public Integer getPercentage()
	{
		return percentage;
	}

	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(Integer percentage)
	{
		this.percentage = percentage;
	}

	/**
	 * @return the blinkStatus
	 */
	public BlinkStatusEnum getBlinkStatus()
	{
		return blinkStatus;
	}

	/**
	 * @param blinkStatus the blinkStatus to set
	 */
	public void setBlinkStatus(BlinkStatusEnum blinkStatus)
	{
		this.blinkStatus = blinkStatus;
	}

	/**
	 * @return the override
	 */
	public OverrideEnum getOverride()
	{
		return override;
	}

	/**
	 * @param override the override to set
	 */
	public void setOverride(OverrideEnum override)
	{
		this.override = override;
	}

	/**
	 * @return the overridePerDate
	 */
	public Date getOverridePerDate()
	{
		return overridePerDate;
	}

	/**
	 * @param overridePerDate the overridePerDate to set
	 */
	public void setOverridePerDate(Date overridePerDate)
	{
		this.overridePerDate = overridePerDate;
	}

	/**
	 * @return the lifeCycleState
	 */
	public LifeCycleStateEnum getLifeCycleState()
	{
		return lifeCycleState;
	}

	/**
	 * @param lifeCycleState the lifeCycleState to set
	 */
	public void setLifeCycleState(LifeCycleStateEnum lifeCycleState)
	{
		this.lifeCycleState = lifeCycleState;
	}

	/**
	 * @return the isClearOverride
	 */
	public Boolean getIsClearOverride()
	{
		return isClearOverride;
	}

	/**
	 * @return the isClearOverride
	 */
	public Boolean isClearOverride()
	{
		return isClearOverride;
	}

	/**
	 * @param isClearOverride the isClearOverride to set
	 */
	public void setIsClearOverride(Boolean isClearOverride)
	{
		this.isClearOverride = isClearOverride;
	}

	/**
	 * @return the lightInCommunicationFailure
	 */
	public Boolean getLightInCommunicationFailure()
	{
		return lightInCommunicationFailure;
	}

	/**
	 * @param lightInCommunicationFailure the lightInCommunicationFailure to set
	 */
	public void setLightInCommunicationFailure(Boolean lightInCommunicationFailure)
	{
		this.lightInCommunicationFailure = lightInCommunicationFailure;
	}

	/**
	 * @return the lightHistory
	 */
	public Boolean getLightHistory()
	{
		return lightHistory;
	}

	/**
	 * @param lightHistory the lightHistory to set
	 */
	public void setLightHistory(Boolean lightHistory)
	{
		this.lightHistory = lightHistory;
	}

	/**
	 * @return the getDataFromLightEnum
	 */
	public GetDataFromLightEnum getGetDataFromLightEnum()
	{
		return getDataFromLightEnum;
	}

	/**
	 * @param getDataFromLightEnum the getDataFromLightEnum to set
	 */
	public void setGetDataFromLightEnum(GetDataFromLightEnum getDataFromLightEnum)
	{
		this.getDataFromLightEnum = getDataFromLightEnum;
	}

}
