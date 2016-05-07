package com.qat.samples.sysmgmt.bar.mybatis;


import java.util.Date;
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
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.historico.model.Historico;
import com.qat.samples.sysmgmt.historico.model.HistoricoItens;
import com.qat.samples.sysmgmt.historico.model.request.HistoricoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
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
		Historico historico = new Historico(4,(new Date()).getTime());
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
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
		List<Historico> response = getHistoricoBAR().fetchAllHistoricos(new Historico(1,(new Date()).getTime())).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateHistorico()
	{
		Historico historico = new Historico(4,(new Date()).getTime());
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Historico historicoResponse = getHistoricoBAR().fetchHistoricoById(request);
		getHistoricoBAR().updateHistorico(historico);
		historicoResponse = getHistoricoBAR().fetchHistoricoById(request);

	}

	@Test
	public void testFetchHistoricosByRequest() throws Exception
	{
		// check for valid and precount
		HistoricoInquiryRequest request = new HistoricoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Historico> response = getHistoricoBAR().fetchHistoricosByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getHistoricoBAR().fetchHistoricosByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		HistoricoInquiryRequest request2 = new HistoricoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Historico> response2 = getHistoricoBAR().fetchHistoricosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getHistoricoBAR().deleteAllHistoricos();
		HistoricoInquiryRequest request3 = new HistoricoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Historico> response3 = getHistoricoBAR().fetchHistoricosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### HISTORICOITENS ####======================================


@Test
	public void testDeleteHistoricoItens()
	{
		HistoricoItens historicoitens = new HistoricoItens(4,2,3);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		HistoricoItens historicoitensResponse = getHistoricoBAR().fetchHistoricoItensById(request);
		Assert.assertEquals(historicoitensResponse, null);
		getHistoricoBAR().insertHistoricoItens(historicoitens);
		historicoitensResponse = getHistoricoBAR().fetchHistoricoItensById(request);
//		Assert.assertEquals(historicoitens.getId(), historicoitensResponse.getId());
//		getHistoricoBAR().deleteHistoricoItensById(historicoitens);
//		historicoitensResponse = getHistoricoBAR().fetchHistoricoItensById(request);
//		Assert.assertEquals(historicoitensResponse, null);
	}

	@Test
	public void testFetchAllHistoricoItenss()
	{
	HistoricoItens historicoitens = new HistoricoItens();
		List<HistoricoItens> response = getHistoricoBAR().fetchAllHistoricoItenss(historicoitens).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllHistoricoItenss()
	{
		getHistoricoBAR().deleteAllHistoricoItenss();
	HistoricoItens historicoitens = new HistoricoItens();
		List<HistoricoItens> response = getHistoricoBAR().fetchAllHistoricoItenss(new HistoricoItens(1,2,3)).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateHistoricoItens()
	{
		HistoricoItens historicoitens = new HistoricoItens(1,2,3);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		HistoricoItens historicoitensResponse = getHistoricoBAR().fetchHistoricoItensById(request);
	//	Assert.assertEquals((Long)historicoitensResponse.getProcessId(),1);
		getHistoricoBAR().updateHistoricoItens(historicoitens);
		historicoitensResponse = getHistoricoBAR().fetchHistoricoItensById(request);
		//Assert.assertEquals(historicoitensResponse.getProcessId(), "2");
	}

	@Test
	public void testFetchHistoricoItenssByRequest() throws Exception
	{
		// check for valid and precount
		HistoricoInquiryRequest request = new HistoricoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<HistoricoItens> response = getHistoricoBAR().fetchHistoricoItenssByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getHistoricoBAR().fetchHistoricoItenssByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		HistoricoInquiryRequest request2 = new HistoricoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<HistoricoItens> response2 = getHistoricoBAR().fetchHistoricoItenssByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getHistoricoBAR().deleteAllHistoricoItenss();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<HistoricoItens> response3 = getHistoricoBAR().fetchHistoricoItenssByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertHistoricoItens.sql", false);
		executeSqlScript("conf/insertHistorico.sql", false);
	}

}
