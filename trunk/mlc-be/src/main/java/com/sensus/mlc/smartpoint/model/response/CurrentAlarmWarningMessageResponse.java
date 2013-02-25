package com.sensus.mlc.smartpoint.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;

/**
 * The Class CurrentAlarmWarningMessageResponse.
 */
public class CurrentAlarmWarningMessageResponse extends Response
{

	/** The current alarm warning messages. */
	private List<CurrentAlarmWarningMessage> currentAlarmWarningMessages;

	/**
	 * Gets the current alarm warning messages.
	 * 
	 * @return the current alarm warning messages
	 */
	public List<CurrentAlarmWarningMessage> getCurrentAlarmWarningMessages()
	{
		return this.currentAlarmWarningMessages;
	}

	/**
	 * Sets the current alarm warning messages.
	 * 
	 * @param currentAlarmWarningMessages the new current alarm warning messages
	 */
	public void setCurrentAlarmWarningMessages(List<CurrentAlarmWarningMessage> currentAlarmWarningMessages)
	{
		this.currentAlarmWarningMessages = currentAlarmWarningMessages;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CurrentAlarmWarningMessageResponse [getCurrentAlarmWarningMessages()="
				+ this.getCurrentAlarmWarningMessages() + "]";
	}

}