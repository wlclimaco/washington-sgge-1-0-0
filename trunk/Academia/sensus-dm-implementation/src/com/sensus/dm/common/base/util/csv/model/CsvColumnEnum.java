package com.sensus.dm.common.base.util.csv.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum CsvColumnEnum.
 * 
 * @author QAT Global.
 */
public enum CsvColumnEnum implements IStringEnum
{

	/** The address. */
	ADDRESS("ADDRESS"),

	/** The action date. */
	ACTION_DATE("ACTION_DATE"),

	/** The action date sch. */
	ACTION_DATE_SCH("ACTION_DATE_SCH"),

	/** The action name. */
	ACTION_NAME("ACTION_NAME"),

	/** The action name sch. */
	ACTION_NAME_SCH("ACTION_NAME_SCH"),

	/** The action type. */
	ACTION_TYPE("ACTION_TYPE"),

	/** The action type sch. */
	ACTION_TYPE_SCH("ACTION_TYPE_SCH"),

	/** The alarm. */
	ALARM("ALARM"),

	/** The daily consumption average. */
	DAILY_CONSUMPTION_AVERAGE("DAILY_CONSUMPTION_AVERAGE"),

	/** The date added. */
	DATE_ADDED("DATE_ADDED"),

	/** The deliver on. */
	DELIVER_ON("DELIVER_ON"),

	/** The description. */
	DESCRIPTION("DESCRIPTION"),

	/** The device id. */
	DEVICE_ID("DEVICE_ID"),

	/** The device count. */
	DEVICE_COUNT("DEVICE_COUNT"),

	/** The devices failed. */
	DEVICES_FAILED("DEVICES_FAILED"),

	/** The devices total. */
	DEVICES_TOTAL("DEVICES_TOTAL"),

	/** The device type. */
	DEVICE_TYPE("DEVICE_TYPE"),

	/** The encryption support. */
	ENCRYPTION_SUPPORT("ENCRYPTION_SUPPORT"),

	/** The event. */
	EVENT("EVENT"),

	/** The event name. */
	EVENT_NAME("EVENT_NAME"),

	/** The frequency. */
	FREQUENCY("FREQUENCY"),

	/** The flexnet. */
	FLEXNET("FLEXNET"),

	/** The group type. */
	GROUP_TYPE("GROUP_TYPE"),

	/** The full participation. */
	FULL_PARTICIPATION("FULL_PARTICIPATION"),

	/** The last heard. */
	LAST_HEARD("LAST_HEARD"),

	/** The leak time. */
	LEAK_TIME("LEAK_TIME"),

	/** The lifecycle state. */
	LIFECYCLE_STATE("LIFECYCLE_STATE"),

	/** The install date. */
	INSTALL_DATE("INSTALL_DATE"),

	/** The name. */
	NAME("NAME"),

	/** The network address. */
	NETWORK_ADDRESS("NETWORK_ADDRESS"),

	/** The message error. */
	MESSAGE_ERROR("ERROR"),

	/** The network status. */
	NETWORK_STATUS("NETWORK_STATUS"),

	/** The modified date. */
	MODIFIED_DATE("modified_date"),

	/** The opt out. */
	OPT_OUT("OPT_OUT"),

	/** The parent device id. */
	PARENT_DEVICE_ID("PARANT_DEVICE_ID"),

	/** The participation. */
	PARTICIPATION("PARTICIPATION"),

	/** The premise id. */
	PREMISE_ID("PREMISE_ID"),

	/** The prior consumption. */
	PRIOR_CONSUMPTION("PRIOR_CONSUMPTION"),

	/** The prior consumption percentage. */
	PRIOR_CONSUMPTION_PERCENTAGE("PRIOR_CONSUMPTION_PERCENTAGE"),

	/** The process item state. */
	PROCESS_ITEM_STATE("PROCESS_ITEM_STATE"),

	/** The quarantine. */
	QUARANTINE("QUARANTINE"),

	/** The schedule by. */
	SCHEDULE_BY("SCHEDULE_BY"),

	/** The status. */
	STATUS("STATUS"),

	/** The start time. */
	START_TIME("START_TIME"),

	/** The schedule status. */
	SCHEDULE_STATUS("SCHEDULE_STATUS"),

	/** The recent consumption. */
	RECENT_CONSUMPTION("RECENT_CONSUMPTION"),

	/** The recent consumption percentage. */
	RECENT_CONSUMPTION_PERCENTAGE("RECENT_CONSUMPTION_PERCENTAGE"),

	/** The remote disconnect. */
	REMOTE_DISCONNECT("REMOTE_DISCONNECT"),

	/** The requested by. */
	REQUESTED_BY("REQUESTED_BY"),

	/** The read time. */
	READ_TIME("READ_TIME"),

	/** The read value. */
	READ_VALUE("READ_VALUE"),

	/** The peak demand. */
	PEAK_DEMAND("PEAK_DEMAND"),

	/** The demand units. */
	DEMAND_UNITS("DEMAND_UNITS"),

	/** The peak time. */
	PEAK_TIME("PEAK_TIME"),

	/** The reading date. */
	READING_DATE("READING_DATE"),

	/** The reset date. */
	RESET_DATE("RESET_DATE"),

	/** The counter. */
	COUNTER("COUNTER"),

	/** The tier. */
	TIER("TIER"),

	/** The relay. */
	RELAY("RELAY"),

	/** The tamper time. */
	TAMPER_TIME("TAMPER_TIME"),

	/** The enrollment group. */
	ENROLLMENT_GROUP("ENROLLMENT_GROUP"),

	/** The device class. */
	DEVICE_CLASS("DEVICE_CLASS"),

	/** The randomize duration. */
	RANDOMIZE_DURATION("RANDOMIZE_DURATION");

	/** The property Id. */
	private String columnValue;

	/**
	 * Instantiates a new csv column enum.
	 * 
	 * @param paramColumnValue the param column value
	 */
	private CsvColumnEnum(String paramColumnValue)
	{
		columnValue = paramColumnValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return columnValue;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the csv column enum
	 */
	public static CsvColumnEnum enumForValue(String value)
	{
		for (CsvColumnEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values.
	 * 
	 * @return the valid values
	 */
	public static String getValidValues()
	{
		CsvColumnEnum[] enums = CsvColumnEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (CsvColumnEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
