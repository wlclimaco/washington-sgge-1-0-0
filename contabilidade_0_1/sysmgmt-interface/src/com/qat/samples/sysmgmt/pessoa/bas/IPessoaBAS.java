package com.qat.samples.sysmgmt.pessoa.bas;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.advocacia.model.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.model.request.AdvogadoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.model.response.AdvogadoResponse;
import com.qat.samples.sysmgmt.agencia.model.request.AgenciaInquiryRequest;
import com.qat.samples.sysmgmt.agencia.model.response.AgenciaResponse;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.model.response.BeneficiosResponse;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.response.InquilinoResponse;
import com.qat.samples.sysmgmt.condominio.model.response.SindicoResponse;
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
 * The Interface IEntidadeBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "PessoaService", targetNamespace = "http://qat.com/sysmgmt", portName = "PessoaServicePort")
public interface IPessoaBAS
{

	@WebMethod(action = "insertCliente")
	@WebResult(name = "insertClienteReturn")
	@WSDLDocumentation(value = "Insert a Usuario record and optionally returns a list of Usuarios.")
	public ClienteResponse insertCliente(ClienteMaintenanceRequest request);

	@WebMethod(action = "updateCliente")
	@WebResult(name = "updateClienteReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public ClienteResponse updateCliente(ClienteMaintenanceRequest request);

	@WebMethod(action = "deleteCliente")
	@WebResult(name = "deleteClienteReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public ClienteResponse deleteCliente(ClienteMaintenanceRequest request);

	@WebMethod(action = "fetchClienteById")
	@WebResult(name = "fetchClienteByIdReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public ClienteResponse fetchClienteById(FetchByIdRequest request);

	@WebMethod(action = "fetchClienteByRequest")
	@WebResult(name = "fetchClienteByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public ClienteResponse fetchClienteByRequest(ClienteInquiryRequest request);

	// =========================

	// ===================Inquilimo

	@WebMethod(action = "insertInquilino")
	@WebResult(name = "insertInquilinoReturn")
	@WSDLDocumentation(value = "Insert a Usuario record and optionally returns a list of Usuarios.")
	public InquilinoResponse insertInquilino(InquilinoMaintenanceRequest request);

	@WebMethod(action = "updateInquilino")
	@WebResult(name = "updateInquilinoReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public InquilinoResponse updateInquilino(InquilinoMaintenanceRequest request);

	@WebMethod(action = "deleteInquilino")
	@WebResult(name = "deleteInquilinoReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public InquilinoResponse deleteInquilino(InquilinoMaintenanceRequest request);

	@WebMethod(action = "fetchInquilinoById")
	@WebResult(name = "fetchInquilinoByIdReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public InquilinoResponse fetchInquilinoById(FetchByIdRequest request);

	@WebMethod(action = "fetchInquilinoByRequest")
	@WebResult(name = "fetchInquilinoByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public InquilinoResponse fetchInquilinoByRequest(InquilinoInquiryRequest request);

	// =========================
	// ==========================Sindico
	@WebMethod(action = "insertSindico")
	@WebResult(name = "insertSindicoReturn")
	@WSDLDocumentation(value = "Insert a Usuario record and optionally returns a list of Usuarios.")
	public SindicoResponse insertSindico(SindicoMaintenanceRequest request);

	@WebMethod(action = "updateSindico")
	@WebResult(name = "updateSindicoReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public SindicoResponse updateSindico(SindicoMaintenanceRequest request);

	@WebMethod(action = "deleteSindico")
	@WebResult(name = "deleteSindicoReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public SindicoResponse deleteSindico(SindicoMaintenanceRequest request);

	@WebMethod(action = "fetchSindicoById")
	@WebResult(name = "fetchSindicoByIdReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public SindicoResponse fetchSindicoById(FetchByIdRequest request);

	@WebMethod(action = "fetchSindicoByRequest")
	@WebResult(name = "fetchSindicoByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public SindicoResponse fetchSindicoByRequest(SindicoInquiryRequest request);

	// =========================

	@WebMethod(action = "insertAdvogado")
	@WebResult(name = "insertAdvogadoReturn")
	@WSDLDocumentation(value = "Insert a Usuario record and optionally returns a list of Usuarios.")
	public AdvogadoResponse insertAdvogado(AdvogadoMaintenanceRequest request);

	@WebMethod(action = "updateAdvogado")
	@WebResult(name = "updateAdvogadoReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public AdvogadoResponse updateAdvogado(AdvogadoMaintenanceRequest request);

	@WebMethod(action = "deleteAdvogado")
	@WebResult(name = "deleteAdvogadoReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public AdvogadoResponse deleteAdvogado(AdvogadoMaintenanceRequest request);

	@WebMethod(action = "fetchAdvogadoById")
	@WebResult(name = "fetchAdvogadoByIdReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public AdvogadoResponse fetchAdvogadoById(FetchByIdRequest request);

	@WebMethod(action = "fetchAdvogadoByRequest")
	@WebResult(name = "fetchAdvogadoByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public AdvogadoResponse fetchAdvogadoByRequest(AdvogadoInquiryRequest request);

	// =========================

	@WebMethod(action = "insertFornecedor")
	@WebResult(name = "insertFornecedorReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public FornecedorResponse insertFornecedor(FornecedorMaintenanceRequest request);

	@WebMethod(action = "updateFornecedor")
	@WebResult(name = "updateFornecedorReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public FornecedorResponse updateFornecedor(FornecedorMaintenanceRequest request);

	@WebMethod(action = "deleteFornecedor")
	@WebResult(name = "deleteFornecedorReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public FornecedorResponse deleteFornecedor(FornecedorMaintenanceRequest request);

	@WebMethod(action = "fetchFornecedorById")
	@WebResult(name = "fetchFornecedorByIdReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public FornecedorResponse fetchFornecedorById(FetchByIdRequest request);

	@WebMethod(action = "fetchFornecedorByRequest")
	@WebResult(name = "fetchFornecedorByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public FornecedorResponse fetchFornecedorByRequest(FornecedorInquiryRequest request);

	@WebMethod(action = "insertTransportador")
	@WebResult(name = "insertTransportadorReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public TransportadorResponse insertTransportador(TransportadorMaintenanceRequest request);

	@WebMethod(action = "updateTransportador")
	@WebResult(name = "updateTransportadorReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public TransportadorResponse updateTransportador(TransportadorMaintenanceRequest request);

	@WebMethod(action = "deleteTransportador")
	@WebResult(name = "deleteTransportadorReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public TransportadorResponse deleteTransportador(TransportadorMaintenanceRequest request);

	@WebMethod(action = "fetchTransportadorById")
	@WebResult(name = "fetchTransportadorByIdReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public TransportadorResponse fetchTransportadorById(FetchByIdRequest request);

	@WebMethod(action = "fetchTransportadorByRequest")
	@WebResult(name = "fetchTransportadorByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public TransportadorResponse fetchTransportadorByRequest(TransportadorInquiryRequest request);

	@WebMethod(action = "insertContador")
	@WebResult(name = "insertContadorReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public ContadorResponse insertContador(ContadorMaintenanceRequest request);

	@WebMethod(action = "updateContador")
	@WebResult(name = "updateContadorReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public ContadorResponse updateContador(ContadorMaintenanceRequest request);

	@WebMethod(action = "deleteContador")
	@WebResult(name = "deleteContadorReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public ContadorResponse deleteContador(ContadorMaintenanceRequest request);

	@WebMethod(action = "fetchContadorById")
	@WebResult(name = "fetchContadorByIdReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public ContadorResponse fetchContadorById(FetchByIdRequest request);

	@WebMethod(action = "fetchContadorByRequest")
	@WebResult(name = "fetchContadorByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public ContadorResponse fetchContadorByRequest(ContadorInquiryRequest request);

	@WebMethod(action = "fetchProfissaoByRequest")
	@WebResult(name = "fetchProfissaoByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public ProfissaoResponse fetchProfissaoByRequest(ProfissaoInquiryRequest request);

	@WebMethod(action = "fetchConvenioByRequest")
	@WebResult(name = "fetchConvenioByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public ConvenioResponse fetchConvenioByRequest(ConvenioInquiryRequest request);

	@WebMethod(action = "fetchContatoByRequest")
	@WebResult(name = "fetchContatoByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public ContatoResponse fetchContatoByRequest(ContatoInquiryRequest request);

	@WebMethod(action = "fetchBancoByRequest")
	@WebResult(name = "fetchBancoByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public BancoResponse fetchBancoByRequest(BancoInquiryRequest request);

	@WebMethod(action = "fetchAgenciaByRequest")
	@WebResult(name = "fetchAgenciaByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public AgenciaResponse fetchAgenciaByRequest(AgenciaInquiryRequest request);

	@WebMethod(action = "fetchContaByRequest")
	@WebResult(name = "fetchContaByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public ContaResponse fetchContaByRequest(ContaInquiryRequest request);

	@WebMethod(action = "fetchEstadoByRequest")
	@WebResult(name = "fetchEstadoByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public EstadoResponse fetchEstadoByRequest(EstadoInquiryRequest request);

	@WebMethod(action = "fetchFormaPgByRequest")
	@WebResult(name = "fetchFormaPgByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public FormaPgResponse fetchFormaPgByRequest(FormaPgInquiryRequest request);

	@WebMethod(action = "fetchCidadeRequest")
	@WebResult(name = "fetchCidadeRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public CidadeResponse fetchCidadeRequest(CidadeInquiryRequest request);

	@WebMethod(action = "fetchEventosRequest")
	@WebResult(name = "fetchEventosRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public EventoResponse fetchEventosRequest(EventoInquiryRequest request);

	@WebMethod(action = "fetchBeneficiosRequest")
	@WebResult(name = "fetchBeneficiosRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public BeneficiosResponse fetchBeneficiosRequest(BeneficiosInquiryRequest request);

	@WebMethod(action = "fetchHorarioFuncsRequest")
	@WebResult(name = "fetchHorarioFuncsRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public HorarioFuncResponse fetchHorarioFuncsRequest(HoraFuncInquiryRequest request);

	@WebMethod(action = "insertFuncionario")
	@WebResult(name = "insertFuncionarioReturn")
	@WSDLDocumentation(value = "Insert a Usuario record and optionally returns a list of Usuarios.")
	public FuncionarioResponse insertFuncionario(FuncionarioMaintenanceRequest request);

	@WebMethod(action = "updateFuncionario")
	@WebResult(name = "updateFuncionarioReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public FuncionarioResponse updateFuncionario(FuncionarioMaintenanceRequest request);

	@WebMethod(action = "deleteFuncionario")
	@WebResult(name = "deleteFuncionarioReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public FuncionarioResponse deleteFuncionario(FuncionarioMaintenanceRequest request);

	@WebMethod(action = "fetchFuncionarioById")
	@WebResult(name = "fetchFuncionarioByIdReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public FuncionarioResponse fetchFuncionarioById(FetchByIdRequest request);

	@WebMethod(action = "fetchFuncionarioByRequest")
	@WebResult(name = "fetchFuncionarioByRequestReturn")
	@WSDLDocumentation(value = "Insert a usuarios record and optionally returns a list of usuarios.")
	public FuncionarioResponse fetchFuncionarioByRequest(FuncionarioInquiryRequest request);

}
