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
import com.qat.samples.sysmgmt.bar.IHistoricoBAR;
import com.qat.samples.sysmgmt.model.Historico;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/historicobartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class HistoricoBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(HistoricoBARTest.class);
private IHistoricoBAR historicoBAR; // injected by Spring through @Resource

@Resource
public void setHistoricoBAR(IHistoricoBAR historicoBAR)
{
	this.historicoBAR = historicoBAR;
}

public IHistoricoBAR getHistoricoBAR()
{
	return historicoBAR;
}


//===================================### HISTORICO ####======================================


@Test
	public void testDeleteHistorico()
	{
		Historico historico = new Historico(999, "Historico_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(999);
		Historico historicoResponse = getHistoricoBAR().fetchHistoricoById(request);
		Assert.assertEquals(historicoResponse, null);
		getHistoricoBAR().insertHistorico(historico);
		historicoResponse = getHistoricoBAR().fetchHistoricoById(request);
		Assert.assertEquals(historico.getId(), historicoResponse.getId());
		getHistoricoBAR().deleteHistoricoById(historico);
		historicoResponse = getHistoricoBAR().fetchHistoricoById(request);
		Assert.assertEquals(historicoResponse, null);
	}

	@Test
	public void testFetchAllHistoricos()
	{
	Historico historico = new Historico();
		List<Historico> response = getHistoricoBAR().fetchAllHistoricos(historico).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllHistoricos()
	{
		getHistoricoBAR().deleteAllHistoricos();
	Historico historico = new Historico();
		List<Historico> response = getHistoricoBAR().fetchAllHistoricos().getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateHistorico()
	{
		Historico historico = new Historico(1234, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1234);
		Historico historicoResponse = getHistoricoBAR().fetchHistoricoById(request);
		Assert.assertEquals(historicoResponse.getDescription(), "NATIVE INSERT");
		getHistoricoBAR().updateHistorico(historico);
		historicoResponse = getHistoricoBAR().fetchHistoricoById(request);
		Assert.assertEquals(historicoResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchHistoricosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Historico> response = getHistoricoBAR().fetchHistoricosByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getHistoricoBAR().fetchHistoricosByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Historico> response2 = getHistoricoBAR().fetchHistoricosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getHistoricoBAR().deleteAllHistoricos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Historico> response3 = getHistoricoBAR().fetchHistoricosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertHistorico.sql", false);
	}


//===================================### HISTORICOITENS ####======================================


@Test
	public void testDeleteHistoricoitens()
	{
		Historicoitens historicoitens = new Historicoitens(999, "Historicoitens_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(999);
		Historicoitens historicoitensResponse = getHistoricoBAR().fetchHistoricoitensById(request);
		Assert.assertEquals(historicoitensResponse, null);
		getHistoricoBAR().insertHistoricoitens(historicoitens);
		historicoitensResponse = getHistoricoBAR().fetchHistoricoitensById(request);
		Assert.assertEquals(historicoitens.getId(), historicoitensResponse.getId());
		getHistoricoBAR().deleteHistoricoitensById(historicoitens);
		historicoitensResponse = getHistoricoBAR().fetchHistoricoitensById(request);
		Assert.assertEquals(historicoitensResponse, null);
	}

	@Test
	public void testFetchAllHistoricoitenss()
	{
	Historicoitens historicoitens = new Historicoitens();
		List<Historicoitens> response = getHistoricoBAR().fetchAllHistoricoitenss(historicoitens).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllHistoricoitenss()
	{
		getHistoricoBAR().deleteAllHistoricoitenss();
	Historicoitens historicoitens = new Historicoitens();
		List<Historicoitens> response = getHistoricoBAR().fetchAllHistoricoitenss().getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateHistoricoitens()
	{
		Historicoitens historicoitens = new Historicoitens(1234, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1234);
		Historicoitens historicoitensResponse = getHistoricoBAR().fetchHistoricoitensById(request);
		Assert.assertEquals(historicoitensResponse.getDescription(), "NATIVE INSERT");
		getHistoricoBAR().updateHistoricoitens(historicoitens);
		historicoitensResponse = getHistoricoBAR().fetchHistoricoitensById(request);
		Assert.assertEquals(historicoitensResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchHistoricoitenssByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Historicoitens> response = getHistoricoBAR().fetchHistoricoitenssByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getHistoricoBAR().fetchHistoricoitenssByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Historicoitens> response2 = getHistoricoBAR().fetchHistoricoitenssByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getHistoricoBAR().deleteAllHistoricoitenss();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Historicoitens> response3 = getHistoricoBAR().fetchHistoricoitenssByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertHistoricoitens.sql", false);
	}

}
