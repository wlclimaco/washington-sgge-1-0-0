package com.prosperitasglobal.sendsolv.ach.bac.impl;

import java.math.BigDecimal;

import com.prosperitasglobal.cbof.model.Currency;
import com.prosperitasglobal.sendsolv.ach.bac.IAchCommonBAC;
import com.prosperitasglobal.sendsolv.ach.bac.IAchTransactionBAC;
import com.prosperitasglobal.sendsolv.ach.bac.IAchTxnOrchestrationBAC;
import com.prosperitasglobal.sendsolv.ach.bad.AchBAD;
import com.prosperitasglobal.sendsolv.ach.model.AchStatus;
import com.prosperitasglobal.sendsolv.ach.model.request.AchTransactionRequest;
import com.prosperitasglobal.sendsolv.callingcard.bac.ICallingCardBAC;
import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.prosperitasglobal.sendsolv.callingcard.model.request.CallingCardMaintenanceRequest;
import com.prosperitasglobal.sendsolv.dac.IPayerDAC;
import com.prosperitasglobal.sendsolv.model.CurrencyAvailability;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferAmount;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum;
import com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction;
import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.ProductPlanApplicability;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferMaintenanceRequest;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.request.Request;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * Implementation of the {@link IAchTxnOrchestrationBAC} interface that contains operations involved in orchestrating a
 * {@link MoneyTransfer} and the communication with the automated clearing house.
 */
public class AchTxnOrchestrationBACImpl implements IAchTxnOrchestrationBAC
{
	private IAchTransactionBAC achTransactionBAC;

	private IPayerDAC payerDAC;

	private ICallingCardBAC callingCardBAC;

	/**
	 * This method will create a {@link MoneyTransferTransaction} for the {@link MoneyTransfer} in the
	 * {@link MoneyTransferMaintenanceRequest}. The type of transaction is determined by the parameter
	 * <code>typeOfRequest</code>.
	 *
	 * @param request The request.
	 * @param typeOfRequest The type of request.
	 * @return The {@link InternalResultsResponse} containing the {@MoneyTransfer} specified in the
	 *         {@link MoneyTransferMaintenanceRequest}. The {@link MoneyTransfer} will contain a new
	 *         submit {@link MoneyTransferStatus} attached (actual type determined by the <code>typeOfRequest</code>
	 *         parameter). The new status attached will contain a {@link MoneyTransferTransaction} attached. This is the
	 *         transaction data that will be used for sending data to an automated clearing house.
	 */
	protected InternalResultsResponse<MoneyTransfer> processCreateMoneyTransferTransactionRequest(
			MoneyTransferMaintenanceRequest request, MoneyTransferStatusEnum typeOfRequest)
			{
		InternalResultsResponse<MoneyTransfer> response = new InternalResultsResponse<MoneyTransfer>();
		response.addResult(request.getMoneyTransfer());

		MoneyTransferTransaction transaction = AchBAD.createMoneyTransferTransaction(request);
		MoneyTransferStatus transferStatus = AchBAD.createMoneyTransferStatus(request, transaction, typeOfRequest);
		request.getMoneyTransfer().addMoneyTransferStatus(transferStatus);

		return response;
			}

