package com.sensus.dm.common.device.model;

import com.sensus.common.model.IStringEnum;

/**
 * Meter Column Enum.
 * 
 * @author QAT Brazil.
 * 
 */
public enum DeviceColumnEnum implements IStringEnum
{
	/** The FLEXNET_ID. */
	FLEXNET_ID("flexnet_id"),

	/** The DEVID e_ id. */
	DEVICE_ID("device_id"),

	/** The NETWOR k_ address. */
	NETWORK_ADDRESS("network_address"),

	/** The CITY_NAME. */
	CITY_NAME("city"),

	/** The ZIP_CODE. */
	ZIP_CODE("zip"),

	/** The ADDRESS. */
	ADDRESS("address"),

	/** The INSTALL_DATE. */
	INSTALL_DATE("install_date"),

	/** The DEVICE_TYPE. */
	DEVICE_TYPE("device_type"),

	/** The device type id. */
	DEVICE_TYPE_ID("device_type_id"),

	/** The device subtype. */
	DEVICE_SUBTYPE("device_subtype"),

	/** The DESCRIPTION. */
	DESCRIPTION("DESCRIPTION"),

	/** The MAP_IT. */
	MAP_IT("MAP_IT"),

	/** The ENCRYPTION_STATUS. */
	ENCRYPTION_STATUS("mr.encrypted"),

	/** The LIFECYCLE_STATUS. */
	LIFECYCLE_STATUS("LIFECYCLE_STATUS"),

	/** The LIFECYCLE_STATE. */
	LIFECYCLE_STATE("lifecycle_state"),

	/** The LATITUDE. */
	LATITUDE("latitude"),

	/** The LONGITUDE. */
	LONGITUDE("longitude"),

	/** The DEVICE_TYPE. */
	METER_TYPE("meter_type_id"),

	/** The ENCRYPTION_SUPPORT. */
	ENCRYPTION_SUPPORT("encrypted"),

	/** The CONNECTION_STATUS. */
	CONNECTION_STATUS("connection_status"),

	/** The REMOTE_DISCONNECT. */
	REMOTE_DISCONNECT("remote_disconnect"),

	/** The DEVICE_TYPE_DESC. */
	DEVICE_TYPE_DESC("UPPER(device_type_desc)"),

	/** The premise id. */
	PREMISE_ID("premise_id"),

	/** The parent id. */
	PARENT_ID("parent_id"),

	/** The status. */
	STATUS("status"),

	/** The status meter. */
	STATUS_METER("STATUS_METER"),

	/** The last heard. */
	LAST_HEARD("last_heard"),

	/** The alarm. */
	ALARM("COALESCE(alarm, 'ZZZ')"),

	/** The alarm time. */
	ALARM_TIME("alarm_time"),

	/** The quarantine. */
	QUARANTINE("quarantine");

	/** The property Id. */
	private String columnValue;

	/**
	 * Instantiates a new property enum.
	 * 
	 * @param paramColumnValue the param column value
	 */
	private DeviceColumnEnum(String paramColumnValue)
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
	 * @return the property enum
	 */
	public static DeviceColumnEnum enumForValue(String value)
	{
		for (DeviceColumnEnum e : values())
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
		DeviceColumnEnum[] enums = DeviceColumnEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (DeviceColumnEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}