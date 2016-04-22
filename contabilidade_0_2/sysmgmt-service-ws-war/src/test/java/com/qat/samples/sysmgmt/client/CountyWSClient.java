package com.qat.samples.sysmgmt.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qat.samples.sysmgmt.model.response.CountyResponse;
import com.qat.samples.sysmgmt.service.ICountyWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;

public final class CountyWSClient
{
	private CountyWSClient()
	{
	}

	public static void main(String[] args) throws Exception
	{
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext(
						new String[] {"classpath:conf/qat-sysmgmt-base-ws-client-context.xml"});
		ICountyWS client = (ICountyWS)context.getBean("countyWSClientTarget");
		FetchAllRequest request = new FetchAllRequest();
		CountyResponse response = client.fetchAllCounties(request);
		System.out.println("Response: " + response);
		System.exit(0);
	}
}
