package com.sensus.lc.controller.process;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.filters.BaseFilterController;
import com.sensus.lc.controller.model.FiltersResponse;
import com.sensus.lc.process.bcf.IProcessBCF;
import com.sensus.lc.process.model.ProcessFilter;
import com.sensus.lc.process.model.request.InquiryProcessRequest;
import com.sensus.lc.process.model.response.InquiryProcessResponse;

/**
 * The Class ProcessViewController.
 */
@Controller
@RequestMapping("/process")
public class ProcessViewController extends BaseFilterController
{

	/** The Constant ROLE_ROLE_ADMIN. */
	private static final String ROLE_ROLE_ADMIN = "ROLE_Role.Admin";

	/** The Constant FETCH_LIST. */
	public static final String FETCH_LIST = "";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProcessViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "ProcessViewController";

	/** The Constant VIEW_LIGHT_MAIN. */
	private static final String VIEW_PROCESS_MAIN = "/process/process_main";

	/** The Constant INQUIRY_ACTION_TABLE. */
	private static final String INQUIRY_ACTION_TABLE = "table";

	/** The Constant INQUIRY_DATE_PATTERN. */
	private static final String INQUIRY_DATE_PATTERN = "mm/dd/yy";

	/** The Constant SORT_START_DATETIME. */
	private static final String SORT_START_DATETIME = "START_DATETIME";

	/** The Constant PROCESS. */
	private static final String PROCESS = "systemintelligence/process";

	/** The Constant FILTER_SEARCH. */
	private static final String FILTER_SEARCH = "SEARCH";

	/** The Constant FILTER_USERS. */
	private static final String FILTER_USERS = "USERS";

	/** The Constant FILTER_EVENT_TYPE. */
	private static final String FILTER_EVENT_TYPE = "EVENT_TYPE";

	/** The Constant FILTERS_EVENT. */
	private static final String FILTERS_EVENT = "FILTERS_EVENT";

	/** The process bcf. */
	private IProcessBCF processBCF;

	/**
	 * Gets the process bcf.
	 * 
	 * @return the process bcf
	 */
	public IProcessBCF getProcessBCF()
	{
		return processBCF;
	}

	/**
	 * Sets the process bcf.
	 * 
	 * @param processBCF the new process bcf
	 */
	@Resource
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/**
	 * Load list.
	 * 
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_PROCESS_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		UserContext userContext = getUserContext(request);

		InquiryProcessRequest inquiryProcessRequest = new InquiryProcessRequest(userContext);
		inquiryProcessRequest.setAction(INQUIRY_ACTION_TABLE);
		inquiryProcessRequest.setDatePattern(INQUIRY_DATE_PATTERN);
		inquiryProcessRequest.setTimezone(getUserSettings().getTimezone());
		inquiryProcessRequest.setProcessFilter(new ProcessFilter());
		inquiryProcessRequest.addSortExpressions(new SortExpression(SORT_START_DATETIME, Direction.Descending));

		// Check for page size
		Integer pageSize = getUserSettings().getPageSize();
		if (!ValidationUtil.isNullOrZero(pageSize))
		{
			inquiryProcessRequest.setPageSize(pageSize);
		}

		InquiryProcessResponse inquiryProcessResponse = getProcessBCF().fetchAllProcess(inquiryProcessRequest);

		// Filters
		FiltersResponse filtersResponse = new FiltersResponse();
		getFilterFactory().configureFilter(FILTER_SEARCH, getUserContext(request), filtersResponse, PROCESS);
		getFilterFactory().configureFilter(FILTERS_EVENT, getUserContext(request), filtersResponse);
		getFilterFactory().configureFilter(FILTER_EVENT_TYPE, getUserContext(request), filtersResponse);

		if (userContext.getUserRole().equals(ROLE_ROLE_ADMIN))
		{
			getFilterFactory().configureFilter(FILTER_USERS, getUserContext(request), filtersResponse);
		}

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(inquiryProcessResponse));
			modelAndView.addObject(FILTERS, getMapper().writeValueAsString(filtersResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}
		return modelAndView;
	}
}