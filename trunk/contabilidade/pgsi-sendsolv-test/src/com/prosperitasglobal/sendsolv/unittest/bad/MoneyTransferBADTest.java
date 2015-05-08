package com.prosperitasglobal.sendsolv.unittest.bad;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.Currency;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bad.MoneyTransferBAD;
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
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.PlanCategory;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.QATModelOL;
import com.qat.framework.model.UserContext;
import com.qat.framework.util.UserContextHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * A JUNIT for testing the business area delegate (BAD) for money transfers.
 */
public class MoneyTransferBADTest
{
	/** Constant BigDecimal 100. */
	private static final BigDecimal BIG_DECIMAL_ONE_HUNDRED = new BigDecimal("100");

	/** Constant BigDecimal 0. */
	private static final BigDecimal BIG_DECIMAL_ZERO = new BigDecimal("0");

	/** Constant for country USA. */
	private static final Country COUNTRY_MEX = new Country("MEX");

	/** Constant for country USA. */
	private static final Country COUNTRY_USA = new Country("USA");

	/** Constant for USD currency. */
	private static final Currency CURRENCY_MXP = new Currency("MXP");

	/** Constant for USD currency. */
	private static final Currency CURRENCY_USD = new Currency("USD");

	/** Default PGSi spread. */
	private static final BigDecimal PGSI_DEFAULT_SPREAD = new BigDecimal("8.2367");

	/** Constant for test user. */
	private static final String QAT_TEST = "QATTest";

	/** Constant for 10 days. */
	private static final Integer TEN_DAYS = 10;

	/** Constant for USD to MXP exchange rate. */
	private static final BigDecimal USD_TO_MXP_EXCHANGE_RATE = new BigDecimal("1.75");

	/** USD to USD exchange rate. */
	private static final BigDecimal USD_TO_USD_EXCHAGE_RATE = new BigDecimal("1.00");

	/**
	 * Method to add a specific {@link MoneyTransferStatus} to the {@link MoneyTransfer}.
	 *
	 * @param moneyTransfer The money transfer.
	 * @param statusEnum The status enumeration.
	 */
	private void addMoneyTransferStatus(MoneyTransfer moneyTransfer, MoneyTransferStatusEnum statusEnum)
	{
		MoneyTransferStatus status = new MoneyTransferStatus();
		status.setStatus(statusEnum);
		status.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		status.setCreateUser(UserContextHelper.getUserContext().getUserId());
		moneyTransfer.addMoneyTransferStatus(status);
	}

	/**
	 * Compare to make sure the money transfer was created correctly.
	 *
	 * @param moneyTransfer The money transfer.
	 * @param moneyTransferBatch The parent money transfer batch.
	 * @param transferSetting The transfer setting.
	 * @param spread The spread used.
	 */
	private void assertCreateMoneyTransfer(MoneyTransfer moneyTransfer, MoneyTransferBatch moneyTransferBatch,
			TransferSetting transferSetting, BigDecimal spread)
	{
		assertQATModelOL(moneyTransfer, PersistanceActionEnum.INSERT);

		Assert.assertEquals("MoneyTransfer transferSetting mismatch!", transferSetting.getId(), moneyTransfer
				.getTransferSetting().getId());

		Assert.assertTrue("MoneyTransfer spreadPercentage mismatch!",
				spread.compareTo(moneyTransfer.getSpreadPercentage()) == 0);

		Assert.assertTrue("MoneyTransfer disountAmount mismatch!",
				obtainDiscountAmount(transferSetting).compareTo(moneyTransfer.getDiscountAmount()) == 0);

		Assert.assertTrue("MoneyTransfer originFlatFee mismatch!",
				obtainFee(transferSetting).compareTo(moneyTransfer.getOriginFlatFee()) == 0);

		Assert.assertTrue("MoneyTransfer originCallCreditAmount mismatch!",
				obtainCallCredit(transferSetting).compareTo(moneyTransfer.getOriginCallCreditAmount()) == 0);

		assertMoneyTransferOriginAmount(moneyTransfer, transferSetting, CURRENCY_USD, COUNTRY_USA);

		Assert.assertTrue("MoneyTransfer foreignExchangeRate not zero!",
				BIG_DECIMAL_ZERO.compareTo(moneyTransfer.getForeignExchangeRate()) == 0);

		Assert.assertTrue(
				"MoneyTransfer foreignExchangeRateCustomer mismatch!",
				calculateCustomerExchangeRate(BIG_DECIMAL_ZERO, spread).compareTo(
						moneyTransfer.getForeignExchangeRateCustomer()) == 0);

		assertMoneyTransferDestinationAmount(moneyTransfer, transferSetting, BIG_DECIMAL_ZERO, spread);

		assertMoneyTransferRecipientAccount(moneyTransfer, transferSetting);

		Assert.assertEquals("MoneyTransfer confirmationNumber not emptry string!", "",
				moneyTransfer.getConfirmationNumber());

		Assert.assertEquals("MoneyTransfer moneyTransferBatchId mismatch!", moneyTransferBatch.getId(),
				moneyTransfer.getMoneyTransferBatchId());

		assertNoteList(moneyTransfer);

		Assert.assertEquals("MoneyTransfer paymentType mismatch!", transferSetting.getProductPlanApplicability()
				.getPaymentType(), moneyTransfer.getPaymentType());

		assertMoneyTransferStatusList(moneyTransfer);

		assertMoneyTransferCurrentStatus(moneyTransfer);

		Assert.assertNull("MoneyTransfer id is not null!", moneyTransfer.getId());
		Assert.assertNull("MoneyTransfer moneyTransferDetail is not null!", moneyTransfer.getMoneyTransferDetail());
	}

