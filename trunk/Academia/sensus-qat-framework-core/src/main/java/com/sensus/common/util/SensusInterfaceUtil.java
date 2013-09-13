package com.sensus.common.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.sensus.common.model.Message;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InquiryResponse;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.model.response.Response;
import com.sensus.common.validation.ValidationUtil;

/**
 * Utility type class with various helper methods to be used by interfaces like BAS's and BAI's in various
 * situations/conditions.
 */
public final class SensusInterfaceUtil
{

	/**
	 * Standard exception handling method which will:
	 * <ol>
	 * <li>Create a {@link com.sensus.common.model.Message} object instance indicating an exception has occurred. This
	 * message will be created with a default 911 message code.
	 * <li>The message will contain the exception including the stack trace in the message trace-info property.
	 * <li>Add the message object to the Response.
	 * <li>Set the operation success indicator to false on the Response.
	 * <li>LOG the message as an error using the LOG parameter.
	 * </ol>
	 * 
	 * @param log An instance of the log to be used to log the exception.
	 * @param resp The Response to use for adding the message to.
	 * @param ex The actual exception instance which will be logged and from which the stack trace will be extracted and
	 *            placed into the message Trace property.
	 */
	public static void handleException(Logger log, Response resp, Exception ex)
	{
		Message msg =
				Message.internalMessage("911", Message.MessageSeverity.Error,
						"An exception has occured, see the trace-info for more information in addition this exception will be logged."
								+ ex.getMessage());
		msg.getMessageInfo().setTraceInfo(ex.toString());
		resp.addMessage(msg);
		resp.setOperationSuccess(false);
		log.error(msg.toString(), ex);
	}

	/**
	 * Standard exception handling method which will:
	 * <ol>
	 * <li>Create a {@link com.sensus.common.model.Message} object instance using the included message-code and argument
	 * parameters.
	 * <li>The Message will contain the exception including the stack trace in the message trace-info property.
	 * <li>The Message object will be added to the Response.
	 * <li>Set the operation success indicator to false on the Response.
	 * <li>LOG the message as an error using the LOG parameter.
	 * </ol>
	 * 
	 * @param log An instance of the log to be used to log the exception.
	 * @param resp The Response to use for adding the message to.
	 * @param ex The actual exception instance which will be logged and from which the stack trace will be extracted and
	 *            placed into the message Trace property.
	 * @param msgCode The message code to be used in creating a {@link com.sensus.common.model.Message} instance which
	 *            will then be added to the response.
	 * @param args The arguments to be used in the creation of the Message instance.
	 */
	public static void handleException(Logger log, Response resp, Exception ex, String msgCode, Object[] args)
	{
		Message msg = new Message(msgCode, Message.MessageSeverity.Fatal, Message.MessageLevel.Object, args);
		msg.getMessageInfo().setTraceInfo(ex.toString());
		resp.addMessage(msg);
		resp.setOperationSuccess(false);
		log.error(msg.toString(), ex);
	}

	/**
	 * Standard exception handling method which will:
	 * <ol>
	 * <li>Create a {@link com.sensus.common.model.Message} object instance using the included message-code and argument
	 * parameters.
	 * <li>The Message will contain the exception including the stack trace in the message trace-info property.
	 * <li>The Message object will be added to the Response.
	 * <li>Set the operation success indicator to false on the Response.
	 * <li>LOG the message as an error using the LOG parameter.
	 * </ol>
	 * 
	 * @param log An instance of the log to be used to log the exception.
	 * @param resp The Response to use for adding the message to.
	 * @param ex The actual exception instance which will be logged and from which the stack trace will be extracted and
	 *            placed into the message Trace property.
	 * @param msgCode The message code to be used in creating a {@link com.sensus.common.model.Message} instance which
	 *            will then be added to the response.
	 */
	public static void handleException(Logger log, Response resp, Exception ex, String msgCode)
	{
		Message msg = new Message(msgCode, Message.MessageSeverity.Fatal, Message.MessageLevel.Object);
		msg.getMessageInfo().setTraceInfo(ex.toString());
		resp.addMessage(msg);
		resp.setOperationSuccess(false);
		log.error(msg.toString(), ex);
	}

	/**
	 * Standard exception handling method which will:
	 * <ol>
	 * <li>Set the Trace property on the Message instance to the stack trace output from the exception.
	 * <li>The Message object will be added to the Response.
	 * <li>Set the operation success indicator to false on the Response.
	 * <li>LOG the message as an error using the LOG parameter.
	 * </ol>
	 * 
	 * @param log An instance of the log to be used to log the exception.
	 * @param resp The Response to use for adding the message to.
	 * @param ex The actual exception instance which will be logged and from which the stack trace will be extracted and
	 *            placed into the message Trace property.
	 * @param msg An instance of {@link com.sensus.common.model.Message}
	 */
	public static void handleException(Logger log, Response resp, Exception ex, Message msg)
	{
		msg.getMessageInfo().setTraceInfo(ex.toString());
		resp.addMessage(msg);
		resp.setOperationSuccess(false);
		log.error(msg.toString(), ex);
	}

