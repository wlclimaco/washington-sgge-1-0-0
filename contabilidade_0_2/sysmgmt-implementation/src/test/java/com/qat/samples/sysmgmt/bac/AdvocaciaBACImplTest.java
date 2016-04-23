package com.qat.samples.sysmgmt.bac.Advogado;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.advocacia.Processo;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IAdvogadoBAC. (Business Area Component - BAC)
 */
public interface AdvocaciaBACImpl
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
	public InternalResultsResponse<Advogado> insertAdvogado(AdvogadoMaintenanceRequest request);
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

	/**
	 * Update Advogado.
	 *
	 * @param request the Advogado maintenance request
	 *
	 * @return the internal response
	 */
	public InternalResultsResponse<Advogado> updateAdvogado(AdvogadoMaintenanceRequest request);

	/**
	 * Delete Advogado.
	 *
	 * @param request the Advogado maintenance request
	 *
	 * @return the internal results response
	 */
	public InternalResultsResponse<Advogado> deleteAdvogado(AdvogadoMaintenanceRequest request);

	/**
	 * Refresh Advogados.
	 *
	 * @param request refreshRequest containing the value of the number of Advogados you want refreshed
	 * @return internal results response
	 */
	public InternalResultsResponse<Advogado> refreshAdvogados(RefreshRequest request);

	/**
	 * Fetch Advogado by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Advogado> fetchAdvogadoById(FetchByIdRequest request);

	/**
	 * Fetch all Advogados.
	 *
	 * @return the internal results response< Advogado>
	 */
	public InternalResultsResponse<Advogado> fetchAllAdvogados();

	/**
	 * Fetch Advogados by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Advogado> fetchAdvogadosByRequest(PagedInquiryRequest request);



	//=========Processo
	/**
	 * Insert Advogado.
	 *
	 * @param request the Advogado maintenance request
	 *
	 * @return the internal results response
	 */
	public InternalResultsResponse<Processo> insertProcesso(ProcessoMaintenanceRequest request)
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

	/**
	 * Update Processo.
	 *
	 * @param request the Processo maintenance request
	 *
	 * @return the internal response
	 */
	public InternalResultsResponse<Processo> updateProcesso(ProcessoMaintenanceRequest request);

	/**
	 * Delete Processo.
	 *
	 * @param request the Processo maintenance request
	 *
	 * @return the internal results response
	 */
	public InternalResultsResponse<Processo> deleteProcesso(ProcessoMaintenanceRequest request);

	/**
	 * Refresh Processos.
	 *
	 * @param request refreshRequest containing the value of the number of Processos you want refreshed
	 * @return internal results response
	 */
	public InternalResultsResponse<Processo> refreshProcessos(RefreshRequest request);

	/**
	 * Fetch Processo by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Processo> fetchProcessoById(FetchByIdRequest request);

	/**
	 * Fetch all Processos.
	 *
	 * @return the internal results response< Processo>
	 */
	public InternalResultsResponse<Processo> fetchAllProcessos();

	/**
	 * Fetch Processos by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Processo> fetchProcessosByRequest(PagedInquiryRequest request);
}
