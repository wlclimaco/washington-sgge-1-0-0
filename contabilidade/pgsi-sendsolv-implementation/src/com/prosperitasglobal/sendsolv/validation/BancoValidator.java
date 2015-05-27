package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Empresa;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * EmpresaValidator is the class that handles all validation for the organization. It is supposed to be used in tandem
 * with the BusinessValidator, so
 * BusinessVallidator takes care of the common fields between Organization and Empresa.
 * This class focuses on validating these fields by checking for Null and NullOrEmpty. The class utilizes the
 * ValidationUtil tool of the QAT framework to perform these checks. If any one of these fields is null or nullOrEmpty
 * an error message is set informing the user that the field is required.
 */
public class BancoValidator extends ChangeControlValidator implements IValidator
{

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_PARENTID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_PARENTID_REQUIRED =
			"sendsolv.base.empresavalidator.parentorganization.required";

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_LOCATION_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_LOCATION_REQUIRED =
			"sendsolv.base.empresavalidator.empresa.required";

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
		Empresa empresa =
				(Empresa)validationContext.getObjectToBeValidated(Empresa.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(empresa))
		{
			validationContext.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_LOCATION_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}

		// performValidation(validationContext, empresa);

		if (!validationContext.isStopProcessing()
				&& !ValidationContextIndicator.FETCH_BY_ID.equals(validationContext.getValidationContextIndicator()))
		{
			validateAdditionalFields(validationContext.getMessages(), empresa);
		}

	}

	/**
	 * Validate additional fields.
	 *
	 * @param list the list
	 * @param empresa Runs all validation methods for the Empresa.
	 */
	private void validateAdditionalFields(List<MessageInfo> list, Empresa empresa)
	{
		// validateParentOrganizationId(list, empresa);
		if (ValidationUtil.isNullOrEmpty(list))
		{
			return;
		}
		validateParentStatus(list, empresa);
	}

	/**
	 * Validate parent status.
	 *
	 * @param list the list
	 * @param empresa the empresa
	 */
	private void validateParentStatus(List<MessageInfo> list, Empresa empresa)
	{
		InternalResponse applyStatusResponse = new InternalResponse();
		FetchByIdRequest request = new FetchByIdRequest();

		// request.setId(empresa.getParentOrganizationId());
		// InternalResultsResponse<Organization> response = getOrganizationDAC().fetchOrganizationById(request);

		// if (response.isInError())
		// {
		// applyStatusResponse.merge(response);
		// }

		// if (StatusEnum.SETUP.equals(response.getFirstResult().getStatus()))
		// {
		// list.add(
		// new MessageInfo(CBOF_BASE_APPLYSTATUS_STATUS_DENIED,
		// Message.MessageSeverity.Error,
		// Message.MessageLevel.Field,
		// new Object[] {String.valueOf(BusinessTypeEnum.LOCATION).toLowerCase(),
		// empresa.getStatus()}));
		// }

		if (!ValidationUtil.isNull(applyStatusResponse))
		{
			list.addAll(applyStatusResponse.getMessageInfoList());
		}
	}

}
