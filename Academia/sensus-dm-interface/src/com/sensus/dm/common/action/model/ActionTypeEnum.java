package com.sensus.dm.common.action.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ActionTypeEnum.
 * 
 * @author QAT Global
 */
public enum ActionTypeEnum implements IIntegerEnum
{

	/*
	 * Demand Reset Category
	 */
	/** The initiate demand reset event. */
	INITIATE_DEMAND_RESET_EVENT(1, "sensus.dm.action.initiate.demand.reset", ActionCategoryEnum.DEMAND_RESET),

	/*
	 * Han Device Category
	 */
	/** The connect to han. */
	CONNECT_TO_HAN(9, "sensus.dm.action.connect.to.han", ActionCategoryEnum.HAN),

	/** The connect han device. */
	CONNECT_HAN_DEVICE(10, "sensus.dm.action.connect.han.device", ActionCategoryEnum.HAN),

	/** The disconnect han device. */
	DISCONNECT_HAN_DEVICE(11, "sensus.dm.action.disconnect.han.device", ActionCategoryEnum.HAN),

	/** The import han device. */
	IMPORT_HAN_DEVICE(5, "sensus.dm.action.import.han.device", ActionCategoryEnum.HAN),

	/** The delete han device. */
	DELETE_HAN_DEVICE(16, "sensus.dm.action.delete.han.device", ActionCategoryEnum.HAN),

	/** The get han connection status. */
	GET_HAN_CONNECTION_STATUS(12, "sensus.dm.action.get.connection.status", ActionCategoryEnum.HAN),

	/** The send han text message. */
	SEND_HAN_TEXT_MESSAGE(6, "sensus.dm.action.send.han.text.message", ActionCategoryEnum.HAN),

	/** The cancel send han text message. */
	CANCEL_SEND_HAN_TEXT_MESSAGE(26, "sensus.dm.action.cancel.send.han.text.message", ActionCategoryEnum.HAN),

	/** The retry import han device. */
	RETRY_IMPORT_HAN_DEVICE(27, "sensus.dm.action.retry.import.han.device", ActionCategoryEnum.HAN),

	/*
	 * Demand Response Category
	 */
	/** The initiate demand response event. */
	INITIATE_DEMAND_RESPONSE_EVENT(4, "sensus.dm.action.demand.response", ActionCategoryEnum.DEMAND_RESPONSE),

	/** The initiate demand response setup. */
	INITIATE_DEMAND_RESPONSE_SETUP(7, "sensus.dm.action.setup.demand.response", ActionCategoryEnum.DEMAND_RESPONSE),

	/** The get demand response event status. */
	GET_DEMAND_RESPONSE_EVENT_STATUS(8, "sensus.dm.action.get.demand.response.event.status",
			ActionCategoryEnum.DEMAND_RESPONSE),

	/** The get demand response setup status. */
	GET_DEMAND_RESPONSE_SETUP_STATUS(24, "sensus.dm.action.get.demand.response.setup.status",
			ActionCategoryEnum.DEMAND_RESPONSE),

	/** The cancel demand response event. */
	CANCEL_DEMAND_RESPONSE_EVENT(25, "sensus.dm.action.cancel.demand.response", ActionCategoryEnum.DEMAND_RESPONSE),

	/*
	 * Service Switch Category
	 */
	/** The remote connect. */
	REMOTE_CONNECT(2, "sensus.dm.action.remote.connect", ActionCategoryEnum.SERVICE_SWITCH),

	/** The remote disconnect. */
	REMOTE_DISCONNECT(3, "sensus.dm.action.remote.disconnect", ActionCategoryEnum.SERVICE_SWITCH),

	/** The get remote connect status. */
	GET_REMOTE_CONNECT_STATUS(18, "sensus.dm.action.get.remote.connect.status",
			ActionCategoryEnum.SERVICE_SWITCH),

	/** The remote arm connect. */
	REMOTE_ARM_CONNECT(17, "sensus.dm.action.remote.arm.connect", ActionCategoryEnum.SERVICE_SWITCH),

	/*
	 * Get Data From Device Category
	 */
	/** The demand read. */
	DEMAND_READ(19, "sensus.dm.action.demand.read", ActionCategoryEnum.GET_DATA_FROM_DEVICE),

	/*
	 * Manage Alerts Category
	 */
	/** The get tamper detect timeout. */
	GET_TAMPER_DETECT_TIMER(22, "sensus.dm.action.get.tamper.detect.timer", ActionCategoryEnum.MANAGE_ALERTS),

	/** The set tamper detect timeout. */
	SET_TAMPER_DETECT_TIMER(23, "sensus.dm.action.set.tamper.detect.timer", ActionCategoryEnum.MANAGE_ALERTS);

	/** The action type. */
	private Integer actionType;

	/** The action type name. */
	private String actionTypeName;

	/** The action type category enum. */
	private ActionCategoryEnum actionCategoryEnum;

	/**
	 * Instantiates a new action type enum.
	 * 
	 * @param actionTypeParam the action type param
	 * @param actionTypeNameParam the action type name param
	 * @param actionCategoryEnumParam the action category enum param
	 */
	private ActionTypeEnum(Integer actionTypeParam, String actionTypeNameParam,
			ActionCategoryEnum actionCategoryEnumParam)
	{
		actionType = actionTypeParam;
		actionTypeName = actionTypeNameParam;
		actionCategoryEnum = actionCategoryEnumParam;
	}

	/**
	 * Gets the action type.
	 * 
	 * @return the action type
	 */
	public Integer getActionType()
	{
		return actionType;
	}

	/**
	 * Gets the action type name.
	 * 
	 * @return the action type name
	 */
	public String getActionTypeName()
	{
		return actionTypeName;
	}

	/**
	 * Gets the action type category enum.
	 * 
	 * @return the action type category enum
	 */
	public ActionCategoryEnum getActionCategoryEnum()
	{
		return actionCategoryEnum;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the action type enum
	 */
	public static ActionTypeEnum enumForValue(Integer value)
	{
		for (ActionTypeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Enum for description.
	 * 
	 * @param value the value
	 * @return the action type enum
	 */
	public static ActionTypeEnum enumForDescription(String value)
	{
		for (ActionTypeEnum e : values())
		{
			if (e.getActionTypeName().equals(value))
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
		ActionTypeEnum[] enums = ActionTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (ActionTypeEnum i : enums)
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
		return actionType;
	}
}