	/**
	 * Compares the created MoneyTransferBatch against expectations.
	 *
	 * @param moneyTransferBatch The MoneyTransferBatch created.
	 * @param location The location used for creation.
	 * @param payDate The pay date frequency based event used in creation.
	 */
	private void assertCreateMoneyTransferBatch(MoneyTransferBatch moneyTransferBatch, Location location,
			FrequencyBasedEventCalendar payDate)
	{
		assertQATModelOL(moneyTransferBatch, PersistanceActionEnum.INSERT);

		assertMoneyTransferBatchCurrentStatus(moneyTransferBatch,
				calculateActionDueDate(payDate, location));

		Assert.assertNull("MoneyTransferBatch id is not null!", moneyTransferBatch.getId());
		Assert.assertEquals("MoneyTransferBatch location mismatch!", location.getId(), moneyTransferBatch.getLocation()
				.getId());

		assertMoneyTransferList(moneyTransferBatch);
		assertNoteList(moneyTransferBatch);
		assertMoneyTransferBatchOriginAmount(moneyTransferBatch, CURRENCY_USD, COUNTRY_USA);

		assertMoneyTransferBatchStatusList(moneyTransferBatch,
				calculateActionDueDate(payDate, location));

		Assert.assertTrue("MoneyTransferBatch transferDate mismatch!", PGSiDateUtil.compareDates(
				calculateTransferDate(payDate, location),
				moneyTransferBatch.getTransferDate()) == 0);

		Assert.assertTrue("MoneyTransferBatch payrollReceivedDate mismatch!", PGSiDateUtil.compareDates(
				calculatePayrollReceivedDate(payDate, location),
				moneyTransferBatch.getPayrollReceivedDate()) == 0);
	}

	/**
	 * Compare to make sure the money transfer was updated correctly.
	 *
	 * @param moneyTransfer The money transfer.
	 * @param dailyCurrencyRateDetail The daily currency rate detail.
	 * @param action The expected model action.
	 */
	private void assertDynamicUpdateAttributes(MoneyTransfer moneyTransfer,
			DailyCurrencyRateDetail dailyCurrencyRateDetail, PersistanceActionEnum action)
	{
		assertQATModelOL(moneyTransfer, action);

		if (ValidationUtil.isNull(dailyCurrencyRateDetail))
		{
			assertMoneyTransferDestinationAmount(moneyTransfer, moneyTransfer.getTransferSetting(),
					moneyTransfer.getForeignExchangeRate(), moneyTransfer.getSpreadPercentage());
		}
		else
		{
			assertMoneyTransferDestinationAmount(moneyTransfer, moneyTransfer.getTransferSetting(),
					dailyCurrencyRateDetail.getExchangeRate(), moneyTransfer.getSpreadPercentage());
		}
	}

	/**
	 * Compares to make sure the money transfer amount is correct.
	 *
	 * @param expectedMoneyTransferAmount The expected amount.
	 * @param actualMoneyTransferAmount The actual amount.
	 */
	private void assertMoneyTransferAmount(MoneyTransferAmount expectedMoneyTransferAmount,
			MoneyTransferAmount actualMoneyTransferAmount)
	{
		Assert.assertTrue("MoneyTransferAmount amount mismatch!",
				expectedMoneyTransferAmount.getAmount().compareTo(actualMoneyTransferAmount.getAmount()) == 0);
		Assert.assertEquals("MoneyTransferAmount country mismatch!",
				expectedMoneyTransferAmount.getCountry().getCode(), actualMoneyTransferAmount.getCountry().getCode());
		Assert.assertEquals("MoneyTransferAmount currency mismatch!", expectedMoneyTransferAmount.getCurrency()
				.getCode(), actualMoneyTransferAmount.getCurrency().getCode());
		Assert.assertNull("MoneyTransferAmount stateProvinceRegion not null!",
				actualMoneyTransferAmount.getStateProvinceRegion());
	}

	/**
	 * Compares to make sure the money transfer batch current status is correct.
	 *
	 * @param moneyTransferBatch The money transfer batch.
	 * @param actionDueDate The action due date.
	 */
	private void assertMoneyTransferBatchCurrentStatus(MoneyTransferBatch moneyTransferBatch, Long actionDueDate)
	{
		Assert.assertNotNull("MoneyTransferBatch currentStatus is null!", moneyTransferBatch.getCurrentStatus());
		assertMoneyTransferBatchStatus(moneyTransferBatch.getCurrentStatus(), actionDueDate);
	}

	/**
	 * Compares to make sure the money transfer batch origin amount is correct.
	 *
	 * @param moneyTransferBatch The money transfer batch.
	 * @param originCurrency The currency of the origin.
	 * @param originCountry The country of the currency.
	 */
	private void assertMoneyTransferBatchOriginAmount(MoneyTransferBatch moneyTransferBatch, Currency originCurrency,
			Country originCountry)
	{
		Assert.assertNotNull("MoneyTransferBatch originAmount is null!", moneyTransferBatch.getOriginAmount());

		MoneyTransferAmount expectedMoneyTransferAmount = new MoneyTransferAmount();
		expectedMoneyTransferAmount.setCountry(originCountry);
		expectedMoneyTransferAmount.setCurrency(originCurrency);
		expectedMoneyTransferAmount.setAmount(BIG_DECIMAL_ZERO);

		assertMoneyTransferAmount(expectedMoneyTransferAmount, moneyTransferBatch.getOriginAmount());
	}

