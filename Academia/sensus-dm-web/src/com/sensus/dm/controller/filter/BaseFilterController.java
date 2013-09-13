package com.sensus.dm.controller.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.scheduler.model.FrequencyTypeEnum;
import com.sensus.common.scheduler.model.ScheduleStatusEnum;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionCategoryEnum;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.base.model.DeviceTypePermissions;
import com.sensus.dm.common.base.model.SearchTypeEnum;
import com.sensus.dm.common.base.model.ServicesTypePermissions;
import com.sensus.dm.common.device.bcf.IDeviceBCF;
import com.sensus.dm.common.device.model.AlarmEnum;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.group.bcf.IGroupBCF;
import com.sensus.dm.common.group.model.GroupTypeEnum;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;
import com.sensus.dm.common.group.model.response.InquiryGroupResponse;
import com.sensus.dm.common.process.bcf.IProcessTypeBCF;
import com.sensus.dm.common.process.model.ProcessCategory;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.tag.bcf.ITagBCF;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.response.InquiryTagResponse;
import com.sensus.dm.controller.base.BaseViewController;
import com.sensus.dm.controller.util.DMUtil;
import com.sensus.dm.elec.action.bcf.IActionBCF;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStateEnum;
import com.sensus.dm.elec.device.model.HanDeviceSearch;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;
import com.sensus.dm.elec.device.model.HanLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCMLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCMTypeEnum;
import com.sensus.dm.elec.device.model.RemoteConnectStatusEnum;
import com.sensus.dm.elec.settings.bcf.ISettingsBCF;
import com.sensus.dm.elec.settings.model.response.InquiryUserResponse;
import com.sensus.dm.water.device.model.WaterGasMeterStatusEnum;

/**
 * The Class BaseFilterController.
 */
