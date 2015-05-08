package com.prosperitasglobal.sendsolv.unittest.bac;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.dac.INoteDAC;
import com.prosperitasglobal.cbof.model.Currency;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC;
import com.prosperitasglobal.sendsolv.dac.IDailyCurrencyRateDAC;
import com.prosperitasglobal.sendsolv.dac.IMoneyTransferBatchDAC;
import com.prosperitasglobal.sendsolv.dac.IMoneyTransferDAC;
import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.Account;
import com.prosperitasglobal.sendsolv.model.AccountTypeEnum;
import com.prosperitasglobal.sendsolv.model.DailyCurrencyRate;
import com.prosperitasglobal.sendsolv.model.DailyCurrencyRateDetail;
import com.prosperitasglobal.sendsolv.model.FrequencyBasedEventCalendar;
import com.prosperitasglobal.sendsolv.model.FundingSummaryDTO;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchDTO;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchParticipantId;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatusEnum;
import com.prosperitasglobal.sendsolv.model.MoneyTransferDetail;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipantId;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.request.DailyCurrencyRateInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferAutoApprovalRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchStatusMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferStatusMaintenanceRequest;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.model.response.ResultsSetInfo;
import com.qat.framework.util.UserContextHelper;

/**
 * The Class MoneyTransferBACImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bac/conf/moneytransferimpltest.xml"})
public class MoneyTransferBACImplTest extends AbstractJUnit4SpringContextTests
{
	/** Foreign Currency. */
	private static final String FOREIGN_CURRENCY_MXP = "MXP";

	/** The error set when the dynamic attribute update had problems. */
	private static final String DYNAMIC_ATTRIBUTE_UPDATE_PROBLEM =
			"sendsolv.base.moneytransferbacimpl.dynamic.attribute.update.problem";

	/** Constant account number 123. */
	private static final int ACCOUNT_NUMBER_123 = 123;

	/** Constant BigDecimal 4000.55. */
	private static final BigDecimal NUMBER_4000_55 = new BigDecimal(4000.55);

	/** Constant BigDecimal 8000.55. */
	private static final BigDecimal NUMBER_8000_55 = new BigDecimal(8000.55);

	/** Constant BigDecimal 500. */
	private static final BigDecimal NUMBER_500 = new BigDecimal(500);

	/** Constant BigDecimal 4000. */
	private static final BigDecimal NUMBER_4000 = new BigDecimal(4000);

	/** Constant BigDecimal 2500. */
	private static final BigDecimal NUMBER_2500 = new BigDecimal(2500);

	/** Constant BigDecimal 1000.55. */
	private static final BigDecimal NUMBER_1000_55 = new BigDecimal(1000.55);

	/** The Constant NUMBER_150. */
	private static final BigDecimal NUMBER_150 = new BigDecimal(150);

	/** The log. */
	protected static final Logger LOG = LoggerFactory.getLogger(MoneyTransferBACImplTest.class);

	/** Constant int 10. */
	private static final int DAYS_10 = 10;

	/** Constant Integer 1. */
	private static final Integer ONE = 1;

	/** Constant Integer 2. */
	private static final Integer TWO = 2;

	/** Constant Integer 3. */
	private static final Integer THREE = 3;

	/** Constant Integer 4. */
	private static final Integer FOUR = 4;

	/** Constant Integer 5. */
	private static final Integer FIVE = 5;

	/** The Constant 99999. */
	private static final Integer INT_99999 = 99999;

	/** The money transfer bac. */
	private IMoneyTransferBAC moneyTransferStagingBAC;

	/** The mock money transfer dac. */
	private IMoneyTransferDAC mockMoneyTransferDAC;

	/** The mock money transfer batch dac. */
	private IMoneyTransferBatchDAC mockMoneyTransferBatchDAC;

	/** The participant id dac. */
	private IParticipantIdDAC participantIdDAC;

	/** The mock daily currency rate dac. */
	private IDailyCurrencyRateDAC mockDailyCurrencyRateDAC;

	/** The mock note dac. */
	private INoteDAC mockNoteDAC;

	/** The mock person dac. */
	private IPersonDAC mockPersonDAC;

	/**
	 * Verify money transfer was created correctly.
	 *
	 * @param response The response from the create money transfer.
	 */
	private void assertCreateMoneyTransfer(InternalResultsResponse<MoneyTransfer> response)
	{
		MoneyTransfer moneyTransfer = response.getFirstResult();

		Assert.assertTrue(response.getResultsList().size() == 1);
		Assert.assertNotNull("MoneyTransfer sender account is null!", moneyTransfer.getSenderAccount());
		Assert.assertEquals("MoneyTransfer sender account.type mismatch!", AccountTypeEnum.ACCOUNT_NUMBER,
				moneyTransfer.getSenderAccount().getType());
		Assert.assertEquals("MoneyTransfer sender account.number mismatch!", new Integer(ACCOUNT_NUMBER_123),
				moneyTransfer.getSenderAccount().getNumber());
		Assert.assertNotNull("MoneyTransfer key is null!", moneyTransfer.getKey());
		Assert.assertNull("MoneyTransfer id is not null!", moneyTransfer.getId());
	}

	/**
	 * Compares the created MoneyTransferBatch against expectations.
	 *
	 * @param response The response from the create money transfer batch.
	 */
	private void assertCreateMoneyTransferBatch(InternalResultsResponse<MoneyTransferBatch> response)
	{
		MoneyTransferBatch moneyTransferBatch = response.getFirstResult();

		Assert.assertTrue(response.getResultsList().size() == 1);
		Assert.assertNotNull("MoneyTransferBatch key is null!", moneyTransferBatch.getKey());
		Assert.assertNull("MoneyTransferBatch id is not null!", moneyTransferBatch.getId());
	}

	/**
	 * Compares the result set info to make sure results are as expected.
	 *
	 * @param response The response.
	 * @param expectedPageSize The expected page size.
	 * @param expectedTotalRowsAvailable The expected total rows available.
	 * @param expectedStartPage The expected start page.
	 * @param expectedMoreRowsAvailable The expected more rows available.
	 */
	private void assertInternalResultsResponseResultSetInfo(InternalResultsResponse<?> response, int expectedPageSize,
			int expectedTotalRowsAvailable, int expectedStartPage, boolean expectedMoreRowsAvailable)
	{
		ResultsSetInfo resultSetInfo = response.getResultsSetInfo();

		Assert.assertTrue("Expected moreRowsAvailable " + expectedMoreRowsAvailable
				+ " but contains moreRowsAvailable " + resultSetInfo.isMoreRowsAvailable(),
				expectedMoreRowsAvailable == resultSetInfo.isMoreRowsAvailable());
		Assert.assertTrue(
				"Expected pageSize " + expectedPageSize + " but contains pageSize " + resultSetInfo.getPageSize(),
				expectedPageSize == resultSetInfo.getPageSize());
		Assert.assertTrue(
				"Expected startPage " + expectedStartPage + " but contains startPage " + resultSetInfo.getStartPage(),
				expectedStartPage == resultSetInfo.getStartPage());
		Assert.assertTrue("Expected totalRowsAvailable " + expectedTotalRowsAvailable
				+ " but contains totalRowsAvailable " + resultSetInfo.getTotalRowsAvailable(),
				expectedTotalRowsAvailable == resultSetInfo.getTotalRowsAvailable());
	}

	/**
	 * Obtains the mock money transfer response.
	 *
	 * @param moneyTransferList The list of money transfers.
	 * @return The mock response.
	 */
	private InternalResultsResponse<MoneyTransfer> obtainMockMoneyTransferResponse(
			List<MoneyTransfer> moneyTransferList)
	{
		InternalResultsResponse<MoneyTransfer> response = new InternalResultsResponse<MoneyTransfer>();
		response.addResults(moneyTransferList);
		response.getResultsSetInfo().setTotalRowsAvailable(moneyTransferList.size());
		response.getResultsSetInfo().setMoreRowsAvailable(false);
		response.getResultsSetInfo().setPageSize(moneyTransferList.size());
		response.getResultsSetInfo().setStartPage(1);
		return response;
	}

	/**
	 * Obtains the mock money transfer batch response.
	 *
	 * @param moneyTransferBatchList The list of money transfer batches.
	 * @return The mock response.
	 */
	private InternalResultsResponse<MoneyTransferBatch> obtainMockMoneyTransferBatchResponse(
			List<MoneyTransferBatch> moneyTransferBatchList)
	{
		InternalResultsResponse<MoneyTransferBatch> response = new InternalResultsResponse<MoneyTransferBatch>();
		response.addResults(moneyTransferBatchList);
		response.getResultsSetInfo().setTotalRowsAvailable(moneyTransferBatchList.size());
		response.getResultsSetInfo().setMoreRowsAvailable(false);
		response.getResultsSetInfo().setPageSize(moneyTransferBatchList.size());
		response.getResultsSetInfo().setStartPage(1);
		return response;
	}

	/**
	 * Obtains the mock daily currency rate response.
	 *
	 * @param moneyTransferBatch The money transfer batch.
	 * @return The mock response.
	 */
	private InternalResultsResponse<DailyCurrencyRate> obtainMockDailyCurrencyRateResponse(
			MoneyTransferBatch moneyTransferBatch)
	{
		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();
		transferSetting.setAccount(new Account());
		transferSetting.getAccount().setNumber(1);
		transferSetting.getAccount().setType(AccountTypeEnum.SAVINGS);
		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		request.setMoneyTransferBatch(moneyTransferBatch);
		request.setTransferSetting(transferSetting);

		DailyCurrencyRate rate = new DailyCurrencyRate();
		rate.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		rate.setCreateUser(UserContextHelper.getUserContext().getUserId());
		rate.setCurrency(transferSetting.getProductPlanApplicability().getCurrency());
		rate.setValidForDate(PGSiDateUtil.getCurrentDateMillisUTC());
		rate.setPayerId(transferSetting.getProductPlanApplicability().getPayer().getId());

		DailyCurrencyRateDetail rateDetail = new DailyCurrencyRateDetail();
		rateDetail.setExchangeRate(new BigDecimal("1.78"));
		rateDetail.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		rateDetail.setCreateUser(UserContextHelper.getUserContext().getUserId());

		rate.getDailyCurrencyRateDetailList().add(rateDetail);
		rateDetail = new DailyCurrencyRateDetail();
		rateDetail.setExchangeRate(new BigDecimal("1.76"));
		rateDetail.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		rateDetail.setCreateUser(UserContextHelper.getUserContext().getUserId());

		rate.getDailyCurrencyRateDetailList().add(rateDetail);

		DailyCurrencyRate rate2 = new DailyCurrencyRate();
		rate2.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		rate2.setCreateUser(UserContextHelper.getUserContext().getUserId());
		rate2.setCurrency(transferSetting.getProductPlanApplicability().getCurrency());
		rate2.setValidForDate(PGSiDateUtil.getCurrentDateMillisUTC());
		rate2.setPayerId(transferSetting.getProductPlanApplicability().getPayer().getId());

		rateDetail = new DailyCurrencyRateDetail();
		rateDetail.setExchangeRate(new BigDecimal("1.74"));
		rateDetail.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		rateDetail.setCreateUser(UserContextHelper.getUserContext().getUserId());

		rate2.getDailyCurrencyRateDetailList().add(rateDetail);

		rateDetail = new DailyCurrencyRateDetail();
		rateDetail.setExchangeRate(new BigDecimal("1.72"));
		rateDetail.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		rateDetail.setCreateUser(UserContextHelper.getUserContext().getUserId());

		rate2.getDailyCurrencyRateDetailList().add(rateDetail);

		InternalResultsResponse<DailyCurrencyRate> mockResponse = new InternalResultsResponse<DailyCurrencyRate>();
		mockResponse.addResult(rate);
		mockResponse.addResult(rate2);

		mockResponse.getResultsSetInfo().setMoreRowsAvailable(false);
		mockResponse.getResultsSetInfo().setPageSize(mockResponse.getResultsList().size());
		mockResponse.getResultsSetInfo().setStartPage(1);
		mockResponse.getResultsSetInfo().setTotalRowsAvailable(mockResponse.getResultsList().size());

		return mockResponse;
	}

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getMockMoneyTransferBatchDAC());
		Mockito.reset(getMockMoneyTransferDAC());
		Mockito.reset(getMockDailyCurrencyRateDAC());
		Mockito.reset(getParticipantIdDAC());
	}

	/**
	 * Gets the money transfer bac.
	 *
	 * @return the money transfer bac
	 */
	public IMoneyTransferBAC getMoneyTransferStagingBAC()
	{
		return moneyTransferStagingBAC;
	}

	/**
	 * Gets the participant id dac.
	 *
	 * @return the participant id dac
	 */
	public IParticipantIdDAC getParticipantIdDAC()
	{
		return participantIdDAC;
	}

	/**
	 * Sets the money transfer bac.
	 *
	 * @param moneyTransferStagingBAC the money transfer bac
	 */
	@Resource
	public void setMoneyTransferStagingBAC(IMoneyTransferBAC moneyTransferStagingBAC)
	{
		this.moneyTransferStagingBAC = moneyTransferStagingBAC;
	}

	/**
	 * Gets the mock money transfer batch dac.
	 *
	 * @return the mock money transfer batch dac
	 */
	public IMoneyTransferBatchDAC getMockMoneyTransferBatchDAC()
	{
		return mockMoneyTransferBatchDAC;
	}

	/**
	 * Sets the mock money transfer batch dac.
	 *
	 * @param mockMoneyTransferBatchDAC the mock money transfer batch dac
	 */
	@Resource
	public void setMockMoneyTransferBatchDAC(IMoneyTransferBatchDAC mockMoneyTransferBatchDAC)
	{
		this.mockMoneyTransferBatchDAC = mockMoneyTransferBatchDAC;
	}

	/**
	 * Gets the mock money transfer dac.
	 *
	 * @return the mock money transfer dac
	 */
	public IMoneyTransferDAC getMockMoneyTransferDAC()
	{
		return mockMoneyTransferDAC;
	}

	/**
	 * Sets the mock money transfer dac.
	 *
	 * @param mockMoneyTransferDAC the mock money transfer dac
	 */
	@Resource
	public void setMockMoneyTransferDAC(IMoneyTransferDAC mockMoneyTransferDAC)
	{
		this.mockMoneyTransferDAC = mockMoneyTransferDAC;
	}

	/**
	 * Sets the participant id dac.
	 *
	 * @param participantIdDAC the participant id dac
	 */
	@Resource
	public void setParticipantIdDAC(IParticipantIdDAC participantIdDAC)
	{
		this.participantIdDAC = participantIdDAC;
	}

	/**
	 * Gets the mock daily currency rate dac.
	 *
	 * @return the mock daily currency rate dac
	 */
	public IDailyCurrencyRateDAC getMockDailyCurrencyRateDAC()
	{
		return mockDailyCurrencyRateDAC;
	}

	/**
	 * Sets the mock daily currency rate dac.
	 *
	 * @param mockDailyCurrencyRateDAC the mock daily currency rate dac
	 */
	@Resource
	public void setMockDailyCurrencyRateDAC(IDailyCurrencyRateDAC mockDailyCurrencyRateDAC)
	{
		this.mockDailyCurrencyRateDAC = mockDailyCurrencyRateDAC;
	}

	/**
	 * Get the implementation of the interface {@link INoteDAC}. Injected by Spring.
	 *
	 * @return The implementation of the interface {@link INoteDAC}.
	 */
	public INoteDAC getMockNoteDAC()
	{
		return mockNoteDAC;
	}

	/**
	 * Set the implementation of the interface {@link INoteDAC}. Injected by Spring.
	 *
	 * @param mockNoteDAC The implementation of the interface {@link INoteDAC} to set.
	 */
	@Resource
	public void setMockNoteDAC(INoteDAC mockNoteDAC)
	{
		this.mockNoteDAC = mockNoteDAC;
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
	 * Test successful creation of money transfer.
	 */
	@Test
	public void testCreateMoneyTransfer()
	{
		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);

		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		request.getMoneyTransferBatch().setId(null);
		request.setTransferSetting(CommonTestRoutines.createDummyTransferSetting());
		request.getTransferSetting().setAccount(new Account());
		request.getTransferSetting().getAccount().setType(AccountTypeEnum.SAVINGS);
		request.getTransferSetting().getAccount().setNumber(1);

		Mockito.when(getMockMoneyTransferDAC().insertMoneyTransfer(Matchers.any(MoneyTransfer.class)))
				.thenReturn(new InternalResponse());

		Mockito.when(getParticipantIdDAC().fetchNextMoneyTransferParticipantId()).thenReturn(
				new InternalResultsResponse<MoneyTransferParticipantId>(Arrays
						.asList(new MoneyTransferParticipantId('A', 'A', 1, INT_99999))));

		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferStagingBAC().createMoneyTransfer(request);
		CommonTestRoutines.assertResponse(response);
		assertCreateMoneyTransfer(response);

		Mockito.verify(getMockMoneyTransferDAC()).insertMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getParticipantIdDAC()).fetchNextMoneyTransferParticipantId();
	}

	/**
	 * Test successful creation of money transfer batch.
	 */
	@Test
	public void testCreateMoneyTransferParticipantIdError()
	{
		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);

		MoneyTransferCreateRequest request = new MoneyTransferCreateRequest();
		request.setMoneyTransferBatch(CommonTestRoutines.createDummyMoneyTransferBatch(location));
		request.getMoneyTransferBatch().setId(null);
		request.setTransferSetting(CommonTestRoutines.createDummyTransferSetting());
		request.getTransferSetting().setAccount(new Account());
		request.getTransferSetting().getAccount().setType(AccountTypeEnum.SAVINGS);
		request.getTransferSetting().getAccount().setNumber(1);

		InternalResultsResponse<MoneyTransferParticipantId> mockResponse =
				new InternalResultsResponse<MoneyTransferParticipantId>();
		mockResponse.setStatus(Status.ExceptionError);
		Mockito.when(getParticipantIdDAC().fetchNextMoneyTransferParticipantId()).thenReturn(mockResponse);

		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferStagingBAC().createMoneyTransfer(request);
		Assert.assertEquals(Status.ExceptionError, response.getStatus());

		Mockito.verify(getParticipantIdDAC()).fetchNextMoneyTransferParticipantId();
	}

	/**
	 * Test successful creation of money transfer batch.
	 */
	@Test
	public void testCreateMoneyTransferBatch()
	{
		MoneyTransferBatchCreateRequest request = new MoneyTransferBatchCreateRequest();
		UserContextHelper.setUserContext(CommonTestRoutines.createDummyUserContext());

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		FrequencyBasedEventCalendar paymentPreparationDate = new FrequencyBasedEventCalendar();
		paymentPreparationDate.setEventDate(PGSiDateUtil.getCurrentDateMillisUTC());

		FrequencyBasedEventCalendar payDate = new FrequencyBasedEventCalendar();
		payDate.setEventDate(PGSiDateUtil.addDays(PGSiDateUtil.getCurrentDateMillisUTC(), DAYS_10));

		request.setLocation(location);
		request.setPaymentPreparationDate(paymentPreparationDate);
		request.setPayDate(payDate);
		request.setUserContext(UserContextHelper.getUserContext());

		Mockito.when(getMockMoneyTransferBatchDAC().insertMoneyTransferBatch(Matchers.any(MoneyTransferBatch.class)))
				.thenReturn(new InternalResponse());

		Mockito.when(getParticipantIdDAC().fetchNextMoneyTransferBatchParticipantId()).thenReturn(
				new InternalResultsResponse<MoneyTransferBatchParticipantId>(Arrays
						.asList(new MoneyTransferBatchParticipantId('A', 'A', 1, INT_99999))));

		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferStagingBAC().createMoneyTransferBatch(request);

		CommonTestRoutines.assertResponse(response);
		assertCreateMoneyTransferBatch(response);

		Mockito.verify(getMockMoneyTransferBatchDAC()).insertMoneyTransferBatch(Matchers.any(MoneyTransferBatch.class));
		Mockito.verify(getParticipantIdDAC()).fetchNextMoneyTransferBatchParticipantId();
	}

	/**
	 * Test successful creation of money transfer batch.
	 */
	@Test
	public void testCreateMoneyTransferBatchParticipantIdError()
	{
		MoneyTransferBatchCreateRequest request = new MoneyTransferBatchCreateRequest();
		UserContextHelper.setUserContext(CommonTestRoutines.createDummyUserContext());

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		FrequencyBasedEventCalendar paymentPreparationDate = new FrequencyBasedEventCalendar();
		paymentPreparationDate.setEventDate(PGSiDateUtil.getCurrentDateMillisUTC());

		FrequencyBasedEventCalendar payDate = new FrequencyBasedEventCalendar();
		payDate.setEventDate(PGSiDateUtil.addDays(PGSiDateUtil.getCurrentDateMillisUTC(), DAYS_10));

		request.setLocation(location);
		request.setPaymentPreparationDate(paymentPreparationDate);
		request.setPayDate(payDate);
		request.setUserContext(UserContextHelper.getUserContext());

		InternalResultsResponse<MoneyTransferBatchParticipantId> mockResponse =
				new InternalResultsResponse<MoneyTransferBatchParticipantId>();
		mockResponse.setStatus(Status.ExceptionError);
		Mockito.when(getParticipantIdDAC().fetchNextMoneyTransferBatchParticipantId()).thenReturn(mockResponse);

		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferStagingBAC().createMoneyTransferBatch(request);

		Assert.assertEquals(Status.ExceptionError, response.getStatus());

		Mockito.verify(getParticipantIdDAC()).fetchNextMoneyTransferBatchParticipantId();
	}

	/**
	 * Test insert money transfer.
	 */
	@Test
	public void testInsertMoneyTransfer()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		moneyTransferBatch.setLocation(location);

		MoneyTransfer moneyTransfer =
				CommonTestRoutines.createDummyMoneyTransfer(moneyTransferBatch,
						CommonTestRoutines.createDummyTransferSetting());
		request.setMoneyTransfer(moneyTransfer);

		Mockito.when(getMockMoneyTransferDAC().insertMoneyTransfer(moneyTransfer)).thenReturn(new InternalResponse());

		InternalResponse response = getMoneyTransferStagingBAC().insertMoneyTransfer(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockMoneyTransferDAC()).insertMoneyTransfer(moneyTransfer);

	}

	/**
	 * Test insert money transfer with error.
	 */
	@Test
	public void testInsertMoneyTransferWithError()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		moneyTransferBatch.setLocation(location);

		MoneyTransfer moneyTransfer = CommonTestRoutines.createDummyMoneyTransfer(moneyTransferBatch,
				CommonTestRoutines.createDummyTransferSetting());
		request.setMoneyTransfer(moneyTransfer);

		Mockito.when(getMockMoneyTransferDAC().insertMoneyTransfer(moneyTransfer)).thenReturn(
				new InternalResponse(Status.NoRowsInsertedError));

		InternalResponse response = getMoneyTransferStagingBAC().insertMoneyTransfer(request);

		Assert.assertEquals(Status.NoRowsInsertedError, response.getStatus());

		Mockito.verify(getMockMoneyTransferDAC()).insertMoneyTransfer(moneyTransfer);

	}

	/**
	 * Test insert money transfer batch.
	 */
	@Test
	public void testInsertMoneyTransferBatch()
	{
		MoneyTransferBatchMaintenanceRequest request = new MoneyTransferBatchMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		moneyTransferBatch.setLocation(location);
		request.setMoneyTransferBatch(moneyTransferBatch);

		Mockito.when(getMockMoneyTransferBatchDAC().insertMoneyTransferBatch(moneyTransferBatch)).thenReturn(
				new InternalResponse());

		InternalResponse response = getMoneyTransferStagingBAC().insertMoneyTransferBatch(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockMoneyTransferBatchDAC()).insertMoneyTransferBatch(moneyTransferBatch);

	}

	/**
	 * Test insert money transfer batch with error.
	 */
	@Test
	public void testInsertMoneyTransferBatchWithError()
	{
		MoneyTransferBatchMaintenanceRequest request = new MoneyTransferBatchMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		moneyTransferBatch.setLocation(location);
		request.setMoneyTransferBatch(moneyTransferBatch);

		Mockito.when(getMockMoneyTransferBatchDAC().insertMoneyTransferBatch(moneyTransferBatch)).thenReturn(
				new InternalResponse(Status.NoRowsInsertedError));

		Mockito.when(getParticipantIdDAC().fetchNextMoneyTransferBatchParticipantId()).thenReturn(
				new InternalResultsResponse<MoneyTransferBatchParticipantId>(Arrays
						.asList(new MoneyTransferBatchParticipantId('A', 'A', 1, INT_99999))));

		InternalResponse response = getMoneyTransferStagingBAC().insertMoneyTransferBatch(request);

		Assert.assertEquals(Status.NoRowsInsertedError, response.getStatus());

		Mockito.verify(getMockMoneyTransferBatchDAC()).insertMoneyTransferBatch(moneyTransferBatch);

	}

	/**
	 * Test update money transfer.
	 */
	@Test
	public void testUpdateMoneyTransfer()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		moneyTransferBatch.setLocation(location);

		MoneyTransfer moneyTransfer =
				CommonTestRoutines.createDummyMoneyTransfer(moneyTransferBatch,
						CommonTestRoutines.createDummyTransferSetting());
		request.setMoneyTransfer(moneyTransfer);

		Mockito.when(getMockMoneyTransferDAC().updateMoneyTransfer(moneyTransfer)).thenReturn(new InternalResponse());

		InternalResponse response = getMoneyTransferStagingBAC().updateMoneyTransfer(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockMoneyTransferDAC()).updateMoneyTransfer(moneyTransfer);
	}

	/**
	 * Test update money transfer.
	 */
	@Test
	public void testUpdateMoneyTransferApproved()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		moneyTransferBatch.setLocation(location);

		MoneyTransfer moneyTransfer =
				CommonTestRoutines.createDummyMoneyTransfer(moneyTransferBatch,
						CommonTestRoutines.createDummyTransferSetting(), MoneyTransferStatusEnum.APPROVED);
		request.setMoneyTransfer(moneyTransfer);

		Mockito.when(getMockMoneyTransferDAC().updateMoneyTransfer(moneyTransfer)).thenReturn(new InternalResponse());

		InternalResponse response = getMoneyTransferStagingBAC().updateMoneyTransfer(request);

		CommonTestRoutines.assertResponse(response);
		Assert.assertEquals(request.getUserContext().getUserId(), moneyTransfer.getReleaseUser());

		Mockito.verify(getMockMoneyTransferDAC()).updateMoneyTransfer(moneyTransfer);
	}

	/**
	 * Test update money transfer with error.
	 */
	@Test
	public void testUpdateMoneyTransferWithError()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		moneyTransferBatch.setLocation(location);

		MoneyTransfer moneyTransfer =
				CommonTestRoutines.createDummyMoneyTransfer(moneyTransferBatch,
						CommonTestRoutines.createDummyTransferSetting());
		request.setMoneyTransfer(moneyTransfer);

		Mockito.when(getMockMoneyTransferDAC().updateMoneyTransfer(moneyTransfer)).thenReturn(
				new InternalResponse(Status.NoRowsUpdatedError));
		InternalResponse response = getMoneyTransferStagingBAC().updateMoneyTransfer(request);

		Assert.assertEquals(Status.NoRowsUpdatedError, response.getStatus());

		Mockito.verify(getMockMoneyTransferDAC()).updateMoneyTransfer(moneyTransfer);
	}

	/**
	 * Test update money transfer batch.
	 */
	@Test
	public void testUpdateMoneyTransferBatch()
	{
		MoneyTransferBatchMaintenanceRequest request = new MoneyTransferBatchMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		moneyTransferBatch.setLocation(location);
		request.setMoneyTransferBatch(moneyTransferBatch);

		Mockito.when(getMockMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch)).thenReturn(
				new InternalResponse());

		InternalResponse response = getMoneyTransferStagingBAC().updateMoneyTransferBatch(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockMoneyTransferBatchDAC()).updateMoneyTransferBatch(moneyTransferBatch);
	}

	/**
	 * Test update money transfer batch.
	 */
	@Test
	public void testUpdateMoneyTransferBatchReleased()
	{
		MoneyTransferBatchMaintenanceRequest request = new MoneyTransferBatchMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

		MoneyTransferBatch moneyTransferBatch =
				CommonTestRoutines.createDummyMoneyTransferBatch(location, MoneyTransferBatchStatusEnum.RELEASED);
		moneyTransferBatch.setLocation(location);
		request.setMoneyTransferBatch(moneyTransferBatch);

		Mockito.when(getMockMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch)).thenReturn(
				new InternalResponse());

		InternalResponse response = getMoneyTransferStagingBAC().updateMoneyTransferBatch(request);

		CommonTestRoutines.assertResponse(response);
		Assert.assertEquals(request.getUserContext().getUserId(), moneyTransferBatch.getReleaseUser());

		Mockito.verify(getMockMoneyTransferBatchDAC()).updateMoneyTransferBatch(moneyTransferBatch);
	}

	/**
	 * Test update money transfer batch with error.
	 */
	@Test
	public void testUpdateMoneyTransferBatchWithError()
	{
		MoneyTransferBatchMaintenanceRequest request = new MoneyTransferBatchMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		moneyTransferBatch.setLocation(location);
		request.setMoneyTransferBatch(moneyTransferBatch);

		Mockito.when(getMockMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch)).thenReturn(
				new InternalResponse(Status.NoRowsUpdatedError));
		InternalResponse response = getMoneyTransferStagingBAC().updateMoneyTransferBatch(request);

		Assert.assertEquals(Status.NoRowsUpdatedError, response.getStatus());

		Mockito.verify(getMockMoneyTransferBatchDAC()).updateMoneyTransferBatch(moneyTransferBatch);
	}

	/**
	 * Test fetch money transfer by request.
	 */
	@Test
	public void testFetchMoneyTransferByRequest()
	{
		MoneyTransferInquiryRequest request = new MoneyTransferInquiryRequest();

		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);
		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);

		/* Mockito Items. */
		Mockito.when(getMockMoneyTransferDAC().fetchMoneyTransferByRequest(request)).thenReturn(
				obtainMockMoneyTransferResponse(Arrays.asList(moneyTransferBatch.getMoneyTransferList().get(0),
						moneyTransferBatch.getMoneyTransferList().get(0))));

		Mockito.when(
				getMockDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(
						Matchers.any(DailyCurrencyRateInquiryRequest.class))).thenReturn(
				new InternalResultsResponse<DailyCurrencyRate>());

		InternalResultsResponse<MoneyTransfer> mockResponse = new InternalResultsResponse<MoneyTransfer>();
		mockResponse.addResult(moneyTransferBatch.getMoneyTransferList().get(0));

		Mockito.when(
				getMockMoneyTransferDAC().updateFetchMoneyTransfer(moneyTransferBatch.getMoneyTransferList().get(0)))
				.thenReturn(mockResponse);

		/* Actual Test. */
		InternalResultsResponse<MoneyTransfer> response =
				getMoneyTransferStagingBAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertInternalResultsResponseResultSetInfo(response, 2, 2, 1, false);

		/* Mockito Items. */
		Mockito.verify(getMockMoneyTransferDAC()).fetchMoneyTransferByRequest(request);

		Mockito.verify(getMockMoneyTransferDAC(), Mockito.times(2)).updateFetchMoneyTransfer(
				moneyTransferBatch.getMoneyTransferList().get(0));

		Mockito.verify(getMockDailyCurrencyRateDAC(), Mockito.times(FOUR)).fetchDailyCurrencyRateByRequest(
				Matchers.any(DailyCurrencyRateInquiryRequest.class));
	}

	/**
	 * Test fetch money transfer auto approval by transfer setting.
	 */
	@Test
	public void testFetchMoneyTransferAutoApprovalByTransferSetting()
	{
		/*
		 * On this test, these are the scenarios that is going to be tested:
		 * - Scenario1: Previous Transaction has the status == APPROVED
		 * - Scenario2: Previous Transaction has the same value
		 * - Scenario3: Net Pay greater than transaction value
		 * - Scenario4: Previous transaction == APPROVED and Previous Transaction with the same amount
		 * - Scenario5: Previous transaction == APPROVED and Previous Transaction with the same amount and net pay
		 * greater than transfer value
		 * - Scenario6: Previous transaction == APPROVED and net pay greater than transfer value
		 */

		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(new Location());
		MoneyTransferAutoApprovalRequest request = new MoneyTransferAutoApprovalRequest();
		request.setMoneyTransferBatchId(moneyTransferBatch.getId());

		/*
		 * SCENARIO 1
		 */
		request.setIsNetPayGreaterTransValue(Boolean.FALSE);
		request.setIsSameAmountPreviousTransaction(Boolean.FALSE);
		request.setIsPreviousTransactionApproved(Boolean.TRUE);

		assertMoneyTranferAutoApprovalBasedOnScenario(moneyTransferBatch, MoneyTransferStatusEnum.PENDING_APPROVAL,
				MoneyTransferStatusEnum.APPROVED, NUMBER_1000_55, NUMBER_4000, request);

		/*
		 * SCENARIO 2
		 */
		request.setIsNetPayGreaterTransValue(Boolean.FALSE);
		request.setIsSameAmountPreviousTransaction(Boolean.TRUE);
		request.setIsPreviousTransactionApproved(Boolean.FALSE);

		assertMoneyTranferAutoApprovalBasedOnScenario(moneyTransferBatch, MoneyTransferStatusEnum.PENDING_APPROVAL,
				MoneyTransferStatusEnum.ORDER_SUBMITTED, NUMBER_1000_55, NUMBER_1000_55, request);

		/*
		 * SCENARIO 3
		 */
		request.setIsNetPayGreaterTransValue(Boolean.TRUE);
		request.setIsSameAmountPreviousTransaction(Boolean.FALSE);
		request.setIsPreviousTransactionApproved(Boolean.FALSE);

		assertMoneyTranferAutoApprovalBasedOnScenario(moneyTransferBatch, MoneyTransferStatusEnum.PENDING_APPROVAL,
				MoneyTransferStatusEnum.PENDING_APPROVAL, NUMBER_150, NUMBER_1000_55, request);

		/*
		 * SCENARIO 4
		 */
		request.setIsNetPayGreaterTransValue(Boolean.FALSE);
		request.setIsSameAmountPreviousTransaction(Boolean.TRUE);
		request.setIsPreviousTransactionApproved(Boolean.TRUE);

		assertMoneyTranferAutoApprovalBasedOnScenario(moneyTransferBatch, MoneyTransferStatusEnum.PENDING_APPROVAL,
				MoneyTransferStatusEnum.APPROVED, NUMBER_1000_55, NUMBER_1000_55, request);

		/*
		 * SCENARIO 5
		 */
		request.setIsNetPayGreaterTransValue(Boolean.TRUE);
		request.setIsSameAmountPreviousTransaction(Boolean.TRUE);
		request.setIsPreviousTransactionApproved(Boolean.TRUE);

		assertMoneyTranferAutoApprovalBasedOnScenario(moneyTransferBatch, MoneyTransferStatusEnum.PENDING_APPROVAL,
				MoneyTransferStatusEnum.APPROVED, NUMBER_150, NUMBER_150, request);

		/*
		 * SCENARIO 6
		 */
		request.setIsNetPayGreaterTransValue(Boolean.TRUE);
		request.setIsSameAmountPreviousTransaction(Boolean.FALSE);
		request.setIsPreviousTransactionApproved(Boolean.TRUE);

		assertMoneyTranferAutoApprovalBasedOnScenario(moneyTransferBatch, MoneyTransferStatusEnum.PENDING_APPROVAL,
				MoneyTransferStatusEnum.APPROVED, NUMBER_150, NUMBER_2500, request);
	}

	/**
	 * Test fetch money transfer by request with error.
	 */
	@Test
	public void testFetchMoneyTransferByRequestWithError()
	{
		MoneyTransferInquiryRequest request = new MoneyTransferInquiryRequest();
		InternalResultsResponse<MoneyTransfer> response = new InternalResultsResponse<MoneyTransfer>();
		response.setStatus(Status.NoRowsFoundError);

		/* Mockito Items. */
		Mockito.when(getMockMoneyTransferDAC().fetchMoneyTransferByRequest(request)).thenReturn(response);

		/* Actual Test. */
		response = getMoneyTransferStagingBAC().fetchMoneyTransferByRequest(request);
		Assert.assertEquals(Status.NoRowsFoundError, response.getStatus());

		/* Mockito Items. */
		Mockito.verify(getMockMoneyTransferDAC()).fetchMoneyTransferByRequest(request);
	}

	/**
	 * Test fetch money transfer batch by request.
	 */
	@Test
	public void testFetchMoneyTransferBatchByRequest()
	{
		MoneyTransferBatchInquiryRequest request = new MoneyTransferBatchInquiryRequest();

		/* Mockito Items. */
		Mockito.when(getMockMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request)).thenReturn(
				new InternalResultsResponse<MoneyTransferBatch>(Arrays.asList(new MoneyTransferBatch(),
						new MoneyTransferBatch(), new MoneyTransferBatch(), new MoneyTransferBatch())));

		/* Actual Test. */
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferStagingBAC().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		/* Mockito Items. */
		Mockito.verify(getMockMoneyTransferBatchDAC()).fetchMoneyTransferBatchByRequest(request);
	}

	/**
	 * Test fetch money transfer batch by request with error.
	 */
	@Test
	public void testFetchMoneyTransferBatchByRequestWithError()
	{
		MoneyTransferBatchInquiryRequest request = new MoneyTransferBatchInquiryRequest();
		InternalResultsResponse<MoneyTransferBatch> response = new InternalResultsResponse<MoneyTransferBatch>();
		response.setStatus(Status.NoRowsFoundError);

		/* Mockito Items. */
		Mockito.when(getMockMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request)).thenReturn(response);

		/* Actual Test. */
		response = getMoneyTransferStagingBAC().fetchMoneyTransferBatchByRequest(request);
		Assert.assertEquals(Status.NoRowsFoundError, response.getStatus());

		/* Mockito Items. */
		Mockito.verify(getMockMoneyTransferBatchDAC()).fetchMoneyTransferBatchByRequest(request);
	}

	/**
	 * Test fetch money transfer by id.
	 */
	@Test
	public void testFetchMoneyTransferById()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);
		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);

		/* Mockito Items. */
		Mockito.when(getMockMoneyTransferDAC().fetchMoneyTransferById(request.getId())).thenReturn(
				obtainMockMoneyTransferResponse(Arrays.asList(moneyTransferBatch.getMoneyTransferList().get(0))));

		Mockito.when(
				getMockDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(
						Matchers.any(DailyCurrencyRateInquiryRequest.class))).thenReturn(
				obtainMockDailyCurrencyRateResponse(moneyTransferBatch));

		Mockito.when(
				getMockMoneyTransferDAC().updateFetchMoneyTransfer(moneyTransferBatch.getMoneyTransferList().get(0)))
				.thenReturn(obtainMockMoneyTransferResponse(Arrays.asList(
						moneyTransferBatch.getMoneyTransferList().get(0))));

		/* Actual Test. */
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferStagingBAC().fetchMoneyTransferById(request);
		CommonTestRoutines.assertResultResponse(response);
		assertInternalResultsResponseResultSetInfo(response, 1, 1, 1, false);

		/* Mockito Items. */
		Mockito.verify(getMockMoneyTransferDAC()).fetchMoneyTransferById(request.getId());

		Mockito.verify(getMockDailyCurrencyRateDAC()).fetchDailyCurrencyRateByRequest(
				Matchers.any(DailyCurrencyRateInquiryRequest.class));

	}

	/**
	 * Test fetch money transfer by id.
	 */
	@Test
	public void testFetchMoneyTransferByIdUpdateFetchError()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);
		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);

		/* Mockito Items. */
		Mockito.when(getMockMoneyTransferDAC().fetchMoneyTransferById(request.getId())).thenReturn(
				obtainMockMoneyTransferResponse(Arrays.asList(moneyTransferBatch.getMoneyTransferList().get(0))));

		Mockito.when(
				getMockDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(
						Matchers.any(DailyCurrencyRateInquiryRequest.class))).thenReturn(
				obtainMockDailyCurrencyRateResponse(moneyTransferBatch));

		InternalResultsResponse<MoneyTransfer> mockResponse = new InternalResultsResponse<MoneyTransfer>();
		mockResponse.setStatus(Status.ExceptionError);
		Mockito.when(
				getMockMoneyTransferDAC().updateFetchMoneyTransfer(moneyTransferBatch.getMoneyTransferList().get(0)))
				.thenReturn(mockResponse);

		/* Actual Test. */
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferStagingBAC().fetchMoneyTransferById(request);
		Assert.assertEquals(Status.ExceptionError, response.getStatus());
		CommonTestRoutines.assertMessagesInfo(response.getMessageInfoList(), LOG,
				DYNAMIC_ATTRIBUTE_UPDATE_PROBLEM);

		/* Mockito Items. */
		Mockito.verify(getMockMoneyTransferDAC()).fetchMoneyTransferById(request.getId());

		Mockito.verify(getMockDailyCurrencyRateDAC()).fetchDailyCurrencyRateByRequest(
				Matchers.any(DailyCurrencyRateInquiryRequest.class));

		Mockito.verify(getMockMoneyTransferDAC()).updateFetchMoneyTransfer(Matchers.any(MoneyTransfer.class));

	}

	/**
	 * Test fetch money transfer by id.
	 */
	@Test
	public void testFetchMoneyTransferByIdNoDailyCurrencyRateDetailFound()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);
		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		moneyTransferBatch.getMoneyTransferList().get(0).getTransferSetting().getProductPlanApplicability()
				.setCurrency(new Currency(FOREIGN_CURRENCY_MXP));

		/* Mockito Items. */
		Mockito.when(getMockMoneyTransferDAC().fetchMoneyTransferById(request.getId())).thenReturn(
				obtainMockMoneyTransferResponse(Arrays.asList(moneyTransferBatch.getMoneyTransferList().get(0))));

		InternalResultsResponse<DailyCurrencyRate> mockResponse =
				obtainMockDailyCurrencyRateResponse(moneyTransferBatch);
		mockResponse.getResultsList().get(0).setDailyCurrencyRateDetailList(new ArrayList<DailyCurrencyRateDetail>());
		mockResponse.getResultsList().get(1).setDailyCurrencyRateDetailList(new ArrayList<DailyCurrencyRateDetail>());

		Mockito.when(
				getMockDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(
						Matchers.any(DailyCurrencyRateInquiryRequest.class))).thenReturn(mockResponse);

		/* Actual Test. */
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferStagingBAC().fetchMoneyTransferById(request);
		CommonTestRoutines.assertResultResponse(response);
		assertInternalResultsResponseResultSetInfo(response, 1, 1, 1, false);

		/* Mockito Items. */
		Mockito.verify(getMockMoneyTransferDAC()).fetchMoneyTransferById(request.getId());

		Mockito.verify(getMockDailyCurrencyRateDAC()).fetchDailyCurrencyRateByRequest(
				Matchers.any(DailyCurrencyRateInquiryRequest.class));

	}

	/**
	 * Test fetch money transfer by id.
	 */
	@Test
	public void testFetchMoneyTransferByIdErrorDailyCurrencyRate()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		InternalResultsResponse<DailyCurrencyRate> mockResponse = new InternalResultsResponse<DailyCurrencyRate>();
		mockResponse.setStatus(Status.ExceptionError);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);
		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);

		moneyTransferBatch.getMoneyTransferList().get(0).getTransferSetting().getProductPlanApplicability()
				.setCurrency(new Currency(FOREIGN_CURRENCY_MXP));

		/* Mockito Items. */
		Mockito.when(getMockMoneyTransferDAC().fetchMoneyTransferById(request.getId())).thenReturn(
				obtainMockMoneyTransferResponse(Arrays.asList(moneyTransferBatch.getMoneyTransferList().get(0))));

		Mockito.when(getMockDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(
				Matchers.any(DailyCurrencyRateInquiryRequest.class))).thenReturn(mockResponse);

		/* Actual Test. */
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferStagingBAC().fetchMoneyTransferById(request);
		CommonTestRoutines.assertResultResponse(response);
		assertInternalResultsResponseResultSetInfo(response, 1, 1, 1, false);

		/* Mockito Items. */
		Mockito.verify(getMockMoneyTransferDAC()).fetchMoneyTransferById(request.getId());

		Mockito.verify(getMockDailyCurrencyRateDAC(), Mockito.times(2)).fetchDailyCurrencyRateByRequest(
				Matchers.any(DailyCurrencyRateInquiryRequest.class));

	}

	/**
	 * Test fetch money transfer by id.
	 */
	@Test
	public void testFetchMoneyTransferByIdDailyCurrencyRateNotFound()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);
		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);

		moneyTransferBatch.getMoneyTransferList().get(0).getTransferSetting().getProductPlanApplicability()
				.setCurrency(new Currency(FOREIGN_CURRENCY_MXP));

		/* Mockito Items. */
		Mockito.when(getMockMoneyTransferDAC().fetchMoneyTransferById(request.getId())).thenReturn(
				obtainMockMoneyTransferResponse(Arrays.asList(moneyTransferBatch.getMoneyTransferList().get(0))));

		Mockito.when(getMockDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(
				Matchers.any(DailyCurrencyRateInquiryRequest.class))).thenReturn(
				new InternalResultsResponse<DailyCurrencyRate>());

		/* Actual Test. */
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferStagingBAC().fetchMoneyTransferById(request);
		CommonTestRoutines.assertResultResponse(response);
		assertInternalResultsResponseResultSetInfo(response, 1, 1, 1, false);

		/* Mockito Items. */
		Mockito.verify(getMockMoneyTransferDAC()).fetchMoneyTransferById(request.getId());

		Mockito.verify(getMockDailyCurrencyRateDAC(), Mockito.times(2)).fetchDailyCurrencyRateByRequest(
				Matchers.any(DailyCurrencyRateInquiryRequest.class));

	}

	/**
	 * Test fetch money transfer by id with error.
	 */
	@Test
	public void testFetchMoneyTransferByIdWithError()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		InternalResultsResponse<MoneyTransfer> response = new InternalResultsResponse<MoneyTransfer>();
		response.setStatus(Status.NoRowsFoundError);

		/* Mockito Items. */
		Mockito.when(getMockMoneyTransferDAC().fetchMoneyTransferById(request.getId())).thenReturn(response);

		/* Actual Test. */
		response = getMoneyTransferStagingBAC().fetchMoneyTransferById(request);
		Assert.assertEquals(Status.NoRowsFoundError, response.getStatus());

		/* Mockito Items. */
		Mockito.verify(getMockMoneyTransferDAC()).fetchMoneyTransferById(request.getId());
	}

	/**
	 * Test fetch money transfer batch by id.
	 */
	@Test
	public void testFetchMoneyTransferBatchById()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		/* Mockito Items. */
		Mockito.when(getMockMoneyTransferBatchDAC().fetchMoneyTransferBatchById(request.getId())).thenReturn(
				obtainMockMoneyTransferBatchResponse(Arrays.asList(new MoneyTransferBatch())));

		/* Actual Test. */
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferStagingBAC().fetchMoneyTransferBatchById(request);
		CommonTestRoutines.assertResultResponse(response);
		assertInternalResultsResponseResultSetInfo(response, 1, 1, 1, false);

		/* Mockito Items. */
		Mockito.verify(getMockMoneyTransferBatchDAC()).fetchMoneyTransferBatchById(request.getId());
	}

	/**
	 * Test fetch money transfer batch by id.
	 */
	@Test
	public void testFetchMoneyTransferBatchByIdUpdateFetchError()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);
		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);

		/* Mockito Items. */
		Mockito.when(getMockMoneyTransferBatchDAC().fetchMoneyTransferBatchById(request.getId())).thenReturn(
				obtainMockMoneyTransferBatchResponse(Arrays.asList(moneyTransferBatch)));

		Mockito.when(
				getMockDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(
						Matchers.any(DailyCurrencyRateInquiryRequest.class))).thenReturn(
				obtainMockDailyCurrencyRateResponse(moneyTransferBatch));

		InternalResultsResponse<MoneyTransfer> mockResponse = new InternalResultsResponse<MoneyTransfer>();
		mockResponse.setStatus(Status.ExceptionError);
		Mockito.when(
				getMockMoneyTransferDAC().updateFetchMoneyTransfer(moneyTransferBatch.getMoneyTransferList().get(0)))
				.thenReturn(mockResponse);

		/* Actual Test. */
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferStagingBAC().fetchMoneyTransferBatchById(request);

		Assert.assertEquals(Status.ExceptionError, response.getStatus());
		CommonTestRoutines.assertMessagesInfo(response.getMessageInfoList(), LOG,
				DYNAMIC_ATTRIBUTE_UPDATE_PROBLEM);

		/* Mockito Items. */
		Mockito.verify(getMockMoneyTransferBatchDAC()).fetchMoneyTransferBatchById(request.getId());

		Mockito.verify(getMockDailyCurrencyRateDAC()).fetchDailyCurrencyRateByRequest(
				Matchers.any(DailyCurrencyRateInquiryRequest.class));

		Mockito.verify(getMockMoneyTransferDAC()).updateFetchMoneyTransfer(Matchers.any(MoneyTransfer.class));
	}

	/**
	 * Test fetch money transfer batch by id.
	 */
	@Test
	public void testFetchMoneyTransferBatchByIdNoUpdateFetch()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);
		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		MoneyTransferStatus status = new MoneyTransferStatus();
		status.setStatus(MoneyTransferStatusEnum.ORDER_SUBMITTED);
		moneyTransferBatch.getMoneyTransferList().get(0).addMoneyTransferStatus(status);

		/* Mockito Items. */
		Mockito.when(getMockMoneyTransferBatchDAC().fetchMoneyTransferBatchById(request.getId())).thenReturn(
				obtainMockMoneyTransferBatchResponse(Arrays.asList(moneyTransferBatch)));

		InternalResultsResponse<DailyCurrencyRate> mockRateResponse = new InternalResultsResponse<DailyCurrencyRate>();
		Mockito.when(
				getMockDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(
						Matchers.any(DailyCurrencyRateInquiryRequest.class))).thenReturn(
				mockRateResponse);

		/* Actual Test. */
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferStagingBAC().fetchMoneyTransferBatchById(request);

		CommonTestRoutines.assertResultResponse(response);
		assertInternalResultsResponseResultSetInfo(response, 1, 1, 1, false);

		/* Mockito Items. */
		Mockito.verify(getMockMoneyTransferBatchDAC()).fetchMoneyTransferBatchById(request.getId());

		Mockito.verify(getMockDailyCurrencyRateDAC(), Mockito.times(2)).fetchDailyCurrencyRateByRequest(
				Matchers.any(DailyCurrencyRateInquiryRequest.class));
	}

	/**
	 * Test fetch money transfer batch by id with error.
	 */
	@Test
	public void testFetchMoneyTransferBatchByIdWithError()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);
		InternalResultsResponse<MoneyTransferBatch> response = new InternalResultsResponse<MoneyTransferBatch>();
		response.setStatus(Status.NoRowsFoundError);

		/* Mockito Items. */
		Mockito.when(getMockMoneyTransferBatchDAC().fetchMoneyTransferBatchById(request.getId())).thenReturn(response);

		/* Actual Test. */
		response = getMoneyTransferStagingBAC().fetchMoneyTransferBatchById(request);
		Assert.assertEquals(Status.NoRowsFoundError, response.getStatus());

		/* Mockito Items. */
		Mockito.verify(getMockMoneyTransferBatchDAC()).fetchMoneyTransferBatchById(request.getId());
	}

	/**
	 * Test delete money transfer.
	 */
	@Test
	public void testDeleteMoneyTransfer()
	{
		MoneyTransfer moneyTransfer = new MoneyTransfer();
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(moneyTransfer);

		Mockito.when(getMockMoneyTransferDAC().deleteMoneyTransfer(moneyTransfer)).thenReturn(new InternalResponse());

		InternalResponse response = getMoneyTransferStagingBAC().deleteMoneyTransfer(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockMoneyTransferDAC()).deleteMoneyTransfer(moneyTransfer);
	}

	/**
	 * Test delete money transfer with error.
	 */
	@Test
	public void testDeleteMoneyTransferWithError()
	{
		MoneyTransfer moneyTransfer = new MoneyTransfer();
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(moneyTransfer);

		Mockito.when(getMockMoneyTransferDAC().deleteMoneyTransfer(moneyTransfer)).thenReturn(new InternalResponse());

		Mockito.when(getMockMoneyTransferDAC().deleteMoneyTransfer(moneyTransfer)).thenReturn(
				new InternalResponse(Status.NoRowsRemovedError));

		InternalResponse results = getMoneyTransferStagingBAC().deleteMoneyTransfer(request);

		Assert.assertEquals(Status.NoRowsRemovedError, results.getStatus());

		Mockito.verify(getMockMoneyTransferDAC()).deleteMoneyTransfer(moneyTransfer);
	}

	/**
	 * Test delete money transfer batch.
	 */
	@Test
	public void testDeleteMoneyTransferBatch()
	{
		MoneyTransferBatch moneyTransferBatch = new MoneyTransferBatch();
		MoneyTransferBatchMaintenanceRequest request = new MoneyTransferBatchMaintenanceRequest();
		request.setMoneyTransferBatch(moneyTransferBatch);

		Mockito.when(getMockMoneyTransferBatchDAC().deleteMoneyTransferBatch(moneyTransferBatch)).thenReturn(
				new InternalResponse());

		InternalResponse response = getMoneyTransferStagingBAC().deleteMoneyTransferBatch(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockMoneyTransferBatchDAC()).deleteMoneyTransferBatch(moneyTransferBatch);
	}

	/**
	 * Test delete money transfer batch with error.
	 */
	@Test
	public void testDeleteMoneyTransferBatchWithError()
	{
		MoneyTransferBatch moneyTransferBatch = new MoneyTransferBatch();
		MoneyTransferBatchMaintenanceRequest request = new MoneyTransferBatchMaintenanceRequest();
		request.setMoneyTransferBatch(moneyTransferBatch);

		Mockito.when(getMockMoneyTransferBatchDAC().deleteMoneyTransferBatch(moneyTransferBatch)).thenReturn(
				new InternalResponse());

		Mockito.when(getMockMoneyTransferBatchDAC().deleteMoneyTransferBatch(moneyTransferBatch)).thenReturn(
				new InternalResponse(Status.NoRowsRemovedError));

		InternalResponse results = getMoneyTransferStagingBAC().deleteMoneyTransferBatch(request);

		Assert.assertEquals(Status.NoRowsRemovedError, results.getStatus());

		Mockito.verify(getMockMoneyTransferBatchDAC()).deleteMoneyTransferBatch(moneyTransferBatch);
	}

	/**
	 * Test insert money transfer batch status.
	 */
	@Test
	public void testInsertMoneyTransferBatchStatus()
	{
		MoneyTransferBatchStatusMaintenanceRequest request = new MoneyTransferBatchStatusMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferBatchStatus moneyTransferBatchStatus =
				CommonTestRoutines.createDummyMoneyTransferBatchStatus(MoneyTransferBatchStatusEnum.CANCELLED);

		List<MoneyTransferBatchStatus> list = new ArrayList<MoneyTransferBatchStatus>();
		list.add(moneyTransferBatchStatus);

		request.setMoneyTransferBatchStatusList(list);
		request.setNote("It is a test related with STATUS/CANCELLED");

		Mockito.when(getMockMoneyTransferBatchDAC().insertMoneyTransferBatchStatus(moneyTransferBatchStatus))
				.thenReturn(
						new InternalResponse());

		Mockito.when(getMockNoteDAC().insertNote(Matchers.any(Note.class))).thenReturn(
				new InternalResultsResponse<Note>());

		MoneyTransferBatch mtb = new MoneyTransferBatch();

		Mockito.when(getMockMoneyTransferBatchDAC().fetchMoneyTransferBatchById(Matchers.any(Integer.class)))
				.thenReturn(
						new InternalResultsResponse<MoneyTransferBatch>(mtb));

		InternalResponse response = getMoneyTransferStagingBAC().insertMoneyTransferBatchStatus(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockMoneyTransferBatchDAC()).insertMoneyTransferBatchStatus(moneyTransferBatchStatus);

	}

	/**
	 * Try to insert a new status that already is the current status for that {@link MoneyTransferBatch}.
	 */
	@Test
	public void testInsertMoneyTransferBatchStatusAlreadyExists()
	{
		MoneyTransferBatchStatusMaintenanceRequest request = new MoneyTransferBatchStatusMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferBatchStatus moneyTransferBatchStatus =
				CommonTestRoutines.createDummyMoneyTransferBatchStatus(MoneyTransferBatchStatusEnum.CANCELLED);

		List<MoneyTransferBatchStatus> list = new ArrayList<MoneyTransferBatchStatus>();
		list.add(moneyTransferBatchStatus);

		request.setMoneyTransferBatchStatusList(list);

		Mockito.when(getMockMoneyTransferBatchDAC().insertMoneyTransferBatchStatus(moneyTransferBatchStatus))
				.thenReturn(
						new InternalResponse());

		Mockito.when(getMockNoteDAC().insertNote(Matchers.any(Note.class))).thenReturn(
				new InternalResultsResponse<Note>());

		MoneyTransferBatch mtb = new MoneyTransferBatch();
		mtb.setStatusList(Arrays.asList(CommonTestRoutines
				.createDummyMoneyTransferBatchStatus(MoneyTransferBatchStatusEnum.CANCELLED)));

		Mockito.when(getMockMoneyTransferBatchDAC().fetchMoneyTransferBatchById(Matchers.any(Integer.class)))
				.thenReturn(
						new InternalResultsResponse<MoneyTransferBatch>(mtb));

		InternalResponse response = getMoneyTransferStagingBAC().insertMoneyTransferBatchStatus(request);

		CommonTestRoutines.assertResponse(response);
	}

	/**
	 * Test insert money transfer batch status.
	 */
	@Test
	public void testInsertMoneyTransferBatchStatusReleased()
	{
		MoneyTransferBatchStatusMaintenanceRequest request = new MoneyTransferBatchStatusMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferBatchStatus moneyTransferBatchStatus =
				CommonTestRoutines.createDummyMoneyTransferBatchStatus(MoneyTransferBatchStatusEnum.RELEASED);

		List<MoneyTransferBatchStatus> list = new ArrayList<MoneyTransferBatchStatus>();
		list.add(moneyTransferBatchStatus);

		request.setMoneyTransferBatchStatusList(list);
		request.setNote("It is a test related with STATUS/RELEASED");

		Mockito.when(getMockMoneyTransferBatchDAC().insertMoneyTransferBatchStatus(moneyTransferBatchStatus))
				.thenReturn(
						new InternalResponse());

		MoneyTransferBatch mtb = new MoneyTransferBatch();

		Mockito.when(getMockMoneyTransferBatchDAC().fetchMoneyTransferBatchById(Matchers.any(Integer.class)))
				.thenReturn(
						new InternalResultsResponse<MoneyTransferBatch>(mtb));

		Mockito.when(getMockMoneyTransferBatchDAC().updateMoneyTransferBatch(mtb)).thenReturn(new InternalResponse());

		Mockito.when(getMockNoteDAC().insertNote(Matchers.any(Note.class))).thenReturn(
				new InternalResultsResponse<Note>());

		InternalResponse response = getMoneyTransferStagingBAC().insertMoneyTransferBatchStatus(request);

		CommonTestRoutines.assertResponse(response);

		Assert.assertEquals(request.getUserContext().getUserId(), mtb.getReleaseUser());
		Assert.assertEquals(PersistanceActionEnum.UPDATE, mtb.getModelAction());

		Mockito.verify(getMockMoneyTransferBatchDAC()).insertMoneyTransferBatchStatus(moneyTransferBatchStatus);
		Mockito.verify(getMockMoneyTransferBatchDAC()).updateMoneyTransferBatch(mtb);

	}

	/**
	 * Test fetch money transfer batch with summary by id.
	 */
	@Test
	public void testFetchMoneyTransferBatchWithSummaryById()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);
		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(new Location());

		// -- List of MoneyTransfer that will contain money transfers with different values
		List<MoneyTransfer> moneyTransferList = new ArrayList<MoneyTransfer>();

		moneyTransferList
				.add(createMoneyTransfer(MoneyTransferStatusEnum.APPROVED, NUMBER_1000_55, moneyTransferBatch, ONE));
		moneyTransferList
				.add(createMoneyTransfer(MoneyTransferStatusEnum.ON_HOLD, NUMBER_2500, moneyTransferBatch, TWO));
		moneyTransferList.add(createMoneyTransfer(MoneyTransferStatusEnum.PENDING_APPROVAL, NUMBER_4000,
				moneyTransferBatch, THREE));
		moneyTransferList.add(createMoneyTransfer(MoneyTransferStatusEnum.ORDER_SUBMITTED, NUMBER_500,
				moneyTransferBatch, FOUR));

		moneyTransferBatch.setMoneyTransferList(moneyTransferList);

		/* Mockito Items. */
		Mockito.when(getMockMoneyTransferBatchDAC().fetchMoneyTransferBatchById(request.getId())).thenReturn(
				obtainMockMoneyTransferBatchResponse(Arrays.asList(moneyTransferBatch)));

		mockMoneyTransfer(moneyTransferBatch);

		Mockito.when(
				getMockDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(
						Matchers.any(DailyCurrencyRateInquiryRequest.class))).thenReturn(
				new InternalResultsResponse<DailyCurrencyRate>());

		/* Actual Test. */
		InternalResultsResponse<MoneyTransferBatchDTO> response =
				getMoneyTransferStagingBAC().fetchMoneyTransferBatchWithSummaryById(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFieldsMoneyTransferSummary(response.getFirstResult().getFundingSummary());

		/* Mockito Items. */
		Mockito.verify(getMockMoneyTransferBatchDAC()).fetchMoneyTransferBatchById(request.getId());

	}

	/**
	 * Test insert money transfer status.
	 */
	@Test
	public void testInsertMoneyTransferStatus()
	{
		MoneyTransferStatusMaintenanceRequest request = new MoneyTransferStatusMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferStatus moneyTransferStatus =
				CommonTestRoutines.createDummyMoneyTransferStatus(MoneyTransferStatusEnum.APPROVED);

		List<MoneyTransferStatus> list = new ArrayList<MoneyTransferStatus>();
		list.add(moneyTransferStatus);

		request.setMoneyTransferStatusList(list);
		request.setNote("It is a test related with STATUS/APPROVED");

		Mockito.when(getMockMoneyTransferDAC().insertMoneyTransferStatus(moneyTransferStatus)).thenReturn(
				new InternalResponse());

		MoneyTransfer mt = new MoneyTransfer();

		Mockito.when(getMockMoneyTransferDAC().fetchMoneyTransferById(Matchers.any(Integer.class))).thenReturn(
				new InternalResultsResponse<MoneyTransfer>(mt));

		Mockito.when(getMockMoneyTransferDAC().updateMoneyTransfer(mt)).thenReturn(new InternalResponse());

		Mockito.when(getMockNoteDAC().insertNote(Matchers.any(Note.class))).thenReturn(
				new InternalResultsResponse<Note>());

		InternalResponse response = getMoneyTransferStagingBAC().insertMoneyTransferStatus(request);

		CommonTestRoutines.assertResponse(response);

		Assert.assertEquals(PersistanceActionEnum.UPDATE, mt.getModelAction());
		Assert.assertEquals(request.getUserContext().getUserId(), mt.getReleaseUser());

		Mockito.verify(getMockMoneyTransferDAC()).insertMoneyTransferStatus(moneyTransferStatus);
		Mockito.verify(getMockMoneyTransferDAC()).updateMoneyTransfer(mt);

	}

	/**
	 * Try to insert a new status that already is the current status for that {@link MoneyTransfer}.
	 */
	@Test
	public void testInsertMoneyTransferStatusAlreadyExists()
	{
		MoneyTransferStatusMaintenanceRequest request = new MoneyTransferStatusMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		MoneyTransferStatus moneyTransferStatus =
				CommonTestRoutines.createDummyMoneyTransferStatus(MoneyTransferStatusEnum.MODIFICATION_APPROVED);

		List<MoneyTransferStatus> list = new ArrayList<MoneyTransferStatus>();
		list.add(moneyTransferStatus);
		request.setMoneyTransferStatusList(list);

		MoneyTransfer mt = new MoneyTransfer();
		mt.setStatusList(Arrays.asList(CommonTestRoutines
				.createDummyMoneyTransferStatus(MoneyTransferStatusEnum.MODIFICATION_APPROVED)));

		Mockito.when(getMockMoneyTransferDAC().fetchMoneyTransferById(Matchers.any(Integer.class))).thenReturn(
				new InternalResultsResponse<MoneyTransfer>(mt));

		Mockito.when(getMockMoneyTransferDAC().updateMoneyTransfer(mt)).thenReturn(new InternalResponse());

		Mockito.when(getMockNoteDAC().insertNote(Matchers.any(Note.class))).thenReturn(
				new InternalResultsResponse<Note>());

		InternalResponse response = getMoneyTransferStagingBAC().insertMoneyTransferStatus(request);

		CommonTestRoutines.assertResponse(response);
	}

	/**
	 * Test insert money transfer status with error.
	 */
	@Test
	public void testInsertMoneyTransferStatusWithError()
	{
		MoneyTransferStatus moneyTransferStatus = new MoneyTransferStatus();
		MoneyTransferStatusMaintenanceRequest request = new MoneyTransferStatusMaintenanceRequest();

		List<MoneyTransferStatus> list = new ArrayList<MoneyTransferStatus>();
		list.add(moneyTransferStatus);
		request.setMoneyTransferStatusList(list);

		Mockito.when(getMockMoneyTransferDAC().insertMoneyTransferStatus(moneyTransferStatus)).thenReturn(
				new InternalResponse());

		Mockito.when(getMockMoneyTransferDAC().insertMoneyTransferStatus(moneyTransferStatus)).thenReturn(
				new InternalResponse(Status.NoRowsInsertedError));

		MoneyTransfer mt = new MoneyTransfer();

		Mockito.when(getMockMoneyTransferDAC().fetchMoneyTransferById(Matchers.any(Integer.class))).thenReturn(
				new InternalResultsResponse<MoneyTransfer>(mt));

		InternalResponse results = getMoneyTransferStagingBAC().insertMoneyTransferStatus(request);

		Assert.assertEquals(Status.NoRowsInsertedError, results.getStatus());

		Mockito.verify(getMockMoneyTransferDAC()).insertMoneyTransferStatus(moneyTransferStatus);
	}

	/**
	 * Mock the money transfer batch response.
	 *
	 * @param moneyTransferBatch The money transfer batch.
	 */
	private void mockMoneyTransfer(MoneyTransferBatch moneyTransferBatch)
	{
		// -- prepare the mock to return the previous money transfer
		InternalResultsResponse<MoneyTransfer> moneyTransferResponse = new InternalResultsResponse<MoneyTransfer>();
		moneyTransferResponse.setStatus(Status.OperationSuccess);
		moneyTransferResponse.addResult(createMoneyTransfer(MoneyTransferStatusEnum.APPROVED, NUMBER_500,
				moneyTransferBatch, FIVE));

		Mockito.when(
				getMockMoneyTransferDAC().fetchMoneyTransferByRequest(Matchers.any(MoneyTransferInquiryRequest.class)))
				.thenReturn(moneyTransferResponse);
	}

	/**
	 * Create a {@link MoneyTransfer} with the status from parameter.
	 *
	 * @param status The money transfer status enum.
	 * @param amount - total value
	 * @param moneyTransferBatch The money transfer batch.
	 * @param moneyTransferId The money transfer id.
	 * @return a new MoneyTransfer
	 */
	private MoneyTransfer createMoneyTransfer(MoneyTransferStatusEnum status, BigDecimal amount,
			MoneyTransferBatch moneyTransferBatch, Integer moneyTransferId)
	{
		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();
		MoneyTransfer moneyTransfer = CommonTestRoutines.createDummyMoneyTransfer(moneyTransferBatch, transferSetting);
		moneyTransfer.setId(moneyTransferId);

		if ((status == MoneyTransferStatusEnum.PENDING_APPROVAL) || (status == MoneyTransferStatusEnum.ORDER_SUBMITTED))
		{
			moneyTransfer.getTransferSetting().getProductPlanApplicability().getPayer()
					.setPostfundAllowed(Boolean.TRUE);
		}

		moneyTransfer.getOriginAmount().setAmount(amount);
		moneyTransfer.getStatusList().get(0).setStatus(status);

		// -- create the money transfer detail
		MoneyTransferDetail moneyTransferDetail = new MoneyTransferDetail();
		moneyTransferDetail.setMember(CommonTestRoutines.createDummyMember());
		moneyTransferDetail.setRecipient(CommonTestRoutines.createDummyRecipient());
		moneyTransferDetail.getMember().setId(ONE);
		moneyTransfer.setMoneyTransferDetail(moneyTransferDetail);

		moneyTransfer.getTransferSetting().getProductPlanApplicability()
				.setCurrency(new Currency(FOREIGN_CURRENCY_MXP));

		return moneyTransfer;
	}

	/**
	 * Compare the calculated values fields with the return from the fetch.
	 *
	 * @param fundingSummaryDTO The funding summary dto.
	 */
	private void assertFieldsMoneyTransferSummary(FundingSummaryDTO fundingSummaryDTO)
	{
		Assert.assertTrue(
				"The batch total value should be 8,000.55", NUMBER_8000_55.setScale(2, BigDecimal.ROUND_HALF_UP)
						.compareTo(fundingSummaryDTO.getBatchTotal().setScale(2, BigDecimal.ROUND_HALF_UP)) == 0);

		Assert.assertTrue(
				"The payroll amount value should be 4,500.55", NUMBER_4000_55.setScale(2, BigDecimal.ROUND_HALF_UP)
						.compareTo(fundingSummaryDTO.getPayrollAmount().setScale(2, BigDecimal.ROUND_HALF_UP)) == 0);

		Assert.assertTrue(
				"The prefund value should be 4000.00", NUMBER_4000.setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(
						fundingSummaryDTO.getPrefunds().setScale(2, BigDecimal.ROUND_HALF_UP)) == 0);

		Assert.assertTrue(
				"The postfund value should be 500", NUMBER_500.setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(
						fundingSummaryDTO.getPostfunds().setScale(2, BigDecimal.ROUND_HALF_UP)) == 0);
	}

	/**
	 * Mock fetch money transfer batch by id.
	 *
	 * @param moneyTransferBatch the money transfer batch
	 */
	private void mockFetchMoneyTransferBatchById(MoneyTransferBatch moneyTransferBatch)
	{
		Mockito.when(getMockMoneyTransferBatchDAC().fetchMoneyTransferBatchById(moneyTransferBatch.getId()))
				.thenReturn(obtainMockMoneyTransferBatchResponse(Arrays.asList(moneyTransferBatch)));
	}

	/**
	 * Mock fetch money transfer by request.
	 *
	 * @param moneyTransfer the money transfer
	 */
	private void mockFetchMoneyTransferByRequest(MoneyTransfer moneyTransfer)
	{
		Mockito.when(
				getMockMoneyTransferDAC().fetchMoneyTransferByRequest(Matchers.any(MoneyTransferInquiryRequest.class)))
				.thenReturn(
						obtainMockMoneyTransferResponse(Arrays.asList(moneyTransfer)));

	}

	/**
	 * Mock fetch member by id.
	 */
	private void mockFetchMemberById()
	{
		Member member = CommonTestRoutines.createDummyMember();

		Mockito.when(getMockPersonDAC().fetchMemberById(Matchers.any(FetchByIdRequest.class))).thenReturn(
				new InternalResultsResponse<Member>(member));
	}

	/**
	 * Assert money transfer auto approval scenario number1, when the previous transaction has the status == APPROVED.
	 *
	 * @param currentBatch the current batch
	 * @param statusCurrent the status current
	 * @param statusPrevious the status previous
	 * @param valueCurrent the value current
	 * @param valuePrevious the value previous
	 * @param request the request
	 */
	private void assertMoneyTranferAutoApprovalBasedOnScenario(MoneyTransferBatch currentBatch,
			MoneyTransferStatusEnum statusCurrent, MoneyTransferStatusEnum statusPrevious, BigDecimal valueCurrent,
			BigDecimal valuePrevious, MoneyTransferAutoApprovalRequest request)
	{
		List<MoneyTransfer> moneyTransferList = new ArrayList<MoneyTransfer>();

		moneyTransferList
		.add(createMoneyTransfer(statusCurrent, valueCurrent, currentBatch,
				ONE));

		currentBatch.setMoneyTransferList(moneyTransferList);

		mockFetchMemberById();
		mockFetchMoneyTransferBatchById(currentBatch);

		MoneyTransfer previousTransaction =
				createMoneyTransfer(statusPrevious, valuePrevious, currentBatch, TWO);

		mockFetchMoneyTransferByRequest(previousTransaction);

		InternalResultsResponse<Integer> response =
				getMoneyTransferStagingBAC().fetchMoneyTransferAutoApprovalByTransferSetting(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue("The money transfer ID should be ONE", response.getFirstResult().equals(ONE));

	}
}