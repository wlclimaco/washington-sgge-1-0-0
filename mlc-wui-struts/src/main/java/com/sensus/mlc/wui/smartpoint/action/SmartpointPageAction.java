package com.sensus.mlc.wui.smartpoint.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.response.InquiryGroupResponse;
import com.sensus.mlc.schedule.bcf.IScheduleBCF;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.tag.bcf.ITagBCF;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.response.InquiryTagResponse;
import com.sensus.mlc.wui.base.action.LayoutBase;
import com.sensus.mlc.wui.base.model.IdValuePair;

/**
 * Struts Action for rendering the SmartPoint Page and the include for related action dialogs. It extends LayoutBase
 * which provides the JavaScript and CSS Imports if the page is used with the Sitemesh decorator.
 * 
 * @author Anke Doerfel-Parker
 */
@SuppressWarnings("serial")
public class SmartpointPageAction extends LayoutBase
{

	/** CONSTANTS *. */

	/**
	 * The default "empty" value for droplists.
	 */
	private static final String DEFAULT_VALUE = "0";

	/**
	 * The key for the default "empty" droplist prompt.
	 */
	public static final String DEFAULT_DESCRIPTION_KEY = "widgets.combobox.prompt2";

	/** The Constant DEFAULT_ERROR_LOAD. */
	public static final String DEFAULT_ERROR_LOAD = "schedule.actions.openupdate.error";

	/** The Constant DEFAULT_DESCRIPTION_LIGHT_TYPE. */
	public static final String DEFAULT_DESCRIPTION_ALL = "widgets.combobox.prompt.all";

	/**
	 * The key for the default "loading error" droplist prompt.
	 */
	public static final String DEFAULT_ERROR_KEY = "widgets.combobox.errorprompt2";

	/** The Constant TEXT_ERROR. */
	public static final String TEXT_ERROR = "smartpoint.status.error";

	/** The Constant TEXT_WARNING. */
	public static final String TEXT_WARNING = "smartpoint.status.warning";

	/** The Constant TEXT_ACTIVE. */
	public static final String TEXT_ACTIVE = "smartpoint.status.active";

	/** The Constant TEXT_MAINTENANCE. */
	public static final String TEXT_MAINTENANCE = "smartpoint.status.filter.maintenance";

	/** The Constant TEXT_DEACTIVATED. */
	public static final String TEXT_DEACTIVATED = "smartpoint.status.deactivated";

	/** The Constant TEXT_SMP_ACTIONS_LOCK. */
	public static final String TEXT_SMP_ACTIONS_LOCK = "smartpoint.actions.lock";

	/** The Constant TEXT_SMP_ACTIONS_UNLOCK. */
	public static final String TEXT_SMP_ACTIONS_UNLOCK = "smartpoint.actions.unlock";

	/** The Constant LABEL_APPLY_PROTECTED. */
	public static final String LABEL_APPLY_PROTECTED = "applyProtected";

	/** The Constant LABEL_APPLY_LISTEN. */
	public static final String LABEL_APPLY_LISTEN = "applyListen";

	/** The Constant LAMP_TYPE. */
	public static final String LAMP_TYPE = "LAMP_TYPE_WATTAGE_DIMMABLE";

	/** The Constant GROUP_LIST_ERROR. */
	private static final String GROUP_LIST_ERROR = "Error loading Group List";

	/** The Constant TAG_LIST_ERROR. */
	private static final String TAG_LIST_ERROR = "Error loading Tag List";

	/** The Constant UPDATE_STATUS. */
	// private static final String UPDATE_STATUS = "smartpoint.actions.updateStatus";

	/** The Constant CLEAR_ALERTS. */
	private static final String CLEAR_ALERTS = "smartpoint.actions.clearAlerts";

	/** The lamp type list. */
	private List<IdValuePair> lampTypeList = new ArrayList<IdValuePair>();

	/** The lamp type list. */
	private List<IdValuePair> wattageList = new ArrayList<IdValuePair>();

