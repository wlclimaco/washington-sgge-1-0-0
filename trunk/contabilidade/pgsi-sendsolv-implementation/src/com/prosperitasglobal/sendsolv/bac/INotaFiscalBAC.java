package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Contas;
import com.prosperitasglobal.sendsolv.model.NotaFiscalEntrada;
import com.prosperitasglobal.sendsolv.model.NotaFiscalSaida;
import com.prosperitasglobal.sendsolv.model.Orcamento;
import com.prosperitasglobal.sendsolv.model.PedidoCompras;
import com.prosperitasglobal.sendsolv.model.request.ContasMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalEntradaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalSaidaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.OrcamentoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PedidoComprasMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

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
			NotaFiscalEntradaMaintenanceRequest request);

	public InternalResultsResponse<NotaFiscalSaida> insertNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request);

	public InternalResultsResponse<NotaFiscalSaida> updateNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request);

	public InternalResponse deleteNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request);

	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaById(FetchByIdRequest request);

	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaByRequest(
			NotaFiscalSaidaMaintenanceRequest request);

	public InternalResultsResponse<PedidoCompras> insertPedidoCompras(PedidoComprasMaintenanceRequest request);

	public InternalResultsResponse<PedidoCompras> updatePedidoCompras(PedidoComprasMaintenanceRequest request);

	public InternalResponse deletePedidoComprasl(PedidoComprasMaintenanceRequest request);

	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasById(FetchByIdRequest request);

	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasByRequest(PedidoComprasMaintenanceRequest request);

	public InternalResultsResponse<Orcamento> insertOrcamento(OrcamentoMaintenanceRequest request);

	public InternalResultsResponse<Orcamento> updateOrcamento(OrcamentoMaintenanceRequest request);

	public InternalResponse deleteOrcamento(OrcamentoMaintenanceRequest request);

	public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request);

	public InternalResultsResponse<Orcamento> fetchOrcamentoByRequest(OrcamentoMaintenanceRequest request);

	public InternalResultsResponse<Contas> fetchContasByRequest(ContasMaintenanceRequest request);

	public InternalResultsResponse<Contas> insertContas(ContasMaintenanceRequest request);

	public InternalResultsResponse<Contas> updateContas(ContasMaintenanceRequest request);

	public InternalResponse deleteContas(ContasMaintenanceRequest request);

	public InternalResultsResponse<Contas> fetchContasById(FetchByIdRequest request);

}
