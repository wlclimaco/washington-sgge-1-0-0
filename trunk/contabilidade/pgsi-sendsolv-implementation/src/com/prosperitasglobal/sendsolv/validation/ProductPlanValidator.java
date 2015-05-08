package com.prosperitasglobal.sendsolv.validation;

import com.prosperitasglobal.sendsolv.model.ProductPlan;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;

/**
 * ProductPlanValidator is the class that handles all validation for the {@link ProductPlan}.
 */
public abstract class ProductPlanValidator extends ChangeControlValidator implements IValidator
{
	/**
	 * Perform validation.
	 *
	 * @param validationContext The validation context.
	 * @param productPlan The {@link ProductPlan} to validate.
	 */
	protected void performValidation(ValidationContext validationContext, ProductPlan productPlan)
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
			return;
		}

		validateChangeControlFields(validationContext.getMessages(), productPlan, validationContext);
	}
}
