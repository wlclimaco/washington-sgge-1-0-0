package com.sensus.common.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sensus.common.model.Message;
import com.sensus.common.model.MessageInfo;

/**
 * This class is used to hold all the various objects related to validation.<br/>
 * It is used in the context of invoking validation using the {@link ValidationController}. Note there are many
 * constructors and convenience methods to assist in the population of this class.
 * An instance of this class holds the objects required to be validated. During validation the "objects to be validated"
 * collection can be added to so as to support additional validation "down stream".
 * There is also "special" support for an instance of the {@link ValidationContextIndicator} enumeration.
 */
public class ValidationContext
{
	// [start] private fields

	/** The Constant CONTEXT_INDICATOR_SIMPLE_NAME. */
	private static final String CONTEXT_INDICATOR_SIMPLE_NAME = ValidationContextIndicator.class.getSimpleName();

	/** The objects to be validated. */
	private Map<String, Object> objectsToBeValidated = new HashMap<String, Object>();

	/** The validation arguments. */
	private Map<String, Object> validationArguments = new HashMap<String, Object>();

	/** The messages. */
	private List<MessageInfo> messages = new ArrayList<MessageInfo>();

	/** The stop processing. */
	private boolean stopProcessing;

	// [end] private fields

	// [start] Constructors

	/**
	 * Instantiates a new empty ValidationContext.
	 */
	public ValidationContext()
	{
	}

	/**
	 * Instantiates a new ValidationContext with the given contents.
	 * 
	 * @param newObjectsToBeValidated A map of objects to be validated.
	 * @param newValidationArguments A map of validation arguments
	 * @param newMessages A List of message info instances to be added to the context.
	 */
	public ValidationContext(Map<String, Object> newObjectsToBeValidated, Map<String, Object> newValidationArguments,
			List<MessageInfo> newMessages)
	{
		setObjectsToBeValidated(newObjectsToBeValidated);
		setValidationArguments(newValidationArguments);
		setMessages(newMessages);
	}

	/**
	 * Instantiates a new ValidationContext.
	 * 
	 * @param newMessages A List of message info instances to be added to the context.
	 */
	public ValidationContext(List<MessageInfo> newMessages)
	{
		setMessages(newMessages);
	}

	/**
	 * Instantiates a new ValidationContext with the given contents.
	 * 
	 * @param newObjectsToBeValidated A map of objects to be validated.
	 * @param newMessages A List of message info instances to be added to the context.
	 */
	public ValidationContext(Map<String, Object> newObjectsToBeValidated, List<MessageInfo> newMessages)
	{
		setObjectsToBeValidated(newObjectsToBeValidated);
		setMessages(newMessages);
	}

	/**
	 * Instantiates a new ValidationContext with the given contents.
	 * 
	 * @param objectToBeValidatedName The name of the first object to be validated.
	 * @param objectToBeValidated The actual first object to be validated.
	 */
	public ValidationContext(String objectToBeValidatedName, Object objectToBeValidated)
	{
		getObjectsToBeValidated().put(objectToBeValidatedName, objectToBeValidated);
	}

	/**
	 * Instantiates a new ValidationContext with the given contents.
	 * 
	 * @param objectToBeValidatedName The name of the first object to be validated.
	 * @param objectToBeValidated The actual first object to be validated.
	 * @param validationContextIndicator An instance of the ValidationContextIndicator which will be placed into the
	 *            arguments collection.
	 */
	public ValidationContext(String objectToBeValidatedName, Object objectToBeValidated,
			ValidationContextIndicator validationContextIndicator)
	{
		getObjectsToBeValidated().put(objectToBeValidatedName, objectToBeValidated);
		getValidationArguments().put(CONTEXT_INDICATOR_SIMPLE_NAME, validationContextIndicator);
	}

	/**
	 * Instantiates a new ValidationContext with the given contents.
	 * 
	 * @param objectToBeValidatedName The name of the first object to be validated.
	 * @param objectToBeValidated The actual first object to be validated.
	 * @param validationArgumentName The name of the first argument to be included in the context.
	 * @param validationArgument The actual first argument object to be included in the context.
	 */
	public ValidationContext(String objectToBeValidatedName, Object objectToBeValidated,
			String validationArgumentName, Object validationArgument)
	{
		getObjectsToBeValidated().put(objectToBeValidatedName, objectToBeValidated);
		getValidationArguments().put(validationArgumentName, validationArgument);
	}

