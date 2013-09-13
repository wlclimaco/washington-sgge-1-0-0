package com.sensus.dm.controller.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.validation.ValidationUtil;

/**
 * The Class SessionUtil.
 */
@Controller
@RequestMapping("/util/session")
public class SessionUtil
{

	/** The Constant KEY. */
	private static final String KEY = "key";

	/** The Constant REMOVE. */
	private static final String REMOVE = "/remove";

	/** The Constant READ. */
	private static final String READ = "/read";

	/** The Constant UPDATE. */
	private static final String UPDATE = "/update";

	/** The Constant VALUE. */
	private static final String VALUE = "value";

	/**
	 * Update.
	 * 
	 * @param request the request
	 * @param servletRequest the servlet request
	 */
	@RequestMapping(value = UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public void update(@RequestBody Map<String, Object> request, HttpServletRequest servletRequest)
	{
		try
		{
			HttpSession session = servletRequest.getSession(true);

			if (!ValidationUtil.isNull(request.get(KEY))
					&& !ValidationUtil.isNull(request.get(VALUE)))
			{
				session.setAttribute(request.get(KEY).toString(), request.get(VALUE));
			}
		}
		catch (Exception e)
		{

		}

	}

	/**
	 * Read.
	 * 
	 * @param request the request
	 * @param servletRequest the servlet request
	 * @return the hash map
	 */
	@RequestMapping(value = READ, method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> read(@RequestBody List<String> request, HttpServletRequest servletRequest)
	{

		HttpSession session = servletRequest.getSession(true);
		HashMap<String, Object> response = new HashMap<String, Object>();

		try
		{
			if (!ValidationUtil.isNull(request) && (request.size() > 0))
			{
				for (String key : request)
				{
					response.put(key, session.getAttribute(key));
				}
			}
		}
		catch (Exception e)
		{
		}

		return response;
	}

	/**
	 * Delete.
	 * 
	 * @param request the request
	 * @param servletRequest the servlet request
	 */
	@RequestMapping(value = REMOVE, method = RequestMethod.POST)
	@ResponseBody
	public void remove(@RequestBody List<String> request, HttpServletRequest servletRequest)
	{
		HttpSession session = servletRequest.getSession(true);

		try
		{
			if (!ValidationUtil.isNull(request))
			{
				for (String key : request)
				{
					session.removeAttribute(key);
				}
			}
		}
		catch (Exception e)
		{

		}
	}
}
