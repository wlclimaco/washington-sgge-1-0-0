package com.sensus.dm.common.device.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class GeocodeDeviceInfo.
 * 
 * @author QAT Global
 */
@SuppressWarnings("serial")
public class GeocodeDeviceInfo extends SensusModel
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
	private Integer devicesTotalByLatLong;

	/** The bottom left lat. */
	private Double bottomLeftLat;

	/** The bottom left lon. */
	private Double bottomLeftLon;

	/** The top right lat. */
	private Double topRightLat;

	/** The top right lon. */
	private Double topRightLon;

	/** The current light status. */
	private AlertEnum alertEnum;

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
	 * Gets the devices total by lat long.
	 * 
	 * @return the devices total by lat long
	 */
	public Integer getDevicesTotalByLatLong()
	{
		return devicesTotalByLatLong;
	}

	/**
	 * Sets the devices total by lat long.
	 * 
	 * @param devicesTotalByLatLong the new devices total by lat long
	 */
	public void setDevicesTotalByLatLong(Integer devicesTotalByLatLong)
	{
		this.devicesTotalByLatLong = devicesTotalByLatLong;
	}

	/**
	 * Gets the bottom left lat.
	 * 
	 * @return the bottom left lat
	 */
	public Double getBottomLeftLat()
	{
		return bottomLeftLat;
	}

	/**
	 * Sets the bottom left lat.
	 * 
	 * @param bottomLeftLat the new bottom left lat
	 */
	public void setBottomLeftLat(Double bottomLeftLat)
	{
		this.bottomLeftLat = bottomLeftLat;
	}

	/**
	 * Gets the bottom left lon.
	 * 
	 * @return the bottom left lon
	 */
	public Double getBottomLeftLon()
	{
		return bottomLeftLon;
	}

	/**
	 * Sets the bottom left lon.
	 * 
	 * @param bottomLeftLon the new bottom left lon
	 */
	public void setBottomLeftLon(Double bottomLeftLon)
	{
		this.bottomLeftLon = bottomLeftLon;
	}

	/**
	 * Gets the top right lat.
	 * 
	 * @return the top right lat
	 */
	public Double getTopRightLat()
	{
		return topRightLat;
	}

	/**
	 * Sets the top right lat.
	 * 
	 * @param topRightLat the new top right lat
	 */
	public void setTopRightLat(Double topRightLat)
	{
		this.topRightLat = topRightLat;
	}

	/**
	 * Gets the top right lon.
	 * 
	 * @return the top right lon
	 */
	public Double getTopRightLon()
	{
		return topRightLon;
	}

	/**
	 * Sets the top right lon.
	 * 
	 * @param topRightLon the new top right lon
	 */
	public void setTopRightLon(Double topRightLon)
	{
		this.topRightLon = topRightLon;
	}

	/**
	 * Gets the alert enum.
	 * 
	 * @return the alert enum
	 */
	public AlertEnum getAlertEnum()
	{
		return alertEnum;
	}

	/**
	 * Sets the alert enum.
	 * 
	 * @param alertEnum the new alert enum
	 */
	public void setAlertEnum(AlertEnum alertEnum)
	{
		this.alertEnum = alertEnum;
	}

	/**
	 * Gets the alert enum value.
	 * 
	 * @return the alert enum value
	 */
	public Integer getAlertEnumValue()
	{
		if (getAlertEnum() != null)
		{
			return getAlertEnum().getValue();
		}

		return null;
	}

	/**
	 * Sets the alert enum value.
	 * 
	 * @param alertId the new alert enum value
	 */
	public void setAlertEnumValue(Integer alertId)
	{
		setAlertEnum(AlertEnum.enumForValue(alertId));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "GeocodeDeviceInfo [getLatitudeAvg()=" + getLatitudeAvg() + ", getLongitudeAvg()=" + getLongitudeAvg()
				+ ", getLatitudeTrunc()=" + getLatitudeTrunc() + ", getLongitudeTrunc()=" + getLongitudeTrunc()
				+ ", getDevicesTotalByLatLong()=" + getDevicesTotalByLatLong() + ", getBottomLeftLat()="
				+ getBottomLeftLat() + ", getBottomLeftLon()=" + getBottomLeftLon() + ", getTopRightLat()="
				+ getTopRightLat() + ", getTopRightLon()=" + getTopRightLon() + ", getAlertEnum()=" + getAlertEnum()
				+ ", getAlertEnumValue()=" + getAlertEnumValue() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}
}
