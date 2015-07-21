package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Contas;
import com.prosperitasglobal.sendsolv.model.NotaFiscalEntrada;
import com.prosperitasglobal.sendsolv.model.NotaFiscalSaida;
import com.prosperitasglobal.sendsolv.model.Orcamento;
import com.prosperitasglobal.sendsolv.model.PedidoCompras;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface INotaFiscalDAC.
 */
public interface INotaFiscalDAC
{

	public InternalResultsResponse<NotaFiscalEntrada> insertNotaFiscalEntrada(
			NotaFiscalEntrada request);

	public InternalResultsResponse<NotaFiscalEntrada> updateNotaFiscalEntrada(
			NotaFiscalEntrada request);

	public InternalResponse deleteNotaFiscalEntrada(NotaFiscalEntrada request);

	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaById(FetchByIdRequest request);

	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaByRequest(
			NotaFiscalInquiryRequest request);

	public InternalResultsResponse<NotaFiscalSaida> insertNotaFiscalSaida(NotaFiscalSaida request);

	public InternalResultsResponse<NotaFiscalSaida> updateNotaFiscalSaida(NotaFiscalSaida request);

	public InternalResponse deleteNotaFiscalSaida(NotaFiscalSaida request);

	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaById(FetchByIdRequest request);

	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaByRequest(
			NotaFiscalInquiryRequest request);

	public InternalResultsResponse<PedidoCompras> insertPedidoCompras(PedidoCompras request);

	public InternalResultsResponse<PedidoCompras> updatePedidoCompras(PedidoCompras request);

	public InternalResponse deletePedidoComprasl(PedidoCompras request);

	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasById(FetchByIdRequest request);

	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasByRequest(NotaFiscalInquiryRequest request);

	public InternalResultsResponse<Orcamento> insertOrcamento(Orcamento request);

	public InternalResultsResponse<Orcamento> updateOrcamento(Orcamento request);

	public InternalResponse deleteOrcamento(Orcamento request);

	public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request);

	public InternalResultsResponse<Orcamento> fetchOrcamentoByRequest(NotaFiscalInquiryRequest request);

	public InternalResultsResponse<Contas> fetchContasByRequest(Contas request);

	public InternalResultsResponse<Contas> insertContas(Contas request);

	public InternalResultsResponse<Contas> updateContas(Contas request);

	public InternalResponse deleteContas(Contas request);

	public InternalResultsResponse<Contas> fetchContasById(FetchByIdRequest request);

}
