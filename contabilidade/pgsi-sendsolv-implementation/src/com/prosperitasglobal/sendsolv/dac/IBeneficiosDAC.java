package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Beneficios;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
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
	public Integer updateBeneficios(Beneficios beneficios);

	/**
	 * Insert beneficios.
	 *
	 * @param beneficios the beneficios
	 * @return the internal results response< beneficios>
	 */
	public Integer insertBeneficios(Beneficios beneficios);

	/**
	 * Delete beneficios.
	 *
	 * @param beneficios the beneficios
	 * @return the internal response
	 */
	public Integer deleteBeneficios(Beneficios beneficios);

	public InternalResultsResponse<Beneficios> fetchBeneficiosByRequest(PagedInquiryRequest request);

}
