package com.prosperitasglobal.sendsolv.model;

/**
 * Represents a recipient participant id within the SendSolv system.
 */
@SuppressWarnings("serial")
public class RecipientParticipantId extends ParticipantId
{
	/**
	 * Default constructor for a recipient participant id.
	 */
	public RecipientParticipantId()
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
	public RecipientParticipantId(Character upperId, Character lowerId, Integer sequence, Integer maximumSequence)
	{
		super(upperId, lowerId, sequence, maximumSequence);
	}

	/**
	 * Get the participant id type. This method will always return {@link ParticipantIdTypeEnum#RECIPIENT}. This
	 * guarantees us that the correct participant id type is assigned to this class.
	 *
	 * @return The participant id type for a recipient.
	 */
	@Override
	public ParticipantIdTypeEnum getParticipantIdType()
	{
		return ParticipantIdTypeEnum.RECIPIENT;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RecipientParticipantId [getParticipantIdType()=" + getParticipantIdType() + ", toString()="
				+ super.toString() + "]";
	}
}
