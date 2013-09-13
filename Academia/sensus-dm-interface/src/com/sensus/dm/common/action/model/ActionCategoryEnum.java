package com.sensus.dm.common.action.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ActionTypeCategoryEnum.
 * 
 * @author QAT Global
 */
public enum ActionCategoryEnum implements IIntegerEnum
{
	/** The demand reset. */
	DEMAND_RESET(1, "sensus.dm.action.category.demand.reset"),

	/** The connect disconnect. */
	CONNECT_DISCONNECT(2, "sensus.dm.action.category.connect.disconnect"),

	/** The han. */
	HAN(3, "sensus.dm.action.category.han"),

	/** The get data from device. */
	GET_DATA_FROM_DEVICE(4, "sensus.dm.action.category.get.data.from.device"),

	/** The delete device. */
	DELETE_DEVICE(6, "sensus.dm.action.category.delete.device"),

	/** The demand read. */
	DEMAND_READ(7, "sensus.dm.action.category.demand.read"),

	/** The demand read. */
	DEMAND_SET(8, "sensus.dm.action.category.demand.set"),

	/** The manage alerts. */
	MANAGE_ALERTS(9, "sensus.dm.action.category.manage.alert"),

	/** The demand response. */
	DEMAND_RESPONSE(10, "sensus.dm.action.category.demand.response"),

	/** The service switch. */
	SERVICE_SWITCH(11, "sensus.dm.action.category.service.switch");

	/** The action type category. */
	private Integer actionTypeCategory;

	/** The action type category name. */
	private String actionTypeCategoryName;

	/**
	 * Instantiates a new action type category enum.
	 * 
	 * @param actionTypeCategoryParam the action type category param
	 * @param actionTypeCategoryNameParam the action type category name param
	 */
	private ActionCategoryEnum(Integer actionTypeCategoryParam, String actionTypeCategoryNameParam)
	{
		actionTypeCategory = actionTypeCategoryParam;
		actionTypeCategoryName = actionTypeCategoryNameParam;
	}

	/**
	 * Gets the action type category.
	 * 
	 * @return the action type category
	 */
	public Integer getActionTypeCategory()
	{
		return actionTypeCategory;
	}

	/**
	 * Gets the action type category name.
	 * 
	 * @return the action type category name
	 */
	public String getActionTypeCategoryName()
	{
		return actionTypeCategoryName;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the action type category enum
	 */
	public static ActionCategoryEnum enumForValue(Integer value)
	{
		for (ActionCategoryEnum e : values())
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
		ActionCategoryEnum[] enums = ActionCategoryEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (ActionCategoryEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return actionTypeCategory;
	}
}
