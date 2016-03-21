package com.qat.samples.sysmgmt.pessoa.bas.rest;

import com.qat.samples.sysmgmt.advocacia.model.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.model.request.AdvogadoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.model.response.AdvogadoResponse;
import com.qat.samples.sysmgmt.agencia.model.request.AgenciaInquiryRequest;
import com.qat.samples.sysmgmt.agencia.model.response.AgenciaResponse;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.model.response.BeneficiosResponse;
import com.qat.samples.sysmgmt.condpag.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.response.ContatoResponse;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.ProfissaoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.response.BancoResponse;
import com.qat.samples.sysmgmt.dp.model.response.ContaResponse;
import com.qat.samples.sysmgmt.dp.model.response.ConvenioResponse;
import com.qat.samples.sysmgmt.dp.model.response.EventoResponse;
import com.qat.samples.sysmgmt.dp.model.response.FormaPgResponse;
import com.qat.samples.sysmgmt.dp.model.response.FuncionarioResponse;
import com.qat.samples.sysmgmt.dp.model.response.HorarioFuncResponse;
import com.qat.samples.sysmgmt.dp.model.response.ProfissaoResponse;
import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.estado.model.response.EstadoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.bai.IPessoaBAI;
import com.qat.samples.sysmgmt.pessoa.bas.IPessoaRESTBAS;
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
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
public class PessoaBAS implements IPessoaRESTBAS
{

	/** The empresa bai. */
	private IPessoaBAI pessoaBAI; // injected by Spring through setter

	public IPessoaBAI getPessoaBAI()
	{
		return pessoaBAI;
	}

	public void setPessoaBAI(IPessoaBAI pessoaBAI)
	{
		this.pessoaBAI = pessoaBAI;
	}

	@Override
	public AdvogadoResponse insertAdvogado(AdvogadoMaintenanceRequest request)
	{
		return getPessoaBAI().insertAdvogado(request);
	}

	@Override
	public AdvogadoResponse updateAdvogado(AdvogadoMaintenanceRequest request)
	{
		return getPessoaBAI().updateAdvogado(request);

	}

	@Override
	public AdvogadoResponse deleteAdvogado(AdvogadoMaintenanceRequest request)
	{
		return getPessoaBAI().deleteAdvogado(request);

	}

	@Override
	public AdvogadoResponse fetchAdvogadoById(FetchByIdRequest request)
	{
		return getPessoaBAI().fetchAdvogadoById(request);

	}

	@Override
	public AdvogadoResponse fetchAdvogadoByRequest(AdvogadoInquiryRequest request)
	{
		return getPessoaBAI().fetchAdvogadoByRequest(request);

	}

	// ==========================
	
	
	@Override
	public SindicoResponse insertSindico(SindicoMaintenanceRequest request)
	{
		return getPessoaBAI().insertSindico(request);
	}

	@Override
	public SindicoResponse updateSindico(SindicoMaintenanceRequest request)
	{
		return getPessoaBAI().updateSindico(request);

	}

	@Override
	public SindicoResponse deleteSindico(SindicoMaintenanceRequest request)
	{
		return getPessoaBAI().deleteSindico(request);

	}

	@Override
	public SindicoResponse fetchSindicoById(FetchByIdRequest request)
	{
		return getPessoaBAI().fetchSindicoById(request);

	}

	@Override
	public SindicoResponse fetchSindicoByRequest(SindicoInquiryRequest request)
	{
		return getPessoaBAI().fetchSindicoByRequest(request);

	}

	// ==========================

	
	@Override
	public InquilinoResponse insertInquilino(InquilinoMaintenanceRequest request)
	{
		return getPessoaBAI().insertInquilino(request);
	}

	@Override
	public InquilinoResponse updateInquilino(InquilinoMaintenanceRequest request)
	{
		return getPessoaBAI().updateInquilino(request);

	}

	@Override
	public InquilinoResponse deleteInquilino(InquilinoMaintenanceRequest request)
	{
		return getPessoaBAI().deleteInquilino(request);

	}

	@Override
	public InquilinoResponse fetchInquilinoById(FetchByIdRequest request)
	{
		return getPessoaBAI().fetchInquilinoById(request);

	}

	@Override
	public InquilinoResponse fetchInquilinoByRequest(InquilinoInquiryRequest request)
	{
		return getPessoaBAI().fetchInquilinoByRequest(request);

	}

	// ==========================


	@Override
	public ClienteResponse insertCliente(ClienteMaintenanceRequest request)
	{
		return getPessoaBAI().insertCliente(request);
	}

	@Override
	public ClienteResponse updateCliente(ClienteMaintenanceRequest request)
	{
		return getPessoaBAI().updateCliente(request);

	}

	@Override
	public ClienteResponse deleteCliente(ClienteMaintenanceRequest request)
	{
		return getPessoaBAI().deleteCliente(request);

	}

	@Override
	public ClienteResponse fetchClienteById(FetchByIdRequest request)
	{
		return getPessoaBAI().fetchClienteById(request);

	}

	@Override
	public ClienteResponse fetchClienteByRequest(ClienteInquiryRequest request)
	{
		return getPessoaBAI().fetchClienteByRequest(request);

	}

	@Override
	public FornecedorResponse insertFornecedor(FornecedorMaintenanceRequest request)
	{
		return getPessoaBAI().insertFornecedor(request);

	}

