package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Member;

/**
 * The Class MemberMaintenanceRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 2:15:09 PM
 */
public class MemberMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes. */
	private Member member;

	/**
	 * The Constructor.
	 */
	public MemberMaintenanceRequest()
	{

	}

	/**
	 * Gets the member.
	 *
	 * @return the member
	 */
	public Member getMember()
	{
		return member;
	}

	/**
	 * Sets the member.
	 *
	 * @param member the member
	 */
	public void setMember(Member member)
	{
		this.member = member;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MemberMaintenanceRequest [getMember()=" + getMember() + ", getUserContext()=" + getUserContext() + "]";
	}
}