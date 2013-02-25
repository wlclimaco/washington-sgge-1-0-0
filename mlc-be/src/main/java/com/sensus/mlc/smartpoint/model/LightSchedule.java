package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.SensusModel;

/**
 * Model object that contains light configuration and schedule properties.
 *
 * @see Light for more details about the light object and its relationships.
 *
 * @author Thiago Silva - QAT
 *
 */
@SuppressWarnings("serial")
public class LightSchedule extends SensusModel
{

	/**
	 * Attributes.
	 */
	private Integer lightId;

	private String sunriseTime;

	private Integer sunriseOffset;

	private String sunsetTime;

	private Integer sunsetOffset;

	/**
	 * @return the lightId
	 */
	public Integer getLightId()
	{
		return lightId;
	}

	/**
	 * @param lightId the lightId to set
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
	}

	/**
	 * @return the sunriseTime
	 */
	public String getSunriseTime()
	{
		return sunriseTime;
	}

	/**
	 * @param sunriseTime the sunriseTime to set
	 */
	public void setSunriseTime(String sunriseTime)
	{
		this.sunriseTime = sunriseTime;
	}


	/**
	 * @return the sunsetTime
	 */
	public String getSunsetTime()
	{
		return sunsetTime;
	}

	/**
	 * @param sunsetTime the sunsetTime to set
	 */
	public void setSunsetTime(String sunsetTime)
	{
		this.sunsetTime = sunsetTime;
	}

	/**
	 * @return the sunriseOffset
	 */
	public Integer getSunriseOffset()
	{
		return sunriseOffset;
	}

	/**
	 * @param sunriseOffset the sunriseOffset to set
	 */
	public void setSunriseOffset(Integer sunriseOffset)
	{
		this.sunriseOffset = sunriseOffset;
	}

	/**
	 * @return the sunsetOffset
	 */
	public Integer getSunsetOffset()
	{
		return sunsetOffset;
	}

	/**
	 * @param sunsetOffset the sunsetOffset to set
	 */
	public void setSunsetOffset(Integer sunsetOffset)
	{
		this.sunsetOffset = sunsetOffset;
	}





}
