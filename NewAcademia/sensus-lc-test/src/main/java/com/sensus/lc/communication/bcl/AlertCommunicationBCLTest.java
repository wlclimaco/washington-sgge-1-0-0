package com.sensus.lc.communication.bcl;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.communication.model.AlertCommunication;
import com.sensus.lc.communication.model.AlertCommunicationConfig;
import com.sensus.lc.communication.model.request.AlertCommunicationConfigRequest;
import com.sensus.lc.communication.model.request.AlertCommunicationMaintenanceRequest;
import com.sensus.lc.communication.model.request.AlertCommunicationRequest;

@ContextConfiguration(locations = {
		"classpath:com/sensus/mlc/communication/alertcommunicationbcltest.xml"})
public class AlertCommunicationBCLTest extends AbstractTestBaseBusiness
{

	private IAlertCommunicationBCL alertCommunicationBCL;

	/**
	 * @return the alertCommunicationBCL
	 */
	public IAlertCommunicationBCL getAlertCommunicationBCL()
	{
		return alertCommunicationBCL;
	}

	/**
	 * @param alertCommunicationBCL the alertCommunicationBCL to set
	 */
	@Resource(name = "alertCommunicationBCLTarget")
	public void setAlertCommunicationBCL(IAlertCommunicationBCL alertCommunicationBCL)
	{
		this.alertCommunicationBCL = alertCommunicationBCL;
	}

	/**
	 * Test fetch alert communication by request.
	 */
	@Test
	public void testFetchAlertCommunicationByRequest()
	{
		AlertCommunicationRequest request = new AlertCommunicationRequest(1);
		InternalResultsResponse<AlertCommunication> response =
				getAlertCommunicationBCL().fetchAlertCommunicationByRequest(request);

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch alert communication config by request.
	 */
	@Test
	public void testFetchAlertCommunicationConfigByRequest()
	{
		AlertCommunicationConfigRequest request = new AlertCommunicationConfigRequest();
		request.setTenantId(TestBaseUtil.createTenant().getId());
		request.setUserContext(TestBaseUtil.createUserContext());
		InternalResultsResponse<AlertCommunicationConfig> response =
				getAlertCommunicationBCL().fetchAlertCommunicationConfigByRequest(request);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test update alert communication.
	 */
	@Test
	public void testUpdateAlertCommunication()
	{
		AlertCommunication alertCommunication = new AlertCommunication();
		AlertCommunicationMaintenanceRequest request = new AlertCommunicationMaintenanceRequest(alertCommunication);
		InternalResponse response = getAlertCommunicationBCL().updateAlertCommunication(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test verify alert communication.
	 */
	@Test
	public void testVerifyAlertCommunication()
	{
		/**
		 * this method is executed by a batch job, there is no response to validate.
		 */
		getAlertCommunicationBCL().verifyAlertCommunication();
	}

}
