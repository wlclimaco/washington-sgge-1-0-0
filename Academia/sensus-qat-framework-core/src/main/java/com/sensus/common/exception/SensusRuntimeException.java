package com.sensus.common.exception;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.MessageInfo;

/**
 * The base class for all QAT framework exceptions.<br/>
 * This class contains a collection of {@link com.sensus.common.model.MessageInfo} objects which should be populated
 * based on the exception condition.<br/>
 * See the sub-classes for more process specific exceptions classes.
 */
public class SensusRuntimeException extends RuntimeException
{
	/** The message info list. */
	private List<MessageInfo> messageInfoList;

	/**
	 * Instantiates a new QAT runtime exception.
	 */
	public SensusRuntimeException()
	{
	}

	/**
	 * Instantiates a new QAT runtime exception with a message.
	 * 
	 * @param message the message
	 */
	public SensusRuntimeException(String message)
	{
		super(message);
	}

	/**
	 * Instantiates a new QAT runtime exception with a message and wraps another Throwable exception/cause.
	 * 
	 * @param message the message
	 * @param cause the cause
	 */
	public SensusRuntimeException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Instantiates a new QAT runtime exception wrapping another Throwable exception/cause.
	 * 
	 * @param cause the cause
	 */
	public SensusRuntimeException(Throwable cause)
	{
		super(cause);
	}

	// [end] Constructors

	// [start] Public methods

	/**
	 * Gets the message info list.<br/>
	 * note, this should never return null, however this method will return an empty list indicating there are no
	 * messages.
	 * 
	 * @return the message info list
	 */
	public List<MessageInfo> getMessageInfoList()
	{
		if (this.messageInfoList == null)
		{
			this.messageInfoList = new ArrayList<MessageInfo>();
		}

		return this.messageInfoList;
	}

	/**
	 * Sets the message info list.<br/>
	 * Use this method to replace the entire collection of MessageInfo objects. If you only need to add to the
	 * collection then use the the getMessageInfoList method and add to the returned collection.
	 * 
	 * @param messageInfoList the new message info list
	 */
	public void setMessageInfoList(List<MessageInfo> messageInfoList)
	{
		this.messageInfoList = messageInfoList;
	}
}