	/**
	 * Instantiates a new validation context.
	 * 
	 * @param objectToBeValidatedName The name of the first object to be validated.
	 * @param objectToBeValidated The actual first object to be validated.
	 * @param validationArgumentName The name of the first argument to be included in the context.
	 * @param validationArgument The actual first argument object to be included in the context.
	 * @param validationContextIndicator An instance of the ValidationContextIndicator which will be placed into the
	 *            arguments collection.
	 */
	public ValidationContext(String objectToBeValidatedName, Object objectToBeValidated,
			String validationArgumentName, Object validationArgument,
			ValidationContextIndicator validationContextIndicator)
	{
		getObjectsToBeValidated().put(objectToBeValidatedName, objectToBeValidated);
		getValidationArguments().put(validationArgumentName, validationArgument);
		putValidationContextIndicator(validationContextIndicator);
	}

	/**
	 * Instantiates a new ValidationContext with the given ValidationContextIndicator value.
	 * 
	 * @param validationContextIndicator An instance of the ValidationContextIndicator which will be placed into the
	 *            arguments collection.
	 */
	public ValidationContext(ValidationContextIndicator validationContextIndicator)
	{
		putValidationContextIndicator(validationContextIndicator);
	}

	// [end] Constructors

	// [start] Convenience methods

	/**
	 * Gets the object, by name, to be validated.
	 * 
	 * @param objectToBeValidatedName The name of the object to return.
	 * @return The object to be validated or null if not found by name.
	 */
	public Object getObjectToBeValidated(String objectToBeValidatedName)
	{
		return getObjectsToBeValidated().get(objectToBeValidatedName);
	}

	/**
	 * Checks to see if the given name is contained within the objects to be validated collection.
	 * 
	 * @param objectToBeValidatedName The name of the object to search for.
	 * @return true if found.
	 */
	public boolean hasObjectToBeValidated(String objectToBeValidatedName)
	{
		return getObjectsToBeValidated().containsKey(objectToBeValidatedName);
	}

	/**
	 * Put the given object into the collection using the given name.
	 * 
	 * @param objectToBeValidatedName The name of the object to add.
	 * @param objectToBeValidated The object to be added.
	 */
	public void putObjectToBeValidated(String objectToBeValidatedName, Object objectToBeValidated)
	{
		getObjectsToBeValidated().put(objectToBeValidatedName, objectToBeValidated);
	}

	/**
	 * Returns the {@link ValidationContextIndicator} if one exist within the arguments collection.
	 * 
	 * @return The ValidationContextIndicator, if it exists.
	 */
	public ValidationContextIndicator getValidationContextIndicator()
	{
		if (getValidationArguments().containsKey(CONTEXT_INDICATOR_SIMPLE_NAME))
		{
			return (ValidationContextIndicator)getValidationArguments().get(CONTEXT_INDICATOR_SIMPLE_NAME);
		}

		return null;
	}

	/**
	 * Puts the {@link ValidationContextIndicator} into the arguments collection.
	 * 
	 * @param validationContextIndicator The ValidationContextIndicator
	 */
	public void putValidationContextIndicator(ValidationContextIndicator validationContextIndicator)
	{
		getValidationArguments().put(CONTEXT_INDICATOR_SIMPLE_NAME, validationContextIndicator);
	}

	/**
	 * Adds the object error MessageInfo.
	 * 
	 * @param code The message code to create the message with.
	 * @return The new message info instance
	 */
	public MessageInfo addObjectErrorMessage(String code)
	{
		return this.addMessage(code, Message.MessageSeverity.Error, Message.MessageLevel.Object, null);
	}

	/**
	 * Adds the object error message.
	 * 
	 * @param code The message code to create the message with.
	 * @param args The message arguments for the message, if any.
	 * @return The new message info instance.
	 */
	public MessageInfo addObjectErrorMessage(String code, Object[] args)
	{
		return addMessage(code, Message.MessageSeverity.Error, Message.MessageLevel.Object, args);
	}

