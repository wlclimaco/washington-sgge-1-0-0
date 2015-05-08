package com.prosperitasglobal.sendsolv.validation;

import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * BusinessProductPlanValidator is the class that handles all validation for the {@link BusinessProductPlan}.
 */
public class BusinessProductPlanValidator extends ProductPlanValidator implements IValidator
{
	/** The error code set when the {@link BusinessProductPlan} object is null. */
	public static final String BUSINESS_PRODUCT_PLAN_REQUIRED =
			"sendsolv.base.businessproductplanvalidator.businessproductplan.required";

	/**
	 * Perform validation.
	 *
	 * @param validationContext The validation context.
	 * @param businessProductPlan The {@link BusinessProductPlan} to validate.
	 */
	protected void performValidation(ValidationContext validationContext, BusinessProductPlan businessProductPlan)
	{
		switch (validationContext.getValidationContextIndicator())
		{
			case DELETE:
				break;
			case INSERT:
				break;
			case UPDATE:
				break;
			default:
				break;
		}

		if (ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
		{
			validationContext.setStopProcessing(true);
			return;
		}
	}

	/**
	 * Validate the {@link BusinessProductPlan} object.
	 *
	 * @param validationContext The validation context.
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		BusinessProductPlan businessProductPlan =
				(BusinessProductPlan)validationContext
						.getObjectToBeValidated(BusinessProductPlan.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(businessProductPlan))
		{
			validationContext.getMessages().add(
					new MessageInfo(BUSINESS_PRODUCT_PLAN_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			validationContext.setStopProcessing(true);
			return;
		}

		// Call parent validation first.
		super.performValidation(validationContext, businessProductPlan);

		// Validate the specific items for a business product plan.
		performValidation(validationContext, businessProductPlan);
	}
}
