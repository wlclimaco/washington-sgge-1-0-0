package com.sensus.lc.communication.dac;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.communication.model.AlertCommunication;
import com.sensus.lc.communication.model.AlertCommunicationConfig;
import com.sensus.lc.communication.model.request.AlertCommunicationConfigRequest;
import com.sensus.lc.communication.model.request.AlertCommunicationMaintenanceRequest;
import com.sensus.lc.communication.model.request.AlertCommunicationRequest;

/**
 * The Class MockAlertCommunicationDAC.
 */
public class MockAlertCommunicationDAC extends AbstractMockBase implements IAlertCommunicationDAC
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.communication.dac.IAlertCommunicationDAC#fetchAlertCommunicationByRequest(com.sensus.lc.communication
	 * .model.request.AlertCommunicationRequest)
	 */
	@Override
	public InternalResultsResponse<AlertCommunication> fetchAlertCommunicationByRequest(
			AlertCommunicationRequest request)
	{
		InternalResultsResponse<AlertCommunication> response = new InternalResultsResponse<AlertCommunication>();
		AlertCommunication value = new AlertCommunication();

		Calendar dateCalendar = new GregorianCalendar();
		dateCalendar.add(Calendar.MINUTE, -10);

		value.setCycleStartTime(dateCalendar.getTime());
		value.setPhaseStartTime(dateCalendar.getTime());
		value.setPhaseIndicator(1);
		value.setTenantId(1);
		response.addResult(value);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.communication.dac.IAlertCommunicationDAC#fetchAlertCommunicationConfigByRequest(com.sensus.lc.
	 * communication.model.request.AlertCommunicationConfigRequest)
	 */
	@Override
	public InternalResultsResponse<AlertCommunicationConfig> fetchAlertCommunicationConfigByRequest(
			AlertCommunicationConfigRequest request)
	{
		InternalResultsResponse<AlertCommunicationConfig> response =
				new InternalResultsResponse<AlertCommunicationConfig>();

		List<AlertCommunicationConfig> configs = new ArrayList<AlertCommunicationConfig>();

		AlertCommunicationConfig config = new AlertCommunicationConfig();
		config.setElapsedTime(1);
		config.setPhase(0);
		config.setTenantId(1);
		configs.add(config);

		config = new AlertCommunicationConfig();
		config.setElapsedTime(5);
		config.setPhase(1);
		config.setTenantId(1);
		configs.add(config);

		config = new AlertCommunicationConfig();
		config.setElapsedTime(5);
		config.setPhase(2);
		config.setTenantId(1);
		configs.add(config);

		config = new AlertCommunicationConfig();
		config.setElapsedTime(5);
		config.setPhase(3);
		config.setTenantId(1);
		configs.add(config);

		config = new AlertCommunicationConfig();
		config.setElapsedTime(5);
		config.setPhase(4);
		config.setTenantId(1);
		configs.add(config);

		response.getResultsList().addAll(configs);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.communication.dac.IAlertCommunicationDAC#updateAlertCommunication(com.sensus.lc.communication.model
	 * .request.AlertCommunicationMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateAlertCommunication(AlertCommunicationMaintenanceRequest request)
	{
		return new InternalResponse();
	}

}
