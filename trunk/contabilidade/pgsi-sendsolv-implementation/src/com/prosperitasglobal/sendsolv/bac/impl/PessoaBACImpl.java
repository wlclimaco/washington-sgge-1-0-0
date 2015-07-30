package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IPessoaBAC;
import com.prosperitasglobal.sendsolv.dac.IPessoaDAC;
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
import com.prosperitasglobal.sendsolv.model.request.ContadorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ContadorMaintenanceRequest;
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
}
