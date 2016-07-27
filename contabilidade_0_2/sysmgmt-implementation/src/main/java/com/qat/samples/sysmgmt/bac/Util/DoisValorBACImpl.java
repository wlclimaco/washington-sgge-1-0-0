/** create by system gera-java version 1.0.0 27/07/2016 15:44 : 43*/
package com.qat.samples.sysmgmt.bac.undefined;


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
import com.qat.samples.sysmgmt.bac.IDoisValorBAC;
import com.qat.samples.sysmgmt.bar.IComprasBAR;
import com.qat.samples.sysmgmt.model.DoisValor;
import com.qat.samples.sysmgmt.model.request.DoisValorMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
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
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertDoisvalor(com.qat.samples.sysmgmt.model.request.DoisvalorMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Doisvalor> insertDoisvalor(DoisvalorMaintenanceRequest request)
{
	InternalResultsResponse<Doisvalor> response =
			processDoisvalor(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IDoisvalorBAC#updateDoisvalor(com.qat.samples.sysmgmt.model.request.DoisvalorMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Doisvalor> updateDoisvalor(DoisvalorMaintenanceRequest request)
{
	InternalResultsResponse<Doisvalor> response =
			processDoisvalor(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IDoisvalorBAC#deleteDoisvalor(com.qat.samples.sysmgmt.model.request.DoisvalorMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Doisvalor> deleteDoisvalor(DoisvalorMaintenanceRequest request)
{
	InternalResultsResponse<Doisvalor> response =
			processDoisvalor(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IDoisvalorBAC#refreshDoisvalors(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Doisvalor> refreshDoisvalors(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getDoisValorBAR().deleteAllDoisvalors();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getDoisValorBAR().insertDoisvalor(new Doisvalor(i, "DoisvalorDesc" + i));
	}

	// Call maintain to see if we need to return the doisvalor list and if so whether it should be paged or not
	return maintainReturnListDoisvalor(request.getReturnList(), request.getReturnListPaged(),new Doisvalor());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IDoisvalorBAC#fetchAllDoisvalors(Doisvalor doisvalor)
 */
@Override
public InternalResultsResponse<Doisvalor> fetchAllDoisvalors(Doisvalor doisvalor)
{
	InternalResultsResponse<Doisvalor> response = new InternalResultsResponse<Doisvalor>();
	response.getResultsList().addAll(getDoisValorBAR().fetchAllDoisvalors(doisvalor).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IDoisvalorBAC#fetchDoisvalorById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Doisvalor> fetchDoisvalorById(FetchByIdRequest request)
{
	InternalResultsResponse<Doisvalor> response = new InternalResultsResponse<Doisvalor>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getDoisValorBAR().fetchDoisvalorById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IDoisvalorBAC#fetchDoisvalorsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Doisvalor> fetchDoisvalorsByRequest(DoisvalorInquiryRequest request)
{
	return getDoisValorBAR().fetchDoisvalorsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the doisvalor response
 */
private InternalResultsResponse<Doisvalor> processDoisvalor(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		DoisvalorMaintenanceRequest request)
		{
	InternalResultsResponse<Doisvalor> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Doisvalor.class.getSimpleName(), request.getDoisvalor(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Doisvalor>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceDoisvalor(request.getDoisvalor(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Doisvalor>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_DOISVALOR_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the doisvalor list and if so whether it should be paged or
		// not
		response = maintainReturnListDoisvalor(request.getReturnList(), request.getReturnListPaged(),new Doisvalor());

		return response;
			}

	/**
	 * Do persistenceDoisvalor.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceDoisvalor(Doisvalor doisvalor, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getDoisValorBAR().insertDoisvalor(doisvalor);

			case UPDATE:
				return getDoisValorBAR().updateDoisvalor(doisvalor);

			case DELETE:
				return getDoisValorBAR().deleteDoisvalorById(doisvalor);
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
	private InternalResultsResponse<Doisvalor> maintainReturnListDoisvalor(Boolean listIndicator, Boolean pageListIndicator,Doisvalor doisvalor)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				DoisvalorInquiryRequest request = new DoisvalorInquiryRequest();
				request.setPreQueryCount(true);
				return fetchDoisvalorsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllDoisvalors(doisvalor);
			}
		}
		else
		{
			return new InternalResultsResponse<Doisvalor>();
		}
	}
}
