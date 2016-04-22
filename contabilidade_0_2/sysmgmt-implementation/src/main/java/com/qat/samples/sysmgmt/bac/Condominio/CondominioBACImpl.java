package com.qat.samples.sysmgmt.bac.Condominio;


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
import com.qat.samples.sysmgmt.bar.Condominio.ICondominioBAR;
import com.qat.samples.sysmgmt.condominio.model.Avisos;
import com.qat.samples.sysmgmt.condominio.model.Inquilino;
import com.qat.samples.sysmgmt.condominio.model.Sindico;
import com.qat.samples.sysmgmt.condominio.model.request.AvisoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.AvisoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for Condominio leveraging the injected ICondominioBAR.
 */
@Component
public class CondominioBACImpl implements ICondominioBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_CONDOMINIO_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_CONDOMINIO_BAC_EXCEPTION_MSG = "sysmgmt.base.Condominiobacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CondominioBACImpl.class);

	/** The Condominio BAR. */
	private ICondominioBAR condominioBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the Condominio BAR.
	 *
	 * @param CondominioBAR the new Condominio BAR
	 */
	public void setCondominioBAR(ICondominioBAR condominioBAR)
	{
		this.condominioBAR = condominioBAR;
	}

	/**
	 * Gets the Condominio BAR.
	 *
	 * @return the Condominio BAR
	 */
	public ICondominioBAR getCondominioBAR()
	{
		return condominioBAR;
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

//===================================### SINDICO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertSindico(com.qat.samples.sysmgmt.model.request.SindicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Sindico> insertSindico(SindicoMaintenanceRequest request)
{
	InternalResultsResponse<Sindico> response =
			processSindico(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ISindicoBAC#updateSindico(com.qat.samples.sysmgmt.model.request.SindicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Sindico> updateSindico(SindicoMaintenanceRequest request)
{
	InternalResultsResponse<Sindico> response =
			processSindico(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ISindicoBAC#deleteSindico(com.qat.samples.sysmgmt.model.request.SindicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Sindico> deleteSindico(SindicoMaintenanceRequest request)
{
	InternalResultsResponse<Sindico> response =
			processSindico(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ISindicoBAC#refreshSindicos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Sindico> refreshSindicos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getCondominioBAR().deleteAllSindicos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getCondominioBAR().insertSindico(new Sindico(i, "SindicoDesc" + i));
	}

	// Call maintain to see if we need to return the sindico list and if so whether it should be paged or not
	return maintainReturnListSindico(request.getReturnList(), request.getReturnListPaged(),new Sindico());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ISindicoBAC#fetchAllSindicos(Sindico sindico)
 */
@Override
public InternalResultsResponse<Sindico> fetchAllSindicos(Sindico sindico)
{
	InternalResultsResponse<Sindico> response = new InternalResultsResponse<Sindico>();
	response.getResultsList().addAll(getCondominioBAR().fetchAllSindicos(sindico).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ISindicoBAC#fetchSindicoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Sindico> fetchSindicoById(FetchByIdRequest request)
{
	InternalResultsResponse<Sindico> response = new InternalResultsResponse<Sindico>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getCondominioBAR().fetchSindicoById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ISindicoBAC#fetchSindicosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Sindico> fetchSindicosByRequest(SindicoInquiryRequest request)
{
	return getCondominioBAR().fetchSindicosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the sindico response
 */
private InternalResultsResponse<Sindico> processSindico(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		SindicoMaintenanceRequest request)
		{
	InternalResultsResponse<Sindico> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Sindico.class.getSimpleName(), request.getSindico(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Sindico>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceSindico(request.getSindico(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Sindico>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONDOMINIO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the sindico list and if so whether it should be paged or
		// not
		response = maintainReturnListSindico(request.getReturnList(), request.getReturnListPaged(),new Sindico());

		return response;
			}

	/**
	 * Do persistenceSindico.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceSindico(Sindico sindico, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCondominioBAR().insertSindico(sindico);

			case UPDATE:
				return getCondominioBAR().updateSindico(sindico);

			case DELETE:
				return getCondominioBAR().deleteSindicoById(sindico);
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
	private InternalResultsResponse<Sindico> maintainReturnListSindico(Boolean listIndicator, Boolean pageListIndicator,Sindico sindico)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				SindicoInquiryRequest request = new SindicoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchSindicosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllSindicos(sindico);
			}
		}
		else
		{
			return new InternalResultsResponse<Sindico>();
		}
	}

//===================================### INQUILINO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertInquilino(com.qat.samples.sysmgmt.model.request.InquilinoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Inquilino> insertInquilino(InquilinoMaintenanceRequest request)
{
	InternalResultsResponse<Inquilino> response =
			processInquilino(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IInquilinoBAC#updateInquilino(com.qat.samples.sysmgmt.model.request.InquilinoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Inquilino> updateInquilino(InquilinoMaintenanceRequest request)
{
	InternalResultsResponse<Inquilino> response =
			processInquilino(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IInquilinoBAC#deleteInquilino(com.qat.samples.sysmgmt.model.request.InquilinoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Inquilino> deleteInquilino(InquilinoMaintenanceRequest request)
{
	InternalResultsResponse<Inquilino> response =
			processInquilino(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IInquilinoBAC#refreshInquilinos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Inquilino> refreshInquilinos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getCondominioBAR().deleteAllInquilinos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getCondominioBAR().insertInquilino(new Inquilino(i, "InquilinoDesc" + i));
	}

	// Call maintain to see if we need to return the inquilino list and if so whether it should be paged or not
	return maintainReturnListInquilino(request.getReturnList(), request.getReturnListPaged(),new Inquilino());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IInquilinoBAC#fetchAllInquilinos(Inquilino inquilino)
 */
@Override
public InternalResultsResponse<Inquilino> fetchAllInquilinos(Inquilino inquilino)
{
	InternalResultsResponse<Inquilino> response = new InternalResultsResponse<Inquilino>();
	response.getResultsList().addAll(getCondominioBAR().fetchAllInquilinos(inquilino).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IInquilinoBAC#fetchInquilinoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Inquilino> fetchInquilinoById(FetchByIdRequest request)
{
	InternalResultsResponse<Inquilino> response = new InternalResultsResponse<Inquilino>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getCondominioBAR().fetchInquilinoById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IInquilinoBAC#fetchInquilinosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Inquilino> fetchInquilinosByRequest(InquilinoInquiryRequest request)
{
	return getCondominioBAR().fetchInquilinosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the inquilino response
 */
private InternalResultsResponse<Inquilino> processInquilino(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		InquilinoMaintenanceRequest request)
		{
	InternalResultsResponse<Inquilino> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Inquilino.class.getSimpleName(), request.getInquilino(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Inquilino>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceInquilino(request.getInquilino(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Inquilino>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONDOMINIO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the inquilino list and if so whether it should be paged or
		// not
		response = maintainReturnListInquilino(request.getReturnList(), request.getReturnListPaged(),new Inquilino());

		return response;
			}

	/**
	 * Do persistenceInquilino.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceInquilino(Inquilino inquilino, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCondominioBAR().insertInquilino(inquilino);

			case UPDATE:
				return getCondominioBAR().updateInquilino(inquilino);

			case DELETE:
				return getCondominioBAR().deleteInquilinoById(inquilino);
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
	private InternalResultsResponse<Inquilino> maintainReturnListInquilino(Boolean listIndicator, Boolean pageListIndicator,Inquilino inquilino)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				InquilinoInquiryRequest request = new InquilinoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchInquilinosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllInquilinos(inquilino);
			}
		}
		else
		{
			return new InternalResultsResponse<Inquilino>();
		}
	}

//===================================### AVISOS ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertAvisos(com.qat.samples.sysmgmt.model.request.AvisosMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Avisos> insertAvisos(AvisoMaintenanceRequest request)
{
	InternalResultsResponse<Avisos> response =
			processAvisos(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAvisosBAC#updateAvisos(com.qat.samples.sysmgmt.model.request.AvisosMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Avisos> updateAvisos(AvisoMaintenanceRequest request)
{
	InternalResultsResponse<Avisos> response =
			processAvisos(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAvisosBAC#deleteAvisos(com.qat.samples.sysmgmt.model.request.AvisosMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Avisos> deleteAvisos(AvisoMaintenanceRequest request)
{
	InternalResultsResponse<Avisos> response =
			processAvisos(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAvisosBAC#refreshAvisoss(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Avisos> refreshAvisos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getCondominioBAR().deleteAllAvisoss();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getCondominioBAR().insertAvisos(new Avisos(i, "AvisosDesc" + i));
	}

	// Call maintain to see if we need to return the avisos list and if so whether it should be paged or not
	return maintainReturnListAvisos(request.getReturnList(), request.getReturnListPaged(),new Avisos());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAvisosBAC#fetchAllAvisoss(Avisos avisos)
 */
@Override
public InternalResultsResponse<Avisos> fetchAllAvisoss(Avisos avisos)
{
	InternalResultsResponse<Avisos> response = new InternalResultsResponse<Avisos>();
	response.getResultsList().addAll(getCondominioBAR().fetchAllAvisos(avisos).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAvisosBAC#fetchAvisosById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Avisos> fetchAvisosById(FetchByIdRequest request)
{
	InternalResultsResponse<Avisos> response = new InternalResultsResponse<Avisos>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getCondominioBAR().fetchAvisosById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAvisosBAC#fetchAvisossByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Avisos> fetchAvisosByRequest(AvisoInquiryRequest request)
{
	return getCondominioBAR().fetchAvisosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the avisos response
 */
private InternalResultsResponse<Avisos> processAvisos(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		AvisoMaintenanceRequest request)
		{
	InternalResultsResponse<Avisos> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Avisos.class.getSimpleName(), request.getAviso(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Avisos>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceAvisos(request.getAviso(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Avisos>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONDOMINIO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the avisos list and if so whether it should be paged or
		// not
		response = maintainReturnListAvisos(request.getReturnList(), request.getReturnListPaged(),new Avisos());

		return response;
			}

	/**
	 * Do persistenceAvisos.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceAvisos(Avisos avisos, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCondominioBAR().insertAvisos(avisos);

			case UPDATE:
				return getCondominioBAR().updateAvisos(avisos);

			case DELETE:
				return getCondominioBAR().deleteAvisosById(avisos);
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
	private InternalResultsResponse<Avisos> maintainReturnListAvisos(Boolean listIndicator, Boolean pageListIndicator,Avisos avisos)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				AvisoInquiryRequest request = new AvisoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchAvisosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllAvisoss(avisos);
			}
		}
		else
		{
			return new InternalResultsResponse<Avisos>();
		}
	}
}
