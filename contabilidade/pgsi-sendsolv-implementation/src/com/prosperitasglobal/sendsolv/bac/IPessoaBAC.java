package com.prosperitasglobal.sendsolv.bac;


/**
 * The Interface IPessoaBAC.
 */
public interface IPessoaBAC
{

	/**
	 * Insert location.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Pessoa> insertPessoa(PessoaMaintenanceRequest request);

	/**
	 * Update location.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Pessoa> updatePessoa(PessoaMaintenanceRequest request);

	/**
	 * Delete location.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deletePessoa(PessoaMaintenanceRequest request);

	/**
	 * Fetch location by id.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Pessoa> fetchPessoaById(FetchByIdRequest request);

	/**
	 * Fetch location by request.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Pessoa> fetchPessoaByRequest(PessoaInquiryRequest request);

}
