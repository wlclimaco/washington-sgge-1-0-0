package com.prosperitasglobal.sendsolv.model;

/**
 * The Class Recipient carries information about a {@link Person} that uses a PGSi service to receive money from a
 * {@link Member}.
 */
@SuppressWarnings("serial")
public class Recipient extends TransferParticipant
{
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Recipient [toString()=" + super.toString() + "]";
	}
}