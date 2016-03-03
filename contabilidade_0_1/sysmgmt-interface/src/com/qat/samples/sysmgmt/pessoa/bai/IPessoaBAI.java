package com.qat.samples.sysmgmt.pessoa.bai;

import com.qat.samples.sysmgmt.agencia.model.request.AgenciaInquiryRequest;
import com.qat.samples.sysmgmt.agencia.model.response.AgenciaResponse;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.model.response.BeneficiosResponse;
import com.qat.samples.sysmgmt.condpag.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.response.ContatoResponse;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.ProfissaoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.response.BancoResponse;
import com.qat.samples.sysmgmt.dp.model.response.ContaResponse;
import com.qat.samples.sysmgmt.dp.model.response.ConvenioResponse;
import com.qat.samples.sysmgmt.dp.model.response.EventoResponse;
import com.qat.samples.sysmgmt.dp.model.response.FormaPgResponse;
import com.qat.samples.sysmgmt.dp.model.response.HorarioFuncResponse;
import com.qat.samples.sysmgmt.dp.model.response.ProfissaoResponse;
import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.estado.model.response.EstadoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContaInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContadorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ConvenioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.response.ClienteResponse;
import com.qat.samples.sysmgmt.pessoa.model.response.ContadorResponse;
import com.qat.samples.sysmgmt.pessoa.model.response.FornecedorResponse;
import com.qat.samples.sysmgmt.pessoa.model.response.TransportadorResponse;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.response.CidadeResponse;

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

	// ==========

	/**
	 * Insert transportador.
	 * 
	 * @param request the request
	 * @return the transportador response
	 */
	public ContadorResponse insertContador(ContadorMaintenanceRequest request);

	/**
	 * Update transportador.
	 * 
	 * @param request the request
	 * @return the transportador response
	 */
	public ContadorResponse updateContador(ContadorMaintenanceRequest request);

	/**
	 * Delete transportador.
	 * 
	 * @param request the request
	 * @return the transportador response
	 */
	public ContadorResponse deleteContador(ContadorMaintenanceRequest request);

	/**
	 * Fetch transportador by id.
	 * 
	 * @param request the request
	 * @return the transportador response
	 */
	public ContadorResponse fetchContadorById(FetchByIdRequest request);

	/**
	 * Fetch transportador by request.
	 * 
	 * @param request the request
	 * @return the transportador response
	 */
	public ContadorResponse fetchContadorByRequest(ContadorInquiryRequest request);

	/**
	 * Fetch profissao by request.
	 * 
	 * @param request the request
	 * @return the profissao response
	 */
	public ProfissaoResponse fetchProfissaoByRequest(ProfissaoInquiryRequest request);

	/**
	 * Fetch contato by request.
	 * 
	 * @param request the request
	 * @return the contato response
	 */
	public ContatoResponse fetchContatoByRequest(ContatoInquiryRequest request);

	public AgenciaResponse fetchAgenciaByRequest(AgenciaInquiryRequest request);

	public EstadoResponse fetchEstadoByRequest(EstadoInquiryRequest request);

	public CidadeResponse fetchCidadeRequest(CidadeInquiryRequest request);

	public EventoResponse fetchEventosRequest(EventoInquiryRequest request);

	public BeneficiosResponse fetchBeneficiosRequest(BeneficiosInquiryRequest request);

	public HorarioFuncResponse fetchHorarioFuncsRequest(HoraFuncInquiryRequest request);

	public ConvenioResponse fetchConvenioByRequest(ConvenioInquiryRequest request);

	public BancoResponse fetchBancoByRequest(BancoInquiryRequest request);

	public FormaPgResponse fetchFormaPgByRequest(FormaPgInquiryRequest request);

	public ContaResponse fetchContaByRequest(ContaInquiryRequest request);

}