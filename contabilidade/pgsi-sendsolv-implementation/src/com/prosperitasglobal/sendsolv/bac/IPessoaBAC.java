package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
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
 * The Interface IPessoaBAC.
 */
public interface IPessoaBAC
{

	public InternalResultsResponse<Cliente> insertCliente(ClienteMaintenanceRequest request);

	public InternalResultsResponse<Cliente> updateCliente(ClienteMaintenanceRequest request);

	public InternalResponse deleteCliente(ClienteMaintenanceRequest request);

	public InternalResultsResponse<Cliente> fetchClienteById(FetchByIdRequest request);

	public InternalResultsResponse<Cliente> fetchClienteByRequest(ClienteInquiryRequest request);

	// =============================

	public InternalResultsResponse<Fornecedor> insertFornecedor(FornecedorMaintenanceRequest request);

	public InternalResultsResponse<Fornecedor> updateFornecedor(FornecedorMaintenanceRequest request);

	public InternalResponse deleteFornecedor(FornecedorMaintenanceRequest request);

	public InternalResultsResponse<Fornecedor> fetchFornecedorById(FetchByIdRequest request);

	public InternalResultsResponse<Fornecedor> fetchFornecedorByRequest(FornecedorInquiryRequest request);

	// ====================

	public InternalResultsResponse<Transportador> insertTransportador(TransportadorMaintenanceRequest request);

	public InternalResultsResponse<Transportador> updateTransportador(TransportadorMaintenanceRequest request);

	public InternalResponse deleteTransportador(TransportadorMaintenanceRequest request);

	public InternalResultsResponse<Transportador> fetchTransportadorById(FetchByIdRequest request);

	public InternalResultsResponse<Transportador> fetchTransportadorByRequest(TransportadorInquiryRequest request);

}
