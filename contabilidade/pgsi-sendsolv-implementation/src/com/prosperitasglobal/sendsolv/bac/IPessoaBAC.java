package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Agencia;
import com.prosperitasglobal.sendsolv.model.Banco;
import com.prosperitasglobal.sendsolv.model.Beneficios;
import com.prosperitasglobal.sendsolv.model.Cidade;
import com.prosperitasglobal.sendsolv.model.Cliente;
import com.prosperitasglobal.sendsolv.model.Conta;
import com.prosperitasglobal.sendsolv.model.Contato;
import com.prosperitasglobal.sendsolv.model.Convenio;
import com.prosperitasglobal.sendsolv.model.Estado;
import com.prosperitasglobal.sendsolv.model.Eventos;
import com.prosperitasglobal.sendsolv.model.FormaPg;
import com.prosperitasglobal.sendsolv.model.Fornecedor;
import com.prosperitasglobal.sendsolv.model.HorarioFunc;
import com.prosperitasglobal.sendsolv.model.Profissao;
import com.prosperitasglobal.sendsolv.model.Transportador;
import com.prosperitasglobal.sendsolv.model.request.AgenciaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.BancoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.BeneficiosInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CidadeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClienteInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClienteMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.ContaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ContatoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ConvenioInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EstadoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EventoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FormaPgInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.HoraFuncInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ProfissaoInquiryRequest;
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

	public InternalResultsResponse<Profissao> fetchProfissaoByRequest(ProfissaoInquiryRequest request);

	public InternalResultsResponse<Convenio> fetchConvenioByRequest(ConvenioInquiryRequest request);

	public InternalResultsResponse<Contato> fetchContatoByRequest(ContatoInquiryRequest request);

	public InternalResultsResponse<Banco> fetchBancoByRequest(BancoInquiryRequest request);

	public InternalResultsResponse<FormaPg> fetchFormaPgByRequest(FormaPgInquiryRequest request);

	public InternalResultsResponse<Agencia> fetchAgenciaByRequest(AgenciaInquiryRequest request);

	public InternalResultsResponse<Conta> fetchContaByRequest(ContaInquiryRequest request);

	public InternalResultsResponse<Estado> fetchEstadoByRequest(EstadoInquiryRequest request);

	public InternalResultsResponse<Cidade> fetchCidadeRequest(CidadeInquiryRequest request);

	public InternalResultsResponse<Eventos> fetchEventosRequest(EventoInquiryRequest request);

	public InternalResultsResponse<Beneficios> fetchBeneficiosRequest(BeneficiosInquiryRequest request);

	public InternalResultsResponse<HorarioFunc> fetchHorarioFuncsRequest(HoraFuncInquiryRequest request);

}
