package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModel;

/**
 * This class is a representation of a participant in a Money Transfer Transaction.
 */
@SuppressWarnings("serial")
public class MoneyTransferParticipant extends QATModel
{
	/** The name of the participant. */
	private MoneyTransferParticipantPersonName name;

	/** The address of the participant. */
	private MoneyTransferParticipantAddress address;

	/** The account to use for the money transfer. */
	private Account account;

	/** The phone number of the participant. */
	private String phoneNumber;

	/**
	 * Get the account to use for the transfer.
	 *
	 * @return The account.
	 */
	public Account getAccount()
	{
		return account;
	}

	/**
	 * Set the account to use for the transfer.
	 *
	 * @param account The account to set.
	 */
	public void setAccount(Account account)
	{
		this.account = account;
	}

	/**
	 * Get the name of the participant.
	 *
	 * @return The name.
	 */
	public MoneyTransferParticipantPersonName getName()
	{
		return name;
	}

	/**
	 * Set the name of the participant.
	 *
	 * @param name The name to set.
	 */
	public void setName(MoneyTransferParticipantPersonName name)
	{
		this.name = name;
	}

	/**
	 * Get the address of the participant.
	 *
	 * @return The address
	 */
	public MoneyTransferParticipantAddress getAddress()
	{
		return address;
	}

	/**
	 * Set the address of the participant.
	 *
	 * @param address The address to set.
	 */
	public void setAddress(MoneyTransferParticipantAddress address)
	{
		this.address = address;
	}

	/**
	 * Get the phone number of the participant.
	 *
	 * @return The phone number.
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	/**
	 * Set the phone number of the participant.
	 *
	 * @param phoneNumber The phone number to set.
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferParticipant [getAccount()=" + getAccount() + ", getName()=" + getName()
				+ ", getAddress()=" + getAddress() + ", getPhoneNumber()=" + getPhoneNumber() + ", toString()="
				+ super.toString() + "]";
	}
}
