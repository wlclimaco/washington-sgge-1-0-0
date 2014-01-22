package com.sensus.lc.communication.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.communication.model.AlertCommunication;
import com.sensus.lc.communication.model.AlertCommunicationConfig;
import com.sensus.lc.communication.model.request.AlertCommunicationConfigRequest;
import com.sensus.lc.communication.model.request.AlertCommunicationMaintenanceRequest;
import com.sensus.lc.communication.model.request.AlertCommunicationRequest;
import com.sensus.lc.communication.model.request.EmailRequest;

/**
 * The Interface IAlertCommunicationBCL.
 */
public interface IAlertCommunicationBCL
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

	/**
	 * Send email.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse sendEmail(EmailRequest request);

	/**
	 * Verify alert communication.
	 */
	void verifyAlertCommunication();

}
