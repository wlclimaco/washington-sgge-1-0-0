package com.sensus.lc.base.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum ObjectToBeValidatedKeyEnum.
 */
public enum ObjectToBeValidatedKeyEnum implements IStringEnum
{
	LIGHT_ID("lightId"),

	INITIAL_DATE("initialDate"),

	END_DATE("endDate"),

	PROPERTIES("properties"),

	CSV_FILE_NAME("csvFileName"),

	PROCESS_ID("processId"),

	LIGHT_PARAMETERS("lightParameters"),

	LIGHT_PROTECTED("lightProtected"),

	ALLOWED_GROUP_ID_LIST("allowedGroupIdList"),

	COLUMN_LIST("columnList"),

	FILTER_LIST("filterList"),

	PARAMETERS("parameters"),

	CSV_FILE("csvFile"),

	GROUP_LIST("groupList"),

	BOTTOM_LEFT_LAT("bottomLeftLat"),

	BOTTOM_LEFT_LON("bottomLeftLon"),

	TOP_RIGHT_LAT("topRightLat"),

	TOP_RIGHT_LON("topRightLon"),

	MAX_DEVICE_COUNT("maxDeviceCount"),

	NEW_PASSWORD("newPassword"),

	ACTUAL_PASSWORD("actualPassword"),

	SETTING_LIST("settingList"),

	LIGHT_LIST("lightList"),

	TAG_LIST("tagList"),

	OVERRIDE_ENUM("overrideEnum"),

	OVERRRIDE_PER_DATE("overridePerDate"),

	LIGHT_BLINK_ENUM("lightBlinkEnum"),

	PERCENTAGE("percentage");

	private String objectToBeValidatedKey;

	/**
	 * Instantiates a new object to be validated key enum.
	 * 
	 * @param key the key
	 */
	private ObjectToBeValidatedKeyEnum(String key)
	{
		this.objectToBeValidatedKey = key;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return this.objectToBeValidatedKey;
	}
}