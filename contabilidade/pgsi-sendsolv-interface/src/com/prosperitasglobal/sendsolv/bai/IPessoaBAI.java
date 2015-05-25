package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.ClienteInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClienteMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.TransportadorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TransportadorMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.ClienteResponse;
import com.prosperitasglobal.sendsolv.model.response.FornecedorResponse;
import com.prosperitasglobal.sendsolv.model.response.TransportadorResponse;

public interface IPessoaBAI
{

	public ClienteResponse insertCliente(ClienteMaintenanceRequest request);

	public ClienteResponse updateCliente(ClienteMaintenanceRequest request);

	public ClienteResponse deleteCliente(ClienteMaintenanceRequest request);

	public ClienteResponse fetchClienteById(FetchByIdRequest request);

	public ClienteResponse fetchClienteByRequest(ClienteInquiryRequest request);

	// ===========

	public FornecedorResponse insertFornecedor(FornecedorMaintenanceRequest request);

	public FornecedorResponse updateFornecedor(FornecedorMaintenanceRequest request);

	public FornecedorResponse deleteFornecedor(FornecedorMaintenanceRequest request);

	public FornecedorResponse fetchFornecedorById(FetchByIdRequest request);

	public FornecedorResponse fetchFornecedorByRequest(FornecedorInquiryRequest request);

	// ==========

	public TransportadorResponse insertTransportador(TransportadorMaintenanceRequest request);

	public TransportadorResponse updateTransportador(TransportadorMaintenanceRequest request);

	public TransportadorResponse deleteTransportador(TransportadorMaintenanceRequest request);

	public TransportadorResponse fetchTransportadorById(FetchByIdRequest request);

	public TransportadorResponse fetchTransportadorByRequest(TransportadorInquiryRequest request);

}