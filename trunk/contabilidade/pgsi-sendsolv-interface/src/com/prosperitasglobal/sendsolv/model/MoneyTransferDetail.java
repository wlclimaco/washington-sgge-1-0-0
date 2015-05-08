package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModel;

/**
 * This class is a representation of a Money Transfers referenced objects.
 */
@SuppressWarnings("serial")
public class MoneyTransferDetail extends QATModel
{
	/** The money transfers member. */
	private Member member;

	/** The money transfers recipient. */
	private Recipient recipient;

	/**
	 * Default constructor.
	 */
	public MoneyTransferDetail()
	{
		super();
	}

	/**
	 * Get the {@link Member} of the money transfer.
	 *
	 * @return The {@link Member}.
	 */
	public Member getMember()
	{
		return member;
	}

	/**
	 * Set the {@link Member} of the money transfer.
	 *
	 * @param member The {@link Member} to set.
	 */
	public void setMember(Member member)
	{
		this.member = member;
	}

	/**
	 * Get the {@link Recipient} of the money transfer.
	 *
	 * @return The {@link Recipient}.
	 */
	public Recipient getRecipient()
	{
		return recipient;
	}

	/**
	 * Set the {@link Recipient} of the money transfer.
	 *
	 * @param recipient The {@link Recipient} to set.
	 */
	public void setRecipient(Recipient recipient)
	{
		this.recipient = recipient;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferDetail [getMember()=" + getMember() + ", getRecipient()=" + getRecipient()
				+ ", toString()=" + super.toString() + "]";
	}
}
