package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Beneficios;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IBeneficiosDAC.
 */
public interface IBeneficiosDAC
{

	/**
	 * Update beneficios.
	 *
	 * @param beneficios the beneficios
	 * @return the internal results response< beneficios>
	 */
	public InternalResultsResponse<Beneficios> updateBeneficios(Beneficios beneficios);

	/**
	 * Insert beneficios.
	 *
	 * @param beneficios the beneficios
	 * @return the internal results response< beneficios>
	 */
	public InternalResultsResponse<Beneficios> insertBeneficios(Beneficios beneficios);

	/**
	 * Delete beneficios.
	 *
	 * @param beneficios the beneficios
	 * @return the internal response
	 */
	public InternalResponse deleteBeneficios(Beneficios beneficios);

	/**
	 * Fetch beneficios by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Beneficios> fetchBeneficiosById(FetchByIdRequest request);

	/**
	 * Fetch all beneficioss.
	 *
	 * @return the internal results response< beneficios>
	 */
	public InternalResultsResponse<Beneficios> fetchAllBeneficioss();

	/**
	 * Fetch beneficios by request.
	 *
	 * @param request the request
	 * @return the internal results response< beneficios>
	 */
	public InternalResultsResponse<Beneficios> fetchBeneficiosByRequest(PagedInquiryRequest request);

}
