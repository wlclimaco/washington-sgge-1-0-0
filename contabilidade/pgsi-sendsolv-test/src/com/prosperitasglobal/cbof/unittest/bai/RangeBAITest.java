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

import com.prosperitasglobal.cbof.bai.IRangeBAI;
import com.prosperitasglobal.cbof.dac.IRangeDAC;
import com.prosperitasglobal.cbof.model.Range;
import com.prosperitasglobal.cbof.model.request.RangeRequest;
import com.prosperitasglobal.cbof.model.response.RangeResponse;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class RangeBAITest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/cbofbaiimpltest.xml",
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/pgsi-cbof-validators-context-test.xml",
"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"})
public class RangeBAITest extends AbstractJUnit4SpringContextTests
{

	/** The range bai. */
	private IRangeBAI rangeBAI;

	private IRangeDAC mockRangeDAC;

	/**
	 * Gets the range bai.
	 *
	 * @return the range bai
	 */
	public IRangeBAI getRangeBAI()
	{
		return rangeBAI;
	}

	/**
	 * Sets the range bai.
	 *
	 * @param rangeBAI the range bai
	 */
	@Resource
	public void setRangeBAI(IRangeBAI rangeBAI)
	{
		this.rangeBAI = rangeBAI;
	}

	/**
	 * Gets the mock range dac.
	 *
	 * @return the mock range dac
	 */
	public IRangeDAC getMockRangeDAC()
	{
		return mockRangeDAC;
	}

	/**
	 * Sets the mock range dac.
	 *
	 * @param mockRangeDAC the mock range dac
	 */
	@Resource
	public void setMockRangeDAC(IRangeDAC mockRangeDAC)
	{
		this.mockRangeDAC = mockRangeDAC;
	}

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/**
	 * Test fetch all range.
	 */
	@Test
	public void testFetchAllRange()
	{
		RangeRequest request = new RangeRequest();
		Mockito.when(getMockRangeDAC().fetchAllRanges(request)).thenReturn(
				new InternalResultsResponse<Range>(Arrays.asList(new Range())));
		RangeResponse results = getRangeBAI().fetchAllRange(request);
		CommonTestRoutines.assertResponse(results);

		Mockito.verify(getMockRangeDAC()).fetchAllRanges(request);

	}

	/**
	 * Test fetch all range with error.
	 */
	@Test
	public void testFetchAllRangeWithError()
	{
		RangeRequest request = new RangeRequest();

		Mockito.when(getMockRangeDAC().fetchAllRanges(request)).then(
				new Answer<InternalResultsResponse<Range>>()
				{
					@Override
					public InternalResultsResponse<Range> answer(InvocationOnMock invocation) throws Throwable
					{
						InternalResultsResponse<Range> result = new InternalResultsResponse<Range>();
						result.setStatus(Status.ExceptionError);
						return result;
					}
				});
		RangeResponse results = getRangeBAI().fetchAllRange(request);
		Assert.assertNotNull(results);
		Assert.assertNull(results.getRangeList());

		Mockito.verify(getMockRangeDAC()).fetchAllRanges(request);

	}

	/**
	 * Test fetch all range exception.
	 */
	@Test
	public void testFetchAllRangeException()
	{
		RangeRequest request = new RangeRequest();

		Mockito.when(getMockRangeDAC().fetchAllRanges(request)).thenThrow(new RuntimeException());

		RangeResponse results = getRangeBAI().fetchAllRange(request);

		CommonTestRoutines.assertMessages(results, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test range by id.
	 */
	@Test
	public void testRangeById()
	{
		RangeRequest request = new RangeRequest();
		// First test sending no id. It will stop on the validation
		RangeResponse results = getRangeBAI().fetchRangeById(request);
		CommonTestRoutines.assertResponse(results);

		request.setId(1);
		Mockito.when(getMockRangeDAC().fetchRangeById(request)).thenReturn(
				new InternalResultsResponse<Range>(Arrays.asList(new Range())));
		results = getRangeBAI().fetchRangeById(request);
		CommonTestRoutines.assertResponse(results);

		Mockito.verify(getMockRangeDAC()).fetchRangeById(request);
	}

	/**
	 * Test range by id with error.
	 */
	@Test
	public void testRangeByIdWithError()
	{
		RangeRequest request = new RangeRequest();
		request.setId(1);
		Mockito.when(getMockRangeDAC().fetchRangeById(request)).then(
				new Answer<InternalResultsResponse<Range>>()
				{
					@Override
					public InternalResultsResponse<Range> answer(InvocationOnMock invocation) throws Throwable
					{
						InternalResultsResponse<Range> result = new InternalResultsResponse<Range>();
						result.setStatus(Status.ExceptionError);
						return result;
					}
				});
		RangeResponse results = getRangeBAI().fetchRangeById(request);
		Assert.assertNotNull(results);
		Assert.assertNull(results.getRangeList());

		Mockito.verify(getMockRangeDAC()).fetchRangeById(request);
	}
}
