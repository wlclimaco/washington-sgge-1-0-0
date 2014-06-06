package com.qat.samples.sysmgmt.validation;

import java.util.List;

import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.model.Bundle;

/**
 * The Class BundleDValidator.
 */
public class ProdutoValidator implements IValidator
{

	private static final String SYSMGMT_BASE_BUNDLEVALIDATOR_PROCDESC_REQUIRED =
			"sysmgmt.base.bundlevalidator.bundledesc.required";
	private static final String SYSMGMT_BASE_BUNDLEVALIDATOR_PROCCODE_REQUIRED =
			"sysmgmt.base.bundlevalidator.bundlecode.required";
	private static final String SYSMGMT_BASE_BUNDLEVALIDATOR_ID_REQUIRED =
			"sysmgmt.base.bundlevalidator.id.required";

	@Override
	public void validate(ValidationContext validationContext)
	{
		Bundle bundle = (Bundle)validationContext.getObjectToBeValidated(Bundle.class.getSimpleName());

		switch (validationContext.getValidationContextIndicator())
		{
			case DELETE:
				validateProcId(validationContext.getMessages(), bundle);
				break;
			case UPDATE:
				validateProcId(validationContext.getMessages(), bundle);
				validateProcCode(validationContext.getMessages(), bundle);
				validateProcDesc(validationContext.getMessages(), bundle);
				break;
			default:
				validateProcCode(validationContext.getMessages(), bundle);
				validateProcDesc(validationContext.getMessages(), bundle);
				break;
		}
	}

	private void validateProcId(List<MessageInfo> list, Bundle bundle)
	{
		ValidationUtil.isNullOrZero(bundle.getId(), SYSMGMT_BASE_BUNDLEVALIDATOR_ID_REQUIRED, list);
	}

	private void validateProcCode(List<MessageInfo> list, Bundle bundle)
	{
		ValidationUtil.isNullOrEmpty(bundle.getCode(), SYSMGMT_BASE_BUNDLEVALIDATOR_PROCCODE_REQUIRED, list);
	}

	private void validateProcDesc(List<MessageInfo> list, Bundle bundle)
	{
		ValidationUtil.isNullOrEmpty(bundle.getDescription(), SYSMGMT_BASE_BUNDLEVALIDATOR_PROCDESC_REQUIRED,
				list);
	}
}
