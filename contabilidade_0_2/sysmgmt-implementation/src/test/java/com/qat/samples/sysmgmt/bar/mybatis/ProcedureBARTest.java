package com.qat.samples.sysmgmt.bar.mybatis;

import java.math.BigDecimal;

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
import com.qat.samples.sysmgmt.bar.IProcedureBAR;
import com.qat.samples.sysmgmt.model.Procedure;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/procedurebartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ProcedureBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(ProcedureBARTest.class);
	private IProcedureBAR procedureBAR; // injected by Spring through setter below

	public IProcedureBAR getProcedureBAR()
	{
		return procedureBAR;
	}

	@Resource
	public void setProcedureBAR(IProcedureBAR newValue)
	{
		procedureBAR = newValue;
	}

	@Test
	public void testInsertProcedure() throws Exception
	{
		getProcedureBAR().deleteAllProcedures();
		Procedure procedure = createProcedure();
		getProcedureBAR().insertProcedure(procedure);
		FetchByIdRequest request = createFetchByIdRequest(procedure.getId());
		Procedure response = getProcedureBAR().fetchProcedureById(request);
		Assert.assertEquals(procedure.getId(), response.getId());
		Assert.assertEquals(procedure.getDescription(), response.getDescription());
		Assert.assertEquals(procedure.getCode(), response.getCode());
	}

	@Test
	public void testUpdateProcedure() throws Exception
	{
		getProcedureBAR().deleteAllProcedures();
		Procedure procedure = createProcedure();
		getProcedureBAR().insertProcedure(procedure);
		FetchByIdRequest request = createFetchByIdRequest(procedure.getId());
		procedure.setDescription("NewDescription");
		getProcedureBAR().updateProcedure(procedure);
		Procedure response = getProcedureBAR().fetchProcedureById(request);
		Assert.assertEquals(procedure.getId(), response.getId());
		Assert.assertEquals(procedure.getDescription(), response.getDescription());
	}

	@Test
	public void testDeleteAll() throws Exception
	{
		getProcedureBAR().deleteAllProcedures();
		Assert.assertTrue(getProcedureBAR().fetchAllProcedures().isEmpty());
	}

	@Test
	public void testFetchProceduresByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Procedure> response = getProcedureBAR().fetchProceduresByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check next page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(4);
		response = getProcedureBAR().fetchProceduresByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Procedure> response2 = getProcedureBAR().fetchProceduresByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProcedureBAR().deleteAllProcedures();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Procedure> response3 = getProcedureBAR().fetchProceduresByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Test
	public void testDeleteProcedure() throws Exception
	{
		Procedure procedure = createProcedure();
		getProcedureBAR().insertProcedure(procedure);
		FetchByIdRequest request = createFetchByIdRequest(procedure.getId());
		Assert.assertNotNull(getProcedureBAR().fetchProcedureById(request));
		getProcedureBAR().deleteProcedure(procedure);
		Assert.assertNull(getProcedureBAR().fetchProcedureById(request));
	}

	private Procedure createProcedure()
	{
		Procedure procedure = new Procedure();
		procedure.setCode("Code");
		procedure.setDescription("Description");
		procedure.setPrice(new BigDecimal(12));
		return procedure;
	}

	private FetchByIdRequest createFetchByIdRequest(Integer value)
	{
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(value);
		return request;
	}

	@Before
	public void setup()
	{
		// clear all DBMS just in case there is data in there that will interfere with our tests
		getProcedureBAR().deleteAllProcedures();
		executeSqlScript("conf/insertProcedure.sql", false);
	}

}
