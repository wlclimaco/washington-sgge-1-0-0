package com.prosperitasglobal.sendsolv.unittest.dac.mybatis;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.CurrencyPurchase;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class CurrencyPurchaseDACTest. This test covers currency purchase.
 */
public class CurrencyPurchaseDACTest extends AbstractTestBaseDAC
{
	private static final String CURRENCY_PURCHASE_NOT_NULL = "Currency Purchase should not be null";

	private static final BigDecimal CURRENY_PURCHASE_EXCHANGE_RATE = new BigDecimal("11.00");

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(OrganizationDACTest.class);

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
	 * This method compares fields between two CurrencyPurchase objects.
	 *
	 * @param currencyPurchase The currencyPurchase inserted/updated.
	 * @param dbCurrencyPurchase The DB currencyPurchase fetched.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsCurrencyPurchase(CurrencyPurchase currencyPurchase, CurrencyPurchase dbCurrencyPurchase,
			PersistanceActionEnum modelAction)
	{
		assertFieldsQATModel(currencyPurchase, dbCurrencyPurchase, modelAction);

		if (modelAction == PersistanceActionEnum.INSERT)
		{
			Assert.assertNotNull("DB CurrencyPurchase id must not be null!", dbCurrencyPurchase.getId());
		}
		else if ((modelAction == PersistanceActionEnum.UPDATE) || (modelAction == PersistanceActionEnum.NONE))
		{
			Assert.assertEquals("CurrencyPurchae id mismatch !", currencyPurchase.getId(), dbCurrencyPurchase.getId());
		}

		Assert.assertEquals("CurrencyPurchase payerId mismatch!", currencyPurchase.getPayerId(),
				dbCurrencyPurchase.getPayerId());
		Assert.assertEquals("CurrencyPurchase amountPurchased mismatch!", currencyPurchase.getAmountPurchased()
				.longValueExact(),
				dbCurrencyPurchase.getAmountPurchased().longValueExact());
		Assert.assertEquals("CurrencyPurchase currency mismatch !", currencyPurchase.getCurrency().getCode(),
				dbCurrencyPurchase.getCurrency().getCode());
		Assert.assertEquals("CurrencyPurchase purchaseDate mismatch !", currencyPurchase.getPurchaseDate(),
				dbCurrencyPurchase.getPurchaseDate());
		Assert.assertEquals("CurrencyPurchase foreignExchangeRate mismatch !",
				currencyPurchase.getForeignExchangeRate().longValueExact(), dbCurrencyPurchase.getForeignExchangeRate()
						.longValueExact());

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
	 * Test currencyPurchase against database by id.
	 *
	 * @param cureencyPurchase The currencyPurchase
	 * @param modelAction The model action performed.
	 */
	private void compareCurrencyPurchaseAgainstDatabaseById(CurrencyPurchase currencyPurchase,
			PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<CurrencyPurchase> response =
				getCurrencyPurchaseDAC().fetchCurrencyPurchaseById(currencyPurchase.getId());
		assertFetch(response, modelAction);
		if (modelAction != PersistanceActionEnum.DELETE)
		{
			assertFieldsCurrencyPurchase(currencyPurchase, response.getFirstResult(), modelAction);
		}
	}

	/** Sets up the database. */
	@Before
	public void setUp()
	{
		executeSqlScript("com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/deleteBusiness.sql", false);
	}

	/**
	 * Test successful insert of a currency purchase.
	 */
	@Test
	public void testInsertCurrencyPurchase()
	{
		CurrencyPurchase currencyPurchase = insertCurrencyPurchase();
		compareCurrencyPurchaseAgainstDatabaseById(currencyPurchase, PersistanceActionEnum.INSERT);
	}

	/**
	 * Test successful delete of a currency purchase.
	 */
	@Test
	public void testDeleteCurrencyPurchase()
	{
		CurrencyPurchase currencyPurchase = insertCurrencyPurchase();
		currencyPurchase.setModelAction(PersistanceActionEnum.DELETE);
		getCurrencyPurchaseDAC().deleteCurrencyPurchase(currencyPurchase);
		compareCurrencyPurchaseAgainstDatabaseById(currencyPurchase, PersistanceActionEnum.DELETE);
	}

	/**
	 * Test fetch currency purchase by id.
	 */
	@Test
	public void testfetchCurrencyPurchaseById()
	{
		CurrencyPurchase currencyPurchase = insertCurrencyPurchase();

		LOG.info("\nStarting testFetchCurrencyPurchaseById");
		InternalResultsResponse<CurrencyPurchase> resp =
				getCurrencyPurchaseDAC().fetchCurrencyPurchaseById(currencyPurchase.getId());

		CurrencyPurchase dbCurrencyPurchase = resp.getFirstResult();

		Assert.assertNotNull(CURRENCY_PURCHASE_NOT_NULL, dbCurrencyPurchase);
		LOG.info("CurrencyPurchase is: " + dbCurrencyPurchase.toString());

	}

	/**
	 * Test successful update of a currency purchase.
	 */
	@Test
	public void testUpdateCurrencyPurchase()
	{
		CurrencyPurchase currencyPurchase = insertCurrencyPurchase();
		currencyPurchase.setForeignExchangeRate(CURRENY_PURCHASE_EXCHANGE_RATE);
		currencyPurchase.setAmountNotAllocated(new BigDecimal("20.00"));
		InternalResponse response = getCurrencyPurchaseDAC().updateCurrencyPurchase(currencyPurchase);
		CommonTestRoutines.assertResponse(response);
		compareCurrencyPurchaseAgainstDatabaseById(currencyPurchase, PersistanceActionEnum.UPDATE);
	}

}
