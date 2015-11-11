package com.qat.samples.sysmgmt.nf.bai;

import com.qat.samples.sysmgmt.condpag.model.request.CondPgInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
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
import com.qat.samples.sysmgmt.nf.model.response.CaixaResponse;
import com.qat.samples.sysmgmt.nf.model.response.CondPgResponse;
import com.qat.samples.sysmgmt.nf.model.response.ContasResponse;
import com.qat.samples.sysmgmt.nf.model.response.NotaFiscalEntradaResponse;
import com.qat.samples.sysmgmt.nf.model.response.NotaFiscalSaidaResponse;
import com.qat.samples.sysmgmt.nf.model.response.OrcamentoResponse;
import com.qat.samples.sysmgmt.nf.model.response.PedidoComprasResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface INotaFiscalBAI.
 */
public interface INotaFiscalBAI
{

	public NotaFiscalEntradaResponse insertNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request);

	public NotaFiscalEntradaResponse updateNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request);

	public NotaFiscalEntradaResponse deleteNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request);

	public NotaFiscalEntradaResponse fetchNotaFiscalEntradaById(FetchByIdRequest request);

	public NotaFiscalEntradaResponse fetchNotaFiscalEntradaByRequest(NotaFiscalInquiryRequest request);

	public NotaFiscalSaidaResponse insertNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request);

	public NotaFiscalSaidaResponse updateNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request);

	public NotaFiscalSaidaResponse deleteNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request);

	public NotaFiscalSaidaResponse fetchNotaFiscalSaidaById(FetchByIdRequest request);

	public NotaFiscalSaidaResponse fetchNotaFiscalSaidaByRequest(NotaFiscalInquiryRequest request);

	public PedidoComprasResponse insertPedidoCompras(PedidoComprasMaintenanceRequest request);

	public PedidoComprasResponse updatePedidoCompras(PedidoComprasMaintenanceRequest request);

	public PedidoComprasResponse deletePedidoComprasl(PedidoComprasMaintenanceRequest request);

	public PedidoComprasResponse fetchPedidoComprasById(FetchByIdRequest request);

	public PedidoComprasResponse fetchPedidoComprasByRequest(PedidoComprasInquiryRequest request);

	public OrcamentoResponse insertOrcamento(OrcamentoMaintenanceRequest request);

	public OrcamentoResponse updateOrcamento(OrcamentoMaintenanceRequest request);

	public OrcamentoResponse deleteOrcamento(OrcamentoMaintenanceRequest request);

	public OrcamentoResponse fetchOrcamentoById(FetchByIdRequest request);

	public OrcamentoResponse fetchOrcamentoByRequest(OrcamentoInquiryRequest request);

	public ContasResponse fetchContasByRequest(ContasInquiryRequest request);

	public ContasResponse insertContas(ContasMaintenanceRequest request);

	public ContasResponse updateContas(ContasMaintenanceRequest request);

	public ContasResponse deleteContas(ContasMaintenanceRequest request);

	public ContasResponse fetchContasById(FetchByIdRequest request);

	public CaixaResponse fetchCaixaByRequest(CaixaInquiryRequest request);

	public CondPgResponse fetchCondPgByRequest(CondPgInquiryRequest request);

}