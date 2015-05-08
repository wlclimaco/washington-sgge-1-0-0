package com.prosperitasglobal.cbof.model;

/**
 * The Class Email.
 */
@SuppressWarnings("serial")
public class Email extends Contact
{

	/** Attributes. */
	private String emailaddress;

	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress()
	{
		return emailaddress;
	}

	/**
	 * Sets the email address.
	 *
	 * @param emailAddress the email address
	 */
	public void setEmailAddress(String emailAddress)
	{
		emailaddress = emailAddress;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Email [getEmailAddress()=" + getEmailAddress() + ", toString()=" + super.toString() + "]";
	}

}
