package com.qat.samples.sysmgmt.bac.Cadastros;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.convenio.model.Convenio;
import com.qat.samples.sysmgmt.entidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.estado.model.request.EstadoMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.Cidade;
import com.qat.samples.sysmgmt.util.model.Tarefa;
import com.qat.samples.sysmgmt.util.model.request.ConvenioMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.TarefaMaintenanceRequest;

/**
 * The Interface IProcedureBAC. (Business Area Component - BAC)
 */
public interface CadastrosBACImpl
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
	public InternalResultsResponse<Cliente> insertCliente(ClienteMaintenanceRequest request)
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

	public InternalResultsResponse<Cliente> updateCliente(ClienteMaintenanceRequest request);

	public InternalResultsResponse<Cliente> deleteCliente(ClienteMaintenanceRequest request);

	public InternalResultsResponse<Cliente> refreshClientes(RefreshRequest request);

	public InternalResultsResponse<Cliente> fetchClienteById(FetchByIdRequest request);

	public InternalResultsResponse<Cliente> fetchAllClientes();

	public InternalResultsResponse<Cliente> fetchClientesByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<Fornecedor> insertFornecedor(FornecedorMaintenanceRequest request)
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

	public InternalResultsResponse<Fornecedor> updateFornecedor(FornecedorMaintenanceRequest request);

	public InternalResultsResponse<Fornecedor> deleteFornecedor(FornecedorMaintenanceRequest request);

	public InternalResultsResponse<Fornecedor> refreshFornecedors(RefreshRequest request);

	public InternalResultsResponse<Fornecedor> fetchFornecedorById(FetchByIdRequest request);

	public InternalResultsResponse<Fornecedor> fetchAllFornecedors();

	public InternalResultsResponse<Fornecedor> fetchFornecedorsByRequest(PagedInquiryRequest request);

	//

	public InternalResultsResponse<Transportador> insertTransportador(TransportadorMaintenanceRequest request)
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

	public InternalResultsResponse<Transportador> updateTransportador(TransportadorMaintenanceRequest request);

	public InternalResultsResponse<Transportador> deleteTransportador(TransportadorMaintenanceRequest request);

	public InternalResultsResponse<Transportador> refreshTransportadors(RefreshRequest request);

	public InternalResultsResponse<Transportador> fetchTransportadorById(FetchByIdRequest request);

	public InternalResultsResponse<Transportador> fetchAllTransportadors();

	public InternalResultsResponse<Transportador> fetchTransportadorsByRequest(PagedInquiryRequest request);

	//

	public InternalResultsResponse<Convenio> insertConvenio(ConvenioMaintenanceRequest request)
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

	public InternalResultsResponse<Convenio> updateConvenio(ConvenioMaintenanceRequest request);

	public InternalResultsResponse<Convenio> deleteConvenio(ConvenioMaintenanceRequest request);

	public InternalResultsResponse<Convenio> refreshConvenios(RefreshRequest request);

	public InternalResultsResponse<Convenio> fetchConvenioById(FetchByIdRequest request);

	public InternalResultsResponse<Convenio> fetchAllConvenios();

	public InternalResultsResponse<Convenio> fetchConveniosByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<Cidade> insertCidade(CidadeMaintenanceRequest request)
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

	public InternalResultsResponse<Cidade> updateCidade(CidadeMaintenanceRequest request);

	public InternalResultsResponse<Cidade> deleteCidade(CidadeMaintenanceRequest request);

	public InternalResultsResponse<Cidade> refreshCidades(RefreshRequest request);

	public InternalResultsResponse<Cidade> fetchCidadeById(FetchByIdRequest request);

	public InternalResultsResponse<Cidade> fetchAllCidades();

	public InternalResultsResponse<Cidade> fetchCidadesByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<Estado> insertEstado(EstadoMaintenanceRequest request)
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

	public InternalResultsResponse<Estado> updateEstado(EstadoMaintenanceRequest request);

	public InternalResultsResponse<Estado> deleteEstado(EstadoMaintenanceRequest request);

	public InternalResultsResponse<Estado> refreshEstados(RefreshRequest request);

	public InternalResultsResponse<Estado> fetchEstadoById(FetchByIdRequest request);

	public InternalResultsResponse<Estado> fetchAllEstados();

	public InternalResultsResponse<Estado> fetchEstadosByRequest(PagedInquiryRequest request);

	//
	public InternalResultsResponse<Tarefa> insertTarefa(TarefaMaintenanceRequest request)
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

	public InternalResultsResponse<Tarefa> updateTarefa(TarefaMaintenanceRequest request);

	public InternalResultsResponse<Tarefa> deleteTarefa(TarefaMaintenanceRequest request);

	public InternalResultsResponse<Tarefa> refreshTarefas(RefreshRequest request);

	public InternalResultsResponse<Tarefa> fetchTarefaById(FetchByIdRequest request);

	public InternalResultsResponse<Tarefa> fetchAllTarefas();

	public InternalResultsResponse<Tarefa> fetchTarefasByRequest(PagedInquiryRequest request);


}
