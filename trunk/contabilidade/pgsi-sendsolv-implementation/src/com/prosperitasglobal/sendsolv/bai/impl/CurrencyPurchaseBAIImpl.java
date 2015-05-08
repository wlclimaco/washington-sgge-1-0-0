package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.ICurrencyPurchaseBAC;
import com.prosperitasglobal.sendsolv.bai.ICurrencyPurchaseBAI;
import com.prosperitasglobal.sendsolv.model.CurrencyPurchase;
import com.prosperitasglobal.sendsolv.model.request.CurrencyPurchaseMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.CurrencyPurchaseResponse;
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

public class CurrencyPurchaseBAIImpl implements ICurrencyPurchaseBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	public static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = CurrencyPurchaseBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CurrencyPurchaseBAIImpl.class);

	/** The Constant PROSPERITASGLOBAL_BASE_PAYERVALIDATOR_ID_REQUIRED. */
	public static final String PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_ID_REQUIRED =
			"sendsolv.base.currencypurchasevalidator.currency.purchase.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	private static final String PROSPERITASGLOBAL_BASE_OL_ERROR =
			"sendsolv.base.optimistic.locking.error";

	/** The currencyPurchase bac. */
	private ICurrencyPurchaseBAC currencyPurchaseBAC; // injected by Spring through setter

	/** The validation controller. */
	private ValidationController currencyPurchaseValidationController;

	@Override
	public CurrencyPurchaseResponse fetchCurrencyPurchaseById(FetchByIdRequest request)
	{
		CurrencyPurchaseResponse response = new CurrencyPurchaseResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getCurrencyPurchaseBAC().fetchCurrencyPurchaseById(request);
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

	@Override
	public CurrencyPurchaseResponse insertCurrencyPurchase(CurrencyPurchaseMaintenanceRequest request)
	{
		CurrencyPurchaseResponse response = new CurrencyPurchaseResponse();
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

	@Override
	public CurrencyPurchaseResponse deleteCurrencyPurchase(CurrencyPurchaseMaintenanceRequest request)
	{
		CurrencyPurchaseResponse response = new CurrencyPurchaseResponse();
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

	@Override
	public CurrencyPurchaseResponse updateCurrencyPurchase(CurrencyPurchaseMaintenanceRequest request)
	{
		CurrencyPurchaseResponse response = new CurrencyPurchaseResponse();
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

	/**
	 * Gets the currencyPurchase bac.
	 *
	 * @return the currencyPurchase bac
	 */
	public ICurrencyPurchaseBAC getCurrencyPurchaseBAC()
	{
		return currencyPurchaseBAC;
	}

	private CurrencyPurchaseResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			CurrencyPurchaseMaintenanceRequest request)
	{
		CurrencyPurchaseResponse response = new CurrencyPurchaseResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(CurrencyPurchase.class.getSimpleName(), request.getCurrencyPurchase(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getCurrencyPurchaseValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistance(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (CurrencyPurchaseResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Spring Sets the currencyPurchase bac.
	 *
	 * @param currencyPurchaseBAC the new currencyPurchase bac
	 */
	public void setCurrencyPurchaseBAC(ICurrencyPurchaseBAC currencyPurchaseBAC)
	{
		this.currencyPurchaseBAC = currencyPurchaseBAC;
	}

	/**
	 * Get currencyPurchase validation controller.
	 *
	 * @return the validation controller
	 */
	public ValidationController getCurrencyPurchaseValidationController()
	{
		return currencyPurchaseValidationController;
	}

	/**
	 * Spring sets the currencyPurchase validation controller.
	 *
	 * @param currencyPurchaseValidationController the new validation controller
	 */
	public void setCurrencyPurchaseValidationController(ValidationController currencyPurchaseValidationController)
	{
		this.currencyPurchaseValidationController = currencyPurchaseValidationController;
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
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistance(CurrencyPurchaseMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCurrencyPurchaseBAC().insertCurrencyPurchase(request);

			case UPDATE:
				return getCurrencyPurchaseBAC().updateCurrencyPurchase(request);

			case DELETE:
				return getCurrencyPurchaseBAC().deleteCurrencyPurchase(request);

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
