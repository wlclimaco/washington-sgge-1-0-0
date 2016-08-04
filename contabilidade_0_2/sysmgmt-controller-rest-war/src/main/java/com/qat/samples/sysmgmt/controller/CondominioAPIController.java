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
import com.qat.samples.sysmgmt.bac.Condominio.ICondominioBAC;
import com.qat.samples.sysmgmt.condominio.model.Avisos;
import com.qat.samples.sysmgmt.condominio.model.request.AvisoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.AvisoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.response.AvisoResponse;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class CondominioAPIController.
 */
@Controller
@RequestMapping("/condominio/api")
public class CondominioAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.condominiocontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CondominioAPIController.class);

	/** The condominio bac. */
	private ICondominioBAC condominioBAC; // injected by @Resource

	/**
	 * Gets the condominio bac.
	 *
	 * @return the condominio bac
	 */
	public ICondominioBAC getCondominioBAC() {
		return condominioBAC;
	}

	/**
	 * Sets the condominio bac.
	 *
	 * @param condominioBAC
	 *            the new condominio bac
	 */
	@Resource
	public void setCondominioBAC(ICondominioBAC condominioBAC) {
		this.condominioBAC = condominioBAC;
	}

	// ===================================### AVISOS
	// ####======================================
	/**
	 * Refresh avisoss.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the avisos response
	 */
	@RequestMapping(value = "/avisos/refresh", method = RequestMethod.GET)
	@ResponseBody
	public AvisoResponse refreshAvisoss(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		AvisoResponse avisosResponse = new AvisoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().refreshAvisos(request);
			ResponseHandler.handleOperationStatusAndMessages(avisosResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, avisosResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return avisosResponse;

	}

	/**
	 * Fetch avisos paged.
	 *
	 * @param request
	 *            the request
	 * @return the avisos response
	 */
	@RequestMapping(value = "/avisos/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public AvisoResponse fetchAvisosPaged(@RequestBody AvisoInquiryRequest request) {
		AvisoResponse avisosResponse = new AvisoResponse();
		try {
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().fetchAvisosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(avisosResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, avisosResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return avisosResponse;
	}

	/**
	 * Insert avisos.
	 *
	 * @param request
	 *            the request
	 * @return the avisos response
	 */
	@RequestMapping(value = "/avisos/insert", method = RequestMethod.POST)
	@ResponseBody
	public AvisoResponse insertAvisos(@RequestBody AvisoMaintenanceRequest request) {
		AvisoResponse avisosResponse = new AvisoResponse();
		try {
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().insertAvisos(request);
			ResponseHandler.handleOperationStatusAndMessages(avisosResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, avisosResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return avisosResponse;
	}

	/**
	 * Update avisos.
	 *
	 * @param request
	 *            the request
	 * @return the avisos response
	 */
	@RequestMapping(value = "/avisos/update", method = RequestMethod.POST)
	@ResponseBody
	public AvisoResponse updateAvisos(@RequestBody AvisoMaintenanceRequest request) {
		AvisoResponse avisosResponse = new AvisoResponse();
		try {
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().updateAvisos(request);
			ResponseHandler.handleOperationStatusAndMessages(avisosResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, avisosResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return avisosResponse;
	}

	/**
	 * Delete avisos.
	 *
	 * @param request
	 *            the request
	 * @return the avisos response
	 */
	@RequestMapping(value = "/avisos/delete", method = RequestMethod.POST)
	@ResponseBody
	public AvisoResponse deleteAvisos(@RequestBody AvisoMaintenanceRequest request) {
		AvisoResponse avisosResponse = new AvisoResponse();

		try {
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().deleteAvisos(request);
			ResponseHandler.handleOperationStatusAndMessages(avisosResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, avisosResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return avisosResponse;

	}

}
