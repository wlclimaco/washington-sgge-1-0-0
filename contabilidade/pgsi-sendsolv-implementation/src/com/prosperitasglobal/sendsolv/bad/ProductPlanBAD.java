package com.prosperitasglobal.sendsolv.bad;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.FeeTier;
import com.prosperitasglobal.sendsolv.model.PlanCategory;
import com.prosperitasglobal.sendsolv.model.ProductPlanApplicability;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.UserContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * This BAD (Business Area Delegate) exists to server common functions dealing with any class that extends from
 * a {@link com.prosperitasglobal.sendsolv.model.ProductPlan}.
 */
public final class ProductPlanBAD
{
	/**
	 * Private default constructor. Keeps from having attempts made at getting a new instance.
	 */
	private ProductPlanBAD()
	{

	}

	/**
	 * Create a new {@link FeeTier} from an existing {@link FeeTier}. The new one will have the <code>modelAction</code>
	 * set to {@link PersistanceActionEnum#INSERT}, the <code>createDateUTC</code> set to the current date/time, and the
	 * <code>createUser</code> will be set to the user from the current {@link UserContext} of the transaction.<br>
	 * <br>
	 * All of the attributes will be created new, avoiding the case where the same object is being used by multiple
	 * instances.
	 *
	 * @param feeTier The {@link FeeTier} from which the new one will be created.
	 * @param userContext The {@link UserContext} containing the user information.
	 * @return The new {@link FeeTier} created from the {@link FeeTier} parameter.
	 */
	private static FeeTier createFeeTier(FeeTier feeTier, UserContext userContext)
	{
		FeeTier createdFeeTier = new FeeTier();
		mapModelAttributes(createdFeeTier, userContext);

		if (!ValidationUtil.isNull(feeTier.getFeeValue()))
		{
			createdFeeTier.setFeeValue(new BigDecimal(feeTier.getFeeValue().toString()));
		}

		if (!ValidationUtil.isNull(feeTier.getMaximumValue()))
		{
			createdFeeTier.setMaximumValue(new BigDecimal(feeTier.getMaximumValue().toString()));
		}

		if (!ValidationUtil.isNull(feeTier.getMinimumValue()))
		{
			createdFeeTier.setMinimumValue(new BigDecimal(feeTier.getMinimumValue().toString()));
		}

		if (!ValidationUtil.isNull(feeTier.getSequenceNumber()))
		{
			createdFeeTier.setSequenceNumber(new Integer(feeTier.getSequenceNumber()));
		}

		return createdFeeTier;
	}

	/**
	 * Create a new {@link List} of {@link FeeTier} objects from an existing {@link List} of {@link FeeTier} objects.
	 * The new one will contain a new {@link FeeTier} for each one in the {@link List} of {@link FeeTier} parameter.<br>
	 *
	 * @param planCategory The {@link PlanCategory} containing the existing {@link List} of {@link FeeTier}.
	 * @param userContext The {@link UserContext} containing the user information.
	 * @return The new {@link List} of {@link FeeTier} created from the {@link PlanCategory} parameter.
	 */
	private static List<FeeTier> createFeeTierList(PlanCategory planCategory, UserContext userContext)
	{
		List<FeeTier> feeTierList = new ArrayList<FeeTier>();

		if (!ValidationUtil.isNullOrEmpty(planCategory.getFeeTierList()))
		{
			for (FeeTier feeTier : planCategory.getFeeTierList())
			{
				feeTierList.add(createFeeTier(feeTier, userContext));
			}
		}

		return feeTierList;
	}

	/**
	 * Create a new {@link PlanCategory} from an existing {@link PlanCategory}. The new one will have the
	 * <code>modelAction</code> set to {@link PersistanceActionEnum#INSERT}, the <code>createDateUTC</code> set to the
	 * current date/time, and the <code>createUser</code> will be set to the user from the current {@link UserContext}
	 * of the transaction.<br>
	 * <br>
	 * All of the attributes will be created new, avoiding the case where the same object is being used by multiple
	 * instances.
	 *
	 * @param planCategory The {@link PlanCategory} from which the new one will be created.
	 * @param userContext The {@link UserContext} containing the user information.
	 * @return The new {@link PlanCategory} created from the {@link PlanCategory} parameter.
	 */
	private static PlanCategory createPlanCategory(PlanCategory planCategory, UserContext userContext)
	{
		PlanCategory createdPlanCategory = new PlanCategory();
		mapModelAttributes(createdPlanCategory, userContext);

		if (!ValidationUtil.isNull(planCategory.getCallCreditAllowance()))
		{
			createdPlanCategory
					.setCallCreditAllowance(new BigDecimal(planCategory.getCallCreditAllowance().toString()));
		}

		createdPlanCategory.setName(planCategory.getName());
		createdPlanCategory.setFeeTierList(createFeeTierList(planCategory, userContext));
		return createdPlanCategory;
	}

	/**
	 * Create a new {@link List} of {@link PlanCategory} objects from an existing {@link List} of {@link PlanCategory}
	 * objects. The new one will contain a new {@link PlanCategory} for each one in the {@link List} of
	 * {@link PlanCategory} parameter.<br>
	 *
	 * @param planCategory The {@link TemplateProductPlan} containing the existing {@link List} of {@link PlanCategory}.
	 * @param userContext The {@link UserContext} containing the user information.
	 * @return The new {@link List} of {@link PlanCategory} created from the {@link TemplateProductPlan} parameter.
	 */
	private static List<PlanCategory> createPlanCategoryList(TemplateProductPlan templateProductPlan,
			UserContext userContext)
	{
		List<PlanCategory> planCategoryList = new ArrayList<PlanCategory>();

		if (!ValidationUtil.isNullOrEmpty(templateProductPlan.getPlanCategoryList()))
		{
			for (PlanCategory planCategory : templateProductPlan.getPlanCategoryList())
			{
				planCategoryList.add(createPlanCategory(planCategory, userContext));
			}
		}

		return planCategoryList;
	}

