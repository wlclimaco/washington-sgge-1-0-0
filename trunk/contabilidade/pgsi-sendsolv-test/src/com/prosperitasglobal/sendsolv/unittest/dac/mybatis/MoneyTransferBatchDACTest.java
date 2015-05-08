package com.prosperitasglobal.sendsolv.unittest.dac.mybatis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.Currency;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.cbof.model.StateProvinceRegion;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferAmount;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatusEnum;
import com.prosperitasglobal.sendsolv.model.PaymentTypeEnum;
import com.prosperitasglobal.sendsolv.model.criteria.MoneyTransferBatchCriteria;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MoneyTransferBatchDACTest. This test covers money transfers.
 */
public class MoneyTransferBatchDACTest extends AbstractTestBaseDAC
{
	/** The big page size for fetching. */
	private static final Integer BIG_PAGE_SIZE = 100;
	/** The sort name for a location. */
	private static final String LOCATION_NAME_SORT_FIELD = "locationName";
	/** The number of money transfer batches to insert. */
	private static final int MONEY_TRANSFER_BATCHES_TO_INSERT = 3;
	/** The number of money transfer batches to insert for paging test. */
	private static final int MONEY_TRANSFER_BATCHES_TO_INSERT_PAGING = 6;
	/** The number of rows need to match reason. */
	private static final String NUMBER_OF_ROWS_NEED_TO_MATCH = "Number of rows need to match";
	/** The small page size for fetching. */
	private static final Integer PAGE_SIZE = 2;
	/** The sort name for the status. */
	private static final String STATUS_SORT_FIELD = "status";
	/** The sort name for the transfer date. */
	private static final String TRANSFER_DATE_SORT_FIELD = "transferDate";

	/**
	 * Compare the results of the fetch.
	 *
	 * @param response The response from the fetch.
	 * @param action The model action that was performed before the fetch.
	 */
	private void assertFetch(InternalResultsResponse<?> response, PersistanceActionEnum action)
	{
		if ((action == PersistanceActionEnum.INSERT) || (action == PersistanceActionEnum.UPDATE)
				|| (action == PersistanceActionEnum.NONE))
		{
			CommonTestRoutines.assertResultResponse(response);
			Assert.assertTrue("response.getResultsList().size() needs to be > 0", response.getResultsList().size() > 0);
			Assert.assertNotNull("response.getFirstResult() cannot be null", response.getFirstResult());
		}
		else if (action == PersistanceActionEnum.DELETE)
		{
			Assert.assertTrue("response.getResultsList().size() needs to be 0", response.getResultsList().size() == 0);
			Assert.assertNull("response.getFirstResult() must be null", response.getFirstResult());
		}
	}

	/**
	 * Test MoneyTransfer against database by id.
	 *
	 * @param dbMoneyTransferList The list of MoneyTransfer from the db.
	 * @param moneyTransfer The MoneyTransfer inserted/updated.
	 * @param action The model action performed.
	 */
	private void assertEquals(List<MoneyTransfer> dbMoneyTransferList, MoneyTransfer moneyTransfer,
			PersistanceActionEnum action)
	{
		boolean moneyTransferFound = false;
		for (MoneyTransfer dbMoneyTransfer : dbMoneyTransferList)
		{
			if (dbMoneyTransfer.getId().equals(moneyTransfer.getId()))
			{
				moneyTransferFound = true;
				if ((action == PersistanceActionEnum.INSERT) || (action == PersistanceActionEnum.UPDATE)
						|| (action == PersistanceActionEnum.NONE))
				{
					break;
				}
				else
				{
					Assert.assertTrue("DB MoneyTransfer still exists after DELETE", false);
				}
			}
		}
		if (!moneyTransferFound
				&& ((action == PersistanceActionEnum.INSERT) || (action == PersistanceActionEnum.UPDATE) ||
				(action == PersistanceActionEnum.NONE)))
		{
			Assert.assertTrue("DB MoneyTransfer does not exists after INSERT/UPDATE/NONE", false);
		}
	}

