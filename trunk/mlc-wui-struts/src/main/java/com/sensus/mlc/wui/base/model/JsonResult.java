package com.sensus.mlc.wui.base.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sensus.mlc.wui.base.util.Constants;

/**
 * Base class for all response objects for JSON requests. Provides basic result status and messages. In the future, this
 * class should probably be expanded to handle different message type (field, object...) and levels (info, error...). At
 * this point, all messages added here will be treated the same way.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
@SuppressWarnings("serial")
public class JsonResult implements Serializable
{
	/**
	 * List of internationalized messages.
	 */
	private List<String> messages = new ArrayList<String>();

	/** The int result. */
	private Integer[] intResult;

	/** The string result. */
	private String[] stringResult;

	/**
	 * Whether the operation was successful ("OK") or not ("FAIL"). Default to successful.
	 */
	private String result = Constants.JSON_OK;

	/**
	 * Set the result.
	 * 
	 * @param resultIn the result
	 */
	public void setResult(String resultIn)
	{
		result = resultIn;
	}

	/**
	 * Get the result.
	 * 
	 * @return the result
	 */
	public String getResult()
	{
		return result;
	}

	/**
	 * Get the list messages.
	 * 
	 * @return the list of messages
	 */
	public List<String> getMessages()
	{
		return messages;
	}

	/**
	 * Set the list of messages.
	 * 
	 * @param messagesIn the list of messages
	 */
	public void setMessages(List<String> messagesIn)
	{
		messages = messagesIn;
	}

	/**
	 * Set the list of messages from a string array.
	 * 
	 * @param messagesIn the list of messages as string array
	 */
	public void setMessages(String[] messagesIn)
	{
		messages = Arrays.asList(messagesIn);
	}

	/**
	 * Returns string representation of this object.
	 * 
	 * @return string representation of this object
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "JsonResult [getMessages()=" + getMessages() + ", getResult()=" + getResult()
				+ "]";
	}

	/**
	 * @return the intResult
	 */
	public Integer[] getIntResult()
	{
		return intResult;
	}

	/**
	 * @param intResult the intResult to set
	 */
	public void setIntResult(Integer[] intResult)
	{
		this.intResult = intResult;
	}

	/**
	 * @return the stringResult
	 */
	public String[] getStringResult()
	{
		return stringResult;
	}

	/**
	 * @param stringResult the stringResult to set
	 */
	public void setStringResult(String[] stringResult)
	{
		this.stringResult = stringResult;
	}

}
