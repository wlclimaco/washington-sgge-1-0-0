package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.PayerInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.PayerResponse;

/**
 * The Interface IPayerBAI.
 */
public interface IPayerBAI
{
	/**
	 * Fetch payer by id.
	 *
	 * @param request The request.
	 * @return The Payer Response.
	 */
	public PayerResponse fetchPayerById(FetchByIdRequest request);

	/**
	 * Fetch payer by request.
	 *
	 * @param request The request.
	 * @return The payer response.
	 */
	public PayerResponse fetchPayerByRequest(PayerInquiryRequest request);
}
