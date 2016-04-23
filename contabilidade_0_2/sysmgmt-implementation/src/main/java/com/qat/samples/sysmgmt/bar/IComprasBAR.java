package com.qat.samples.sysmgmt.bar;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;
import com.qat.samples.sysmgmt.nf.model.PedidoCompras;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface ICountyBAR. (Data Access Component - DAC)
 */
public interface IComprasBAR
{

	public InternalResultsResponse<NotaFiscalEntrada> insertNotaFiscalEntrada(NotaFiscalEntrada request);

	public InternalResultsResponse<NotaFiscalEntrada> updateNotaFiscalEntrada(NotaFiscalEntrada request);

	public InternalResultsResponse<NotaFiscalEntrada> deleteNotaFiscalEntrada(NotaFiscalEntrada request);

	public InternalResultsResponse<NotaFiscalEntrada> refreshNotaFiscalEntradas(RefreshRequest request);

	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaById(FetchByIdRequest request);

	public InternalResultsResponse<NotaFiscalEntrada> fetchAllNotaFiscalEntradas();

	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradasByRequest(NotaFiscalInquiryRequest request);

	//
	public InternalResultsResponse<PedidoCompras> insertPedidoCompras(PedidoCompras request);

	public InternalResultsResponse<PedidoCompras> updatePedidoCompras(PedidoCompras request);

	public InternalResultsResponse<PedidoCompras> deletePedidoCompras(PedidoCompras request);

	public InternalResultsResponse<PedidoCompras> refreshPedidoComprass(RefreshRequest request);

	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasById(FetchByIdRequest request);

	public InternalResultsResponse<PedidoCompras> fetchAllPedidoComprass();

	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasByRequest(PedidoComprasInquiryRequest request);

	public void deleteAllNotaFiscalEntradas();

	public InternalResultsResponse<NotaFiscalEntrada> fetchAllNotaFiscalEntradas(NotaFiscalInquiryRequest request);

	public InternalResponse deleteNotaFiscalEntradaById(NotaFiscalEntrada county);

	public void deleteAllPedidoCompras();

	public InternalResultsResponse<PedidoCompras> fetchAllPedidoCompras();

	public InternalResponse deletePedidoComprasById(PedidoCompras county);

}
