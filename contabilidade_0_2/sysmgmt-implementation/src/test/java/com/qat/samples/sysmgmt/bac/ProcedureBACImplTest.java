package com.qat.samples.sysmgmt.bac;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResponse.SystemErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.IProcedureBAR;
import com.qat.samples.sysmgmt.model.Procedure;
import com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

@ContextConfiguration(locations = {
"classpath:conf/procedurebacimpltest-context.xml"})
public class ProcedureBACImplTest extends AbstractJUnit4SpringContextTests
{
	private IProcedureBAR mockProcedureBAR;

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

	public IProcedureBAR getMockProcedureBAR()
	{
		return mockProcedureBAR;
	}

	@Resource
	public void setMockProcedureBAR(IProcedureBAR mockBAR)
	{
		mockProcedureBAR = mockBAR;
	}

	@Test
	public void testInsertProcedure()
	{
		Procedure procedure = new Procedure("pc88", "desc88", null);

		// This shows how to handle when you need to do some specialized code in the mocked method
		Mockito.when(getMockProcedureBAR().insertProcedure(procedure)).then(new Answer<InternalResponse>()
				{
			@Override
			public InternalResponse answer(InvocationOnMock invocation) throws Throwable
			{
				// invocation.getArguments() contains the objects passed to the method.
				Procedure proc = (Procedure)invocation.getArguments()[0];
				proc.setId(100);
				return new InternalResponse();
			}
				});

		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest();
		request.setProcedure(procedure);

		InternalResultsResponse<Procedure> response = getProcedureBAC().insertProcedure(request);

		Assert.assertNotNull(response);
		Assert.assertNotNull(procedure.getPrice());
		Assert.assertEquals(new Integer(100), procedure.getId());

		// ensures the insertProcedure method on the mock was called 1 time.
		Mockito.verify(getMockProcedureBAR()).insertProcedure(procedure);
	}

	@Test
	public void testInsertProcedureValidationError()
	{
		Procedure procedure = new Procedure("pc88", null, null);

		// This shows how to handle when you need to do some specialized code in the mocked method
		Mockito.when(getMockProcedureBAR().insertProcedure(procedure)).then(new Answer<InternalResponse>()
				{
			@Override
			public InternalResponse answer(InvocationOnMock invocation) throws Throwable
			{
				// invocation.getArguments() contains the objects passed to the method.
				Procedure proc = (Procedure)invocation.getArguments()[0];
				proc.setId(100);
				return new InternalResponse();
			}
				});

		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest();
		request.setProcedure(procedure);

		InternalResultsResponse<Procedure> response = getProcedureBAC().insertProcedure(request);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.hasSystemError());
		Assert.assertEquals(SystemErrorCategory.SystemValidation, response.getSystemError());

