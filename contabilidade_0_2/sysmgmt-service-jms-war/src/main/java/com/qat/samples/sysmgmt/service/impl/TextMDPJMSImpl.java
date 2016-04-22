package com.qat.samples.sysmgmt.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.service.jms.TextMDPJMS;

/**
 * The TextMDPJMSImpl class shows the handling of business event as a simple String published
 * to a JMS topic. In this case, it is expected that a Spring MessageListenerAdapter
 * is used to delegate to this class.
 *
 */
public class TextMDPJMSImpl implements TextMDPJMS
{

	private static final Logger LOG = LoggerFactory.getLogger(TextMDPJMSImpl.class);

	/**
	 * Simply log the String message received.
	 *
	 * @param message String
	 */
	@Override
	public void recvText(String message)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Topic MDP Text received: " + message + " time rcvd: " + System.currentTimeMillis() + " ms");
		}
	}
}
