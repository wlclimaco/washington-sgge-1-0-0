package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * LocationValidator is the class that handles all validation for the organization. It is supposed to be used in tandem
 * with the BusinessValidator, so
 * BusinessVallidator takes care of the common fields between Organization and Location.
 * This class focuses on validating these fields by checking for Null and NullOrEmpty. The class utilizes the
 * ValidationUtil tool of the QAT framework to perform these checks. If any one of these fields is null or nullOrEmpty
 * an error message is set informing the user that the field is required.
 */
public class LocationValidator extends BusinessValidator
{

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_PARENTID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_PARENTID_REQUIRED =
			"sendsolv.base.locationvalidator.parentorganization.required";

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_LOCATION_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_LOCATION_REQUIRED =
			"sendsolv.base.locationvalidator.location.required";

	/** The Constant CBOF_BASE_APPLYSTATUS_STATUS_DENIED. */
	private static final String CBOF_BASE_APPLYSTATUS_STATUS_DENIED = "cbof.base.applystatus.status.denied";

	/**
	 * Switches validations depending on the validation context indicator.
	 *
	 * @param validationContext the validation context
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		Location location =
				(Location)validationContext.getObjectToBeValidated(Location.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(location))
		{
			validationContext.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_LOCATION_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}

		// performValidation(validationContext, location);

		if (!validationContext.isStopProcessing()
				&& !ValidationContextIndicator.FETCH_BY_ID.equals(validationContext.getValidationContextIndicator()))
		{
			validateAdditionalFields(validationContext.getMessages(), location);
		}

	}

	/**
	 * Validate additional fields.
	 *
	 * @param list the list
	 * @param location Runs all validation methods for the Location.
	 */
	private void validateAdditionalFields(List<MessageInfo> list, Location location)
	{
		validateParentOrganizationId(list, location);
		if (ValidationUtil.isNullOrEmpty(list))
		{
			return;
		}
		validateParentStatus(list, location);
	}

	/**
	 * Validate parent status.
	 *
	 * @param list the list
	 * @param location the location
	 */
	private void validateParentStatus(List<MessageInfo> list, Location location)
	{
		InternalResponse applyStatusResponse = new InternalResponse();
		FetchByIdRequest request = new FetchByIdRequest();

		request.setId(location.getParentOrganizationId());
		InternalResultsResponse<Organization> response = getOrganizationDAC().fetchOrganizationById(request);

		if (response.isInError())
		{
			applyStatusResponse.merge(response);
		}

		if (StatusEnum.SETUP.equals(response.getFirstResult().getStatus()))
		{
			list.add(
					new MessageInfo(CBOF_BASE_APPLYSTATUS_STATUS_DENIED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field,
							new Object[] {String.valueOf(BusinessTypeEnum.LOCATION).toLowerCase(),
							location.getStatus()}));
		}

		if (!ValidationUtil.isNull(applyStatusResponse))
		{
			list.addAll(applyStatusResponse.getMessageInfoList());
		}
	}

	/**
	 * Validate parent organization id.
	 *
	 * @param list the list
	 * @param location the location
	 */
	private void validateParentOrganizationId(List<MessageInfo> list, Location location)
	{
		ValidationUtil.isNullOrZero(location.getParentOrganizationId(),
				PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_PARENTID_REQUIRED, list);
	}
}
