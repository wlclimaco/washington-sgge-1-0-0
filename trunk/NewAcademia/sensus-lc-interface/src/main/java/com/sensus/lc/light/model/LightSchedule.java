package com.sensus.lc.light.model;

import java.util.Date;

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
	private Integer parentId;

	/** The sunrise time. */
	private String sunriseTime;

	/** The sunrise time date. */
	private Date sunriseTimeDate;

	/** The sunrise offset. */
	private Integer sunriseOffset;

	/** The sunset time. */
	private String sunsetTime;

	/** The sunset time date. */
	private Date sunsetTimeDate;

	/** The sunset offset. */
	private Integer sunsetOffset;

	/**
	 * Gets the light id.
	 *
	 * @return the parentId
	 */
	public Integer getParentId()
	{
		return parentId;
	}

	/**
	 * Sets the light id.
	 *
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}

	/**
	 * Gets the sunrise time.
	 *
	 * @return the sunriseTime
	 */
	public String getSunriseTime()
	{
		return sunriseTime;
	}

	/**
	 * Sets the sunrise time.
	 *
	 * @param sunriseTime the sunriseTime to set
	 */
	public void setSunriseTime(String sunriseTime)
	{
		this.sunriseTime = sunriseTime;
	}

	/**
	 * Gets the sunrise time date.
	 *
	 * @return the sunrise time date
	 */
	public Date getSunriseTimeDate()
	{
		return sunriseTimeDate;
	}

	/**
	 * Sets the sunrise time date.
	 *
	 * @param sunriseTimeDate the new sunrise time date
	 */
	public void setSunriseTimeDate(Date sunriseTimeDate)
	{
		this.sunriseTimeDate = sunriseTimeDate;
	}

	/**
	 * Gets the sunset time.
	 *
	 * @return the sunsetTime
	 */
	public String getSunsetTime()
	{
		return sunsetTime;
	}

	/**
	 * Sets the sunset time.
	 *
	 * @param sunsetTime the sunsetTime to set
	 */
	public void setSunsetTime(String sunsetTime)
	{
		this.sunsetTime = sunsetTime;
	}

	/**
	 * Gets the sunset time date.
	 *
	 * @return the sunset time date
	 */
	public Date getSunsetTimeDate()
	{
		return sunsetTimeDate;
	}

	/**
	 * Sets the sunset time date.
	 *
	 * @param sunsetTimeDate the new sunset time date
	 */
	public void setSunsetTimeDate(Date sunsetTimeDate)
	{
		this.sunsetTimeDate = sunsetTimeDate;
	}

	/**
	 * Gets the sunrise offset.
	 *
	 * @return the sunriseOffset
	 */
	public Integer getSunriseOffset()
	{
		return sunriseOffset;
	}

	/**
	 * Sets the sunrise offset.
	 *
	 * @param sunriseOffset the sunriseOffset to set
	 */
	public void setSunriseOffset(Integer sunriseOffset)
	{
		this.sunriseOffset = sunriseOffset;
	}

	/**
	 * Gets the sunset offset.
	 *
	 * @return the sunsetOffset
	 */
	public Integer getSunsetOffset()
	{
		return sunsetOffset;
	}

	/**
	 * Sets the sunset offset.
	 *
	 * @param sunsetOffset the sunsetOffset to set
	 */
	public void setSunsetOffset(Integer sunsetOffset)
	{
		this.sunsetOffset = sunsetOffset;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "LightSchedule [getParentId()=" + getParentId() + ", getSunriseTime()=" + getSunriseTime()
				+ ", getSunriseTimeDate()=" + getSunriseTimeDate() + ", getSunsetTime()=" + getSunsetTime()
				+ ", getSunsetTimeDate()=" + getSunsetTimeDate() + ", getSunriseOffset()=" + getSunriseOffset()
				+ ", getSunsetOffset()=" + getSunsetOffset() + ", toString()=" + super.toString() + "]";
	}
}