	/**
	 * This method compares fields between two MoneyTransferAmount objects.
	 *
	 * @param moneyTransferAmount The moneyTransferAmount inserted/updated.
	 * @param dbMoneyTransferAmount The DB moneyTransferAmount fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(MoneyTransferAmount moneyTransferAmount, MoneyTransferAmount dbMoneyTransferAmount,
			PersistanceActionEnum action)
	{
		Assert.assertTrue("MoneyTransferAmount amount",
				moneyTransferAmount.getAmount().compareTo(dbMoneyTransferAmount.getAmount()) == 0);
		Assert.assertEquals("MoneyTransferAmount currency", moneyTransferAmount.getCurrency().getCode(),
				dbMoneyTransferAmount.getCurrency().getCode());
		Assert.assertEquals("MoneyTransferAmount country", moneyTransferAmount.getCountry().getCode(),
				dbMoneyTransferAmount.getCountry().getCode());
	}

	/**
	 * This method compares fields between two MoneyTransferBatch objects.
	 *
	 * @param moneyTransferBatch The money transfer batch inserted/updated.
	 * @param dbMoneyTransferBatch The DB money transfer batch fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(MoneyTransferBatch moneyTransferBatch, MoneyTransferBatch dbMoneyTransferBatch,
			PersistanceActionEnum action)
	{
		CommonTestRoutines.assertFieldsQATModelOL(moneyTransferBatch, dbMoneyTransferBatch, action);

		if (action == PersistanceActionEnum.INSERT)
		{
			Assert.assertNotNull("DB MoneyTransferBatch id must not be null", dbMoneyTransferBatch.getId());
		}
		else if ((action == PersistanceActionEnum.UPDATE) || (action == PersistanceActionEnum.NONE))
		{
			Assert.assertEquals("MoneyTransferBatch id mismatch ", moneyTransferBatch.getId(),
					dbMoneyTransferBatch.getId());
		}

		Assert.assertNotNull("MoneyTransferBatch key", dbMoneyTransferBatch.getKey());
		Assert.assertEquals("MoneyTransferBatch location", moneyTransferBatch.getLocation().getId(),
				dbMoneyTransferBatch.getLocation().getId());
		Assert.assertEquals("MoneyTransferBatch transferDate", moneyTransferBatch.getTransferDate(),
				dbMoneyTransferBatch.getTransferDate());
		Assert.assertEquals("MoneyTransferBatch payrollReceived", moneyTransferBatch.getPayrollReceivedDate(),
				dbMoneyTransferBatch.getPayrollReceivedDate());
		Assert.assertEquals("MoneyTransferBatch payPreparationDate", moneyTransferBatch.getPayPreparationDate(),
				dbMoneyTransferBatch.getPayPreparationDate());
		Assert.assertEquals("MoneyTransferBatch releaseUser", moneyTransferBatch.getReleaseUser(),
				dbMoneyTransferBatch.getReleaseUser());
		assertEquals(moneyTransferBatch.getOriginAmount(), dbMoneyTransferBatch.getOriginAmount(), action);

		if (!ValidationUtil.isNullOrEmpty(moneyTransferBatch.getMoneyTransferList()))
		{
			for (MoneyTransfer moneyTransfer : moneyTransferBatch.getMoneyTransferList())
			{
				if (action == PersistanceActionEnum.INSERT)
				{
					assertEquals(dbMoneyTransferBatch.getMoneyTransferList(), moneyTransfer, action);
				}
				else
				{
					assertEquals(dbMoneyTransferBatch.getMoneyTransferList(), moneyTransfer,
							PersistanceActionEnum.NONE);
				}
			}
		}

		if (!ValidationUtil.isNullOrEmpty(moneyTransferBatch.getStatusList()))
		{
			for (MoneyTransferBatchStatus moneyTransferBatchStatus : moneyTransferBatch.getStatusList())
			{
				if (action == PersistanceActionEnum.INSERT)
				{
					assertEquals(dbMoneyTransferBatch.getStatusList(), moneyTransferBatchStatus, action);
				}
				else
				{
					assertEquals(dbMoneyTransferBatch.getStatusList(), moneyTransferBatchStatus,
							PersistanceActionEnum.NONE);
				}
			}
		}

		if (!ValidationUtil.isNullOrEmpty(moneyTransferBatch.getNoteList()))
		{
			for (Note note : moneyTransferBatch.getNoteList())
			{
				if (action == PersistanceActionEnum.INSERT)
				{
					assertEquals(dbMoneyTransferBatch.getNoteList(), note, action);
				}
				else
				{
					assertEquals(dbMoneyTransferBatch.getNoteList(), note, PersistanceActionEnum.NONE);
				}
			}
		}
	}

	/**
	 * Test MoneyTransferBatchStatus against database by id.
	 *
	 * @param dbMoneyTransferBatchStatusList The list of MoneyTransferBatchStatus from the db.
	 * @param moneyTransferBatchStatus The MoneyTransferBatchStatus inserted/updated.
	 * @param action The model action performed.
	 */
	private void assertEquals(List<MoneyTransferBatchStatus> dbMoneyTransferBatchStatusList,
			MoneyTransferBatchStatus moneyTransferBatchStatus, PersistanceActionEnum action)
	{
		boolean moneyTransferBatchStatusFound = false;
		for (MoneyTransferBatchStatus dbMoneyTransferBatchStatus : dbMoneyTransferBatchStatusList)
		{
			if (dbMoneyTransferBatchStatus.getId().equals(moneyTransferBatchStatus.getId()))
			{
				moneyTransferBatchStatusFound = true;
				if ((action == PersistanceActionEnum.INSERT) || (action == PersistanceActionEnum.UPDATE)
						|| (action == PersistanceActionEnum.NONE))
				{
					assertEquals(moneyTransferBatchStatus, dbMoneyTransferBatchStatus, action);
					break;
				}
				else
				{
					Assert.assertTrue("DB MoneyTransferBatchStatus still exists after DELETE", false);
				}
			}
		}

		if (!moneyTransferBatchStatusFound
				&& ((action == PersistanceActionEnum.INSERT) || (action == PersistanceActionEnum.UPDATE) ||
				(action == PersistanceActionEnum.NONE)))
		{
			Assert.assertTrue("DB MoneyTransferBatchStatus does not exists after INSERT/UPDATE/NONE", false);
		}
	}

