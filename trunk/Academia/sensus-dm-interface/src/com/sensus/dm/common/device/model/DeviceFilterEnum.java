package com.sensus.dm.common.device.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum DeviceFilterEnum.
 * 
 * @author QAT Brazil.
 */
public enum DeviceFilterEnum implements IStringEnum
{

	/** The ADDRESS. */
	ADDRESS("ADDRESS"),

	/** The CITY_NAME. */
	CITY_NAME("CITY"),

	/** The CONNECTIO n_ status. */
	CONNECTION_STATUS("CONNECTION_STATUS"),

	/** The DESCRIPTION. */
	DESCRIPTION("DESCRIPTION"),

	/** The DEVICE_ID. */
	DEVICE_ID("DEVICE_ID"),

	/** The DEVICE_TYPE. */
	DEVICE_TYPE("DEVICE_TYPE"),

	/** DEVICE_TYPE_DESCRIPTION. */
	DEVICE_TYPE_DESCRIPTION("device_type_descripton"),

	/** The DATE_FORMAT. */
	DATE_FORMAT("DATE_FORMAT"),

	/** The ENCRYPTIO status. */
	ENCRYPTION_STATUS("mr.encryptable"),

	/** The ENCRYPTION supported. */
	ENCRYPTION_SUPPORTED("mr.encrypted"),

	/** The ENGINEERING_VERSION. */
	ENGINEERING_VERSION("ENGINEERING_VERSION"),

	/** The FLEXNET_ID. */
	FLEXNET_ID("flexnet_id"),

	/** The GROUP. */
	GROUP("GROUP_ID"),

	/** The INSTALL_DATE. */
	INSTALL_DATE("INSTALL_DATE"),

	/** The INSTALL_DATE_START. */
	INSTALL_DATE_START("INSTALL_DATE_START"),

	/** The INSTALL_DATE_END. */
	INSTALL_DATE_END("INSTALL_DATE_END"),

	/** The LAS t_ rea d_ value. */
	LAST_READ_VALUE("LAST_READ_VALUE"),

	/** The LAS t_ rea d_ units. */
	LAST_READ_UNITS("LAST_READ_UNITS"),

	/** The LAS t_ rea d_ time. */
	LAST_READ_TIME("LAST_READ_TIME"),

	/** The LATITUDE. */
	LATITUDE("latitude"),

	/** The LIFECYCL e_ status. */
	LIFECYCLE_STATUS("LIFECYCLE_STATUS"),

	/** The LIFECYCL e_ state. */
	LIFECYCLE_STATE("LIFECYCLE_STATE"),

	/** The LONGITUDE. */
	LONGITUDE("longitude"),

	/** The METE r_ firmware. */
	METER_FIRMWARE("METER_FIRMWARE"),

	/** The flexnet firmware. */
	FLEXNET_FIRMWARE("FLEXNET_FIRMWARE"),

	/** The MAJOR_VERSION. */
	MAJOR_VERSION("MAJOR_VERSION"),

	/** The MINOR_VERSION. */
	MINOR_VERSION("MINOR_VERSION"),

	/** The NETWORk ADDRESS. */
	NETWORK_ADDRESS("NETWORK_ADDRESS"),

	/** The PATCH_VERSION. */
	PATCH_VERSION("PATCH_VERSION"),

	/** The PREMISE ID. */
	PREMISE_ID("PREMISE_ID"),

	/** The PROCESSES. */
	PROCESSES("PROCESS"),

	/** The QUERY. */
	QUERY("QUERY"),

	/** The RADIO_FIRMWARE. */
	RADIO_FIRMWARE("RADIO_FIRMWARE"),

	/** The REMOT e_ disconnect. */
	REMOTE_DISCONNECT("REMOTE_DISCONNECT"),

	/** The status meter. */
	STATUS_METER("STATUS_METER"),

	/** The TAG. */
	TAG("TAG"),

	/** The TO u_ status. */
	TOU_STATUS("TOU_STATUS"),

	/** The TO u_ schedule. */
	TOU_SCHEDULE("TOU_SCHEDULE"),

	/** The TO u_ available. */
	TOU_AVAILABLE("TOU_AVAILABLE"),

	/** The SEARCH. */
	SEARCH("SEARCH"),

	/** The ZIP_CODE. */
	ZIP_CODE("ZIP"),

	/** The device type enum. */
	DEVICE_TYPE_ENUM("DEVICE_TYPE_ENUM"),

	/** The device subtype. */
	DEVICE_SUBTYPE("DEVICE_SUBTYPE"),

	/** The alarm. */
	ALARM("ALARM"),

	/** The alert. */
	ALERT("ALERT"),

	/** The quarantine. */
	QUARANTINE("QUARANTINE");

	/** The property Id. */
	private String filterValue;

	/**
	 * Instantiates a new property enum.
	 * 
	 * @param paramFilterValue the param filter value
	 */
	private DeviceFilterEnum(String paramFilterValue)
	{
		filterValue = paramFilterValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return filterValue;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the property enum
	 */
	public static DeviceFilterEnum enumForValue(String value)
	{
		for (DeviceFilterEnum e : values())
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
		DeviceFilterEnum[] enums = DeviceFilterEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (DeviceFilterEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
