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

import com.prosperitasglobal.cbof.bai.IIndustryClassificationBAI;
import com.prosperitasglobal.cbof.dac.ICodeValueDAC;
import com.prosperitasglobal.cbof.dac.IIndustryClassificationDAC;
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.model.response.CodeValueResponse;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class IndustryClassificationBAITest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/cbofbaiimpltest.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/pgsi-cbof-validators-context-test.xml"})
public class IndustryClassificationBAITest extends AbstractJUnit4SpringContextTests
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant ONE_HUNDRED_ELEVEN. */
	private static final String ONE_HUNDRED_ELEVEN = "111";

	/** The Constant USA. */
	private static final String USA = "USA";

	/** The industry classification bai. */
	private IIndustryClassificationBAI industryClassificationBAI;

	/** The mock industry classification dac. */
	private IIndustryClassificationDAC mockIndustryClassificationDAC;

	/** The mock code value dac. */
	private ICodeValueDAC mockCodeValueDAC;

	/**
	 * Gets the industry classification bai.
	 *
	 * @return the industry classification bai
	 */
	public IIndustryClassificationBAI getIndustryClassificationBAI()
	{
		return industryClassificationBAI;
	}

	/**
	 * Sets the industry classification bai.
	 *
	 * @param industryClassificationBAI the industry classification bai
	 */
	@Resource
	public void setIndustryClassificationBAI(IIndustryClassificationBAI industryClassificationBAI)
	{
		this.industryClassificationBAI = industryClassificationBAI;
	}

	/**
	 * Gets the mock industry classification dac.
	 *
	 * @return the mock industry classification dac
	 */
	public IIndustryClassificationDAC getMockIndustryClassificationDAC()
	{
		return mockIndustryClassificationDAC;
	}

	/**
	 * Sets the mock industry classification dac.
	 *
	 * @param mockIndustryClassificationDAC the mock industry classification dac
	 */
	@Resource
	public void setMockIndustryClassificationDAC(IIndustryClassificationDAC mockIndustryClassificationDAC)
	{
		this.mockIndustryClassificationDAC = mockIndustryClassificationDAC;
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
	 * Test fetch all naics.
	 */
	@Test
	public void testFetchAllNAICS()
	{
		CodeValueRequest request = new CodeValueRequest();

		Mockito.when(getMockCodeValueDAC().fetchAllCodeValueByType(request)).thenReturn(
				new InternalResultsResponse<CodeValue>(Arrays.asList(new CodeValue())));

		CodeValueResponse results = getIndustryClassificationBAI().fetchAllNAICS(request);
		CommonTestRoutines.assertResponse(results);
		Mockito.verify(getMockCodeValueDAC()).fetchAllCodeValueByType(request);
	}

	/**
	 * Test fetch all naics with error.
	 */
	@Test
	public void testFetchAllNAICSWithError()
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
		CodeValueResponse results = getIndustryClassificationBAI().fetchAllNAICS(request);
		Assert.assertNotNull(results);
		Assert.assertNull(results.getCodeValueList());

		Mockito.verify(getMockCodeValueDAC()).fetchAllCodeValueByType(request);

	}

	/**
	 * Test fetch all naics exception.
	 */
	@Test
	public void testFetchAllNAICSException()
	{
		CodeValueRequest request = new CodeValueRequest();

		Mockito.when(getMockCodeValueDAC().fetchAllCodeValueByType(request)).thenThrow(new RuntimeException());

		CodeValueResponse results = getIndustryClassificationBAI().fetchAllNAICS(request);

		CommonTestRoutines.assertMessages(results, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch all sic.
	 */
	@Test
	public void testFetchAllSIC()
	{
		CodeValueRequest request = new CodeValueRequest();

		Mockito.when(getMockCodeValueDAC().fetchAllCodeValueByType(request)).thenReturn(
				new InternalResultsResponse<CodeValue>(Arrays.asList(new CodeValue())));

		CodeValueResponse results = getIndustryClassificationBAI().fetchAllSIC(request);
		CommonTestRoutines.assertResponse(results);

		Mockito.verify(getMockCodeValueDAC()).fetchAllCodeValueByType(request);

	}

	/**
	 * Test fetch all sic with error.
	 */
	@Test
	public void testFetchAllSICWithError()
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
		CodeValueResponse results = getIndustryClassificationBAI().fetchAllSIC(request);
		Assert.assertNotNull(results);
		Assert.assertNull(results.getCodeValueList());

		Mockito.verify(getMockCodeValueDAC()).fetchAllCodeValueByType(request);
	}

	/**
	 * Test fetch sics by code.
	 */
	@Test
	public void testFetchSICSByCode()
	{
		CodeValueRequest request = new CodeValueRequest();
		CodeValueResponse results = getIndustryClassificationBAI().fetchSICByCode(request);
		CommonTestRoutines.assertResponse(results);

		request.setCode(ONE_HUNDRED_ELEVEN);
		Mockito.when(getMockIndustryClassificationDAC().fetchCodeValueByCode(request)).thenReturn(
				new InternalResultsResponse<CodeValue>(Arrays.asList(new CodeValue())));
		results = getIndustryClassificationBAI().fetchSICByCode(request);
		CommonTestRoutines.assertResponse(results);

		Mockito.verify(getMockIndustryClassificationDAC()).fetchCodeValueByCode(request);
	}

	/**
	 * Test fetch sics by code with error.
	 */
	@Test
	public void testFetchSICSByCodeWithError()
	{
		CodeValueRequest request = new CodeValueRequest();
		request.setCode(USA);

		Mockito.when(getMockIndustryClassificationDAC().fetchCodeValueByCode(request)).then(
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

		CodeValueResponse result = getIndustryClassificationBAI().fetchSICByCode(request);
		Assert.assertNotNull(result);
		Assert.assertNull(result.getCodeValueList());

		Mockito.verify(getMockIndustryClassificationDAC()).fetchCodeValueByCode(request);
	}

	/**
	 * Test fetch naics by code.
	 */
	@Test
	public void testFetchNAICSByCode()
	{

		CodeValueRequest request = new CodeValueRequest();
		CodeValueResponse results = getIndustryClassificationBAI().fetchNAICSByCode(request);
		CommonTestRoutines.assertResponse(results);

		request.setCode(ONE_HUNDRED_ELEVEN);
		Mockito.when(getMockIndustryClassificationDAC().fetchCodeValueByCode(request)).thenReturn(
				new InternalResultsResponse<CodeValue>(Arrays.asList(new CodeValue())));
		results = getIndustryClassificationBAI().fetchNAICSByCode(request);
		CommonTestRoutines.assertResponse(results);

		Mockito.verify(getMockIndustryClassificationDAC()).fetchCodeValueByCode(request);
	}

	/**
	 * Test fetch naics by code with error.
	 */
	@Test
	public void testFetchNAICSByCodeWithError()
	{
		CodeValueRequest request = new CodeValueRequest();
		request.setCode(USA);

		Mockito.when(getMockIndustryClassificationDAC().fetchCodeValueByCode(request)).then(
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
		CodeValueResponse results = getIndustryClassificationBAI().fetchNAICSByCode(request);
		Assert.assertNotNull(results);
		Assert.assertNull(results.getCodeValueList());

		Mockito.verify(getMockIndustryClassificationDAC()).fetchCodeValueByCode(request);
	}
}
