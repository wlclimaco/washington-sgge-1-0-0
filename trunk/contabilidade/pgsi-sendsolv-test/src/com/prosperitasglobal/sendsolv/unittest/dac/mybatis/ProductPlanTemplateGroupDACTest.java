package com.prosperitasglobal.sendsolv.unittest.dac.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroup;
import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.QATModelOL;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class ProductPlanTemplateGroupDACTest. This test covers business/template product plans.
 */
public class ProductPlanTemplateGroupDACTest extends AbstractTestBaseDAC
{
	private static final int NUMBER_TO_INSERT = 8;

	private static final int INT_TWO = 2;

	private static final String NAME_SORT_FIELD = "name";

	/** The Constant NUMBER_OF_ROWS_NEED_TO_MATCH. */
	private static final String NUMBER_OF_ROWS_NEED_TO_MATCH = "Number of rows need to match";

	/** The Constant RESPONSE_GET_FIRST_RESULT_CANNOT_BE_NULL. */
	private static final String RESPONSE_RESULT_CANNOT_BE_NULL = "response.getFirstResult() cannot be null";

	/** The Constant RESPONSE_RESULT_MUST_BE_NULL. */
	private static final String RESPONSE_RESULT_MUST_BE_NULL = "response.getFirstResult() must be null";

	/** The Constant RESPONSE_RESULTS_LIST_SIZE_NEEDS_TO_BE_0. */
	private static final String RESPONSE_RESULTS_LIST_SIZE_NEEDS_TO_BE_0 =
			"response.getResultsList().size() needs to be = 0";
	/** The Constant RESPONSE_GET_RESULTS_LIST_SIZE_NEEDS_TO_BE_0. */
	private static final String RESPONSE_RESULTS_LIST_SIZE_NEEDS_TO_BE_GREATER_THAN_0 =
			"response.getResultsList().size() needs to be > 0";

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

