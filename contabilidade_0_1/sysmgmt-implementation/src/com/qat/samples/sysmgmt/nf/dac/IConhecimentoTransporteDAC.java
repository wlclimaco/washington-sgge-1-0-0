package com.qat.samples.sysmgmt.nf.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.ConhecimentoTransporte;

/**
 * The Interface IConhecimentoTransporteDAC.
 */
public interface IConhecimentoTransporteDAC
{

	/**
	 * Update evento.
	 * 
	 * @param conhecimentoTransporte the conhecimento transporte
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateConhecimentoTransporte(ConhecimentoTransporte conhecimentoTransporte,
			InternalResultsResponse<?> response);

	/**
	 * Insert ConhecimentoTransporte.
	 * 
	 * @param conhecimentoTransporte the conhecimento transporte
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertConhecimentoTransporte(ConhecimentoTransporte conhecimentoTransporte, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete business ConhecimentoTransporte.
	 * 
	 * @param conhecimentoTransporte the conhecimento transporte
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteConhecimentoTransporte(ConhecimentoTransporte conhecimentoTransporte,
			InternalResultsResponse<?> response);

	/**
	 * Fetch conhecimento transporte by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<ConhecimentoTransporte> fetchConhecimentoTransporteByRequest(
			PagedInquiryRequest request);

	InternalResultsResponse<ConhecimentoTransporte> fetchConhecimentoTransporteById(Integer id);

}
