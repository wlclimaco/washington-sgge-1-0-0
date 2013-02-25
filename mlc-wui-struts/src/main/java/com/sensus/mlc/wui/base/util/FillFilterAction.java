package com.sensus.mlc.wui.base.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.ecomode.model.EcoModeFilterEnum;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.response.InquiryGroupResponse;
import com.sensus.mlc.process.model.LCActionCategoryEnum;
import com.sensus.mlc.schedule.bcf.IScheduleBCF;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.mlc.settings.bcf.ISettingsBCF;
import com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF;
import com.sensus.mlc.smartpoint.model.LightPropertyForSearchEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.PropertyValidValue;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest;
import com.sensus.mlc.smartpoint.model.response.PropertyValidValuesResponse;
import com.sensus.mlc.tag.bcf.ITagBCF;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.response.InquiryTagResponse;
import com.sensus.mlc.user.bcf.IUserBCF;
import com.sensus.mlc.user.model.User;
import com.sensus.mlc.user.model.request.InquiryUserRequest;
import com.sensus.mlc.user.model.response.InquiryUserResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.smartpoint.model.ViewFilterLightTypes;

/**
 * The Class FillFilterAction. Used to Fill the filter component.
 * 
 * @author Raphael Constantino
 */
public class FillFilterAction extends ActionBase
{

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant TWO. */
	private static final Integer TWO = 2;

	/** The Constant TRUE. */
	private static final String TRUE = "true";

	/** The Constant FALSE. */
	private static final String FALSE = "false";

	/** The filter result. */
	private FilterJsonResult filterResult;

	/** The data. */
	private String data;

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/** The schedule bcf. */
	private IScheduleBCF scheduleBCF;

	/** The smart point bcf. */
	private ISmartPointAccessorBCF smartPointAccessorBCF;

	/** The settings bcf. */
	private ISettingsBCF settingsBCF;

	private IUserBCF userBCF;

