package com.prosperitasglobal.sendsolv.callingcard.bai.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.callingcard.bac.ICallingCardBAC;
import com.prosperitasglobal.sendsolv.callingcard.bai.ICallingCardBAI;
import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.prosperitasglobal.sendsolv.callingcard.model.request.CallingCardMaintenanceRequest;
import com.prosperitasglobal.sendsolv.callingcard.model.response.CallingCardMaintenanceResponse;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.Response;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;

public class CallingCardBAIImpl implements ICallingCardBAI
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	public static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = CallingCardBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CallingCardBAIImpl.class);

	/** The Constant PROSPERITASGLOBAL_BASE_PAYERVALIDATOR_ID_REQUIRED. */
	public static final String PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_ID_REQUIRED =
			"sendsolv.base.callingcardevalidator.currency.purchase.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	private static final String PROSPERITASGLOBAL_BASE_OL_ERROR =
			"sendsolv.base.optimistic.locking.error";

	/** The currencyPurchase bac. */
	private ICallingCardBAC callingCardBAC; // injected by Spring through setter

	/** The validation controller. */
	private ValidationController callingCardValidationController;

	/*
	 * Assign - INSERT
	 * Refill - UPDATE
	 * Balance - FETCH BY ID
	 * Fetch More - FETCH
	 * Get Available - FETCH
	 */

	@Override
	public CallingCardMaintenanceResponse assignCard(CallingCardMaintenanceRequest request)
	{
		CallingCardMaintenanceResponse response = new CallingCardMaintenanceResponse();
		try
		{
			// UPDATE sets the modify user/date
			if (!ValidationUtil.isNull(request.getCallingCardInfo()))
			{
				request.getCallingCardInfo().setModelAction(PersistanceActionEnum.UPDATE);
			}
			response = process(ValidationContextIndicator.FETCH_BY_ID, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CallingCardMaintenanceResponse refillCard(CallingCardMaintenanceRequest request)
	{
		CallingCardMaintenanceResponse response = new CallingCardMaintenanceResponse();
		try
		{
			// UPDATE sets the modify user/date
			if (!ValidationUtil.isNull(request.getCallingCardInfo()))
			{
				request.getCallingCardInfo().setModelAction(PersistanceActionEnum.UPDATE);
			}
			response = process(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CallingCardMaintenanceResponse getCardBalance(CallingCardMaintenanceRequest request)
	{
		CallingCardMaintenanceResponse response = new CallingCardMaintenanceResponse();
		try
		{
			response = process(ValidationContextIndicator.FETCH_BY_ID, PersistanceActionEnum.FETCH, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CallingCardMaintenanceResponse retrieveMorePinNumbers(CallingCardMaintenanceRequest request)
	{
		CallingCardMaintenanceResponse response = new CallingCardMaintenanceResponse();
		try
		{
			response = process(ValidationContextIndicator.FETCH, PersistanceActionEnum.FETCH, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CallingCardMaintenanceResponse fetchAvailablePins(CallingCardMaintenanceRequest request)
	{
		CallingCardMaintenanceResponse response = new CallingCardMaintenanceResponse();
		try
		{
			response = process(ValidationContextIndicator.FETCH, PersistanceActionEnum.NONE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	private CallingCardMaintenanceResponse process(ValidationContextIndicator indicator,
			PersistanceActionEnum persistType,
			CallingCardMaintenanceRequest request)
	{
		CallingCardMaintenanceResponse response = new CallingCardMaintenanceResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(CallingCardInfo.class.getSimpleName(), request.getCallingCardInfo(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getCallingCardValidationController().validate(context))
		{
			// Persist
			internalResponse = doAction(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (CallingCardMaintenanceResponse)handleReturn(response, internalResponse, context.getMessages(), true);
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
	 * Do action.
	 *
	 * @param request the request
	 * @param actionType the update type
	 * @return the internal response
	 */
	private InternalResponse doAction(CallingCardMaintenanceRequest request, PersistanceActionEnum actionType)
	{
		switch (actionType)
		{
			case UPDATE:
				if (ValidationUtil.isNullOrZero(request.getCallingCardInfo().getAmount()))
				{
					return getCallingCardBAC().assignCard(request);
				}
				else
				{
					return getCallingCardBAC().refillCard(request);
				}

			case FETCH:
				if (ValidationUtil.isNullOrZero(request.getCallingCardInfo().getPersonId()))
				{
					return getCallingCardBAC().retrieveMorePinNumbers(request);
				}
				else
				{
					return getCallingCardBAC().getCardBalance(request);
				}

			case NONE:
				return getCallingCardBAC().fetchAvailablePins(request);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	public ICallingCardBAC getCallingCardBAC()
	{
		return callingCardBAC;
	}

	public void setCallingCardBAC(ICallingCardBAC callingCardBAC)
	{
		this.callingCardBAC = callingCardBAC;
	}

	public ValidationController getCallingCardValidationController()
	{
		return callingCardValidationController;
	}

	public void setCallingCardValidationController(ValidationController callingCardValidationController)
	{
		this.callingCardValidationController = callingCardValidationController;
	}
}
