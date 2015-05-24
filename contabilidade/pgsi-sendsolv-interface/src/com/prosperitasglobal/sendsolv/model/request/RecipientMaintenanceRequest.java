package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Recipient;
import com.qat.framework.model.request.Request;

/**
 * The Class CodeValueRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 2:15:09 PM
 */
public class RecipientMaintenanceRequest extends Request
{

	/** Attributes. */
	private Recipient recipient;

	/**
	 * The Constructor.
	 */
	public RecipientMaintenanceRequest()
	{

	}

	/**
	 * Gets the recipient.
	 *
	 * @return the recipient
	 */
	public Recipient getRecipient()
	{
		return recipient;
	}

	/**
	 * Sets the recipient.
	 *
	 * @param recipient the recipient
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
		return "RecipientMaintenanceRequest [getRecipient()=" + getRecipient() + ", getUserContext()="
				+ getUserContext() + "]";
	}

}