/** create by system gera-java version 1.0.0 27/07/2016 15:44 : 43*/
package com.qat.samples.sysmgmt.bac.Util;


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
import com.qat.samples.sysmgmt.bar.Util.IDoisValorBAR;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.request.DoisValoresInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.DoisValoresMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for DoisValor leveraging the injected IDoisValorBAR.
 */
@Component
public class DoisValorBACImpl implements IDoisValorBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_DOISVALOR_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_DOISVALOR_BAC_EXCEPTION_MSG = "sysmgmt.base.DoisValorbacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DoisValorBACImpl.class);

	/** The DoisValor BAR. */
	private IDoisValorBAR doisvalorBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the DoisValor BAR.
	 *
	 * @param DoisValorBAR the new DoisValor BAR
	 */
	public void setDoisValorBAR(IDoisValorBAR doisvalorBAR)
	{
		this.doisvalorBAR = doisvalorBAR;
	}

	/**
	 * Gets the DoisValor BAR.
	 *
	 * @return the DoisValor BAR
	 */
	public IDoisValorBAR getDoisValorBAR()
	{
		return doisvalorBAR;
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

//===================================### DOISVALOR ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertDoisValores(com.qat.samples.sysmgmt.model.request.DoisValoresMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<DoisValores> insertDoisValor(DoisValoresMaintenanceRequest request)
{
	InternalResultsResponse<DoisValores> response =
			processDoisValores(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IDoisValoresBAC#updateDoisValores(com.qat.samples.sysmgmt.model.request.DoisValoresMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<DoisValores> updateDoisValor(DoisValoresMaintenanceRequest request)
{
	InternalResultsResponse<DoisValores> response =
			processDoisValores(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IDoisValoresBAC#deleteDoisValores(com.qat.samples.sysmgmt.model.request.DoisValoresMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<DoisValores> deleteDoisValor(DoisValoresMaintenanceRequest request)
{
	InternalResultsResponse<DoisValores> response =
			processDoisValores(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IDoisValoresBAC#refreshDoisValoress(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<DoisValores> refreshDoisValors(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getDoisValorBAR().deleteAllDoisValoress();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getDoisValorBAR().insertDoisValores(new DoisValores(i, "DoisValoresDesc" + i));
	}

	// Call maintain to see if we need to return the doisvalor list and if so whether it should be paged or not
	return maintainReturnListDoisValores(request.getReturnList(), request.getReturnListPaged(),new DoisValores());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IDoisValoresBAC#fetchAllDoisValoress(DoisValores doisvalor)
 */
@Override
public InternalResultsResponse<DoisValores> fetchAllDoisValors(DoisValores doisvalor)
{
	InternalResultsResponse<DoisValores> response = new InternalResultsResponse<DoisValores>();
	response.getResultsList().addAll(getDoisValorBAR().fetchAllDoisValoress(doisvalor).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IDoisValoresBAC#fetchDoisValoresById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<DoisValores> fetchDoisValorById(FetchByIdRequest request)
{
	InternalResultsResponse<DoisValores> response = new InternalResultsResponse<DoisValores>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getDoisValorBAR().fetchDoisValoresById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IDoisValoresBAC#fetchDoisValoressByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<DoisValores> fetchDoisValorsByRequest(DoisValoresInquiryRequest request)
{
	return getDoisValorBAR().fetchDoisValoressByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the doisvalor response
 */
private InternalResultsResponse<DoisValores> processDoisValores(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		DoisValoresMaintenanceRequest request)
		{
	InternalResultsResponse<DoisValores> response = null;

	// Validate
	ValidationContext context = new ValidationContext(DoisValores.class.getSimpleName(), request.getDoisValores(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<DoisValores>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceDoisValores(request.getDoisValores(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<DoisValores>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_DOISVALOR_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the doisvalor list and if so whether it should be paged or
		// not
		response = maintainReturnListDoisValores(request.getReturnList(), request.getReturnListPaged(),new DoisValores());

		return response;
			}

	/**
	 * Do persistenceDoisValores.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceDoisValores(DoisValores doisvalor, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getDoisValorBAR().insertDoisValores(doisvalor);

			case UPDATE:
				return getDoisValorBAR().updateDoisValores(doisvalor);

			case DELETE:
				return getDoisValorBAR().deleteDoisValoresById(doisvalor);
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
	private InternalResultsResponse<DoisValores> maintainReturnListDoisValores(Boolean listIndicator, Boolean pageListIndicator,DoisValores doisvalor)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				DoisValoresInquiryRequest request = new DoisValoresInquiryRequest();
				request.setPreQueryCount(true);
				return fetchDoisValorsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllDoisValors(doisvalor);
			}
		}
		else
		{
			return new InternalResultsResponse<DoisValores>();
		}
	}
}
