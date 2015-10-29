package com.qat.samples.sysmgmt.util;

import com.qat.framework.model.QATModel;

/**
 * The Class PGSIUser.
 */
@SuppressWarnings("serial")
public class PGSIUser extends QATModel
{

	/** Attributes. */
	private Integer userId;

	/** The status. */
	private StatusEnum status;

	/**
	 * The Constructor.
	 */
	public PGSIUser()
	{

	}

	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public Integer getUserId()
	{
		return userId;
	}

	/**
	 * Sets the user id.
	 * 
	 * @param userId the user id
	 */
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public StatusEnum getStatus()
	{
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status the status
	 */
	public void setStatus(StatusEnum status)
	{
		this.status = status;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PGSIUser [getUserId()=" + getUserId() + ", getStatus()=" + getStatus() + ", toString()="
				+ super.toString() + "]";
	}
}