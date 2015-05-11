package com.prosperitasglobal.sendsolv.dac;


/**
 * The Interface ICnaeDAC.
 */
public interface IEmailDAC
{

	/**
	 * Update cnae.
	 *
	 * @param cnae the cnae
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Cnae> updateCnae(Cnae cnae);

	/**
	 * Insert cnae.
	 *
	 * @param cnae the cnae
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Cnae> insertCnae(Cnae cnae);

	/**
	 * Delete cnae.
	 *
	 * @param cnae the cnae
	 * @return the internal response
	 */
	public InternalResponse deleteCnae(Cnae cnae);

	/**
	 * Fetch cnae by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Cnae> fetchCnaeById(FetchByIdRequest request);

	/**
	 * Fetch all cnaes.
	 *
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Cnae> fetchAllCnaes();

	/**
	 * Fetch cnae by request.
	 *
	 * @param request the request
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Cnae> fetchCnaeByRequest(CnaeInquiryRequest request);

}
