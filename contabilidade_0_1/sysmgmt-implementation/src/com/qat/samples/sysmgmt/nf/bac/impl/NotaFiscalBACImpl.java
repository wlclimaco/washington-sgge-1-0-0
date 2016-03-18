package com.qat.samples.sysmgmt.nf.bac.impl;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.condpag.CondPag;
import com.qat.samples.sysmgmt.condpag.model.request.CondPgInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.Caixa;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.nf.bac.INotaFiscalBAC;
import com.qat.samples.sysmgmt.nf.dac.INotaFiscalDAC;
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
