package com.sensus.common.model;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;

/**
 * This class is used to represent all the meta-data about a given message.<br/>
 * It contains the message code, the severity, the message level, etc. The actual text is not carried in this class
 * since all messages need to support internationalization. As a result the {@link Message} class is used to wrap this
 * MessageInfo class and in turn resolve the message code to the Locale specific text.<br/>
 * Usually these MessageInfo class are used internal to the application and when a message needs to be returned to the
 * client/consumer it is wrapped in a {@link Message} object instance and included in the Response object returned to
 * the client/consumer.<br/>
 * The MessageInfo class included the concept of a message severity which can range from informational to fatal. In
 * addition messages can be associated to an individual Object or a Field or something else. Each message has a unique
 * code. This message code is used to "lookup" the message text when this MessageInfo is wrapped in an {@link Message}
 * instance as described above. This in turn enables the
 * messages to be internationalized. This MessageInfo class should
 * be used throughout the application as a means of communicating the success or failure of a given operation or
 * process.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageInfo
{
	// [start] Private fields

	/** The message type. */
	private MessageSeverity severity = MessageSeverity.None;

	/** The Exception level. */
	private MessageLevel level = MessageLevel.None;

	/** The Message code. */
	private String code;

	/** The Message field. */
	private String relatedPropertyName;

	/** The create date. */
	private final Date createDate = Calendar.getInstance().getTime();

	/** Message arguments. */
	@XmlTransient
	private Object[] arguments;

	/** The internal message indicator. */
	@XmlTransient
	private boolean internalMsg;

	/** Trace information. */
	private String traceInfo;

	/** The internal text. */
	@XmlTransient
	private String internalText;

	// [end] Private fields

	// [start] Constructors

	/**
	 * Instantiates a new message info.
	 */
	public MessageInfo()
	{
	}

	/**
	 * Constructor with most critical arguments.
	 * 
	 * @param code The message code to be used within this Message instance.
	 * @param severity The severity used within this Message instance.
	 * @param level The level used within this Message instance.
	 * @param args The arguments related to the message that should be used when the message text is formated. This
	 *            supports variable substitution within a given message text.
	 */
	public MessageInfo(String code, Message.MessageSeverity severity, Message.MessageLevel level, Object... args)
	{
		this.severity = severity;
		this.level = level;
		arguments = args;
		this.code = code;
	}

	/**
	 * Constructor with minimal arguments.
	 * 
	 * @param newCode The message code to be used within this Message instance.
	 * @param newSeverity The severity used within this Message instance.
	 * @param newLevel The level used within this Message instance.
	 */
	public MessageInfo(String newCode, Message.MessageSeverity newSeverity, Message.MessageLevel newLevel)
	{
		severity = newSeverity;
		level = newLevel;
		code = newCode;
	}

	/**
	 * Internal message.
	 * 
	 * @param code the code
	 * @param severity the severity
	 * @param text the text
	 * 
	 * @return the message info
	 */
	public static MessageInfo internalMessage(String code, Message.MessageSeverity severity, String text)
	{
		MessageInfo mi = new MessageInfo();
		mi.code = code;
		mi.severity = severity;
		mi.internalText = text;
		mi.internalMsg = true;

		return mi;
	}

	public static MessageInfo createObjectError(String code, Object... args)
	{
		return new MessageInfo(code, Message.MessageSeverity.Error, Message.MessageLevel.Object, args);
	}

	public static MessageInfo createFieldValidationError(String code, Object... args)
	{
		return new MessageInfo(code, Message.MessageSeverity.Error, Message.MessageLevel.FieldValidation, args);
	}

	// [end] Constructors

	// [start] Properties

	/**
	 * Gets the severity.
	 * 
	 * @return the severity
	 */
	public MessageSeverity getSeverity()
	{
		return severity;
	}

	/**
	 * Sets the severity.
	 * 
	 * @param severity the new severity
	 */
	public void setSeverity(MessageSeverity severity)
	{
		this.severity = severity;
	}

	/**
	 * Gets the level.
	 * 
	 * @return the level
	 */
	public MessageLevel getLevel()
	{
		return level;
	}

	/**
	 * Sets the level.
	 * 
	 * @param level the new level
	 */
	public void setLevel(MessageLevel level)
	{
		this.level = level;
	}

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Sets the code.
	 * 
	 * @param code the new code
	 */
	protected void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * Gets the related property name.
	 * 
	 * @return the related property name
	 */
	public String getRelatedPropertyName()
	{
		return relatedPropertyName;
	}

	/**
	 * Sets the related property name.
	 * 
	 * @param relatedPropertyName the new related property name
	 */
	public void setRelatedPropertyName(String relatedPropertyName)
	{
		this.relatedPropertyName = relatedPropertyName;
	}

	/**
	 * Gets the arguments.
	 * 
	 * @return the arguments
	 */
	public Object[] getArguments()
	{
		return arguments;
	}

	/**
	 * Sets the arguments.
	 * 
	 * @param arguments the new arguments
	 */
	public void setArguments(Object[] arguments)
	{
		this.arguments = arguments;
	}

	/**
	 * Checks if is internal message.
	 * 
	 * @return true, if is internal message.
	 */
	public boolean isInternalMsg()
	{
		return internalMsg;
	}

	/**
	 * Gets the internal msg text.
	 * 
	 * @return the internal msg text
	 */
	protected String getInternalMsgText()
	{
		return internalText;
	}

	/**
	 * Gets the trace info.
	 * 
	 * @return the trace info
	 */
	public String getTraceInfo()
	{
		return traceInfo;
	}

	/**
	 * Sets the trace info.
	 * 
	 * @param traceInfo the new trace info
	 */
	public void setTraceInfo(String traceInfo)
	{
		this.traceInfo = traceInfo;
	}

	/**
	 * Gets the creates the date.
	 * 
	 * @return the creates the date
	 */
	public Date getCreateDate()
	{
		return createDate;
	}

	// [end] Properties

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("MessageInfo::Severity=[" + severity + "], Level=[" + level + "], code=[" + code
				+ "], create date=[" + createDate + "], "
				+ ", relatedPropertyName=" + relatedPropertyName + "]"
				+ "internal message=[" + internalMsg + "], trace info=["
				+ traceInfo + "]");
		if (internalMsg == true)
		{
			sb.append("internal message text=[" + internalText + "]");
		}

		return sb.toString();
	}
}