	/**
	 * This method will submit the {@link MoneyTransfer} to the automated clearing house.
	 *
	 * @param request The request containing the {@link MoneyTransfer} to submit.
	 * @return The {@link InternalResultsResponse} containing the results of the automated clearing house processing.
	 */
	protected InternalResultsResponse<MoneyTransfer> processSubmitMoneyTransferTransactionRequest(
			MoneyTransferMaintenanceRequest request)
	{
		InternalResultsResponse<MoneyTransfer> response = new InternalResultsResponse<MoneyTransfer>();
		response.addResult(request.getMoneyTransfer());

		AchTransactionRequest achRequest = new AchTransactionRequest();
		MoneyTransferTransaction transaction =
				request.getMoneyTransfer().getCurrentStatus().getMoneyTransferTransaction();
		achRequest.setMoneyTransferTransaction(transaction);
		achRequest.setUserContext(request.getUserContext());

		InternalResultsResponse<AchStatus> achResponse = null;

		MoneyTransferStatusEnum submitStatus = request.getMoneyTransfer().getCurrentStatus().getStatus();

		switch (submitStatus)
		{
			case CANCELLATION_SUBMITTED:
				achResponse = getAchTransactionBAC().cancel(achRequest);
				break;

			case MODIFICATION_SUBMITTED:
				achResponse = getAchTransactionBAC().modify(achRequest);
				break;

			default:
				achResponse = getAchTransactionBAC().transfer(achRequest);
				break;
		}

		AchStatus achStatus = achResponse.getFirstResult();

		if (ValidationUtil.isNull(achStatus))
		{
			response.merge(achResponse);
			return response;
		}

		MoneyTransferStatus transferStatus =
				AchBAD.createMoneyTransferStatus(request, achStatus.getMoneyTransferStatus());

		if (ValidationUtil.isNull(transferStatus.getStatus()))
		{
			throw new IllegalStateException(getClass().getSimpleName()
					+ "No status was set from call to ACH!");
		}

		// In the event we don't have access to the calling card URL don't inject the BAC
		if (!ValidationUtil.isNull(getCallingCardBAC()))
		{
			// Only refill a card when an order is submitted and ACH accepts it
			if (MoneyTransferStatusEnum.ORDER_SUBMITTED.equals(submitStatus)
					&& MoneyTransferStatusEnum.NOT_PAID.equals(achStatus.getMoneyTransferStatus()))
			{
				CallingCardMaintenanceRequest callingCardRequest = new CallingCardMaintenanceRequest();
				CallingCardInfo callingCardInfo = new CallingCardInfo();
				callingCardInfo.setPersonId(request.getMoneyTransfer().getTransferSetting().getMemberId());
				callingCardInfo.setAmount(request.getMoneyTransfer().getTransferSetting().getPlanCategory()
						.getCallCreditAllowance());

				callingCardRequest.setCallingCardInfo(callingCardInfo);
				InternalResultsResponse<CallingCardInfo> callingCardResponse =
						getCallingCardBAC().refillCard(callingCardRequest);
				if (callingCardResponse.isInError())
				{
					response.merge(callingCardResponse);
					return response;
				}
			}
		}

		transferStatus.setResponse(achStatus.getAchResponse());
		request.getMoneyTransfer().addMoneyTransferStatus(transferStatus);

		return response;
	}

	/**
	 * Get the implementation of the {@link IAchTransactionBAC} interface. Injected by Spring.
	 *
	 * @return The implementation.
	 */
	public IAchTransactionBAC getAchTransactionBAC()
	{
		return achTransactionBAC;
	}

	/**
	 * Set the implementation of the {@link IAchTransactionBAC} interface. Injected by Spring.
	 *
	 * @param achTransactionBAC The implementation to set.
	 */
	public void setAchTransactionBAC(IAchTransactionBAC achTransactionBAC)
	{
		this.achTransactionBAC = achTransactionBAC;
	}

	/**
	 * Get the implementation of the {@link IPayerDAC} interface. Injected by Spring.
	 *
	 * @return
	 */
	public IPayerDAC getPayerDAC()
	{
		return payerDAC;
	}

	/**
	 * Set the implementation of the {@link IPayerDAC} interface. Injected by Spring.
	 *
	 * @param payerDAC
	 */
	public void setPayerDAC(IPayerDAC payerDAC)
	{
		this.payerDAC = payerDAC;
	}

	/**
	 * @return the callingCardBAC
	 */
	public ICallingCardBAC getCallingCardBAC()
	{
		return callingCardBAC;
	}

	/**
	 * @param callingCardBAC the callingCardBAC to set
	 */
	public void setCallingCardBAC(ICallingCardBAC callingCardBAC)
	{
		this.callingCardBAC = callingCardBAC;
	}

	/**
	 * This method will create a new {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction} using the
	 * {@link MoneyTransfer} data as the source for the information. The new
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction} will be attached to a new
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatus} with a status of
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum#ORDER_SUBMITTED}.
	 *
	 * @param request The {@link MoneyTransferMaintenanceRequest} containing the {@link MoneyTransfer} that will be the
	 *            basis for creating the {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction}.
	 * @return The {@link InternalResultsResponse} containing the {@link MoneyTransfer} found in the
	 *         {@link MoneyTransferMaintenanceRequest}. The {@link MoneyTransfer} will have a new
	 *         {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatus} added with a status
	 *         {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum#ORDER_SUBMITTED} and it will contain
	 *         the {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction}.
	 */
	@Override
	public InternalResultsResponse<MoneyTransfer> createCancelMoneyTransferTransaction(
			MoneyTransferMaintenanceRequest request)
	{
		return processCreateMoneyTransferTransactionRequest(request, MoneyTransferStatusEnum.CANCELLATION_SUBMITTED);
	}

