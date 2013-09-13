package com.sensus.dm.controller.dashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.Response;
import com.sensus.dm.common.base.model.ServicesTypePermissions;
import com.sensus.dm.common.device.bcf.ICustomSearchBCF;
import com.sensus.dm.common.device.model.AlarmsTypesCount;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.DeviceTypeCount;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryCustomSearchResponse;
import com.sensus.dm.common.process.bcf.IProcessBCF;
import com.sensus.dm.common.process.model.ProcessSearch;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.response.InquiryProcessResponse;
import com.sensus.dm.controller.base.BaseModuleController;
import com.sensus.dm.controller.util.DMConvertUtil;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStateEnum;
import com.sensus.dm.elec.device.model.ElectricMeterSearch;
import com.sensus.dm.elec.device.model.HanDeviceSearch;
import com.sensus.dm.elec.device.model.HanLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCMSearch;
import com.sensus.dm.gas.device.model.GasMeterSearch;
import com.sensus.dm.water.device.bcf.IWaterMeterBCF;
import com.sensus.dm.water.device.model.WaterGasMeterStatusCount;
import com.sensus.dm.water.device.model.WaterMeterSearch;

/**
 * The Class DashboardViewController.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardViewController extends BaseModuleController
{

	/** The Constant FIVE. */
	private static final Integer FIVE = 5;

	/** The Constant CONTENT_MODULES. */
	private static final String CONTENT_MODULES = "contentModules";

	/** The Constant VIEW_DASHBOARD_MAIN. */
	private static final String VIEW_DASHBOARD_MAIN = "/dashboard/dashboard_main";

	/** The Constant FETCH_LIST. */
	private static final String LOAD = "";

	/** The Constant START_TIME_SORT_EXPRESSION. */
	private static final String START_TIME_SORT_EXPRESSION = "start_time";

	/** The Constant NAME_SORT_EXPRESSION. */
	private static final String NAME_SORT_EXPRESSION = "name";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "DashboardViewController";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DashboardViewController.class);

	/** The process bcf. */
	private IProcessBCF processBCF;

	/** The custom search bcf. */
	private ICustomSearchBCF customSearchBCF;

	/** The water meter bcf. */
	private IWaterMeterBCF waterMeterBCF;

	/**
	 * Gets the water meter bcf.
	 * 
	 * @return the water meter bcf
	 */
	public IWaterMeterBCF getWaterMeterBCF()
	{
		return waterMeterBCF;
	}

	/**
	 * Sets the water meter bcf.
	 * 
	 * @param waterMeterBCF the new water meter bcf
	 */
	@Resource
	public void setWaterMeterBCF(IWaterMeterBCF waterMeterBCF)
	{
		this.waterMeterBCF = waterMeterBCF;
	}

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
	 * Gets the custom search bcf.
	 * 
	 * @return the custom search bcf
	 */
	public ICustomSearchBCF getCustomSearchBCF()
	{
		return customSearchBCF;
	}

	/**
	 * Sets the custom search bcf.
	 * 
	 * @param customSearchBCF the new custom search bcf
	 */
	@Resource
	public void setCustomSearchBCF(ICustomSearchBCF customSearchBCF)
	{
		this.customSearchBCF = customSearchBCF;
	}

	/**
	 * Load.
	 * 
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = LOAD, method = RequestMethod.GET)
	public ModelAndView load(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_DASHBOARD_MAIN);

		// Dashboard Module
		modelAndView.addObject(CONTENT_MODULES,
				fillModulesResponse(getDashboardUiModulesByService(ServiceTypeEnum.valueOf(getServiceType()))));

		// Response Map
		Map<String, Response> responseMap = new HashMap<String, Response>();

		responseMap.put("dashboardHeader", getDashboardHeader());
		responseMap.put("todayProcesses", getTodayProcesses());
		responseMap.put("customSearches", getCustomSearches());

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(responseMap));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}

	/**
	 * Gets the today processes.
	 * 
	 * @return the today processes
	 */
	private InquiryProcessResponse getTodayProcesses()
	{
		InquiryProcessRequest inquiryProcessRequest = new InquiryProcessRequest();

		// Add user context to request
		addUserContextToRequest(inquiryProcessRequest);

		// Max 5 results
		inquiryProcessRequest.setEndRow(4);
		inquiryProcessRequest.setPageSize(FIVE);

		// Sort expression
		inquiryProcessRequest.addSortExpressions(
				new SortExpression(START_TIME_SORT_EXPRESSION, Direction.Descending));

		// Process Search with start and end of current day
		inquiryProcessRequest.setProcessSearch(new ProcessSearch(
				DMConvertUtil.getStartOfDay(
						DMConvertUtil.convertDateToDefaultUTC(new Date()), getUserSettings().getTimeZoneMinutes()),
				DMConvertUtil.getEndOfDay(new Date(), getUserSettings().getTimeZoneMinutes())));

		return getProcessBCF().fetchProcesses(inquiryProcessRequest);
	}

	/**
	 * Gets the custom searches.
	 * 
	 * @return the custom searches
	 */
	private InquiryCustomSearchResponse getCustomSearches()
	{
		InquiryCustomSearchRequest inquiryCustomSearchRequest = new InquiryCustomSearchRequest();

		// Add user context to request
		addUserContextToRequest(inquiryCustomSearchRequest);

		// Max results
		inquiryCustomSearchRequest.setPageSize(FIVE);

		// Set sort expression
		inquiryCustomSearchRequest.addSortExpressions(
				new SortExpression(NAME_SORT_EXPRESSION, Direction.Ascending));

		inquiryCustomSearchRequest.setIsDashboard(true);

		return getCustomSearchBCF().fetchAllCustomSearch(inquiryCustomSearchRequest);
	}

	/**
	 * Gets the dashboard header.
	 * 
	 * @return the dashboard header
	 */
	private DeviceResponse getDashboardHeader()
	{
		DeviceRequest deviceRequest = new DeviceRequest();
		DeviceResponse deviceResponse = new DeviceResponse();

		// Add user context to request
		addUserContextToRequest(deviceRequest);

		switch (deviceRequest.getServiceTypeEnum())
		{
			case ELECTRIC:

				fillDeviceRequestToElectricDashboard(deviceRequest);

				// DeviceTypes
				deviceResponse.setDeviceTypeCountList(getDevicesTypeCount(deviceRequest));

				break;

			case WATER:

				fillDeviceRequestToWaterDashboard(deviceRequest);

				// Communications
				deviceResponse.setWaterGasMeterStatusCount(getWaterGasMeterStatusCount(deviceRequest));

				// Quarantine
				deviceResponse.setQuarantineAmount(getQuarantineAmount(deviceRequest));
				break;

			case GAS:

				fillDeviceRequestToGasDashboard(deviceRequest);

				// Communications
				deviceResponse.setWaterGasMeterStatusCount(getWaterGasMeterStatusCount(deviceRequest));

				// Quarantine
				deviceResponse.setQuarantineAmount(getQuarantineAmount(deviceRequest));
				break;
		}

		// Alarms
		deviceResponse.setAlarmsTypesCount(getAlarmsTypeCount(deviceRequest));

		return deviceResponse;
	}

	/**
	 * Fill device request to water dashboard.
	 * 
	 * @param deviceRequest the device request
	 */
	private void fillDeviceRequestToWaterDashboard(DeviceRequest deviceRequest)
	{
		// Water Meter Search
		WaterMeterSearch waterMeterSearch = new WaterMeterSearch();

		// Alarm list of water service
		List<ServicesTypePermissions> servicesType =
				fetchServiceTypePermissionsByServiceType(deviceRequest.getServiceTypeEnum());

		waterMeterSearch.setAlarmEnumList(servicesType.get(0).getDeviceTypePermissions().get(0).getAlarms());

		// Device Search
		deviceRequest.setDeviceSearch(new DeviceSearch(waterMeterSearch));
	}

	/**
	 * Fill device request to gas dashboard.
	 * 
	 * @param deviceRequest the device request
	 */
	private void fillDeviceRequestToGasDashboard(DeviceRequest deviceRequest)
	{
		// Gas Meter Search
		GasMeterSearch gasMeterSearch = new GasMeterSearch();

		// Alarm list of gas service
		List<ServicesTypePermissions> servicesType =
				fetchServiceTypePermissionsByServiceType(deviceRequest.getServiceTypeEnum());

		gasMeterSearch.setAlarmEnumList(servicesType.get(0).getDeviceTypePermissions().get(0).getAlarms());

		// Device Search
		deviceRequest.setDeviceSearch(new DeviceSearch(gasMeterSearch));
	}

	/**
	 * Sets the electric dashboard activity request.
	 * 
	 * @param deviceRequest the new electric dashboard activity request
	 */
	private void fillDeviceRequestToElectricDashboard(DeviceRequest deviceRequest)
	{
		DeviceSearch deviceSearch = new DeviceSearch();

		// Electric Meter Search
		ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch();
		List<ElectricMeterLifecycleStateEnum> electricLifecycleStateEnumList =
				new ArrayList<ElectricMeterLifecycleStateEnum>();
		electricLifecycleStateEnumList.add(ElectricMeterLifecycleStateEnum.INSTALLED);
		electricMeterSearch.setElectricMeterLifecycleStateEnumList(electricLifecycleStateEnumList);

		// HAN Device Search
		HanDeviceSearch hanDeviceSearch = new HanDeviceSearch();
		List<HanLifecycleStateEnum> hanLifecycleStateEnumList = new ArrayList<HanLifecycleStateEnum>();
		hanLifecycleStateEnumList.add(HanLifecycleStateEnum.JOINED);
		hanDeviceSearch.setHanLifecycleStateEnumList(hanLifecycleStateEnumList);

		// LCM Search
		LCMSearch lcmSearch = new LCMSearch();
		List<ServicesTypePermissions> servicesType =
				fetchServiceTypePermissionsByServiceType(deviceRequest.getServiceTypeEnum());

		lcmSearch.setAlarmEnumList(servicesType.get(0).getDeviceTypePermissions().get(2).getAlarms());

		// Device Search
		deviceSearch.setElectricMeterSearch(electricMeterSearch);
		deviceSearch.setHanDeviceSearch(hanDeviceSearch);
		deviceSearch.setLcmSearch(lcmSearch);

		deviceRequest.setDeviceSearch(deviceSearch);
	}

	/**
	 * Gets the alarms type count.
	 * 
	 * @param deviceRequest the device request
	 * @return the alarms type count
	 */
	private List<AlarmsTypesCount> getAlarmsTypeCount(DeviceRequest deviceRequest)
	{
		return getDeviceBCF().fetchAlarmsTypesCount(deviceRequest).getAlarmsTypesCount();
	}

	/**
	 * Gets the devices type count.
	 * 
	 * @param deviceRequest the device request
	 * @return the devices type count
	 */
	private List<DeviceTypeCount> getDevicesTypeCount(DeviceRequest deviceRequest)
	{
		return getDeviceBCF().fetchAllDevicesTypeCount(deviceRequest).getDeviceTypeCountList();
	}

	/**
	 * Gets the water gas meter status count.
	 * 
	 * @param deviceRequest the device request
	 * @return the water gas meter status count
	 */
	private List<WaterGasMeterStatusCount> getWaterGasMeterStatusCount(DeviceRequest deviceRequest)
	{
		return getWaterMeterBCF().fetchCommunication(deviceRequest).getWaterGasMeterStatusCount();
	}

	/**
	 * Gets the quarantine amount.
	 * 
	 * @param deviceRequest the device request
	 * @return the quarantine amount
	 */
	private Integer getQuarantineAmount(DeviceRequest deviceRequest)
	{
		return getDeviceBCF().fetchQuarantineCount(deviceRequest).getQuarantineAmount();
	}
}
