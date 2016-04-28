/** create by system gera-java version 1.0.0 28/04/2016 16:21 : 34*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IClinicaBAC;
import com.qat.samples.sysmgmt.model.Clinica;
import com.qat.samples.sysmgmt.model.response.ClinicaResponse;
import com.qat.samples.sysmgmt.service.IClinicaWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
/**
 * ClinicaWS used to provide WS interface. Delegates all calls to the IClinicaBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class ClinicaWSImpl implements IClinicaWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.clinicajmswsimpl.defaultexception";
	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.clinicajmswsimpl.defaulterror";
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = ClinicaWSImpl.class.getName();
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ClinicaWSImpl.class);
	private IClinicaBAC clinicaBAC;
	/**
	 * @return the clinicaBAC which is expected to provide the implementation.
	 */
	public IClinicaBAC getClinicaBAC()
	{	
		return clinicaBAC;
	}

//===================================### MEDICO ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IMedicoBAC}.
	 *
	 * @param medicoBAC the medicoBAC to set.
	 */
	public void setMedicoBAC(IMedicoBAC medicoBAC)
	{
		this.medicoBAC = medicoBAC;
	}
	
	/**
	 * Delegates call to {@link IMedicoBAC}
	 *
	 * @param request a MedicoRequest
	 * @return MedicoResponse
	 */
	@Override
	public MedicoResponse insertMedico(MedicoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		MedicoResponse response = new MedicoResponse();
		try
		{
			InternalResultsResponse<Medico> internalResponse = getMedicoBAC().insertMedico(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IMedicoBAC}
	 *
	 * @param request a MedicoRequest
	 * @return MedicoResponse
	 */
	@Override
	public MedicoResponse updateMedico(MedicoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		MedicoResponse response = new MedicoResponse();
		try
		{
			InternalResultsResponse<Medico> internalResponse = getMedicoBAC().updateMedico(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IMedicoBAC}
	 *
	 * @param request a MedicoRequest
	 * @return MedicoResponse
	 */
	@Override
	public MedicoResponse deleteMedico(MedicoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		MedicoResponse response = new MedicoResponse();
		try
		{
			InternalResultsResponse<Medico> internalResponse = getMedicoBAC().deleteMedico(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IMedicoBAC}
	 *
	 * @param request a MedicoRequest
	 * @return MedicoResponse
	 */
	@Override
	public MedicoResponse fetchMedicoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		MedicoResponse response = new MedicoResponse();
		try
		{
			InternalResultsResponse<Medico> internalResponse = getMedicoBAC().fetchMedicoById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IMedicoBAC}
	 *
	 * @param request a MedicoRequest
	 * @return MedicoResponse
	 */
	@Override
	public MedicoResponse fetchMedicosByRequest(MedicoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		MedicoResponse response = new MedicoResponse();
		try
		{
			InternalResultsResponse<Medico> internalResponse = getMedicoBAC().fetchMedicosByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IMedicoBAC}
	 *
	 * @param request a MedicoRequest
	 * @return MedicoResponse
	 */
	@Override
	public MedicoResponse refreshMedicos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		MedicoResponse response = new MedicoResponse();
		try
		{
			InternalResultsResponse<Medico> internalResponse = getMedicoBAC().refreshMedicos(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IMedicoBAC}
	 *
	 * @param request a MedicoRequest
	 * @return MedicoResponse
	 */
	@Override
	public MedicoResponse fetchAllMedicos(FetchAllRequest request)
	{
		MedicoResponse response = new MedicoResponse();
		try
		{
			InternalResultsResponse<Medico> internalResponse = getMedicoBAC().fetchAllMedicos();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### PACIENTE ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IPacienteBAC}.
	 *
	 * @param pacienteBAC the pacienteBAC to set.
	 */
	public void setPacienteBAC(IPacienteBAC pacienteBAC)
	{
		this.pacienteBAC = pacienteBAC;
	}
	
	/**
	 * Delegates call to {@link IPacienteBAC}
	 *
	 * @param request a PacienteRequest
	 * @return PacienteResponse
	 */
	@Override
	public PacienteResponse insertPaciente(PacienteMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PacienteResponse response = new PacienteResponse();
		try
		{
			InternalResultsResponse<Paciente> internalResponse = getPacienteBAC().insertPaciente(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IPacienteBAC}
	 *
	 * @param request a PacienteRequest
	 * @return PacienteResponse
	 */
	@Override
	public PacienteResponse updatePaciente(PacienteMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PacienteResponse response = new PacienteResponse();
		try
		{
			InternalResultsResponse<Paciente> internalResponse = getPacienteBAC().updatePaciente(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IPacienteBAC}
	 *
	 * @param request a PacienteRequest
	 * @return PacienteResponse
	 */
	@Override
	public PacienteResponse deletePaciente(PacienteMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PacienteResponse response = new PacienteResponse();
		try
		{
			InternalResultsResponse<Paciente> internalResponse = getPacienteBAC().deletePaciente(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IPacienteBAC}
	 *
	 * @param request a PacienteRequest
	 * @return PacienteResponse
	 */
	@Override
	public PacienteResponse fetchPacienteById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PacienteResponse response = new PacienteResponse();
		try
		{
			InternalResultsResponse<Paciente> internalResponse = getPacienteBAC().fetchPacienteById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IPacienteBAC}
	 *
	 * @param request a PacienteRequest
	 * @return PacienteResponse
	 */
	@Override
	public PacienteResponse fetchPacientesByRequest(PacienteInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PacienteResponse response = new PacienteResponse();
		try
		{
			InternalResultsResponse<Paciente> internalResponse = getPacienteBAC().fetchPacientesByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IPacienteBAC}
	 *
	 * @param request a PacienteRequest
	 * @return PacienteResponse
	 */
	@Override
	public PacienteResponse refreshPacientes(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PacienteResponse response = new PacienteResponse();
		try
		{
			InternalResultsResponse<Paciente> internalResponse = getPacienteBAC().refreshPacientes(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IPacienteBAC}
	 *
	 * @param request a PacienteRequest
	 * @return PacienteResponse
	 */
	@Override
	public PacienteResponse fetchAllPacientes(FetchAllRequest request)
	{
		PacienteResponse response = new PacienteResponse();
		try
		{
			InternalResultsResponse<Paciente> internalResponse = getPacienteBAC().fetchAllPacientes();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### CONSULTA ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IConsultaBAC}.
	 *
	 * @param consultaBAC the consultaBAC to set.
	 */
	public void setConsultaBAC(IConsultaBAC consultaBAC)
	{
		this.consultaBAC = consultaBAC;
	}
	
	/**
	 * Delegates call to {@link IConsultaBAC}
	 *
	 * @param request a ConsultaRequest
	 * @return ConsultaResponse
	 */
	@Override
	public ConsultaResponse insertConsulta(ConsultaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ConsultaResponse response = new ConsultaResponse();
		try
		{
			InternalResultsResponse<Consulta> internalResponse = getConsultaBAC().insertConsulta(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IConsultaBAC}
	 *
	 * @param request a ConsultaRequest
	 * @return ConsultaResponse
	 */
	@Override
	public ConsultaResponse updateConsulta(ConsultaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ConsultaResponse response = new ConsultaResponse();
		try
		{
			InternalResultsResponse<Consulta> internalResponse = getConsultaBAC().updateConsulta(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IConsultaBAC}
	 *
	 * @param request a ConsultaRequest
	 * @return ConsultaResponse
	 */
	@Override
	public ConsultaResponse deleteConsulta(ConsultaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ConsultaResponse response = new ConsultaResponse();
		try
		{
			InternalResultsResponse<Consulta> internalResponse = getConsultaBAC().deleteConsulta(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IConsultaBAC}
	 *
	 * @param request a ConsultaRequest
	 * @return ConsultaResponse
	 */
	@Override
	public ConsultaResponse fetchConsultaById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ConsultaResponse response = new ConsultaResponse();
		try
		{
			InternalResultsResponse<Consulta> internalResponse = getConsultaBAC().fetchConsultaById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IConsultaBAC}
	 *
	 * @param request a ConsultaRequest
	 * @return ConsultaResponse
	 */
	@Override
	public ConsultaResponse fetchConsultasByRequest(ConsultaInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ConsultaResponse response = new ConsultaResponse();
		try
		{
			InternalResultsResponse<Consulta> internalResponse = getConsultaBAC().fetchConsultasByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IConsultaBAC}
	 *
	 * @param request a ConsultaRequest
	 * @return ConsultaResponse
	 */
	@Override
	public ConsultaResponse refreshConsultas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ConsultaResponse response = new ConsultaResponse();
		try
		{
			InternalResultsResponse<Consulta> internalResponse = getConsultaBAC().refreshConsultas(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IConsultaBAC}
	 *
	 * @param request a ConsultaRequest
	 * @return ConsultaResponse
	 */
	@Override
	public ConsultaResponse fetchAllConsultas(FetchAllRequest request)
	{
		ConsultaResponse response = new ConsultaResponse();
		try
		{
			InternalResultsResponse<Consulta> internalResponse = getConsultaBAC().fetchAllConsultas();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### EXAME ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IExameBAC}.
	 *
	 * @param exameBAC the exameBAC to set.
	 */
	public void setExameBAC(IExameBAC exameBAC)
	{
		this.exameBAC = exameBAC;
	}
	
	/**
	 * Delegates call to {@link IExameBAC}
	 *
	 * @param request a ExameRequest
	 * @return ExameResponse
	 */
	@Override
	public ExameResponse insertExame(ExameMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ExameResponse response = new ExameResponse();
		try
		{
			InternalResultsResponse<Exame> internalResponse = getExameBAC().insertExame(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IExameBAC}
	 *
	 * @param request a ExameRequest
	 * @return ExameResponse
	 */
	@Override
	public ExameResponse updateExame(ExameMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ExameResponse response = new ExameResponse();
		try
		{
			InternalResultsResponse<Exame> internalResponse = getExameBAC().updateExame(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IExameBAC}
	 *
	 * @param request a ExameRequest
	 * @return ExameResponse
	 */
	@Override
	public ExameResponse deleteExame(ExameMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ExameResponse response = new ExameResponse();
		try
		{
			InternalResultsResponse<Exame> internalResponse = getExameBAC().deleteExame(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IExameBAC}
	 *
	 * @param request a ExameRequest
	 * @return ExameResponse
	 */
	@Override
	public ExameResponse fetchExameById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ExameResponse response = new ExameResponse();
		try
		{
			InternalResultsResponse<Exame> internalResponse = getExameBAC().fetchExameById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IExameBAC}
	 *
	 * @param request a ExameRequest
	 * @return ExameResponse
	 */
	@Override
	public ExameResponse fetchExamesByRequest(ExameInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ExameResponse response = new ExameResponse();
		try
		{
			InternalResultsResponse<Exame> internalResponse = getExameBAC().fetchExamesByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IExameBAC}
	 *
	 * @param request a ExameRequest
	 * @return ExameResponse
	 */
	@Override
	public ExameResponse refreshExames(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ExameResponse response = new ExameResponse();
		try
		{
			InternalResultsResponse<Exame> internalResponse = getExameBAC().refreshExames(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IExameBAC}
	 *
	 * @param request a ExameRequest
	 * @return ExameResponse
	 */
	@Override
	public ExameResponse fetchAllExames(FetchAllRequest request)
	{
		ExameResponse response = new ExameResponse();
		try
		{
			InternalResultsResponse<Exame> internalResponse = getExameBAC().fetchAllExames();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
}
