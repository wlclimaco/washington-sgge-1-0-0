package com.qat.samples.sysmgmt.unittest.bas.ws;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qat.samples.sysmgmt.entidade.bas.IEmpresaBAS;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.response.CidadeResponse;

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
		IEmpresaBAS client = (IEmpresaBAS)context.getBean("empresaBASClientTarget");
		CidadeInquiryRequest request = new CidadeInquiryRequest();
		CidadeResponse response = client.fetchCidadeByRequest(request);
		System.out.println("Response: " + response);
		System.exit(0);
	}
}
