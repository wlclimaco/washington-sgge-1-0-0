package com.qat.samples.sysmgmt.pessoa.bas;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
 * The Interface ISupermercadoRESTBAS. (Business Area Service - BAS)
 */
@Consumes("application/json")
@Produces("application/json")
public interface IPessoaRESTBAS
{

	@POST
	@Path("/insertCliente/")
	public ClienteResponse insertCliente(ClienteMaintenanceRequest request);

	@POST
	@Path("/updateCliente/")
	public ClienteResponse updateCliente(ClienteMaintenanceRequest request);

	@POST
	@Path("/deleteCliente/")
	public ClienteResponse deleteCliente(ClienteMaintenanceRequest request);

	@POST
	@Path("/fetchClienteById/")
	public ClienteResponse fetchClienteById(FetchByIdRequest request);

	@POST
	@Path("/fetchClienteByRequest/")
	public ClienteResponse fetchClienteByRequest(ClienteInquiryRequest request);

	// ===========

	@POST
	@Path("/insertAdvogado/")
	public AdvogadoResponse insertAdvogado(AdvogadoMaintenanceRequest request);

	@POST
	@Path("/updateAdvogado/")
	public AdvogadoResponse updateAdvogado(AdvogadoMaintenanceRequest request);

	@POST
	@Path("/deleteAdvogado/")
	public AdvogadoResponse deleteAdvogado(AdvogadoMaintenanceRequest request);

	@POST
	@Path("/fetchAdvogadoById/")
	public AdvogadoResponse fetchAdvogadoById(FetchByIdRequest request);

	@POST
	@Path("/fetchAdvogadoByRequest/")
	public AdvogadoResponse fetchAdvogadoByRequest(AdvogadoInquiryRequest request);

	// ===========

	@POST
	@Path("/insertFornecedor/")
	public FornecedorResponse insertFornecedor(FornecedorMaintenanceRequest request);

	@POST
	@Path("/updateFornecedor/")
	public FornecedorResponse updateFornecedor(FornecedorMaintenanceRequest request);

	@POST
	@Path("/deleteFornecedor/")
	public FornecedorResponse deleteFornecedor(FornecedorMaintenanceRequest request);

	@POST
	@Path("/fetchFornecedorById/")
	public FornecedorResponse fetchFornecedorById(FetchByIdRequest request);

	@POST
	@Path("/fetchFornecedorByRequest/")
	public FornecedorResponse fetchFornecedorByRequest(FornecedorInquiryRequest request);

	// ==========

	@POST
	@Path("/insertFuncionario/")
	public FuncionarioResponse insertFuncionario(FuncionarioMaintenanceRequest request);

	@POST
	@Path("/updateFuncionario/")
	public FuncionarioResponse updateFuncionario(FuncionarioMaintenanceRequest request);

	@POST
	@Path("/deleteFuncionario/")
	public FuncionarioResponse deleteFuncionario(FuncionarioMaintenanceRequest request);

	@POST
	@Path("/fetchFuncionarioById/")
	public FuncionarioResponse fetchFuncionarioById(FetchByIdRequest request);

	@POST
	@Path("/fetchFuncionarioByRequest/")
	public FuncionarioResponse fetchFuncionarioByRequest(FuncionarioInquiryRequest request);

	// ==========

	@POST
	@Path("/insertTransportador/")
	public TransportadorResponse insertTransportador(TransportadorMaintenanceRequest request);

	@POST
	@Path("/updateTransportador/")
	public TransportadorResponse updateTransportador(TransportadorMaintenanceRequest request);

	@POST
	@Path("/deleteTransportador/")
	public TransportadorResponse deleteTransportador(TransportadorMaintenanceRequest request);

	@POST
	@Path("/fetchTransportadorById/")
	public TransportadorResponse fetchTransportadorById(FetchByIdRequest request);

	@POST
	@Path("/fetchTransportadorByRequest/")
	public TransportadorResponse fetchTransportadorByRequest(TransportadorInquiryRequest request);

	// ==========

	// ==========

	@POST
	@Path("/insertContador/")
	public ContadorResponse insertContador(ContadorMaintenanceRequest request);

	@POST
	@Path("/updateContador/")
	public ContadorResponse updateContador(ContadorMaintenanceRequest request);

	@POST
	@Path("/deleteContador/")
	public ContadorResponse deleteContador(ContadorMaintenanceRequest request);

	@POST
	@Path("/fetchContadorById/")
	public ContadorResponse fetchContadorById(FetchByIdRequest request);

	@POST
	@Path("/fetchContadorByRequest/")
	public ContadorResponse fetchContadorByRequest(ContadorInquiryRequest request);

	@POST
	@Path("/fetchProfissaoByRequest/")
	public ProfissaoResponse fetchProfissaoByRequest(ProfissaoInquiryRequest request);

	@POST
	@Path("/fetchContatoByRequest/")
	public ContatoResponse fetchContatoByRequest(ContatoInquiryRequest request);

	@POST
	@Path("/fetchAgenciaByRequest/")
	public AgenciaResponse fetchAgenciaByRequest(AgenciaInquiryRequest request);

	@POST
	@Path("/fetchEstadoByRequest/")
	public EstadoResponse fetchEstadoByRequest(EstadoInquiryRequest request);

	@POST
	@Path("/fetchCidadeRequest/")
	public CidadeResponse fetchCidadeRequest(CidadeInquiryRequest request);

	@POST
	@Path("/fetchEventosRequest/")
	public EventoResponse fetchEventosRequest(EventoInquiryRequest request);

	@POST
	@Path("/fetchBeneficiosRequest/")
	public BeneficiosResponse fetchBeneficiosRequest(BeneficiosInquiryRequest request);

	@POST
	@Path("/fetchHorarioFuncsRequest/")
	public HorarioFuncResponse fetchHorarioFuncsRequest(HoraFuncInquiryRequest request);

	@POST
	@Path("/fetchConvenioByRequest/")
	public ConvenioResponse fetchConvenioByRequest(ConvenioInquiryRequest request);

	@POST
	@Path("/fetchBancoByRequest/")
	public BancoResponse fetchBancoByRequest(BancoInquiryRequest request);

	@POST
	@Path("/fetchFormaPgByRequest/")
	public FormaPgResponse fetchFormaPgByRequest(FormaPgInquiryRequest request);

	@POST
	@Path("/fetchContaByRequest/")
	public ContaResponse fetchContaByRequest(ContaInquiryRequest request);

}
