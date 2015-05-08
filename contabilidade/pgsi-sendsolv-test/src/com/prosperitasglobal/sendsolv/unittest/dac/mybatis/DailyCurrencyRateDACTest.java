package com.prosperitasglobal.sendsolv.unittest.dac.mybatis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.DailyCurrencyRate;
import com.prosperitasglobal.sendsolv.model.DailyCurrencyRateDetail;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum;
import com.prosperitasglobal.sendsolv.model.criteria.DailyCurrencyRateCriteria;
import com.prosperitasglobal.sendsolv.model.criteria.MoneyTransferCriteria;
import com.prosperitasglobal.sendsolv.model.request.DailyCurrencyRateInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MoneyTransferDACTest. This test covers money transfers.
 */
public class DailyCurrencyRateDACTest extends AbstractTestBaseDAC
{
	private static final Integer BIG_PAGE_SIZE = 100;
	/** The log. */
	protected static final Logger LOG = LoggerFactory.getLogger(DailyCurrencyRateDACTest.class);
	private static final String DATE_SORT_FIELD = "date";
	private static final int NUM_OF_DAILY_CURRENCY_RATES_TO_INSERT = 6;
	private static final String NUMBER_OF_ROWS_NEED_TO_MATCH = "Number of rows need to match";
	private static final Integer PAGE_SIZE = 2;

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
			Assert.assertTrue("response.getResultsList().size() needs to be > 0", response.getResultsList().size() > 0);
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
	 * Compares the fetch result for a DailyCurrencyRate against the criteria member.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param dailyCurrencyRateList The list of DailyCurrencyRate returned from the fetch.
	 */
	private void assertFilterDailyCurrencyRate(DailyCurrencyRateCriteria criteria,
			List<DailyCurrencyRate> dailyCurrencyRateList)
	{
		Assert.assertTrue("DailyCurrencyRateCriteria Fetch contains 0!", dailyCurrencyRateList.size() > 0);

		for (DailyCurrencyRate dailyCurrencyRate : dailyCurrencyRateList)
		{
			if (!ValidationUtil.isNull(criteria.getCurrency()))
			{
				Assert.assertEquals("DailyCurrencyRate currency criteria mismatch!", criteria.getCurrency().getCode(),
						dailyCurrencyRate.getCurrency().getCode());
			}

			if (!ValidationUtil.isNull(criteria.getPayerId()))
			{
				Assert.assertEquals("DailyCurrencyRate payer criteria mismatch!", criteria.getPayerId(),
						dailyCurrencyRate.getPayerId());
			}
		}
	}

	/**
	 * Asserts that the list of DailyCurrencyRate objects is sorted correctly.
	 *
	 * @param dailyCurrencyRateList The sorted list of DailyCurrencyRate objects.
	 * @param fieldName The field name that was sorted.
	 * @param direction The direction of the sort.
	 */
	private void assertSortDailyCurrencyRate(List<DailyCurrencyRate> dailyCurrencyRateList, String fieldName,
			Direction direction)
	{
		boolean firstTime = true;
		DailyCurrencyRate previousDailyCurrencyRate = null;

		for (DailyCurrencyRate dailyCurrencyRate : dailyCurrencyRateList)
		{
			if (firstTime)
			{
				firstTime = false;
			}
			else
			{
				if (fieldName.equals(DATE_SORT_FIELD))
				{
					assertAttributeSort(previousDailyCurrencyRate.getValidForDate().toString(), dailyCurrencyRate
							.getValidForDate().toString(), direction);
				}
				else
				{
					Assert.assertTrue("Unknown DailyCurrencyRate sort field: " + fieldName, false);
				}
			}

			previousDailyCurrencyRate = dailyCurrencyRate;
		}
	}

