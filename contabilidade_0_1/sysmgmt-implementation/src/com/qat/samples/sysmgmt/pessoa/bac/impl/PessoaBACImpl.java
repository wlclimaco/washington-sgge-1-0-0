package com.qat.samples.sysmgmt.pessoa.bac.impl;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.advocacia.model.Advogado;
import com.qat.samples.sysmgmt.advocacia.model.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.model.request.AdvogadoMaintenanceRequest;
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
import com.qat.samples.sysmgmt.pessoa.bac.IPessoaBAC;
import com.qat.samples.sysmgmt.pessoa.dac.IPessoaDAC;
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
 * The Class PessoaBACImpl.
 */
public class PessoaBACImpl implements IPessoaBAC
{

	/** The person dac. */
	private IPessoaDAC PessoaDAC;

	/**
	 * @return the PessoaDAC
	 */
	public IPessoaDAC getPessoaDAC()
	{
		return PessoaDAC;
	}

	/**
	 * @param PessoaDAC the PessoaDAC to set
	 */
	public void setPessoaDAC(IPessoaDAC PessoaDAC)
	{
		this.PessoaDAC = PessoaDAC;
	}

	@Override
	public InternalResultsResponse<Advogado> insertAdvogado(AdvogadoMaintenanceRequest request)
	{
		InternalResultsResponse<Advogado> response = new InternalResultsResponse<Advogado>();

		response = getPessoaDAC().insertAdvogado(request.getAdvogado());

		return response;
	}

	@Override
	public InternalResultsResponse<Advogado> updateAdvogado(AdvogadoMaintenanceRequest request)
	{
		InternalResultsResponse<Advogado> response = new InternalResultsResponse<Advogado>();

		response = getPessoaDAC().updateAdvogado(request.getAdvogado());

		return response;
	}

	@Override
	public InternalResponse deleteAdvogado(AdvogadoMaintenanceRequest request)
	{
		return getPessoaDAC().deleteAdvogado(request.getAdvogado());
	}

	@Override
	public InternalResultsResponse<Advogado> fetchAdvogadoById(FetchByIdRequest request)
	{
		return getPessoaDAC().fetchAdvogadoById(request);
	}

	@Override
	public InternalResultsResponse<Advogado> fetchAdvogadoByRequest(AdvogadoInquiryRequest request)
	{
		return getPessoaDAC().fetchAdvogadoByRequest(request);
	}

	// ================================= 
	
	@Override
	public InternalResultsResponse<Sindico> insertSindico(SindicoMaintenanceRequest request)
	{
		InternalResultsResponse<Sindico> response = new InternalResultsResponse<Sindico>();

		response = getPessoaDAC().insertSindico(request.getSindico());

		return response;
	}

	@Override
	public InternalResultsResponse<Sindico> updateSindico(SindicoMaintenanceRequest request)
	{
		InternalResultsResponse<Sindico> response = new InternalResultsResponse<Sindico>();

		response = getPessoaDAC().updateSindico(request.getSindico());

		return response;
	}

	@Override
	public InternalResponse deleteSindico(SindicoMaintenanceRequest request)
	{
		return getPessoaDAC().deleteSindico(request.getSindico());
	}

	@Override
	public InternalResultsResponse<Sindico> fetchSindicoById(FetchByIdRequest request)
	{
		return getPessoaDAC().fetchSindicoById(request);
	}

	@Override
	public InternalResultsResponse<Sindico> fetchSindicoByRequest(SindicoInquiryRequest request)
	{
		return getPessoaDAC().fetchSindicoByRequest(request);
	}

	// =================================
	
	@Override
	public InternalResultsResponse<Inquilino> insertInquilino(InquilinoMaintenanceRequest request)
	{
		InternalResultsResponse<Inquilino> response = new InternalResultsResponse<Inquilino>();

		response = getPessoaDAC().insertInquilino(request.getInquilino());

		return response;
	}

	@Override
	public InternalResultsResponse<Inquilino> updateInquilino(InquilinoMaintenanceRequest request)
	{
		InternalResultsResponse<Inquilino> response = new InternalResultsResponse<Inquilino>();

		response = getPessoaDAC().updateInquilino(request.getInquilino());

		return response;
	}

