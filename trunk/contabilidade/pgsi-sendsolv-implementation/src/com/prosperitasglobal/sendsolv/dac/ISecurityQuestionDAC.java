package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.PersonSecurityAnswer;
import com.prosperitasglobal.sendsolv.model.SecurityQuestion;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ISecurityQuestionDAC covers operations for related with security questions and answers to the
 * {@link Member}.
 */
public interface ISecurityQuestionDAC
{
	/*
	 * ==================================
	 * Person Security Answer Operations.
	 * ==================================
	 */

	/**
	 * Insert person security answer.
	 *
	 * @param securityAnswer the security answer
	 * @return the internal results response
	 */
	public InternalResultsResponse<PersonSecurityAnswer> insertPersonSecurityAnswer(PersonSecurityAnswer
			securityAnswer);

	/**
	 * Update person security answer.
	 *
	 * @param securityAnswer the security answer
	 * @return the internal results response
	 */
	public InternalResultsResponse<PersonSecurityAnswer> updatePersonSecurityAnswer(PersonSecurityAnswer
			securityAnswer);

	/**
	 * Delete person security answer.
	 *
	 * @param securityAnswer the security answer
	 * @return the internal response
	 */
	public InternalResponse deletePersonSecurityAnswer(PersonSecurityAnswer securityAnswer);

	/**
	 * Fetch person security answers by person id.
	 *
	 * @param personId the person id
	 * @return the internal results response
	 */
	public InternalResultsResponse<PersonSecurityAnswer> fetchPersonSecurityAnswersByPersonId(Integer personId);

	/*
	 * ==================================
	 * Security Question Operations.
	 * ==================================
	 */

	/**
	 * Fetch all security questions.
	 *
	 * @return the internal results response
	 */
	public InternalResultsResponse<SecurityQuestion> fetchAllSecurityQuestions();

	/**
	 * Insert security question.
	 *
	 * @param securityQuestion the security question
	 * @return the internal results response
	 */
	public InternalResultsResponse<SecurityQuestion> insertSecurityQuestion(SecurityQuestion securityQuestion);

}
