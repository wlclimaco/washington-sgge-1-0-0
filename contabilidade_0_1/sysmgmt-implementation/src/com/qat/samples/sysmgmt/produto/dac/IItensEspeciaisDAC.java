package com.qat.samples.sysmgmt.produto.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.ItensEspeciais;

/**
 * The Interface IItensEspeciaissDAC.
 */
public interface IItensEspeciaisDAC
{

	/**
	 * Update evento.
	 * 
	 * @param itensEspeciais the itens especiais
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateItensEspeciais(ItensEspeciais itensEspeciais, InternalResultsResponse<?> response);

	/**
	 * Insert ItensEspeciais.
	 * 
	 * @param itensEspeciais the itens especiais
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertItensEspeciais(ItensEspeciais itensEspeciais, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete business ItensEspeciais.
	 * 
	 * @param itensEspeciais the itens especiais
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteItensEspeciais(ItensEspeciais itensEspeciais, InternalResultsResponse<?> response);

	/**
	 * Fetch eventos by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<ItensEspeciais> fetchItensEspeciaisByRequest(PagedInquiryRequest request);

}