	/**
	 * This method compares fields between two MoneyTransferBatchStatus objects.
	 *
	 * @param moneyTransferBatchStatus The MoneyTransferBatchStatus inserted/updated.
	 * @param dbMoneyTransferBatchStatus The DB MoneyTransferBatchStatus fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(MoneyTransferBatchStatus moneyTransferBatchStatus,
			MoneyTransferBatchStatus dbMoneyTransferBatchStatus, PersistanceActionEnum action)
	{
		CommonTestRoutines.assertFieldsQATModel(moneyTransferBatchStatus, dbMoneyTransferBatchStatus, action);
		Assert.assertEquals("MoneyTransferBatchStatus moneyTransferBatchId",
				moneyTransferBatchStatus.getMoneyTransferBatchId(),
				dbMoneyTransferBatchStatus.getMoneyTransferBatchId());
		Assert.assertEquals("MoneyTransferBatchStatus actionDueDate", moneyTransferBatchStatus.getActionDueDate(),
				dbMoneyTransferBatchStatus.getActionDueDate());
		Assert.assertEquals("MoneyTransferBatchStatus status", moneyTransferBatchStatus.getStatusValue(),
				dbMoneyTransferBatchStatus.getStatusValue());
	}

	/**
	 * This method compares 2 different status.
	 *
	 * @param expectedStatus Expected status.
	 * @param actualStatus Actual status.
	 */
	private void assertEquals(Status expectedStatus, Status actualStatus)
	{
		Assert.assertEquals("Expected status " + expectedStatus + "but got status " + actualStatus, expectedStatus,
				actualStatus);
	}

	/**
	 * Test Note against database by id.
	 *
	 * @param dbNoteList The list of Note from the db.
	 * @param note The Note inserted/updated.
	 * @param action The model action performed.
	 */
	private void assertEquals(List<Note> dbNoteList, Note note, PersistanceActionEnum action)
	{
		boolean noteFound = false;
		for (Note dbNote : dbNoteList)
		{
			if (dbNote.getId().equals(note.getId()))
			{
				noteFound = true;
				if ((action == PersistanceActionEnum.INSERT) || (action == PersistanceActionEnum.UPDATE)
						|| (action == PersistanceActionEnum.NONE))
				{
					assertEquals(note, dbNote, action);
					break;
				}
				else
				{
					Assert.assertTrue("DB Note still exists after DELETE", false);
				}
			}
		}

		if (!noteFound && ((action == PersistanceActionEnum.INSERT) || (action == PersistanceActionEnum.UPDATE) ||
				(action == PersistanceActionEnum.NONE)))
		{
			Assert.assertTrue("DB Note does not exists after INSERT/UPDATE/NONE", false);
		}
	}

	/**
	 * This method compares fields between two Note objects.
	 *
	 * @param note The Note inserted/updated.
	 * @param dbNote The DB Note fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(Note note, Note dbNote, PersistanceActionEnum action)
	{
		CommonTestRoutines.assertFieldsQATModel(note, dbNote, action);
		Assert.assertEquals("Note noteText", note.getNoteText(), dbNote.getNoteText());
		Assert.assertEquals("Note parentKey", note.getParentKey(), dbNote.getParentKey());
	}

	/**
	 * Compares the fetch result for a MoneyTransferBatch against the criteria member.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param moneyTransferBatchList The list of MoneyTransferBatch returned from the fetch.
	 */
	private void assertFilterMoneyTransferBatch(MoneyTransferBatchCriteria criteria,
			List<MoneyTransferBatch> moneyTransferBatchList)
	{
		Assert.assertTrue("MoneyTransferBatch Fetch contains 0", moneyTransferBatchList.size() > 0);

		for (MoneyTransferBatch moneyTransferBatch : moneyTransferBatchList)
		{
			if (!ValidationUtil.isNull(criteria.getLocationId()))
			{
				Assert.assertEquals("MoneyTransferBatch locationId", criteria.getLocationId(), moneyTransferBatch
						.getLocation().getId());
			}

			if (!ValidationUtil.isNullOrEmpty(criteria.getStatusList()))
			{
				boolean statusMatch = false;
				for (MoneyTransferBatchStatusEnum status : criteria.getStatusList())
				{
					if (moneyTransferBatch.getCurrentStatus().getStatus() == status)
					{
						statusMatch = true;
						break;
					}
				}

				Assert.assertTrue("MoneyTransferBatch status", statusMatch);
			}

			if (!ValidationUtil.isNull(criteria.getTransferDate()))
			{
				Assert.assertEquals("MoneyTransferBatch transferDate criteria", criteria.getTransferDate(),
						moneyTransferBatch.getTransferDate());
			}
		}
	}

