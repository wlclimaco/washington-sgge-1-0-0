/** create by system gera-java version 1.0.0 09/05/2016 16:45 : 52*/

package com.qat.samples.sysmgmt.controller;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Empresa.IEmpresaBAC;
import com.qat.samples.sysmgmt.entidade.model.Message;
import com.qat.samples.sysmgmt.entidade.model.request.MessageInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.response.MessageResponse;
import com.qat.samples.sysmgmt.util.model.request.MessageMaintenanceRequest;

@Controller
@RequestMapping("/mvc/chat")
public class ChatController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.empresacontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EmpresaAPIController.class);

	/** The empresa bac. */
	private IEmpresaBAC empresaBAC; // injected by @Resource

	/**
	 * Gets the empresa bac.
	 *
	 * @return the empresa bac
	 */
	public IEmpresaBAC getEmpresaBAC() {
		return empresaBAC;
	}

	/**
	 * Sets the empresa bac.
	 *
	 * @param empresaBAC
	 *            the new empresa bac
	 */
	@Resource
	public void setEmpresaBAC(IEmpresaBAC empresaBAC) {
		this.empresaBAC = empresaBAC;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public MessageResponse getMessages(@RequestBody MessageInquiryRequest request) {

		final DeferredResult<List<String>> deferredResult = new DeferredResult<>(null, Collections.emptyList());
		// chatRequests.put(deferredResult, messageIndex);
		MessageResponse response = new MessageResponse();
		deferredResult.onCompletion(new Runnable() {
			@Override
			public void run() {
				try {
					MessageMaintenanceRequest request1 = new MessageMaintenanceRequest();
					InternalResultsResponse<Message> internalResponse = getEmpresaBAC().deleteMessage(request1);
					ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
				} catch (Exception ex) {
					ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG,
							new Object[] { ex.toString() });
				}
			}
		});

		try {
			InternalResultsResponse<Message> internalResponse = getEmpresaBAC().fetchMessagesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] { ex.toString() });
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public MessageResponse postMessage(@RequestBody MessageMaintenanceRequest request) {
		MessageResponse response = new MessageResponse();
		try {
			InternalResultsResponse<Message> internalResponse = getEmpresaBAC().insertMessage(request);
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] { ex.toString() });
		}
		return response;
	}

}