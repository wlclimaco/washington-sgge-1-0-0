/** create by system gera-java version 1.0.0 28/04/2016 14:59 : 50*/
package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.clinica.model.request.ConsultaInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ConsultaMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ExameInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ExameMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.response.ConsultaResponse;
import com.qat.samples.sysmgmt.clinica.model.response.ExameResponse;
import com.qat.samples.sysmgmt.clinica.model.response.MedicoResponse;
import com.qat.samples.sysmgmt.clinica.model.response.PacienteResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IClinicaBAS delegate used by a JMS listener. (Business Area Service - BAS)
 */
@WebService(serviceName = "ClinicaService", targetNamespace = "http://qat.com/jms", portName = "ClinicaServicePort")
public interface IClinicaWS
{

//===================================### MEDICO ####======================================

/**
	 * Rebuild a list of Medicos.
	 *
	 * @param request MedicoRequest object containing parameter for building the list of Medico objects.
	 *
	 * @return the MedicoResponse containing the list of Medicos built
	 */
	@WebMethod(action = "insertMedicos")
	@WebResult(name = "insertMedicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	MedicoResponse insertMedico(@WebParam(name = "request") MedicoMaintenanceRequest request);

	/**
	 * Rebuild a list of Medicos.
	 *
	 * @param request MedicoRequest object containing parameter for building the list of Medico objects.
	 *
	 * @return the MedicoResponse containing the list of Medicos built
	 */
	@WebMethod(action = "updateMedicos")
	@WebResult(name = "updateMedicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	MedicoResponse updateMedico(@WebParam(name = "request") MedicoMaintenanceRequest request);

	/**
	 * Rebuild a list of Medicos.
	 *
	 * @param request MedicoRequest object containing parameter for building the list of Medico objects.
	 *
	 * @return the MedicoResponse containing the list of Medicos built
	 */
	@WebMethod(action = "deleteMedicos")
	@WebResult(name = "deleteMedicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	MedicoResponse deleteMedico(@WebParam(name = "request") MedicoMaintenanceRequest request);

	/**
	 * Rebuild a list of Medicos.
	 *
	 * @param request MedicoRequest object containing parameter for building the list of Medico objects.
	 *
	 * @return the MedicoResponse containing the list of Medicos built
	 */
	@WebMethod(action = "fetchMedicoById")
	@WebResult(name = "fetchMedicoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	MedicoResponse fetchMedicoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Medicos.
	 *
	 * @param request MedicoRequest object containing parameter for building the list of Medico objects.
	 *
	 * @return the MedicoResponse containing the list of Medicos built
	 */
	@WebMethod(action = "fetchMedicosByRequest")
	@WebResult(name = "fetchMedicosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	MedicoResponse fetchMedicosByRequest(@WebParam(name = "request") MedicoInquiryRequest request);


	/**
	 * Rebuild a list of Medicos.
	 *
	 * @param request MedicoRequest object containing parameter for building the list of Medico objects.
	 *
	 * @return the MedicoResponse containing the list of Medicos built
	 */
	@WebMethod(action = "refreshMedicos")
	@WebResult(name = "refreshMedicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	MedicoResponse refreshMedicos(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Medicos.
	 *
	 * @param request the request
	 *
	 * @return the MedicoResponse containing all Medico objects
	 */
	@WebMethod(action = "fetchAllMedicos")
	@WebResult(name = "fetchAllMedicosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	MedicoResponse fetchAllMedicos(@WebParam(name = "request") FetchAllRequest request);



//===================================### PACIENTE ####======================================

/**
	 * Rebuild a list of Pacientes.
	 *
	 * @param request PacienteRequest object containing parameter for building the list of Paciente objects.
	 *
	 * @return the PacienteResponse containing the list of Pacientes built
	 */
	@WebMethod(action = "insertPacientes")
	@WebResult(name = "insertPacientesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PacienteResponse insertPaciente(@WebParam(name = "request") PacienteMaintenanceRequest request);

	/**
	 * Rebuild a list of Pacientes.
	 *
	 * @param request PacienteRequest object containing parameter for building the list of Paciente objects.
	 *
	 * @return the PacienteResponse containing the list of Pacientes built
	 */
	@WebMethod(action = "updatePacientes")
	@WebResult(name = "updatePacientesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PacienteResponse updatePaciente(@WebParam(name = "request") PacienteMaintenanceRequest request);

	/**
	 * Rebuild a list of Pacientes.
	 *
	 * @param request PacienteRequest object containing parameter for building the list of Paciente objects.
	 *
	 * @return the PacienteResponse containing the list of Pacientes built
	 */
	@WebMethod(action = "deletePacientes")
	@WebResult(name = "deletePacientesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PacienteResponse deletePaciente(@WebParam(name = "request") PacienteMaintenanceRequest request);

	/**
	 * Rebuild a list of Pacientes.
	 *
	 * @param request PacienteRequest object containing parameter for building the list of Paciente objects.
	 *
	 * @return the PacienteResponse containing the list of Pacientes built
	 */
	@WebMethod(action = "fetchPacienteById")
	@WebResult(name = "fetchPacienteByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PacienteResponse fetchPacienteById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Pacientes.
	 *
	 * @param request PacienteRequest object containing parameter for building the list of Paciente objects.
	 *
	 * @return the PacienteResponse containing the list of Pacientes built
	 */
	@WebMethod(action = "fetchPacientesByRequest")
	@WebResult(name = "fetchPacientesByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PacienteResponse fetchPacientesByRequest(@WebParam(name = "request") PacienteInquiryRequest request);


	/**
	 * Rebuild a list of Pacientes.
	 *
	 * @param request PacienteRequest object containing parameter for building the list of Paciente objects.
	 *
	 * @return the PacienteResponse containing the list of Pacientes built
	 */
	@WebMethod(action = "refreshPacientes")
	@WebResult(name = "refreshPacientesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PacienteResponse refreshPacientes(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Pacientes.
	 *
	 * @param request the request
	 *
	 * @return the PacienteResponse containing all Paciente objects
	 */
	@WebMethod(action = "fetchAllPacientes")
	@WebResult(name = "fetchAllPacientesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	PacienteResponse fetchAllPacientes(@WebParam(name = "request") FetchAllRequest request);



//===================================### CONSULTA ####======================================

/**
	 * Rebuild a list of Consultas.
	 *
	 * @param request ConsultaRequest object containing parameter for building the list of Consulta objects.
	 *
	 * @return the ConsultaResponse containing the list of Consultas built
	 */
	@WebMethod(action = "insertConsultas")
	@WebResult(name = "insertConsultasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ConsultaResponse insertConsulta(@WebParam(name = "request") ConsultaMaintenanceRequest request);

	/**
	 * Rebuild a list of Consultas.
	 *
	 * @param request ConsultaRequest object containing parameter for building the list of Consulta objects.
	 *
	 * @return the ConsultaResponse containing the list of Consultas built
	 */
	@WebMethod(action = "updateConsultas")
	@WebResult(name = "updateConsultasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ConsultaResponse updateConsulta(@WebParam(name = "request") ConsultaMaintenanceRequest request);

	/**
	 * Rebuild a list of Consultas.
	 *
	 * @param request ConsultaRequest object containing parameter for building the list of Consulta objects.
	 *
	 * @return the ConsultaResponse containing the list of Consultas built
	 */
	@WebMethod(action = "deleteConsultas")
	@WebResult(name = "deleteConsultasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ConsultaResponse deleteConsulta(@WebParam(name = "request") ConsultaMaintenanceRequest request);

	/**
	 * Rebuild a list of Consultas.
	 *
	 * @param request ConsultaRequest object containing parameter for building the list of Consulta objects.
	 *
	 * @return the ConsultaResponse containing the list of Consultas built
	 */
	@WebMethod(action = "fetchConsultaById")
	@WebResult(name = "fetchConsultaByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ConsultaResponse fetchConsultaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Consultas.
	 *
	 * @param request ConsultaRequest object containing parameter for building the list of Consulta objects.
	 *
	 * @return the ConsultaResponse containing the list of Consultas built
	 */
	@WebMethod(action = "fetchConsultasByRequest")
	@WebResult(name = "fetchConsultasByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ConsultaResponse fetchConsultasByRequest(@WebParam(name = "request") ConsultaInquiryRequest request);


	/**
	 * Rebuild a list of Consultas.
	 *
	 * @param request ConsultaRequest object containing parameter for building the list of Consulta objects.
	 *
	 * @return the ConsultaResponse containing the list of Consultas built
	 */
	@WebMethod(action = "refreshConsultas")
	@WebResult(name = "refreshConsultasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ConsultaResponse refreshConsultas(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Consultas.
	 *
	 * @param request the request
	 *
	 * @return the ConsultaResponse containing all Consulta objects
	 */
	@WebMethod(action = "fetchAllConsultas")
	@WebResult(name = "fetchAllConsultasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	ConsultaResponse fetchAllConsultas(@WebParam(name = "request") FetchAllRequest request);



//===================================### EXAME ####======================================

/**
	 * Rebuild a list of Exames.
	 *
	 * @param request ExameRequest object containing parameter for building the list of Exame objects.
	 *
	 * @return the ExameResponse containing the list of Exames built
	 */
	@WebMethod(action = "insertExames")
	@WebResult(name = "insertExamesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ExameResponse insertExame(@WebParam(name = "request") ExameMaintenanceRequest request);

	/**
	 * Rebuild a list of Exames.
	 *
	 * @param request ExameRequest object containing parameter for building the list of Exame objects.
	 *
	 * @return the ExameResponse containing the list of Exames built
	 */
	@WebMethod(action = "updateExames")
	@WebResult(name = "updateExamesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ExameResponse updateExame(@WebParam(name = "request") ExameMaintenanceRequest request);

	/**
	 * Rebuild a list of Exames.
	 *
	 * @param request ExameRequest object containing parameter for building the list of Exame objects.
	 *
	 * @return the ExameResponse containing the list of Exames built
	 */
	@WebMethod(action = "deleteExames")
	@WebResult(name = "deleteExamesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ExameResponse deleteExame(@WebParam(name = "request") ExameMaintenanceRequest request);

	/**
	 * Rebuild a list of Exames.
	 *
	 * @param request ExameRequest object containing parameter for building the list of Exame objects.
	 *
	 * @return the ExameResponse containing the list of Exames built
	 */
	@WebMethod(action = "fetchExameById")
	@WebResult(name = "fetchExameByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ExameResponse fetchExameById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Exames.
	 *
	 * @param request ExameRequest object containing parameter for building the list of Exame objects.
	 *
	 * @return the ExameResponse containing the list of Exames built
	 */
	@WebMethod(action = "fetchExamesByRequest")
	@WebResult(name = "fetchExamesByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ExameResponse fetchExamesByRequest(@WebParam(name = "request") ExameInquiryRequest request);


	/**
	 * Rebuild a list of Exames.
	 *
	 * @param request ExameRequest object containing parameter for building the list of Exame objects.
	 *
	 * @return the ExameResponse containing the list of Exames built
	 */
	@WebMethod(action = "refreshExames")
	@WebResult(name = "refreshExamesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ExameResponse refreshExames(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Exames.
	 *
	 * @param request the request
	 *
	 * @return the ExameResponse containing all Exame objects
	 */
	@WebMethod(action = "fetchAllExames")
	@WebResult(name = "fetchAllExamesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	ExameResponse fetchAllExames(@WebParam(name = "request") FetchAllRequest request);


}
