package com.qat.samples.sysmgmt.bac.Clinica;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.clinica.model.Consulta;
import com.qat.samples.sysmgmt.clinica.model.Exame;
import com.qat.samples.sysmgmt.clinica.model.request.ConsultaMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ExameMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.pessoa.model.Medico;
import com.qat.samples.sysmgmt.pessoa.model.Paciente;

// TODO: Auto-generated Javadoc
/**
 * The Interface IProcedureBAC. (Business Area Component - BAC)
 */
public interface ClinicaBACImpl
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
	public InternalResultsResponse<Medico> insertMedico(MedicoMaintenanceRequest request)
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
	 * Update medico.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Medico> updateMedico(MedicoMaintenanceRequest request);

	/**
	 * Delete medico.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Medico> deleteMedico(MedicoMaintenanceRequest request);

	/**
	 * Refresh medicos.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Medico> refreshMedicos(RefreshRequest request);

	/**
	 * Fetch medico by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Medico> fetchMedicoById(FetchByIdRequest request);

	/**
	 * Fetch all medicos.
	 *
	 * @return the internal results response
	 */
	public InternalResultsResponse<Medico> fetchAllMedicos();

	/**
	 * Fetch medicos by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Medico> fetchMedicosByRequest(PagedInquiryRequest request);

/**
 * Insert paciente.
 *
 * @param request the request
 * @return the internal results response
 */
//
	public InternalResultsResponse<Paciente> insertPaciente(PacienteMaintenanceRequest request)
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
	 * Update paciente.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Paciente> updatePaciente(PacienteMaintenanceRequest request);

	/**
	 * Delete paciente.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Paciente> deletePaciente(PacienteMaintenanceRequest request);

	/**
	 * Refresh pacientes.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Paciente> refreshPacientes(RefreshRequest request);

	/**
	 * Fetch paciente by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Paciente> fetchPacienteById(FetchByIdRequest request);

	/**
	 * Fetch all pacientes.
	 *
	 * @return the internal results response
	 */
	public InternalResultsResponse<Paciente> fetchAllPacientes();

	/**
	 * Fetch pacientes by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Paciente> fetchPacientesByRequest(PagedInquiryRequest request);

	/**
	 * Insert consulta.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	//
	public InternalResultsResponse<Consulta> insertConsulta(ConsultaMaintenanceRequest request)
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
	 * Update consulta.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Consulta> updateConsulta(ConsultaMaintenanceRequest request);

	/**
	 * Delete consulta.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Consulta> deleteConsulta(ConsultaMaintenanceRequest request);

	/**
	 * Refresh consultas.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Consulta> refreshConsultas(RefreshRequest request);

	/**
	 * Fetch consulta by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Consulta> fetchConsultaById(FetchByIdRequest request);

	/**
	 * Fetch all consultas.
	 *
	 * @return the internal results response
	 */
	public InternalResultsResponse<Consulta> fetchAllConsultas();

	/**
	 * Fetch consultas by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Consulta> fetchConsultasByRequest(PagedInquiryRequest request);

	/**
	 * Insert exame.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	//
	public InternalResultsResponse<Exame> insertExame(ExameMaintenanceRequest request)
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
	 * Update exame.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Exame> updateExame(ExameMaintenanceRequest request);

	/**
	 * Delete exame.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Exame> deleteExame(ExameMaintenanceRequest request);

	/**
	 * Refresh exames.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Exame> refreshExames(RefreshRequest request);

	/**
	 * Fetch exame by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Exame> fetchExameById(FetchByIdRequest request);

	/**
	 * Fetch all exames.
	 *
	 * @return the internal results response
	 */
	public InternalResultsResponse<Exame> fetchAllExames();

	/**
	 * Fetch exames by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Exame> fetchExamesByRequest(PagedInquiryRequest request);


}
