package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IRecipientBAC;
import com.prosperitasglobal.sendsolv.bai.IRecipientBAI;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.request.RecipientInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RecipientMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.RecipientResponse;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.model.response.Response;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class RecipientBAIImpl.
 */
public class RecipientBAIImpl implements IRecipientBAI
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(RecipientBAIImpl.class);

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = RecipientBAIImpl.class.getName();

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	private static final String PROSPERITASGLOBAL_BASE_OL_ERROR =
			"sendsolv.base.optimistic.locking.error";

	/** The Constant PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_ID_REQUIRED =
			"sendsolv.base.recipientvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_PARENT_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_PARENT_ID_REQUIRED =
			"sendsolv.base.recipientvalidator.parent.id.required";

	/** The recipient bac. */
	private IRecipientBAC recipientBAC;

	/** The validation controller. */
	private ValidationController recipientValidationController;

	/** The recipient paged inquiry request validation controller. */
	private ValidationController recipientPagedInquiryRequestValidationController;

	/**
	 * Gets the recipient bac.
	 *
	 * @return the recipient bac
	 */
	public IRecipientBAC getRecipientBAC()
	{
		return recipientBAC;
	}

	/**
	 * Sets the recipient bac.
	 *
	 * @param recipientBAC the recipient bac
	 */
	public void setRecipientBAC(IRecipientBAC recipientBAC)
	{
		this.recipientBAC = recipientBAC;
	}

	/**
	 * Gets the recipient validation controller.
	 *
	 * @return the recipient validation controller
	 */
	public ValidationController getRecipientValidationController()
	{
		return recipientValidationController;
	}

	/**
	 * Sets the recipient validation controller.
	 *
	 * @param recipientValidationController the recipient validation controller
	 */
	public void setRecipientValidationController(ValidationController recipientValidationController)
	{
		this.recipientValidationController = recipientValidationController;
	}

	/**
	 * Gets the recipient paged inquiry request validation controller.
	 *
	 * @return the recipient paged inquiry request validation controller
	 */
	public ValidationController getRecipientPagedInquiryRequestValidationController()
	{
		return recipientPagedInquiryRequestValidationController;
	}

	/**
	 * Sets the recipient paged inquiry request validation controller.
	 *
	 * @param recipientPagedInquiryRequestValidationController the recipient paged inquiry request validation controller
	 */
	public void setRecipientPagedInquiryRequestValidationController(
			ValidationController recipientPagedInquiryRequestValidationController)
	{
		this.recipientPagedInquiryRequestValidationController = recipientPagedInquiryRequestValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.IRecipientBAI#insertRecipient(com.prosperitasglobal.sendsolv.model.request
	 * .RecipientMaintenanceRequest)
	 */
	@Override
	public RecipientResponse insertRecipient(RecipientMaintenanceRequest request)
	{
		RecipientResponse response = new RecipientResponse();
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
	 * com.prosperitasglobal.sendsolv.bai.IRecipientBAI#updateRecipient(com.prosperitasglobal.sendsolv.model.request
	 * .RecipientMaintenanceRequest)
	 */
	@Override
	public RecipientResponse updateRecipient(RecipientMaintenanceRequest request)
	{
		RecipientResponse response = new RecipientResponse();
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
	 * com.prosperitasglobal.sendsolv.bai.IRecipientBAI#deleteRecipient(com.prosperitasglobal.sendsolv.model.request
	 * .RecipientMaintenanceRequest)
	 */
	@Override
	public RecipientResponse deleteRecipient(RecipientMaintenanceRequest request)
	{
		RecipientResponse response = new RecipientResponse();
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
	 * com.prosperitasglobal.sendsolv.bai.IRecipientBAI#fetchRecipientById(com.prosperitasglobal.sendsolv.model.request
	 * .FetchByIdRequest)
	 */
	@Override
	public RecipientResponse fetchRecipientById(FetchByIdRequest request)
	{
		RecipientResponse response = new RecipientResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getRecipientBAC().fetchRecipientById(request);
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
	 * com.prosperitasglobal.sendsolv.bai.IRecipientBAI#fetchRecipientByRequest(com.prosperitasglobal.sendsolv.model
	 * .request.RecipientInquiryRequest)
	 */
	@Override
	public RecipientResponse fetchRecipientByRequest(RecipientInquiryRequest request)
	{
		RecipientResponse response = new RecipientResponse();
		try
		{
			response = fetchPaged(request, response);
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
	 * com.prosperitasglobal.sendsolv.bai.IRecipientBAI#fetchRecipientByOrganization(com.prosperitasglobal.sendsolv.
	 * model.request.RecipientInquiryRequest)
	 */
	@Override
	public RecipientResponse fetchRecipientByOrganization(RecipientInquiryRequest request)
	{
		return fetchRecipientByOrgLoc(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.IRecipientBAI#fetchRecipientByLocation(com.prosperitasglobal.sendsolv.model
	 * .request.RecipientInquiryRequest)
	 */
	@Override
	public RecipientResponse fetchRecipientByLocation(RecipientInquiryRequest request)
	{
		return fetchRecipientByOrgLoc(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bai.IRecipientBAI#applyStatus(com.prosperitasglobal.sendsolv.model.request.
	 * RecipientMaintenanceRequest)
	 */
	@Override
	public RecipientResponse applyStatus(RecipientMaintenanceRequest request)
	{
		RecipientResponse response = new RecipientResponse();
		InternalResponse internalResponse = null;
		try
		{
			// Validate.
			ValidationContext context =
					new ValidationContext(Recipient.class.getSimpleName(), request.getRecipient(),
							ValidationContextIndicator.FETCH_BY_ID);
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

			if (getRecipientValidationController().validate(context))
			{
				internalResponse = getRecipientBAC().applyStatus(request);
			}

			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

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
	 * @return the recipient response
	 */
	private RecipientResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			RecipientMaintenanceRequest request)
	{
		RecipientResponse response = new RecipientResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(Recipient.class.getSimpleName(), request.getRecipient(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getRecipientValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistance(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (RecipientResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistance(RecipientMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getRecipientBAC().insertRecipient(request);

			case UPDATE:
				return getRecipientBAC().updateRecipient(request);

			case DELETE:
				return getRecipientBAC().deleteRecipient(request);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType for Recipient missing!");
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
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 * @return The response.
	 */
	private RecipientResponse fetchPaged(RecipientInquiryRequest request, RecipientResponse response)
	{
		InternalResultsResponse<Recipient> internalResponse = new InternalResultsResponse<Recipient>();

		ValidationContext context =
				new ValidationContext(RecipientInquiryRequest.class.getSimpleName(), request,
						ValidationContextIndicator.FETCH);

		if (getRecipientPagedInquiryRequestValidationController().validate(context))
		{
			internalResponse = getRecipientBAC().fetchRecipientByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (RecipientResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Fetch recipient by org loc.
	 *
	 * @param request the request
	 * @return the recipient response
	 */
	private RecipientResponse fetchRecipientByOrgLoc(RecipientInquiryRequest request)
	{
		RecipientResponse response = new RecipientResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();

			ValidationContext context =
					new ValidationContext(RecipientInquiryRequest.class.getSimpleName(), request,
							ValidationContextIndicator.FETCH);

			// validate fetchId field
			if (ValidationUtil.isNull(request.getParentId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_PARENT_ID_REQUIRED);
			}
			else if (getRecipientPagedInquiryRequestValidationController().validate(context))
			{
				internalResponse = getRecipientBAC().fetchRecipientByRequest(request);
			}

			// Handle the processing for all previous methods regardless of them failing or succeeding.
			response = (RecipientResponse)handleReturn(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

}
