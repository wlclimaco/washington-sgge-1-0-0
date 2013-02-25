package com.sensus.mlc.wui.filters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.ListTypeEnum;
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
import com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF;
import com.sensus.mlc.smartpoint.model.LightPropertyForSearchEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.tag.bcf.ITagBCF;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.response.InquiryTagResponse;
import com.sensus.mlc.user.bcf.IUserBCF;
import com.sensus.mlc.user.model.User;
import com.sensus.mlc.user.model.request.InquiryUserRequest;
import com.sensus.mlc.user.model.response.InquiryUserResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;
import com.sensus.mlc.wui.model.FiltersModel;
import com.sensus.mlc.wui.model.FiltersResponse;

/**
 * The Class FiltersAPIController.
 * 
 * @author QATEmployee
 */
@Controller
@RequestMapping("/api/filters")
public class FiltersAPIController extends BaseController
{

	/** The Constant TRUE. */
	private static final String TRUE = "true";

	/** The Constant FALSE. */
	private static final String FALSE = "false";

	/** The schedule bcf. */
	private IScheduleBCF scheduleBCF;

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/** The user bcf. */
	private IUserBCF userBCF;

	/** The smart point accessor bcf. */
	private ISmartPointAccessorBCF smartPointAccessorBCF;

	/** The Constant ACTION. */
	private static final String ACTION = "action";

	/** The Constant BUILD. */
	private static final String BUILD = "build";

	/** The Constant FILTERS. */
	private static final String FILTERS = "filters";

	/** The Constant ACTION_TYPE. */
	private static final String ACTION_TYPE = "ACTION_TYPE";

	/** The Constant ALARM_TYPE. */
	private static final String ALARM_TYPE = "ALARM_TYPE";

	/** The Constant CONFIGURATION. */
	private static final String CONFIGURATION = "CONFIGURATION";

	/** The Constant ECOMODE. */
	private static final String ECOMODE = "ECO_MODE";

	/** The Constant EVENT_SCHEDULE. */
	private static final String EVENT_SCHEDULE = "EVENT_SCHEDULE";

	/** The Constant EVENT_TYPE. */
	private static final String EVENT_TYPE = "EVENT_TYPE";

	/** The Constant GROUPS. */
	private static final String GROUPS = "GROUPS";

	/** The Constant LIGHT_TYPES. */
	private static final String LIGHT_TYPES = "LIGHT_TYPES";

	/** The Constant OFFSET_SCHEDULE. */
	private static final String OFFSET_SCHEDULE = "OFFSET_SCHEDULE";

	/** The Constant SEARCH. */
	private static final String SEARCH = "SEARCH";

	/** The Constant STATUS. */
	private static final String STATUS = "STATUS";

	/** The Constant TAGS. */
	private static final String TAGS = "TAGS";

	/** The Constant USERS. */
	private static final String USERS = "USERS";

	/** The Constant WARNING_TYPE. */
	private static final String WARNING_TYPE = "WARNING_TYPE";

	/** The Constant COLOR_TEMPERATURE. */
	private static final String COLOR_TEMPERATURE = "COLOR_TEMPERATURE";

	/** The Constant HOUSING_COLOR. */
	private static final String HOUSING_COLOR = "HOUSING_COLOR";

