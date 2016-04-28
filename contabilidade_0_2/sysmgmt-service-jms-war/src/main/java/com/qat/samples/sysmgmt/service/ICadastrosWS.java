/** create by system gera-java version 1.0.0 28/04/2016 14:59 : 50*/
package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.dp.model.response.ConvenioResponse;
import com.qat.samples.sysmgmt.entidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.estado.model.request.EstadoMaintenanceRequest;
import com.qat.samples.sysmgmt.estado.model.response.EstadoResponse;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ConvenioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.response.ClienteResponse;
import com.qat.samples.sysmgmt.pessoa.model.response.FornecedorResponse;
import com.qat.samples.sysmgmt.pessoa.model.response.TransportadorResponse;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.ConvenioMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.util.model.request.TarefaInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.TarefaMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.util.model.response.TarefaResponse;

/**
 * The Interface ICadastrosBAS delegate used by a JMS listener. (Business Area Service - BAS)
 */
@WebService(serviceName = "CadastrosService", targetNamespace = "http://qat.com/jms", portName = "CadastrosServicePort")
public interface ICadastrosWS
{

//===================================### CLIENTE ####======================================

/**
	 * Rebuild a list of Clientes.
	 *
	 * @param request ClienteRequest object containing parameter for building the list of Cliente objects.
	 *
	 * @return the ClienteResponse containing the list of Clientes built
	 */
	@WebMethod(action = "insertClientes")
	@WebResult(name = "insertClientesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ClienteResponse insertCliente(@WebParam(name = "request") ClienteMaintenanceRequest request);

	/**
	 * Rebuild a list of Clientes.
	 *
	 * @param request ClienteRequest object containing parameter for building the list of Cliente objects.
	 *
	 * @return the ClienteResponse containing the list of Clientes built
	 */
	@WebMethod(action = "updateClientes")
	@WebResult(name = "updateClientesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ClienteResponse updateCliente(@WebParam(name = "request") ClienteMaintenanceRequest request);

	/**
	 * Rebuild a list of Clientes.
	 *
	 * @param request ClienteRequest object containing parameter for building the list of Cliente objects.
	 *
	 * @return the ClienteResponse containing the list of Clientes built
	 */
	@WebMethod(action = "deleteClientes")
	@WebResult(name = "deleteClientesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ClienteResponse deleteCliente(@WebParam(name = "request") ClienteMaintenanceRequest request);

	/**
	 * Rebuild a list of Clientes.
	 *
	 * @param request ClienteRequest object containing parameter for building the list of Cliente objects.
	 *
	 * @return the ClienteResponse containing the list of Clientes built
	 */
	@WebMethod(action = "fetchClienteById")
	@WebResult(name = "fetchClienteByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ClienteResponse fetchClienteById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Clientes.
	 *
	 * @param request ClienteRequest object containing parameter for building the list of Cliente objects.
	 *
	 * @return the ClienteResponse containing the list of Clientes built
	 */
	@WebMethod(action = "fetchClientesByRequest")
	@WebResult(name = "fetchClientesByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ClienteResponse fetchClientesByRequest(@WebParam(name = "request") ClienteInquiryRequest request);


	/**
	 * Rebuild a list of Clientes.
	 *
	 * @param request ClienteRequest object containing parameter for building the list of Cliente objects.
	 *
	 * @return the ClienteResponse containing the list of Clientes built
	 */
	@WebMethod(action = "refreshClientes")
	@WebResult(name = "refreshClientesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ClienteResponse refreshClientes(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Clientes.
	 *
	 * @param request the request
	 *
	 * @return the ClienteResponse containing all Cliente objects
	 */
	@WebMethod(action = "fetchAllClientes")
	@WebResult(name = "fetchAllClientesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	ClienteResponse fetchAllClientes(@WebParam(name = "request") FetchAllRequest request);



//===================================### FORNECEDOR ####======================================

/**
	 * Rebuild a list of Fornecedors.
	 *
	 * @param request FornecedorRequest object containing parameter for building the list of Fornecedor objects.
	 *
	 * @return the FornecedorResponse containing the list of Fornecedors built
	 */
	@WebMethod(action = "insertFornecedors")
	@WebResult(name = "insertFornecedorsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FornecedorResponse insertFornecedor(@WebParam(name = "request") FornecedorMaintenanceRequest request);

	/**
	 * Rebuild a list of Fornecedors.
	 *
	 * @param request FornecedorRequest object containing parameter for building the list of Fornecedor objects.
	 *
	 * @return the FornecedorResponse containing the list of Fornecedors built
	 */
	@WebMethod(action = "updateFornecedors")
	@WebResult(name = "updateFornecedorsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FornecedorResponse updateFornecedor(@WebParam(name = "request") FornecedorMaintenanceRequest request);

	/**
	 * Rebuild a list of Fornecedors.
	 *
	 * @param request FornecedorRequest object containing parameter for building the list of Fornecedor objects.
	 *
	 * @return the FornecedorResponse containing the list of Fornecedors built
	 */
	@WebMethod(action = "deleteFornecedors")
	@WebResult(name = "deleteFornecedorsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FornecedorResponse deleteFornecedor(@WebParam(name = "request") FornecedorMaintenanceRequest request);

	/**
	 * Rebuild a list of Fornecedors.
	 *
	 * @param request FornecedorRequest object containing parameter for building the list of Fornecedor objects.
	 *
	 * @return the FornecedorResponse containing the list of Fornecedors built
	 */
	@WebMethod(action = "fetchFornecedorById")
	@WebResult(name = "fetchFornecedorByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FornecedorResponse fetchFornecedorById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Fornecedors.
	 *
	 * @param request FornecedorRequest object containing parameter for building the list of Fornecedor objects.
	 *
	 * @return the FornecedorResponse containing the list of Fornecedors built
	 */
	@WebMethod(action = "fetchFornecedorsByRequest")
	@WebResult(name = "fetchFornecedorsByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FornecedorResponse fetchFornecedorsByRequest(@WebParam(name = "request") FornecedorInquiryRequest request);


	/**
	 * Rebuild a list of Fornecedors.
	 *
	 * @param request FornecedorRequest object containing parameter for building the list of Fornecedor objects.
	 *
	 * @return the FornecedorResponse containing the list of Fornecedors built
	 */
	@WebMethod(action = "refreshFornecedors")
	@WebResult(name = "refreshFornecedorsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FornecedorResponse refreshFornecedors(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Fornecedors.
	 *
	 * @param request the request
	 *
	 * @return the FornecedorResponse containing all Fornecedor objects
	 */
	@WebMethod(action = "fetchAllFornecedors")
	@WebResult(name = "fetchAllFornecedorsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	FornecedorResponse fetchAllFornecedors(@WebParam(name = "request") FetchAllRequest request);



//===================================### TRANSPORTADOR ####======================================

/**
	 * Rebuild a list of Transportadors.
	 *
	 * @param request TransportadorRequest object containing parameter for building the list of Transportador objects.
	 *
	 * @return the TransportadorResponse containing the list of Transportadors built
	 */
	@WebMethod(action = "insertTransportadors")
	@WebResult(name = "insertTransportadorsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	TransportadorResponse insertTransportador(@WebParam(name = "request") TransportadorMaintenanceRequest request);

	/**
	 * Rebuild a list of Transportadors.
	 *
	 * @param request TransportadorRequest object containing parameter for building the list of Transportador objects.
	 *
	 * @return the TransportadorResponse containing the list of Transportadors built
	 */
	@WebMethod(action = "updateTransportadors")
	@WebResult(name = "updateTransportadorsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	TransportadorResponse updateTransportador(@WebParam(name = "request") TransportadorMaintenanceRequest request);

	/**
	 * Rebuild a list of Transportadors.
	 *
	 * @param request TransportadorRequest object containing parameter for building the list of Transportador objects.
	 *
	 * @return the TransportadorResponse containing the list of Transportadors built
	 */
	@WebMethod(action = "deleteTransportadors")
	@WebResult(name = "deleteTransportadorsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	TransportadorResponse deleteTransportador(@WebParam(name = "request") TransportadorMaintenanceRequest request);

	/**
	 * Rebuild a list of Transportadors.
	 *
	 * @param request TransportadorRequest object containing parameter for building the list of Transportador objects.
	 *
	 * @return the TransportadorResponse containing the list of Transportadors built
	 */
	@WebMethod(action = "fetchTransportadorById")
	@WebResult(name = "fetchTransportadorByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	TransportadorResponse fetchTransportadorById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Transportadors.
	 *
	 * @param request TransportadorRequest object containing parameter for building the list of Transportador objects.
	 *
	 * @return the TransportadorResponse containing the list of Transportadors built
	 */
	@WebMethod(action = "fetchTransportadorsByRequest")
	@WebResult(name = "fetchTransportadorsByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	TransportadorResponse fetchTransportadorsByRequest(@WebParam(name = "request") TransportadorInquiryRequest request);


	/**
	 * Rebuild a list of Transportadors.
	 *
	 * @param request TransportadorRequest object containing parameter for building the list of Transportador objects.
	 *
	 * @return the TransportadorResponse containing the list of Transportadors built
	 */
	@WebMethod(action = "refreshTransportadors")
	@WebResult(name = "refreshTransportadorsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	TransportadorResponse refreshTransportadors(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Transportadors.
	 *
	 * @param request the request
	 *
	 * @return the TransportadorResponse containing all Transportador objects
	 */
	@WebMethod(action = "fetchAllTransportadors")
	@WebResult(name = "fetchAllTransportadorsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	TransportadorResponse fetchAllTransportadors(@WebParam(name = "request") FetchAllRequest request);



//===================================### CONVENIO ####======================================

/**
	 * Rebuild a list of Convenios.
	 *
	 * @param request ConvenioRequest object containing parameter for building the list of Convenio objects.
	 *
	 * @return the ConvenioResponse containing the list of Convenios built
	 */
	@WebMethod(action = "insertConvenios")
	@WebResult(name = "insertConveniosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ConvenioResponse insertConvenio(@WebParam(name = "request") ConvenioMaintenanceRequest request);

	/**
	 * Rebuild a list of Convenios.
	 *
	 * @param request ConvenioRequest object containing parameter for building the list of Convenio objects.
	 *
	 * @return the ConvenioResponse containing the list of Convenios built
	 */
	@WebMethod(action = "updateConvenios")
	@WebResult(name = "updateConveniosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ConvenioResponse updateConvenio(@WebParam(name = "request") ConvenioMaintenanceRequest request);

	/**
	 * Rebuild a list of Convenios.
	 *
	 * @param request ConvenioRequest object containing parameter for building the list of Convenio objects.
	 *
	 * @return the ConvenioResponse containing the list of Convenios built
	 */
	@WebMethod(action = "deleteConvenios")
	@WebResult(name = "deleteConveniosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ConvenioResponse deleteConvenio(@WebParam(name = "request") ConvenioMaintenanceRequest request);

	/**
	 * Rebuild a list of Convenios.
	 *
	 * @param request ConvenioRequest object containing parameter for building the list of Convenio objects.
	 *
	 * @return the ConvenioResponse containing the list of Convenios built
	 */
	@WebMethod(action = "fetchConvenioById")
	@WebResult(name = "fetchConvenioByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ConvenioResponse fetchConvenioById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Convenios.
	 *
	 * @param request ConvenioRequest object containing parameter for building the list of Convenio objects.
	 *
	 * @return the ConvenioResponse containing the list of Convenios built
	 */
	@WebMethod(action = "fetchConveniosByRequest")
	@WebResult(name = "fetchConveniosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ConvenioResponse fetchConveniosByRequest(@WebParam(name = "request") ConvenioInquiryRequest request);


	/**
	 * Rebuild a list of Convenios.
	 *
	 * @param request ConvenioRequest object containing parameter for building the list of Convenio objects.
	 *
	 * @return the ConvenioResponse containing the list of Convenios built
	 */
	@WebMethod(action = "refreshConvenios")
	@WebResult(name = "refreshConveniosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ConvenioResponse refreshConvenios(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Convenios.
	 *
	 * @param request the request
	 *
	 * @return the ConvenioResponse containing all Convenio objects
	 */
	@WebMethod(action = "fetchAllConvenios")
	@WebResult(name = "fetchAllConveniosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	ConvenioResponse fetchAllConvenios(@WebParam(name = "request") FetchAllRequest request);



//===================================### CIDADE ####======================================

/**
	 * Rebuild a list of Cidades.
	 *
	 * @param request CidadeRequest object containing parameter for building the list of Cidade objects.
	 *
	 * @return the CidadeResponse containing the list of Cidades built
	 */
	@WebMethod(action = "insertCidades")
	@WebResult(name = "insertCidadesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CidadeResponse insertCidade(@WebParam(name = "request") CidadeMaintenanceRequest request);

	/**
	 * Rebuild a list of Cidades.
	 *
	 * @param request CidadeRequest object containing parameter for building the list of Cidade objects.
	 *
	 * @return the CidadeResponse containing the list of Cidades built
	 */
	@WebMethod(action = "updateCidades")
	@WebResult(name = "updateCidadesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CidadeResponse updateCidade(@WebParam(name = "request") CidadeMaintenanceRequest request);

	/**
	 * Rebuild a list of Cidades.
	 *
	 * @param request CidadeRequest object containing parameter for building the list of Cidade objects.
	 *
	 * @return the CidadeResponse containing the list of Cidades built
	 */
	@WebMethod(action = "deleteCidades")
	@WebResult(name = "deleteCidadesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CidadeResponse deleteCidade(@WebParam(name = "request") CidadeMaintenanceRequest request);

	/**
	 * Rebuild a list of Cidades.
	 *
	 * @param request CidadeRequest object containing parameter for building the list of Cidade objects.
	 *
	 * @return the CidadeResponse containing the list of Cidades built
	 */
	@WebMethod(action = "fetchCidadeById")
	@WebResult(name = "fetchCidadeByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CidadeResponse fetchCidadeById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Cidades.
	 *
	 * @param request CidadeRequest object containing parameter for building the list of Cidade objects.
	 *
	 * @return the CidadeResponse containing the list of Cidades built
	 */
	@WebMethod(action = "fetchCidadesByRequest")
	@WebResult(name = "fetchCidadesByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CidadeResponse fetchCidadesByRequest(@WebParam(name = "request") CidadeInquiryRequest request);


	/**
	 * Rebuild a list of Cidades.
	 *
	 * @param request CidadeRequest object containing parameter for building the list of Cidade objects.
	 *
	 * @return the CidadeResponse containing the list of Cidades built
	 */
	@WebMethod(action = "refreshCidades")
	@WebResult(name = "refreshCidadesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CidadeResponse refreshCidades(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Cidades.
	 *
	 * @param request the request
	 *
	 * @return the CidadeResponse containing all Cidade objects
	 */
	@WebMethod(action = "fetchAllCidades")
	@WebResult(name = "fetchAllCidadesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	CidadeResponse fetchAllCidades(@WebParam(name = "request") FetchAllRequest request);



//===================================### ESTADO ####======================================

/**
	 * Rebuild a list of Estados.
	 *
	 * @param request EstadoRequest object containing parameter for building the list of Estado objects.
	 *
	 * @return the EstadoResponse containing the list of Estados built
	 */
	@WebMethod(action = "insertEstados")
	@WebResult(name = "insertEstadosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EstadoResponse insertEstado(@WebParam(name = "request") EstadoMaintenanceRequest request);

	/**
	 * Rebuild a list of Estados.
	 *
	 * @param request EstadoRequest object containing parameter for building the list of Estado objects.
	 *
	 * @return the EstadoResponse containing the list of Estados built
	 */
	@WebMethod(action = "updateEstados")
	@WebResult(name = "updateEstadosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EstadoResponse updateEstado(@WebParam(name = "request") EstadoMaintenanceRequest request);

	/**
	 * Rebuild a list of Estados.
	 *
	 * @param request EstadoRequest object containing parameter for building the list of Estado objects.
	 *
	 * @return the EstadoResponse containing the list of Estados built
	 */
	@WebMethod(action = "deleteEstados")
	@WebResult(name = "deleteEstadosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EstadoResponse deleteEstado(@WebParam(name = "request") EstadoMaintenanceRequest request);

	/**
	 * Rebuild a list of Estados.
	 *
	 * @param request EstadoRequest object containing parameter for building the list of Estado objects.
	 *
	 * @return the EstadoResponse containing the list of Estados built
	 */
	@WebMethod(action = "fetchEstadoById")
	@WebResult(name = "fetchEstadoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EstadoResponse fetchEstadoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Estados.
	 *
	 * @param request EstadoRequest object containing parameter for building the list of Estado objects.
	 *
	 * @return the EstadoResponse containing the list of Estados built
	 */
	@WebMethod(action = "fetchEstadosByRequest")
	@WebResult(name = "fetchEstadosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EstadoResponse fetchEstadosByRequest(@WebParam(name = "request") EstadoInquiryRequest request);


	/**
	 * Rebuild a list of Estados.
	 *
	 * @param request EstadoRequest object containing parameter for building the list of Estado objects.
	 *
	 * @return the EstadoResponse containing the list of Estados built
	 */
	@WebMethod(action = "refreshEstados")
	@WebResult(name = "refreshEstadosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EstadoResponse refreshEstados(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Estados.
	 *
	 * @param request the request
	 *
	 * @return the EstadoResponse containing all Estado objects
	 */
	@WebMethod(action = "fetchAllEstados")
	@WebResult(name = "fetchAllEstadosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	EstadoResponse fetchAllEstados(@WebParam(name = "request") FetchAllRequest request);



//===================================### TAREFA ####======================================

/**
	 * Rebuild a list of Tarefas.
	 *
	 * @param request TarefaRequest object containing parameter for building the list of Tarefa objects.
	 *
	 * @return the TarefaResponse containing the list of Tarefas built
	 */
	@WebMethod(action = "insertTarefas")
	@WebResult(name = "insertTarefasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	TarefaResponse insertTarefa(@WebParam(name = "request") TarefaMaintenanceRequest request);

	/**
	 * Rebuild a list of Tarefas.
	 *
	 * @param request TarefaRequest object containing parameter for building the list of Tarefa objects.
	 *
	 * @return the TarefaResponse containing the list of Tarefas built
	 */
	@WebMethod(action = "updateTarefas")
	@WebResult(name = "updateTarefasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	TarefaResponse updateTarefa(@WebParam(name = "request") TarefaMaintenanceRequest request);

	/**
	 * Rebuild a list of Tarefas.
	 *
	 * @param request TarefaRequest object containing parameter for building the list of Tarefa objects.
	 *
	 * @return the TarefaResponse containing the list of Tarefas built
	 */
	@WebMethod(action = "deleteTarefas")
	@WebResult(name = "deleteTarefasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	TarefaResponse deleteTarefa(@WebParam(name = "request") TarefaMaintenanceRequest request);

	/**
	 * Rebuild a list of Tarefas.
	 *
	 * @param request TarefaRequest object containing parameter for building the list of Tarefa objects.
	 *
	 * @return the TarefaResponse containing the list of Tarefas built
	 */
	@WebMethod(action = "fetchTarefaById")
	@WebResult(name = "fetchTarefaByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	TarefaResponse fetchTarefaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Tarefas.
	 *
	 * @param request TarefaRequest object containing parameter for building the list of Tarefa objects.
	 *
	 * @return the TarefaResponse containing the list of Tarefas built
	 */
	@WebMethod(action = "fetchTarefasByRequest")
	@WebResult(name = "fetchTarefasByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	TarefaResponse fetchTarefasByRequest(@WebParam(name = "request") TarefaInquiryRequest request);


	/**
	 * Rebuild a list of Tarefas.
	 *
	 * @param request TarefaRequest object containing parameter for building the list of Tarefa objects.
	 *
	 * @return the TarefaResponse containing the list of Tarefas built
	 */
	@WebMethod(action = "refreshTarefas")
	@WebResult(name = "refreshTarefasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	TarefaResponse refreshTarefas(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Tarefas.
	 *
	 * @param request the request
	 *
	 * @return the TarefaResponse containing all Tarefa objects
	 */
	@WebMethod(action = "fetchAllTarefas")
	@WebResult(name = "fetchAllTarefasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	TarefaResponse fetchAllTarefas(@WebParam(name = "request") FetchAllRequest request);


}
