package com.prosperitasglobal.sendsolv.unittest.bac;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.IPayerBAC;
import com.prosperitasglobal.sendsolv.dac.IPayerDAC;
import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.request.PayerInquiryRequest;
import com.prosperitasglobal.sendsolv.unittest.bai.PayerBAIImplTest;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class PayerBACImplTest.
 */

@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bac/conf/payerbacimpltest.xml"})
@ActiveProfiles("sqlserver")
public class PayerBACImplTest extends AbstractJUnit4SpringContextTests
{
	/** The log. */
	protected static final Logger LOG = LoggerFactory.getLogger(PayerBAIImplTest.class);

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The payer bac. */
	private IPayerBAC payerBAC;

	/**
	 * Gets the payer bac.
	 *
	 * @return the payer bac
	 */
	public IPayerBAC getPayerBAC()
	{
		return payerBAC;
	}

	/** The mock payer dac. */
	private IPayerDAC mockPayerDAC;

	/**
	 * Sets the payer bac.
	 *
	 * @param payerBAC the payer bac
	 */
	@Resource
	public void setPayerBAC(IPayerBAC payerBAC)
	{
		this.payerBAC = payerBAC;
	}

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getMockPayerDAC());
	}

	/**
	 * Sets the mock payer dac.
	 *
	 * @param mockDAC the mock payer dac
	 */
	@Resource
	public void setMockPayerDAC(IPayerDAC mockDAC)
	{
		mockPayerDAC = mockDAC;
	}

	/**
	 * Gets the mock payer dac.
	 *
	 * @return the mock payer dac
	 */
	public IPayerDAC getMockPayerDAC()
	{
		return mockPayerDAC;
	}

	/**
	 * Test fetch payer by id.
	 */
	@Test
	public void testFetchPayerById()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		Integer id = request.getId();

		Mockito.when(getMockPayerDAC().fetchPayerById(id)).thenReturn(
				new InternalResultsResponse<Payer>(Arrays.asList(new Payer())));

		InternalResultsResponse<Payer> response = getPayerBAC().fetchPayerById(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getMockPayerDAC()).fetchPayerById(ONE);
	}

	/**
	 *
	 */
	@Test
	public void testFetchPayerByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		InternalResultsResponse<Payer> response = new InternalResultsResponse<Payer>();
		response.setStatus(Status.NoRowsFoundError);

		// test for bad return
		Mockito.when(getMockPayerDAC().fetchPayerById(ONE)).thenReturn(response);

		response = getPayerBAC().fetchPayerById(request);
		Assert.assertEquals(Status.NoRowsFoundError, response.getStatus());

		Mockito.verify(getMockPayerDAC()).fetchPayerById(ONE);
	}

	/**
	 *
	 */
	@Test
	public void testFetchPayerByIdNoPayerId()
	{
		FetchByIdRequest request = new FetchByIdRequest();

		InternalResultsResponse<Payer> response = new InternalResultsResponse<Payer>();
		response.setStatus(Status.NoRowsFoundError);

		// test for bad return
		Mockito.when(getMockPayerDAC().fetchPayerById(request.getId())).thenReturn(response);

		response = getPayerBAC().fetchPayerById(request);
		Assert.assertEquals(Status.NoRowsFoundError, response.getStatus());

		Mockito.verify(getMockPayerDAC()).fetchPayerById(request.getId());
	}

	/**
	 * Test fetch payer by request.
	 */
	@Test
	public void testFetchPayerByRequest()
	{
		PayerInquiryRequest request = new PayerInquiryRequest();

		Mockito.when(getMockPayerDAC().fetchPayerByRequest(request)).thenReturn(
				new InternalResultsResponse<Payer>(Arrays.asList(new Payer())));

		InternalResultsResponse<Payer> response = getPayerBAC().fetchPayerByRequest(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getMockPayerDAC()).fetchPayerByRequest(request);
	}

	/**
	 *
	 */
	@Test
	public void testFetchPayerByRequestException()
	{
		PayerInquiryRequest request = new PayerInquiryRequest();

		InternalResultsResponse<Payer> response = new InternalResultsResponse<Payer>();
		response.setStatus(Status.NoRowsFoundError);

		// test for bad return
		Mockito.when(getMockPayerDAC().fetchPayerByRequest(request)).thenReturn(response);

		response = getPayerBAC().fetchPayerByRequest(request);
		Assert.assertEquals(Status.NoRowsFoundError, response.getStatus());

		Mockito.verify(getMockPayerDAC()).fetchPayerByRequest(request);
	}
}