	/** The Constant VOLTAGE_RANGE. */
	private static final String VOLTAGE_RANGE = "VOLTAGE_RANGE";

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
	@RequestMapping(value = "/fetch", method = RequestMethod.POST)
	@ResponseBody
	public FiltersResponse fetch(@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{
		FiltersResponse filtersResponse = new FiltersResponse();

		try
		{
			ColumnFilterRequest columnFiltersRequest = new ColumnFilterRequest();
			setUserContext(columnFiltersRequest, request);

			switch (jsonRequest.get(ACTION).toString())
			{

				case BUILD:

					List<String> data = (List<String>)jsonRequest.get(FILTERS);

					Map<String, Object> records = new HashMap<String, Object>();

					for (String filter : data)
					{

						switch (filter)
						{

							case ACTION_TYPE:

								records = new HashMap<String, Object>();

								records.put(LCActionCategoryEnum.CONTROL_LIGHTS.getValue().toString(),
										getText("process.page.filter.type.controllights", request.getLocale()));
								records.put(LCActionCategoryEnum.MANAGE_LIGHTS.getValue().toString(),
										getText("process.page.filter.type.managelights", request.getLocale()));
								records.put(LCActionCategoryEnum.MANAGE_GROUPS.getValue().toString(),
										getText("process.page.filter.type.managegroups", request.getLocale()));
								records.put(LCActionCategoryEnum.MANAGE_TAGS.getValue().toString(),
										getText("process.page.filter.type.managetags", request.getLocale()));
								records.put(LCActionCategoryEnum.MANAGE_SCHEDULES.getValue().toString(),
										getText("process.page.filter.type.manageschedules", request.getLocale()));

								filtersResponse.setActionType(new FiltersModel("checkbox", records));

								break;

							case ALARM_TYPE:

								records = new HashMap<String, Object>();

								records.put(StatusExceptionTypeEnum.LAMP_FAILURE.getValue().toString(),
										getText("sensus.mlc.status_subtype.lampfailure", request.getLocale()));
								records.put(StatusExceptionTypeEnum.POWER_FAILURE.getValue().toString(),
										getText("sensus.mlc.status_subtype.powerfailure", request.getLocale()));
								records.put(StatusExceptionTypeEnum.BOARD_FAILURE.getValue().toString(),
										getText("sensus.mlc.status_subtype.boardfailure", request.getLocale()));
								records.put(StatusExceptionTypeEnum.METROLOGY_ERROR.getValue().toString(),
										getText("sensus.mlc.status_subtype.metrologyerror", request.getLocale()));
								records.put(StatusExceptionTypeEnum.METROLOGY_COM_FAILURE.getValue().toString(),
										getText("sensus.mlc.status_subtype.metrologycomfailure", request.getLocale()));

								filtersResponse.setAlarmType(new FiltersModel("checkbox", records));

								break;

							case CONFIGURATION:

								records = new HashMap<String, Object>();

								records.put(TRUE.toString(),
										getText("smartpoint.filter.protected", request.getLocale()));
								records.put(FALSE.toString(),
										getText("smartpoint.filter.listening", request.getLocale()));

								filtersResponse.setConfiguration(new FiltersModel("checkbox", records));

								break;

							case ECOMODE:

								records = new HashMap<String, Object>();

								records.put(EcoModeFilterEnum.ECONOMY.getValue().toString(),
										getText("smartpoint.filter.ecomodeEconomy", request.getLocale()));
								records.put(EcoModeFilterEnum.VALUE.getValue().toString(),
										getText("smartpoint.filter.ecomodeValue", request.getLocale()));
								records.put(EcoModeFilterEnum.SECURITY.getValue().toString(),
										getText("smartpoint.filter.ecomodeSecurity", request.getLocale()));

								filtersResponse.setEcomode(new FiltersModel("checkbox", records));

								break;

							case EVENT_SCHEDULE:

								records = new HashMap<String, Object>();

								InquiryScheduleRequest scheduleRequest = new InquiryScheduleRequest();
								scheduleRequest.setPageSize(0);
								scheduleRequest.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);
								setUserContext(scheduleRequest, request);
								InquiryScheduleResponse response = getScheduleBCF().fetchAllSchedules(scheduleRequest);
								if (!ValidationUtil.isNullOrEmpty(response.getSchedules()))
								{
									for (Schedule schedule : response.getSchedules())
									{
										records.put(schedule.getId().toString(), schedule.getName());
									}
								}

								filtersResponse.setEvent_schedule(new FiltersModel("checkbox", records));

								break;

							case EVENT_TYPE:

								records = new HashMap<String, Object>();

								records.put(LCActionCategoryEnum.CONTROL_LIGHTS.getValue().toString(),
										getText("process.page.filter.type.controllights", request.getLocale()));
								records.put(LCActionCategoryEnum.MANAGE_LIGHTS.getValue().toString(),
										getText("process.page.filter.type.managelights", request.getLocale()));
								records.put(LCActionCategoryEnum.MANAGE_GROUPS.getValue().toString(),
										getText("process.page.filter.type.managegroups", request.getLocale()));
								records.put(LCActionCategoryEnum.MANAGE_TAGS.getValue().toString(),
										getText("process.page.filter.type.managetags", request.getLocale()));
								records.put(LCActionCategoryEnum.MANAGE_SCHEDULES.getValue().toString(),
										getText("process.page.filter.type.manageschedules", request.getLocale()));

								filtersResponse.setEvent_type(new FiltersModel("checkbox", records));

								break;

							case GROUPS:

								records = new HashMap<String, Object>();

								InquiryPaginationRequest groupRequest = new InquiryPaginationRequest();
								groupRequest.setPageSize(0);
								setUserContext(groupRequest, request);

								InquiryGroupResponse groupsResponse = getGroupBCF().fetchAllGroups(groupRequest);
								if (groupsResponse.isOperationSuccess())
								{
									if (!ValidationUtil.isNullOrEmpty(groupsResponse.getGroups()))
									{
										for (Group group : groupsResponse.getGroups())
										{
											records.put(group.getId().toString(), group.getName());
										}
									}
								}

								filtersResponse.setGroups(new FiltersModel("checkbox", records));

								break;

							case LIGHT_TYPES:
								/*
								 * records = new HashMap<String, Object>();
								 * PropertyValidValuesRequest lightTypesRequest = new PropertyValidValuesRequest();
								 * setUserContext(lightTypesRequest, request);
								 * lightTypesRequest.setProperties(new ArrayList<PropertyEnum>());
								 * lightTypesRequest.getProperties().add(PropertyEnum.LAMP_TYPE);
								 * lightTypesRequest.getProperties().add(PropertyEnum.WATTAGE_RATING);
								 * lightTypesRequest.getProperties().add(PropertyEnum.HOUSING);
								 * PropertyValidValuesResponse lightTypesRequest =
								 * getSmartPointAccessorBCF().fetchPropertyValidValues(lightTypesRequest);
								 * List<Integer> wattageSort = new ArrayList<Integer>();
								 * ViewFilterLightTypes viewFilterLightTypes = new ViewFilterLightTypes();
								 * List<String[]> mapsLampTypes = new ArrayList<String[]>();
								 * List<String[]> mapsWattages = new ArrayList<String[]>();
								 * List<String[]> mapsHousing = new ArrayList<String[]>();
								 * if (!ValidationUtil.isNullOrEmpty(response.getPropertyValidValues()))
								 * {
								 * for (PropertyValidValue pvv : response.getPropertyValidValues())
								 * {
								 * if (PropertyEnum.LAMP_TYPE.equals(pvv.getPropertyEnum()))
								 * {
								 * new HashMap<String, String>();
								 * mapsLampTypes.add(getKeyValue(pvv.getValue(), pvv.getValue()));
								 * }
								 * else if (PropertyEnum.WATTAGE_RATING.equals(pvv.getPropertyEnum()))
								 * {
								 * wattageSort.add(Integer.parseInt(pvv.getValue().replace('W', ' ').trim()));
								 * }
								 * else if (PropertyEnum.HOUSING.equals(pvv.getPropertyEnum()))
								 * {
								 * new HashMap<String, String>();
								 * mapsHousing.add(getKeyValue(pvv.getValue(), pvv.getValue()));
								 * }
								 * }
								 * }
								 * List<String[]> mapsDimmable = new ArrayList<String[]>();
								 * mapsDimmable.add(getKeyValue(TRUE,
								 * getText("smartpoint.filter.lamptype.dimmable.yes")));
								 * mapsDimmable.add(getKeyValue(FALSE,
								 * getText("smartpoint.filter.lamptype.dimmable.no")));
								 * Collections.sort(wattageSort);
								 * for (Integer wattage : wattageSort)
								 * {
								 * String val = wattage.toString() + "W";
								 * new HashMap<String, String>();
								 * mapsWattages.add(getKeyValue(val, val));
								 * }
								 * viewFilterLightTypes.setLamptype(mapsLampTypes);
								 * viewFilterLightTypes.setWattage(mapsWattages);
								 * viewFilterLightTypes.setHousing(mapsHousing);
								 * viewFilterLightTypes.setDimmable(mapsDimmable);
								 * getFilterResult().setLightTypes(viewFilterLightTypes);
								 */

								break;

							case OFFSET_SCHEDULE:

								records = new HashMap<String, Object>();

								InquiryScheduleRequest offsetRequest = new InquiryScheduleRequest();
								offsetRequest.setPageSize(0);
								offsetRequest.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
								setUserContext(offsetRequest, request);
								InquiryScheduleResponse scheduleResponse =
										getScheduleBCF().fetchAllSchedules(offsetRequest);
								if (!ValidationUtil.isNullOrEmpty(scheduleResponse.getSchedules()))
								{
									for (Schedule schedule : scheduleResponse.getSchedules())
									{
										records.put(schedule.getId().toString(), schedule.getName());
									}
								}

								filtersResponse.setOffset_schedule(new FiltersModel("checkbox", records));

								break;

							case SEARCH:

								records = new HashMap<String, Object>();

								records.put(PropertyEnum.POLE_ID.getValue().toString(),
										getText("smartpoint.search.poleID", request.getLocale()));
								records.put(PropertyEnum.FLEXNET_ID.getValue().toString(),
										getText("smartpoint.search.flexNetID", request.getLocale()));
								records.put(LightPropertyForSearchEnum.EVENT_ID.getValue().toString(),
										getText("process.page.filter.lightpropertysearch.eventID", request.getLocale()));
								records.put(
										LightPropertyForSearchEnum.RNI_ID.getValue().toString(),
										getText("process.page.filter.lightpropertysearch.FlexNetID",
												request.getLocale()));
								records.put(
										LightPropertyForSearchEnum.ACTION_ID.getValue().toString(),
										getText("process.page.filter.lightpropertysearch.actionID", request.getLocale()));
								records.put(
										LightPropertyForSearchEnum.ACTION_NAME.getValue().toString(),
										getText("process.page.filter.lightpropertysearch.actionName",
												request.getLocale()));

								filtersResponse.setSearch(new FiltersModel("checkbox", records));

								break;

							case STATUS:

								records = new HashMap<String, Object>();

								records.put(LightStatusEnum.ACTIVE.getValue().toString(),
										getText("smartpoint.status.filter.active", request.getLocale()));
								records.put(LightStatusEnum.ALARM.getValue().toString(),
										getText("smartpoint.status.filter.error", request.getLocale()));
								records.put(LightStatusEnum.DEACTIVATED.getValue().toString(),
										getText("smartpoint.status.filter.deactivated", request.getLocale()));
								records.put(LightStatusEnum.MAINTENANCE.getValue().toString(),
										getText("smartpoint.status.filter.maintenance", request.getLocale()));
								records.put(LightStatusEnum.OVERRIDE.getValue().toString(),
										getText("smartpoint.status.filter.override", request.getLocale()));
								records.put(LightStatusEnum.WARNING.getValue().toString(),
										getText("smartpoint.status.filter.warning", request.getLocale()));

								filtersResponse.setStatus(new FiltersModel("checkbox", records));

								break;

							case TAGS:

								records = new HashMap<String, Object>();

								InquiryTagRequest tagRequest = new InquiryTagRequest();
								tagRequest.setPageSize(0);
								setUserContext(tagRequest, request);
								InquiryTagResponse tagResponse = getTagBCF().fetchAllTags(tagRequest);
								if (tagResponse.isOperationSuccess())
								{
									if (!ValidationUtil.isNullOrEmpty(tagResponse.getTags()))
									{
										for (Tag tag : tagResponse.getTags())
										{
											records.put(tag.getId().toString(), tag.getName());
										}
									}

									filtersResponse.setTags(new FiltersModel("checkbox", records));
								}

								break;

							case USERS:

								records = new HashMap<String, Object>();

								InquiryUserRequest userRequest = new InquiryUserRequest();
								setUserContext(userRequest, request);
								InquiryUserResponse userResponse = getUserBCF().fetchAllUsers(userRequest);
								if (userResponse.isOperationSuccess())
								{
									if (!ValidationUtil.isNullOrEmpty(userResponse.getUsers()))
									{
										for (User user : userResponse.getUsers())
										{
											records.put(user.getId().toString(), user.getFullName());
										}
									}
								}

								filtersResponse.setUsers(new FiltersModel("checkbox", records));

								break;

							case WARNING_TYPE:

								records = new HashMap<String, Object>();

								records.put(StatusExceptionTypeEnum.POWER_SURGE_DETECTED.getValue().toString(),
										getText("sensus.mlc.status_subtype.powersurgedetected", request.getLocale()));
								records.put(StatusExceptionTypeEnum.BROWN_OUT_DETECTED.getValue().toString(),
										getText("sensus.mlc.status_subtype.brownoutdetected", request.getLocale()));
								records.put(StatusExceptionTypeEnum.COMMUNICATION_FAIL.getValue().toString(),
										getText("sensus.mlc.status_subtype.communicationfail", request.getLocale()));
								records.put(StatusExceptionTypeEnum.HIGH_CURRENT.getValue().toString(),
										getText("sensus.mlc.status_subtype.highcurrent", request.getLocale()));
								records.put(StatusExceptionTypeEnum.LOW_CURRENT.getValue().toString(),
										getText("sensus.mlc.status_subtype.lowcurrent", request.getLocale()));
								records.put(StatusExceptionTypeEnum.REVERSE_ENERGY.getValue().toString(),
										getText("sensus.mlc.status_subtype.reverseenergy", request.getLocale()));
								records.put(StatusExceptionTypeEnum.METROLOGY_RESET.getValue().toString(),
										getText("sensus.mlc.status_subtype.metrologyreset", request.getLocale()));

								filtersResponse.setWarning_type(new FiltersModel("checkbox", records));

								break;

							case COLOR_TEMPERATURE:

								records = new HashMap<String, Object>();

								records.put("3,000K", "3,000K");
								records.put("4,000K", "4,000K");
								records.put("5,000K", "5,000K");
								records.put("6,000K", "6,000K");

								filtersResponse.setColor_temperature(new FiltersModel("checkbox", records));

								break;

							case HOUSING_COLOR:

								records = new HashMap<String, Object>();

								records.put("GRAY", "Gray");
								records.put("BLACK", "Black");
								records.put("SILVER", "Silver");
								records.put("BRONZE", "Bronze");
								records.put("WHITE", "White");
								records.put("OFF-WHITE_CREAM", "Off-White / Cream");
								records.put("GREEN", "Green");
								records.put("ALUMINUM", "Aluminum");

								filtersResponse.setHousing_color(new FiltersModel("checkbox", records));

								break;

							case VOLTAGE_RANGE:

								records = new HashMap<String, Object>();

								filtersResponse.setVoltage_range(new FiltersModel("checkbox", records));

								break;
						}

					}

					break;

				case FETCH_CUSTOMIZE:

					setUserContext(columnFiltersRequest, request);

					columnFiltersRequest.setListTypeEnum(ListTypeEnum.valueOf(String.valueOf(jsonRequest
							.get(LIST_TYPE_ENUM))));

					ColumnFilterResponse columnFilterResponse =
							getSmartPointAccessorBCF().fetchAllColumnFilters(columnFiltersRequest);

					filtersResponse.setAdditionalColumns(columnFilterResponse.getAdditionalColumns());
					filtersResponse.setAdditionalFilters(columnFilterResponse.getAdditionalFilters());
					filtersResponse.setFilters(columnFilterResponse.getFilters());
					filtersResponse.setListColumn(columnFilterResponse.getListColumn());

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
	@Resource
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
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
	 * Gets the user bcf.
	 * 
	 * @return the userBCF
	 */
	public IUserBCF getUserBCF()
	{
		return userBCF;
	}

	/**
	 * Sets the user bcf.
	 * 
	 * @param userBCF the userBCF to set
	 */
	@Resource
	public void setUserBCF(IUserBCF userBCF)
	{
		this.userBCF = userBCF;
	}

	/**
	 * Gets the smart point accessor bcf.
	 * 
	 * @return the smartPointAccessorBCF
	 */
	public ISmartPointAccessorBCF getSmartPointAccessorBCF()
	{
		return smartPointAccessorBCF;
	}

	/**
	 * Sets the smart point accessor bcf.
	 * 
	 * @param smartPointAccessorBCF the smartPointAccessorBCF to set
	 */
	@Resource
	public void setSmartPointAccessorBCF(ISmartPointAccessorBCF smartPointAccessorBCF)
	{
		this.smartPointAccessorBCF = smartPointAccessorBCF;
	}

}
