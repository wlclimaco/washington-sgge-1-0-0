package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.model.CurrencyPurchase;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CurrencyPurchaseValidator.
 */
public class CurrencyPurchaseValidator implements IValidator
{
	/** The Constant PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_CURRENCYPURCHASE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_CURRENCYPURCHASE_REQUIRED =
			"sendsolv.base.currencypurchasevalidator.currency.purchase.required";

	/** The Constant PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_ID_REQUIRED =
			"sendsolv.base.currencypurchasevalidator.currency.purchase.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_AMOUNT_PURCHASED_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_AMOUNT_PURCHASED_REQUIRED =
			"sendsolv.base.currencypurchasevalidator.amount.purchased.required";

	/** The Constant PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_FOREIGN_EXCHANGE_RATE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_FOREIGN_EXCHANGE_RATE_REQUIRED =
			"sendsolv.base.currencypurchasevalidator.foreign.exchange.rate.required";

	/** The Constant PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_PAYER_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_PAYER_ID_REQUIRED =
			"sendsolv.base.currencypurchasevalidator.payer.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_CURRENCY_CODE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_CURRENCY_CODE_REQUIRED =
			"sendsolv.base.currencypurchasevalidator.currency.code.required";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.validation.CurrencyPurchaseValidator#validate(com.qat.framework.validation.
	 * ValidationContext
	 * )
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		CurrencyPurchase currencyPurchase =
				(CurrencyPurchase)validationContext.getObjectToBeValidated(CurrencyPurchase.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(currencyPurchase))
		{
			validationContext.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_CURRENCYPURCHASE_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}

		performValidation(validationContext, currencyPurchase);

		validateAdditionalFields(validationContext.getMessages(), currencyPurchase);
	}

	/**
	 * Perform validation.
	 *
	 * @param validationContext the validation context
	 * @param currencyPurchase the currency purchase
	 */
	protected void performValidation(ValidationContext validationContext, CurrencyPurchase currencyPurchase)
	{

		switch (validationContext.getValidationContextIndicator())
		{
			case DELETE:
				validateId(validationContext.getMessages(), currencyPurchase);
				break;
			case INSERT:
				validateAll(validationContext.getMessages(), currencyPurchase);
				break;
			case UPDATE:
				validateId(validationContext.getMessages(), currencyPurchase);
				validateAll(validationContext.getMessages(), currencyPurchase);
				break;
			case FETCH_BY_ID:
				validateId(validationContext.getMessages(), currencyPurchase);
				break;
			default:
				validateAll(validationContext.getMessages(), currencyPurchase);
				break;
		}

	}

	/**
	 * Validate all.
	 *
	 * @param messages the messages
	 * @param currencyPurchase the currency purchase
	 */
	private void validateAll(List<MessageInfo> messages, CurrencyPurchase currencyPurchase)
	{
		if (!PersistanceActionEnum.NONE.equals(currencyPurchase.getModelAction()))
		{

			validateAmountPurchased(messages, currencyPurchase);
			validateForeignExcahngeRate(messages, currencyPurchase);
			validatePayerId(messages, currencyPurchase);
			validateCurrencyCode(messages, currencyPurchase);

		}

	}

	/**
	 * Validate amount purchased.
	 *
	 * @param messages the messages
	 * @param currencyPurchase the currency purchase
	 */
	protected void validateAmountPurchased(List<MessageInfo> messages, CurrencyPurchase currencyPurchase)
	{
		ValidationUtil.isNullOrZero(currencyPurchase.getAmountPurchased(),
				PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_AMOUNT_PURCHASED_REQUIRED, messages);
	}

	/**
	 * Validate foreign excahnge rate.
	 *
	 * @param messages the messages
	 * @param currencyPurchase the currency purchase
	 */
	protected void validateForeignExcahngeRate(List<MessageInfo> messages, CurrencyPurchase currencyPurchase)
	{
		ValidationUtil.isNullOrZero(currencyPurchase.getForeignExchangeRate(),
				PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_FOREIGN_EXCHANGE_RATE_REQUIRED, messages);
	}

	/**
	 * Validate payer id.
	 *
	 * @param messages the messages
	 * @param currencyPurchase the currency purchase
	 */
	protected void validatePayerId(List<MessageInfo> messages, CurrencyPurchase currencyPurchase)
	{
		ValidationUtil.isNullOrZero(currencyPurchase.getPayerId(),
				PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_PAYER_ID_REQUIRED, messages);
	}

	/**
	 * Validate currency code.
	 *
	 * @param messages the messages
	 * @param currencyPurchase the currency purchase
	 */
	protected void validateCurrencyCode(List<MessageInfo> messages, CurrencyPurchase currencyPurchase)
	{
		ValidationUtil.isNullOrEmpty(currencyPurchase.getCurrency().getCode(),
				PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_CURRENCY_CODE_REQUIRED, messages);
	}

	/**
	 * Validate id.
	 *
	 * @param messages the messages
	 * @param currencyPurchase the currency purchase
	 */
	protected void validateId(List<MessageInfo> messages, CurrencyPurchase currencyPurchase)
	{
		ValidationUtil.isNullOrZero(currencyPurchase.getId(),
				PROSPERITASGLOBAL_BASE_CURRENCYPURCHASEVALIDATOR_ID_REQUIRED, messages);
	}

	/**
	 * Validate additional fields.
	 *
	 * @param messages the messages
	 * @param currencyPurchase the currency purchase
	 */
	private void validateAdditionalFields(List<MessageInfo> messages, CurrencyPurchase currencyPurchase)
	{

	}
}
