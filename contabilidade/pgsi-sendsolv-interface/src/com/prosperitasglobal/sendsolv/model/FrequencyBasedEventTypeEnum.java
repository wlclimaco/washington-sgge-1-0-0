package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * Enumeration specifying the values defined for frequency based event type in the SendSolv system.
 */
public enum FrequencyBasedEventTypeEnum implements IIntegerEnum, II18nEnum
{
	/** Pay day. */
	PAY_DAY(1, "com.prosperitasglobal.sendsolv.model.frequencybasedeventypeenum.payday"),

	/** Pay preparation. */
	PAY_PREPARATION(2, "com.prosperitasglobal.sendsolv.model.frequencybasedeventypeenum.paypreparation"),

	/** Funds transfer. */
	FUNDS_TRANSFER(3, "com.prosperitasglobal.sendsolv.model.frequencybasedeventypeenum.fundstransfer"),

	/** Money transfer. */
	MONEY_TRANSFER(4, "com.prosperitasglobal.sendsolv.model.frequencybasedeventypeenum.moneytransfer"),

	/** Batch approval. */
	BATCH_APPROVAL(5, "com.prosperitasglobal.sendsolv.model.frequencybasedeventypeenum.batchapproval"),

	/** Batch creation. */
	BATCH_CREATION(6, "com.prosperitasglobal.sendsolv.model.frequencybasedeventypeenum.batchcreation"),

	/** Change transfer setting. */
	CHANGE_TRANSFER_SETTING(7, "com.prosperitasglobal.sendsolv.model.frequencybasedeventypeenum.changetransfersetting");

	/** The code. */
	private Integer code;

	/** The label key. */
	private String labelKey;

	/**
	 * The Constructor.
	 *
	 * @param value The value of the frequency based event type enumeration.
	 * @param labelKeyParam The label key parameter.
	 */
	private FrequencyBasedEventTypeEnum(Integer value, String labelKeyParam)
	{
		code = value;
		labelKey = labelKeyParam;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return code;
	}

	/**
	 * Enumeration for value.
	 *
	 * @param value The value of the frequency based event type enumeration.
	 * @return The frequency based event type enumeration.
	 */
	public static FrequencyBasedEventTypeEnum enumForValue(Integer value)
	{
		for (FrequencyBasedEventTypeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values for the frequency based type event's.
	 *
	 * @return The valid values.
	 */
	public static String getValidValues()
	{
		String comma = "";
		StringBuilder enumValue = new StringBuilder();

		for (FrequencyBasedEventTypeEnum i : FrequencyBasedEventTypeEnum.class.getEnumConstants())
		{
			enumValue.append(comma).append(i.getValue());
			comma = ", ";
		}

		return enumValue.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.II18nEnum#getLabelKey()
	 */
	@Override
	public String getLabelKey()
	{
		return labelKey;
	}
}