	/** The input voltage list. */
	private List<IdValuePair> inputVoltageList = new ArrayList<IdValuePair>();

	/** The color temperature list. */
	private List<IdValuePair> colorTemperatureList = new ArrayList<IdValuePair>();

	/** The firmware version list. */
	private List<IdValuePair> firmwareVersionList = new ArrayList<IdValuePair>();

	/** The Constant PROPERTY_VALID_VALUES_LIST_ERROR. */
	// private static final String PROPERTY_VALID_VALUES_LIST_ERROR = "Error loading Property Valid Value List";

	/**
	 * The logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(SmartpointPageAction.class);

	/**
	 * The Schedule BCF object.
	 */
	private IScheduleBCF scheduleBCF;

	/**
	 * The Tag BCF object.
	 */
	private ITagBCF tagBCF;

	/**
	 * The Group BCF object.
	 */
	private IGroupBCF groupBCF;

	/** The list smartpoints ids. */
	private String listSmartpointsIds;

	/**
	 * Provides data for a droplist of all available Tags. If an error occurs, an error prompt will be added to the
	 * list.
	 * 
	 * @return Always "SUCCESS".
	 */
	public List<IdValuePair> getTagList()
	{
		List<IdValuePair> list = new ArrayList<IdValuePair>();
		try
		{
			InquiryTagRequest request = new InquiryTagRequest(getUserContext());

			request.setPageSize(0);

			InquiryTagResponse response = getTagBCF().fetchAllTags(request);
			if (!response.isOperationSuccess())
			{
				list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));

				if (LOG.isErrorEnabled())
				{
					LOG.error(response.getMessageList());
				}

			}
			else
			{
				if (!ValidationUtil.isNull(response.getTags()))
				{
					for (Tag tag : response.getTags())
					{
						list.add(new IdValuePair(tag.getId(), tag.getName()));
					}
				}
			}
		}
		catch (Exception e)
		{
			list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
			if (LOG.isErrorEnabled())
			{
				LOG.error(TAG_LIST_ERROR, e);
			}
		}
		return list;
	}

	/**
	 * Provides data for a droplist of all available Groups. If an error occurs, an error prompt will be added to the
	 * list.
	 * 
	 * @return Always "SUCCESS".
	 */
	public List<IdValuePair> getGroupList()
	{
		List<IdValuePair> list = new ArrayList<IdValuePair>();
		try
		{
			InquiryPaginationRequest request = new InquiryPaginationRequest(getUserContext());

			request.setPageSize(0);

			InquiryGroupResponse response = getGroupBCF().fetchAllGroups(request);
			if (!response.isOperationSuccess())
			{
				list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));

				if (LOG.isErrorEnabled())
				{
					LOG.error(response.getMessageList());
				}
			}
			else
			{
				if (!ValidationUtil.isNull(response.getGroups()))
				{
					for (Group group : response.getGroups())
					{
						list.add(new IdValuePair(group.getId(), group.getName()));
					}
				}
				list.add(0, new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_DESCRIPTION_KEY)));
			}
		}
		catch (Exception e)
		{
			list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
			if (LOG.isErrorEnabled())
			{
				LOG.error(GROUP_LIST_ERROR, e);
			}
		}
		return list;
	}

	/**
	 * Provides data for a droplist of all available Groups. If an error occurs, an error prompt will be added to the
	 * list.
	 * 
	 * @return Always "SUCCESS".
	 */
	public List<IdValuePair> getEventScheduleList()
	{
		List<IdValuePair> list = new ArrayList<IdValuePair>();
		try
		{
			InquiryScheduleRequest request = new InquiryScheduleRequest(getUserContext());

			request.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);
			request.setPageSize(0);

			InquiryScheduleResponse response = getScheduleBCF().fetchAllSchedules(request);
			if (!response.isOperationSuccess())
			{
				list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));

				if (LOG.isErrorEnabled())
				{
					LOG.error(response.getMessageList());
				}

			}
			else
			{
				if (!ValidationUtil.isNull(response.getSchedules()))
				{
					for (Schedule schedule : response.getSchedules())
					{
						list.add(new IdValuePair(schedule.getId(), schedule.getName()));
					}
				}
				list.add(0, new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_DESCRIPTION_KEY)));
			}
		}
		catch (Exception e)
		{
			list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
			if (LOG.isErrorEnabled())
			{
				LOG.error(getText(DEFAULT_ERROR_KEY), e);
			}
		}
		return list;
	}

	/**
	 * Gets the offset schedule list.
	 * 
	 * @return the offset schedule list
	 */
	public List<IdValuePair> getOffsetScheduleList()
	{
		List<IdValuePair> list = new ArrayList<IdValuePair>();
		try
		{
			InquiryScheduleRequest request = new InquiryScheduleRequest(getUserContext());

			request.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
			request.setPageSize(0);

			InquiryScheduleResponse response = getScheduleBCF().fetchAllSchedules(request);
			if (!response.isOperationSuccess())
			{
				list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));

				if (LOG.isErrorEnabled())
				{
					LOG.error(response.getMessageList());
				}

			}
			else
			{
				if (!ValidationUtil.isNull(response.getSchedules()))
				{
					for (Schedule schedule : response.getSchedules())
					{
						list.add(new IdValuePair(schedule.getId(), schedule.getName()));
					}
				}
				list.add(0, new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_DESCRIPTION_KEY)));
			}
		}
		catch (Exception e)
		{
			list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error loading Schedule List", e);
			}
		}
		return list;
	}

	/**
	 * Provides data for a droplist of all available Status.
	 * 
	 * @return Always "SUCCESS".
	 */
	public List<IdValuePair> getStatusList()
	{
		List<IdValuePair> list = new ArrayList<IdValuePair>();
		list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_DESCRIPTION_KEY)));
		list.add(new IdValuePair(LightStatusEnum.ACTIVE.getValue(), getText(TEXT_ACTIVE)));
		list.add(new IdValuePair(LightStatusEnum.ALARM.getValue(), getText(TEXT_ERROR)));
		list.add(new IdValuePair(LightStatusEnum.DEACTIVATED.getValue(), getText(TEXT_DEACTIVATED)));
		list.add(new IdValuePair(LightStatusEnum.MAINTENANCE.getValue(), getText(TEXT_MAINTENANCE)));
		list.add(new IdValuePair(LightStatusEnum.WARNING.getValue(), getText(TEXT_WARNING)));
		return list;
	}

	/**
	 * Gets the alarm list.
	 * 
	 * @return the alarm list
	 */
	/*
	 * public List<IdValuePair> getAlarmList()
	 * {
	 * List<IdValuePair> list = new ArrayList<IdValuePair>();
	 * list.add(new IdValuePair(StatusExceptionTypeEnum.LAMP_FAILURE.getValue(),
	 * getText("sensus.mlc.status_subtype.lampfailure")));
	 * list.add(new IdValuePair(StatusExceptionTypeEnum.POWER_FAILURE.getValue(),
	 * getText("sensus.mlc.status_subtype.powerfailure")));
	 * list.add(new IdValuePair(StatusExceptionTypeEnum.COMMUNICATION_FAIL.getValue(),
	 * getText("sensus.mlc.status_subtype.communicationFail")));
	 * return list;
	 * }
	 */

	/**
	 * Gets the warning list.
	 * 
	 * @return the warning list
	 */
	/*
	 * public List<IdValuePair> getWarningList()
	 * {
	 * List<IdValuePair> list = new ArrayList<IdValuePair>();
	 * list.add(new IdValuePair(StatusExceptionTypeEnum.POWER_SURGE_DETECTED.getValue(),
	 * getText("sensus.mlc.status_subtype.brownoutdetected")));
	 * list.add(new IdValuePair(StatusExceptionTypeEnum.BROWN_OUT_DETECTED.getValue(),
	 * getText("sensus.mlc.status_subtype.powersurgedetected")));
	 * return list;
	 * }
	 */

	/**
	 * Provides data for a droplist of all available Actions.
	 * 
	 * @return Always "SUCCESS".
	 */
	public List<IdValuePair> getActionList()
	{
		// Manage Group
		List<IdValuePair> groupList = new ArrayList<IdValuePair>();
		groupList.add(new IdValuePair("addToGroup", getText("smartpoint.actions.addtogroup")));
		groupList.add(new IdValuePair("removeFromGroup", getText("smartpoint.actions.removegroup")));

		// Manage Lights
		List<IdValuePair> manageLight = new ArrayList<IdValuePair>();
		manageLight.add(new IdValuePair("clearAlerts", getText(CLEAR_ALERTS)));
		manageLight.add(new IdValuePair("clearManualOverride", getText("smartpoint.actions.clearManual")));
		manageLight.add(new IdValuePair("resetValuesLightList", getText("smartpoint.actions.reset")));

		// Configuration
		List<IdValuePair> configList = new ArrayList<IdValuePair>();
		configList.add(new IdValuePair(LABEL_APPLY_PROTECTED, getText(TEXT_SMP_ACTIONS_LOCK)));
		configList.add(new IdValuePair(LABEL_APPLY_LISTEN, getText(TEXT_SMP_ACTIONS_UNLOCK)));
		manageLight.add(new IdValuePair("Lock", configList));
		manageLight.add(new IdValuePair("updateStatus", getText("smartpoint.actions.getDataLight")));

		// Manage Tag
		List<IdValuePair> tagList = new ArrayList<IdValuePair>();
		tagList.add(new IdValuePair("addTag", getText("smartpoint.actions.addtag")));
		tagList.add(new IdValuePair("removeTag", getText("smartpoint.actions.removetag")));

		// Manage Schedule
		List<IdValuePair> scheduleList = new ArrayList<IdValuePair>();
		scheduleList.add(new IdValuePair("applyEventSchedule", getText("smartpoint.actions.applyeventschedule")));
		scheduleList.add(new IdValuePair("applyOffsetSchedule", getText("smartpoint.actions.applyoffsetchedule")));
		scheduleList.add(new IdValuePair("resetEventSchedule", getText("smartpoint.actions.removeeventschedule")));
		scheduleList.add(new IdValuePair("resetOffsetSchedule", getText("smartpoint.actions.removeoffsetschedule")));

		// Update Status
		// List<IdValuePair> updateStatus = new ArrayList<IdValuePair>();
		// updateStatus.add(new IdValuePair("updateStatus", getText(UPDATE_STATUS)));

		// Lock
		List<IdValuePair> lockList = new ArrayList<IdValuePair>();
		lockList.add(new IdValuePair(LABEL_APPLY_PROTECTED, getText(TEXT_SMP_ACTIONS_LOCK)));
		lockList.add(new IdValuePair(LABEL_APPLY_LISTEN, getText(TEXT_SMP_ACTIONS_UNLOCK)));

		// Clear Alerts
		// List<IdValuePair> clearAlerts = new ArrayList<IdValuePair>();
		// clearAlerts.add(new IdValuePair("clearAlerts", getText(CLEAR_ALERTS)));

		List<IdValuePair> idValuePair = new ArrayList<IdValuePair>();

		// idValuePair.add(new IdValuePair(getText(CLEAR_ALERTS), clearAlerts));
		idValuePair.add(new IdValuePair(getText("smartpoint.actions.group"), groupList));
		idValuePair.add(new IdValuePair(getText("smartpoint.actions.manage"), manageLight));
		idValuePair.add(new IdValuePair(getText("smartpoint.actions.tag"), tagList));
		idValuePair.add(new IdValuePair(getText("smartpoint.actions.schedule"), scheduleList));
		// idValuePair.add(new IdValuePair(getText(UPDATE_STATUS), updateStatus));

		return idValuePair;
	}

	/**
	 * Gets the valid values.
	 * 
	 * @return the valid values
	 */
	/*
	 * public String getValidValues()
	 * {
	 * try
	 * {
	 * PropertyValidValuesRequest request = new PropertyValidValuesRequest(getUserContext());
	 * request.setProperties(new ArrayList<PropertyEnum>());
	 * request.getProperties().add(PropertyEnum.LAMP_TYPE);
	 * request.getProperties().add(PropertyEnum.WATTAGE_RATING);
	 * request.getProperties().add(PropertyEnum.INPUT_VOLTAGE_RANGE);
	 * request.getProperties().add(PropertyEnum.COLOR_TEMPERATURE);
	 * request.getProperties().add(PropertyEnum.FIRMWARE_VERSION);
	 * PropertyValidValuesResponse response = getSmartpointBCF().fetchPropertyValidValues(request);
	 * if (!response.isOperationSuccess())
	 * {
	 * getLampTypeList().add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
	 * getWattageList().add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
	 * getInputVoltageList().add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
	 * getColorTemperatureList().add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
	 * getFirmwareVersionList().add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
	 * if (LOG.isErrorEnabled())
	 * {
	 * LOG.error(response.getMessageList());
	 * }
	 * }
	 * else
	 * {
	 * if (!ValidationUtil.isNull(response.getPropertyValidValues()))
	 * {
	 * for (PropertyValidValue propertyValidValue : response.getPropertyValidValues())
	 * {
	 * if (propertyValidValue.getPropertyEnum().equals(PropertyEnum.LAMP_TYPE))
	 * {
	 * getLampTypeList().add(new IdValuePair(propertyValidValue.getId(), propertyValidValue
	 * .getValue()));
	 * }
	 * else if (propertyValidValue.getPropertyEnum().equals(PropertyEnum.WATTAGE_RATING))
	 * {
	 * getWattageList().add(new IdValuePair(propertyValidValue.getId(), propertyValidValue
	 * .getValue()));
	 * }
	 * else if (propertyValidValue.getPropertyEnum().equals(PropertyEnum.INPUT_VOLTAGE_RANGE))
	 * {
	 * getInputVoltageList().add(new IdValuePair(propertyValidValue.getId(), propertyValidValue
	 * .getValue()));
	 * }
	 * else if (propertyValidValue.getPropertyEnum().equals(PropertyEnum.COLOR_TEMPERATURE))
	 * {
	 * getColorTemperatureList().add(
	 * new IdValuePair(propertyValidValue.getId(), propertyValidValue
	 * .getValue()));
	 * }
	 * else if (propertyValidValue.getPropertyEnum().equals(PropertyEnum.FIRMWARE_VERSION))
	 * {
	 * getFirmwareVersionList().add(
	 * new IdValuePair(propertyValidValue.getId(), propertyValidValue
	 * .getValue()));
	 * }
	 * }
	 * }
	 * getLampTypeList().add(0, new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_DESCRIPTION_KEY)));
	 * getWattageList().add(0, new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_DESCRIPTION_KEY)));
	 * getInputVoltageList().add(0, new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_DESCRIPTION_KEY)));
	 * getColorTemperatureList().add(0, new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_DESCRIPTION_KEY)));
	 * getFirmwareVersionList().add(0, new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_DESCRIPTION_KEY)));
	 * }
	 * }
	 * catch (Exception e)
	 * {
	 * getLampTypeList().add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
	 * getWattageList().add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
	 * getInputVoltageList().add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
	 * getColorTemperatureList().add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
	 * getFirmwareVersionList().add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
	 * if (LOG.isErrorEnabled())
	 * {
	 * LOG.error(PROPERTY_VALID_VALUES_LIST_ERROR, e);
	 * }
	 * }
	 * return SUCCESS;
	 * }
	 */

	/**
	 * Get the Schedule BCF Object.
	 * 
	 * @return the Schedule BCF Object
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * Set the Schedule BCF Object.
	 * 
	 * @param scheduleBCFIn the scheduleBCF to set
	 */
	public void setScheduleBCF(IScheduleBCF scheduleBCFIn)
	{
		scheduleBCF = scheduleBCFIn;
	}

	/**
	 * Get the Tag BCF Object.
	 * 
	 * @return the Tag BCF Object
	 */
	public ITagBCF getTagBCF()
	{
		return tagBCF;
	}

	/**
	 * Set the Tag BCF object.
	 * 
	 * @param tagBCFIn the Tag BCF object to set
	 */
	public void setTagBCF(ITagBCF tagBCFIn)
	{
		tagBCF = tagBCFIn;
	}

	/**
	 * Get the Group BCF object.
	 * 
	 * @return the Group BCF object
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Get the Group BCF object.
	 * 
	 * @param groupBCFIn the new group bcf
	 * @return the Group BCF object
	 */
	public void setGroupBCF(IGroupBCF groupBCFIn)
	{
		groupBCF = groupBCFIn;
	}

	/**
	 * Long running process dialog.
	 * 
	 * @return the string
	 */
	public String longRunningProcessDialog()
	{
		return SUCCESS;

	}

	/**
	 * Sets the list smartpoints ids.
	 * 
	 * @param listSmartpointsIds the new list smartpoints ids
	 */
	public void setListSmartpointsIds(String listSmartpointsIds)
	{
		this.listSmartpointsIds = listSmartpointsIds;

	}

	/**
	 * Gets the list smartpoints ids.
	 * 
	 * @return the list smartpoints ids
	 */
	public String getListSmartpointsIds()
	{
		return listSmartpointsIds;
	}

	/**
	 * Gets the lamp type list.
	 * 
	 * @return the lamp type list
	 */
	public List<IdValuePair> getLampTypeList()
	{
		return lampTypeList;
	}

	/**
	 * Sets the lamp type list.
	 * 
	 * @param lampTypeList the new lamp type list
	 */
	public void setLampTypeList(List<IdValuePair> lampTypeList)
	{
		this.lampTypeList = lampTypeList;
	}

	/**
	 * Gets the input voltage list.
	 * 
	 * @return the input voltage list
	 */
	public List<IdValuePair> getInputVoltageList()
	{
		return inputVoltageList;
	}

	/**
	 * Sets the input voltage list.
	 * 
	 * @param inputVoltageList the new input voltage list
	 */
	public void setInputVoltageList(List<IdValuePair> inputVoltageList)
	{
		this.inputVoltageList = inputVoltageList;
	}

	/**
	 * Gets the color temperature list.
	 * 
	 * @return the color temperature list
	 */
	public List<IdValuePair> getColorTemperatureList()
	{
		return colorTemperatureList;
	}

	/**
	 * Sets the color temperature list.
	 * 
	 * @param colorTemperatureList the new color temperature list
	 */
	public void setColorTemperatureList(List<IdValuePair> colorTemperatureList)
	{
		this.colorTemperatureList = colorTemperatureList;
	}

	/**
	 * Gets the firmware version list.
	 * 
	 * @return the firmware version list
	 */
	public List<IdValuePair> getFirmwareVersionList()
	{
		return firmwareVersionList;
	}

	/**
	 * Sets the firmware version list.
	 * 
	 * @param firmwareVersionList the new firmware version list
	 */
	public void setFirmwareVersionList(List<IdValuePair> firmwareVersionList)
	{
		this.firmwareVersionList = firmwareVersionList;
	}

	/**
	 * Gets the wattage list.
	 * 
	 * @return the wattage list
	 */
	public List<IdValuePair> getWattageList()
	{
		return wattageList;
	}

	/**
	 * Sets the wattage list.
	 * 
	 * @param wattageList the new wattage list
	 */
	public void setWattageList(List<IdValuePair> wattageList)
	{
		this.wattageList = wattageList;
	}

}