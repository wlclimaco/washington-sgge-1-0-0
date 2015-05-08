package com.prosperitasglobal.sendsolv.model;

/**
 * Represents an organization participant id within the SendSolv system.
 */
@SuppressWarnings("serial")
public class OrganizationParticipantId extends ParticipantId
{
	/**
	 * Default constructor for an organization participant id.
	 */
	public OrganizationParticipantId()
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
	public OrganizationParticipantId(Character upperId, Character lowerId, Integer sequence, Integer maximumSequence)
	{
		super(upperId, lowerId, sequence, maximumSequence);
	}

	/**
	 * Get the participant id type. This method will always return {@link ParticipantIdTypeEnum#ORGANIZATION}. This
	 * guarantees us that the correct participant id type is assigned to this class.
	 *
	 * @return The participant id type for an organization.
	 */
	@Override
	public ParticipantIdTypeEnum getParticipantIdType()
	{
		return ParticipantIdTypeEnum.ORGANIZATION;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "OrganizationParticipantId [getParticipantIdType()=" + getParticipantIdType() + ", toString()="
				+ super.toString() + "]";
	}
}
