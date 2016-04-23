package com.qat.samples.sysmgmt.bac.Financeiro;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.banco.model.request.BancoMaintenanceRequest;
import com.qat.samples.sysmgmt.condpag.model.CondPag;
import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.financeiro.model.Caixa;
import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;
import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Interface IProcedureBAC. (Business Area Component - BAC)
 */
public interface FinanceiroBACImpl
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
	public InternalResultsResponse<ContasPagar> insertContasPagar(ContasPagarMaintenanceRequest request)
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

	public InternalResultsResponse<ContasPagar> updateContasPagar(ContasPagarMaintenanceRequest request);

	public InternalResultsResponse<ContasPagar> deleteContasPagar(ContasPagarMaintenanceRequest request);

	public InternalResultsResponse<ContasPagar> refreshContasPagars(RefreshRequest request);

	public InternalResultsResponse<ContasPagar> fetchContasPagarById(FetchByIdRequest request);

	public InternalResultsResponse<ContasPagar> fetchAllContasPagars();

	public InternalResultsResponse<ContasPagar> fetchContasPagarsByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<ContasReceber> insertContasReceber(ContasReceberMaintenanceRequest request)
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

	public InternalResultsResponse<ContasReceber> updateContasReceber(ContasReceberMaintenanceRequest request);

	public InternalResultsResponse<ContasReceber> deleteContasReceber(ContasReceberMaintenanceRequest request);

	public InternalResultsResponse<ContasReceber> refreshContasRecebers(RefreshRequest request);

	public InternalResultsResponse<ContasReceber> fetchContasReceberById(FetchByIdRequest request);

	public InternalResultsResponse<ContasReceber> fetchAllContasRecebers();

	public InternalResultsResponse<ContasReceber> fetchContasRecebersByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<CondPag> insertCondPag(CondPagMaintenanceRequest request)
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

	public InternalResultsResponse<CondPag> updateCondPag(CondPagMaintenanceRequest request);

	public InternalResultsResponse<CondPag> deleteCondPag(CondPagMaintenanceRequest request);

	public InternalResultsResponse<CondPag> refreshCondPags(RefreshRequest request);

	public InternalResultsResponse<CondPag> fetchCondPagById(FetchByIdRequest request);

	public InternalResultsResponse<CondPag> fetchAllCondPags();

	public InternalResultsResponse<CondPag> fetchCondPagsByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<FormaPg> insertFormaPg(FormaPgMaintenanceRequest request)
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

	public InternalResultsResponse<FormaPg> updateFormaPg(FormaPgMaintenanceRequest request);

	public InternalResultsResponse<FormaPg> deleteFormaPg(FormaPgMaintenanceRequest request);

	public InternalResultsResponse<FormaPg> refreshFormaPgs(RefreshRequest request);

	public InternalResultsResponse<FormaPg> fetchFormaPgById(FetchByIdRequest request);

	public InternalResultsResponse<FormaPg> fetchAllFormaPgs();

	public InternalResultsResponse<FormaPg> fetchFormaPgsByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<Banco> insertBanco(BancoMaintenanceRequest request)
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

	public InternalResultsResponse<Banco> updateBanco(BancoMaintenanceRequest request);

	public InternalResultsResponse<Banco> deleteBanco(BancoMaintenanceRequest request);

	public InternalResultsResponse<Banco> refreshBancos(RefreshRequest request);

	public InternalResultsResponse<Banco> fetchBancoById(FetchByIdRequest request);

	public InternalResultsResponse<Banco> fetchAllBancos();

	public InternalResultsResponse<Banco> fetchBancosByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<ContaCorrente> insertContaCorrente(ContaCorrenteMaintenanceRequest request)
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

	public InternalResultsResponse<ContaCorrente> updateContaCorrente(ContaCorrenteMaintenanceRequest request);

	public InternalResultsResponse<ContaCorrente> deleteContaCorrente(ContaCorrenteMaintenanceRequest request);

	public InternalResultsResponse<ContaCorrente> refreshContaCorrentes(RefreshRequest request);

	public InternalResultsResponse<ContaCorrente> fetchContaCorrenteById(FetchByIdRequest request);

	public InternalResultsResponse<ContaCorrente> fetchAllContaCorrentes();

	public InternalResultsResponse<ContaCorrente> fetchContaCorrentesByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<Caixa> insertCaixa(CaixaMaintenanceRequest request)
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

	public InternalResultsResponse<Caixa> updateCaixa(CaixaMaintenanceRequest request);

	public InternalResultsResponse<Caixa> deleteCaixa(CaixaMaintenanceRequest request);

	public InternalResultsResponse<Caixa> refreshCaixas(RefreshRequest request);

	public InternalResultsResponse<Caixa> fetchCaixaById(FetchByIdRequest request);

	public InternalResultsResponse<Caixa> fetchAllCaixas();

	public InternalResultsResponse<Caixa> fetchCaixasByRequest(PagedInquiryRequest request);

	//
}