	/**
	 * Compares to make sure the money transfer batch status is correct.
	 *
	 * @param moneyTransferBatchStatus The status.
	 * @param actionDueDate The action due date.
	 */
	private void assertMoneyTransferBatchStatus(MoneyTransferBatchStatus moneyTransferBatchStatus, Long actionDueDate)
	{
		assertQATModel(moneyTransferBatchStatus, PersistanceActionEnum.INSERT);

		Assert.assertNull("MoneyTransferBatchStatus id not null!", moneyTransferBatchStatus.getId());
		Assert.assertNull("MoneyTransferBatchStatus moneyTransferBatchId is not null!",
				moneyTransferBatchStatus.getMoneyTransferBatchId());
		Assert.assertEquals("MoneyTransferBatchStatus status is not INITIAL!",
				MoneyTransferBatchStatusEnum.BATCH_CREATION, moneyTransferBatchStatus.getStatus());
		Assert.assertTrue("MoneyTransferBatch actionDueDate mismatch!", PGSiDateUtil.compareDates(
				actionDueDate,
				moneyTransferBatchStatus.getActionDueDate()) == 0);
	}

	/**
	 * Compares to make sure the money transfer batch status list is correct.
	 *
	 * @param moneyTransferBatch The money transfer batch.
	 * @param actionDueDate The action due date.
	 */
	private void assertMoneyTransferBatchStatusList(MoneyTransferBatch moneyTransferBatch, Long actionDueDate)
	{
		Assert.assertNotNull("MoneyTransferBatch statusList is null!", moneyTransferBatch.getStatusList());
		Assert.assertTrue("MoneyTransferBatch statusList.size is not 1!",
				moneyTransferBatch.getStatusList().size() == 1);
		Assert.assertNotNull("MoneyTransferBatch statusList.get(0) is null!",
				moneyTransferBatch.getStatusList().get(0));
		assertMoneyTransferBatchStatus(moneyTransferBatch.getStatusList().get(0), actionDueDate);
	}

	/**
	 * Compare to make sure the money transfer current status is correct.
	 *
	 * @param moneyTransfer The money transfer.
	 */
	private void assertMoneyTransferCurrentStatus(MoneyTransfer moneyTransfer)
	{
		Assert.assertNotNull("MoneyTransfer currentStatus is null!", moneyTransfer.getCurrentStatus());
		assertMoneyTransferStatus(moneyTransfer.getCurrentStatus());
	}

	/**
	 * Compares to make sure the money transfer destination amount is correct.
	 *
	 * @param moneyTransfer The money transfer.
	 * @param transferSetting The transfer setting.
	 * @param exchangeRate The exchange rate.
	 * @param spread The spread.
	 */
	private void assertMoneyTransferDestinationAmount(MoneyTransfer moneyTransfer, TransferSetting transferSetting,
			BigDecimal exchangeRate, BigDecimal spread)
	{
		Assert.assertNotNull("MoneyTransfer destinationAmount is null!", moneyTransfer.getDestinationAmount());

		MoneyTransferAmount expectedMoneyTransferAmount = new MoneyTransferAmount();
		expectedMoneyTransferAmount.setCountry(transferSetting.getProductPlanApplicability().getPayer().getCountry());
		expectedMoneyTransferAmount.setCurrency(transferSetting.getProductPlanApplicability().getCurrency());
		expectedMoneyTransferAmount.setAmount(calculateDestinationAmount(transferSetting, exchangeRate, spread));

		assertMoneyTransferAmount(expectedMoneyTransferAmount, moneyTransfer.getDestinationAmount());
	}

	/**
	 * Compare to make sure the money transfer list is correct.
	 *
	 * @param moneyTransferBatch The money transfer batch.
	 */
	private void assertMoneyTransferList(MoneyTransferBatch moneyTransferBatch)
	{
		Assert.assertNotNull("MoneyTransferBatch moneyTransferList is null!",
				moneyTransferBatch.getMoneyTransferList());
		Assert.assertTrue("MoneyTransferBatch moneyTransferListList.size is not 0!", moneyTransferBatch
				.getMoneyTransferList().size() == 0);
	}

	/**
	 * Compares to make sure the origin amount is correct.
	 *
	 * @param moneyTransfer The money transfer.
	 * @param transferSetting The transfer setting.
	 * @param originCurrency The currency of the origin.
	 * @param originCountry The country of the currency.
	 */
	private void assertMoneyTransferOriginAmount(MoneyTransfer moneyTransfer, TransferSetting transferSetting,
			Currency originCurrency, Country originCountry)
	{
		Assert.assertNotNull("MoneyTransfer originAmount is null!", moneyTransfer.getOriginAmount());

		MoneyTransferAmount expectedMoneyTransferAmount = new MoneyTransferAmount();
		expectedMoneyTransferAmount.setCountry(originCountry);
		expectedMoneyTransferAmount.setCurrency(originCurrency);
		expectedMoneyTransferAmount.setAmount(calculateOriginAmount(transferSetting));

		assertMoneyTransferAmount(expectedMoneyTransferAmount, moneyTransfer.getOriginAmount());
	}

	/**
	 * Compares to make sure the recipient account is correct.
	 *
	 * @param moneyTransfer The money transfer.
	 * @param transferSetting The transfer setting.
	 */
	private void assertMoneyTransferRecipientAccount(MoneyTransfer moneyTransfer, TransferSetting transferSetting)
	{
		Account moneyTransferAccount = moneyTransfer.getRecipientAccount();
		Account transferSettingAccount = transferSetting.getAccount();

		if (ValidationUtil.isNull(transferSettingAccount))
		{
			Assert.assertNull("MoneyTransfer recipient account is not null!", moneyTransferAccount);
		}
		else
		{
			Assert.assertEquals("MoneyTransfer recipient account.type mismatch!", transferSettingAccount.getType(),
					moneyTransferAccount.getType());
			Assert.assertEquals("MoneyTransfer recipient account.number mismatch!", transferSettingAccount.getNumber(),
					moneyTransferAccount.getNumber());
		}
	}

