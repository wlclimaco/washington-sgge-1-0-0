package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Organization;
import com.qat.framework.model.request.MaintenanceRequest;

/**
 * The Class OrganizationMaintenanceRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:13:40 AM
 */
public class OrganizationMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Organization organization;

	/**
	 * The Constructor.
	 */
	public OrganizationMaintenanceRequest()
	{

	}

	/**
	 * Gets the organization.
	 *
	 * @return the organization
	 */
	public Organization getOrganization()
	{
		return organization;
	}

	/**
	 * Sets the organization.
	 *
	 * @param organization the organization
	 */
	public void setOrganization(Organization organization)
	{
		this.organization = organization;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "OrganizationMaintenanceRequest [getOrganization()=" + getOrganization() + ", getUserContext()="
				+ getUserContext() + "]";
	}

}