	/**
	 * This method simple invokes the
	 * {@link #handleOperationStatusAndMessages(Response, InternalResponse, List, boolean)} with a
	 * null value for the validationMessages parameter. See
	 * 
	 * @param response An instance of {@link Response}
	 * @param internalResponse An instance of {@link InternalResponse}
	 * @param copyOver See the {@link #handleOperationStatusAndMessages(Response, InternalResponse, List, boolean)}
	 *            method.
	 * @return The external response operation success property value. {@link Response#isOperationSuccess()}
	 *         {@link #handleOperationStatusAndMessages(Response, InternalResponse, List, boolean)} method for more
	 *         information.
	 */
	public static boolean handleOperationStatusAndMessages(Response response, InternalResponse internalResponse,
			boolean copyOver)
	{
		return handleOperationStatusAndMessages(response, internalResponse, null, copyOver);
	}

	/**
	 * This method simple invokes the
	 * {@link #handleOperationStatusAndMessages(Response, InternalResponse, List, boolean)} with a
	 * single message on the List if the operation is not successful and not copying over the messages when successful.
	 * 
	 * @param response An instance of {@link Response}
	 * @param internalResponse An instance of {@link InternalResponse}
	 * @param message A validation message key.
	 * @return The external response operation success property value. {@link Response#isOperationSuccess()}
	 *         {@link #handleOperationStatusAndMessages(Response, InternalResponse, List, boolean)} method for more
	 *         information.
	 */
	public static boolean handleOperationStatusAndMessages(Response response, InternalResponse internalResponse,
			String message)
	{
		List<MessageInfo> validationMessages = new ArrayList<MessageInfo>(1);
		if ((internalResponse.getStatus() != Status.OperationSuccess)
				&& (ValidationUtil.isNullOrEmpty(internalResponse.getMessageInfoList())))
		{
			validationMessages
					.add(new MessageInfo(message, Message.MessageSeverity.Error, Message.MessageLevel.Object));
		}
		return handleOperationStatusAndMessages(response, internalResponse, validationMessages, false);
	}

	/**
	 * Convenience method for handling response from validation or other method which return an {@link InternalResponse}
	 * type object. If the validationMessages collection contains anything then we assume validation failed, copy over
	 * the messages from the {@link InternalResponse} to the external {@link Response} and set the operation success
	 * property to false.<br/>
	 * If the internalResponse property is not null then the {@link Response#setOperationSuccess(boolean)} property will
	 * be set using the {@link Response#setOperationSuccess(com.sensus.common.model.InternalResponse.Status)}
	 * convenience method. In addition any messages from the internal response will be copies to the external response.
	 * 
	 * @param response An instance of {@link Response}
	 * @param internalResponse An instance of {@link InternalResponse}
	 * @param validationMessages Collection of messages, most likely from validation but could be from some other
	 *            method.
	 * @param copyOver If true, and the internalResponse parameter is an instance of InternalResultsResponse AND the
	 *            response parameter is an instance of {@link InquiryResponse}, the contents of the
	 *            InternalResultsResponse will be copied over to the InquiryResponse. Note, there is a special method
	 *            on the InquiryResponse class, {@link InquiryResponse#addResults(java.util.Collection)} that is used
	 *            to perform this copy-over. Make sure the InquiryResponse you are using overrides this method before
	 *            setting this parameter to true.
	 * @return The external response operation success property value. {@link Response#isOperationSuccess()}
	 */
	public static boolean handleOperationStatusAndMessages(Response response, InternalResponse internalResponse,
			List<MessageInfo> validationMessages, boolean copyOver)
	{
		// If there are message then validation failed...
		if ((validationMessages != null) && (validationMessages.isEmpty() == false))
		{
			response.setOperationSuccess(false);
			response.addMessages(validationMessages);
		}
		else
		// If there is a response then set the operation success using it and copy of messages.
		if (internalResponse != null)
		{
			// set the operation success based on mapping found in Response type.
			response.setOperationSuccess(internalResponse.getStatus());
			response.addMessages(internalResponse.getMessageInfoList());

			// If operation success then see if we can copy-over the results.
			if (response.isOperationSuccess() && copyOver)
			{
				if ((internalResponse instanceof InternalResultsResponse<?>) &&
						(response instanceof InquiryResponse))
				{
					((InquiryResponse)response).addResults(((InternalResultsResponse<?>)internalResponse)
							.getResultsList());
					((InquiryResponse)response).setResultsSetInfo(((InternalResultsResponse<?>)internalResponse)
							.getResultsSetInfo());
				}
			}
		}

		return response.isOperationSuccess();
	}

}
