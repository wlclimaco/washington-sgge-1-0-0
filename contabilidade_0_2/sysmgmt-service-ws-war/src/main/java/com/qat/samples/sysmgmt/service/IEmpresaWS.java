/** create by system gera-java version 1.0.0 27/04/2016*/


package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.response.DepositoResponse;
import com.qat.samples.sysmgmt.entidade.model.response.EmpresaResponse;
import com.qat.samples.sysmgmt.entidade.model.response.FilialResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.response.UsuarioResponse;

/**
 * The Interface IEmpresaWS.
 */
@WebService(serviceName = "EmpresaService", targetNamespace = "http://qat.com/sysmgmt", portName = "EmpresaServicePort")
public interface IEmpresaWS
{

//===================================### EMPRESA ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertEmpresa")
	@WebResult(name = "insertEmpresaReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public EmpresaResponse insertEmpresa(@WebParam(name = "request") EmpresaMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateEmpresa")
	@WebResult(name = "updateEmpresaReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public EmpresaResponse updateEmpresa(@WebParam(name = "request") EmpresaMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteEmpresa")
	@WebResult(name = "deleteEmpresaReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public EmpresaResponse deleteEmpresa(@WebParam(name = "request") EmpresaMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshEmpresas")
	@WebResult(name = "refreshEmpresasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public EmpresaResponse refreshEmpresas(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllEmpresas")
	@WebResult(name = "fetchAllEmpresasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public EmpresaResponse fetchAllEmpresas(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchEmpresaById")
	@WebResult(name = "fetchEmpresaByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public EmpresaResponse fetchEmpresaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchEmpresasByRequest")
	@WebResult(name = "fetchEmpresasByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public EmpresaResponse fetchEmpresasByRequest(@WebParam(name = "request") EmpresaInquiryRequest request);


//===================================### FILIAL ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertFilial")
	@WebResult(name = "insertFilialReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public FilialResponse insertFilial(@WebParam(name = "request") FilialMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateFilial")
	@WebResult(name = "updateFilialReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public FilialResponse updateFilial(@WebParam(name = "request") FilialMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteFilial")
	@WebResult(name = "deleteFilialReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public FilialResponse deleteFilial(@WebParam(name = "request") FilialMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshFilials")
	@WebResult(name = "refreshFilialsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public FilialResponse refreshFilials(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllFilials")
	@WebResult(name = "fetchAllFilialsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public FilialResponse fetchAllFilials(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchFilialById")
	@WebResult(name = "fetchFilialByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public FilialResponse fetchFilialById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchFilialsByRequest")
	@WebResult(name = "fetchFilialsByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public FilialResponse fetchFilialsByRequest(@WebParam(name = "request") FilialInquiryRequest request);


//===================================### DEPOSITO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertDeposito")
	@WebResult(name = "insertDepositoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public DepositoResponse insertDeposito(@WebParam(name = "request") DepositoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateDeposito")
	@WebResult(name = "updateDepositoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public DepositoResponse updateDeposito(@WebParam(name = "request") DepositoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteDeposito")
	@WebResult(name = "deleteDepositoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public DepositoResponse deleteDeposito(@WebParam(name = "request") DepositoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshDepositos")
	@WebResult(name = "refreshDepositosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public DepositoResponse refreshDepositos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllDepositos")
	@WebResult(name = "fetchAllDepositosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public DepositoResponse fetchAllDepositos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchDepositoById")
	@WebResult(name = "fetchDepositoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public DepositoResponse fetchDepositoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchDepositosByRequest")
	@WebResult(name = "fetchDepositosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public DepositoResponse fetchDepositosByRequest(@WebParam(name = "request") DepositoInquiryRequest request);


//===================================### USUARIO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertUsuario")
	@WebResult(name = "insertUsuarioReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public UsuarioResponse insertUsuario(@WebParam(name = "request") UsuarioMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateUsuario")
	@WebResult(name = "updateUsuarioReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public UsuarioResponse updateUsuario(@WebParam(name = "request") UsuarioMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteUsuario")
	@WebResult(name = "deleteUsuarioReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public UsuarioResponse deleteUsuario(@WebParam(name = "request") UsuarioMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshUsuarios")
	@WebResult(name = "refreshUsuariosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public UsuarioResponse refreshUsuarios(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllUsuarios")
	@WebResult(name = "fetchAllUsuariosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public UsuarioResponse fetchAllUsuarios(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchUsuarioById")
	@WebResult(name = "fetchUsuarioByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public UsuarioResponse fetchUsuarioById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchUsuariosByRequest")
	@WebResult(name = "fetchUsuariosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public UsuarioResponse fetchUsuariosByRequest(@WebParam(name = "request") UsuarioInquiryRequest request);

}

