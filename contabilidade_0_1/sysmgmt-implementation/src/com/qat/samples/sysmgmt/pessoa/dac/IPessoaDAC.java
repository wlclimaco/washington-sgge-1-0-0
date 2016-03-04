package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.agencia.Agencia;
import com.qat.samples.sysmgmt.agencia.model.request.AgenciaInquiryRequest;
import com.qat.samples.sysmgmt.banco.Banco;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.Beneficios;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.condpag.FormaPg;
import com.qat.samples.sysmgmt.condpag.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.conta.Conta;
import com.qat.samples.sysmgmt.contato.Contato;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.convenio.Convenio;
import com.qat.samples.sysmgmt.dp.Eventos;
import com.qat.samples.sysmgmt.dp.HorarioFunc;
import com.qat.samples.sysmgmt.dp.Profissao;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.ProfissaoInquiryRequest;
import com.qat.samples.sysmgmt.estado.Estado;
import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.Cliente;
import com.qat.samples.sysmgmt.pessoa.Contador;
import com.qat.samples.sysmgmt.pessoa.Fornecedor;
import com.qat.samples.sysmgmt.pessoa.Funcionario;
import com.qat.samples.sysmgmt.pessoa.Transportador;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContaInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ConvenioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.util.Cidade;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;

/**
 * The Interface IPessoaDAC.
 */
public interface IPessoaDAC
{

	// ================================
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
