package com.sensus.dm.controller.process;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

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
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.process.bcf.IProcessBCF;
import com.sensus.dm.common.process.model.ProcessSearch;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.controller.filter.BaseFilterController;
import com.sensus.dm.controller.util.DMConvertUtil;
import com.sensus.dm.water.device.bcf.IWaterMeterBCF;

/**
 * The Class ProcessViewController.
 * 
 * @author QAT BRAZIL
 */
@Controller
@RequestMapping("/process")
public class ProcessViewController extends BaseFilterController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProcessViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "ProcessViewController";

	/** The Constant REQUEST_TIME_ZONE. */
	private static final String REQUEST_TIME_ZONE = "-4";

	/** The Constant SEARCH_START_TIME. */
	private static final String SEARCH_START_TIME = "start_time";

	/** The Constant FETCH_LIST_TODAY. */
	private static final String FETCH_LIST_TODAY = "/tab/today";

	/** The Constant FETCH_LIST_HISTORY. */
	private static final String FETCH_LIST_HISTORY = "/tab/history";

	/** The Constant FETCH_LIST_LEAKREPORT. */
	private static final String FETCH_LIST_LEAKREPORT = "/tab/report";

	/** The Constant VIEW_GROUP_MAIN. */
	private static final String VIEW_PROCESS_TODAY_MAIN = "/process/process_today";

	/** The Constant VIEW_PROCESS_HISTORY_MAIN. */
	private static final String VIEW_PROCESS_HISTORY_MAIN = "/process/process_history";

	/** The Constant VIEW_PROCESS_HISTORY_MAIN. */
	private static final String VIEW_PROCESS_LEAKREPORT_MAIN = "/process/process_leak_report";

	/** The process BCF. */
	private IProcessBCF processBCF;

	/** The water meter BCF. */
	private IWaterMeterBCF waterMeterBCF;

	/**
	 * Gets the water meter BCF.
	 * 
	 * @return the waterMeterBCF
	 */
	public IWaterMeterBCF getWaterMeterBCF()
	{
		return waterMeterBCF;
	}

	/**
	 * Sets the water meter BCF.
	 * 
	 * @param waterMeterBCF the waterMeterBCF to set
	 */
	@Resource
	public void setWaterMeterBCF(IWaterMeterBCF waterMeterBCF)
	{
		this.waterMeterBCF = waterMeterBCF;
	}

	/**
	 * Gets the process BCF.
	 * 
	 * @return the processBCF
	 */
	public IProcessBCF getProcessBCF()
	{
		return processBCF;
	}

	/**
	 * Sets the process BCF.
	 * 
	 * @param processBCF the processBCF to set
	 */
	@Resource
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/**
	 * Fetch list.
	 * 
	 * @param request the request
	 * @return the model (inquiry process response) and view
	 */
	@RequestMapping(value = FETCH_LIST_TODAY, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_PROCESS_TODAY_MAIN);
		InquiryProcessRequest inquiryProcessRequest = new InquiryProcessRequest();

		// ADD user context to request
		addUserContextToRequest(inquiryProcessRequest);

		// Process Search with start and end of current day
		inquiryProcessRequest.setIsToday(true);
		inquiryProcessRequest.addSortExpressions(new SortExpression(SEARCH_START_TIME, Direction.Descending));

		inquiryProcessRequest.setProcessSearch(new ProcessSearch(
				DMConvertUtil.getStartOfDay(new Date(), getUserSettings().getTimeZoneMinutes()),
				DMConvertUtil.getEndOfDay(new Date(), getUserSettings().getTimeZoneMinutes())));

		// Page size
		String pageSize = getUserSettings().getPageSize();
		if (!ValidationUtil.isNullOrEmpty(pageSize))
		{
			inquiryProcessRequest.setPageSize(Integer.valueOf(pageSize));
		}

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(
					getProcessBCF().fetchTodayProcesses(inquiryProcessRequest)));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}

	/**
	 * Load list history.
	 * 
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST_HISTORY, method = RequestMethod.GET)
	public ModelAndView loadListHistory(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_PROCESS_HISTORY_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		InquiryProcessRequest inquiryProcessRequest = new InquiryProcessRequest();

		// ADD user context to request
		addUserContextToRequest(inquiryProcessRequest);

		// Sort expression
		inquiryProcessRequest.addSortExpressions(new SortExpression(SEARCH_START_TIME, Direction.Descending));
		inquiryProcessRequest.setTimeZone(REQUEST_TIME_ZONE);

		// Page Size
		String pageSize = getUserSettings().getPageSize();
		if (!ValidationUtil.isNullOrEmpty(pageSize))
		{
			inquiryProcessRequest.setPageSize(Integer.valueOf(pageSize));
			inquiryProcessRequest.setPreQueryCount(true);
		}

		// Process Search with start of current day and end with 10 days back
		Calendar backTenDaysDate = Calendar.getInstance();
		backTenDaysDate.set(Calendar.DAY_OF_MONTH, -10);

		inquiryProcessRequest.setProcessSearch(new ProcessSearch(
				DMConvertUtil.getStartOfDay(
						DMConvertUtil.convertDateToDefaultUTC(backTenDaysDate.getTime()), getUserSettings()
								.getTimeZoneMinutes()),
				DMConvertUtil.getEndOfDay(new Date(), getUserSettings().getTimeZoneMinutes())));

		// Filters
		HashMap<String, Object> responseFilter = new HashMap<String, Object>();
		createFilterAllActionCategories(responseFilter, request);
		createFilterUser(responseFilter);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(
					getProcessBCF().fetchProcesses(inquiryProcessRequest)));
			modelAndView.addObject(FILTERS, getMapper().writeValueAsString(responseFilter));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
			modelAndView.addObject(FILTERS, null);
		}

		return modelAndView;
	}

	@RequestMapping(value = FETCH_LIST_LEAKREPORT, method = RequestMethod.GET)
	public ModelAndView loadLeakReport(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_PROCESS_LEAKREPORT_MAIN);
		DeviceRequest deviceRequest = new DeviceRequest();

		// ADD user context to request
		addUserContextToRequest(deviceRequest);

		getUserSettings();

		try
		{
			modelAndView.addObject(RESPONSE,
					getMapper().writeValueAsString(getWaterMeterBCF().fetchLeakReport(deviceRequest)));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;

	}
}
