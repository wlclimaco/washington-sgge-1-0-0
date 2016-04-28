/** create by system gera-java version 1.0.0 28/04/2016 14:29 : 20*/

package com.qat.samples.sysmgmt.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IClinicaBAC;
import com.qat.samples.sysmgmt.model.Clinica;
import com.qat.samples.sysmgmt.model.request.ClinicaMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.ClinicaResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.ClinicaInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standard implementation of a web service where the operations are delegated to a BAC.
 * Note the BAC is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class ClinicaWSImpl implements com.qat.samples.sysmgmt.service.IClinicaWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.clinicawsimpl.defaultexception";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.clinicawsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = ClinicaWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ClinicaWSImpl.class);

	/** The clinica BAC. */
	private IClinicaBAC clinicaBAC; // injected by Spring through setter

	/**
	 * Spring Sets the clinica BAC.
	 *
	 * @param clinicaBAC the new clinica BAC
	 */
	public void setClinicaBAC(IClinicaBAC clinicaBAC)
	{
		this.clinicaBAC = clinicaBAC;
	}

	/**
	 * Gets the clinica bac.
	 *
	 * @return the clinica bac
	 */
	public IClinicaBAC getClinicaBAC()
	{
		return clinicaBAC;
	}


//===================================### MEDICO ####======================================
	@Override
	public MedicoResponse insertMedico(MedicoMaintenanceRequest request)
	{
		MedicoResponse response = new MedicoResponse();

		try
		{
			InternalResultsResponse<Medico> internalResponse = getClinicaBAC().insertMedico(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public MedicoResponse updateMedico(MedicoMaintenanceRequest request)
	{
		MedicoResponse response = new MedicoResponse();

		try
		{
			InternalResultsResponse<Medico> internalResponse = getClinicaBAC().updateMedico(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public MedicoResponse deleteMedico(MedicoMaintenanceRequest request)
	{
		MedicoResponse response = new MedicoResponse();

		try
		{
			InternalResultsResponse<Medico> internalResponse = getClinicaBAC().deleteMedico(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public MedicoResponse refreshMedicos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		MedicoResponse response = new MedicoResponse();

		try
		{
			InternalResultsResponse<Medico> internalResponse = getClinicaBAC().refreshMedicos(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public MedicoResponse fetchAllMedicos(FetchAllRequest request)
	{
		MedicoResponse response = new MedicoResponse();

		try
		{
			InternalResultsResponse<Medico> internalResponse = getClinicaBAC().fetchAllMedicos(new Medico());
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dapi.impl.IMedicoWS#fetchMedicoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public MedicoResponse fetchMedicoById(FetchByIdRequest request)
	{
		MedicoResponse response = new MedicoResponse();

		try
		{
			InternalResultsResponse<Medico> internalResponse = new InternalResultsResponse<Medico>();

			internalResponse = getClinicaBAC().fetchMedicoById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public MedicoResponse fetchMedicosByRequest(MedicoInquiryRequest request)
	{
		MedicoResponse response = new MedicoResponse();

		try
		{
			InternalResultsResponse<Medico> internalResponse = getClinicaBAC().fetchMedicosByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### PACIENTE ####======================================
	@Override
	public PacienteResponse insertPaciente(PacienteMaintenanceRequest request)
	{
		PacienteResponse response = new PacienteResponse();

		try
		{
			InternalResultsResponse<Paciente> internalResponse = getClinicaBAC().insertPaciente(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public PacienteResponse updatePaciente(PacienteMaintenanceRequest request)
	{
		PacienteResponse response = new PacienteResponse();

		try
		{
			InternalResultsResponse<Paciente> internalResponse = getClinicaBAC().updatePaciente(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public PacienteResponse deletePaciente(PacienteMaintenanceRequest request)
	{
		PacienteResponse response = new PacienteResponse();

		try
		{
			InternalResultsResponse<Paciente> internalResponse = getClinicaBAC().deletePaciente(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public PacienteResponse refreshPacientes(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PacienteResponse response = new PacienteResponse();

		try
		{
			InternalResultsResponse<Paciente> internalResponse = getClinicaBAC().refreshPacientes(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public PacienteResponse fetchAllPacientes(FetchAllRequest request)
	{
		PacienteResponse response = new PacienteResponse();

		try
		{
			InternalResultsResponse<Paciente> internalResponse = getClinicaBAC().fetchAllPacientes(new Paciente());
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dapi.impl.IPacienteWS#fetchPacienteById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public PacienteResponse fetchPacienteById(FetchByIdRequest request)
	{
		PacienteResponse response = new PacienteResponse();

		try
		{
			InternalResultsResponse<Paciente> internalResponse = new InternalResultsResponse<Paciente>();

			internalResponse = getClinicaBAC().fetchPacienteById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public PacienteResponse fetchPacientesByRequest(PacienteInquiryRequest request)
	{
		PacienteResponse response = new PacienteResponse();

		try
		{
			InternalResultsResponse<Paciente> internalResponse = getClinicaBAC().fetchPacientesByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### CONSULTA ####======================================
	@Override
	public ConsultaResponse insertConsulta(ConsultaMaintenanceRequest request)
	{
		ConsultaResponse response = new ConsultaResponse();

		try
		{
			InternalResultsResponse<Consulta> internalResponse = getClinicaBAC().insertConsulta(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ConsultaResponse updateConsulta(ConsultaMaintenanceRequest request)
	{
		ConsultaResponse response = new ConsultaResponse();

		try
		{
			InternalResultsResponse<Consulta> internalResponse = getClinicaBAC().updateConsulta(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ConsultaResponse deleteConsulta(ConsultaMaintenanceRequest request)
	{
		ConsultaResponse response = new ConsultaResponse();

		try
		{
			InternalResultsResponse<Consulta> internalResponse = getClinicaBAC().deleteConsulta(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ConsultaResponse refreshConsultas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ConsultaResponse response = new ConsultaResponse();

		try
		{
			InternalResultsResponse<Consulta> internalResponse = getClinicaBAC().refreshConsultas(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ConsultaResponse fetchAllConsultas(FetchAllRequest request)
	{
		ConsultaResponse response = new ConsultaResponse();

		try
		{
			InternalResultsResponse<Consulta> internalResponse = getClinicaBAC().fetchAllConsultas(new Consulta());
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dapi.impl.IConsultaWS#fetchConsultaById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public ConsultaResponse fetchConsultaById(FetchByIdRequest request)
	{
		ConsultaResponse response = new ConsultaResponse();

		try
		{
			InternalResultsResponse<Consulta> internalResponse = new InternalResultsResponse<Consulta>();

			internalResponse = getClinicaBAC().fetchConsultaById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ConsultaResponse fetchConsultasByRequest(ConsultaInquiryRequest request)
	{
		ConsultaResponse response = new ConsultaResponse();

		try
		{
			InternalResultsResponse<Consulta> internalResponse = getClinicaBAC().fetchConsultasByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### EXAME ####======================================
	@Override
	public ExameResponse insertExame(ExameMaintenanceRequest request)
	{
		ExameResponse response = new ExameResponse();

		try
		{
			InternalResultsResponse<Exame> internalResponse = getClinicaBAC().insertExame(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ExameResponse updateExame(ExameMaintenanceRequest request)
	{
		ExameResponse response = new ExameResponse();

		try
		{
			InternalResultsResponse<Exame> internalResponse = getClinicaBAC().updateExame(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ExameResponse deleteExame(ExameMaintenanceRequest request)
	{
		ExameResponse response = new ExameResponse();

		try
		{
			InternalResultsResponse<Exame> internalResponse = getClinicaBAC().deleteExame(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ExameResponse refreshExames(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ExameResponse response = new ExameResponse();

		try
		{
			InternalResultsResponse<Exame> internalResponse = getClinicaBAC().refreshExames(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ExameResponse fetchAllExames(FetchAllRequest request)
	{
		ExameResponse response = new ExameResponse();

		try
		{
			InternalResultsResponse<Exame> internalResponse = getClinicaBAC().fetchAllExames(new Exame());
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dapi.impl.IExameWS#fetchExameById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public ExameResponse fetchExameById(FetchByIdRequest request)
	{
		ExameResponse response = new ExameResponse();

		try
		{
			InternalResultsResponse<Exame> internalResponse = new InternalResultsResponse<Exame>();

			internalResponse = getClinicaBAC().fetchExameById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ExameResponse fetchExamesByRequest(ExameInquiryRequest request)
	{
		ExameResponse response = new ExameResponse();

		try
		{
			InternalResultsResponse<Exame> internalResponse = getClinicaBAC().fetchExamesByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}
}
