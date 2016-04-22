package com.qat.samples.sysmgmt.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.service.jms.BizEventMDPJMS;
import com.qat.framework.service.jms.JMSUtil;
import com.qat.samples.sysmgmt.model.SampleBE;

/**
 * The BEMDP2JMSImpl class shows the handling of business events published
 * to a JMS topic. In this case, it is expected that a Spring MessageListenerAdapter
 * is used to delegate to this class.
 *
 */
public class BEMDP2JMSImpl implements BizEventMDPJMS
{

	private static final Logger LOG = LoggerFactory.getLogger(BEMDP2JMSImpl.class);

	/**
	 * Convert the message to a Java bean and log successful or unsuccessful conversion.
	 *
	 * @param message - a Map which will be converted to a Java bean.
	 */
	@Override
	public void recvBizEvent(Map message)
	{
		SampleBE recvObj = null;
		try
		{
			recvObj = (SampleBE)JMSUtil.convertMapToBean(message, SampleBE.class);
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Topic 2 MDP BizEvent received: " + recvObj.toString() + " time rcvd: "
						+ System.currentTimeMillis() + " ms");
			}
		}
		catch (Exception e)
		{
			LOG.error("BizEvent object could not be created: " + e.getMessage());
		}
	}
}
