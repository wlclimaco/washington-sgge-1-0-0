package com.qat.samples.sysmgmt.bac.Site;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.request.ContatoMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoMaintenanceRequest;
import com.qat.samples.sysmgmt.site.model.Site;
import com.qat.samples.sysmgmt.site.model.request.SiteInquiryRequest;
import com.qat.samples.sysmgmt.site.model.request.SiteMaintenanceRequest;

/**
 * The Interface IProcedureBAC. (Business Area Component - BAC)
 */
public interface SiteBACImpl
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
	public InternalResultsResponse<Site> insertSite(SiteMaintenanceRequest request)
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

	public InternalResultsResponse<Site> updateSite(SiteMaintenanceRequest request);

	public InternalResultsResponse<Site> deleteSite(SiteMaintenanceRequest request);

	public InternalResultsResponse<Site> refreshSites(RefreshRequest request);

	public InternalResultsResponse<Site> fetchSiteById(FetchByIdRequest request);

	public InternalResultsResponse<Site> fetchAllSites();

	public InternalResultsResponse<Site> fetchSitesByRequest(SiteInquiryRequest request);


	//
	public InternalResultsResponse<Contato> insertContato(ContatoMaintenanceRequest request)
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

	public InternalResultsResponse<Contato> updateContato(ContatoMaintenanceRequest request);

	public InternalResultsResponse<Contato> deleteContato(ContatoMaintenanceRequest request);

	public InternalResultsResponse<Contato> refreshContatos(RefreshRequest request);

	public InternalResultsResponse<Contato> fetchContatoById(FetchByIdRequest request);

	public InternalResultsResponse<Contato> fetchAllContatos();

	public InternalResultsResponse<Contato> fetchContatosByRequest(ContatoInquiryRequest request);

	//

	public InternalResultsResponse<OrdemServico> insertOrdemServico(OrdemServicoMaintenanceRequest request)
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

	public InternalResultsResponse<OrdemServico> updateOrdemServico(OrdemServicoMaintenanceRequest request);

	public InternalResultsResponse<OrdemServico> deleteOrdemServico(OrdemServicoMaintenanceRequest request);

	public InternalResultsResponse<OrdemServico> refreshOrdemServicos(RefreshRequest request);

	public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request);

	public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos();

	public InternalResultsResponse<OrdemServico> fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request);


	//
	public InternalResultsResponse<Plano> insertPlano(PlanoMaintenanceRequest request)
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

	public InternalResultsResponse<Plano> updatePlano(PlanoMaintenanceRequest request);

	public InternalResultsResponse<Plano> deletePlano(PlanoMaintenanceRequest request);

	public InternalResultsResponse<Plano> refreshPlanos(RefreshRequest request);

	public InternalResultsResponse<Plano> fetchPlanoById(FetchByIdRequest request);

	public InternalResultsResponse<Plano> fetchAllPlanos();

	public InternalResultsResponse<Plano> fetchPlanosByRequest(PlanoInquiryRequest request);
}
