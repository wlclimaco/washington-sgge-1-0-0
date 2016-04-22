package com.qat.samples.sysmgmt.service.impl;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;

import com.qat.samples.sysmgmt.model.County;

/**
 * The Class JmsQueueObjectJMSImpl shows how a JMS listener can receive a {@link ObjectMessage} and reply to
 * the sender of the message.
 */
public class JmsQueueObjectJMSImpl implements SessionAwareMessageListener<ObjectMessage>
{

	private static final Logger LOG = LoggerFactory.getLogger(JmsQueueObjectJMSImpl.class);

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jms.listener.SessionAwareMessageListener#onMessage(javax.jms.Message, javax.jms.Session)
	 */
	/**
	 * This method simply receives the {@link ObjectMessage} and replies to the sender.
	 *
	 * @param message the ObjectMessage sent
	 * @param session the JMS Session
	 */
	@Override
	public void onMessage(ObjectMessage message, Session session) throws JMSException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("queue object message received: " + message.getObject() + " corr id: "
					+ message.getJMSCorrelationID());
		}
		County county = (County)message.getObject();
		county.setDescription("descback");
		if (LOG.isDebugEnabled())
		{
			LOG.debug("object data back: " + county);
		}
		if (message.getJMSReplyTo() != null)
		{
			MessageProducer replyProducer = session.createProducer(message.getJMSReplyTo());
			ObjectMessage replyMessage = session.createObjectMessage();
			replyMessage.setObject(county);
			replyMessage.setJMSCorrelationID(message.getJMSCorrelationID());
			replyProducer.send(replyMessage);
		}
	}
}
