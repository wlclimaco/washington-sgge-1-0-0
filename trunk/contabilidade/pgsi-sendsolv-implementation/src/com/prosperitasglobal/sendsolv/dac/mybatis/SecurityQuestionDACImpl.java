package com.prosperitasglobal.sendsolv.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.ISecurityQuestionDAC;
import com.prosperitasglobal.sendsolv.model.PersonSecurityAnswer;
import com.prosperitasglobal.sendsolv.model.SecurityQuestion;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;

/**
 * The Class SecurityQuestionDACImpl.
 */
public class SecurityQuestionDACImpl extends SqlSessionDaoSupport implements ISecurityQuestionDAC
{

	/** The Constant SECURITY_QUESTION_NAMESPACE. */
	private static final String SECURITY_QUESTION_NAMESPACE = "securityQuestionMap.";

	/** The Constant FETCH_PERSON_SECURITY_ANSWERS_BY_PERSON_ID. */
	private static final String FETCH_PERSON_SECURITY_ANSWERS_BY_PERSON_ID = SECURITY_QUESTION_NAMESPACE
			+ "fetchPersonSecurityAnswersByPersonId";

	/** The Constant FETCH_ALL_SECURITY_QUESTIONS. */
	private static final String FETCH_ALL_SECURITY_QUESTIONS = SECURITY_QUESTION_NAMESPACE
			+ "fetchAllSecurityQuestions";

	/** The Constant UPDATE_PERSON_SECURITY_ANSWER. */
	private static final String UPDATE_PERSON_SECURITY_ANSWER = SECURITY_QUESTION_NAMESPACE
			+ "updatePersonSecurityAnswer";

	/** The Constant INSERT_PERSON_SECURITY_ANSWER. */
	private static final String INSERT_PERSON_SECURITY_ANSWER = SECURITY_QUESTION_NAMESPACE
			+ "insertPersonSecurityAnswer";

	/** The Constant DELETE_SECURITY_ANSWER. */
	private static final String DELETE_SECURITY_ANSWER = SECURITY_QUESTION_NAMESPACE + "deletePersonSecurityAnswer";

	/** The Constant INSERT_SECURITY_QUESTION. */
	private static final String INSERT_SECURITY_QUESTION = SECURITY_QUESTION_NAMESPACE + "insertSecurityQuestion";

	@Override
	public InternalResultsResponse<PersonSecurityAnswer> insertPersonSecurityAnswer(PersonSecurityAnswer securityAnswer)
	{
		InternalResultsResponse<PersonSecurityAnswer> response = new InternalResultsResponse<PersonSecurityAnswer>();
		QATMyBatisDacHelper.doInsert(getSqlSession(), INSERT_PERSON_SECURITY_ANSWER, securityAnswer, response);
		return response;
	}

	@Override
	public InternalResultsResponse<PersonSecurityAnswer> updatePersonSecurityAnswer(PersonSecurityAnswer securityAnswer)
	{
		InternalResultsResponse<PersonSecurityAnswer> response = new InternalResultsResponse<PersonSecurityAnswer>();
		QATMyBatisDacHelper.doUpdate(getSqlSession(), UPDATE_PERSON_SECURITY_ANSWER, securityAnswer,
				response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ISecurityQuestionDAC#deletePersonSecurityAnswer(com.prosperitasglobal.sendsolv
	 * .model.PersonSecurityAnswer)
	 */
	@Override
	public InternalResponse deletePersonSecurityAnswer(PersonSecurityAnswer securityAnswer)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), DELETE_SECURITY_ANSWER, securityAnswer, response);
		return response;
	}

	@Override
	public InternalResultsResponse<PersonSecurityAnswer> fetchPersonSecurityAnswersByPersonId(Integer personId)
	{
		InternalResultsResponse<PersonSecurityAnswer> response = new InternalResultsResponse<PersonSecurityAnswer>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PERSON_SECURITY_ANSWERS_BY_PERSON_ID, personId,
				response);
		return response;
	}

	@Override
	public InternalResultsResponse<SecurityQuestion> fetchAllSecurityQuestions()
	{
		InternalResultsResponse<SecurityQuestion> response = new InternalResultsResponse<SecurityQuestion>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_SECURITY_QUESTIONS, response);
		return response;
	}

	@Override
	public InternalResultsResponse<SecurityQuestion> insertSecurityQuestion(SecurityQuestion securityQuestion)
	{
		InternalResultsResponse<SecurityQuestion> response = new InternalResultsResponse<SecurityQuestion>();
		QATMyBatisDacHelper.doInsert(getSqlSession(), INSERT_SECURITY_QUESTION, securityQuestion, response);
		return response;
	}

}
