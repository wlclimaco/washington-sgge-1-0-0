package com.prosperitasglobal.sendsolv.unittest.bai;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.ISecurityQuestionBAC;
import com.prosperitasglobal.sendsolv.bai.ISecurityQuestionBAI;
import com.prosperitasglobal.sendsolv.model.SecurityQuestion;
import com.prosperitasglobal.sendsolv.model.response.SecurityQuestionResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class SecurityQuestionBAIImplTest.
 */
@ContextConfiguration(locations =
{"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/securityquestionimpltest.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-shared-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"
})
public class SecurityQuestionBAIImplTest extends AbstractJUnit4SpringContextTests
{

	/** The mock security question bac. */
	private ISecurityQuestionBAC mockSecurityQuestionBAC;

	/** The security question bai. */
	private ISecurityQuestionBAI securityQuestionBAI;

	/** The Constant WHAT_IS_YOUR_FAVORITE_LANGUAGE_PROGRAM. */
	private static final String WHAT_IS_YOUR_FAVORITE_LANGUAGE_PROGRAM = "what.is.your.favorite.language.program";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(SecurityQuestionBAIImplTest.class);

	/**
	 * Execute before executing each @Test.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getMockSecurityQuestionBAC());
	}

	/**
	 * Gets the mock security question bac.
	 *
	 * @return the mockSecurityQuestionBAC
	 */
	public ISecurityQuestionBAC getMockSecurityQuestionBAC()
	{
		return mockSecurityQuestionBAC;
	}

	/**
	 * Sets the mock security question bac.
	 *
	 * @param mockSecurityQuestionBAC the mockSecurityQuestionBAC to set
	 */
	@Resource
	public void setMockSecurityQuestionBAC(ISecurityQuestionBAC mockSecurityQuestionBAC)
	{
		this.mockSecurityQuestionBAC = mockSecurityQuestionBAC;
	}

	/**
	 * Gets the security question bai.
	 *
	 * @return the securityQuestionBAI
	 */
	public ISecurityQuestionBAI getSecurityQuestionBAI()
	{
		return securityQuestionBAI;
	}

	/**
	 * Sets the security question bai.
	 *
	 * @param securityQuestionBAI the securityQuestionBAI to set
	 */
	@Resource
	public void setSecurityQuestionBAI(ISecurityQuestionBAI securityQuestionBAI)
	{
		this.securityQuestionBAI = securityQuestionBAI;
	}

	/**
	 * Test fetch all security questions.
	 */
	@Test
	public void testFetchAllSecurityQuestions()
	{
		SecurityQuestion securityQuestion = createSecurityQuestion(WHAT_IS_YOUR_FAVORITE_LANGUAGE_PROGRAM);

		InternalResultsResponse<SecurityQuestion> response = new InternalResultsResponse<SecurityQuestion>();
		response.addResult(securityQuestion);

		Mockito.when(getMockSecurityQuestionBAC().fetchAllSecurityQuestions()).thenReturn(response);

		SecurityQuestionResponse securityQuestionResponse = getSecurityQuestionBAI().fetchAllSecurityQuestions();

		CommonTestRoutines.assertResponse(securityQuestionResponse);
	}

	/**
	 * Test fetch all security questions with exception.
	 */
	@Test
	public void testFetchAllSecurityQuestionsWithException()
	{
		Mockito.when(getMockSecurityQuestionBAC().fetchAllSecurityQuestions()).thenThrow(new RuntimeException());

		SecurityQuestionResponse securityQuestionResponse = getSecurityQuestionBAI().fetchAllSecurityQuestions();
		CommonTestRoutines.assertMessages(securityQuestionResponse, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockSecurityQuestionBAC()).fetchAllSecurityQuestions();
	}

	/**
	 * Create a dummy security question.
	 *
	 * @param question the question
	 * @return the security question
	 */
	private SecurityQuestion createSecurityQuestion(String question)
	{
		SecurityQuestion securityQuestion = new SecurityQuestion();
		securityQuestion.setSecurityQuestionKey(question);

		return securityQuestion;
	}
}
