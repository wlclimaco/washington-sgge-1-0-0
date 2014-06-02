package com.qat.samples.sysmgmt.unittest.bac;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bac.IProcedureBAC;
import com.qat.samples.sysmgmt.dac.IProcedureDAC;
import com.qat.samples.sysmgmt.model.Procedure;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {"classpath:conf/web/conf/qat-sysmgmt-base-validators-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/procedurebacimpltest.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-resourcebundles-context.xml"})
public class ProcedureBACImplTest extends AbstractJUnit4SpringContextTests
{

	private IProcedureBAC procedureBAC;

	public IProcedureBAC getProcedureBAC()
	{
		return procedureBAC;
	}

	@Resource
	public void setProcedureBAC(IProcedureBAC procedureBAC)
	{
		this.procedureBAC = procedureBAC;
	}

	@Test
	public void testInsertProcedure()
	{
		Procedure procedure = new Procedure("pc88", "desc88", null);
		InternalResponse response = getProcedureBAC().insertProcedure(procedure);
		assertNotNull(response);
		assertNotNull(procedure.getPrice());
		assertNotNull(procedure.getId());
	}

	@Test
	public void testUpdateProcedure()
	{
		assertNotNull(getProcedureBAC().updateProcedure(new Procedure("pc89", "desc89", null)));
	}

	@Test
	public void testDeleteProcedure()
	{
		assertNotNull(getProcedureBAC().deleteProcedure(new Procedure()));
	}

	@Test
	public void testFetchAllProcedure()
	{
		InternalResultsResponse<Procedure> results = getProcedureBAC().fetchAllProcedures();
		assertNotNull(results);
		assertNotNull(results.getResultsList());
	}

	@Test
	public void testFetchProceduresByRequest()
	{
		InternalResultsResponse<Procedure> results = getProcedureBAC().fetchProceduresByRequest(new PagedInquiryRequest());
		assertNotNull(results);
		assertNotNull(results.getResultsList());
	}

	@Test
	public void testFetchProcedureById()
	{
		assertNotNull(getProcedureBAC().fetchProcedureById(new FetchByIdRequest()));
	}

	@Test
	public void testRefreshProcedures()
	{
		getProcedureBAC().refreshProcedures(4);

	}

	@Test
	public void testRefreshProceduresLessThanOne()
	{
		getProcedureBAC().refreshProcedures(0);
	}

	public static class MockProcedureDAC implements IProcedureDAC
	{
		@Override
		public InternalResponse deleteAllProcedures()
		{
			return new InternalResponse();
		}

		@Override
		public InternalResponse deleteProcedure(Procedure procedure)
		{
			return new InternalResponse();
		}

		@Override
		public List<Procedure> fetchAllProcedures()
		{
			List<Procedure> results = new ArrayList<Procedure>();
			results.add(new Procedure());
			results.add(new Procedure());
			results.add(new Procedure());
			results.add(new Procedure());
			return results;
		}

		@Override
		public Procedure fetchProcedureById(FetchByIdRequest request)
		{
			Procedure procedure = new Procedure();
			return procedure;
		}

		@Override
		public InternalResponse insertProcedure(Procedure procedure)
		{
			procedure.setId(100);
			return new InternalResponse();
		}

		@Override
		public InternalResponse updateProcedure(Procedure procedure)
		{
			return new InternalResponse();
		}

		@Override
		public InternalResultsResponse<Procedure> fetchProceduresByRequest(PagedInquiryRequest request)
		{
			InternalResultsResponse<Procedure> results = new InternalResultsResponse<Procedure>();
			results.addResults(fetchAllProcedures());
			return results;
		}

	}
}
