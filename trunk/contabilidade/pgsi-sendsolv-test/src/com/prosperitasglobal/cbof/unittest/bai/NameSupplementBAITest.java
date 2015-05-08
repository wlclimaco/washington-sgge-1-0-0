package com.prosperitasglobal.cbof.unittest.bai;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.bai.INameSupplementBAI;
import com.prosperitasglobal.cbof.dac.ICodeValueDAC;
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.model.response.CodeValueResponse;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class NameSupplementBAITest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/cbofbaiimpltest.xml",
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/pgsi-cbof-validators-context-test.xml",
"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"})
public class NameSupplementBAITest extends AbstractJUnit4SpringContextTests
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The name supplement bai. */
	private INameSupplementBAI nameSupplementBAI;

	/** The mock code value dac. */
	private ICodeValueDAC mockCodeValueDAC;;

	/**
	 * Gets the name supplement bai.
	 *
	 * @return the name supplement bai
	 */
	public INameSupplementBAI getNameSupplementBAI()
	{
		return nameSupplementBAI;
	}

	/**
	 * Sets the name supplement bai.
	 *
	 * @param nameSupplementBAI the name supplement bai
	 */
	@Resource
	public void setNameSupplementBAI(INameSupplementBAI nameSupplementBAI)
	{
		this.nameSupplementBAI = nameSupplementBAI;
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
	 * Test fetch all prefix.
	 */
	@Test
	public void testFetchAllPrefix()
	{
		CodeValueRequest request = new CodeValueRequest();

		Mockito.when(getMockCodeValueDAC().fetchAllCodeValueByType(request)).thenReturn(
				new InternalResultsResponse<CodeValue>(Arrays.asList(new CodeValue())));

		CodeValueResponse results = getNameSupplementBAI().fetchAllPrefix(request);
		CommonTestRoutines.assertResponse(results);

		Mockito.verify(getMockCodeValueDAC()).fetchAllCodeValueByType(request);
	}

	/**
	 * Test fetch all prefix with error.
	 */
	@Test
	public void testFetchAllPrefixWithError()
	{
		CodeValueRequest request = new CodeValueRequest();

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

		CodeValueResponse results = getNameSupplementBAI().fetchAllPrefix(request);
		Assert.assertNotNull(results);
		Assert.assertNull(results.getCodeValueList());

		Mockito.verify(getMockCodeValueDAC()).fetchAllCodeValueByType(request);

	}

	/**
	 * Test fetch all prefix exception.
	 */
	@Test
	public void testFetchAllPrefixException()
	{
		CodeValueRequest request = new CodeValueRequest();

		Mockito.when(getMockCodeValueDAC().fetchAllCodeValueByType(request)).thenThrow(new RuntimeException());

		CodeValueResponse results = getNameSupplementBAI().fetchAllPrefix(request);

		CommonTestRoutines.assertMessages(results, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch all suffix.
	 */
	@Test
	public void testFetchAllSuffix()
	{
		CodeValueRequest request = new CodeValueRequest();

		Mockito.when(getMockCodeValueDAC().fetchAllCodeValueByType(request)).thenReturn(
				new InternalResultsResponse<CodeValue>(Arrays.asList(new CodeValue())));

		CodeValueResponse results = getNameSupplementBAI().fetchAllSuffix(request);
		CommonTestRoutines.assertResponse(results);

		Mockito.verify(getMockCodeValueDAC()).fetchAllCodeValueByType(request);
	}

	/**
	 * Test fetch all suffix with error.
	 */
	@Test
	public void testFetchAllSuffixWithError()
	{
		CodeValueRequest request = new CodeValueRequest();

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

		CodeValueResponse results = getNameSupplementBAI().fetchAllSuffix(request);
		Assert.assertNotNull(results);
		Assert.assertNull(results.getCodeValueList());

		Mockito.verify(getMockCodeValueDAC()).fetchAllCodeValueByType(request);

	}

	/**
	 * Test fetch all suffix exception.
	 */
	@Test
	public void testFetchAllSuffixException()
	{
		CodeValueRequest request = new CodeValueRequest();

		Mockito.when(getMockCodeValueDAC().fetchAllCodeValueByType(request)).thenThrow(new RuntimeException());

		CodeValueResponse results = getNameSupplementBAI().fetchAllSuffix(request);

		CommonTestRoutines.assertMessages(results, DEFAULT_EXCEPTION_MSG);
	}

}
