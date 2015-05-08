package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.request.PayerInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IPayerBAC.
 */
public interface IPayerBAC
{

	/**
	 * Fetch payer by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Payer> fetchPayerById(FetchByIdRequest request);

	/**
	 * Fetch payer by request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all of the {@link Payer} that were found with the matching
	 *         criteria. Also contains information about whether the fetch was successful or not.
	 */
	public InternalResultsResponse<Payer> fetchPayerByRequest(PayerInquiryRequest request);
}
