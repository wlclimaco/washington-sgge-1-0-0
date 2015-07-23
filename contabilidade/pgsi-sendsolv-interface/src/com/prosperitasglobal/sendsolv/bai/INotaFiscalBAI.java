package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.CaixaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CondPgInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ContasInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ContasMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalEntradaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalSaidaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.OrcamentoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.OrcamentoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PedidoComprasInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PedidoComprasMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.CaixaResponse;
import com.prosperitasglobal.sendsolv.model.response.CondPgResponse;
import com.prosperitasglobal.sendsolv.model.response.ContasResponse;
import com.prosperitasglobal.sendsolv.model.response.NotaFiscalEntradaResponse;
import com.prosperitasglobal.sendsolv.model.response.NotaFiscalSaidaResponse;
import com.prosperitasglobal.sendsolv.model.response.OrcamentoResponse;
import com.prosperitasglobal.sendsolv.model.response.PedidoComprasResponse;

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