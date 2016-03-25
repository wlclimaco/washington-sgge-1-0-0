package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.condominio.model.Mandado;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

public interface IMandadoDAC
{
	/**
	 * Update mandado.
	 * 
	 * @param mandado the mandado
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateMandado(Mandado mandado, InternalResultsResponse<?> response);

	/**
	 * Insert mandado.
	 * 
	 * @param mandado the mandado
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertMandado(Mandado mandado, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business mandado.
	 * 
	 * @param mandado the mandado
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteMandado(Mandado mandado, InternalResultsResponse<?> response);

	public InternalResultsResponse<Mandado> fetchMandadoById(FetchByIdRequest request);

	public InternalResultsResponse<Mandado> fetchMandadoByRequest(PagedInquiryRequest request);

}