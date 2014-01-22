package com.sensus.lc.communication.bcl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.communication.model.request.EmailRequest;

@ContextConfiguration(locations = {
		"classpath:com/sensus/mlc/communication/emailservicebclimpletest.xml"})
public class EmailServiceBCLTest extends AbstractTestBaseBusiness
{

	private static String EMAIL_TEST_TO = "ADRESSTO";
	private static String EMAIL_TEST_FROM = "ADRESSFROM";
	private static String EMAIL_TEST_SUBJECT = "Email test subject";
	private static String EMAIL_TEST_BODYTEXT = "Email test body text";

	/** The email service bcl. */
	private IEmailServiceBCL emailServiceBCL;

	/**
	 * @return the emailServiceBCL
	 */
	public IEmailServiceBCL getEmailServiceBCL()
	{
		return emailServiceBCL;
	}

	/**
	 * @param emailServiceBCL the emailServiceBCL to set
	 */
	@Resource(name = "emailCommunicationBCLTarget")
	public void setEmailServiceBCL(IEmailServiceBCL emailServiceBCL)
	{
		this.emailServiceBCL = emailServiceBCL;
	}

	@Test
	public void sendMailTest()
	{
		EmailRequest request = new EmailRequest();
		List<String> cc = new ArrayList<String>();
		cc.add(EMAIL_TEST_TO);
		request.setCc(cc);

		request.setSubject(EMAIL_TEST_SUBJECT);
		request.setBody(EMAIL_TEST_BODYTEXT);
		request.setFrom(EMAIL_TEST_FROM);

		try
		{
			getEmailServiceBCL().sendEmail(request);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
