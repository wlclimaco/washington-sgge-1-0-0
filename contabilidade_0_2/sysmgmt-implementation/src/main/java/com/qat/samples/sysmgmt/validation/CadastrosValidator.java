package com.qat.samples.sysmgmt.validation;

import java.util.List;

import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.model.County;

/**
 * The Class CountyValidator.
 */
public class CadastrosValidator implements IValidator
{
	private static final String SYSMGMT_BASE_COUNTYVALIDATOR_DESC_LENGTH_INVALID =
			"sysmgmt.base.countyvalidator.desc.length.invalid";
	private static final String SYSMGMT_BASE_COUNTYVALIDATOR_DESC_REQUIRED =
			"sysmgmt.base.countyvalidator.desc.required";
	private static final String SYSMGMT_BASE_COUNTYVALIDATOR_ID_REQUIRED = "sysmgmt.base.countyvalidator.id.required";

	private int descriptionLength = 50;

	public int getDescriptionLength()
	{
		return descriptionLength;
	}

	public void setDescriptionLength(int descriptionLength)
	{
		this.descriptionLength = descriptionLength;
	}

	@Override
	public void validate(ValidationContext validationContext)
	{
		County county =
				(County)validationContext.getObjectToBeValidated(County.class.getSimpleName());

		switch (validationContext.getValidationContextIndicator())
		{

			case INSERT:
				validateAll(validationContext.getMessages(), county);
				break;
			case UPDATE:
				validateAll(validationContext.getMessages(), county);
				break;
			case DELETE:
				validateCountyId(validationContext.getMessages(), county);
				break;
			default:
				validateAll(validationContext.getMessages(), county);
				break;
		}
	}

	private void validateAll(List<MessageInfo> list, County county)
	{
		validateCountyId(list, county);
		validateCountyDesc(list, county);
	}

	private void validateCountyId(List<MessageInfo> list, County county)
	{
		ValidationUtil.isNullOrZero(county.getId(), SYSMGMT_BASE_COUNTYVALIDATOR_ID_REQUIRED, list);
	}

	private void validateCountyDesc(List<MessageInfo> list, County county)
	{
		ValidationUtil.isNullOrEmpty(county.getDescription(),
				SYSMGMT_BASE_COUNTYVALIDATOR_DESC_REQUIRED, list);

		ValidationUtil.isValidLength(county.getDescription(), getDescriptionLength(),
				SYSMGMT_BASE_COUNTYVALIDATOR_DESC_LENGTH_INVALID, list);
	}
}
