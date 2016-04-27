/** create by system gera-java version 1.0.0 27/04/2016*/


package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.ClinicaMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.ClinicaResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.ClinicaInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IClinicaWS.
 */
@WebService(serviceName = "ClinicaService", targetNamespace = "http://qat.com/sysmgmt", portName = "ClinicaServicePort")
public interface IClinicaWS
{

//===================================### MEDICO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertMedico")
	@WebResult(name = "insertMedicoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public MedicoResponse insertMedico(@WebParam(name = "request") MedicoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateMedico")
	@WebResult(name = "updateMedicoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public MedicoResponse updateMedico(@WebParam(name = "request") MedicoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteMedico")
	@WebResult(name = "deleteMedicoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public MedicoResponse deleteMedico(@WebParam(name = "request") MedicoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshMedicos")
	@WebResult(name = "refreshMedicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public MedicoResponse refreshMedicos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllMedicos")
	@WebResult(name = "fetchAllMedicosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public MedicoResponse fetchAllMedicos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchMedicoById")
	@WebResult(name = "fetchMedicoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public MedicoResponse fetchMedicoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchMedicosByRequest")
	@WebResult(name = "fetchMedicosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public MedicoResponse fetchMedicosByRequest(@WebParam(name = "request") MedicoInquiryRequest request);


//===================================### PACIENTE ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertPaciente")
	@WebResult(name = "insertPacienteReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public PacienteResponse insertPaciente(@WebParam(name = "request") PacienteMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updatePaciente")
	@WebResult(name = "updatePacienteReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public PacienteResponse updatePaciente(@WebParam(name = "request") PacienteMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deletePaciente")
	@WebResult(name = "deletePacienteReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public PacienteResponse deletePaciente(@WebParam(name = "request") PacienteMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshPacientes")
	@WebResult(name = "refreshPacientesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public PacienteResponse refreshPacientes(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllPacientes")
	@WebResult(name = "fetchAllPacientesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public PacienteResponse fetchAllPacientes(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchPacienteById")
	@WebResult(name = "fetchPacienteByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public PacienteResponse fetchPacienteById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchPacientesByRequest")
	@WebResult(name = "fetchPacientesByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public PacienteResponse fetchPacientesByRequest(@WebParam(name = "request") PacienteInquiryRequest request);


//===================================### CONSULTA ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertConsulta")
	@WebResult(name = "insertConsultaReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public ConsultaResponse insertConsulta(@WebParam(name = "request") ConsultaMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateConsulta")
	@WebResult(name = "updateConsultaReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public ConsultaResponse updateConsulta(@WebParam(name = "request") ConsultaMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteConsulta")
	@WebResult(name = "deleteConsultaReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public ConsultaResponse deleteConsulta(@WebParam(name = "request") ConsultaMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshConsultas")
	@WebResult(name = "refreshConsultasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public ConsultaResponse refreshConsultas(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllConsultas")
	@WebResult(name = "fetchAllConsultasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public ConsultaResponse fetchAllConsultas(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchConsultaById")
	@WebResult(name = "fetchConsultaByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public ConsultaResponse fetchConsultaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchConsultasByRequest")
	@WebResult(name = "fetchConsultasByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public ConsultaResponse fetchConsultasByRequest(@WebParam(name = "request") ConsultaInquiryRequest request);


//===================================### EXAME ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertExame")
	@WebResult(name = "insertExameReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public ExameResponse insertExame(@WebParam(name = "request") ExameMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateExame")
	@WebResult(name = "updateExameReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public ExameResponse updateExame(@WebParam(name = "request") ExameMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteExame")
	@WebResult(name = "deleteExameReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public ExameResponse deleteExame(@WebParam(name = "request") ExameMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshExames")
	@WebResult(name = "refreshExamesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public ExameResponse refreshExames(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllExames")
	@WebResult(name = "fetchAllExamesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public ExameResponse fetchAllExames(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchExameById")
	@WebResult(name = "fetchExameByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public ExameResponse fetchExameById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchExamesByRequest")
	@WebResult(name = "fetchExamesByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public ExameResponse fetchExamesByRequest(@WebParam(name = "request") ExameInquiryRequest request);

}

