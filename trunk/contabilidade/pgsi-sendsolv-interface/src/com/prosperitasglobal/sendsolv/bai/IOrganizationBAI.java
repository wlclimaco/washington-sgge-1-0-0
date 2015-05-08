package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.OrganizationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.DocumentResponse;
import com.prosperitasglobal.sendsolv.model.response.OrganizationResponse;
import com.prosperitasglobal.sendsolv.model.response.RiskResponse;

/**
 * The Interface IOrganizationBAI.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:12:02 AM
 */
public interface IOrganizationBAI
{

	/**
	 * Insert organization.
	 *
	 * @param request the request
	 * @return the organization paged response
	 */
	public OrganizationResponse insertOrganization(OrganizationMaintenanceRequest request);

	/**
	 * Insert organization.
	 *
	 * @param request the request
	 * @return the organization paged response
	 */
	public OrganizationResponse updateOrganization(OrganizationMaintenanceRequest request);

	/**
	 * Insert organization.
	 *
	 * @param request the request
	 * @return the organization paged response
	 */
	public OrganizationResponse deleteOrganization(OrganizationMaintenanceRequest request);

	/**
	 * Fetch organization by id.
	 *
	 * @param request the request
	 * @return the organization response
	 */
	public OrganizationResponse fetchOrganizationById(FetchByIdRequest request);

	/**
	 * Fetch organization by request.
	 *
	 * @param request the request
	 * @return the organization paged response
	 */
	public OrganizationResponse fetchOrganizationByRequest(PagedInquiryRequest request);

	/**
	 * Insert document.
	 *
	 * @param request the request
	 * @return the document response
	 */
	public DocumentResponse insertDocument(DocumentMaintenanceRequest request);

	/**
	 * Update document.
	 *
	 * @param request the request
	 * @return the document response
	 */
	public DocumentResponse updateDocument(DocumentMaintenanceRequest request);

	/**
	 * Delete document.
	 *
	 * @param request the request
	 * @return the document response
	 */
	public DocumentResponse deleteDocument(DocumentMaintenanceRequest request);

	/**
	 * Update risk.
	 *
	 * @param request the request
	 * @return the document response
	 */
	public RiskResponse updateRisk(RiskMaintenanceRequest request);

	/**
	 * Apply status.
	 *
	 * @param request the request
	 * @return the organization response
	 */
	public OrganizationResponse applyStatus(OrganizationMaintenanceRequest request);
}
