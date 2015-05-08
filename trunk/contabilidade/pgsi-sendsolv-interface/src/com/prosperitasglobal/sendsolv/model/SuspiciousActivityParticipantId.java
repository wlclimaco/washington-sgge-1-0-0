package com.prosperitasglobal.sendsolv.model;

/**
 * Represents a suspicious activity participant id within the SendSolv system.
 */
@SuppressWarnings("serial")
public class SuspiciousActivityParticipantId extends ParticipantId
{
	/**
	 * Default constructor for a suspicious activity participant id.
	 */
	public SuspiciousActivityParticipantId()
	{
		super();
	}

	/**
	 * Constructor for ParticipantId.
	 *
	 * @param upperId The upper id.
	 * @param lowerId The lower id.
	 * @param sequence The sequence.
	 * @param maximumSequence The maximum sequence.
	 */
	public SuspiciousActivityParticipantId(Character upperId, Character lowerId, Integer sequence,
			Integer maximumSequence)
	{
		super(upperId, lowerId, sequence, maximumSequence);
	}

	/**
	 * Get the participant id type. This method will always return {@link ParticipantIdTypeEnum#SUSPICIOUS_ACTIVITY}.
	 * This guarantees us that the correct participant id type is assigned to this class.
	 *
	 * @return The participant id type for a suspicious activity.
	 */
	@Override
	public ParticipantIdTypeEnum getParticipantIdType()
	{
		return ParticipantIdTypeEnum.SUSPICIOUS_ACTIVITY;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SuspiciousActivityParticipantId [getParticipantIdType()=" + getParticipantIdType() + ", toString()="
				+ super.toString() + "]";
	}
}
