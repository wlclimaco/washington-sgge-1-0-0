package com.prosperitasglobal.sendsolv.dac;


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

	public InternalResultsResponse<Beneficios> fetchBeneficiosByRequest(PagedInquiryRequest request);

}
