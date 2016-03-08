package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.agencia.Agencia;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IAgenciaDAC.
 */
public interface IAgenciaDAC
{

	/**
	 * Update agencia.
	 * 
	 * @param agencia the agencia
	 * @return the internal results response< agencia>
	 */
	public Integer updateAgencia(Agencia agencia);

	/**
	 * Insert agencia.
	 * 
	 * @param agencia the agencia
	 * @return the internal results response< agencia>
	 */
	public Integer insertAgencia(Agencia agencia);

	/**
	 * Delete agencia.
	 * 
	 * @param agencia the agencia
	 * @return the internal response
	 */
	public Integer deleteAgencia(Agencia agencia);

	/**
	 * Fetch agencia by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Agencia> fetchAgenciaById(FetchByIdRequest request);

	/**
	 * Fetch all agencias.
	 * 
	 * @return the internal results response< agencia>
	 */
	public InternalResultsResponse<Agencia> fetchAllAgencias();

	/**
	 * Fetch agencia by request.
	 * 
	 * @param request the request
	 * @return the internal results response< agencia>
	 */
	public InternalResultsResponse<Agencia> fetchAgenciaByRequest(PagedInquiryRequest request);

}
