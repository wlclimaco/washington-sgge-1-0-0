package com.prosperitasglobal.sendsolv.unittest.dac.mybatis;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.dac.ISecurityQuestionDAC;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.PersonSecurityAnswer;
import com.prosperitasglobal.sendsolv.model.SecurityQuestion;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class SecurityQuestionDACTest.
 */
public class SecurityQuestionDACTest extends AbstractTestBaseDAC
{

	/** The Constant DELPHI. */
	private static final String DELPHI = "DELPHI";

	/** The Constant JAVA_SCRIPT. */
	private static final String JAVA_SCRIPT = "JAVA SCRIPT";

	/** The Constant JAVA. */
	private static final String JAVA = "JAVA";

	/** The Constant WHAT_IS_YOUR_FAVORITE_LANGUAGE_PROGRAM. */
	private static final String WHAT_IS_YOUR_FAVORITE_LANGUAGE_PROGRAM = "what.is.your.favorite.language.program";

	/** The security question dac. */
	private ISecurityQuestionDAC securityQuestionDAC;

	/** The security question. */
	private SecurityQuestion securityQuestion;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		executeSqlScript("com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/deleteSecurityQuestion.sql", false);

		// before starting to execute the tests, create and include in the database a new security question that will be
		// used in others test methods.
		setSecurityQuestion(insertSecurityQuestion(WHAT_IS_YOUR_FAVORITE_LANGUAGE_PROGRAM));
	}

	/**
	 * Gets the security question dac.
	 *
	 * @return the securityQuestionDAC
	 */
	@Override
	public ISecurityQuestionDAC getSecurityQuestionDAC()
	{
		return securityQuestionDAC;
	}

	/**
	 * Sets the security question dac.
	 *
	 * @param securityQuestionDAC the securityQuestionDAC to set
	 */
	@Override
	@Resource
	public void setSecurityQuestionDAC(ISecurityQuestionDAC securityQuestionDAC)
	{
		this.securityQuestionDAC = securityQuestionDAC;
	}

	/**
	 * Gets the security question.
	 *
	 * @return the securityQuestion
	 */
	public SecurityQuestion getSecurityQuestion()
	{
		return securityQuestion;
	}

	/**
	 * Sets the security question.
	 *
	 * @param securityQuestion the securityQuestion to set
	 */
	public void setSecurityQuestion(SecurityQuestion securityQuestion)
	{
		this.securityQuestion = securityQuestion;
	}

	/**
	 * Test fetch success all security questions.
	 */
	@Test
	public void testFetchAllSecurityQuestions()
	{
		InternalResultsResponse<SecurityQuestion> response = getSecurityQuestionDAC().fetchAllSecurityQuestions();
		CommonTestRoutines.assertResponse(response);

		// -- should have only one result
		Assert.assertTrue(response.getFirstResult().getSecurityQuestionKey()
				.equals(WHAT_IS_YOUR_FAVORITE_LANGUAGE_PROGRAM));
	}

	/**
	 * Test insert person security answer.
	 */
	@Test
	public void testInsertAndFetchPersonSecurityAnswer()
	{
		// -- insert a new member to perform this test
		Member member = insertMember(0);

		// -- test the insert security answer method
		InternalResultsResponse<PersonSecurityAnswer> response =
				getSecurityQuestionDAC().insertPersonSecurityAnswer(
						createPersonSecurityAnswer(JAVA, getSecurityQuestion(), member));
		CommonTestRoutines.assertResponse(response);

		// -- test the fetch security answer by person id
		response = getSecurityQuestionDAC().fetchPersonSecurityAnswersByPersonId(member.getId());
		CommonTestRoutines.assertResponse(response);

		Assert.assertTrue("The security answer should be JAVA", response.getFirstResult().getAnswerText().equals(JAVA));
	}

	/**
	 * Test update security answer.
	 */
	@Test
	public void testUpdateSecurityAnswer()
	{
		// -- insert a new member to perform this test
		Member member = insertMember(0);

		PersonSecurityAnswer personSecurityAnswer =
				createPersonSecurityAnswer(JAVA_SCRIPT, getSecurityQuestion(), member);

		// -- test the insert security answer method
		InternalResultsResponse<PersonSecurityAnswer> response =
				getSecurityQuestionDAC().insertPersonSecurityAnswer(personSecurityAnswer);
		CommonTestRoutines.assertResponse(response);

		// -- test the update
		personSecurityAnswer.setAnswerText(DELPHI);
		response = getSecurityQuestionDAC().updatePersonSecurityAnswer(personSecurityAnswer);
		CommonTestRoutines.assertResponse(response);

		// -- verify if the answer was updated
		response = getSecurityQuestionDAC().fetchPersonSecurityAnswersByPersonId(member.getId());
		CommonTestRoutines.assertResponse(response);

		Assert.assertTrue("The security answer should be DELPHI",
				response.getFirstResult().getAnswerText().equals(DELPHI));
	}

}
