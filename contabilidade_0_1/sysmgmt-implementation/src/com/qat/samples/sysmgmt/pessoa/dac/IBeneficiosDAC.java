package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.beneficios.BeneficioPessoa;
import com.qat.samples.sysmgmt.beneficios.Beneficios;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
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
	public Integer updateBeneficios(Beneficios beneficios, InternalResultsResponse<?> response);

	/**
	 * Insert beneficios.
	 * 
	 * @param beneficios the beneficios
	 * @return the internal results response< beneficios>
	 */
	public Integer insertBeneficios(Beneficios beneficios, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete beneficios.
	 * 
	 * @param beneficios the beneficios
	 * @return the internal response
	 */
	public Integer deleteBeneficios(Beneficios beneficios, InternalResultsResponse<?> response);

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
	public Integer updateBeneficioPessoa(BeneficioPessoa beneficios, InternalResultsResponse<?> response);

	/**
	 * Insert beneficio pessoa.
	 * 
	 * @param beneficios the beneficios
	 * @return the integer
	 */
	public Integer insertBeneficioPessoa(BeneficioPessoa beneficios, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete beneficio pessoa.
	 * 
	 * @param beneficios the beneficios
	 * @return the integer
	 */
	public Integer deleteBeneficioPessoa(BeneficioPessoa beneficios, InternalResultsResponse<?> response);

	public InternalResultsResponse<Beneficios> fetchBeneficiosById(FetchByIdRequest request);

}
