package com.sensus.dm.elec.action.model;

import com.sensus.common.model.IStringEnum;

/**
 * The DemandResponseParticipationEnum.
 * 
 * @author QAT Brazil
 */
public enum DemandResponseParticipationEnum implements IStringEnum
{

	/** The UNDEFINED. */
	UNDEFINED("undefined"),

	/** The USERCHOOSEOPTOUT. */
	USERCHOOSEOPTOUT("UserChooseOptOut"),

	/** The USERCHOOSEOPTIN. */
	USERCHOOSEOPTIN("UserChooseOptIn"),

	/** The EVENTSUPERSEDED. */
	EVENTSUPERSEDED("EventSuperseded"),

	/** The EVENTPARTIALLYCOMPLETEDWITHUSEROPTOUT. */
	EVENTPARTIALLYCOMPLETEDWITHUSEROPTOUT(
			"EventPartiallyCompletedWithUserOptOut"),

	/** The EVENTPARTIALLYCOMPLETEDDUETOUSEROPTIN. */
	EVENTPARTIALLYCOMPLETEDDUETOUSEROPTIN(
			"EventPartiallyCompletedDueToUserOptIn"),

	/** The EVENTCOMPLETEDWITHNOUSERPARTICIPATION. */
	EVENTCOMPLETEDWITHNOUSERPARTICIPATION(
			"EventCompletedWithNoUserParticipation"),

	/** The REJECTEDINVALIDCANCELCOMMANDDEFAULT. */
	REJECTEDINVALIDCANCELCOMMANDDEFAULT("RejectedInvalidCancelCommandDefault"),

	/** The REJECTEDINVALIDCANCELCOMMANDINVALIDEFFECTIVETIME. */
	REJECTEDINVALIDCANCELCOMMANDINVALIDEFFECTIVETIME(
			"RejectedInvalidCancelCommandInvalidEffectiveTime"),

	/** The REJECTEDEVENTRECEIVEDAFTEREXPIRED. */
	REJECTEDEVENTRECEIVEDAFTEREXPIRED("RejectedEventReceivedAfterExpired"),

	/** The REJECTEDINVALIDCANCELCOMMANDUNDEFINEDEVENT. */
	REJECTEDINVALIDCANCELCOMMANDUNDEFINEDEVENT(
			"RejectedInvalidCancelCommandUndefinedEvent"),

	/** The LOADCONTROLEVENTCOMMANDREJECTED. */
	LOADCONTROLEVENTCOMMANDREJECTED("LoadControlEventCommandRejected"),

	/** The FULLPARTICIPATION. */
	FULL_PARTICIPATION("FullParticipation"),

	/** The opt out. */
	OPT_OUT("OptOut"),

	/** The partial participation. */
	PARTIAL_PARTICIPATION("PartialParticipation"),

	/** The partial participation and opt out. */
	PARTIAL_PARTICIPATION_AND_OPT_OUT("PartialParticipationAndOptOut");

	/** The status id. */
	private String statusId;

	/**
	 * Instantiates a new process item history status enum.
	 * 
	 * @param paramType
	 *            the param type
	 */
	private DemandResponseParticipationEnum(String paramType)
	{
		statusId = paramType;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public String getValue()
	{
		return statusId;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value
	 *            the value
	 * 
	 * @return TypeEnum
	 */
	public static DemandResponseParticipationEnum enumForValue(String value)
	{
		for (DemandResponseParticipationEnum e : values())
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
		DemandResponseParticipationEnum[] enums = DemandResponseParticipationEnum.class
				.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (DemandResponseParticipationEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
