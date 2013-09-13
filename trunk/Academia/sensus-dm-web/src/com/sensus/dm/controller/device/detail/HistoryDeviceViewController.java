package com.sensus.dm.controller.device.detail;

import java.io.IOException;
import java.math.BigInteger;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.process.bcf.IProcessBCF;
import com.sensus.dm.common.process.model.ProcessSearch;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.controller.filter.BaseFilterController;
import com.sensus.dm.controller.util.DMConvertUtil;

/**
 * The Class AboutDeviceViewController.
 */
@Controller
@RequestMapping("/device/tab/history")
public class HistoryDeviceViewController extends BaseFilterController
{

	/** The Constant REQUEST_TIME_ZONE. */
	private static final String REQUEST_TIME_ZONE = "-4";

	/** The Constant REQUEST_DATE_FORMAT. */
	private static final String REQUEST_DATE_FORMAT = "MM/dd/yyyy";

	/** The Constant SEARCH_START_TIME. */
	private static final String SEARCH_START_TIME = "start_time";

	/** The Constant VIEW_ABOUT_DEVICE_MAIN. */
	private static final String VIEW_HISTORY_DEVICE_MAIN = "/device_detail/device_detail_history";

	/** The Constant LOAD. */
	private static final String LOAD = "";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "HistoryDeviceViewController";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(HistoryDeviceViewController.class);

	/** The process BCF. */
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
	 * Load.
	 * 
	 * @param flexNetId the flex net id
	 * @param deviceType the device type
	 * @param deviceSubType the device sub type
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = LOAD, method = RequestMethod.GET)
	public ModelAndView load(@RequestParam(value = "id", required = true) BigInteger flexNetId,
			@RequestParam(value = "deviceType", required = true) DeviceTypeEnum deviceType,
			@RequestParam(value = "typeEnum", required = false) String deviceSubType,
			HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView(VIEW_HISTORY_DEVICE_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		InquiryProcessRequest inquiryProcessRequest = new InquiryProcessRequest();

		// ADD user context to request
		addUserContextToRequest(inquiryProcessRequest);

		// Process Search with start of current day and end with 10 days back
		Calendar backTenDaysDate = Calendar.getInstance();
		backTenDaysDate.set(Calendar.DAY_OF_MONTH, -10);

		// inquiryProcessRequest.getDevices().add(new Device(flexNetId));

		inquiryProcessRequest.setProcessSearch(new ProcessSearch(
				DMConvertUtil.getStartOfDay(
						DMConvertUtil.convertDateToDefaultUTC(backTenDaysDate.getTime()), getUserSettings()
								.getTimeZoneMinutes()),
				DMConvertUtil.getEndOfDay(new Date(), getUserSettings().getTimeZoneMinutes())));

		Device device = new Device(new Radio(flexNetId));

		inquiryProcessRequest.addDevice(device);

		inquiryProcessRequest.addSortExpressions(new SortExpression(SEARCH_START_TIME, Direction.Descending));
		inquiryProcessRequest.setDateFormat(REQUEST_DATE_FORMAT);
		inquiryProcessRequest.setTimeZone(REQUEST_TIME_ZONE);

		String pageSize = getUserSettings().getPageSize();
		if (!ValidationUtil.isNullOrEmpty(pageSize))
		{
			inquiryProcessRequest.setPageSize(Integer.valueOf(pageSize));
			inquiryProcessRequest.setPreQueryCount(true);
		}

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
}
