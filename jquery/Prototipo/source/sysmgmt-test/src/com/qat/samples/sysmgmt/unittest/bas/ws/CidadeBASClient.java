package com.qat.samples.sysmgmt.unittest.bas.ws;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qat.samples.sysmgmt.cidade.bas.ICidadeBAS;
import com.qat.samples.sysmgmt.cidade.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;

public final class CidadeBASClient
{
	private CidadeBASClient()
	{
	}

	public static void main(String[] args) throws Exception
	{
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext(
						new String[] {"classpath:com/qat/samples/sysmgmt/unittest/conf/qat-sysmgmt-base-bas-client-context.xml"});
		ICidadeBAS client = (ICidadeBAS)context.getBean("cidadeBASClientTarget");
		FetchAllRequest request = new FetchAllRequest();
		CidadeResponse response = client.fetchAllCidades(request);
		System.out.println("Response: " + response);
		System.exit(0);
	}
}
