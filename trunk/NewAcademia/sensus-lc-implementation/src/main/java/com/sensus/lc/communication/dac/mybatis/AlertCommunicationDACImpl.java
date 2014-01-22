package com.sensus.lc.communication.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doUpdate;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.communication.dac.IAlertCommunicationDAC;
import com.sensus.lc.communication.model.AlertCommunication;
import com.sensus.lc.communication.model.AlertCommunicationConfig;
import com.sensus.lc.communication.model.request.AlertCommunicationConfigRequest;
import com.sensus.lc.communication.model.request.AlertCommunicationMaintenanceRequest;
import com.sensus.lc.communication.model.request.AlertCommunicationRequest;

/**
 * Implementation for AlertCommunicationDAC.
 */
public class AlertCommunicationDACImpl extends SqlSessionDaoSupport implements IAlertCommunicationDAC
{

	/**
	 * Queries constant.
	 */
	private static final String FETCH_ALERT_COMMUNICATION_BY_REQUEST = "fetchAlertCommunicationByRequest";
	private static final String FETCH_ALERT_COMMUNICATION_CONFIG_BY_REQUEST = "fetchAlertCommunicationConfigByRequest";
	private static final String UPDATE_ALERT_COMMUNICATION = "UpdateAlertCommunication";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.communication.dac.IAlertCommunicationDAC#fetchAlertCommunicationById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<AlertCommunication> fetchAlertCommunicationByRequest(
			AlertCommunicationRequest request)
	{
		InternalResultsResponse<AlertCommunication> response = new InternalResultsResponse<AlertCommunication>();
		AlertCommunication alertCommunication =
				(AlertCommunication)doQueryForObject(getSqlSession(), FETCH_ALERT_COMMUNICATION_BY_REQUEST, request);

		if (!ValidationUtil.isNull(alertCommunication))
		{
			response.addResult(alertCommunication);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.communication.dac.IAlertCommunicationDAC#fetchAlertCommunicationConfigById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<AlertCommunicationConfig> fetchAlertCommunicationConfigByRequest(
			AlertCommunicationConfigRequest request)
	{
		InternalResultsResponse<AlertCommunicationConfig> response =
				new InternalResultsResponse<AlertCommunicationConfig>();
		doQueryForList(getSqlSession(), FETCH_ALERT_COMMUNICATION_CONFIG_BY_REQUEST, request, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.communication.dac.IAlertCommunicationDAC#updateAlertCommunication(com.sensus.mlc.communication
	 * .model.request.AlertCommunicationMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateAlertCommunication(AlertCommunicationMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();
		doUpdate(getSqlSession(), UPDATE_ALERT_COMMUNICATION, request.getAlertCommunication(), response);
		return response;
	}

}
