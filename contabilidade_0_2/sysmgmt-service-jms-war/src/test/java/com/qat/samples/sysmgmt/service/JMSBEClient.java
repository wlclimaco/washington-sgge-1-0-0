package com.qat.samples.sysmgmt.service;

import java.util.Map;

import javax.jms.Destination;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jndi.JndiTemplate;

import com.qat.framework.service.jms.JMSSender;
import com.qat.framework.service.jms.JMSUtil;
import com.qat.samples.sysmgmt.model.SampleBE;

/**
 * The Class JMSBEClient is an integration test that
 * shows how a business event can be raised using JMS.
 * It makes two calls, the second to a queue expecting a different
 * kind of Message object. The second call fails with a
 * ClassCastException on the server.
 */
public final class JMSBEClient
{

	private JMSBEClient()
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
							"classpath:conf/qat-jmssample-be-client-context.xml");
			JMSSender jmsSend = (JMSSender)ctx.getBean("jmsSender");
			String strCorr = JMSUtil.getNextCorrID();
			SampleBE obj1 = new SampleBE(strCorr, "PriceBE", "pc100", "12.00");
			Map mp3 = JMSUtil.convertBeanToMap(obj1);
			jmsSend.sendMapMessage(mp3, strCorr);
			System.out.print("First send msg:" + obj1.toString() + " first send corr id:" + strCorr);

			/*
			 * The following is a test to show how to switch to another Destination. Because this test switches
			 * to a Destination where the Listener is expecting TextMessages, you should see a CastClassException
			 * in the Server log for this send.
			 */
			JndiTemplate jndi = (JndiTemplate)ctx.getBean("jndiTemplate");
			Destination dest = (Destination)jndi.lookup("queue/SampRequest");
			jmsSend.setRequestDestination(dest);
			strCorr = JMSUtil.getNextCorrID();
			jmsSend.sendMapMessage(mp3, strCorr);
			System.out.print("Second send msg:" + obj1.toString() + " second send corr id:" + strCorr);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