	@Override
	public FornecedorResponse updateFornecedor(FornecedorMaintenanceRequest request)
	{
		return getPessoaBAI().updateFornecedor(request);

	}

	@Override
	public FornecedorResponse deleteFornecedor(FornecedorMaintenanceRequest request)
	{
		return getPessoaBAI().deleteFornecedor(request);

	}

	@Override
	public FornecedorResponse fetchFornecedorById(FetchByIdRequest request)
	{
		return getPessoaBAI().fetchFornecedorById(request);

	}

	@Override
	public FornecedorResponse fetchFornecedorByRequest(FornecedorInquiryRequest request)
	{
		return getPessoaBAI().fetchFornecedorByRequest(request);

	}

	@Override
	public FuncionarioResponse insertFuncionario(FuncionarioMaintenanceRequest request)
	{
		return getPessoaBAI().insertFuncionario(request);

	}

	@Override
	public FuncionarioResponse updateFuncionario(FuncionarioMaintenanceRequest request)
	{
		return getPessoaBAI().updateFuncionario(request);

	}

	@Override
	public FuncionarioResponse deleteFuncionario(FuncionarioMaintenanceRequest request)
	{
		return getPessoaBAI().deleteFuncionario(request);

	}

	@Override
	public FuncionarioResponse fetchFuncionarioById(FetchByIdRequest request)
	{
		return getPessoaBAI().fetchFuncionarioById(request);

	}

	@Override
	public FuncionarioResponse fetchFuncionarioByRequest(FuncionarioInquiryRequest request)
	{
		return getPessoaBAI().fetchFuncionarioByRequest(request);

	}

	@Override
	public TransportadorResponse insertTransportador(TransportadorMaintenanceRequest request)
	{
		return getPessoaBAI().insertTransportador(request);

	}

	@Override
	public TransportadorResponse updateTransportador(TransportadorMaintenanceRequest request)
	{
		return getPessoaBAI().updateTransportador(request);

	}

	@Override
	public TransportadorResponse deleteTransportador(TransportadorMaintenanceRequest request)
	{
		return getPessoaBAI().deleteTransportador(request);

	}

	@Override
	public TransportadorResponse fetchTransportadorById(FetchByIdRequest request)
	{
		return getPessoaBAI().fetchTransportadorById(request);

	}

	@Override
	public TransportadorResponse fetchTransportadorByRequest(TransportadorInquiryRequest request)
	{
		return getPessoaBAI().fetchTransportadorByRequest(request);

	}

	@Override
	public ContadorResponse insertContador(ContadorMaintenanceRequest request)
	{
		return getPessoaBAI().insertContador(request);

	}

	@Override
	public ContadorResponse updateContador(ContadorMaintenanceRequest request)
	{
		return getPessoaBAI().updateContador(request);

	}

	@Override
	public ContadorResponse deleteContador(ContadorMaintenanceRequest request)
	{
		return getPessoaBAI().deleteContador(request);

	}

	@Override
	public ContadorResponse fetchContadorById(FetchByIdRequest request)
	{
		return getPessoaBAI().fetchContadorById(request);

	}

	@Override
	public ContadorResponse fetchContadorByRequest(ContadorInquiryRequest request)
	{
		return getPessoaBAI().fetchContadorByRequest(request);

	}

	@Override
	public ProfissaoResponse fetchProfissaoByRequest(ProfissaoInquiryRequest request)
	{
		return getPessoaBAI().fetchProfissaoByRequest(request);

	}

	@Override
	public ContatoResponse fetchContatoByRequest(ContatoInquiryRequest request)
	{
		return getPessoaBAI().fetchContatoByRequest(request);

	}

	@Override
	public AgenciaResponse fetchAgenciaByRequest(AgenciaInquiryRequest request)
	{
		return getPessoaBAI().fetchAgenciaByRequest(request);

	}

	@Override
	public EstadoResponse fetchEstadoByRequest(EstadoInquiryRequest request)
	{
		return getPessoaBAI().fetchEstadoByRequest(request);

	}

	@Override
	public CidadeResponse fetchCidadeRequest(CidadeInquiryRequest request)
	{
		return getPessoaBAI().fetchCidadeRequest(request);

	}

	@Override
	public EventoResponse fetchEventosRequest(EventoInquiryRequest request)
	{
		return getPessoaBAI().fetchEventosRequest(request);

	}

	@Override
	public BeneficiosResponse fetchBeneficiosRequest(BeneficiosInquiryRequest request)
	{
		return getPessoaBAI().fetchBeneficiosRequest(request);

	}

	@Override
	public HorarioFuncResponse fetchHorarioFuncsRequest(HoraFuncInquiryRequest request)
	{
		return getPessoaBAI().fetchHorarioFuncsRequest(request);

	}

	@Override
	public ConvenioResponse fetchConvenioByRequest(ConvenioInquiryRequest request)
	{
		return getPessoaBAI().fetchConvenioByRequest(request);

	}

	@Override
	public BancoResponse fetchBancoByRequest(BancoInquiryRequest request)
	{
		return getPessoaBAI().fetchBancoByRequest(request);

	}

	@Override
	public FormaPgResponse fetchFormaPgByRequest(FormaPgInquiryRequest request)
	{
		return getPessoaBAI().fetchFormaPgByRequest(request);

	}

	@Override
	public ContaResponse fetchContaByRequest(ContaInquiryRequest request)
	{
		return getPessoaBAI().fetchContaByRequest(request);

	}
}
