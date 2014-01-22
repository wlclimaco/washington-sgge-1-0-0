package com.sensus.lc.communication.bcl.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.communication.bcl.IEmailServiceBCL;
import com.sensus.lc.communication.model.request.EmailRequest;

/**
 * Business component for Email communication.
 */
public class EmailServiceBCLImpl implements IEmailServiceBCL
{

	/** The Constant EMAIL_FAILURE. */
	private static final String EMAIL_FAILURE = "sensus.mlc.emailservicebcl.emailfailure";

	/** The mail sender. */
	private JavaMailSender mailSender;

	/**
	 * Logger.
	 */
	private static final Log LOG = LogFactory.getLog(EmailServiceBCLImpl.class);

	/**
	 * @return the mailSender
	 */
	public JavaMailSender getMailSender()
	{
		return mailSender;
	}

	/**
	 * @param mailSender the mailSender to set
	 */
	public void setMailSender(JavaMailSender mailSender)
	{
		this.mailSender = mailSender;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.communication.bcl.IEmailServiceBCL#sendEmail(com.sensus.mlc.communication.model.request.EmailRequest
	 * )
	 */
	@Override
	public InternalResponse sendEmail(EmailRequest request)
	{
		InternalResponse response = new InternalResponse();

		try
		{
			// Prepare Spring's email message for messages without attachment.
			if (ValidationUtil.isNullOrEmpty(request.getAttachmentFileName()))
			{
				SimpleMailMessage message = new SimpleMailMessage();

				message.setFrom(request.getFrom());
				message.setTo(request.getCc().toArray(new String[0]));
				message.setSubject(request.getSubject());
				message.setText(request.getBody());
				mailSender.send(message);

				return response;
			}

			// Prepare attached email
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(request.getFrom());
			helper.setTo(request.getCc().toArray(new String[0]));
			helper.setSubject(request.getSubject());
			helper.setText(request.getBody());

			File csvFile = new File(request.getAttachmentFileName());
			helper.addAttachment(csvFile.getName(), csvFile);

			mailSender.send(helper.getMimeMessage());
			csvFile.delete();
		}
		catch (MessagingException ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ex.getMessage());
			}
			response.setStatus(Status.ExternalError);
			response.addMessage(EMAIL_FAILURE, MessageSeverity.Error, MessageLevel.None);
		}
		return response;
	}

}
