package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IPessoaBAC;
import com.prosperitasglobal.sendsolv.dac.IPessoaDAC;
import com.prosperitasglobal.sendsolv.model.Cliente;
import com.prosperitasglobal.sendsolv.model.Fornecedor;
import com.prosperitasglobal.sendsolv.model.Transportador;
import com.prosperitasglobal.sendsolv.model.request.ClienteInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClienteMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.TransportadorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TransportadorMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class PessoaBACImpl.
 */
public class PessoaBACImpl implements IPessoaBAC
{

	/** The person dac. */
	private IPessoaDAC PessoaDAC;

	/**
	 * @return the PessoaDAC
	 */
	public IPessoaDAC getPessoaDAC()
	{
		return PessoaDAC;
	}

	/**
	 * @param PessoaDAC the PessoaDAC to set
	 */
	public void setPessoaDAC(IPessoaDAC PessoaDAC)
	{
		this.PessoaDAC = PessoaDAC;
	}

	@Override
	public InternalResultsResponse<Cliente> insertCliente(ClienteMaintenanceRequest request)
	{
		InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();

		response = getPessoaDAC().insertCliente(request.getCliente());

		return response;
	}

	@Override
	public InternalResultsResponse<Cliente> updateCliente(ClienteMaintenanceRequest request)
	{
		InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();

		response = getPessoaDAC().updateCliente(request.getCliente());

		return response;
	}

	@Override
	public InternalResponse deleteCliente(ClienteMaintenanceRequest request)
	{
		return getPessoaDAC().deleteCliente(request.getCliente());
	}

	@Override
	public InternalResultsResponse<Cliente> fetchClienteById(FetchByIdRequest request)
	{
		return getPessoaDAC().fetchClienteById(request);
	}

	@Override
	public InternalResultsResponse<Cliente> fetchClienteByRequest(ClienteInquiryRequest request)
	{
		return getPessoaDAC().fetchClienteByRequest(request);
	}

	// =================================

	@Override
	public InternalResultsResponse<Fornecedor> insertFornecedor(FornecedorMaintenanceRequest request)
	{
		InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();

		response = getPessoaDAC().insertFornecedor(request.getFornecedor());

		return response;
	}

	@Override
	public InternalResultsResponse<Fornecedor> updateFornecedor(FornecedorMaintenanceRequest request)
	{
		InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();

		response = getPessoaDAC().updateFornecedor(request.getFornecedor());

		return response;
	}

	@Override
	public InternalResponse deleteFornecedor(FornecedorMaintenanceRequest request)
	{
		return getPessoaDAC().deleteFornecedor(request.getFornecedor());
	}

	@Override
	public InternalResultsResponse<Fornecedor> fetchFornecedorById(FetchByIdRequest request)
	{
		return getPessoaDAC().fetchFornecedorById(request);
	}

	@Override
	public InternalResultsResponse<Fornecedor> fetchFornecedorByRequest(FornecedorInquiryRequest request)
	{
		return getPessoaDAC().fetchFornecedorByRequest(request);
	}

	// ========================================
	@Override
	public InternalResultsResponse<Transportador> insertTransportador(TransportadorMaintenanceRequest request)
	{
		InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();

		response = getPessoaDAC().insertTransportador(request.getTransportador());

		return response;
	}

	@Override
	public InternalResultsResponse<Transportador> updateTransportador(TransportadorMaintenanceRequest request)
	{
		InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();

		response = getPessoaDAC().updateTransportador(request.getTransportador());

		return response;
	}

	@Override
	public InternalResponse deleteTransportador(TransportadorMaintenanceRequest request)
	{
		return getPessoaDAC().deleteTransportador(request.getTransportador());
	}

	@Override
	public InternalResultsResponse<Transportador> fetchTransportadorById(FetchByIdRequest request)
	{
		return getPessoaDAC().fetchTransportadorById(request);
	}

	@Override
	public InternalResultsResponse<Transportador> fetchTransportadorByRequest(TransportadorInquiryRequest request)
	{
		return getPessoaDAC().fetchTransportadorByRequest(request);
	}
}
