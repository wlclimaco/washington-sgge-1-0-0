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
import com.qat.samples.sysmgmt.bac.IProcedureBAC;
import com.qat.samples.sysmgmt.model.Procedure;
import com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.ProcedureResponse;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class ProcedureAPIController.
 */
@Controller
@RequestMapping("/procedure/api")
public class ProcedureAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.procedurecontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProcedureAPIController.class);

	/** The procedure bac. */
	private IProcedureBAC procedureBAC; // injected by @Resource

	/**
	 * Gets the procedure bac.
	 *
	 * @return the procedure bac
	 */
	public IProcedureBAC getProcedureBAC() {
		return procedureBAC;
	}

	/**
	 * Sets the procedure bac.
	 *
	 * @param procedureBAC
	 *            the new procedure bac
	 */
	@Resource
	public void setProcedureBAC(IProcedureBAC procedureBAC) {
		this.procedureBAC = procedureBAC;
	}

	/**
	 * Refresh procedures.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the procedure response
	 */
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ProcedureResponse refreshProcedures(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ProcedureResponse procedureResponse = new ProcedureResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Procedure> internalResponse = getProcedureBAC().refreshProcedures(request);
			ResponseHandler.handleOperationStatusAndMessages(procedureResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, procedureResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return procedureResponse;

	}

	/**
	 * Fetch procedure paged.
	 *
	 * @param request
	 *            the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ProcedureResponse fetchProcedurePaged(@RequestBody PagedInquiryRequest request) {
		ProcedureResponse procedureResponse = new ProcedureResponse();
		try {
			InternalResultsResponse<Procedure> internalResponse = getProcedureBAC().fetchProceduresByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(procedureResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, procedureResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return procedureResponse;
	}

	/**
	 * Insert procedure.
	 *
	 * @param request
	 *            the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public ProcedureResponse insertProcedure(@RequestBody ProcedureMaintenanceRequest request) {
		ProcedureResponse procedureResponse = new ProcedureResponse();
		try {
			InternalResultsResponse<Procedure> internalResponse = getProcedureBAC().insertProcedure(request);
			ResponseHandler.handleOperationStatusAndMessages(procedureResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, procedureResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return procedureResponse;
	}

	/**
	 * Update procedure.
	 *
	 * @param request
	 *            the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ProcedureResponse updateProcedure(@RequestBody ProcedureMaintenanceRequest request) {
		ProcedureResponse procedureResponse = new ProcedureResponse();
		try {
			InternalResultsResponse<Procedure> internalResponse = getProcedureBAC().updateProcedure(request);
			ResponseHandler.handleOperationStatusAndMessages(procedureResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, procedureResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return procedureResponse;
	}

	/**
	 * Delete procedure.
	 *
	 * @param request
	 *            the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ProcedureResponse deleteProcedure(@RequestBody ProcedureMaintenanceRequest request) {
		ProcedureResponse procedureResponse = new ProcedureResponse();

		try {
			InternalResultsResponse<Procedure> internalResponse = getProcedureBAC().deleteProcedure(request);
			ResponseHandler.handleOperationStatusAndMessages(procedureResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, procedureResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return procedureResponse;

	}
}
