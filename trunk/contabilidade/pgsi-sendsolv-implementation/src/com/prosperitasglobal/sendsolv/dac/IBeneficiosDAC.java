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

	public Integer updateBeneficioPessoa(BeneficioPessoa beneficios);

	public Integer insertBeneficioPessoa(BeneficioPessoa beneficios);

	public Integer deleteBeneficioPessoa(BeneficioPessoa beneficios);

}
