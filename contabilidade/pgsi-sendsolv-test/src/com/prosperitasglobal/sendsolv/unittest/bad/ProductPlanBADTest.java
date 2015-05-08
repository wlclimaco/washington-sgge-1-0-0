package com.prosperitasglobal.sendsolv.unittest.bad;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bad.ProductPlanBAD;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.FeeTier;
import com.prosperitasglobal.sendsolv.model.PlanCategory;
import com.prosperitasglobal.sendsolv.model.Product;
import com.prosperitasglobal.sendsolv.model.ProductPlan;
import com.prosperitasglobal.sendsolv.model.ProductPlanApplicability;
import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.QATModelOL;
import com.qat.framework.model.UserContext;

/**
 * A JUNIT for testing the business area delegate (BAD) for product plans.
 */
public class ProductPlanBADTest
{
	/**
	 * Assert FeeTiers.
	 *
	 * @param templateFeeTier The FeeTier from the template product plan.
	 * @param businessFeeTier The FeeTier from the business product plan.
	 * @param userContext The user context.
	 */
	private void assertFieldsFeeTier(FeeTier templateFeeTier, FeeTier businessFeeTier, UserContext userContext)
	{
		assertFieldsQATModelOL(businessFeeTier, userContext);

		Assert.assertEquals("BusinessProductPlan FeeTier fee value mismatch!", templateFeeTier.getFeeValue(),
				businessFeeTier.getFeeValue());
		Assert.assertEquals("BusinessProductPlan FeeTier maximum value mismatch!", templateFeeTier.getMaximumValue(),
				businessFeeTier.getMaximumValue());
		Assert.assertEquals("BusinessProductPlan FeeTier minimum value mismatch!", templateFeeTier.getMinimumValue(),
				businessFeeTier.getMinimumValue());
		Assert.assertEquals("BusinessProductPlan FeeTier sequence number mismatch!",
				templateFeeTier.getSequenceNumber(), businessFeeTier.getSequenceNumber());
		Assert.assertNull("BusinessProductPlan FeeTier id must be null!", businessFeeTier.getId());
	}

	/**
	 * Assert List of FeeTiers.
	 *
	 * @param templateFeeTierList The list of FeeTier from the template product plan.
	 * @param businessFeeTierList The list of FeeTier from the business product plan.
	 * @param userContext The user context.
	 */
	private void assertFieldsFeeTier(List<FeeTier> templateFeeTierList, List<FeeTier> businessFeeTierList,
			UserContext userContext)
	{
		int index = 0;
		FeeTier businessFeeTier = null;
		for (FeeTier templateFeeTier : templateFeeTierList)
		{
			businessFeeTier = businessFeeTierList.get(index);
			assertFieldsFeeTier(templateFeeTier, businessFeeTier, userContext);
			index++;
		}
	}

	/**
	 * Assert list of PlanCategory.
	 *
	 * @param templatePlanCategoryList The list of PlanCategory from the template product plan.
	 * @param businessPlanCategoryList The list of PlanCategory from the business product plan.
	 * @param userContext The user context.
	 */
	private void assertFieldsPlanCategory(List<PlanCategory> templatePlanCategoryList,
			List<PlanCategory> businessPlanCategoryList, UserContext userContext)
	{
		int index = 0;
		PlanCategory businessPlanCategory = null;
		for (PlanCategory templatePlanCategory : templatePlanCategoryList)
		{
			businessPlanCategory = businessPlanCategoryList.get(index);
			assertFieldsPlanCategory(templatePlanCategory, businessPlanCategory, userContext);
			assertFieldsFeeTier(templatePlanCategory.getFeeTierList(), businessPlanCategory.getFeeTierList(),
					userContext);
			index++;
		}
	}

