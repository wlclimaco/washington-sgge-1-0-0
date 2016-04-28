/** create by system gera-java version 1.0.0 28/04/2016 14:59 : 50*/
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
 * The Interface IEmpresaBAS delegate used by a JMS listener. (Business Area Service - BAS)
 */
@WebService(serviceName = "EmpresaService", targetNamespace = "http://qat.com/jms", portName = "EmpresaServicePort")
public interface IEmpresaWS
{

//===================================### EMPRESA ####======================================

/**
	 * Rebuild a list of Empresas.
	 *
	 * @param request EmpresaRequest object containing parameter for building the list of Empresa objects.
	 *
	 * @return the EmpresaResponse containing the list of Empresas built
	 */
	@WebMethod(action = "insertEmpresas")
	@WebResult(name = "insertEmpresasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EmpresaResponse insertEmpresa(@WebParam(name = "request") EmpresaMaintenanceRequest request);

	/**
	 * Rebuild a list of Empresas.
	 *
	 * @param request EmpresaRequest object containing parameter for building the list of Empresa objects.
	 *
	 * @return the EmpresaResponse containing the list of Empresas built
	 */
	@WebMethod(action = "updateEmpresas")
	@WebResult(name = "updateEmpresasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EmpresaResponse updateEmpresa(@WebParam(name = "request") EmpresaMaintenanceRequest request);

	/**
	 * Rebuild a list of Empresas.
	 *
	 * @param request EmpresaRequest object containing parameter for building the list of Empresa objects.
	 *
	 * @return the EmpresaResponse containing the list of Empresas built
	 */
	@WebMethod(action = "deleteEmpresas")
	@WebResult(name = "deleteEmpresasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EmpresaResponse deleteEmpresa(@WebParam(name = "request") EmpresaMaintenanceRequest request);

	/**
	 * Rebuild a list of Empresas.
	 *
	 * @param request EmpresaRequest object containing parameter for building the list of Empresa objects.
	 *
	 * @return the EmpresaResponse containing the list of Empresas built
	 */
	@WebMethod(action = "fetchEmpresaById")
	@WebResult(name = "fetchEmpresaByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EmpresaResponse fetchEmpresaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Empresas.
	 *
	 * @param request EmpresaRequest object containing parameter for building the list of Empresa objects.
	 *
	 * @return the EmpresaResponse containing the list of Empresas built
	 */
	@WebMethod(action = "fetchEmpresasByRequest")
	@WebResult(name = "fetchEmpresasByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EmpresaResponse fetchEmpresasByRequest(@WebParam(name = "request") EmpresaInquiryRequest request);


	/**
	 * Rebuild a list of Empresas.
	 *
	 * @param request EmpresaRequest object containing parameter for building the list of Empresa objects.
	 *
	 * @return the EmpresaResponse containing the list of Empresas built
	 */
	@WebMethod(action = "refreshEmpresas")
	@WebResult(name = "refreshEmpresasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EmpresaResponse refreshEmpresas(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Empresas.
	 *
	 * @param request the request
	 *
	 * @return the EmpresaResponse containing all Empresa objects
	 */
	@WebMethod(action = "fetchAllEmpresas")
	@WebResult(name = "fetchAllEmpresasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	EmpresaResponse fetchAllEmpresas(@WebParam(name = "request") FetchAllRequest request);



//===================================### FILIAL ####======================================

/**
	 * Rebuild a list of Filials.
	 *
	 * @param request FilialRequest object containing parameter for building the list of Filial objects.
	 *
	 * @return the FilialResponse containing the list of Filials built
	 */
	@WebMethod(action = "insertFilials")
	@WebResult(name = "insertFilialsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FilialResponse insertFilial(@WebParam(name = "request") FilialMaintenanceRequest request);

	/**
	 * Rebuild a list of Filials.
	 *
	 * @param request FilialRequest object containing parameter for building the list of Filial objects.
	 *
	 * @return the FilialResponse containing the list of Filials built
	 */
	@WebMethod(action = "updateFilials")
	@WebResult(name = "updateFilialsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FilialResponse updateFilial(@WebParam(name = "request") FilialMaintenanceRequest request);

	/**
	 * Rebuild a list of Filials.
	 *
	 * @param request FilialRequest object containing parameter for building the list of Filial objects.
	 *
	 * @return the FilialResponse containing the list of Filials built
	 */
	@WebMethod(action = "deleteFilials")
	@WebResult(name = "deleteFilialsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FilialResponse deleteFilial(@WebParam(name = "request") FilialMaintenanceRequest request);

	/**
	 * Rebuild a list of Filials.
	 *
	 * @param request FilialRequest object containing parameter for building the list of Filial objects.
	 *
	 * @return the FilialResponse containing the list of Filials built
	 */
	@WebMethod(action = "fetchFilialById")
	@WebResult(name = "fetchFilialByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FilialResponse fetchFilialById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Filials.
	 *
	 * @param request FilialRequest object containing parameter for building the list of Filial objects.
	 *
	 * @return the FilialResponse containing the list of Filials built
	 */
	@WebMethod(action = "fetchFilialsByRequest")
	@WebResult(name = "fetchFilialsByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FilialResponse fetchFilialsByRequest(@WebParam(name = "request") FilialInquiryRequest request);


	/**
	 * Rebuild a list of Filials.
	 *
	 * @param request FilialRequest object containing parameter for building the list of Filial objects.
	 *
	 * @return the FilialResponse containing the list of Filials built
	 */
	@WebMethod(action = "refreshFilials")
	@WebResult(name = "refreshFilialsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FilialResponse refreshFilials(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Filials.
	 *
	 * @param request the request
	 *
	 * @return the FilialResponse containing all Filial objects
	 */
	@WebMethod(action = "fetchAllFilials")
	@WebResult(name = "fetchAllFilialsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	FilialResponse fetchAllFilials(@WebParam(name = "request") FetchAllRequest request);



//===================================### DEPOSITO ####======================================

/**
	 * Rebuild a list of Depositos.
	 *
	 * @param request DepositoRequest object containing parameter for building the list of Deposito objects.
	 *
	 * @return the DepositoResponse containing the list of Depositos built
	 */
	@WebMethod(action = "insertDepositos")
	@WebResult(name = "insertDepositosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	DepositoResponse insertDeposito(@WebParam(name = "request") DepositoMaintenanceRequest request);

	/**
	 * Rebuild a list of Depositos.
	 *
	 * @param request DepositoRequest object containing parameter for building the list of Deposito objects.
	 *
	 * @return the DepositoResponse containing the list of Depositos built
	 */
	@WebMethod(action = "updateDepositos")
	@WebResult(name = "updateDepositosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	DepositoResponse updateDeposito(@WebParam(name = "request") DepositoMaintenanceRequest request);

	/**
	 * Rebuild a list of Depositos.
	 *
	 * @param request DepositoRequest object containing parameter for building the list of Deposito objects.
	 *
	 * @return the DepositoResponse containing the list of Depositos built
	 */
	@WebMethod(action = "deleteDepositos")
	@WebResult(name = "deleteDepositosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	DepositoResponse deleteDeposito(@WebParam(name = "request") DepositoMaintenanceRequest request);

	/**
	 * Rebuild a list of Depositos.
	 *
	 * @param request DepositoRequest object containing parameter for building the list of Deposito objects.
	 *
	 * @return the DepositoResponse containing the list of Depositos built
	 */
	@WebMethod(action = "fetchDepositoById")
	@WebResult(name = "fetchDepositoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	DepositoResponse fetchDepositoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Depositos.
	 *
	 * @param request DepositoRequest object containing parameter for building the list of Deposito objects.
	 *
	 * @return the DepositoResponse containing the list of Depositos built
	 */
	@WebMethod(action = "fetchDepositosByRequest")
	@WebResult(name = "fetchDepositosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	DepositoResponse fetchDepositosByRequest(@WebParam(name = "request") DepositoInquiryRequest request);


	/**
	 * Rebuild a list of Depositos.
	 *
	 * @param request DepositoRequest object containing parameter for building the list of Deposito objects.
	 *
	 * @return the DepositoResponse containing the list of Depositos built
	 */
	@WebMethod(action = "refreshDepositos")
	@WebResult(name = "refreshDepositosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	DepositoResponse refreshDepositos(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Depositos.
	 *
	 * @param request the request
	 *
	 * @return the DepositoResponse containing all Deposito objects
	 */
	@WebMethod(action = "fetchAllDepositos")
	@WebResult(name = "fetchAllDepositosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	DepositoResponse fetchAllDepositos(@WebParam(name = "request") FetchAllRequest request);



//===================================### USUARIO ####======================================

/**
	 * Rebuild a list of Usuarios.
	 *
	 * @param request UsuarioRequest object containing parameter for building the list of Usuario objects.
	 *
	 * @return the UsuarioResponse containing the list of Usuarios built
	 */
	@WebMethod(action = "insertUsuarios")
	@WebResult(name = "insertUsuariosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	UsuarioResponse insertUsuario(@WebParam(name = "request") UsuarioMaintenanceRequest request);

	/**
	 * Rebuild a list of Usuarios.
	 *
	 * @param request UsuarioRequest object containing parameter for building the list of Usuario objects.
	 *
	 * @return the UsuarioResponse containing the list of Usuarios built
	 */
	@WebMethod(action = "updateUsuarios")
	@WebResult(name = "updateUsuariosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	UsuarioResponse updateUsuario(@WebParam(name = "request") UsuarioMaintenanceRequest request);

	/**
	 * Rebuild a list of Usuarios.
	 *
	 * @param request UsuarioRequest object containing parameter for building the list of Usuario objects.
	 *
	 * @return the UsuarioResponse containing the list of Usuarios built
	 */
	@WebMethod(action = "deleteUsuarios")
	@WebResult(name = "deleteUsuariosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	UsuarioResponse deleteUsuario(@WebParam(name = "request") UsuarioMaintenanceRequest request);

	/**
	 * Rebuild a list of Usuarios.
	 *
	 * @param request UsuarioRequest object containing parameter for building the list of Usuario objects.
	 *
	 * @return the UsuarioResponse containing the list of Usuarios built
	 */
	@WebMethod(action = "fetchUsuarioById")
	@WebResult(name = "fetchUsuarioByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	UsuarioResponse fetchUsuarioById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Usuarios.
	 *
	 * @param request UsuarioRequest object containing parameter for building the list of Usuario objects.
	 *
	 * @return the UsuarioResponse containing the list of Usuarios built
	 */
	@WebMethod(action = "fetchUsuariosByRequest")
	@WebResult(name = "fetchUsuariosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	UsuarioResponse fetchUsuariosByRequest(@WebParam(name = "request") UsuarioInquiryRequest request);


	/**
	 * Rebuild a list of Usuarios.
	 *
	 * @param request UsuarioRequest object containing parameter for building the list of Usuario objects.
	 *
	 * @return the UsuarioResponse containing the list of Usuarios built
	 */
	@WebMethod(action = "refreshUsuarios")
	@WebResult(name = "refreshUsuariosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	UsuarioResponse refreshUsuarios(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Usuarios.
	 *
	 * @param request the request
	 *
	 * @return the UsuarioResponse containing all Usuario objects
	 */
	@WebMethod(action = "fetchAllUsuarios")
	@WebResult(name = "fetchAllUsuariosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	UsuarioResponse fetchAllUsuarios(@WebParam(name = "request") FetchAllRequest request);


}
