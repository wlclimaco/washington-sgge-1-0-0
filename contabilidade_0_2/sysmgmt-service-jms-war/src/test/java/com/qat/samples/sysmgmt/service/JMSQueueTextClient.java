package com.qat.samples.sysmgmt.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qat.framework.service.jms.JMSReceiver;
import com.qat.framework.service.jms.JMSSender;
import com.qat.framework.service.jms.JMSUtil;

/**
 * The Class JMSQueueTextClient is an integration test that shows the send
 * of a TextMessage and the retrieve of a TextMessage.
 */
public final class JMSQueueTextClient
{

	/** The Constant DEF_MSG. */
	private static final String DEF_MSG = "Hello JMS";

	private JMSQueueTextClient()
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
							"classpath:conf/qat-jmssample-queuetext-client-context.xml");
			JMSSender jmsSend = (JMSSender)ctx.getBean("jmsSenderWithReply");
			JMSReceiver jmsRcv = (JMSReceiver)ctx.getBean("jmsReceiver");

			if (args.length > 0)
			{
				for (int i = 0; i < 15; i++)
				{
					jmsSend.sendTextMessage(args[0] + i, args[0] + i);
					System.out.print("\n\nsend msg:" + args[0] + i + "\tsend corr id:" + args[0] + i);
					Thread.sleep(50);
					String t2 = jmsRcv.recvTextMessage(args[0] + i);
					System.out.print("\nrcv msg:" + t2 + "\nrecv corr id:" + args[0] + i);
				}
			}
			else
			{
				String strCorr = JMSUtil.getNextCorrID();
				jmsSend.sendTextMessage(DEF_MSG, strCorr);
				System.out.print("send msg:" + DEF_MSG + " " + strCorr);
				Thread.sleep(200);
				String t = jmsRcv.recvTextMessage(strCorr);
				System.out.print("\nrcv msg:" + t + "\nrecv corr id:" + strCorr);
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
