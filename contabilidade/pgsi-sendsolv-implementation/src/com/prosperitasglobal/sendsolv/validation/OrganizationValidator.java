package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.model.Organization;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * OrganizationValidator is the class that handles all validation for the organization. It is supposed to be used in
 * tandem
 * with the BusinessValidator, so BusinessVallidator takes care of the common fields between Organization and Location.
 * This class focuses on validating these fields by checking for Null and NullOrEmpty. The class utilizes the
 * ValidationUtil tool of the QAT framework to perform these checks. If any one of these fields is null or nullOrEmpty
 * an error message is set informing the user that the field is required.
 */
public class OrganizationValidator extends BusinessValidator
{

	/** The Constant PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_DBANAME_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_DBANAME_TOO_BIG =
			"sendsolv.base.organizationvalidator.dbaname.too.big";

	/** The Constant TWO_HUNDRED_FIFTY_FIVE. */
	private static final Integer TWO_HUNDRED_FIFTY_FIVE = 255;

	/** The Constant PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_NUMBEROFLOCATIONS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_NUMBEROFLOCATIONS_REQUIRED =
			"sendsolv.base.organizationvalidator.numberoflocations.required";

	/** The Constant PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_DBANAME_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_DBANAME_REQUIRED =
			"sendsolv.base.organizationvalidator.dbaname.required";

	/** The Constant PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_ISPAYROLLCENTRALIZED_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_ISPAYROLLCENTRALIZED_REQUIRED =
			"sendsolv.base.organizationvalidator.ispayrollcentralized.required";

	/** The Constant PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_ORGANIZATION_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_ORGANIZATION_REQUIRED =
			"sendsolv.base.organizationvalidator.organization.required";

	/**
	 * Switches validations depending on the validation context indicator.
	 *
	 * @param validationContext the validation context
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		Organization organization =
				(Organization)validationContext.getObjectToBeValidated(Organization.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(organization))
		{
			validationContext.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_ORGANIZATION_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}

		performValidation(validationContext, organization);

		if (!validationContext.isStopProcessing()
				&& !ValidationContextIndicator.FETCH_BY_ID.equals(validationContext.getValidationContextIndicator()))
		{
			validateAdditionalFields(validationContext.getMessages(), organization);
		}
	}

	/**
	 * Validate additional fields.
	 *
	 * @param list the list
	 * @param organization Runs all validation methods for the organization.
	 */
	private void validateAdditionalFields(List<MessageInfo> list, Organization organization)
	{
		validateIsPayrollCentralized(list, organization);
		validateDbaName(list, organization);
		validateNumberOfLocations(list, organization);
	}

	/**
	 * Checks if the NumberOfLocations is null, if true then sets a NumberOfLocations required message.
	 *
	 * @param list the list
	 * @param organization the organization
	 */
	private void validateNumberOfLocations(List<MessageInfo> list, Organization organization)
	{
		ValidationUtil.isNullOrZero(organization.getNumberOfLocations(),
				PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_NUMBEROFLOCATIONS_REQUIRED, list);
	}

	/**
	 * Checks if the DbaName is null, if true then sets a DbaName required message.
	 *
	 * @param list the list
	 * @param organization the organization
	 */
	private void validateDbaName(List<MessageInfo> list, Organization organization)
	{
		if (ValidationUtil.isNull(organization.getDbaName()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_DBANAME_REQUIRED,
					Message.MessageSeverity.Error, Message.MessageLevel.Field));
			return;
		}

		if (organization.getDbaName().length() > TWO_HUNDRED_FIFTY_FIVE)
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_DBANAME_TOO_BIG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}

	}

	/**
	 * Checks if the IsPayrollCentralized is null, if true then sets a IsPayrollCentralized required message.
	 *
	 * @param list the list
	 * @param organization the organization
	 */
	private void validateIsPayrollCentralized(List<MessageInfo> list, Organization organization)
	{
		ValidationUtil.isNull(organization.getIsPayrollCentralized(),
				PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_ISPAYROLLCENTRALIZED_REQUIRED, list);
	}
}
