/** create by system gera-java version 1.0.0 28/04/2016 14:59 : 50*/
package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosMaintenanceRequest;
import com.qat.samples.sysmgmt.beneficios.model.response.BeneficiosResponse;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.EventosMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.response.EventoResponse;
import com.qat.samples.sysmgmt.dp.model.response.FuncionarioResponse;
import com.qat.samples.sysmgmt.dp.model.response.HorarioFuncResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IDpBAS delegate used by a JMS listener. (Business Area Service - BAS)
 */
@WebService(serviceName = "DpService", targetNamespace = "http://qat.com/jms", portName = "DpServicePort")
public interface IDpWS
{

//===================================### FUNCIONARIO ####======================================

/**
	 * Rebuild a list of Funcionarios.
	 *
	 * @param request FuncionarioRequest object containing parameter for building the list of Funcionario objects.
	 *
	 * @return the FuncionarioResponse containing the list of Funcionarios built
	 */
	@WebMethod(action = "insertFuncionarios")
	@WebResult(name = "insertFuncionariosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FuncionarioResponse insertFuncionario(@WebParam(name = "request") FuncionarioMaintenanceRequest request);

	/**
	 * Rebuild a list of Funcionarios.
	 *
	 * @param request FuncionarioRequest object containing parameter for building the list of Funcionario objects.
	 *
	 * @return the FuncionarioResponse containing the list of Funcionarios built
	 */
	@WebMethod(action = "updateFuncionarios")
	@WebResult(name = "updateFuncionariosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FuncionarioResponse updateFuncionario(@WebParam(name = "request") FuncionarioMaintenanceRequest request);

	/**
	 * Rebuild a list of Funcionarios.
	 *
	 * @param request FuncionarioRequest object containing parameter for building the list of Funcionario objects.
	 *
	 * @return the FuncionarioResponse containing the list of Funcionarios built
	 */
	@WebMethod(action = "deleteFuncionarios")
	@WebResult(name = "deleteFuncionariosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FuncionarioResponse deleteFuncionario(@WebParam(name = "request") FuncionarioMaintenanceRequest request);

	/**
	 * Rebuild a list of Funcionarios.
	 *
	 * @param request FuncionarioRequest object containing parameter for building the list of Funcionario objects.
	 *
	 * @return the FuncionarioResponse containing the list of Funcionarios built
	 */
	@WebMethod(action = "fetchFuncionarioById")
	@WebResult(name = "fetchFuncionarioByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FuncionarioResponse fetchFuncionarioById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Funcionarios.
	 *
	 * @param request FuncionarioRequest object containing parameter for building the list of Funcionario objects.
	 *
	 * @return the FuncionarioResponse containing the list of Funcionarios built
	 */
	@WebMethod(action = "fetchFuncionariosByRequest")
	@WebResult(name = "fetchFuncionariosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FuncionarioResponse fetchFuncionariosByRequest(@WebParam(name = "request") FuncionarioInquiryRequest request);


	/**
	 * Rebuild a list of Funcionarios.
	 *
	 * @param request FuncionarioRequest object containing parameter for building the list of Funcionario objects.
	 *
	 * @return the FuncionarioResponse containing the list of Funcionarios built
	 */
	@WebMethod(action = "refreshFuncionarios")
	@WebResult(name = "refreshFuncionariosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FuncionarioResponse refreshFuncionarios(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Funcionarios.
	 *
	 * @param request the request
	 *
	 * @return the FuncionarioResponse containing all Funcionario objects
	 */
	@WebMethod(action = "fetchAllFuncionarios")
	@WebResult(name = "fetchAllFuncionariosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	FuncionarioResponse fetchAllFuncionarios(@WebParam(name = "request") FetchAllRequest request);



//===================================### EVENTOS ####======================================

/**
	 * Rebuild a list of Eventoss.
	 *
	 * @param request EventosRequest object containing parameter for building the list of Eventos objects.
	 *
	 * @return the EventosResponse containing the list of Eventoss built
	 */
	@WebMethod(action = "insertEventoss")
	@WebResult(name = "insertEventossReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EventoResponse insertEventos(@WebParam(name = "request") EventosMaintenanceRequest request);

	/**
	 * Rebuild a list of Eventoss.
	 *
	 * @param request EventosRequest object containing parameter for building the list of Eventos objects.
	 *
	 * @return the EventosResponse containing the list of Eventoss built
	 */
	@WebMethod(action = "updateEventoss")
	@WebResult(name = "updateEventossReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EventoResponse updateEventos(@WebParam(name = "request") EventosMaintenanceRequest request);

	/**
	 * Rebuild a list of Eventoss.
	 *
	 * @param request EventosRequest object containing parameter for building the list of Eventos objects.
	 *
	 * @return the EventosResponse containing the list of Eventoss built
	 */
	@WebMethod(action = "deleteEventoss")
	@WebResult(name = "deleteEventossReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EventoResponse deleteEventos(@WebParam(name = "request") EventosMaintenanceRequest request);

	/**
	 * Rebuild a list of Eventoss.
	 *
	 * @param request EventosRequest object containing parameter for building the list of Eventos objects.
	 *
	 * @return the EventosResponse containing the list of Eventoss built
	 */
	@WebMethod(action = "fetchEventosById")
	@WebResult(name = "fetchEventosByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EventoResponse fetchEventosById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Eventoss.
	 *
	 * @param request EventosRequest object containing parameter for building the list of Eventos objects.
	 *
	 * @return the EventosResponse containing the list of Eventoss built
	 */
	@WebMethod(action = "fetchEventossByRequest")
	@WebResult(name = "fetchEventossByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EventoResponse fetchEventossByRequest(@WebParam(name = "request") EventoInquiryRequest request);


	/**
	 * Rebuild a list of Eventoss.
	 *
	 * @param request EventosRequest object containing parameter for building the list of Eventos objects.
	 *
	 * @return the EventosResponse containing the list of Eventoss built
	 */
	@WebMethod(action = "refreshEventoss")
	@WebResult(name = "refreshEventossReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	EventoResponse refreshEventoss(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Eventoss.
	 *
	 * @param request the request
	 *
	 * @return the EventosResponse containing all Eventos objects
	 */
	@WebMethod(action = "fetchAllEventoss")
	@WebResult(name = "fetchAllEventossReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	EventoResponse fetchAllEventoss(@WebParam(name = "request") FetchAllRequest request);



//===================================### BENEFICIOS ####======================================

/**
	 * Rebuild a list of Beneficioss.
	 *
	 * @param request BeneficiosRequest object containing parameter for building the list of Beneficios objects.
	 *
	 * @return the BeneficiosResponse containing the list of Beneficioss built
	 */
	@WebMethod(action = "insertBeneficioss")
	@WebResult(name = "insertBeneficiossReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	BeneficiosResponse insertBeneficios(@WebParam(name = "request") BeneficiosMaintenanceRequest request);

	/**
	 * Rebuild a list of Beneficioss.
	 *
	 * @param request BeneficiosRequest object containing parameter for building the list of Beneficios objects.
	 *
	 * @return the BeneficiosResponse containing the list of Beneficioss built
	 */
	@WebMethod(action = "updateBeneficioss")
	@WebResult(name = "updateBeneficiossReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	BeneficiosResponse updateBeneficios(@WebParam(name = "request") BeneficiosMaintenanceRequest request);

	/**
	 * Rebuild a list of Beneficioss.
	 *
	 * @param request BeneficiosRequest object containing parameter for building the list of Beneficios objects.
	 *
	 * @return the BeneficiosResponse containing the list of Beneficioss built
	 */
	@WebMethod(action = "deleteBeneficioss")
	@WebResult(name = "deleteBeneficiossReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	BeneficiosResponse deleteBeneficios(@WebParam(name = "request") BeneficiosMaintenanceRequest request);

	/**
	 * Rebuild a list of Beneficioss.
	 *
	 * @param request BeneficiosRequest object containing parameter for building the list of Beneficios objects.
	 *
	 * @return the BeneficiosResponse containing the list of Beneficioss built
	 */
	@WebMethod(action = "fetchBeneficiosById")
	@WebResult(name = "fetchBeneficiosByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	BeneficiosResponse fetchBeneficiosById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Beneficioss.
	 *
	 * @param request BeneficiosRequest object containing parameter for building the list of Beneficios objects.
	 *
	 * @return the BeneficiosResponse containing the list of Beneficioss built
	 */
	@WebMethod(action = "fetchBeneficiossByRequest")
	@WebResult(name = "fetchBeneficiossByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	BeneficiosResponse fetchBeneficiossByRequest(@WebParam(name = "request") BeneficiosInquiryRequest request);


	/**
	 * Rebuild a list of Beneficioss.
	 *
	 * @param request BeneficiosRequest object containing parameter for building the list of Beneficios objects.
	 *
	 * @return the BeneficiosResponse containing the list of Beneficioss built
	 */
	@WebMethod(action = "refreshBeneficioss")
	@WebResult(name = "refreshBeneficiossReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	BeneficiosResponse refreshBeneficioss(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Beneficioss.
	 *
	 * @param request the request
	 *
	 * @return the BeneficiosResponse containing all Beneficios objects
	 */
	@WebMethod(action = "fetchAllBeneficioss")
	@WebResult(name = "fetchAllBeneficiossReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	BeneficiosResponse fetchAllBeneficioss(@WebParam(name = "request") FetchAllRequest request);



//===================================### HORAFUNC ####======================================

/**
	 * Rebuild a list of HoraFuncs.
	 *
	 * @param request HoraFuncRequest object containing parameter for building the list of HoraFunc objects.
	 *
	 * @return the HoraFuncResponse containing the list of HoraFuncs built
	 */
	@WebMethod(action = "insertHoraFuncs")
	@WebResult(name = "insertHoraFuncsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HorarioFuncResponse insertHoraFunc(@WebParam(name = "request") HoraFuncMaintenanceRequest request);

	/**
	 * Rebuild a list of HoraFuncs.
	 *
	 * @param request HoraFuncRequest object containing parameter for building the list of HoraFunc objects.
	 *
	 * @return the HoraFuncResponse containing the list of HoraFuncs built
	 */
	@WebMethod(action = "updateHoraFuncs")
	@WebResult(name = "updateHoraFuncsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HorarioFuncResponse updateHoraFunc(@WebParam(name = "request") HoraFuncMaintenanceRequest request);

	/**
	 * Rebuild a list of HoraFuncs.
	 *
	 * @param request HoraFuncRequest object containing parameter for building the list of HoraFunc objects.
	 *
	 * @return the HoraFuncResponse containing the list of HoraFuncs built
	 */
	@WebMethod(action = "deleteHoraFuncs")
	@WebResult(name = "deleteHoraFuncsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HorarioFuncResponse deleteHoraFunc(@WebParam(name = "request") HoraFuncMaintenanceRequest request);

	/**
	 * Rebuild a list of HoraFuncs.
	 *
	 * @param request HoraFuncRequest object containing parameter for building the list of HoraFunc objects.
	 *
	 * @return the HoraFuncResponse containing the list of HoraFuncs built
	 */
	@WebMethod(action = "fetchHoraFuncById")
	@WebResult(name = "fetchHoraFuncByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HorarioFuncResponse fetchHoraFuncById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of HoraFuncs.
	 *
	 * @param request HoraFuncRequest object containing parameter for building the list of HoraFunc objects.
	 *
	 * @return the HoraFuncResponse containing the list of HoraFuncs built
	 */
	@WebMethod(action = "fetchHoraFuncsByRequest")
	@WebResult(name = "fetchHoraFuncsByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HorarioFuncResponse fetchHoraFuncsByRequest(@WebParam(name = "request") HoraFuncInquiryRequest request);


	/**
	 * Rebuild a list of HoraFuncs.
	 *
	 * @param request HoraFuncRequest object containing parameter for building the list of HoraFunc objects.
	 *
	 * @return the HoraFuncResponse containing the list of HoraFuncs built
	 */
	@WebMethod(action = "refreshHoraFuncs")
	@WebResult(name = "refreshHoraFuncsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HorarioFuncResponse refreshHoraFuncs(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all HoraFuncs.
	 *
	 * @param request the request
	 *
	 * @return the HoraFuncResponse containing all HoraFunc objects
	 */
	@WebMethod(action = "fetchAllHoraFuncs")
	@WebResult(name = "fetchAllHoraFuncsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	HorarioFuncResponse fetchAllHoraFuncs(@WebParam(name = "request") FetchAllRequest request);


}
