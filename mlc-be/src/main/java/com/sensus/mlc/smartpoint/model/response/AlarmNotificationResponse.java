package com.sensus.mlc.smartpoint.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.smartpoint.model.StatusMessage;

/**
 * The Class LightResponse.
 * 
 * @author - Alex Barros - QAT Omaha
 */
public class AlarmNotificationResponse extends Response
{

	/** The status messages. */
	private List<StatusMessage> statusMessages;

	/**
	 * Sets the status messages.
	 * 
	 * @param statusMessages the new status messages
	 */
	public void setStatusMessages(List<StatusMessage> statusMessages)
	{
		this.statusMessages = statusMessages;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AlarmNotificationResponse [getStatusMessages()=" + this.getStatusMessages() + ", getMessageIterator()="
				+ this.getMessageIterator() + ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()="
				+ this.getMessageInfoList() + ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}