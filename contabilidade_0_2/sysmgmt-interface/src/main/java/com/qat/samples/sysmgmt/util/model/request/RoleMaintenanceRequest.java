package com.qat.samples.sysmgmt.util.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.entidade.model.Role;

/**
 * The Model Object RoleRequest.
 */
public class RoleMaintenanceRequest extends Request
{

	/** The role. */
	@XmlElement(nillable = true)
	private Role role;

	/**
	 * The return list.
	 * Indicate true to return a list of objects or false not to return any objects
	 * */
	@XmlElement(nillable = true)
	private Boolean returnList;

	/**
	 * The return list paged.
	 * Indicate true to return the list of objects paged or false to return all the objects at once
	 * you must set returnList to true for this to work
	 * */
	@XmlElement(nillable = true)
	private Boolean returnListPaged;

	/**
	 * Instantiates a new role maintenance request.
	 *
	 * @param role the role
	 * @param returnList the return list
	 * @param returnListPaged the return list paged
	 */
	public RoleMaintenanceRequest(Role role, Boolean returnList, Boolean returnListPaged)
	{
		this.role = role;
		this.returnList = returnList;
		this.returnListPaged = returnListPaged;
	}

	/**
	 * Instantiates a new role maintenance request.
	 */
	public RoleMaintenanceRequest()
	{

	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Role getRole()
	{
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(Role role)
	{
		this.role = role;
	}

	/**
	 * Gets the return list.
	 *
	 * @return the return list
	 */
	public Boolean getReturnList()
	{
		return (returnList == null) ? false : returnList;
	}

	/**
	 * Sets the return list.
	 *
	 * @param returnList the new return list
	 */
	public void setReturnList(Boolean returnList)
	{
		this.returnList = returnList;
	}

	/**
	 * Gets the return list paged.
	 *
	 * @return the return list paged
	 */
	public Boolean getReturnListPaged()
	{
		return (returnListPaged == null) ? false : returnListPaged;
	}

	/**
	 * Sets the return list paged.
	 *
	 * @param returnListPaged the new return list paged
	 */
	public void setReturnListPaged(Boolean returnListPaged)
	{
		this.returnListPaged = returnListPaged;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RoleMaintenanceRequest [getRole()=" + getRole() + ", getReturnList()=" + getReturnList() + ", getReturnListPaged()=" + getReturnListPaged() + ", getUserContext()="
				+ getRequestContext() + "]";
	}

}