package com.sensus.dm.controller.electricdevice;

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

import com.sensus.cbof.model.Device;
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
import com.sensus.dm.controller.util.DMUtil;
import com.sensus.dm.elec.device.bcf.IElectricMeterBCF;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterSearch;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanDeviceSearch;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMSearch;
import com.sensus.dm.elec.device.model.LCMTypeEnum;

/**
 * The Class ElectricMeterViewController.
 */
@Controller
@RequestMapping("/electriclist")
public class ElectricViewController extends DeviceBaseController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ElectricViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "ElectricMeterViewController";

	/** The Constant DEVICE_SUBTYPE. */
	private static final String DEVICE_SUBTYPE = "device_subtype";

	/** The Constant LIFECYCLE_STATE. */
	private static final String LIFECYCLE_STATE = "lifecycle_state";

	/** The Constant LOAD_LIST. */
	private static final String LOAD_LIST = "";

	/** The Constant VIEW_ELECTRIC_MAIN. */
	private static final String VIEW_ELECTRIC_MAIN = "/device/electric_main";

	/** The electric meter bcf. */
	private IElectricMeterBCF electricMeterBCF;

	/**
	 * Gets the electric meter bcf.
	 * 
	 * @return the electric meter bcf
	 */
	public IElectricMeterBCF getElectricMeterBCF()
	{
		return electricMeterBCF;
	}

	/**
	 * Sets the electric meter bcf.
	 * 
	 * @param electricMeterBCF the new electric meter bcf
	 */
	@Resource
	public void setElectricMeterBCF(IElectricMeterBCF electricMeterBCF)
	{
		this.electricMeterBCF = electricMeterBCF;
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
		ModelAndView modelAndView = new ModelAndView(VIEW_ELECTRIC_MAIN);
		modelAndView.addObject(SERVICE_TYPE, getServiceType());

		try
		{
			Map<String, String[]> parameters = request.getParameterMap();

			// Device Types
			List<DeviceTypeEnum> deviceTypeList = DMParamConvertUtil.fillDeviceTypes(parameters.get("device_type"));

			// Whether device types is null set ELECTRIC as default
			if (ValidationUtil.isNull(deviceTypeList))
			{
				deviceTypeList = new ArrayList<DeviceTypeEnum>(1);
				deviceTypeList.add(DeviceTypeEnum.ELECTRIC_METER);
			}

			// HAN Sub Types
			List<HanDeviceTypeEnum> hanTypeList = DMParamConvertUtil.fillHANSubTypes(parameters.get(DEVICE_SUBTYPE));

			modelAndView.addObject(SESSION, getMapper().writeValueAsString(loadSessionColumnFilter(request)));
			modelAndView.addObject(COLUMN_FILTER, getMapper().writeValueAsString(
					loadColumnFilters(ValidationUtil.isNullOrEmpty(hanTypeList) ?
							deviceTypeList.get(0).toString() : String.valueOf(hanTypeList.get(0)))));

			// Whether is initial load or saved search request
			// Check whether has initial load or not
			if (!isInitialLoad(request, modelAndView))
			{
				return modelAndView;
			}

			DeviceSearch deviceSearch = new DeviceSearch();
			InquiryDeviceRequest inquiryDeviceRequest = new InquiryDeviceRequest();
			InquiryDeviceResponse inquiryDeviceResponse = null;

			inquiryDeviceRequest.setDeviceSearch(deviceSearch);

			// Tags
			deviceSearch.setTags(DMParamConvertUtil.fillTags(parameters.get("tag")));

			// Groups
			deviceSearch.setGroups(DMParamConvertUtil.fillGroups(parameters.get("group")));

			// Process ID
			deviceSearch.setProcessId(DMParamConvertUtil.fillProcessId(parameters.get("processId")));

			// Sort Expression
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

			inquiryDeviceRequest.setDeviceType(deviceTypeList.get(0));
			deviceSearch.setDeviceTypes(deviceTypeList);

			switch (inquiryDeviceRequest.getDeviceType())
			{

				case ELECTRIC_METER:

					ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch();
					ElectricMeter electricMeter = new ElectricMeter();

					deviceSearch.setElectricMeterSearch(electricMeterSearch);
					electricMeterSearch.setElectricMeter(electricMeter);

					// Quarantine
					electricMeter.setQuarantine(DMParamConvertUtil.fillQuarantine(parameters.get("quarantine")));

					// ELECTRIC Life Cycle State
					electricMeterSearch.setElectricMeterLifecycleStateEnumList(DMParamConvertUtil
							.fillElectricLifecycleState(parameters.get(LIFECYCLE_STATE)));

					// Fetch All Electric Meters
					inquiryDeviceResponse = getElectricMeterBCF().fetchAllElectricMeters(inquiryDeviceRequest);

					// Convert Time Zone to minutes and set in the displayMinutes field
					DMConvertUtil.convertTimeZoneToMinutes(inquiryDeviceResponse.getDevices());

					break;

				case HAN_DEVICE:

					HanDeviceSearch hanDeviceSearch = new HanDeviceSearch();

					deviceSearch.setHanDeviceSearch(hanDeviceSearch);

					// HAn Sub Type
					hanDeviceSearch.setHanDeviceTypeEnumList(DMParamConvertUtil.fillHANSubTypes(parameters
							.get(DEVICE_SUBTYPE)));

					// HAN Life Cycle State
					hanDeviceSearch.setHanLifecycleStateEnumList(DMParamConvertUtil
							.fillHanLifecycleState(parameters.get(LIFECYCLE_STATE)));

					// Fetch all HanDevices
					inquiryDeviceResponse = getElectricMeterBCF().fetchAllHanDevices(inquiryDeviceRequest);

					// Convert MacAddress
					if (!ValidationUtil.isNull(inquiryDeviceResponse.getDevices()))
					{
						HanDevice hanDevice;

						for (Device device : inquiryDeviceResponse.getDevices())
						{
							hanDevice = (HanDevice)device;
							hanDevice.setMacAddress(DMUtil.convertBigInteger(hanDevice.getRadio().getFlexNetId()));
						}
					}

					break;

				case LCM:

					LCMSearch lcmSearch = new LCMSearch();

					deviceSearch.setLcmSearch(lcmSearch);

					// LCM Sub Type
					lcmSearch.setLcmTypeEnumList(DMParamConvertUtil.fillLCMSubTypes(parameters
							.get(DEVICE_SUBTYPE)));

					// LCM Life Cycle State
					lcmSearch.setLcmLifecycleStateEnumList(DMParamConvertUtil
							.fillLcmLifecycleState(parameters.get(LIFECYCLE_STATE)));

					// LCM Alarms
					lcmSearch.setAlarmEnumList(DMParamConvertUtil.fillAlarms(parameters.get("alarm")));

					// Fetch All LCMs
					inquiryDeviceResponse = getElectricMeterBCF().fetchAllLCMs(inquiryDeviceRequest);

					// Convert MacAddress
					if (!ValidationUtil.isNull(inquiryDeviceResponse.getDevices()))
					{
						LCM lcm;

						for (Device device : inquiryDeviceResponse.getDevices())
						{
							lcm = (LCM)device;

							if (!LCMTypeEnum.FLEXNET_LCM.equals(lcm.getLcmTypeEnum()))
							{
								lcm.setMacAddress(DMUtil.convertBigInteger(lcm.getRadio().getFlexNetId()));
							}
							else
							{
								lcm.setMacAddress(lcm.getRadio().getFlexNetId().toString());
							}
						}
					}

					break;
			}

			if (!ValidationUtil.isNull(inquiryDeviceResponse))
			{
				modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(inquiryDeviceResponse));
			}
		}
		catch (Exception e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}
}