package com.qat.samples.sysmgmt.validation;

import java.util.List;

import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.model.Drug;

/**
 * The Class DrugValidator.
 */
public class DrugValidator implements IValidator
{
	private static final String SYSMGMT_BASE_DRUGVALIDATOR_NDCDESC_REQUIRED =
			"sysmgmt.base.drugvalidator.ndcdesc.required";
	private static final String SYSMGMT_BASE_DRUGVALIDATOR_NDCCODE_REQUIRED =
			"sysmgmt.base.drugvalidator.ndccode.required";
	private static final String SYSMGMT_BASE_DRUGVALIDATOR_DRUGPRICES_REQUIRED =
			"sysmgmt.base.drugvalidator.drugprices.required";
	private static final String DRUG_PRICES_KEY = "DrugPrices";

	@Override
	public void validate(ValidationContext validationContext)
	{
		Drug drug =
				(Drug)validationContext.getObjectToBeValidated(Drug.class.getSimpleName());

		switch (validationContext.getValidationContextIndicator())
		{
			case DELETE:
				validateNdcCode(validationContext.getMessages(), drug);
				break;
			case INSERT:
				validateAll(validationContext.getMessages(), drug);
				break;
			case UPDATE:
				validateAll(validationContext.getMessages(), drug);
				break;
			default:
				validateAll(validationContext.getMessages(), drug);
				break;
		}
		// DrugPrices(List) Object so the DrugPriceValidator has it in context for better re-use
		validationContext.getObjectsToBeValidated().put(DRUG_PRICES_KEY, drug.getDrugPrices());
	}

	private void validateAll(List<MessageInfo> list, Drug drug)
	{
		validateNdcCode(list, drug);
		validateNdcDesc(list, drug);
		validateDrugPrices(list, drug);
	}

	private void validateDrugPrices(List<MessageInfo> list, Drug drug)
	{
		ValidationUtil.isNullOrEmpty(drug.getDrugPrices(),
				SYSMGMT_BASE_DRUGVALIDATOR_DRUGPRICES_REQUIRED, list);
	}

	private void validateNdcCode(List<MessageInfo> list, Drug drug)
	{
		ValidationUtil.isNullOrEmpty(drug.getCode(), SYSMGMT_BASE_DRUGVALIDATOR_NDCCODE_REQUIRED, list);
	}

	private void validateNdcDesc(List<MessageInfo> list, Drug drug)
	{
		ValidationUtil.isNullOrEmpty(drug.getDescription(),
				SYSMGMT_BASE_DRUGVALIDATOR_NDCDESC_REQUIRED, list);
	}
}
