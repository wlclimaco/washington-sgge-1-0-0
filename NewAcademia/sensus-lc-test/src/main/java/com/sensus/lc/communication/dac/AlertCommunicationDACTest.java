package com.sensus.lc.communication.dac;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;

import java.util.Date;

import org.junit.Test;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractTestBaseDAC;
import com.sensus.lc.communication.model.AlertCommunication;
import com.sensus.lc.communication.model.AlertCommunicationConfig;
import com.sensus.lc.communication.model.request.AlertCommunicationConfigRequest;
import com.sensus.lc.communication.model.request.AlertCommunicationMaintenanceRequest;
import com.sensus.lc.communication.model.request.AlertCommunicationRequest;

/**
 * Unit test for Alert Communication.
 */
public class AlertCommunicationDACTest extends AbstractTestBaseDAC
{

	/** The Constant TENANT_ID. */
	// TODO remove hardcode.
	private static final Integer TENANT_ID = 1;

	/**
	 * Test fetch alert communication by request.
	 */
	@Test
	public void testFetchAlertCommunicationByRequest()
	{
		AlertCommunicationRequest request = new AlertCommunicationRequest(TENANT_ID);
		InternalResultsResponse<AlertCommunication> response =
				getAlertCommunicationDAC().fetchAlertCommunicationByRequest(request);

		assertResponse(response);
	}

	/**
	 * Test fetch alert communication config by request.
	 */
	@Test
	public void testFetchAlertCommunicationConfigByRequest()
	{
		AlertCommunicationConfigRequest request = new AlertCommunicationConfigRequest();
		request.setTenantId(TENANT_ID);
		InternalResultsResponse<AlertCommunicationConfig> response =
				getAlertCommunicationDAC().fetchAlertCommunicationConfigByRequest(request);

		assertResponse(response);
	}

	/**
	 * Test update alert communication.
	 */
	@Test
	public void testUpdateAlertCommunication()
	{

		AlertCommunication alertCommunication = new AlertCommunication();
		alertCommunication.setTenantId(TENANT_ID);
		alertCommunication.setSuccessIndicator(Boolean.TRUE);
		alertCommunication.setCycleStartTime(new Date());
		alertCommunication.setPhaseIndicator(2);
		alertCommunication.setPhaseStartTime(new Date());
		AlertCommunicationMaintenanceRequest request = new AlertCommunicationMaintenanceRequest(alertCommunication);

		InternalResponse response = getAlertCommunicationDAC().updateAlertCommunication(request);
		assertResponse(response);
	}

}
