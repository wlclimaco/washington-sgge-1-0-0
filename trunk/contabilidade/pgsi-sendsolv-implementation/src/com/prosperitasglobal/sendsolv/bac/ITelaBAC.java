package com.prosperitasglobal.sendsolv.bac;


/**
 * The Interface ITelaBAC.
 */
public interface ITelaBAC
{

	/**
	 * Insert location.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Tela> insertTela(TelaMaintenanceRequest request);

	/**
	 * Update location.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Tela> updateTela(TelaMaintenanceRequest request);

	/**
	 * Delete location.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteTela(TelaMaintenanceRequest request);

	/**
	 * Fetch location by id.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Tela> fetchTelaById(FetchByIdRequest request);

	/**
	 * Fetch location by request.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Tela> fetchTelaByRequest(PagedInquiryRequest request);

}