	/**
	 * Assert PlanCategory.
	 *
	 * @param templatePlanCategory The PlanCategory from the template product plan.
	 * @param businessPlanCategory The PlanCategory from the business product plan.
	 * @param userContext The user context.
	 */
	private void assertFieldsPlanCategory(PlanCategory templatePlanCategory, PlanCategory businessPlanCategory,
			UserContext userContext)
	{
		assertFieldsQATModelOL(businessPlanCategory, userContext);

		Assert.assertEquals("BusinessProductPlan PlanCategory call credit allowance mismatch!",
				templatePlanCategory.getCallCreditAllowance(), businessPlanCategory.getCallCreditAllowance());
		Assert.assertEquals("BusinessProductPlan PlanCategory name mismatch!", templatePlanCategory.getName(),
				businessPlanCategory.getName());
		Assert.assertEquals("BusinessProductPlan PlanCategory FeeTier List size mismatch!", templatePlanCategory
				.getFeeTierList().size(), businessPlanCategory.getFeeTierList().size());
		Assert.assertNull("BusinessProductPlan PlanCategory id must be null!", businessPlanCategory.getId());
	}

	/**
	 * Assert Product.
	 *
	 * @param templateProduct The product of the template product plan.
	 * @param businessProduct The product of the business product plan.
	 */
	private void assertFieldsProduct(Product templateProduct, Product businessProduct)
	{
		Assert.assertNotNull("BusinessProductPlan Product is null!", businessProduct);
		Assert.assertNotNull("BusinessProductPlan Product id is null!", businessProduct.getId());
		Assert.assertEquals("BusinessProductPlan Product id mismatch!", templateProduct.getId(),
				businessProduct.getId());
	}

	/**
	 * Assert ProductPlan.
	 *
	 * @param templateProductPlan The template product plan.
	 * @param businessProductPlan The business product plan.
	 * @param userContext The user context.
	 */
	private void assertFieldsProductPlan(ProductPlan templateProductPlan, ProductPlan businessProductPlan,
			UserContext userContext)
	{
		assertFieldsQATModelOL(businessProductPlan, userContext);

		Assert.assertEquals("BusinessProductPlan isDefaultProductPlan mismatch!",
				templateProductPlan.isDefaultProductPlan(), businessProductPlan.isDefaultProductPlan());
		Assert.assertEquals("BusinessProductPlan effective end date mismatch!",
				templateProductPlan.getEffectiveEndDate(), businessProductPlan.getEffectiveEndDate());
		Assert.assertEquals("BusinessProductPlan effective start date mismatch!",
				templateProductPlan.getEffectiveStartDate(), businessProductPlan.getEffectiveStartDate());
		Assert.assertEquals("BusinessProductPlan name mismatch!", templateProductPlan.getName(),
				businessProductPlan.getName());
		Assert.assertEquals("BusinessProductPlan status mismatch!", templateProductPlan.getPlanStatus(),
				businessProductPlan.getPlanStatus());
		Assert.assertNull("BusinessProductPlan id must not null!", businessProductPlan.getId());

		// Assert the parent Product
		assertFieldsProduct(templateProductPlan.getParentProduct(), businessProductPlan.getParentProduct());

		// Assert the PlanCategory's
		Assert.assertEquals("BusinessProductPlan PlanCategory List size mismatch!", templateProductPlan
				.getPlanCategoryList().size(), businessProductPlan.getPlanCategoryList().size());
		assertFieldsPlanCategory(templateProductPlan.getPlanCategoryList(), businessProductPlan.getPlanCategoryList(),
				userContext);

		// Assert the PlanPlanApplicabilityList's
		Assert.assertEquals("BusinessProductPlan ProductPlanApplicability List size mismatch!", templateProductPlan
				.getProductPlanApplicabilityList().size(),
				businessProductPlan.getProductPlanApplicabilityList().size());
		assertFieldsProductPlanApplicability(templateProductPlan.getProductPlanApplicabilityList(),
				businessProductPlan.getProductPlanApplicabilityList(), userContext);
	}

