/** create by system gera-java version 1.0.0 27/04/2016*/


package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.DpMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.DpResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.DpInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IDpWS.
 */
@WebService(serviceName = "DpService", targetNamespace = "http://qat.com/sysmgmt", portName = "DpServicePort")
public interface IDpWS
{

//===================================### FUNCIONARIO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertFuncionario")
	@WebResult(name = "insertFuncionarioReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public FuncionarioResponse insertFuncionario(@WebParam(name = "request") FuncionarioMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateFuncionario")
	@WebResult(name = "updateFuncionarioReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public FuncionarioResponse updateFuncionario(@WebParam(name = "request") FuncionarioMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteFuncionario")
	@WebResult(name = "deleteFuncionarioReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public FuncionarioResponse deleteFuncionario(@WebParam(name = "request") FuncionarioMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshFuncionarios")
	@WebResult(name = "refreshFuncionariosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public FuncionarioResponse refreshFuncionarios(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllFuncionarios")
	@WebResult(name = "fetchAllFuncionariosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public FuncionarioResponse fetchAllFuncionarios(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchFuncionarioById")
	@WebResult(name = "fetchFuncionarioByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public FuncionarioResponse fetchFuncionarioById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchFuncionariosByRequest")
	@WebResult(name = "fetchFuncionariosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public FuncionarioResponse fetchFuncionariosByRequest(@WebParam(name = "request") FuncionarioInquiryRequest request);


//===================================### EVENTOS ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertEventos")
	@WebResult(name = "insertEventosReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public EventosResponse insertEventos(@WebParam(name = "request") EventosMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateEventos")
	@WebResult(name = "updateEventosReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public EventosResponse updateEventos(@WebParam(name = "request") EventosMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteEventos")
	@WebResult(name = "deleteEventosReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public EventosResponse deleteEventos(@WebParam(name = "request") EventosMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshEventoss")
	@WebResult(name = "refreshEventossReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public EventosResponse refreshEventoss(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllEventoss")
	@WebResult(name = "fetchAllEventossReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public EventosResponse fetchAllEventoss(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchEventosById")
	@WebResult(name = "fetchEventosByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public EventosResponse fetchEventosById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchEventossByRequest")
	@WebResult(name = "fetchEventossByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public EventosResponse fetchEventossByRequest(@WebParam(name = "request") EventosInquiryRequest request);


//===================================### BENEFICIOS ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertBeneficios")
	@WebResult(name = "insertBeneficiosReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public BeneficiosResponse insertBeneficios(@WebParam(name = "request") BeneficiosMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateBeneficios")
	@WebResult(name = "updateBeneficiosReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public BeneficiosResponse updateBeneficios(@WebParam(name = "request") BeneficiosMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteBeneficios")
	@WebResult(name = "deleteBeneficiosReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public BeneficiosResponse deleteBeneficios(@WebParam(name = "request") BeneficiosMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshBeneficioss")
	@WebResult(name = "refreshBeneficiossReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public BeneficiosResponse refreshBeneficioss(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllBeneficioss")
	@WebResult(name = "fetchAllBeneficiossReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public BeneficiosResponse fetchAllBeneficioss(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchBeneficiosById")
	@WebResult(name = "fetchBeneficiosByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public BeneficiosResponse fetchBeneficiosById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchBeneficiossByRequest")
	@WebResult(name = "fetchBeneficiossByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public BeneficiosResponse fetchBeneficiossByRequest(@WebParam(name = "request") BeneficiosInquiryRequest request);


//===================================### HORAFUNC ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertHoraFunc")
	@WebResult(name = "insertHoraFuncReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public HoraFuncResponse insertHoraFunc(@WebParam(name = "request") HoraFuncMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateHoraFunc")
	@WebResult(name = "updateHoraFuncReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public HoraFuncResponse updateHoraFunc(@WebParam(name = "request") HoraFuncMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteHoraFunc")
	@WebResult(name = "deleteHoraFuncReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public HoraFuncResponse deleteHoraFunc(@WebParam(name = "request") HoraFuncMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshHoraFuncs")
	@WebResult(name = "refreshHoraFuncsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public HoraFuncResponse refreshHoraFuncs(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllHoraFuncs")
	@WebResult(name = "fetchAllHoraFuncsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public HoraFuncResponse fetchAllHoraFuncs(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchHoraFuncById")
	@WebResult(name = "fetchHoraFuncByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public HoraFuncResponse fetchHoraFuncById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchHoraFuncsByRequest")
	@WebResult(name = "fetchHoraFuncsByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public HoraFuncResponse fetchHoraFuncsByRequest(@WebParam(name = "request") HoraFuncInquiryRequest request);

}

