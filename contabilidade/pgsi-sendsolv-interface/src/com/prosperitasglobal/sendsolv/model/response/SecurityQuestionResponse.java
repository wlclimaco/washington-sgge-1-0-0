package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.SecurityQuestion;
import com.qat.framework.model.response.InquiryResponse;

/**
 * Security question response.
 *
 */
public class SecurityQuestionResponse extends InquiryResponse
{
	/** The security question list returned. */
	private List<SecurityQuestion> securityQuestionList;

	/**
	 * @return the securityQuestionList
	 */
	public List<SecurityQuestion> getSecurityQuestionList()
	{
		return securityQuestionList;
	}

	/**
	 * @param securityQuestionList the securityQuestionList to set
	 */
	public void setSecurityQuestionList(List<SecurityQuestion> securityQuestionList)
	{
		this.securityQuestionList = securityQuestionList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setSecurityQuestionList((List<SecurityQuestion>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SecurityQuestionResponse [getSecurityQuestionList()=" + getSecurityQuestionList()
				+ ", getResultsSetInfo()=" + getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
