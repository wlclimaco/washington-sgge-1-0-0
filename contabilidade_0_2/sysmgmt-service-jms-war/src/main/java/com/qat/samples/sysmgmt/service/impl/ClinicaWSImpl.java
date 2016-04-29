/** create by system gera-java version 1.0.0 28/04/2016 20:5 : 32*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Clinica.IClinicaBAC;
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
import com.qat.samples.sysmgmt.clinica.model.response.ConsultaResponse;
import com.qat.samples.sysmgmt.clinica.model.response.ExameResponse;
import com.qat.samples.sysmgmt.clinica.model.response.MedicoResponse;
import com.qat.samples.sysmgmt.clinica.model.response.PacienteResponse;
import com.qat.samples.sysmgmt.pessoa.model.Medico;
import com.qat.samples.sysmgmt.pessoa.model.Paciente;
import com.qat.samples.sysmgmt.service.IClinicaWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
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
	/**
	 * Spring injection uses this method to inject an implementation of {@link IClinicaBAC}.
	 *
	 * @param clinicaBAC the clinicaBAC to set.
	 */
	public void setClinicaBAC(IClinicaBAC clinicaBAC)
	{
		this.clinicaBAC = clinicaBAC;
	}


//===================================### MEDICO ####======================================

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
			InternalResultsResponse<Medico> internalResponse = getClinicaBAC().insertMedico(request);
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
			InternalResultsResponse<Medico> internalResponse = getClinicaBAC().updateMedico(request);
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
			InternalResultsResponse<Medico> internalResponse = getClinicaBAC().deleteMedico(request);
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
			InternalResultsResponse<Medico> internalResponse = getClinicaBAC().fetchMedicoById(request);
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
			InternalResultsResponse<Medico> internalResponse = getClinicaBAC().fetchMedicosByRequest(request);
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
			InternalResultsResponse<Medico> internalResponse = getClinicaBAC().refreshMedicos(request);
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
			InternalResultsResponse<Medico> internalResponse = getClinicaBAC().fetchAllMedicos(new Medico());
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
			InternalResultsResponse<Paciente> internalResponse = getClinicaBAC().insertPaciente(request);
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
			InternalResultsResponse<Paciente> internalResponse = getClinicaBAC().updatePaciente(request);
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
			InternalResultsResponse<Paciente> internalResponse = getClinicaBAC().deletePaciente(request);
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
			InternalResultsResponse<Paciente> internalResponse = getClinicaBAC().fetchPacienteById(request);
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
			InternalResultsResponse<Paciente> internalResponse = getClinicaBAC().fetchPacientesByRequest(request);
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
			InternalResultsResponse<Paciente> internalResponse = getClinicaBAC().refreshPacientes(request);
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
			InternalResultsResponse<Paciente> internalResponse = getClinicaBAC().fetchAllPacientes(new Paciente());
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
			InternalResultsResponse<Consulta> internalResponse = getClinicaBAC().insertConsulta(request);
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
			InternalResultsResponse<Consulta> internalResponse = getClinicaBAC().updateConsulta(request);
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
			InternalResultsResponse<Consulta> internalResponse = getClinicaBAC().deleteConsulta(request);
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
			InternalResultsResponse<Consulta> internalResponse = getClinicaBAC().fetchConsultaById(request);
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
			InternalResultsResponse<Consulta> internalResponse = getClinicaBAC().fetchConsultasByRequest(request);
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
			InternalResultsResponse<Consulta> internalResponse = getClinicaBAC().refreshConsultas(request);
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
			InternalResultsResponse<Consulta> internalResponse = getClinicaBAC().fetchAllConsultas(new Consulta());
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
			InternalResultsResponse<Exame> internalResponse = getClinicaBAC().insertExame(request);
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
			InternalResultsResponse<Exame> internalResponse = getClinicaBAC().updateExame(request);
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
			InternalResultsResponse<Exame> internalResponse = getClinicaBAC().deleteExame(request);
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
			InternalResultsResponse<Exame> internalResponse = getClinicaBAC().fetchExameById(request);
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
			InternalResultsResponse<Exame> internalResponse = getClinicaBAC().fetchExamesByRequest(request);
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
			InternalResultsResponse<Exame> internalResponse = getClinicaBAC().refreshExames(request);
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
			InternalResultsResponse<Exame> internalResponse = getClinicaBAC().fetchAllExames(new Exame());
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