	private void assertFetch(InternalResultsResponse<?> response, PersistanceActionEnum modelAction)
	{
		if ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE)
				|| (modelAction == PersistanceActionEnum.NONE))
		{
			CommonTestRoutines.assertResultResponse(response);
			Assert.assertTrue(RESPONSE_RESULTS_LIST_SIZE_NEEDS_TO_BE_GREATER_THAN_0,
					response.getResultsList().size() > 0);
			Assert.assertNotNull(RESPONSE_RESULT_CANNOT_BE_NULL, response.getFirstResult());
		}
		else if (modelAction == PersistanceActionEnum.DELETE)
		{
			Assert.assertTrue(RESPONSE_RESULTS_LIST_SIZE_NEEDS_TO_BE_0, response.getResultsList().size() == 0);
			Assert.assertNull(RESPONSE_RESULT_MUST_BE_NULL, response.getFirstResult());
		}
	}

	/**
	 * This method compares fields between two TemplateProductPlan objects.
	 *
	 * @param templateProductPlan The template product plan inserted.
	 * @param dbTemplateProductPlan The DB template product plan fetched.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsProductPlanTemplateGroup(ProductPlanTemplateGroup productPlanTemplateGroup,
			ProductPlanTemplateGroup dbTemplateProductPlan, PersistanceActionEnum modelAction)
	{
		assertFieldsQATModelOL(productPlanTemplateGroup, dbTemplateProductPlan, modelAction);

		Assert.assertEquals("ProductPlanTemplateGroup name mismatch!", productPlanTemplateGroup.getName(),
				dbTemplateProductPlan.getName());
		Assert.assertEquals("ProductPlanTemplateGroup status mismatch!", productPlanTemplateGroup.getStatus(),
				dbTemplateProductPlan.getStatus());

		if (modelAction == PersistanceActionEnum.INSERT)
		{
			Assert.assertNotNull("DB ProductPlan id must not be null!", dbTemplateProductPlan.getId());
		}
		else if ((modelAction == PersistanceActionEnum.UPDATE) || (modelAction == PersistanceActionEnum.NONE))
		{
			Assert.assertEquals("ProductPlan id mismatch!", productPlanTemplateGroup.getId(),
					dbTemplateProductPlan.getId());
		}

		/* Verify TemplateProductPlan children. */
		for (TemplateProductPlan templateProductPlan : productPlanTemplateGroup.getTemplateProductPlanList())
		{
			assertFieldsTemplateProductPlan(dbTemplateProductPlan.getTemplateProductPlanList(), templateProductPlan,
					productPlanTemplateGroup.getId(), modelAction);
		}
	}

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
	 * Test template product plan for a couple of items. Because these objects are maintained by another DAC, all we
	 * will
	 * check here is to make sure the id of the product plan template group is found on each of the children. Also,
	 * we will verify that each of the objects passed in the collection is present on the DB fetch.
	 *
	 * @param businessProductPlan The business product plan
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsTemplateProductPlan(List<TemplateProductPlan> dbTemplateProductPlanList,
			TemplateProductPlan templateProductPlan, Integer parentId, PersistanceActionEnum modelAction)
	{
		boolean templateProductPlanFound = false;
		for (TemplateProductPlan dbTemplateProductPlan : dbTemplateProductPlanList)
		{
			if (dbTemplateProductPlan.getId().equals(templateProductPlan.getId()))
			{
				templateProductPlanFound = true;
				if ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE)
						|| (modelAction == PersistanceActionEnum.NONE))
				{
					Assert.assertEquals("DB TemplateProductPlan productPlanTemplateGroupId mismatch!", parentId,
							dbTemplateProductPlan.getProductPlanTemplateGroupId());
					break;
				}
				else
				{
					Assert.assertTrue("DB PlanCategory still exists after DELETE", false);
				}
			}
		}

		if (!templateProductPlanFound
				&& ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE) ||
				(modelAction == PersistanceActionEnum.NONE)))
		{
			Assert.assertTrue("DB PlanCategory does not exists after INSERT/UPDATE/NONE", false);
		}
	}

	/**
	 * Asserts that the list of product objects is sorted correctly.
	 *
	 * @param productList The sorted list of product objects.
	 * @param fieldName The field name that was sorted.
	 * @param direction The direction of the sort.
	 */
	private void assertSortProductPlanTemplateGroup(List<ProductPlanTemplateGroup> productPlanTemplateGroupList,
			String fieldName,
			Direction direction)
	{
		boolean firstTime = true;
		ProductPlanTemplateGroup previousProductPlanTemplateGroup = null;
		for (ProductPlanTemplateGroup productPlanTemplateGroup : productPlanTemplateGroupList)
		{
			if (firstTime)
			{
				previousProductPlanTemplateGroup = productPlanTemplateGroup;
				firstTime = false;
			}
			else
			{
				if (fieldName.equals(NAME_SORT_FIELD))
				{
					assertAttributeSort(previousProductPlanTemplateGroup.getName(), productPlanTemplateGroup.getName(),
							direction);
				}
				else
				{
					Assert.assertTrue("Unknown TemplateProductPlan sort field: " + fieldName, false);
				}
			}
		}
	}

	/**
	 * Test business product plan against database by id.
	 *
	 * @param businessProductPlan The business product plan
	 * @param modelAction The model action performed.
	 */
	private void compareProductPlanTemplateGroupAgainstDatabaseById(ProductPlanTemplateGroup productPlanTemplateGroup,
			PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<ProductPlanTemplateGroup> response =
				getProductPlanTemplateGroupDAC().fetchProductPlanTemplateGroupById(productPlanTemplateGroup.getId());
		assertFetch(response, modelAction);
		if (modelAction != PersistanceActionEnum.DELETE)
		{
			assertFieldsProductPlanTemplateGroup(productPlanTemplateGroup, response.getFirstResult(), modelAction);
		}
	}

	/**
	 * Test business product plan against database by id.
	 *
	 * @param businessProductPlan The business product plan
	 * @param modelAction The model action performed.
	 */
	private void compareTemplateProductPlanAgainstDatabaseById(ProductPlanTemplateGroup productPlanTemplateGroup,
			TemplateProductPlan templateProductPlan, PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<ProductPlanTemplateGroup> response =
				getProductPlanTemplateGroupDAC().fetchProductPlanTemplateGroupById(productPlanTemplateGroup.getId());
		assertFetch(response, modelAction);
		assertFieldsTemplateProductPlan(productPlanTemplateGroup.getTemplateProductPlanList(), templateProductPlan,
				productPlanTemplateGroup.getId(), modelAction);
	}

	/**
	 * Insert products.
	 *
	 * @param numberOfProductsToInsert the number of products to insert
	 * @param uniqueIndexForProduct Unique index for product.
	 * @return the list< product>
	 */
	private List<ProductPlanTemplateGroup> insertProductPlanTemplateGroups(
			Integer numberOfProductPlanTemplateGroupsToInsert,
			boolean uniqueIndexForProductPlanTemplateGroup)
	{
		ArrayList<ProductPlanTemplateGroup> productPlanTemplateGroupList = new ArrayList<ProductPlanTemplateGroup>();

		ProductPlanTemplateGroup productPlanTemplateGroup = null;
		for (int i = 0; i < numberOfProductPlanTemplateGroupsToInsert; i++)
		{
			if (uniqueIndexForProductPlanTemplateGroup)
			{
				productPlanTemplateGroup = insertProductPlanTemplateGroup(i + 1);
			}
			else
			{
				productPlanTemplateGroup = insertProductPlanTemplateGroup(0);
			}

			if (!ValidationUtil.isNull(productPlanTemplateGroup))
			{
				productPlanTemplateGroupList.add(productPlanTemplateGroup);
			}
		}

		return productPlanTemplateGroupList;
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
	 * Test delete template product plan.
	 */
	@Test
	public void testDeleteProductPlanTemplateGroup()
	{
		ProductPlanTemplateGroup productPlanTemplateGroup = insertProductPlanTemplateGroup();
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response =
				getProductPlanTemplateGroupDAC().deleteProductPlanTemplateGroup(productPlanTemplateGroup);
		CommonTestRoutines.assertResponse(response);
		compareProductPlanTemplateGroupAgainstDatabaseById(productPlanTemplateGroup, PersistanceActionEnum.DELETE);
	}

	/**
	 * Test update business product plan.
	 */
	@Test
	public void testDeleteProductPlanTemplateGroupOptimisticLocking()
	{
		ProductPlanTemplateGroup productPlanTemplateGroup = insertProductPlanTemplateGroup();
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.UPDATE);
		productPlanTemplateGroup.setName(productPlanTemplateGroup.getName() + productPlanTemplateGroup.getName());
		InternalResponse response =
				getProductPlanTemplateGroupDAC().updateProductPlanTemplateGroup(productPlanTemplateGroup);
		CommonTestRoutines.assertResponse(response);
		compareProductPlanTemplateGroupAgainstDatabaseById(productPlanTemplateGroup, PersistanceActionEnum.UPDATE);

		productPlanTemplateGroup.setVersion(0);
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse deleteResponse =
				getProductPlanTemplateGroupDAC().deleteProductPlanTemplateGroup(productPlanTemplateGroup);
		Assert.assertEquals("ProductPlanTemplateGroup status should be Optimistic Locking on Delete",
				deleteResponse.getStatus(), Status.OptimisticLockingError);
	}

	/**
	 * Test update business product plan.
	 */
	@Test
	public void testDeleteTemplateProductPlan()
	{
		ProductPlanTemplateGroup productPlanTemplateGroup = insertProductPlanTemplateGroup();
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.UPDATE);
		productPlanTemplateGroup.setName(productPlanTemplateGroup.getName() + productPlanTemplateGroup.getName());

		TemplateProductPlan templateProductPlan = productPlanTemplateGroup.getTemplateProductPlanList().get(0);
		templateProductPlan.setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response =
				getProductPlanTemplateGroupDAC().updateProductPlanTemplateGroup(productPlanTemplateGroup);
		CommonTestRoutines.assertResponse(response);
		compareTemplateProductPlanAgainstDatabaseById(productPlanTemplateGroup, templateProductPlan,
				PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test update business product plan.
	 */
	@Test
	public void testDeleteTemplateProductPlanOptimisticLocking()
	{
		ProductPlanTemplateGroup productPlanTemplateGroup = insertProductPlanTemplateGroup();
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.UPDATE);
		productPlanTemplateGroup.setName(productPlanTemplateGroup.getName() + productPlanTemplateGroup.getName());

		TemplateProductPlan templateProductPlan = productPlanTemplateGroup.getTemplateProductPlanList().get(0);
		templateProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response =
				getProductPlanTemplateGroupDAC().updateProductPlanTemplateGroup(productPlanTemplateGroup);
		CommonTestRoutines.assertResponse(response);
		compareProductPlanTemplateGroupAgainstDatabaseById(productPlanTemplateGroup, PersistanceActionEnum.UPDATE);

		templateProductPlan.setModelAction(PersistanceActionEnum.DELETE);
		templateProductPlan.setVersion(0);
		productPlanTemplateGroup.setVersion(1);
		InternalResponse deleteResponse =
				getProductPlanTemplateGroupDAC().deleteProductPlanTemplateGroup(productPlanTemplateGroup);
		Assert.assertEquals("TemplateProductPlan status should be Optimistic Locking on delete",
				deleteResponse.getStatus(), Status.OptimisticLockingError);
	}

	/**
	 * Test fetch product by id.
	 */
	@Test
	public void testFetchProductPlanTemplateGroupByRequest()
	{
		Integer pageSize = INT_TWO;
		Integer totalMembers = NUMBER_TO_INSERT;

		insertProductPlanTemplateGroups(NUMBER_TO_INSERT, true);

		// get first page
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();

		InternalResultsResponse<ProductPlanTemplateGroup> response =
				getProductPlanTemplateGroupDAC().fetchProductPlanTemplateGroupByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortProductPlanTemplateGroup(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		ProductPlanTemplateGroup lastProductPlanTemplateGroupFetched = response.getResultsList().get(1);

		// go to next page
		request.setStartPage(1);
		response = getProductPlanTemplateGroupDAC().fetchProductPlanTemplateGroupByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertAttributeSort(lastProductPlanTemplateGroupFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
		assertSortProductPlanTemplateGroup(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		lastProductPlanTemplateGroupFetched = response.getResultsList().get(1);

		// one more page
		request.setStartPage(2);
		response = getProductPlanTemplateGroupDAC().fetchProductPlanTemplateGroupByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortProductPlanTemplateGroup(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		assertAttributeSort(lastProductPlanTemplateGroupFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
	}

	/**
	 * Test fetch product by id sorted by product name Ascending.
	 */
	@Test
	public void testFetchProductPlanTemplateGroupByRequestSortedByProductNameAsc()
	{
		Integer pageSize = INT_TWO;
		Integer totalMembers = NUMBER_TO_INSERT;

		insertProductPlanTemplateGroups(NUMBER_TO_INSERT, true);

		// get first page
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.addSortExpressions(new SortExpression(NAME_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<ProductPlanTemplateGroup> response =
				getProductPlanTemplateGroupDAC().fetchProductPlanTemplateGroupByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortProductPlanTemplateGroup(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		ProductPlanTemplateGroup lastProductPlanTemplateGroupFetched = response.getResultsList().get(1);

		// go to next page
		request.setStartPage(1);
		response = getProductPlanTemplateGroupDAC().fetchProductPlanTemplateGroupByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertAttributeSort(lastProductPlanTemplateGroupFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
		assertSortProductPlanTemplateGroup(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		lastProductPlanTemplateGroupFetched = response.getResultsList().get(1);

		// one more page
		request.setStartPage(2);
		response = getProductPlanTemplateGroupDAC().fetchProductPlanTemplateGroupByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortProductPlanTemplateGroup(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		assertAttributeSort(lastProductPlanTemplateGroupFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
	}

	/**
	 * Test fetch product by id sorted by product name Descending.
	 */
	@Test
	public void testFetchProductPlanTemplateGroupByRequestSortedByProductNameDesc()
	{
		Integer pageSize = INT_TWO;
		Integer totalMembers = NUMBER_TO_INSERT;

		insertProductPlanTemplateGroups(NUMBER_TO_INSERT, true);

		// get first page
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.addSortExpressions(new SortExpression(NAME_SORT_FIELD, Direction.Descending));

		InternalResultsResponse<ProductPlanTemplateGroup> response =
				getProductPlanTemplateGroupDAC().fetchProductPlanTemplateGroupByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortProductPlanTemplateGroup(response.getResultsList(), NAME_SORT_FIELD, Direction.Descending);
		ProductPlanTemplateGroup lastProductPlanTemplateGroupFetched = response.getResultsList().get(1);

		// go to next page
		request.setStartPage(1);
		response = getProductPlanTemplateGroupDAC().fetchProductPlanTemplateGroupByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertAttributeSort(lastProductPlanTemplateGroupFetched.getName(), response.getFirstResult().getName(),
				Direction.Descending);
		assertSortProductPlanTemplateGroup(response.getResultsList(), NAME_SORT_FIELD, Direction.Descending);
		lastProductPlanTemplateGroupFetched = response.getResultsList().get(1);

		// one more page
		request.setStartPage(2);
		response = getProductPlanTemplateGroupDAC().fetchProductPlanTemplateGroupByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortProductPlanTemplateGroup(response.getResultsList(), NAME_SORT_FIELD, Direction.Descending);
		assertAttributeSort(lastProductPlanTemplateGroupFetched.getName(), response.getFirstResult().getName(),
				Direction.Descending);
	}

	/**
	 * Test insert template product plan.
	 */
	@Test
	public void testInsertProductPlanTemplateGroup()
	{
		ProductPlanTemplateGroup productPlanTemplateGroup = insertProductPlanTemplateGroup();
		compareProductPlanTemplateGroupAgainstDatabaseById(productPlanTemplateGroup, PersistanceActionEnum.INSERT);
	}

	/**
	 * Test update business product plan.
	 */
	@Test
	public void testUpdateProductPlanTemplateGroup()
	{
		ProductPlanTemplateGroup productPlanTemplateGroup = insertProductPlanTemplateGroup();
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.UPDATE);
		productPlanTemplateGroup.setName(productPlanTemplateGroup.getName() + productPlanTemplateGroup.getName());
		InternalResponse response =
				getProductPlanTemplateGroupDAC().updateProductPlanTemplateGroup(productPlanTemplateGroup);
		CommonTestRoutines.assertResponse(response);
		compareProductPlanTemplateGroupAgainstDatabaseById(productPlanTemplateGroup, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test update business product plan.
	 */
	@Test
	public void testUpdateProductPlanTemplateGroupOptimisticLocking()
	{
		ProductPlanTemplateGroup productPlanTemplateGroup = insertProductPlanTemplateGroup();
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.UPDATE);
		productPlanTemplateGroup.setName(productPlanTemplateGroup.getName() + productPlanTemplateGroup.getName());
		InternalResponse response =
				getProductPlanTemplateGroupDAC().updateProductPlanTemplateGroup(productPlanTemplateGroup);
		CommonTestRoutines.assertResponse(response);
		compareProductPlanTemplateGroupAgainstDatabaseById(productPlanTemplateGroup, PersistanceActionEnum.UPDATE);

		productPlanTemplateGroup.setVersion(0);
		response = getProductPlanTemplateGroupDAC().updateProductPlanTemplateGroup(productPlanTemplateGroup);
		Assert.assertEquals("ProductPlanTemplateGroup status should be Optimistic Locking on update",
				response.getStatus(),
				Status.OptimisticLockingError);

	}

	/**
	 * Test update business product plan.
	 */
	@Test
	public void testUpdateTemplateProductPlan()
	{
		ProductPlanTemplateGroup productPlanTemplateGroup = insertProductPlanTemplateGroup();
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.UPDATE);
		productPlanTemplateGroup.setName(productPlanTemplateGroup.getName() + productPlanTemplateGroup.getName());

		TemplateProductPlan templateProductPlan = productPlanTemplateGroup.getTemplateProductPlanList().get(0);
		templateProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response =
				getProductPlanTemplateGroupDAC().updateProductPlanTemplateGroup(productPlanTemplateGroup);
		CommonTestRoutines.assertResponse(response);
		compareProductPlanTemplateGroupAgainstDatabaseById(productPlanTemplateGroup, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test update business product plan.
	 */
	@Test
	public void testUpdateTemplateProductPlanOptimisticLocking()
	{
		ProductPlanTemplateGroup productPlanTemplateGroup = insertProductPlanTemplateGroup();
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.UPDATE);
		productPlanTemplateGroup.setName(productPlanTemplateGroup.getName() + productPlanTemplateGroup.getName());

		TemplateProductPlan templateProductPlan = productPlanTemplateGroup.getTemplateProductPlanList().get(0);
		templateProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response =
				getProductPlanTemplateGroupDAC().updateProductPlanTemplateGroup(productPlanTemplateGroup);
		CommonTestRoutines.assertResponse(response);
		compareProductPlanTemplateGroupAgainstDatabaseById(productPlanTemplateGroup, PersistanceActionEnum.UPDATE);

		templateProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		templateProductPlan.setVersion(0);
		productPlanTemplateGroup.setVersion(1);
		response = getProductPlanTemplateGroupDAC().updateProductPlanTemplateGroup(productPlanTemplateGroup);
		Assert.assertEquals("TemplateProductPlan status should be Optimistic Locking on update",
				response.getStatus(), Status.OptimisticLockingError);

	}
}