public class BaseFilterController extends BaseViewController
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant ONE. */
	private static final int ONE = 1;

	/** The Constant TWO. */
	private static final int TWO = 2;

	/** The Constant ACTION_CATEGORIES. */
	public static final String ACTION_CATEGORIES = "action_categories";

	/** The Constant ALARMS. */
	public static final String ALARM = "alarm";

	/** The Constant ALL_ACTION_CATEGORIES. */
	public static final String ALL_ACTION_CATEGORIES = "all_action_categories";

	/** The Constant DESCRIPTION. */
	public static final String DESCRIPTION = "description";

	/** The Constant DEVICE_TYPE. */
	public static final String DEVICE_SUBTYPE = "device_subtype";

	/** The Constant FILTERS. */
	public static final String FILTERS = "filters";

	/** The Constant GROUPS. */
	public static final String GROUP = "group";

	/** The Constant GROUP_DEVICE_TYPE. */
	public static final String GROUP_DEVICE_TYPE = "group_device_type";

	/** The Constant GROUP_TYPE. */
	public static final String GROUP_TYPE = "group_type";

	/** The Constant HAN_DEVICE. */
	private static final String HAN_DEVICE = "HAN_DEVICE";

	/** The Constant LCM. */
	private static final String LCM = "LCM";

	/** The Constant LIFECYCLE_STATE. */
	public static final String LIFECYCLE_STATE = "lifecycle_state";

	/** The Constant LIFECYCLE_STATE_INSTALLED. */
	private static final String LIFECYCLE_STATE_INSTALLED = "filter.lifecycle_state.installed";

	/** The Constant LIFECYCLE_STATE_JOINED. */
	private static final String LIFECYCLE_STATE_JOINED = "filter.lifecycle_state.joined";

	/** The Constant LIFECYCLE_STATE_PENDING_JOIN. */
	private static final String LIFECYCLE_STATE_PENDING_JOIN = "filter.lifecycle_state.pending_join";

	/** The Constant LIFECYCLE_STATE_UNJOINED. */
	private static final String LIFECYCLE_STATE_UNJOINED = "filter.lifecycle_state.unjoined";

	/** The Constant LIFECYCLE_STATE_UNKNOWN. */
	private static final String LIFECYCLE_STATE_UNKNOWN = "filter.lifecycle_state.unknown";

	/** The Constant NAME. */
	public static final String NAME = "name";

	/** The Constant PAGES_BILLING. */
	private static final String PAGES_BILLING = "commons.pages.billing";

	/** The Constant PAGES_OPERATIONS. */
	private static final String PAGES_OPERATIONS = "commons.pages.operations";

	/** The Constant PAGES_OTHER. */
	private static final String PAGES_OTHER = "commons.pages.other";

	/** The Constant QUARANTINE. */
	public static final String QUARANTINE = "quarantine";

	/** The Constant REMOTE_DISCONNECT. */
	public static final String REMOTE_DISCONNECT = "remote_disconnect";

	/** The Constant REPEATS. */
	public static final String REPEATS = "repeats";

	/** The Constant SCHEDULE_ACTION_CATEGORIES. */
	public static final String SCHEDULE_ACTION_CATEGORIES = "schedule_action_categories";

	/** The Constant SEARCH. */
	public static final String SEARCH = "search";

	/** The Constant STATUS_METER. */
	public static final String STATUS_METER = "status_meter";

	/** The Constant STATUS_SCHEDULED. */
	public static final String STATUS_SCHEDULED = "status_scheduled";

	/** The Constant TAG. */
	public static final String TAG = "tag";

	/** The Constant USERS. */
	public static final String USERS = "users";

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/** The settings bcf. */
	private ISettingsBCF settingsBCF;

	/** The device bcf. */
	private IDeviceBCF deviceBCF;

	/** The action bcf. */
	private IActionBCF actionBCF;

	/** The process type bcf. */
	private IProcessTypeBCF processTypeBCF;

	/**
	 * Gets the process type bcf.
	 * 
	 * @return the process type bcf
	 */
	public IProcessTypeBCF getProcessTypeBCF()
	{
		return processTypeBCF;
	}

	/**
	 * Sets the process type bcf.
	 * 
	 * @param processTypeBCF the new process type bcf
	 */
	@Resource
	public void setProcessTypeBCF(IProcessTypeBCF processTypeBCF)
	{
		this.processTypeBCF = processTypeBCF;
	}

	/**
	 * Gets the group bcf.
	 * 
	 * @return the group bcf
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param groupBCF the new group bcf
	 */
	@Resource
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Gets the tag bcf.
	 * 
	 * @return the tagBCF
	 */
	public ITagBCF getTagBCF()
	{
		return tagBCF;
	}

	/**
	 * Sets the tag bcf.
	 * 
	 * @param tagBCF the tagBCF to set
	 */
	@Resource
	public void setTagBCF(ITagBCF tagBCF)
	{
		this.tagBCF = tagBCF;
	}

	/**
	 * Gets the settings bcf.
	 * 
	 * @return the settingsBCF
	 */
	public ISettingsBCF getSettingsBCF()
	{
		return settingsBCF;
	}

	/**
	 * Sets the settings bcf.
	 * 
	 * @param settingsBCF the settingsBCF to set
	 */
	@Resource
	public void setSettingsBCF(ISettingsBCF settingsBCF)
	{
		this.settingsBCF = settingsBCF;
	}

	/**
	 * Gets the device bcf.
	 * 
	 * @return the device bcf
	 */
	public IDeviceBCF getDeviceBCF()
	{
		return deviceBCF;
	}

	/**
	 * Sets the device bcf.
	 * 
	 * @param deviceBCF the new device bcf
	 */
	@Resource
	public void setDeviceBCF(IDeviceBCF deviceBCF)
	{
		this.deviceBCF = deviceBCF;
	}

	/**
	 * Gets the action bcf.
	 * 
	 * @return the actionBCF
	 */
	public IActionBCF getActionBCF()
	{
		return actionBCF;
	}

	/**
	 * Sets the action bcf.
	 * 
	 * @param actionBCF the actionBCF to set
	 */
	@Resource
	public void setActionBCF(IActionBCF actionBCF)
	{
		this.actionBCF = actionBCF;
	}

	/**
	 * Creates the filter group device type.
	 * 
	 * @param response the response
	 * @param servletRequest the servlet request
	 */
	public void createFilterGroupDeviceType(HashMap<String, Object> response, HttpServletRequest servletRequest)
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(HanDeviceTypeEnum.IHD.toString(),
				getText("commons.pages.IHD", servletRequest.getLocale())));

		records.add(getKeyValue(HanDeviceTypeEnum.THERMOSTAT.toString(),
				getText("commons.pages.THERMOSTAT", servletRequest.getLocale())));

		records.add(getKeyValue(DeviceTypeEnum.ELECTRIC_METER.toString(),
				getText("commons.pages.ELECTRIC_METER", servletRequest.getLocale())));

		records.add(getKeyValue(DeviceTypeEnum.LCM.toString(),
				getText("commons.pages.LCM", servletRequest.getLocale())));

		sortFilterRecords(records);

		response.put(GROUP_DEVICE_TYPE, records);
	}

	/**
	 * Creates the filter group.
	 * 
	 * @param response the response
	 * @param deviceTypeEnums the device type enums
	 * @param deviceSubTypes the device sub types
	 */
	public void createFilterGroup(Map<String, Object> response, List<String> deviceTypeEnums,
			List<String> deviceSubTypes)
	{
		InquiryGroupRequest inquiryGroupRequest = new InquiryGroupRequest();

		inquiryGroupRequest.getSortExpressions().add(new SortExpression(NAME, Direction.Ascending));

		DeviceSearch deviceSearch = new DeviceSearch(DeviceTypeEnum.valueOf(deviceTypeEnums.get(ZERO)));

		fillHanDeviceSearch(deviceSearch, deviceSubTypes);

		inquiryGroupRequest.setDeviceSearch(deviceSearch);

		addUserContextToRequest(inquiryGroupRequest);

		InquiryGroupResponse inquiryGroupResponse = getGroupBCF().fetchAllGroups(inquiryGroupRequest);

		response.put(GROUP, inquiryGroupResponse.getGroups());
	}

	/**
	 * Creates the filter tag.
	 * 
	 * @param response the response
	 */
	public void createFilterTag(Map<String, Object> response)
	{
		InquiryTagRequest inquiryTagRequest = new InquiryTagRequest();
		inquiryTagRequest.getSortExpressions().add(new SortExpression(NAME, Direction.Ascending));

		addUserContextToRequest(inquiryTagRequest);

		InquiryTagResponse inquiryTagResponse = getTagBCF().fetchAllTags(inquiryTagRequest);

		response.put(TAG, inquiryTagResponse.getTags());
	}

	/**
	 * Creates the filter user.
	 * 
	 * @param response the response
	 */
	public void createFilterUser(Map<String, Object> response)
	{
		PropertyRequest propertyRequest = new PropertyRequest();

		addUserContextToRequest(propertyRequest);

		InquiryUserResponse inquiryUserResponse = getSettingsBCF().fetchAllUsers(propertyRequest);

		response.put(USERS, inquiryUserResponse.getUsers());
	}

	/**
	 * Creates the filter lifecycle state.
	 * 
	 * @param response the response
	 * @param servletRequest the servlet request
	 * @param deviceTypeEnums the device type enums
	 */
	public void createFilterLifecycleState(Map<String, Object> response, HttpServletRequest servletRequest,
			List<String> deviceTypeEnums)
	{
		List<String[]> records = new ArrayList<String[]>();

		String id = deviceTypeEnums.get(ZERO);

		if (!ValidationUtil.isNullOrEmpty(id))
		{
			if (id.equals("ELECTRIC_METER"))
			{
				records.add(getKeyValue(ElectricMeterLifecycleStateEnum.INSTALLED.toString(),
						getText(LIFECYCLE_STATE_INSTALLED, servletRequest.getLocale())));
				records.add(getKeyValue(ElectricMeterLifecycleStateEnum.INVENTORY.toString(),
						getText("filter.lifecycleState.Inventory", servletRequest.getLocale())));
				records.add(getKeyValue(ElectricMeterLifecycleStateEnum.NOT_ASSIGNED.toString(),
						getText("filter.lifecycleState.NotAssigned", servletRequest.getLocale())));
			}
			else if (id.equals(HAN_DEVICE))
			{
				records.add(getKeyValue(HanLifecycleStateEnum.JOINED.toString(),
						getText(LIFECYCLE_STATE_JOINED, servletRequest.getLocale())));
				records.add(getKeyValue(HanLifecycleStateEnum.UNJOINED.toString(),
						getText(LIFECYCLE_STATE_UNJOINED, servletRequest.getLocale())));
				records.add(getKeyValue(HanLifecycleStateEnum.UNKNOWN.toString(),
						getText(LIFECYCLE_STATE_UNKNOWN, servletRequest.getLocale())));
				records.add(getKeyValue(HanLifecycleStateEnum.PENDING_JOIN.toString(),
						getText(LIFECYCLE_STATE_PENDING_JOIN, servletRequest.getLocale())));
			}
			else if (id.equals(LCM))
			{
				records.add(getKeyValue(LCMLifecycleStateEnum.JOINED.toString(),
						getText(LIFECYCLE_STATE_JOINED, servletRequest.getLocale())));
				records.add(getKeyValue(LCMLifecycleStateEnum.UNJOINED.toString(),
						getText(LIFECYCLE_STATE_UNJOINED, servletRequest.getLocale())));
				records.add(getKeyValue(LCMLifecycleStateEnum.UNKNOWN.toString(),
						getText(LIFECYCLE_STATE_UNKNOWN, servletRequest.getLocale())));
				records.add(getKeyValue(LCMLifecycleStateEnum.PENDING_JOIN.toString(),
						getText(LIFECYCLE_STATE_PENDING_JOIN, servletRequest.getLocale())));
				records.add(getKeyValue(LCMLifecycleStateEnum.INSTALLED.toString(),
						getText(LIFECYCLE_STATE_INSTALLED, servletRequest.getLocale())));
			}
		}

		sortFilterRecords(records);

		response.put(LIFECYCLE_STATE, records);
	}

	/**
	 * Creates the filter alarms.
	 * 
	 * @param response the response
	 * @param servletRequest the servlet request
	 * @param deviceTypeEnums the device type enums
	 */
	public void createFilterAlarms(Map<String, Object> response, HttpServletRequest servletRequest,
			List<String> deviceTypeEnums)
	{

		List<String[]> records = new ArrayList<String[]>();

		String id = deviceTypeEnums.get(ZERO);

		if (!ValidationUtil.isNullOrEmpty(id))
		{
			// Get device type permission
			List<DeviceTypePermissions> listDeviceTypePermissions = (List<DeviceTypePermissions>)
					DMUtil.getDeviceTypePermissions(getServiceTypePermission().getDeviceTypePermissions(),
							DeviceTypeEnum.valueOf(id));

			if (!ValidationUtil.isNullOrEmpty(listDeviceTypePermissions))
			{
				DeviceTypePermissions deviceTypePermissions = listDeviceTypePermissions.get(0);

				// Create filters
				for (AlarmEnum alarm : deviceTypePermissions.getAlarms())
				{
					records.add(getKeyValue(alarm.toString(),
							getText("filter.alarm." + alarm.toString().toLowerCase(), servletRequest.getLocale())));
				}
			}
		}

		sortFilterRecords(records);

		response.put(ALARM, records);
	}

	/**
	 * Creates the filter remote disconnect.
	 * 
	 * @param response the response
	 * @param servletRequest the servlet request
	 */
	public void createFilterRemoteDisconnect(Map<String, Object> response, HttpServletRequest servletRequest)
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(RemoteConnectStatusEnum.ARMED.toString(),
				getText("commons.pages.ARMED", servletRequest.getLocale())));

		records.add(getKeyValue(RemoteConnectStatusEnum.CONNECT.toString(),
				getText("commons.pages.CONNECT", servletRequest.getLocale())));

		records.add(getKeyValue(RemoteConnectStatusEnum.DISCONNECT.toString(),
				getText("commons.pages.DISCONNECT", servletRequest.getLocale())));

		records.add(getKeyValue(RemoteConnectStatusEnum.UNKNOWN.toString(),
				getText("commons.pages.UNKNOWN", servletRequest.getLocale())));

		sortFilterRecords(records);

		response.put(REMOTE_DISCONNECT, records);
	}

	/**
	 * Creates the filter group type.
	 * 
	 * @param response the response
	 * @param servletRequest the servlet request
	 */
	public void createFilterGroupType(Map<String, Object> response, HttpServletRequest servletRequest)
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(GroupTypeEnum.BILLING.toString(),
				getText(PAGES_BILLING, servletRequest.getLocale())));
		records.add(getKeyValue(GroupTypeEnum.OPERATIONS.toString(),
				getText(PAGES_OPERATIONS, servletRequest.getLocale())));
		records.add(getKeyValue(GroupTypeEnum.ALL_OTHERS.toString(),
				getText(PAGES_OTHER, servletRequest.getLocale())));

		sortFilterRecords(records);

		response.put(GROUP_TYPE, records);
	}

	/**
	 * Creates the filter status scheduled.
	 * 
	 * @param response the response
	 * @param servletRequest the servlet request
	 */
	public void createFilterStatusScheduled(Map<String, Object> response, HttpServletRequest servletRequest)
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(ScheduleStatusEnum.ENABLED.toString(),
				getText("systemintelligence.scheduledEvent.status.ENABLED", servletRequest.getLocale())));
		records.add(getKeyValue(ScheduleStatusEnum.PAUSED.toString(),
				getText("systemintelligence.scheduledEvent.status.PAUSED", servletRequest.getLocale())));
		records.add(getKeyValue(ScheduleStatusEnum.FAILED.toString(),
				getText("systemintelligence.scheduledEvent.status.FAILED", servletRequest.getLocale())));

		sortFilterRecords(records);

		response.put(STATUS_SCHEDULED, records);
	}

	/**
	 * Creates the filter action type.
	 * 
	 * @param response the response
	 * @param servletRequest the servlet request
	 */
	public void createFilterScheduleActionCategories(Map<String, Object> response,
			HttpServletRequest servletRequest)
	{
		List<String[]> records = new ArrayList<String[]>();

		// Schedule Action Categories
		setActionCategory(records, getActionCategories(getServiceTypePermission().getScheduleActions()),
				servletRequest);

		response.put(SCHEDULE_ACTION_CATEGORIES, records);
	}

	/**
	 * Creates the filter action categories.
	 * 
	 * @param response the response
	 * @param servletRequest the servlet request
	 */
	public void createFilterActionCategories(Map<String, Object> response, HttpServletRequest servletRequest)
	{
		List<String[]> records = new ArrayList<String[]>();

		// List Action Categories
		setActionCategory(records, getActionCategories(getServiceTypePermission().getListActions()),
				servletRequest);

		sortFilterRecords(records);

		response.put(ACTION_CATEGORIES, records);
	}

	/**
	 * Creates the filter action type all.
	 * 
	 * @param response the response
	 * @param servletRequest the servlet request
	 */
	public void createFilterAllActionCategories(Map<String, Object> response, HttpServletRequest servletRequest)
	{
		List<String[]> records = new ArrayList<String[]>();

		// List Action Categories
		setActionCategory(records, getActionCategories(getServiceTypePermission().getEventHistoryFilterActions()),
				servletRequest);

		// Process Categories
		ProcessRequest processRequest = new ProcessRequest();
		addUserContextToRequest(processRequest);
		ProcessResponse processResponse = getProcessTypeBCF().fetchAllProcessCategory(processRequest);

		if (!ValidationUtil.isNull(processResponse)
				&& !ValidationUtil.isNullOrEmpty(processResponse.getProcessCategories()))
		{
			for (ProcessCategory category : processResponse.getProcessCategories())
			{
				records.add(getKeyValue(category.getId().toString(),
						getText(category.getName(), servletRequest.getLocale())));
			}
		}

		sortFilterRecords(records);

		// Add to response
		response.put(ALL_ACTION_CATEGORIES, records);
	}

	/**
	 * Creates the filter device type.
	 * 
	 * @param response the response
	 * @param servletRequest the servlet request
	 * @param deviceTypeEnums the device type enums
	 */
	public void createFilterDeviceType(Map<String, Object> response, HttpServletRequest servletRequest,
			List<String> deviceTypeEnums)
	{
		List<String[]> records = new ArrayList<String[]>();

		String id = deviceTypeEnums.get(ZERO);

		if (!ValidationUtil.isNullOrEmpty(id))
		{
			if (id.equals(HAN_DEVICE))
			{
				records.add(getKeyValue(HanDeviceTypeEnum.THERMOSTAT.toString(),
						getText("filter.device_subtype.thermostat", servletRequest.getLocale())));
				records.add(getKeyValue(HanDeviceTypeEnum.IHD.toString(),
						getText("filter.device_subtype.ihd", servletRequest.getLocale())));
			}
			else if (id.equals(LCM))
			{
				records.add(getKeyValue(LCMTypeEnum.LCM.toString(),
						getText("filter.device_subtype.lcm", servletRequest.getLocale())));
				records.add(getKeyValue(LCMTypeEnum.FLEXNET_LCM.toString(),
						getText("filter.device_subtype.flexnet_lcm", servletRequest.getLocale())));
			}
		}

		sortFilterRecords(records);

		response.put(DEVICE_SUBTYPE, records);
	}

	/**
	 * Creates the filter repeats.
	 * 
	 * @param response the response
	 * @param servletRequest the servlet request
	 */
	public void createFilterRepeats(Map<String, Object> response, HttpServletRequest servletRequest)
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(FrequencyTypeEnum.DAILY.toString(),
				getText("commons.pages.daily", servletRequest.getLocale())));
		records.add(getKeyValue(FrequencyTypeEnum.NEVER.toString(),
				getText("commons.pages.never", servletRequest.getLocale())));
		records.add(getKeyValue(FrequencyTypeEnum.EVERY_MON_WED_FRI.toString(),
				getText("commons.pages.every_mon_wed_fri", servletRequest.getLocale())));
		records.add(getKeyValue(FrequencyTypeEnum.EVERY_TUE_THURS.toString(),
				getText("commons.pages.every_tue_thurs", servletRequest.getLocale())));
		records.add(getKeyValue(FrequencyTypeEnum.EVERY_WEEKDAY.toString(),
				getText("commons.pages.every_weekday", servletRequest.getLocale())));
		records.add(getKeyValue(FrequencyTypeEnum.MONTHLY.toString(),
				getText("commons.pages.monthly", servletRequest.getLocale())));
		records.add(getKeyValue(FrequencyTypeEnum.WEEKLY.toString(),
				getText("commons.pages.weekly", servletRequest.getLocale())));
		records.add(getKeyValue(FrequencyTypeEnum.YEARLY.toString(),
				getText("commons.pages.yearly", servletRequest.getLocale())));

		sortFilterRecords(records);

		response.put(REPEATS, records);
	}

	/**
	 * Creates the filter search.
	 * 
	 * @param response the response
	 * @param servletRequest the servlet request
	 */
	public void createFilterSearch(Map<String, Object> response, HttpServletRequest servletRequest)
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(SearchTypeEnum.ACTION_NAME.toString(),
				getText("commons.pages.actionName", servletRequest.getLocale())));
		records.add(getKeyValue(SearchTypeEnum.ID.toString(),
				getText("commons.pages.id", servletRequest.getLocale())));
		records.add(getKeyValue(SearchTypeEnum.EVENT_ID.toString(),
				getText("commons.pages.eventId", servletRequest.getLocale())));
		records.add(getKeyValue(SearchTypeEnum.DEVICE_ID.toString(),
				getText("commons.pages.deviceId", servletRequest.getLocale())));
		records.add(getKeyValue(SearchTypeEnum.NETWORK_ADDRESS.toString(),
				getText("commons.pages.networkAddress", servletRequest.getLocale())));
		records.add(getKeyValue(SearchTypeEnum.ADDRESS.toString(),
				getText("commons.pages.address", servletRequest.getLocale())));
		records.add(getKeyValue(SearchTypeEnum.EVENT_NAME.toString(),
				getText("commons.pages.eventName", servletRequest.getLocale())));
		records.add(getKeyValue(SearchTypeEnum.PREMISE_ID.toString(),
				getText("commons.pages.premiseId", servletRequest.getLocale())));

		response.put(SEARCH, records);
	}

	/**
	 * Creates the filter status meter.
	 * 
	 * @param response the response
	 * @param servletRequest the servlet request
	 */
	public void createFilterStatusMeter(Map<String, Object> response, HttpServletRequest servletRequest)
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(WaterGasMeterStatusEnum.IDLE.toString(),
				getText("filter.status_meter.idle", servletRequest.getLocale())));
		records.add(getKeyValue(WaterGasMeterStatusEnum.WALK_BY_DRIVE_BY.toString(),
				getText("filter.status_meter.walk_by_drive_by", servletRequest.getLocale())));
		records.add(getKeyValue(WaterGasMeterStatusEnum.FIXED_BASE_LAT.toString(),
				getText("filter.status_meter.fixed_base_lat", servletRequest.getLocale())));
		records.add(getKeyValue(WaterGasMeterStatusEnum.FIXED_BASE_MOM.toString(),
				getText("filter.status_meter.fixed_base_mom", servletRequest.getLocale())));
		records.add(getKeyValue(WaterGasMeterStatusEnum.UNKNOWN.toString(),
				getText("filter.status_meter.unknown", servletRequest.getLocale())));

		response.put(STATUS_METER, records);
	}

	/**
	 * Fill han device search.
	 * 
	 * @param deviceSearch the device search
	 * @param deviceSubTypes the device sub types
	 */
	public void fillHanDeviceSearch(DeviceSearch deviceSearch, List<String> deviceSubTypes)
	{
		if (!ValidationUtil.isNullOrEmpty(deviceSubTypes)
				&& (deviceSearch.getFirstDeviceType() == DeviceTypeEnum.HAN_DEVICE))
		{
			deviceSearch.setHanDeviceSearch(new HanDeviceSearch());

			for (String deviceSubType : deviceSubTypes)
			{
				deviceSearch.getHanDeviceSearch().addHanDeviceTypeEnum(HanDeviceTypeEnum.valueOf(deviceSubType));
			}
		}
	}

	/**
	 * Creates the filter electric description.
	 * 
	 * @param response the response
	 * @param deviceTypeEnums the device type enums
	 * @param deviceSubTypes the device sub types
	 */
	public void createFilterElectricDescription(Map<String, Object> response, List<String> deviceTypeEnums,
			List<String> deviceSubTypes)
	{
		DeviceRequest deviceRequest = new DeviceRequest();

		DeviceSearch deviceSearch = new DeviceSearch(DeviceTypeEnum.valueOf(deviceTypeEnums.get(ZERO)));

		fillHanDeviceSearch(deviceSearch, deviceSubTypes);

		deviceRequest.setDeviceSearch(deviceSearch);

		addUserContextToRequest(deviceRequest);

		DeviceResponse deviceResponse = getDeviceBCF().fetchAllDeviceTypeDescriptions(deviceRequest);

		response.put(DESCRIPTION, deviceResponse.getDeviceModels());
	}

	/**
	 * Creates the filter quarantine.
	 * 
	 * @param response the response
	 * @param servletRequest the servlet request
	 */
	public void createFilterQuarantine(HashMap<String, Object> response, HttpServletRequest servletRequest)
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(Boolean.TRUE.toString(),
				getText("filter.quarantine.name", servletRequest.getLocale())));

		response.put(QUARANTINE, records);
	}

	/**
	 * Gets the key value.
	 * 
	 * @param id the id
	 * @param name the name
	 * @return the key value
	 */
	public String[] getKeyValue(String id, String name)
	{
		String[] record = new String[TWO];

		record[ZERO] = id.toString();
		record[ONE] = name;

		return record;
	}

	/**
	 * Sets the action category.
	 * 
	 * @param records the records
	 * @param actionCategories the action categories
	 * @param servletRequest the servlet request
	 */
	private void setActionCategory(List<String[]> records, List<ActionCategoryEnum> actionCategories,
			HttpServletRequest servletRequest)
	{
		for (ActionCategoryEnum category : actionCategories)
		{
			records.add(getKeyValue(category.getActionTypeCategoryName(),
					getText(category.getActionTypeCategoryName(), servletRequest.getLocale())));
		}
	}

	/**
	 * Gets the action categories.
	 * 
	 * @param actions the actions
	 * @return the action categories
	 */
	public List<ActionCategoryEnum> getActionCategories(List<ActionType> actions)
	{
		List<ActionCategoryEnum> actionCategories = new ArrayList<ActionCategoryEnum>();

		if (!ValidationUtil.isNullOrEmpty(actions))
		{
			ActionCategoryEnum actionCategory;
			Boolean isAdd;

			for (ActionType action : actions)
			{
				isAdd = true;
				actionCategory = action.getActionTypeEnum().getActionCategoryEnum();

				for (ActionCategoryEnum category : actionCategories)
				{
					if (category.equals(actionCategory))
					{
						isAdd = false;
						break;
					}
				}

				if (isAdd)
				{
					actionCategories.add(actionCategory);
				}
			}

			return actionCategories;
		}

		return actionCategories;
	}

	/**
	 * Gets the service type permission.
	 * 
	 * @return the service type permission
	 */
	public ServicesTypePermissions getServiceTypePermission()
	{
		// Fetch Services
		DeviceRequest deviceRequest = new DeviceRequest();
		deviceRequest.setGrantedAuthorityList(getUserSettings().getRoles());
		DeviceResponse deviceResponse = getDeviceBCF().fetchServicesByDeviceType(deviceRequest);

		if (!ValidationUtil.isNull(deviceResponse) && deviceResponse.isOperationSuccess()
				&& !ValidationUtil.isNull(deviceResponse.getServicesPermissions()))
		{
			List<ServicesTypePermissions> servicesTypePermissionsList =
					(List<ServicesTypePermissions>)DMUtil.getServiceTypePermissions(deviceResponse
							.getServicesPermissions()
							.getServicesTypePermissions(), ServiceTypeEnum.valueOf(getServiceType()));

			return servicesTypePermissionsList.get(0);
		}

		return new ServicesTypePermissions();
	}

	/**
	 * Sort.
	 * 
	 * @param filterRecords the filter records
	 */
	public void sortFilterRecords(List<String[]> filterRecords)
	{
		Collections.sort(filterRecords, new Comparator<String[]>()
		{
			@Override
			public int compare(String[] a, String[] b)
			{
				return a[1].compareTo(b[1]);
			}
		});
	}
}