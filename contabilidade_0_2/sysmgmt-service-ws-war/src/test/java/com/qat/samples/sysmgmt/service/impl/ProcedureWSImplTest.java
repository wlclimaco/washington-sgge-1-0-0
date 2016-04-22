package com.qat.samples.sysmgmt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bac.IProcedureBAC;
import com.qat.samples.sysmgmt.model.Procedure;
import com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.ProcedureResponse;
import com.qat.samples.sysmgmt.service.IProcedureWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

@ContextConfiguration(locations = {"classpath:conf/procedurewsimpltest-context.xml",
		"classpath:conf/unittest-base-context.xml"})
public class ProcedureWSImplTest extends AbstractJUnit4SpringContextTests
{

	private IProcedureBAC procedureBAC;
	private IProcedureWS procedureWS;

	public IProcedureWS getProcedureWS()
	{
		return procedureWS;
	}

	@Resource
	public void setProcedureBAS(IProcedureWS procedureWS)
	{
		this.procedureWS = procedureWS;
	}

	public IProcedureBAC getMockProcedureBAC()
	{
		return procedureBAC;
	}

	@Resource
	public void setMockProcedureBAC(IProcedureBAC mockBAC)
	{
		procedureBAC = mockBAC;
	}

	private InternalResultsResponse<Procedure> createFetchResponse()
	{
		InternalResultsResponse<Procedure> response = new InternalResultsResponse<Procedure>();
		List<Procedure> list = new ArrayList<>();
		list.add(new Procedure("pc888", "desc888", null));
		response.addResults(list);
		return response;
	}

	@Test
	public void testInsert()
	{
		Procedure procedure = new Procedure("pc88", "desc88", null);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);

		// This shows how to handle when you need to do some specialized code in the mocked method
		Mockito.when(getMockProcedureBAC().insertProcedure(request)).thenReturn(
				new InternalResultsResponse<Procedure>());
		ProcedureResponse response = getProcedureWS().insertProcedure(request);
		Assert.assertNotNull(response);

		// ensures the insertProcedure method on the mock was called 1 time.
		Mockito.verify(getMockProcedureBAC()).insertProcedure(request);
	}

	@Test
	public void testUpdate()
	{
		Procedure procedure = new Procedure("pc88", "desc88", null);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);

		// This shows how to handle when you need to do some specialized code in the mocked method
		Mockito.when(getMockProcedureBAC().updateProcedure(request)).thenReturn(
				new InternalResultsResponse<Procedure>());
		ProcedureResponse response = getProcedureWS().updateProcedure(request);
		Assert.assertNotNull(response);

		// ensures the updateProcedure method on the mock was called 1 time.
		Mockito.verify(getMockProcedureBAC()).updateProcedure(request);
	}

	@Test
	public void testDelete()
	{
		Procedure procedure = new Procedure("pc88", "desc88", null);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);

		// This shows how to handle when you need to do some specialized code in the mocked method
		Mockito.when(getMockProcedureBAC().deleteProcedure(request)).thenReturn(
				new InternalResultsResponse<Procedure>());
		ProcedureResponse response = getProcedureWS().deleteProcedure(request);
		Assert.assertNotNull(response);

		// ensures the deleteProcedure method on the mock was called 1 time.
		Mockito.verify(getMockProcedureBAC()).deleteProcedure(request);
	}

	@Test
	public void testFetchAll()
	{

		FetchAllRequest request = new FetchAllRequest();
		Mockito.when(getMockProcedureBAC().fetchAllProcedures()).thenReturn(createFetchResponse());

		ProcedureResponse response = getProcedureWS().fetchAllProcedures(request);
		Assert.assertTrue(response.getMessageList().isEmpty());
		Assert.assertFalse(response.getProcedures().isEmpty());

		// ensures the fetchAllProcedures method on the mock was called 1 time.
		Mockito.verify(getMockProcedureBAC()).fetchAllProcedures();
	}

	@Test
	public void testFetchById()
	{
		FetchByIdRequest request = new FetchByIdRequest(1);
		Mockito.when(getMockProcedureBAC().fetchProcedureById(request)).thenReturn(createFetchResponse());

		ProcedureResponse response = getProcedureWS().fetchProcedureById(request);

		Assert.assertTrue(response.getMessageList().isEmpty());
		Assert.assertFalse(response.getProcedures().isEmpty());

		Mockito.verify(getMockProcedureBAC()).fetchProcedureById(request);
	}

	@Test
	public void testFetchByRequest()
	{
		Mockito.when(getMockProcedureBAC().fetchProceduresByRequest(Matchers.any(PagedInquiryRequest.class)))
		.thenReturn(
				createFetchResponse());

		ProcedureResponse response = getProcedureWS().fetchProceduresByRequest(new PagedInquiryRequest());

		Assert.assertTrue(response.getMessageList().isEmpty());
		Assert.assertFalse(response.getProcedures().isEmpty());

		Mockito.verify(getMockProcedureBAC()).fetchProceduresByRequest(Matchers.any(PagedInquiryRequest.class));
	}

	@Test
	public void testRefresh()
	{
		RefreshRequest request = new RefreshRequest(4, true, false);
		Mockito.when(getMockProcedureBAC().refreshProcedures(request)).thenReturn(createFetchResponse());

		ProcedureResponse response = getProcedureWS().refreshProcedures(request);

		Assert.assertTrue(response.getMessageList().isEmpty());

		Mockito.verify(getMockProcedureBAC()).refreshProcedures(request);
	}

	@Before
	public void setup()
	{
		Mockito.reset(getMockProcedureBAC());
	}

}
