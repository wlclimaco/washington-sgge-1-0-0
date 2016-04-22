package com.qat.samples.sysmgmt.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.mockrunner.jms.DestinationManager;
import com.qat.framework.service.jms.JMSUtil;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.SampleBE;

/**
 *
 * The purpose of the JMSListenersTest is to show how the MockRunner JMS framework can be used
 * to test JMS Listener or Provider classes without having to run an application server.
 * The MockRunner framework provides fake Destination and Connector resources.
 *
 * The SimpleMessageListenerContainer is used instead of the DefaultMessageListenerContainer
 * because the DefaultMessageListenerContainer runs asynchronously in a separate thread.
 * When unit testing, this additional complexity is not needed and can cause inconsistent
 * results.
 */

@ContextConfiguration(locations = {"classpath:conf/qat-jms-provider-context.xml"})
public class JMSListenersTest extends AbstractJUnit4SpringContextTests
{
	private static final String CORRELATION_ID_SHOULD_BE_EQUAL =
			"The correlation ID on the send message should be the same on the reply message!";

	private static final String THE_MESSAGE_CANNOT_BE_NULL = "The message cannot be null!";

	private static final String DEF_MSG = "Hello JMS";

	@Resource
	private JmsTemplate jmsTemplate;

	@Resource
	private DestinationManager destinationManager;

	@Resource
	private SimpleMessageListenerContainer queMessageListenerContainer1;

	@Resource
	private SimpleMessageListenerContainer queMessageListenerContainer2;

	@Resource
	private SimpleMessageListenerContainer queMessageListenerContainer4;

	@Resource
	private SimpleMessageListenerContainer topic1MessageListenerContainer1;

	@Resource
	private SimpleMessageListenerContainer topic1MessageListenerContainer2;

	@Resource
	private SimpleMessageListenerContainer topic2MessageListenerContainer1;

	@Before
	public void init()
	{
		Assert.assertNotNull(jmsTemplate);
		Assert.assertNotNull(destinationManager);
		Assert.assertNotNull(queMessageListenerContainer1);
		Assert.assertNotNull(queMessageListenerContainer2);
		Assert.assertNotNull(queMessageListenerContainer4);
		Assert.assertNotNull(topic1MessageListenerContainer1);
		Assert.assertNotNull(topic1MessageListenerContainer2);
		Assert.assertNotNull(topic2MessageListenerContainer1);
	}

	@Test
	public void jmsQueueTextBASTest1() throws Exception
	{
		Destination mockQueue = queMessageListenerContainer1.getDestination();
		final Destination mockReplyQueue = destinationManager.createQueue("queue/SampReply");
		final String strCorrID = JMSUtil.getNextCorrID();

		jmsTemplate.convertAndSend(mockQueue, DEF_MSG, new MessagePostProcessor()
		{

			@Override
			public Message postProcessMessage(Message msg) throws JMSException
			{
				msg.setJMSCorrelationID(strCorrID);
				msg.setJMSReplyTo(mockReplyQueue);

				return msg;
			}
		});

		TextMessage message = (TextMessage)jmsTemplate.receive(mockReplyQueue);

		Assert.assertNotNull(THE_MESSAGE_CANNOT_BE_NULL, message);
		Assert.assertNotNull("The text message cannot be null!", message.getText());
		Assert.assertFalse("The sent message cannot equal the reply message!", DEF_MSG.equals(message.getText()));
		Assert.assertEquals(CORRELATION_ID_SHOULD_BE_EQUAL,
				strCorrID, message.getJMSCorrelationID());
	}

	@Test
	public void jmsQueueMapBASTest1() throws Exception
	{
		Destination mockQueue = queMessageListenerContainer2.getDestination();
		final Destination mockReplyQueue = destinationManager.createQueue("queue/SampReply");
		final String strCorrID = JMSUtil.getNextCorrID();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Name", "Mark");
		map.put("Age", new Integer(47));

		jmsTemplate.convertAndSend(mockQueue, map, new MessagePostProcessor()
		{

			@Override
			public Message postProcessMessage(Message msg) throws JMSException
			{
				msg.setJMSCorrelationID(strCorrID);
				msg.setJMSReplyTo(mockReplyQueue);

				return msg;
			}
		});

		MapMessage message = (MapMessage)jmsTemplate.receive(mockReplyQueue);

		Assert.assertNotNull(THE_MESSAGE_CANNOT_BE_NULL, message);
		Assert.assertNotNull("The map message should contain ResultBack key!", message.getString("ResultBack"));
		Assert.assertEquals(CORRELATION_ID_SHOULD_BE_EQUAL,
				strCorrID, message.getJMSCorrelationID());
	}

	@Test
	public void jmsQueueObjectBASTest1() throws Exception
	{
		Destination mockQueue = queMessageListenerContainer4.getDestination();
		final Destination mockReplyQueue = destinationManager.createQueue("queue/SampReply");
		final String strCorrID = JMSUtil.getNextCorrID();

		County county = new County(1, "jmsout");

		jmsTemplate.convertAndSend(mockQueue, county, new MessagePostProcessor()
		{

			@Override
			public Message postProcessMessage(Message msg) throws JMSException
			{
				msg.setJMSCorrelationID(strCorrID);
				msg.setJMSReplyTo(mockReplyQueue);

				return msg;
			}
		});

		ObjectMessage message = (ObjectMessage)jmsTemplate.receive(mockReplyQueue);

		Assert.assertNotNull(THE_MESSAGE_CANNOT_BE_NULL, message);
		Assert.assertNotNull("The object message cannot not be null!", message.getObject());
		Assert.assertEquals("The County object returned should contain 'descback' in its description", "descback",
				((County)message.getObject()).getDescription());
		Assert.assertEquals(CORRELATION_ID_SHOULD_BE_EQUAL,
				strCorrID, message.getJMSCorrelationID());
	}

	@Test
	public void beMDPBASTest1() throws Exception
	{
		Destination mockTopic = topic1MessageListenerContainer1.getDestination();
		final String strCorrID = JMSUtil.getNextCorrID();

		SampleBE obj1 = new SampleBE(strCorrID, "PriceBE", "pc100", "12.00");
		Map map = JMSUtil.convertBeanToMap(obj1);

		jmsTemplate.convertAndSend(mockTopic, map, new MessagePostProcessor()
		{

			@Override
			public Message postProcessMessage(Message msg) throws JMSException
			{
				msg.setJMSCorrelationID(strCorrID);

				return msg;
			}
		});

	}

	@Test
	public void textMDPBASTest1() throws Exception
	{
		Destination mockTopic = topic2MessageListenerContainer1.getDestination();
		final String strCorrID = JMSUtil.getNextCorrID();

		jmsTemplate.convertAndSend(mockTopic, DEF_MSG, new MessagePostProcessor()
		{

			@Override
			public Message postProcessMessage(Message msg) throws JMSException
			{
				msg.setJMSCorrelationID(strCorrID);

				return msg;
			}
		});

	}

}
