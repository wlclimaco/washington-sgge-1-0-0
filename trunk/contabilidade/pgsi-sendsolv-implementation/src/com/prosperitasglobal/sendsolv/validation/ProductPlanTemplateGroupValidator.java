package com.prosperitasglobal.sendsolv.validation;

import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroup;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * ProductPlanTemplateGroupValidator is the class that handles all validation for the {@link ProductPlanTemplateGroup}.
 */
public class ProductPlanTemplateGroupValidator extends ChangeControlValidator implements IValidator
{
	/** The error code set when the {@link ProductPlanTemplateGroup} object is null. */
	public static final String PRODUCT_PLAN_TEMPLATE_GROUP_REQUIRED =
			"sendsolv.base.productplantemplategroupvalidator.productplantemplategroup.required";

	/**
	 * Perform validation.
	 *
	 * @param validationContext The validation context.
	 * @param productPlanTemplateGroup The {@link ProductPlanTemplateGroup} to validate.
	 */
	protected void performValidation(ValidationContext validationContext,
			ProductPlanTemplateGroup productPlanTemplateGroup)
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
	 * Validate the {@link ProductPlanTemplateGroup} object.
	 *
	 * @param validationContext The validation context.
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		ProductPlanTemplateGroup productPlanTemplateGroup =
				(ProductPlanTemplateGroup)validationContext
						.getObjectToBeValidated(ProductPlanTemplateGroup.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(productPlanTemplateGroup))
		{
			validationContext.getMessages().add(
					new MessageInfo(PRODUCT_PLAN_TEMPLATE_GROUP_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			validationContext.setStopProcessing(true);
			return;
		}

		// Validate the specific items for a product plan template group.
		performValidation(validationContext, productPlanTemplateGroup);
	}
}
