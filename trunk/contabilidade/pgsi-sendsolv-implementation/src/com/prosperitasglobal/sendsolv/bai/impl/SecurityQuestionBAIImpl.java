package com.prosperitasglobal.sendsolv.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.bac.ISecurityQuestionBAC;
import com.prosperitasglobal.sendsolv.bai.ISecurityQuestionBAI;
import com.prosperitasglobal.sendsolv.model.SecurityQuestion;
import com.prosperitasglobal.sendsolv.model.response.SecurityQuestionResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;

/**
 * The Class SecurityQuestionBAIImpl.
 */
public class SecurityQuestionBAIImpl implements ISecurityQuestionBAI
{
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = SecurityQuestionBAIImpl.class.getName();

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SecurityQuestionBAIImpl.class);

	/** The security question bac. */
	private ISecurityQuestionBAC securityQuestionBAC;

	/**
	 * Gets the security question bac.
	 *
	 * @return the securityQuestionBAC
	 */
	public ISecurityQuestionBAC getSecurityQuestionBAC()
	{
		return securityQuestionBAC;
	}

	/**
	 * Sets the security question bac.
	 *
	 * @param securityQuestionBAC the securityQuestionBAC to set
	 */
	public void setSecurityQuestionBAC(ISecurityQuestionBAC securityQuestionBAC)
	{
		this.securityQuestionBAC = securityQuestionBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bai.ISecurityQuestionBAI#fetchAllSecurityQuestions()
	 */
	@Override
	public SecurityQuestionResponse fetchAllSecurityQuestions()
	{
		SecurityQuestionResponse securityQuestionResponse = new SecurityQuestionResponse();

		try
		{
			InternalResultsResponse<SecurityQuestion> response = getSecurityQuestionBAC().fetchAllSecurityQuestions();
			QATInterfaceUtil.handleOperationStatusAndMessages(securityQuestionResponse, response, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, securityQuestionResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] {CLASS_NAME});
		}

		return securityQuestionResponse;
	}

}
