package com.prosperitasglobal.sendsolv.unittest.bai;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.IPayerBAC;
import com.prosperitasglobal.sendsolv.bai.IPayerBAI;
import com.prosperitasglobal.sendsolv.bai.impl.PayerBAIImpl;
import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.request.PayerInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.PayerResponse;
import com.prosperitasglobal.sendsolv.validation.PagedInquiryRequestValidator;
import com.prosperitasglobal.sendsolv.validation.PayerInquiryRequestValidator;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class PayerBAIImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/pgsi-sendsolv-validators-context-test.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/sendsolv-dac-context.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-shared-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/payerbaiimpltest.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"
		// this last xml file points to message files and is local to this project.
		})
@ActiveProfiles("sqlserver")
public class PayerBAIImplTest extends AbstractJUnit4SpringContextTests
{
	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(PayerBAIImplTest.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";
	/** The Constant PROSPERITASGLOBAL_BASE_PAYERVALIDATOR_COUNTRY_CODE_REQUIRED. */

	/** The Constant PROSPERITASGLOBAL_BASE_PAYERVALIDATOR_PAYER_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PAYERVALIDATOR_PAYER_ID_REQUIRED =
			"sendsolv.base.payervalidator.payerid.required";

	/** The Constant BAC_KEY. */
	private static final String BAC_KEY = "sendsolv.base.payervalidator.id.required";

	// error response object to be used in several tests.
	/** The err response. */
	private InternalResultsResponse<Payer> errResponse;

	/** The location bai. */
	private IPayerBAI payerBAI;

	/** The mock location bac. */
	private IPayerBAC mockPayerBAC;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getMockPayerBAC());
	}

	/**
	 * Gets the mock location bac.
	 *
	 * @return the mock location bac
	 */
	public IPayerBAC getMockPayerBAC()
	{
		return mockPayerBAC;
	}

	/**
	 * Sets the mock location bac.
	 *
	 * @param payerBAC the mock location bac
	 */
	@Resource
	public void setMockPayerBAC(IPayerBAC payerBAC)
	{
		mockPayerBAC = payerBAC;
	}

	/**
	 * Gets the location bai.
	 *
	 * @return the location bai
	 */
	public IPayerBAI getPayerBAI()
	{
		return payerBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param payerBAI the location bai
	 */
	@Resource
	public void setPayerBAI(IPayerBAI payerBAI)
	{
		this.payerBAI = payerBAI;
	}

	/**
	 * Gets the err response.
	 *
	 * @return the err response
	 */
	public InternalResultsResponse<Payer> getErrResponse()
	{
		return errResponse;
	}

	/**
	 * Sets the err response.
	 *
	 * @param errResponse the err response
	 */
	public void setErrResponse(InternalResultsResponse<Payer> errResponse)
	{
		this.errResponse = errResponse;
	}

	/**
	 * The Constructor.
	 */
	public PayerBAIImplTest()
	{
		// setup the error response object.
		setErrResponse(new InternalResultsResponse<Payer>());
		getErrResponse().addMessage(BAC_KEY, MessageSeverity.Error, MessageLevel.Field);
		getErrResponse().setStatus(Status.UnspecifiedError);
	}

	/**
	 * Test fetch payer by id.
	 */
	@Test
	public void testFetchPayerById()
	{
		Payer payer = CommonTestRoutines.createDummyPayer();
		FetchByIdRequest request = new FetchByIdRequest(payer.getId());
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockPayerBAC().fetchPayerById(request)).thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyPayer()));

		PayerResponse result = getPayerBAI().fetchPayerById(request);

		CommonTestRoutines.assertResponse(result);

		Mockito.verify(getMockPayerBAC()).fetchPayerById(request);
	}

	/**
	 *
	 */
	@Test
	public void testFetchPayerByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(1);

		Mockito.when(getMockPayerBAC().fetchPayerById(request)).thenThrow(new RuntimeException());

		PayerResponse response = getPayerBAI().fetchPayerById(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockPayerBAC()).fetchPayerById(request);
	}

	/**
	 *
	 */
	@Test
	public void testFetchPayerByIdNoPayerId()
	{
		FetchByIdRequest request = new FetchByIdRequest();
		PayerResponse response = getPayerBAI().fetchPayerById(request);

		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_PAYERVALIDATOR_PAYER_ID_REQUIRED);

		// Ensures the mock's fetchPayerById was not called, due to validation error.
		Mockito.verify(getMockPayerBAC(), Mockito.never()).fetchPayerById(request);
	}

	/**
	 * Test successful fetch of a {@link Payer} by request.
	 */
	@Test
	public void testFetchPayerByRequest()
	{
		PayerInquiryRequest request = new PayerInquiryRequest();
		request.addSortExpressions(new SortExpression("name", Direction.Ascending));

		Mockito.when(getMockPayerBAC().fetchPayerByRequest(Matchers.any(PayerInquiryRequest.class))).thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyPayer()));

		PayerResponse response = getPayerBAI().fetchPayerByRequest(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockPayerBAC()).fetchPayerByRequest(request);
	}

	/**
	 * Test fetch of a {@link Payer} by request that takes an exception.
	 */
	@Test
	public void testFetchPayerByRequestException()
	{
		PayerInquiryRequest request = new PayerInquiryRequest();

		Mockito.when(getMockPayerBAC().fetchPayerByRequest(request)).thenThrow(new RuntimeException());

		PayerResponse response = getPayerBAI().fetchPayerByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, PayerBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockPayerBAC()).fetchPayerByRequest(request);
	}

	/**
	 * Test fetch of a {@link Payer} by request that takes an error on sort fields.
	 */
	@Test
	public void testFetchPayerByRequestInvalidSortColumn()
	{
		PayerInquiryRequest request = new PayerInquiryRequest();
		request.addSortExpressions(new SortExpression("badname", Direction.Ascending));

		Mockito.when(getMockPayerBAC().fetchPayerByRequest(Matchers.any(PayerInquiryRequest.class))).thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyPayer()));

		PayerResponse response = getPayerBAI().fetchPayerByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, PagedInquiryRequestValidator.INVALID_SORT_FIELD);

		Mockito.verify(getMockPayerBAC(), Mockito.never()).fetchPayerByRequest(request);
	}

	/**
	 * Test fetch of a {@link Payer} by request where request is null.
	 */
	@Test
	public void testFetchPayerByRequestNull()
	{
		Mockito.when(getMockPayerBAC().fetchPayerByRequest(Matchers.any(PayerInquiryRequest.class))).thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyPayer()));

		PayerInquiryRequest request = null;
		PayerResponse response = getPayerBAI().fetchPayerByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, PayerInquiryRequestValidator.PAYER_INQUIRY_REQUEST_REQUIRED);

		Mockito.verify(getMockPayerBAC(), Mockito.never()).fetchPayerByRequest(request);
	}

	/**
	 * Creates the fetch response.
	 *
	 * @param payer the payer
	 * @return the internal results response< payer>
	 */
	private InternalResultsResponse<Payer> createFetchResponse(Payer payer)
	{
		InternalResultsResponse<Payer> response = new InternalResultsResponse<Payer>();
		response.addResult(payer);
		return response;
	}

}
