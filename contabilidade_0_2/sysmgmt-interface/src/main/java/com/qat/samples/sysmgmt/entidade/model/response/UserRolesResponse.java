package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.UserRoles;

public class UserRolesResponse extends InquiryResponse
{

	/** Attributes */
	private List<UserRoles> userRolesList;

	/**
	 * The Constructor.
	 */
	public UserRolesResponse()
	{

	}

	/**
	 * @return the userRolesList
	 */
	public List<UserRoles> getUserRolesList()
	{
		return userRolesList;
	}

	/**
	 * @param userRolesList the userRolesList to set
	 */
	public void setUserRolesList(List<UserRoles> userRolesList)
	{
		this.userRolesList = userRolesList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setUserRolesList((List<UserRoles>)coll);
	}

	@Override
	public String toString()
	{
		return "UserRolesResponse [getUserRolesList()=" + getUserRolesList() + ", toString()=" + super.toString() + "]";
	}

}