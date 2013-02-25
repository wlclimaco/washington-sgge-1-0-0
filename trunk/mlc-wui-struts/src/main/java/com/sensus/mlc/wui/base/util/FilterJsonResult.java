package com.sensus.mlc.wui.base.util;

import java.util.Arrays;

import com.sensus.mlc.wui.base.model.JsonResult;
import com.sensus.mlc.wui.smartpoint.model.ViewFilterLightTypes;

/**
 * Base class for Filter Json results for callbacks by the JQuery filter component.
 * 
 * @author Lucas Oliveira
 */
public class FilterJsonResult extends JsonResult
{

	/** The action type. */
	private String[][] actionType;

	/** The alarm type. */
	private String[][] alarmType;

	/** The configuration. */
	private String[][] configuration;

	/** The dimmable. */
	private String[][] dimmable;

	/** The eco mode. */
	private String[][] ecoMode;

	/** The event type. */
	private String[][] eventType;

	/** The event schedule. */
	private String[][] eventSchedule;

	/** The groups. */
	private String[][] groups;

	/** The light types. */
	private ViewFilterLightTypes lightTypes;

	/** The offset schedule. */
	private String[][] offsetSchedule;

	/** The query type. */
	private String[][] queryType;

	/** The status. */
	private String[][] status;

	/** The tags. */
	private String[][] tags;

	/** The users. */
	private String[][] users;

	/** The warning type. */
	private String[][] warningType;

	/** The housing color. */
	private String[][] housingColor;

	/** The model number. */
	private String[][] modelNumber;

	/** The firmware version. */
	private String[][] firmwareVersion;

	/** The bulb serial number. */
	private String[][] bulbSerialNumber;

	/** The upper assembly serial number. */
	private String[][] upperAssemblySerialNumber;

	/** The lower assembly serial number. */
	private String[][] lowerAssemblySerialNumber;

	/** The color temperature. */
	private String[][] colorTemperature;

	/** The voltage range. */
	private String[][] voltageRange;

	/** The light driver serial number. */
	private String[][] lightDriverSerialNumber;

	/** The date added. */
	private String[][] dateAdded;

	/**
	 * Gets the groups.
	 * 
	 * @return the groups
	 */
	public String[][] getGroups()
	{
		return groups;
	}

