package com.prosperitasglobal.sendsolv.bac;


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
