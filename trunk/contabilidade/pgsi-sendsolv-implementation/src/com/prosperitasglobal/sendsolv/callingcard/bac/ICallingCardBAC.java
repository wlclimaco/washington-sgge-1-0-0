package com.prosperitasglobal.sendsolv.callingcard.bac;

import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.prosperitasglobal.sendsolv.callingcard.model.request.CallingCardMaintenanceRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * This Interface exposes operations to deal with Calling Cards, namely add/remove amounts, assign a Calling Card to a
 * Member and retrieve pin numbers.
 */
public interface ICallingCardBAC
{

	/**
	 * Refill card related to the Person sent inside {@link CallingCardMaintenanceRequest}.
	 * The amount can be positive or negative.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<CallingCardInfo> refillCard(CallingCardMaintenanceRequest request);

	/**
	 * Assign a CallingCard to a {@link com.prosperitasglobal.sendsolv.model.Member}.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<CallingCardInfo> assignCard(CallingCardMaintenanceRequest request);

	/**
	 * Retrieve more pin numbers and add them to the pin number table.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<CallingCardInfo> retrieveMorePinNumbers(CallingCardMaintenanceRequest request);

	/**
	 * Gets the card balance given a {@link com.prosperitasglobal.sendsolv.model.Member}.
	 *
	 * @param request the request
	 * @return the card balance
	 */
	public InternalResultsResponse<CallingCardInfo> getCardBalance(CallingCardMaintenanceRequest request);

	/**
	 * Fetch available pins (the ones that don't have {@link com.prosperitasglobal.sendsolv.model.Person}. associated)
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<CallingCardInfo> fetchAvailablePins(CallingCardMaintenanceRequest request);
}
