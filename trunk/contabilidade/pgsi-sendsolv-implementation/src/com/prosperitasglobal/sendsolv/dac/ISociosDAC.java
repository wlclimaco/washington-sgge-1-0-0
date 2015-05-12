package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Socio;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ISocioDAC.
 */
public interface ISociosDAC
{

	/**
	 * Update cnae.
	 *
	 * @param cnae the cnae
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Socio> updateSocio(Socio cnae);

	/**
	 * Insert cnae.
	 *
	 * @param cnae the cnae
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Socio> insertSocio(Socio cnae);

	/**
	 * Delete cnae.
	 *
	 * @param cnae the cnae
	 * @return the internal response
	 */
	public InternalResponse deleteSocio(Socio cnae);

	/**
	 * Fetch cnae by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Socio> fetchSocioById(FetchByIdRequest request);

	/**
	 * Fetch all cnaes.
	 *
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Socio> fetchAllSocios();

	/**
	 * Fetch cnae by request.
	 *
	 * @param request the request
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Socio> fetchSocioByRequest(PagedInquiryRequest request);

}