	@Override
	public InternalResponse deleteInquilino(InquilinoMaintenanceRequest request)
	{
		return getPessoaDAC().deleteInquilino(request.getInquilino());
	}

	@Override
	public InternalResultsResponse<Inquilino> fetchInquilinoById(FetchByIdRequest request)
	{
		return getPessoaDAC().fetchInquilinoById(request);
	}

	@Override
	public InternalResultsResponse<Inquilino> fetchInquilinoByRequest(InquilinoInquiryRequest request)
	{
		return getPessoaDAC().fetchInquilinoByRequest(request);
	}

	// =================================

	@Override
	public InternalResultsResponse<Cliente> insertCliente(ClienteMaintenanceRequest request)
	{
		InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();

		response = getPessoaDAC().insertCliente(request.getCliente());

		return response;
	}

	@Override
	public InternalResultsResponse<Cliente> updateCliente(ClienteMaintenanceRequest request)
	{
		InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();

		response = getPessoaDAC().updateCliente(request.getCliente());

		return response;
	}

	@Override
	public InternalResponse deleteCliente(ClienteMaintenanceRequest request)
	{
		return getPessoaDAC().deleteCliente(request.getCliente());
	}

	@Override
	public InternalResultsResponse<Cliente> fetchClienteById(FetchByIdRequest request)
	{
		return getPessoaDAC().fetchClienteById(request);
	}

	@Override
	public InternalResultsResponse<Cliente> fetchClienteByRequest(ClienteInquiryRequest request)
	{
		return getPessoaDAC().fetchClienteByRequest(request);
	}

	// =================================

	@Override
	public InternalResultsResponse<Fornecedor> insertFornecedor(FornecedorMaintenanceRequest request)
	{
		InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();

		response = getPessoaDAC().insertFornecedor(request.getFornecedor());

		return response;
	}

	@Override
	public InternalResultsResponse<Fornecedor> updateFornecedor(FornecedorMaintenanceRequest request)
	{
		InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();

		response = getPessoaDAC().updateFornecedor(request.getFornecedor());

		return response;
	}

	@Override
	public InternalResponse deleteFornecedor(FornecedorMaintenanceRequest request)
	{
		return getPessoaDAC().deleteFornecedor(request.getFornecedor());
	}

	@Override
	public InternalResultsResponse<Fornecedor> fetchFornecedorById(FetchByIdRequest request)
	{
		return getPessoaDAC().fetchFornecedorById(request);
	}

	@Override
	public InternalResultsResponse<Fornecedor> fetchFornecedorByRequest(FornecedorInquiryRequest request)
	{
		return getPessoaDAC().fetchFornecedorByRequest(request);
	}

	// ========================================
	@Override
	public InternalResultsResponse<Transportador> insertTransportador(TransportadorMaintenanceRequest request)
	{
		InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();

		response = getPessoaDAC().insertTransportador(request.getTransportador());

		return response;
	}

	@Override
	public InternalResultsResponse<Transportador> updateTransportador(TransportadorMaintenanceRequest request)
	{
		InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();

		response = getPessoaDAC().updateTransportador(request.getTransportador());

		return response;
	}

	@Override
	public InternalResponse deleteTransportador(TransportadorMaintenanceRequest request)
	{
		return getPessoaDAC().deleteTransportador(request.getTransportador());
	}

	@Override
	public InternalResultsResponse<Transportador> fetchTransportadorById(FetchByIdRequest request)
	{
		return getPessoaDAC().fetchTransportadorById(request);
	}

	@Override
	public InternalResultsResponse<Transportador> fetchTransportadorByRequest(TransportadorInquiryRequest request)
	{
		return getPessoaDAC().fetchTransportadorByRequest(request);
	}

	// ========================================
	@Override
	public InternalResultsResponse<Contador> insertContador(ContadorMaintenanceRequest request)
	{
		InternalResultsResponse<Contador> response = new InternalResultsResponse<Contador>();

		response = getPessoaDAC().insertContador(request.getContador());

		return response;
	}

