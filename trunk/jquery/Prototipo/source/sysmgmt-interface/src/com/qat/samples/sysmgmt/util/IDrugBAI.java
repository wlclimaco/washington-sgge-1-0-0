package com.qat.samples.sysmgmt.util;

import com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByCodeRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.DrugResponse;

/**
 * The Interface IDrugBAI. (Business Area Interface - BAI)
 */
public interface IDrugBAI
{

	/**
	 * Fetch drug by code.
	 * 
	 * @param request the request
	 * @return the drug response
	 */
	public DrugResponse fetchDrugByCode(FetchByCodeRequest request);

	/**
	 * Insert drug.
	 * 
	 * @param request the request
	 * @return the drug paged response
	 */
	public DrugResponse insertDrug(DrugMaintenanceRequest request);

	/**
	 * Update drug.
	 * 
	 * @param request the request
	 * @return the drug paged response
	 */
	public DrugResponse updateDrug(DrugMaintenanceRequest request);

	/**
	 * Delete drug.
	 * 
	 * @param request the request
	 * @return the drug paged response
	 */
	public DrugResponse deleteDrug(DrugMaintenanceRequest request);

	/**
	 * Fetch all drugs.
	 * 
	 * @param request the request
	 * @return the drug response
	 */
	public DrugResponse fetchAllDrugs(FetchAllRequest request);

	/**
	 * Refresh drugs.
	 * 
	 * @param request the request
	 * @return the drug paged response
	 */
	public DrugResponse refreshDrugs(RefreshRequest request);

	/**
	 * Fetch drugs by request.
	 * 
	 * @param request the request
	 * @return the drug paged response
	 */
	public DrugResponse fetchDrugsByRequest(PagedInquiryRequest request);

}
