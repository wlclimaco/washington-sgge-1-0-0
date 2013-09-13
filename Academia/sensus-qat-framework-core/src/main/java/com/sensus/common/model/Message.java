package com.sensus.common.model;

import java.io.StringWriter;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.util.SensusMessageUtil;

/**
 * This class encapsulates the concept of a message including its internationalized text. The message could be related
 * to batch processing, user-interface processing or something else. See the {@link MessageInfo} class for additional
 * information about using messages within QAT applications.<br/>
 * This class serves as a wrapper for the {@link MessageInfo} class and facilitates the resolution of the message code
 * to the correct internationalized message text.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Message
{
	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(Message.class);

	/** The NOT_found. */
	private static String NOT_FOUND = "Message Error - Message code[{0}] was not found. Make sure you have configured the Spring "
			+ "\"messageSource\" bean and included the appropriate property files in the classpath";

	/** The not found message format. */
	private static MessageFormat notFoundMessageFormat = new MessageFormat(NOT_FOUND);

	private MessageInfo msgInfo = new MessageInfo();

	/** The Message text. */
	private String text;

	@XmlTransient
	private boolean textResolved = false;

	/**
	 * The MessageLevel enumeration is used to indicate the related level to which the message is associated.
	 */
	public static enum MessageLevel
	{
		/** The MessageLevel is not indicated. */
		None,

		/** The Message is related Object. */
		Object,

		/** The Message is related to a Field. */
		Field,

		/** The Message is related to Field Validation. */
		FieldValidation,

		/**
		 * The Message is related to something other than an Object or a Field. Typically this is used for custom type
		 * messages.
		 */
		Other
	};

	/**
	 * The MessageSeverity enumeration is used to indicate the severity of a message.
	 */
	public static enum MessageSeverity
	{
		/** No MessageSeverity was indicated. */
		None,

		/** The Message is Informational. */
		Info,

		/** The Message is a Warning. */
		Warning,

		/** The Message is an Error. */
		Error,

		/** The Message is Fatal. */
		Fatal
	}

	/**
	 * Do not allow for a default constructor for the Message Object.
	 */
	private Message()
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
	public Message(String code, Message.MessageSeverity severity, Message.MessageLevel level, Object... args)
	{
		msgInfo.setSeverity(severity);
		msgInfo.setLevel(level);
		msgInfo.setArguments(args);
		msgInfo.setCode(code);

		resolveMessageCode();
	}

	/**
	 * Constructor with minimal arguments.
	 *
	 * @param code The message code to be used within this Message instance.
	 * @param severity The severity used within this Message instance.
	 * @param level The level used within this Message instance.
	 */
	public Message(String code, Message.MessageSeverity severity, Message.MessageLevel level)
	{
		msgInfo.setSeverity(severity);
		msgInfo.setLevel(level);
		msgInfo.setCode(code);

		resolveMessageCode();
	}

	/**
	 * Create a new instance using an instance of {@link MessageInfo}.
	 *
	 * @param msgInfo the msg info
	 */
	public Message(MessageInfo msgInfo)
	{
		setMessageInfo(msgInfo);
	}

	/**
	 * Internal messages are used within the QAT product set and should not be visible to the end customer. As a result
	 * the msgCode will not be resolved. Accordingly the msgCode should be unique to the module generating the message.<br/>
	 * <b>Caution should be used</> be used in using "internalMessages" to insure the text will never be seen by the
	 * end-user and in turn do not support internationalization.
	 *
	 * @param code The message code to be used within this Message instance.
	 * @param severity The severity used within this Message instance.
	 * @param text The actual message text.
	 *
	 * @return the message
	 */
	public static Message internalMessage(String code, Message.MessageSeverity severity, String text)
	{
		// Don't new-up the Message using the 3 parameter constructor since it will try
		// to set the code which won't work since this is an internal type message.
		// Instead set the values manually.
		// Message msg = new Message(code, severity, MessageLevel.Object);
		Message msg = new Message();

		msg.msgInfo = MessageInfo.internalMessage(code, severity, text);
		msg.textResolved = true;
		msg.text = "This is an internal message and should not be passed through to the consumer/client";

		return msg;
	}


	/**
	 * Sets the message info and resolves the message code to the correct internationalized message text.
	 *
	 * @param info the new message info
	 */
	public void setMessageInfo(MessageInfo info)
	{
		msgInfo = info;
		resolveMessageCode();
	}

	/**
	 * Gets the message info.
	 *
	 * @return the message info
	 */
	public MessageInfo getMessageInfo()
	{
		return msgInfo;
	}

	/**
	 * Gets the message text.
	 *
	 * @return the text for this message
	 */
	public String getText()
	{
		if (msgInfo.isInternalMsg())
		{
			return msgInfo.getInternalMsgText();
		}

		if (textResolved == false)
		{
			resolveMessageCode();
		}

		return text;
	}

	// /**
	// * You can't set the Text directly.
	// */
	// public void setText(String text)
	// {
	// this.text = text;
	// }

	// [end] Public properties

	// [start] toXMLString method

	/**
	 * To xml string.
	 *
	 * @return the string
	 */
	public String toXMLString()
	{
		JAXBContext context;
		StringWriter sw = new StringWriter();
		try
		{
			context = JAXBContext.newInstance(Message.class);
			Marshaller m = context.createMarshaller();
			m.marshal(this, sw);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		return sw.toString();
	}

	/**
	 * Resolve message codes.
	 *
	 * @param messageCode the message code
	 * @param args the args
	 *
	 * @return the string
	 */
	private void resolveMessageCode()
	{
		if (getMessageInfo().isInternalMsg())
		{
			return;
		}

		if (msgInfo.getCode() == null)
		{
			text = "Message code is null.";
		}
		else
		{
			// QATMessageUtil will return null if messageCode not found
			String messageText = SensusMessageUtil.getMessage(msgInfo.getCode(), msgInfo.getArguments());

			if (messageText == null)
			{
				messageText = notFoundMessageFormat.format(new Object[] {msgInfo.getCode()});
				LOG.warn(messageText);
			}

			text = messageText;
			textResolved = true;
		}
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		sb.append("Message severity[" + getMessageInfo().getSeverity() + "] create date["
				+ sdf.format(getMessageInfo().getCreateDate().getTime()) + "] ");
		sb.append("code[" + getMessageInfo().getCode() + "] level[" + getMessageInfo().getLevel()
				+ "] internal[" + getMessageInfo().isInternalMsg() + "] text=[" + getText() + "]");
		if (getMessageInfo().getTraceInfo() != null)
		{
			sb.append("\ntrace info[" + getMessageInfo().getTraceInfo() + "]");
		}

		return sb.toString();
	}

}
