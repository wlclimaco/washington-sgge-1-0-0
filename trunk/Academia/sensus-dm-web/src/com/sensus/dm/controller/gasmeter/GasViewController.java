package com.sensus.dm.controller.gasmeter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.controller.base.DeviceBaseController;
import com.sensus.dm.controller.util.DMConvertUtil;
import com.sensus.dm.controller.util.DMParamConvertUtil;
import com.sensus.dm.gas.device.bcf.IGasMeterBCF;
import com.sensus.dm.gas.device.model.GasMeter;
import com.sensus.dm.gas.device.model.GasMeterSearch;

/**
 * The Class GasViewController.
 */
@Controller
@RequestMapping("/gaslist")
public class GasViewController extends DeviceBaseController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(GasViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "GasViewController";

	/** The Constant VIEW_GAS_MAIN. */
	private static final String VIEW_GAS_MAIN = "/device/gas_main";

	/** The Constant LOAD_LIST. */
	private static final String LOAD_LIST = "";

	/** The gas meter BCF. */
	private IGasMeterBCF gasMeterBCF;

	/**
	 * Gets the gas meter bcf.
	 * 
	 * @return the gas meter bcf
	 */
	public IGasMeterBCF getGasMeterBCF()
	{
		return gasMeterBCF;
	}

	/**
	 * Sets the gas meter bcf.
	 * 
	 * @param gasMeterBCF the new gas meter bcf
	 */
	@Resource
	public void setGasMeterBCF(IGasMeterBCF gasMeterBCF)
	{
		this.gasMeterBCF = gasMeterBCF;
	}

	/**
	 * Load list.
	 * 
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = LOAD_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_GAS_MAIN);
		modelAndView.addObject(SERVICE_TYPE, getServiceType());

		try
		{
			modelAndView.addObject(SESSION, getMapper().writeValueAsString(loadSessionColumnFilter(request)));
			modelAndView.addObject(COLUMN_FILTER,
					getMapper().writeValueAsString(loadColumnFilters(DeviceTypeEnum.GAS_METER.toString())));

			// Whether is initial load or saved search request
			// Check whether has initial load or not
			if (!isInitialLoad(request, modelAndView))
			{
				return modelAndView;
			}

			DeviceSearch deviceSearch = new DeviceSearch();
			InquiryDeviceRequest inquiryDeviceRequest = new InquiryDeviceRequest();

			inquiryDeviceRequest.setDeviceSearch(deviceSearch);

			Map<String, String[]> parameters = request.getParameterMap();

			// Tags
			deviceSearch.setTags(DMParamConvertUtil.fillTags(parameters.get("tag")));

			// Groups
			deviceSearch.setGroups(DMParamConvertUtil.fillGroups(parameters.get("group")));

			// Process ID
			deviceSearch.setProcessId(DMParamConvertUtil.fillProcessId(parameters.get("processId")));

			// Sort Expression
			inquiryDeviceRequest.addSortExpressions(new SortExpression("COALESCE(alarm, 'ZZZ')", Direction.Ascending));
			inquiryDeviceRequest.addSortExpressions(new SortExpression("alarm_time", Direction.Ascending));
			inquiryDeviceRequest.addSortExpressions(new SortExpression("device_id", Direction.Ascending));

			// ADD user context to request
			addUserContextToRequest(inquiryDeviceRequest);

			// Pagination
			String pageSize = getUserSettings().getPageSize();

			if (!ValidationUtil.isNullOrEmpty(pageSize))
			{
				inquiryDeviceRequest.setPageSize(Integer.valueOf(pageSize));
				inquiryDeviceRequest.setPreQueryCount(true);
			}

			// Device Types
			List<DeviceTypeEnum> deviceTypeList = new ArrayList<DeviceTypeEnum>();
			deviceTypeList.add(DeviceTypeEnum.GAS_METER);

			inquiryDeviceRequest.setDeviceType(DeviceTypeEnum.GAS_METER);
			deviceSearch.setDeviceTypes(deviceTypeList);

			GasMeterSearch gasMeterSearch = new GasMeterSearch();
			GasMeter gasMeter = new GasMeter();

			deviceSearch.setGasMeterSearch(gasMeterSearch);
			gasMeterSearch.setGasMeter(gasMeter);

			// Quarantine
			gasMeter.setQuarantine(DMParamConvertUtil.fillQuarantine(parameters.get("quarantine")));

			// Status
			gasMeterSearch.setWaterGasMeterStatusEnumList(DMParamConvertUtil.fillWaterGasMeterStatus(
					parameters.get("status_meter")));

			// Alarms
			gasMeterSearch.setAlarmEnumList(DMParamConvertUtil.fillAlarms(parameters.get("alarm")));

			// Fetch All Gas Meters
			InquiryDeviceResponse inquiryDeviceResponse = getGasMeterBCF().fetchAllGasMeters(inquiryDeviceRequest);

			// Convert Time zone to minutes and set in the displayMinutes field
			DMConvertUtil.convertTimeZoneToMinutes(inquiryDeviceResponse.getDevices());

			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(inquiryDeviceResponse));
		}
		catch (Exception e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}
}
