package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.sendsolv.model.response.SecurityQuestionResponse;

/**
 * This interface exposes operations do deal with the Security Questions & Answers, it assigns a list of
 * questions/answers to the {@link com.prosperitasglobal.sendsolv.model.Member}.
 */
public interface ISecurityQuestionBAI
{

	/**
	 * Fetch all security questions.
	 *
	 * @return the security question response
	 */
	public SecurityQuestionResponse fetchAllSecurityQuestions();

}
