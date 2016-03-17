package com.qat.samples.sysmgmt.financeiro.bai.impl;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.financeiro.Financeiro;
import com.qat.samples.sysmgmt.financeiro.bac.IFinanceiroBAC;
import com.qat.samples.sysmgmt.financeiro.bai.IFinanceiroBAI;
import com.qat.samples.sysmgmt.financeiro.model.request.FinanceiroInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FinanceiroMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.response.FinanceiroResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Class FinanceiroBAIImpl.
 */
public class FinanceiroBAIImpl implements IFinanceiroBAI
{
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = FinanceiroBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FinanceiroBAIImpl.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED =
			"sendsolv.base.empresavalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED =
			"sendsolv.base.validator.paging.parameters.required";

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	private static final String PROSPERITASGLOBAL_BASE_OL_ERROR =
			"sendsolv.base.optimistic.locking.error";

	/** The empresa bac. */
	private IFinanceiroBAC empresaBAC; // injected by Spring through setter

	/** The validation controller. */
	private ValidationController validationController;

	/**
	 * Get empresa validation controller.
	 * 
	 * @return the validation controller
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Spring sets the empresa validation controller.
	 * 
	 * @param empresaValidationController the new validation controller
	 */
	public void setValidationController(ValidationController empresaValidationController)
	{
		validationController = empresaValidationController;
	}

	/**
	 * Spring Sets the empresa bac.
	 * 
	 * @param empresaBAC the new empresa bac
	 */
	public void setFinanceiroBAC(IFinanceiroBAC empresaBAC)
	{
		this.empresaBAC = empresaBAC;
	}

	/**
	 * Gets the empresa bac.
	 * 
	 * @return the empresa bac
	 */
	public IFinanceiroBAC getFinanceiroBAC()
	{
		return empresaBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#insertLocaation(com.prosperitasglobal.sendsolv.model
	 * .request.LocaationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	@Override
	public FinanceiroResponse insertFinanceiro(FinanceiroMaintenanceRequest request)
	{
		FinanceiroResponse response = new FinanceiroResponse();
		try
		{
			response = process(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#updateLocaation(com.prosperitasglobal.sendsolv.model
	 * .request.LocaationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	@Override
	public FinanceiroResponse updateFinanceiro(FinanceiroMaintenanceRequest request)
	{
		FinanceiroResponse response = new FinanceiroResponse();
		try
		{
			response = process(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#deleteLocaation(com.prosperitasglobal.sendsolv.model
	 * .request.LocaationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	@Override
	public FinanceiroResponse deleteFinanceiro(FinanceiroMaintenanceRequest request)
	{
		FinanceiroResponse response = new FinanceiroResponse();
		try
		{
			response = process(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#fetchLocaationById(com.prosperitasglobal.sendsolv.model.
	 * request
	 * .CountyRequest)
	 */
	@Override
	public FinanceiroResponse fetchFinanceiroById(FetchByIdRequest request)
	{
		FinanceiroResponse response = new FinanceiroResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getFetchId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getFinanceiroBAC().fetchFinanceiroById(request);
			}
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.IFinanceiroBAI#fetchFinanceiroByRequest(com.prosperitasglobal.sendsolv.model
	 * .request.PagedInquiryRequest)
	 */
	@Override
	public FinanceiroResponse fetchFinanceiroByRequest(FinanceiroInquiryRequest request)
	{
		FinanceiroResponse response = new FinanceiroResponse();
		try
		{
			fetchPaged(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bai.IFinanceiroBAI#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */

	/**
	 * Fetch paged.
	 * 
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPaged(FinanceiroInquiryRequest request, FinanceiroResponse response)
	{
		InternalResultsResponse<Financeiro> internalResponse = new InternalResultsResponse<Financeiro>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getFinanceiroBAC().fetchFinanceiroByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Process.
	 * 
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the empresa response
	 */
	private FinanceiroResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			FinanceiroMaintenanceRequest request)
	{
		FinanceiroResponse response = new FinanceiroResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(Financeiro.class.getSimpleName(), request.getFinanceiro(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistance(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Handle return.
	 * 
	 * @param response the response
	 * @param internalResponse the internal response
	 * @param messages the messages
	 * @param copyOver the copy over
	 * @return the response
	 */
	private FinanceiroResponse handleReturn(FinanceiroResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{
		// In the case there was an Optimistic Locking error, add the specific message
		if (!ValidationUtil.isNull(internalResponse) && !ValidationUtil.isNull(internalResponse.getStatus())
				&& Status.OptimisticLockingError.equals(internalResponse.getStatus()))
		{
			messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_OL_ERROR, MessageSeverity.Error,
					MessageLevel.Object));
		}

		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, messages, copyOver);
		return response;
	}

	/**
	 * Do persistance.
	 * 
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistance(FinanceiroMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getFinanceiroBAC().insertFinanceiro(request);

			case UPDATE:
				return getFinanceiroBAC().updateFinanceiro(request);

			case DELETE:
				return getFinanceiroBAC().deleteFinanceiro(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

}
