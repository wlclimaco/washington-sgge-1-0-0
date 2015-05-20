package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Location;

/**
 * The Class LocationMaintenanceRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:49 AM
 */
public class LocationMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Location location;

	/**
	 * The Constructor.
	 */
	public LocationMaintenanceRequest()
	{

	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public Location getLocation()
	{
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the location
	 */
	public void setLocation(Location location)
	{
		this.location = location;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationMaintenanceRequest [getLocation()=" + getLocation() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}