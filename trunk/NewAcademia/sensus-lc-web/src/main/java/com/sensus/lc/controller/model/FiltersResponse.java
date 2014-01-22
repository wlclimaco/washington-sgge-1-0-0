package com.sensus.lc.controller.model;

import com.sensus.lc.light.model.response.ColumnFilterResponse;

public class FiltersResponse extends ColumnFilterResponse
{

	/** The custom_filters. */
	private FiltersModel custom_filters;

	private FiltersModel address;

	/** The action type. */
	private FiltersModel action_type;

	/** The alarm type. */
	private FiltersModel alarm_type;

	/** The bulb_serial_number. */
	private FiltersModel bulb_serial_number;

	/** The configuration. */
	private FiltersModel configuration;

	/** The date_added. */
	private FiltersModel date_added;

	/** The ecomode. */
	private FiltersModel ecomode;

	/** The event_schedule. */
	private FiltersModel event_schedule;

	/** The event_type. */
	private FiltersModel eventType;

	/** The firmware_version. */
	private FiltersModel firmware_version;

	/** The groups. */
	private FiltersModel groups;

	/** The light_driver_serial_number. */
	private FiltersModel light_driver_serial_number;

	/** The lower_assembly_serial_number. */
	private FiltersModel lower_assembly_serial_number;

	/** The meter_firmware. */
	private FiltersModel meter_firmware;

	/** The offset_schedule. */
	private FiltersModel offset_schedule;

	/** The search. */
	private FiltersModel search;

	/** The state. */
	private FiltersModel lifecycle_state;

	/** The alerts. */
	private FiltersModel alerts;

	/** The tags. */
	private FiltersModel tags;

	private FiltersModel upper_assembly_serial_number;

	/** The users. */
	private FiltersModel users;

	/** The warning_type. */
	private FiltersModel warning_type;

	/** The color_temperature. */
	private FiltersModel color_temperature;

	/** The housing_color. */
	private FiltersModel housing_color;

	/** The voltage_range. */
	private FiltersModel voltage_range;

	/** The model_number. */
	private FiltersModel model_number;

	/** The install_date. */
	private FiltersModel install_date;

	/** The light_type. */
	private ViewFilterLightTypes light_type;

	/** The queryType. */
	private String[][] queryType;

	/**
	 * @return the queryType
	 */
	public String[][] getQueryType()
	{
		return queryType;
	}

	/**
	 * @param queryType the queryType to set
	 */
	public void setQueryType(String[][] queryType)
	{
		this.queryType = queryType;
	}

	/**
	 * @return the light_type
	 */
	public ViewFilterLightTypes getLight_type()
	{
		return light_type;
	}

	/**
	 * @param light_type the light_type to set
	 */
	public void setLight_type(ViewFilterLightTypes light_type)
	{
		this.light_type = light_type;
	}

	/**
	 * @return the configuration
	 */
	public FiltersModel getConfiguration()
	{
		return configuration;
	}

	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(FiltersModel configuration)
	{
		this.configuration = configuration;
	}

	/**
	 * @return the ecomode
	 */
	public FiltersModel getEcomode()
	{
		return ecomode;
	}

	/**
	 * @param ecomode the ecomode to set
	 */
	public void setEcomode(FiltersModel ecomode)
	{
		this.ecomode = ecomode;
	}

