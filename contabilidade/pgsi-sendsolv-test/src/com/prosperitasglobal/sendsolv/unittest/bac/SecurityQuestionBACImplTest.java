package com.prosperitasglobal.sendsolv.unittest.bac;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.dac.ISecurityQuestionDAC;
import com.prosperitasglobal.sendsolv.model.SecurityQuestion;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class SecurityQuestionBACImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bac/conf/securityquestionbacimpltest.xml"})
public class SecurityQuestionBACImplTest extends AbstractJUnit4SpringContextTests
{

	/** The mock security question dac. */
	private ISecurityQuestionDAC mockSecurityQuestionDAC;

	/**
	 * Gets the mock security question dac.
	 *
	 * @return the mockSecurityQuestionDAC
	 */
	public ISecurityQuestionDAC getMockSecurityQuestionDAC()
	{
		return mockSecurityQuestionDAC;
	}

	/**
	 * Sets the mock security question dac.
	 *
	 * @param mockSecurityQuestionDAC the mockSecurityQuestionDAC to set
	 */
	@Resource
	public void setMockSecurityQuestionDAC(ISecurityQuestionDAC mockSecurityQuestionDAC)
	{
		this.mockSecurityQuestionDAC = mockSecurityQuestionDAC;
	}

	/**
	 * Test fetch all security questions.
	 */
	@Test
	public void testFetchAllSecurityQuestions()
	{
		Mockito.when(getMockSecurityQuestionDAC().fetchAllSecurityQuestions()).thenReturn(
				new InternalResultsResponse<SecurityQuestion>(Arrays.asList(new SecurityQuestion())));

		InternalResultsResponse<SecurityQuestion> response = getMockSecurityQuestionDAC().fetchAllSecurityQuestions();

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getMockSecurityQuestionDAC()).fetchAllSecurityQuestions();

	}

}
