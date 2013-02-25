package com.sensus.mlc.smartpoint.model;

import java.util.List;

import com.sensus.common.model.SensusModel;

/**
 * The Class GeocodeSmartpointInfo.
 */
@SuppressWarnings("serial")
public class GeocodeSmartpointInfo extends SensusModel
{
	/** The latitude avg. */
	private Double latitudeAvg;

	/** The longitude avg. */
	private Double longitudeAvg;

	/** The latitude trunc. */
	private Double latitudeTrunc;

	/** The longitude trunc. */
	private Double longitudeTrunc;

	/** The smartpoints total by lat long. */
	private Integer smartpointsTotalByLatLong;

	/** The current light status. */
	private Integer currentLightStatus;

	/** The lights. */
	private List<Integer> lightIds;

	/**
	 * Gets the latitude avg.
	 * 
	 * @return the latitude avg
	 */
	public Double getLatitudeAvg()
	{
		return latitudeAvg;
	}

	/**
	 * Sets the latitude avg.
	 * 
	 * @param latitudeAvg the new latitude avg
	 */
	public void setLatitudeAvg(Double latitudeAvg)
	{
		this.latitudeAvg = latitudeAvg;
	}

	/**
	 * Gets the longitude avg.
	 * 
	 * @return the longitude avg
	 */
	public Double getLongitudeAvg()
	{
		return longitudeAvg;
	}

	/**
	 * Sets the longitude avg.
	 * 
	 * @param longitudeAvg the new longitude avg
	 */
	public void setLongitudeAvg(Double longitudeAvg)
	{
		this.longitudeAvg = longitudeAvg;
	}

	/**
	 * Gets the latitude trunc.
	 * 
	 * @return the latitude trunc
	 */
	public Double getLatitudeTrunc()
	{
		return latitudeTrunc;
	}

	/**
	 * Sets the latitude trunc.
	 * 
	 * @param latitudeTrunc the new latitude trunc
	 */
	public void setLatitudeTrunc(Double latitudeTrunc)
	{
		this.latitudeTrunc = latitudeTrunc;
	}

	/**
	 * Gets the longitude trunc.
	 * 
	 * @return the longitude trunc
	 */
	public Double getLongitudeTrunc()
	{
		return longitudeTrunc;
	}

	/**
	 * Sets the longitude trunc.
	 * 
	 * @param longitudeTrunc the new longitude trunc
	 */
	public void setLongitudeTrunc(Double longitudeTrunc)
	{
		this.longitudeTrunc = longitudeTrunc;
	}

	/**
	 * Gets the smartpoints total by lat long.
	 * 
	 * @return the smartpoints total by lat long
	 */
	public Integer getSmartpointsTotalByLatLong()
	{
		return smartpointsTotalByLatLong;
	}

	/**
	 * Sets the smartpoints total by lat long.
	 * 
	 * @param smartpointsTotalByLatLong the new smartpoints total by lat long
	 */
	public void setSmartpointsTotalByLatLong(Integer smartpointsTotalByLatLong)
	{
		this.smartpointsTotalByLatLong = smartpointsTotalByLatLong;
	}

	/**
	 * Gets the current light status.
	 * 
	 * @return the current light status
	 */
	public Integer getCurrentLightStatus()
	{
		return currentLightStatus;
	}

	/**
	 * Sets the current light status.
	 * 
	 * @param currentLightStatus the new current light status
	 */
	public void setCurrentLightStatus(Integer currentLightStatus)
	{
		this.currentLightStatus = currentLightStatus;
	}

	/**
	 * Gets the light ids.
	 * 
	 * @return the light ids
	 */
	public List<Integer> getLightIds()
	{
		return lightIds;
	}

	/**
	 * Sets the light ids.
	 * 
	 * @param lightIds the new light ids
	 */
	public void setLightIds(List<Integer> lightIds)
	{
		this.lightIds = lightIds;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "GeocodeSmartpointInfo [getLatitudeAvg()=" + getLatitudeAvg() + ", getLongitudeAvg()="
				+ getLongitudeAvg() + ", getLatitudeTrunc()=" + getLatitudeTrunc() + ", getLongitudeTrunc()="
				+ getLongitudeTrunc() + ", getSmartpointsTotalByLatLong()=" + getSmartpointsTotalByLatLong()
				+ ", getCurrentLightStatus()=" + getCurrentLightStatus() + ", getLightIds()=" + getLightIds()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}
}
