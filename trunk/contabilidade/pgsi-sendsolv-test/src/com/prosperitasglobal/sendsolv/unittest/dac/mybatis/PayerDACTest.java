package com.prosperitasglobal.sendsolv.unittest.dac.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.StateProvinceRegion;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.AutomatedClearingHouse;
import com.prosperitasglobal.sendsolv.model.CurrencyAvailability;
import com.prosperitasglobal.sendsolv.model.DailyCurrencyRate;
import com.prosperitasglobal.sendsolv.model.DailyCurrencyRateDetail;
import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.PaymentType;
import com.prosperitasglobal.sendsolv.model.criteria.PayerCriteria;
import com.prosperitasglobal.sendsolv.model.request.PayerInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.QATModelOL;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class PayerDACTest. This test covers payers.
 */
public class PayerDACTest extends AbstractTestBaseDAC
{
	/** The Constant ACH_PAYEE_CODE. */
	private static final String ACH_PAYEE_CODE = "111110    ";

	private static final int NUM_OF_PAYERS_TO_INSERT = 10;

	private static final String PAYER_NOT_NULL = "Payer should not be null";

	private static final String STATE_PROVINCE_REGION_LIST =
			"stateProvinceRegion list should contain at least 1 region";

	private static final String STATE_PROVINCE_REGION_LIST_CONTAINS = "stateProvinceRegion list contains ";

	private static final String PAYMENT_TYPE_LIST =
			"paymentTypeList list should contain at least 1 payment type";

	private static final String PAYMENT_TYPE_LIST_CONTAINS = "paymentTypeList list contains ";

	private static final Integer PAGE_SIZE = 2;

	/** The Constant NUMBER_OF_ROWS_NEED_TO_MATCH. */
	private static final String NUMBER_OF_ROWS_NEED_TO_MATCH = "Number of rows need to match";

	private static final String NAME_SORT_FIELD = "name";
	private static final String COUNTRY_SORT_FIELD = "country";

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(OrganizationDACTest.class);

	/**
	 * Compare 2 attributes against the sort.
	 *
	 * @param attribute1 The first attribute.
	 * @param attribute2 The second attribute.
	 * @param direction The sort direction.
	 */
	private void assertAttributeSort(String attribute1, String attribute2, Direction direction)
	{
		if (Direction.Ascending == direction)
		{
			Assert.assertTrue("Asc Sort Incorrect! Attribute Name " + attribute1 + " is greater than Attribute "
					+ attribute2, attribute1.toUpperCase().compareTo(attribute2.toUpperCase()) <= 0);
		}
		else
		{
			Assert.assertTrue("Desc Sort Incorrect! Attribute Name " + attribute1 + " is less than Attribute "
					+ attribute2, attribute1.toUpperCase().compareTo(attribute2.toUpperCase()) >= 0);
		}
	}