	/**
	 * Adds the field error message.
	 * 
	 * @param code The message code to create the message with.
	 * @return The new message info instance.
	 */
	public MessageInfo addFieldErrorMessage(String code)
	{
		return addMessage(code, Message.MessageSeverity.Error, Message.MessageLevel.Field, null);
	}

	/**
	 * Adds the field error message.
	 * 
	 * @param code The message code to use in the new message.
	 * @param args The message arguments, if any.
	 * @return The new message info instance.
	 */
	public MessageInfo addFieldErrorMessage(String code, Object[] args)
	{
		return this.addMessage(code, Message.MessageSeverity.Error, Message.MessageLevel.Field, args);
	}

	/**
	 * Adds the message based on the parameters.
	 * 
	 * @param code The message code to use in the new message.
	 * @param severity The message severity
	 * @param level The message level
	 * @param args The message arguments to use with the message if any.
	 * @return The new message info instance.
	 */
	public MessageInfo addMessage(String code, Message.MessageSeverity severity, Message.MessageLevel level,
			Object[] args)
	{
		MessageInfo messageInfo = new MessageInfo(code, severity, level, args);
		addMessage(messageInfo);
		return messageInfo;
	}

	/**
	 * Add the given message info instance to the context message collection.
	 * 
	 * @param messageInfo the message info
	 */
	public void addMessage(MessageInfo messageInfo)
	{
		getMessages().add(messageInfo);
	}

	// [end] Convenience methods

	// [start] Properties
	/**
	 * Sets the objects to be validated collection.
	 * 
	 * @param objectsToBeValidated the objects to be validated
	 */
	protected void setObjectsToBeValidated(Map<String, Object> objectsToBeValidated)
	{
		if (objectsToBeValidated != null)
		{
			this.objectsToBeValidated = objectsToBeValidated;
		}
	}

	/**
	 * This map is used to hold objects which will be leveraged during validation.<br/>
	 * Since a map is used to contain these objects a key value is required. The key must be well known between
	 * the class which adds the objects to the context map and the validator which will use that same object during
	 * validation. Typically the object class simple name is used for this purpose.
	 * 
	 * @return The objects to be validated. Note this method should never return null.
	 */
	public Map<String, Object> getObjectsToBeValidated()
	{
		return objectsToBeValidated;
	}

	/**
	 * Sets the messages collection overlaying anything that may be been there previously.
	 * 
	 * @param messages the new messages
	 */
	protected void setMessages(List<MessageInfo> messages)
	{
		if (messages != null)
		{
			this.messages = messages;
		}
	}

	/**
	 * Gets the messages collection.
	 * 
	 * @return the messages. Note this method should never return null.
	 */
	public List<MessageInfo> getMessages()
	{
		return messages;
	}

	/**
	 * Sets the validation arguments collection.
	 * 
	 * @param validationArguments the validation arguments
	 */
	protected void setValidationArguments(Map<String, Object> validationArguments)
	{
		if (validationArguments != null)
		{
			this.validationArguments = validationArguments;
		}
	}

	/**
	 * Similar to {@link #getObjectsToBeValidated()} this collection of validation arguments is leverage by the
	 * validators during validation processing. Since a map is used to contain these arguments a key value is required.
	 * The key must be well known between the class which adds the arguments to the context map and the validator which
	 * will use that same object during validation.
	 * 
	 * @return The validation arguments. Note this method should never return null.
	 */
	public Map<String, Object> getValidationArguments()
	{
		return validationArguments;
	}

	/**
	 * Sets the stop processing property based on the parameter.
	 * 
	 * @param stopProcessing the new stop processing
	 */
	public void setStopProcessing(boolean stopProcessing)
	{
		this.stopProcessing = stopProcessing;
	}

	/**
	 * Used to determine if the {@link ValidationController} should stop processing as it is iterating through the
	 * validators.
	 * 
	 * @return true = stop processing.
	 */
	public boolean isStopProcessing()
	{
		return stopProcessing;
	}

	// [end] Properties

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("ValidationContext [getObjectsToBeValidated()=");
		builder.append(getObjectsToBeValidated());
		builder.append(", getMessages()=");
		builder.append(getMessages());
		builder.append(", getValidationArguments()=");
		builder.append(getValidationArguments());
		builder.append(", isStopProcessing()=");
		builder.append(isStopProcessing());
		builder.append("]");
		return builder.toString();
	}
}