	/**
	 * Compare to make sure the money transfer status is correct.
	 *
	 * @param moneyTransferStatus The money transfer status.
	 */
	private void assertMoneyTransferStatus(MoneyTransferStatus moneyTransferStatus)
	{
		assertQATModel(moneyTransferStatus, PersistanceActionEnum.INSERT);

		Assert.assertNull("MoneyTransferStatus id not null!", moneyTransferStatus.getId());
		Assert.assertNull("MoneyTransferStatus moneyTransferId is not null!", moneyTransferStatus.getMoneyTransferId());
		Assert.assertNull("MoneyTransferStatus moneyTransferTransaction is not null!",
				moneyTransferStatus.getMoneyTransferTransaction());
		Assert.assertNull("MoneyTransferStatus response is not null!", moneyTransferStatus.getResponse());
		Assert.assertEquals("MoneyTransferStatus status is not APPROVED!", MoneyTransferStatusEnum.PENDING_APPROVAL,
				moneyTransferStatus.getStatus());
	}

	/**
	 * Compares to make sure the money transfer status list is correct.
	 *
	 * @param moneyTransfer The money transfer.
	 */
	private void assertMoneyTransferStatusList(MoneyTransfer moneyTransfer)
	{
		Assert.assertNotNull("MoneyTransfer statusList is null!", moneyTransfer.getStatusList());
		Assert.assertTrue("MoneyTransfer statusList.size is not 1!", moneyTransfer.getStatusList().size() == 1);
		Assert.assertNotNull("MoneyTransfer statusList.get(0) is null!", moneyTransfer.getStatusList().get(0));
		assertMoneyTransferStatus(moneyTransfer.getStatusList().get(0));
	}

	/**
	 * Compares to make sure the note list is correct.
	 *
	 * @param moneyTransfer The money transfer.
	 */
	private void assertNoteList(MoneyTransfer moneyTransfer)
	{
		Assert.assertNotNull("MoneyTransfer noteList is null!", moneyTransfer.getNoteList());
		Assert.assertTrue("MoneyTransfer notList.size not 0!", moneyTransfer.getNoteList().size() == 0);
	}

	/**
	 * Compares to make sure the note list is correct.
	 *
	 * @param moneyTransferBatch The money transfer batch.
	 */
	private void assertNoteList(MoneyTransferBatch moneyTransferBatch)
	{
		Assert.assertNotNull("MoneyTransferBatch notList is null!", moneyTransferBatch.getNoteList());
		Assert.assertTrue("MoneyTransferBatch notList.size not 0!", moneyTransferBatch.getNoteList().size() == 0);
	}

	/**
	 * Compares to make sure the QATModel is correct.
	 *
	 * @param qatModel The QATModel.
	 * @param action The expected model action.
	 */
	private void assertQATModel(QATModel qatModel, PersistanceActionEnum action)
	{
		if (PersistanceActionEnum.INSERT == action)
		{
			Assert.assertNotNull("QATModel createDateUTC is null!", qatModel.getCreateDateUTC());
			Assert.assertEquals("QATModel createUser mismatch!", UserContextHelper.getUserContext().getUserId(),
					qatModel.getCreateUser());
			Assert.assertNull("QATModel modifyDateUTC is not null!", qatModel.getModifyDateUTC());
			Assert.assertNull("QATModel modifyUser is not null!", qatModel.getModifyUser());
			Assert.assertEquals("QATModel modelAction is not INSERT!", PersistanceActionEnum.INSERT,
					qatModel.getModelAction());
		}
		else if (PersistanceActionEnum.UPDATE == action)
		{
			Assert.assertNotNull("QATModel modifyDateUTC is null!", qatModel.getModifyDateUTC());
			Assert.assertEquals("QATModel updateUser mismatch!", UserContextHelper.getUserContext().getUserId(),
					qatModel.getModifyUser());
			Assert.assertEquals("QATModel modelAction is not UPDATE!", PersistanceActionEnum.UPDATE,
					qatModel.getModelAction());
		}
		else if (PersistanceActionEnum.NONE == action)
		{
			Assert.assertEquals("QATModel modelAction is not NONE!", PersistanceActionEnum.NONE,
					qatModel.getModelAction());
		}
	}

	/**
	 * Compares to make sure the QATModlOL is correct.
	 *
	 * @param qatModelOL The QATModelOL to compare.
	 * @param action The expected model action.
	 */
	private void assertQATModelOL(QATModelOL qatModelOL, PersistanceActionEnum action)
	{
		assertQATModel(qatModelOL, action);
		Assert.assertTrue("QATModelOL version is not 0!", qatModelOL.getVersion() == 0);
	}

	/**
	 * Calculates the action due date.
	 *
	 * @param payDate The calendar.
	 * @param location The location.
	 * @return The action due date.
	 */
	private Long calculateActionDueDate(FrequencyBasedEventCalendar payDate, Location location)
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
	 * Calculates the customer exchange rate.
	 *
	 * @param exchangeRate The exchange rate.
	 * @param spread The spread percentage.
	 * @return The customer exchange rate.
	 */
	private BigDecimal calculateCustomerExchangeRate(BigDecimal exchangeRate, BigDecimal spread)
	{
		BigDecimal rate = exchangeRate;
		BigDecimal spreadReduction = rate.multiply(spread);
		spreadReduction = spreadReduction.divide(BIG_DECIMAL_ONE_HUNDRED);
		rate = rate.subtract(spreadReduction);
		return rate;
	}

	/**
	 * Calculates the destination amount.
	 *
	 * @param transferSetting The transfer setting.
	 * @param exchangeRate The exchange rate.
	 * @param spread The spread.
	 * @return The destination amount.
	 */
	private BigDecimal calculateDestinationAmount(TransferSetting transferSetting, BigDecimal exchangeRate,
			BigDecimal spread)
	{
		BigDecimal destinationAmount = calculateOriginAmount(transferSetting);
		return destinationAmount.multiply(calculateCustomerExchangeRate(exchangeRate, spread));
	}

