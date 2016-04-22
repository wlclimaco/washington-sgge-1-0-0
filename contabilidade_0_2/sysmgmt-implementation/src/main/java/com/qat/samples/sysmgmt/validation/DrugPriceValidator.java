package com.qat.samples.sysmgmt.validation;

import java.util.List;

import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.MessageLevel;
import com.qat.framework.model.MessageSeverity;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.model.DrugPrice;

/**
 * The Class DrugPriceValidator.
 */
public class DrugPriceValidator implements IValidator
{
	private static final String SYSMGMT_BASE_DRUGPRICEVALIDATOR_PRICE_REQUIRED =
			"sysmgmt.base.drugpricevalidator.price.required";
	private static final String SYSMGMT_BASE_DRUGPRICEVALIDATOR_SEXIND_REQUIRED =
			"sysmgmt.base.drugpricevalidator.sexind.required";
	private static final String SYSMGMT_BASE_DRUGPRICEVALIDATOR_DATE_REQUIRED =
			"sysmgmt.base.drugpricevalidator.date.required";
	private static final String SYSMGMT_BASE_MOA_REQUIRED = "sysmgmt.base.moa.required";
	private static final String DRUG_PRICES_KEY = "DrugPrices";
	private List<DrugPrice> drugPrices;

	@Override
	public void validate(ValidationContext validationContext)
	{
		drugPrices = (List<DrugPrice>)validationContext.getObjectToBeValidated(DRUG_PRICES_KEY);
		if (!ValidationUtil.isNullOrEmpty(drugPrices))
		{
			for (DrugPrice drugPrice : drugPrices)
			{
				validateDrugPrice(drugPrice, validationContext);
			}
		}
	}

	private void validateDrugPrice(DrugPrice drugPrice, ValidationContext context)
	{
		switch (context.getValidationContextIndicator())
		{
			case INSERT:
				validateAll(context.getMessages(), drugPrice);
				break;
			case UPDATE:
				validateAll(context.getMessages(), drugPrice);
				break;
			case DELETE:
				validateModelAction(context.getMessages(), drugPrice);
				validatePriceSexInd(context.getMessages(), drugPrice);
				break;
			default:
				break;
		}
	}

	private void validateAll(List<MessageInfo> list, DrugPrice drugPrice)
	{
		validateModelAction(list, drugPrice);
		validateEffectiveDate(list, drugPrice);
		validatePrice(list, drugPrice);
		validatePriceSexInd(list, drugPrice);
	}

	private void validateModelAction(List<MessageInfo> list, DrugPrice drugPrice)
	{
		ValidationUtil.isNull(drugPrice.getModelAction(), SYSMGMT_BASE_MOA_REQUIRED, list);
	}

	private void validateEffectiveDate(List<MessageInfo> list, DrugPrice drugPrice)
	{
		if (ValidationUtil.isNull(drugPrice.getEffectiveDateUTC()))
		{
			list.add(new MessageInfo(SYSMGMT_BASE_DRUGPRICEVALIDATOR_DATE_REQUIRED, MessageSeverity.Error,
					MessageLevel.Field));
		}
	}

	private void validatePriceSexInd(List<MessageInfo> list, DrugPrice drugPrice)
	{
		if (ValidationUtil.isNull(drugPrice.getPriceSexIndicator()))
		{
			list.add(new MessageInfo(SYSMGMT_BASE_DRUGPRICEVALIDATOR_SEXIND_REQUIRED, MessageSeverity.Error,
					MessageLevel.Field));
		}
	}

	private void validatePrice(List<MessageInfo> list, DrugPrice drugPrice)
	{
		ValidationUtil.isNull(drugPrice.getDrugPrice(), SYSMGMT_BASE_DRUGPRICEVALIDATOR_PRICE_REQUIRED, list);
	}

}
