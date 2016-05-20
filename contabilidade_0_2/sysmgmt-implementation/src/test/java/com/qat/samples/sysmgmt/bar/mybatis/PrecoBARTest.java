/** create by system gera-java version 1.0.0 07/05/2016 18:43 : 6*/
package com.qat.samples.sysmgmt.bar.mybatis;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.Produto.IPrecoBAR;
import com.qat.samples.sysmgmt.produto.model.Preco;
import com.qat.samples.sysmgmt.produto.model.PrecoTypeEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class PrecoBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(PrecoBARTest.class);
private IPrecoBAR precoBAR; // injected by Spring through @Resource

@Resource
public void setPrecoBAR(IPrecoBAR precoBAR)
{
	this.precoBAR = precoBAR;
}

public IPrecoBAR getPrecoBAR()
{
	return precoBAR;
}


//===================================### PRECO ####======================================


@Test
	public void testDeletePreco()
	{
		Preco preco = new Preco(4, PrecoTypeEnum.VENDA,new Double(9.99));
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Preco precoResponse = getPrecoBAR().fetchPrecoById(request);
		Assert.assertEquals(precoResponse, null);
		getPrecoBAR().insertPreco(preco);
		precoResponse = getPrecoBAR().fetchPrecoById(request);
		Assert.assertEquals(preco.getId(), precoResponse.getId());
		getPrecoBAR().deletePrecoById(preco);
		precoResponse = getPrecoBAR().fetchPrecoById(request);
		Assert.assertEquals(precoResponse, null);
	}

	@Test
	public void testFetchAllPrecos()
	{
	Preco preco = new Preco();
		List<Preco> response = getPrecoBAR().fetchAllPrecos(preco).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllPrecos()
	{
		getPrecoBAR().deleteAllPrecos();
	Preco preco = new Preco();
		List<Preco> response = getPrecoBAR().fetchAllPrecos(new Preco()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdatePreco()
	{
		Preco preco = new Preco(4, PrecoTypeEnum.VENDA,new Double(9.99));
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Preco precoResponse = getPrecoBAR().fetchPrecoById(request);
//		Assert.assertEquals(precoResponse.getString, "NATIVE INSERT");
//		getPrecoBAR().updatePreco(preco);
////		precoResponse = getPrecoBAR().fetchPrecoById(request);
//		Assert.assertEquals(precoResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchPrecosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Preco> response = getPrecoBAR().fetchPrecosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPrecoBAR().fetchPrecosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Preco> response2 = getPrecoBAR().fetchPrecosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getPrecoBAR().deleteAllPrecos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Preco> response3 = getPrecoBAR().fetchPrecosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertPreco.sql", false);
	}

}
