package com.prosperitasglobal.sendsolv.unittest.bai;

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
import com.prosperitasglobal.sendsolv.bac.ICurrencyPurchaseBAC;
import com.prosperitasglobal.sendsolv.bai.ICurrencyPurchaseBAI;
import com.prosperitasglobal.sendsolv.model.CurrencyPurchase;
import com.prosperitasglobal.sendsolv.model.request.CurrencyPurchaseMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.CurrencyPurchaseResponse;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class CurrencyPurchaseBAIImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/currencypurchasebaiimpltest.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/sendsolv-dac-context.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-shared-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/pgsi-sendsolv-validators-context-test.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"})
@ActiveProfiles("sqlserver")
public class CurrencyPurchaseBAIImplTest extends AbstractJUnit4SpringContextTests
{
	/** The log. */
	protected static final Logger LOG = LoggerFactory.getLogger(CurrencyPurchaseBAIImplTest.class);

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant BAC_KEY. */
	private static final String BAC_KEY = "sendsolv.base.currencypurchasevalidator.currency.purchase.id.required";

	/**
	 * error response object to be used in several tests.
	 * The err response.
	 */
	private InternalResultsResponse<CurrencyPurchase> errResponse;

	/** The currencyPurchase bai. */
	private ICurrencyPurchaseBAI currencyPurchaseBAI;

