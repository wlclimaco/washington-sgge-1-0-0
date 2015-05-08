package com.prosperitasglobal.sendsolv.model;

/**
 * Represents a location participant id within the SendSolv system.
 */
@SuppressWarnings("serial")
public class LocationParticipantId extends ParticipantId
{
	/**
	 * Default constructor for a location participant id.
	 */
	public LocationParticipantId()
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
	public LocationParticipantId(Character upperId, Character lowerId, Integer sequence,
			Integer maximumSequence)
	{
		super(upperId, lowerId, sequence, maximumSequence);
	}

	/**
	 * Get the participant id type. This method will always return {@link ParticipantIdTypeEnum#LOCATION}. This
	 * guarantees us that the correct participant id type is assigned to this class.
	 *
	 * @return The participant id type for a location.
	 */
	@Override
	public ParticipantIdTypeEnum getParticipantIdType()
	{
		return ParticipantIdTypeEnum.LOCATION;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationParticipantId [getParticipantIdType()=" + getParticipantIdType() + ", toString()="
				+ super.toString() + "]";
	}
}
