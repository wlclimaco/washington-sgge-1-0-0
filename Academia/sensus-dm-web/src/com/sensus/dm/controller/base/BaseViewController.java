package com.sensus.dm.controller.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.web.json.CustomObjectMapper;

/**
 * The Class BaseViewController.
 */
public class BaseViewController extends BaseController
{

	/** The Constant SAVED. */
	private static final String SAVED = "saved";

	/** The Constant TRUE. */
	private static final String TRUE = "true";

	/** The Constant REFRESH. */
	public static final String REFRESH = "refresh";

	/** The Constant INITIAL_LOAD. */
	private static final String INITIAL_LOAD = "initialLoad";

	/** The Constant FALSE. */
	private static final String FALSE = "false";

	/** The Constant RESPONSE. */
	public static final String RESPONSE = "response";

	/** The mapper. */
	private CustomObjectMapper mapper;

	/**
	 * Gets the mapper.
	 * 
	 * @return the mapper
	 */
	public CustomObjectMapper getMapper()
	{
		return mapper;
	}

	/**
	 * Sets the mapper.
	 * 
	 * @param mapper the new mapper
	 */
	@Resource
	public void setMapper(CustomObjectMapper mapper)
	{
		this.mapper = mapper;
	}

	/**
	 * Checks for initial load.
	 * 
	 * @param request the request
	 * @param modelAndView the model and view
	 * @return the boolean
	 */
	public Boolean isInitialLoad(HttpServletRequest request, ModelAndView modelAndView)
	{
		if (FALSE.equals(request.getParameter(INITIAL_LOAD))
				|| TRUE.equals(request.getParameter(SAVED)))
		{
			modelAndView.addObject(REFRESH, REFRESH);

			return false;
		}

		return true;
	}

}