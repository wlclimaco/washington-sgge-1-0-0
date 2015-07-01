package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.PedidoCompras;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IPedidoComprasDAC.
 */
public interface IPedidoCompraDAC
{

	/**
	 * Update pedido compra.
	 *
	 * @param pedidoCompra the pedido compra
	 * @param response the response
	 * @return the integer
	 */
	public Integer updatePedidoCompras(PedidoCompras pedidoCompra, InternalResultsResponse<?> response);

	/**
	 * Insert pedido compra.
	 *
	 * @param pedidoCompra the pedido compra
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertPedidoCompras(PedidoCompras pedidoCompra, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete pedido compra.
	 *
	 * @param pedidoCompra the pedido compra
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePedidoCompras(PedidoCompras pedidoCompra, InternalResultsResponse<?> response);

	/**
	 * Fetch pedido compra by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasByRequest(PagedInquiryRequest request);

}
