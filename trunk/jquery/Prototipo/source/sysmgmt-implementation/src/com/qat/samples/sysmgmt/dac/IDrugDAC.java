package com.qat.samples.sysmgmt.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.Drug;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IDrugDAC. (Data Access Component - DAC)
 */
public interface IDrugDAC
{

	/**
	 * Fetch drug by code.
	 * 
	 * @param drugCode the drug code
	 * @return the drug
	 */
	public Drug fetchDrugByCode(String drugCode);

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
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateDrug(Drug drug);

	/**
	 * Delete drug.
	 * 
	 * @param drug the drug
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteDrug(Drug drug);

	/**
	 * Delete all drugs.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllDrugs();

	/**
	 * Fetch all drugs.
	 * 
	 * @return the list< drug>
	 */
	public List<Drug> fetchAllDrugs();

	/**
	 * Fetch drugs by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Drug> fetchDrugsByRequest(PagedInquiryRequest request);

}
