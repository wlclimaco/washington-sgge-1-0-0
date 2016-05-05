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

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.Telefone.ITelefoneBAR;
import com.qat.samples.sysmgmt.util.model.Telefone;
import com.qat.samples.sysmgmt.util.model.TelefoneTypeEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class TelefoneBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(TelefoneBARTest.class);
private ITelefoneBAR telefoneBAR; // injected by Spring through @Resource

@Resource
public void setTelefoneBAR(ITelefoneBAR telefoneBAR)
{
	this.telefoneBAR = telefoneBAR;
}

public ITelefoneBAR getTelefoneBAR()
{
	return telefoneBAR;
}


//===================================### TELEFONE ####======================================


@Test
	public void testDeleteTelefone()
	{
		Telefone telefone = new Telefone(4,"034" ,"33158065", TelefoneTypeEnum.GERENTE, PersistenceActionEnum.NONE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Telefone telefoneResponse = getTelefoneBAR().fetchTelefoneById(request);
		Assert.assertEquals(telefoneResponse, null);
		getTelefoneBAR().insertTelefone(telefone);
		telefoneResponse = getTelefoneBAR().fetchTelefoneById(request);
		Assert.assertEquals(telefone.getId(), telefoneResponse.getId());
		getTelefoneBAR().deleteTelefoneById(telefone);
		telefoneResponse = getTelefoneBAR().fetchTelefoneById(request);
		Assert.assertEquals(telefoneResponse, null);
	}

	@Test
	public void testFetchAllTelefones()
	{
	Telefone telefone = new Telefone();
		List<Telefone> response = getTelefoneBAR().fetchAllTelefones(telefone).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllTelefones()
	{
		getTelefoneBAR().deleteAllTelefones();
	Telefone telefone = new Telefone();
		List<Telefone> response = getTelefoneBAR().fetchAllTelefones(new Telefone(1,"034" ,"33158065", TelefoneTypeEnum.GERENTE, PersistenceActionEnum.NONE)).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateTelefone()
	{
		Telefone telefone = new Telefone(1,"034" ,"33158065", TelefoneTypeEnum.GERENTE, PersistenceActionEnum.NONE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Telefone telefoneResponse = getTelefoneBAR().fetchTelefoneById(request);
		Assert.assertEquals(telefoneResponse.getNumero(), "numero_5");
		getTelefoneBAR().updateTelefone(telefone);
		telefoneResponse = getTelefoneBAR().fetchTelefoneById(request);
		Assert.assertEquals(telefoneResponse.getNumero(), "33158065");
	}

	@Test
	public void testFetchTelefonesByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Telefone> response = getTelefoneBAR().fetchTelefonesByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getTelefoneBAR().fetchTelefonesByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Telefone> response2 = getTelefoneBAR().fetchTelefonesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getTelefoneBAR().deleteAllTelefones();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Telefone> response3 = getTelefoneBAR().fetchTelefonesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertTelefone.sql", false);
	}

}
