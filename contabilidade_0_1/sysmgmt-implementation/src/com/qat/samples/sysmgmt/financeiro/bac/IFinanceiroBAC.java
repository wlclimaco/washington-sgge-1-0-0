package com.qat.samples.sysmgmt.financeiro.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.financeiro.model.Financeiro;
import com.qat.samples.sysmgmt.financeiro.model.request.FinanceiroInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FinanceiroMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface IFinanceiroBAC.
 */
public interface IFinanceiroBAC
{

	/**
	 * Insert location.
	 * 
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Financeiro> insertFinanceiro(FinanceiroMaintenanceRequest request);

	/**
	 * Update location.
	 * 
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Financeiro> updateFinanceiro(FinanceiroMaintenanceRequest request);

	/**
	 * Delete location.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteFinanceiro(FinanceiroMaintenanceRequest request);

	/**
	 * Fetch location by id.
	 * 
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Financeiro> fetchFinanceiroById(FetchByIdRequest request);

	/**
	 * Fetch location by request.
	 * 
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Financeiro> fetchFinanceiroByRequest(FinanceiroInquiryRequest request);

}
