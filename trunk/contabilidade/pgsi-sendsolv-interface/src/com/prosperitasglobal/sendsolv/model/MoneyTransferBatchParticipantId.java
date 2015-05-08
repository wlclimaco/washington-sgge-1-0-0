package com.prosperitasglobal.sendsolv.model;

/**
 * Represents a money transfer batch participant id within the SendSolv system.
 */
@SuppressWarnings("serial")
public class MoneyTransferBatchParticipantId extends ParticipantId
{
	/**
	 * Default constructor for a money transfer batch participant id.
	 */
	public MoneyTransferBatchParticipantId()
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
	public MoneyTransferBatchParticipantId(Character upperId, Character lowerId, Integer sequence,
			Integer maximumSequence)
	{
		super(upperId, lowerId, sequence, maximumSequence);
	}

	/**
	 * Get the participant id type. This method will always return {@link ParticipantIdTypeEnum#MONEY_TRANSFER_BATCH}.
	 * This guarantees us that the correct participant id type is assigned to this class.
	 *
	 * @return The participant id type for a money transfer batch.
	 */
	@Override
	public ParticipantIdTypeEnum getParticipantIdType()
	{
		return ParticipantIdTypeEnum.MONEY_TRANSFER_BATCH;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferBatchParticipantId [getParticipantIdType()=" + getParticipantIdType() + ", toString()="
				+ super.toString() + "]";
	}
}
