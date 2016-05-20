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
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServicoItens;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServicoStatus;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.ordemServico.model.response.OrdemServicoResponse;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class OrdemServicoAPIController.
 */
@Controller
@RequestMapping("/ordemservico/api")
public class OrdemServicoAPIController extends BaseController {
	// /** The Constant DEFAULT_EXCEPTION_MSG. */
	// private static final String DEFAULT_EXCEPTION_MSG =
	// "sysmgmt.base.ordemservicocontrollerrest.defaultexception";
	//
	// /** The Constant LOG. */
	// private static final Logger LOG =
	// LoggerFactory.getLogger(OrdemServicoAPIController.class);
	//
	// /** The ordemservico bac. */
	// private IOrdemServicoBAC ordemservicoBAC; // injected by @Resource
	//
	// /**
	// * Gets the ordemservico bac.
	// *
	// * @return the ordemservico bac
	// */
	// public IOrdemServicoBAC getOrdemServicoBAC() {
	// return ordemservicoBAC;
	// }
	//
	// /**
	// * Sets the ordemservico bac.
	// *
	// * @param ordemservicoBAC
	// * the new ordemservico bac
	// */
	// @Resource
	// public void setOrdemServicoBAC(IOrdemServicoBAC ordemservicoBAC) {
	// this.ordemservicoBAC = ordemservicoBAC;
	// }
	//
	// // ===================================### ORDEMSERVICO
	// // ####======================================
	// /**
	// * Refresh ordemservicos.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the ordemservico response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public OrdemServicoResponse
	// refreshOrdemServicos(@RequestParam("refreshInt") Integer refreshInt,
	// @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// Boolean retPaged) {
	// OrdemServicoResponse ordemservicoResponse = new OrdemServicoResponse();
	//
	// try {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<OrdemServico> internalResponse =
	// getOrdemServicoBAC().refreshOrdemServicos(request);
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, ordemservicoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return ordemservicoResponse;
	//
	// }
	//
	// /**
	// * Fetch ordemservico paged.
	// *
	// * @param request
	// * the request
	// * @return the ordemservico response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public OrdemServicoResponse fetchOrdemServicoPaged(@RequestBody
	// OrdemServicoInquiryRequest request) {
	// OrdemServicoResponse ordemservicoResponse = new OrdemServicoResponse();
	// try {
	// InternalResultsResponse<OrdemServico> internalResponse =
	// getOrdemServicoBAC()
	// .fetchOrdemServicosByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, ordemservicoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return ordemservicoResponse;
	// }
	//
	// /**
	// * Insert ordemservico.
	// *
	// * @param request
	// * the request
	// * @return the ordemservico response
	// */
	// @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// @ResponseBody
	// public OrdemServicoResponse insertOrdemServico(@RequestBody
	// OrdemServicoMaintenanceRequest request) {
	// OrdemServicoResponse ordemservicoResponse = new OrdemServicoResponse();
	// try {
	// InternalResultsResponse<OrdemServico> internalResponse =
	// getOrdemServicoBAC().insertOrdemServico(request);
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, ordemservicoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return ordemservicoResponse;
	// }
	//
	// /**
	// * Update ordemservico.
	// *
	// * @param request
	// * the request
	// * @return the ordemservico response
	// */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// @ResponseBody
	// public OrdemServicoResponse updateOrdemServico(@RequestBody
	// OrdemServicoMaintenanceRequest request) {
	// OrdemServicoResponse ordemservicoResponse = new OrdemServicoResponse();
	// try {
	// InternalResultsResponse<OrdemServico> internalResponse =
	// getOrdemServicoBAC().updateOrdemServico(request);
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, ordemservicoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return ordemservicoResponse;
	// }
	//
	// /**
	// * Delete ordemservico.
	// *
	// * @param request
	// * the request
	// * @return the ordemservico response
	// */
	// @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// @ResponseBody
	// public OrdemServicoResponse deleteOrdemServico(@RequestBody
	// OrdemServicoMaintenanceRequest request) {
	// OrdemServicoResponse ordemservicoResponse = new OrdemServicoResponse();
	//
	// try {
	// InternalResultsResponse<OrdemServico> internalResponse =
	// getOrdemServicoBAC().deleteOrdemServico(request);
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, ordemservicoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return ordemservicoResponse;
	//
	// }
	//
	// // // ===================================### ORDEMSERVICOTYPE
	// // // ####======================================
	// // /**
	// // * Refresh ordemservicotypes.
	// // *
	// // * @param refreshInt
	// // * the refresh int
	// // * @param retList
	// // * the ret list
	// // * @param retPaged
	// // * the ret paged
	// // * @return the ordemservicotype response
	// // */
	// // @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// // @ResponseBody
	// // public OrdemServicoTypeResponse
	// // refreshOrdemServicoTypes(@RequestParam("refreshInt") Integer
	// refreshInt,
	// // @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// // Boolean retPaged) {
	// // OrdemServicoTypeResponse ordemservicotypeResponse = new
	// // OrdemServicoTypeResponse();
	// //
	// // try {
	// // RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// // retPaged);
	// // InternalResultsResponse<OrdemServicoType> internalResponse =
	// // getOrdemServicoBAC()
	// // .refreshOrdemServicoTypes(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicotypeResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, ordemservicotypeResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return ordemservicotypeResponse;
	// //
	// // }
	// //
	// // /**
	// // * Fetch ordemservicotype paged.
	// // *
	// // * @param request
	// // * the request
	// // * @return the ordemservicotype response
	// // */
	// // @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// // @ResponseBody
	// // public OrdemServicoTypeResponse
	// fetchOrdemServicoTypePaged(@RequestBody
	// // OrdemServicoTypeInquiryRequest request) {
	// // OrdemServicoTypeResponse ordemservicotypeResponse = new
	// // OrdemServicoTypeResponse();
	// // try {
	// // InternalResultsResponse<OrdemServicoType> internalResponse =
	// // getOrdemServicoBAC()
	// // .fetchOrdemServicoTypesByRequest(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicotypeResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, ordemservicotypeResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return ordemservicotypeResponse;
	// // }
	// //
	// // /**
	// // * Insert ordemservicotype.
	// // *
	// // * @param request
	// // * the request
	// // * @return the ordemservicotype response
	// // */
	// // @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// // @ResponseBody
	// // public OrdemServicoTypeResponse insertOrdemServicoType(@RequestBody
	// // OrdemServicoTypeMaintenanceRequest request) {
	// // OrdemServicoTypeResponse ordemservicotypeResponse = new
	// // OrdemServicoTypeResponse();
	// // try {
	// // InternalResultsResponse<OrdemServicoType> internalResponse =
	// // getOrdemServicoBAC()
	// // .insertOrdemServicoType(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicotypeResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, ordemservicotypeResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return ordemservicotypeResponse;
	// // }
	// //
	// // /**
	// // * Update ordemservicotype.
	// // *
	// // * @param request
	// // * the request
	// // * @return the ordemservicotype response
	// // */
	// // @RequestMapping(value = "/update", method = RequestMethod.POST)
	// // @ResponseBody
	// // public OrdemServicoTypeResponse updateOrdemServicoType(@RequestBody
	// // OrdemServicoTypeMaintenanceRequest request) {
	// // OrdemServicoTypeResponse ordemservicotypeResponse = new
	// // OrdemServicoTypeResponse();
	// // try {
	// // InternalResultsResponse<OrdemServicoType> internalResponse =
	// // getOrdemServicoBAC()
	// // .updateOrdemServicoType(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicotypeResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, ordemservicotypeResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return ordemservicotypeResponse;
	// // }
	// //
	// // /**
	// // * Delete ordemservicotype.
	// // *
	// // * @param request
	// // * the request
	// // * @return the ordemservicotype response
	// // */
	// // @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// // @ResponseBody
	// // public OrdemServicoTypeResponse deleteOrdemServicoType(@RequestBody
	// // OrdemServicoTypeMaintenanceRequest request) {
	// // OrdemServicoTypeResponse ordemservicotypeResponse = new
	// // OrdemServicoTypeResponse();
	// //
	// // try {
	// // InternalResultsResponse<OrdemServicoType> internalResponse =
	// // getOrdemServicoBAC()
	// // .deleteOrdemServicoType(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicotypeResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, ordemservicotypeResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return ordemservicotypeResponse;
	// //
	// // }
	// //
	// // // ===================================### ORDEMSERVICOSTATUS
	// // // ####======================================
	// // /**
	// // * Refresh ordemservicostatuss.
	// // *
	// // * @param refreshInt
	// // * the refresh int
	// // * @param retList
	// // * the ret list
	// // * @param retPaged
	// // * the ret paged
	// // * @return the ordemservicostatus response
	// // */
	// // @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// // @ResponseBody
	// // public OrdemServicoStatusResponse
	// // refreshOrdemServicoStatuss(@RequestParam("refreshInt") Integer
	// // refreshInt,
	// // @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// // Boolean retPaged) {
	// // OrdemServicoStatusResponse ordemservicostatusResponse = new
	// // OrdemServicoStatusResponse();
	// //
	// // try {
	// // RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// // retPaged);
	// // InternalResultsResponse<OrdemServicoStatus> internalResponse =
	// // getOrdemServicoBAC()
	// // .refreshOrdemServicoStatuss(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicostatusResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, ordemservicostatusResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return ordemservicostatusResponse;
	// //
	// // }
	// //
	// // /**
	// // * Fetch ordemservicostatus paged.
	// // *
	// // * @param request
	// // * the request
	// // * @return the ordemservicostatus response
	// // */
	// // @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// // @ResponseBody
	// // public OrdemServicoStatusResponse fetchOrdemServicoStatusPaged(
	// // @RequestBody OrdemServicoStatusInquiryRequest request) {
	// // OrdemServicoStatusResponse ordemservicostatusResponse = new
	// // OrdemServicoStatusResponse();
	// // try {
	// // InternalResultsResponse<OrdemServicoStatus> internalResponse =
	// // getOrdemServicoBAC()
	// // .fetchOrdemServicoStatussByRequest(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicostatusResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, ordemservicostatusResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return ordemservicostatusResponse;
	// // }
	// //
	// // /**
	// // * Insert ordemservicostatus.
	// // *
	// // * @param request
	// // * the request
	// // * @return the ordemservicostatus response
	// // */
	// // @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// // @ResponseBody
	// // public OrdemServicoStatusResponse insertOrdemServicoStatus(
	// // @RequestBody OrdemServicoStatusMaintenanceRequest request) {
	// // OrdemServicoStatusResponse ordemservicostatusResponse = new
	// // OrdemServicoStatusResponse();
	// // try {
	// // InternalResultsResponse<OrdemServicoStatus> internalResponse =
	// // getOrdemServicoBAC()
	// // .insertOrdemServicoStatus(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicostatusResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, ordemservicostatusResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return ordemservicostatusResponse;
	// // }
	// //
	// // /**
	// // * Update ordemservicostatus.
	// // *
	// // * @param request
	// // * the request
	// // * @return the ordemservicostatus response
	// // */
	// // @RequestMapping(value = "/update", method = RequestMethod.POST)
	// // @ResponseBody
	// // public OrdemServicoStatusResponse updateOrdemServicoStatus(
	// // @RequestBody OrdemServicoStatusMaintenanceRequest request) {
	// // OrdemServicoStatusResponse ordemservicostatusResponse = new
	// // OrdemServicoStatusResponse();
	// // try {
	// // InternalResultsResponse<OrdemServicoStatus> internalResponse =
	// // getOrdemServicoBAC()
	// // .updateOrdemServicoStatus(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicostatusResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, ordemservicostatusResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return ordemservicostatusResponse;
	// // }
	// //
	// // /**
	// // * Delete ordemservicostatus.
	// // *
	// // * @param request
	// // * the request
	// // * @return the ordemservicostatus response
	// // */
	// // @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// // @ResponseBody
	// // public OrdemServicoStatusResponse deleteOrdemServicoStatus(
	// // @RequestBody OrdemServicoStatusMaintenanceRequest request) {
	// // OrdemServicoStatusResponse ordemservicostatusResponse = new
	// // OrdemServicoStatusResponse();
	// //
	// // try {
	// // InternalResultsResponse<OrdemServicoStatus> internalResponse =
	// // getOrdemServicoBAC()
	// // .deleteOrdemServicoStatus(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicostatusResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, ordemservicostatusResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return ordemservicostatusResponse;
	// //
	// // }
	// //
	// // // ===================================### ORDEMSERVICOITENS
	// // // ####======================================
	// // /**
	// // * Refresh ordemservicoitenss.
	// // *
	// // * @param refreshInt
	// // * the refresh int
	// // * @param retList
	// // * the ret list
	// // * @param retPaged
	// // * the ret paged
	// // * @return the ordemservicoitens response
	// // */
	// // @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// // @ResponseBody
	// // public OrdemServicoItensResponse
	// // refreshOrdemServicoItenss(@RequestParam("refreshInt") Integer
	// refreshInt,
	// // @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// // Boolean retPaged) {
	// // OrdemServicoItensResponse ordemservicoitensResponse = new
	// // OrdemServicoItensResponse();
	// //
	// // try {
	// // RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// // retPaged);
	// // InternalResultsResponse<OrdemServicoItens> internalResponse =
	// // getOrdemServicoBAC()
	// // .refreshOrdemServicoItenss(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicoitensResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, ordemservicoitensResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return ordemservicoitensResponse;
	// //
	// // }
	// //
	// // /**
	// // * Fetch ordemservicoitens paged.
	// // *
	// // * @param request
	// // * the request
	// // * @return the ordemservicoitens response
	// // */
	// // @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// // @ResponseBody
	// // public OrdemServicoItensResponse
	// fetchOrdemServicoItensPaged(@RequestBody
	// // OrdemServicoItensInquiryRequest request) {
	// // OrdemServicoItensResponse ordemservicoitensResponse = new
	// // OrdemServicoItensResponse();
	// // try {
	// // InternalResultsResponse<OrdemServicoItens> internalResponse =
	// // getOrdemServicoBAC()
	// // .fetchOrdemServicoItenssByRequest(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicoitensResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, ordemservicoitensResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return ordemservicoitensResponse;
	// // }
	// //
	// // /**
	// // * Insert ordemservicoitens.
	// // *
	// // * @param request
	// // * the request
	// // * @return the ordemservicoitens response
	// // */
	// // @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// // @ResponseBody
	// // public OrdemServicoItensResponse insertOrdemServicoItens(@RequestBody
	// // OrdemServicoItensMaintenanceRequest request) {
	// // OrdemServicoItensResponse ordemservicoitensResponse = new
	// // OrdemServicoItensResponse();
	// // try {
	// // InternalResultsResponse<OrdemServicoItens> internalResponse =
	// // getOrdemServicoBAC()
	// // .insertOrdemServicoItens(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicoitensResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, ordemservicoitensResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return ordemservicoitensResponse;
	// // }
	// //
	// // /**
	// // * Update ordemservicoitens.
	// // *
	// // * @param request
	// // * the request
	// // * @return the ordemservicoitens response
	// // */
	// // @RequestMapping(value = "/update", method = RequestMethod.POST)
	// // @ResponseBody
	// // public OrdemServicoItensResponse updateOrdemServicoItens(@RequestBody
	// // OrdemServicoItensMaintenanceRequest request) {
	// // OrdemServicoItensResponse ordemservicoitensResponse = new
	// // OrdemServicoItensResponse();
	// // try {
	// // InternalResultsResponse<OrdemServicoItens> internalResponse =
	// // getOrdemServicoBAC()
	// // .updateOrdemServicoItens(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicoitensResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, ordemservicoitensResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return ordemservicoitensResponse;
	// // }
	// //
	// // /**
	// // * Delete ordemservicoitens.
	// // *
	// // * @param request
	// // * the request
	// // * @return the ordemservicoitens response
	// // */
	// // @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// // @ResponseBody
	// // public OrdemServicoItensResponse deleteOrdemServicoItens(@RequestBody
	// // OrdemServicoItensMaintenanceRequest request) {
	// // OrdemServicoItensResponse ordemservicoitensResponse = new
	// // OrdemServicoItensResponse();
	// //
	// // try {
	// // InternalResultsResponse<OrdemServicoItens> internalResponse =
	// // getOrdemServicoBAC()
	// // .deleteOrdemServicoItens(request);
	// //
	// ResponseHandler.handleOperationStatusAndMessages(ordemservicoitensResponse,
	// // internalResponse, true);
	// // } catch (Exception ex) {
	// // ResponseHandler.handleException(LOG, ordemservicoitensResponse, ex,
	// // DEFAULT_EXCEPTION_MSG,
	// // new Object[] { ex.toString() });
	// // }
	// // return ordemservicoitensResponse;
	// //
	// // }

}
