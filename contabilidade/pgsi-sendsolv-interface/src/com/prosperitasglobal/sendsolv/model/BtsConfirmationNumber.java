package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModel;

/**
 * Represents a BTS Automated Clearing House confirmation number within the SendSolv system.
 */
@SuppressWarnings("serial")
public class BtsConfirmationNumber extends QATModel
{
	/** The prefix number. Assigned by BTS. */
	private Integer prefixNumber;

	/** The sequence. */
	private Integer sequence;

	/** The check digit. */
	private Integer checkDigit;

	/**
	 * Default constructor for BtsConfirmationNumber.
	 */
	public BtsConfirmationNumber()
	{
		super();
	}

	/**
	 * Constructor for BtsConfirmationNumber.
	 *
	 * @param prefixNumber The BTS assigned prefix number.
	 * @param sequence The sequence number.
	 */
	public BtsConfirmationNumber(Integer prefixNumber, Integer sequence)
	{
		this();
		this.prefixNumber = prefixNumber;
		this.sequence = sequence;
	}

	/**
	 * Get the check digit.
	 *
	 * @return The check digit.
	 */
	public Integer getCheckDigit()
	{
		return checkDigit;
	}

	/**
	 * Set the check digit.
	 *
	 * @param checkDigit The check digit.
	 */
	public void setCheckDigit(Integer checkDigit)
	{
		this.checkDigit = checkDigit;
	}

	/**
	 * Get the BTS assigned number.
	 *
	 * @return The number.
	 */
	public Integer getPrefixNumber()
	{
		return prefixNumber;
	}

	/**
	 * Set the BTS assigned number.
	 *
	 * @param prefixNumber The number.
	 */
	public void setPrefixNumber(Integer prefixNumber)
	{
		this.prefixNumber = prefixNumber;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "BtsConfirmationNumber [getCheckDigit()=" + getCheckDigit() + ", getPrefixNumber()=" + getPrefixNumber()
				+ ", getSequence()=" + getSequence() + ", toString()=" + super.toString() + "]";
	}
}
