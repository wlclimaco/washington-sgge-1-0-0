package com.sensus.dm.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Message;
import com.sensus.common.model.response.Response;
import com.sensus.common.validation.ValidationUtil;

/**
 * Utility for copying messages from the BCF model objects to Json Result objects.
 *
 * @author Anke Doerfel-Parker
 */
public final class ResultUtil
{
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(ResultUtil.class);

	/**
	 * Private constructor to prevent instantiation.
	 */
	private ResultUtil()
	{
	}

	/**
	 * Transfer messages from a BCF response object to a List object.
	 *
	 * @param message the list of message object to receive the messages
	 * @param response the BCF response object that is the message source
	 * @return the boolean
	 */
	public static Boolean setMessages(List<String> message, Response response)
	{
		if (!ValidationUtil.isNullOrEmpty(response.getMessageList()))
		{
			for (Message msg : response.getMessageList())
			{
				message.add(msg.getText());

				if (LOG.isErrorEnabled())
				{
					LOG.error(msg.getText());
				}
			}
		}
		return response.isOperationSuccess();
	}

	/**
	 * Sets the messages.
	 *
	 * @param response the response
	 * @param messageList the message list
	 * @return the boolean
	 */
	public static Boolean setMessages(Response response, List<Message> messageList)
	{
		messageList = new ArrayList<Message>();
		if (!ValidationUtil.isNullOrEmpty(response.getMessageList()))
		{
			for (Message msg : response.getMessageList())
			{
				messageList.add(msg);

				if (LOG.isErrorEnabled())
				{
					LOG.error(msg.getText());
				}
			}
		}
		return response.isOperationSuccess();
	}

	/**
	 * Return the size of the list or 0 if list is null.
	 *
	 * @param list the list to obtain the size of
	 * @return the size of the list
	 */
	public static Integer getListSize(List<?> list)
	{
		if (list == null)
		{
			return 0;
		}
		return list.size();
	}

	/**
	 * Returns the provided number, or the default if the provided number is null.
	 *
	 * @param number the number to return if not null
	 * @param deflt the value to return if number is null
	 * @return the number
	 */
	public static Integer getNumber(Integer number, Integer deflt)
	{
		if (number != null)
		{
			return number;
		}
		return deflt;
	}

}
