package com.sensus.dm.common.device.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class GeocodeLatLongTrunc.
 * 
 * @author - QAT Brazil
 */
@SuppressWarnings("serial")
public class GeocodeLatLongTrunc extends SensusModel
{
	/** The latitude trunc. */
	private Double latitudeTrunc;

	/** The longitude trunc. */
	private Double longitudeTrunc;

	/**
	 * Instantiates a new geocode lat long trunc.
	 */
	public GeocodeLatLongTrunc()
	{
	}

	/**
	 * Instantiates a new geocode lat long trunc.
	 * 
	 * @param latitudeTruncParam the latitude trunc param
	 * @param longitudeTruncParam the longitude trunc param
	 */
	public GeocodeLatLongTrunc(Double latitudeTruncParam, Double longitudeTruncParam)
	{
		setLatitudeTrunc(latitudeTruncParam);
		setLongitudeTrunc(longitudeTruncParam);
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "GeocodeLatLongTrunc [getLatitudeTrunc()=" + getLatitudeTrunc() + ", getLongitudeTrunc()="
				+ getLongitudeTrunc() + "]";
	}
}
