package com.qat.samples.sysmgmt.bac.Fiscal;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopMaintenanceRequest;
import com.qat.samples.sysmgmt.cnae.model.Cnae;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeMaintenanceRequest;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Interface IProcedureBAC. (Business Area Component - BAC)
 */
public interface FiscalBACImpl
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
	public InternalResultsResponse<Regime> insertRegime(RegimeMaintenanceRequest request)
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

	public InternalResultsResponse<Regime> updateRegime(RegimeMaintenanceRequest request);

	public InternalResultsResponse<Regime> deleteRegime(RegimeMaintenanceRequest request);

	public InternalResultsResponse<Regime> refreshRegimes(RefreshRequest request);

	public InternalResultsResponse<Regime> fetchRegimeById(FetchByIdRequest request);

	public InternalResultsResponse<Regime> fetchAllRegimes();

	public InternalResultsResponse<Regime> fetchRegimesByRequest(PagedInquiryRequest request);

	//

	public InternalResultsResponse<Cfop> insertCfop(CfopMaintenanceRequest request)
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

	public InternalResultsResponse<Cfop> updateCfop(CfopMaintenanceRequest request);

	public InternalResultsResponse<Cfop> deleteCfop(CfopMaintenanceRequest request);

	public InternalResultsResponse<Cfop> refreshCfops(RefreshRequest request);

	public InternalResultsResponse<Cfop> fetchCfopById(FetchByIdRequest request);

	public InternalResultsResponse<Cfop> fetchAllCfops();

	public InternalResultsResponse<Cfop> fetchCfopsByRequest(PagedInquiryRequest request);

	//

	public InternalResultsResponse<Cnae> insertCnae(CnaeMaintenanceRequest request);
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
	public InternalResultsResponse<Cnae> updateCnae(CnaeMaintenanceRequest request);

	public InternalResultsResponse<Cnae> deleteCnae(CnaeMaintenanceRequest request);

	public InternalResultsResponse<Cnae> refreshCnaes(RefreshRequest request);

	public InternalResultsResponse<Cnae> fetchCnaeById(FetchByIdRequest request);

	public InternalResultsResponse<Cnae> fetchAllCnaes();

	public InternalResultsResponse<Cnae> fetchCnaesByRequest(PagedInquiryRequest request);

	//
}
