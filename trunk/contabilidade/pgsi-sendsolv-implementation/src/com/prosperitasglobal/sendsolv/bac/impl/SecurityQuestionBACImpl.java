package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.sendsolv.bac.ISecurityQuestionBAC;
import com.prosperitasglobal.sendsolv.dac.ISecurityQuestionDAC;
import com.prosperitasglobal.sendsolv.model.SecurityQuestion;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class SecurityQuestionBACImpl.
 */
public class SecurityQuestionBACImpl implements ISecurityQuestionBAC
{

	/** The security question dac. */
	private ISecurityQuestionDAC securityQuestionDAC;

	/**
	 * Gets the security question dac.
	 *
	 * @return the securityQuestionDAC
	 */
	public ISecurityQuestionDAC getSecurityQuestionDAC()
	{
		return securityQuestionDAC;
	}

	/**
	 * Sets the security question dac.
	 *
	 * @param securityQuestionDAC the securityQuestionDAC to set
	 */
	public void setSecurityQuestionDAC(ISecurityQuestionDAC securityQuestionDAC)
	{
		this.securityQuestionDAC = securityQuestionDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ISecurityQuestionBAC#fetchAllSecurityQuestions()
	 */
	@Override
	public InternalResultsResponse<SecurityQuestion> fetchAllSecurityQuestions()
	{
		return getSecurityQuestionDAC().fetchAllSecurityQuestions();
	}

}
