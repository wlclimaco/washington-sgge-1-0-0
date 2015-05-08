package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.request.PayerInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IPayerDAC.
 */
public interface IPayerDAC
{
	/**
	 * Delete a payer.
	 *
	 * @param payer The payer to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 */
	public InternalResponse deletePayer(Payer payer);

	/**
	 * Fetch payer by id.
	 *
	 * @param id the id
	 * @return the internal results response<payer>
	 */
	public InternalResultsResponse<Payer> fetchPayerById(Integer id);

	/**
	 * Fetch all payer matching the request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all the fetched {@link Payer}'s along with information
	 *         about the success/failure of the fetch.
	 */
	public InternalResultsResponse<Payer> fetchPayerByRequest(PayerInquiryRequest request);

	/**
	 * Insert a payer.
	 *
	 * @param payer The payer to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 */
	public InternalResponse insertPayer(Payer payer);

	/**
	 * Update a payer.
	 *
	 * @param payer The payer to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 */
	public InternalResponse updatePayer(Payer payer);
}
