package com.qat.samples.sysmgmt.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qat.samples.sysmgmt.model.response.CountyResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;

/**
 *
 * CountyBASClient is an integration test to show how a Web Service can be
 * called using JMS as a transport. It uses CXF to provide the transport framework.
 *
 */
public final class CountyWSClient
{
	private CountyWSClient()
	{
	}

	public static void main(String[] args) throws Exception
	{
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext(
						new String[] {"classpath:conf/qat-jmssample-ws-client-context.xml"});
		ICountyWS client = (ICountyWS)context.getBean("countyWSClientTarget");
		FetchAllRequest request = new FetchAllRequest();
		CountyResponse response = client.fetchAllCounties(request);
		System.out.println("Response: " + response);
		context.close();
		System.exit(0);
	}
}
