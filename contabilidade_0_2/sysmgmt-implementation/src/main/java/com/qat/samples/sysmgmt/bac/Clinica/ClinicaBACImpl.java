package com.qat.samples.sysmgmt.bac.Clinica;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.MessageLevel;
import com.qat.framework.model.MessageSeverity;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.SystemErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Clinica.IClinicaBAR;
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
 * Standards based implementation of a BAC for Clinica leveraging the injected IClinicaBAR.
 */
@Component
public class ClinicaBACImpl implements IClinicaBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_CLINICA_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_CLINICA_BAC_EXCEPTION_MSG = "sysmgmt.base.Clinicabacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ClinicaBACImpl.class);

	/** The Clinica BAR. */
	private IClinicaBAR clinicaBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the Clinica BAR.
	 *
	 * @param ClinicaBAR the new Clinica BAR
	 */
	public void setClinicaBAR(IClinicaBAR clinicaBAR)
	{
		this.clinicaBAR = clinicaBAR;
	}

	/**
	 * Gets the Clinica BAR.
	 *
	 * @return the Clinica BAR
	 */
	public IClinicaBAR getClinicaBAR()
	{
		return clinicaBAR;
	}

	/**
	 * Gets the validation controller
	 *
	 * @return ValidationController
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Sets the validation Controller
	 *
	 * @param validationController
	 */
	public void setValidationController(ValidationController validationController)
	{
		this.validationController = validationController;
	}

