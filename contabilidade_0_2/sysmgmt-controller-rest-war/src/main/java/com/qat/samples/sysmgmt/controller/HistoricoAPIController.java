/** create by system gera-java version 1.0.0 09/05/2016 16:51 : 47*/

package com.qat.samples.sysmgmt.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Historico.IHistoricoBAC;
import com.qat.samples.sysmgmt.historico.model.Historico;
import com.qat.samples.sysmgmt.historico.model.request.HistoricoInquiryRequest;
import com.qat.samples.sysmgmt.historico.model.response.HistoricoResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Class HistoricoAPIController.
 */
@Controller
@RequestMapping("/historico/api")
public class HistoricoAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.historicocontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(HistoricoAPIController.class);

	/** The historico bac. */
	private IHistoricoBAC historicoBAC; // injected by @Resource

	/**
	 * Gets the historico bac.
	 *
	 * @return the historico bac
	 */
	public IHistoricoBAC getHistoricoBAC() {
		return historicoBAC;
	}

	/**
	 * Sets the historico bac.
	 *
	 * @param historicoBAC
	 *            the new historico bac
	 */
	@Resource
	public void setHistoricoBAC(IHistoricoBAC historicoBAC) {
		this.historicoBAC = historicoBAC;
	}

	/**
	 * Fetch historico paged.
	 *
	 * @param request
	 *            the request
	 * @return the historico response
	 */
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public HistoricoResponse fetchHistoricoPaged(@RequestBody HistoricoInquiryRequest request) {
		HistoricoResponse historicoResponse = new HistoricoResponse();
		try {
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().fetchHistoricosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(historicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, historicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return historicoResponse;
	}

	@RequestMapping(value = "/fetchPageAll", method = RequestMethod.POST)
	@ResponseBody
	public HistoricoResponse fetchHistoricoall(@RequestBody Historico request) {
		HistoricoResponse historicoResponse = new HistoricoResponse();
		try {
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().fetchAllHistoricos(request);
			ResponseHandler.handleOperationStatusAndMessages(historicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, historicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return historicoResponse;
	}

	@RequestMapping(value = "/fetchById", method = RequestMethod.POST)
	@ResponseBody
	public HistoricoResponse fetchHistoricoById(@RequestBody FetchByIdRequest request) {
		HistoricoResponse historicoResponse = new HistoricoResponse();
		try {
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().fetchHistoricoById(request);
			ResponseHandler.handleOperationStatusAndMessages(historicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, historicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return historicoResponse;
	}
}
