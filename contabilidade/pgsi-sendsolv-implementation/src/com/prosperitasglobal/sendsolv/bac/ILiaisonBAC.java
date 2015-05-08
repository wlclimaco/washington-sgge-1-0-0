package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.request.LiaisonMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ILiaisonBAC.
 *
 * @author aporto
 * @version 1.0
 * @created 11-Sep-2014 09:51:30 AM
 */
public interface ILiaisonBAC
{

	/**
	 * Insert liaison.
	 *
	 * @param request the request
	 * @return the internal results response< liaison>
	 */
	public InternalResultsResponse<Liaison> insertLiaison(LiaisonMaintenanceRequest request);

	/**
	 * Update liaison.
	 *
	 * @param request the request
	 * @return the internal results response< liaison>
	 */
	public InternalResultsResponse<Liaison> updateLiaison(LiaisonMaintenanceRequest request);

	/**
	 * Delete liaison.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteLiaison(LiaisonMaintenanceRequest request);

	/**
	 * Fetch liaison by id.
	 *
	 * @param request the request
	 * @return the internal results response< liaison>
	 */
	public InternalResultsResponse<Liaison> fetchLiaisonById(FetchByIdRequest request);

	/**
	 * Fetch liaison by request.
	 *
	 * @param request the request
	 * @return the internal results response< liaison>
	 */
	public InternalResultsResponse<Liaison> fetchLiaisonByRequest(PagedInquiryRequest request);

}
