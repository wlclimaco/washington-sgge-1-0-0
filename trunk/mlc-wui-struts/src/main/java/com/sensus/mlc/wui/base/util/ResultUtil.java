package com.sensus.mlc.wui.base.util;

import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.wui.base.model.JsonResult;

/**
 * Utility for copying messages from the BCF model objects to Json Result objects.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public final class ResultUtil
{
	/**
	 * Private constructor to prevent instantiation.
	 */
	private ResultUtil()
	{
	}

	/**
	 * Transfer messages from a BCF response object to a Json Result object and set the Json result to "FAIL" if the BCF
	 * response object indicates an operation failure ("OK" is the default for Json results).
	 * 
	 * Developer Note: The current implementation only transfers the message strings.
	 * 
	 * @param result the Json result object to receive the messages
	 * @param response the BCF response object that is the message source
	 */
	public static void setMessages(JsonResult result, Response response)
	{
		if (!response.isOperationSuccess())
		{
			result.setResult(Constants.JSON_FAIL);
		}
		for (Message msg : response.getMessageList())
		{
			result.getMessages().add(msg.getText());
		}
	}

	/**
	 * Transfer messages from a BCF response object to a List object.
	 * 
	 * @param message the list of message object to receive the messages
	 * @param response the BCF response object that is the message source
	 */
	public static void setMessages(List<String> message, Response response)
	{
		for (Message msg : response.getMessageList())
		{
			message.add(msg.getText());
		}
	}

	/**
	 * Return the size of the list or 0 if list is null.
	 * 
	 * @param list the list to obtain the size of
	 * @return the size of the list
	 */
	@SuppressWarnings("unchecked")
	public static Integer getListSize(List list)
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
		if (!ValidationUtil.isNull(number))
		{
			return number;
		}
		return deflt;
	}

}
