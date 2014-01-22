package com.sensus.lc.communication.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.communication.model.AlertCommunication;
import com.sensus.lc.communication.model.AlertCommunicationConfig;
import com.sensus.lc.communication.model.request.AlertCommunicationConfigRequest;
import com.sensus.lc.communication.model.request.AlertCommunicationMaintenanceRequest;
import com.sensus.lc.communication.model.request.AlertCommunicationRequest;

/**
 * The Interface IAlertCommunicationDAC.
 */
public interface IAlertCommunicationDAC
{

	/**
	 * Fetch alert communication by id.
	 * 
	 * @param tenantId the tenant id
	 * @return the internal results response
	 */
	InternalResultsResponse<AlertCommunication> fetchAlertCommunicationByRequest(AlertCommunicationRequest request);

	/**
	 * Fetch alert communication config by id.
	 * 
	 * @param tenantId the tenant id
	 * @return the internal results response
	 */
	InternalResultsResponse<AlertCommunicationConfig> fetchAlertCommunicationConfigByRequest(
			AlertCommunicationConfigRequest request);

	/**
	 * Update alert communication.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse updateAlertCommunication(AlertCommunicationMaintenanceRequest request);

}
