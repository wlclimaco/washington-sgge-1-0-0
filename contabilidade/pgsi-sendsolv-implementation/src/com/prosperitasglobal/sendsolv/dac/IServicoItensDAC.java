package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.ServicoItens;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IServicoItensDAC.
 */
public interface IServicoItensDAC
{

	/**
	 * Update servico itens.
	 *
	 * @param servicoItens the servico itens
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateServicoItens(ServicoItens servicoItens, InternalResultsResponse<?> response);

	/**
	 * Insert servico itens.
	 *
	 * @param servicoItens the servico itens
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertServicoItens(ServicoItens servicoItens, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete servico itens.
	 *
	 * @param servicoItens the servico itens
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteServicoItens(ServicoItens servicoItens, InternalResultsResponse<?> response);

	/**
	 * Fetch servico itens by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<ServicoItens> fetchServicoItensByRequest(PagedInquiryRequest request);

}
