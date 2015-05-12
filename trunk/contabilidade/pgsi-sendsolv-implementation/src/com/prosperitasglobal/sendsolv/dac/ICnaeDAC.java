package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Cnae;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ICnaeDAC.
 */
public interface ICnaeDAC
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
	public InternalResultsResponse<Cnae> fetchCnaeByRequest(PagedInquiryRequest request);

}
