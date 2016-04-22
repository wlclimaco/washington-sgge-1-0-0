package com.qat.samples.sysmgmt.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qat.framework.service.jms.JMSReceiver;
import com.qat.framework.service.jms.JMSSender;
import com.qat.framework.service.jms.JMSUtil;

/**
 * The Class JMSQueueMapClient is an integration test showing the send of a MapMessage
 * and retrieval of a reply MapMessage.
 */
public final class JMSQueueMapClient
{
	private JMSQueueMapClient()
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
							"classpath:conf/qat-jmssample-queuemap-client-context.xml");
			JMSSender jmsSend = (JMSSender)ctx.getBean("jmsSenderWithReply");
			JMSReceiver jmsRcv = (JMSReceiver)ctx.getBean("jmsReceiver");
			Map map = new HashMap();
			map.put("Name", "Mark");
			map.put("Age", new Integer(47));
			String strCorr = JMSUtil.getNextCorrID();
			jmsSend.sendMapMessage(map, strCorr);
			System.out.print("send msg:" + map.toString() + " " + strCorr);
			Thread.sleep(200);
			Map t = jmsRcv.recvMapMessage(strCorr);
			System.out.print("\nrcv msg:" + t.toString());

		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