	/**
	 * Asserts that the list of MoneyTransferBatch objects is sorted correctly.
	 *
	 * @param moneyTransferBatchList The sorted list of MoneyTransferBatch objects.
	 * @param fieldName The field name that was sorted.
	 * @param direction The direction of the sort.
	 */
	private void assertSortMoneyTransferBatch(List<MoneyTransferBatch> moneyTransferBatchList, String fieldName,
			Direction direction)
	{
		boolean firstTime = true;
		MoneyTransferBatch previousMoneyTransferBatch = null;

		for (MoneyTransferBatch moneyTransferBatch : moneyTransferBatchList)
		{
			if (!firstTime)
			{
				switch (fieldName)
				{
					case TRANSFER_DATE_SORT_FIELD:
						CommonTestRoutines.assertAttributeSort(previousMoneyTransferBatch.getTransferDate().toString(),
								moneyTransferBatch.getTransferDate().toString(), direction);
						break;
					case LOCATION_NAME_SORT_FIELD:
						CommonTestRoutines.assertAttributeSort(previousMoneyTransferBatch.getLocation().getName(),
								moneyTransferBatch.getLocation().getName(), direction);
						break;
					case STATUS_SORT_FIELD:
						CommonTestRoutines.assertAttributeSort(previousMoneyTransferBatch.getCurrentStatus()
								.getStatusValue().toString(), moneyTransferBatch.getCurrentStatus().getStatusValue()
								.toString(), direction);
						break;
					default:
						Assert.assertTrue("Unknown MoneyTransferBatch sort field: " + fieldName, false);
						break;
				}
			}
			firstTime = false;
			previousMoneyTransferBatch = moneyTransferBatch;
		}
	}

	/**
	 * Test MoneyTransfer database by id.
	 *
	 * @param moneyTransferBatch The MoneyTransferBatch.
	 * @param moneyTransfer The MoneyTransfer.
	 * @param action The model action performed.
	 */
	private void compare(MoneyTransferBatch moneyTransferBatch,
			MoneyTransfer moneyTransfer, PersistanceActionEnum action)
	{
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchById(moneyTransferBatch.getId());
		CommonTestRoutines.assertResultResponse(response);
		assertEquals(response.getFirstResult().getMoneyTransferList(), moneyTransfer, action);
	}

	/**
	 * Test MoneyTransferBatch against database by id.
	 *
	 * @param moneyTransferBatch The MoneyTransferBatch
	 * @param action The model action performed.
	 */
	private void compare(MoneyTransferBatch moneyTransferBatch,
			PersistanceActionEnum action)
	{
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchById(moneyTransferBatch.getId());
		assertFetch(response, action);
		if (action != PersistanceActionEnum.DELETE)
		{
			assertEquals(moneyTransferBatch, response.getFirstResult(), action);
		}
	}

	/**
	 * Test Note database by id.
	 *
	 * @param moneyTransferBatch The MoneyTransferBatch.
	 * @param note The Note.
	 * @param action The model action performed.
	 */
	private void compare(MoneyTransferBatch moneyTransferBatch, Note note,
			PersistanceActionEnum action)
	{
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchById(moneyTransferBatch.getId());
		CommonTestRoutines.assertResultResponse(response);
		assertEquals(response.getFirstResult().getNoteList(), note, action);
	}

	/**
	 * Test MoneyTransferBatchStatus database by id.
	 *
	 * @param moneyTransferBatch The MoneyTransferBatch.
	 * @param moneyTransferBatchStatus The MoneyTransferBatchStatus.
	 * @param action The model action performed.
	 */
	private void compare(MoneyTransferBatch moneyTransferBatch,
			MoneyTransferBatchStatus moneyTransferBatchStatus, PersistanceActionEnum action)
	{
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchById(moneyTransferBatch.getId());
		CommonTestRoutines.assertResultResponse(response);
		assertEquals(response.getFirstResult().getStatusList(), moneyTransferBatchStatus, action);
	}

