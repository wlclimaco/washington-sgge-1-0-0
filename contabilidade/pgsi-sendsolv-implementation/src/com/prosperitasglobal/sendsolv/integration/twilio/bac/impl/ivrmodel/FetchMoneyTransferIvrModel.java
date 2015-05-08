package com.prosperitasglobal.sendsolv.integration.twilio.bac.impl.ivrmodel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.IMoneyTransferDAC;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecordContext;
import com.prosperitasglobal.sendsolv.integration.twilio.model.SupportedLanguage;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.prosperitasglobal.sendsolv.integration.twilio.util.IvrCommonRoutines;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMessageUtil;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class FetchMoneyTransferIvrModel.
 */
public class FetchMoneyTransferIvrModel extends BinaryIvrModel
{
	private static final String REPEAT_TRANSACTION = "8";

	private static final String NEXT_TRANSACTION = "6";

	private static final String FX_RATE_FORMAT = "#####0.####";

	private static final String MONETARY_VALUE_FORMAT = "#####0.##";

	private static final String PAUSE_BETWEEN_NUMBERS = ", ";

	private String entryPointMenu;

	private String entryPointDetail;

	/** The money transfer dac. */
	private IMoneyTransferDAC moneyTransferDAC;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FetchMoneyTransferIvrModel.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.integration.twilio.bac.impl.actions.IvrAction#resolveAction(com.prosperitasglobal
	 * .sendsolv.integration.twilio.model.request.IvrRequest,
	 * com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord)
	 */
	@Override
	public InternalResultsResponse<String> resolveAction(IvrRequest ivrRequest, CallRecord callRecord)
	{
		String defaultLanguageText = "en";
		MoneyTransfer moneyTransfer = null;

		InternalResultsResponse<String> response = new InternalResultsResponse<String>();

		Integer ivrOption = Integer.parseInt(ivrRequest.getDigits());

		// do different things depending on current action and previous action
		// previous = "prior_transaction_menu"
		// <entry key="dynamic">
		// <ref bean="fetchMoneyTransferAction"/>
		// 6 = priorPeriodAction
		// 7 = internationalCallAction
		// 8 = repeatAction
		// 9 = previousMenuAction

		// previous = "read_prior_transaction"
		// To hear your next transaction information, Press 6
		// To repeat this transaction information, Press 8
		// To return to the previous menu, Press 9

		// Define language
		SupportedLanguage currentLanguage =
				IvrCommonRoutines.getCurrentLanguage(callRecord.getLanguage(),
						ivrRequest.getSupportedLanguagesList());

		CallRecordContext callRecordContext = new CallRecordContext();

		// This model could be called from 2 different points: 1-the Prior Transaction Menu or the 2-Transaction Detail
		// 1- Coming from prior_transaction_menu
		if (ivrRequest.getIvrController().equals(getEntryPointMenu()))
		{
			callRecord.setCurrentIvrOption(ivrOption);
			callRecordContext.setIvrOption(ivrOption);
			callRecordContext.setParentId(callRecord.getId());

			InternalResultsResponse<CallRecordContext> callRecordContextResponse =
					getCallRecordDAC().fetchCallRecordContextByIvrOption(callRecordContext);

			if (!callRecordContextResponse.isInError())
			{
				callRecordContext = callRecordContextResponse.getFirstResult();
			}
			else
			{
				response.merge(callRecordContextResponse);
				if (LOG.isDebugEnabled())
				{
					LOG.debug("Cannot retrieve CallRecordContext for ivrOption = " + ivrOption
							+ " and callRecord.getId = "
							+ callRecord.getId());
				}
			}
			// <entry key="6">
			// <ref bean="priorPeriodAction"/>
		}
		// 2 - Coming from read_prior_transaction
		else if (ivrRequest.getIvrController().equals(getEntryPointDetail()))
		{
			int currentIvrOption = callRecord.getCurrentIvrOption();

			// To hear your next transaction information, Press 6
			if (ivrRequest.getDigits().equals(NEXT_TRANSACTION))
			{
				// If out of transactions, show disclaimer. Otherwise, show transaction (next or current)
				if ((currentIvrOption + 1) > callRecord.getCallRecordContextList().size())
				{
					response =
							createResponse(ivrRequest, getAdditionalViewName(), getAdditionalNextController(),
									defaultLanguageText,
									createAdditionalAudioFileToken(ivrRequest, currentLanguage),
									response);
				}
				else
				{
					callRecordContext = callRecord.getCallRecordContextList().get(currentIvrOption);
					callRecord.setCurrentIvrOption(currentIvrOption + 1);
				}
			}
			// To repeat this transaction information, Press 8
			else if (ivrRequest.getDigits().equals(REPEAT_TRANSACTION))
			{
				callRecordContext = callRecord.getCallRecordContextList().get(currentIvrOption - 1);
				callRecord.setCurrentIvrOption(currentIvrOption);
			}
			else
			{
				callRecord.setCurrentIvrOption(currentIvrOption);
			}
		}

		if (!ValidationUtil.isNullOrZero(callRecordContext.getMoneyTransferId()))
		{
			moneyTransfer = retrieveMoneyTransferInfo(callRecordContext.getMoneyTransferId(), response);

			if (!response.isInError())
			{
				// Update database with ivr option
				InternalResponse updateResponse = getCallRecordDAC().updateCallRecord(callRecord);

				if (!updateResponse.isInError())
				{
					response =
							createResponse(
									ivrRequest,
									getViewName(),
									getNextController(),
									currentLanguage.getLanguage(),
									createAdditionalTokens(moneyTransfer, callRecordContext,
											currentLanguage.getLanguage()), response);
				}
				else
				{
					response =
							createResponse(ivrRequest, getExceptionViewName(), getAdditionalNextController(),
									defaultLanguageText,
									null,
									response);
				}
			}
			else
			{
				response =
						createResponse(ivrRequest, getExceptionViewName(), getAdditionalNextController(),
								defaultLanguageText, null,
								response);
			}
		}

		return response;
	}

	/**
	 * Retrieve money transfer info.
	 *
	 * @param callRecord the call record
	 * @param ivrOption the ivr option
	 * @param internalResponse the internal response
	 * @return the money transfer
	 */
	private MoneyTransfer retrieveMoneyTransferInfo(Integer moneyTransferId,
			InternalResponse internalResponse)
	{
		MoneyTransfer moneyTransfer = null;

		InternalResultsResponse<MoneyTransfer> moneyTransfeResponse =
				getMoneyTransferDAC().fetchMoneyTransferById(moneyTransferId);

		if (!moneyTransfeResponse.isInError())
		{
			moneyTransfer = moneyTransfeResponse.getFirstResult();
		}
		else
		{
			internalResponse.merge(moneyTransfeResponse);
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Cannot retrieve MoneyTransfer for id = " + moneyTransferId);
			}
		}

		return moneyTransfer;
	}

	/**
	 * Replace parameters.
	 *
	 * @param message the message
	 * @param nextAction the next action
	 * @param ivrRequest the ivr request
	 * @param transferSummaryList the transfer summary list
	 * @return the string
	 */
	private Map<String, String> createAdditionalTokens(MoneyTransfer moneyTransfer,
			CallRecordContext callRecordContext, String languageCode)
			{
		Map<String, String> tokens = new HashMap<String, String>();
		tokens.put("RECIPIENT",
				callRecordContext.getRecipientFirstName() + " " + callRecordContext.getRecipientLastName());

		// The following code adds spaces between the numbers
		tokens.put("CONFIRMATION_NUMBER", moneyTransfer.getConfirmationNumber().replace("", PAUSE_BETWEEN_NUMBERS)
				.trim());

		tokens.put("PAYROLL_DEDUCTION", calculatePayrollDeduction(moneyTransfer));

		tokens.put("TRANSACTION_FEE", formatValue(moneyTransfer.getOriginFlatFee()));

		tokens.put("NET_TRANSFER", formatValue(moneyTransfer.getOriginAmount().getAmount()));

		tokens.put("EFFECTIVE_RATE", formatFxRate(moneyTransfer.getForeignExchangeRateCustomer()));

		tokens.put("DESTINATION_AMOUNT", formatValue(moneyTransfer.getDestinationAmount().getAmount()));

		tokens.put("CURRENCY_NAME", moneyTransfer.getDestinationAmount().getCurrency().getName());

		tokens.put("STATUS", QATMessageUtil.getMessage(moneyTransfer.getCurrentStatus().getStatus().getLabelKey(),
				null, moneyTransfer
						.getCurrentStatus().getStatus().name(), new Locale(languageCode)));

		return tokens;
			}

	private Map<String, String> createAdditionalAudioFileToken(IvrRequest ivrRequest, SupportedLanguage currentLanguage)
	{
		Map<String, String> tokens = new HashMap<String, String>();

		// This is where the audio files live
		String resourceFullpath =
				ivrRequest.getMainUrl() + ivrRequest.getStaticResourcePath() + "/" + currentLanguage.getLanguage()
				+ "/" + getAudioFileName();

		tokens.put("AUDIO_PATH", resourceFullpath);

		return tokens;
	}

	/**
	 * Calculate payroll deduction.
	 * origin_amount +flat_fee_amount_origin_currency- discount_amount
	 *
	 * @param moneyTransfer the money transfer
	 * @return the big decimal
	 */
	private String calculatePayrollDeduction(MoneyTransfer moneyTransfer)
	{
		BigDecimal result = moneyTransfer.getOriginAmount().getAmount().add(moneyTransfer.getOriginFlatFee())
				.subtract(moneyTransfer.getDiscountAmount());

		return formatValue(result);
	}

	private String formatValue(BigDecimal value)
	{
		return new DecimalFormat(MONETARY_VALUE_FORMAT).format(value);
	}

	private String formatFxRate(BigDecimal value)
	{
		return new DecimalFormat(FX_RATE_FORMAT).format(value);
	}

	/**
	 * Gets the money transfer dac.
	 *
	 * @return the money transfer dac
	 */
	public IMoneyTransferDAC getMoneyTransferDAC()
	{
		return moneyTransferDAC;
	}

	/**
	 * Sets the money transfer dac.
	 *
	 * @param moneyTransferDAC the new money transfer dac
	 */
	public void setMoneyTransferDAC(IMoneyTransferDAC moneyTransferDAC)
	{
		this.moneyTransferDAC = moneyTransferDAC;
	}

	public String getEntryPointMenu()
	{
		return entryPointMenu;
	}

	public void setEntryPointMenu(String entryPointMenu)
	{
		this.entryPointMenu = entryPointMenu;
	}

	public String getEntryPointDetail()
	{
		return entryPointDetail;
	}

	public void setEntryPointDetail(String entryPointDetail)
	{
		this.entryPointDetail = entryPointDetail;
	}
}
