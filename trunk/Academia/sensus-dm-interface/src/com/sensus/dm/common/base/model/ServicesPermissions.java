package com.sensus.dm.common.base.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ServicesTypePermissions.
 * 
 * @author QAT Global.
 */
public class ServicesPermissions
{

	/** The services type permissions. */
	private List<ServicesTypePermissions> servicesTypePermissions;

	/**
	 * Instantiates a new services type permissions.
	 */
	public ServicesPermissions()
	{

	}

	/**
	 * Instantiates a new services permissions.
	 * 
	 * @param servicesTypePermissionListParam the services type permissions
	 */
	public ServicesPermissions(List<ServicesTypePermissions> servicesTypePermissionListParam)
	{
		setServicesTypePermissions(servicesTypePermissionListParam);
	}

	/**
	 * Gets the services type permissions.
	 * 
	 * @return the services type permissions
	 */
	public List<ServicesTypePermissions> getServicesTypePermissions()
	{
		return servicesTypePermissions;
	}

	/**
	 * Sets the services type permissions.
	 * 
	 * @param param the new services type permissions
	 */
	public void setServicesTypePermissions(List<ServicesTypePermissions> param)
	{
		servicesTypePermissions = param;
	}

	/**
	 * Adds the services type permissions.
	 * 
	 * @param servicesTypePermission the services type permission
	 */
	public void addServicesTypePermissions(ServicesTypePermissions servicesTypePermission)
	{
		if (getServicesTypePermissions() == null)
		{
			setServicesTypePermissions(new ArrayList<ServicesTypePermissions>());
		}

		getServicesTypePermissions().add(servicesTypePermission);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ServicesPermissions [getServicesTypePermissions()=" + getServicesTypePermissions() + "]";
	}
}
