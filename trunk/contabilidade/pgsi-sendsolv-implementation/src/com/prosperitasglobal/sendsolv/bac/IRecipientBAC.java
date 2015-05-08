package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.request.RecipientInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RecipientMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IRecipientBAC.
 */
public interface IRecipientBAC
{

	/**
	 * Insert recipient.
	 *
	 * @param request the request
	 * @return the internal results response< recipient>
	 */
	public InternalResultsResponse<Recipient> insertRecipient(RecipientMaintenanceRequest request);

	/**
	 * Update recipient.
	 *
	 * @param request the request
	 * @return the internal results response< recipient>
	 */
	public InternalResultsResponse<Recipient> updateRecipient(RecipientMaintenanceRequest request);

	/**
	 * Delete recipient.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteRecipient(RecipientMaintenanceRequest request);

	/**
	 * Fetch recipient by id.
	 *
	 * @param request the request
	 * @return the internal results response< recipient>
	 */
	public InternalResultsResponse<Recipient> fetchRecipientById(FetchByIdRequest request);

	/**
	 * Fetch recipient by request.
	 *
	 * @param request the request
	 * @return the internal results response< recipient>
	 */
	public InternalResultsResponse<Recipient> fetchRecipientByRequest(RecipientInquiryRequest request);

	/**
	 * Apply status.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse applyStatus(RecipientMaintenanceRequest request);

}
