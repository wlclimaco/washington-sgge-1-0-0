package com.qat.samples.sysmgmt.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qat.framework.service.jms.JMSSender;
import com.qat.framework.service.jms.JMSUtil;

/**
 * The Class JMSTextTopicClient integration test shows how a simple String
 * event is published to a Topic.
 */
public final class JMSTextTopicClient
{

	/** The Constant DEF_MSG. */
	private static final String DEF_MSG = "Hello JMS Text Message";

	private JMSTextTopicClient()
	{
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		try
		{
			ApplicationContext ctx =
					new ClassPathXmlApplicationContext(
							"classpath:conf/qat-jmssample-texttopic-client-context.xml");
			JMSSender jmsSend = (JMSSender)ctx.getBean("jmsSender");
			String strCorr = JMSUtil.getNextCorrID();
			jmsSend.sendTextMessage(DEF_MSG, strCorr);
			System.out.print("send msg:" + DEF_MSG + " " + strCorr);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