	/**
	 * This method will create a new {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction} using the
	 * {@link MoneyTransfer} data as the source for the information. The new
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction} will be attached to a new
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatus} with a status of
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum#MODIFICATION_SUBMITTED}.
	 *
	 * @param request The {@link MoneyTransferMaintenanceRequest} containing the {@link MoneyTransfer} that will be the
	 *            basis for creating the {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction}.
	 * @return The {@link InternalResultsResponse} containing the {@link MoneyTransfer} found in the
	 *         {@link MoneyTransferMaintenanceRequest}. The {@link MoneyTransfer} will have a new
	 *         {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatus} added with a status
	 *         {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum#MODIFICATION_SUBMITTED} and it will
	 *         contain the {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction}.
	 */
	@Override
	public InternalResultsResponse<MoneyTransfer> createModifyMoneyTransferTransaction(
			MoneyTransferMaintenanceRequest request)
			{
		return processCreateMoneyTransferTransactionRequest(request, MoneyTransferStatusEnum.MODIFICATION_SUBMITTED);
			}

	/**
	 * This method will create a new {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction} using the
	 * {@link MoneyTransfer} data as the source for the information. The new
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction} will be attached to a new
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatus} with a status of
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum#ORDER_SUBMITTED}.
	 *
	 * @param request The {@link MoneyTransferMaintenanceRequest} containing the {@link MoneyTransfer} that will be the
	 *            basis for creating the {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction}.
	 * @return The {@link InternalResultsResponse} containing the {@link MoneyTransfer} found in the
	 *         {@link MoneyTransferMaintenanceRequest}. The {@link MoneyTransfer} will have a new
	 *         {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatus} added with a status
	 *         {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum#ORDER_SUBMITTED} and it will contain
	 *         the {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction}.
	 */
	@Override
	public InternalResultsResponse<MoneyTransfer> createTransferMoneyTransferTransaction(
			MoneyTransferMaintenanceRequest request)
			{
		Request achRequest = new Request();
		achRequest.setUserContext(request.getUserContext());

		if (ValidationUtil.isNullOrEmpty(request.getMoneyTransfer().getConfirmationNumber()))
		{
			InternalResponse caResponse = updateCurrencyAvailability(request);

			if (caResponse.isInError())
			{
				if (caResponse.getStatus().equals(Status.ValidationError))
				{
					return processCreateMoneyTransferTransactionRequest(request,
							MoneyTransferStatusEnum.APPROVED_NOT_FUNDED);
				}
				else
				{
					InternalResultsResponse<MoneyTransfer> response = new InternalResultsResponse<MoneyTransfer>();
					response.merge(caResponse);
					response.addResult(request.getMoneyTransfer());
					return response;
				}
			}

			InternalResultsResponse<String> achIdentifierResponse =
					getAchTransactionBAC().obtainNewAchTransactionIdentifier(achRequest);

			if (achIdentifierResponse.isInError())
			{
				InternalResultsResponse<MoneyTransfer> response = new InternalResultsResponse<MoneyTransfer>();
				response.addResult(request.getMoneyTransfer());
				response.merge(achIdentifierResponse);
				response.addMessage(IAchCommonBAC.SENDSOLV_INTEGRATION_ACH_EXCEPTION_PGSI_ERROR,
						MessageSeverity.Fatal, MessageLevel.None);
				return response;
			}

			String achIdentifier = achIdentifierResponse.getFirstResult();

			request.getMoneyTransfer().setConfirmationNumber(achIdentifier);
		}

		return processCreateMoneyTransferTransactionRequest(request, MoneyTransferStatusEnum.ORDER_SUBMITTED);
			}

	/**
	 * This method will submit the {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction} found on the
	 * most current {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatus} of the {@link MoneyTransfer} found
	 * in the request.
	 *
	 * @param request The {@link MoneyTransferMaintenanceRequest} containing the {@link MoneyTransfer} that will be the
	 *            submitted to the automated clearing house for processing.
	 * @return The {@link InternalResultsResponse} containing the {@link MoneyTransfer} found in the
	 *         {@link MoneyTransferMaintenanceRequest}. The {@link MoneyTransfer} will be updated with the results of
	 *         the automated clearing house processing. To check if the system had problems, check the
	 *         {@link InternalResultsResponse}. To check the results from the automated clearing house processing, use
	 *         method {@link MoneyTransfer#getCurrentStatus()} to get the most current status. On that status,
	 *         interrogate the {@link com.prosperitasglobal.sendsolv.model.response.MoneyTransferProcessingResponse}.
	 *         That will contain the results of the automated clearing house processing.
	 * @see InternalResultsResponse
	 * @see com.prosperitasglobal.sendsolv.model.response.MoneyTransferProcessingResponse
	 */
	@Override
	public InternalResultsResponse<MoneyTransfer> submitMoneyTransferTransaction(
			MoneyTransferMaintenanceRequest request)
	{
		return processSubmitMoneyTransferTransactionRequest(request);
	}

