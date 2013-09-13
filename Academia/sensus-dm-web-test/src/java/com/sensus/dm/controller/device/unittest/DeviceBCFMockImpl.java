package com.sensus.dm.controller.device.unittest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.base.model.DeviceTypeModelPermissions;
import com.sensus.dm.common.base.model.DeviceTypePermissions;
import com.sensus.dm.common.base.model.ServicesPermissions;
import com.sensus.dm.common.base.model.ServicesTypePermissions;
import com.sensus.dm.common.base.model.UiModulesPermissions;
import com.sensus.dm.common.device.bcf.IDeviceBCF;
import com.sensus.dm.common.device.model.Alarm;
import com.sensus.dm.common.device.model.AlarmEnum;
import com.sensus.dm.common.device.model.AlarmsTypesCount;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceTypeCount;
import com.sensus.dm.common.device.model.GeocodeDeviceInfo;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryGeocodeDeviceInfoResponse;
import com.sensus.dm.controller.unittest.util.BaseMockImpl;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterConfiguration;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStateEnum;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;
import com.sensus.dm.elec.device.model.HanLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMTypeEnum;

/**
 * The Class DeviceBCFMockImpl.
 */
public class DeviceBCFMockImpl extends BaseMockImpl implements IDeviceBCF
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAllByPremiseId(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllByPremiseId(DeviceRequest deviceRequest)
	{
		DeviceResponse deviceResponse = new DeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{

			deviceResponse.setDevices(new ArrayList<Device>());

			for (int i = 0; i < 3; i++)
			{
				ElectricMeter eletricMeter = new ElectricMeter();
				HanDevice hanDevice = new HanDevice();
				Radio radio = new Radio();
				DeviceModel deviceModel = new DeviceModel();
				ElectricMeterConfiguration meterConfiguration = new ElectricMeterConfiguration();
				if (i == 0)
				{
					deviceModel.setDescription("iConA");
					radio.setFlexNetId(BigInteger.valueOf(4234560));
					radio.setCreateDate(new Date());
					eletricMeter.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
					eletricMeter.setDeviceId("4234560M");
					eletricMeter.setLifecycleStateEnum(ElectricMeterLifecycleStateEnum.INSTALLED);
					eletricMeter.setRadio(radio);
					eletricMeter.setDeviceModel(deviceModel);
					eletricMeter.setConfiguration(meterConfiguration);
				}
				else if (i == 1)
				{
					deviceModel.setDescription("UtilityPRO");
					radio.setFlexNetId(BigInteger.valueOf(254453289));
					radio.setCreateDate(new Date());
					hanDevice.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
					hanDevice.setDeviceId("4234560M.01");
					hanDevice.setLifecycleStateEnum(HanLifecycleStateEnum.JOINED);
					hanDevice.setRadio(radio);
					hanDevice.setDeviceModel(deviceModel);
					hanDevice.setHanDeviceTypeEnum(HanDeviceTypeEnum.THERMOSTAT);
				}
				else
				{
					deviceModel.setDescription("Comverge PowerPortal");
					radio.setFlexNetId(BigInteger.valueOf(254453223));
					radio.setCreateDate(new Date());
					hanDevice.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
					hanDevice.setDeviceId("4234560M.02");
					hanDevice.setLifecycleStateEnum(HanLifecycleStateEnum.PENDING_JOIN);
					hanDevice.setRadio(radio);
					hanDevice.setDeviceModel(deviceModel);
					hanDevice.setHanDeviceTypeEnum(HanDeviceTypeEnum.IHD);
				}

				List<Device> devices = new ArrayList<Device>();

				devices.add(eletricMeter);
				devices.add(hanDevice);

				deviceResponse.setDevices(devices);
			}

			deviceResponse.setOperationSuccess(true);
			deviceResponse.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			return deviceResponse;
		}

		return (DeviceResponse)testOtherDefaultModes(deviceResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.IDeviceBCF#fetchServicesByDeviceType(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchServicesByDeviceType(DeviceRequest deviceRequest)
	{
		DeviceResponse deviceResponse = new DeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<ServicesTypePermissions> servicesTypePermissionList = new ArrayList<ServicesTypePermissions>();
			ServicesTypePermissions serviceTypePermissions;

			List<ServiceTypeEnum> serviceTypeEnumList = new ArrayList<ServiceTypeEnum>();
			serviceTypeEnumList.add(ServiceTypeEnum.ELECTRIC);
			serviceTypeEnumList.add(ServiceTypeEnum.WATER);
			serviceTypeEnumList.add(ServiceTypeEnum.GAS);

			for (ServiceTypeEnum serviceTypeEnum : serviceTypeEnumList)
			{
				serviceTypePermissions = new ServicesTypePermissions();

				serviceTypePermissions.setServiceTypeEnum(serviceTypeEnum);
				serviceTypePermissions.setListActions(getActions(serviceTypeEnum));
				serviceTypePermissions.setScheduleActions(getActions(serviceTypeEnum));
				serviceTypePermissions.setEventHistoryFilterActions(getActions(serviceTypeEnum));
				serviceTypePermissions.setDashboardUiModules(getDashboardUiModules(serviceTypeEnum));
				serviceTypePermissions.setDeviceTypePermissions(getDeviceTypePermissionsList(serviceTypeEnum));

				servicesTypePermissionList.add(serviceTypePermissions);
			}

			deviceResponse.setServicesPermissions(new ServicesPermissions(servicesTypePermissionList));
		}

		return (DeviceResponse)testOtherDefaultModes(deviceResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAllDevicesTypeCount(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllDevicesTypeCount(DeviceRequest deviceRequest)
	{
		DeviceResponse deviceResponse = new DeviceResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			List<DeviceTypeCount> deviceTypeCountList = new ArrayList<DeviceTypeCount>(5);

			DeviceTypeCount deviceTypeCount = new DeviceTypeCount(new Device(DeviceTypeEnum.ELECTRIC_METER));
			deviceTypeCount.setId(1);
			deviceTypeCount.setDeviceCount(1900);
			deviceTypeCountList.add(deviceTypeCount);

			deviceTypeCount = new DeviceTypeCount(new LCM(LCMTypeEnum.FLEXNET_LCM));
			deviceTypeCount.setId(2);
			deviceTypeCount.setDeviceCount(50);
			deviceTypeCountList.add(deviceTypeCount);

			deviceTypeCount = new DeviceTypeCount(new LCM(LCMTypeEnum.LCM));
			deviceTypeCount.setId(3);
			deviceTypeCount.setDeviceCount(15);
			deviceTypeCountList.add(deviceTypeCount);

			deviceTypeCount = new DeviceTypeCount(new HanDevice(HanDeviceTypeEnum.IHD));
			deviceTypeCount.setId(4);
			deviceTypeCount.setDeviceCount(2);
			deviceTypeCountList.add(deviceTypeCount);

			deviceTypeCount = new DeviceTypeCount(new HanDevice(HanDeviceTypeEnum.THERMOSTAT));
			deviceTypeCount.setId(5);
			deviceTypeCount.setDeviceCount(1);
			deviceTypeCountList.add(deviceTypeCount);

			deviceResponse.setDeviceTypeCountList(deviceTypeCountList);

			return deviceResponse;
		}

		return (DeviceResponse)testOtherDefaultModes(deviceResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAlarmHistory(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public DeviceResponse fetchAlarmHistory(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{

			Alarm alarm;

			List<Alarm> alarmList = new ArrayList<Alarm>();

			alarm = new Alarm();
			alarm.setAlarmEnum(AlarmEnum.BACK_FLOW);
			alarm.setAlarmTime(new Date());

			response.setAlarms(alarmList);

			return response;

		}
		return (DeviceResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.IDeviceBCF#fetchDevicesBoundsToMap(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryGeocodeDeviceInfoResponse fetchDevicesBoundsToMap(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InquiryGeocodeDeviceInfoResponse response = new InquiryGeocodeDeviceInfoResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{

			response.setGeocodeDeviceInfo(getListGeocode());
			response.setOperationSuccess(Boolean.TRUE);

			return response;
		}

		return (InquiryGeocodeDeviceInfoResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchDevicesToMap(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryGeocodeDeviceInfoResponse fetchDevicesToMap(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InquiryGeocodeDeviceInfoResponse response = new InquiryGeocodeDeviceInfoResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setGeocodeDeviceInfo(getListGeocode());
			response.setOperationSuccess(Boolean.TRUE);

			return response;
		}

		return (InquiryGeocodeDeviceInfoResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchDeviceByIdImport(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchDeviceByIdImport(DeviceRequest deviceRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAllDeviceTypeDescriptions(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllDeviceTypeDescriptions(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{

			List<DeviceModel> deviceModels = new ArrayList<DeviceModel>();

			deviceModels.add(new DeviceModel());

			response.setDeviceModels(deviceModels);

			return response;

		}
		return (DeviceResponse)testOtherDefaultModes(response);
	}

	/**
	 * Gets the list geocode.
	 * 
	 * @return the list geocode
	 */
	private List<GeocodeDeviceInfo> getListGeocode()
	{
		List<GeocodeDeviceInfo> listGeocode = new ArrayList<GeocodeDeviceInfo>();

		GeocodeDeviceInfo geoCodeDeviceInfo = new GeocodeDeviceInfo();
		geoCodeDeviceInfo.setBottomLeftLat(39.776611328125);
		geoCodeDeviceInfo.setBottomLeftLon(-117.10418701171875);
		geoCodeDeviceInfo.setTopRightLat(39.776611328125);
		geoCodeDeviceInfo.setTopRightLon(-117.08699035644531);

		listGeocode.add(geoCodeDeviceInfo);

		return listGeocode;
	}

	/* ** TABS ** */
	/**
	 * Gets the electric meter detail modules tabs.
	 * 
	 * @return the electric meter detail modules tabs
	 */
	public static List<String> getElectricMeterDetailModulesTabs()
	{

		String[] array = {"aboutThisDevice", "history", "snapshot", "loadProfile", "demandReads", "tou"};

		return Arrays.asList(array);
	}

	/**
	 * Gets the han lcm detail module tabs.
	 * 
	 * @return the han lcm detail module tabs
	 */
	public static List<String> getHanLcmDetailModuleTabs()
	{

		String[] array = {"aboutThisDevice", "history"};

		return Arrays.asList(array);
	}

	/**
	 * Gets the water or gas meter detail module tabs.
	 * 
	 * @return the water or gas meter detail module tabs
	 */
	public static List<String> getWaterOrGasMeterDetailModuleTabs()
	{

		String[] array = {"aboutThisDevice", "readData", "communications", "history"};

		return Arrays.asList(array);
	}

	/* ** SUMMARY DATAS ** */
	/**
	 * Gets the electric meter detail modules summary datas.
	 * 
	 * @return the electric meter detail modules summary datas
	 */
	public static List<String> getElectricMeterDetailModulesSummaryDatas()
	{

		String[] array = {"messageReceived", "deviceInformation", "hour", "month", "peakDemand", "actions"};

		return Arrays.asList(array);
	}

	/**
	 * Gets the han device or lcm detail module summary datas.
	 * 
	 * @return the han device or lcm detail module summary datas
	 */
	public static List<String> getHanDeviceOrLcmDetailModuleSummaryDatas()
	{

		String[] array = {"networkStatus", "parent", "connectedDate", "deviceInformation", "actions"};

		return Arrays.asList(array);
	}

	/**
	 * Gets the water or gas meter detail module summary datas.
	 * 
	 * @return the water or gas meter detail module summary datas
	 */
	public static List<String> getWaterOrGasMeterDetailModuleSummaryDatas()
	{

		String[] array = {"messageReceived", "deviceInformation", "lifecycle", "transmit", "lastRead",
				"smartPoint", "actions"};

		return Arrays.asList(array);
	}

	/* ** CONTENTS ** */
	/**
	 * Gets the electric meter detail modules contents.
	 * 
	 * @return the electric meter detail modules contents
	 */
	public static List<String> getElectricMeterDetailModulesContents()
	{

		String[] array = {"group", "tag", "postNote", "location", "deviceInformation", "scheduledEvents", "hanDevices"};

		return Arrays.asList(array);
	}

	/**
	 * Gets the han device or lcm detail module contents.
	 * 
	 * @return the han device or lcm detail module contents
	 */
	public static List<String> getHanDeviceOrLcmDetailModuleContents()
	{

		String[] array = {"group", "tag", "postNote", "location", "deviceInformation", "scheduledEvents",
				"demandResponseProgramParticipation", "demandResponseSetup"};

		return Arrays.asList(array);
	}

	/**
	 * Gets the flex net lcm detail module contents.
	 * 
	 * @return the flex net lcm detail module contents
	 */
	public static List<String> getFlexNetLcmDetailModuleContents()
	{

		String[] array = {"alarmsflexnetLcm", "lcmRelay"};

		return Arrays.asList(array);
	}

	/**
	 * Gets the water meter detail module contents.
	 * 
	 * @return the water meter detail module contents
	 */
	public static List<String> getWaterMeterDetailModuleContents()
	{

		String[] array = {"group", "tag", "postNote", "location", "waterMeterDeviceInformation", "deviceDetails",
				"alarmsWaterMeter"};

		return Arrays.asList(array);
	}

	/**
	 * Gets the gas meter detail module contents.
	 * 
	 * @return the gas meter detail module contents
	 */
	public static List<String> getGasMeterDetailModuleContents()
	{

		String[] array = {"group", "tag", "postNote", "location", "gasMeterDeviceInformation", "deviceDetails",
				"alarmsGasMeter"};

		return Arrays.asList(array);
	}

	/**
	 * Gets the actions.
	 * 
	 * @param serviceType the service type
	 * @return the actions
	 */
	public static List<ActionType> getActions(ServiceTypeEnum serviceType)
	{

		List<ActionType> listActionType = new ArrayList<ActionType>();

		ActionType actionType = new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT);
		actionType.setProtect(Boolean.TRUE);

		listActionType.add(actionType);

		return listActionType;
	}

	/**
	 * Gets the alarms.
	 * 
	 * @return the alarms
	 */
	public static List<AlarmEnum> getAlarms()
	{
		List<AlarmEnum> alarmsList = new ArrayList<AlarmEnum>();

		alarmsList.add(AlarmEnum.BACK_FLOW);
		alarmsList.add(AlarmEnum.BATTERY_DOOR_TAMPER);

		return alarmsList;
	}

	/**
	 * Gets the dashboard ui modules.
	 * 
	 * @param serviceType the service type
	 * @return the dashboard ui modules
	 */
	public static List<String> getDashboardUiModules(ServiceTypeEnum serviceType)
	{
		List<String> dashboardUiModules = new ArrayList<String>();

		dashboardUiModules.add("activity");
		dashboardUiModules.add("savedSearches");
		dashboardUiModules.add("eventsToday");

		return dashboardUiModules;
	}

	/**
	 * Gets the device type permissions list.
	 * 
	 * @param serviceType the service type
	 * @return the device type permissions list
	 */
	public static List<DeviceTypePermissions> getDeviceTypePermissionsList(ServiceTypeEnum serviceType)
	{
		List<DeviceTypePermissions> deviceTypePermissionsList = new ArrayList<DeviceTypePermissions>();

		switch (serviceType)
		{
			case ELECTRIC:

				deviceTypePermissionsList.add(getDeviceTypePermissions(DeviceTypeEnum.ELECTRIC_METER));
				deviceTypePermissionsList.add(getDeviceTypePermissions(DeviceTypeEnum.HAN_DEVICE));
				deviceTypePermissionsList.add(getDeviceTypePermissions(DeviceTypeEnum.LCM));
				break;

			case WATER:

				deviceTypePermissionsList.add(getDeviceTypePermissions(DeviceTypeEnum.WATER_METER));
				break;

			case GAS:

				deviceTypePermissionsList.add(getDeviceTypePermissions(DeviceTypeEnum.GAS_METER));
				break;
		}

		return deviceTypePermissionsList;
	}

	/**
	 * Gets the device type models.
	 * 
	 * @param deviceType the device type
	 * @return the device type models
	 */
	public static List<DeviceTypeModelPermissions> getDeviceTypeModels(DeviceTypeEnum deviceType)
	{
		List<DeviceTypeModelPermissions> deviceTypeModelPermissionsList = new ArrayList<DeviceTypeModelPermissions>();

		switch (deviceType)
		{
			case ELECTRIC_METER:

				deviceTypeModelPermissionsList.add(new DeviceTypeModelPermissions(
						null,
						new UiModulesPermissions(
								getElectricMeterDetailModulesTabs(),
								getElectricMeterDetailModulesSummaryDatas(),
								getElectricMeterDetailModulesContents()),
						"COMMON"));
				break;

			case HAN_DEVICE:

				deviceTypeModelPermissionsList.add(new DeviceTypeModelPermissions(
						null,
						new UiModulesPermissions(
								getHanLcmDetailModuleTabs(),
								getHanDeviceOrLcmDetailModuleSummaryDatas(),
								getHanDeviceOrLcmDetailModuleContents()),
						"COMMON"));
				break;

			case LCM:

				deviceTypeModelPermissionsList
						.add(new DeviceTypeModelPermissions(
								null,
								new UiModulesPermissions(
										getHanLcmDetailModuleTabs(),
										getHanDeviceOrLcmDetailModuleSummaryDatas(),
										getHanDeviceOrLcmDetailModuleContents()),
								"COMMON"));

				deviceTypeModelPermissionsList.add(new DeviceTypeModelPermissions(null,
						new UiModulesPermissions(null, null, null), "LCM"));

				deviceTypeModelPermissionsList.add(new DeviceTypeModelPermissions(
						null,
						new UiModulesPermissions(null, null, getFlexNetLcmDetailModuleContents()), "FLEXNET_LCM"));
				break;

			case WATER_METER:

				deviceTypeModelPermissionsList
						.add(new DeviceTypeModelPermissions(
								null,
								new UiModulesPermissions(
										getWaterOrGasMeterDetailModuleTabs(),
										getWaterOrGasMeterDetailModuleSummaryDatas(),
										getWaterMeterDetailModuleContents()),
								"COMMON"));
				break;

			case GAS_METER:

				deviceTypeModelPermissionsList
						.add(new DeviceTypeModelPermissions(
								null,
								new UiModulesPermissions(
										getWaterOrGasMeterDetailModuleTabs(),
										getWaterOrGasMeterDetailModuleSummaryDatas(),
										getGasMeterDetailModuleContents()),
								"COMMON"));
				break;
		}

		return deviceTypeModelPermissionsList;
	}

	/**
	 * Gets the device type permissions.
	 * 
	 * @param deviceType the device type
	 * @return the device type permissions
	 */
	public static DeviceTypePermissions getDeviceTypePermissions(DeviceTypeEnum deviceType)
	{
		DeviceTypePermissions deviceTypePermissions = new DeviceTypePermissions();

		deviceTypePermissions.setAlarms(getAlarms());
		deviceTypePermissions.setDeviceType(deviceType);
		deviceTypePermissions.setDeviceTypeModels(getDeviceTypeModels(deviceType));

		return deviceTypePermissions;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchQuarantineCount(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchQuarantineCount(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			response.setQuarantineAmount(100);
		}

		return (DeviceResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAlarmsTypesCount(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAlarmsTypesCount(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			List<AlarmsTypesCount> alarmsTypesCount = new ArrayList<AlarmsTypesCount>();

			alarmsTypesCount.add(new AlarmsTypesCount(AlarmEnum.RELAY_CURRENT_TAMPER, 1));
			alarmsTypesCount.add(new AlarmsTypesCount(AlarmEnum.RELAY_VOLTAGE_TAMPER, 2));

			response.setAlarmsTypesCount(alarmsTypesCount);

			return response;
		}

		return (DeviceResponse)testOtherDefaultModes(response);
	}
}