	/**
	 * Fill filter.
	 * 
	 * @return the string
	 */
	public String fillFilter()
	{
		setFilterResult(new FilterJsonResult());

		try
		{
			String[] dataSplit = getData().split(",");

			for (String filter : dataSplit)
			{

				if (filter.equalsIgnoreCase("action_type"))
				{
					createFilterActionType();
				}

				if (filter.equalsIgnoreCase("alarm_type"))
				{
					createFilterAlarmType();
				}

				if (filter.equalsIgnoreCase("configuration"))
				{
					createFilterConfiguration();
				}

				if (filter.equalsIgnoreCase("ecomode"))
				{
					createFilterEcoMode();
				}

				if (filter.equalsIgnoreCase("event_schedule"))
				{
					createFilterEventSchedule();
				}

				if (filter.equalsIgnoreCase("event_type"))
				{
					createFilterEventType();
				}

				if (filter.equalsIgnoreCase("groups"))
				{
					createFilterGroup();
				}

				if (filter.equalsIgnoreCase("light_types"))
				{
					createFilterPropertyValidValues();
					// createFilterDimmable();
				}

				if (filter.equalsIgnoreCase("offset_schedule"))
				{
					createFilterOffsetSchedule();
				}

				if (filter.equalsIgnoreCase("search"))
				{
					createFilterSearch();
				}

				if (filter.equalsIgnoreCase("status"))
				{
					createFilterStatus();
				}

				if (filter.equalsIgnoreCase("tags"))
				{
					createFilterTag();
				}

				if (filter.equalsIgnoreCase("users"))
				{
					createFilterUser();
				}

				if (filter.equalsIgnoreCase("warning_type"))
				{
					createFilterWarningType();
				}

				if (filter.equalsIgnoreCase("color_temperature"))
				{
					createFilterColorTemperature();
				}

				if (filter.equalsIgnoreCase("housing_color"))
				{
					createFilterHousingColor();
				}

				if (filter.equalsIgnoreCase("voltage_range"))
				{
					createFilterVoltageRange();
				}

			}

		}
		catch (Exception e)
		{
			getFilterResult().setResult(Constants.JSON_FAIL);
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error Filtering", e);
			}
		}
		return SUCCESS;
	}

	/**
	 * Creates the filter group.
	 */
	private void createFilterGroup()
	{
		InquiryPaginationRequest request = new InquiryPaginationRequest(getUserContext());
		request.setPageSize(0);

		InquiryGroupResponse response = getGroupBCF().fetchAllGroups(request);

		if (response.isOperationSuccess())
		{

			List<String[]> records = new ArrayList<String[]>();

			if (!ValidationUtil.isNullOrEmpty(response.getGroups()))
			{
				for (Group group : response.getGroups())
				{
					records.add(getKeyValue(group.getId().toString(), group.getName()));
				}
			}

			/** Set JSON Result **/
			getFilterResult().setGroups(records.toArray(new String[records.size()][]));
		}
	}

	/**
	 * Creates the filter status.
	 */
	private void createFilterStatus()
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(LightStatusEnum.ACTIVE.getValue().toString(),
				getText("smartpoint.status.filter.active")));
		records.add(getKeyValue(LightStatusEnum.ALARM.getValue().toString(),
				getText("smartpoint.status.filter.error")));
		records.add(getKeyValue(LightStatusEnum.DEACTIVATED.getValue().toString(),
				getText("smartpoint.status.filter.deactivated")));
		records.add(getKeyValue(LightStatusEnum.MAINTENANCE.getValue().toString(),
				getText("smartpoint.status.filter.maintenance")));
		records.add(getKeyValue(LightStatusEnum.OVERRIDE.getValue().toString(),
				getText("smartpoint.status.filter.override")));
		records.add(getKeyValue(LightStatusEnum.WARNING.getValue().toString(),
				getText("smartpoint.status.filter.warning")));

		/** Set JSON Result **/
		getFilterResult().setStatus(records.toArray(new String[records.size()][]));

	}

	/**
	 * Creates the filter tag.
	 */
	private void createFilterTag()
	{
		InquiryTagRequest request = new InquiryTagRequest(getUserContext());
		request.setPageSize(0);

		InquiryTagResponse response = getTagBCF().fetchAllTags(request);

		if (response.isOperationSuccess())
		{
			List<String[]> records = new ArrayList<String[]>();
			if (!ValidationUtil.isNullOrEmpty(response.getTags()))
			{
				for (Tag tag : response.getTags())
				{
					records.add(getKeyValue(tag.getId().toString(), tag.getName()));
				}
			}

			/** Set JSON Result **/
			getFilterResult().setTags(records.toArray(new String[records.size()][]));
		}

	}

	private void createFilterEcoMode()
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(EcoModeFilterEnum.ECONOMY.getValue().toString(),
				getText("smartpoint.filter.ecomodeEconomy")));
		records.add(getKeyValue(EcoModeFilterEnum.VALUE.getValue().toString(),
				getText("smartpoint.filter.ecomodeValue")));
		records.add(getKeyValue(EcoModeFilterEnum.SECURITY.getValue().toString(),
				getText("smartpoint.filter.ecomodeSecurity")));

		// Set JSON Result
		getFilterResult().setEcoMode(records.toArray(new String[records.size()][]));

	}

	/**
	 * Creates the filter event schedule.
	 */
	private void createFilterEventSchedule()
	{
		InquiryScheduleRequest request = new InquiryScheduleRequest(getUserContext());

		request.setPageSize(0);
		request.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);

		InquiryScheduleResponse response = getScheduleBCF().fetchAllSchedules(request);

		List<String[]> records = new ArrayList<String[]>();
		if (!ValidationUtil.isNullOrEmpty(response.getSchedules()))
		{
			for (Schedule schedule : response.getSchedules())
			{
				records.add(getKeyValue(schedule.getId().toString(), schedule.getName()));
			}
		}

		/** Set JSON Result **/
		getFilterResult().setEventSchedule(records.toArray(new String[records.size()][]));

	}

	/**
	 * Creates the filter offset schedule.
	 */
	private void createFilterOffsetSchedule()
	{
		InquiryScheduleRequest request = new InquiryScheduleRequest(getUserContext());
		request.setPageSize(0);
		request.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);

		InquiryScheduleResponse response = getScheduleBCF().fetchAllSchedules(request);

		List<String[]> records = new ArrayList<String[]>();
		if (!ValidationUtil.isNullOrEmpty(response.getSchedules()))
		{
			for (Schedule schedule : response.getSchedules())
			{
				records.add(getKeyValue(schedule.getId().toString(), schedule.getName()));
			}
		}

		/** Set JSON Result **/
		getFilterResult().setOffsetSchedule(records.toArray(new String[records.size()][]));

	}

	/**
	 * Creates the filter configuration.
	 */
	private void createFilterConfiguration()
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(TRUE.toString(), getText("smartpoint.filter.protected")));
		records.add(getKeyValue(FALSE.toString(), getText("smartpoint.filter.listening")));

		/** Set JSON Result **/
		getFilterResult().setConfiguration(records.toArray(new String[records.size()][]));

	}

	/**
	 * Creates the filter search.
	 */
	private void createFilterSearch()
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(PropertyEnum.POLE_ID.getValue().toString(),
				getText("smartpoint.search.poleID")));
		records.add(getKeyValue(PropertyEnum.FLEXNET_ID.getValue().toString(),
				getText("smartpoint.search.flexNetID")));
		records.add(getKeyValue(LightPropertyForSearchEnum.EVENT_ID.getValue().toString(),
				getText("process.page.filter.lightpropertysearch.eventID")));
		records.add(getKeyValue(LightPropertyForSearchEnum.RNI_ID.getValue().toString(),
				getText("process.page.filter.lightpropertysearch.FlexNetID")));
		records.add(getKeyValue(LightPropertyForSearchEnum.ACTION_ID.getValue().toString(),
				getText("process.page.filter.lightpropertysearch.actionID")));
		records.add(getKeyValue(LightPropertyForSearchEnum.ACTION_NAME.getValue().toString(),
				getText("process.page.filter.lightpropertysearch.actionName")));

		/** Set JSON Result **/
		getFilterResult().setQueryType(records.toArray(new String[records.size()][]));

	}

	/**
	 * Creates the filter property valid values.
	 */
	private void createFilterPropertyValidValues()
	{
		PropertyValidValuesRequest request = new PropertyValidValuesRequest(getUserContext());
		request.setProperties(new ArrayList<PropertyEnum>());
		request.getProperties().add(PropertyEnum.LAMP_TYPE);
		request.getProperties().add(PropertyEnum.WATTAGE_RATING);
		request.getProperties().add(PropertyEnum.HOUSING);

		PropertyValidValuesResponse response = getSmartPointAccessorBCF().fetchPropertyValidValues(request);
		List<Integer> wattageSort = new ArrayList<Integer>();
		ViewFilterLightTypes viewFilterLightTypes = new ViewFilterLightTypes();
		List<String[]> mapsLampTypes = new ArrayList<String[]>();
		List<String[]> mapsWattages = new ArrayList<String[]>();
		List<String[]> mapsHousing = new ArrayList<String[]>();

		if (!ValidationUtil.isNullOrEmpty(response.getPropertyValidValues()))
		{
			for (PropertyValidValue pvv : response.getPropertyValidValues())
			{
				if (PropertyEnum.LAMP_TYPE.equals(pvv.getPropertyEnum()))
				{
					new HashMap<String, String>();
					mapsLampTypes.add(getKeyValue(pvv.getValue(), pvv.getValue()));
				}
				else if (PropertyEnum.WATTAGE_RATING.equals(pvv.getPropertyEnum()))
				{
					wattageSort.add(Integer.parseInt(pvv.getValue().replace('W', ' ').trim()));
				}
				else if (PropertyEnum.HOUSING.equals(pvv.getPropertyEnum()))
				{
					new HashMap<String, String>();
					mapsHousing.add(getKeyValue(pvv.getValue(), pvv.getValue()));
				}
			}
		}

		List<String[]> mapsDimmable = new ArrayList<String[]>();

		mapsDimmable.add(getKeyValue(TRUE, getText("smartpoint.filter.lamptype.dimmable.yes")));
		mapsDimmable.add(getKeyValue(FALSE, getText("smartpoint.filter.lamptype.dimmable.no")));

		Collections.sort(wattageSort);
		for (Integer wattage : wattageSort)
		{
			String val = wattage.toString() + "W";
			new HashMap<String, String>();
			mapsWattages.add(getKeyValue(val, val));

		}
		viewFilterLightTypes.setLamptype(mapsLampTypes);
		viewFilterLightTypes.setWattage(mapsWattages);
		viewFilterLightTypes.setHousing(mapsHousing);
		viewFilterLightTypes.setDimmable(mapsDimmable);

		getFilterResult().setLightTypes(viewFilterLightTypes);

	}

	/**
	 * Creates the filter dimmable.
	 */
	private void createFilterDimmable()
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(TRUE, getText("smartpoint.filter.lamptype.dimmable.yes")));
		records.add(getKeyValue(FALSE, getText("smartpoint.filter.lamptype.dimmable.no")));

		/** Set JSON Result **/
		getFilterResult().setDimmable(records.toArray(new String[records.size()][]));

	}

	/**
	 * Creates the filter event type.
	 */
	private void createFilterEventType()
	{

		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(LCActionCategoryEnum.CONTROL_LIGHTS.getValue().toString(),
				getText("process.page.filter.type.controllights")));
		records.add(getKeyValue(LCActionCategoryEnum.MANAGE_LIGHTS.getValue().toString(),
				getText("process.page.filter.type.managelights")));
		records.add(getKeyValue(LCActionCategoryEnum.MANAGE_GROUPS.getValue().toString(),
				getText("process.page.filter.type.managegroups")));
		records.add(getKeyValue(LCActionCategoryEnum.MANAGE_TAGS.getValue().toString(),
				getText("process.page.filter.type.managetags")));
		records.add(getKeyValue(LCActionCategoryEnum.MANAGE_SCHEDULES.getValue().toString(),
				getText("process.page.filter.type.manageschedules")));

		/** Set JSON Result **/
		getFilterResult().setEventType(records.toArray(new String[records.size()][]));

	}

	/**
	 * Creates the filter action type.
	 */
	private void createFilterActionType()
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(LCActionCategoryEnum.CONTROL_LIGHTS.getValue().toString(),
				getText("process.page.filter.type.controllights")));
		records.add(getKeyValue(LCActionCategoryEnum.MANAGE_LIGHTS.getValue().toString(),
				getText("process.page.filter.type.managelights")));
		records.add(getKeyValue(LCActionCategoryEnum.MANAGE_GROUPS.getValue().toString(),
				getText("process.page.filter.type.managegroups")));
		records.add(getKeyValue(LCActionCategoryEnum.MANAGE_TAGS.getValue().toString(),
				getText("process.page.filter.type.managetags")));
		records.add(getKeyValue(LCActionCategoryEnum.MANAGE_SCHEDULES.getValue().toString(),
				getText("process.page.filter.type.manageschedules")));

		/** Set JSON Result **/
		getFilterResult().setActionType(records.toArray(new String[records.size()][]));

	}

	/**
	 * Creates the filter user.
	 */
	private void createFilterUser()
	{

		InquiryUserRequest request = new InquiryUserRequest(getUserContext());

		InquiryUserResponse response = getUserBCF().fetchAllUsers(request);

		List<String[]> records = new ArrayList<String[]>();
		if (response.isOperationSuccess())
		{
			if (!ValidationUtil.isNullOrEmpty(response.getUsers()))
			{
				for (User user : response.getUsers())
				{
					records.add(getKeyValue(user.getId().toString(), user.getFullName()));
				}
			}
		}

		/** Set JSON Result **/
		getFilterResult().setUsers(records.toArray(new String[records.size()][]));

	}

	/**
	 * Creates the filter warning type.
	 */
	private void createFilterWarningType()
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(StatusExceptionTypeEnum.POWER_SURGE_DETECTED.getValue().toString(),
				getText("sensus.mlc.status_subtype.powersurgedetected")));
		records.add(getKeyValue(StatusExceptionTypeEnum.BROWN_OUT_DETECTED.getValue().toString(),
				getText("sensus.mlc.status_subtype.brownoutdetected")));
		records.add(getKeyValue(StatusExceptionTypeEnum.COMMUNICATION_FAIL.getValue().toString(),
				getText("sensus.mlc.status_subtype.communicationfail")));
		records.add(getKeyValue(StatusExceptionTypeEnum.HIGH_CURRENT.getValue().toString(),
				getText("sensus.mlc.status_subtype.highcurrent")));
		records.add(getKeyValue(StatusExceptionTypeEnum.LOW_CURRENT.getValue().toString(),
				getText("sensus.mlc.status_subtype.lowcurrent")));
		records.add(getKeyValue(StatusExceptionTypeEnum.REVERSE_ENERGY.getValue().toString(),
				getText("sensus.mlc.status_subtype.reverseenergy")));
		records.add(getKeyValue(StatusExceptionTypeEnum.METROLOGY_RESET.getValue().toString(),
				getText("sensus.mlc.status_subtype.metrologyreset")));

		/** Set JSON Result **/
		getFilterResult().setWarningType(records.toArray(new String[records.size()][]));

	}

	/**
	 * Creates the filter alarm type.
	 */
	private void createFilterAlarmType()
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue(StatusExceptionTypeEnum.LAMP_FAILURE.getValue().toString(),
				getText("sensus.mlc.status_subtype.lampfailure")));
		records.add(getKeyValue(StatusExceptionTypeEnum.POWER_FAILURE.getValue().toString(),
				getText("sensus.mlc.status_subtype.powerfailure")));
		records.add(getKeyValue(StatusExceptionTypeEnum.BOARD_FAILURE.getValue().toString(),
				getText("sensus.mlc.status_subtype.boardfailure")));
		records.add(getKeyValue(StatusExceptionTypeEnum.METROLOGY_ERROR.getValue().toString(),
				getText("sensus.mlc.status_subtype.metrologyerror")));
		records.add(getKeyValue(StatusExceptionTypeEnum.METROLOGY_COM_FAILURE.getValue().toString(),
				getText("sensus.mlc.status_subtype.metrologycomfailure")));
		/** Set JSON Result **/
		getFilterResult().setAlarmType(records.toArray(new String[records.size()][]));

	}

	private void createFilterColorTemperature()
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue("3,000K", "3,000K"));
		records.add(getKeyValue("4,000K", "4,000K"));
		records.add(getKeyValue("5,000K", "5,000K"));
		records.add(getKeyValue("6,000K", "6,000K"));

		/** Set JSON Result **/
		getFilterResult().setColorTemperature(records.toArray(new String[records.size()][]));
	}

	private void createFilterHousingColor()
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue("GRAY", "Gray"));
		records.add(getKeyValue("BLACK", "Black"));
		records.add(getKeyValue("SILVER", "Silver"));
		records.add(getKeyValue("BRONZE", "Bronze"));
		records.add(getKeyValue("WHITE", "White"));
		records.add(getKeyValue("OFF-WHITE_CREAM", "Off-White / Cream"));
		records.add(getKeyValue("GREEN", "Green"));
		records.add(getKeyValue("ALUMINUM", "Aluminum"));

		/** Set JSON Result **/
		getFilterResult().setHousingColor(records.toArray(new String[records.size()][]));
	}

	/**
	 * Creates the filter voltage range.
	 */
	private void createFilterVoltageRange()
	{
		List<String[]> records = new ArrayList<String[]>();

		records.add(getKeyValue("150-300V", "150-300V"));
		records.add(getKeyValue("90-300V", "90-300V"));

		/** Set JSON Result **/
		getFilterResult().setVoltageRange(records.toArray(new String[records.size()][]));
	}

	/**
	 * Gets the key value.
	 * 
	 * @param id the id
	 * @param name the name
	 * @return the key value
	 */
	private String[] getKeyValue(String id, String name)
	{

		String[] record = new String[TWO];

		record[ZERO] = id;

		record[ONE] = name;

		return record;

	}

	/**
	 * Gets the filter result.
	 * 
	 * @return the filterResult
	 */
	public FilterJsonResult getFilterResult()
	{
		return filterResult;
	}

	/**
	 * Sets the filter result.
	 * 
	 * @param filterResult the filterResult to set
	 */
	public void setFilterResult(FilterJsonResult filterResult)
	{
		this.filterResult = filterResult;
	}

	/**
	 * Gets the data.
	 * 
	 * @return the data
	 */
	public String getData()
	{
		return data;
	}

	/**
	 * Sets the data.
	 * 
	 * @param data the data to set
	 */
	public void setData(String data)
	{
		this.data = data;
	}

	/**
	 * Gets the group bcf.
	 * 
	 * @return the groupBCF
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param groupBCF the groupBCF to set
	 */
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
	public void setTagBCF(ITagBCF tagBCF)
	{
		this.tagBCF = tagBCF;
	}

	/**
	 * Gets the schedule bcf.
	 * 
	 * @return the scheduleBCF
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * Sets the schedule bcf.
	 * 
	 * @param scheduleBCF the scheduleBCF to set
	 */
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	/**
	 * Gets the smart point accessor bcf.
	 * 
	 * @return the smart point accessor bcf
	 */
	public ISmartPointAccessorBCF getSmartPointAccessorBCF()
	{
		return smartPointAccessorBCF;
	}

	/**
	 * Sets the smart point accessor bcf.
	 * 
	 * @param smartPointAccessorBCF the new smart point accessor bcf
	 */
	public void setSmartPointAccessorBCF(ISmartPointAccessorBCF smartPointAccessorBCF)
	{
		this.smartPointAccessorBCF = smartPointAccessorBCF;
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
	public void setSettingsBCF(ISettingsBCF settingsBCF)
	{
		this.settingsBCF = settingsBCF;
	}

	/**
	 * @return the userBCF
	 */
	public IUserBCF getUserBCF()
	{
		return userBCF;
	}

	/**
	 * @param userBCF the userBCF to set
	 */
	public void setUserBCF(IUserBCF userBCF)
	{
		this.userBCF = userBCF;
	}

}