	/**
	 * This method will update the cumulative debit property of the CurrencyAvailability
	 * for the payer and currency in the MoneyTransfer. It also checks if the payer is
	 * a payer requiring prefunding and sets a ValidationError if so and the cumulative cre
	 * credit amount is less than the cumulative debit amount.
	 *
	 * @param request
	 * @return InternalResponse
	 */
	private InternalResponse updateCurrencyAvailability(MoneyTransferMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();

		CurrencyAvailability availability = obtainCurrencyAvailability(request);

		if (ValidationUtil.isNull(availability) || availability.getModelAction().equals(PersistanceActionEnum.NONE))
		{
			return response;
		}

		Payer payer = request.getMoneyTransfer().getTransferSetting().getProductPlanApplicability().getPayer();

		if (!payer.isPostfundAllowed())
		{
			BigDecimal difference =
					availability.getCurrencyDebitCumulative().subtract(availability.getCurrencyCreditCumulative());

			if (difference.compareTo(BigDecimal.ZERO) > 0)
			{
				response.setStatus(Status.ValidationError);

				return response;
			}
		}

		return getPayerDAC().updatePayer(payer);
	}

	/**
	 * Looks for a CurrencyAvailability in the Payer's List for the Currency of the MoneyTransfer
	 * and returns it. If one is not found for the currency, a new one is created.
	 *
	 * @param request
	 * @return CurrencyAvailability
	 */
	private CurrencyAvailability obtainCurrencyAvailability(MoneyTransferMaintenanceRequest request)
	{
		MoneyTransfer transfer = request.getMoneyTransfer();

		if (ValidationUtil.isNull(transfer))
		{
			return null;
		}

		TransferSetting transferSetting = transfer.getTransferSetting();

		if (ValidationUtil.isNull(transferSetting))
		{
			return null;
		}

		ProductPlanApplicability applicability = transferSetting.getProductPlanApplicability();

		if (ValidationUtil.isNull(applicability))
		{
			return null;
		}

		Payer payer = applicability.getPayer();

		if (ValidationUtil.isNull(payer))
		{
			return null;
		}

		MoneyTransferAmount destinationAmount = transfer.getDestinationAmount();

		if (ValidationUtil.isNull(destinationAmount))
		{
			return null;
		}

		Currency currency = destinationAmount.getCurrency();

		if (ValidationUtil.isNull(currency))
		{
			return null;
		}

		String currencyCode = currency.getCode();

		if (ValidationUtil.isNullOrEmpty(currency.getCode()))
		{
			return null;
		}

		if (ValidationUtil.isNull(destinationAmount.getAmount())
				|| destinationAmount.getAmount().equals(BigDecimal.ZERO))
		{
			return null;
		}

		if (!ValidationUtil.isNullOrEmpty(payer.getCurrencyAvailabilityList()))
		{
			for (CurrencyAvailability availability : payer.getCurrencyAvailabilityList())
			{
				if (currencyCode.equals(availability.getCurrency().getCode()))
				{
					if (availability.addToCurrencyDebitCumulative(destinationAmount.getAmount()))
					{
						availability.setModelAction(PersistanceActionEnum.UPDATE);
					}

					return availability;
				}
			}
		}

		CurrencyAvailability availability = new CurrencyAvailability();

		availability.setPayerId(payer.getId());

		availability.setCurrencyDebitCumulative(destinationAmount.getAmount());
		availability.setCurrencyCreditCumulative(BigDecimal.ZERO);
		availability.setEffectiveForeignExchangeRate(BigDecimal.ZERO);
		availability.setCurrency(currency);

		availability.setModelAction(PersistanceActionEnum.INSERT);
		availability.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		availability.setCreateUser(request.getUserContext().getUserId());

		payer.getCurrencyAvailabilityList().add(availability);

		return availability;
	}
}
