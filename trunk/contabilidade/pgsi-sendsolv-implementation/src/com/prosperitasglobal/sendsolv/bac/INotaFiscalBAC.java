package com.prosperitasglobal.sendsolv.bac;


/**
 * The Interface INotaFiscalBAC.
 */
public interface INotaFiscalBAC
{

	/**
	 * Insert location.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<NotaFiscal> insertNotaFiscal(NotaFiscalMaintenanceRequest request);

	/**
	 * Update location.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<NotaFiscal> updateNotaFiscal(NotaFiscalMaintenanceRequest request);

	/**
	 * Delete location.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteNotaFiscal(NotaFiscalMaintenanceRequest request);

	/**
	 * Fetch location by id.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<NotaFiscal> fetchNotaFiscalById(FetchByIdRequest request);

	/**
	 * Fetch location by request.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<NotaFiscal> fetchNotaFiscalByRequest(NotaFiscalInquiryRequest request);

}
