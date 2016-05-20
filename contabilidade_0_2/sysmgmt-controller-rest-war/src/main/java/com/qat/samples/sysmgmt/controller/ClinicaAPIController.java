/** create by system gera-java version 1.0.0 09/05/2016 16:51 : 47*/

package com.qat.samples.sysmgmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class ClinicaAPIController.
 */
@Controller
@RequestMapping("/clinica/api")
public class ClinicaAPIController extends BaseController {
	// /** The Constant DEFAULT_EXCEPTION_MSG. */
	// private static final String DEFAULT_EXCEPTION_MSG =
	// "sysmgmt.base.clinicacontrollerrest.defaultexception";
	//
	// /** The Constant LOG. */
	// private static final Logger LOG =
	// LoggerFactory.getLogger(ClinicaAPIController.class);
	//
	// /** The clinica bac. */
	// private IClinicaBAC clinicaBAC; // injected by @Resource
	//
	// /**
	// * Gets the clinica bac.
	// *
	// * @return the clinica bac
	// */
	// public IClinicaBAC getClinicaBAC() {
	// return clinicaBAC;
	// }
	//
	// /**
	// * Sets the clinica bac.
	// *
	// * @param clinicaBAC
	// * the new clinica bac
	// */
	// @Resource
	// public void setClinicaBAC(IClinicaBAC clinicaBAC) {
	// this.clinicaBAC = clinicaBAC;
	// }
	//
	// // ===================================### CONSULTA
	// // ####======================================
	// /**
	// * Refresh consultas.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the consulta response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public ConsultaResponse refreshConsultas(@RequestParam("refreshInt")
	// Integer refreshInt,
	// @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// Boolean retPaged) {
	// ConsultaResponse consultaResponse = new ConsultaResponse();
	//
	// try {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<Consulta> internalResponse =
	// getClinicaBAC().refreshConsultas(request);
	// ResponseHandler.handleOperationStatusAndMessages(consultaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, consultaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return consultaResponse;
	//
	// }
	//
	// /**
	// * Fetch consulta paged.
	// *
	// * @param request
	// * the request
	// * @return the consulta response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public ConsultaResponse fetchConsultaPaged(@RequestBody
	// ConsultaInquiryRequest request) {
	// ConsultaResponse consultaResponse = new ConsultaResponse();
	// try {
	// InternalResultsResponse<Consulta> internalResponse =
	// getClinicaBAC().fetchConsultasByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(consultaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, consultaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return consultaResponse;
	// }
	//
	// /**
	// * Insert consulta.
	// *
	// * @param request
	// * the request
	// * @return the consulta response
	// */
	// @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// @ResponseBody
	// public ConsultaResponse insertConsulta(@RequestBody
	// ConsultaMaintenanceRequest request) {
	// ConsultaResponse consultaResponse = new ConsultaResponse();
	// try {
	// InternalResultsResponse<Consulta> internalResponse =
	// getClinicaBAC().insertConsulta(request);
	// ResponseHandler.handleOperationStatusAndMessages(consultaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, consultaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return consultaResponse;
	// }
	//
	// /**
	// * Update consulta.
	// *
	// * @param request
	// * the request
	// * @return the consulta response
	// */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// @ResponseBody
	// public ConsultaResponse updateConsulta(@RequestBody
	// ConsultaMaintenanceRequest request) {
	// ConsultaResponse consultaResponse = new ConsultaResponse();
	// try {
	// InternalResultsResponse<Consulta> internalResponse =
	// getClinicaBAC().updateConsulta(request);
	// ResponseHandler.handleOperationStatusAndMessages(consultaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, consultaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return consultaResponse;
	// }
	//
	// /**
	// * Delete consulta.
	// *
	// * @param request
	// * the request
	// * @return the consulta response
	// */
	// @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// @ResponseBody
	// public ConsultaResponse deleteConsulta(@RequestBody
	// ConsultaMaintenanceRequest request) {
	// ConsultaResponse consultaResponse = new ConsultaResponse();
	//
	// try {
	// InternalResultsResponse<Consulta> internalResponse =
	// getClinicaBAC().deleteConsulta(request);
	// ResponseHandler.handleOperationStatusAndMessages(consultaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, consultaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return consultaResponse;
	//
	// }
	//
	// // ===================================### EXAME
	// // ####======================================
	// /**
	// * Refresh exames.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the exame response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public ExameResponse refreshExames(@RequestParam("refreshInt") Integer
	// refreshInt,
	// @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// Boolean retPaged) {
	// ExameResponse exameResponse = new ExameResponse();
	//
	// try {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<Exame> internalResponse =
	// getClinicaBAC().refreshExames(request);
	// ResponseHandler.handleOperationStatusAndMessages(exameResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, exameResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return exameResponse;
	//
	// }
	//
	// /**
	// * Fetch exame paged.
	// *
	// * @param request
	// * the request
	// * @return the exame response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public ExameResponse fetchExamePaged(@RequestBody ExameInquiryRequest
	// request) {
	// ExameResponse exameResponse = new ExameResponse();
	// try {
	// InternalResultsResponse<Exame> internalResponse =
	// getClinicaBAC().fetchExamesByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(exameResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, exameResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return exameResponse;
	// }
	//
	// /**
	// * Insert exame.
	// *
	// * @param request
	// * the request
	// * @return the exame response
	// */
	// @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// @ResponseBody
	// public ExameResponse insertExame(@RequestBody ExameMaintenanceRequest
	// request) {
	// ExameResponse exameResponse = new ExameResponse();
	// try {
	// InternalResultsResponse<Exame> internalResponse =
	// getClinicaBAC().insertExame(request);
	// ResponseHandler.handleOperationStatusAndMessages(exameResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, exameResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return exameResponse;
	// }
	//
	// /**
	// * Update exame.
	// *
	// * @param request
	// * the request
	// * @return the exame response
	// */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// @ResponseBody
	// public ExameResponse updateExame(@RequestBody ExameMaintenanceRequest
	// request) {
	// ExameResponse exameResponse = new ExameResponse();
	// try {
	// InternalResultsResponse<Exame> internalResponse =
	// getClinicaBAC().updateExame(request);
	// ResponseHandler.handleOperationStatusAndMessages(exameResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, exameResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return exameResponse;
	// }
	//
	// /**
	// * Delete exame.
	// *
	// * @param request
	// * the request
	// * @return the exame response
	// */
	// @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// @ResponseBody
	// public ExameResponse deleteExame(@RequestBody ExameMaintenanceRequest
	// request) {
	// ExameResponse exameResponse = new ExameResponse();
	//
	// try {
	// InternalResultsResponse<Exame> internalResponse =
	// getClinicaBAC().deleteExame(request);
	// ResponseHandler.handleOperationStatusAndMessages(exameResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, exameResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return exameResponse;
	//
	// }

}
