package com.sensus.common.model.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sensus.common.model.Message;
import com.sensus.common.model.MessageInfo;

/**
 * Used as the internal response object for internal operations. Internal operations are defined as operations and
 * methods within a given business area. Typically these are operation within a BAC and other classes and operation that
 * are <B>NOT</B> exposed via an interface, BAS or BAI.<br/>
 * Often a BAC is "wrapped" by a BAS or BAI. In these situations the BAC would return an InternalResponse object.<br/>
 * Most often these types of Responses will be used as the return objects from a call to the BAC or DAC layers.<br/>
 * These "internal" response objects enables loose coupling between a business area and any interfaces that may access
 * it. <br/>
 * This response type includes an enumeration which is used to indicate the overall results of the operation/method.
 * This will be used by the client/consumer to determine if the operation/method was successful or not. This response
 * type does <b>not</b> and <b>should not</b> contain any {@link Message} objects. Instead the {@link MessageInfo} type
 * is used when additional information about the operations results or errors need to be communicated back to the
 * client/consumer. <br/>
 * The practice of creating a sub-class, which is usually done when using the {@link Response} type, from this type is
 * no longer required since this InternalResponse is a generic type object and is often sufficient to be used by itself.<br/>
 * Typical usage of this type is as follows:<br/>
 * <ul>
 * <li>Within the operation/method a new instance of this type is created.
 * <li>The execution of the logic within the method proceeds and if all goes well this new instance of this type is
 * returned.
 * <li>If there is some type of error then you should make use of the "addMessage" methods adding a new MessageInfo to
 * this response type.
 * <li>If there is some type of additional context required or related to the results of this operations/method then
 * make use of the Status-Context collection and methods to add context objects to this InternalResponse instance.
 * <li>Make sure you document the contents of this InternalResponse object so that the client/consumer knows how to
 * process the results of your operation/method.
 * <ul/>
 * Note, this class type does not support the returning a collection of "results" object or a results list. See the
 * {@link InternalResultsResponse} type if you need to do this.
 * 
 * @see InternalResultsResponse
 */
public class InternalResponse
{
	// [start] Private properties

	/** The status. */
	private Status status = Status.OperationSuccess;

	/**
	 * Objects related to the Status. The contents is dependent upon the status. These are <b>NOT<b/> Message objects,
	 * instead they help the consumer of the operation, from which this InternalResponse object is returned, determine
	 * what happened and gives them as much information as possible about the error. The content is defined by each
	 * enumeration status value and is documented in the enumeration.
	 */
	private Map<String, Object> statusContextMap;

	/** The message info list. */
	private List<MessageInfo> messageInfoList;

	/**
	 * Used to indicate the status of an operation within the context of an internal response. As a standard all "error"
	 * statuses must contain the word "Error".
	 */
	public enum Status
	{

		/** The Operation success. */
		OperationSuccess,

		/**
		 * The Exception error.<br/>
		 * In the status context there will be a context item named "exception" with the actual java.lang.Exception
		 * instance.
		 */
		ExceptionError,

		/** The System error. */
		SystemError,

		/** The Persistence error. */
		PersistenceError,

		/** A validation error has occurred. **/
		ValidationError,

		/**
		 * The Optimistic locking error.<br/>
		 * In the status context there will be a context item named "currentversionnumber" of type Integer with the
		 * current version number from the database.
		 */
		OptimisticLockingError,

		/**
		 * There was some type of error verifying the version number for a given object. Most often this occurs when the
		 * statement checking/retrieving the version number returns null, as in row not found.
		 */
		VersionNotFoundError,

		/** The No rows found error. */
		NoRowsFoundError,

		/** The No rows updated error. */
		NoRowsUpdatedError,

		/** The No rows removed error. */
		NoRowsRemovedError,

		/** The No rows inserted error. */
		NoRowsInsertedError,

		/** More than one row was found when only a single row was expected. */
		SingletonSelectError,

		/**
		 * There was some type of error relating to building a complex query. The message collection should contain
		 * details messages related to this error status.
		 **/
		QueryBuilderError,

		/** The Unspecified error. */
		UnspecifiedError,

		/**
		 * Used to indicate some type of external error has occurred. Usually this is used when working with external
		 * systems
		 * and the error status or code do not match up.
		 */
		ExternalError
	}

	// [end] Private properties

	// [start] public constructors

	/**
	 * Instantiates a new internal response with the given Status value.
	 * 
	 * @param newStatus the new status
	 */
	public InternalResponse(Status newStatus)
	{
		status = newStatus;
	}

	/**
	 * Default constructor with a Status of OperationSuccess.
	 */
	public InternalResponse()
	{
	}

	// [end] public constructors

	// [start] Public properties.

	/**
	 * See the {@link #getStatusContextObject(String) getStatusContext} method for information about the status context.<br/>
	 * This method returns the entire map of status context objects. Note, all key values for the map should be
	 * lower-case. If there are no entries in the status context map then an empty map will be returned.
	 * 
	 * @return The entire status context map
	 */
	public Map<String, Object> getStatusContextMap()
	{
		if (statusContextMap == null)
		{
			statusContextMap = new HashMap<String, Object>();
		}

		return statusContextMap;
	}

