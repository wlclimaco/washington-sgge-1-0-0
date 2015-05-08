package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * The Enumeration ParticipantIdTypeEnum lists the different types of identifiers in the SendSolv system.
 */
public enum ParticipantIdTypeEnum implements IIntegerEnum, II18nEnum
{
	/** The money transfer batch identifier. */
	MONEY_TRANSFER_BATCH(1, "B", "com.prosperitasglobal.sendsolv.model.participantidtypeenum.moneytransferbatch"),

	/** The member identifier. */
	MEMBER(2, "M", "com.prosperitasglobal.sendsolv.model.participantidtypeenum.member"),

	/** The recipient identifier. */
	RECIPIENT(3, "R", "com.prosperitasglobal.sendsolv.model.participantidtypeenum.recipient"),

	/** The organization identifier. */
	ORGANIZATION(4, "O", "com.prosperitasglobal.sendsolv.model.participantidtypeenum.organization"),

	/** The location identifier. */
	LOCATION(5, "L", "com.prosperitasglobal.sendsolv.model.participantidtypeenum.location"),

	/** The recipient identifier. */
	CONTACT(6, "C", "com.prosperitasglobal.sendsolv.model.participantidtypeenum.contact"),

	/** The transfer setting identifier. */
	TRANSFER_SETTING(7, "T", "com.prosperitasglobal.sendsolv.model.participantidtypeenum.transfersetting"),

	/** The money transfer identifier. */
	MONEY_TRANSFER(8, "S", "com.prosperitasglobal.sendsolv.model.participantidtypeenum.moneytransfer"),

	/** The suspicious activity identifier. */
	SUSPICIOUS_ACTIVITY(9, "U", "com.prosperitasglobal.sendsolv.model.participantidtypeenum.suspiciousactivity");

	/** The code. */
	private Integer code;

	/** The label key. */
	private String labelKey;

	/** The characters identification code. */
	private String identifierCharacters;

	/**
	 * The Constructor.
	 *
	 * @param value the value
	 * @param labelKeyParam the label key parameter.
	 * @param identifierChars The characters identifier for the identifier.
	 */
	private ParticipantIdTypeEnum(Integer value, String identifierChars, String labelKeyParam)
	{
		code = value;
		labelKey = labelKeyParam;
		identifierCharacters = identifierChars;
	}

	/**
	 * Gets the code value of the enumeration.
	 *
	 * @return The code.
	 */
	@Override
	public Integer getValue()
	{
		return code;
	}

	/**
	 * Enumeration for value.
	 *
	 * @param value The value.
	 * @return The identifier type enumeration.
	 */
	public static ParticipantIdTypeEnum enumForValue(Integer value)
	{
		for (ParticipantIdTypeEnum e : values())
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
	 * @return The valid values.
	 */
	public static String getValidValues()
	{
		String comma = "";
		StringBuilder enumValue = new StringBuilder();

		for (ParticipantIdTypeEnum i : ParticipantIdTypeEnum.class.getEnumConstants())
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

	/**
	 * Gets the identifier characters of the enumeration.
	 *
	 * @return The characters.
	 */
	public String getIdentifierCharacters()
	{
		return identifierCharacters;
	}
}