package com.qat.samples.sysmgmt.bac.Dp;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.beneficios.model.Beneficios;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.Eventos;
import com.qat.samples.sysmgmt.dp.model.HorarioFunc;
import com.qat.samples.sysmgmt.dp.model.request.EventosMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.pessoa.model.Funcionario;

/**
 * The Interface IProcedureBAC. (Business Area Component - BAC)
 */
public interface DpBACImpl
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
	public InternalResultsResponse<Funcionario> insertFuncionario(FuncionarioMaintenanceRequest request)
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

	public InternalResultsResponse<Funcionario> updateFuncionario(FuncionarioMaintenanceRequest request);

	public InternalResultsResponse<Funcionario> deleteFuncionario(FuncionarioMaintenanceRequest request);

	public InternalResultsResponse<Funcionario> refreshFuncionarios(RefreshRequest request);

	public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request);

	public InternalResultsResponse<Funcionario> fetchAllFuncionarios();

	public InternalResultsResponse<Funcionario> fetchFuncionariosByRequest(PagedInquiryRequest request);

	//

	public InternalResultsResponse<Eventos> insertEventos(EventosMaintenanceRequest request)
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

	public InternalResultsResponse<Eventos> updateEventos(EventosMaintenanceRequest request);

	public InternalResultsResponse<Eventos> deleteEventos(EventosMaintenanceRequest request);

	public InternalResultsResponse<Eventos> refreshEventoss(RefreshRequest request);

	public InternalResultsResponse<Eventos> fetchEventosById(FetchByIdRequest request);

	public InternalResultsResponse<Eventos> fetchAllEventoss();

	public InternalResultsResponse<Eventos> fetchEventossByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<Beneficios> insertBeneficios(BeneficiosMaintenanceRequest request)
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

	public InternalResultsResponse<Beneficios> updateBeneficios(BeneficiosMaintenanceRequest request);

	public InternalResultsResponse<Beneficios> deleteBeneficios(BeneficiosMaintenanceRequest request);

	public InternalResultsResponse<Beneficios> refreshBeneficios(RefreshRequest request);

	public InternalResultsResponse<Beneficios> fetchBeneficiosById(FetchByIdRequest request);

	public InternalResultsResponse<Beneficios> fetchAllBeneficios();

	public InternalResultsResponse<Beneficios> fetchBeneficiosByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<HorarioFunc> insertHoraFunc(HoraFuncMaintenanceRequest request)
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

	public InternalResultsResponse<HorarioFunc> updateHoraFunc(HoraFuncMaintenanceRequest request);

	public InternalResultsResponse<HorarioFunc> deleteHoraFunc(HoraFuncMaintenanceRequest request);

	public InternalResultsResponse<HorarioFunc> refreshHoraFunc(RefreshRequest request);

	public InternalResultsResponse<HorarioFunc> fetchHoraFuncById(FetchByIdRequest request);

	public InternalResultsResponse<HorarioFunc> fetchAllHoraFunc();

	public InternalResultsResponse<HorarioFunc> fetchHoraFuncByRequest(PagedInquiryRequest request);
}
