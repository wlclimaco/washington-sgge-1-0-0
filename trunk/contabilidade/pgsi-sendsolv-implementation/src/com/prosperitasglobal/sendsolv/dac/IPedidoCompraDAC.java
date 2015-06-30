package com.prosperitasglobal.sendsolv.dac;

/**
 * The Interface IPedidoCompraDAC.
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
	public Integer updatePedidoCompra(PedidoCompra pedidoCompra, InternalResultsResponse<?> response);

	/**
	 * Insert pedido compra.
	 *
	 * @param pedidoCompra the pedido compra
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertPedidoCompra(PedidoCompra pedidoCompra, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete pedido compra.
	 *
	 * @param pedidoCompra the pedido compra
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePedidoCompra(PedidoCompra pedidoCompra, InternalResultsResponse<?> response);

	/**
	 * Fetch pedido compra by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<PedidoCompra> fetchPedidoCompraByRequest(PagedInquiryRequest request);

}
