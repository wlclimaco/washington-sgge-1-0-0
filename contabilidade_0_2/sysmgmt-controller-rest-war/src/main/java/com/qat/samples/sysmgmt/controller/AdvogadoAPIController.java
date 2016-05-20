/** create by system gera-java version 1.0.0 09/05/2016 16:45 : 52*/

package com.qat.samples.sysmgmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class AdvogadoAPIController.
 */
@Controller
@RequestMapping("/advocacia/api")
public class AdvogadoAPIController extends BaseController {
	// /** The Constant DEFAULT_EXCEPTION_MSG. */
	// private static final String DEFAULT_EXCEPTION_MSG =
	// "sysmgmt.base.advogadocontrollerrest.defaultexception";
	//
	// /** The Constant LOG. */
	// private static final Logger LOG =
	// LoggerFactory.getLogger(AdvogadoAPIController.class);
	//
	// /** The advogado bac. */
	// private IAdvocaciaBAC advogadoBAC; // injected by @Resource
	//
	// /**
	// * Gets the advogado bac.
	// *
	// * @return the advogado bac
	// */
	// public IAdvocaciaBAC getAdvogadoBAC() {
	// return advogadoBAC;
	// }
	//
	// /**
	// * Sets the advogado bac.
	// *
	// * @param advogadoBAC
	// * the new advogado bac
	// */
	// @Resource
	// public void setAdvogadoBAC(IAdvocaciaBAC advogadoBAC) {
	// this.advogadoBAC = advogadoBAC;
	// }
	//
	// // ===================================### AUDIENCIA
	// // ####======================================
	// /**
	// * Refresh audiencias.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the audiencia response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public AudienciaResponse refreshAudiencias(@RequestParam("refreshInt")
	// Integer refreshInt,
	// @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// Boolean retPaged) {
	// AudienciaResponse audienciaResponse = new AudienciaResponse();
	//
	// try {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<Audiencia> internalResponse =
	// getAdvogadoBAC().refreshAudiencias(request);
	// ResponseHandler.handleOperationStatusAndMessages(audienciaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, audienciaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return audienciaResponse;
	//
	// }
	//
	// /**
	// * Fetch audiencia paged.
	// *
	// * @param request
	// * the request
	// * @return the audiencia response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public AudienciaResponse fetchAudienciaPaged(@RequestBody
	// AudienciaInquiryRequest request) {
	// AudienciaResponse audienciaResponse = new AudienciaResponse();
	// try {
	// InternalResultsResponse<Audiencia> internalResponse =
	// getAdvogadoBAC().fetchAudienciasByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(audienciaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, audienciaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return audienciaResponse;
	// }
	//
	// /**
	// * Insert audiencia.
	// *
	// * @param request
	// * the request
	// * @return the audiencia response
	// */
	// @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// @ResponseBody
	// public AudienciaResponse insertAudiencia(@RequestBody
	// AudienciaMaintenanceRequest request) {
	// AudienciaResponse audienciaResponse = new AudienciaResponse();
	// try {
	// InternalResultsResponse<Audiencia> internalResponse =
	// getAdvogadoBAC().insertAudiencia(request);
	// ResponseHandler.handleOperationStatusAndMessages(audienciaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, audienciaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return audienciaResponse;
	// }
	//
	// /**
	// * Update audiencia.
	// *
	// * @param request
	// * the request
	// * @return the audiencia response
	// */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// @ResponseBody
	// public AudienciaResponse updateAudiencia(@RequestBody
	// AudienciaMaintenanceRequest request) {
	// AudienciaResponse audienciaResponse = new AudienciaResponse();
	// try {
	// InternalResultsResponse<Audiencia> internalResponse =
	// getAdvogadoBAC().updateAudiencia(request);
	// ResponseHandler.handleOperationStatusAndMessages(audienciaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, audienciaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return audienciaResponse;
	// }
	//
	// /**
	// * Delete audiencia.
	// *
	// * @param request
	// * the request
	// * @return the audiencia response
	// */
	// @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// @ResponseBody
	// public AudienciaResponse deleteAudiencia(@RequestBody
	// AudienciaMaintenanceRequest request) {
	// AudienciaResponse audienciaResponse = new AudienciaResponse();
	//
	// try {
	// InternalResultsResponse<Audiencia> internalResponse =
	// getAdvogadoBAC().deleteAudiencia(request);
	// ResponseHandler.handleOperationStatusAndMessages(audienciaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, audienciaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return audienciaResponse;
	//
	// }
	//
	// // ===================================### PROCESSO
	// // ####======================================
	// /**
	// * Refresh processos.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the processo response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public ProcessoResponse refreshProcessos(@RequestParam("refreshInt")
	// Integer refreshInt,
	// @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// Boolean retPaged) {
	// ProcessoResponse processoResponse = new ProcessoResponse();
	//
	// try {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<Processo> internalResponse =
	// getAdvogadoBAC().refreshProcessos(request);
	// ResponseHandler.handleOperationStatusAndMessages(processoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, processoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return processoResponse;
	//
	// }
	//
	// /**
	// * Fetch processo paged.
	// *
	// * @param request
	// * the request
	// * @return the processo response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public ProcessoResponse fetchProcessoPaged(@RequestBody
	// ProcessoInquiryRequest request) {
	// ProcessoResponse processoResponse = new ProcessoResponse();
	// try {
	// InternalResultsResponse<Processo> internalResponse =
	// getAdvogadoBAC().fetchProcessosByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(processoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, processoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return processoResponse;
	// }
	//
	// /**
	// * Insert processo.
	// *
	// * @param request
	// * the request
	// * @return the processo response
	// */
	// @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// @ResponseBody
	// public ProcessoResponse insertProcesso(@RequestBody
	// ProcessoMaintenanceRequest request) {
	// ProcessoResponse processoResponse = new ProcessoResponse();
	// try {
	// InternalResultsResponse<Processo> internalResponse =
	// getAdvogadoBAC().insertProcesso(request);
	// ResponseHandler.handleOperationStatusAndMessages(processoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, processoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return processoResponse;
	// }
	//
	// /**
	// * Update processo.
	// *
	// * @param request
	// * the request
	// * @return the processo response
	// */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// @ResponseBody
	// public ProcessoResponse updateProcesso(@RequestBody
	// ProcessoMaintenanceRequest request) {
	// ProcessoResponse processoResponse = new ProcessoResponse();
	// try {
	// InternalResultsResponse<Processo> internalResponse =
	// getAdvogadoBAC().updateProcesso(request);
	// ResponseHandler.handleOperationStatusAndMessages(processoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, processoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return processoResponse;
	// }
	//
	// /**
	// * Delete processo.
	// *
	// * @param request
	// * the request
	// * @return the processo response
	// */
	// @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// @ResponseBody
	// public ProcessoResponse deleteProcesso(@RequestBody
	// ProcessoMaintenanceRequest request) {
	// ProcessoResponse processoResponse = new ProcessoResponse();
	//
	// try {
	// InternalResultsResponse<Processo> internalResponse =
	// getAdvogadoBAC().deleteProcesso(request);
	// ResponseHandler.handleOperationStatusAndMessages(processoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, processoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return processoResponse;
	//
	// }

}
