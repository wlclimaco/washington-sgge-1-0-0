package com.qat.samples.sysmgmt.service.impl;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;

/**
 * The Class JmsQueueMapJMSImpl shows how a JMS listener can receive a {@link MapMessage} and reply to
 * the sender of the message.
 */
public class JmsQueueMapJMSImpl implements SessionAwareMessageListener<MapMessage>
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(JmsQueueMapJMSImpl.class);

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jms.listener.SessionAwareMessageListener#onMessage(javax.jms.Message, javax.jms.Session)
	 */
	/**
	 * This method simply receives the {@link MapMessage} and replies to the sender.
	 *
	 * @param message the MapMessage sent
	 * @param session the JMS Session
	 */
	@Override
	public void onMessage(MapMessage message, Session session) throws JMSException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("queue map message received: " + message.getString("Name") + "," + message.getString("Age")
					+ " time rcvd: " + System.currentTimeMillis() + " ms");
		}
		if (message.getJMSReplyTo() != null)
		{
			MessageProducer replyProducer = session.createProducer(message.getJMSReplyTo());
			MapMessage replyMessage = session.createMapMessage();
			replyMessage.setString("ResultBack", "Processed");
			replyMessage.setString("DataBack", "Data Sent Back:" + System.currentTimeMillis());
			replyMessage.setString("CopyofCorrID", message.getJMSCorrelationID());
			if (LOG.isDebugEnabled())
			{
				LOG.debug("queue map message sent: " + replyMessage.getString("ResultBack") + ","
						+ replyMessage.getString("DataBack") + ","
						+ replyMessage.getString("CopyofCorrID"));
			}
			replyMessage.setJMSCorrelationID(message.getJMSCorrelationID());
			replyProducer.send(replyMessage);
		}
	}
}