	/**
	 * Create a new {@link ProductPlanApplicability} from an existing {@link ProductPlanApplicability}. The new one will
	 * have the <code>modelAction</code> set to {@link PersistanceActionEnum#INSERT}, the <code>createDateUTC</code> set
	 * to the current date/time, and the <code>createUser</code> will be set to the user from the current
	 * {@link UserContext} of the transaction.<br>
	 * <br>
	 * All of the attributes will be created new, avoiding the case where the same object is being used by multiple
	 * instances.
	 *
	 * @param planCategory The {@link ProductPlanApplicability} from which the new one will be created.
	 * @param userContext The {@link UserContext} containing the user information.
	 * @return The new {@link ProductPlanApplicability} created from the {@link ProductPlanApplicability} parameter.
	 */
	private static ProductPlanApplicability createProductPlanApplicability(
			ProductPlanApplicability productPlanApplicability, UserContext userContext)
	{
		ProductPlanApplicability createdProductPlanApplicability = new ProductPlanApplicability();
		mapModelAttributes(createdProductPlanApplicability, userContext);
		createdProductPlanApplicability.setPayer(productPlanApplicability.getPayer());
		createdProductPlanApplicability.setPaymentType(productPlanApplicability.getPaymentType());
		createdProductPlanApplicability.setCurrency(productPlanApplicability.getCurrency());
		return createdProductPlanApplicability;
	}

	/**
	 * Create a new {@link List} of {@link ProductPlanApplicability} objects from an existing {@link List} of
	 * {@link ProductPlanApplicability} objects. The new one will contain a new {@link ProductPlanApplicability} for
	 * each one in the {@link List} of {@link ProductPlanApplicability} parameter.<br>
	 *
	 * @param planCategory The {@link TemplateProductPlan} containing the existing {@link List} of {@link PlanCategory}.
	 * @param userContext The {@link UserContext} containing the user information.
	 * @return The new {@link List} of {@link ProductPlanApplicability} created from the {@link TemplateProductPlan}
	 *         parameter.
	 */
	private static List<ProductPlanApplicability> createProductPlanApplicabilityList(
			TemplateProductPlan templateProductPlan, UserContext userContext)
	{
		List<ProductPlanApplicability> productPlanApplicabilityList = new ArrayList<ProductPlanApplicability>();

		if (!ValidationUtil.isNullOrEmpty(templateProductPlan.getProductPlanApplicabilityList()))
		{
			for (ProductPlanApplicability productPlanApplicability : templateProductPlan
					.getProductPlanApplicabilityList())
			{
				productPlanApplicabilityList.add(createProductPlanApplicability(productPlanApplicability, userContext));
			}
		}

		return productPlanApplicabilityList;
	}

	/**
	 * Map the common {@link QATModel} attributes for the new objects.
	 *
	 * @param model the (@link QATModel} to set the attributes.
	 * @param userContext The {@link UserContext} containing information about the user.
	 */
	private static void mapModelAttributes(QATModel model, UserContext userContext)
	{
		model.setModelAction(PersistanceActionEnum.INSERT);
		model.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		model.setCreateUser(userContext.getUserId());
	}

	/**
	 * Create a new {@link BusinessProductPlan} from an existing {@link TemplateProductPlan}. The new one will
	 * have the <code>modelAction</code> set to {@link PersistanceActionEnum#INSERT}, the <code>createDateUTC</code> set
	 * to the current date/time, and the <code>createUser</code> will be set to the user from the current
	 * {@link UserContext} of the transaction.<br>
	 * <br>
	 * All of the attributes will be created new, avoiding the case where the same object is being used by multiple
	 * instances.
	 *
	 * @param templateProductPlan The {@link TemplateProductPlan} from which the new {@link BusinessProductPlan} will be
	 *            created.
	 * @param locationId The location id to associated.
	 * @param userContext The {@link UserContext} containing the user information.
	 * @return The new {@link BusinessProductPlan} created from the {@link TemplateProductPlan} parameter.
	 */
	public static BusinessProductPlan createBusinessProductPlan(TemplateProductPlan templateProductPlan,
			Integer locationId, UserContext userContext)
	{
		BusinessProductPlan businessProductPlan = new BusinessProductPlan();
		mapModelAttributes(businessProductPlan, userContext);
		businessProductPlan.setParentProduct(templateProductPlan.getParentProduct());
		businessProductPlan.setPlanStatus(StatusEnum.SETUP);
		businessProductPlan.setName(templateProductPlan.getName());

		if (!ValidationUtil.isNull(templateProductPlan.getEffectiveEndDate()))
		{
			businessProductPlan.setEffectiveEndDate(new Long(templateProductPlan.getEffectiveEndDate()));
		}

		if (!ValidationUtil.isNull(templateProductPlan.getEffectiveStartDate()))
		{
			businessProductPlan.setEffectiveStartDate(new Long(templateProductPlan.getEffectiveStartDate()));
		}

		businessProductPlan.setDefaultProductPlan(new Boolean(templateProductPlan.isDefaultProductPlan()));
		businessProductPlan.setPlanCategoryList(createPlanCategoryList(templateProductPlan, userContext));
		businessProductPlan.setProductPlanApplicabilityList(createProductPlanApplicabilityList(templateProductPlan,
				userContext));
		businessProductPlan.setLocationId(locationId);
		return businessProductPlan;
	}
}
