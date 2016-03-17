package com.qat.samples.sysmgmt.pessoa.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.advocacia.model.Advogado;
import com.qat.samples.sysmgmt.advocacia.model.request.AdvogadoInquiryRequest;
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
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioMaintenanceRequest;
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
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContaInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContadorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ConvenioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorMaintenanceRequest;
import com.qat.samples.sysmgmt.util.Cidade;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;

/**
 * The Interface IPessoaBAC.
 */
public interface IPessoaBAC
{
	public InternalResultsResponse<Advogado> insertAdvogado(AdvogadoMaintenanceRequest request);

	public InternalResultsResponse<Advogado> updateAdvogado(AdvogadoMaintenanceRequest request);

	public InternalResponse deleteAdvogado(AdvogadoMaintenanceRequest request);

	public InternalResultsResponse<Advogado> fetchAdvogadoById(FetchByIdRequest request);

	public InternalResultsResponse<Advogado> fetchAdvogadoByRequest(AdvogadoInquiryRequest request);

	// =============================

	public InternalResultsResponse<Funcionario> insertFuncionario(FuncionarioMaintenanceRequest request);

	public InternalResultsResponse<Funcionario> updateFuncionario(FuncionarioMaintenanceRequest request);

	public InternalResponse deleteFuncionario(FuncionarioMaintenanceRequest request);

	public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request);

	public InternalResultsResponse<Funcionario> fetchFuncionarioByRequest(FuncionarioInquiryRequest request);

	// =============================

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

	// ====================

	public InternalResultsResponse<Contador> insertContador(ContadorMaintenanceRequest request);

	public InternalResultsResponse<Contador> updateContador(ContadorMaintenanceRequest request);

	public InternalResponse deleteContador(ContadorMaintenanceRequest request);

	public InternalResultsResponse<Contador> fetchContadorById(FetchByIdRequest request);

	public InternalResultsResponse<Contador> fetchContadorByRequest(ContadorInquiryRequest request);

	// ==============

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
