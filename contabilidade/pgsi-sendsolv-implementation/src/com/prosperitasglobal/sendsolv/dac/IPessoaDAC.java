package com.prosperitasglobal.sendsolv.dac;


/**
 * The Interface IPessoaDAC.
 */
public interface IPessoaDAC
{

	public InternalResultsResponse<Cliente> updateCliente(Cliente cnae);

	public InternalResultsResponse<Cliente> insertCliente(Cliente cnae);

	public InternalResponse deleteCliente(Cliente cnae);

	public InternalResultsResponse<Cliente> fetchClienteById(FetchByIdRequest request);

	public InternalResultsResponse<Cliente> fetchClienteByRequest(ClienteInquiryRequest request);

	// ================================
	public InternalResultsResponse<Fornecedor> updateFornecedor(Fornecedor cnae);

	public InternalResultsResponse<Fornecedor> insertFornecedor(Fornecedor cnae);

	public InternalResponse deleteFornecedor(Fornecedor cnae);

	public InternalResultsResponse<Fornecedor> fetchFornecedorById(FetchByIdRequest request);

	public InternalResultsResponse<Fornecedor> fetchFornecedorByRequest(FornecedorInquiryRequest request);

	// =====================
	public InternalResultsResponse<Transportador> updateTransportador(Transportador cnae);

	public InternalResultsResponse<Transportador> insertTransportador(Transportador cnae);

	public InternalResponse deleteTransportador(Transportador cnae);

	public InternalResultsResponse<Transportador> fetchTransportadorById(FetchByIdRequest request);

	public InternalResultsResponse<Transportador> fetchTransportadorByRequest(TransportadorInquiryRequest request);

}
