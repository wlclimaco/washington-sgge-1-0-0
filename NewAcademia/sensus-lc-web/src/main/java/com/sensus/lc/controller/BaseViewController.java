package com.sensus.lc.controller;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.model.UserContext;
import com.sensus.common.web.json.CustomObjectMapper;
import com.sensus.lc.tenant.model.Tenant;

/**
 * @author Delcides Junior
 *
 */
/**
 * The Class BaseViewController.
 */
public class BaseViewController extends BaseController
{

	/** The Constant REFRESH. */
	private static final String REFRESH = "refresh";

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
		if (FALSE.equals(request.getParameter(INITIAL_LOAD)))
		{
			modelAndView.addObject(REFRESH, REFRESH);

			return false;
		}

		return true;
	}

	/**
	 * Gets the hour timezone by tenant.
	 * 
	 * @param userContext the user context
	 * @return the hour timezone by tenant
	 */
	public int getHourTimezoneByTenant(UserContext userContext)
	{
		String timezone = userContext.<Tenant> getTenant().getLightTimeZone();
		return (TimeZone.getTimeZone(timezone).getOffset(new Date().getTime()) / 1000 / 60 / 60) * -1;
	}
}