	/**
	 * Assert list of ProductPlanApplicability.
	 *
	 * @param templateProductPlanApplicabilityList The list of ProductPlanApplicability from the template product plan.
	 * @param businessProductPlanApplicabilityList The list of ProductPlanApplicability from the business product plan.
	 * @param userContext The user context.
	 */
	private void assertFieldsProductPlanApplicability(
			List<ProductPlanApplicability> templateProductPlanApplicabilityList,
			List<ProductPlanApplicability> businessProductPlanApplicabilityList, UserContext userContext)
	{
		int index = 0;
		ProductPlanApplicability businessProductPlanApplicability = null;
		for (ProductPlanApplicability templateProductPlanApplicability : templateProductPlanApplicabilityList)
		{
			businessProductPlanApplicability = businessProductPlanApplicabilityList.get(index);
			assertFieldsProductPlanApplicability(templateProductPlanApplicability, businessProductPlanApplicability,
					userContext);
			index++;
		}
	}

	/**
	 * Assert ProductPlanApplicability.
	 *
	 * @param templateProductPlanApplicability The ProductPlanApplicability from the template product plan.
	 * @param businessProductPlanApplicability The ProductPlanApplicability from the business product plan.
	 * @param userContext The user context.
	 */
	private void assertFieldsProductPlanApplicability(ProductPlanApplicability templateProductPlanApplicability,
			ProductPlanApplicability businessProductPlanApplicability, UserContext userContext)
	{
		assertFieldsQATModelOL(businessProductPlanApplicability, userContext);

		Assert.assertEquals("BusinessProductPlan ProductApplicability payer mismatch!",
				templateProductPlanApplicability.getPayer().getId().intValue(), businessProductPlanApplicability
						.getPayer().getId().intValue());
		Assert.assertEquals("BusinessProductPlan ProductApplicability paymentType mismatch!",
				templateProductPlanApplicability.getPaymentType(), businessProductPlanApplicability.getPaymentType());
		Assert.assertEquals("BusinessProductPlan ProductApplicability currency mismatch!",
				templateProductPlanApplicability.getCurrency(), businessProductPlanApplicability.getCurrency());
		Assert.assertNull("BusinessProductPlan ProductApplicability id must be null!",
				businessProductPlanApplicability.getId());
	}

	/**
	 * Assert the values on a QATModel object.
	 *
	 * @param qatModel The QATModel object.
	 * @param userContext The user context.
	 */
	private void assertFieldsQATModel(QATModel qatModel, UserContext userContext)
	{
		Assert.assertNotNull("QATModel create date UTC missing!", qatModel.getCreateDateUTC());
		Assert.assertEquals("QATModel create user mismatch!", qatModel.getCreateUser(), userContext.getUserId());
		Assert.assertNull("QATModel modify date UTC is not null", qatModel.getModifyDateUTC());
		Assert.assertNull("QATModel modify user is not null", qatModel.getModifyUser());
		Assert.assertEquals("QATModel modelAction not INSERT!", PersistanceActionEnum.INSERT,
				qatModel.getModelAction());
	}

	/**
	 * Assert the values on a QATModelOL object.
	 *
	 * @param qatModelOL The QATModel object.
	 * @param userContext The user context.
	 */
	private void assertFieldsQATModelOL(QATModelOL qatModelOL, UserContext userContext)
	{
		assertFieldsQATModel(qatModelOL, userContext);

		Assert.assertEquals("QATModelOL version is not 0!", 0, qatModelOL.getVersion().intValue());
	}

	/**
	 * Test successful creation of a {@link com.prosperitasglobal.sendsolv.model.BusinessProductPlan} from a
	 * {@link TemplateProductPlan} and a location id.
	 */
	@Test
	public void testCreateBusinessProductPlans()
	{
		TemplateProductPlan templateProductPlan =
				CommonTestRoutines.createDummyTemplateProductPlan(CommonTestRoutines.createDummyProduct(),
						CommonTestRoutines.createDummyPayer());
		UserContext userContext = new UserContext("QATTest");
		BusinessProductPlan businessProductPlan =
				ProductPlanBAD.createBusinessProductPlan(templateProductPlan, 1, userContext);
		assertFieldsProductPlan(templateProductPlan, businessProductPlan, userContext);
	}
}
