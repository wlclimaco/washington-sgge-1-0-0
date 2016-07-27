/** create by system gera-java version 1.0.0 27/07/2016 15:55 : 11*/
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
import com.qat.samples.sysmgmt.bar.IDoisValorBAR;
import com.qat.samples.sysmgmt.model.DoisValor;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.DoisValorInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class DoisValorBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(DoisValorBARTest.class);
private IDoisValorBAR doisvalorBAR; // injected by Spring through @Resource

@Resource
public void setDoisValorBAR(IDoisValorBAR doisvalorBAR)
{
	this.doisvalorBAR = doisvalorBAR;
}

public IDoisValorBAR getDoisValorBAR()
{
	return doisvalorBAR;
}


//===================================### DOISVALOR ####======================================


@Test
	public void testDeleteDoisvalor()
	{
		Doisvalor doisvalor = insertDoisvalor(4, TabelaEnum.DOISVALOR, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Doisvalor doisvalorResponse = getDoisValorBAR().fetchDoisvalorById(request);
		Assert.assertEquals(doisvalorResponse, null);
		getDoisValorBAR().insertDoisvalor(doisvalor);
		doisvalorResponse = getDoisValorBAR().fetchDoisvalorById(request);
		Assert.assertEquals(doisvalor.getId(), doisvalorResponse.getId());
		getDoisValorBAR().deleteDoisvalorById(doisvalor);
		doisvalorResponse = getDoisValorBAR().fetchDoisvalorById(request);
		Assert.assertEquals(doisvalorResponse, null);
	}

	@Test
	public void testFetchAllDoisvalors()
	{
	Doisvalor doisvalor = new Doisvalor();
		List<Doisvalor> response = getDoisValorBAR().fetchAllDoisvalors(doisvalor).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllDoisvalors()
	{
		getDoisValorBAR().deleteAllDoisvalors();
	Doisvalor doisvalor = new Doisvalor();
		List<Doisvalor> response = getDoisValorBAR().fetchAllDoisvalors(new Doisvalor()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateDoisvalor()
	{
		Doisvalor doisvalor = insertDoisvalor(1, TabelaEnum.DOISVALOR, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Doisvalor doisvalorResponse = getDoisValorBAR().fetchDoisvalorById(request);
		Assert.assertEquals(doisvalorResponse.getDescription(), "NATIVE INSERT");
		getDoisValorBAR().updateDoisvalor(doisvalor);
		doisvalorResponse = getDoisValorBAR().fetchDoisvalorById(request);
		Assert.assertEquals(doisvalorResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchDoisvalorsByRequest() throws Exception
	{
		// check for valid and precount
		DoisvalorInquiryRequest request = new DoisvalorInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Doisvalor> response = getDoisValorBAR().fetchDoisvalorsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getDoisValorBAR().fetchDoisvalorsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		DoisvalorInquiryRequest request2 = new DoisvalorInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Doisvalor> response2 = getDoisValorBAR().fetchDoisvalorsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getDoisValorBAR().deleteAllDoisvalors();
		DoisvalorInquiryRequest request3 = new DoisvalorInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Doisvalor> response3 = getDoisValorBAR().fetchDoisvalorsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertDoisvalor.sql", false);
	}

	
	public DoisValor insertDoisValor(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			DoisValor doisvalor = new DoisValor();
			Date a = new Date();
			doisvalor.setId(100);
			doisvalor.setValor("NATIVE INSERT UPDATE");
			doisvalor.setDescricao("NATIVE INSERT UPDATE");
			doisvalor.setParentId(id);
			doisvalor.setEmprId(1);
			doisvalor.setModifyDateUTC(a.getTime());
			doisvalor.setCreateDateUTC(a.getTime());
			doisvalor.setCreateUser("system");
			doisvalor.setModifyUser("system");
			doisvalor.setProcessId(1);
			doisvalor.setModelAction(action);
	
			return doisvalor;
		}


}
