package com.sensus.lc.light.model.request;

import static com.sensus.common.validation.ValidationUtil.isNull;

import com.sensus.cbof.model.Location;
import com.sensus.common.model.request.MaintenanceRequest;

/**
 * Used for updating location information about a given Light.
 */
public class LocationMaintenanceRequest extends MaintenanceRequest
{

	/** The light id. */
	private Integer lightId;

	/** The location. */
	private Location location;

	/**
	 * Gets the light id.
	 *
	 * @return the light id
	 */
	public Integer getLightId()
	{
		return lightId;
	}

	/**
	 * Sets the light id.
	 *
	 * @param lightId the new light id
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
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
	 * @param location the new location
	 */
	public void setLocation(Location location)
	{
		this.location = location;
	}

	/**
	 * Checks if is location filled.
	 *
	 * @return true, if is location filled
	 */
	public boolean isLocationFilled()
	{
		return !isNull(getLocation())
				&& (!isNull(getLocation().getAddress())
						|| !isNull(getLocation().getCity())
						|| !isNull(getLocation().getCountry())
						|| !isNull(getLocation().getLatitude())
						|| !isNull(getLocation().getLongitude())
						|| !isNull(getLocation().getState())
						|| !isNull(getLocation().getZip()));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationMaintenanceRequest [getLightId()=" + getLightId() + ", getLocation()=" + getLocation()
				+ ", isLocationFilled()=" + isLocationFilled() + ", toString()=" + super.toString() + "]";
	}
}
