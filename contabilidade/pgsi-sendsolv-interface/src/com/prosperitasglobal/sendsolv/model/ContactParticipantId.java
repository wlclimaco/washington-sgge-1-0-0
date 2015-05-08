package com.prosperitasglobal.sendsolv.model;

/**
 * Represents a contact participant id within the SendSolv system.
 */
@SuppressWarnings("serial")
public class ContactParticipantId extends ParticipantId
{
	/**
	 * Default constructor for a contact participant id.
	 */
	public ContactParticipantId()
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
	public ContactParticipantId(Character upperId, Character lowerId, Integer sequence,
			Integer maximumSequence)
	{
		super(upperId, lowerId, sequence, maximumSequence);
	}

	/**
	 * Get the participant id type. This method will always return {@link ParticipantIdTypeEnum#CONTACT}. This
	 * guarantees us that the correct participant id type is assigned to this class.
	 *
	 * @return The participant id type for a contact.
	 */
	@Override
	public ParticipantIdTypeEnum getParticipantIdType()
	{
		return ParticipantIdTypeEnum.CONTACT;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ContactParticipantId [getParticipantIdType()=" + getParticipantIdType() + ", toString()="
				+ super.toString() + "]";
	}
}
