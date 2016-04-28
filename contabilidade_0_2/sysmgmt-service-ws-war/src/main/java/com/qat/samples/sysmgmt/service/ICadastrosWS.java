/** create by system gera-java version 1.0.0 27/04/2016*/


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
 * The Interface ICadastrosWS.
 */
@WebService(serviceName = "CadastrosService", targetNamespace = "http://qat.com/sysmgmt", portName = "CadastrosServicePort")
public interface ICadastrosWS
{

//===================================### CLIENTE ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertCliente")
	@WebResult(name = "insertClienteReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public ClienteResponse insertCliente(@WebParam(name = "request") ClienteMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateCliente")
	@WebResult(name = "updateClienteReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public ClienteResponse updateCliente(@WebParam(name = "request") ClienteMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteCliente")
	@WebResult(name = "deleteClienteReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public ClienteResponse deleteCliente(@WebParam(name = "request") ClienteMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshClientes")
	@WebResult(name = "refreshClientesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public ClienteResponse refreshClientes(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllClientes")
	@WebResult(name = "fetchAllClientesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public ClienteResponse fetchAllClientes(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchClienteById")
	@WebResult(name = "fetchClienteByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public ClienteResponse fetchClienteById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchClientesByRequest")
	@WebResult(name = "fetchClientesByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public ClienteResponse fetchClientesByRequest(@WebParam(name = "request") ClienteInquiryRequest request);


//===================================### FORNECEDOR ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertFornecedor")
	@WebResult(name = "insertFornecedorReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public FornecedorResponse insertFornecedor(@WebParam(name = "request") FornecedorMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateFornecedor")
	@WebResult(name = "updateFornecedorReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public FornecedorResponse updateFornecedor(@WebParam(name = "request") FornecedorMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteFornecedor")
	@WebResult(name = "deleteFornecedorReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public FornecedorResponse deleteFornecedor(@WebParam(name = "request") FornecedorMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshFornecedors")
	@WebResult(name = "refreshFornecedorsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public FornecedorResponse refreshFornecedors(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllFornecedors")
	@WebResult(name = "fetchAllFornecedorsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public FornecedorResponse fetchAllFornecedors(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchFornecedorById")
	@WebResult(name = "fetchFornecedorByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public FornecedorResponse fetchFornecedorById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchFornecedorsByRequest")
	@WebResult(name = "fetchFornecedorsByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public FornecedorResponse fetchFornecedorsByRequest(@WebParam(name = "request") FornecedorInquiryRequest request);


//===================================### TRANSPORTADOR ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertTransportador")
	@WebResult(name = "insertTransportadorReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public TransportadorResponse insertTransportador(@WebParam(name = "request") TransportadorMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateTransportador")
	@WebResult(name = "updateTransportadorReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public TransportadorResponse updateTransportador(@WebParam(name = "request") TransportadorMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteTransportador")
	@WebResult(name = "deleteTransportadorReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public TransportadorResponse deleteTransportador(@WebParam(name = "request") TransportadorMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshTransportadors")
	@WebResult(name = "refreshTransportadorsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public TransportadorResponse refreshTransportadors(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllTransportadors")
	@WebResult(name = "fetchAllTransportadorsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public TransportadorResponse fetchAllTransportadors(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchTransportadorById")
	@WebResult(name = "fetchTransportadorByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public TransportadorResponse fetchTransportadorById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchTransportadorsByRequest")
	@WebResult(name = "fetchTransportadorsByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public TransportadorResponse fetchTransportadorsByRequest(@WebParam(name = "request") TransportadorInquiryRequest request);


//===================================### CONVENIO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertConvenio")
	@WebResult(name = "insertConvenioReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public ConvenioResponse insertConvenio(@WebParam(name = "request") ConvenioMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateConvenio")
	@WebResult(name = "updateConvenioReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public ConvenioResponse updateConvenio(@WebParam(name = "request") ConvenioMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteConvenio")
	@WebResult(name = "deleteConvenioReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public ConvenioResponse deleteConvenio(@WebParam(name = "request") ConvenioMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshConvenios")
	@WebResult(name = "refreshConveniosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public ConvenioResponse refreshConvenios(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllConvenios")
	@WebResult(name = "fetchAllConveniosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public ConvenioResponse fetchAllConvenios(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchConvenioById")
	@WebResult(name = "fetchConvenioByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public ConvenioResponse fetchConvenioById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchConveniosByRequest")
	@WebResult(name = "fetchConveniosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public ConvenioResponse fetchConveniosByRequest(@WebParam(name = "request") ConvenioInquiryRequest request);


//===================================### CIDADE ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertCidade")
	@WebResult(name = "insertCidadeReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public CidadeResponse insertCidade(@WebParam(name = "request") CidadeMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateCidade")
	@WebResult(name = "updateCidadeReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public CidadeResponse updateCidade(@WebParam(name = "request") CidadeMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteCidade")
	@WebResult(name = "deleteCidadeReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public CidadeResponse deleteCidade(@WebParam(name = "request") CidadeMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshCidades")
	@WebResult(name = "refreshCidadesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public CidadeResponse refreshCidades(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllCidades")
	@WebResult(name = "fetchAllCidadesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public CidadeResponse fetchAllCidades(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchCidadeById")
	@WebResult(name = "fetchCidadeByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public CidadeResponse fetchCidadeById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchCidadesByRequest")
	@WebResult(name = "fetchCidadesByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public CidadeResponse fetchCidadesByRequest(@WebParam(name = "request") CidadeInquiryRequest request);


//===================================### ESTADO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertEstado")
	@WebResult(name = "insertEstadoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public EstadoResponse insertEstado(@WebParam(name = "request") EstadoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateEstado")
	@WebResult(name = "updateEstadoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public EstadoResponse updateEstado(@WebParam(name = "request") EstadoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteEstado")
	@WebResult(name = "deleteEstadoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public EstadoResponse deleteEstado(@WebParam(name = "request") EstadoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshEstados")
	@WebResult(name = "refreshEstadosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public EstadoResponse refreshEstados(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllEstados")
	@WebResult(name = "fetchAllEstadosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public EstadoResponse fetchAllEstados(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchEstadoById")
	@WebResult(name = "fetchEstadoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public EstadoResponse fetchEstadoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchEstadosByRequest")
	@WebResult(name = "fetchEstadosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public EstadoResponse fetchEstadosByRequest(@WebParam(name = "request") EstadoInquiryRequest request);


//===================================### TAREFA ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertTarefa")
	@WebResult(name = "insertTarefaReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public TarefaResponse insertTarefa(@WebParam(name = "request") TarefaMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateTarefa")
	@WebResult(name = "updateTarefaReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public TarefaResponse updateTarefa(@WebParam(name = "request") TarefaMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteTarefa")
	@WebResult(name = "deleteTarefaReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public TarefaResponse deleteTarefa(@WebParam(name = "request") TarefaMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshTarefas")
	@WebResult(name = "refreshTarefasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public TarefaResponse refreshTarefas(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllTarefas")
	@WebResult(name = "fetchAllTarefasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public TarefaResponse fetchAllTarefas(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchTarefaById")
	@WebResult(name = "fetchTarefaByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public TarefaResponse fetchTarefaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchTarefasByRequest")
	@WebResult(name = "fetchTarefasByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public TarefaResponse fetchTarefasByRequest(@WebParam(name = "request") TarefaInquiryRequest request);

}

