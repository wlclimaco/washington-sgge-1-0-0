package com.prosperitasglobal.sendsolv.payments.controller.unittest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.bai.IMoneyTransferBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchDTO;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferStatusMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchDTOResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferResponse;
import com.qat.framework.model.response.MaintenanceResponse;

public class PaymentAPIControllerTest extends AbstractTestBase
{
	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private ILocationBAI locationBAI;
	private IMoneyTransferBAI moneyTransferBAI;
	private static final Logger LOG = LoggerFactory.getLogger(PaymentAPIControllerTest.class);

	public IMoneyTransferBAI getMoneyTransferBAI()
	{
		return moneyTransferBAI;
	}

	@Resource
	public void setMoneyTransferBAI(IMoneyTransferBAI moneyTransferBAI)
	{
		this.moneyTransferBAI = moneyTransferBAI;
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	public ILocationBAI getLocationBAI()
	{
		return locationBAI;
	}

	@Resource
	public void setLocationBAI(ILocationBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	@Test
	public void fetchAllLocations()
	{

		// Mock Response 1
		LocationResponse response = new LocationResponse();
		response.setLocationList(new ArrayList<Location>());
		for (int i = 0; i < 5; i++)
		{
			Location location = new Location();
			location.setBusinessType(BusinessTypeEnum.LOCATION);
			location.setId(i);
			response.getLocationList().add(location);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getLocationBAI().fetchLocationByRequest(
						Matchers.any(PagedInquiryRequest.class)))
				.thenReturn(response);

		try
		{

			setData("{\"sortExpressions\":[],\"preQueryCount\":true,\"startPage\":0,\"pageSize\":25}");

			performTest("/api/payment/upcoming_pay_dates").andExpect(
					jsonPath("$.locationList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new LocationResponse();
			response.setOperationSuccess(true);
			response.setLocationList(new ArrayList<Location>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getLocationBAI().fetchLocationByRequest(
							Matchers.any(PagedInquiryRequest.class)))
					.thenReturn(response);

			performTest("/api/payment/upcoming_pay_dates").andExpect(
					jsonPath("$.locationList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void fetchAllBatches()
	{
		// Mock Response
		MoneyTransferBatchResponse response = new MoneyTransferBatchResponse();
		response.setMoneyTransferBatchList(new ArrayList<MoneyTransferBatch>());
		MoneyTransferBatch moneyTransfer;

		for (int i = 0; i < 5; i++)
		{
			moneyTransfer = new MoneyTransferBatch();
			moneyTransfer.setId(1);
			response.getMoneyTransferBatchList().add(moneyTransfer);
		}

		Mockito.when(
				getMoneyTransferBAI().fetchMoneyTransferBatchByRequest(
						Matchers.any(MoneyTransferBatchInquiryRequest.class)))
				.thenReturn(response);

		try
		{
			setData("{\"pageSize\":25,\"startPage\":0,\"sortExpressions\":[],\"preQueryCount\":true,\"criteria\":{}}");
			performTest("/api/payment/fetchAllBatches").andExpect(
					jsonPath("$.moneyTransferBatchList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));
			Mockito.calls(1);
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

	@Test
	public void fetchAllTransfer()
	{
		MoneyTransferResponse response = new MoneyTransferResponse();
		MoneyTransfer transfer;

		for (int i = 0; i < 10; i++)
		{
			transfer = new MoneyTransfer();
			transfer.setId(i);
			response.getMoneyTransferList().add(transfer);
		}

		Mockito.when(
				getMoneyTransferBAI().fetchMoneyTransferByRequest(
						Matchers.any(MoneyTransferInquiryRequest.class)))
				.thenReturn(response);

		Mockito.calls(1);

		try
		{
			setData("{\"pageSize\":25,\"startPage\":0,\"sortExpressions\":[],\"preQueryCount\":true,\"criteria\":{}}");
			performTest("/api/payment/fetch_transfer_batch_Id").andExpect(
					jsonPath("$.moneyTransferList", hasSize(10)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));
			Mockito.calls(1);
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

	@Test
	public void fetchAllBatchById()
	{
		// Mock Response
		MoneyTransferBatchDTOResponse response = new MoneyTransferBatchDTOResponse();
		response.setMoneyTransferBatchDTOList(new ArrayList<MoneyTransferBatchDTO>());
		MoneyTransferBatch moneyTransfer;

		for (int i = 0; i < 5; i++)
		{
			moneyTransfer = new MoneyTransferBatch();
			moneyTransfer.setId(1);
			MoneyTransferBatchDTO money = new MoneyTransferBatchDTO();
			money.setMoneyTransferBatch(moneyTransfer);
			response.getMoneyTransferBatchDTOList().add(money);
		}

		Mockito.when(
				getMoneyTransferBAI().fetchMoneyTransferBatchWithSummaryById(
						Matchers.any(FetchByIdRequest.class)))
				.thenReturn(response);

		try
		{
			setData("{\"id\":6}}");
			performTest("/api/payment/batch_by_id").andExpect(
					jsonPath("$.moneyTransferBatchDTOList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));
			Mockito.calls(1);
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

	@Test
	public void updateMoneyTransferByRequest()
	{
		MaintenanceResponse response = new MaintenanceResponse();
		response.setOperationSuccess(true);

		Mockito.when(
				getMoneyTransferBAI().updateMoneyTransfer(
						Matchers.any(MoneyTransferMaintenanceRequest.class)))
				.thenReturn(response);

		try
		{
			setData("{\"moneyTransferList\":[{\"modelAction\":\"UPDATE\",\"createUser\":\"admin\",\"modifyUser\":\"washington\",\"createDateUTC\":1420461816499,\"modifyDateUTC\":1420461816499,\"version\":2,\"id\":1040,\"senderAccount\":null,\"recipientAccount\":null,\"destinationAmount\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"country\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"MEX\",\"currencyList\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"MXP\",\"name\":\"MEXICAN PESO   \"}],\"description\":\"Mexico\",\"phoneCode\":\"52 \"},\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"EUR\",\"name\":\"EURO           \"},\"stateProvinceRegion\":null,\"amount\":1},\"discountAmount\":1,\"originFlatFee\":null,\"originAutomatedClearingHouseFee\":null,\"originCallCreditAmount\":null,\"foreignExchangeRate\":1,\"foreignExchangeRateCustomer\":0.99,\"moneyTransferBatchId\":1006,\"moneyTransferDetail\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"member\":{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":\"washington\",\"createDateUTC\":1419878012435,\"modifyDateUTC\":1419944682526,\"version\":2,\"id\":260,\"prefix\":null,\"firstName\":\"Washington\",\"middleName\":\"Climaco\",\"lastName\":\"Luis\",\"suffix\":null,\"gender\":\"MALE\",\"dateOfBirth\":1417399200000,\"pepStatus\":\"YES\",\"personType\":\"MEMBER\",\"personStatus\":\"SETUP\",\"contactList\":[{\"type\":\"phone\",\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":0,\"id\":26742,\"parentKey\":260,\"parentKeyType\":\"UNKNOWN\",\"contactType\":\"OTHER\",\"priority\":\"PRIMARY\",\"verified\":false,\"effectiveStartDate\":1419878012423,\"effectiveEndDate\":null,\"country\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"USA\",\"currencyList\":[],\"description\":\"United States of America\",\"phoneCode\":\"1 \"},\"number\":\"123456789\",\"extension\":\"\",\"parentKeyValue\":6,\"contactTypeValue\":2,\"priorityValue\":1,\"verifiedValue\":\"N\"},{\"type\":\"email\",\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":0,\"id\":26743,\"parentKey\":260,\"parentKeyType\":\"UNKNOWN\",\"contactType\":\"EMAIL_PERSONAL\",\"priority\":\"PRIMARY\",\"verified\":false,\"effectiveStartDate\":1419878012425,\"effectiveEndDate\":null,\"emailAddress\":\"dasasds@gmail.com\",\"parentKeyValue\":6,\"contactTypeValue\":3,\"priorityValue\":1,\"verifiedValue\":\"N\"}],\"nameList\":[{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1419878012435,\"modifyDateUTC\":null,\"id\":1596,\"personId\":260,\"otherName\":\"Martins\"}],\"noteList\":[],\"risk\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":0,\"parentKey\":-1,\"parentKeyType\":\"UNKNOWN\",\"riskLevel\":\"LOW\",\"riskLevelNote\":\"Test 001\",\"riskLevelValue\":1},\"documentList\":[{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1419878012474,\"modifyDateUTC\":null,\"id\":11,\"parentKey\":260,\"parentKeyType\":\"LOCATION\",\"documentType\":{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1419878012474,\"modifyDateUTC\":null,\"id\":2416,\"name\":\"Social Security Number\",\"description\":null,\"applicability\":\"MEMBER\",\"applicabilityValue\":3},\"keywordText\":null,\"filingStatus\":null,\"isActionRequired\":null,\"noteText\":null,\"issueCountry\":null,\"expirationDate\":null,\"value\":\"123456789\",\"issueStateProvinceRegion\":null,\"parentKeyValue\":2,\"filingStatusValue\":null}],\"participantId\":\"MAA00001\",\"transferSettingList\":[{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1419882063595,\"modifyDateUTC\":null,\"version\":0,\"account\":null,\"customFeeList\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":0,\"effectiveEndDate\":1417485600000,\"effectiveStartDate\":null,\"id\":1,\"status\":\"ACTIVE\",\"transferSettingId\":1,\"value\":9.99,\"statusValue\":1}],\"cyclesToSkip\":8,\"effectiveEndDate\":null,\"effectiveStartDate\":1420077600000,\"employmentInfo\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":0,\"currentPay\":null,\"employeeId\":null,\"employmentInfoStatus\":\"SETUP\",\"enrollDate\":null,\"hireDate\":null,\"id\":11,\"jobTitle\":null,\"locationId\":8,\"locationName\":\"EEFC_location\",\"organizationId\":6,\"organizationName\":\"5E36_parent_organization\",\"memberId\":null,\"payPerPeriod\":null,\"enrollmentType\":null,\"employmentInfoStatusValue\":3,\"enrollmentTypeValue\":null},\"id\":1,\"key\":null,\"memberId\":260,\"numberOfCyclesSkipped\":8,\"planCategory\":{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1419881967453,\"modifyDateUTC\":null,\"version\":0,\"id\":13,\"callCreditAllowance\":10.75,\"feeTierList\":[{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1419881967453,\"modifyDateUTC\":null,\"version\":0,\"feeValue\":10.25,\"id\":13,\"maximumValue\":100,\"minimumValue\":5.1,\"planCategoryId\":13,\"sequenceNumber\":1}],\"name\":\"Test Plan Category\",\"productPlanId\":13},\"productPlanApplicability\":{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1419881967453,\"modifyDateUTC\":null,\"version\":0,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"id\":13,\"productPlanId\":13,\"payer\":{\"modelAction\":\"NONE\",\"createUser\":\"pgsi\",\"modifyUser\":null,\"createDateUTC\":1,\"modifyDateUTC\":null,\"id\":1901,\"achPayeeCode\":\"654       \",\"automatedClearingHouseId\":1,\"name\":\"A.J. RENNER    \",\"country\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRA\",\"currencyList\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"}],\"description\":\"Brazil\",\"phoneCode\":\"55 \"},\"paymentTypeList\":[{\"modelAction\":\"NONE\",\"createUser\":\"pgsi\",\"modifyUser\":null,\"createDateUTC\":1,\"modifyDateUTC\":null,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"type\":\"CREATE_ACCOUNT\",\"typeValue\":5}],\"postfundAllowed\":false,\"stateProvinceRegionList\":[],\"dailyCurrencyRateList\":[{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":\"ms access\",\"createDateUTC\":1416927603000,\"modifyDateUTC\":1416927603000,\"version\":0,\"id\":5866,\"validForDate\":1416880800000,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"payerId\":1901,\"dailyCurrencyRateDetailList\":[{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":null,\"createDateUTC\":1416927603000,\"modifyDateUTC\":null,\"id\":5896,\"dailyCurrencyRateId\":5866,\"exchangeRate\":2.537}]}],\"currencyAvailabilityList\":[],\"postfundAllowedValue\":\"N\"},\"paymentType\":\"CREATE_ACCOUNT\",\"paymentTypeValue\":5},\"recipientId\":261,\"status\":\"ACTIVE\",\"transferAmount\":1.99,\"transferType\":\"RECURRING\",\"noteList\":[],\"transferTypeValue\":1,\"statusValue\":1},{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1419882101401,\"modifyDateUTC\":null,\"version\":0,\"account\":null,\"customFeeList\":[],\"cyclesToSkip\":9,\"effectiveEndDate\":null,\"effectiveStartDate\":1421287200000,\"employmentInfo\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":0,\"currentPay\":null,\"employeeId\":null,\"employmentInfoStatus\":\"SETUP\",\"enrollDate\":null,\"hireDate\":null,\"id\":11,\"jobTitle\":null,\"locationId\":8,\"locationName\":\"EEFC_location\",\"organizationId\":6,\"organizationName\":\"5E36_parent_organization\",\"memberId\":null,\"payPerPeriod\":null,\"enrollmentType\":null,\"employmentInfoStatusValue\":3,\"enrollmentTypeValue\":null},\"id\":2,\"key\":null,\"memberId\":260,\"numberOfCyclesSkipped\":9,\"planCategory\":{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1419881967453,\"modifyDateUTC\":null,\"version\":0,\"id\":13,\"callCreditAllowance\":10.75,\"feeTierList\":[{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1419881967453,\"modifyDateUTC\":null,\"version\":0,\"feeValue\":10.25,\"id\":13,\"maximumValue\":100,\"minimumValue\":5.1,\"planCategoryId\":13,\"sequenceNumber\":1}],\"name\":\"Test Plan Category\",\"productPlanId\":13},\"productPlanApplicability\":{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1419881967453,\"modifyDateUTC\":null,\"version\":0,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"id\":13,\"productPlanId\":13,\"payer\":{\"modelAction\":\"NONE\",\"createUser\":\"pgsi\",\"modifyUser\":null,\"createDateUTC\":1,\"modifyDateUTC\":null,\"id\":1901,\"achPayeeCode\":\"654       \",\"automatedClearingHouseId\":1,\"name\":\"A.J. RENNER    \",\"country\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRA\",\"currencyList\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"}],\"description\":\"Brazil\",\"phoneCode\":\"55 \"},\"paymentTypeList\":[{\"modelAction\":\"NONE\",\"createUser\":\"pgsi\",\"modifyUser\":null,\"createDateUTC\":1,\"modifyDateUTC\":null,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"type\":\"CREATE_ACCOUNT\",\"typeValue\":5}],\"postfundAllowed\":false,\"stateProvinceRegionList\":[],\"dailyCurrencyRateList\":[{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":\"ms access\",\"createDateUTC\":1416927603000,\"modifyDateUTC\":1416927603000,\"version\":0,\"id\":5866,\"validForDate\":1416880800000,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"payerId\":1901,\"dailyCurrencyRateDetailList\":[{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":null,\"createDateUTC\":1416927603000,\"modifyDateUTC\":null,\"id\":5896,\"dailyCurrencyRateId\":5866,\"exchangeRate\":2.537}]}],\"currencyAvailabilityList\":[],\"postfundAllowedValue\":\"N\"},\"paymentType\":\"CREATE_ACCOUNT\",\"paymentTypeValue\":5},\"recipientId\":261,\"status\":\"ACTIVE\",\"transferAmount\":1.99,\"transferType\":\"RECURRING\",\"noteList\":[{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1419882101401,\"modifyDateUTC\":null,\"id\":6194,\"noteText\":\"qwqqweqeqwewe\",\"sequenceNumber\":1,\"parentKey\":2,\"parentKeyType\":\"UNKNOWN\"}],\"transferTypeValue\":1,\"statusValue\":1}],\"pinNumber\":\"6039\",\"employmentInfoList\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":0,\"currentPay\":null,\"employeeId\":null,\"employmentInfoStatus\":\"SETUP\",\"enrollDate\":null,\"hireDate\":null,\"id\":11,\"jobTitle\":null,\"locationId\":8,\"locationName\":\"EEFC_location\",\"organizationId\":6,\"organizationName\":\"5E36_parent_organization\",\"memberId\":null,\"payPerPeriod\":null,\"enrollmentType\":null,\"employmentInfoStatusValue\":3,\"enrollmentTypeValue\":null}],\"countryUsageList\":[{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1419878012435,\"modifyDateUTC\":null,\"version\":0,\"id\":31,\"personId\":260,\"country\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRA\",\"currencyList\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"}],\"description\":\"Brazil\",\"phoneCode\":\"55 \"},\"usage\":\"BIRTH\",\"usageValue\":3},{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1419878012448,\"modifyDateUTC\":null,\"version\":0,\"id\":32,\"personId\":260,\"country\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRA\",\"currencyList\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"}],\"description\":\"Brazil\",\"phoneCode\":\"55 \"},\"usage\":\"RESIDENCE\",\"usageValue\":2},{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1419878012464,\"modifyDateUTC\":null,\"version\":0,\"id\":33,\"personId\":260,\"country\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRA\",\"currencyList\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"}],\"description\":\"Brazil\",\"phoneCode\":\"55 \"},\"usage\":\"CITIZENSHIP\",\"usageValue\":1}],\"preferredLanguage\":null,\"bestTimeToCall\":\"10:10\",\"sdnstatus\":\"UNKNOWN\",\"pepStatusValue\":1,\"personStatusValue\":3,\"personTypeValue\":2,\"genderValue\":1,\"sdnstatusValue\":5},\"recipient\":{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1419878110683,\"modifyDateUTC\":null,\"version\":0,\"id\":261,\"prefix\":{\"modelAction\":\"NONE\",\"createUser\":\"pgsi\",\"modifyUser\":null,\"createDateUTC\":1,\"modifyDateUTC\":null,\"id\":1516,\"code\":\"Mr.\",\"value\":\"Mister\"},\"firstName\":\"Washington\",\"middleName\":\"Climaco\",\"lastName\":\"Junior\",\"suffix\":{\"modelAction\":\"NONE\",\"createUser\":\"pgsi\",\"modifyUser\":null,\"createDateUTC\":1,\"modifyDateUTC\":null,\"id\":1525,\"code\":\"Jr.\",\"value\":\"Junior\"},\"gender\":\"MALE\",\"dateOfBirth\":1417572000000,\"pepStatus\":\"UNKNOWN\",\"personType\":\"RECIPIENT\",\"personStatus\":\"SETUP\",\"contactList\":[{\"type\":\"phone\",\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":0,\"id\":26744,\"parentKey\":261,\"parentKeyType\":\"UNKNOWN\",\"contactType\":\"OTHER\",\"priority\":\"PRIMARY\",\"verified\":false,\"effectiveStartDate\":1419878110682,\"effectiveEndDate\":null,\"country\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"USA\",\"currencyList\":[],\"description\":\"United States of America\",\"phoneCode\":\"1 \"},\"number\":\"123456789\",\"extension\":\"\",\"parentKeyValue\":6,\"contactTypeValue\":2,\"priorityValue\":1,\"verifiedValue\":\"N\"},{\"type\":\"email\",\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":0,\"id\":26745,\"parentKey\":261,\"parentKeyType\":\"UNKNOWN\",\"contactType\":\"EMAIL_PERSONAL\",\"priority\":\"PRIMARY\",\"verified\":false,\"effectiveStartDate\":1419878110682,\"effectiveEndDate\":null,\"emailAddress\":\"dasasds@gmail.com\",\"parentKeyValue\":6,\"contactTypeValue\":3,\"priorityValue\":1,\"verifiedValue\":\"N\"}],\"nameList\":[{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1419878110683,\"modifyDateUTC\":null,\"id\":1597,\"personId\":261,\"otherName\":\"Martins\"}],\"noteList\":[],\"risk\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":0,\"parentKey\":-1,\"parentKeyType\":\"UNKNOWN\",\"riskLevel\":\"UNKNOWN\",\"riskLevelNote\":\"\",\"riskLevelValue\":3},\"documentList\":[],\"participantId\":\"RAA00001\",\"transferSettingList\":[{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1419882063595,\"modifyDateUTC\":null,\"version\":0,\"account\":null,\"customFeeList\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":0,\"effectiveEndDate\":1417485600000,\"effectiveStartDate\":null,\"id\":1,\"status\":\"ACTIVE\",\"transferSettingId\":1,\"value\":9.99,\"statusValue\":1}],\"cyclesToSkip\":8,\"effectiveEndDate\":null,\"effectiveStartDate\":1420077600000,\"employmentInfo\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":0,\"currentPay\":null,\"employeeId\":null,\"employmentInfoStatus\":\"SETUP\",\"enrollDate\":null,\"hireDate\":null,\"id\":11,\"jobTitle\":null,\"locationId\":8,\"locationName\":\"EEFC_location\",\"organizationId\":6,\"organizationName\":\"5E36_parent_organization\",\"memberId\":null,\"payPerPeriod\":null,\"enrollmentType\":null,\"employmentInfoStatusValue\":3,\"enrollmentTypeValue\":null},\"id\":1,\"key\":null,\"memberId\":260,\"numberOfCyclesSkipped\":8,\"planCategory\":{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1419881967453,\"modifyDateUTC\":null,\"version\":0,\"id\":13,\"callCreditAllowance\":10.75,\"feeTierList\":[{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1419881967453,\"modifyDateUTC\":null,\"version\":0,\"feeValue\":10.25,\"id\":13,\"maximumValue\":100,\"minimumValue\":5.1,\"planCategoryId\":13,\"sequenceNumber\":1}],\"name\":\"Test Plan Category\",\"productPlanId\":13},\"productPlanApplicability\":{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1419881967453,\"modifyDateUTC\":null,\"version\":0,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"id\":13,\"productPlanId\":13,\"payer\":{\"modelAction\":\"NONE\",\"createUser\":\"pgsi\",\"modifyUser\":null,\"createDateUTC\":1,\"modifyDateUTC\":null,\"id\":1901,\"achPayeeCode\":\"654       \",\"automatedClearingHouseId\":1,\"name\":\"A.J. RENNER    \",\"country\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRA\",\"currencyList\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"}],\"description\":\"Brazil\",\"phoneCode\":\"55 \"},\"paymentTypeList\":[{\"modelAction\":\"NONE\",\"createUser\":\"pgsi\",\"modifyUser\":null,\"createDateUTC\":1,\"modifyDateUTC\":null,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"type\":\"CREATE_ACCOUNT\",\"typeValue\":5}],\"postfundAllowed\":false,\"stateProvinceRegionList\":[],\"dailyCurrencyRateList\":[{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":\"ms access\",\"createDateUTC\":1416927603000,\"modifyDateUTC\":1416927603000,\"version\":0,\"id\":5866,\"validForDate\":1416880800000,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"payerId\":1901,\"dailyCurrencyRateDetailList\":[{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":null,\"createDateUTC\":1416927603000,\"modifyDateUTC\":null,\"id\":5896,\"dailyCurrencyRateId\":5866,\"exchangeRate\":2.537}]}],\"currencyAvailabilityList\":[],\"postfundAllowedValue\":\"N\"},\"paymentType\":\"CREATE_ACCOUNT\",\"paymentTypeValue\":5},\"recipientId\":261,\"status\":\"ACTIVE\",\"transferAmount\":1.99,\"transferType\":\"RECURRING\",\"noteList\":[],\"transferTypeValue\":1,\"statusValue\":1},{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1419882101401,\"modifyDateUTC\":null,\"version\":0,\"account\":null,\"customFeeList\":[],\"cyclesToSkip\":9,\"effectiveEndDate\":null,\"effectiveStartDate\":1421287200000,\"employmentInfo\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":0,\"currentPay\":null,\"employeeId\":null,\"employmentInfoStatus\":\"SETUP\",\"enrollDate\":null,\"hireDate\":null,\"id\":11,\"jobTitle\":null,\"locationId\":8,\"locationName\":\"EEFC_location\",\"organizationId\":6,\"organizationName\":\"5E36_parent_organization\",\"memberId\":null,\"payPerPeriod\":null,\"enrollmentType\":null,\"employmentInfoStatusValue\":3,\"enrollmentTypeValue\":null},\"id\":2,\"key\":null,\"memberId\":260,\"numberOfCyclesSkipped\":9,\"planCategory\":{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1419881967453,\"modifyDateUTC\":null,\"version\":0,\"id\":13,\"callCreditAllowance\":10.75,\"feeTierList\":[{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1419881967453,\"modifyDateUTC\":null,\"version\":0,\"feeValue\":10.25,\"id\":13,\"maximumValue\":100,\"minimumValue\":5.1,\"planCategoryId\":13,\"sequenceNumber\":1}],\"name\":\"Test Plan Category\",\"productPlanId\":13},\"productPlanApplicability\":{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1419881967453,\"modifyDateUTC\":null,\"version\":0,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"id\":13,\"productPlanId\":13,\"payer\":{\"modelAction\":\"NONE\",\"createUser\":\"pgsi\",\"modifyUser\":null,\"createDateUTC\":1,\"modifyDateUTC\":null,\"id\":1901,\"achPayeeCode\":\"654       \",\"automatedClearingHouseId\":1,\"name\":\"A.J. RENNER    \",\"country\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRA\",\"currencyList\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"}],\"description\":\"Brazil\",\"phoneCode\":\"55 \"},\"paymentTypeList\":[{\"modelAction\":\"NONE\",\"createUser\":\"pgsi\",\"modifyUser\":null,\"createDateUTC\":1,\"modifyDateUTC\":null,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"type\":\"CREATE_ACCOUNT\",\"typeValue\":5}],\"postfundAllowed\":false,\"stateProvinceRegionList\":[],\"dailyCurrencyRateList\":[{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":\"ms access\",\"createDateUTC\":1416927603000,\"modifyDateUTC\":1416927603000,\"version\":0,\"id\":5866,\"validForDate\":1416880800000,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"payerId\":1901,\"dailyCurrencyRateDetailList\":[{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":null,\"createDateUTC\":1416927603000,\"modifyDateUTC\":null,\"id\":5896,\"dailyCurrencyRateId\":5866,\"exchangeRate\":2.537}]}],\"currencyAvailabilityList\":[],\"postfundAllowedValue\":\"N\"},\"paymentType\":\"CREATE_ACCOUNT\",\"paymentTypeValue\":5},\"recipientId\":261,\"status\":\"ACTIVE\",\"transferAmount\":1.99,\"transferType\":\"RECURRING\",\"noteList\":[{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1419882101401,\"modifyDateUTC\":null,\"id\":6194,\"noteText\":\"qwqqweqeqwewe\",\"sequenceNumber\":1,\"parentKey\":2,\"parentKeyType\":\"UNKNOWN\"}],\"transferTypeValue\":1,\"statusValue\":1}],\"sdnstatus\":\"UNKNOWN\",\"pepStatusValue\":3,\"personStatusValue\":3,\"personTypeValue\":3,\"genderValue\":1,\"sdnstatusValue\":5}},\"noteList\":[],\"originAmount\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"country\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRA\",\"currencyList\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"}],\"description\":\"Brazil\",\"phoneCode\":\"55 \"},\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"EUR\",\"name\":\"EURO           \"},\"stateProvinceRegion\":null,\"amount\":3},\"paymentType\":\"CASH_TO_AGENT\",\"spreadPercentage\":1,\"statusList\":[{\"modelAction\":\"NONE\",\"createUser\":\"washington\",\"modifyUser\":\"admin\",\"createDateUTC\":1420461816499,\"modifyDateUTC\":1420461816499,\"id\":1049,\"moneyTransferId\":1040,\"status\":\"CANCELLED\",\"response\":null,\"moneyTransferTransaction\":null,\"responseString\":null,\"statusValue\":12}],\"transferSetting\":{\"modelAction\":\"NONE\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1419882063595,\"modifyDateUTC\":null,\"version\":0,\"account\":null,\"customFeeList\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":0,\"effectiveEndDate\":1417485600000,\"effectiveStartDate\":null,\"id\":1,\"status\":\"ACTIVE\",\"transferSettingId\":1,\"value\":9.99,\"statusValue\":1}],\"cyclesToSkip\":8,\"effectiveEndDate\":null,\"effectiveStartDate\":1420077600000,\"employmentInfo\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":0,\"currentPay\":null,\"employeeId\":null,\"employmentInfoStatus\":\"SETUP\",\"enrollDate\":null,\"hireDate\":null,\"id\":11,\"jobTitle\":null,\"locationId\":8,\"locationName\":\"EEFC_location\",\"organizationId\":6,\"organizationName\":\"5E36_parent_organization\",\"memberId\":null,\"payPerPeriod\":null,\"enrollmentType\":null,\"employmentInfoStatusValue\":3,\"enrollmentTypeValue\":null},\"id\":1,\"key\":null,\"memberId\":260,\"numberOfCyclesSkipped\":8,\"planCategory\":{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1419881967453,\"modifyDateUTC\":null,\"version\":0,\"id\":13,\"callCreditAllowance\":10.75,\"feeTierList\":[{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1419881967453,\"modifyDateUTC\":null,\"version\":0,\"feeValue\":10.25,\"id\":13,\"maximumValue\":100,\"minimumValue\":5.1,\"planCategoryId\":13,\"sequenceNumber\":1}],\"name\":\"Test Plan Category\",\"productPlanId\":13},\"productPlanApplicability\":{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1419881967453,\"modifyDateUTC\":null,\"version\":0,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"id\":13,\"productPlanId\":13,\"payer\":{\"modelAction\":\"NONE\",\"createUser\":\"pgsi\",\"modifyUser\":null,\"createDateUTC\":1,\"modifyDateUTC\":null,\"id\":1901,\"achPayeeCode\":\"654       \",\"automatedClearingHouseId\":1,\"name\":\"A.J. RENNER    \",\"country\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRA\",\"currencyList\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"}],\"description\":\"Brazil\",\"phoneCode\":\"55 \"},\"paymentTypeList\":[{\"modelAction\":\"NONE\",\"createUser\":\"pgsi\",\"modifyUser\":null,\"createDateUTC\":1,\"modifyDateUTC\":null,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"type\":\"CREATE_ACCOUNT\",\"typeValue\":5}],\"postfundAllowed\":false,\"stateProvinceRegionList\":[],\"dailyCurrencyRateList\":[{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":\"ms access\",\"createDateUTC\":1416927603000,\"modifyDateUTC\":1416927603000,\"version\":0,\"id\":5866,\"validForDate\":1416880800000,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"payerId\":1901,\"dailyCurrencyRateDetailList\":[{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":null,\"createDateUTC\":1416927603000,\"modifyDateUTC\":null,\"id\":5896,\"dailyCurrencyRateId\":5866,\"exchangeRate\":2.537}]}],\"currencyAvailabilityList\":[],\"postfundAllowedValue\":\"N\"},\"paymentType\":\"CREATE_ACCOUNT\",\"paymentTypeValue\":5},\"recipientId\":261,\"status\":\"ACTIVE\",\"transferAmount\":1.99,\"transferType\":\"RECURRING\",\"noteList\":[],\"transferTypeValue\":1,\"statusValue\":1},\"confirmationNumber\":\"1\",\"fundingDate\":null,\"paymentTypeValue\":3}]}");
			performTest("/api/payment/updateMoneyTransfer").andExpect(jsonPath("$.operationSuccess", equalTo(true)));
			Mockito.calls(1);
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void updateBatch()
	{
		MaintenanceResponse response = new MaintenanceResponse();
		response.setOperationSuccess(true);

		Mockito.when(
				getMoneyTransferBAI().updateMoneyTransferBatch(
						Matchers.any(MoneyTransferBatchMaintenanceRequest.class)))
				.thenReturn(response);
		try
		{
			setData("{\"userContext\":{\"userId\":\"admin\",\"id\":null,\"userRole\":null,\"localeString\":null,\"tenant\":null,\"authorities\":null},\"moneyTransferBatch\":{\"id\":70,\"modelAction\":\"UPDATE\",\"payrollReceivedDate\":1425385545581,\"location\":{\"id\":20},\"version\":2}}");
			performTest("/api/payment/update").andExpect(jsonPath("$.operationSuccess", equalTo(true)));
			Mockito.calls(1);
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

	@Test
	public void insertMoneyTransferStatus()
	{
		// Mock Response 1
		MaintenanceResponse response = new MaintenanceResponse();
		response.setOperationSuccess(true);
		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getMoneyTransferBAI().insertMoneyTransferStatus(
						Matchers.any(MoneyTransferStatusMaintenanceRequest.class)))
				.thenReturn(response);

		try
		{
			setData("{\"moneyTransferStatusList\":[{\"id\":null,\"moneyTransferId\":2689,\"status\":\"CANCELLED\",\"response\":null,\"moneyTransferTransaction\":null,\"modelAction\":\"INSERT\"}]}");
			performTest("/api/payment/insertTransferStatus").andExpect(jsonPath("$.operationSuccess", equalTo(true)));
			Mockito.calls(1);
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

	@Test
	public void insertBatch()
	{
		MoneyTransferBatchResponse response = new MoneyTransferBatchResponse();
		List<MoneyTransferBatch> moneyTransferBatchList = new ArrayList<MoneyTransferBatch>();
		MoneyTransferBatch moneyTransferBatch = null;

		for (int i = 0; i < 5; i++)
		{
			moneyTransferBatch = new MoneyTransferBatch();
			moneyTransferBatch.setId(i);
			moneyTransferBatchList.add(moneyTransferBatch);
		}

		response.setMoneyTransferBatchList(moneyTransferBatchList);

		Mockito.when(
				getMoneyTransferBAI().insertMoneyTransferBatch(
						Matchers.any(MoneyTransferBatchCreateRequest.class)))
				.thenReturn(response);

		try
		{
			setData("{\"userContext\":{\"userId\":\"admin\",\"id\":null,\"userRole\":null,\"localeString\":null,\"tenant\":null,\"authorities\":null},\"location\":{\"id\":20,\"fundsTransferDayLimit\":null,\"batchApprovalDayLimit\":null},\"paymentPreparationDate\":{\"eventDate\":1425340800000},\"payDate\":{\"eventDate\":1425340800000}}");
			performTest("/api/payment/insertMoneyTransferBatch").andExpect(
					jsonPath("$.moneyTransferBatchList", hasSize(5))).andExpect(
					jsonPath("$.operationSuccess", equalTo(true)));
			Mockito.calls(1);
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

	@Test
	public void createOneOffTransaction()
	{
		MoneyTransferResponse moneyTransferResponse = new MoneyTransferResponse();
		List<MoneyTransfer> moneyTransferList = new ArrayList<MoneyTransfer>();
		moneyTransferList.add(new MoneyTransfer());
		moneyTransferResponse.setMoneyTransferList(moneyTransferList);

		try
		{
			setData("{\"userContext\":{\"userId\":\"admin\",\"id\":null,\"userRole\":null,\"localeString\":null,\"tenant\":null,\"authorities\":null},\"moneyTransferBatch\":{\"id\":71,\"payPreparationDate\":1420070400000},\"transferSetting\":{\"modelAction\":\"INSERT\",\"createUser\":\"admin\",\"modifyUser\":\"admin\",\"createDateUTC\":1425390456786,\"modifyDateUTC\":1417522365042,\"version\":7,\"account\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"id\":null,\"number\":1,\"type\":\"SAVINGS\",\"typeValue\":1},\"customFeeList\":[{\"modelAction\":\"INSERT\",\"createUser\":\"admin\",\"modifyUser\":null,\"createDateUTC\":1425390456733,\"modifyDateUTC\":null,\"version\":0,\"effectiveEndDate\":1427770800000,\"effectiveStartDate\":null,\"id\":1016,\"status\":\"ACTIVE\",\"transferSettingId\":1052,\"value\":20,\"statusValue\":1}],\"cyclesToSkip\":0,\"effectiveEndDate\":1425340800000,\"effectiveStartDate\":1425340800000,\"employmentInfo\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"version\":2,\"currentPay\":null,\"employeeId\":null,\"employmentInfoStatus\":\"ACTIVE\",\"enrollDate\":null,\"hireDate\":null,\"id\":8,\"jobTitle\":null,\"locationId\":8,\"locationName\":\"EEFC_location\",\"organizationId\":6,\"organizationName\":\"5E36_parent_organization\",\"memberId\":264,\"payPerPeriod\":null,\"enrollmentType\":null,\"employmentInfoStatusValue\":1,\"enrollmentTypeValue\":null},\"id\":1052,\"key\":\"TAA00057\",\"memberId\":264,\"numberOfCyclesSkipped\":0,\"planCategory\":{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1417397155030,\"modifyDateUTC\":null,\"version\":0,\"id\":2,\"callCreditAllowance\":2,\"feeTierList\":[{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1417397155030,\"modifyDateUTC\":null,\"version\":0,\"feeValue\":7.99,\"id\":5,\"maximumValue\":99,\"minimumValue\":0,\"planCategoryId\":2,\"sequenceNumber\":1},{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1417397155031,\"modifyDateUTC\":null,\"version\":0,\"feeValue\":6.99,\"id\":6,\"maximumValue\":199,\"minimumValue\":100,\"planCategoryId\":2,\"sequenceNumber\":2},{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1417397155031,\"modifyDateUTC\":null,\"version\":0,\"feeValue\":5.99,\"id\":7,\"maximumValue\":399,\"minimumValue\":200,\"planCategoryId\":2,\"sequenceNumber\":3},{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1417397155031,\"modifyDateUTC\":null,\"version\":0,\"feeValue\":4.99,\"id\":8,\"maximumValue\":599,\"minimumValue\":400,\"planCategoryId\":2,\"sequenceNumber\":4},{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1417397155031,\"modifyDateUTC\":null,\"version\":0,\"feeValue\":3.99,\"id\":9,\"maximumValue\":null,\"minimumValue\":600,\"planCategoryId\":2,\"sequenceNumber\":5}],\"name\":\"Silver\",\"productPlanId\":1},\"productPlanApplicability\":{\"modelAction\":\"NONE\",\"createUser\":\"PGSiBatch\",\"modifyUser\":null,\"createDateUTC\":1417397155031,\"modifyDateUTC\":null,\"version\":0,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"id\":1,\"productPlanId\":1,\"payer\":{\"modelAction\":\"NONE\",\"createUser\":\"pgsi\",\"modifyUser\":null,\"createDateUTC\":1,\"modifyDateUTC\":null,\"id\":1901,\"achPayeeCode\":\"654       \",\"automatedClearingHouseId\":1,\"name\":\"A.J. RENNER    \",\"country\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRA\",\"currencyList\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"DOP\",\"name\":\"DOMINICAN REP P\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"USD\",\"name\":\"US DOLLAR      \"}],\"description\":\"Brazil\",\"phoneCode\":\"55\"},\"paymentTypeList\":[{\"modelAction\":\"NONE\",\"createUser\":\"pgsi\",\"modifyUser\":null,\"createDateUTC\":1,\"modifyDateUTC\":null,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"type\":\"CREATE_ACCOUNT\",\"typeValue\":5}],\"postfundAllowed\":false,\"stateProvinceRegionList\":[],\"dailyCurrencyRateList\":[{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":\"ms access\",\"createDateUTC\":1416927603000,\"modifyDateUTC\":1416927603000,\"version\":0,\"id\":5866,\"validForDate\":1416927603000,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"payerId\":1901,\"dailyCurrencyRateDetailList\":[{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":null,\"createDateUTC\":1416927603000,\"modifyDateUTC\":null,\"id\":5896,\"dailyCurrencyRateId\":5866,\"exchangeRate\":2.537}]},{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":\"ms access\",\"createDateUTC\":1421835815000,\"modifyDateUTC\":1421835815000,\"version\":0,\"id\":6004,\"validForDate\":1421835815000,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"payerId\":1901,\"dailyCurrencyRateDetailList\":[{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":null,\"createDateUTC\":1421835815000,\"modifyDateUTC\":null,\"id\":6036,\"dailyCurrencyRateId\":6004,\"exchangeRate\":2.6123}]},{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":\"ms access\",\"createDateUTC\":1421920991000,\"modifyDateUTC\":1421920992000,\"version\":0,\"id\":6253,\"validForDate\":1421906400000,\"currency\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDateUTC\":null,\"modifyDateUTC\":null,\"code\":\"BRL\",\"name\":\"CRUZEIRO REAL  \"},\"payerId\":1901,\"dailyCurrencyRateDetailList\":[{\"modelAction\":\"NONE\",\"createUser\":\"ms access\",\"modifyUser\":null,\"createDateUTC\":1421920992000,\"modifyDateUTC\":null,\"id\":6285,\"dailyCurrencyRateId\":6253,\"exchangeRate\":2.58}]}],\"currencyAvailabilityList\":[],\"postfundAllowedValue\":\"N\"},\"paymentType\":\"CREATE_ACCOUNT\",\"paymentTypeValue\":5},\"recipientId\":265,\"status\":\"ACTIVE\",\"transferAmount\":5000,\"transferType\":\"ONE_TIME\",\"noteList\":[],\"transferTypeValue\":2,\"statusValue\":1}}");
			performTest("/api/payment/insertMoneyTransfer").andExpect(
					jsonPath("$.moneyTransferList", hasSize(1))).andExpect(
					jsonPath("$.operationSuccess", equalTo(true)));
			Mockito.calls(1);
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}
}
