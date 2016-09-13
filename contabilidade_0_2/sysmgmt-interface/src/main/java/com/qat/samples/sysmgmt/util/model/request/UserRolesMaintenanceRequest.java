package com.qat.samples.sysmgmt.util.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.entidade.model.UserRoles;

/**
 * The Model Object DrugRequest.
 */
public class UserRolesMaintenanceRequest extends Request
{

	/** The drug. */
	@XmlElement(nillable = true)
	private UserRoles userRole;

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
	 * Instantiates a new drug maintenance request.
	 *
	 * @param drug the drug
	 * @param returnList the return list
	 * @param returnListPaged the return list paged
	 */
	public UserRolesMaintenanceRequest(UserRoles drug, Boolean returnList, Boolean returnListPaged)
	{
		this.userRole = drug;
		this.returnList = returnList;
		this.returnListPaged = returnListPaged;
	}

	/**
	 * Instantiates a new drug maintenance request.
	 */
	public UserRolesMaintenanceRequest()
	{

	}



	public UserRoles getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoles userRole) {
		this.userRole = userRole;
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

	@Override
	public String toString() {
		return "UserRolesMaintenanceRequest [getUserRole()=" + getUserRole() + ", getReturnList()=" + getReturnList()
				+ ", getReturnListPaged()=" + getReturnListPaged() + ", toString()=" + super.toString() + "]";
	}

}