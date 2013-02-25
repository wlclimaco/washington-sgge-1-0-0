package com.sensus.mlc.process.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ProcessResultEnum.
 */
public enum LCActionTypeEnum implements IIntegerEnum
{

	/** The ADD_SMP_TO_GRP. */
	ADD_SMP_TO_GRP(1, LCActionCategoryEnum.MANAGE_GROUPS),

	/** The DEL_SMP_FROM_GRP. */
	DEL_SMP_FROM_GRP(2, LCActionCategoryEnum.MANAGE_GROUPS),

	/** The SET_INTENSITY_ BY_GRP. */
	SET_INTENSITY_BY_GRP(3, LCActionCategoryEnum.MANAGE_GROUPS),

	/** The SET_INTENSITY_BY_LIGHT. */
	SET_INTENSITY_BY_LIGHT(4, LCActionCategoryEnum.CONTROL_LIGHTS),

	/** The DEL_GRP. */
	DEL_GRP(5, LCActionCategoryEnum.MANAGE_GROUPS),

	/** The SET_PROTECTED. */
	SET_PROTECTED(6, LCActionCategoryEnum.MANAGE_LIGHTS),

	/** The GET_LIGHT_STATUS. */
	GET_LIGHT_STATUS(7, LCActionCategoryEnum.MANAGE_LIGHTS),

	/** The ABORT. */
	ABORT(8, LCActionCategoryEnum.UNKNOWN),

	/** The DEL_ SCHEDULE. */
	DEL_SCHEDULE(9, LCActionCategoryEnum.MANAGE_SCHEDULES),

	/** The DEL_SMP_FROM_SCHEDULE. */
	DEL_SMP_FROM_SCHEDULE_EVENT(10, LCActionCategoryEnum.MANAGE_LIGHTS),

	/** The DEL_SMP_FROM_SCHEDULE. */
	DEL_SMP_FROM_SCHEDULE_OFFSET(11, LCActionCategoryEnum.CONTROL_LIGHTS),

	/** The ADD_ SMP_TO_SCHEDULE_EVENT. */
	ADD_SMP_TO_SCHEDULE_EVENT(12, LCActionCategoryEnum.MANAGE_LIGHTS),

	/** The INSERT_GROUP. */
	INSERT_GROUP(13, LCActionCategoryEnum.MANAGE_GROUPS),

	/** The INSERT_SCHEDULE. */
	INSERT_SCHEDULE(14, LCActionCategoryEnum.MANAGE_SCHEDULES),

	/** The INSERT_TAG. */
	INSERT_TAG(15, LCActionCategoryEnum.MANAGE_TAGS),

	/** The DELETE_TAG. */
	DELETE_TAG(16, LCActionCategoryEnum.MANAGE_TAGS),

	/** The AUTO_GROUP. */
	AUTO_GROUP(17, LCActionCategoryEnum.MANAGE_TAGS),

	/** The UPDATE_GROUP. */
	UPDATE_GROUP(18, LCActionCategoryEnum.MANAGE_GROUPS),

	/** The UPDATE_SCHEDULE. */
	UPDATE_SCHEDULE(19, LCActionCategoryEnum.MANAGE_SCHEDULES),

	/** The ADD_ SMP_TO_SCHEDULE. */
	ADD_SMP_TO_TAG(20, LCActionCategoryEnum.MANAGE_TAGS),

	/** The DEL_SMP_FROM_GRP. */
	DEL_SMP_FROM_TAG(21, LCActionCategoryEnum.MANAGE_TAGS),

	/** The CLEAR_ALARM. */
	CLEAR_ALARM(22, LCActionCategoryEnum.CONTROL_LIGHTS),

	/** The CLEAR_ALL_ALARMS. */
	CLEAR_ALL_ALARMS(23, LCActionCategoryEnum.CONTROL_LIGHTS),

	/** The UPDATE_LIGHT_LAT_LONG. */
	UPDATE_LIGHT_LAT_LONG(24, LCActionCategoryEnum.MANAGE_LIGHTS),

