/** create by system gera-java version 1.0.0 09/05/2016 16:45 : 52*/

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
import com.qat.samples.sysmgmt.advocacia.Audiencia;
import com.qat.samples.sysmgmt.advocacia.Processo;
import com.qat.samples.sysmgmt.advocacia.request.AudienciaInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AudienciaMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.response.AudienciaResponse;
import com.qat.samples.sysmgmt.advocacia.response.ProcessoResponse;
import com.qat.samples.sysmgmt.bac.Advogado.IAdvocaciaBAC;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class AdvogadoAPIController.
 */
@Controller
@RequestMapping("/advocacia/api")
public class AdvogadoAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.advogadocontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AdvogadoAPIController.class);

	/** The advogado bac. */
	private IAdvocaciaBAC advocaciaBAC; // injected by @Resource

	public IAdvocaciaBAC getAdvocaciaBAC() {
		return advocaciaBAC;
	}

	@Resource
	public void setAdvocaciaBAC(IAdvocaciaBAC advocaciaBAC) {
		this.advocaciaBAC = advocaciaBAC;
	}

	// ===================================### AUDIENCIA
	// ####======================================
	/**
	 * Refresh audiencias.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the audiencia response
	 */
	@RequestMapping(value = "/audiencia/refresh", method = RequestMethod.GET)
	@ResponseBody
	public AudienciaResponse refreshAudiencias(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		AudienciaResponse audienciaResponse = new AudienciaResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Audiencia> internalResponse = getAdvocaciaBAC().refreshAudiencias(request);
			ResponseHandler.handleOperationStatusAndMessages(audienciaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, audienciaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return audienciaResponse;

	}

	/**
	 * Fetch audiencia paged.
	 *
	 * @param request
	 *            the request
	 * @return the audiencia response
	 */
	@RequestMapping(value = "/audiencia/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public AudienciaResponse fetchAudienciaPaged(@RequestBody AudienciaInquiryRequest request) {
		AudienciaResponse audienciaResponse = new AudienciaResponse();
		try {
			InternalResultsResponse<Audiencia> internalResponse = getAdvocaciaBAC().fetchAudienciasByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(audienciaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, audienciaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return audienciaResponse;
	}

	/**
	 * Insert audiencia.
	 *
	 * @param request
	 *            the request
	 * @return the audiencia response
	 */
	@RequestMapping(value = "/audiencia/insert", method = RequestMethod.POST)
	@ResponseBody
	public AudienciaResponse insertAudiencia(@RequestBody AudienciaMaintenanceRequest request) {
		AudienciaResponse audienciaResponse = new AudienciaResponse();
		try {
			InternalResultsResponse<Audiencia> internalResponse = getAdvocaciaBAC().insertAudiencia(request);
			ResponseHandler.handleOperationStatusAndMessages(audienciaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, audienciaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return audienciaResponse;
	}

	/**
	 * Update audiencia.
	 *
	 * @param request
	 *            the request
	 * @return the audiencia response
	 */
	@RequestMapping(value = "/audiencia/update", method = RequestMethod.POST)
	@ResponseBody
	public AudienciaResponse updateAudiencia(@RequestBody AudienciaMaintenanceRequest request) {
		AudienciaResponse audienciaResponse = new AudienciaResponse();
		try {
			InternalResultsResponse<Audiencia> internalResponse = getAdvocaciaBAC().updateAudiencia(request);
			ResponseHandler.handleOperationStatusAndMessages(audienciaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, audienciaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return audienciaResponse;
	}

	/**
	 * Delete audiencia.
	 *
	 * @param request
	 *            the request
	 * @return the audiencia response
	 */
	@RequestMapping(value = "/audiencia/delete", method = RequestMethod.POST)
	@ResponseBody
	public AudienciaResponse deleteAudiencia(@RequestBody AudienciaMaintenanceRequest request) {
		AudienciaResponse audienciaResponse = new AudienciaResponse();

		try {
			InternalResultsResponse<Audiencia> internalResponse = getAdvocaciaBAC().deleteAudiencia(request);
			ResponseHandler.handleOperationStatusAndMessages(audienciaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, audienciaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return audienciaResponse;

	}

	// ===================================### PROCESSO
	// ####======================================
	/**
	 * Refresh processos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the processo response
	 */
	@RequestMapping(value = "/processo/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ProcessoResponse refreshProcessos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ProcessoResponse processoResponse = new ProcessoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Processo> internalResponse = getAdvocaciaBAC().refreshProcessos(request);
			ResponseHandler.handleOperationStatusAndMessages(processoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, processoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return processoResponse;

	}

	/**
	 * Fetch processo paged.
	 *
	 * @param request
	 *            the request
	 * @return the processo response
	 */
	@RequestMapping(value = "/processo/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ProcessoResponse fetchProcessoPaged(@RequestBody ProcessoInquiryRequest request) {
		ProcessoResponse processoResponse = new ProcessoResponse();
		try {
			InternalResultsResponse<Processo> internalResponse = getAdvocaciaBAC().fetchProcessosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(processoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, processoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return processoResponse;
	}

	/**
	 * Insert processo.
	 *
	 * @param request
	 *            the request
	 * @return the processo response
	 */
	@RequestMapping(value = "/processo/insert", method = RequestMethod.POST)
	@ResponseBody
	public ProcessoResponse insertProcesso(@RequestBody ProcessoMaintenanceRequest request) {
		ProcessoResponse processoResponse = new ProcessoResponse();
		try {
			InternalResultsResponse<Processo> internalResponse = getAdvocaciaBAC().insertProcesso(request);
			ResponseHandler.handleOperationStatusAndMessages(processoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, processoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return processoResponse;
	}

	/**
	 * Update processo.
	 *
	 * @param request
	 *            the request
	 * @return the processo response
	 */
	@RequestMapping(value = "/processo/update", method = RequestMethod.POST)
	@ResponseBody
	public ProcessoResponse updateProcesso(@RequestBody ProcessoMaintenanceRequest request) {
		ProcessoResponse processoResponse = new ProcessoResponse();
		try {
			InternalResultsResponse<Processo> internalResponse = getAdvocaciaBAC().updateProcesso(request);
			ResponseHandler.handleOperationStatusAndMessages(processoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, processoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return processoResponse;
	}

	/**
	 * Delete processo.
	 *
	 * @param request
	 *            the request
	 * @return the processo response
	 */
	@RequestMapping(value = "/processo/delete", method = RequestMethod.POST)
	@ResponseBody
	public ProcessoResponse deleteProcesso(@RequestBody ProcessoMaintenanceRequest request) {
		ProcessoResponse processoResponse = new ProcessoResponse();

		try {
			InternalResultsResponse<Processo> internalResponse = getAdvocaciaBAC().deleteProcesso(request);
			ResponseHandler.handleOperationStatusAndMessages(processoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, processoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return processoResponse;

	}

}
