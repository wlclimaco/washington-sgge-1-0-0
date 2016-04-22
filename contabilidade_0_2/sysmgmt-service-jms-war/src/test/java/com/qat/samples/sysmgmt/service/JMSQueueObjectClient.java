package com.qat.samples.sysmgmt.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qat.framework.service.jms.JMSReceiver;
import com.qat.framework.service.jms.JMSSender;
import com.qat.framework.service.jms.JMSUtil;
import com.qat.samples.sysmgmt.model.County;

/**
 * The Class JMSQueueObjectClient is an integration test that shows the send
 * of an ObjectMessage and retrieve of an ObjectMessage.
 */
public final class JMSQueueObjectClient
{
	private JMSQueueObjectClient()
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
							"classpath:conf/qat-jmssample-queueobject-client-context.xml");
			JMSSender jmsSend = (JMSSender)ctx.getBean("jmsSenderWithReply");
			JMSReceiver jmsRcv = (JMSReceiver)ctx.getBean("jmsReceiver");
			County county = new County(1, "jmsout");
			String strCorr = JMSUtil.getNextCorrID();
			jmsSend.sendObjectMessage(county, strCorr);
			System.out.print("send object msg:" + county + " " + strCorr);
			Thread.sleep(200);
			County respCounty = (County)jmsRcv.recvObjectMessage(strCorr);
			System.out.print("\nrcv object msg:" + respCounty);

		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
