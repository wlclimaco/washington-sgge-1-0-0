package com.prosperitasglobal.sendsolv.bad;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.Currency;
import com.prosperitasglobal.sendsolv.model.Account;
import com.prosperitasglobal.sendsolv.model.CustomFee;
import com.prosperitasglobal.sendsolv.model.DailyCurrencyRateDetail;
import com.prosperitasglobal.sendsolv.model.FeeTier;
import com.prosperitasglobal.sendsolv.model.FrequencyBasedEventCalendar;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferAmount;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatusEnum;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum;
import com.prosperitasglobal.sendsolv.model.PlanCategory;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.util.UserContextHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * This BAD (Business Area Delegate) exists to server common functions dealing with any class that extends from
 * a {@link com.prosperitasglobal.sendsolv.model.ProductPlan}.
 */
public final class MoneyTransferBAD
{
	/** Constant Zero. */
	private static final BigDecimal BIG_DECIMAL_ZERO = new BigDecimal("0.00");

	/** Constant 100. */
	private static final BigDecimal BIG_DECIMAL_ONE_HUNDRED = new BigDecimal("100");

	/** The Constant Exchange Rate of 1. */
	private static final BigDecimal RATE_1 = new BigDecimal("1.00");

	/**
	 * Private default constructor. Keeps from having attempts made at getting a new instance.
	 */
	private MoneyTransferBAD()
	{

	}

	/**
	 * Calculate the action due date.
	 * <p>
	 * The action due date is calculated by adding the {@link Location#getFundsTransferDayLimit()} to the pay date event
	 * date {@link FrequencyBasedEventCalendar#getEventDate()}. The day limit should be stored on the database as a
	 * negative number, representing the days before the pay date.
	 *
	 * @param location The {@link Location} containing the day limit.
	 * @param payDate The {@link FrequencyBasedEventCalendar} containing the pay date.
	 * @return The action due date. If the pay date is <code>null</code>, the current date will be returned. Also, if
	 *         the funds transfer day limit is <code>null</code>, then the pay date will be returned.
	 */
	private static Long calculateActionDueDate(Location location, FrequencyBasedEventCalendar payDate)
	{
		if (ValidationUtil.isNull(payDate))
		{
			return PGSiDateUtil.getCurrentDateMillisUTC();
		}

		if (ValidationUtil.isNull(location.getFundsTransferDayLimit()))
		{
			return payDate.getEventDate();
		}

		return PGSiDateUtil.addDays(payDate.getEventDate(), location.getFundsTransferDayLimit());
	}

	/**
	 * Calculate the customer exchange rate.
	 * <p>
	 *
	 * @param dailyCurrencyRateDetail The daily currency rate detail.
	 * @param spreadPercentage The spread percentage.
	 * @return The customer exchange rate.
	 */
	private static BigDecimal calculateCustomerExchangeRate(DailyCurrencyRateDetail dailyCurrencyRateDetail,
			BigDecimal spreadPercentage)
	{
		BigDecimal rate = dailyCurrencyRateDetail.getExchangeRate();
		BigDecimal spreadReduction = rate.multiply(spreadPercentage);
		spreadReduction = spreadReduction.divide(BIG_DECIMAL_ONE_HUNDRED);
		rate = rate.subtract(spreadReduction);
		return rate;
	}

	/**
	 * Calculate the destination amount.
	 *
	 * @param moneyTransfer The money transfer.
	 * @return The destination amount.
	 */
	private static BigDecimal calculateDestinationAmount(MoneyTransfer moneyTransfer)
	{
		BigDecimal destinationAmount = moneyTransfer.getOriginAmount().getAmount();
		return destinationAmount.multiply(moneyTransfer.getForeignExchangeRateCustomer());
	}

	/**
	 * Calculate the origin amount.
	 *
	 * @param moneyTransfer The money transfer.
	 * @param transferSetting The transfer setting.
	 * @return The origin amount.
	 */
	private static BigDecimal calculateOriginAmount(TransferSetting transferSetting, MoneyTransfer moneyTransfer)
	{
		BigDecimal originAmount = transferSetting.getTransferAmount();
		originAmount = originAmount.subtract(moneyTransfer.getOriginFlatFee());
		return originAmount.add(moneyTransfer.getDiscountAmount());
	}