	/**
	 * The Status Context is/are objects related to the Status. The contents is dependent upon the status. <br/>
	 * These are <b>NOT<b/> Message objects, instead they help the consumer of the operation, from which this
	 * InternalResponse object is returned, determine what happened and gives them as much information as possible about
	 * the error. The contents of the context is defined by each enumeration status value and is documented in the
	 * enumeration.<br/>
	 * This method retrieves and item from the context map.
	 * 
	 * @param key The key of the item to retrieve.
	 * 
	 * @return The status context object
	 */
	public Object getStatusContextObject(String key)
	{
		return getStatusContextMap().get(key.toLowerCase());
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public Status getStatus()
	{
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status the new status
	 */
	public void setStatus(Status status)
	{
		this.status = status;
	}

	/**
	 * Gets the message info list.
	 * 
	 * @return the message info list
	 */
	public List<MessageInfo> getMessageInfoList()
	{
		if (messageInfoList == null)
		{
			messageInfoList = new ArrayList<MessageInfo>();
		}

		return messageInfoList;
	}

	// [end] Public properties

	// [start] Public methods

	/**
	 * See the {@link #getStatusContextObject(String) getStatusContext} method for information about the status context.<br/>
	 * This method removes an item from the status context map.
	 * 
	 * @param key The key of the status context item to be removed.
	 */
	public void removeStatusContextObject(String key)
	{
		getStatusContextMap().remove(key.toLowerCase());
	}

	/**
	 * See the {@link #getStatusContextObject(String) getStatusContext} method for information about the status context.<br/>
	 * This method adds a context item to the map collection.
	 * 
	 * @param key The name of the context item
	 * @param obj The context item
	 */
	public void addStatusContextObject(String key, Object obj)
	{
		getStatusContextMap().put(key.toLowerCase(), obj);
	}

	/**
	 * Determine if the status is an error type status.
	 * 
	 * @return boolean indicator - True if status is an error type status.
	 */
	public boolean isInError()
	{
		// return this.status.name().toLowerCase().contains("error") ? true : false;\
		return status != Status.OperationSuccess;
	}

	// [end] Public methods

	// [start] Add message methods

	/**
	 * Adds a collection of MessageInfo instances into this response.
	 * 
	 * @param messageInfoList the message info list
	 */
	public void addMessages(List<MessageInfo> messageInfoList)
	{
		getMessageInfoList().addAll(messageInfoList);
	}

	/**
	 * Add a new MessageInfo into this response.
	 * 
	 * @param code the code
	 * @param severity the severity
	 * @param level the level
	 */
	public void addMessage(String code, Message.MessageSeverity severity, Message.MessageLevel level)
	{
		getMessageInfoList().add(new MessageInfo(code, severity, level));
	}

	/**
	 * Add a new MessageInfo into this response.
	 * 
	 * @param code the code
	 * @param severity the severity
	 * @param level the level
	 * @param args the args
	 */
	public void addMessage(String code, Message.MessageSeverity severity, Message.MessageLevel level, Object[] args)
	{
		getMessageInfoList().add(new MessageInfo(code, severity, level, args));
	}

	/**
	 * Adds the object error MessageInfo.
	 * 
	 * @param code the code
	 */
	public void addObjectErrorMessage(String code)
	{
		this.addMessage(code, Message.MessageSeverity.Error, Message.MessageLevel.Object);
	}

	/**
	 * Adds the object error message.
	 * 
	 * @param code the code
	 * @param args the args
	 */
	public void addObjectErrorMessage(String code, Object[] args)
	{
		this.addMessage(code, Message.MessageSeverity.Error, Message.MessageLevel.Object, args);
	}

	/**
	 * Adds the field error message.
	 * 
	 * @param code the code
	 */
	public void addFieldErrorMessage(String code)
	{
		this.addMessage(code, Message.MessageSeverity.Error, Message.MessageLevel.Field);
	}

	/**
	 * Adds the field error message.
	 * 
	 * @param code the code
	 * @param args the args
	 */
	public void addFieldErrorMessage(String code, Object[] args)
	{
		this.addMessage(code, Message.MessageSeverity.Error, Message.MessageLevel.Field, args);
	}

	/**
	 * Adds an internal message to this response. Internal messages should not return to the consumer of a public
	 * interface BAS or BAI.
	 * 
	 * @param code the code
	 * @param severity the severity
	 * @param text the text
	 */
	public void addInternalMessage(String code, Message.MessageSeverity severity, String text)
	{
		MessageInfo mi = MessageInfo.internalMessage(code, severity, text);
		getMessageInfoList().add(mi);
	}

	/**
	 * Merge the content of a given instance of InternalResponse with this instance.
	 * Note is the status of "this" instance is not success than the parameter status will be copied over.
	 * This prevent a failed status from being changed over to a success status.
	 * 
	 * @param internalResponse the internal response
	 */
	public void merge(InternalResponse internalResponse)
	{
		if (getStatus() == Status.OperationSuccess)
		{
			setStatus(internalResponse.getStatus());
		}

		getStatusContextMap().putAll(internalResponse.getStatusContextMap());
		getMessageInfoList().addAll(internalResponse.getMessageInfoList());
	}

	/**
	 * Merge the contents of an external response into this internal response.
	 * This includes the messages and the status is set based on the external response operation being false.
	 * In which case this internal response will have its status set to UnspecifiedError.
	 * 
	 * @param response the response
	 */
	public void merge(Response response)
	{
		if (response != null)
		{
			getMessageInfoList().addAll(response.getMessageInfoList());
			if (!response.isOperationSuccess())
			{
				setStatus(Status.UnspecifiedError);
			}
		}
	}

	@Override
	public String toString()
	{
		return "InternalResponse [getStatusContextMap()=" + getStatusContextMap() + ", getStatus()=" + getStatus()
				+ ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isInError()=" + isInError() + "]";
	}

}
