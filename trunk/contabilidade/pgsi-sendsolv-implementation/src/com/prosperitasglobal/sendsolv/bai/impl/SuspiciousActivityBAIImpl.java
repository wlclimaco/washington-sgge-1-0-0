package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.ISuspiciousActivityBAC;
import com.prosperitasglobal.sendsolv.bai.ISuspiciousActivityBAI;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivity;
import com.prosperitasglobal.sendsolv.model.request.SarInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.SarMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.SarResponse;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.model.response.MaintenanceResponse;
import com.qat.framework.model.response.Response;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class SuspiciousActivityBAIImpl.
 */
public class SuspiciousActivityBAIImpl implements ISuspiciousActivityBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_ID_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_ID_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED =
			"sendsolv.base.validator.paging.parameters.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SuspiciousActivityBAIImpl.class);

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = SuspiciousActivity.class.getName();

	/** The suspicious activity bac. */
	private ISuspiciousActivityBAC suspiciousActivityBAC;

	/** The suspicious activity validation controller. */
	private ValidationController suspiciousActivityValidationController;

	/**
	 * Gets the suspicious activity bac.
	 *
	 * @return the suspiciousActivityBAC
	 */
	public ISuspiciousActivityBAC getSuspiciousActivityBAC()
	{
		return suspiciousActivityBAC;
	}

	/**
	 * Sets the suspicious activity bac.
	 *
	 * @param suspiciousActivityBAC the suspiciousActivityBAC to set
	 */
	public void setSuspiciousActivityBAC(ISuspiciousActivityBAC suspiciousActivityBAC)
	{
		this.suspiciousActivityBAC = suspiciousActivityBAC;
	}

	/**
	 * Gets the suspicious activity validation controller.
	 *
	 * @return the suspiciousActivityValidationController
	 */
	public ValidationController getSuspiciousActivityValidationController()
	{
		return suspiciousActivityValidationController;
	}

	/**
	 * Sets the suspicious activity validation controller.
	 *
	 * @param suspiciousActivityValidationController the suspiciousActivityValidationController to set
	 */
	public void setSuspiciousActivityValidationController(ValidationController suspiciousActivityValidationController)
	{
		this.suspiciousActivityValidationController = suspiciousActivityValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ISuspiciousActivityBAI#insertSuspiciousActivity(com.prosperitasglobal.sendsolv
	 * .model.request.SarMaintenanceRequest)
	 */
	@Override
	public MaintenanceResponse insertSuspiciousActivity(SarMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();
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
	 * com.prosperitasglobal.sendsolv.bai.ISuspiciousActivityBAI#updateSuspiciousActivity(com.prosperitasglobal.sendsolv
	 * .model.request.SarMaintenanceRequest)
	 */
	@Override
	public MaintenanceResponse updateSuspiciousActivity(SarMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();
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
	 * com.prosperitasglobal.sendsolv.bai.ISuspiciousActivityBAI#deleteSuspiciousActivity(com.prosperitasglobal.sendsolv
	 * .model.request.SarMaintenanceRequest)
	 */
	@Override
	public MaintenanceResponse deleteSuspiciousActivity(SarMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();
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
	 * com.prosperitasglobal.sendsolv.bai.ISuspiciousActivityBAI#fetchSuspiciousActivityByRequest(com.prosperitasglobal
	 * .sendsolv.model.request.SarInquiryRequest)
	 */
	@Override
	public SarResponse fetchSuspiciousActivityByRequest(SarInquiryRequest request)
	{
		SarResponse response = new SarResponse();
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
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ISuspiciousActivityBAI#fetchSuspiciousActivityByIdRequest
	 * (com.prosperitasglobal.cbof.model.request.FetchByIdRequest)
	 */
	@Override
	public SarResponse fetchSuspiciousActivityByIdRequest(FetchByIdRequest request)
	{
		SarResponse response = new SarResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
			{
				internalResponse.addFieldErrorMessage(SUSPICIOUS_ACTIVITY_VALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getSuspiciousActivityBAC().fetchSuspiciousActivityByIdRequest(request);
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
	 * @see com.prosperitasglobal.sendsolv.bai.ISuspiciousActivityBAI#fetchBusinessSuspiciousActivityByIdRequest(com.
	 * prosperitasglobal.cbof.model.request.FetchByIdRequest)
	 */
	@Override
	public SarResponse fetchBusinessSuspiciousActivityByIdRequest(FetchByIdRequest request)
	{
		SarResponse response = new SarResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
			{
				internalResponse.addFieldErrorMessage(SUSPICIOUS_ACTIVITY_VALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getSuspiciousActivityBAC().fetchBusinessSuspiciousActivityByIdRequest(request);
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

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the maintenance response
	 */
	private MaintenanceResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			SarMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();
		InternalResponse internalResponse = null;

		// Validate.
		ValidationContext context =
				new ValidationContext(SuspiciousActivity.class.getSimpleName(), request.getSuspiciousActivity(),
						indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getSuspiciousActivityValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistance(request, persistType);
		}

		return (MaintenanceResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistance(SarMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getSuspiciousActivityBAC().insertSuspiciousActivity(request);

			case UPDATE:
				return getSuspiciousActivityBAC().updateSuspiciousActivity(request);

			case DELETE:
				return getSuspiciousActivityBAC().deleteSuspiciousActivity(request);

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
	 * Handle return.
	 *
	 * @param response the response
	 * @param internalResponse the internal response
	 * @param messages the messages
	 * @param copyOver the copy over
	 * @return the response
	 */
	private Response handleReturn(Response response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, messages, copyOver);
		return response;
	}

	/**
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPaged(SarInquiryRequest request, SarResponse response)
	{
		InternalResultsResponse<SuspiciousActivity> internalResponse =
				new InternalResultsResponse<SuspiciousActivity>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getSuspiciousActivityBAC().fetchSuspiciousActivityByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

}