//===================================### MEDICO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertMedico(com.qat.samples.sysmgmt.model.request.MedicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Medico> insertMedico(MedicoMaintenanceRequest request)
{
	InternalResultsResponse<Medico> response =
			processMedico(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IMedicoBAC#updateMedico(com.qat.samples.sysmgmt.model.request.MedicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Medico> updateMedico(MedicoMaintenanceRequest request)
{
	InternalResultsResponse<Medico> response =
			processMedico(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IMedicoBAC#deleteMedico(com.qat.samples.sysmgmt.model.request.MedicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Medico> deleteMedico(MedicoMaintenanceRequest request)
{
	InternalResultsResponse<Medico> response =
			processMedico(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IMedicoBAC#refreshMedicos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Medico> refreshMedicos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getClinicaBAR().deleteAllMedicos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getClinicaBAR().insertMedico(new Medico(i, "MedicoDesc" + i));
	}

	// Call maintain to see if we need to return the medico list and if so whether it should be paged or not
	return maintainReturnListMedico(request.getReturnList(), request.getReturnListPaged(),new Medico());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IMedicoBAC#fetchAllMedicos(Medico medico)
 */
@Override
public InternalResultsResponse<Medico> fetchAllMedicos(Medico medico)
{
	InternalResultsResponse<Medico> response = new InternalResultsResponse<Medico>();
	response.getResultsList().addAll(getClinicaBAR().fetchAllMedicos(medico).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IMedicoBAC#fetchMedicoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Medico> fetchMedicoById(FetchByIdRequest request)
{
	InternalResultsResponse<Medico> response = new InternalResultsResponse<Medico>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getClinicaBAR().fetchMedicoById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IMedicoBAC#fetchMedicosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Medico> fetchMedicosByRequest(MedicoInquiryRequest request)
{
	return getClinicaBAR().fetchMedicosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the medico response
 */
private InternalResultsResponse<Medico> processMedico(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		MedicoMaintenanceRequest request)
		{
	InternalResultsResponse<Medico> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Medico.class.getSimpleName(), request.getMedico(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Medico>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceMedico(request.getMedico(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Medico>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CLINICA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the medico list and if so whether it should be paged or
		// not
		response = maintainReturnListMedico(request.getReturnList(), request.getReturnListPaged(),new Medico());

		return response;
			}

	/**
	 * Do persistenceMedico.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceMedico(Medico medico, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getClinicaBAR().insertMedico(medico);

			case UPDATE:
				return getClinicaBAR().updateMedico(medico);

			case DELETE:
				return getClinicaBAR().deleteMedicoById(medico);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Medico> maintainReturnListMedico(Boolean listIndicator, Boolean pageListIndicator,Medico medico)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				MedicoInquiryRequest request = new MedicoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchMedicosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllMedicos(medico);
			}
		}
		else
		{
			return new InternalResultsResponse<Medico>();
		}
	}

//===================================### PACIENTE ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertPaciente(com.qat.samples.sysmgmt.model.request.PacienteMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Paciente> insertPaciente(PacienteMaintenanceRequest request)
{
	InternalResultsResponse<Paciente> response =
			processPaciente(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IPacienteBAC#updatePaciente(com.qat.samples.sysmgmt.model.request.PacienteMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Paciente> updatePaciente(PacienteMaintenanceRequest request)
{
	InternalResultsResponse<Paciente> response =
			processPaciente(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IPacienteBAC#deletePaciente(com.qat.samples.sysmgmt.model.request.PacienteMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Paciente> deletePaciente(PacienteMaintenanceRequest request)
{
	InternalResultsResponse<Paciente> response =
			processPaciente(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IPacienteBAC#refreshPacientes(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Paciente> refreshPacientes(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getClinicaBAR().deleteAllPacientes();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getClinicaBAR().insertPaciente(new Paciente(i, "PacienteDesc" + i));
	}

	// Call maintain to see if we need to return the paciente list and if so whether it should be paged or not
	return maintainReturnListPaciente(request.getReturnList(), request.getReturnListPaged(),new Paciente());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IPacienteBAC#fetchAllPacientes(Paciente paciente)
 */
@Override
public InternalResultsResponse<Paciente> fetchAllPacientes(Paciente paciente)
{
	InternalResultsResponse<Paciente> response = new InternalResultsResponse<Paciente>();
	response.getResultsList().addAll(getClinicaBAR().fetchAllPacientes(paciente).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IPacienteBAC#fetchPacienteById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Paciente> fetchPacienteById(FetchByIdRequest request)
{
	InternalResultsResponse<Paciente> response = new InternalResultsResponse<Paciente>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getClinicaBAR().fetchPacienteById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IPacienteBAC#fetchPacientesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Paciente> fetchPacientesByRequest(PacienteInquiryRequest request)
{
	return getClinicaBAR().fetchPacientesByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the paciente response
 */
private InternalResultsResponse<Paciente> processPaciente(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		PacienteMaintenanceRequest request)
		{
	InternalResultsResponse<Paciente> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Paciente.class.getSimpleName(), request.getPaciente(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Paciente>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistencePaciente(request.getPaciente(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Paciente>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CLINICA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the paciente list and if so whether it should be paged or
		// not
		response = maintainReturnListPaciente(request.getReturnList(), request.getReturnListPaged(),new Paciente());

		return response;
			}

	/**
	 * Do persistencePaciente.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistencePaciente(Paciente paciente, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getClinicaBAR().insertPaciente(paciente);

			case UPDATE:
				return getClinicaBAR().updatePaciente(paciente);

			case DELETE:
				return getClinicaBAR().deletePacienteById(paciente);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Paciente> maintainReturnListPaciente(Boolean listIndicator, Boolean pageListIndicator,Paciente paciente)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PacienteInquiryRequest request = new PacienteInquiryRequest();
				request.setPreQueryCount(true);
				return fetchPacientesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllPacientes(paciente);
			}
		}
		else
		{
			return new InternalResultsResponse<Paciente>();
		}
	}

//===================================### CONSULTA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConsulta(com.qat.samples.sysmgmt.model.request.ConsultaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Consulta> insertConsulta(ConsultaMaintenanceRequest request)
{
	InternalResultsResponse<Consulta> response =
			processConsulta(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConsultaBAC#updateConsulta(com.qat.samples.sysmgmt.model.request.ConsultaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Consulta> updateConsulta(ConsultaMaintenanceRequest request)
{
	InternalResultsResponse<Consulta> response =
			processConsulta(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConsultaBAC#deleteConsulta(com.qat.samples.sysmgmt.model.request.ConsultaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Consulta> deleteConsulta(ConsultaMaintenanceRequest request)
{
	InternalResultsResponse<Consulta> response =
			processConsulta(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConsultaBAC#refreshConsultas(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Consulta> refreshConsultas(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getClinicaBAR().deleteAllConsultas();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getClinicaBAR().insertConsulta(new Consulta(i, "ConsultaDesc" + i));
	}

	// Call maintain to see if we need to return the consulta list and if so whether it should be paged or not
	return maintainReturnListConsulta(request.getReturnList(), request.getReturnListPaged(),new Consulta());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConsultaBAC#fetchAllConsultas(Consulta consulta)
 */
@Override
public InternalResultsResponse<Consulta> fetchAllConsultas(Consulta consulta)
{
	InternalResultsResponse<Consulta> response = new InternalResultsResponse<Consulta>();
	response.getResultsList().addAll(getClinicaBAR().fetchAllConsultas(consulta).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConsultaBAC#fetchConsultaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Consulta> fetchConsultaById(FetchByIdRequest request)
{
	InternalResultsResponse<Consulta> response = new InternalResultsResponse<Consulta>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getClinicaBAR().fetchConsultaById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConsultaBAC#fetchConsultasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Consulta> fetchConsultasByRequest(ConsultaInquiryRequest request)
{
	return getClinicaBAR().fetchConsultasByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the consulta response
 */
private InternalResultsResponse<Consulta> processConsulta(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConsultaMaintenanceRequest request)
		{
	InternalResultsResponse<Consulta> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Consulta.class.getSimpleName(), request.getConsulta(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Consulta>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConsulta(request.getConsulta(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Consulta>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CLINICA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the consulta list and if so whether it should be paged or
		// not
		response = maintainReturnListConsulta(request.getReturnList(), request.getReturnListPaged(),new Consulta());

		return response;
			}

	/**
	 * Do persistenceConsulta.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConsulta(Consulta consulta, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getClinicaBAR().insertConsulta(consulta);

			case UPDATE:
				return getClinicaBAR().updateConsulta(consulta);

			case DELETE:
				return getClinicaBAR().deleteConsultaById(consulta);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Consulta> maintainReturnListConsulta(Boolean listIndicator, Boolean pageListIndicator,Consulta consulta)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ConsultaInquiryRequest request = new ConsultaInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConsultasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConsultas(consulta);
			}
		}
		else
		{
			return new InternalResultsResponse<Consulta>();
		}
	}

//===================================### EXAME ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertExame(com.qat.samples.sysmgmt.model.request.ExameMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Exame> insertExame(ExameMaintenanceRequest request)
{
	InternalResultsResponse<Exame> response =
			processExame(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IExameBAC#updateExame(com.qat.samples.sysmgmt.model.request.ExameMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Exame> updateExame(ExameMaintenanceRequest request)
{
	InternalResultsResponse<Exame> response =
			processExame(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IExameBAC#deleteExame(com.qat.samples.sysmgmt.model.request.ExameMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Exame> deleteExame(ExameMaintenanceRequest request)
{
	InternalResultsResponse<Exame> response =
			processExame(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IExameBAC#refreshExames(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Exame> refreshExames(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getClinicaBAR().deleteAllExames();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getClinicaBAR().insertExame(new Exame(i, "ExameDesc" + i));
	}

	// Call maintain to see if we need to return the exame list and if so whether it should be paged or not
	return maintainReturnListExame(request.getReturnList(), request.getReturnListPaged(),new Exame());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IExameBAC#fetchAllExames(Exame exame)
 */
@Override
public InternalResultsResponse<Exame> fetchAllExames(Exame exame)
{
	InternalResultsResponse<Exame> response = new InternalResultsResponse<Exame>();
	response.getResultsList().addAll(getClinicaBAR().fetchAllExames(exame).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IExameBAC#fetchExameById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Exame> fetchExameById(FetchByIdRequest request)
{
	InternalResultsResponse<Exame> response = new InternalResultsResponse<Exame>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getClinicaBAR().fetchExameById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IExameBAC#fetchExamesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Exame> fetchExamesByRequest(ExameInquiryRequest request)
{
	return getClinicaBAR().fetchExamesByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the exame response
 */
private InternalResultsResponse<Exame> processExame(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ExameMaintenanceRequest request)
		{
	InternalResultsResponse<Exame> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Exame.class.getSimpleName(), request.getExame(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Exame>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceExame(request.getExame(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Exame>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CLINICA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the exame list and if so whether it should be paged or
		// not
		response = maintainReturnListExame(request.getReturnList(), request.getReturnListPaged(),new Exame());

		return response;
			}

	/**
	 * Do persistenceExame.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceExame(Exame exame, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getClinicaBAR().insertExame(exame);

			case UPDATE:
				return getClinicaBAR().updateExame(exame);

			case DELETE:
				return getClinicaBAR().deleteExameById(exame);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Exame> maintainReturnListExame(Boolean listIndicator, Boolean pageListIndicator,Exame exame)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ExameInquiryRequest request = new ExameInquiryRequest();
				request.setPreQueryCount(true);
				return fetchExamesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllExames(exame);
			}
		}
		else
		{
			return new InternalResultsResponse<Exame>();
		}
	}
}
