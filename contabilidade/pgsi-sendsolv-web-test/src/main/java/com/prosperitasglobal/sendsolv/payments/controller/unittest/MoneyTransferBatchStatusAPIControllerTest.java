package com.prosperitasglobal.sendsolv.payments.controller.unittest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.bai.IMoneyTransferBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchStatusMaintenanceRequest;
import com.qat.framework.model.response.MaintenanceResponse;

public class MoneyTransferBatchStatusAPIControllerTest extends AbstractTestBase
{
	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE =
			"Unexpected exception while testing response";

	private IMoneyTransferBAI moneyTransferBAI;
	private static final Logger LOG = LoggerFactory.getLogger(MoneyTransferBatchStatusAPIControllerTest.class);

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
	public void insert()
	{

		// Mock Response 1
		MaintenanceResponse response = new MaintenanceResponse();
		response.setOperationSuccess(true);
		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getMoneyTransferBAI().insertMoneyTransferBatchStatus(
						Matchers.any(MoneyTransferBatchStatusMaintenanceRequest.class)))
						.thenReturn(response);

		try
		{

			setData("{\"moneyTransferBatchStatusList\":[{\"id\":null,\"moneyTransferBatchId\":1,\"actionDueDate\":1420541702261,\"status\":\"CANCELLED\"}],\"note\":\"vsdvsdfsdfsdf\"}");

			performTest("/api/moneyTransferBatchStatus/insert")
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE;
			LOG.error(msg, e);
		}

	}
}
