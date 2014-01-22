package com.sensus.lc.controller.filters;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.lc.base.model.ListTypeEnum;
import com.sensus.lc.controller.light.LightAPIController;
import com.sensus.lc.controller.model.FiltersResponse;

/**
 * The Class FiltersAPIController.
 * 
 * @author QATEmployee
 */
@Controller
@RequestMapping("/api/filters")
public class FiltersAPIController extends BaseFilterController
{

	/** The Constant FETCH. */
	private static final String FETCH_FILTERS = "/fetch";

	/** The Constant ACTION. */
	private static final String ACTION = "action";

	/** The Constant BUILD. */
	private static final String BUILD = "build";

	/** The Constant FETCH_CUSTOMIZE. */
	private static final String FETCH_CUSTOMIZE = "fetchCustomize";

	/** The Constant LIST_TYPE_ENUM. */
	private static final String LIST_TYPE_ENUM = "listTypeEnum";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "FiltersAPIController";

	/**
	 * Fetch.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the filters response
	 */

	@RequestMapping(value = FETCH_FILTERS, method = RequestMethod.POST)
	@ResponseBody
	public FiltersResponse fetch(@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{
		FiltersResponse filtersResponse = new FiltersResponse();

		try
		{
			List<?> data = (List<?>)jsonRequest.get(FILTERS);

			switch (jsonRequest.get(ACTION).toString())
			{

				case BUILD:

					for (Object filter : data)
					{
						getFilterFactory().configureFilter(
								String.valueOf(filter),
								getUserContext(request),
								filtersResponse,
								String.valueOf(jsonRequest.get("page")));
					}

					break;

				case FETCH_CUSTOMIZE:

					fetchCustomizeFilters(filtersResponse, request,
							ListTypeEnum.valueOf(String.valueOf(jsonRequest.get(LIST_TYPE_ENUM))));
					break;
				default:
					break;
			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, filtersResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return filtersResponse;

	}
}