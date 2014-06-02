package com.qat.samples.sysmgmt.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.Drug;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

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
	public InternalResponse insertDrug(Drug drug);

	/**
	 * Update drug.
	 * 
	 * @param drug the drug
	 * @return the internal response
	 */
	public InternalResponse updateDrug(Drug drug);

	/**
	 * Delete drug.
	 * 
	 * @param drug the drug
	 * @return the internal response
	 */
	public InternalResponse deleteDrug(Drug drug);

	/**
	 * Refresh drugs.
	 * 
	 * @param refreshNumber the refresh number
	 */
	public void refreshDrugs(Integer refreshNumber);

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
