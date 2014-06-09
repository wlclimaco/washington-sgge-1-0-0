package com.qat.samples.sysmgmt.unittest.bas.ws;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.produto.bas.IProdutoBAS;
import com.qat.samples.sysmgmt.produto.model.response.ProdutoResponse;

public final class ProdutoBASClient
{
	private ProdutoBASClient()
	{
	}

	public static void main(String[] args) throws Exception
	{
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext(
						new String[] {"classpath:com/qat/samples/sysmgmt/unittest/conf/qat-sysmgmt-base-bas-client-context.xml"});
		IProdutoBAS client = (IProdutoBAS)context.getBean("produtoBASClientTarget");
		FetchAllRequest request = new FetchAllRequest();
		ProdutoResponse response = client.fetchAllProdutos(request);
		System.out.println("Response: " + response);
		System.exit(0);
	}
}
