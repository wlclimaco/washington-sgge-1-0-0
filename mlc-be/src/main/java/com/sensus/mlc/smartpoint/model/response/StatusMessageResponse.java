package com.sensus.mlc.smartpoint.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.smartpoint.model.StatusMessage;

/**
 * The Class StatusMessageResponse.
 */
public class StatusMessageResponse extends Response
{

	/** The status message. */
	private StatusMessage statusMessage;

	/** The status messages . */
	private List<StatusMessage> statusMessages;

	/**
	 * Gets the status message.
	 * 
	 * @return the status message
	 */
	public StatusMessage getStatusMessage()
	{
		return this.statusMessage;
	}

	/**
	 * Sets the status message.
	 * 
	 * @param statusMessage the new status message
	 */
	public void setStatusMessage(StatusMessage statusMessage)
	{
		this.statusMessage = statusMessage;
	}

	/**
	 * Gets the status messages.
	 * 
	 * @return the status messages
	 */
	public List<StatusMessage> getStatusMessages()
	{
		return this.statusMessages;
	}

	/**
	 * Sets the status messages.
	 * 
	 * @param statusMessages the new status messages
	 */
	public void setStatusMessages(List<StatusMessage> statusMessages)
	{
		this.statusMessages = statusMessages;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "StatusMessageResponse [getStatusMessage()=" + this.getStatusMessage() + ", getStatusMessages()="
				+ this.getStatusMessages() + ", getMessageIterator()="
				+ this.getMessageIterator() + ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()="
				+ this.getMessageInfoList() + ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}

}