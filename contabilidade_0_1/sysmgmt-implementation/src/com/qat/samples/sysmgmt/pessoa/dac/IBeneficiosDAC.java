package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.beneficios.BeneficioPessoa;
import com.qat.samples.sysmgmt.beneficios.Beneficios;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

// TODO: Auto-generated Javadoc
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

	/**
	 * Fetch beneficios by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Beneficios> fetchBeneficiosByRequest(PagedInquiryRequest request);

	/**
	 * Update beneficio pessoa.
	 * 
	 * @param beneficios the beneficios
	 * @return the integer
	 */
	public Integer updateBeneficioPessoa(BeneficioPessoa beneficios);

	/**
	 * Insert beneficio pessoa.
	 * 
	 * @param beneficios the beneficios
	 * @return the integer
	 */
	public Integer insertBeneficioPessoa(BeneficioPessoa beneficios);

	/**
	 * Delete beneficio pessoa.
	 * 
	 * @param beneficios the beneficios
	 * @return the integer
	 */
	public Integer deleteBeneficioPessoa(BeneficioPessoa beneficios);

}
