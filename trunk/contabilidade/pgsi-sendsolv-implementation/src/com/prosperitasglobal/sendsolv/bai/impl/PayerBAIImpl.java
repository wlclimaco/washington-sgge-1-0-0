package com.prosperitasglobal.sendsolv.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IPayerBAC;
import com.prosperitasglobal.sendsolv.bai.IPayerBAI;
import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.request.PayerInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.PayerResponse;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;

/**
 * A class that implements methods for working with payer functions.
 */
public class PayerBAIImpl implements IPayerBAI
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	public static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = PayerBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PayerBAIImpl.class);

	/** The Constant PROSPERITASGLOBAL_BASE_PAYERVALIDATOR_ID_REQUIRED. */
	public static final String PROSPERITASGLOBAL_BASE_PAYERVALIDATOR_ID_REQUIRED =
			"sendsolv.base.payervalidator.payerid.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PAYERVALIDATOR_CODE_REQUIRED. */
	public static final String PROSPERITASGLOBAL_BASE_PAYERVALIDATOR_COUNTRYCODE_REQUIRED =
			"sendsolv.base.payervalidator.countrycode.required";

	/** The payer bac. */
	private IPayerBAC payerBAC; // injected by Spring through setter

	/** The payer inquiry request validation controller. */
	private ValidationController payerInquiryRequestValidationController;

	/**
	 * Fetch the {@link Payer} using the paged inquiry.
	 *
	 * @param request The request.
	 * @param response The {@link PayerResponse} containing the current state of the process.
	 * @return The updated {@link PayerResponse} containing the next state of the process.
	 */
	private PayerResponse fetchPaged(PayerInquiryRequest request, PayerResponse response)
	{
		InternalResultsResponse<Payer> internalResponse = new InternalResultsResponse<Payer>();

		ValidationContext context =
				new ValidationContext(PayerInquiryRequest.class.getSimpleName(), request,
						ValidationContextIndicator.FETCH);

		if (getPayerInquiryRequestValidationController().validate(context))
		{
			internalResponse = getPayerBAC().fetchPayerByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		return response;
	}

	/**
	 * Spring Sets the payer bac.
	 *
	 * @param payerBAC the new payer bac
	 */
	public void setPayerBAC(IPayerBAC payerBAC)
	{
		this.payerBAC = payerBAC;
	}

	/**
	 * Gets the payer bac.
	 *
	 * @return the payer bac
	 */
	public IPayerBAC getPayerBAC()
	{
		return payerBAC;
	}

	/**
	 * Gets the payer inquiry request validation controller.
	 *
	 * @return The payer inquiry request validation controller
	 */
	public ValidationController getPayerInquiryRequestValidationController()
	{
		return payerInquiryRequestValidationController;
	}

	/**
	 * Sets the payer inquiry request validation controller.
	 *
	 * @param payerInquiryRequestValidationController The payer inquiry request validation controller to set.
	 */
	public void setPayerInquiryRequestValidationController(ValidationController payerInquiryRequestValidationController)
	{
		this.payerInquiryRequestValidationController = payerInquiryRequestValidationController;
	}

	@Override
	public PayerResponse fetchPayerById(FetchByIdRequest request)
	{
		PayerResponse response = new PayerResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_PAYERVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getPayerBAC().fetchPayerById(request);
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
	 * Fetch all payers matching the information contained in the criteria.
	 *
	 * @param request The request.
	 * @return The {@link PayerResponse} containing the {@link java.util.List} of {@link Payer}'s that matched the
	 *         criteria. Also contains information about whether the action was successful or not. If the action was not
	 *         successful, no {@link Payer} will be contained in the response.
	 */
	@Override
	public PayerResponse fetchPayerByRequest(PayerInquiryRequest request)
	{
		PayerResponse response = new PayerResponse();
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
}
