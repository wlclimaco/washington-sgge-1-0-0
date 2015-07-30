package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Agencia;
import com.prosperitasglobal.sendsolv.model.Banco;
import com.prosperitasglobal.sendsolv.model.Beneficios;
import com.prosperitasglobal.sendsolv.model.Cidade;
import com.prosperitasglobal.sendsolv.model.Cliente;
import com.prosperitasglobal.sendsolv.model.Conta;
import com.prosperitasglobal.sendsolv.model.Contador;
import com.prosperitasglobal.sendsolv.model.Contato;
import com.prosperitasglobal.sendsolv.model.Convenio;
import com.prosperitasglobal.sendsolv.model.Estado;
import com.prosperitasglobal.sendsolv.model.Eventos;
import com.prosperitasglobal.sendsolv.model.FormaPg;
import com.prosperitasglobal.sendsolv.model.Fornecedor;
import com.prosperitasglobal.sendsolv.model.Funcionario;
import com.prosperitasglobal.sendsolv.model.HorarioFunc;
import com.prosperitasglobal.sendsolv.model.Profissao;
import com.prosperitasglobal.sendsolv.model.Transportador;
import com.prosperitasglobal.sendsolv.model.request.AgenciaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.BancoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.BeneficiosInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CidadeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClienteInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ContaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ContadorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ContatoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ConvenioInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EstadoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EventoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FormaPgInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FuncionarioInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.HoraFuncInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ProfissaoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TransportadorInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

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

	// =====================
	public InternalResultsResponse<Funcionario> updateFuncionario(Funcionario funcionario);

	public InternalResultsResponse<Funcionario> insertFuncionario(Funcionario funcionario);

	public InternalResponse deleteFuncionario(Funcionario funcionario);

	public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request);

	public InternalResultsResponse<Funcionario> fetchFuncionarioByRequest(FuncionarioInquiryRequest request);

	// ======================

	public InternalResultsResponse<Contador> updateContador(Contador funcionario);

	public InternalResultsResponse<Contador> insertContador(Contador funcionario);

	public InternalResponse deleteContador(Contador funcionario);

	public InternalResultsResponse<Contador> fetchContadorById(FetchByIdRequest request);

	public InternalResultsResponse<Contador> fetchContadorByRequest(ContadorInquiryRequest request);

	// =====================

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
