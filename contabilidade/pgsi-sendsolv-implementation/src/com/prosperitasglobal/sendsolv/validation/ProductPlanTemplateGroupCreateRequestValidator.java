package com.prosperitasglobal.sendsolv.validation;

import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupCreateRequest;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class ProductPlanTemplateGroupCreateRequestValidator. Validates the
 * {@link com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupCreateRequest} for a create.
 */
public class ProductPlanTemplateGroupCreateRequestValidator implements IValidator
{
	/** The error code set when the {@link ProductPlanTemplateGroupCreateRequest} object is null. */
	public static final String PRODUCT_PLAN_TEMPLATE_GROUP_CREATE_REQUEST_REQUIRED =
			"sendsolv.base.productplantemplategroupcreaterequestvalidator.create.request.required";

	/**
	 * Perform validation.
	 *
	 * @param validationContext The validation context.
	 * @param productPlanTemplateGroupCreateRequest The {@link ProductPlanTemplateGroupCreateRequest} to validate.
	 */
	protected void performValidation(ValidationContext validationContext,
			ProductPlanTemplateGroupCreateRequest productPlanTemplateGroupCreateRequest)
	{

	}

	/**
	 * Validate the {@link ProductPlanTemplateGroupCreateRequest} object.
	 *
	 * @param validationContext The validation context.
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		ProductPlanTemplateGroupCreateRequest productPlanTemplateGroupCreateRequest =
				(ProductPlanTemplateGroupCreateRequest)validationContext
						.getObjectToBeValidated(ProductPlanTemplateGroupCreateRequest.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(productPlanTemplateGroupCreateRequest))
		{
			validationContext.getMessages().add(
					new MessageInfo(PRODUCT_PLAN_TEMPLATE_GROUP_CREATE_REQUEST_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			validationContext.setStopProcessing(true);
			return;
		}

		// Validate the specific items for a product plan template group create request.
		performValidation(validationContext, productPlanTemplateGroupCreateRequest);
	}
}
