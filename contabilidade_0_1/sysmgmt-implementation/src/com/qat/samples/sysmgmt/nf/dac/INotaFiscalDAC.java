package com.qat.samples.sysmgmt.nf.dac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.condpag.CondPag;
import com.qat.samples.sysmgmt.condpag.model.request.CondPgInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.Caixa;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.nf.model.Contas;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.nf.model.Orcamento;
import com.qat.samples.sysmgmt.nf.model.PedidoCompras;
import com.qat.samples.sysmgmt.nf.model.request.CaixaInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.ContasInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasInquiryRequest;

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

	public InternalResponse deletePedidoCompras(PedidoCompras request);

	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasById(FetchByIdRequest request);

	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasByRequest(PedidoComprasInquiryRequest request);

	public InternalResultsResponse<Orcamento> insertOrcamento(Orcamento request);

	public InternalResultsResponse<Orcamento> updateOrcamento(Orcamento request);

	public InternalResponse deleteOrcamento(Orcamento request);

	public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request);

	public InternalResultsResponse<Orcamento> fetchOrcamentoByRequest(OrcamentoInquiryRequest request);

	public InternalResultsResponse<Contas> fetchContasByRequest(ContasInquiryRequest request);

	public InternalResultsResponse<Contas> insertContas(Contas request);

	public InternalResultsResponse<Contas> updateContas(Contas request);

	public InternalResponse deleteContas(Contas request);

	public InternalResultsResponse<Contas> fetchContasById(FetchByIdRequest request);

	public InternalResultsResponse<Caixa> fetchCaixaByRequest(CaixaInquiryRequest request);

	public InternalResultsResponse<CondPag> fetchCondPgByRequest(CondPgInquiryRequest request);

}
