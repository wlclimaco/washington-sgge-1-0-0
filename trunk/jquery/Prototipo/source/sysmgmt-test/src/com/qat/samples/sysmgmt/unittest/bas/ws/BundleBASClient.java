package com.qat.samples.sysmgmt.unittest.bas.ws;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qat.samples.sysmgmt.bas.IBundleBAS;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.response.BundleResponse;

public final class BundleBASClient
{
	private BundleBASClient()
	{
	}

	public static void main(String[] args) throws Exception
	{
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext(
						new String[] {"classpath:com/qat/samples/sysmgmt/unittest/conf/qat-sysmgmt-base-bas-client-context.xml"});
		IBundleBAS client = (IBundleBAS)context.getBean("bundleBASClientTarget");
		FetchAllRequest request = new FetchAllRequest();
		BundleResponse response = client.fetchAllBundles(request);
		System.out.println("Response: " + response);
		System.exit(0);
	}
}
