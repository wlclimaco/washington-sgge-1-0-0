package com.qat.samples.sysmgmt.unittest.bas.ws;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qat.samples.sysmgmt.bas.ICountyBAS;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.response.CountyResponse;

public final class CountyBASClient
{
	private CountyBASClient()
	{
	}

	public static void main(String[] args) throws Exception
	{
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext(
						new String[] {"classpath:com/qat/samples/sysmgmt/unittest/conf/qat-sysmgmt-base-bas-client-context.xml"});
		ICountyBAS client = (ICountyBAS)context.getBean("countyBASClientTarget");
		FetchAllRequest request = new FetchAllRequest();
		CountyResponse response = client.fetchAllCounties(request);
		System.out.println("Response: " + response);
		System.exit(0);
	}
}