	/**
	 * Insert MoneyTransferBatches.
	 *
	 * @param numberOfMoneyTransferBatchesToInsert The number of MoneyTransferBatches to insert
	 * @return The {@link List} of {@link MoneyTransferBatch}.
	 */
	private List<MoneyTransferBatch> insertMoneyTransferBatches(Integer numberOfMoneyTransferBatchesToInsert)
	{
		ArrayList<MoneyTransferBatch> moneyTransferBatchList = new ArrayList<MoneyTransferBatch>();
		MoneyTransferBatch moneyTransferBatch = null;
		for (int i = 0; i < numberOfMoneyTransferBatchesToInsert; i++)
		{
			moneyTransferBatch = insertMoneyTransferBatch(i + 1);
			if (!ValidationUtil.isNull(moneyTransferBatch))
			{
				moneyTransferBatchList.add(moneyTransferBatch);
			}
		}
		return moneyTransferBatchList;
	}

	/**
	 * Convenience method for returning a default inquiry request.
	 *
	 * @return The request.
	 */
	private MoneyTransferBatchInquiryRequest obtainDefaultMoneyTransferBatchInquiryRequest()
	{
		MoneyTransferBatchInquiryRequest request = new MoneyTransferBatchInquiryRequest();
		request.setPageSize(BIG_PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		MoneyTransferBatchCriteria criteria = new MoneyTransferBatchCriteria();
		request.setCriteria(criteria);
		return request;
	}

	/** Sets up the database. */
	@Before
	public void setUp()
	{
		executeSqlScript("com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/deleteBusiness.sql", false);
	}

	/** Test successful delete of a money transfer batch. */
	@Test
	public void testDeleteMoneyTransferBatch()
	{
		MoneyTransferBatch moneyTransferBatch = insertMoneyTransferBatch(1);
		moneyTransferBatch.setModelAction(PersistanceActionEnum.DELETE);
		getMoneyTransferBatchDAC().deleteMoneyTransferBatch(moneyTransferBatch);
		compare(moneyTransferBatch, PersistanceActionEnum.DELETE);
	}

	/** Test a delete of a money transfer batch with an Optimistic Locking error. */
	@Test
	public void testDeleteMoneyTransferBatchOptimisticLocking()
	{
		MoneyTransferBatch moneyTransferBatch = insertMoneyTransferBatch(1);
		moneyTransferBatch.setModelAction(PersistanceActionEnum.UPDATE);
		getMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch);
		moneyTransferBatch.setModelAction(PersistanceActionEnum.DELETE);
		moneyTransferBatch.setVersion(0);
		InternalResponse response = getMoneyTransferBatchDAC().deleteMoneyTransferBatch(moneyTransferBatch);
		CommonTestRoutines.assertResponse(response);
		assertEquals(Status.OptimisticLockingError, response.getStatus());
	}

