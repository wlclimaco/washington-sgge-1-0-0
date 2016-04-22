package com.qat.samples.sysmgmt.bac.Clinica;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.clinica.model.Consulta;
import com.qat.samples.sysmgmt.clinica.model.Exame;
import com.qat.samples.sysmgmt.clinica.model.request.ConsultaInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ConsultaMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ExameInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ExameMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.Medico;
import com.qat.samples.sysmgmt.pessoa.model.Paciente;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IClinicaBAC. (Business Area Component - BAC)
 */
public interface IClinicaBAC
{



//===================================### MEDICO ####======================================
	/**

	/**
	 * Insert medico.
	 *
* @param request the medico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Medico> insertMedico(MedicoMaintenanceRequest request);

	/**
* Update medico.
*
* @param request the medico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Medico> updateMedico(MedicoMaintenanceRequest request);

	/**
* Delete medico.
*
* @param request the medico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Medico> deleteMedico(MedicoMaintenanceRequest request);

	/**
* Refresh medicos.
*
* @param request containing the number to refresh with and whether to return the result
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
* @return the internal results response< medico>
*/
	public InternalResultsResponse<Medico> fetchAllMedicos(Medico  medico);

	/**
* Fetch medicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Medico> fetchMedicosByRequest(MedicoInquiryRequest request);


//===================================### PACIENTE ####======================================
	/**

	/**
	 * Insert paciente.
	 *
* @param request the paciente maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Paciente> insertPaciente(PacienteMaintenanceRequest request);

	/**
* Update paciente.
*
* @param request the paciente maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Paciente> updatePaciente(PacienteMaintenanceRequest request);

	/**
* Delete paciente.
*
* @param request the paciente maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Paciente> deletePaciente(PacienteMaintenanceRequest request);

	/**
* Refresh pacientes.
*
* @param request containing the number to refresh with and whether to return the result
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
* @return the internal results response< paciente>
*/
	public InternalResultsResponse<Paciente> fetchAllPacientes(Paciente  paciente);

	/**
* Fetch pacientes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Paciente> fetchPacientesByRequest(PacienteInquiryRequest request);


//===================================### CONSULTA ####======================================
	/**

	/**
	 * Insert consulta.
	 *
* @param request the consulta maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Consulta> insertConsulta(ConsultaMaintenanceRequest request);

	/**
* Update consulta.
*
* @param request the consulta maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Consulta> updateConsulta(ConsultaMaintenanceRequest request);

	/**
* Delete consulta.
*
* @param request the consulta maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Consulta> deleteConsulta(ConsultaMaintenanceRequest request);

	/**
* Refresh consultas.
*
* @param request containing the number to refresh with and whether to return the result
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
* @return the internal results response< consulta>
*/
	public InternalResultsResponse<Consulta> fetchAllConsultas(Consulta  consulta);

	/**
* Fetch consultas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Consulta> fetchConsultasByRequest(ConsultaInquiryRequest request);


//===================================### EXAME ####======================================
	/**

	/**
	 * Insert exame.
	 *
* @param request the exame maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Exame> insertExame(ExameMaintenanceRequest request);

	/**
* Update exame.
*
* @param request the exame maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Exame> updateExame(ExameMaintenanceRequest request);

	/**
* Delete exame.
*
* @param request the exame maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Exame> deleteExame(ExameMaintenanceRequest request);

	/**
* Refresh exames.
*
* @param request containing the number to refresh with and whether to return the result
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
* @return the internal results response< exame>
*/
	public InternalResultsResponse<Exame> fetchAllExames(Exame  exame);

	/**
* Fetch exames by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Exame> fetchExamesByRequest(ExameInquiryRequest request);

}
