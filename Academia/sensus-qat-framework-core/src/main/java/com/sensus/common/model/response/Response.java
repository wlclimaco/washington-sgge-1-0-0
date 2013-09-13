package com.sensus.common.model.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.sensus.common.model.Message;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.request.Request;

/**
 * This Response object is used to encapsulate the results of an operation/method typically invoked upon a BAS/BAI
 * provider. <br/>
 * It is used in the context of an operation provider responding to a consumer invocation of an operation or method..
 * The consumer passes in an {@link Request} object and the operation provider returns a {@link Response} object. This
 * Response type contains base properties that are common to every response. Like:
 * <ol>
 * <li>Messages related to the execution of the operation, error, info etc.
 * <li>Operation success indicator
 * <li>Start row from the results set
 * <li>Total rows available from the results set
 * <li>Other
 * </ol>
 * Operation providers may need to extend this class and add other properties related to the operation.<br/>
 * Review the rest of the properties to get a full understand of how this class works. See also the
 * {@link InquiryResponse}, {@link MaintenanceResponse} for sub-classes of this
 * type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Response
{

	/** The messages. Not a generic type since generics are not WS-I complaint. */
	private List<Message> messageList = new ArrayList<Message>();

	/** The operation success. */
	private boolean operationSuccess = true;

	/**
	 * Gets the message iterator.
	 *
	 * @return the messages
	 */
	public Iterator<Message> getMessageIterator()
	{
		return messageList.iterator();
	}

	/**
	 * Gets the message list in the form of a generic List type.
	 *
	 * @return the message list
	 */
	public List<Message> getMessageList()
	{
		return messageList;
	}

	/**
	 * Returns a collection of the internal MessageInfo instance related to each Message object within this Response.
	 *
	 * @return A collection of MessageInfo instances.
	 */
	public List<MessageInfo> getMessageInfoList()
	{
		List<MessageInfo> list = new ArrayList<MessageInfo>();

		for (Message msg : messageList)
		{
			list.add(msg.getMessageInfo());
		}

		return list;
	}

	/**
	 * Sets the message list.
	 *
	 * @param msgList the new message list
	 */
	public void setMessageList(List<Message> msgList)
	{
		messageList = msgList;
	}

	/**
	 * Checks to see if the operation was a success. The purpose and use of this property is as follows:<br/>
	 * <ul>
	 * <li>OperationSuccess is used to indicate if the operation completed normally at a system level.
	 * <li>This is not an indication of any type of business failure. Use the Response Message-List to determine
	 * business errors, informational messages/warnings or other indicators on the Response.
	 * <li>For example if a fetch for a specific Provider results in the Provider not being found then the
	 * OperationSuccess would still be TRUE. In this case the results-set collection being empty would give indication
	 * as to what happened.
	 * <li>However, if the database was down and the query was unable to complete then the OperationSuccess would be
	 * false indicating there was a system error.
	 * <li>OperationsSuccess is an indication of whether or not the operation completed as was originally designed.
	 * Business errors/warnings etc are a part of the original design and are therefore not an indication of any type of
	 * operation failure.
	 *
	 * @return Taking appropriate action based on this value depends not only on the previous definition of its standard
	 *         purpose and use but also the operation being invoked. Using the example above the consumer would first
	 *         check to make sure that OperationSuccess is true indicating there where no "system" type errors that
	 *         occurred during the execution of the given operation. Subsequently the consumer would use the response
	 *         message-list to insure there are no business related errors. Business errors and warnings are always
	 *         dependent upon the operation being invoked. As a result the operation being invoked should indicate how
	 *         the response and messages should be interpreted.
	 */
	public boolean isOperationSuccess()
	{
		return operationSuccess;
	}

	/**
	 * Sets the operation success.
	 *
	 * @param operationSuccess the new operation success
	 */
	public void setOperationSuccess(boolean operationSuccess)
	{
		this.operationSuccess = operationSuccess;
	}

	/**
	 * Convenience method for setting the operation success property.<br/>
	 *
	 * @param iStatus The status from an {@link InternalResponse}
	 * @return The boolean value for the operation success property.
	 */
	public boolean setOperationSuccess(InternalResponse.Status iStatus)
	{
		switch (iStatus)
		{
			case ExceptionError:
			case SystemError:
			case PersistenceError:
			case OptimisticLockingError:
			case VersionNotFoundError:
			case SingletonSelectError:
			case UnspecifiedError:
			case ExternalError:
			{
				operationSuccess = false;
				break;
			}
			default:
			{
				operationSuccess = true;
			}
		}

		return operationSuccess;

	}

	/**
	 * Add a message.
	 *
	 * @param msg the msg
	 */
	public void addMessage(Message msg)
	{
		messageList.add(msg);
	}

	/**
	 * Add a message with the given parameters.
	 *
	 * @param code the code
	 * @param severity the severity
	 * @param level the level
	 */
	public void addMessage(String code, Message.MessageSeverity severity, Message.MessageLevel level)
	{
		messageList.add(new Message(code, severity, level));
	}

	/**
	 * Add a message with the given parameters.
	 *
	 * @param code the code
	 * @param severity the severity
	 * @param level the level
	 * @param args the args
	 */
	public void addMessage(String code, Message.MessageSeverity severity, Message.MessageLevel level, Object[] args)
	{
		messageList.add(new Message(code, severity, level, args));
	}

	/**
	 * Add a message of the given type with the given parameters.
	 *
	 * @param code the code
	 */
	public void addObjectErrorMessage(String code)
	{
		this.addMessage(code, Message.MessageSeverity.Error, Message.MessageLevel.Object);
	}

	/**
	 * Add a message of the given type with the given parameters.
	 *
	 * @param code the code
	 * @param args the args
	 */
	public void addObjectErrorMessage(String code, Object[] args)
	{
		this.addMessage(code, Message.MessageSeverity.Error, Message.MessageLevel.Object, args);
	}

	/**
	 * Convenience method to add an object error message and set the operation success property to false upon this
	 * Response instance.
	 *
	 * @param code The code.
	 * @param args The message arguments.
	 */
	public void addOperationFailedMessage(String code, Object... args)
	{
		this.addObjectErrorMessage(code, args);
		this.setOperationSuccess(false);
	}

	/**
	 * Convenience method to add internal error messages and set the operation success property to false upon this
	 * Response instance.
	 *
	 * @param messages List<MessageInfo>
	 */
	public void addOperationFailedMessage(List<MessageInfo> messages)
	{
		addMessages(messages);
		this.setOperationSuccess(false);
	}

	/**
	 * Add a message of the given type with the given parameters.
	 *
	 * @param code the code
	 */
	public void addFieldErrorMessage(String code)
	{
		this.addMessage(code, Message.MessageSeverity.Error, Message.MessageLevel.Field);
	}

	/**
	 * Adds a field level message for a given property.
	 *
	 * @param code The Message code
	 * @param propertyName The name of the related property
	 */
	public void addFieldErrorMessage(String code, String propertyName)
	{
		Message msg = new Message(code, Message.MessageSeverity.Error, Message.MessageLevel.Field);
		msg.getMessageInfo().setRelatedPropertyName(propertyName);
		this.addMessage(msg);
	}

	/**
	 * Adds a field level message for a given property with the given arguments.
	 *
	 * @param code The Message code
	 * @param propertyName The name of the related property
	 * @param args the args
	 */
	public void addFieldErrorMessage(String code, String propertyName, Object[] args)
	{
		Message msg = new Message(code, Message.MessageSeverity.Error, Message.MessageLevel.Field, args);
		msg.getMessageInfo().setRelatedPropertyName(propertyName);
		this.addMessage(msg);
	}

	/**
	 * Add a message of the given type with the given parameters.
	 *
	 * @param code the code
	 * @param args the args
	 */
	public void addFieldErrorMessage(String code, Object[] args)
	{
		this.addMessage(code, Message.MessageSeverity.Error, Message.MessageLevel.Field, args);
	}

	/**
	 * Add a MessageInfo type message.
	 *
	 * @param msgInfo the msg info
	 */
	public void addMessage(MessageInfo msgInfo)
	{
		this.addMessage(new Message(msgInfo));
	}

	/**
	 * Add from a collection of MessageInfo instances.
	 *
	 * @param list the list
	 */
	public void addMessages(List<MessageInfo> list)
	{
		for (MessageInfo mi : list)
		{
			this.addMessage(mi);
		}
	}

	/**
	 * Merges the passed in response to this response by adding any additional messages on the passed in response
	 * to this response. The system success values are conditionally updated in case to not
	 * override a response that is already in a failed state.
	 *
	 * @param response the response to update this response with.
	 */
	public void merge(Response response)
	{
		getMessageList().addAll(response.getMessageList());

		if (isOperationSuccess())
		{
			setOperationSuccess(response.isOperationSuccess());
		}
	}
}
