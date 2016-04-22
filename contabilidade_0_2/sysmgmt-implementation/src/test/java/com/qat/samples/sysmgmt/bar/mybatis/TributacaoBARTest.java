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
import com.qat.samples.sysmgmt.bar.Tributacao.ITributacaoBAR;
import com.qat.samples.sysmgmt.fiscal.model.Tributacao;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/tributacaobartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class TributacaoBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(TributacaoBARTest.class);
private ITributacaoBAR tributacaoBAR; // injected by Spring through @Resource

@Resource
public void setTributacaoBAR(ITributacaoBAR tributacaoBAR)
{
	this.tributacaoBAR = tributacaoBAR;
}

public ITributacaoBAR getTributacaoBAR()
{
	return tributacaoBAR;
}


//===================================### TRIBUTACAO ####======================================


@Test
	public void testDeleteTributacao()
	{
		Tributacao tributacao = new Tributacao(999, "Tributacao_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(999);
		Tributacao tributacaoResponse = getTributacaoBAR().fetchTributacaoById(request);
		Assert.assertEquals(tributacaoResponse, null);
		getTributacaoBAR().insertTributacao(tributacao);
		tributacaoResponse = getTributacaoBAR().fetchTributacaoById(request);
		Assert.assertEquals(tributacao.getId(), tributacaoResponse.getId());
		getTributacaoBAR().deleteTributacaoById(tributacao);
		tributacaoResponse = getTributacaoBAR().fetchTributacaoById(request);
		Assert.assertEquals(tributacaoResponse, null);
	}

	@Test
	public void testFetchAllTributacaos()
	{
	Tributacao tributacao = new Tributacao();
		List<Tributacao> response = getTributacaoBAR().fetchAllTributacaos(tributacao).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllTributacaos()
	{
		getTributacaoBAR().deleteAllTributacaos();
		List<Tributacao> response = getTributacaoBAR().fetchAllTributacaos(new Tributacao()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateTributacao()
	{
		Tributacao tributacao = new Tributacao(1234, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1234);
		Tributacao tributacaoResponse = getTributacaoBAR().fetchTributacaoById(request);
		Assert.assertEquals(tributacaoResponse.getEmprId(), "NATIVE INSERT");
		getTributacaoBAR().updateTributacao(tributacao);
		tributacaoResponse = getTributacaoBAR().fetchTributacaoById(request);
		Assert.assertEquals(tributacaoResponse.getEmprId(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchTributacaosByRequest() throws Exception
	{
		// check for valid and precount
		TributacaoInquiryRequest request = new TributacaoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Tributacao> response = getTributacaoBAR().fetchTributacaosByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getTributacaoBAR().fetchTributacaosByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		TributacaoInquiryRequest request2 = new TributacaoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Tributacao> response2 = getTributacaoBAR().fetchTributacaosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getTributacaoBAR().deleteAllTributacaos();
		TributacaoInquiryRequest request3 = new TributacaoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Tributacao> response3 = getTributacaoBAR().fetchTributacaosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertTributacao.sql", false);
	}

}
