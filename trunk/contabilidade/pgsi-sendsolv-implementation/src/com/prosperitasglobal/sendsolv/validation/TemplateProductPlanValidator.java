package com.prosperitasglobal.sendsolv.validation;

import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * TemplateProductPlanValidator is the class that handles all validation for the {@link TemplateProductPlan}.
 */
public class TemplateProductPlanValidator extends ProductPlanValidator implements IValidator
{
	/** The error code set when the {@link TemplateProductPlan} object is null. */
	public static final String TEMPLATE_PRODUCT_PLAN_REQUIRED =
			"sendsolv.base.templateproductplanvalidator.teamplateproductplan.required";

	/**
	 * Perform validation.
	 *
	 * @param validationContext The validation context.
	 * @param templateProductPlan The {@link TemplateProductPlan} to validate.
	 */
	protected void performValidation(ValidationContext validationContext, TemplateProductPlan templateProductPlan)
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
	 * Validate the {@link TemplateProductPlan} object.
	 *
	 * @param validationContext The validation context.
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		TemplateProductPlan templateProductPlan =
				(TemplateProductPlan)validationContext
						.getObjectToBeValidated(TemplateProductPlan.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(templateProductPlan))
		{
			validationContext.getMessages().add(
					new MessageInfo(TEMPLATE_PRODUCT_PLAN_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			validationContext.setStopProcessing(true);
			return;
		}

		// Call parent validation first.
		super.performValidation(validationContext, templateProductPlan);

		// Validate the specific items for a template product plan.
		performValidation(validationContext, templateProductPlan);
	}
}