	/**
	 * Calculates the origin amount.
	 *
	 * @param transferSetting The transfer setting.
	 * @return The origin amount.
	 */
	private BigDecimal calculateOriginAmount(TransferSetting transferSetting)
	{
		BigDecimal originAmount = transferSetting.getTransferAmount();
		originAmount = originAmount.subtract(obtainFee(transferSetting));
		return originAmount.add(obtainDiscountAmount(transferSetting));
	}

	/**
	 * Calculates the transfer date.
	 *
	 * @param payDate The calendar.
	 * @param location The location.
	 * @return The transfer date.
	 */
	private Long calculatePayrollReceivedDate(FrequencyBasedEventCalendar payDate, Location location)
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
	 * Calculates the transfer date.
	 *
	 * @param payDate The calendar.
	 * @param location The location.
	 * @return The transfer date.
	 */
	private Long calculateTransferDate(FrequencyBasedEventCalendar payDate, Location location)
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
	 * Common method for calling the createMoneyTransfer method on the MoneyTransferBAD.
	 *
	 * @param moneyTransferBatch The money transfer batch to pass.
	 * @param transferSetting The transfer setting to pass.
	 * @return The MoneyTransfer created.
	 */
	private MoneyTransfer createMoneyTransfer(MoneyTransferBatch moneyTransferBatch, TransferSetting transferSetting)
	{
		return MoneyTransferBAD.createMoneyTransfer(moneyTransferBatch, transferSetting, CURRENCY_USD, COUNTRY_USA,
				PGSI_DEFAULT_SPREAD);
	}

	/**
	 * Common method for calling the createMoneyTransferBatch method on the MoneyTransferBAD.
	 *
	 * @param location The location to pass.
	 * @param paymentPreparationDate The event calendar of the payment preparation date.
	 * @param payDate the event calendar of the pay date.
	 * @return The MoneyTransferBatch created.
	 */
	private MoneyTransferBatch createMoneyTransferBatch(Location location,
			FrequencyBasedEventCalendar paymentPreparationDate, FrequencyBasedEventCalendar payDate)
	{
		return MoneyTransferBAD.createMoneyTransferBatch(location, paymentPreparationDate, payDate, CURRENCY_USD,
				COUNTRY_USA);
	}

	/**
	 * Returns a {@link DailyCurrencyRateDetail} with a rate of <code>rate</code>.
	 *
	 * @param rate The rate to use.
	 * @return The {@link DailyCurrencyRateDetail}.
	 */
	private DailyCurrencyRateDetail getDefaultCurrencyRateDetail(BigDecimal rate)
	{
		DailyCurrencyRateDetail dailyCurrencyRateDetail = new DailyCurrencyRateDetail();
		dailyCurrencyRateDetail.setExchangeRate(rate);
		return dailyCurrencyRateDetail;
	}

	/**
	 * Returns a default {@link FrequencyBasedEventCalendar} for the payment preparation date.
	 *
	 * @return The {@link FrequencyBasedEventCalendar}.
	 */
	private FrequencyBasedEventCalendar getDefaultPaymentPreparationDate()
	{
		FrequencyBasedEventCalendar paymentPreparationDate = new FrequencyBasedEventCalendar();
		paymentPreparationDate.setEventDate(PGSiDateUtil.getCurrentDateMillisUTC());
		return paymentPreparationDate;
	}

	/**
	 * Returns a default {@link FrequencyBasedEventCalendar} for the pay date.
	 *
	 * @return The {@link FrequencyBasedEventCalendar}.
	 */
	private FrequencyBasedEventCalendar getDefaultPayDate()
	{
		FrequencyBasedEventCalendar payDate = new FrequencyBasedEventCalendar();
		payDate.setEventDate(PGSiDateUtil.addDays(PGSiDateUtil.getCurrentDateMillisUTC(), TEN_DAYS));
		return payDate;
	}

	/**
	 * Returns a default {@link Location}.
	 *
	 * @return The {@link Location}.
	 */
	private Location getDefaultLocation()
	{
		Organization organization = CommonTestRoutines.createDummyOrganization();
		return CommonTestRoutines.createDummyLocation(organization);
	}