	/**
	 * Sets the groups.
	 * 
	 * @param groups the groups to set
	 */
	public void setGroups(String[][] groups)
	{
		this.groups = groups;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public String[][] getStatus()
	{
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status the status to set
	 */
	public void setStatus(String[][] status)
	{
		this.status = status;
	}

	/**
	 * Gets the tags.
	 * 
	 * @return the tags
	 */
	public String[][] getTags()
	{
		return tags;
	}

	/**
	 * Sets the tags.
	 * 
	 * @param tags the tags to set
	 */
	public void setTags(String[][] tags)
	{
		this.tags = tags;
	}

	/**
	 * Gets the event schedule.
	 * 
	 * @return the eventSchedule
	 */
	public String[][] getEventSchedule()
	{
		return eventSchedule;
	}

	/**
	 * Sets the event schedule.
	 * 
	 * @param eventSchedule the eventSchedule to set
	 */
	public void setEventSchedule(String[][] eventSchedule)
	{
		this.eventSchedule = eventSchedule;
	}

	/**
	 * Gets the offset schedule.
	 * 
	 * @return the offsetSchedule
	 */
	public String[][] getOffsetSchedule()
	{
		return offsetSchedule;
	}

	/**
	 * Sets the offset schedule.
	 * 
	 * @param offsetSchedule the offsetSchedule to set
	 */
	public void setOffsetSchedule(String[][] offsetSchedule)
	{
		this.offsetSchedule = offsetSchedule;
	}

	/**
	 * Gets the configuration.
	 * 
	 * @return the configuration
	 */
	public String[][] getConfiguration()
	{
		return configuration;
	}

	/**
	 * Sets the configuration.
	 * 
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(String[][] configuration)
	{
		this.configuration = configuration;
	}

	/**
	 * Gets the query type.
	 * 
	 * @return the queryType
	 */
	public String[][] getQueryType()
	{
		return queryType;
	}

	/**
	 * Sets the query type.
	 * 
	 * @param queryType the queryType to set
	 */
	public void setQueryType(String[][] queryType)
	{
		this.queryType = queryType;
	}

	/**
	 * Gets the light types.
	 * 
	 * @return the lightTypes
	 */
	public ViewFilterLightTypes getLightTypes()
	{
		return lightTypes;
	}

	/**
	 * Sets the light types.
	 * 
	 * @param lightTypes the lightTypes to set
	 */
	public void setLightTypes(ViewFilterLightTypes lightTypes)
	{
		this.lightTypes = lightTypes;
	}

	/**
	 * Gets the dimmable.
	 * 
	 * @return the dimmable
	 */
	public String[][] getDimmable()
	{
		return dimmable;
	}

	/**
	 * Sets the dimmable.
	 * 
	 * @param dimmable the dimmable to set
	 */
	public void setDimmable(String[][] dimmable)
	{
		this.dimmable = dimmable;
	}

	/**
	 * Gets the event type.
	 * 
	 * @return the eventType
	 */
	public String[][] getEventType()
	{
		return eventType;
	}

	/**
	 * Sets the event type.
	 * 
	 * @param eventType the eventType to set
	 */
	public void setEventType(String[][] eventType)
	{
		this.eventType = eventType;
	}

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public String[][] getUsers()
	{
		return users;
	}

	/**
	 * Sets the user.
	 * 
	 * @param users the new users
	 */
	public void setUsers(String[][] users)
	{
		this.users = users;
	}

	/**
	 * Gets the alarm type.
	 * 
	 * @return the alarmType
	 */
	public String[][] getAlarmType()
	{
		return alarmType;
	}

	/**
	 * Sets the alarm type.
	 * 
	 * @param alarmType the alarmType to set
	 */
	public void setAlarmType(String[][] alarmType)
	{
		this.alarmType = alarmType;
	}

	/**
	 * Gets the warning type.
	 * 
	 * @return the warningType
	 */
	public String[][] getWarningType()
	{
		return warningType;
	}

	/**
	 * Sets the warning type.
	 * 
	 * @param warningType the warningType to set
	 */
	public void setWarningType(String[][] warningType)
	{
		this.warningType = warningType;
	}

	/**
	 * Gets the action type.
	 * 
	 * @return the actionType
	 */
	public String[][] getActionType()
	{
		return actionType;
	}

	/**
	 * Sets the action type.
	 * 
	 * @param actionType the actionType to set
	 */
	public void setActionType(String[][] actionType)
	{
		this.actionType = actionType;
	}

	/**
	 * Gets the eco mode.
	 * 
	 * @return the ecoMode
	 */
	public String[][] getEcoMode()
	{
		return ecoMode;
	}

	/**
	 * Sets the eco mode.
	 * 
	 * @param ecoMode the ecoMode to set
	 */
	public void setEcoMode(String[][] ecoMode)
	{
		this.ecoMode = ecoMode;
	}

	/**
	 * Gets the housing color.
	 * 
	 * @return the housing color
	 */
	public String[][] getHousingColor()
	{
		return housingColor;
	}

	/**
	 * Sets the housing color.
	 * 
	 * @param housingColor the new housing color
	 */
	public void setHousingColor(String[][] housingColor)
	{
		this.housingColor = housingColor;
	}

	/**
	 * Gets the color temperature.
	 * 
	 * @return the color temperature
	 */
	public String[][] getColorTemperature()
	{
		return colorTemperature;
	}

	/**
	 * Sets the color temperature.
	 * 
	 * @param colorTemperature the new color temperature
	 */
	public void setColorTemperature(String[][] colorTemperature)
	{
		this.colorTemperature = colorTemperature;
	}

	/**
	 * @return the voltageRange
	 */
	public String[][] getVoltageRange()
	{
		return voltageRange;
	}

	/**
	 * @param voltageRange the voltageRange to set
	 */
	public void setVoltageRange(String[][] voltageRange)
	{
		this.voltageRange = voltageRange;
	}

	/**
	 * Gets the model number.
	 * 
	 * @return the model number
	 */
	public String[][] getModelNumber()
	{
		return modelNumber;
	}

	/**
	 * Sets the model number.
	 * 
	 * @param modelNumber the new model number
	 */
	public void setModelNumber(String[][] modelNumber)
	{
		this.modelNumber = modelNumber;
	}

	/**
	 * Gets the firmware version.
	 * 
	 * @return the firmware version
	 */
	public String[][] getFirmwareVersion()
	{
		return firmwareVersion;
	}

	/**
	 * Sets the firmware version.
	 * 
	 * @param firmwareVersion the new firmware version
	 */
	public void setFirmwareVersion(String[][] firmwareVersion)
	{
		this.firmwareVersion = firmwareVersion;
	}

	/**
	 * Gets the bulb serial number.
	 * 
	 * @return the bulb serial number
	 */
	public String[][] getBulbSerialNumber()
	{
		return bulbSerialNumber;
	}

	/**
	 * Sets the bulb serial number.
	 * 
	 * @param bulbSerialNumber the new bulb serial number
	 */
	public void setBulbSerialNumber(String[][] bulbSerialNumber)
	{
		this.bulbSerialNumber = bulbSerialNumber;
	}

	/**
	 * Gets the upper assembly serial number.
	 * 
	 * @return the upper assembly serial number
	 */
	public String[][] getUpperAssemblySerialNumber()
	{
		return upperAssemblySerialNumber;
	}

	/**
	 * Sets the upper assembly serial number.
	 * 
	 * @param upperAssemblySerialNumber the new upper assembly serial number
	 */
	public void setUpperAssemblySerialNumber(String[][] upperAssemblySerialNumber)
	{
		this.upperAssemblySerialNumber = upperAssemblySerialNumber;
	}

	/**
	 * Gets the lower assembly serial number.
	 * 
	 * @return the lower assembly serial number
	 */
	public String[][] getLowerAssemblySerialNumber()
	{
		return lowerAssemblySerialNumber;
	}

	/**
	 * Sets the lower assembly serial number.
	 * 
	 * @param lowerAssemblySerialNumber the new lower assembly serial number
	 */
	public void setLowerAssemblySerialNumber(String[][] lowerAssemblySerialNumber)
	{
		this.lowerAssemblySerialNumber = lowerAssemblySerialNumber;
	}

	/**
	 * @return the lightDriverSerialNumber
	 */
	public String[][] getLightDriverSerialNumber()
	{
		return lightDriverSerialNumber;
	}

	/**
	 * @param lightDriverSerialNumber the lightDriverSerialNumber to set
	 */
	public void setLightDriverSerialNumber(String[][] lightDriverSerialNumber)
	{
		this.lightDriverSerialNumber = lightDriverSerialNumber;
	}

	/**
	 * @return the dateAdded
	 */
	public String[][] getDateAdded()
	{
		return dateAdded;
	}

	/**
	 * @param dateAdded the dateAdded to set
	 */
	public void setDateAdded(String[][] dateAdded)
	{
		this.dateAdded = dateAdded;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FilterJsonResult [getGroups()=" + Arrays.toString(getGroups()) + ", getStatus()="
				+ Arrays.toString(getStatus()) + ", getTags()=" + Arrays.toString(getTags()) + ", getEventSchedule()="
				+ Arrays.toString(getEventSchedule()) + ", getOffsetSchedule()=" + Arrays.toString(getOffsetSchedule())
				+ ", getConfiguration()=" + Arrays.toString(getConfiguration()) + ", getQueryType()="
				+ Arrays.toString(getQueryType()) + ", getLightTypes()=" + getLightTypes() + ", getDimmable()="
				+ Arrays.toString(getDimmable()) + ", getEventType()=" + Arrays.toString(getEventType())
				+ ", getUsers()=" + Arrays.toString(getUsers()) + ", getAlarmType()=" + Arrays.toString(getAlarmType())
				+ ", getWarningType()=" + Arrays.toString(getWarningType()) + ", getActionType()="
				+ Arrays.toString(getActionType()) + ", getEcoMode()=" + Arrays.toString(getEcoMode())
				+ ", getHousingColor()=" + Arrays.toString(getHousingColor()) + ", getColorTemperature()="
				+ Arrays.toString(getColorTemperature()) + ", getVoltageRange()=" + Arrays.toString(getVoltageRange())
				+ ", getModelNumber()=" + Arrays.toString(getModelNumber()) + ", getFirmwareVersion()="
				+ Arrays.toString(getFirmwareVersion()) + ", getBulbSerialNumber()="
				+ Arrays.toString(getBulbSerialNumber()) + ", getUpperAssemblySerialNumber()="
				+ Arrays.toString(getUpperAssemblySerialNumber()) + ", getLowerAssemblySerialNumber()="
				+ Arrays.toString(getLowerAssemblySerialNumber()) + "]";
	}
}
