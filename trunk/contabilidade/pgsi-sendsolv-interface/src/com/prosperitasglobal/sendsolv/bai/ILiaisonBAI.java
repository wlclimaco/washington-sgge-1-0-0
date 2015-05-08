package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.LiaisonMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LiaisonResponse;

/**
 * The Interface ILiaisonBAI.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:24:55 AM
 */
public interface ILiaisonBAI
{

	/**
	 * Insert liaison.
	 *
	 * @param request the request
	 * @return the liaison response
	 */
	public LiaisonResponse insertLiaison(LiaisonMaintenanceRequest request);

	/**
	 * Update liaison.
	 *
	 * @param request the request
	 * @return the liaison response
	 */
	public LiaisonResponse updateLiaison(LiaisonMaintenanceRequest request);

	/**
	 * Delete liaison.
	 *
	 * @param request the request
	 * @return the liaison response
	 */
	public LiaisonResponse deleteLiaison(LiaisonMaintenanceRequest request);

	/**
	 * Fetch Liaison by id.
	 *
	 * @param request the request
	 * @return the Liaison response
	 */
	public LiaisonResponse fetchLiaisonById(FetchByIdRequest request);

	/**
	 * Fetch Liaison by request.
	 *
	 * @param request the request
	 * @return the Liaison response
	 */
	public LiaisonResponse fetchLiaisonByRequest(PagedInquiryRequest request);

	/**
	 * Fetch Liaison by organization.
	 *
	 * @param request the request
	 * @return the Liaison response
	 */
	public LiaisonResponse fetchLiaisonByOrganization(PagedInquiryRequest request);

	/**
	 * Fetch liaison by location.
	 *
	 * @param request the request
	 * @return the liaison response
	 */
	public LiaisonResponse fetchLiaisonByLocation(PagedInquiryRequest request);

}