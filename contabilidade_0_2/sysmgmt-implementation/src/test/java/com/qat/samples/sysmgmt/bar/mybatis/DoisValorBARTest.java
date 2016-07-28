/** create by system gera-java version 1.0.0 27/07/2016 15:55 : 11*/
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

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.Util.IDoisValorBAR;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.DoisValoresInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

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
private IDoisValorBAR DoisValoresBAR; // injected by Spring through @Resource

@Resource
public void setDoisValoresBAR(IDoisValorBAR DoisValoresBAR)
{
	this.DoisValoresBAR = DoisValoresBAR;
}

public IDoisValorBAR getDoisValoresBAR()
{
	return DoisValoresBAR;
}


//===================================### DoisValores ####======================================


@Test
	public void testDeleteDoisValores()
	{
		DoisValores DoisValores = insertDoisValores(4, TabelaEnum.DOISVALOR, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		DoisValores DoisValoresResponse = getDoisValoresBAR().fetchDoisValoresById(request);
		Assert.assertEquals(DoisValoresResponse, null);
		getDoisValoresBAR().insertDoisValores(DoisValores);
		DoisValoresResponse = getDoisValoresBAR().fetchDoisValoresById(request);
		Assert.assertEquals(DoisValores.getId(), DoisValoresResponse.getId());
		getDoisValoresBAR().deleteDoisValoresById(DoisValores);
		DoisValoresResponse = getDoisValoresBAR().fetchDoisValoresById(request);
		Assert.assertEquals(DoisValoresResponse, null);
	}

	@Test
	public void testFetchAllDoisValoress()
	{
	DoisValores DoisValores = new DoisValores();
		List<DoisValores> response = getDoisValoresBAR().fetchAllDoisValoress(DoisValores).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllDoisValoress()
	{
		getDoisValoresBAR().deleteAllDoisValoress();
	DoisValores DoisValores = new DoisValores();
		List<DoisValores> response = getDoisValoresBAR().fetchAllDoisValoress(new DoisValores()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateDoisValores()
	{
		DoisValores DoisValores = insertDoisValores(1, TabelaEnum.DOISVALOR, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		DoisValores DoisValoresResponse = getDoisValoresBAR().fetchDoisValoresById(request);
		Assert.assertEquals(DoisValoresResponse.getDescricao(), "NATIVE INSERT");
		getDoisValoresBAR().updateDoisValores(DoisValores);
		DoisValoresResponse = getDoisValoresBAR().fetchDoisValoresById(request);
		Assert.assertEquals(DoisValoresResponse.getDescricao(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchDoisValoressByRequest() throws Exception
	{
		// check for valid and precount
		DoisValoresInquiryRequest request = new DoisValoresInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<DoisValores> response = getDoisValoresBAR().fetchDoisValoressByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getDoisValoresBAR().fetchDoisValoressByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		DoisValoresInquiryRequest request2 = new DoisValoresInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<DoisValores> response2 = getDoisValoresBAR().fetchDoisValoressByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getDoisValoresBAR().deleteAllDoisValoress();
		DoisValoresInquiryRequest request3 = new DoisValoresInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<DoisValores> response3 = getDoisValoresBAR().fetchDoisValoressByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertDoisValores.sql", false);
	}

	
	public DoisValores insertDoisValores(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			DoisValores DoisValores = new DoisValores();
			Date a = new Date();
			DoisValores.setId(100);
			DoisValores.setNome("NATIVE INSERT UPDATE");
			DoisValores.setDescricao("NATIVE INSERT UPDATE");
			DoisValores.setParentId(id);
			DoisValores.setEmprId(1);
			DoisValores.setModifyDateUTC(a.getTime());
			DoisValores.setCreateDateUTC(a.getTime());
			DoisValores.setCreateUser("system");
			DoisValores.setModifyUser("system");
			DoisValores.setProcessId(1);
			DoisValores.setModelAction(action);
	
			return DoisValores;
		}


}
