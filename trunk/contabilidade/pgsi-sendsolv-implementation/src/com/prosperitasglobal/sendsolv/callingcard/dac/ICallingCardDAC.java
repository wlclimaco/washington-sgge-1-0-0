package com.prosperitasglobal.sendsolv.callingcard.dac;

import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.prosperitasglobal.sendsolv.callingcard.model.request.CallingCardMaintenanceRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * This Interface exposes operations to deal with Calling Cards, namely add/remove amounts, assign a Calling Card to a
 * Member and retrieve pin numbers.
 */
public interface ICallingCardDAC
{

	/**
	 * Insert a new calling card (not assigned to any {@link com.prosperitasglobal.sendsolv.model.Member}).
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<CallingCardInfo> insertCallingCard(CallingCardMaintenanceRequest request);

	/**
	 * Assign a calling card to a {@link com.prosperitasglobal.sendsolv.model.Member}.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<CallingCardInfo> updateCallingCard(CallingCardMaintenanceRequest request);

	/**
	 * Fetch available pins,i.e., these not associated to a {@link com.prosperitasglobal.sendsolv.model.Member}.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<CallingCardInfo> fetchAvailablePins(CallingCardMaintenanceRequest request);

	/**
	 * Fetch calling card id pin associated to a {@link com.prosperitasglobal.sendsolv.model.Member} id.
	 *
	 * @param personId the person id
	 * @return the internal results response
	 */
	public InternalResultsResponse<Integer> fetchIdPinByPersonId(Integer personId);

}
