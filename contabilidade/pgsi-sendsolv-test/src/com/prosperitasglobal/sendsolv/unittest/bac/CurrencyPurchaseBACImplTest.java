package com.prosperitasglobal.sendsolv.unittest.bac;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.ICurrencyPurchaseBAC;
import com.prosperitasglobal.sendsolv.dac.ICurrencyPurchaseDAC;
import com.prosperitasglobal.sendsolv.model.CurrencyPurchase;
import com.prosperitasglobal.sendsolv.model.request.CurrencyPurchaseMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class CurrencyPurchaseBACImplTest.
 */

@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bac/conf/currencypurchasebacimpltest.xml"})
public class CurrencyPurchaseBACImplTest extends AbstractJUnit4SpringContextTests
{

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The mock currencyPurchase dac. */
	private ICurrencyPurchaseDAC mockCurrencyPurchaseDAC;

	/** The currencyPurchase bac. */
	private ICurrencyPurchaseBAC currencyPurchaseBAC;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getMockCurrencyPurchaseDAC());
	}

	/**
	 * Gets the currencyPurchase bac.
	 *
	 * @return the currencyPurchase bac
	 */
	public ICurrencyPurchaseBAC getCurrencyPurchaseBAC()
	{
		return currencyPurchaseBAC;
	}

	/**
	 * Sets the currencyPurchase bac.
	 *
	 * @param currencyPurchaseBAC the currencyPurchase bac
	 */
	@Resource
	public void setCurrencyPurchaseBAC(ICurrencyPurchaseBAC currencyPurchaseBAC)
	{
		this.currencyPurchaseBAC = currencyPurchaseBAC;
	}

	/**
	 * Gets the mock currencyPurchase dac.
	 *
	 * @return the mock currencyPurchase dac
	 */
	public ICurrencyPurchaseDAC getMockCurrencyPurchaseDAC()
	{
		return mockCurrencyPurchaseDAC;
	}

	/**
	 * Sets the mock currencyPurchase dac.
	 *
	 * @param mockDAC the mock currencyPurchase dac
	 */
	@Resource
	public void setMockCurrencyPurchaseDAC(ICurrencyPurchaseDAC mockDAC)
	{
		mockCurrencyPurchaseDAC = mockDAC;
	}

	/**
	 * Test insert currencyPurchase.
	 */
	@Test
	public void testInsertCurrencyPurchase()
	{
		CurrencyPurchaseMaintenanceRequest request = new CurrencyPurchaseMaintenanceRequest();

		CurrencyPurchase currencyPurchase = CommonTestRoutines.createDummyCurrencyPurchase();
		request.setCurrencyPurchase(currencyPurchase);

		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockCurrencyPurchaseDAC().insertCurrencyPurchase(currencyPurchase)).thenReturn(
				new InternalResultsResponse<CurrencyPurchase>(Arrays.asList(new CurrencyPurchase())));

		InternalResponse response = getCurrencyPurchaseBAC().insertCurrencyPurchase(request);

		CommonTestRoutines.assertResponse(response);

		// ensures the insertCurrencyPurchase method on the mock was called 1 time.
		Mockito.verify(getMockCurrencyPurchaseDAC()).insertCurrencyPurchase(currencyPurchase);

	}

	/**
	 * Test insert currencyPurchase with error.
	 */
	@Test
	public void testInsertCurrencyPurchaseWithError()
	{
		CurrencyPurchaseMaintenanceRequest request = new CurrencyPurchaseMaintenanceRequest();

		CurrencyPurchase currencyPurchase = CommonTestRoutines.createDummyCurrencyPurchase();
		request.setCurrencyPurchase(currencyPurchase);

		CommonTestRoutines.createDummyUserContext(request);

		InternalResultsResponse<CurrencyPurchase> response = new InternalResultsResponse<CurrencyPurchase>();
		response.setStatus(Status.NoRowsInsertedError);

		Mockito.when(getMockCurrencyPurchaseDAC().insertCurrencyPurchase(currencyPurchase)).thenReturn(response);

		InternalResponse results = getCurrencyPurchaseBAC().insertCurrencyPurchase(request);

		Assert.assertEquals(Status.NoRowsInsertedError, results.getStatus());
		Mockito.verify(getMockCurrencyPurchaseDAC()).insertCurrencyPurchase(currencyPurchase);

	}

	/**
	 * Test update currencyPurchase.
	 */
	@Test
	public void testUpdateCurrencyPurchase()
	{
		CurrencyPurchase currencyPurchase = CommonTestRoutines.createDummyCurrencyPurchase();

		CurrencyPurchaseMaintenanceRequest request = new CurrencyPurchaseMaintenanceRequest();
		request.setCurrencyPurchase(currencyPurchase);

		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockCurrencyPurchaseDAC().updateCurrencyPurchase(currencyPurchase)).thenReturn(
				new InternalResultsResponse<CurrencyPurchase>(Arrays.asList(new CurrencyPurchase())));

		InternalResponse response = getCurrencyPurchaseBAC().updateCurrencyPurchase(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockCurrencyPurchaseDAC()).updateCurrencyPurchase(currencyPurchase);
	}

	/**
	 * Test update currencyPurchase with error.
	 */
	@Test
	public void testUpdateCurrencyPurchaseWithError()
	{
		CurrencyPurchase currencyPurchase = CommonTestRoutines.createDummyCurrencyPurchase();

		CurrencyPurchaseMaintenanceRequest request = new CurrencyPurchaseMaintenanceRequest();
		request.setCurrencyPurchase(currencyPurchase);

		CommonTestRoutines.createDummyUserContext(request);

		InternalResultsResponse<CurrencyPurchase> response = new InternalResultsResponse<CurrencyPurchase>();
		response.setStatus(Status.NoRowsUpdatedError);

		Mockito.when(getMockCurrencyPurchaseDAC().updateCurrencyPurchase(currencyPurchase)).thenReturn(response);

		InternalResponse results = getCurrencyPurchaseBAC().updateCurrencyPurchase(request);

		Assert.assertEquals(Status.NoRowsUpdatedError, results.getStatus());
		Mockito.verify(getMockCurrencyPurchaseDAC()).updateCurrencyPurchase(currencyPurchase);
	}

	/**
	 * Test delete currencyPurchase.
	 */
	@Test
	public void testDeleteCurrencyPurchase()
	{
		CurrencyPurchase currencyPurchase = new CurrencyPurchase();
		CurrencyPurchaseMaintenanceRequest request = new CurrencyPurchaseMaintenanceRequest();
		request.setCurrencyPurchase(currencyPurchase);

		Mockito.when(getMockCurrencyPurchaseDAC().deleteCurrencyPurchase(currencyPurchase)).thenReturn(
				new InternalResponse());

		InternalResponse response = getCurrencyPurchaseBAC().deleteCurrencyPurchase(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockCurrencyPurchaseDAC()).deleteCurrencyPurchase(currencyPurchase);
	}

	/**
	 * Test delete currencyPurchase with error.
	 */
	@Test
	public void testDeleteCurrencyPurchaseWithError()
	{
		CurrencyPurchase currencyPurchase = new CurrencyPurchase();
		CurrencyPurchaseMaintenanceRequest request = new CurrencyPurchaseMaintenanceRequest();
		request.setCurrencyPurchase(currencyPurchase);

		Mockito.when(getMockCurrencyPurchaseDAC().deleteCurrencyPurchase(currencyPurchase)).thenReturn(
				new InternalResponse());

		Mockito.when(getMockCurrencyPurchaseDAC().deleteCurrencyPurchase(currencyPurchase)).thenReturn(
				new InternalResponse(Status.NoRowsRemovedError));

		InternalResponse results = getCurrencyPurchaseBAC().deleteCurrencyPurchase(request);

		Assert.assertEquals(Status.NoRowsRemovedError, results.getStatus());
		Mockito.verify(getMockCurrencyPurchaseDAC()).deleteCurrencyPurchase(currencyPurchase);
	}

	/**
	 * Test fetch money transfer by id.
	 */
	@Test
	public void testFetchCurrencyPurchaseById()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		Mockito.when(getMockCurrencyPurchaseDAC().fetchCurrencyPurchaseById(request.getId())).thenReturn(
				new InternalResultsResponse<CurrencyPurchase>(Arrays.asList(new CurrencyPurchase())));

		InternalResultsResponse<CurrencyPurchase> response =
				getCurrencyPurchaseBAC().fetchCurrencyPurchaseById(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getMockCurrencyPurchaseDAC()).fetchCurrencyPurchaseById(request.getId());
	}

	/**
	 * Test fetch money transfer by id with error.
	 */
	@Test
	public void testFetchCurrencyPurchaseByIdWithError()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		InternalResultsResponse<CurrencyPurchase> response = new InternalResultsResponse<CurrencyPurchase>();
		response.setStatus(Status.NoRowsFoundError);

		Mockito.when(getMockCurrencyPurchaseDAC().fetchCurrencyPurchaseById(request.getId())).thenReturn(response);

		response = getCurrencyPurchaseBAC().fetchCurrencyPurchaseById(request);

		Assert.assertEquals(Status.NoRowsFoundError, response.getStatus());

		Mockito.verify(getMockCurrencyPurchaseDAC()).fetchCurrencyPurchaseById(request.getId());
	}

}