	/**
	 * Returns a default {@link MoneyTransferBatch}.
	 *
	 * @return The {@link MoneyTransferBatch}.
	 */
	private MoneyTransferBatch getDefaultMoneyTransferBatch()
	{
		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);
		return CommonTestRoutines.createDummyMoneyTransferBatch(location);
	}

	/**
	 * Returns a default {@link TransferSetting}.
	 *
	 * @param currency The currency to use on the transfer setting.
	 * @param country The country to use on the transfer setting.
	 * @return The {@link TransferSetting}.
	 */
	private TransferSetting getDefaultTransferSetting(Currency currency, Country country)
	{
		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();
		transferSetting.getProductPlanApplicability().setCurrency(currency);
		transferSetting.getProductPlanApplicability().getPayer().setCountry(country);
		return transferSetting;
	}

	/**
	 * Obtains the expected call credit.
	 *
	 * @param transferSetting The transfer setting.
	 * @return The call credit.
	 */
	private BigDecimal obtainCallCredit(TransferSetting transferSetting)
	{
		return transferSetting.getPlanCategory().getCallCreditAllowance();
	}

	/**
	 * Obtains the expected discount amount.
	 *
	 * @param transferSetting The transfer setting.
	 * @return The discount amount.
	 */
	private BigDecimal obtainDiscountAmount(TransferSetting transferSetting)
	{
		BigDecimal amount = BIG_DECIMAL_ZERO;
		Long currentDate = PGSiDateUtil.getCurrentDateMillisUTC();
		Long effectiveEndDate = null;
		Long effectiveStartDate = null;

		for (CustomFee customFee : transferSetting.getCustomFeeList())
		{
			effectiveEndDate = customFee.getEffectiveEndDate();
			effectiveStartDate = customFee.getEffectiveStartDate();

			if (PGSiDateUtil.compareDates(effectiveEndDate, currentDate) >= 0)
			{
				if (ValidationUtil.isNull(effectiveStartDate) ||
						(PGSiDateUtil.compareDates(effectiveStartDate, currentDate) <= 0))
				{
					amount = customFee.getValue();
					break;
				}
			}
		}
		return amount;
	}

	/**
	 * Obtains the expected fee.
	 *
	 * @param transferSetting The transfer setting.
	 * @return The fee.
	 */
	private BigDecimal obtainFee(TransferSetting transferSetting)
	{
		BigDecimal amount = null;

		PlanCategory planCategory = transferSetting.getPlanCategory();

		for (FeeTier feeTier : planCategory.getFeeTierList())
		{
			if ((transferSetting.getTransferAmount().compareTo(feeTier.getMaximumValue()) <= 0) &&
					(transferSetting.getTransferAmount().compareTo(feeTier.getMinimumValue()) >= 0))
			{
				amount = feeTier.getFeeValue();
				break;
			}
		}

		return amount;
	}

	/**
	 * Common method for calling the updateDynamicAttributes method on the MoneyTransferBAD.
	 *
	 * @param moneyTransfer The money transfer to pass.
	 * @param dailyCurrencyRateDetail The daily currency rate to pass.
	 */
	private void updateDynamicAttributes(MoneyTransfer moneyTransfer, DailyCurrencyRateDetail dailyCurrencyRateDetail)
	{
		moneyTransfer.setModelAction(PersistanceActionEnum.NONE);
		MoneyTransferBAD.updateDynamicAttributes(moneyTransfer, CURRENCY_USD, COUNTRY_USA, dailyCurrencyRateDetail);
	}

	/**
	 * Executed before each @Test method.
	 */
	@Before
	public void setUp()
	{
		UserContext userContext = new UserContext(QAT_TEST);
		UserContextHelper.setUserContext(userContext);
	}

	/**
	 * Test successful creation of a {@link MoneyTransferBatch} from a {@link Location} and a
	 * {@link FrequencyBasedEventCalendar}.
	 */
	@Test
	public void testCreateMoneyTransferBatch()
	{
		Location location = getDefaultLocation();
		FrequencyBasedEventCalendar paymentPreparationDate = getDefaultPaymentPreparationDate();
		FrequencyBasedEventCalendar payDate = getDefaultPayDate();

		MoneyTransferBatch moneyTransferBatch = createMoneyTransferBatch(location, paymentPreparationDate, payDate);
		assertCreateMoneyTransferBatch(moneyTransferBatch, location, payDate);
	}

	/**
	 * Test successful creation of a {@link MoneyTransferBatch} from a {@link Location} with no action dates and a
	 * {@link FrequencyBasedEventCalendar}.
	 */
	@Test
	public void testCreateMoneyTransferBatchLocationNoDates()
	{
		Location location = getDefaultLocation();
		location.setBatchApprovalDayLimit(null);
		location.setFundsTransferDayLimit(null);
		FrequencyBasedEventCalendar paymentPreparationDate = getDefaultPaymentPreparationDate();
		FrequencyBasedEventCalendar payDate = getDefaultPayDate();

		MoneyTransferBatch moneyTransferBatch = createMoneyTransferBatch(location, paymentPreparationDate, payDate);
		assertCreateMoneyTransferBatch(moneyTransferBatch, location, payDate);
	}

	/**
	 * Test successful creation of a {@link MoneyTransferBatch} from a {@link Location} with no action dates and a
	 * {@link FrequencyBasedEventCalendar}.
	 */
	@Test
	public void testCreateMoneyTransferBatchLocationNoPayDate()
	{
		Location location = getDefaultLocation();
		FrequencyBasedEventCalendar paymentPreparationDate = getDefaultPaymentPreparationDate();
		FrequencyBasedEventCalendar payDate = null;

		MoneyTransferBatch moneyTransferBatch = createMoneyTransferBatch(location, paymentPreparationDate, payDate);
		assertCreateMoneyTransferBatch(moneyTransferBatch, location, payDate);
	}

	/**
	 * Test successful creation of a {@link MoneyTransfer} from a {@link TransferSetting} where the transfer is from USA
	 * to MEX.
	 */
	@Test
	public void testCreateMoneyTransferUSDToMXP()
	{
		TransferSetting transferSetting = getDefaultTransferSetting(CURRENCY_MXP, COUNTRY_MEX);
		MoneyTransferBatch moneyTransferBatch = getDefaultMoneyTransferBatch();

		MoneyTransfer moneyTransfer = createMoneyTransfer(moneyTransferBatch, transferSetting);

		// Because this is a USD to MXP transfer, the spread should be set to the default.
		BigDecimal expectedSpread = PGSI_DEFAULT_SPREAD;
		assertCreateMoneyTransfer(moneyTransfer, moneyTransferBatch, transferSetting, expectedSpread);
	}

	/**
	 * Test successful creation of a {@link MoneyTransfer} from a {@link TransferSetting} where the transfer is from USA
	 * to USA.
	 */
	@Test
	public void testCreateMoneyTransferUSDToUSD()
	{
		TransferSetting transferSetting = getDefaultTransferSetting(CURRENCY_USD, COUNTRY_USA);
		MoneyTransferBatch moneyTransferBatch = getDefaultMoneyTransferBatch();

		MoneyTransfer moneyTransfer = createMoneyTransfer(moneyTransferBatch, transferSetting);

		// Because this is a USD to USD transfer, the spread should be set to zero.
		BigDecimal expectedSpread = BIG_DECIMAL_ZERO;
		assertCreateMoneyTransfer(moneyTransfer, moneyTransferBatch, transferSetting, expectedSpread);
	}

	/**
	 * Test successful creation of a {@link MoneyTransfer} from a {@link TransferSetting} where the transfer is from USA
	 * to USA and the account is missing from the {@link TransferSetting}.
	 */
	@Test
	public void testCreateMoneyTransferUSDToUSDAccountMissing()
	{
		TransferSetting transferSetting = getDefaultTransferSetting(CURRENCY_USD, COUNTRY_USA);
		transferSetting.setAccount(null);
		MoneyTransferBatch moneyTransferBatch = getDefaultMoneyTransferBatch();

		MoneyTransfer moneyTransfer = createMoneyTransfer(moneyTransferBatch, transferSetting);

		// Because this is a USD to USD transfer, the spread should be set to zero.
		BigDecimal expectedSpread = BIG_DECIMAL_ZERO;
		assertCreateMoneyTransfer(moneyTransfer, moneyTransferBatch, transferSetting, expectedSpread);
	}

	/**
	 * Test successful creation of a {@link MoneyTransfer} from a {@link TransferSetting} where the transfer is from USA
	 * to USA and the customer fee list is empty.
	 */
	@Test
	public void testCreateMoneyTransferUSDToUSDCustomFeeListEmpty()
	{
		TransferSetting transferSetting = getDefaultTransferSetting(CURRENCY_USD, COUNTRY_USA);
		transferSetting.setCustomFeeList(new ArrayList<CustomFee>());
		MoneyTransferBatch moneyTransferBatch = getDefaultMoneyTransferBatch();

		MoneyTransfer moneyTransfer = createMoneyTransfer(moneyTransferBatch, transferSetting);

		// Because this is a USD to USD transfer, the spread should be set to zero.
		BigDecimal expectedSpread = BIG_DECIMAL_ZERO;
		assertCreateMoneyTransfer(moneyTransfer, moneyTransferBatch, transferSetting, expectedSpread);
	}

	/**
	 * Test successful update of a {@link MoneyTransfer} from a {@link DailyCurrencyRateDetail} where the transfer is
	 * from USA to MEX.
	 */
	@Test
	public void testUpdateDynamicAttributesUSDToMXP()
	{
		TransferSetting transferSetting = getDefaultTransferSetting(CURRENCY_MXP, COUNTRY_MEX);
		MoneyTransferBatch moneyTransferBatch = getDefaultMoneyTransferBatch();
		MoneyTransfer moneyTransfer = createMoneyTransfer(moneyTransferBatch, transferSetting);
		DailyCurrencyRateDetail dailyCurrencyRateDetail = getDefaultCurrencyRateDetail(USD_TO_MXP_EXCHANGE_RATE);

		updateDynamicAttributes(moneyTransfer, dailyCurrencyRateDetail);

		/*
		 * MoneyTransfer should have been updated with new values because it was created with an exchange rate of
		 * zero.
		 */
		assertDynamicUpdateAttributes(moneyTransfer, dailyCurrencyRateDetail, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test successful update of a {@link MoneyTransfer} from a {@link DailyCurrencyRateDetail} where the transfer is
	 * from USA to MEX.
	 */
	@Test
	public void testUpdateDynamicAttributesUSDToMXPAlreadySubmittedZeroExchange()
	{
		TransferSetting transferSetting = getDefaultTransferSetting(CURRENCY_MXP, COUNTRY_MEX);
		MoneyTransferBatch moneyTransferBatch = getDefaultMoneyTransferBatch();
		MoneyTransfer moneyTransfer = createMoneyTransfer(moneyTransferBatch, transferSetting);
		addMoneyTransferStatus(moneyTransfer, MoneyTransferStatusEnum.ORDER_SUBMITTED);
		moneyTransfer.setForeignExchangeRate(BIG_DECIMAL_ZERO);
		DailyCurrencyRateDetail dailyCurrencyRateDetail = getDefaultCurrencyRateDetail(USD_TO_MXP_EXCHANGE_RATE);

		updateDynamicAttributes(moneyTransfer, dailyCurrencyRateDetail);

		/*
		 * MoneyTransfer should have been updated with new values because it was created with an exchange rate of
		 * zero.
		 */
		assertDynamicUpdateAttributes(moneyTransfer, dailyCurrencyRateDetail, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test successful update of a {@link MoneyTransfer} from a {@link DailyCurrencyRateDetail} where the transfer is
	 * from USA to MEX.
	 */
	@Test
	public void testUpdateDynamicAttributesUSDToMXPAlreadySubmittedZeroExchangeCust()
	{
		TransferSetting transferSetting = getDefaultTransferSetting(CURRENCY_MXP, COUNTRY_MEX);
		MoneyTransferBatch moneyTransferBatch = getDefaultMoneyTransferBatch();
		MoneyTransfer moneyTransfer = createMoneyTransfer(moneyTransferBatch, transferSetting);
		addMoneyTransferStatus(moneyTransfer, MoneyTransferStatusEnum.ORDER_SUBMITTED);
		moneyTransfer.setForeignExchangeRate(USD_TO_MXP_EXCHANGE_RATE);
		moneyTransfer.setForeignExchangeRateCustomer(BIG_DECIMAL_ZERO);
		DailyCurrencyRateDetail dailyCurrencyRateDetail = getDefaultCurrencyRateDetail(USD_TO_MXP_EXCHANGE_RATE);

		updateDynamicAttributes(moneyTransfer, dailyCurrencyRateDetail);

		/*
		 * MoneyTransfer should have been updated with new values because it was created with an exchange rate of
		 * zero.
		 */
		assertDynamicUpdateAttributes(moneyTransfer, dailyCurrencyRateDetail, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test no update of a {@link MoneyTransfer} from a {@link DailyCurrencyRateDetail} where the transfer is
	 * from USA to MEX is done because the daily currency is null.
	 */
	@Test
	public void testUpdateDynamicAttributesUSDToMXPNoDailyRate()
	{
		TransferSetting transferSetting = getDefaultTransferSetting(CURRENCY_MXP, COUNTRY_MEX);
		MoneyTransferBatch moneyTransferBatch = getDefaultMoneyTransferBatch();
		MoneyTransfer moneyTransfer = createMoneyTransfer(moneyTransferBatch, transferSetting);

		updateDynamicAttributes(moneyTransfer, null);

		/*
		 * MoneyTransfer should NOT have been updated with new values because there is no exchange rate and the transfer
		 * is from USD to MXP.
		 */
		assertDynamicUpdateAttributes(moneyTransfer, null, PersistanceActionEnum.NONE);
	}

	/**
	 * Test successful update of a {@link MoneyTransfer} from a {@link DailyCurrencyRateDetail}.
	 */
	@Test
	public void testUpdateDynamicAttributesUSDToUSD()
	{
		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();
		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);
		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		MoneyTransfer moneyTransfer = createMoneyTransfer(moneyTransferBatch, transferSetting);
		DailyCurrencyRateDetail dailyCurrencyRateDetail = getDefaultCurrencyRateDetail(USD_TO_USD_EXCHAGE_RATE);

		updateDynamicAttributes(moneyTransfer, dailyCurrencyRateDetail);

		/*
		 * MoneyTransfer should have been updated with new values because it was created with an exchange rate of
		 * zero.
		 */
		assertDynamicUpdateAttributes(moneyTransfer, dailyCurrencyRateDetail, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test successful update of a {@link MoneyTransfer} from a {@link DailyCurrencyRateDetail}.
	 */
	@Test
	public void testUpdateDynamicAttributesUSDToUSDAlreadyCancelSubmitted()
	{
		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();
		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);
		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		MoneyTransfer moneyTransfer = createMoneyTransfer(moneyTransferBatch, transferSetting);
		DailyCurrencyRateDetail dailyCurrencyRateDetail = getDefaultCurrencyRateDetail(USD_TO_USD_EXCHAGE_RATE);
		updateDynamicAttributes(moneyTransfer, dailyCurrencyRateDetail);
		addMoneyTransferStatus(moneyTransfer, MoneyTransferStatusEnum.CANCELLATION_SUBMITTED);

		updateDynamicAttributes(moneyTransfer, dailyCurrencyRateDetail);

		/* MoneyTransfer should NOT have been updated with new values because it has been submitted. */
		assertDynamicUpdateAttributes(moneyTransfer, dailyCurrencyRateDetail, PersistanceActionEnum.NONE);
	}

	/**
	 * Test successful update of a {@link MoneyTransfer} from a {@link DailyCurrencyRateDetail}.
	 */
	@Test
	public void testUpdateDynamicAttributesUSDToUSDAlreadyModificationSubmitted()
	{
		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();
		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);
		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		MoneyTransfer moneyTransfer = createMoneyTransfer(moneyTransferBatch, transferSetting);
		DailyCurrencyRateDetail dailyCurrencyRateDetail = getDefaultCurrencyRateDetail(USD_TO_USD_EXCHAGE_RATE);
		updateDynamicAttributes(moneyTransfer, dailyCurrencyRateDetail);
		addMoneyTransferStatus(moneyTransfer, MoneyTransferStatusEnum.MODIFICATION_SUBMITTED);

		updateDynamicAttributes(moneyTransfer, dailyCurrencyRateDetail);

		/* MoneyTransfer should NOT have been updated with new values because it has been submitted. */
		assertDynamicUpdateAttributes(moneyTransfer, dailyCurrencyRateDetail, PersistanceActionEnum.NONE);
	}

	/**
	 * Test successful update of a {@link MoneyTransfer} from a {@link DailyCurrencyRateDetail}.
	 */
	@Test
	public void testUpdateDynamicAttributesUSDToUSDAlreadyOrderSubmitted()
	{
		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();
		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);
		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		MoneyTransfer moneyTransfer = createMoneyTransfer(moneyTransferBatch, transferSetting);
		DailyCurrencyRateDetail dailyCurrencyRateDetail = getDefaultCurrencyRateDetail(USD_TO_USD_EXCHAGE_RATE);
		updateDynamicAttributes(moneyTransfer, dailyCurrencyRateDetail);
		addMoneyTransferStatus(moneyTransfer, MoneyTransferStatusEnum.ORDER_SUBMITTED);

		updateDynamicAttributes(moneyTransfer, dailyCurrencyRateDetail);

		/* MoneyTransfer should NOT have been updated with new values because it has been submitted. */
		assertDynamicUpdateAttributes(moneyTransfer, dailyCurrencyRateDetail, PersistanceActionEnum.NONE);
	}

	/**
	 * Test successful update of a {@link MoneyTransfer} from a {@link DailyCurrencyRateDetail}.
	 */
	@Test
	public void testUpdateDynamicAttributesUSDToUSDNoDailyRate()
	{
		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();
		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);
		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);

		MoneyTransfer moneyTransfer = createMoneyTransfer(moneyTransferBatch, transferSetting);

		updateDynamicAttributes(moneyTransfer, null);

		/*
		 * MoneyTransfer should have been updated with new values because it is created with an exchange rate of
		 * zero.
		 */
		assertDynamicUpdateAttributes(moneyTransfer, null, PersistanceActionEnum.UPDATE);
	}
}
