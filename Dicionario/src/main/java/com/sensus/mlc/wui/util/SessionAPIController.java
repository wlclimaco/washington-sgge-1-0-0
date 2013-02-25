package com.sensus.mlc.wui.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.model.SystemResponse;

@Controller
@RequestMapping("/api/session")
public class SessionAPIController extends BaseController
{

	/** The Constant ACTION. */
	private static final String ACTION = "action";

	private static final String INSERT = "insert";

	private static final String LIST_TYPE_ENUM = "listTypeEnum";

	private static final String FETCH_COLUMN_FILTERS = "fetchColumnFilters";

	private static final String COLUMN_FILTERS = "columnFilters";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "SessionAPIController";

	@RequestMapping(value = "/fetch", method = RequestMethod.POST)
	@ResponseBody
	public SystemResponse fetch(@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{

		SystemResponse systemResponse = new SystemResponse();
		try
		{

			HttpSession session = request.getSession();

			switch (jsonRequest.get(ACTION).toString())
			{
				case INSERT:

					session.setAttribute(COLUMN_FILTERS, jsonRequest.get(LIST_TYPE_ENUM));

					break;

				case FETCH_COLUMN_FILTERS:

					systemResponse.addAttributeToSession(COLUMN_FILTERS, session.getAttribute(COLUMN_FILTERS));

					break;

			}

		}
		catch (Exception e)
		{
			//SensusInterfaceUtil.handleException(LOG, systemResponse, e, DEFAULT_EXCEPTION_MSG,
			//		new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return systemResponse;
	}

	@Override
	public void setMessageSource(MessageSource arg0)
	{
		// TODO Auto-generated method stub

	}

}
