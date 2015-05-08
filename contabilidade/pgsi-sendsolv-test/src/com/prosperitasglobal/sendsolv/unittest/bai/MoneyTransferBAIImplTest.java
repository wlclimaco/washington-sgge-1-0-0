package com.prosperitasglobal.sendsolv.unittest.bai;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC;
import com.prosperitasglobal.sendsolv.bai.IMoneyTransferBAI;
import com.prosperitasglobal.sendsolv.bai.impl.MoneyTransferBAIImpl;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.dac.IOrganizationDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchDTO;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatusEnum;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferAutoApprovalRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchStatusMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferStatusMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferAutoApprovalResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchDTOResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferResponse;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.prosperitasglobal.sendsolv.validation.MoneyTransferAutoApprovalRequestValidator;
import com.prosperitasglobal.sendsolv.validation.MoneyTransferBatchInquiryRequestValidator;
import com.prosperitasglobal.sendsolv.validation.MoneyTransferBatchStatusMaintenanceRequestValidator;
import com.prosperitasglobal.sendsolv.validation.MoneyTransferBatchValidator;
import com.prosperitasglobal.sendsolv.validation.MoneyTransferInquiryRequestValidator;
import com.prosperitasglobal.sendsolv.validation.MoneyTransferStatusMaintenanceRequestValidator;
import com.prosperitasglobal.sendsolv.validation.MoneyTransferValidator;
import com.prosperitasglobal.sendsolv.validation.PagedInquiryRequestValidator;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.model.response.MaintenanceResponse;

