package com.qat.samples.sysmgmt.service.impl;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;

/**
 * The Class JmsQueueTextJMSImpl shows how a JMS listener can receive a {@link TextMessage} and reply to
 * the sender of the message.
 */
public class JmsQueueTextJMSImpl implements SessionAwareMessageListener<TextMessage>
{

	private static final Logger LOG = LoggerFactory.getLogger(JmsQueueTextJMSImpl.class);

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jms.listener.SessionAwareMessageListener#onMessage(javax.jms.Message, javax.jms.Session)
	 */
	/**
	 * This method simply receives the {@link TextMessage} and replies to the sender.
	 *
	 * @param message the TextMessage sent
	 * @param session the JMS Session
	 */
	@Override
	public void onMessage(TextMessage message, Session session) throws JMSException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("queue text message received: " + message.getText() + " corr id: "
					+ message.getJMSCorrelationID());
		}
		String sBack = message.getText() + " reply text data the process decides.";
		if (LOG.isDebugEnabled())
		{
			LOG.debug("text data back: " + sBack);
		}
		if (message.getJMSReplyTo() != null)
		{
			MessageProducer replyProducer = session.createProducer(message.getJMSReplyTo());
			TextMessage replyMessage = session.createTextMessage();
			replyMessage.setText(sBack);
			replyMessage.setJMSCorrelationID(message.getJMSCorrelationID());
			replyProducer.send(replyMessage);
		}
	}
}
