package com.sensus.mlc.schedule.model;

/**
 * The Class OffSetSchedule.
 */
@SuppressWarnings("serial")
public class OffsetSchedule extends Schedule
{

	/** The turn lights off minutes. */
	private Integer sunriseOffsetMinutes;

	/** The turn lights on minutes. */
	private Integer sunsetOffsetMinutes;

	/** The sunrise before. */
	private Boolean sunriseBefore;

	/** The sunset before. */
	private Boolean sunsetBefore;

	/** The percentage. */
	private Integer intensity;

	/**
	 * Instantiates a new off set schedule.
	 */
	public OffsetSchedule()
	{
		setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
	}

	/**
	 * Instantiates a new offset schedule.
	 * 
	 * @param turnLightsOffMinutesParam the turn lights off minutes param
	 * @param turnLightsOnMinutesParam the turn lights on minutes param
	 * @param sunriseBeforeParam the sunrise before param
	 * @param sunsetBeforeParam the sunset before param
	 * @param intensityParam the intensity param
	 */
	public OffsetSchedule(Integer turnLightsOffMinutesParam, Integer turnLightsOnMinutesParam,
			Boolean sunriseBeforeParam, Boolean sunsetBeforeParam, Integer intensityParam)
	{
		setSunriseOffsetMinutes(turnLightsOffMinutesParam);
		setSunsetOffsetMinutes(turnLightsOnMinutesParam);
		setSunriseBefore(sunriseBeforeParam);
		setSunsetBefore(sunsetBeforeParam);
		setIntensity(intensityParam);
	}

	/**
	 * Gets the turn lights off minutes.
	 * 
	 * @return the turn lights off minutes
	 */
	public Integer getSunriseOffsetMinutes()
	{
		return sunriseOffsetMinutes;
	}

	/**
	 * Sets the turn lights off minutes.
	 * 
	 * @param turnLightsOffMinutes the new turn lights off minutes
	 */
	public void setSunriseOffsetMinutes(Integer turnLightsOffMinutes)
	{
		sunriseOffsetMinutes = turnLightsOffMinutes;
	}

	/**
	 * Gets the turn lights on minutes.
	 * 
	 * @return the turn lights on minutes
	 */
	public Integer getSunsetOffsetMinutes()
	{
		return sunsetOffsetMinutes;
	}

	/**
	 * Sets the turn lights on minutes.
	 * 
	 * @param turnLightsOnMinutes the new turn lights on minutes
	 */
	public void setSunsetOffsetMinutes(Integer turnLightsOnMinutes)
	{
		sunsetOffsetMinutes = turnLightsOnMinutes;
	}

	/**
	 * Gets the sunrise before.
	 * 
	 * @return the sunrise before
	 */
	public Boolean getSunriseBefore()
	{
		return sunriseBefore;
	}

	/**
	 * Sets the sunrise before.
	 * 
	 * @param sunriseBefore the new sunrise before
	 */
	public void setSunriseBefore(Boolean sunriseBefore)
	{
		this.sunriseBefore = sunriseBefore;
	}

	/**
	 * Gets the sunset before.
	 * 
	 * @return the sunset before
	 */
	public Boolean getSunsetBefore()
	{
		return sunsetBefore;
	}

	/**
	 * Sets the sunset before.
	 * 
	 * @param sunsetBefore the new sunset before
	 */
	public void setSunsetBefore(Boolean sunsetBefore)
	{
		this.sunsetBefore = sunsetBefore;
	}

	/**
	 * Gets the intensity.
	 * 
	 * @return the intensity
	 */
	public Integer getIntensity()
	{
		return intensity;
	}

	/**
	 * Sets the intensity.
	 * 
	 * @param intensity the new intensity
	 */
	public void setIntensity(Integer intensity)
	{
		this.intensity = intensity;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.model.Schedule#toString()
	 */
	@Override
	public String toString()
	{
		return "OffsetSchedule [getSunriseOffsetMinutes()=" + getSunriseOffsetMinutes() + ", getSunsetOffsetMinutes()="
				+ getSunsetOffsetMinutes() + ", getSunriseBefore()=" + getSunriseBefore() + ", getSunsetBefore()="
				+ getSunsetBefore() + ", getIntensity()=" + getIntensity() + ", getDescription()=" + getDescription()
				+ ", getId()=" + getId() + ", getLights()=" + getLights() + ", getName()=" + getName()
				+ ", getSmartPointCount()=" + getSmartPointCount() + ", getScheduleTypeEnum()=" + getScheduleTypeEnum()
				+ ", getScheduleTypeValue()=" + getScheduleTypeValue() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}

}