/**
 * The Class MoneyTransferBAIImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/pgsi-sendsolv-validators-context-test.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/sendsolv-dac-context.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-shared-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/moneytransferbaiimpltest.xml"})
public class MoneyTransferBAIImplTest extends AbstractJUnit4SpringContextTests
{

	/** The Constant MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_LOCATION_REQUIRED. */
	private static final String MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_LOCATION_REQUIRED =
			"sendsolv.base.moneytransferbatchcreaterequestvalidator.location.required";

	/** The error code to set when the id used for a fetch is null. */
	private static final String NULL_OBJECT_ID_ERROR = "sendsolv.base.object.id.missing.for.fetch.error";

	/** The error code to set when a fetch by id error is encountered. */
	private static final String FETCH_FAILED_ERROR = "sendsolv.base.error.fetching.object.error";

	/** The Constant LOCATION_NOT_ACTIVE. */
	private static final String LOCATION_NOT_ACTIVE =
			"sendsolv.base.moneytransfercreaterequestvalidator.location.not.active";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_LOCATION_PARENT_INACTIVE. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_LOCATION_PARENT_NOT_ACTIVE =
			"sendsolv.base.businessvalidator.location.parent.not.active";

	/** The Constant MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_PAY_DATE_REQUIRED. */
	private static final String MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_PAY_DATE_REQUIRED =
			"sendsolv.base.moneytransferbatchcreaterequestvalidator.pay.date.required";

	/** The Constant MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_PAYMENT_PREPARATION_DATE_REQUIRED. */
	private static final String MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_PAYMENT_PREPARATION_DATE_REQUIRED =
			"sendsolv.base.moneytransferbatchcreaterequestvalidator.payment.preparation.date.required";

	/** The Constant MONEY_TRANSFER_CREATE_REQUEST_TRANSFER_SETTING_REQUIRED. */
	private static final String MONEY_TRANSFER_CREATE_REQUEST_TRANSFER_SETTING_REQUIRED =
			"sendsolv.base.moneytransfercreaterequestvalidator.transfersetting.required";

	/** The Constant TRANSFER_SETTING_NOT_ACTIVE. */
	private static final String TRANSFER_SETTING_NOT_ACTIVE =
			"sendsolv.base.moneytransfercreaterequestvalidator.transfersetting.not.active";

	/** The Constant MEMBER_NOT_ACTIVE. */
	private static final String MEMBER_NOT_ACTIVE =
			"sendsolv.base.moneytransfercreaterequestvalidator.member.not.active";

	/** The Constant NO_ACTIVE_ID_DOCUMENT_FOR_MEMBER. */
	private static final String NO_ACTIVE_ID_DOCUMENT_FOR_MEMBER =
			"sendsolv.base.processor.moneytransferbatchtransfersettingitemprocessor.no.active.iddocument.for.member";

	/** The Constant RECIPIENT_NOT_ACTIVE. */
	private static final String RECIPIENT_NOT_ACTIVE =
			"sendsolv.base.moneytransfercreaterequestvalidator.recipient.not.active";

	/** The Constant MONEY_TRANSFER_CREATE_REQUEST_EMPLOYEE_INFO_REQUIRED. */
	private static final String MONEY_TRANSFER_CREATE_REQUEST_EMPLOYEE_INFO_REQUIRED =
			"sendsolv.base.moneytransfercreaterequestvalidator.employeeinfo.required";

	/** The Constant EMPLOYEE_INFO_NOT_ACTIVE. */
	private static final String EMPLOYEE_INFO_NOT_ACTIVE =
			"sendsolv.base.moneytransfercreaterequestvalidator.employeeinfo.not.active";

	/** The Constant MONEY_TRANSFER_CREATE_REQUEST_VALIDATOR_PAY_PREPARATION_DATE_OUT_OF_RANGE. */
	private static final String MONEY_TRANSFER_CREATE_REQUEST_VALIDATOR_PAY_PREPARATION_DATE_OUT_OF_RANGE =
			"sendsolv.base.moneytransfercreaterequestvalidator.pay.preparation.date.out.of.range";

	/** The Constant INVALID COLUMN. */
	private static final String INVALID_COLUMN = "invalid_column";

	/** The Constant NUMBER_1. */
	private static final int NUMBER_1 = 1;

	/** The Constant SORT_STATUS. */
	private static final String SORT_STATUS = "status";

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(MoneyTransferBAIImplTest.class);

	/** The money transfer bai. */
	private IMoneyTransferBAI moneyTransferBAI;

	/** The money transfer bac. */
	private IMoneyTransferBAC moneyTransferBAC;

	/** The mock location dac. */
	private ILocationDAC mockLocationDAC;

	/** The mock organization dac. */
	private IOrganizationDAC mockOrganizationDAC;

	/** The mock person dac. */
	private IPersonDAC mockPersonDAC;

	/**
	 * Gets the money transfer bai.
	 *
	 * @return the moneyTransferBAI
	 */
	public IMoneyTransferBAI getMoneyTransferBAI()
	{
		return moneyTransferBAI;
	}

	/**
	 * Sets the money transfer bai.
	 *
	 * @param moneyTransferBAI the moneyTransferBAI to set
	 */
	@Resource
	public void setMoneyTransferBAI(IMoneyTransferBAI moneyTransferBAI)
	{
		this.moneyTransferBAI = moneyTransferBAI;
	}

	/**
	 * Gets the money transfer bac.
	 *
	 * @return the moneyTransferBAC
	 */
	public IMoneyTransferBAC getMoneyTransferBAC()
	{
		return moneyTransferBAC;
	}

	/**
	 * Sets the money transfer bac.
	 *
	 * @param moneyTransferBAC the moneyTransferBAC to set
	 */
	@Resource
	public void setMoneyTransferBAC(IMoneyTransferBAC moneyTransferBAC)
	{
		this.moneyTransferBAC = moneyTransferBAC;
	}

	/**
	 * Execute before executing each @Test.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getMoneyTransferBAC());
		Mockito.reset(getMockLocationDAC());
		Mockito.reset(getMockOrganizationDAC());
		Mockito.reset(getMockPersonDAC());

	}

	/**
	 * Gets the mock location dac.
	 *
	 * @return the mockLocationDAC
	 */
	public ILocationDAC getMockLocationDAC()
	{
		return mockLocationDAC;
	}

	/**
	 * Sets the mock location dac.
	 *
	 * @param mockLocationDAC the mockLocationDAC to set
	 */
	@Resource
	public void setMockLocationDAC(ILocationDAC mockLocationDAC)
	{
		this.mockLocationDAC = mockLocationDAC;
	}

	/**
	 * Gets the mock organization dac.
	 *
	 * @return the mockOrganizationDAC
	 */
	public IOrganizationDAC getMockOrganizationDAC()
	{
		return mockOrganizationDAC;
	}

	/**
	 * Sets the mock organization dac.
	 *
	 * @param mockOrganizationDAC the mockOrganizationDAC to set
	 */
	@Resource
	public void setMockOrganizationDAC(IOrganizationDAC mockOrganizationDAC)
	{
		this.mockOrganizationDAC = mockOrganizationDAC;
	}

	/**
	 * Gets the mock person dac.
	 *
	 * @return the mockPersonDAC
	 */
	public IPersonDAC getMockPersonDAC()
	{
		return mockPersonDAC;
	}

	/**
	 * Sets the mock person dac.
	 *
	 * @param mockPersonDAC the mockPersonDAC to set
	 */
	@Resource
	public void setMockPersonDAC(IPersonDAC mockPersonDAC)
	{
		this.mockPersonDAC = mockPersonDAC;
	}

	/**
	 * Test successful fetch method of a {@link MoneyTransfer} by id.
	 */
	@Test
	public void testFetchMoneyTransferById()
	{
		MoneyTransfer moneyTransfer =
				CommonTestRoutines.createDummyMoneyTransfer(new MoneyTransferBatch(), new TransferSetting());
		FetchByIdRequest request = new FetchByIdRequest(NUMBER_1);
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMoneyTransferBAC().fetchMoneyTransferById(request)).thenReturn(
				createFetchResponse(moneyTransfer));

		MoneyTransferResponse response = getMoneyTransferBAI().fetchMoneyTransferById(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMoneyTransferBAC()).fetchMoneyTransferById(request);
	}

	/**
	 * Test fetch of a {@link MoneyTransf} by id that takes an exception.
	 */
	@Test
	public void testFetchMoneyTransferByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(NUMBER_1);

		Mockito.when(getMoneyTransferBAC().fetchMoneyTransferById(request)).thenThrow(new RuntimeException());

		MoneyTransferResponse response = getMoneyTransferBAI().fetchMoneyTransferById(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMoneyTransferBAC()).fetchMoneyTransferById(request);
	}

	/**
	 * Test fetch of a {@link MoneyTransfer} by id when the id is 0 or null.
	 */
	@Test
	public void testFetchMoneyTransferByIdMissingId()
	{
		MoneyTransfer moneyTransfer =
				CommonTestRoutines.createDummyMoneyTransfer(new MoneyTransferBatch(), new TransferSetting());
		FetchByIdRequest request = new FetchByIdRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMoneyTransferBAC().fetchMoneyTransferById(request)).thenReturn(
				createFetchResponse(moneyTransfer));

		MoneyTransferResponse response = getMoneyTransferBAI().fetchMoneyTransferById(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.MONEY_TRANSFER_ID_REQUIRED);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).fetchMoneyTransferById(request);
	}

	/**
	 * Test successful fetch method of a {@link MoneyTransfBatch}.
	 */
	@Test
	public void testFetchMoneyTransferBatchById()
	{
		MoneyTransferBatch moneyTransferBatch =
				CommonTestRoutines.createDummyMoneyTransferBatch(new Location());

		FetchByIdRequest request = new FetchByIdRequest(moneyTransferBatch.getId());
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMoneyTransferBAC().fetchMoneyTransferBatchById(request)).thenReturn(
				createFetchResponse(moneyTransferBatch));

		MoneyTransferBatchResponse response = getMoneyTransferBAI().fetchMoneyTransferBatchById(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMoneyTransferBAC()).fetchMoneyTransferBatchById(request);
	}

	/**
	 * Test fetch of a {@link MoneyTransfBatch} by id that takes an exception.
	 */
	@Test
	public void testFetchMoneyTransferBatchByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(NUMBER_1);

		Mockito.when(getMoneyTransferBAC().fetchMoneyTransferBatchById(request)).thenThrow(new RuntimeException());

		MoneyTransferBatchResponse response = getMoneyTransferBAI().fetchMoneyTransferBatchById(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMoneyTransferBAC()).fetchMoneyTransferBatchById(request);
	}

	/**
	 * Test fetch of a {@link MoneyTransferBatch} by id when the id is 0 or null.
	 */
	@Test
	public void testFetchMoneyTransferBatchByIdMissingId()
	{
		MoneyTransferBatch moneyTransferBatch =
				CommonTestRoutines.createDummyMoneyTransferBatch(new Location());
		FetchByIdRequest request = new FetchByIdRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMoneyTransferBAC().fetchMoneyTransferBatchById(request)).thenReturn(
				createFetchResponse(moneyTransferBatch));

		MoneyTransferBatchResponse response = getMoneyTransferBAI().fetchMoneyTransferBatchById(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.MONEY_TRANSFER_BATCH_ID_REQUIRED);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).fetchMoneyTransferBatchById(request);
	}

	/**
	 * Test successful fetch of a {@link MoneyTransfer} by request.
	 */
	@Test
	public void testFetchMoneyTransferByRequest()
	{
		MoneyTransferInquiryRequest request = new MoneyTransferInquiryRequest();
		request.addSortExpressions(new SortExpression(SORT_STATUS, Direction.Ascending));

		Mockito.when(getMoneyTransferBAC().fetchMoneyTransferByRequest(Matchers.any(MoneyTransferInquiryRequest.class)))
				.thenReturn(
						createFetchResponse(CommonTestRoutines.createDummyMoneyTransfer(new MoneyTransferBatch(),
								new TransferSetting())));

		MoneyTransferResponse response = getMoneyTransferBAI().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMoneyTransferBAC()).fetchMoneyTransferByRequest(request);
	}

	/**
	 * Test fetch of {@link MoneyTransfer} by request that takes an exception.
	 */
	@Test
	public void testFetchMoneyTransferByRequestException()
	{
		MoneyTransferInquiryRequest request = new MoneyTransferInquiryRequest();

		Mockito.when(getMoneyTransferBAC().fetchMoneyTransferByRequest(request)).thenThrow(new RuntimeException());

		MoneyTransferResponse response = getMoneyTransferBAI().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMoneyTransferBAC()).fetchMoneyTransferByRequest(request);
	}

	/**
	 * Test fetch of a {@link MoneyTransfer} by request when the sort column is invalid.
	 */
	@Test
	public void testFetchMoneyTransferByRequestInvalidSortColumn()
	{
		MoneyTransferInquiryRequest request = new MoneyTransferInquiryRequest();
		request.addSortExpressions(new SortExpression(INVALID_COLUMN, Direction.Ascending));

		Mockito.when(getMoneyTransferBAC().fetchMoneyTransferByRequest(Matchers.any(MoneyTransferInquiryRequest.class)))
				.thenReturn(
						createFetchResponse(CommonTestRoutines.createDummyMoneyTransfer(new MoneyTransferBatch(),
								new TransferSetting())));

		MoneyTransferResponse response = getMoneyTransferBAI().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, PagedInquiryRequestValidator.INVALID_SORT_FIELD);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).fetchMoneyTransferByRequest(request);
	}

	/**
	 * Test fetch of a {@link MoneyTransfer} when request is null.
	 */
	@Test
	public void testFetchMoneyTransferByRequestNull()
	{
		Mockito.when(getMoneyTransferBAC().fetchMoneyTransferByRequest(Matchers.any(MoneyTransferInquiryRequest.class)))
		.thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyMoneyTransfer(new MoneyTransferBatch(),
						new TransferSetting())));

		MoneyTransferInquiryRequest request = null;
		MoneyTransferResponse response = getMoneyTransferBAI().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG,
				MoneyTransferInquiryRequestValidator.MONEY_TRANSFER_INQUIRY_REQUEST_REQUIRED);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).fetchMoneyTransferByRequest(request);
	}

	/**
	 * Test successful fetch of a {@link MoneyTransferBatch} by request.
	 */
	@Test
	public void testFetchMoneyTransferBatchByRequest()
	{
		MoneyTransferBatchInquiryRequest request = new MoneyTransferBatchInquiryRequest();
		request.addSortExpressions(new SortExpression(SORT_STATUS, Direction.Ascending));

		Mockito.when(
				getMoneyTransferBAC().fetchMoneyTransferBatchByRequest(
						Matchers.any(MoneyTransferBatchInquiryRequest.class)))
						.thenReturn(createFetchResponse(
								CommonTestRoutines.createDummyMoneyTransferBatch(new Location())));

		MoneyTransferBatchResponse response = getMoneyTransferBAI().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMoneyTransferBAC()).fetchMoneyTransferBatchByRequest(request);
	}

	/**
	 * Test fetch of {@link MoneyTransferBatch} by request that takes an exception.
	 */
	@Test
	public void testFetchMoneyTransferBatchByRequestException()
	{
		MoneyTransferBatchInquiryRequest request = new MoneyTransferBatchInquiryRequest();

		Mockito.when(getMoneyTransferBAC().fetchMoneyTransferBatchByRequest(request)).thenThrow(new RuntimeException());

		MoneyTransferBatchResponse response = getMoneyTransferBAI().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMoneyTransferBAC()).fetchMoneyTransferBatchByRequest(request);
	}

	/**
	 * Test fetch of a {@link MoneyTransferBatch} by request when the sort column is invalid.
	 */
	@Test
	public void testFetchMoneyTransferBatchByRequestInvalidSortColumn()
	{
		MoneyTransferBatchInquiryRequest request = new MoneyTransferBatchInquiryRequest();
		request.addSortExpressions(new SortExpression(INVALID_COLUMN, Direction.Ascending));

		Mockito.when(
				getMoneyTransferBAC().fetchMoneyTransferBatchByRequest(
						Matchers.any(MoneyTransferBatchInquiryRequest.class)))
				.thenReturn(
						createFetchResponse(CommonTestRoutines.createDummyMoneyTransferBatch(new Location())));

		MoneyTransferBatchResponse response = getMoneyTransferBAI().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, PagedInquiryRequestValidator.INVALID_SORT_FIELD);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).fetchMoneyTransferBatchByRequest(request);
	}

	/**
	 * Test fetch of a {@link MoneyTransferBatch} when request is null.
	 */
	@Test
	public void testFetchMoneyTransferBatchByRequestNull()
	{
		Mockito.when(
				getMoneyTransferBAC().fetchMoneyTransferBatchByRequest(
						Matchers.any(MoneyTransferBatchInquiryRequest.class)))
						.thenReturn(
								createFetchResponse(CommonTestRoutines.createDummyMoneyTransferBatch(new Location())));

		MoneyTransferBatchInquiryRequest request = null;
		MoneyTransferBatchResponse response = getMoneyTransferBAI().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG,
				MoneyTransferBatchInquiryRequestValidator.MONEY_TRANSFER_BATCH_INQUIRY_REQUEST_REQUIRED);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).fetchMoneyTransferBatchByRequest(request);
	}

	/**
	 * Test successful fetch of a list of IDs that will be auto approved.
	 */
	@Test
	public void testFetchMoneyTransferAutoApprovalByTranferSetting()
	{
		MoneyTransferAutoApprovalRequest request = new MoneyTransferAutoApprovalRequest();
		request.setMoneyTransferBatchId(NUMBER_1);
		request.setIsNetPayGreaterTransValue(Boolean.FALSE);
		request.setIsPreviousTransactionApproved(Boolean.FALSE);
		request.setIsSameAmountPreviousTransaction(Boolean.TRUE);

		Mockito.when(
				getMoneyTransferBAC().fetchMoneyTransferAutoApprovalByTransferSetting(
						Matchers.any(MoneyTransferAutoApprovalRequest.class)))
				.thenReturn(new InternalResultsResponse<Integer>());

		MoneyTransferAutoApprovalResponse response =
				getMoneyTransferBAI().fetchMoneyTransferAutoApprovalByTransferSetting(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMoneyTransferBAC()).fetchMoneyTransferAutoApprovalByTransferSetting(request);
	}

	/**
	 * Test the fetch of a list of IDs that takes an exception.
	 */
	@Test
	public void testFetchMoneyTransferAutoApprovalByTranferSettingWithException()
	{
		MoneyTransferAutoApprovalRequest request = new MoneyTransferAutoApprovalRequest();
		request.setMoneyTransferBatchId(NUMBER_1);
		request.setIsNetPayGreaterTransValue(Boolean.FALSE);
		request.setIsPreviousTransactionApproved(Boolean.FALSE);
		request.setIsSameAmountPreviousTransaction(Boolean.TRUE);

		Mockito.when(
				getMoneyTransferBAC().fetchMoneyTransferAutoApprovalByTransferSetting(
						Matchers.any(MoneyTransferAutoApprovalRequest.class))).thenThrow(new RuntimeException());

		MoneyTransferAutoApprovalResponse response =
				getMoneyTransferBAI().fetchMoneyTransferAutoApprovalByTransferSetting(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMoneyTransferBAC()).fetchMoneyTransferAutoApprovalByTransferSetting(request);
	}

	/**
	 * Test validation situation when the MoneyTransferBatch ID is null.
	 */
	@Test
	public void testFetchMoneyTransferAutoApprovalByTranferSettingWithoutMoneyTransferBatchId()
	{
		MoneyTransferAutoApprovalRequest request = new MoneyTransferAutoApprovalRequest();
		request.setIsNetPayGreaterTransValue(Boolean.FALSE);
		request.setIsPreviousTransactionApproved(Boolean.FALSE);
		request.setIsSameAmountPreviousTransaction(Boolean.TRUE);

		Mockito.when(
				getMoneyTransferBAC().fetchMoneyTransferAutoApprovalByTransferSetting(
						Matchers.any(MoneyTransferAutoApprovalRequest.class)))
				.thenReturn(new InternalResultsResponse<Integer>());

		MoneyTransferAutoApprovalResponse response =
				getMoneyTransferBAI().fetchMoneyTransferAutoApprovalByTransferSetting(request);

		CommonTestRoutines
		.assertMessages(
				response,
				LOG,
				MoneyTransferAutoApprovalRequestValidator.MONEY_TRANSFER_AUTO_APPROVAL_MONEY_TRANSFER_BATCH_ID_REQUIRED);

	}

	/**
	 * Test validation situation when the isSameAmountPreviousTransaction is null.
	 */
	@Test
	public void testFetchMoneyTransferAutoApprovalByTranferSettingWithoutFlagIsSameAmount()
	{
		MoneyTransferAutoApprovalRequest request = new MoneyTransferAutoApprovalRequest();
		request.setMoneyTransferBatchId(NUMBER_1);
		request.setIsNetPayGreaterTransValue(Boolean.FALSE);
		request.setIsPreviousTransactionApproved(Boolean.FALSE);

		Mockito.when(
				getMoneyTransferBAC().fetchMoneyTransferAutoApprovalByTransferSetting(
						Matchers.any(MoneyTransferAutoApprovalRequest.class)))
				.thenReturn(new InternalResultsResponse<Integer>());

		MoneyTransferAutoApprovalResponse response =
				getMoneyTransferBAI().fetchMoneyTransferAutoApprovalByTransferSetting(request);

		CommonTestRoutines
		.assertMessages(
				response,
				LOG,
				MoneyTransferAutoApprovalRequestValidator.MONEY_TRANSFER_AUTO_APPROVAL_IS_SAME_AMOUNT_PREVIOUS_TRANSACTION_REQUIRED);

	}

	/**
	 * Test validation situation when the isNetPayGreaterTransValue is null.
	 */
	@Test
	public void testFetchMoneyTransferAutoApprovalByTranferSettingWithoutFlagIsNeyPayGreaterThanValue()
	{
		MoneyTransferAutoApprovalRequest request = new MoneyTransferAutoApprovalRequest();
		request.setMoneyTransferBatchId(NUMBER_1);
		request.setIsSameAmountPreviousTransaction(Boolean.TRUE);
		request.setIsPreviousTransactionApproved(Boolean.FALSE);

		Mockito.when(
				getMoneyTransferBAC().fetchMoneyTransferAutoApprovalByTransferSetting(
						Matchers.any(MoneyTransferAutoApprovalRequest.class)))
				.thenReturn(new InternalResultsResponse<Integer>());

		MoneyTransferAutoApprovalResponse response =
				getMoneyTransferBAI().fetchMoneyTransferAutoApprovalByTransferSetting(request);

		CommonTestRoutines
		.assertMessages(
				response,
				LOG,
				MoneyTransferAutoApprovalRequestValidator.MONEY_TRANSFER_AUTO_APPROVAL_IS_NET_PAYER_GREATER_THAN_TRANSFER_REQUIRED);

	}

	/**
	 * Test validation situation when the isPreviousTransactionApproved is null.
	 */
	@Test
	public void testFetchMoneyTransferAutoApprovalByTranferSettingWithoutFlagisPreviousTransactionApproved()
	{
		MoneyTransferAutoApprovalRequest request = new MoneyTransferAutoApprovalRequest();
		request.setMoneyTransferBatchId(NUMBER_1);
		request.setIsSameAmountPreviousTransaction(Boolean.TRUE);
		request.setIsNetPayGreaterTransValue(Boolean.TRUE);

		Mockito.when(
				getMoneyTransferBAC().fetchMoneyTransferAutoApprovalByTransferSetting(
						Matchers.any(MoneyTransferAutoApprovalRequest.class)))
				.thenReturn(new InternalResultsResponse<Integer>());

		MoneyTransferAutoApprovalResponse response =
				getMoneyTransferBAI().fetchMoneyTransferAutoApprovalByTransferSetting(request);

		CommonTestRoutines
		.assertMessages(
				response,
				LOG,
				MoneyTransferAutoApprovalRequestValidator.MONEY_TRANSFER_AUTO_APPROVAL_IS_PREVIOUS_TRANSACTION_APPROVED_REQUIRED);

	}

	/**
	 * Test validation situation when the fetch does not have one request.
	 */
	@Test
	public void testFetchMoneyTransferAutoApprovalByTranferSettingWithoutRequest()
	{
		MoneyTransferAutoApprovalRequest request = null;

		Mockito.when(
				getMoneyTransferBAC().fetchMoneyTransferAutoApprovalByTransferSetting(
						Matchers.any(MoneyTransferAutoApprovalRequest.class)))
				.thenReturn(new InternalResultsResponse<Integer>());

		MoneyTransferAutoApprovalResponse response =
				getMoneyTransferBAI().fetchMoneyTransferAutoApprovalByTransferSetting(request);

		CommonTestRoutines
		.assertMessages(
				response,
				LOG,
				MoneyTransferAutoApprovalRequestValidator.MONEY_TRANSFER_AUTO_APPROVAL_REQUEST_REQUIRED);

	}

	/**
	 * Test successful update of a {@link MoneyTransfer}.
	 */
	@Test
	public void testUpdateMoneyTransfer()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransfer moneyTransfer =
				CommonTestRoutines.createDummyMoneyTransfer(new MoneyTransferBatch(), new TransferSetting());
		moneyTransfer.setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransfer.setId(NUMBER_1);
		request.setMoneyTransfer(moneyTransfer);

		Mockito.when(getMoneyTransferBAC().updateMoneyTransfer(request)).thenReturn(
				new InternalResultsResponse<MoneyTransfer>());

		MaintenanceResponse response = getMoneyTransferBAI().updateMoneyTransfer(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMoneyTransferBAC()).updateMoneyTransfer(request);
	}

	/**
	 * Test update of {@link MoneyTransfer} that takes an exception.
	 */
	@Test
	public void testUpdateMoneyTransferException()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransfer moneyTransfer =
				CommonTestRoutines.createDummyMoneyTransfer(new MoneyTransferBatch(), new TransferSetting());
		moneyTransfer.setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransfer.setId(NUMBER_1);
		request.setMoneyTransfer(moneyTransfer);

		Mockito.when(getMoneyTransferBAC().updateMoneyTransfer(request)).thenThrow(new RuntimeException());

		MaintenanceResponse response = getMoneyTransferBAI().updateMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMoneyTransferBAC()).updateMoneyTransfer(request);
	}

	/**
	 * Test update of a {@link MoneyTransfer} when the MoneyTransfer is null.
	 */
	@Test
	public void testUpdateMoneyTransferWhenMoneyTransferNull()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMoneyTransferBAC().updateMoneyTransfer(request)).thenReturn(
				new InternalResultsResponse<MoneyTransfer>());

		MaintenanceResponse response = getMoneyTransferBAI().updateMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG,
				MoneyTransferValidator.MONEY_TRANSFER_REQUIRED);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).updateMoneyTransfer(request);
	}

	/**
	 * Test update of a {@link MoneyTransfer} when money transfer id is null.
	 */
	@Test
	public void testUpdateMoneyTransferWhenIdNull()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransfer moneyTransfer =
				CommonTestRoutines.createDummyMoneyTransfer(new MoneyTransferBatch(), new TransferSetting());
		moneyTransfer.setModelAction(PersistanceActionEnum.UPDATE);
		request.setMoneyTransfer(moneyTransfer);

		Mockito.when(getMoneyTransferBAC().updateMoneyTransfer(request)).thenReturn(
				new InternalResultsResponse<MoneyTransfer>());

		MaintenanceResponse response = getMoneyTransferBAI().updateMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG,
				MoneyTransferValidator.MONEY_TRANSFER_ID_REQUIRED);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).updateMoneyTransfer(request);
	}

	/**
	 * Test update of a {@link MoneyTransfer} with an Optimistic Locking error.
	 */
	@Test
	public void testUpdateMoneyTransferOptimisticLocking()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransfer moneyTransfer =
				CommonTestRoutines.createDummyMoneyTransfer(new MoneyTransferBatch(), new TransferSetting());
		moneyTransfer.setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransfer.setId(NUMBER_1);
		request.setMoneyTransfer(moneyTransfer);

		InternalResultsResponse<MoneyTransfer> internalResponse =
				new InternalResultsResponse<MoneyTransfer>();
		internalResponse.setStatus(Status.OptimisticLockingError);
		Mockito.when(getMoneyTransferBAC().updateMoneyTransfer(request)).thenReturn(internalResponse);

		MaintenanceResponse response = getMoneyTransferBAI().updateMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.OL_ERROR);

		Mockito.verify(getMoneyTransferBAC()).updateMoneyTransfer(request);
	}

	/**
	 * Test successful update of a {@link MoneyTransferBatch}.
	 */
	@Test
	public void testUpdateMoneyTransferBatch()
	{
		MoneyTransferBatchMaintenanceRequest request = new MoneyTransferBatchMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferBatch moneyTransferBatch =
				CommonTestRoutines.createDummyMoneyTransferBatch(new Location());
		moneyTransferBatch.setModelAction(PersistanceActionEnum.UPDATE);
		request.setMoneyTransferBatch(moneyTransferBatch);

		Mockito.when(getMoneyTransferBAC().updateMoneyTransferBatch(request)).thenReturn(
				new InternalResultsResponse<MoneyTransferBatch>());

		MaintenanceResponse response = getMoneyTransferBAI().updateMoneyTransferBatch(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMoneyTransferBAC()).updateMoneyTransferBatch(request);
	}

	/**
	 * Test update of {@link MoneyTransferBatch} that takes an exception.
	 */
	@Test
	public void testUpdateMoneyTransferBatchException()
	{
		MoneyTransferBatchMaintenanceRequest request = new MoneyTransferBatchMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferBatch moneyTransferBatch =
				CommonTestRoutines.createDummyMoneyTransferBatch(new Location());
		moneyTransferBatch.setModelAction(PersistanceActionEnum.UPDATE);
		request.setMoneyTransferBatch(moneyTransferBatch);

		Mockito.when(getMoneyTransferBAC().updateMoneyTransferBatch(request)).thenThrow(new RuntimeException());

		MaintenanceResponse response = getMoneyTransferBAI().updateMoneyTransferBatch(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMoneyTransferBAC()).updateMoneyTransferBatch(request);
	}

	/**
	 * Test update of a {@link MoneyTransferBatch} when the MoneyTransferBatch is null.
	 */
	@Test
	public void testUpdateMoneyTransferBatchWhenMoneyTransferBatchNull()
	{
		MoneyTransferBatchMaintenanceRequest request = new MoneyTransferBatchMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMoneyTransferBAC().updateMoneyTransferBatch(request)).thenReturn(
				new InternalResultsResponse<MoneyTransferBatch>());

		MaintenanceResponse response = getMoneyTransferBAI().updateMoneyTransferBatch(request);
		CommonTestRoutines.assertMessages(response, LOG,
				MoneyTransferBatchValidator.MONEY_TRANSFER_BATCH_REQUIRED);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).updateMoneyTransferBatch(request);
	}

	/**
	 * Test update of a {@link MoneyTransferBatch} when money transfer batch id is null.
	 */
	@Test
	public void testUpdateMoneyTransferBatchWhenIdNull()
	{
		MoneyTransferBatchMaintenanceRequest request = new MoneyTransferBatchMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferBatch moneyTransferBatch =
				CommonTestRoutines.createDummyMoneyTransferBatch(new Location());
		moneyTransferBatch.setModelAction(PersistanceActionEnum.UPDATE);
		request.setMoneyTransferBatch(moneyTransferBatch);
		request.getMoneyTransferBatch().setId(null);

		Mockito.when(getMoneyTransferBAC().updateMoneyTransferBatch(request)).thenReturn(
				new InternalResultsResponse<MoneyTransferBatch>());

		MaintenanceResponse response = getMoneyTransferBAI().updateMoneyTransferBatch(request);
		CommonTestRoutines.assertMessages(response, LOG,
				MoneyTransferBatchValidator.MONEY_TRANSFER_BATCH_ID_REQUIRED);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).updateMoneyTransferBatch(request);
	}

	/**
	 * Test update of a {@link MoneyTransferBatch} with an Optimistic Locking error.
	 */
	@Test
	public void testUpdateMoneyTransferBatchOptimisticLocking()
	{
		MoneyTransferBatchMaintenanceRequest request = new MoneyTransferBatchMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferBatch moneyTransferBatch =
				CommonTestRoutines.createDummyMoneyTransferBatch(new Location());
		moneyTransferBatch.setModelAction(PersistanceActionEnum.UPDATE);
		request.setMoneyTransferBatch(moneyTransferBatch);

		InternalResultsResponse<MoneyTransferBatch> internalResponse =
				new InternalResultsResponse<MoneyTransferBatch>();
		internalResponse.setStatus(Status.OptimisticLockingError);
		Mockito.when(getMoneyTransferBAC().updateMoneyTransferBatch(request)).thenReturn(internalResponse);

		MaintenanceResponse response = getMoneyTransferBAI().updateMoneyTransferBatch(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.OL_ERROR);

		Mockito.verify(getMoneyTransferBAC()).updateMoneyTransferBatch(request);
	}

	/**
	 * Test successful insert of a {@link MoneyTransferStatus}.
	 */
	@Test
	public void testInsertMoneyTransferStatus()
	{
		List<MoneyTransferStatus> list = new ArrayList<MoneyTransferStatus>();

		MoneyTransferStatusMaintenanceRequest request = new MoneyTransferStatusMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferStatus moneyTransferStatus =
				CommonTestRoutines.createDummyMoneyTransferStatus(MoneyTransferStatusEnum.APPROVED);
		moneyTransferStatus.setModelAction(PersistanceActionEnum.INSERT);
		moneyTransferStatus.setMoneyTransferId(NUMBER_1);
		list.add(moneyTransferStatus);
		request.setMoneyTransferStatusList(list);

		InternalResultsResponse<MoneyTransferStatus> internalResponse =
				new InternalResultsResponse<MoneyTransferStatus>();
		Mockito.when(getMoneyTransferBAC().insertMoneyTransferStatus(request)).thenReturn(internalResponse);

		MaintenanceResponse response = getMoneyTransferBAI().insertMoneyTransferStatus(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMoneyTransferBAC()).insertMoneyTransferStatus(request);
	}

	/**
	 * Test insert of {@link MoneyTransferStatus} that takes an exception.
	 */
	@Test
	public void testInsertMoneyTransferStatusException()
	{
		List<MoneyTransferStatus> list = new ArrayList<MoneyTransferStatus>();

		MoneyTransferStatusMaintenanceRequest request = new MoneyTransferStatusMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferStatus moneyTransferStatus =
				CommonTestRoutines.createDummyMoneyTransferStatus(MoneyTransferStatusEnum.APPROVED);
		moneyTransferStatus.setModelAction(PersistanceActionEnum.INSERT);
		moneyTransferStatus.setMoneyTransferId(NUMBER_1);
		list.add(moneyTransferStatus);
		request.setMoneyTransferStatusList(list);

		Mockito.when(getMoneyTransferBAC().insertMoneyTransferStatus(request)).thenThrow(new RuntimeException());

		MaintenanceResponse response = getMoneyTransferBAI().insertMoneyTransferStatus(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMoneyTransferBAC()).insertMoneyTransferStatus(request);
	}

	/**
	 * Test insert of a {@link MoneyTransferStatus} when list is null.
	 */
	@Test
	public void testInsertMoneyTransferStatusWhenListNull()
	{
		MoneyTransferStatusMaintenanceRequest request = new MoneyTransferStatusMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMoneyTransferBAC().insertMoneyTransferStatus(request)).thenReturn(
				new InternalResultsResponse<MoneyTransferStatus>());

		MaintenanceResponse response = getMoneyTransferBAI().insertMoneyTransferStatus(request);
		CommonTestRoutines.assertMessages(response, LOG,
				MoneyTransferStatusMaintenanceRequestValidator.MONEY_TRANSFER_STATUS_LIST_REQUIRED);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).insertMoneyTransferStatus(request);
	}

	/**
	 * Test insert of a {@link MoneyTransferStatus} when the status is null.
	 */
	@Test
	public void testInsertMoneyTransferStatusWhenStatusNull()
	{
		List<MoneyTransferStatus> list = new ArrayList<MoneyTransferStatus>();

		MoneyTransferStatusMaintenanceRequest request = new MoneyTransferStatusMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferStatus moneyTransferStatus =
				CommonTestRoutines.createDummyMoneyTransferStatus(MoneyTransferStatusEnum.CANCELLED);
		moneyTransferStatus.setModelAction(PersistanceActionEnum.INSERT);
		moneyTransferStatus.setMoneyTransferId(NUMBER_1);
		moneyTransferStatus.setStatus(null);
		list.add(moneyTransferStatus);
		request.setMoneyTransferStatusList(list);

		Mockito.when(getMoneyTransferBAC().insertMoneyTransferStatus(request)).thenReturn(
				new InternalResultsResponse<MoneyTransferStatus>());

		MaintenanceResponse response = getMoneyTransferBAI().insertMoneyTransferStatus(request);

		CommonTestRoutines.assertMessages(response, LOG,
				MoneyTransferStatusMaintenanceRequestValidator.MONEY_TRANSFER_STATUS_STATUS_REQUIRED);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).insertMoneyTransferStatus(request);

	}

	/**
	 * Test insert of a {@link MoneyTransferStatus} when id is null.
	 */
	@Test
	public void testInsertMoneyTransferStatusWhenIdNull()
	{
		List<MoneyTransferStatus> list = new ArrayList<MoneyTransferStatus>();

		MoneyTransferStatusMaintenanceRequest request = new MoneyTransferStatusMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferStatus moneyTransferStatus =
				CommonTestRoutines.createDummyMoneyTransferStatus(MoneyTransferStatusEnum.APPROVED);
		moneyTransferStatus.setModelAction(PersistanceActionEnum.INSERT);
		list.add(moneyTransferStatus);
		request.setMoneyTransferStatusList(list);

		Mockito.when(getMoneyTransferBAC().insertMoneyTransferStatus(request)).thenReturn(
				new InternalResultsResponse<MoneyTransferBatchStatus>());

		MaintenanceResponse response = getMoneyTransferBAI().insertMoneyTransferStatus(request);

		CommonTestRoutines.assertMessages(response, LOG,
				MoneyTransferStatusMaintenanceRequestValidator.MONEY_TRANSFER_ID_REQUIRED);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).insertMoneyTransferStatus(request);

	}

	/**
	 * Test successful insert of a {@link MoneyTransferBatchStatus}.
	 */
	@Test
	public void testInsertMoneyTransferBatchStatus()
	{
		List<MoneyTransferBatchStatus> list = new ArrayList<MoneyTransferBatchStatus>();

		MoneyTransferBatchStatusMaintenanceRequest request = new MoneyTransferBatchStatusMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferBatchStatus moneyTransferBatchStatus =
				CommonTestRoutines.createDummyMoneyTransferBatchStatus(MoneyTransferBatchStatusEnum.CANCELLED);
		moneyTransferBatchStatus.setModelAction(PersistanceActionEnum.INSERT);
		list.add(moneyTransferBatchStatus);
		request.setMoneyTransferBatchStatusList(list);

		InternalResultsResponse<MoneyTransferBatchStatus> internalResponse =
				new InternalResultsResponse<MoneyTransferBatchStatus>();
		Mockito.when(getMoneyTransferBAC().insertMoneyTransferBatchStatus(request)).thenReturn(internalResponse);

		MaintenanceResponse response = getMoneyTransferBAI().insertMoneyTransferBatchStatus(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMoneyTransferBAC()).insertMoneyTransferBatchStatus(request);
	}

	/**
	 * Test insert of {@link MoneyTransferBatchStatus} that takes an exception.
	 */
	@Test
	public void testInsertMoneyTransferBatchStatusException()
	{
		List<MoneyTransferBatchStatus> list = new ArrayList<MoneyTransferBatchStatus>();

		MoneyTransferBatchStatusMaintenanceRequest request = new MoneyTransferBatchStatusMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferBatchStatus moneyTransferBatchStatus =
				CommonTestRoutines.createDummyMoneyTransferBatchStatus(MoneyTransferBatchStatusEnum.CANCELLED);
		moneyTransferBatchStatus.setModelAction(PersistanceActionEnum.INSERT);
		list.add(moneyTransferBatchStatus);
		request.setMoneyTransferBatchStatusList(list);

		Mockito.when(getMoneyTransferBAC().insertMoneyTransferBatchStatus(request)).thenThrow(new RuntimeException());

		MaintenanceResponse response = getMoneyTransferBAI().insertMoneyTransferBatchStatus(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMoneyTransferBAC()).insertMoneyTransferBatchStatus(request);
	}

	/**
	 * Test insert of a {@link MoneyTransferBatchStatus} when list is null.
	 */
	@Test
	public void testInsertMoneyTransferBatchStatusWhenListNull()
	{
		MoneyTransferBatchStatusMaintenanceRequest request = new MoneyTransferBatchStatusMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMoneyTransferBAC().insertMoneyTransferBatchStatus(request)).thenReturn(
				new InternalResultsResponse<MoneyTransferBatchStatus>());

		MaintenanceResponse response = getMoneyTransferBAI().insertMoneyTransferBatchStatus(request);
		CommonTestRoutines.assertMessages(response, LOG,
				MoneyTransferBatchStatusMaintenanceRequestValidator.MONEY_TRANSFER_BATCH_STATUS_LIST_REQUIRED);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).insertMoneyTransferBatchStatus(request);
	}

	/**
	 * Test insert of a {@link MoneyTransferBatchStatus} when the status is null.
	 */
	@Test
	public void testInsertMoneyTransferBatchStatusWhenStatusNull()
	{
		List<MoneyTransferBatchStatus> list = new ArrayList<MoneyTransferBatchStatus>();

		MoneyTransferBatchStatusMaintenanceRequest request = new MoneyTransferBatchStatusMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferBatchStatus moneyTransferBatchStatus =
				CommonTestRoutines.createDummyMoneyTransferBatchStatus(MoneyTransferBatchStatusEnum.CANCELLED);
		moneyTransferBatchStatus.setModelAction(PersistanceActionEnum.INSERT);
		moneyTransferBatchStatus.setStatus(null);
		list.add(moneyTransferBatchStatus);
		request.setMoneyTransferBatchStatusList(list);

		Mockito.when(getMoneyTransferBAC().insertMoneyTransferBatchStatus(request)).thenReturn(
				new InternalResultsResponse<MoneyTransferBatchStatus>());

		MaintenanceResponse response = getMoneyTransferBAI().insertMoneyTransferBatchStatus(request);

		CommonTestRoutines.assertMessages(response, LOG,
				MoneyTransferBatchStatusMaintenanceRequestValidator.MONEY_TRANSFER_BATCH_STATUS_STATUS_REQUIRED);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).insertMoneyTransferBatchStatus(request);

	}

	/**
	 * Test insert of a {@link MoneyTransferBatchStatus} when id is null.
	 */
	@Test
	public void testInsertMoneyTransferBatchStatusWhenIdNull()
	{
		List<MoneyTransferBatchStatus> list = new ArrayList<MoneyTransferBatchStatus>();

		MoneyTransferBatchStatusMaintenanceRequest request = new MoneyTransferBatchStatusMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferBatchStatus moneyTransferBatchStatus =
				CommonTestRoutines.createDummyMoneyTransferBatchStatus(MoneyTransferBatchStatusEnum.CANCELLED);
		moneyTransferBatchStatus.setModelAction(PersistanceActionEnum.INSERT);
		moneyTransferBatchStatus.setMoneyTransferBatchId(null);
		list.add(moneyTransferBatchStatus);
		request.setMoneyTransferBatchStatusList(list);

		Mockito.when(getMoneyTransferBAC().insertMoneyTransferBatchStatus(request)).thenReturn(
				new InternalResultsResponse<MoneyTransferBatchStatus>());

		MaintenanceResponse response = getMoneyTransferBAI().insertMoneyTransferBatchStatus(request);

		CommonTestRoutines.assertMessages(response, LOG,
				MoneyTransferBatchStatusMaintenanceRequestValidator.MONEY_TRANSFER_BATCH_ID_REQUIRED);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).insertMoneyTransferBatchStatus(request);

	}

	/**
	 * Test successful fetch method of a {@link MoneyTransfBatchDTO}.
	 */
	@Test
	public void testFetchMoneyTransferBatchWithSummaryById()
	{
		MoneyTransferBatchDTO moneyTransferBatchDTO = new MoneyTransferBatchDTO();

		FetchByIdRequest request = new FetchByIdRequest(NUMBER_1);
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMoneyTransferBAC().fetchMoneyTransferBatchWithSummaryById(request)).thenReturn(
				createFetchResponse(moneyTransferBatchDTO));

		MoneyTransferBatchResponse response = getMoneyTransferBAI().fetchMoneyTransferBatchById(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMoneyTransferBAC()).fetchMoneyTransferBatchById(request);
	}

	/**
	 * Test fetch of a {@link MoneyTransfBatchDTO} by id that takes an exception.
	 */
	@Test
	public void testFetchMoneyTransferBatchWithSummaryByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(NUMBER_1);

		Mockito.when(getMoneyTransferBAC().fetchMoneyTransferBatchWithSummaryById(request)).thenThrow(
				new RuntimeException());

		MoneyTransferBatchDTOResponse response = getMoneyTransferBAI().fetchMoneyTransferBatchWithSummaryById(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMoneyTransferBAC()).fetchMoneyTransferBatchWithSummaryById(request);
	}

	/**
	 * Test fetch of a {@link MoneyTransferBatchDTO} by id when the id is 0 or null.
	 */
	@Test
	public void testFetchMoneyTransferBatchWithSummaryByIdMissingId()
	{
		MoneyTransferBatchDTO moneyTransferBatchDTO = new MoneyTransferBatchDTO();

		FetchByIdRequest request = new FetchByIdRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMoneyTransferBAC().fetchMoneyTransferBatchWithSummaryById(request)).thenReturn(
				createFetchResponse(moneyTransferBatchDTO));

		MoneyTransferBatchDTOResponse response = getMoneyTransferBAI().fetchMoneyTransferBatchWithSummaryById(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.MONEY_TRANSFER_BATCH_ID_REQUIRED);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).fetchMoneyTransferBatchWithSummaryById(request);
	}

	/**
	 * Test insert money transfer batch.
	 */
	@Test
	public void testInsertMoneyTransferBatch()
	{
		MoneyTransferBatchCreateRequest request = new MoneyTransferBatchCreateRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setLocation(CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization()));
		request.getLocation().setId(NUMBER_1);
		request.setPayDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());
		request.setPaymentPreparationDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());

		InternalResultsResponse<MoneyTransferBatch> internalResponse =
				new InternalResultsResponse<MoneyTransferBatch>();
		Mockito.when(getMoneyTransferBAC().createOnOffMoneyTransferBatch(request)).thenReturn(internalResponse);

		mockLocationById();
		mockOrganizationById();

		MoneyTransferBatchResponse response = getMoneyTransferBAI().insertMoneyTransferBatch(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMoneyTransferBAC()).createOnOffMoneyTransferBatch(request);
	}

	/**
	 * Test insert money transfer batch exception.
	 */
	@Test
	public void testInsertMoneyTransferBatchException()
	{
		MoneyTransferBatchCreateRequest request = new MoneyTransferBatchCreateRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setLocation(CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization()));
		request.getLocation().setId(NUMBER_1);
		request.setPayDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());
		request.setPaymentPreparationDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());

		Mockito.when(getMoneyTransferBAC().createOnOffMoneyTransferBatch(request)).thenThrow(new RuntimeException());

		MoneyTransferBatchResponse response = getMoneyTransferBAI().insertMoneyTransferBatch(request);
		CommonTestRoutines.assertMessages(response, LOG, MoneyTransferBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).createOnOffMoneyTransferBatch(request);
	}

	/**
	 * Test insert money transfer batch without location.
	 */
	@Test
	public void testInsertMoneyTransferBatchWithoutLocation()
	{
		MoneyTransferBatchCreateRequest request = new MoneyTransferBatchCreateRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setPayDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());
		request.setPaymentPreparationDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());

		MoneyTransferBatchResponse response = getMoneyTransferBAI().insertMoneyTransferBatch(request);
		CommonTestRoutines.assertMessages(response, LOG,
				MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_LOCATION_REQUIRED);
	}

	/**
	 * Test insert money transfer batch without location id.
	 */
	@Test
	public void testInsertMoneyTransferBatchWithoutLocationId()
	{
		MoneyTransferBatchCreateRequest request = new MoneyTransferBatchCreateRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setLocation(CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization()));
		request.setPayDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());
		request.setPaymentPreparationDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());

		MoneyTransferBatchResponse response = getMoneyTransferBAI().insertMoneyTransferBatch(request);
		CommonTestRoutines.assertMessages(response, LOG, NULL_OBJECT_ID_ERROR);

		Mockito.verify(getMoneyTransferBAC(), Mockito.never()).createMoneyTransferBatch(request);
	}

	/**
	 * Test insert money transfer batch location by id with error.
	 */
	@Test
	public void testInsertMoneyTransferBatchLocationByIdWithError()
	{
		MoneyTransferBatchCreateRequest request = new MoneyTransferBatchCreateRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setLocation(CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization()));
		request.getLocation().setId(NUMBER_1);
		request.setPayDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());
		request.setPaymentPreparationDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());

		InternalResultsResponse<Location> locResponse = new InternalResultsResponse<Location>();
		locResponse.setStatus(Status.NoRowsFoundError);

		Mockito.when(
				getMockLocationDAC().fetchLocationById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(locResponse);

		MoneyTransferBatchResponse response = getMoneyTransferBAI().insertMoneyTransferBatch(request);
		CommonTestRoutines.assertMessages(response, LOG, FETCH_FAILED_ERROR);

		Mockito.verify(getMockLocationDAC()).fetchLocationById(Matchers.any(FetchByIdRequest.class));
	}

	/**
	 * Test insert money transfer batch location not active.
	 */
	@Test
	public void testInsertMoneyTransferBatchLocationNotActive()
	{
		MoneyTransferBatchCreateRequest request = new MoneyTransferBatchCreateRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setLocation(CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization()));
		request.getLocation().setId(NUMBER_1);
		request.getLocation().setStatus(StatusEnum.INACTIVE);
		request.setPayDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());
		request.setPaymentPreparationDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());

		InternalResultsResponse<Location> locResponse = new InternalResultsResponse<Location>();
		locResponse.setStatus(Status.OperationSuccess);
		locResponse.addResult(request.getLocation());

		Mockito.when(
				getMockLocationDAC().fetchLocationById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(locResponse);

		MoneyTransferBatchResponse response = getMoneyTransferBAI().insertMoneyTransferBatch(request);
		CommonTestRoutines.assertMessages(response, LOG, LOCATION_NOT_ACTIVE);

		Mockito.verify(getMockLocationDAC()).fetchLocationById(Matchers.any(FetchByIdRequest.class));
	}

	/**
	 * Test insert money transfer batch location withou parent organization id.
	 */
	@Test
	public void testInsertMoneyTransferBatchLocationWithouParentOrganizationId()
	{
		MoneyTransferBatchCreateRequest request = new MoneyTransferBatchCreateRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setLocation(CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization()));
		request.getLocation().setId(NUMBER_1);
		request.getLocation().setParentOrganizationId(null);
		request.setPayDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());
		request.setPaymentPreparationDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());

		InternalResultsResponse<Location> locResponse = new InternalResultsResponse<Location>();
		locResponse.setStatus(Status.OperationSuccess);
		locResponse.addResult(request.getLocation());

		Mockito.when(
				getMockLocationDAC().fetchLocationById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(locResponse);

		MoneyTransferBatchResponse response = getMoneyTransferBAI().insertMoneyTransferBatch(request);
		CommonTestRoutines.assertMessages(response, LOG, NULL_OBJECT_ID_ERROR);

		Mockito.verify(getMockLocationDAC()).fetchLocationById(Matchers.any(FetchByIdRequest.class));
	}

	/**
	 * Test insert money transfer batch location parent organization by id with error.
	 */
	@Test
	public void testInsertMoneyTransferBatchLocationParentOrganizationByIdWithError()
	{
		MoneyTransferBatchCreateRequest request = new MoneyTransferBatchCreateRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setLocation(CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization()));
		request.getLocation().setId(NUMBER_1);
		request.getLocation().setParentOrganizationId(null);
		request.setPayDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());
		request.setPaymentPreparationDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());

		InternalResultsResponse<Organization> orgResponse = new InternalResultsResponse<Organization>();
		orgResponse.setStatus(Status.NoRowsFoundError);
		orgResponse.addResult(CommonTestRoutines.createDummyOrganization());

		Mockito.when(
				getMockOrganizationDAC().fetchOrganizationById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(orgResponse);

		mockLocationById();

		MoneyTransferBatchResponse response = getMoneyTransferBAI().insertMoneyTransferBatch(request);
		CommonTestRoutines.assertMessages(response, LOG, FETCH_FAILED_ERROR);

		Mockito.verify(getMockOrganizationDAC()).fetchOrganizationById(Matchers.any(FetchByIdRequest.class));
	}

	/**
	 * Test insert money transfer batch location parent organization id not active.
	 */
	@Test
	public void testInsertMoneyTransferBatchLocationParentOrganizationIdNotActive()
	{
		MoneyTransferBatchCreateRequest request = new MoneyTransferBatchCreateRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setLocation(CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization()));
		request.getLocation().setId(NUMBER_1);
		request.setPayDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());
		request.setPaymentPreparationDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setStatus(StatusEnum.INACTIVE);

		InternalResultsResponse<Organization> orgResponse = new InternalResultsResponse<Organization>();
		orgResponse.setStatus(Status.OperationSuccess);
		orgResponse.addResult(organization);

		Mockito.when(
				getMockOrganizationDAC().fetchOrganizationById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(orgResponse);

		mockLocationById();

		MoneyTransferBatchResponse response = getMoneyTransferBAI().insertMoneyTransferBatch(request);
		CommonTestRoutines.assertMessages(response, LOG,
				PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_LOCATION_PARENT_NOT_ACTIVE);

		Mockito.verify(getMockOrganizationDAC()).fetchOrganizationById(Matchers.any(FetchByIdRequest.class));
	}

	/**
	 * Test insert money transfer batch without payment preparation date.
	 */
	@Test
	public void testInsertMoneyTransferBatchWithoutPaymentPreparationDate()
	{
		MoneyTransferBatchCreateRequest request = new MoneyTransferBatchCreateRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setLocation(CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization()));
		request.getLocation().setId(NUMBER_1);
		request.setPayDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());

		mockLocationById();
		mockOrganizationById();

		MoneyTransferBatchResponse response = getMoneyTransferBAI().insertMoneyTransferBatch(request);
		CommonTestRoutines.assertMessages(response, LOG,
				MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_PAYMENT_PREPARATION_DATE_REQUIRED);
	}

	/**
	 * Test insert money transfer batch without pay date.
	 */
	@Test
	public void testInsertMoneyTransferBatchWithoutPayDate()
	{
		MoneyTransferBatchCreateRequest request = new MoneyTransferBatchCreateRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setLocation(CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization()));
		request.getLocation().setId(NUMBER_1);
		request.setPaymentPreparationDate(CommonTestRoutines.createDummyFrequencyBaseEventCalendar());

		mockLocationById();
		mockOrganizationById();

		MoneyTransferBatchResponse response = getMoneyTransferBAI().insertMoneyTransferBatch(request);
		CommonTestRoutines.assertMessages(response, LOG,
				MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_PAY_DATE_REQUIRED);
	}

	/**
	 * Test insert money transfer.
	 */
	@Test
	public void testInsertMoneyTransfer()
	{
		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		CommonTestRoutines.createDummyUserContext(request);
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		request.setTransferSetting(CommonTestRoutines.createDummyTransferSetting());

		InternalResultsResponse<MoneyTransfer> internalResponse =
				new InternalResultsResponse<MoneyTransfer>();
		Mockito.when(getMoneyTransferBAC().createOnOffMoneyTransfer(request)).thenReturn(internalResponse);

		mockMemberById();
		mockRecipientById();
		mockLocationById();

		MoneyTransferResponse response = getMoneyTransferBAI().insertMoneyTransfer(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMoneyTransferBAC()).createOnOffMoneyTransfer(request);
	}

	/**
	 * Test insert money transfer without transfer setting.
	 */
	@Test
	public void testInsertMoneyTransferWithoutTransferSetting()
	{
		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferResponse response = getMoneyTransferBAI().insertMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG,
				MONEY_TRANSFER_CREATE_REQUEST_TRANSFER_SETTING_REQUIRED);
	}

	/**
	 * Test insert money transfer transfer setting not active.
	 */
	@Test
	public void testInsertMoneyTransferTransferSettingNotActive()
	{
		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();
		transferSetting.setStatus(StatusEnum.INACTIVE);
		request.setTransferSetting(transferSetting);
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferResponse response = getMoneyTransferBAI().insertMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG,
				TRANSFER_SETTING_NOT_ACTIVE);
	}

	/**
	 * Test insert money transfer member by id with error.
	 */
	@Test
	public void testInsertMoneyTransferMemberByIdWithError()
	{
		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		request.setTransferSetting(CommonTestRoutines.createDummyTransferSetting());
		CommonTestRoutines.createDummyUserContext(request);

		InternalResultsResponse<Member> memberResponse = new InternalResultsResponse<Member>();
		Member member = CommonTestRoutines.createDummyMember();
		memberResponse.setStatus(Status.NoRowsFoundError);
		memberResponse.addResult(member);

		Mockito.when(
				getMockPersonDAC().fetchMemberById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(memberResponse);

		mockRecipientById();
		mockLocationById();

		MoneyTransferResponse response = getMoneyTransferBAI().insertMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG,
				FETCH_FAILED_ERROR);

		Mockito.verify(getMockPersonDAC()).fetchMemberById(Matchers.any(FetchByIdRequest.class));
	}

	/**
	 * Test insert money transfer withou member id.
	 */
	@Test
	public void testInsertMoneyTransferWithouMemberId()
	{
		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		request.setTransferSetting(CommonTestRoutines.createDummyTransferSetting());
		request.getTransferSetting().setMemberId(null);
		CommonTestRoutines.createDummyUserContext(request);

		mockRecipientById();
		mockLocationById();

		MoneyTransferResponse response = getMoneyTransferBAI().insertMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG,
				NULL_OBJECT_ID_ERROR);
	}

	/**
	 * Test insert money transfer member not active.
	 */
	@Test
	public void testInsertMoneyTransferMemberNotActive()
	{
		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		request.setTransferSetting(CommonTestRoutines.createDummyTransferSetting());
		CommonTestRoutines.createDummyUserContext(request);

		InternalResultsResponse<Member> memberResponse = new InternalResultsResponse<Member>();
		Member member = CommonTestRoutines.createDummyMember();
		member.setPersonStatus(StatusEnum.INACTIVE);
		memberResponse.setStatus(Status.OperationSuccess);
		memberResponse.addResult(member);

		Mockito.when(
				getMockPersonDAC().fetchMemberById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(memberResponse);

		mockRecipientById();
		mockLocationById();

		MoneyTransferResponse response = getMoneyTransferBAI().insertMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG,
				MEMBER_NOT_ACTIVE);

		Mockito.verify(getMockPersonDAC()).fetchMemberById(Matchers.any(FetchByIdRequest.class));
	}

	/**
	 * Test insert money transfer member not document active.
	 */
	@Test
	public void testInsertMoneyTransferMemberNotDocumentActive()
	{
		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		request.setTransferSetting(CommonTestRoutines.createDummyTransferSetting());
		CommonTestRoutines.createDummyUserContext(request);

		InternalResultsResponse<Member> memberResponse = new InternalResultsResponse<Member>();
		Member member = CommonTestRoutines.createDummyMember();
		member.setPersonStatus(StatusEnum.ACTIVE);
		Long expirationDate = member.getDocumentList().get(0).getExpirationDate();
		expirationDate = PGSiDateUtil.subtractDays(expirationDate, NUMBER_1);
		member.getDocumentList().get(0).setExpirationDate(expirationDate);
		memberResponse.setStatus(Status.OperationSuccess);
		memberResponse.addResult(member);

		Mockito.when(
				getMockPersonDAC().fetchMemberById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(memberResponse);

		mockRecipientById();
		mockLocationById();

		MoneyTransferResponse response = getMoneyTransferBAI().insertMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG,
				NO_ACTIVE_ID_DOCUMENT_FOR_MEMBER);

		Mockito.verify(getMockPersonDAC()).fetchMemberById(Matchers.any(FetchByIdRequest.class));
	}

	/**
	 * Test insert money transfer without recipient id.
	 */
	@Test
	public void testInsertMoneyTransferWithoutRecipientId()
	{
		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();
		transferSetting.setRecipientId(null);
		request.setTransferSetting(transferSetting);
		CommonTestRoutines.createDummyUserContext(request);

		mockMemberById();
		mockLocationById();

		MoneyTransferResponse response = getMoneyTransferBAI().insertMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG,
				NULL_OBJECT_ID_ERROR);
	}

	/**
	 * Test insert money transfer recipient by id with error.
	 */
	@Test
	public void testInsertMoneyTransferRecipientByIdWithError()
	{
		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		request.setTransferSetting(CommonTestRoutines.createDummyTransferSetting());
		CommonTestRoutines.createDummyUserContext(request);

		InternalResultsResponse<Recipient> recipientResponse = new InternalResultsResponse<Recipient>();
		recipientResponse.setStatus(Status.OperationSuccess);
		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		recipient.setPersonStatus(StatusEnum.INACTIVE);
		recipientResponse.addResult(recipient);

		Mockito.when(
				getMockPersonDAC().fetchRecipientById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(recipientResponse);

		mockMemberById();
		mockLocationById();

		MoneyTransferResponse response = getMoneyTransferBAI().insertMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG,
				RECIPIENT_NOT_ACTIVE);

		Mockito.verify(getMockPersonDAC()).fetchRecipientById(Matchers.any(FetchByIdRequest.class));
	}

	/**
	 * Test insert money transfer without employment info.
	 */
	@Test
	public void testInsertMoneyTransferWithoutEmploymentInfo()
	{
		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();
		transferSetting.setEmploymentInfo(null);
		request.setTransferSetting(transferSetting);
		CommonTestRoutines.createDummyUserContext(request);

		mockRecipientById();
		mockMemberById();
		mockLocationById();

		MoneyTransferResponse response = getMoneyTransferBAI().insertMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG,
				MONEY_TRANSFER_CREATE_REQUEST_EMPLOYEE_INFO_REQUIRED);

	}

	/**
	 * Test insert money transfer employment info not active.
	 */
	@Test
	public void testInsertMoneyTransferEmploymentInfoNotActive()
	{
		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();
		transferSetting.getEmploymentInfo().setEmploymentInfoStatus(StatusEnum.INACTIVE);
		request.setTransferSetting(transferSetting);
		CommonTestRoutines.createDummyUserContext(request);

		mockRecipientById();
		mockMemberById();
		mockLocationById();

		MoneyTransferResponse response = getMoneyTransferBAI().insertMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG,
				EMPLOYEE_INFO_NOT_ACTIVE);
	}

	/**
	 * Test insert money transfer employment info location by id with error.
	 */
	@Test
	public void testInsertMoneyTransferEmploymentInfoLocationByIdWithError()
	{
		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		request.setTransferSetting(CommonTestRoutines.createDummyTransferSetting());
		CommonTestRoutines.createDummyUserContext(request);

		InternalResultsResponse<Location> locResponse = new InternalResultsResponse<Location>();
		locResponse.setStatus(Status.NoRowsFoundError);

		Mockito.when(
				getMockLocationDAC().fetchLocationById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(locResponse);

		mockRecipientById();
		mockMemberById();

		MoneyTransferResponse response = getMoneyTransferBAI().insertMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG,
				FETCH_FAILED_ERROR);

		Mockito.verify(getMockLocationDAC()).fetchLocationById(Matchers.any(FetchByIdRequest.class));
	}

	/**
	 * Test insert money transfer employment info location not active.
	 */
	@Test
	public void testInsertMoneyTransferEmploymentInfoLocationNotActive()
	{
		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		request.setTransferSetting(CommonTestRoutines.createDummyTransferSetting());
		CommonTestRoutines.createDummyUserContext(request);

		InternalResultsResponse<Location> locResponse = new InternalResultsResponse<Location>();
		locResponse.setStatus(Status.OperationSuccess);
		location.setStatus(StatusEnum.INACTIVE);
		locResponse.addResult(location);

		Mockito.when(
				getMockLocationDAC().fetchLocationById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(locResponse);

		mockRecipientById();
		mockMemberById();

		MoneyTransferResponse response = getMoneyTransferBAI().insertMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG,
				LOCATION_NOT_ACTIVE);

		Mockito.verify(getMockLocationDAC()).fetchLocationById(Matchers.any(FetchByIdRequest.class));
	}

	/**
	 * Test insert money transfer pay preparation out of range.
	 */
	@Test
	public void testInsertMoneyTransferPayPreparationOutOfRange()
	{
		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		request.setTransferSetting(CommonTestRoutines.createDummyTransferSetting());
		Long payPreparationDate = request.getMoneyTransferBatch().getPayPreparationDate();
		payPreparationDate = PGSiDateUtil.subtractDays(payPreparationDate, NUMBER_1);
		request.getMoneyTransferBatch().setPayPreparationDate(payPreparationDate);

		CommonTestRoutines.createDummyUserContext(request);

		mockLocationById();
		mockRecipientById();
		mockMemberById();

		MoneyTransferResponse response = getMoneyTransferBAI().insertMoneyTransfer(request);
		CommonTestRoutines.assertMessages(response, LOG,
				MONEY_TRANSFER_CREATE_REQUEST_VALIDATOR_PAY_PREPARATION_DATE_OUT_OF_RANGE);

		Mockito.verify(getMockLocationDAC()).fetchLocationById(Matchers.any(FetchByIdRequest.class));
	}

	/**
	 * Creates the fetch response for a {@link MoneyTransfer}.
	 *
	 * @param moneyTransfer the money transfer.
	 * @return the internal results response of {@link MoneyTransfer}
	 */
	private InternalResultsResponse<MoneyTransfer> createFetchResponse(MoneyTransfer moneyTransfer)
	{
		InternalResultsResponse<MoneyTransfer> response = new InternalResultsResponse<MoneyTransfer>();
		response.addResult(moneyTransfer);
		return response;
	}

	/**
	 * Creates the fetch response for a {@link MoneyTransferBatch}.
	 *
	 * @param moneyTransferBatch the money transfer batch.
	 * @return the internal results response of {@link MoneyTransferBatch}
	 */
	private InternalResultsResponse<MoneyTransferBatch> createFetchResponse(MoneyTransferBatch moneyTransferBatch)
	{
		InternalResultsResponse<MoneyTransferBatch> response = new InternalResultsResponse<MoneyTransferBatch>();
		response.addResult(moneyTransferBatch);
		return response;
	}

	/**
	 * Creates the fetch response for a {@link MoneyTransferBatchDTO}.
	 *
	 * @param moneyTransferBatchDTO the money transfer batch dto
	 * @return the internal results response of {@link MoneyTransferBatchDTO}
	 */
	private InternalResultsResponse<MoneyTransferBatchDTO> createFetchResponse(
			MoneyTransferBatchDTO moneyTransferBatchDTO)
			{
		InternalResultsResponse<MoneyTransferBatchDTO> response = new InternalResultsResponse<MoneyTransferBatchDTO>();
		response.addResult(moneyTransferBatchDTO);
		return response;
			}

	/**
	 * Mock location by id.
	 */
	private void mockLocationById()
	{
		InternalResultsResponse<Location> response = new InternalResultsResponse<Location>();
		response.setStatus(Status.OperationSuccess);
		response.addResult(CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization()));

		Mockito.when(
				getMockLocationDAC().fetchLocationById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(response);
	}

	/**
	 * Mock organization by id.
	 */
	private void mockOrganizationById()
	{
		InternalResultsResponse<Organization> response = new InternalResultsResponse<Organization>();
		response.setStatus(Status.OperationSuccess);
		response.addResult(CommonTestRoutines.createDummyOrganization());

		Mockito.when(
				getMockOrganizationDAC().fetchOrganizationById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(response);
	}

	/**
	 * Mock member by id.
	 */
	private void mockMemberById()
	{
		InternalResultsResponse<Member> response = new InternalResultsResponse<Member>();
		Member member = CommonTestRoutines.createDummyMember();
		member.setPersonStatus(StatusEnum.ACTIVE);
		Long expirationDate = member.getDocumentList().get(0).getExpirationDate();
		expirationDate = PGSiDateUtil.addDays(expirationDate, NUMBER_1);
		member.getDocumentList().get(0).setExpirationDate(expirationDate);
		response.setStatus(Status.OperationSuccess);
		response.addResult(member);

		Mockito.when(
				getMockPersonDAC().fetchMemberById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(response);
	}

	/**
	 * Mock recipient by id.
	 */
	private void mockRecipientById()
	{
		InternalResultsResponse<Recipient> response = new InternalResultsResponse<Recipient>();
		response.setStatus(Status.OperationSuccess);
		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		recipient.setPersonStatus(StatusEnum.ACTIVE);
		response.addResult(recipient);

		Mockito.when(
				getMockPersonDAC().fetchRecipientById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(response);
	}

}
