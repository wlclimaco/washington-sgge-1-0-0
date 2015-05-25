package com.prosperitasglobal.sendsolv.bac;

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
