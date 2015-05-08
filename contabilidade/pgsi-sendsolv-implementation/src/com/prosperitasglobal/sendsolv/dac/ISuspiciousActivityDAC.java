package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivity;
import com.prosperitasglobal.sendsolv.model.request.SarInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.SarMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ISuspiciousActivityDAC.
 */
public interface ISuspiciousActivityDAC
{

	/**
	 * This method should be used when a new SuspiciousActivity event is being entered.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse insertSuspiciousActivity(SarMaintenanceRequest request);

	/**
	 * This method should be used when a SuspiciousActivity needs to be deleted.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteSuspiciousActivity(SarMaintenanceRequest request);

	/**
	 * This method can be used to lookup SuspiciousActivity objects by various criteria.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<SuspiciousActivity> fetchSuspiciousActivityByRequest(SarInquiryRequest request);

	/**
	 * This method can be used to lookup SuspiciousActivity by the internal system
	 * identifier or by the business key. If the lookup is by business key, all instances
	 * with the same business key will be returned.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<SuspiciousActivity> fetchSuspiciousActivityByIdRequest(FetchByIdRequest request);

	/**
	 * Fetch business suspicious activity by id request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<SuspiciousActivity> fetchBusinessSuspiciousActivityByIdRequest(
			FetchByIdRequest request);
}