	/**
	 * Calculate the payroll received date.
	 *
	 * The payroll received date is calculated by adding the {@link Location#getFundsTransferDayLimit()} to the pay date
	 * event date {@link FrequencyBasedEventCalendar#getEventDate()}. The day limit should be stored on the database as
	 * a negative number, representing the days before the pay date.
	 *
	 * @param location The {@link Location} containing the day limit.
	 * @param payDate The {@link FrequencyBasedEventCalendar} containing the pay date.
	 * @return The payroll received date. If the pay date is <code>null</code>, the current date will be returned. Also,
	 *         if the funds transfer day limit is <code>null</code>, then the pay date will be returned.
	 */
	private static Long calculatePayrollReceivedDate(Location location, FrequencyBasedEventCalendar payDate)
	{
		if (ValidationUtil.isNull(payDate))
		{
			return PGSiDateUtil.getCurrentDateMillisUTC();
		}

		if (ValidationUtil.isNull(location.getFundsTransferDayLimit()))
		{
			return payDate.getEventDate();
		}

		return PGSiDateUtil.addDays(payDate.getEventDate(), location.getFundsTransferDayLimit());
	}

	/**
	 * Calculate the transfer date.
	 *
	 * The transfer date is calculated by adding the {@link Location#getBatchApprovalDayLimit()} to the pay date event
	 * date {@link FrequencyBasedEventCalendar#getEventDate()}. The day limit should be stored on the database as a
	 * negative number, representing the days before the pay date.
	 *
	 * @param location The {@link Location} containing the day limit.
	 * @param payDate The {@link FrequencyBasedEventCalendar} containing the pay date.
	 * @return The transfer date. If the batch approval day limit is <code>null</code>, the current date will be
	 *         returned. Also, if the batch approval day limit is <code>null</code>, then the pay date will be returned.
	 */
	private static Long calculateTransferDate(Location location, FrequencyBasedEventCalendar payDate)
	{
		if (ValidationUtil.isNull(payDate))
		{
			return PGSiDateUtil.getCurrentDateMillisUTC();
		}

		if (ValidationUtil.isNull(location.getBatchApprovalDayLimit()))
		{
			return payDate.getEventDate();
		}

		return PGSiDateUtil.addDays(payDate.getEventDate(), location.getBatchApprovalDayLimit());
	}

	/**
	 * Create a {@link Account} object from another {@link Account} object.
	 *
	 * @param transferSetting The {@link TransferSetting} containing the {@link Account} which will be used to to build
	 *            the new {@link Account}.
	 * @return The new {@link Account}.
	 */
	private static Account createAccount(TransferSetting transferSetting)
	{
		if (ValidationUtil.isNull(transferSetting.getAccount()))
		{
			return null;
		}

		Account copyAccount = new Account();
		copyAccount.setNumber(new Integer(transferSetting.getAccount().getNumber()));
		copyAccount.setType(transferSetting.getAccount().getType());
		return copyAccount;
	}

	/**
	 * Create the destination amount.
	 *
	 * @param transferSetting The transfer setting.
	 * @param moneyTransfer The money transfer.
	 * @return The money transfer amount.
	 */
	private static MoneyTransferAmount createDestinationAmount(TransferSetting transferSetting,
			MoneyTransfer moneyTransfer)
	{
		MoneyTransferAmount moneyTransferAmount = new MoneyTransferAmount();
		moneyTransferAmount.setCountry(transferSetting.getProductPlanApplicability().getPayer().getCountry());
		moneyTransferAmount.setCurrency(transferSetting.getProductPlanApplicability().getCurrency());
		moneyTransferAmount.setAmount(calculateDestinationAmount(moneyTransfer));
		return moneyTransferAmount;
	}

	/**
	 * Create a {@link MoneyTransferAmount} object for the amount, currency, and country specified in the parameters.
	 *
	 * @param amount The {@link BigDecimal} amount to use.
	 * @param currency The {@link Currency} of the amount.
	 * @param country The {@link Country} of the {@link Currency}.
	 * @return The new {@link MoneyTransferAmount}.
	 */
	private static MoneyTransferAmount createMoneyTransferAmount(BigDecimal amount, Currency currency, Country country)
	{
		MoneyTransferAmount moneyTransferAmount = new MoneyTransferAmount();
		moneyTransferAmount.setAmount(amount);
		moneyTransferAmount.setCurrency(currency);
		moneyTransferAmount.setCountry(country);
		return moneyTransferAmount;
	}

