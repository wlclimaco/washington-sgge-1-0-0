package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Member;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class MemberResponse.
 *
 * @author aporto
 * @version 1.0
 * @created 11-Sep-2014 09:39:00 AM
 */
public class MemberResponse extends InquiryResponse
{

	/** Attributes. */
	private List<Member> memberList;

	/**
	 * Gets the member list.
	 *
	 * @return the member list
	 */
	public List<Member> getMemberList()
	{
		return memberList;
	}

	/**
	 * Sets the member list.
	 *
	 * @param memberList the member list
	 */
	public void setMemberList(List<Member> memberList)
	{
		this.memberList = memberList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setMemberList((List<Member>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MemberResponse [getMemberList()=" + getMemberList() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}

}
