package com.prosperitasglobal.sendsolv.model.response;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.FrequencyBasedEvent;
import com.qat.framework.model.response.Response;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class FrequencyBasedEventResponse.
 */
public class FrequencyBasedEventResponse extends Response
{
	/** The list of frequency based events. */
	private List<FrequencyBasedEvent> frequencyBasedEventList;

	/**
	 * Get the list of frequency based events.
	 *
	 * @return The list of frequency based events.
	 */
	public List<FrequencyBasedEvent> getFrequencyBasedEventList()
	{
		if (ValidationUtil.isNull(frequencyBasedEventList))
		{
			setFrequencyBasedEventList(new ArrayList<FrequencyBasedEvent>());
		}

		return frequencyBasedEventList;
	}

	/**
	 * Set the list of frequency based events.
	 *
	 * @param frequencyBasedEventList The list of frequency based events to set.
	 */
	public void setFrequencyBasedEventList(List<FrequencyBasedEvent> frequencyBasedEventList)
	{
		this.frequencyBasedEventList = frequencyBasedEventList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FrequencyBasedEventResponse [getFrequencyBasedEventList()=" + getFrequencyBasedEventList()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}
}
