package com.qat.samples.sysmgmt.bar.Clinica;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.clinica.model.Consulta;
import com.qat.samples.sysmgmt.clinica.model.Exame;
import com.qat.samples.sysmgmt.clinica.model.request.ConsultaInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ExameInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.Medico;
import com.qat.samples.sysmgmt.pessoa.model.Paciente;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Interface ClinicaBAR.. (Data Access Component - DAC)
 */
public interface IClinicaBAR
{

	/**
	 * Fetch medico by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Medico> fetchMedicoById(FetchByIdRequest request);

	/**
* Insert medico.
*
* @param medico the medico
*
* @return the internal response
*/
	public InternalResponse insertMedico(Medico medico);

	/**
* Update medico.
*
* @param medico the medico
*
* @return the internal response
*/
	public InternalResponse updateMedico(Medico medico);

	/**
* Delete medico.
*
* @param medico the medico
*
* @return the internal response
*/
	public InternalResponse deleteMedicoById(Medico medico);

	/**
* Delete all medicos.
*
* @return the internal response
*/
	public InternalResponse deleteAllMedicos();

	/**
* Fetch all medicos.
*
* @return the list< medico>
*/
	public InternalResultsResponse<Medico> fetchAllMedicos(Medico  medico);

	/**
* Fetch medicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Medico> fetchMedicosByRequest(MedicoInquiryRequest request);

	/**
	 * Fetch paciente by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Paciente> fetchPacienteById(FetchByIdRequest request);

	/**
* Insert paciente.
*
* @param paciente the paciente
*
* @return the internal response
*/
	public InternalResponse insertPaciente(Paciente paciente);

	/**
* Update paciente.
*
* @param paciente the paciente
*
* @return the internal response
*/
	public InternalResponse updatePaciente(Paciente paciente);

	/**
* Delete paciente.
*
* @param paciente the paciente
*
* @return the internal response
*/
	public InternalResponse deletePacienteById(Paciente paciente);

	/**
* Delete all pacientes.
*
* @return the internal response
*/
	public InternalResponse deleteAllPacientes();

	/**
* Fetch all pacientes.
*
* @return the list< paciente>
*/
	public InternalResultsResponse<Paciente> fetchAllPacientes(Paciente  paciente);

	/**
* Fetch pacientes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Paciente> fetchPacientesByRequest(PacienteInquiryRequest request);

	/**
	 * Fetch consulta by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Consulta> fetchConsultaById(FetchByIdRequest request);

	/**
* Insert consulta.
*
* @param consulta the consulta
*
* @return the internal response
*/
	public InternalResponse insertConsulta(Consulta consulta);

	/**
* Update consulta.
*
* @param consulta the consulta
*
* @return the internal response
*/
	public InternalResponse updateConsulta(Consulta consulta);

	/**
* Delete consulta.
*
* @param consulta the consulta
*
* @return the internal response
*/
	public InternalResponse deleteConsultaById(Consulta consulta);

	/**
* Delete all consultas.
*
* @return the internal response
*/
	public InternalResponse deleteAllConsultas();

	/**
* Fetch all consultas.
*
* @return the list< consulta>
*/
	public InternalResultsResponse<Consulta> fetchAllConsultas(Consulta  consulta);

	/**
* Fetch consultas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Consulta> fetchConsultasByRequest(ConsultaInquiryRequest request);

	/**
	 * Fetch exame by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Exame> fetchExameById(FetchByIdRequest request);

	/**
* Insert exame.
*
* @param exame the exame
*
* @return the internal response
*/
	public InternalResponse insertExame(Exame exame);

	/**
* Update exame.
*
* @param exame the exame
*
* @return the internal response
*/
	public InternalResponse updateExame(Exame exame);

	/**
* Delete exame.
*
* @param exame the exame
*
* @return the internal response
*/
	public InternalResponse deleteExameById(Exame exame);

	/**
* Delete all exames.
*
* @return the internal response
*/
	public InternalResponse deleteAllExames();

	/**
* Fetch all exames.
*
* @return the list< exame>
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
