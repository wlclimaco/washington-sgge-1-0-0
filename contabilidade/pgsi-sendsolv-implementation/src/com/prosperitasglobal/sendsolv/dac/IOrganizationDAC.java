package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IOrganizationDAC.
 */
public interface IOrganizationDAC
{

	/**
	 * Update organization.
	 *
	 * @param organization the organization
	 * @return the internal results response< organization>
	 */
	public InternalResultsResponse<Organization> updateOrganization(Organization organization);

	/**
	 * Insert organization.
	 *
	 * @param organization the organization
	 * @return the internal results response< organization>
	 */
	public InternalResultsResponse<Organization> insertOrganization(Organization organization);

	/**
	 * Delete organization.
	 *
	 * @param organization the organization
	 * @return the internal response
	 */
	public InternalResponse deleteOrganization(Organization organization);

	/**
	 * Fetch organization by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Organization> fetchOrganizationById(FetchByIdRequest request);

	/**
	 * Fetch organization by request.
	 *
	 * @param request the request
	 * @return the internal results response< organization>
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
	 * @param organization the organization
	 * @return the internal response
	 */
	public InternalResponse applyOrganizationStatus(Organization organization);
}