		// ensures the insertProcedure method on the mock was called 1 time.
		Mockito.verify(getMockProcedureBAR(), Mockito.never()).insertProcedure(procedure);
	}

	@Test
	public void testUpdateProcedure()
	{
		Procedure procedure = new Procedure("pc89", "desc89", null);
		procedure.setId(1);
		Mockito.when(getMockProcedureBAR().updateProcedure(procedure)).thenReturn(new InternalResponse());

		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest();
		request.setProcedure(procedure);

		Assert.assertNotNull(getProcedureBAC().updateProcedure(request));

		Mockito.verify(getMockProcedureBAR()).updateProcedure(procedure);
	}

	@Test
	public void testUpdateProcedureException()
	{
		Procedure procedure = new Procedure("pc89", "desc89", null);
		procedure.setId(1);
		Mockito.when(getMockProcedureBAR().updateProcedure(procedure)).thenReturn(
				new InternalResponse(BusinessErrorCategory.NoRowsUpdated));
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest();
		request.setProcedure(procedure);
		InternalResultsResponse<Procedure> results = getProcedureBAC().updateProcedure(request);
		Assert.assertTrue(results.hasBusinessError());
		Assert.assertEquals(BusinessErrorCategory.NoRowsUpdated, results.getBusinessError());

		Mockito.verify(getMockProcedureBAR()).updateProcedure(procedure);
	}

	@Test
	public void testUpdateProcedureValidationError()
	{
		Procedure procedure = new Procedure();
		procedure.setId(1);
		Mockito.when(getMockProcedureBAR().updateProcedure(procedure)).thenReturn(
				new InternalResponse());
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest();
		request.setProcedure(procedure);
		InternalResultsResponse<Procedure> results = getProcedureBAC().updateProcedure(request);
		Assert.assertEquals(SystemErrorCategory.SystemValidation, results.getSystemError());

		Mockito.verify(getMockProcedureBAR(), Mockito.never()).updateProcedure(procedure);
	}

	@Test
	public void testDeleteProcedure()
	{
		Procedure procedure = new Procedure("pc89", "desc89", null);
		procedure.setId(1);
		Mockito.when(getMockProcedureBAR().deleteProcedure(procedure)).thenReturn(new InternalResponse());

		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest();
		request.setProcedure(procedure);

		Assert.assertNotNull(getProcedureBAC().deleteProcedure(request));

		Mockito.verify(getMockProcedureBAR()).deleteProcedure(procedure);
	}

	@Test
	public void testDeleteProcedureException()
	{
		Procedure procedure = new Procedure();
		procedure.setId(1);
		// test for bad return
		Mockito.when(getMockProcedureBAR().deleteProcedure(procedure)).thenReturn(
				new InternalResponse(BusinessErrorCategory.NoRowsRemoved));
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest();
		request.setProcedure(procedure);
		InternalResponse results = getProcedureBAC().deleteProcedure(request);
		Assert.assertEquals(BusinessErrorCategory.NoRowsRemoved, results.getBusinessError());
		Mockito.verify(getMockProcedureBAR()).deleteProcedure(procedure);
	}

	@Test
	public void testDeleteProcedureValidationError()
	{
		Procedure procedure = new Procedure();

		// test for bad return
		Mockito.when(getMockProcedureBAR().deleteProcedure(procedure)).thenReturn(
				new InternalResponse());
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest();
		request.setProcedure(procedure);
		InternalResponse results = getProcedureBAC().deleteProcedure(request);
		Assert.assertEquals(SystemErrorCategory.SystemValidation, results.getSystemError());
		Mockito.verify(getMockProcedureBAR(), Mockito.never()).deleteProcedure(procedure);
	}

	@Test
	public void testFetchAllProcedure()
	{
		Mockito.when(getMockProcedureBAR().fetchAllProcedures()).thenReturn(Arrays.asList(new Procedure(),
				new Procedure(), new Procedure(), new Procedure()));

		InternalResultsResponse<Procedure> results = getProcedureBAC().fetchAllProcedures();
		Assert.assertNotNull(results);
		Assert.assertNotNull(results.getResultsList());
		Assert.assertEquals(4, results.getResultsList().size());

		Mockito.verify(getMockProcedureBAR()).fetchAllProcedures();
	}

	@Test
	public void testFetchProceduresByRequest()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();

		Mockito.when(getMockProcedureBAR().fetchProceduresByRequest(request)).thenReturn(
				new InternalResultsResponse<Procedure>(
						Arrays.asList(new Procedure(), new Procedure(), new Procedure(), new Procedure())));

		InternalResultsResponse<Procedure> results = getProcedureBAC().fetchProceduresByRequest(request);
		Assert.assertNotNull(results);
		Assert.assertNotNull(results.getResultsList());
		Assert.assertEquals(4, results.getResultsList().size());

		Mockito.verify(getMockProcedureBAR()).fetchProceduresByRequest(request);
	}

	@Test
	public void testFetchProceduresByRequestNotFound()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();

		InternalResultsResponse<Procedure> irResponse = new InternalResultsResponse<Procedure>();
		irResponse.setStatus(BusinessErrorCategory.NoRowsFound);

		Mockito.when(getMockProcedureBAR().fetchProceduresByRequest(request)).thenReturn(irResponse);

		InternalResultsResponse<Procedure> results = getProcedureBAC().fetchProceduresByRequest(request);
		Assert.assertNotNull(results);
		Assert.assertEquals(BusinessErrorCategory.NoRowsFound, results.getBusinessError());

		Mockito.verify(getMockProcedureBAR()).fetchProceduresByRequest(request);
	}

	@Test
	public void testFetchProcedureById()
	{
		FetchByIdRequest request = new FetchByIdRequest();

		request.setFetchId(1);

		Mockito.when(getMockProcedureBAR().fetchProcedureById(request)).thenReturn(new Procedure());

		Assert.assertNotNull(getProcedureBAC().fetchProcedureById(request));

		Mockito.verify(getMockProcedureBAR()).fetchProcedureById(request);
	}

	@Test
	public void testFetchProcedureByIdValidationError()
	{
		FetchByIdRequest request = new FetchByIdRequest();

		Mockito.when(getMockProcedureBAR().fetchProcedureById(request)).thenReturn(new Procedure());

		InternalResultsResponse<Procedure> results = getProcedureBAC().fetchProcedureById(request);

		Assert.assertNotNull(results);

		Assert.assertTrue(results.hasSystemError());
		Assert.assertEquals(SystemErrorCategory.SystemValidation, results.getSystemError());

		Mockito.verify(getMockProcedureBAR(), Mockito.never()).fetchProcedureById(request);
	}

	@Test
	public void testRefreshProcedures()
	{
		Mockito.when(getMockProcedureBAR().insertProcedure(Matchers.any(Procedure.class))).thenReturn(
				new InternalResponse());

		RefreshRequest request = new RefreshRequest();

		request.setRefreshInt(4);

		getProcedureBAC().refreshProcedures(request);

		// ensures insertProcedure was called 4 times.
		Mockito.verify(getMockProcedureBAR(), Mockito.times(4)).insertProcedure(Matchers.any(Procedure.class));
	}

	@Test
	public void testRefreshProceduresLessThanOne()
	{
		Mockito.when(getMockProcedureBAR().insertProcedure(Matchers.any(Procedure.class))).thenReturn(
				new InternalResponse());

		RefreshRequest request = new RefreshRequest();

		request.setRefreshInt(0);

		getProcedureBAC().refreshProcedures(request);

		// if 0 is passed, it inserts the minimum entries (5).
		Mockito.verify(getMockProcedureBAR(), Mockito.times(5)).insertProcedure(Matchers.any(Procedure.class));
	}

	@Before
	public void setup()
	{
		Mockito.reset(getMockProcedureBAR());
	}

}