	@Override
	public InternalResultsResponse<Contador> updateContador(ContadorMaintenanceRequest request)
	{
		InternalResultsResponse<Contador> response = new InternalResultsResponse<Contador>();

		response = getPessoaDAC().updateContador(request.getContador());

		return response;
	}

	@Override
	public InternalResponse deleteContador(ContadorMaintenanceRequest request)
	{
		return getPessoaDAC().deleteContador(request.getContador());
	}

	@Override
	public InternalResultsResponse<Contador> fetchContadorById(FetchByIdRequest request)
	{
		return getPessoaDAC().fetchContadorById(request);
	}

	@Override
	public InternalResultsResponse<Contador> fetchContadorByRequest(ContadorInquiryRequest request)
	{
		return getPessoaDAC().fetchContadorByRequest(request);
	}

	@Override
	public InternalResultsResponse<Profissao> fetchProfissaoByRequest(ProfissaoInquiryRequest request)
	{
		return getPessoaDAC().fetchProfissaoByRequest(request);
	}

	@Override
	public InternalResultsResponse<Convenio> fetchConvenioByRequest(ConvenioInquiryRequest request)
	{
		return getPessoaDAC().fetchConvenioByRequest(request);
	}

	@Override
	public InternalResultsResponse<Contato> fetchContatoByRequest(ContatoInquiryRequest request)
	{
		return getPessoaDAC().fetchContatoByRequest(request);
	}

	@Override
	public InternalResultsResponse<Banco> fetchBancoByRequest(BancoInquiryRequest request)
	{
		return getPessoaDAC().fetchBancoByRequest(request);
	}

	@Override
	public InternalResultsResponse<FormaPg> fetchFormaPgByRequest(FormaPgInquiryRequest request)
	{
		return getPessoaDAC().fetchFormaPgByRequest(request);
	}

	@Override
	public InternalResultsResponse<Agencia> fetchAgenciaByRequest(AgenciaInquiryRequest request)
	{
		return getPessoaDAC().fetchAgenciaByRequest(request);
	}

	@Override
	public InternalResultsResponse<Conta> fetchContaByRequest(ContaInquiryRequest request)
	{
		return getPessoaDAC().fetchContaByRequest(request);
	}

	@Override
	public InternalResultsResponse<Estado> fetchEstadoByRequest(EstadoInquiryRequest request)
	{
		return getPessoaDAC().fetchEstadoByRequest(request);
	}

	@Override
	public InternalResultsResponse<Cidade> fetchCidadeRequest(CidadeInquiryRequest request)
	{
		return getPessoaDAC().fetchCidadeRequest(request);
	}

	@Override
	public InternalResultsResponse<Eventos> fetchEventosRequest(EventoInquiryRequest request)
	{
		return getPessoaDAC().fetchEventosRequest(request);
	}

	@Override
	public InternalResultsResponse<Beneficios> fetchBeneficiosRequest(BeneficiosInquiryRequest request)
	{
		return getPessoaDAC().fetchBeneficiosRequest(request);
	}

	@Override
	public InternalResultsResponse<HorarioFunc> fetchHorarioFuncsRequest(HoraFuncInquiryRequest request)
	{
		return getPessoaDAC().fetchHorarioFuncsRequest(request);
	}

	@Override
	public InternalResultsResponse<Funcionario> insertFuncionario(FuncionarioMaintenanceRequest request)
	{
		return getPessoaDAC().insertFuncionario(request.getFuncionario());
	}

	@Override
	public InternalResultsResponse<Funcionario> updateFuncionario(FuncionarioMaintenanceRequest request)
	{
		return getPessoaDAC().updateFuncionario(request.getFuncionario());
	}

	@Override
	public InternalResponse deleteFuncionario(FuncionarioMaintenanceRequest request)
	{
		return getPessoaDAC().deleteFuncionario(request.getFuncionario());
	}

	@Override
	public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request)
	{
		return getPessoaDAC().fetchFuncionarioById(request);
	}

	@Override
	public InternalResultsResponse<Funcionario> fetchFuncionarioByRequest(FuncionarioInquiryRequest request)
	{
		return getPessoaDAC().fetchFuncionarioByRequest(request);
	}
}
