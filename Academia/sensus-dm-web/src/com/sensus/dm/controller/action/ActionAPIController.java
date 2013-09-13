package com.sensus.dm.controller.action;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.controller.base.BaseController;
import com.sensus.dm.elec.action.bcf.IActionBCF;
import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.action.model.response.ActionResponse;

/**
 * The Class ActionAPIController.
 */
@Controller
@RequestMapping("/api/action")
public class ActionAPIController extends BaseController
{
	/** The Constant ABORT. */
	private static final String ABORT = "/abort";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ActionAPIController";

	/** The Constant DELETE_DEVICE_OPT_OUT_LIST. */
	private static final String DELETE_DEVICE_OPT_OUT_LIST = "/deleteDeviceToOptOutList";

	/** The Constant INSERT_DEVICE_OPT_OUT_LIST. */
	private static final String INSERT_DEVICE_OPT_OUT_LIST = "/insertDeviceToOptOutList";

	/** The Constant PROCESS_ID. */
	private static final String PROCESS_ID = "processID";

	/** The logger for this class. */
	private static final Logger LOG = LoggerFactory.getLogger(ActionAPIController.class);

	/** The action BCF. */
	private IActionBCF actionBCF;

	/**
	 * Gets the action BCF.
	 * 
	 * @return the action BCF
	 */
	public IActionBCF getActionBCF()
	{
		return actionBCF;
	}

	/**
	 * Sets the action BCF.
	 * 
	 * @param actionBCF the new action BCF
	 */
	@Resource
	public void setActionBCF(IActionBCF actionBCF)
	{
		this.actionBCF = actionBCF;
	}

	/**
	 * Abort process.
	 * 
	 * @param requestMap the request map
	 * @return the response
	 */
	@RequestMapping(value = ABORT, method = RequestMethod.POST)
	@ResponseBody
	public Response abortProcess(@RequestBody Map<String, Integer> requestMap)
	{
		ActionResponse response = new ActionResponse();

		try
		{
			if (!ValidationUtil.isNull(requestMap.get(PROCESS_ID)))
			{
				ActionRequest actionRequest = new ActionRequest();

				addUserContextToRequest(actionRequest);

				actionRequest.setProcessId(requestMap.get(PROCESS_ID));

				response = getActionBCF().abortAction(actionRequest);
			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Insert device to schedule opt out list.
	 * 
	 * @param actionRequest the action request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_DEVICE_OPT_OUT_LIST, method = RequestMethod.POST)
	@ResponseBody
	public Response insertDeviceToScheduleOptOutList(@RequestBody ActionRequest actionRequest)
	{
		ActionResponse actionResponse = new ActionResponse();

		try
		{
			addUserContextToRequest(actionRequest);

			actionResponse = getActionBCF().insertDevicesOptOutList(actionRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, actionResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return actionResponse;
	}

	/**
	 * Delete device to schedule opt out list.
	 * 
	 * @param actionRequest the action request
	 * @return the response
	 */
	@RequestMapping(value = DELETE_DEVICE_OPT_OUT_LIST, method = RequestMethod.POST)
	@ResponseBody
	public Response deleteDeviceToScheduleOptOutList(@RequestBody ActionRequest actionRequest)
	{
		ActionResponse actionResponse = new ActionResponse();

		try
		{
			addUserContextToRequest(actionRequest);

			actionResponse = getActionBCF().deleteDevicesOptOutList(actionRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, actionResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return actionResponse;
	}

}
