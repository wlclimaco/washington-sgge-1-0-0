package com.prosperitasglobal.sendsolv.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;

import com.qat.framework.model.UserContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class BaseController.
 *
 * @author Flavio Tosta
 */
public abstract class BaseController
{

	/** The Constant PARAM_USER_ID. */
	private static final String PARAM_USER_ID = "userId";

	/** The Constant RESPONSE. */
	public static final String RESPONSE = "response";

	/** The Mapper Object. */
	private ObjectMapper mapper;

	/**
	 * Gets the mapper.
	 *
	 * @return the mapper
	 */
	public ObjectMapper getMapper()
	{
		return mapper;
	}

	/**
	 * Sets the mapper.
	 *
	 * @param mapper the new mapper
	 */
	@Resource
	public void setMapper(ObjectMapper mapper)
	{
		this.mapper = mapper;
	}

	/**
	 * Fetch user context.
	 *
	 * @param request the request
	 * @return the user context
	 */
	public UserContext fetchUserContext(HttpServletRequest request)
	{
		if (ValidationUtil.isNullOrEmpty(request.getParameter(PARAM_USER_ID)))
		{
			return null;
		}

		UserContext userContext = new UserContext(request.getParameter(PARAM_USER_ID));

		return userContext;
	}

}