package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModel;

/**
 * Represents a participant id within the SendSolv system.
 */
@SuppressWarnings("serial")
public abstract class ParticipantId extends QATModel
{
	/** The upper id. */
	private Character upperId;
	/** The lower id. */
	private Character lowerId;
	/** The sequence. */
	private Integer sequence;
	/** The maximum sequence. */
	private Integer maximumSequence;

	/**
	 * Default constructor for ParticipantId.
	 */
	public ParticipantId()
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
	public ParticipantId(Character upperId, Character lowerId, Integer sequence, Integer maximumSequence)
	{
		this();
		setUpperId(upperId);
		setLowerId(lowerId);
		setSequence(sequence);
		setMaximumSequence(maximumSequence);
	}

	/**
	 * Get the lower id.
	 *
	 * @return The lower id.
	 */
	public Character getLowerId()
	{
		return lowerId;
	}

	/**
	 * Set the lower id.
	 *
	 * @param lowerId The lower id.
	 */
	public void setLowerId(Character lowerId)
	{
		this.lowerId = lowerId;
	}

	/**
	 * Get the maximum sequence.
	 *
	 * @return The maximum sequence.
	 */
	public Integer getMaximumSequence()
	{
		return maximumSequence;
	}

	/**
	 * Set the maximum sequence.
	 *
	 * @param maximumSequence The maximum sequence.
	 */
	public void setMaximumSequence(Integer maximumSequence)
	{
		this.maximumSequence = maximumSequence;
	}

	/**
	 * Get the participant id type. This method must be implemented by any extending class. Also, it is a ready only
	 * value, thus no setter methods.
	 *
	 * @return The participant id type.
	 */
	public abstract ParticipantIdTypeEnum getParticipantIdType();

	/**
	 * Gets the participant id type value of the enumeration.
	 *
	 * @return The participant id type value of the enumeration.
	 */
	public Integer getParticipantIdTypeValue()
	{
		if (getParticipantIdType() == null)
		{
			return null;
		}

		return getParticipantIdType().getValue();
	}

	/**
	 * Get the sequence.
	 *
	 * @return The sequence.
	 */
	public Integer getSequence()
	{
		return sequence;
	}

	/**
	 * Set the sequence.
	 *
	 * @param sequence The sequence.
	 */
	public void setSequence(Integer sequence)
	{
		this.sequence = sequence;
	}

	/**
	 * Get the upper id.
	 *
	 * @return The upper id.
	 */
	public Character getUpperId()
	{
		return upperId;
	}

	/**
	 * Set the upper id.
	 *
	 * @param upperId The upper id.
	 */
	public void setUpperId(Character upperId)
	{
		this.upperId = upperId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ParticipantId [getLowerId()=" + getLowerId() + ", getMaximumSequence()=" + getMaximumSequence()
				+ ", getParticipantIdType()=" + getParticipantIdType() + ", getParticipantIdTypeValue()="
				+ getParticipantIdTypeValue() + ", getSequence()=" + getSequence() + ", getUpperId()=" + getUpperId()
				+ ", toString()=" + super.toString() + "]";
	}
}
