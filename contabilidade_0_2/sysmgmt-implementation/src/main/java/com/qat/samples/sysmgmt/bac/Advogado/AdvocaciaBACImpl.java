/** create by system gera-java version 1.0.0 28/04/2016 20:29 : 56*/
package com.qat.samples.sysmgmt.bac.Advogado;


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
import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.advocacia.Audiencia;
import com.qat.samples.sysmgmt.advocacia.Processo;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.AudienciaInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AudienciaMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoMaintenanceRequest;
import com.qat.samples.sysmgmt.bar.Advogado.IAdvocaciaBAR;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for Advogado leveraging the injected IAdvogadoBAR.
 */
@Component
public class AdvocaciaBACImpl implements IAdvocaciaBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_ADVOGADO_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_ADVOGADO_BAC_EXCEPTION_MSG = "sysmgmt.base.Advogadobacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AdvocaciaBACImpl.class);

	/** The Advogado BAR. */
	private IAdvocaciaBAR advogadoBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the Advogado BAR.
	 *
	 * @param AdvogadoBAR the new Advogado BAR
	 */
	public void setAdvogadoBAR(IAdvocaciaBAR advogadoBAR)
	{
		this.advogadoBAR = advogadoBAR;
	}

	/**
	 * Gets the Advogado BAR.
	 *
	 * @return the Advogado BAR
	 */
	public IAdvocaciaBAR getAdvogadoBAR()
	{
		return advogadoBAR;
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

//===================================### ADVOGADO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertAdvogado(com.qat.samples.sysmgmt.model.request.AdvogadoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Advogado> insertAdvogado(AdvogadoMaintenanceRequest request)
{
	InternalResultsResponse<Advogado> response =
			processAdvogado(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAdvogadoBAC#updateAdvogado(com.qat.samples.sysmgmt.model.request.AdvogadoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Advogado> updateAdvogado(AdvogadoMaintenanceRequest request)
{
	InternalResultsResponse<Advogado> response =
			processAdvogado(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAdvogadoBAC#deleteAdvogado(com.qat.samples.sysmgmt.model.request.AdvogadoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Advogado> deleteAdvogado(AdvogadoMaintenanceRequest request)
{
	InternalResultsResponse<Advogado> response =
			processAdvogado(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAdvogadoBAC#refreshAdvogados(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Advogado> refreshAdvogados(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getAdvogadoBAR().deleteAllAdvogados();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getAdvogadoBAR().insertAdvogado(new Advogado(i, "AdvogadoDesc" + i));
	}

	// Call maintain to see if we need to return the advogado list and if so whether it should be paged or not
	return maintainReturnListAdvogado(request.getReturnList(), request.getReturnListPaged(),new Advogado());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAdvogadoBAC#fetchAllAdvogados(Advogado advogado)
 */
@Override
public InternalResultsResponse<Advogado> fetchAllAdvogados(Advogado advogado)
{
	InternalResultsResponse<Advogado> response = new InternalResultsResponse<Advogado>();
	response.getResultsList().addAll(getAdvogadoBAR().fetchAllAdvogados(advogado).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAdvogadoBAC#fetchAdvogadoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Advogado> fetchAdvogadoById(FetchByIdRequest request)
{
	InternalResultsResponse<Advogado> response = new InternalResultsResponse<Advogado>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getAdvogadoBAR().fetchAdvogadoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAdvogadoBAC#fetchAdvogadosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Advogado> fetchAdvogadosByRequest(AdvogadoInquiryRequest request)
{
	return getAdvogadoBAR().fetchAdvogadosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the advogado response
 */
private InternalResultsResponse<Advogado> processAdvogado(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		AdvogadoMaintenanceRequest request)
		{
	InternalResultsResponse<Advogado> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Advogado.class.getSimpleName(), request.getAdvogado(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Advogado>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceAdvogado(request.getAdvogado(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Advogado>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_ADVOGADO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the advogado list and if so whether it should be paged or
		// not
		response = maintainReturnListAdvogado(request.getReturnList(), request.getReturnListPaged(),new Advogado());

		return response;
			}

	/**
	 * Do persistenceAdvogado.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceAdvogado(Advogado advogado, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getAdvogadoBAR().insertAdvogado(advogado);

			case UPDATE:
				return getAdvogadoBAR().updateAdvogado(advogado);

			case DELETE:
				return getAdvogadoBAR().deleteAdvogadoById(advogado);
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
	private InternalResultsResponse<Advogado> maintainReturnListAdvogado(Boolean listIndicator, Boolean pageListIndicator,Advogado advogado)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				AdvogadoInquiryRequest request = new AdvogadoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchAdvogadosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllAdvogados(advogado);
			}
		}
		else
		{
			return new InternalResultsResponse<Advogado>();
		}
	}

//===================================### AUDIENCIA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertAudiencia(com.qat.samples.sysmgmt.model.request.AudienciaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Audiencia> insertAudiencia(AudienciaMaintenanceRequest request)
{
	InternalResultsResponse<Audiencia> response =
			processAudiencia(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAudienciaBAC#updateAudiencia(com.qat.samples.sysmgmt.model.request.AudienciaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Audiencia> updateAudiencia(AudienciaMaintenanceRequest request)
{
	InternalResultsResponse<Audiencia> response =
			processAudiencia(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAudienciaBAC#deleteAudiencia(com.qat.samples.sysmgmt.model.request.AudienciaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Audiencia> deleteAudiencia(AudienciaMaintenanceRequest request)
{
	InternalResultsResponse<Audiencia> response =
			processAudiencia(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAudienciaBAC#refreshAudiencias(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Audiencia> refreshAudiencias(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getAdvogadoBAR().deleteAllAudiencias();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getAdvogadoBAR().insertAudiencia(new Audiencia(i, "AudienciaDesc" + i));
	}

	// Call maintain to see if we need to return the audiencia list and if so whether it should be paged or not
	return maintainReturnListAudiencia(request.getReturnList(), request.getReturnListPaged(),new Audiencia());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAudienciaBAC#fetchAllAudiencias(Audiencia audiencia)
 */
@Override
public InternalResultsResponse<Audiencia> fetchAllAudiencias(Audiencia audiencia)
{
	InternalResultsResponse<Audiencia> response = new InternalResultsResponse<Audiencia>();
	response.getResultsList().addAll(getAdvogadoBAR().fetchAllAudiencias(audiencia).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAudienciaBAC#fetchAudienciaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Audiencia> fetchAudienciaById(FetchByIdRequest request)
{
	InternalResultsResponse<Audiencia> response = new InternalResultsResponse<Audiencia>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getAdvogadoBAR().fetchAudienciaById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAudienciaBAC#fetchAudienciasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Audiencia> fetchAudienciasByRequest(AudienciaInquiryRequest request)
{
	return getAdvogadoBAR().fetchAudienciasByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the audiencia response
 */
private InternalResultsResponse<Audiencia> processAudiencia(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		AudienciaMaintenanceRequest request)
		{
	InternalResultsResponse<Audiencia> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Audiencia.class.getSimpleName(), request.getAudiencia(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Audiencia>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceAudiencia(request.getAudiencia(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Audiencia>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_ADVOGADO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the audiencia list and if so whether it should be paged or
		// not
		response = maintainReturnListAudiencia(request.getReturnList(), request.getReturnListPaged(),new Audiencia());

		return response;
			}

	/**
	 * Do persistenceAudiencia.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceAudiencia(Audiencia audiencia, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getAdvogadoBAR().insertAudiencia(audiencia);

			case UPDATE:
				return getAdvogadoBAR().updateAudiencia(audiencia);

			case DELETE:
				return getAdvogadoBAR().deleteAudienciaById(audiencia);
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
	private InternalResultsResponse<Audiencia> maintainReturnListAudiencia(Boolean listIndicator, Boolean pageListIndicator,Audiencia audiencia)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				AudienciaInquiryRequest request = new AudienciaInquiryRequest();
				request.setPreQueryCount(true);
				return fetchAudienciasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllAudiencias(audiencia);
			}
		}
		else
		{
			return new InternalResultsResponse<Audiencia>();
		}
	}

//===================================### PROCESSO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertProcesso(com.qat.samples.sysmgmt.model.request.ProcessoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Processo> insertProcesso(ProcessoMaintenanceRequest request)
{
	InternalResultsResponse<Processo> response =
			processProcesso(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IProcessoBAC#updateProcesso(com.qat.samples.sysmgmt.model.request.ProcessoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Processo> updateProcesso(ProcessoMaintenanceRequest request)
{
	InternalResultsResponse<Processo> response =
			processProcesso(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IProcessoBAC#deleteProcesso(com.qat.samples.sysmgmt.model.request.ProcessoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Processo> deleteProcesso(ProcessoMaintenanceRequest request)
{
	InternalResultsResponse<Processo> response =
			processProcesso(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IProcessoBAC#refreshProcessos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Processo> refreshProcessos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getAdvogadoBAR().deleteAllProcessos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getAdvogadoBAR().insertProcesso(new Processo(i, "ProcessoDesc" + i));
	}

	// Call maintain to see if we need to return the processo list and if so whether it should be paged or not
	return maintainReturnListProcesso(request.getReturnList(), request.getReturnListPaged(),new Processo());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IProcessoBAC#fetchAllProcessos(Processo processo)
 */
@Override
public InternalResultsResponse<Processo> fetchAllProcessos(Processo processo)
{
	InternalResultsResponse<Processo> response = new InternalResultsResponse<Processo>();
	response.getResultsList().addAll(getAdvogadoBAR().fetchAllProcessos(processo).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IProcessoBAC#fetchProcessoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Processo> fetchProcessoById(FetchByIdRequest request)
{
	InternalResultsResponse<Processo> response = new InternalResultsResponse<Processo>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getAdvogadoBAR().fetchProcessoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IProcessoBAC#fetchProcessosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Processo> fetchProcessosByRequest(ProcessoInquiryRequest request)
{
	return getAdvogadoBAR().fetchProcessosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the processo response
 */
private InternalResultsResponse<Processo> processProcesso(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ProcessoMaintenanceRequest request)
		{
	InternalResultsResponse<Processo> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Processo.class.getSimpleName(), request.getProcesso(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Processo>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceProcesso(request.getProcesso(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Processo>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_ADVOGADO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the processo list and if so whether it should be paged or
		// not
		response = maintainReturnListProcesso(request.getReturnList(), request.getReturnListPaged(),new Processo());

		return response;
			}

	/**
	 * Do persistenceProcesso.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceProcesso(Processo processo, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getAdvogadoBAR().insertProcesso(processo);

			case UPDATE:
				return getAdvogadoBAR().updateProcesso(processo);

			case DELETE:
				return getAdvogadoBAR().deleteProcessoById(processo);
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
	private InternalResultsResponse<Processo> maintainReturnListProcesso(Boolean listIndicator, Boolean pageListIndicator,Processo processo)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ProcessoInquiryRequest request = new ProcessoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchProcessosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllProcessos(processo);
			}
		}
		else
		{
			return new InternalResultsResponse<Processo>();
		}
	}
}
