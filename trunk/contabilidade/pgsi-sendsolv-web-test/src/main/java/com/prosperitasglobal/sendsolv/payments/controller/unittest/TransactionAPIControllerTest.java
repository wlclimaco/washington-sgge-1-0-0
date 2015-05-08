package com.prosperitasglobal.sendsolv.payments.controller.unittest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bai.IMoneyTransferBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferResponse;

public class TransactionAPIControllerTest extends AbstractTestBase
{
	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE =
			"Unexpected exception while testing response";

	private IMoneyTransferBAI moneyTransferBAI;
	private static final Logger LOG = LoggerFactory.getLogger(TransactionAPIControllerTest.class);

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

	@Test
	public void fetchAllTransaction()
	{

		// Mock Response 1
		MoneyTransferResponse response = new MoneyTransferResponse();
		response.setMoneyTransferList(new ArrayList<MoneyTransfer>());
		for (int i = 0; i < 5; i++)
		{
			MoneyTransfer moneyTransfer = new MoneyTransfer();
			moneyTransfer.setId(i);
			moneyTransfer.setMoneyTransferBatchId(i + 10);

			response.getMoneyTransferList().add(moneyTransfer);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getMoneyTransferBAI().fetchMoneyTransferByRequest(
						Matchers.any(MoneyTransferInquiryRequest.class)))
				.thenReturn(response);

		try
		{
			setData("{\"userContext\":{\"userId\":\"admin\",\"id\":null,\"userRole\":null,\"localeString\":null,\"tenant\":null,\"authorities\":null},\"pageSize\":25,\"startPage\":0,\"sortExpressions\":[{\"field\":\"status\",\"direction\":\"Ascending\"}],\"preQueryCount\":true,\"criteria\":{\"transactionId\":null,\"confirmationNumber\":null,\"currencyCode\":null,\"locationName\":null,\"organizationName\":null,\"member\":null,\"statusList\":null}}");
			performTest("/api/transaction/transactions").andExpect(
					jsonPath("$.moneyTransferList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new MoneyTransferResponse();
			response.setOperationSuccess(true);
			response.setMoneyTransferList(new ArrayList<MoneyTransfer>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getMoneyTransferBAI().fetchMoneyTransferByRequest(
							Matchers.any(MoneyTransferInquiryRequest.class)))
					.thenReturn(response);

			performTest("/api/transaction/transactions").andExpect(
					jsonPath("$.moneyTransferList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE;
			LOG.error(msg, e);
		}

	}

	@Test
	public void fetchBatchId()
	{

		// Mock Response 1
		MoneyTransferBatchResponse response = new MoneyTransferBatchResponse();
		response.setMoneyTransferBatchList(new ArrayList<MoneyTransferBatch>());
		for (int i = 0; i < 5; i++)
		{
			MoneyTransferBatch moneyTransfer = new MoneyTransferBatch();
			moneyTransfer.setId(i);
			response.getMoneyTransferBatchList().add(moneyTransfer);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getMoneyTransferBAI().fetchMoneyTransferBatchById(
						Matchers.any(FetchByIdRequest.class)))
				.thenReturn(response);

		try
		{

			setData("{\"id\":\"1490057\"}");

			performTest("/api/transaction/fetchBatchId").andExpect(
					jsonPath("$.moneyTransferBatchList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new MoneyTransferBatchResponse();
			response.setMoneyTransferBatchList(new ArrayList<MoneyTransferBatch>());
			response.setOperationSuccess(true);
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getMoneyTransferBAI().fetchMoneyTransferBatchById(
							Matchers.any(FetchByIdRequest.class)))
					.thenReturn(response);

			performTest("/api/transaction/fetchBatchId").andExpect(
					jsonPath("$.moneyTransferBatchList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE;
			LOG.error(msg, e);
		}

	}
}