	/** The mock currencyPurchase bac. */
	private ICurrencyPurchaseBAC mockCurrencyPurchaseBAC;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getMockCurrencyPurchaseBAC());
	}

	/**
	 * Gets the mock currencyPurchase bac.
	 *
	 * @return the mock currencyPurchase bac
	 */
	public ICurrencyPurchaseBAC getMockCurrencyPurchaseBAC()
	{
		return mockCurrencyPurchaseBAC;
	}

	/**
	 * Sets the mock currencyPurchase bac.
	 *
	 * @param mockBAC the mock currencyPurchase bac
	 */
	@Resource
	public void setMockCurrencyPurchaseBAC(ICurrencyPurchaseBAC mockBAC)
	{
		mockCurrencyPurchaseBAC = mockBAC;
	}

	/**
	 * Gets the currencyPurchase bai.
	 *
	 * @return the currencyPurchase bai
	 */
	public ICurrencyPurchaseBAI getCurrencyPurchaseBAI()
	{
		return currencyPurchaseBAI;
	}

	/**
	 * Sets the currencyPurchase bai.
	 *
	 * @param currencyPurchaseBAI the currencyPurchase bai
	 */
	@Resource
	public void setCurrencyPurchaseBAI(ICurrencyPurchaseBAI currencyPurchaseBAI)
	{
		this.currencyPurchaseBAI = currencyPurchaseBAI;
	}

	/**
	 * Gets the err response.
	 *
	 * @return the err response
	 */
	public InternalResultsResponse<CurrencyPurchase> getErrResponse()
	{
		return errResponse;
	}

	/**
	 * Sets the err response.
	 *
	 * @param errResponse the err response
	 */
	public void setErrResponse(InternalResultsResponse<CurrencyPurchase> errResponse)
	{
		this.errResponse = errResponse;
	}

	/**
	 * Creates the fetch response.
	 *
	 * @param currencyPurchase the currencyPurchase
	 * @return the internal results response< currencyPurchase>
	 */
	private InternalResultsResponse<CurrencyPurchase> createFetchResponse(CurrencyPurchase currencyPurchase)
	{
		InternalResultsResponse<CurrencyPurchase> response = new InternalResultsResponse<CurrencyPurchase>();
		response.addResult(currencyPurchase);

		return response;
	}

	/**
	 * The Constructor.
	 */
	public CurrencyPurchaseBAIImplTest()
	{
		// setup the error response object.
		setErrResponse(new InternalResultsResponse<CurrencyPurchase>());
		getErrResponse().addMessage(BAC_KEY, MessageSeverity.Error, MessageLevel.Field);
		getErrResponse().setStatus(Status.UnspecifiedError);
	}

	/**
	 * Test insert currencyPurchase.
	 */
	@Test
	public void testInsertCurrencyPurchase()
	{
		CurrencyPurchaseMaintenanceRequest request = new CurrencyPurchaseMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		CurrencyPurchase currencyPurchase = CommonTestRoutines.createDummyCurrencyPurchase();
		currencyPurchase.setModelAction(PersistanceActionEnum.INSERT);
		request.setCurrencyPurchase(currencyPurchase);

		InternalResultsResponse<CurrencyPurchase> internalResponse = new InternalResultsResponse<CurrencyPurchase>();
		internalResponse.addResult(currencyPurchase);

		Mockito.when(getMockCurrencyPurchaseBAC().insertCurrencyPurchase(request)).thenReturn(internalResponse);

		CurrencyPurchaseResponse response = getCurrencyPurchaseBAI().insertCurrencyPurchase(request);
		CommonTestRoutines.assertResponse(response);

		// ensures the mock's insertCurrencyPurchase was called 1 time.
		Mockito.verify(getMockCurrencyPurchaseBAC()).insertCurrencyPurchase(request);
	}

	/**
	 * Test insert currencyPurchase with exception.
	 */
	@Test
	public void testInsertCurrencyPurchaseWithException()
	{
		CurrencyPurchaseMaintenanceRequest request = new CurrencyPurchaseMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		CurrencyPurchase currencyPurchase = CommonTestRoutines.createDummyCurrencyPurchase();
		currencyPurchase.setModelAction(PersistanceActionEnum.INSERT);

		request.setCurrencyPurchase(currencyPurchase);

		// Throws an exception when mock's insertCurrencyPurchase is called.
		Mockito.when(getMockCurrencyPurchaseBAC().insertCurrencyPurchase(request)).thenThrow(new RuntimeException());

		CurrencyPurchaseResponse response = getCurrencyPurchaseBAI().insertCurrencyPurchase(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockCurrencyPurchaseBAC()).insertCurrencyPurchase(request);
	}

	/**
	 * Test update currencyPurchase without id.
	 */
	@Test
	public void testUpdateCurrencyPurchaseWithoutId()
	{
		CurrencyPurchaseMaintenanceRequest request = new CurrencyPurchaseMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		CurrencyPurchase currencyPurchase = CommonTestRoutines.createDummyCurrencyPurchase();
		currencyPurchase.setId(null);
		request.setCurrencyPurchase(currencyPurchase);
		currencyPurchase.setModelAction(PersistanceActionEnum.UPDATE);

		CurrencyPurchaseResponse response = getCurrencyPurchaseBAI().updateCurrencyPurchase(request);

		CommonTestRoutines.assertMessages(response, BAC_KEY);

		Mockito.verify(getMockCurrencyPurchaseBAC(), Mockito.never()).insertCurrencyPurchase(request);

	}

	/**
	 * Test update currencyPurchase.
	 */
	@Test
	public void testUpdateCurrencyPurchase()
	{
		CurrencyPurchaseMaintenanceRequest request = new CurrencyPurchaseMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		CurrencyPurchase currencyPurchase = CommonTestRoutines.createDummyCurrencyPurchase();
		currencyPurchase.setId(1);
		currencyPurchase.setModelAction(PersistanceActionEnum.UPDATE);

		request.setCurrencyPurchase(currencyPurchase);

		Mockito.when(getMockCurrencyPurchaseBAC().updateCurrencyPurchase(request)).thenReturn(
				new InternalResultsResponse<CurrencyPurchase>());

		CurrencyPurchaseResponse response = getCurrencyPurchaseBAI().updateCurrencyPurchase(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockCurrencyPurchaseBAC()).updateCurrencyPurchase(request);
	}

	/**
	 * Test update currencyPurchase exception.
	 */
	@Test
	public void testUpdateCurrencyPurchaseException()
	{
		CurrencyPurchaseMaintenanceRequest request = new CurrencyPurchaseMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		CurrencyPurchase currencyPurchase = CommonTestRoutines.createDummyCurrencyPurchase();
		currencyPurchase.setId(THREE);
		currencyPurchase.setModelAction(PersistanceActionEnum.UPDATE);

		request.setCurrencyPurchase(currencyPurchase);

		Mockito.when(getMockCurrencyPurchaseBAC().updateCurrencyPurchase(request)).thenThrow(new RuntimeException());

		CurrencyPurchaseResponse response = getCurrencyPurchaseBAI().updateCurrencyPurchase(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockCurrencyPurchaseBAC()).updateCurrencyPurchase(request);
	}

	/**
	 * Test delete currencyPurchase.
	 */
	@Test
	public void testDeleteCurrencyPurchase()
	{
		CurrencyPurchaseMaintenanceRequest request = new CurrencyPurchaseMaintenanceRequest();
		CurrencyPurchase currencyPurchase = new CurrencyPurchase();
		currencyPurchase.setId(ONE);
		request.setCurrencyPurchase(currencyPurchase);

		Mockito.when(getMockCurrencyPurchaseBAC().deleteCurrencyPurchase(request)).thenReturn(
				new InternalResultsResponse<CurrencyPurchase>());

		CurrencyPurchaseResponse response = getCurrencyPurchaseBAI().deleteCurrencyPurchase(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockCurrencyPurchaseBAC()).deleteCurrencyPurchase(request);
	}

	/**
	 * Test delete currencyPurchase exception.
	 */
	@Test
	public void testDeleteCurrencyPurchaseException()
	{
		CurrencyPurchaseMaintenanceRequest request = new CurrencyPurchaseMaintenanceRequest();
		CurrencyPurchase currencyPurchase = new CurrencyPurchase();
		currencyPurchase.setId(THREE);
		request.setCurrencyPurchase(currencyPurchase);

		Mockito.when(getMockCurrencyPurchaseBAC().deleteCurrencyPurchase(request)).thenThrow(new RuntimeException());

		CurrencyPurchaseResponse response = getCurrencyPurchaseBAI().deleteCurrencyPurchase(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockCurrencyPurchaseBAC()).deleteCurrencyPurchase(request);
	}

	/**
	 * Test delete currencyPurchase failure.
	 */
	@Test
	public void testDeleteCurrencyPurchaseFailure()
	{
		CurrencyPurchaseMaintenanceRequest request = new CurrencyPurchaseMaintenanceRequest();

		CurrencyPurchase currencyPurchase = new CurrencyPurchase();
		currencyPurchase.setId(THREE);
		request.setCurrencyPurchase(currencyPurchase);

		// returns the error object when deleteCurrencyPurchase is called.
		Mockito.when(getMockCurrencyPurchaseBAC().deleteCurrencyPurchase(request)).thenReturn(getErrResponse());

		CurrencyPurchaseResponse response = getCurrencyPurchaseBAI().deleteCurrencyPurchase(request);

		CommonTestRoutines.assertMessages(response, BAC_KEY);

		Mockito.verify(getMockCurrencyPurchaseBAC()).deleteCurrencyPurchase(request);
	}

	/**
	 * Test fetch currencyPurchase by id.
	 */
	@Test
	public void testFetchCurrencyPurchaseById()
	{
		CurrencyPurchase currencyPurchase = CommonTestRoutines.createDummyCurrencyPurchase();
		FetchByIdRequest request = new FetchByIdRequest(currencyPurchase.getId());
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockCurrencyPurchaseBAC().fetchCurrencyPurchaseById(request)).thenReturn(
				createFetchResponse(currencyPurchase));

		CurrencyPurchaseResponse response = getCurrencyPurchaseBAI().fetchCurrencyPurchaseById(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockCurrencyPurchaseBAC()).fetchCurrencyPurchaseById(request);
	}

	/**
	 * Test fetch currencyPurchase by id exception.
	 */
	@Test
	public void testFetchCurrencyPurchaseByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(THREE);

		Mockito.when(getMockCurrencyPurchaseBAC().fetchCurrencyPurchaseById(request)).thenThrow(new RuntimeException());

		CurrencyPurchaseResponse response = getCurrencyPurchaseBAI().fetchCurrencyPurchaseById(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockCurrencyPurchaseBAC()).fetchCurrencyPurchaseById(request);
	}

	/**
	 * Test fetch currencyPurchase by id no currencyPurchase id.
	 */
	@Test
	public void testFetchCurrencyPurchaseByIdNoCurrencyPurchaseId()
	{
		FetchByIdRequest request = new FetchByIdRequest();
		CurrencyPurchaseResponse response = getCurrencyPurchaseBAI().fetchCurrencyPurchaseById(request);

		Assert.assertEquals(1, response.getMessageList().size());
		Assert.assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());

		// Ensures the mock's fetchCurrencyPurchaseById was not called, due to validation error.
		Mockito.verify(getMockCurrencyPurchaseBAC(), Mockito.never()).fetchCurrencyPurchaseById(request);
	}

}
