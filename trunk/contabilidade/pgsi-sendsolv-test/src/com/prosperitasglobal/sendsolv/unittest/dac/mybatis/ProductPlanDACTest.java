package com.prosperitasglobal.sendsolv.unittest.dac.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.FeeTier;
import com.prosperitasglobal.sendsolv.model.PlanCategory;
import com.prosperitasglobal.sendsolv.model.Product;
import com.prosperitasglobal.sendsolv.model.ProductPlan;
import com.prosperitasglobal.sendsolv.model.ProductPlanApplicability;
import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.prosperitasglobal.sendsolv.model.criteria.BusinessProductPlanCriteria;
import com.prosperitasglobal.sendsolv.model.criteria.TemplateProductPlanCriteria;
import com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TemplateProductPlanInquiryRequest;
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
 * The Class ProductPlanDACTest. This test covers business/template product plans.
 */
public class ProductPlanDACTest extends AbstractTestBaseDAC
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
	 * This method compares fields between two BusinessProductPlan objects.
	 *
	 * @param businessProductPlan The business product plan inserted.
	 * @param dbBusinessProductPlan The DB business product plan fetched.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsBusinessProductPlan(BusinessProductPlan businessProductPlan,
			BusinessProductPlan dbBusinessProductPlan, PersistanceActionEnum modelAction)
	{
		assertFieldsProductPlan(businessProductPlan, dbBusinessProductPlan, modelAction);

		Assert.assertEquals("BusinessProductPlan location id mismatch!",
				businessProductPlan.getLocationId(),
				dbBusinessProductPlan.getLocationId());
	}

	/**
	 * This method compares fields between two FeeTier objects.
	 *
	 * @param feeTier The feeTier inserted/updated.
	 * @param dbFeeTier The DB feeTier fetched.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsFeeTier(FeeTier feeTier, FeeTier dbFeeTier, PersistanceActionEnum modelAction)
	{
		assertFieldsQATModelOL(feeTier, dbFeeTier, modelAction);

		Assert.assertTrue("FeeTier fee value mismatch!", feeTier.getFeeValue().compareTo(dbFeeTier.getFeeValue()) == 0);
		Assert.assertTrue("FeeTier maximum value mismatch!",
				feeTier.getMaximumValue().compareTo(dbFeeTier.getMaximumValue()) == 0);
		Assert.assertTrue("FeeTier minimum value mismatch!",
				feeTier.getMinimumValue().compareTo(dbFeeTier.getMinimumValue()) == 0);
		Assert.assertEquals("FeeTier sequence number mismatch!", feeTier.getSequenceNumber(),
				dbFeeTier.getSequenceNumber());

		if (modelAction == PersistanceActionEnum.INSERT)
		{
			Assert.assertNotNull("DB FeeTier id must not be null!", dbFeeTier.getId());
		}
		else if (modelAction == PersistanceActionEnum.UPDATE)
		{
			Assert.assertEquals("FeeTier id mismatch!", feeTier.getId(), dbFeeTier.getId());
		}
	}

	/**
	 * Test feeTier's against database by id.
	 *
	 * @param dbFeeTierList The list of FeeTier.
	 * @param feeTier The feeTier inserted/updated.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsFeeTier(List<FeeTier> dbFeeTierList, FeeTier feeTier, PersistanceActionEnum modelAction)
	{
		boolean feeTierFound = false;
		for (FeeTier dbFeeTier : dbFeeTierList)
		{
			if (dbFeeTier.getId().equals(feeTier.getId()))
			{
				feeTierFound = true;
				if ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE)
						|| (modelAction == PersistanceActionEnum.NONE))
				{
					assertFieldsFeeTier(feeTier, dbFeeTier, modelAction);
					break;
				}
				else
				{
					Assert.assertTrue("DB FeeTier still exists after DELETE", false);
				}
			}
		}

		if (!feeTierFound
				&& ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE) ||
				(modelAction == PersistanceActionEnum.NONE)))
		{
			Assert.assertTrue("DB FeeTier does not exists after INSERT/UPDATE/NONE", false);
		}
	}

	/**
	 * Test FeeTiers against database by id.
	 *
	 * @param dbPlanCategoryList The list of PlanCategory objects fetch from the db.
	 * @param planCategory The PlanCategory inserted/updated.
	 * @param feeTier The FeeTier inserted/updated.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsFeeTier(List<PlanCategory> dbPlanCategoryList, PlanCategory planCategory, FeeTier feeTier,
			PersistanceActionEnum modelAction)
	{
		boolean planCategoryFound = false;
		for (PlanCategory dbPlanCategory : dbPlanCategoryList)
		{
			if (dbPlanCategory.getId().equals(planCategory.getId()))
			{
				planCategoryFound = true;
				assertFieldsFeeTier(dbPlanCategory.getFeeTierList(), feeTier, modelAction);
			}
		}

		if (!planCategoryFound)
		{
			Assert.assertTrue("DB PlanCategory does not exists after INSERT/UPDATE/NONE of FeeTier", false);
		}
	}

	/**
	 * Test PlanCategory against database by id.
	 *
	 * @param dbPlanCategoryList The list of PlanCategory from the db.
	 * @param planCategory The PlanCategory inserted/updated.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsPlanCategory(List<PlanCategory> dbPlanCategoryList, PlanCategory planCategory,
			PersistanceActionEnum modelAction)
	{
		boolean planCategoryFound = false;
		for (PlanCategory dbPlanCategory : dbPlanCategoryList)
		{
			if (dbPlanCategory.getId().equals(planCategory.getId()))
			{
				planCategoryFound = true;
				if ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE)
						|| (modelAction == PersistanceActionEnum.NONE))
				{
					assertFieldsPlanCategory(planCategory, dbPlanCategory, modelAction);
					break;
				}
				else
				{
					Assert.assertTrue("DB PlanCategory still exists after DELETE", false);
				}
			}
		}

		if (!planCategoryFound
				&& ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE) ||
				(modelAction == PersistanceActionEnum.NONE)))
		{
			Assert.assertTrue("DB PlanCategory does not exists after INSERT/UPDATE/NONE", false);
		}
	}

	/**
	 * This method compares fields between two PlanCategory objects.
	 *
	 * @param planCategory The PlanCategory inserted/updated.
	 * @param dbPlanCategory The DB PlanCategory fetched.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsPlanCategory(PlanCategory planCategory, PlanCategory dbPlanCategory,
			PersistanceActionEnum modelAction)
	{
		assertFieldsQATModelOL(planCategory, dbPlanCategory, modelAction);

		Assert.assertTrue("PlanCategory description mismatch!",
				planCategory.getCallCreditAllowance().compareTo(dbPlanCategory.getCallCreditAllowance()) == 0);
		Assert.assertEquals("PlanCategory effective end date mismatch!", planCategory.getName(),
				dbPlanCategory.getName());
		Assert.assertEquals("PlanCategory effective start date mismatch!", planCategory.getProductPlanId(),
				dbPlanCategory.getProductPlanId());

		if (modelAction == PersistanceActionEnum.INSERT)
		{
			Assert.assertNotNull("DB PlanCategory id must not be null!", dbPlanCategory.getId());
		}
		else if (modelAction == PersistanceActionEnum.UPDATE)
		{
			Assert.assertEquals("PlanCategory id mismatch!", planCategory.getId(), dbPlanCategory.getId());
		}
	}

	/**
	 * This method compares fields between two Product objects.
	 *
	 * @param product The product inserted/updated.
	 * @param dbProduct The DB product fetched.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsProduct(Product product, Product dbProduct, PersistanceActionEnum modelAction)
	{
		if (modelAction == PersistanceActionEnum.INSERT)
		{
			Assert.assertNotNull("DB Product id must not be null!", dbProduct.getId());
		}
		else if ((modelAction == PersistanceActionEnum.UPDATE) || (modelAction == PersistanceActionEnum.NONE))
		{
			Assert.assertEquals("Product id mismatch!", product.getId(), dbProduct.getId());
		}
	}

	/**
	 * This method compares fields between two ProductPlan objects.
	 *
	 * @param productPlan The product plan inserted.
	 * @param dbProductPlan The DB product plan fetched.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsProductPlan(ProductPlan productPlan, ProductPlan dbProductPlan,
			PersistanceActionEnum modelAction)
	{
		assertFieldsQATModelOL(productPlan, dbProductPlan, modelAction);

		Assert.assertEquals("ProductPlan isDefaultProductPlan mismatch!", productPlan.isDefaultProductPlan(),
				dbProductPlan.isDefaultProductPlan());
		Assert.assertEquals("ProductPlan effective end date mismatch!", productPlan.getEffectiveEndDate(),
				dbProductPlan.getEffectiveEndDate());
		Assert.assertEquals("ProductPlan effective start date mismatch!", productPlan.getEffectiveStartDate(),
				dbProductPlan.getEffectiveStartDate());
		Assert.assertEquals("ProductPlan name mismatch!", productPlan.getName(), dbProductPlan.getName());
		Assert.assertEquals("ProductPlan status mismatch!", productPlan.getPlanStatus(), dbProductPlan.getPlanStatus());
		Assert.assertEquals("ProductPlan productPlanType mismatch!", productPlan.getProductPlanType(),
				dbProductPlan.getProductPlanType());

		if (modelAction == PersistanceActionEnum.INSERT)
		{
			Assert.assertNotNull("DB ProductPlan id must not be null!", dbProductPlan.getId());
		}
		else if ((modelAction == PersistanceActionEnum.UPDATE) || (modelAction == PersistanceActionEnum.NONE))
		{
			Assert.assertEquals("ProductPlan id mismatch!", productPlan.getId(), dbProductPlan.getId());
		}

		// Assert the parent Product
		assertFieldsProduct(productPlan.getParentProduct(), dbProductPlan.getParentProduct(),
				PersistanceActionEnum.NONE);

		// Assert the child PlanCategory's
		for (PlanCategory planCategory : productPlan.getPlanCategoryList())
		{
			if (modelAction == PersistanceActionEnum.INSERT)
			{
				assertFieldsPlanCategory(dbProductPlan.getPlanCategoryList(), planCategory, modelAction);
			}
			else
			{
				assertFieldsPlanCategory(dbProductPlan.getPlanCategoryList(), planCategory, PersistanceActionEnum.NONE);
			}
		}

		// Assert the child PlanCategory's
		for (ProductPlanApplicability productPlanApplicability : productPlan.getProductPlanApplicabilityList())
		{
			if (modelAction == PersistanceActionEnum.INSERT)
			{
				assertFieldsProductPlanApplicability(dbProductPlan.getProductPlanApplicabilityList(),
						productPlanApplicability, modelAction);
			}
			else
			{
				assertFieldsProductPlanApplicability(dbProductPlan.getProductPlanApplicabilityList(),
						productPlanApplicability, PersistanceActionEnum.NONE);
			}
		}

		productPlan.getProductPlanApplicabilityList();
	}

	/**
	 * Test product plan applicability against database by id.
	 *
	 * @param dbProductPlanApplicabilityList The list of product plan applicability from db.
	 * @param productPlanApplicability The product plan applicability inserted/update.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsProductPlanApplicability(List<ProductPlanApplicability> dbProductPlanApplicabilityList,
			ProductPlanApplicability productPlanApplicability,
			PersistanceActionEnum modelAction)
	{
		boolean productPlanApplicabilityFound = false;
		for (ProductPlanApplicability dbProductPlanApplicability : dbProductPlanApplicabilityList)
		{
			if (dbProductPlanApplicability.getId().equals(productPlanApplicability.getId()))
			{
				productPlanApplicabilityFound = true;
				if ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE)
						|| (modelAction == PersistanceActionEnum.NONE))
				{
					assertFieldsProductPlanApplicability(productPlanApplicability, dbProductPlanApplicability,
							modelAction);
					break;
				}
				else
				{
					Assert.assertTrue("DB ProductPlanApplicability still exists after DELETE", false);
				}
			}
		}

		if (!productPlanApplicabilityFound
				&& ((modelAction == PersistanceActionEnum.INSERT) || (modelAction == PersistanceActionEnum.UPDATE) ||
				(modelAction == PersistanceActionEnum.NONE)))
		{
			Assert.assertTrue("DB ProductPlanApplicability does not exists after INSERT/UPDATE/NONE", false);
		}
	}

	/**
	 * This method compares fields between two ProductPlanApplicability objects.
	 *
	 * @param productPlanApplicability The product plan applicability inserted/updated.
	 * @param dbProductPlanApplicability The DB product plan applicability fetched.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsProductPlanApplicability(ProductPlanApplicability productPlanApplicability,
			ProductPlanApplicability dbProductPlanApplicability,
			PersistanceActionEnum modelAction)
	{
		assertFieldsQATModel(productPlanApplicability, dbProductPlanApplicability, modelAction);

		Assert.assertEquals("ProductPlanApplicability payer mismatch!", productPlanApplicability.getPayer().getId()
				.intValue(),
				dbProductPlanApplicability.getPayer().getId().intValue());
		Assert.assertEquals("ProductPlanApplicability payment type mismatch!",
				productPlanApplicability.getPaymentType(),
				dbProductPlanApplicability.getPaymentType());
		Assert.assertEquals("ProductPlanApplicability product plan id mismatch!", productPlanApplicability
				.getProductPlanId().intValue(),
				dbProductPlanApplicability.getProductPlanId().intValue());

		if (modelAction == PersistanceActionEnum.INSERT)
		{
			Assert.assertNotNull("DB ProductPlanApplicability id must not be null!",
					dbProductPlanApplicability.getId());
		}
		else if (modelAction == PersistanceActionEnum.UPDATE)
		{
			Assert.assertEquals("ProductPlanApplicability id mismatch!", productPlanApplicability.getId(),
					dbProductPlanApplicability.getId());
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
	 * This method compares fields between two TemplateProductPlan objects.
	 *
	 * @param templateProductPlan The template product plan inserted.
	 * @param dbTemplateProductPlan The DB template product plan fetched.
	 * @param modelAction The model action performed.
	 */
	private void assertFieldsTemplateProductPlan(TemplateProductPlan templateProductPlan,
			TemplateProductPlan dbTemplateProductPlan, PersistanceActionEnum modelAction)
	{
		assertFieldsProductPlan(templateProductPlan, dbTemplateProductPlan, modelAction);

		Assert.assertEquals("TemplateProductPlan product plan template group id mismatch!",
				templateProductPlan.getProductPlanTemplateGroupId(),
				dbTemplateProductPlan.getProductPlanTemplateGroupId());
	}

	/**
	 * Compares the fetch result for a BusinessProductPlan against the criteria member.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param businessProductPlanList The list of product plans returned from the fetch.
	 */
	private void assertFilterBusinessProductPlan(BusinessProductPlanCriteria criteria,
			List<BusinessProductPlan> businessProductPlanList)
	{
		Assert.assertTrue("BusinessProductPlan Fetch contains 0!", businessProductPlanList.size() > 0);

		for (BusinessProductPlan businessProductPlan : businessProductPlanList)
		{
			if (!ValidationUtil.isNull(criteria.getId()))
			{
				Assert.assertEquals("BusinessProductPlan id mismatch!", criteria.getId(), businessProductPlan.getId());
			}

			if (!ValidationUtil.isNull(criteria.getLocationId()))
			{
				Assert.assertEquals("BusinessProductPlan locationId mismatch!", criteria.getLocationId(),
						businessProductPlan.getLocationId());
			}
		}
	}

	/**
	 * Compares the fetch result for a TemplateProductPlan against the criteria member.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param templateProductPlanList The list of product plans returned from the fetch.
	 */
	private void assertFilterTemplateProductPlan(TemplateProductPlanCriteria criteria,
			List<TemplateProductPlan> templateProductPlanList)
	{
		Assert.assertTrue("TemplateProductPlan Fetch contains 0!", templateProductPlanList.size() > 0);

		for (TemplateProductPlan templateProductPlan : templateProductPlanList)
		{
			if (!ValidationUtil.isNull(criteria.getId()))
			{
				Assert.assertEquals("TemplateProductPlan id mismatch!", criteria.getId(), templateProductPlan.getId());
			}

			if (!ValidationUtil.isNull(criteria.getProductPlanTemplateGroupId()))
			{
				Assert.assertEquals("TemplateProductPlan productPlanTemplteGroupId mismatch!",
						criteria.getProductPlanTemplateGroupId(),
						templateProductPlan.getProductPlanTemplateGroupId());
			}
		}
	}

	/**
	 * Asserts that the list of business product plan objects is sorted correctly.
	 *
	 * @param businessProductPlanList The sorted list of business product plan objects.
	 * @param fieldName The field name that was sorted.
	 * @param direction The direction of the sort.
	 */
	private void assertSortBusinessProductPlan(List<BusinessProductPlan> businessProductPlanList, String fieldName,
			Direction direction)
	{
		boolean firstTime = true;
		BusinessProductPlan previousBusinessProductPlan = null;
		for (BusinessProductPlan businessProductPlan : businessProductPlanList)
		{
			if (firstTime)
			{
				previousBusinessProductPlan = businessProductPlan;
				firstTime = false;
			}
			else
			{
				if (fieldName.equals(NAME_SORT_FIELD))
				{
					assertAttributeSort(previousBusinessProductPlan.getName(), businessProductPlan.getName(),
							direction);
				}
				else
				{
					Assert.assertTrue("Unknown BusinessProductPlan sort field: " + fieldName, false);
				}
			}
		}
	}

	/**
	 * Asserts that the list of template product plan objects is sorted correctly.
	 *
	 * @param templateProductPlanList The sorted list of template product plan objects.
	 * @param fieldName The field name that was sorted.
	 * @param direction The direction of the sort.
	 */
	private void assertSortTemplateProductPlan(List<TemplateProductPlan> templateProductPlanList, String fieldName,
			Direction direction)
	{
		boolean firstTime = true;
		TemplateProductPlan previousTemplateProductPlan = null;
		for (TemplateProductPlan templateProductPlan : templateProductPlanList)
		{
			if (firstTime)
			{
				previousTemplateProductPlan = templateProductPlan;
				firstTime = false;
			}
			else
			{
				if (fieldName.equals(NAME_SORT_FIELD))
				{
					assertAttributeSort(previousTemplateProductPlan.getName(), templateProductPlan.getName(),
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
	private void compareBusinessProductPlanAgainstDatabaseById(BusinessProductPlan businessProductPlan,
			PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<BusinessProductPlan> response =
				getProductPlanDAC().fetchBusinessProductPlanById(businessProductPlan.getId());
		assertFetch(response, modelAction);
		if (modelAction != PersistanceActionEnum.DELETE)
		{
			assertFieldsBusinessProductPlan(businessProductPlan, response.getFirstResult(), modelAction);
		}
	}

	/**
	 * Test FeeTier against database by id.
	 *
	 * @param businessProductPlan The business product plan
	 * @param planCategory The Plan category.
	 * @param feeTier The FeeTier.
	 * @param modelAction The model action performed.
	 */
	private void compareFeeTierAgainstDatabaseById(BusinessProductPlan businessProductPlan,
			PlanCategory planCategory, FeeTier feeTier, PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<BusinessProductPlan> response =
				getProductPlanDAC().fetchBusinessProductPlanById(businessProductPlan.getId());
		CommonTestRoutines.assertResultResponse(response);
		assertFieldsFeeTier(response.getFirstResult().getPlanCategoryList(), planCategory, feeTier, modelAction);
	}

	/**
	 * Test business plan category against database by id.
	 *
	 * @param businessProductPlan The business product plan
	 * @param planCategory The plan category.
	 * @param modelAction The model action performed.
	 */
	private void comparePlanCategoryAgainstDatabaseById(BusinessProductPlan businessProductPlan,
			PlanCategory planCategory, PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<BusinessProductPlan> response =
				getProductPlanDAC().fetchBusinessProductPlanById(businessProductPlan.getId());
		CommonTestRoutines.assertResultResponse(response);
		assertFieldsPlanCategory(response.getFirstResult().getPlanCategoryList(), planCategory, modelAction);
	}

	/**
	 * Test product against database by id.
	 *
	 * @param product The product
	 * @param modelAction The model action performed.
	 */
	private void compareProductAgainstDatabaseById(Product product, PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<Product> response = getProductPlanDAC().fetchProductById(product.getId());
		assertFetch(response, modelAction);
		if (modelAction != PersistanceActionEnum.DELETE)
		{
			assertFieldsProduct(product, response.getFirstResult(), modelAction);
		}
	}

	/**
	 * Test Product Plan Applicability against database by id.
	 *
	 * @param businessProductPlan The business product plan
	 * @param productPlanApplicability The product plan applicability.
	 * @param modelAction The model action performed.
	 */
	private void compareProductPlanApplicabilityAgainstDatabaseById(BusinessProductPlan businessProductPlan,
			ProductPlanApplicability productPlanApplicability, PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<BusinessProductPlan> response =
				getProductPlanDAC().fetchBusinessProductPlanById(businessProductPlan.getId());
		CommonTestRoutines.assertResultResponse(response);
		assertFieldsProductPlanApplicability(response.getFirstResult().getProductPlanApplicabilityList(),
				productPlanApplicability, modelAction);
	}

	/**
	 * Test template product plan against database by id.
	 *
	 * @param templateProductPlan The template product plan
	 * @param modelAction The model action performed.
	 */
	private void compareTemplateProductPlanAgainstDatabaseById(TemplateProductPlan templateProductPlan,
			PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<TemplateProductPlan> response =
				getProductPlanDAC().fetchTemplateProductPlanById(templateProductPlan.getId());
		assertFetch(response, modelAction);
		if (modelAction != PersistanceActionEnum.DELETE)
		{
			assertFieldsTemplateProductPlan(templateProductPlan, response.getFirstResult(), modelAction);
		}
	}

	/**
	 * Insert business product plans.
	 *
	 * @param numberOfBusinessProductPlansToInsert The number of business product plans to insert
	 * @param uniqueIndexForBusinessProductPlan Unique index for business product plan.
	 * @return the list< business product plan>
	 */
	private List<BusinessProductPlan> insertBusinessProductPlans(Integer numberOfBusinessProductPlansToInsert,
			boolean uniqueIndexForBusinessProductPlan)
	{
		ArrayList<BusinessProductPlan> businessProductPlanList = new ArrayList<BusinessProductPlan>();

		BusinessProductPlan businessProductPlan = null;
		for (int i = 0; i < numberOfBusinessProductPlansToInsert; i++)
		{
			if (uniqueIndexForBusinessProductPlan)
			{
				businessProductPlan = insertBusinessProductPlan(i + 1);
			}
			else
			{
				businessProductPlan = insertBusinessProductPlan(0);
			}

			if (!ValidationUtil.isNull(businessProductPlan))
			{
				businessProductPlanList.add(businessProductPlan);
			}
		}

		return businessProductPlanList;
	}

	/**
	 * Insert template product plans.
	 *
	 * @param numberOfTemplateProductPlansToInsert The number of template product plans to insert
	 * @param uniqueIndexForTemplateProductPlan The Unique index for template product plan.
	 * @return the list< template product plans>
	 */
	private List<TemplateProductPlan> insertTemplateProductPlans(Integer numberOfTemplateProductPlansToInsert,
			boolean uniqueIndexForTemplateProductPlan)
	{
		ArrayList<TemplateProductPlan> templateProductPlanList = new ArrayList<TemplateProductPlan>();

		TemplateProductPlan templateProductPlan = null;
		for (int i = 0; i < numberOfTemplateProductPlansToInsert; i++)
		{
			if (uniqueIndexForTemplateProductPlan)
			{
				templateProductPlan = insertTemplateProductPlan(i + 1);
			}
			else
			{
				templateProductPlan = insertTemplateProductPlan(0);
			}

			if (!ValidationUtil.isNull(templateProductPlan))
			{
				templateProductPlanList.add(templateProductPlan);
			}
		}

		return templateProductPlanList;
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
	 * Test successful delete of business product plan.
	 */
	@Test
	public void testDeleteBusinessProductPlan()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();
		businessProductPlan.setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response = getProductPlanDAC().deleteBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareBusinessProductPlanAgainstDatabaseById(businessProductPlan, PersistanceActionEnum.DELETE);
	}

	/**
	 * Test delete of business business product plan with an Optimistic Locking error.
	 */
	@Test
	public void testDeleteBusinessProductPlanOptimisticLocking()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();
		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		businessProductPlan.setName(businessProductPlan.getName() + businessProductPlan.getName());
		InternalResponse response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareBusinessProductPlanAgainstDatabaseById(businessProductPlan, PersistanceActionEnum.UPDATE);

		businessProductPlan.setVersion(0);
		businessProductPlan.setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse deleteResponse = getProductPlanDAC().deleteBusinessProductPlan(businessProductPlan);
		Assert.assertEquals("BusinessProductPlan status should be Optimistic Locking on Business Product Plan",
				deleteResponse.getStatus(), Status.OptimisticLockingError);

	}

	/**
	 * Test successful delete of FeeTier.
	 */
	@Test
	public void testDeleteFeeTier()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();
		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		PlanCategory planCategory = businessProductPlan.getPlanCategoryList().get(0);
		FeeTier feeTier = planCategory.getFeeTierList().get(0);
		feeTier.setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareFeeTierAgainstDatabaseById(businessProductPlan, planCategory, feeTier, PersistanceActionEnum.DELETE);
	}

	/**
	 * Test delete of FeeTier with an Optimistic Locking error.
	 */
	@Test
	public void testDeleteFeeTierOptimisticLocking()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();

		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		PlanCategory planCategory = businessProductPlan.getPlanCategoryList().get(0);
		FeeTier feeTier = planCategory.getFeeTierList().get(0);
		feeTier.setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareFeeTierAgainstDatabaseById(businessProductPlan, planCategory, feeTier, PersistanceActionEnum.UPDATE);

		feeTier.setModelAction(PersistanceActionEnum.DELETE);
		feeTier.setVersion(0);
		businessProductPlan.setVersion(1);
		response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		Assert.assertEquals("FeeTier status should be Optimistic Locking on Delete",
				response.getStatus(), Status.OptimisticLockingError);
	}

	/**
	 * Test successful delete of PlanCategory.
	 */
	@Test
	public void testDeletePlanCategory()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();
		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		PlanCategory planCategory = businessProductPlan.getPlanCategoryList().get(0);
		planCategory.setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		comparePlanCategoryAgainstDatabaseById(businessProductPlan, planCategory, PersistanceActionEnum.DELETE);
	}

	/**
	 * Test delete of PlanCategory with an Optimistic Locking error.
	 */
	@Test
	public void testDeletePlanCategoryOptimisticLocking()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();

		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		PlanCategory planCategory = businessProductPlan.getPlanCategoryList().get(0);
		planCategory.setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		comparePlanCategoryAgainstDatabaseById(businessProductPlan, planCategory, PersistanceActionEnum.UPDATE);

		planCategory.setModelAction(PersistanceActionEnum.DELETE);
		planCategory.setVersion(0);
		businessProductPlan.setVersion(1);
		response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		Assert.assertEquals("PlanCategory status should be Optimistic Locking on Delete",
				response.getStatus(), Status.OptimisticLockingError);
	}

	/**
	 * Test successful delete of a product.
	 */
	@Test
	public void testDeleteProduct()
	{
		Product product = insertProduct();
		product.setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response = getProductPlanDAC().deleteProduct(product);
		CommonTestRoutines.assertResponse(response);
		compareProductAgainstDatabaseById(product, PersistanceActionEnum.DELETE);
	}

	/**
	 * Test delete of a product with Optimistic Locking.
	 */
	@Test
	public void testDeleteProductOptimisticLocking()
	{
		Product product = insertProduct();
		product.setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response = getProductPlanDAC().updateProduct(product);

		product.setModelAction(PersistanceActionEnum.DELETE);
		product.setVersion(0);
		response = getProductPlanDAC().updateProduct(product);

		CommonTestRoutines.assertResponse(response);
		Assert.assertEquals("Product status should be Optimistic Locking on Delete",
				response.getStatus(), Status.OptimisticLockingError);
	}

	/**
	 * Test successful delete of a ProductPlanApplicability.
	 */
	@Test
	public void testDeleteProductPlanApplicability()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();
		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		ProductPlanApplicability productPlanApplicability =
				businessProductPlan.getProductPlanApplicabilityList().get(0);
		productPlanApplicability.setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareProductPlanApplicabilityAgainstDatabaseById(businessProductPlan, productPlanApplicability,
				PersistanceActionEnum.DELETE);
	}

	/**
	 * Test delete of a ProductPlanApplicability with an Optimistic Locking error.
	 */
	@Test
	public void testDeleteProductPlanApplicabilityOptimisticLocking()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();

		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		ProductPlanApplicability productPlanApplicability =
				businessProductPlan.getProductPlanApplicabilityList().get(0);
		productPlanApplicability.setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareProductPlanApplicabilityAgainstDatabaseById(businessProductPlan, productPlanApplicability,
				PersistanceActionEnum.UPDATE);

		productPlanApplicability.setModelAction(PersistanceActionEnum.DELETE);
		productPlanApplicability.setVersion(0);
		businessProductPlan.setVersion(1);
		response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		Assert.assertEquals("ProductPlanApplicability status should be Optimistic Locking on Delete",
				response.getStatus(), Status.OptimisticLockingError);
	}

	/**
	 * Test successful delete of a template product plan.
	 */
	@Test
	public void testDeleteTemplateProductPlan()
	{
		TemplateProductPlan templateProductPlan = insertTemplateProductPlan();
		templateProductPlan.setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response = getProductPlanDAC().deleteTemplateProductPlan(templateProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareTemplateProductPlanAgainstDatabaseById(templateProductPlan, PersistanceActionEnum.DELETE);
	}

	/**
	 * Test delete of a template product plan with Optimistic Locking error.
	 */
	@Test
	public void testDeleteTemplateProductPlanOptimisticLocking()
	{
		TemplateProductPlan templateProductPlan = insertTemplateProductPlan();
		templateProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		templateProductPlan.setName(templateProductPlan.getName() + templateProductPlan.getName());
		InternalResponse response = getProductPlanDAC().updateTemplateProductPlan(templateProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareTemplateProductPlanAgainstDatabaseById(templateProductPlan, PersistanceActionEnum.UPDATE);

		templateProductPlan.setVersion(0);
		templateProductPlan.setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse deleteResponse = getProductPlanDAC().deleteTemplateProductPlan(templateProductPlan);
		Assert.assertEquals("TemplateProductPlan status should be Optimistic Locking on Template Product Plan",
				deleteResponse.getStatus(), Status.OptimisticLockingError);

	}

	/**
	 * Test successful fetch of business product plan by request, only id is specified.
	 */
	@Test
	public void testFetchBusinessProductPlanByRequestId()
	{
		Integer pageSize = NUMBER_TO_INSERT;
		List<BusinessProductPlan> businessProductPlanList = insertBusinessProductPlans(NUMBER_TO_INSERT, true);

		// get first page
		BusinessProductPlanInquiryRequest request = new BusinessProductPlanInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		BusinessProductPlanCriteria criteria = new BusinessProductPlanCriteria();
		criteria.setId(businessProductPlanList.get(0).getId());
		request.setCriteria(criteria);

		InternalResultsResponse<BusinessProductPlan> response =
				getProductPlanDAC().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterBusinessProductPlan(criteria, response.getResultsList());
	}

	/**
	 * Test successful fetch of business product plan by request, both id and location id specified.
	 */
	@Test
	public void testFetchBusinessProductPlanByRequestIdAndLocation()
	{
		Integer pageSize = NUMBER_TO_INSERT;
		List<BusinessProductPlan> businessProductPlanList = insertBusinessProductPlans(NUMBER_TO_INSERT, true);

		// get first page
		BusinessProductPlanInquiryRequest request = new BusinessProductPlanInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		BusinessProductPlanCriteria criteria = new BusinessProductPlanCriteria();
		criteria.setId(businessProductPlanList.get(0).getId());
		criteria.setLocationId(businessProductPlanList.get(0).getLocationId());
		request.setCriteria(criteria);

		InternalResultsResponse<BusinessProductPlan> response =
				getProductPlanDAC().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterBusinessProductPlan(criteria, response.getResultsList());
	}

	/**
	 * Test successful fetch of business product plan by request, only location id is specified.
	 */
	@Test
	public void testFetchBusinessProductPlanByRequestLocationId()
	{
		Integer pageSize = NUMBER_TO_INSERT;
		List<BusinessProductPlan> businessProductPlanList = insertBusinessProductPlans(NUMBER_TO_INSERT, true);

		// get first page
		BusinessProductPlanInquiryRequest request = new BusinessProductPlanInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		BusinessProductPlanCriteria criteria = new BusinessProductPlanCriteria();
		criteria.setLocationId(businessProductPlanList.get(0).getLocationId());
		request.setCriteria(criteria);

		InternalResultsResponse<BusinessProductPlan> response =
				getProductPlanDAC().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterBusinessProductPlan(criteria, response.getResultsList());
	}

	/**
	 * Test successful fetch of business product plan by request, sorted by default order.
	 */
	@Test
	public void testFetchBusinessProductPlanByRequestSortedByDefault()
	{
		Integer pageSize = INT_TWO;
		Integer totalMembers = NUMBER_TO_INSERT;

		insertBusinessProductPlans(NUMBER_TO_INSERT, true);

		// get first page
		BusinessProductPlanInquiryRequest request = new BusinessProductPlanInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();

		InternalResultsResponse<BusinessProductPlan> response =
				getProductPlanDAC().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortBusinessProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		BusinessProductPlan lastBusinessProductPlanFetched = response.getResultsList().get(1);

		// go to next page
		request.setStartPage(1);
		response = getProductPlanDAC().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertAttributeSort(lastBusinessProductPlanFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
		assertSortBusinessProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		lastBusinessProductPlanFetched = response.getResultsList().get(1);

		// one more page
		request.setStartPage(2);
		response = getProductPlanDAC().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortBusinessProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		assertAttributeSort(lastBusinessProductPlanFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
	}

	/**
	 * Test successful fetch of business product plan by request, sorted by name ascending order.
	 */
	@Test
	public void testFetchBusinessProductPlanByRequestSortedByProductNameAsc()
	{
		Integer pageSize = INT_TWO;
		Integer totalMembers = NUMBER_TO_INSERT;

		insertBusinessProductPlans(NUMBER_TO_INSERT, true);

		// get first page
		BusinessProductPlanInquiryRequest request = new BusinessProductPlanInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.addSortExpressions(new SortExpression(NAME_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<BusinessProductPlan> response =
				getProductPlanDAC().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortBusinessProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		BusinessProductPlan lastBusinessProductPlanFetched = response.getResultsList().get(1);

		// go to next page
		request.setStartPage(1);
		response = getProductPlanDAC().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertAttributeSort(lastBusinessProductPlanFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
		assertSortBusinessProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		lastBusinessProductPlanFetched = response.getResultsList().get(1);

		// one more page
		request.setStartPage(2);
		response = getProductPlanDAC().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortBusinessProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		assertAttributeSort(lastBusinessProductPlanFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
	}

	/**
	 * Test successful fetch of business product plan by request, sorted by name descending order.
	 */
	@Test
	public void testFetchBusinessProductPlanByRequestSortedByProductNameDesc()
	{
		Integer pageSize = INT_TWO;
		Integer totalMembers = NUMBER_TO_INSERT;

		insertBusinessProductPlans(NUMBER_TO_INSERT, true);

		// get first page
		BusinessProductPlanInquiryRequest request = new BusinessProductPlanInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.addSortExpressions(new SortExpression(NAME_SORT_FIELD, Direction.Descending));

		InternalResultsResponse<BusinessProductPlan> response =
				getProductPlanDAC().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortBusinessProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Descending);
		BusinessProductPlan lastBusinessProductPlanFetched = response.getResultsList().get(1);

		// go to next page
		request.setStartPage(1);
		response = getProductPlanDAC().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertAttributeSort(lastBusinessProductPlanFetched.getName(), response.getFirstResult().getName(),
				Direction.Descending);
		assertSortBusinessProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Descending);
		lastBusinessProductPlanFetched = response.getResultsList().get(1);

		// one more page
		request.setStartPage(2);
		response = getProductPlanDAC().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortBusinessProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Descending);
		assertAttributeSort(lastBusinessProductPlanFetched.getName(), response.getFirstResult().getName(),
				Direction.Descending);
	}

	/**
	 * Test successful fetch of template product plan by request, only id is specified.
	 */
	@Test
	public void testFetchTemplateProductPlanByRequestId()
	{
		Integer pageSize = NUMBER_TO_INSERT;
		List<TemplateProductPlan> templateProductPlanList = insertTemplateProductPlans(NUMBER_TO_INSERT, true);

		// get first page
		TemplateProductPlanInquiryRequest request = new TemplateProductPlanInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		TemplateProductPlanCriteria criteria = new TemplateProductPlanCriteria();
		criteria.setId(templateProductPlanList.get(0).getId());
		request.setCriteria(criteria);

		InternalResultsResponse<TemplateProductPlan> response =
				getProductPlanDAC().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterTemplateProductPlan(criteria, response.getResultsList());
	}

	/**
	 * Test successful fetch of template product plan by request, both id and product plan template group id specified.
	 */
	@Test
	public void testFetchTemplateProductPlanByRequestIdAndProductPlanTemplateGroupId()
	{
		Integer pageSize = NUMBER_TO_INSERT;
		List<TemplateProductPlan> templateProductPlanList = insertTemplateProductPlans(NUMBER_TO_INSERT, true);

		// get first page
		TemplateProductPlanInquiryRequest request = new TemplateProductPlanInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		TemplateProductPlanCriteria criteria = new TemplateProductPlanCriteria();
		criteria.setId(templateProductPlanList.get(0).getId());
		criteria.setProductPlanTemplateGroupId(templateProductPlanList.get(0).getProductPlanTemplateGroupId());
		request.setCriteria(criteria);

		InternalResultsResponse<TemplateProductPlan> response =
				getProductPlanDAC().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterTemplateProductPlan(criteria, response.getResultsList());
	}

	/**
	 * Test successful fetch of business product plan by request, only product plan template group id is specified.
	 */
	@Test
	public void testFetchTemplateProductPlanByRequestProductPlanTemplateGroupId()
	{
		Integer pageSize = NUMBER_TO_INSERT;
		List<TemplateProductPlan> templateProductPlanList = insertTemplateProductPlans(NUMBER_TO_INSERT, true);

		// get first page
		TemplateProductPlanInquiryRequest request = new TemplateProductPlanInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		TemplateProductPlanCriteria criteria = new TemplateProductPlanCriteria();
		criteria.setProductPlanTemplateGroupId(templateProductPlanList.get(0).getProductPlanTemplateGroupId());
		request.setCriteria(criteria);

		InternalResultsResponse<TemplateProductPlan> response =
				getProductPlanDAC().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterTemplateProductPlan(criteria, response.getResultsList());
	}

	/**
	 * Test successful fetch of template product plan by request, sorted by default order.
	 */
	@Test
	public void testFetchTemplateProductPlanByRequestSortedByDefault()
	{
		Integer pageSize = INT_TWO;
		Integer totalMembers = NUMBER_TO_INSERT;

		insertTemplateProductPlans(NUMBER_TO_INSERT, true);

		// get first page
		TemplateProductPlanInquiryRequest request = new TemplateProductPlanInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();

		InternalResultsResponse<TemplateProductPlan> response =
				getProductPlanDAC().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortTemplateProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		TemplateProductPlan lastTemplateProductPlanFetched = response.getResultsList().get(1);

		// go to next page
		request.setStartPage(1);
		response = getProductPlanDAC().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertAttributeSort(lastTemplateProductPlanFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
		assertSortTemplateProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		lastTemplateProductPlanFetched = response.getResultsList().get(1);

		// one more page
		request.setStartPage(2);
		response = getProductPlanDAC().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortTemplateProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		assertAttributeSort(lastTemplateProductPlanFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
	}

	/**
	 * Test successful fetch of template product plan by request, sorted by name ascending order.
	 */
	@Test
	public void testFetchTemplateProductPlanByRequestSortedByProductNameAsc()
	{
		Integer pageSize = INT_TWO;
		Integer totalMembers = NUMBER_TO_INSERT;

		insertTemplateProductPlans(NUMBER_TO_INSERT, true);

		// get first page
		TemplateProductPlanInquiryRequest request = new TemplateProductPlanInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.addSortExpressions(new SortExpression(NAME_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<TemplateProductPlan> response =
				getProductPlanDAC().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortTemplateProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		TemplateProductPlan lastTemplateProductPlanFetched = response.getResultsList().get(1);

		// go to next page
		request.setStartPage(1);
		response = getProductPlanDAC().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertAttributeSort(lastTemplateProductPlanFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
		assertSortTemplateProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		lastTemplateProductPlanFetched = response.getResultsList().get(1);

		// one more page
		request.setStartPage(2);
		response = getProductPlanDAC().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortTemplateProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
		assertAttributeSort(lastTemplateProductPlanFetched.getName(), response.getFirstResult().getName(),
				Direction.Ascending);
	}

	/**
	 * Test successful fetch of template product plan by request, sorted by name descending order.
	 */
	@Test
	public void testFetchTemplateProductPlanByRequestSortedByProductNameDesc()
	{
		Integer pageSize = INT_TWO;
		Integer totalMembers = NUMBER_TO_INSERT;

		insertTemplateProductPlans(NUMBER_TO_INSERT, true);

		// get first page
		TemplateProductPlanInquiryRequest request = new TemplateProductPlanInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.addSortExpressions(new SortExpression(NAME_SORT_FIELD, Direction.Descending));

		InternalResultsResponse<TemplateProductPlan> response =
				getProductPlanDAC().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortTemplateProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Descending);
		TemplateProductPlan lastTemplateProductPlanFetched = response.getResultsList().get(1);

		// go to next page
		request.setStartPage(1);
		response = getProductPlanDAC().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertAttributeSort(lastTemplateProductPlanFetched.getName(), response.getFirstResult().getName(),
				Direction.Descending);
		assertSortTemplateProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Descending);
		lastTemplateProductPlanFetched = response.getResultsList().get(1);

		// one more page
		request.setStartPage(2);
		response = getProductPlanDAC().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers <= response.getResultsSetInfo()
				.getTotalRowsAvailable());
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, INT_TWO == response.getResultsList().size());
		assertSortTemplateProductPlan(response.getResultsList(), NAME_SORT_FIELD, Direction.Descending);
		assertAttributeSort(lastTemplateProductPlanFetched.getName(), response.getFirstResult().getName(),
				Direction.Descending);
	}

	/**
	 * Test successful insert of business product plan.
	 */
	@Test
	public void testInsertBusinessProductPlan()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();
		compareBusinessProductPlanAgainstDatabaseById(businessProductPlan, PersistanceActionEnum.INSERT);
	}

	/**
	 * Test successful insert of a product.
	 */
	@Test
	public void testInsertProduct()
	{
		Product product = insertProduct();
		compareProductAgainstDatabaseById(product, PersistanceActionEnum.INSERT);
	}

	/**
	 * Test successful insert of a template product plan.
	 */
	@Test
	public void testInsertTemplateProductPlan()
	{
		TemplateProductPlan templateProductPlan = insertTemplateProductPlan();
		compareTemplateProductPlanAgainstDatabaseById(templateProductPlan, PersistanceActionEnum.INSERT);
	}

	/**
	 * Test successful update of a business product plan.
	 */
	@Test
	public void testUpdateBusinessProductPlan()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();
		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		businessProductPlan.setName(businessProductPlan.getName() + businessProductPlan.getName());
		InternalResponse response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareBusinessProductPlanAgainstDatabaseById(businessProductPlan, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test update of a business product plan with an Optimistic Locking error.
	 */
	@Test
	public void testUpdateBusinessProductPlanOptimisticLocking()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();
		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		businessProductPlan.setName(businessProductPlan.getName() + businessProductPlan.getName());
		InternalResponse response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareBusinessProductPlanAgainstDatabaseById(businessProductPlan, PersistanceActionEnum.UPDATE);

		businessProductPlan.setVersion(0);
		response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		Assert.assertEquals("BusinessProductPlan status should be Optimistic Locking", response.getStatus(),
				Status.OptimisticLockingError);

	}

	/**
	 * Test successful update of a FeeTier.
	 */
	@Test
	public void testUpdateFeeTier()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();
		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		PlanCategory planCategory = businessProductPlan.getPlanCategoryList().get(0);
		FeeTier feeTier = planCategory.getFeeTierList().get(0);
		feeTier.setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareFeeTierAgainstDatabaseById(businessProductPlan, planCategory, feeTier, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test update of a FeeTier with an Optimistic Locking error.
	 */
	@Test
	public void testUpdateFeeTierOptimisticLocking()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();

		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		PlanCategory planCategory = businessProductPlan.getPlanCategoryList().get(0);
		FeeTier feeTier = planCategory.getFeeTierList().get(0);
		feeTier.setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareFeeTierAgainstDatabaseById(businessProductPlan, planCategory, feeTier, PersistanceActionEnum.UPDATE);

		feeTier.setModelAction(PersistanceActionEnum.UPDATE);
		feeTier.setVersion(0);
		businessProductPlan.setVersion(1);
		response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		Assert.assertEquals("FeeTier status should be Optimistic Locking on Update",
				response.getStatus(), Status.OptimisticLockingError);
	}

	/**
	 * Test successful update of a PlanCategory.
	 */
	@Test
	public void testUpdatePlanCategory()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();
		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		PlanCategory planCategory = businessProductPlan.getPlanCategoryList().get(0);
		planCategory.setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		comparePlanCategoryAgainstDatabaseById(businessProductPlan, planCategory, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test update of a Plan Category with an Optimistic Locking error.
	 */
	@Test
	public void testUpdatePlanCategoryOptimisticLocking()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();

		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		PlanCategory planCategory = businessProductPlan.getPlanCategoryList().get(0);
		planCategory.setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		comparePlanCategoryAgainstDatabaseById(businessProductPlan, planCategory, PersistanceActionEnum.UPDATE);

		planCategory.setModelAction(PersistanceActionEnum.UPDATE);
		planCategory.setVersion(0);
		businessProductPlan.setVersion(1);
		response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		Assert.assertEquals("PlanCategory status should be Optimistic Locking on Update",
				response.getStatus(), Status.OptimisticLockingError);
	}

	/**
	 * Test successful update of a product.
	 */
	@Test
	public void testUpdateProduct()
	{
		Product product = insertProduct();

		product.setModelAction(PersistanceActionEnum.UPDATE);
		product.setName(product.getName() + product.getName());
		InternalResponse response = getProductPlanDAC().updateProduct(product);
		CommonTestRoutines.assertResponse(response);
		compareProductAgainstDatabaseById(product, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test update of a product with an Optimistic Locking error.
	 */
	@Test
	public void testUpdateProductOptimisticLocking()
	{
		Product product = insertProduct();

		product.setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response = getProductPlanDAC().updateProduct(product);

		product.setModelAction(PersistanceActionEnum.UPDATE);
		product.setVersion(0);
		response = getProductPlanDAC().updateProduct(product);
		Assert.assertEquals("Product status should be Optimistic Locking on Update",
				response.getStatus(), Status.OptimisticLockingError);
	}

	/**
	 * Test successful update of a ProductPlanApplicability.
	 */
	@Test
	public void testUpdateProductPlanApplicability()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();
		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		ProductPlanApplicability productPlanApplicability =
				businessProductPlan.getProductPlanApplicabilityList().get(0);
		productPlanApplicability.setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareProductPlanApplicabilityAgainstDatabaseById(businessProductPlan, productPlanApplicability,
				PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test update of a ProductPlanApplicability with an Optimistic Locking error.
	 */
	@Test
	public void testUpdateProductPlanApplicabilityOptimisticLocking()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();

		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		ProductPlanApplicability productPlanApplicability =
				businessProductPlan.getProductPlanApplicabilityList().get(0);
		productPlanApplicability.setModelAction(PersistanceActionEnum.UPDATE);
		InternalResponse response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareProductPlanApplicabilityAgainstDatabaseById(businessProductPlan, productPlanApplicability,
				PersistanceActionEnum.UPDATE);

		productPlanApplicability.setModelAction(PersistanceActionEnum.UPDATE);
		productPlanApplicability.setVersion(0);
		businessProductPlan.setVersion(1);
		response = getProductPlanDAC().updateBusinessProductPlan(businessProductPlan);
		Assert.assertEquals("ProductPlanApplicability status should be Optimistic Locking on Update",
				response.getStatus(), Status.OptimisticLockingError);
	}

	/**
	 * Test successful update of a template product plan.
	 */
	@Test
	public void testUpdateTemplateProductPlan()
	{
		TemplateProductPlan templateProductPlan = insertTemplateProductPlan();
		templateProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		templateProductPlan.setName(templateProductPlan.getName() + templateProductPlan.getName());
		InternalResponse response = getProductPlanDAC().updateTemplateProductPlan(templateProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareTemplateProductPlanAgainstDatabaseById(templateProductPlan, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test update of a template product plan with an Optimistic Locking error.
	 */
	@Test
	public void testUpdateTemplateProductPlanOptimisticLocking()
	{
		TemplateProductPlan templateProductPlan = insertTemplateProductPlan();
		templateProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		templateProductPlan.setName(templateProductPlan.getName() + templateProductPlan.getName());
		InternalResponse response = getProductPlanDAC().updateTemplateProductPlan(templateProductPlan);
		CommonTestRoutines.assertResponse(response);
		compareTemplateProductPlanAgainstDatabaseById(templateProductPlan, PersistanceActionEnum.UPDATE);

		templateProductPlan.setVersion(0);
		response = getProductPlanDAC().updateTemplateProductPlan(templateProductPlan);
		Assert.assertEquals("TemplateProductPlan status should be Optimistic Locking", response.getStatus(),
				Status.OptimisticLockingError);

	}
}
