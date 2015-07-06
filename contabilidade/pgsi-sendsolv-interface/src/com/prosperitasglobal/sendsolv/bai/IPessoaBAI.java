package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.BancoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClienteInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClienteMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.ContatoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ConvenioInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FormaPgInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.ProfissaoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TransportadorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TransportadorMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.BancoResponse;
import com.prosperitasglobal.sendsolv.model.response.ClienteResponse;
import com.prosperitasglobal.sendsolv.model.response.ContatoResponse;
import com.prosperitasglobal.sendsolv.model.response.ConvenioResponse;
import com.prosperitasglobal.sendsolv.model.response.FormaPgResponse;
import com.prosperitasglobal.sendsolv.model.response.FornecedorResponse;
import com.prosperitasglobal.sendsolv.model.response.ProfissaoResponse;
import com.prosperitasglobal.sendsolv.model.response.TransportadorResponse;

/**
 * The Interface IPessoaBAI.
 */
public interface IPessoaBAI
{

	/**
	 * Insert cliente.
	 *
	 * @param request the request
	 * @return the cliente response
	 */
	public ClienteResponse insertCliente(ClienteMaintenanceRequest request);

	/**
	 * Update cliente.
	 *
	 * @param request the request
	 * @return the cliente response
	 */
	public ClienteResponse updateCliente(ClienteMaintenanceRequest request);

	/**
	 * Delete cliente.
	 *
	 * @param request the request
	 * @return the cliente response
	 */
	public ClienteResponse deleteCliente(ClienteMaintenanceRequest request);

	/**
	 * Fetch cliente by id.
	 *
	 * @param request the request
	 * @return the cliente response
	 */
	public ClienteResponse fetchClienteById(FetchByIdRequest request);

	/**
	 * Fetch cliente by request.
	 *
	 * @param request the request
	 * @return the cliente response
	 */
	public ClienteResponse fetchClienteByRequest(ClienteInquiryRequest request);

	// ===========

	/**
	 * Insert fornecedor.
	 *
	 * @param request the request
	 * @return the fornecedor response
	 */
	public FornecedorResponse insertFornecedor(FornecedorMaintenanceRequest request);

	/**
	 * Update fornecedor.
	 *
	 * @param request the request
	 * @return the fornecedor response
	 */
	public FornecedorResponse updateFornecedor(FornecedorMaintenanceRequest request);

	/**
	 * Delete fornecedor.
	 *
	 * @param request the request
	 * @return the fornecedor response
	 */
	public FornecedorResponse deleteFornecedor(FornecedorMaintenanceRequest request);

	/**
	 * Fetch fornecedor by id.
	 *
	 * @param request the request
	 * @return the fornecedor response
	 */
	public FornecedorResponse fetchFornecedorById(FetchByIdRequest request);

	/**
	 * Fetch fornecedor by request.
	 *
	 * @param request the request
	 * @return the fornecedor response
	 */
	public FornecedorResponse fetchFornecedorByRequest(FornecedorInquiryRequest request);

	// ==========

	/**
	 * Insert transportador.
	 *
	 * @param request the request
	 * @return the transportador response
	 */
	public TransportadorResponse insertTransportador(TransportadorMaintenanceRequest request);

	/**
	 * Update transportador.
	 *
	 * @param request the request
	 * @return the transportador response
	 */
	public TransportadorResponse updateTransportador(TransportadorMaintenanceRequest request);

	/**
	 * Delete transportador.
	 *
	 * @param request the request
	 * @return the transportador response
	 */
	public TransportadorResponse deleteTransportador(TransportadorMaintenanceRequest request);

	/**
	 * Fetch transportador by id.
	 *
	 * @param request the request
	 * @return the transportador response
	 */
	public TransportadorResponse fetchTransportadorById(FetchByIdRequest request);

	/**
	 * Fetch transportador by request.
	 *
	 * @param request the request
	 * @return the transportador response
	 */
	public TransportadorResponse fetchTransportadorByRequest(TransportadorInquiryRequest request);

	// ==========

	/**
	 * Fetch profissao by request.
	 *
	 * @param request the request
	 * @return the profissao response
	 */
	public ProfissaoResponse fetchProfissaoByRequest(ProfissaoInquiryRequest request);

	/**
	 * Fetch convenio by request.
	 *
	 * @param request the request
	 * @return the convenio response
	 */
	public ConvenioResponse fetchConvenioByRequest(ConvenioInquiryRequest request);

	/**
	 * Fetch contato by request.
	 *
	 * @param request the request
	 * @return the contato response
	 */
	public ContatoResponse fetchContatoByRequest(ContatoInquiryRequest request);

	/**
	 * Fetch banco by request.
	 *
	 * @param request the request
	 * @return the banco response
	 */
	public BancoResponse fetchBancoByRequest(BancoInquiryRequest request);

	/**
	 * Fetch forma pg by request.
	 *
	 * @param request the request
	 * @return the forma pg response
	 */
	public FormaPgResponse fetchFormaPgByRequest(FormaPgInquiryRequest request);

}