	/** Test successful fetch of a money transfer batch by id. */
	@Test
	public void testfetchMoneyTransferBatchById()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(2);
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchById(moneyTransferBatchList.get(0).getId());
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue("MoneyTransferBatch fetch by id must return exactly 1 object", response.getResultsList()
				.size() == 1);
		compare(response.getFirstResult(), PersistanceActionEnum.FETCH);
	}

	/** Test successful fetch of money transfer batch by request, no criteria specified. */
	@Test
	public void testFetchMoneyTransferBatchByRequest()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferBatchInquiryRequest request = obtainDefaultMoneyTransferBatchInquiryRequest();
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransferBatch(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of money transfer batch by request, location id specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferBatchByRequestLocationId()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferBatchInquiryRequest request = obtainDefaultMoneyTransferBatchInquiryRequest();
		request.getCriteria().setLocationId(moneyTransferBatchList.get(0).getLocation().getId());
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransferBatch(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of money transfer batch by request, sorted by default order. */
	@Test
	public void testFetchMoneyTransferBatchByRequestSortedByDefault()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferBatchInquiryRequest request = obtainDefaultMoneyTransferBatchInquiryRequest();
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, BIG_PAGE_SIZE >= response.getResultsList().size());
		assertSortMoneyTransferBatch(response.getResultsList(), TRANSFER_DATE_SORT_FIELD, Direction.Ascending);
	}

	/** Test successful fetch of money transfer batch by request, sorted by location name ascending order. */
	@Test
	public void testFetchMoneyTransferBatchByRequestSortedByLocationNameAsc()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferBatchInquiryRequest request = obtainDefaultMoneyTransferBatchInquiryRequest();
		request.addSortExpressions(new SortExpression(LOCATION_NAME_SORT_FIELD, Direction.Ascending));
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, BIG_PAGE_SIZE >= response.getResultsList().size());
		assertSortMoneyTransferBatch(response.getResultsList(), LOCATION_NAME_SORT_FIELD, Direction.Ascending);
	}

	/**
	 * Test successful fetch of money transfer batch by request, sorted by location name ascending order, with
	 * paging.
	 */
	@Test
	public void testFetchMoneyTransferBatchByRequestSortedByLocationNameAscPaging()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT_PAGING);
		MoneyTransferBatchInquiryRequest request = obtainDefaultMoneyTransferBatchInquiryRequest();
		request.setPageSize(PAGE_SIZE);
		request.addSortExpressions(new SortExpression(LOCATION_NAME_SORT_FIELD, Direction.Ascending));
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortMoneyTransferBatch(response.getResultsList(), LOCATION_NAME_SORT_FIELD, Direction.Ascending);
		MoneyTransferBatch lastMoneyTransferBatchFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// go to next page
		request.setStartPage(1);
		response = getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		CommonTestRoutines.assertAttributeSort(lastMoneyTransferBatchFetched.getLocation().getName(), response
				.getFirstResult().getLocation().getName(), Direction.Ascending);
		assertSortMoneyTransferBatch(response.getResultsList(), LOCATION_NAME_SORT_FIELD, Direction.Ascending);
		lastMoneyTransferBatchFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// one more page
		request.setStartPage(2);
		response = getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortMoneyTransferBatch(response.getResultsList(), LOCATION_NAME_SORT_FIELD, Direction.Ascending);
		CommonTestRoutines.assertAttributeSort(lastMoneyTransferBatchFetched.getLocation().getName(), response
				.getFirstResult().getLocation().getName(), Direction.Ascending);
	}

	/** Test successful fetch of money transfer batch by request, sorted by location name descending order. */
	@Test
	public void testFetchMoneyTransferBatchByRequestSortedByLocationNameDesc()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferBatchInquiryRequest request = obtainDefaultMoneyTransferBatchInquiryRequest();
		request.addSortExpressions(new SortExpression(LOCATION_NAME_SORT_FIELD, Direction.Descending));
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, BIG_PAGE_SIZE >= response.getResultsList().size());
		assertSortMoneyTransferBatch(response.getResultsList(), LOCATION_NAME_SORT_FIELD, Direction.Descending);
	}

	/** Test successful fetch of money transfer batch by request, sorted by status ascending order. */
	@Test
	public void testFetchMoneyTransferBatchByRequestSortedByStatusAsc()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferBatchInquiryRequest request = obtainDefaultMoneyTransferBatchInquiryRequest();
		request.addSortExpressions(new SortExpression(STATUS_SORT_FIELD, Direction.Ascending));
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, BIG_PAGE_SIZE >= response.getResultsList().size());
		assertSortMoneyTransferBatch(response.getResultsList(), STATUS_SORT_FIELD, Direction.Ascending);
	}

	/** Test successful fetch of money transfer batch by request, sorted by status descending order. */
	@Test
	public void testFetchMoneyTransferBatchByRequestSortedByStatusDesc()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferBatchInquiryRequest request = obtainDefaultMoneyTransferBatchInquiryRequest();
		request.addSortExpressions(new SortExpression(STATUS_SORT_FIELD, Direction.Descending));
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, BIG_PAGE_SIZE >= response.getResultsList().size());
		assertSortMoneyTransferBatch(response.getResultsList(), STATUS_SORT_FIELD, Direction.Descending);
	}

	/** Test successful fetch of money transfer batch by request, sorted by transfer date ascending order. */
	@Test
	public void testFetchMoneyTransferBatchByRequestSortedByTransferDateAsc()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferBatchInquiryRequest request = obtainDefaultMoneyTransferBatchInquiryRequest();
		request.addSortExpressions(new SortExpression(TRANSFER_DATE_SORT_FIELD, Direction.Ascending));
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, BIG_PAGE_SIZE >= response.getResultsList().size());
		assertSortMoneyTransferBatch(response.getResultsList(), TRANSFER_DATE_SORT_FIELD, Direction.Ascending);
	}

	/** Test successful fetch of money transfer batch by request, sorted by transfer date descending order. */
	@Test
	public void testFetchMoneyTransferBatchByRequestSortedByTransferDateDesc()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferBatchInquiryRequest request = obtainDefaultMoneyTransferBatchInquiryRequest();
		request.addSortExpressions(new SortExpression(TRANSFER_DATE_SORT_FIELD, Direction.Descending));
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, BIG_PAGE_SIZE >= response.getResultsList().size());
		assertSortMoneyTransferBatch(response.getResultsList(), TRANSFER_DATE_SORT_FIELD, Direction.Descending);
	}

	/** Test successful fetch of money transfer batch by request, status specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferBatchByRequestStatus()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferBatch moneyTransferBatch = moneyTransferBatchList.get(0);
		moneyTransferBatch.setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransferBatch.addMoneyTransferBatchStatus(CommonTestRoutines
				.createDummyMoneyTransferBatchStatus(MoneyTransferBatchStatusEnum.SPREAD_REVIEW));
		getMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch);

		MoneyTransferBatchInquiryRequest request = obtainDefaultMoneyTransferBatchInquiryRequest();
		request.getCriteria().setStatusList(new ArrayList<MoneyTransferBatchStatusEnum>());
		request.getCriteria().getStatusList().add(MoneyTransferBatchStatusEnum.SPREAD_REVIEW);
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransferBatch(request.getCriteria(), response.getResultsList());
	}

	/**
	 * Test successful fetch of money transfer batch by request, status specified in the criteria but none should be
	 * found.
	 */
	@Test
	public void testFetchMoneyTransferBatchByRequestStatusNotFound()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferBatchInquiryRequest request = obtainDefaultMoneyTransferBatchInquiryRequest();
		request.getCriteria().setStatusList(new ArrayList<MoneyTransferBatchStatusEnum>());
		request.getCriteria().getStatusList().add(MoneyTransferBatchStatusEnum.ACH_PROCESSING);
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);
		Assert.assertEquals("Response status should be NoRowsFoundError", Status.NoRowsFoundError,
				response.getStatus());
		Assert.assertTrue("Response result count should be zero", response.getResultsList().size() == 0);
	}

	/** Test successful fetch of money transfer batch by request, transfer date specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferBatchByRequestTransferDate()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferBatchInquiryRequest request = obtainDefaultMoneyTransferBatchInquiryRequest();
		request.getCriteria().setTransferDate(moneyTransferBatchList.get(0).getTransferDate());
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransferBatch(request.getCriteria(), response.getResultsList());
	}

	/** Test successful insert of a MoneyTransferBatch. */
	@Test
	public void testInsertMoneyTransferBatch()
	{
		compare(insertMoneyTransferBatch(1), PersistanceActionEnum.INSERT);
	}

	/** Test successful insert of a MoneyTransferBatch. */
	@Test
	public void testInsertMoneyTransferBatchNoMoneyTransfers()
	{
		MoneyTransferBatch moneyTransferBatch = insertMoneyTransferBatch(1);
		moneyTransferBatch.setMoneyTransferList(null);
		compare(moneyTransferBatch, PersistanceActionEnum.INSERT);
	}

	/** Test successful insert of a MoneyTransferBatchStatus. */
	@Test
	public void testInsertMoneyTransferBatchStatus()
	{
		MoneyTransferBatch moneyTransferBatch = insertMoneyTransferBatch(1);
		compare(moneyTransferBatch, PersistanceActionEnum.INSERT);

		MoneyTransferBatchStatus moneyTransferStatus =
				CommonTestRoutines.createDummyMoneyTransferBatchStatus(MoneyTransferBatchStatusEnum.CANCELLED);
		moneyTransferStatus.setMoneyTransferBatchId(moneyTransferBatch.getId());

		InternalResponse response = getMoneyTransferBatchDAC().insertMoneyTransferBatchStatus(moneyTransferStatus);
		CommonTestRoutines.assertResponse(response);
		compare(moneyTransferBatch, moneyTransferStatus, PersistanceActionEnum.INSERT);
	}

	/** Test successful update of a money transfer batch. */
	@Test
	public void testUpdateMoneyTransferBatch()
	{
		MoneyTransferBatch moneyTransferBatch = insertMoneyTransferBatch(1);
		moneyTransferBatch.setTransferDate(PGSiDateUtil.getCurrentDateMillisUTC());
		moneyTransferBatch.getOriginAmount().setAmount(new BigDecimal("1000.00"));
		moneyTransferBatch.getOriginAmount().setCountry(new Country("ABW"));
		moneyTransferBatch.getOriginAmount().setCurrency(new Currency("ADP"));
		moneyTransferBatch.getOriginAmount().setStateProvinceRegion(new StateProvinceRegion("MI"));
		CommonTestRoutines.assignQATModelUpdateFields(moneyTransferBatch);
		CommonTestRoutines.assertResponse(getMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch));
		compare(moneyTransferBatch, PersistanceActionEnum.UPDATE);
	}

	/** Test successful update of a money transfer batch, deleting a money transfer. */
	@Test
	public void testUpdateMoneyTransferBatchDeleteMoneyTransfer()
	{
		MoneyTransferBatch moneyTransferBatch = insertMoneyTransferBatch(1);
		moneyTransferBatch.getMoneyTransferList().get(0).setModelAction(PersistanceActionEnum.DELETE);
		CommonTestRoutines.assertResponse(getMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch));
		compare(moneyTransferBatch, moneyTransferBatch.getMoneyTransferList().get(0), PersistanceActionEnum.DELETE);
	}

	/** Test successful update of a money transfer batch, deleting a note. */
	@Test
	public void testUpdateMoneyTransferBatchDeleteNote()
	{
		MoneyTransferBatch moneyTransferBatch = insertMoneyTransferBatch(1);
		moneyTransferBatch.getNoteList().get(0).setModelAction(PersistanceActionEnum.DELETE);
		CommonTestRoutines.assertResponse(getMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch));
		compare(moneyTransferBatch, moneyTransferBatch.getNoteList().get(0), PersistanceActionEnum.DELETE);
	}

	/** Test successful update of a money transfer batch, deleting a status. */
	@Test
	public void testUpdateMoneyTransferBatchDeleteStatus()
	{
		MoneyTransferBatch moneyTransferBatch = insertMoneyTransferBatch(1);
		moneyTransferBatch.getStatusList().get(0).setModelAction(PersistanceActionEnum.DELETE);
		CommonTestRoutines.assertResponse(getMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch));
		compare(moneyTransferBatch, moneyTransferBatch.getStatusList().get(0), PersistanceActionEnum.DELETE);
	}

	/** Test an update of a money transfer batch with an Optimistic Locking error. */
	@Test
	public void testUpdateMoneyTransferBatchOptimisticLocking()
	{
		MoneyTransferBatch moneyTransferBatch = insertMoneyTransferBatch(1);
		moneyTransferBatch.setModelAction(PersistanceActionEnum.UPDATE);
		getMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch);
		moneyTransferBatch.setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransferBatch.setVersion(0);
		InternalResponse response = getMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch);
		CommonTestRoutines.assertResponse(response);
		assertEquals(Status.OptimisticLockingError, response.getStatus());
	}

	/** Test update of a money transfer batch, updating the money transfer with optimistic locking. */
	@Test
	public void testUpdateMoneyTransferBatchUpdateMoneyTransferOptimisticLocking()
	{
		MoneyTransferBatch moneyTransferBatch = insertMoneyTransferBatch(1);
		moneyTransferBatch.getMoneyTransferList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransferBatch.getMoneyTransferList().get(0).setPaymentType(PaymentTypeEnum.CASH_TO_AGENT);
		InternalResponse response = getMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch);

		moneyTransferBatch.getMoneyTransferList().get(0).setVersion(0);
		moneyTransferBatch.getMoneyTransferList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		response = getMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch);
		CommonTestRoutines.assertResponse(response);
		assertEquals(Status.OptimisticLockingError, response.getStatus());
	}

	/** Test successful update of a money transfer batch, updating the money transfer also. */
	@Test
	public void testUpdateMoneyTransferBatchUpdateMoneyTransfer()
	{
		MoneyTransferBatch moneyTransferBatch = insertMoneyTransferBatch(1);
		moneyTransferBatch.getMoneyTransferList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransferBatch.getMoneyTransferList().get(0).setPaymentType(PaymentTypeEnum.CASH_TO_AGENT);
		CommonTestRoutines.assertResponse(getMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch));
		compare(moneyTransferBatch, moneyTransferBatch.getMoneyTransferList().get(0), PersistanceActionEnum.UPDATE);
	}

	/** Test successful update of a money transfer batch, updating a note also. */
	@Test
	public void testUpdateMoneyTransferBatchUpdateNote()
	{
		MoneyTransferBatch moneyTransferBatch = insertMoneyTransferBatch(1);
		moneyTransferBatch.getNoteList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransferBatch.getNoteList().get(0).setNoteText("Text2");
		CommonTestRoutines.assertResponse(getMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch));
		compare(moneyTransferBatch, moneyTransferBatch.getNoteList().get(0), PersistanceActionEnum.UPDATE);
	}

	/** Test update of a money transfer batch, updating a note also where it isn't found. */
	@Test
	public void testUpdateMoneyTransferBatchUpdateNoteNoFound()
	{
		MoneyTransferBatch moneyTransferBatch = insertMoneyTransferBatch(1);
		moneyTransferBatch.getNoteList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransferBatch.getNoteList().get(0).setNoteText("Text4");
		moneyTransferBatch.getNoteList().get(0).setId(0);
		InternalResponse response = getMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch);
		CommonTestRoutines.assertResponse(response);
		assertEquals(Status.NoRowsUpdatedError, response.getStatus());
	}

	/** Test successful update of a money transfer batch, updating a status also. */
	@Test
	public void testUpdateMoneyTransferBatchUpdateStatus()
	{
		MoneyTransferBatch moneyTransferBatch = insertMoneyTransferBatch(1);
		moneyTransferBatch.getStatusList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransferBatch.getStatusList().get(0).setStatus(MoneyTransferBatchStatusEnum.READY_FOR_RELEASE);
		CommonTestRoutines.assertResponse(getMoneyTransferBatchDAC().updateMoneyTransferBatch(moneyTransferBatch));
		compare(moneyTransferBatch, moneyTransferBatch.getStatusList().get(0), PersistanceActionEnum.UPDATE);
	}
}