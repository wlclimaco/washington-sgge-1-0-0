package com.prosperitasglobal.sendsolv.model;

/**
 * Represents a member participant id within the SendSolv system.
 */
@SuppressWarnings("serial")
public class MemberParticipantId extends ParticipantId
{
	/**
	 * Default constructor for a member participant id.
	 */
	public MemberParticipantId()
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
	public MemberParticipantId(Character upperId, Character lowerId, Integer sequence,
			Integer maximumSequence)
	{
		super(upperId, lowerId, sequence, maximumSequence);
	}

	/**
	 * Get the participant id type. This method will always return {@link ParticipantIdTypeEnum#MEMBER}. This guarantees
	 * us that the correct participant id type is assigned to this class.
	 *
	 * @return The participant id type for a member.
	 */
	@Override
	public ParticipantIdTypeEnum getParticipantIdType()
	{
		return ParticipantIdTypeEnum.MEMBER;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MemberParticipantId [getParticipantIdType()=" + getParticipantIdType() + ", toString()="
				+ super.toString() + "]";
	}
}