	/**
	 * Test DailyCurrencyRate against database by id.
	 *
	 * @param dailyCurrencyRate The DailyCurrencyRate
	 * @param modelAction The model action performed.
	 */
	private void compareDailyCurrencyRateAgainstDatabaseById(DailyCurrencyRate dailyCurrencyRate,
			PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<DailyCurrencyRate> response =
				getDailyCurrencyRateDAC().fetchDailyCurrencyRateById(dailyCurrencyRate.getId());
		assertFetch(response, modelAction);
		if (modelAction != PersistanceActionEnum.DELETE)
		{
			assertFieldsDailyCurrencyRate(dailyCurrencyRate, response.getFirstResult(), modelAction);
		}
	}

	/**
	 * Test DailyCurrencyRateDetail database by id.
	 *
	 * @param dailyCurrencyRate The DailyCurrencyRate.
	 * @param moneyTransfer The DailyCurrencyRateDetail.
	 * @param dailyCurrencyRateDetail The model action performed.
	 */
	private void compareDailyCurrencyRateDetailAgainstDatabaseById(DailyCurrencyRate dailyCurrencyRate,
			DailyCurrencyRateDetail dailyCurrencyRateDetail, PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<DailyCurrencyRate> response =
				getDailyCurrencyRateDAC().fetchDailyCurrencyRateById(dailyCurrencyRate.getId());
		CommonTestRoutines.assertResultResponse(response);
		assertFieldsDailyCurrencyRateDetail(response.getFirstResult().getDailyCurrencyRateDetailList(),
				dailyCurrencyRateDetail, modelAction);
	}

	/**
	 * Insert DailyCurrencyRates.
	 *
	 * @param numberOfDailyCurrencyRatesToInsert The number of DailyCurrencyRates to insert
	 * @return The {@link List} of {@link DailyCurrencyRate}.
	 */
	private List<DailyCurrencyRate> insertDailyCurrencyRates(Integer numberOfDailyCurrencyRatesToInsert)
	{
		ArrayList<DailyCurrencyRate> dailyCurrencyRateList = new ArrayList<DailyCurrencyRate>();
		DailyCurrencyRate dailyCurrencyRate = null;
		for (int i = 0; i < numberOfDailyCurrencyRatesToInsert; i++)
		{
			dailyCurrencyRate = insertDailyCurrencyRate();

			if (!ValidationUtil.isNull(dailyCurrencyRate))
			{
				dailyCurrencyRateList.add(dailyCurrencyRate);
			}
		}

		return dailyCurrencyRateList;
	}

	/** Sets up the database. */
	@Before
	public void setUp()
	{
		executeSqlScript("com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/deleteBusiness.sql", false);
	}

	/** Test successful delete of a daily currency rate. */
	@Test
	public void testDeleteDailyCurrencyRate()
	{
		DailyCurrencyRate dailyCurrencyRate = insertDailyCurrencyRate();
		dailyCurrencyRate.setModelAction(PersistanceActionEnum.DELETE);
		getDailyCurrencyRateDAC().deleteDailyCurrencyRate(dailyCurrencyRate);
		compareDailyCurrencyRateAgainstDatabaseById(dailyCurrencyRate, PersistanceActionEnum.DELETE);
	}

	/** Test successful fetch of a daily currency rate by id. */
	@Test
	public void testfetchDailyCurrencyRateById()
	{
		List<DailyCurrencyRate> dailyCurrencyRateList = insertDailyCurrencyRates(2);
		InternalResultsResponse<DailyCurrencyRate> response =
				getDailyCurrencyRateDAC().fetchDailyCurrencyRateById(dailyCurrencyRateList.get(0).getId());
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue("DailyCurrencyRate fetch by id must return exactly 1 object", response.getResultsList()
				.size() == 1);
		compareDailyCurrencyRateAgainstDatabaseById(response.getFirstResult(), PersistanceActionEnum.FETCH);
	}

