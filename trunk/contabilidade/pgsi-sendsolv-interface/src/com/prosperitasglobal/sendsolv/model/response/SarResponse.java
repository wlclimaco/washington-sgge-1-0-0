package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.SuspiciousActivity;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class SarResponse.
 */
public class SarResponse extends InquiryResponse
{

	/** The suspicious activity list. */
	private List<SuspiciousActivity> suspiciousActivityList;

	/**
	 * Gets the suspicious activity list.
	 *
	 * @return the suspicious activity list
	 */
	public List<SuspiciousActivity> getSuspiciousActivityList()
	{
		return suspiciousActivityList;
	}

	/**
	 * Sets the suspicious activity list.
	 *
	 * @param suspiciousActivityList the new suspicious activity list
	 */
	public void setSuspiciousActivityList(List<SuspiciousActivity> suspiciousActivityList)
	{
		this.suspiciousActivityList = suspiciousActivityList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setSuspiciousActivityList((List<SuspiciousActivity>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SarResponse [suspiciousActivityList=" + suspiciousActivityList + ", getSuspiciousActivityList()="
				+ getSuspiciousActivityList() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}

}
