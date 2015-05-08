package com.prosperitasglobal.sendsolv.model.criteria;

import java.io.Serializable;

/**
 * The Class RecipientCriteria.
 */
@SuppressWarnings("serial")
public class RecipientCriteria implements Serializable
{

	/** The member id. */
	private Integer memberId;

	/**
	 * Gets the member id.
	 *
	 * @return the member id
	 */
	public Integer getMemberId()
	{
		return memberId;
	}

	/**
	 * Sets the member id.
	 *
	 * @param memberId the member id
	 */
	public void setMemberId(Integer memberId)
	{
		this.memberId = memberId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RecipientCriteria [getMemberId()=" + getMemberId() + "]";
	}

}
