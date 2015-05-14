package com.prosperitasglobal.sendsolv.bac;

// TODO: Auto-generated Javadoc
/**
 * The Interface IArquivoBAC.
 */
public interface IArquivoBAC
{

	/**
	 * Insert arquivo.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Arquivo> insertArquivo(ArquivoMaintenanceRequest request);

	/**
	 * Update arquivo.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Arquivo> updateArquivo(ArquivoMaintenanceRequest request);

	/**
	 * Delete arquivo.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteArquivo(ArquivoMaintenanceRequest request);

	/**
	 * Fetch arquivo by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Arquivo> fetchArquivoById(FetchByIdRequest request);

	/**
	 * Fetch arquivo by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Arquivo> fetchArquivoByRequest(ArquivoInquiryRequest request);

}