	/**
	 * This method compares fields between two CurrencyAvailability objects.
	 *
	 * @param currencyAvailability The currency availability inserted/updated.
	 * @param dbCurrencyAvailability The DB currency availability fetched.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsCurrencyAvailability(CurrencyAvailability currencyAvailability,
			CurrencyAvailability dbCurrencyAvailability, PersistanceActionEnum modelAction)
	{
		assertFieldsQATModelOL(currencyAvailability, dbCurrencyAvailability, modelAction);

		if (modelAction == PersistanceActionEnum.INSERT)
		{
			Assert.assertNotNull("DB CurrencyAvailability id must not be null!", dbCurrencyAvailability.getId());
		}
		else if ((modelAction == PersistanceActionEnum.UPDATE) || (modelAction == PersistanceActionEnum.NONE))
		{
			Assert.assertEquals("CurrencyAvailability id mismatch !", currencyAvailability.getId(),
					dbCurrencyAvailability.getId());
		}

		Assert.assertTrue(
				"CurrencyAvailability credit mismatch!",
				currencyAvailability.getCurrencyCreditCumulative().compareTo(
						dbCurrencyAvailability.getCurrencyCreditCumulative()) == 0);
		Assert.assertTrue(
				"CurrencyAvailability debit mismatch!",
				currencyAvailability.getCurrencyDebitCumulative().compareTo(
						dbCurrencyAvailability.getCurrencyDebitCumulative()) == 0);
		Assert.assertEquals("CurrencyAvailability currency mismatch!", currencyAvailability.getCurrency().getCode(),
				dbCurrencyAvailability.getCurrency().getCode());
		Assert.assertTrue(
				"CurrencyAvailability effectiveForeignExchangeRate mismatch!",
				currencyAvailability.getEffectiveForeignExchangeRate().compareTo(
						dbCurrencyAvailability.getEffectiveForeignExchangeRate()) == 0);
		Assert.assertEquals("CurrencyAvailability payerId mismatch!", currencyAvailability.getPayerId(),
				dbCurrencyAvailability.getPayerId());
	}

	/**
	 * Test CurrencyAvailability against database by id.
	 *
	 * @param dbCurrencyAvailabilityList The list of CurrencyAvailability from the db.
	 * @param currencyAvailability The CurrencyAvailability inserted/updated.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsCurrencyAvailability(List<CurrencyAvailability> dbCurrencyAvailabilityList,
			CurrencyAvailability currencyAvailability, PersistanceActionEnum modelAction)
	{
		boolean currencyAvailabilityFound = false;
		for (CurrencyAvailability dbCurrencyAvailability : dbCurrencyAvailabilityList)
		{
			if (dbCurrencyAvailability.getId().equals(currencyAvailability.getId()))
			{
				currencyAvailabilityFound = true;
				if ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE)
						|| (modelAction == PersistanceActionEnum.NONE))
				{
					assertFieldsCurrencyAvailability(currencyAvailability, dbCurrencyAvailability, modelAction);
					break;
				}
				else
				{
					Assert.assertTrue("DB CurrencyAvailability still exists after DELETE", false);
				}
			}
		}

		if (!currencyAvailabilityFound
				&& ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE) ||
						(modelAction == PersistanceActionEnum.NONE)))
		{
			Assert.assertTrue("DB CurrencyAvailability does not exists after INSERT/UPDATE/NONE", false);
		}
	}

	/**
	 * This method compares fields between two DailyCurrencyRate objects.
	 *
	 * @param dailyCurrencyRate The daily currency rate inserted/updated.
	 * @param dbDailyCurrencyRate The DB daily currency rate fetched.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsDailyCurrencyRate(DailyCurrencyRate dailyCurrencyRate,
			DailyCurrencyRate dbDailyCurrencyRate, PersistanceActionEnum modelAction)
	{
		assertFieldsQATModel(dailyCurrencyRate, dbDailyCurrencyRate, modelAction);

		if (modelAction == PersistanceActionEnum.INSERT)
		{
			Assert.assertNotNull("DB DailyCurrencyRate id must not be null!", dbDailyCurrencyRate.getId());
		}
		else if ((modelAction == PersistanceActionEnum.UPDATE) || (modelAction == PersistanceActionEnum.NONE))
		{
			Assert.assertEquals("DailyCurrencyRate id mismatch !", dailyCurrencyRate.getId(),
					dbDailyCurrencyRate.getId());
		}

		Assert.assertEquals("DailyCurrencyRate currency mismatch!", dailyCurrencyRate.getCurrency().getCode(),
				dbDailyCurrencyRate.getCurrency().getCode());
		Assert.assertEquals("DailyCurrencyRate date mismatch!", dailyCurrencyRate.getValidForDate(),
				dbDailyCurrencyRate.getValidForDate());
		Assert.assertEquals("DailyCurrencyRate payer mismatch!", dailyCurrencyRate.getPayerId(),
				dbDailyCurrencyRate.getPayerId());

		// Assert the child DailyCurrencyRateDetails
		if (!ValidationUtil.isNullOrEmpty(dailyCurrencyRate.getDailyCurrencyRateDetailList()))
		{
			for (DailyCurrencyRateDetail dailyCurrencyRateDetail : dailyCurrencyRate.getDailyCurrencyRateDetailList())
			{
				if (modelAction == PersistanceActionEnum.INSERT)
				{
					assertFieldsDailyCurrencyRateDetail(dbDailyCurrencyRate.getDailyCurrencyRateDetailList(),
							dailyCurrencyRateDetail, modelAction);
				}
				else
				{
					assertFieldsDailyCurrencyRateDetail(dbDailyCurrencyRate.getDailyCurrencyRateDetailList(),
							dailyCurrencyRateDetail,
							PersistanceActionEnum.NONE);
				}
			}
		}
	}

	/**
	 * Test DailyCurrencyRate against database by id.
	 *
	 * @param dbDailyCurrencyRateList The list of DailyCurrencyRate from the db.
	 * @param dailyCurrencyRate The DailyCurrencyRate inserted/updated.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsDailyCurrencyRate(List<DailyCurrencyRate> dbDailyCurrencyRateList,
			DailyCurrencyRate dailyCurrencyRate, PersistanceActionEnum modelAction)
	{
		boolean dailyCurrencyRateFound = false;
		for (DailyCurrencyRate dbDailyCurrencyRate : dbDailyCurrencyRateList)
		{
			if (dbDailyCurrencyRate.getId().equals(dailyCurrencyRate.getId()))
			{
				dailyCurrencyRateFound = true;
				if ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE)
						|| (modelAction == PersistanceActionEnum.NONE))
				{
					assertFieldsDailyCurrencyRate(dailyCurrencyRate, dbDailyCurrencyRate, modelAction);
					break;
				}
				else
				{
					Assert.assertTrue("DB DailyCurrencyRate still exists after DELETE", false);
				}
			}
		}

		if (!dailyCurrencyRateFound
				&& ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE) ||
						(modelAction == PersistanceActionEnum.NONE)))
		{
			Assert.assertTrue("DB DailyCurrencyRate does not exists after INSERT/UPDATE/NONE", false);
		}
	}

	/**
	 * Test DailyCurrencyRateDetail against database by id.
	 *
	 * @param dbDailyCurrencyRateDetailList The list of DailyCurrencyRateDetail from the db.
	 * @param dailyCurrencyRateDetail The DailyCurrencyRateDetail inserted/updated.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsDailyCurrencyRateDetail(List<DailyCurrencyRateDetail> dbDailyCurrencyRateDetailList,
			DailyCurrencyRateDetail dailyCurrencyRateDetail, PersistanceActionEnum modelAction)
	{
		boolean dailyCurrencyRateDetailFound = false;
		for (DailyCurrencyRateDetail dbDailyCurrencyRateDetail : dbDailyCurrencyRateDetailList)
		{
			if (dbDailyCurrencyRateDetail.getId().equals(dailyCurrencyRateDetail.getId()))
			{
				dailyCurrencyRateDetailFound = true;
				if ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE)
						|| (modelAction == PersistanceActionEnum.NONE))
				{
					assertFieldsDailyCurrencyRateDetail(dailyCurrencyRateDetail, dbDailyCurrencyRateDetail,
							modelAction);
					break;
				}
				else
				{
					Assert.assertTrue("DB DailyCurrencyRateDetail still exists after DELETE", false);
				}
			}
		}

		if (!dailyCurrencyRateDetailFound
				&& ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE) ||
						(modelAction == PersistanceActionEnum.NONE)))
		{
			Assert.assertTrue("DB DailyCurrencyRateDetail does not exists after INSERT/UPDATE/NONE", false);
		}
	}

	/**
	 * This method compares fields between two DailyCurrencyRateDetail objects.
	 *
	 * @param dailyCurrencyRateDetail The DailyCurrencyRateDetail inserted/updated.
	 * @param dbDailyCurrencyRateDetail The DB DailyCurrencyRateDetail fetched.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsDailyCurrencyRateDetail(DailyCurrencyRateDetail dailyCurrencyRateDetail,
			DailyCurrencyRateDetail dbDailyCurrencyRateDetail, PersistanceActionEnum modelAction)
	{
		assertFieldsQATModel(dailyCurrencyRateDetail, dbDailyCurrencyRateDetail, modelAction);

		Assert.assertEquals("DailyCurrencyRateDetail dailyCurrencyRateId mismatch!",
				dailyCurrencyRateDetail.getDailyCurrencyRateId(), dbDailyCurrencyRateDetail.getDailyCurrencyRateId());
		Assert.assertTrue("DailyCurrencyRateDetail exchangeRate mismatch!",
				dailyCurrencyRateDetail.getExchangeRate().compareTo(dbDailyCurrencyRateDetail.getExchangeRate()) == 0);
	}

	/**
	 * This method compares fields between two Payer objects.
	 *
	 * @param payer The payer inserted/updated.
	 * @param dbPayer The DB payer fetched.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsPayer(Payer payer, Payer dbPayer, PersistanceActionEnum modelAction)
	{
		assertFieldsQATModel(payer, dbPayer, modelAction);

		if (modelAction == PersistanceActionEnum.INSERT)
		{
			Assert.assertNotNull("DB Payer id must not be null!", dbPayer.getId());
		}
		else if ((modelAction == PersistanceActionEnum.UPDATE) || (modelAction == PersistanceActionEnum.NONE))
		{
			Assert.assertEquals("Payer id mismatch !", payer.getId(), dbPayer.getId());
		}

		Assert.assertEquals("Payer achPayeeCode mismatch!", payer.getAchPayeeCode(), dbPayer.getAchPayeeCode());
		Assert.assertEquals("Payer automatedClearingHouseId mismatch!", payer.getAutomatedClearingHouseId(),
				dbPayer.getAutomatedClearingHouseId());
		Assert.assertEquals("Payer country mismatch !", payer.getCountry().getCode(), dbPayer.getCountry().getCode());
		Assert.assertEquals("Payer name mismatch !", payer.getName(), dbPayer.getName());

		// Assert the child PaymentType's
		for (PaymentType paymentType : payer.getPaymentTypeList())
		{
			if (modelAction == PersistanceActionEnum.INSERT)
			{
				assertFieldsPaymentType(dbPayer.getPaymentTypeList(), paymentType, modelAction);
			}
			else
			{
				assertFieldsPaymentType(dbPayer.getPaymentTypeList(), paymentType, PersistanceActionEnum.NONE);
			}
		}

		// Assert the child StateProvinceRegion's
		for (StateProvinceRegion stateProvinceRegion : payer.getStateProvinceRegionList())
		{
			if (modelAction == PersistanceActionEnum.INSERT)
			{
				assertFieldsStateProvinceRegion(dbPayer.getStateProvinceRegionList(), stateProvinceRegion, modelAction);
			}
			else
			{
				assertFieldsStateProvinceRegion(dbPayer.getStateProvinceRegionList(), stateProvinceRegion,
						PersistanceActionEnum.NONE);
			}
		}

		// Assert the child Daily Currency Rates
		for (DailyCurrencyRate dailyCurrencyRate : payer.getDailyCurrencyRateList())
		{
			if (modelAction == PersistanceActionEnum.INSERT)
			{
				assertFieldsDailyCurrencyRate(dbPayer.getDailyCurrencyRateList(), dailyCurrencyRate, modelAction);
			}
			else
			{
				assertFieldsDailyCurrencyRate(dbPayer.getDailyCurrencyRateList(), dailyCurrencyRate,
						PersistanceActionEnum.NONE);
			}
		}

		// Assert the child Currency Availability
		for (CurrencyAvailability currencyAvailability : payer.getCurrencyAvailabilityList())
		{
			if (modelAction == PersistanceActionEnum.INSERT)
			{
				assertFieldsCurrencyAvailability(dbPayer.getCurrencyAvailabilityList(), currencyAvailability,
						modelAction);
			}
			else
			{
				assertFieldsCurrencyAvailability(dbPayer.getCurrencyAvailabilityList(), currencyAvailability,
						PersistanceActionEnum.NONE);
			}
		}

	}

	/**
	 * This method compares fields between two PaymentType objects.
	 *
	 * @param paymentType The paymentType inserted/updated.
	 * @param dbPaymentType The DB paymentType fetched.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsPaymentType(PaymentType paymentType, PaymentType dbPaymentType,
			PersistanceActionEnum modelAction)
	{
		assertFieldsQATModel(paymentType, dbPaymentType, modelAction);

		Assert.assertEquals("PaymentType type mismatch!", paymentType.getType(), dbPaymentType.getType());
		Assert.assertEquals("PaymentType currency mismatch!", paymentType.getCurrency().getCode(), dbPaymentType
				.getCurrency().getCode());
	}

	/**
	 * Test PaymentType against database by id.
	 *
	 * @param dbPaymentTypeList The list of PaymentType from the db.
	 * @param paymentType The PaymentType inserted/updated.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsPaymentType(List<PaymentType> dbPaymentTypeList, PaymentType paymentType,
			PersistanceActionEnum modelAction)
	{
		boolean paymentTypeFound = false;
		for (PaymentType dbPaymentType : dbPaymentTypeList)
		{
			if (dbPaymentType.getTypeValue().equals(paymentType.getTypeValue()))
			{
				paymentTypeFound = true;
				if ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE)
						|| (modelAction == PersistanceActionEnum.NONE))
				{
					assertFieldsPaymentType(paymentType, dbPaymentType, modelAction);
					break;
				}
				else
				{
					Assert.assertTrue("DB PaymentType still exists after DELETE", false);
				}
			}
		}

		if (!paymentTypeFound
				&& ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE) ||
						(modelAction == PersistanceActionEnum.NONE)))
		{
			Assert.assertTrue("DB PaymentType does not exists after INSERT/UPDATE/NONE", false);
		}
	}

	/**
	 * Compare 2 different QATModel objects.
	 *
	 * @param qatModel The first QATModel object.
	 * @param dbQatModel The second QATModel object.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsQATModel(QATModel qatModel, QATModel dbQatModel, PersistanceActionEnum modelAction)
	{
		Assert.assertEquals("QATModel create date UTC mismatch!", qatModel.getCreateDateUTC(),
				dbQatModel.getCreateDateUTC());
		Assert.assertEquals("QATModel create user mismatch!", qatModel.getCreateUser(), dbQatModel.getCreateUser());

		if (modelAction == PersistanceActionEnum.INSERT)
		{
			Assert.assertNull("DB QATModel modify date UTC is not null", dbQatModel.getModifyDateUTC());
			Assert.assertNull("DB QATModel modify user is not null", dbQatModel.getModifyUser());
		}
		else if ((modelAction == PersistanceActionEnum.UPDATE) || (modelAction == PersistanceActionEnum.NONE))
		{
			Assert.assertEquals("QATModel modify date UTC mismatch!", dbQatModel.getModifyDateUTC(),
					qatModel.getModifyDateUTC());
			Assert.assertEquals("QATModel modify user mismatch!", qatModel.getModifyUser(), dbQatModel.getModifyUser());
		}
	}

	/**
	 * Compare 2 different QATModelOL objects.
	 *
	 * @param qatModelOL The first QATModel object.
	 * @param dbQatModelOL The second QATModel object.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsQATModelOL(QATModelOL qatModelOL, QATModelOL dbQatModelOL,
			PersistanceActionEnum modelAction)
	{
		assertFieldsQATModel(qatModelOL, dbQatModelOL, modelAction);

		if (modelAction == PersistanceActionEnum.INSERT)
		{
			Assert.assertEquals("DB QATModelOL version is not 0 for an INSERT", 0,
					dbQatModelOL.getVersion().intValue());
		}
		else if (modelAction == PersistanceActionEnum.UPDATE)
		{
			Assert.assertEquals("DB QATModelOL version is not 1 more than before the UPDATE", qatModelOL.getVersion()
					.intValue() + 1, dbQatModelOL.getVersion().intValue());
		}
		else if (modelAction == PersistanceActionEnum.NONE)
		{
			Assert.assertEquals("QATModelOL version is not equal to DB QATModlOL", qatModelOL.getVersion()
					.intValue(), dbQatModelOL.getVersion().intValue());
		}
	}

	/**
	 * This method compares fields between two StateProvinceRegion objects.
	 *
	 * @param stateProvinceRegion The stateProvinceRegion inserted/updated.
	 * @param dbStateProvinceRegion The DB stateProvinceRegion fetched.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsStateProvinceRegion(StateProvinceRegion stateProvinceRegion,
			StateProvinceRegion dbStateProvinceRegion, PersistanceActionEnum modelAction)
	{
		assertFieldsQATModel(stateProvinceRegion, dbStateProvinceRegion, modelAction);

		Assert.assertEquals("StateProvinceRegion code mismatch!", stateProvinceRegion.getCode(),
				dbStateProvinceRegion.getCode());
	}

	/**
	 * Test StateProvinceRegion against database by id.
	 *
	 * @param dbStateProvinceRegionList The list of StateProvinceRegion from the db.
	 * @param stateProvinceRegion The StateProvinceRegion inserted/updated.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsStateProvinceRegion(List<StateProvinceRegion> dbStateProvinceRegionList,
			StateProvinceRegion stateProvinceRegion, PersistanceActionEnum modelAction)
	{
		boolean stateProvinceRegionFound = false;
		for (StateProvinceRegion dbStateProvinceRegion : dbStateProvinceRegionList)
		{
			if (dbStateProvinceRegion.getId().equals(stateProvinceRegion.getId()))
			{
				stateProvinceRegionFound = true;
				if ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE)
						|| (modelAction == PersistanceActionEnum.NONE))
				{
					assertFieldsStateProvinceRegion(stateProvinceRegion, dbStateProvinceRegion, modelAction);
					break;
				}
				else
				{
					Assert.assertTrue("DB StateProvinceRegion still exists after DELETE", false);
				}
			}
		}

		if (!stateProvinceRegionFound
				&& ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE) ||
						(modelAction == PersistanceActionEnum.NONE)))
		{
			Assert.assertTrue("DB StateProvinceRegion does not exists after INSERT/UPDATE/NONE", false);
		}
	}

	/**
	 * Compare the results of the fetch.
	 *
	 * @param response The response from the fetch.
	 * @param modelAction The model action that was performed before the fetch.
	 */
	private void assertFetch(InternalResultsResponse<?> response, PersistanceActionEnum modelAction)
	{
		if ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE)
				|| (modelAction == PersistanceActionEnum.NONE))
		{
			CommonTestRoutines.assertResultResponse(response);
			Assert.assertTrue("response.getResultsList().size() needs to be > 0",
					response.getResultsList().size() > 0);
			Assert.assertNotNull("response.getFirstResult() cannot be null", response.getFirstResult());
		}
		else if (modelAction == PersistanceActionEnum.DELETE)
		{
			Assert.assertTrue("response.getResultsList().size() needs to be = 0",
					response.getResultsList().size() == 0);
			Assert.assertNull("response.getFirstResult() must be null", response.getFirstResult());
		}
	}

	/**
	 * Compares the fetch result for a Payer against the criteria member.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param payerList The list of payer returned from the fetch.
	 */
	private void assertFilterPayer(PayerCriteria criteria, List<Payer> payerList)
	{
		Assert.assertTrue("Payer Fetch contains 0!", payerList.size() > 0);

		for (Payer payer : payerList)
		{
			if (!ValidationUtil.isNull(criteria.getId()))
			{
				Assert.assertEquals("Payer id mismatch!", criteria.getId(), payer.getId());
			}

			if (!ValidationUtil.isNull(criteria.getName()))
			{
				Assert.assertEquals("Payer name mismatch!", criteria.getName(), payer.getName());
			}

			if (!ValidationUtil.isNull(criteria.getAutomatedClearingHouse()))
			{
				Assert.assertEquals("Payer automated clearing house mismatch!", criteria.getAutomatedClearingHouse()
						.getId(), payer.getAutomatedClearingHouseId());
			}

			if (!ValidationUtil.isNull(criteria.getCountry()))
			{
				Assert.assertEquals("Payer country mismatch!", criteria.getCountry().getCode(), payer.getCountry()
						.getCode());
			}
		}
	}

	/**
	 * Asserts that the list of payer objects is sorted correctly.
	 *
	 * @param payerList The sorted list of payer objects.
	 * @param fieldName The field name that was sorted.
	 * @param direction The direction of the sort.
	 */
	private void assertSortPayer(List<Payer> payerList, String fieldName, Direction direction)
	{
		boolean firstTime = true;
		Payer previousPayer = null;
		for (Payer payer : payerList)
		{
			if (firstTime)
			{
				previousPayer = payer;
				firstTime = false;
			}
			else
			{
				if (fieldName.equals(NAME_SORT_FIELD))
				{
					assertAttributeSort(previousPayer.getName(), payer.getName(), direction);
				}
				else if (fieldName.equals(COUNTRY_SORT_FIELD))
				{
					assertAttributeSort(previousPayer.getCountry().getCode(), payer.getCountry().getCode(), direction);
				}
				else
				{
					Assert.assertTrue("Unknown Payer sort field: " + fieldName, false);
				}
			}
		}
	}

	/**
	 * Test payer against database by id.
	 *
	 * @param payer The payer
	 * @param modelAction The model action performed.
	 */
	private void comparePayerAgainstDatabaseById(Payer payer, PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerById(payer.getId());
		assertFetch(response, modelAction);
		if (modelAction != PersistanceActionEnum.DELETE)
		{
			assertFieldsPayer(payer, response.getFirstResult(), modelAction);
		}
	}

	/**
	 * Test payment type against database by id.
	 *
	 * @param payer The payer.
	 * @param paymentType The payment type.
	 * @param modelAction The model action performed.
	 */
	private void comparePaymentTypeAgainstDatabaseById(Payer payer, PaymentType paymentType,
			PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerById(payer.getId());
		CommonTestRoutines.assertResultResponse(response);
		assertFieldsPaymentType(response.getFirstResult().getPaymentTypeList(), paymentType, modelAction);
	}

	/**
	 * Test currencyAvailability against database by id.
	 *
	 * @param payer The payer.
	 * @param currencyAvailability The currency availability.
	 * @param modelAction The model action performed.
	 */
	private void compareCurrencyAvailabilityAgainstDatabaseById(Payer payer, CurrencyAvailability currencyAvailability,
			PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerById(payer.getId());
		CommonTestRoutines.assertResultResponse(response);
		assertFieldsCurrencyAvailability(response.getFirstResult().getCurrencyAvailabilityList(), currencyAvailability,
				modelAction);
	}

	/**
	 * Test dailyCurrencyRate against database by id.
	 *
	 * @param payer The payer.
	 * @param dailyCurrencyRate The daily currency rate.
	 * @param modelAction The model action performed.
	 */
	private void compareDailyCurrencyRateAgainstDatabaseById(Payer payer, DailyCurrencyRate dailyCurrencyRate,
			PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerById(payer.getId());
		CommonTestRoutines.assertResultResponse(response);
		assertFieldsDailyCurrencyRate(response.getFirstResult().getDailyCurrencyRateList(), dailyCurrencyRate,
				modelAction);
	}

	/**
	 * Test state province region database by id.
	 *
	 * @param payer The payer.
	 * @param stateProvinceRegion The state province region.
	 * @param modelAction The model action performed.
	 */
	private void compareStateProvinceRegionAgainstDatabaseById(Payer payer, StateProvinceRegion stateProvinceRegion,
			PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerById(payer.getId());
		CommonTestRoutines.assertResultResponse(response);
		assertFieldsStateProvinceRegion(response.getFirstResult().getStateProvinceRegionList(), stateProvinceRegion,
				modelAction);
	}

	/**
	 * Insert payers.
	 *
	 * @param numberOfPayersToInsert The number of payers to insert
	 * @param uniqueIndexForPayer Unique index for payers.
	 * @return The {@link List} of {@link Payer}.
	 */
	private List<Payer> insertPayers(Integer numberOfPayersToInsert, boolean uniqueIndexForPayer)
	{
		ArrayList<Payer> payerList = new ArrayList<Payer>();

		Payer payer = null;
		for (int i = 0; i < numberOfPayersToInsert; i++)
		{
			if (uniqueIndexForPayer)
			{
				payer = insertPayer(i + 1);
			}
			else
			{
				payer = insertPayer(0);
			}

			if (!ValidationUtil.isNull(payer))
			{
				payerList.add(payer);
			}
		}

		return payerList;
	}

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		executeSqlScript("com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/deleteBusiness.sql", false);
	}

	/**
	 * Test successful delete of a payer.
	 */
	@Test
	public void testDeletePayer()
	{
		Payer payer = insertPayer();
		payer.setModelAction(PersistanceActionEnum.DELETE);
		getPayerDAC().deletePayer(payer);
		comparePayerAgainstDatabaseById(payer, PersistanceActionEnum.DELETE);
	}

	/**
	 * Test fetch payer by id.
	 */
	@Test
	public void testfetchPayerById()
	{
		Payer payer = insertPayer();

		LOG.info("\nStarting testFetchPayerById");
		InternalResultsResponse<Payer> resp = getPayerDAC().fetchPayerById(payer.getId());

		Payer dbPayer = resp.getFirstResult();

		Assert.assertNotNull(PAYER_NOT_NULL, dbPayer);
		LOG.info("Payer is: " + dbPayer.toString());

		LOG.info("Now we'll \"access\" the state province region list list which will in turn invoke SQL");

		List<StateProvinceRegion> stateProvinceRegionList = dbPayer.getStateProvinceRegionList();
		Assert.assertNotNull("stateProvinceRegion list should not be null for fetchPayerById",
				stateProvinceRegionList);
		Assert.assertTrue(STATE_PROVINCE_REGION_LIST,
				stateProvinceRegionList.size() > 0);
		LOG.info(STATE_PROVINCE_REGION_LIST_CONTAINS + stateProvinceRegionList.size());

		LOG.info("Now we'll \"access\" the PaymentType list which will in turn invoke SQL");

		List<PaymentType> paymentTypeList = dbPayer.getPaymentTypeList();
		Assert.assertNotNull("PaymentType list should not be null for fetchPayerById", paymentTypeList);
		Assert.assertTrue(PAYMENT_TYPE_LIST, paymentTypeList.size() > 0);
		LOG.info(PAYMENT_TYPE_LIST_CONTAINS + paymentTypeList.size());

	}

	/**
	 * Test successful fetch of payer by request, no criteria specified.
	 */
	@Test
	public void testFetchPayerByRequest()
	{
		insertPayers(NUM_OF_PAYERS_TO_INSERT, true);

		PayerInquiryRequest request = new PayerInquiryRequest();
		request.setPageSize(PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		PayerCriteria criteria = new PayerCriteria();
		request.setCriteria(criteria);

		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterPayer(criteria, response.getResultsList());
	}

	/**
	 * Test successful fetch of payer by request, automated clearing house specified in the criteria specified.
	 */
	@Test
	public void testFetchPayerByRequestAutomatedClearingHouse()
	{
		List<Payer> payerList = insertPayers(NUM_OF_PAYERS_TO_INSERT, true);

		PayerInquiryRequest request = new PayerInquiryRequest();
		request.setPageSize(PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		PayerCriteria criteria = new PayerCriteria();
		AutomatedClearingHouse automatedClearingHouse = new AutomatedClearingHouse();
		automatedClearingHouse.setId(payerList.get(0).getAutomatedClearingHouseId());
		criteria.setAutomatedClearingHouse(automatedClearingHouse);
		request.setCriteria(criteria);

		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterPayer(criteria, response.getResultsList());
	}

	/**
	 * Test successful fetch of payer by request, payer country specified in the criteria specified.
	 */
	@Test
	public void testFetchPayerByRequestCountry()
	{
		List<Payer> payerList = insertPayers(NUM_OF_PAYERS_TO_INSERT, true);

		PayerInquiryRequest request = new PayerInquiryRequest();
		request.setPageSize(PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		PayerCriteria criteria = new PayerCriteria();
		criteria.setCountry(payerList.get(0).getCountry());
		request.setCriteria(criteria);

		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterPayer(criteria, response.getResultsList());
	}

	/**
	 * Test successful fetch of payer by request, payer id specified in the criteria specified.
	 */
	@Test
	public void testFetchPayerByRequestId()
	{
		List<Payer> payerList = insertPayers(NUM_OF_PAYERS_TO_INSERT, true);

		PayerInquiryRequest request = new PayerInquiryRequest();
		request.setPageSize(PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		PayerCriteria criteria = new PayerCriteria();
		criteria.setId(payerList.get(0).getId());
		request.setCriteria(criteria);

		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterPayer(criteria, response.getResultsList());
	}

	/**
	 * Test successful fetch of payer by request, payer name specified in the criteria specified.
	 */
	@Test
	public void testFetchPayerByRequestName()
	{
		List<Payer> payerList = insertPayers(NUM_OF_PAYERS_TO_INSERT, true);

		PayerInquiryRequest request = new PayerInquiryRequest();
		request.setPageSize(PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		PayerCriteria criteria = new PayerCriteria();
		criteria.setName(payerList.get(0).getName());
		request.setCriteria(criteria);

		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterPayer(criteria, response.getResultsList());
	}

	/**
	 * Test successful fetch of payer by request, ach payee code specified in the criteria.
	 */
	@Test
	public void testFetchPayerByRequestAchPayeeCode()
	{
		insertPayers(NUM_OF_PAYERS_TO_INSERT, true);

		PayerInquiryRequest request = new PayerInquiryRequest();
		request.setPageSize(PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		PayerCriteria criteria = new PayerCriteria();
		criteria.setAchPayeeCode(ACH_PAYEE_CODE);
		request.setCriteria(criteria);

		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterPayer(criteria, response.getResultsList());
	}

	/**
	 * Test successful fetch of payer by request, sorted by default order.
	 */
	@Test
	public void testFetchPayerByRequestSortedByDefault()
	{
		insertPayers(NUM_OF_PAYERS_TO_INSERT, true);

		PayerInquiryRequest request = new PayerInquiryRequest();
		request.setPageSize(PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();

		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortPayer(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		Payer lastPayerFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// go to next page
		request.setStartPage(1);
		response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertAttributeSort(lastPayerFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
		assertSortPayer(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		lastPayerFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// one more page
		request.setStartPage(2);
		response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortPayer(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		assertAttributeSort(lastPayerFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
	}

	/**
	 * Test successful fetch of payer by request, sorted by country order ascending.
	 */
	@Test
	public void testFetchPayerByRequestSortedByCountryAsc()
	{
		insertPayers(NUM_OF_PAYERS_TO_INSERT, true);

		PayerInquiryRequest request = new PayerInquiryRequest();
		request.setPageSize(PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.addSortExpressions(new SortExpression(COUNTRY_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortPayer(response.getResultsList(), COUNTRY_SORT_FIELD, Direction.Ascending);
		Payer lastPayerFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// go to next page
		request.setStartPage(1);
		response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertAttributeSort(lastPayerFetched.getCountry().getCode(), response.getFirstResult().getCountry().getCode(),
				Direction.Ascending);
		assertSortPayer(response.getResultsList(), COUNTRY_SORT_FIELD, Direction.Ascending);
		lastPayerFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// one more page
		request.setStartPage(2);
		response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortPayer(response.getResultsList(), COUNTRY_SORT_FIELD, Direction.Ascending);
		assertAttributeSort(lastPayerFetched.getCountry().getCode(), response.getFirstResult().getCountry().getCode(),
				Direction.Ascending);
	}

	/**
	 * Test successful fetch of payer by request, sorted by country order descending.
	 */
	@Test
	public void testFetchPayerByRequestSortedByCountryDesc()
	{
		insertPayers(NUM_OF_PAYERS_TO_INSERT, true);

		PayerInquiryRequest request = new PayerInquiryRequest();
		request.setPageSize(PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.addSortExpressions(new SortExpression(COUNTRY_SORT_FIELD, Direction.Descending));

		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortPayer(response.getResultsList(), COUNTRY_SORT_FIELD, Direction.Descending);
		Payer lastPayerFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// go to next page
		request.setStartPage(1);
		response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertAttributeSort(lastPayerFetched.getCountry().getCode(), response.getFirstResult().getCountry().getCode(),
				Direction.Descending);
		assertSortPayer(response.getResultsList(), COUNTRY_SORT_FIELD, Direction.Descending);
		lastPayerFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// one more page
		request.setStartPage(2);
		response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortPayer(response.getResultsList(), COUNTRY_SORT_FIELD, Direction.Descending);
		assertAttributeSort(lastPayerFetched.getCountry().getCode(), response.getFirstResult().getCountry().getCode(),
				Direction.Descending);
	}

	/**
	 * Test successful fetch of payer by request, sorted by name order ascending.
	 */
	@Test
	public void testFetchPayerByRequestSortedByNameAsc()
	{
		insertPayers(NUM_OF_PAYERS_TO_INSERT, true);

		PayerInquiryRequest request = new PayerInquiryRequest();
		request.setPageSize(PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.addSortExpressions(new SortExpression(NAME_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortPayer(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		Payer lastPayerFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// go to next page
		request.setStartPage(1);
		response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertAttributeSort(lastPayerFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
		assertSortPayer(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		lastPayerFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// one more page
		request.setStartPage(2);
		response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortPayer(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		assertAttributeSort(lastPayerFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
	}

	/**
	 * Test successful fetch of payer by request, sorted by name order descending.
	 */
	@Test
	public void testFetchPayerByRequestSortedByNameDesc()
	{
		insertPayers(NUM_OF_PAYERS_TO_INSERT, true);

		PayerInquiryRequest request = new PayerInquiryRequest();
		request.setPageSize(PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.addSortExpressions(new SortExpression(NAME_SORT_FIELD, Direction.Descending));

		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortPayer(response.getResultsList(), NAME_SORT_FIELD, Direction.Descending);
		Payer lastPayerFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// go to next page
		request.setStartPage(1);
		response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertAttributeSort(lastPayerFetched.getName(), response.getFirstResult().getName(),
				Direction.Descending);
		assertSortPayer(response.getResultsList(), NAME_SORT_FIELD, Direction.Descending);
		lastPayerFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// one more page
		request.setStartPage(2);
		response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortPayer(response.getResultsList(), NAME_SORT_FIELD, Direction.Descending);
		assertAttributeSort(lastPayerFetched.getName(), response.getFirstResult().getName(),
				Direction.Descending);
	}

	/**
	 * Test successful insert of a payer.
	 */
	@Test
	public void testInsertPayer()
	{
		Payer payer = insertPayer();
		comparePayerAgainstDatabaseById(payer, PersistanceActionEnum.INSERT);
	}

	/**
	 * Test successful update of a payer.
	 */
	@Test
	public void testUpdatePayer()
	{
		Payer payer = insertPayer();
		payer.setName("New Name");
		payer.setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response = getPayerDAC().updatePayer(payer);
		CommonTestRoutines.assertResponse(response);
		comparePayerAgainstDatabaseById(payer, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test successful update of a payer, deleting a payment type.
	 */
	@Test
	public void testUpdatePayerDeletePaymentType()
	{
		Payer payer = insertPayer();
		payer.getPaymentTypeList().get(0).setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response = getPayerDAC().updatePayer(payer);
		CommonTestRoutines.assertResponse(response);
		comparePaymentTypeAgainstDatabaseById(payer, payer.getPaymentTypeList().get(0), PersistanceActionEnum.DELETE);
	}

	/**
	 * Test successful update of a payer, deleting a daily currency rate.
	 */
	@Test
	public void testUpdatePayerDeleteDailyCurrencyRate()
	{
		Payer payer = insertPayer();
		payer.getDailyCurrencyRateList().get(0).setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response = getPayerDAC().updatePayer(payer);
		CommonTestRoutines.assertResponse(response);
		compareDailyCurrencyRateAgainstDatabaseById(payer, payer.getDailyCurrencyRateList().get(0),
				PersistanceActionEnum.DELETE);
	}

	/**
	 * Test successful update of a payer, updating a daily currency rate.
	 */
	@Test
	public void testUpdatePayerUpdateDailyCurrencyRate()
	{
		Payer payer = insertPayer();
		payer.getDailyCurrencyRateList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response = getPayerDAC().updatePayer(payer);
		CommonTestRoutines.assertResponse(response);
		compareDailyCurrencyRateAgainstDatabaseById(payer, payer.getDailyCurrencyRateList().get(0),
				PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test successful update of a payer, deleting a currency availability.
	 */
	@Test
	public void testUpdatePayerDeleteCurrencyAvailability()
	{
		Payer payer = insertPayer();
		payer.getCurrencyAvailabilityList().get(0).setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response = getPayerDAC().updatePayer(payer);
		CommonTestRoutines.assertResponse(response);
		compareCurrencyAvailabilityAgainstDatabaseById(payer, payer.getCurrencyAvailabilityList().get(0),
				PersistanceActionEnum.DELETE);
	}

	/**
	 * Test successful update of a payer, updating a daily currency rate.
	 */
	@Test
	public void testUpdatePayerUpdateCurrencyAvailability()
	{
		Payer payer = insertPayer();
		payer.getCurrencyAvailabilityList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response = getPayerDAC().updatePayer(payer);
		CommonTestRoutines.assertResponse(response);
		compareCurrencyAvailabilityAgainstDatabaseById(payer, payer.getCurrencyAvailabilityList().get(0),
				PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test successful update of a payer, deleting a state province region.
	 */
	@Test
	public void testUpdatePayerDeleteStateProvinceRegion()
	{
		Payer payer = insertPayer();
		payer.getStateProvinceRegionList().get(0).setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response = getPayerDAC().updatePayer(payer);
		CommonTestRoutines.assertResponse(response);
		compareStateProvinceRegionAgainstDatabaseById(payer, payer.getStateProvinceRegionList().get(0),
				PersistanceActionEnum.DELETE);
	}
}
