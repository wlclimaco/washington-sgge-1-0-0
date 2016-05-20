/** create by system gera-java version 1.0.0 09/05/2016 16:51 : 47*/

package com.qat.samples.sysmgmt.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Historico.IHistoricoBAC;
import com.qat.samples.sysmgmt.historico.model.Historico;
import com.qat.samples.sysmgmt.historico.model.HistoricoItens;
import com.qat.samples.sysmgmt.historico.model.request.HistoricoInquiryRequest;
import com.qat.samples.sysmgmt.historico.model.request.HistoricoMaintenanceRequest;
import com.qat.samples.sysmgmt.historico.model.response.HistoricoResponse;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class HistoricoAPIController.
 */
@Controller
@RequestMapping("/historico/api")
public class HistoricoAPIController extends BaseController {
	// /** The Constant DEFAULT_EXCEPTION_MSG. */
	// private static final String DEFAULT_EXCEPTION_MSG =
	// "sysmgmt.base.historicocontrollerrest.defaultexception";
	//
	// /** The Constant LOG. */
	// private static final Logger LOG =
	// LoggerFactory.getLogger(HistoricoAPIController.class);
	//
	// /** The historico bac. */
	// private IHistoricoBAC historicoBAC; // injected by @Resource
	//
	// /**
	// * Gets the historico bac.
	// *
	// * @return the historico bac
	// */
	// public IHistoricoBAC getHistoricoBAC() {
	// return historicoBAC;
	// }
	//
	// /**
	// * Sets the historico bac.
	// *
	// * @param historicoBAC
	// * the new historico bac
	// */
	// @Resource
	// public void setHistoricoBAC(IHistoricoBAC historicoBAC) {
	// this.historicoBAC = historicoBAC;
	// }
	//
	// // ===================================### HISTORICO
	// // ####======================================
	// /**
	// * Refresh historicos.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the historico response
	// */
	// // @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// // @ResponseBody
	// // public HistoricoResponse refreshHistoricos(@RequestParam("refreshInt")
	// // Integer refreshInt,
	// // @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// // Boolean retPaged) {
	// // HistoricoResponse historicoResponse = new HistoricoResponse();
	// //
	// // try {
	// // RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// // retPaged);
	// // InternalResultsResponse<Historico> internalResponse =
	// // getHistoricoBAC().refreshHistoricos(request);
	// // ResponseHandler.handleOperationStatusAndMessages(historicoResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, historicoResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return historicoResponse;
	// //
	// // }
	//
	// /**
	// * Fetch historico paged.
	// *
	// * @param request
	// * the request
	// * @return the historico response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public HistoricoResponse fetchHistoricoPaged(@RequestBody
	// HistoricoInquiryRequest request) {
	// HistoricoResponse historicoResponse = new HistoricoResponse();
	// try {
	// InternalResultsResponse<Historico> internalResponse =
	// getHistoricoBAC().fetchHistoricosByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(historicoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, historicoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return historicoResponse;
	// }
	//
	// // /**
	// // * Insert historico.
	// // *
	// // * @param request
	// // * the request
	// // * @return the historico response
	// // */
	// // @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// // @ResponseBody
	// // public HistoricoResponse insertHistorico(@RequestBody
	// // HistoricoMaintenanceRequest request) {
	// // HistoricoResponse historicoResponse = new HistoricoResponse();
	// // try {
	// // InternalResultsResponse<Historico> internalResponse =
	// // getHistoricoBAC().insertHistorico(request);
	// // ResponseHandler.handleOperationStatusAndMessages(historicoResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, historicoResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return historicoResponse;
	// // }
	//
	// // /**
	// // * Update historico.
	// // *
	// // * @param request
	// // * the request
	// // * @return the historico response
	// // */
	// // @RequestMapping(value = "/update", method = RequestMethod.POST)
	// // @ResponseBody
	// // public HistoricoResponse updateHistorico(@RequestBody
	// // HistoricoMaintenanceRequest request) {
	// // HistoricoResponse historicoResponse = new HistoricoResponse();
	// // try {
	// // InternalResultsResponse<Historico> internalResponse =
	// // getHistoricoBAC().updateHistorico(request);
	// // ResponseHandler.handleOperationStatusAndMessages(historicoResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, historicoResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return historicoResponse;
	// // }
	// //
	// // /**
	// // * Delete historico.
	// // *
	// // * @param request
	// // * the request
	// // * @return the historico response
	// // */
	// // @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// // @ResponseBody
	// // public HistoricoResponse deleteHistorico(@RequestBody
	// // HistoricoMaintenanceRequest request) {
	// // HistoricoResponse historicoResponse = new HistoricoResponse();
	// //
	// // try {
	// // InternalResultsResponse<Historico> internalResponse =
	// // getHistoricoBAC().deleteHistorico(request);
	// // ResponseHandler.handleOperationStatusAndMessages(historicoResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, historicoResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return historicoResponse;
	// //
	// // }
	//
	// // // ===================================### HISTORICOITENS
	// // // ####======================================
	// // /**
	// // * Refresh historicoitenss.
	// // *
	// // * @param refreshInt
	// // * the refresh int
	// // * @param retList
	// // * the ret list
	// // * @param retPaged
	// // * the ret paged
	// // * @return the historicoitens response
	// // */
	// // @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// // @ResponseBody
	// // public HistoricoItensResponse
	// // refreshHistoricoItenss(@RequestParam("refreshInt") Integer refreshInt,
	// // @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// // Boolean retPaged) {
	// // HistoricoItensResponse historicoitensResponse = new
	// // HistoricoItensResponse();
	// //
	// // try {
	// // RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// // retPaged);
	// // InternalResultsResponse<HistoricoItens> internalResponse =
	// // getHistoricoBAC()
	// // .refreshHistoricoItenss(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(historicoitensResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, historicoitensResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return historicoitensResponse;
	// //
	// // }
	// //
	// // /**
	// // * Fetch historicoitens paged.
	// // *
	// // * @param request
	// // * the request
	// // * @return the historicoitens response
	// // */
	// // @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// // @ResponseBody
	// // public HistoricoItensResponse fetchHistoricoItensPaged(@RequestBody
	// // HistoricoItensInquiryRequest request) {
	// // HistoricoItensResponse historicoitensResponse = new
	// // HistoricoItensResponse();
	// // try {
	// // InternalResultsResponse<HistoricoItens> internalResponse =
	// // getHistoricoBAC()
	// // .fetchHistoricoItenssByRequest(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(historicoitensResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, historicoitensResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return historicoitensResponse;
	// // }
	// //
	// // /**
	// // * Insert historicoitens.
	// // *
	// // * @param request
	// // * the request
	// // * @return the historicoitens response
	// // */
	// // @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// // @ResponseBody
	// // public HistoricoItensResponse insertHistoricoItens(@RequestBody
	// // HistoricoItensMaintenanceRequest request) {
	// // HistoricoItensResponse historicoitensResponse = new
	// // HistoricoItensResponse();
	// // try {
	// // InternalResultsResponse<HistoricoItens> internalResponse =
	// // getHistoricoBAC().insertHistoricoItens(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(historicoitensResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, historicoitensResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return historicoitensResponse;
	// // }
	// //
	// // /**
	// // * Update historicoitens.
	// // *
	// // * @param request
	// // * the request
	// // * @return the historicoitens response
	// // */
	// // @RequestMapping(value = "/update", method = RequestMethod.POST)
	// // @ResponseBody
	// // public HistoricoItensResponse updateHistoricoItens(@RequestBody
	// // HistoricoItensMaintenanceRequest request) {
	// // HistoricoItensResponse historicoitensResponse = new
	// // HistoricoItensResponse();
	// // try {
	// // InternalResultsResponse<HistoricoItens> internalResponse =
	// // getHistoricoBAC().updateHistoricoItens(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(historicoitensResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, historicoitensResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return historicoitensResponse;
	// // }
	// //
	// // /**
	// // * Delete historicoitens.
	// // *
	// // * @param request
	// // * the request
	// // * @return the historicoitens response
	// // */
	// // @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// // @ResponseBody
	// // public HistoricoItensResponse deleteHistoricoItens(@RequestBody
	// // HistoricoItensMaintenanceRequest request) {
	// // HistoricoItensResponse historicoitensResponse = new
	// // HistoricoItensResponse();
	// //
	// // try {
	// // InternalResultsResponse<HistoricoItens> internalResponse =
	// // getHistoricoBAC().deleteHistoricoItens(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(historicoitensResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, historicoitensResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return historicoitensResponse;
	// //
	// // }

}
