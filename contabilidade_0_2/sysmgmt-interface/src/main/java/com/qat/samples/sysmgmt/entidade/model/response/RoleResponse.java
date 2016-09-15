package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.Role;

public class RoleResponse extends InquiryResponse
{

	/** Attributes */
	private List<Role> roleList;

	/**
	 * The Constructor.
	 */
	public RoleResponse()
	{

	}

	/**
	 * @return the roleList
	 */
	public List<Role> getRoleList()
	{
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<Role> roleList)
	{
		this.roleList = roleList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setRoleList((List<Role>)coll);
	}

	@Override
	public String toString()
	{
		return "RoleResponse [getRoleList()=" + getRoleList() + ", toString()=" + super.toString() + "]";
	}

}