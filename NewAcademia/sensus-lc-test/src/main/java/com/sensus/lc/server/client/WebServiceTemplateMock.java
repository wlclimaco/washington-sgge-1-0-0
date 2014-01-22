package com.sensus.lc.server.client;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceMessageExtractor;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.transport.WebServiceConnection;

public class WebServiceTemplateMock extends WebServiceTemplate
{

	public WebServiceTemplateMock()
	{
		// TODO Auto-generated constructor stub
	}

	public WebServiceTemplateMock(WebServiceMessageFactory messageFactory)
	{
		// TODO Auto-generated constructor stub
	}

	public WebServiceTemplateMock(Marshaller marshaller)
	{
		// TODO Auto-generated constructor stub
	}

	public WebServiceTemplateMock(Marshaller marshaller, Unmarshaller unmarshaller)
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> T doSendAndReceive(MessageContext messageContext, WebServiceConnection connection,
			WebServiceMessageCallback requestCallback, WebServiceMessageExtractor<T> responseExtractor)
	{
		return null;
	}

}