	/**
	 * @return the search
	 */
	public FiltersModel getSearch()
	{
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(FiltersModel search)
	{
		this.search = search;
	}

	/**
	 * @return the state
	 */
	public FiltersModel getLifecycle_state()
	{
		return lifecycle_state;
	}

	/**
	 * @param status the state to set
	 */
	public void setLifecycle_state(FiltersModel lifecycle_state)
	{
		this.lifecycle_state = lifecycle_state;
	}

	/**
	 * Gets the alert type.
	 * 
	 * @return the alert type
	 */
	public FiltersModel getAlerts()
	{
		return alerts;
	}

	/**
	 * Sets the alert type.
	 * 
	 * @param alertType the new alert type
	 */
	public void setAlerts(FiltersModel alerts)
	{
		this.alerts = alerts;
	}

	/**
	 * @return the tags
	 */
	public FiltersModel getTags()
	{
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(FiltersModel tags)
	{
		this.tags = tags;
	}

	/**
	 * @return the users
	 */
	public FiltersModel getUsers()
	{
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(FiltersModel users)
	{
		this.users = users;
	}

	/**
	 * @return the eventType
	 */
	public FiltersModel getEventType()
	{
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(FiltersModel eventType)
	{
		this.eventType = eventType;
	}

	/**
	 * @return the groups
	 */
	public FiltersModel getGroups()
	{
		return groups;
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(FiltersModel groups)
	{
		this.groups = groups;
	}

	/**
	 * @return the action_type
	 */
	public FiltersModel getAction_type()
	{
		return action_type;
	}

	/**
	 * @param action_type the action_type to set
	 */
	public void setAction_type(FiltersModel action_type)
	{
		this.action_type = action_type;
	}

	/**
	 * @return the alarm_type
	 */
	public FiltersModel getAlarm_type()
	{
		return alarm_type;
	}

	/**
	 * @param alarm_type the alarm_type to set
	 */
	public void setAlarm_type(FiltersModel alarm_type)
	{
		this.alarm_type = alarm_type;
	}

	/**
	 * @return the event_schedule
	 */
	public FiltersModel getEvent_schedule()
	{
		return event_schedule;
	}

	/**
	 * @param event_schedule the event_schedule to set
	 */
	public void setEvent_schedule(FiltersModel event_schedule)
	{
		this.event_schedule = event_schedule;
	}

	/**
	 * @return the custom_filters
	 */
	public FiltersModel getCustom_filters()
	{
		return custom_filters;
	}

	/**
	 * @param custom_filters the custom_filters to set
	 */
	public void setCustom_filters(FiltersModel custom_filters)
	{
		this.custom_filters = custom_filters;
	}

	/**
	 * @return the offset_schedule
	 */
	public FiltersModel getOffset_schedule()
	{
		return offset_schedule;
	}

	/**
	 * @param offset_schedule the offset_schedule to set
	 */
	public void setOffset_schedule(FiltersModel offset_schedule)
	{
		this.offset_schedule = offset_schedule;
	}

	/**
	 * @return the warning_type
	 */
	public FiltersModel getWarning_type()
	{
		return warning_type;
	}

	/**
	 * @param warning_type the warning_type to set
	 */
	public void setWarning_type(FiltersModel warning_type)
	{
		this.warning_type = warning_type;
	}

	/**
	 * @return the color_temperature
	 */
	public FiltersModel getColor_temperature()
	{
		return color_temperature;
	}

	/**
	 * @param color_temperature the color_temperature to set
	 */
	public void setColor_temperature(FiltersModel color_temperature)
	{
		this.color_temperature = color_temperature;
	}

	/**
	 * @return the housing_color
	 */
	public FiltersModel getHousing_color()
	{
		return housing_color;
	}

	/**
	 * @param housing_color the housing_color to set
	 */
	public void setHousing_color(FiltersModel housing_color)
	{
		this.housing_color = housing_color;
	}

	/**
	 * @return the voltage_range
	 */
	public FiltersModel getVoltage_range()
	{
		return voltage_range;
	}

	/**
	 * @param voltage_range the voltage_range to set
	 */
	public void setVoltage_range(FiltersModel voltage_range)
	{
		this.voltage_range = voltage_range;
	}

	/**
	 * @return the model_number
	 */
	public FiltersModel getModel_number()
	{
		return model_number;
	}

	/**
	 * @param model_number the model_number to set
	 */
	public void setModel_number(FiltersModel model_number)
	{
		this.model_number = model_number;
	}

	/**
	 * @return the address
	 */
	public FiltersModel getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(FiltersModel address)
	{
		this.address = address;
	}

	/**
	 * @return the install_date
	 */
	public FiltersModel getInstall_date()
	{
		return install_date;
	}

	/**
	 * @param install_date the install_date to set
	 */
	public void setInstall_date(FiltersModel install_date)
	{
		this.install_date = install_date;
	}

	/**
	 * @return the date_added
	 */
	public FiltersModel getDate_added()
	{
		return date_added;
	}

	/**
	 * @param date_added the date_added to set
	 */
	public void setDate_added(FiltersModel date_added)
	{
		this.date_added = date_added;
	}

	/**
	 * @return the firmware_version
	 */
	public FiltersModel getFirmware_version()
	{
		return firmware_version;
	}

	/**
	 * @param firmware_version the firmware_version to set
	 */
	public void setFirmware_version(FiltersModel firmware_version)
	{
		this.firmware_version = firmware_version;
	}

	/**
	 * @return the bulb_serial_number
	 */
	public FiltersModel getBulb_serial_number()
	{
		return bulb_serial_number;
	}

	/**
	 * @param bulb_serial_number the bulb_serial_number to set
	 */
	public void setBulb_serial_number(FiltersModel bulb_serial_number)
	{
		this.bulb_serial_number = bulb_serial_number;
	}

	/**
	 * @return the light_driver_serial_number
	 */
	public FiltersModel getLight_driver_serial_number()
	{
		return light_driver_serial_number;
	}

	/**
	 * @param light_driver_serial_number the light_driver_serial_number to set
	 */
	public void setLight_driver_serial_number(FiltersModel light_driver_serial_number)
	{
		this.light_driver_serial_number = light_driver_serial_number;
	}

	/**
	 * @return the lower_assembly_serial_number
	 */
	public FiltersModel getLower_assembly_serial_number()
	{
		return lower_assembly_serial_number;
	}

	/**
	 * @param lower_assembly_serial_number the lower_assembly_serial_number to set
	 */
	public void setLower_assembly_serial_number(FiltersModel lower_assembly_serial_number)
	{
		this.lower_assembly_serial_number = lower_assembly_serial_number;
	}

	/**
	 * @return the upper_assembly_serial_number
	 */
	public FiltersModel getUpper_assembly_serial_number()
	{
		return upper_assembly_serial_number;
	}

	/**
	 * @param upper_assembly_serial_number the upper_assembly_serial_number to set
	 */
	public void setUpper_assembly_serial_number(FiltersModel upper_assembly_serial_number)
	{
		this.upper_assembly_serial_number = upper_assembly_serial_number;
	}

	/**
	 * @return the meter_firmware
	 */
	public FiltersModel getMeter_firmware()
	{
		return meter_firmware;
	}

	/**
	 * @param meter_firmware the meter_firmware to set
	 */
	public void setMeter_firmware(FiltersModel meter_firmware)
	{
		this.meter_firmware = meter_firmware;
	}

}