	/** Test successful fetch of daily currency rate by request, no criteria specified. */
	@Test
	public void testFetchDailyCurrencyRateByRequest()
	{
		insertDailyCurrencyRates(NUM_OF_DAILY_CURRENCY_RATES_TO_INSERT);
		DailyCurrencyRateInquiryRequest request = new DailyCurrencyRateInquiryRequest();
		request.setPageSize(BIG_PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		DailyCurrencyRateCriteria criteria = new DailyCurrencyRateCriteria();
		request.setCriteria(criteria);
		InternalResultsResponse<DailyCurrencyRate> response =
				getDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterDailyCurrencyRate(criteria, response.getResultsList());
	}

	/** Test successful fetch of daily currency rate by request, currency specified in the criteria specified. */
	@Test
	public void testFetchDailyCurrencyRateByRequestCurrency()
	{
		List<DailyCurrencyRate> dailyCurrencyRateList = insertDailyCurrencyRates(NUM_OF_DAILY_CURRENCY_RATES_TO_INSERT);
		DailyCurrencyRateInquiryRequest request = new DailyCurrencyRateInquiryRequest();
		request.setPageSize(BIG_PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		DailyCurrencyRateCriteria criteria = new DailyCurrencyRateCriteria();
		criteria.setCurrency(dailyCurrencyRateList.get(0).getCurrency());
		request.setCriteria(criteria);
		InternalResultsResponse<DailyCurrencyRate> response =
				getDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterDailyCurrencyRate(criteria, response.getResultsList());
	}

	/** Test successful fetch of daily currency rate by request, payer id specified in the criteria specified. */
	@Test
	public void testFetchDailyCurrencyRateByRequestPayerId()
	{
		List<DailyCurrencyRate> dailyCurrencyRateList = insertDailyCurrencyRates(NUM_OF_DAILY_CURRENCY_RATES_TO_INSERT);
		DailyCurrencyRateInquiryRequest request = new DailyCurrencyRateInquiryRequest();
		request.setPageSize(BIG_PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		DailyCurrencyRateCriteria criteria = new DailyCurrencyRateCriteria();
		criteria.setPayerId(dailyCurrencyRateList.get(0).getPayerId());
		request.setCriteria(criteria);
		InternalResultsResponse<DailyCurrencyRate> response =
				getDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterDailyCurrencyRate(criteria, response.getResultsList());
	}

	/** Test successful fetch of daily currency rate by request, sorted by default order. */
	@Test
	public void testFetchDailyCurrencyRateByRequestSortedByDefault()
	{
		insertDailyCurrencyRates(NUM_OF_DAILY_CURRENCY_RATES_TO_INSERT);
		DailyCurrencyRateInquiryRequest request = new DailyCurrencyRateInquiryRequest();
		request.setPageSize(BIG_PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		InternalResultsResponse<DailyCurrencyRate> response =
				getDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, BIG_PAGE_SIZE >= response.getResultsList().size());
		assertSortDailyCurrencyRate(response.getResultsList(), DATE_SORT_FIELD, Direction.Descending);
	}

	/**
	 * Test successful fetch of daily currency rate by request, sorted by default order, with paging.
	 */
	@Test
	public void testFetchMoneyTransferBatchByRequestSortedByLocationNameAscPaging()
	{
		insertDailyCurrencyRates(NUM_OF_DAILY_CURRENCY_RATES_TO_INSERT);
		DailyCurrencyRateInquiryRequest request = new DailyCurrencyRateInquiryRequest();
		request.setPageSize(PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		InternalResultsResponse<DailyCurrencyRate> response =
				getDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortDailyCurrencyRate(response.getResultsList(), DATE_SORT_FIELD, Direction.Descending);
		DailyCurrencyRate lastDailyCurrencyRateFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// go to next page
		request.setStartPage(1);
		response = getDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertAttributeSort(lastDailyCurrencyRateFetched.getValidForDate().toString(), response.getFirstResult()
				.getValidForDate().toString(), Direction.Descending);
		assertSortDailyCurrencyRate(response.getResultsList(), DATE_SORT_FIELD, Direction.Descending);
		lastDailyCurrencyRateFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// one more page
		request.setStartPage(2);
		response = getDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortDailyCurrencyRate(response.getResultsList(), DATE_SORT_FIELD, Direction.Descending);
		assertAttributeSort(lastDailyCurrencyRateFetched.getValidForDate().toString(), response.getFirstResult()
				.getValidForDate().toString(), Direction.Ascending);
	}

	/** Test successful fetch of money transfer by request, status specified in the criteria but not found. */
	@Test
	public void testFetchMoneyTransferByRequestStatusNotFound()
	{
		insertMoneyTransferBatch(NUM_OF_DAILY_CURRENCY_RATES_TO_INSERT);
		MoneyTransferInquiryRequest request = new MoneyTransferInquiryRequest();
		request.setPageSize(BIG_PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		MoneyTransferCriteria criteria = new MoneyTransferCriteria();
		List<MoneyTransferStatusEnum> statusList = new ArrayList<MoneyTransferStatusEnum>();
		statusList.add(MoneyTransferStatusEnum.CANCELLED);
		criteria.setStatusList(statusList);
		request.setCriteria(criteria);
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		Assert.assertEquals("MoneyTransfer Response status should be NoRowsFoundError", Status.NoRowsFoundError,
				response.getStatus());
		Assert.assertTrue("MoneyTransfer Response result count should be zero", response.getResultsList().size() == 0);
	}

	/** Test successful insert of a daily currency rate. */
	@Test
	public void testInsertDailyCurrencyRate()
	{
		DailyCurrencyRate dailyCurrencyRate = insertDailyCurrencyRate();
		compareDailyCurrencyRateAgainstDatabaseById(dailyCurrencyRate, PersistanceActionEnum.INSERT);
	}

	/** Test successful update of a daily currency rate. */
	@Test
	public void testUpdateDailyCurrencyRate()
	{
		DailyCurrencyRate dailyCurrencyRate = insertDailyCurrencyRate();
		dailyCurrencyRate.setModelAction(PersistanceActionEnum.UPDATE);
		dailyCurrencyRate.setModifyDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		dailyCurrencyRate.setModifyUser("NewUser1");
		InternalResponse response = getDailyCurrencyRateDAC().updateDailyCurrencyRate(dailyCurrencyRate);
		CommonTestRoutines.assertResponse(response);
		compareDailyCurrencyRateAgainstDatabaseById(dailyCurrencyRate, PersistanceActionEnum.UPDATE);
	}

	/** Test successful update of a daily currency rate, deleting a daily currency rate detail. */
	@Test
	public void testUpdateDailyCurrencyRateDeleteDailyCurrencyRateDetail()
	{
		DailyCurrencyRate dailyCurrencyRate = insertDailyCurrencyRate();
		dailyCurrencyRate.setModelAction(PersistanceActionEnum.UPDATE);
		dailyCurrencyRate.setModifyDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		dailyCurrencyRate.setModifyUser("NewUser11");
		dailyCurrencyRate.getDailyCurrencyRateDetailList().get(0).setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response = getDailyCurrencyRateDAC().updateDailyCurrencyRate(dailyCurrencyRate);
		CommonTestRoutines.assertResponse(response);
		compareDailyCurrencyRateDetailAgainstDatabaseById(dailyCurrencyRate, dailyCurrencyRate
				.getDailyCurrencyRateDetailList().get(0),
				PersistanceActionEnum.DELETE);
	}

	/** Test successful update of a daily currency rate, updating the daily currency rate detail. */
	@Test
	public void testUpdateDailyCurrencyRateUpdateDailyCurrencyRateDetail()
	{
		DailyCurrencyRate dailyCurrencyRate = insertDailyCurrencyRate();
		dailyCurrencyRate.setModelAction(PersistanceActionEnum.UPDATE);
		dailyCurrencyRate.setModifyDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		dailyCurrencyRate.setModifyUser("NewUser12");
		dailyCurrencyRate.getDailyCurrencyRateDetailList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		dailyCurrencyRate.getDailyCurrencyRateDetailList().get(0).setExchangeRate(new BigDecimal("1.70"));
		InternalResponse response = getDailyCurrencyRateDAC().updateDailyCurrencyRate(dailyCurrencyRate);
		CommonTestRoutines.assertResponse(response);
		compareDailyCurrencyRateDetailAgainstDatabaseById(dailyCurrencyRate, dailyCurrencyRate
				.getDailyCurrencyRateDetailList().get(0),
				PersistanceActionEnum.UPDATE);
	}
}