	/** The UPDATE_LIGHT_POLE_ID. */
	UPDATE_LIGHT_POLE_ID(25, LCActionCategoryEnum.MANAGE_LIGHTS),

	/** The GENERATE_CSV_FILE. */
	GENERATE_CSV_FILE(26, LCActionCategoryEnum.UNKNOWN),

	/** The ADD_ SMP_TO_SCHEDULE_OFFSET. */
	ADD_SMP_TO_SCHEDULE_OFFSET(27, LCActionCategoryEnum.MANAGE_LIGHTS),

	/** The SETUP_DIMMING_CONFIGURATION. */
	SETUP_DIMMING_CONFIGURATION(28, LCActionCategoryEnum.MANAGE_LIGHTS),

	/** The reset light min max. */
	RESET_LIGHT_MIN_MAX(29, LCActionCategoryEnum.MANAGE_LIGHTS),

	/** The insert user. */
	INSERT_USER(30, LCActionCategoryEnum.UNKNOWN),

	/** The update light status. */
	UPDATE_LIGHT_STATUS(31, LCActionCategoryEnum.MANAGE_LIGHTS),

	/** The SET_BLINK_BY_LIGHT. */
	SET_BLINK_BY_LIGHT(32, LCActionCategoryEnum.CONTROL_LIGHTS),

	/** The SET_CLEAR_OVERRIDE_BY_LIGHT. */
	SET_CLEAR_OVERRIDE_BY_LIGHT(33, LCActionCategoryEnum.MANAGE_LIGHTS),

	/** The DEL_SMP_FROM_SCHEDULE. */
	DEL_SCHEDULE_OFFSET_FROM_SMP(34, LCActionCategoryEnum.MANAGE_LIGHTS),

	/** The configuration. */
	CONFIGURATION(35, LCActionCategoryEnum.MANAGE_LIGHTS),

	/** The import csv file. */
	IMPORT_CSV_FILE(36, LCActionCategoryEnum.MANAGE_LIGHTS),

	/** The turn on. */
	TURN_ON(37, LCActionCategoryEnum.CONTROL_LIGHTS),

	/** The turn off. */
	TURN_OFF(38, LCActionCategoryEnum.CONTROL_LIGHTS),

	/** The dim. */
	DIM(39, LCActionCategoryEnum.CONTROL_LIGHTS),

	INSERT_EMPRESA(40, LCActionCategoryEnum.CONTROL_LIGHTS),

	INSERT_FILIAL(41, LCActionCategoryEnum.CONTROL_LIGHTS);

	/** The mlc action type. */
	private Integer mlcActionType;

	/** The category. */
	private LCActionCategoryEnum category;

	/**
	 * Instantiates a new lC action type enum.
	 *
	 * @param mlcActionTypeEnumInd the mlc action type enum ind
	 * @param actionCategory the action category
	 */
	private LCActionTypeEnum(Integer mlcActionTypeEnumInd, LCActionCategoryEnum actionCategory)
	{
		mlcActionType = mlcActionTypeEnumInd;
		category = actionCategory;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return mlcActionType;
	}

	/**
	 * Gets the action category value.
	 *
	 * @return the action category value
	 */
	public Integer getActionCategoryValue()
	{
		return category.getValue();
	}

	/**
	 * Enum for value.
	 *
	 * @param value the value
	 * @return the lC action type enum
	 */
	public static LCActionTypeEnum enumForValue(Integer value)
	{
		for (LCActionTypeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the action types by category.
	 *
	 * @param category the category
	 * @return the action types by category
	 */
	public static List<Integer> getActionTypesByCategory(LCActionCategoryEnum category)
	{
		List<Integer> actionTypeIdList = new ArrayList<Integer>();

		for (LCActionTypeEnum action : values())
		{
			if (action.category == category)
			{
				actionTypeIdList.add(action.getValue());
			}
		}

		return actionTypeIdList;
	}

	/**
	 * Gets the valid values.
	 *
	 * @return the valid values
	 */
	public static String getValidValues()
	{
		LCActionTypeEnum[] enums = LCActionTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (LCActionTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
