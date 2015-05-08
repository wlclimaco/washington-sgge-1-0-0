package com.prosperitasglobal.sendsolv.model;

/**
 * Represents a transfer setting participant id within the SendSolv system.
 */
@SuppressWarnings("serial")
public class TransferSettingParticipantId extends ParticipantId
{
	/**
	 * Default constructor for an transfer setting participant id.
	 */
	public TransferSettingParticipantId()
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
	public TransferSettingParticipantId(Character upperId, Character lowerId, Integer sequence, Integer maximumSequence)
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
		return ParticipantIdTypeEnum.TRANSFER_SETTING;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TransferSettingParticipantId [getParticipantIdType()=" + getParticipantIdType() + ", toString()="
				+ super.toString() + "]";
	}

}
