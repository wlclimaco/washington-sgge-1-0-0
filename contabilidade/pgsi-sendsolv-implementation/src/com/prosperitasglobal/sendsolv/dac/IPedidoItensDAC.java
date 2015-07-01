package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.PedidoItens;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IPedidoItensDAC.
 */
public interface IPedidoItensDAC
{

	/**
	 * Update evento.
	 *
	 * @param pedidoItens the conhecimento transporte
	 * @param response the response
	 * @return the integer
	 */
	public Integer updatePedidoItens(PedidoItens pedidoItens, InternalResultsResponse<?> response);

	/**
	 * Insert PedidoItens.
	 *
	 * @param pedidoItens the conhecimento transporte
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertPedidoItens(PedidoItens pedidoItens, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete business PedidoItens.
	 *
	 * @param pedidoItens the conhecimento transporte
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePedidoItens(PedidoItens pedidoItens, InternalResultsResponse<?> response);

	/**
	 * Fetch conhecimento transporte by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<PedidoItens> fetchPedidoItensByRequest(
			PagedInquiryRequest request);

}
