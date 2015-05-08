package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.sendsolv.model.SecurityQuestion;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ISecurityQuestionBAC.
 */
public interface ISecurityQuestionBAC
{

	/**
	 * Fetch all security questions.
	 *
	 * @return the internal results response
	 */
	public InternalResultsResponse<SecurityQuestion> fetchAllSecurityQuestions();

}
