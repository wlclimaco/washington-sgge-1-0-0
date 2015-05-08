package com.prosperitasglobal.cbof.unittest.bai;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.bai.ICodeValueBAI;
import com.prosperitasglobal.cbof.dac.ICodeValueDAC;
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.CodeValueEnum;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.model.response.CodeValueResponse;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class CodeValueBAITest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/cbofbaiimpltest.xml",
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/pgsi-cbof-validators-context-test.xml",
"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"})
public class CodeValueBAITest extends AbstractJUnit4SpringContextTests
{
	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(CodeValueBAITest.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The code value bai. */
	private ICodeValueBAI codeValueBAI;

	/** The mock code value dac. */
	private ICodeValueDAC mockCodeValueDAC;

	@Before
	public void setUp()
	{
		Mockito.reset(getMockCodeValueDAC());
	}

	/**
	 * Gets the code value bai.
	 *
	 * @return the code value bai
	 */
	public ICodeValueBAI getCodeValueBAI()
	{
		return codeValueBAI;
	}

	/**
	 * Sets the code value bai.
	 *
	 * @param codeValueBAI the code value bai
	 */
	@Resource
	public void setCodeValueBAI(ICodeValueBAI codeValueBAI)
	{
		this.codeValueBAI = codeValueBAI;
	}

	/**
	 * Gets the mock code value dac.
	 *
	 * @return the mock code value dac
	 */
	public ICodeValueDAC getMockCodeValueDAC()
	{
		return mockCodeValueDAC;
	}

	/**
	 * Sets the mock code value dac.
	 *
	 * @param mockCodeValueDAC the mock code value dac
	 */
	@Resource
	public void setMockCodeValueDAC(ICodeValueDAC mockCodeValueDAC)
	{
		this.mockCodeValueDAC = mockCodeValueDAC;
	}

	/**
	 * Test fetch all code value by type.
	 */
	@Test
	public void testFetchAllCodeValueByType()
	{
		CodeValueRequest request = new CodeValueRequest();

		// First test sending no type. It will stop on the validation
		CodeValueResponse results = getCodeValueBAI().fetchAllCodeValueByType(request);
		CommonTestRoutines.assertResponse(results);

		// Second test sending type. It will work
		Mockito.when(getMockCodeValueDAC().fetchAllCodeValueByType(request)).thenReturn(
				new InternalResultsResponse<CodeValue>(Arrays.asList(new CodeValue())));
		request.setCodeValueType(CodeValueEnum.NAICS);

		CodeValueResponse response = getCodeValueBAI().fetchAllCodeValueByType(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockCodeValueDAC()).fetchAllCodeValueByType(request);

	}

	/**
	 * Test fetch all code value by type with error.
	 */
	@Test
	public void testFetchAllCodeValueByTypeWithError()
	{
		CodeValueRequest request = new CodeValueRequest();
		request.setCodeValueType(CodeValueEnum.NAICS);
		// Now test a DAC Exception
		Mockito.when(getMockCodeValueDAC().fetchAllCodeValueByType(request)).then(
				new Answer<InternalResultsResponse<CodeValue>>()
				{
					@Override
					public InternalResultsResponse<CodeValue> answer(InvocationOnMock invocation) throws Throwable
					{
						InternalResultsResponse<CodeValue> result = new InternalResultsResponse<CodeValue>();
						result.setStatus(Status.ExceptionError);
						return result;
					}
				});

		CodeValueResponse results = getCodeValueBAI().fetchAllCodeValueByType(request);

		Assert.assertNotNull(results);
		Assert.assertNull(results.getCodeValueList());

		Mockito.verify(getMockCodeValueDAC()).fetchAllCodeValueByType(request);

	}

	/**
	 * Test fetch all code value by type exception.
	 */
	@Test
	public void testFetchAllCodeValueByTypeException()
	{
		CodeValueRequest request = new CodeValueRequest();
		request.setCodeValueType(CodeValueEnum.NAICS);

		Mockito.when(getMockCodeValueDAC().fetchAllCodeValueByType(request)).thenThrow(new RuntimeException());

		CodeValueResponse results = getCodeValueBAI().fetchAllCodeValueByType(request);

		CommonTestRoutines.assertMessages(results, LOG, DEFAULT_EXCEPTION_MSG);
	}
}
