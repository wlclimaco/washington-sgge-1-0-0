package com.qat.samples.sysmgmt.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.response.Response;
import com.qat.framework.service.jms.BizEventMDPJMS;
import com.qat.framework.service.jms.JMSUtil;
import com.qat.samples.sysmgmt.model.SampleBE;

/**
 * The BEMDP1JMSImpl class shows the handling of business events published
 * to a JMS topic. In this case, it is expected that a Spring MessageListenerAdapter
 * is used to delegate to this class.
 *
 */
public class BEMDP1JMSImpl implements BizEventMDPJMS
{

	private static final Logger LOG = LoggerFactory.getLogger(BEMDP1JMSImpl.class);

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
			callSampleBEBAI(recvObj);
		}
		catch (Exception e)
		{
			LOG.error("BizEvent object could not be created: " + e.getMessage());
		}
	}

	/**
	 * Normally this type of class would at this point delegat further activity to
	 * another object to perform business processing such as a BAI.
	 *
	 * @param sampleBE
	 * @return a Response of some sort to be relayed to some other object.
	 */
	private Response callSampleBEBAI(SampleBE sampleBE)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Topic 1 MDP BizEvent received: " + sampleBE.toString() + " time rcvd: "
					+ System.currentTimeMillis() + " ms");
		}
		return null;
	}

}
