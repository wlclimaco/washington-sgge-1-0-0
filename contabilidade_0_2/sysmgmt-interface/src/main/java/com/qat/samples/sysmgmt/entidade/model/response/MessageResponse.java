package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.entidade.model.Message;

/**
 * The Class EmpresaResponse.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:05 AM
 */
public class MessageResponse extends InquiryResponse
{

	/** Attributes */
	private List<Message> messages;

	/**
	 * The Constructor.
	 */
	public MessageResponse()
	{

	}



	public List<Message> getMessages() {
		return messages;
	}



	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}



	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setMessages((List<Message>)coll);
	}



	@Override
	public String toString() {
		return "MessageResponse [getMessages()=" + getMessages() + ", toString()=" + super.toString() + "]";
	}


}