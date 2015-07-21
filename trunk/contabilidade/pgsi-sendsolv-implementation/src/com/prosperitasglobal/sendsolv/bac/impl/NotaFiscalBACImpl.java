package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.sendsolv.bac.INotaFiscalBAC;

/**
 * The Class NotaFiscalBACImpl.
 */
public class NotaFiscalBACImpl implements INotaFiscalBAC
{

	@Override
	public InternalResultsResponse<NotaFiscalEntrada> insertNotaFiscalEntrada(
			NotaFiscalEntradaMaintenanceRequest request)
	{
		InternalResultsResponse<NotaFiscalEntrada> response = new InternalResultsResponse<NotaFiscalEntrada>();

		response = getNotaFiscalDAC().insertNotaFiscalEntrada(request.getNotaFiscal());

		return response;
	}

	@Override
	public InternalResultsResponse<NotaFiscalEntrada> updateNotaFiscalEntrada(
			NotaFiscalEntradaMaintenanceRequest request)
	{
		InternalResultsResponse<NotaFiscalEntrada> response = new InternalResultsResponse<NotaFiscalEntrada>();

		response = getNotaFiscalDAC().updateNotaFiscalEntrada(request.getNotaFiscal());

		return response;
	}

	@Override
	public InternalResponse deleteNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
	{
		return getNotaFiscalDAC().deleteNotaFiscalEntrada(request.getNotaFiscal());
	}

	@Override
	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaById(FetchByIdRequest request)
	{
		return getNotaFiscalDAC().fetchNotaFiscalEntradaById(request);
	}

	@Override
	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaByRequest(
			NotaFiscalEntradaMaintenanceRequest request)
	{
		return getNotaFiscalDAC().fetchNotaFiscalEntradaByRequest(request);
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> insertNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();

		response = getNotaFiscalDAC().insertNotaFiscalSaida(request.getNotaFiscal());

		return response;
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> updateNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();

		response = getNotaFiscalDAC().insertNotaFiscalSaida(request.getNotaFiscal());

		return response;
	}

	@Override
	public InternalResponse deleteNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		return getNotaFiscalDAC().deleteNotaFiscalSaida(request.getNotaFiscal());
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaById(FetchByIdRequest request)
	{
		return getNotaFiscalDAC().fetchNotaFiscalSaidaById(request);
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaByRequest(
			NotaFiscalSaidaMaintenanceRequest request)
	{
		return getNotaFiscalDAC().fetchNotaFiscalSaidaByRequest(request);
	}

	@Override
	public InternalResultsResponse<PedidoCompras> insertPedidoCompras(PedidoComprasMaintenanceRequest request)
	{
		InternalResultsResponse<PedidoCompras> response = new InternalResultsResponse<PedidoCompras>();

		response = getNotaFiscalDAC().insertPedidoCompras(request.getPedidoCompras());

		return response;
	}

	@Override
	public InternalResultsResponse<PedidoCompras> updatePedidoCompras(PedidoComprasMaintenanceRequest request)
	{
		InternalResultsResponse<PedidoCompras> response = new InternalResultsResponse<PedidoCompras>();

		response = getNotaFiscalDAC().insertPedidoCompras(request.getPedidoCompras());

		return response;
	}

	@Override
	public InternalResponse deletePedidoComprasl(PedidoComprasMaintenanceRequest request)
	{
		return getNotaFiscalDAC().deletePedidoCompras(request.getPedidoCompras());
	}

	@Override
	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasById(FetchByIdRequest request)
	{
		return getNotaFiscalDAC().fetchPedidoComprasById(request);
	}

	@Override
	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasByRequest(PedidoComprasMaintenanceRequest request)
	{
		return getNotaFiscalDAC().fetchPedidoComprasByRequest(request);
	}

	@Override
	public InternalResultsResponse<Orcamento> insertOrcamento(OrcamentoMaintenanceRequest request)
	{
		InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();

		response = getNotaFiscalDAC().insertOrcamento(request.getOrcamento());

		return response;
	}

	@Override
	public InternalResultsResponse<Orcamento> updateOrcamento(OrcamentoMaintenanceRequest request)
	{
		InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();

		response = getNotaFiscalDAC().insertOrcamento(request.getOrcamento());

		return response;
	}

	@Override
	public InternalResponse deleteOrcamento(OrcamentoMaintenanceRequest request)
	{
		return getNotaFiscalDAC().deleteOrcamento(request.getNotaFiscal());
	}

	@Override
	public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request)
	{
		return getNotaFiscalDAC().fetchOrcamentoById(request);
	}

	@Override
	public InternalResultsResponse<Orcamento> fetchOrcamentoByRequest(OrcamentoMaintenanceRequest request)
	{
		return getNotaFiscalDAC().fetchOrcamentoByRequest(request);
	}

	@Override
	public InternalResultsResponse<Contas> fetchContasByRequest(ContasMaintenanceRequest request)
	{
		return getNotaFiscalDAC().fetchEmpresaByRequest(request);
	}

	@Override
	public InternalResultsResponse<Contas> insertContas(ContasMaintenanceRequest request)
	{
		InternalResultsResponse<Contas> response = new InternalResultsResponse<Contas>();

		response = getNotaFiscalDAC().insertContas(request.getContas());

		return response;
	}

	@Override
	public InternalResultsResponse<Contas> updateContas(ContasMaintenanceRequest request)
	{
		InternalResultsResponse<Contas> response = new InternalResultsResponse<Contas>();

		response = getNotaFiscalDAC().insertContas(request.getContas());

		return response;
	}

	@Override
	public InternalResponse deleteContas(ContasMaintenanceRequest request)
	{
		return getNotaFiscalDAC().deleteContas(request.getNotaFiscal());
	}

	@Override
	public InternalResultsResponse<Contas> fetchContasById(FetchByIdRequest request)
	{
		return getNotaFiscalDAC().fetchContasById(request);
	}

}
