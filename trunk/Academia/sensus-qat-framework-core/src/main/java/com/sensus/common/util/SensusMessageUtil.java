package com.sensus.common.util;

import java.util.List;
import java.util.Locale;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.MessageInfo;

/**
 * To be used for retrieving a message. This utility enable us to support internationalization.
 */
public final class SensusMessageUtil
{
	/**
	 * Gets the message.
	 * 
	 * @param messageId The message identifier
	 * 
	 * @return the message or NULL if the message code was not found.
	 */
	public static String getMessage(String messageId)
	{
		return SensusMessageUtil.getMessage(messageId, null);
	}

	/**
	 * Gets the message. <br>
	 * Note, if the message identifier is not found in any properties file the NoSuchMessageException will be thrown.
	 * 
	 * @param messageId The message identifier
	 * @param args Any arguments required by the message.
	 * 
	 * @return the message or NULL if the message code was not found.
	 */
	public static String getMessage(String messageId, Object[] args)
	{
		return SensusMessageUtil.getMessage(messageId, args, null);
	}

	/**
	 * Gets the message using the default locale.
	 * 
	 * @param messageId The message identifier
	 * @param args Any arguments required for the formatting of the message.
	 * @param defaultMsgText If the messageId is not found then this message will be used instead.
	 * 
	 * @return the message or NULL if the message code was not found.
	 */
	public static String getMessage(String messageId, Object[] args, String defaultMsgText)
	{
		return SensusMessageUtil.getMessage(messageId, args, defaultMsgText, Locale.getDefault());
	}

	/**
	 * Gets the message using the specified locale.
	 * 
	 * @param messageId the message id
	 * @param args the args
	 * @param defaultMsgText the default msg text
	 * @param locale the locale
	 * 
	 * @return the message or NULL if the message code was not found.
	 */
	public static String getMessage(String messageId, Object[] args, String defaultMsgText, Locale locale)
	{
		return SensusAppContext.getApplicationContext().getMessage(messageId, args, defaultMsgText, locale);
	}

	/**
	 * Determine if the collection ONLY contains informational type messages.
	 * Any other type of message will result in this method returning false.
	 * 
	 * @param messages The message collection.
	 * @return true, if the collection is empty or ONLY contains informational messages.
	 */
	public static boolean hasOnlyInfo(List<MessageInfo> messages)
	{
		for (MessageInfo msg : messages)
		{
			if (MessageSeverity.Info != msg.getSeverity())
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks for error messages using the {{@link #hasErrors(List, boolean, boolean)} method. Defaulting the
	 * includeWarning and includeFatal parameters to false and true.
	 * 
	 * @param messageList the message list
	 * @return true, if successful
	 */
	public static boolean hasErrors(List<MessageInfo> messageList)
	{
		return hasErrors(messageList, false, true);
	}

	/**
	 * Checks for error messages using the {{@link #hasErrors(List, boolean, boolean)} method. Defaulting the
	 * includeWarning parameter to false.
	 * 
	 * @param messageList the message list
	 * @param includeFatal the include fatal
	 * @return true, if successful
	 */
	public static boolean hasErrors(List<MessageInfo> messageList, boolean includeFatal)
	{
		return hasErrors(messageList, false, includeFatal);
	}

	/**
	 * Checks for error messages including warning and fatal type messages in indicated by the parameters.
	 * 
	 * @param messageList The message list
	 * @param includeWarning Specify to include warning type messages.
	 * @param includeFatal Specify to include fatal type messages.
	 * @return true, if any of these message where found.
	 */
	public static boolean hasErrors(List<MessageInfo> messageList, boolean includeWarning, boolean includeFatal)
	{
		if ((messageList == null) || (messageList.size() == 0))
		{
			return false;
		}

		for (MessageInfo messageInfo : messageList)
		{
			if (messageInfo.getSeverity().equals(Message.MessageSeverity.Error))
			{
				return true;
			}
			else if (includeWarning)
			{
				if (messageInfo.getSeverity().equals(Message.MessageSeverity.Warning))
				{
					return true;
				}
			}
			else if (includeFatal)
			{
				if (messageInfo.getSeverity().equals(Message.MessageSeverity.Fatal))
				{
					return true;
				}
			}
		}

		return false;
	}
}
