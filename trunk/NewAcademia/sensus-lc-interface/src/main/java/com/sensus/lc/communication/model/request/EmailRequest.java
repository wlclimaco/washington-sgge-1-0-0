package com.sensus.lc.communication.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.request.Request;

/**
 * EmailRequest model object.
 * 
 * @author QAT Global - Thiago
 */
public class EmailRequest extends Request
{

	private Boolean html;
	private String body = null;
	private String encode = "UTF-8";
	private String attachmentFileName;
	private String from;
	private List<String> cc = new ArrayList<String>();
	private List<String> bcc = new ArrayList<String>();
	private String subject;
	private Boolean confirmationRequired;

	/**
	 * @return the attachmentFileName
	 */
	public String getAttachmentFileName()
	{
		return attachmentFileName;
	}

	/**
	 * @param attachmentFileName the attachmentFileName to set
	 */
	public void setAttachmentFileName(String attachmentFileName)
	{
		this.attachmentFileName = attachmentFileName;
	}

	/**
	 * @return the html
	 */
	public Boolean istHtml()
	{
		return html;
	}

	/**
	 * @param html the html to set
	 */
	public void setHtml(Boolean html)
	{
		this.html = html;
	}

	/**
	 * @return the body
	 */
	public String getBody()
	{
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body)
	{
		this.body = body;
	}

	/**
	 * @return the encode
	 */
	public String getEncode()
	{
		return encode;
	}

	/**
	 * @param encode the encode to set
	 */
	public void setEncode(String encode)
	{
		this.encode = encode;
	}

	/**
	 * @return the from
	 */
	public String getFrom()
	{
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from)
	{
		this.from = from;
	}

	/**
	 * @return the cc
	 */
	public List<String> getCc()
	{
		return cc;
	}

	/**
	 * @param cc the cc to set
	 */
	public void setCc(List<String> cc)
	{
		this.cc = cc;
	}

	/**
	 * @return the bcc
	 */
	public List<String> getBcc()
	{
		return bcc;
	}

	/**
	 * @param bcc the bcc to set
	 */
	public void setBcc(List<String> bcc)
	{
		this.bcc = bcc;
	}

	/**
	 * @return the subject
	 */
	public String getSubject()
	{
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	/**
	 * @return the confirmationRequired
	 */
	public Boolean isConfirmationRequired()
	{
		return confirmationRequired;
	}

	/**
	 * @param confirmationRequired the confirmationRequired to set
	 */
	public void setConfirmationRequired(Boolean confirmationRequired)
	{
		this.confirmationRequired = confirmationRequired;
	}

}
