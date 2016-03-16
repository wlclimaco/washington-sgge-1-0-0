package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.INotaFiscalBAC;
import com.prosperitasglobal.sendsolv.dac.INotaFiscalDAC;
import com.prosperitasglobal.sendsolv.model.Caixa;
import com.prosperitasglobal.sendsolv.model.CondPag;
import com.prosperitasglobal.sendsolv.model.Contas;
import com.prosperitasglobal.sendsolv.model.NotaFiscalEntrada;
import com.prosperitasglobal.sendsolv.model.NotaFiscalSaida;
import com.prosperitasglobal.sendsolv.model.Orcamento;
import com.prosperitasglobal.sendsolv.model.PedidoCompras;
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
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class NotaFiscalBACImpl.
 */
public class NotaFiscalBACImpl implements INotaFiscalBAC
{
	private INotaFiscalDAC notaFiscalDAC;

	/**
	 * @return the notaFiscalDAC
	 */
	public INotaFiscalDAC getNotaFiscalDAC()
	{
		return notaFiscalDAC;
	}

	/**
	 * @param notaFiscalDAC the notaFiscalDAC to set
	 */
	public void setNotaFiscalDAC(INotaFiscalDAC notaFiscalDAC)
	{
		this.notaFiscalDAC = notaFiscalDAC;
	}

	@Override
	public InternalResultsResponse<NotaFiscalEntrada> insertNotaFiscalEntrada(
			NotaFiscalEntradaMaintenanceRequest request)
	{
		InternalResultsResponse<NotaFiscalEntrada> response = new InternalResultsResponse<NotaFiscalEntrada>();

		response = getNotaFiscalDAC().insertNotaFiscalEntrada(request.getNotafiscal());

		return response;
	}

	@Override
	public InternalResultsResponse<NotaFiscalEntrada> updateNotaFiscalEntrada(
			NotaFiscalEntradaMaintenanceRequest request)
	{
		InternalResultsResponse<NotaFiscalEntrada> response = new InternalResultsResponse<NotaFiscalEntrada>();

		response = getNotaFiscalDAC().updateNotaFiscalEntrada(request.getNotafiscal());

		return response;
	}

	@Override
	public InternalResponse deleteNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
	{
		return getNotaFiscalDAC().deleteNotaFiscalEntrada(request.getNotafiscal());
	}

	@Override
	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaById(FetchByIdRequest request)
	{
		return getNotaFiscalDAC().fetchNotaFiscalEntradaById(request);
	}

	@Override
	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaByRequest(
			NotaFiscalInquiryRequest request)
	{
		return getNotaFiscalDAC().fetchNotaFiscalEntradaByRequest(request);
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> insertNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();

		response = getNotaFiscalDAC().insertNotaFiscalSaida(request.getNotafiscal());

		return response;
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> updateNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();

		response = getNotaFiscalDAC().insertNotaFiscalSaida(request.getNotafiscal());

		return response;
	}

	@Override
	public InternalResponse deleteNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		return getNotaFiscalDAC().deleteNotaFiscalSaida(request.getNotafiscal());
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaById(FetchByIdRequest request)
	{
		return getNotaFiscalDAC().fetchNotaFiscalSaidaById(request);
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaByRequest(
			NotaFiscalInquiryRequest request)
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
	public InternalResponse deletePedidoCompras(PedidoComprasMaintenanceRequest request)
	{
		return getNotaFiscalDAC().deletePedidoCompras(request.getPedidoCompras());
	}

	@Override
	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasById(FetchByIdRequest request)
	{
		return getNotaFiscalDAC().fetchPedidoComprasById(request);
	}

	@Override
	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasByRequest(PedidoComprasInquiryRequest request)
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
		return getNotaFiscalDAC().deleteOrcamento(request.getOrcamento());
	}

	@Override
	public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request)
	{
		return getNotaFiscalDAC().fetchOrcamentoById(request);
	}

	@Override
	public InternalResultsResponse<Orcamento> fetchOrcamentoByRequest(OrcamentoInquiryRequest request)
	{
		return getNotaFiscalDAC().fetchOrcamentoByRequest(request);
	}

	@Override
	public InternalResultsResponse<Contas> fetchContasByRequest(ContasInquiryRequest request)
	{
		return getNotaFiscalDAC().fetchContasByRequest(request);
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
		return getNotaFiscalDAC().deleteContas(request.getContas());
	}

	@Override
	public InternalResultsResponse<Contas> fetchContasById(FetchByIdRequest request)
	{
		return getNotaFiscalDAC().fetchContasById(request);
	}

	@Override
	public InternalResultsResponse<Caixa> fetchCaixaByRequest(CaixaInquiryRequest request)
	{
		return getNotaFiscalDAC().fetchCaixaByRequest(request);
	}

	@Override
	public InternalResultsResponse<CondPag> fetchCondPgByRequest(CondPgInquiryRequest request)
	{
		return getNotaFiscalDAC().fetchCondPgByRequest(request);
	}

}
