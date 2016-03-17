package com.qat.samples.sysmgmt.nf.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.condpag.CondPag;
import com.qat.samples.sysmgmt.condpag.model.request.CondPgInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.Caixa;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.nf.model.Contas;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.nf.model.Orcamento;
import com.qat.samples.sysmgmt.nf.model.PedidoCompras;
import com.qat.samples.sysmgmt.nf.model.request.CaixaInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.ContasInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.ContasMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalEntradaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalSaidaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasMaintenanceRequest;

/**
 * The Interface INotaFiscalBAC.
 */
public interface INotaFiscalBAC
{

	public InternalResultsResponse<NotaFiscalEntrada> insertNotaFiscalEntrada(
			NotaFiscalEntradaMaintenanceRequest request);

	public InternalResultsResponse<NotaFiscalEntrada> updateNotaFiscalEntrada(
			NotaFiscalEntradaMaintenanceRequest request);

	public InternalResponse deleteNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request);

	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaById(FetchByIdRequest request);

	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaByRequest(
			NotaFiscalInquiryRequest request);

	public InternalResultsResponse<NotaFiscalSaida> insertNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request);

	public InternalResultsResponse<NotaFiscalSaida> updateNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request);

	public InternalResponse deleteNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request);

	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaById(FetchByIdRequest request);

	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaByRequest(
			NotaFiscalInquiryRequest request);

	public InternalResultsResponse<PedidoCompras> insertPedidoCompras(PedidoComprasMaintenanceRequest request);

	public InternalResultsResponse<PedidoCompras> updatePedidoCompras(PedidoComprasMaintenanceRequest request);

	public InternalResponse deletePedidoCompras(PedidoComprasMaintenanceRequest request);

	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasById(FetchByIdRequest request);

	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasByRequest(PedidoComprasInquiryRequest request);

	public InternalResultsResponse<Orcamento> insertOrcamento(OrcamentoMaintenanceRequest request);

	public InternalResultsResponse<Orcamento> updateOrcamento(OrcamentoMaintenanceRequest request);

	public InternalResponse deleteOrcamento(OrcamentoMaintenanceRequest request);

	public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request);

	public InternalResultsResponse<Orcamento> fetchOrcamentoByRequest(OrcamentoInquiryRequest request);

	public InternalResultsResponse<Contas> fetchContasByRequest(ContasInquiryRequest request);

	public InternalResultsResponse<Contas> insertContas(ContasMaintenanceRequest request);

	public InternalResultsResponse<Contas> updateContas(ContasMaintenanceRequest request);

	public InternalResponse deleteContas(ContasMaintenanceRequest request);

	public InternalResultsResponse<Contas> fetchContasById(FetchByIdRequest request);

	public InternalResultsResponse<Caixa> fetchCaixaByRequest(CaixaInquiryRequest request);

	public InternalResultsResponse<CondPag> fetchCondPgByRequest(CondPgInquiryRequest request);

}
