package com.sensus.lc.process.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ProcessStatusReasonEnum.
 */
public enum ProcessStatusReasonEnum implements IIntegerEnum
{

	/** The MLCFAILURE. */
	LIGHT_PROTECTED(1),

	/** The RNISYNCFAILURE. */
	RNI_SYNC_FAILURE(2),

	/** The RNIASYNCFAILURE. */
	RNI_ASYNC_FAILURE(3),

	/** The ABORTED. */
	ABORTED(4),

	/** The MLCFAILURE. */
	LIGHT_DEACTIVATED(5),

	/** The MLCFAILURE. */
	LIGHT_IN_MAINTENANCE(6),

	/** The light with failure. */
	LIGHT_WITH_FAILURE(7),

	/** The light with max group allowed. */
	LIGHT_WITH_MAX_GROUP_ALLOWED(8),

	/** The light belong group. */
	LIGHT_BELONG_GROUP(9);

	/** The Constant LIGHT_PROTECTED_MSG. */
	private static final String LIGHT_PROTECTED_MSG = "process.page.error.protected";

	/** The Constant RNI_SYNC_FAILURE_MSG. */
	private static final String RNI_SYNCH_FAILURE_MSG = "process.page.error.sync";

	/** The Constant RNI_ASYNC_FAILURE_MSG. */
	private static final String RNI_ASYNC_FAILURE_MSG = "process.page.error.async";

	/** The Constant ABORTED_MSG. */
	private static final String ABORTED_MSG = "process.page.error.aborted";

	/** The Constant LIGHT_DESACTIVATED_MSG. */
	private static final String LIGHT_DEACTIVATED_MSG = "process.page.error.deactivated";

	/** The Constant LIGHT_IN_MAINTENANCE_MSG. */
	private static final String LIGHT_IN_MAINTENANCE_MSG = "process.page.error.maintenance";

	/** The Constant LIGHT_WITH_FAILURE_MSG. */
	private static final String LIGHT_WITH_FAILURE_MSG = "process.page.error.failure";

	/** The Constant LIGHT_WITH_MAX_GROUP_ALLOWED_MSG. */
	private static final String LIGHT_WITH_MAX_GROUP_ALLOWED_MSG = "process.page.error.max_group_allowed_msg";

	/** The Constant LIGHT_WITH_MAX_GROUP_ALLOWED_MSG. */
	private static final String LIGHT_BELONG_GROUP_MSG = "process.page.error.light_belong_group";

	/** The process reason. */
	private Integer processReason;

	/**
	 * Gets the label key.
	 * 
	 * @return the label key
	 */
	public String getLabelKey()
	{
		String labelKey = null;
		switch (ProcessStatusReasonEnum.enumForValue(getValue()))
		{
			case LIGHT_PROTECTED:
				labelKey = LIGHT_PROTECTED_MSG;
				break;
			case RNI_SYNC_FAILURE:
				labelKey = RNI_SYNCH_FAILURE_MSG;
				break;
			case RNI_ASYNC_FAILURE:
				labelKey = RNI_ASYNC_FAILURE_MSG;
				break;
			case ABORTED:
				labelKey = ABORTED_MSG;
				break;
			case LIGHT_DEACTIVATED:
				labelKey = LIGHT_DEACTIVATED_MSG;
				break;
			case LIGHT_IN_MAINTENANCE:
				labelKey = LIGHT_IN_MAINTENANCE_MSG;
				break;
			case LIGHT_WITH_FAILURE:
				labelKey = LIGHT_WITH_FAILURE_MSG;
				break;
			case LIGHT_WITH_MAX_GROUP_ALLOWED:
				labelKey = LIGHT_WITH_MAX_GROUP_ALLOWED_MSG;
				break;
			case LIGHT_BELONG_GROUP:
				labelKey = LIGHT_BELONG_GROUP_MSG;
				break;
			default:
				break;
		}
		return labelKey;
	}

	/**
	 * Instantiates a new process status reason enum.
	 * 
	 * @param processReasonEnumInd the process reason enum ind
	 */
	private ProcessStatusReasonEnum(Integer processReasonEnumInd)
	{
		processReason = processReasonEnumInd;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return processReason;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the process status reason enum
	 */
	public static ProcessStatusReasonEnum enumForValue(Integer value)
	{
		for (ProcessStatusReasonEnum e : values())
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
		ProcessStatusReasonEnum[] enums = ProcessStatusReasonEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (ProcessStatusReasonEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
