package com.qat.samples.sysmgmt.bac.Produto;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.GrupoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedMaintenanceRequest;

/**
 * The Interface IProcedureBAC. (Business Area Component - BAC)
 */
public interface ProdutoBACImpl
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
	public InternalResultsResponse<Produto> insertProduto(ProdutoMaintenanceRequest request)
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

	public InternalResultsResponse<Produto> updateProduto(ProdutoMaintenanceRequest request);

	public InternalResultsResponse<Produto> deleteProduto(ProdutoMaintenanceRequest request);

	public InternalResultsResponse<Produto> refreshProdutos(RefreshRequest request);

	public InternalResultsResponse<Produto> fetchProdutoById(FetchByIdRequest request);

	public InternalResultsResponse<Produto> fetchAllProdutos();

	public InternalResultsResponse<Produto> fetchProdutosByRequest(ProdutoInquiryRequest request);

	//
	public InternalResultsResponse<Marca> insertMarca(MarcaMaintenanceRequest request)
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

	public InternalResultsResponse<Marca> updateMarca(MarcaMaintenanceRequest request);

	public InternalResultsResponse<Marca> deleteMarca(MarcaMaintenanceRequest request);

	public InternalResultsResponse<Marca> refreshMarcas(RefreshRequest request);

	public InternalResultsResponse<Marca> fetchMarcaById(FetchByIdRequest request);

	public InternalResultsResponse<Marca> fetchAllMarcas();

	public InternalResultsResponse<Marca> fetchMarcasByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<Grupo> insertGrupo(GrupoMaintenanceRequest request)
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

	public InternalResultsResponse<Grupo> updateGrupo(GrupoMaintenanceRequest request);

	public InternalResultsResponse<Grupo> deleteGrupo(GrupoMaintenanceRequest request);

	public InternalResultsResponse<Grupo> refreshGrupos(RefreshRequest request);

	public InternalResultsResponse<Grupo> fetchGrupoById(FetchByIdRequest request);

	public InternalResultsResponse<Grupo> fetchAllGrupos();

	public InternalResultsResponse<Grupo> fetchGruposByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<SubGrupo> insertSubGrupo(SubGrupoMaintenanceRequest request)
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

	public InternalResultsResponse<SubGrupo> updateSubGrupo(SubGrupoMaintenanceRequest request);

	public InternalResultsResponse<SubGrupo> deleteSubGrupo(SubGrupoMaintenanceRequest request);

	public InternalResultsResponse<SubGrupo> refreshSubGrupos(RefreshRequest request);

	public InternalResultsResponse<SubGrupo> fetchSubGrupoById(FetchByIdRequest request);

	public InternalResultsResponse<SubGrupo> fetchAllSubGrupos();

	public InternalResultsResponse<SubGrupo> fetchSubGruposByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<UniMed> insertUniMed(UniMedMaintenanceRequest request)
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

	public InternalResultsResponse<UniMed> updateUniMed(UniMedMaintenanceRequest request);

	public InternalResultsResponse<UniMed> deleteUniMed(UniMedMaintenanceRequest request);

	public InternalResultsResponse<UniMed> refreshUniMeds(RefreshRequest request);

	public InternalResultsResponse<UniMed> fetchUniMedById(FetchByIdRequest request);

	public InternalResultsResponse<UniMed> fetchAllUniMeds();

	public InternalResultsResponse<UniMed> fetchUniMedsByRequest(PagedInquiryRequest request);

	//
}
