package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModel;

/**
 * This class is a representation of a name for a participant in a Money Transfer Transaction.
 */
@SuppressWarnings("serial")
public class MoneyTransferParticipantPersonName extends QATModel
{
	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The mothers maiden name. */
	private String motherMaidenName;

	/**
	 * Get the first name.
	 *
	 * @return The first name.
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Set the first name.
	 *
	 * @param firstName The first name to set.
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * Get the last name.
	 *
	 * @return The last name.
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Set the last name.
	 *
	 * @param lastName The last name to set.
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * Get the mothers maiden name.
	 *
	 * @return The mother maiden name.
	 */
	public String getMotherMaidenName()
	{
		return motherMaidenName;
	}

	/**
	 * Set the mothers maiden name.
	 *
	 * @param motherMaidenName The mother maiden name to set.
	 */
	public void setMotherMaidenName(String motherMaidenName)
	{
		this.motherMaidenName = motherMaidenName;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferParticipantPersonName [getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getMotherMaidenName()=" + getMotherMaidenName() + ", toString()="
				+ super.toString() + "]";
	}
}
