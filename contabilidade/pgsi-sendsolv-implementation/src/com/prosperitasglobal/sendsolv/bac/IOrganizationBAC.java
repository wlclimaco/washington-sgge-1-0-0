package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.OrganizationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IOrganizationBAC. (Business Area Component - BAC)
 */
public interface IOrganizationBAC
{

	/**
	 * Insert organization.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResultsResponse<Organization> insertOrganization(OrganizationMaintenanceRequest request);

	/**
	 * Update organization.
	 *
	 * @param request the request
	 * @return the internal results response< organization>
	 */
	public InternalResultsResponse<Organization> updateOrganization(OrganizationMaintenanceRequest request);

	/**
	 * Delete organization.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteOrganization(OrganizationMaintenanceRequest request);

	/**
	 * Fetch organization by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Organization> fetchOrganizationById(FetchByIdRequest request);

	/**
	 * Fetch organizations by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Organization> fetchOrganizationByRequest(PagedInquiryRequest request);

	/**
	 * Insert document.
	 *
	 * @param request the request
	 * @return the internal results response< document>
	 */
	public InternalResultsResponse<Document> insertDocument(DocumentMaintenanceRequest request);

	/**
	 * Update document.
	 *
	 * @param request the request
	 * @return the internal results response< document>
	 */
	public InternalResultsResponse<Document> updateDocument(DocumentMaintenanceRequest request);

	/**
	 * Delete document.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteDocument(DocumentMaintenanceRequest request);

	/**
	 * Update risk.
	 *
	 * @param request the request
	 * @return the internal results response< risk>
	 */
	public InternalResultsResponse<Risk> updateRisk(RiskMaintenanceRequest request);

	/**
	 * Apply organization status.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse applyOrganizationStatus(OrganizationMaintenanceRequest request);

}