	/**
	 * Create a {@link MoneyTransferBatchStatus} object with the status in the parameters.
	 *
	 * @param status The {@link MoneyTransferBatchStatusEnum} to set on the status.
	 * @param actionDueDate The action due date for the status.
	 * @return The new {@link MoneyTransferBatchStatus} defaulted to the initial status.
	 */
	private static MoneyTransferBatchStatus createMoneyTransferBatchStatus(MoneyTransferBatchStatusEnum status,
			Long actionDueDate)
	{
		MoneyTransferBatchStatus moneyTransferBatchStatus = new MoneyTransferBatchStatus();
		mapModelCreateAttributes(moneyTransferBatchStatus);
		moneyTransferBatchStatus.setActionDueDate(actionDueDate);
		moneyTransferBatchStatus.setStatus(status);
		return moneyTransferBatchStatus;
	}

	/**
	 * Create a new {@link List} of {@link MoneyTransferBatch} objects and default the status to
	 * {@link MoneyTransferBatchStatusEnum#BATCH_CREATION}.
	 *
	 * @param actionDueDate The action due date.
	 * @return The new {@link List} of {@link MoneyTransferBatchStatus} defaulted to the initial status.
	 */
	private static List<MoneyTransferBatchStatus> createMoneyTransferBatchStatusList(Long actionDueDate)
	{
		List<MoneyTransferBatchStatus> moneyTransferBatchStatusList = new ArrayList<MoneyTransferBatchStatus>();
		MoneyTransferBatchStatus moneyTransferBatchStatus =
				createMoneyTransferBatchStatus(MoneyTransferBatchStatusEnum.BATCH_CREATION, actionDueDate);
		moneyTransferBatchStatusList.add(moneyTransferBatchStatus);
		return moneyTransferBatchStatusList;
	}

	/**
	 * Create a {@link MoneyTransferStatus} object with the status in the parameters.
	 *
	 * @param status The {@link MoneyTransferStatusEnum} to set on the status.
	 * @return The new {@link MoneyTransferStatus} defaulted to the initial status.
	 */
	private static MoneyTransferStatus createMoneyTransferStatus(MoneyTransferStatusEnum status)
	{
		MoneyTransferStatus moneyTransferStatus = new MoneyTransferStatus();
		mapModelCreateAttributes(moneyTransferStatus);
		moneyTransferStatus.setStatus(status);
		return moneyTransferStatus;
	}

	/**
	 * Create a new {@link List} of {@link MoneyTransferStatus} objects and default the status to
	 * {@link MoneyTransferStatusEnum#PENDING_APPROVAL}.
	 *
	 * @return The new {@link List} of {@link MoneyTransferStatus} defaulted to the initial status.
	 */
	private static List<MoneyTransferStatus> createMoneyTransferStatusList()
	{
		List<MoneyTransferStatus> moneyTransferStatusList = new ArrayList<MoneyTransferStatus>();
		MoneyTransferStatus moneyTransferStatus = createMoneyTransferStatus(MoneyTransferStatusEnum.PENDING_APPROVAL);
		moneyTransferStatusList.add(moneyTransferStatus);
		return moneyTransferStatusList;
	}

	/**
	 * Create the origin amount.
	 *
	 * @param moneyTransfer The money transfer.
	 * @param transferSetting The transfer setting.
	 * @param currency The currency.
	 * @param country The country.
	 * @return The money transfer amount.
	 */
	private static MoneyTransferAmount createOriginAmount(MoneyTransfer moneyTransfer, TransferSetting transferSetting,
			Currency currency, Country country)
	{
		MoneyTransferAmount moneyTransferAmount = new MoneyTransferAmount();

		moneyTransferAmount.setCurrency(currency);
		moneyTransferAmount.setCountry(country);
		moneyTransferAmount.setAmount(calculateOriginAmount(transferSetting, moneyTransfer));

		return moneyTransferAmount;
	}

