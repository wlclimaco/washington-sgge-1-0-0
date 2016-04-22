package com.qat.samples.sysmgmt.bac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.Drug;
import com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IDrugBAC.
 */
public interface IDrugBAC
{

	/**
	 * Insert drug.
	 *
	 * @param drug the drug
	 * @return the internal response
	 */
	public InternalResultsResponse<Drug> insertDrug(DrugMaintenanceRequest drug);

	/**
	 * Update drug.
	 *
	 * @param drug the drug
	 * @return the internal response
	 */
	public InternalResultsResponse<Drug> updateDrug(DrugMaintenanceRequest request);

	/**
	 * Delete drug.
	 *
	 * @param drug the drug
	 * @return the internal response
	 */
	public InternalResultsResponse<Drug> deleteDrug(DrugMaintenanceRequest request);

	/**
	 * Refresh drugs.
	 *
	 * @param refreshNumber the refresh number
	 */
	public InternalResultsResponse<Drug> refreshDrugs(RefreshRequest request);

	/**
	 * Fetch drug by code.
	 *
	 * @param drugCode the drug code
	 * @return the internal results response
	 */
	public InternalResultsResponse<Drug> fetchDrugByCode(String drugCode);

	/**
	 * Fetch all drugs.
	 *
	 * @return the internal results response
	 */
	public InternalResultsResponse<Drug> fetchAllDrugs();

	/**
	 * Fetch drugs by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Drug> fetchDrugsByRequest(PagedInquiryRequest request);
}
