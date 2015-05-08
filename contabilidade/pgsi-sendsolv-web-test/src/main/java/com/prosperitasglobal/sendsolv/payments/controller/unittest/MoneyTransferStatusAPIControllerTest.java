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
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferStatusMaintenanceRequest;
import com.qat.framework.model.response.MaintenanceResponse;

public class MoneyTransferStatusAPIControllerTest extends AbstractTestBase
{
	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE =
			"Unexpected exception while testing response";

	private IMoneyTransferBAI moneyTransferBAI;
	private static final Logger LOG = LoggerFactory.getLogger(MoneyTransferStatusAPIControllerTest.class);

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
				getMoneyTransferBAI().insertMoneyTransferStatus(
						Matchers.any(MoneyTransferStatusMaintenanceRequest.class)))
				.thenReturn(response);

		try
		{

			setData("{\"moneyTransferStatusList\":[{\"id\":null,\"moneyTransferId\":2689,\"status\":\"CANCELLED\",\"response\":null,\"moneyTransferTransaction\":null,\"modelAction\":\"INSERT\"}]}");
			performTest("/api/moneyTransferStatus/insert")
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
