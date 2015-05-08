package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.FrequencyBasedEventInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.FrequencyBasedEventResponse;

/**
 * The Interface IFrequencyBasedEventBAI.
 */
public interface IFrequencyBasedEventBAI
{
	/**
	 * Fetch frequency based event by id.
	 *
	 * @param request The request.
	 * @return The frequency based event response.
	 */
	public FrequencyBasedEventResponse fetchBusinessProductPlanById(FetchByIdRequest request);

	/**
	 * Fetch frequency based event by request.
	 *
	 * @param request The request.
	 * @return The frequency based event response.
	 */
	public FrequencyBasedEventResponse fetchFrequencyBasedEventByRequest(FrequencyBasedEventInquiryRequest request);
}
