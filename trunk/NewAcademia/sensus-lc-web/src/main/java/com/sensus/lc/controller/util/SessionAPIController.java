package com.sensus.lc.controller.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.lc.controller.BaseController;
import com.sensus.lc.controller.light.LightAPIController;
import com.sensus.lc.controller.model.SystemResponse;

@Controller
@RequestMapping("/api/session")
public class SessionAPIController extends BaseController
{

	private static final String ELEMENT_TYPE = "elementType";

	private static final String DATA = "data";

	private static final String COLUMN_FILTERS = "columnFilters";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "SessionAPIController";

	public HttpSession session;

	@RequestMapping(value = "/fetch", method = RequestMethod.POST)
	@ResponseBody
	public SystemResponse fetch(@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{

		SystemResponse systemResponse = new SystemResponse();
		try
		{

			session = request.getSession();

			systemResponse.addAttributeToSession(COLUMN_FILTERS,
					session.getAttribute(String.valueOf(jsonRequest.get(ELEMENT_TYPE)).toUpperCase()));

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, systemResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return systemResponse;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public SystemResponse insert(@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{

		SystemResponse systemResponse = new SystemResponse();
		try
		{

			session = request.getSession();

			session.setAttribute(String.valueOf(jsonRequest.get(ELEMENT_TYPE)).toUpperCase(), jsonRequest.get(DATA));

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, systemResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return systemResponse;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public SystemResponse remove(@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{

		SystemResponse systemResponse = new SystemResponse();
		try
		{

			session = request.getSession();

			session.removeAttribute(String.valueOf(jsonRequest.get(ELEMENT_TYPE)).toUpperCase());

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, systemResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return systemResponse;
	}

}
