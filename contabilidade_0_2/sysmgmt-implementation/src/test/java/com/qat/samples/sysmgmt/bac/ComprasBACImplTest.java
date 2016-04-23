package com.qat.samples.sysmgmt.bac.Compras;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;
import com.qat.samples.sysmgmt.nf.model.PedidoCompras;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalEntradaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasMaintenanceRequest;

/**
 * The Interface IProcedureBAC. (Business Area Component - BAC)
 */
public interface ComprasBACImpl
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
	public InternalResultsResponse<NotaFiscalEntrada> insertNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
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

	public InternalResultsResponse<NotaFiscalEntrada> updateNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request);

	public InternalResultsResponse<NotaFiscalEntrada> deleteNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request);

	public InternalResultsResponse<NotaFiscalEntrada> refreshNotaFiscalEntradas(RefreshRequest request);

	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaById(FetchByIdRequest request);

	public InternalResultsResponse<NotaFiscalEntrada> fetchAllNotaFiscalEntradas();

	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradasByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<PedidoCompras> insertPedidoCompras(PedidoComprasMaintenanceRequest request)
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

	public InternalResultsResponse<PedidoCompras> updatePedidoCompras(PedidoComprasMaintenanceRequest request);

	public InternalResultsResponse<PedidoCompras> deletePedidoCompras(PedidoComprasMaintenanceRequest request);

	public InternalResultsResponse<PedidoCompras> refreshPedidoComprass(RefreshRequest request);

	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasById(FetchByIdRequest request);

	public InternalResultsResponse<PedidoCompras> fetchAllPedidoComprass();

	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasByRequest(PagedInquiryRequest request);


}