	/**
	 * Convenience method for determining if the {@link MoneyTransfer} has been approved.
	 *
	 * @param moneyTransfer The {@link MoneyTransfer} object.
	 * @return <code>True</code> if an {@link MoneyTransferStatusEnum#APPROVED} status is found in the status history,
	 *         <code>false</code> if it wasn't.
	 */
	private static boolean hasBeenSubmitted(MoneyTransfer moneyTransfer)
	{
		for (MoneyTransferStatus moneyTransferStatus : moneyTransfer.getStatusList())
		{
			if ((MoneyTransferStatusEnum.ORDER_SUBMITTED == moneyTransferStatus.getStatus()) ||
					(MoneyTransferStatusEnum.CANCELLATION_SUBMITTED == moneyTransferStatus.getStatus()) ||
					(MoneyTransferStatusEnum.MODIFICATION_SUBMITTED == moneyTransferStatus.getStatus()))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Maps the destination amounts to the {@link MoneyTransfer}.
	 *
	 * @param moneyTransfer The {@link MoneyTransfer} to map the data to.
	 * @param transferSetting The {@link TransferSetting} to obtain the data from.
	 * @param dailyCurrencyRateDetail The {@link DailyCurrencyRateDetail} for the payer.
	 */
	private static void mapDestinationAmounts(MoneyTransfer moneyTransfer, TransferSetting transferSetting,
			DailyCurrencyRateDetail dailyCurrencyRateDetail)
	{
		moneyTransfer.setForeignExchangeRate(dailyCurrencyRateDetail.getExchangeRate());
		moneyTransfer.setForeignExchangeRateCustomer(calculateCustomerExchangeRate(dailyCurrencyRateDetail,
				moneyTransfer.getSpreadPercentage()));
		moneyTransfer.setDestinationAmount(createDestinationAmount(transferSetting, moneyTransfer));
	}

	/**
	 * Map the common {@link QATModel} create attributes for the new objects.
	 *
	 * @param model the (@link QATModel} to set the attributes.
	 */
	private static void mapModelCreateAttributes(QATModel model)
	{
		model.setModelAction(PersistanceActionEnum.INSERT);
		model.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		model.setCreateUser(UserContextHelper.getUserContext().getUserId());
	}

	/**
	 * Map the common {@link QATModel} create attributes for the new objects.
	 *
	 * @param model the (@link QATModel} to set the attributes.
	 */
	private static void mapModelUpdateAttributes(QATModel model)
	{
		model.setModelAction(PersistanceActionEnum.UPDATE);
		model.setModifyDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		model.setModifyUser(UserContextHelper.getUserContext().getUserId());
	}

	/**
	 * Maps the origin amounts to the {@link MoneyTransfer}.
	 *
	 * @param moneyTransfer The {@link MoneyTransfer} to map the data to.
	 * @param transferSetting The {@link TransferSetting} to obtain the data from.
	 * @param currency The origin currency used.
	 * @param country The country of the currency used.
	 */
	private static void mapOriginAmounts(MoneyTransfer moneyTransfer, TransferSetting transferSetting,
			Currency currency, Country country)
	{
		/*
		 * Its important to set the discount, flat fee, and call credit amounts before setting the origin
		 * amount. The calculation for the origin amount uses the discount and flat fee.
		 */
		moneyTransfer.setDiscountAmount(obtainDiscountAmount(transferSetting));
		moneyTransfer.setOriginFlatFee(obtainFee(transferSetting));
		moneyTransfer.setOriginCallCreditAmount(obtainCallCredit(transferSetting));
		moneyTransfer.setOriginAmount(createOriginAmount(moneyTransfer, transferSetting, currency, country));
	}

	/**
	 * Obtain the call credit allowance.
	 *
	 * @param transferSetting The transfer setting.
	 * @return The allowance.
	 */
	private static BigDecimal obtainCallCredit(TransferSetting transferSetting)
	{
		return transferSetting.getPlanCategory().getCallCreditAllowance();
	}

	/**
	 * Obtain the discount amount.
	 *
	 * @param transferSetting The transfer setting.
	 * @return The discount amount.
	 */
	private static BigDecimal obtainDiscountAmount(TransferSetting transferSetting)
	{
		BigDecimal discountAmount = BIG_DECIMAL_ZERO;
		Long currentDate = PGSiDateUtil.getCurrentDateMillisUTC();

		for (CustomFee customFee : transferSetting.getCustomFeeList())
		{
			if (PGSiDateUtil.compareDates(customFee.getEffectiveEndDate(), currentDate) >= 0)
			{
				if (ValidationUtil.isNull(customFee.getEffectiveStartDate())
						|| (PGSiDateUtil.compareDates(customFee.getEffectiveStartDate(), currentDate) <= 0))
				{
					discountAmount = customFee.getValue();
					break;
				}
			}
		}

		return discountAmount;
	}

	/**
	 * Obtain the fee.
	 *
	 * @param transferSetting The transfer setting.
	 * @return The fee.
	 */
	private static BigDecimal obtainFee(TransferSetting transferSetting)
	{
		BigDecimal fee = BIG_DECIMAL_ZERO;
		BigDecimal transferAmount = transferSetting.getTransferAmount();

		PlanCategory planCategory = transferSetting.getPlanCategory();

		for (FeeTier feeTier : planCategory.getFeeTierList())
		{
			if ((transferAmount.compareTo(feeTier.getMinimumValue()) >= 0)
					&&
					(ValidationUtil.isNull(feeTier.getMaximumValue()) || (transferAmount.compareTo(feeTier
							.getMaximumValue()) <= 0)))
			{
				fee = feeTier.getFeeValue();
				break;
			}
		}

		return fee;
	}

	/**
	 * Create a new {@link MoneyTransfer} from a {@link TransferSetting}. The new one will have the
	 * <code>modelAction</code> set to {@link PersistanceActionEnum#INSERT}, the <code>createDateUTC</code> set to the
	 * current date/time, and the <code>createUser</code> will be set to the user from the current
	 * {@link com.qat.framework.model.UserContext} of the transaction.<br>
	 * <br>
	 * All of the attributes will be created new, avoiding the case where the same object is being used by multiple
	 * instances.
	 *
	 * @param moneyTransferBatch The {@link MoneyTransferBatch} object that will contain this {@link MoneyTransfer}.
	 * @param transferSetting The {@link TransferSetting} from which the new {@link MoneyTransfer} will be created.
	 * @param originCurrency The {@link Currency} of the origin of the transfer.
	 * @param originCountry The {@link Country} of the {@link Currency}.
	 * @param spreadPercentage The default spread percentage for PGSi.
	 *
	 * @return The new {@link MoneyTransferBatch} created from the {@link Location} parameter.
	 */
	public static MoneyTransfer createMoneyTransfer(MoneyTransferBatch moneyTransferBatch,
			TransferSetting transferSetting, Currency originCurrency, Country originCountry,
			BigDecimal spreadPercentage)
	{
		MoneyTransfer moneyTransfer = new MoneyTransfer();
		mapModelCreateAttributes(moneyTransfer);

		moneyTransfer.setTransferSetting(transferSetting);

		// If origin currency equals destination currency, default spread to 0. Can be modified later.
		if (originCurrency.getCode().equals(
				moneyTransfer.getTransferSetting().getProductPlanApplicability().getCurrency().getCode()))
		{
			moneyTransfer.setSpreadPercentage(BIG_DECIMAL_ZERO);
		}
		else
		{
			moneyTransfer.setSpreadPercentage(spreadPercentage);
		}

		mapOriginAmounts(moneyTransfer, transferSetting, originCurrency, originCountry);

		DailyCurrencyRateDetail dailyCurrencyRateDetail = new DailyCurrencyRateDetail();
		dailyCurrencyRateDetail.setExchangeRate(BIG_DECIMAL_ZERO);
		mapDestinationAmounts(moneyTransfer, transferSetting, dailyCurrencyRateDetail);

		moneyTransfer.setRecipientAccount(createAccount(transferSetting));
		moneyTransfer.setConfirmationNumber("");
		moneyTransfer.setMoneyTransferBatchId(moneyTransferBatch.getId());
		moneyTransfer.setNoteList(null);
		moneyTransfer.setPaymentType(transferSetting.getProductPlanApplicability().getPaymentType());
		moneyTransfer.setStatusList(createMoneyTransferStatusList());

		return moneyTransfer;
	}

	/**
	 * Create a new {@link MoneyTransferBatch} from a {@link Location}. The new one will have the
	 * <code>modelAction</code> set to {@link PersistanceActionEnum#INSERT}, the <code>createDateUTC</code> set to the
	 * current date/time, and the <code>createUser</code> will be set to the user from the current
	 * {@link com.qat.framework.model.UserContext} of the transaction.<br>
	 * <br>
	 * All of the attributes will be created new, avoiding the case where the same object is being used by multiple
	 * instances.
	 *
	 * @param location The {@link Location} from which the new {@link MoneyTransferBatch} will be created.
	 * @param originCurrency The {@link Currency} of the origin of the transfer.
	 * @param originCountry The {@link Country} of the {@link Currency}.
	 * @param paymentPreparationDate The {@link FrequencyBasedEventCalendar} which triggered the request.
	 * @param payDate The {@link FrequencyBasedEventCalendar} with the pay date.
	 * @return The new {@link MoneyTransferBatch} created from the {@link Location} parameter.
	 */
	public static MoneyTransferBatch createMoneyTransferBatch(Location location,
			FrequencyBasedEventCalendar paymentPreparationDate, FrequencyBasedEventCalendar payDate,
			Currency originCurrency, Country originCountry)
	{
		MoneyTransferBatch moneyTransferBatch = new MoneyTransferBatch();
		mapModelCreateAttributes(moneyTransferBatch);

		moneyTransferBatch.setLocation(location);
		moneyTransferBatch.setPayPreparationDate(paymentPreparationDate.getEventDate());
		moneyTransferBatch.setPayrollReceivedDate(calculatePayrollReceivedDate(location, payDate));
		moneyTransferBatch.setTransferDate(calculateTransferDate(location, payDate));
		moneyTransferBatch.setOriginAmount(createMoneyTransferAmount(BIG_DECIMAL_ZERO, originCurrency, originCountry));
		moneyTransferBatch.setStatusList(createMoneyTransferBatchStatusList(calculateActionDueDate(location,
				payDate)));

		return moneyTransferBatch;
	}

	/**
	 * This method will update the dynamic attributes of a {@link MoneyTransfer}.
	 * <p>
	 * The dynamic attributes are as follows:<br>
	 * <br>
	 * <li>foreignExchangeRate</li><br>
	 * <br>
	 * <li>foreignExchangeRateCustomer</li><br>
	 * <br>
	 * <li>destinationAmount</li><br>
	 * <br>
	 * NOTE: If the {@link DailyCurrencyRateDetail} is <code>null</code> and the {@link MoneyTransfer} is for a transfer
	 * from and to the same currency, then an exchange rate of 1 will be used.<br>
	 * <br>
	 * IF ANY OF THE FOLLOWING SCENARIOS ARE ENCOUNTERED, NO UPDATE WILL BE PERFORMED:<br>
	 * <br>
	 * <li>If the {@link MoneyTransfer} has already been submitted.</li><br>
	 * <br>
	 * <li>If the {@link DailyCurrencyRateDetail} is <code>null</code> and the {@link MoneyTransfer} is for a transfer
	 * from and to a different currency.</li><br>
	 * <br>
	 * <li>If the <code>foreignExchangeRate</code> already found on the {@link MoneyTransfer} is the same as the
	 * <code>exchangeRate</code> on the {@link DailyCurrencyRateDetail} passed in.</li>
	 * <br>
	 * <br>
	 *
	 * @param moneyTransfer The {@link MoneyTransfer} to update.
	 * @param originCurrency The {@link Currency} of the origin of the transfer.
	 * @param originCountry The {@link Country} of the {@link Currency}.
	 * @param dailyCurrencyRateDetail The destinations daily currency rate detail.
	 */
	public static void updateDynamicAttributes(MoneyTransfer moneyTransfer, Currency originCurrency,
			Country originCountry, DailyCurrencyRateDetail dailyCurrencyRateDetail)
	{
		/*
		 * If the money transfer hasn't been submitted, the foreign exchange rate is zero, or the foreign exchange rate
		 * customer is zero, then see if updates are needed. Else simply return.
		 */
		if (!hasBeenSubmitted(moneyTransfer) ||
				(BIG_DECIMAL_ZERO.compareTo(moneyTransfer.getForeignExchangeRate()) == 0) ||
				(BIG_DECIMAL_ZERO.compareTo(moneyTransfer.getForeignExchangeRateCustomer()) == 0))
		{
			DailyCurrencyRateDetail dailyCurrencyRateDetailToUse = dailyCurrencyRateDetail;

			/* If no daily currency rate detail was passed into method. */
			if (ValidationUtil.isNull(dailyCurrencyRateDetail))
			{
				// If origin currency equals destination currency, the the rate is 1.
				if (originCurrency.getCode().equals(
						moneyTransfer.getTransferSetting().getProductPlanApplicability().getCurrency().getCode()))
				{
					dailyCurrencyRateDetailToUse = new DailyCurrencyRateDetail();
					dailyCurrencyRateDetailToUse.setExchangeRate(RATE_1);
				}
				else
				{
					// Simply return. No update will be performed.
					return;
				}
			}

			mapDestinationAmounts(moneyTransfer, moneyTransfer.getTransferSetting(), dailyCurrencyRateDetailToUse);
			mapModelUpdateAttributes(moneyTransfer);
		}
	}
}
