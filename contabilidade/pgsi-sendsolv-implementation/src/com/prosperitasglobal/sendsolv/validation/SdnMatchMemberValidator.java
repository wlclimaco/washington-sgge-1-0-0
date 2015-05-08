package com.prosperitasglobal.sendsolv.validation;

import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class SdnMatchValidator.
 */
public class SdnMatchMemberValidator extends PersonValidator
{
	private static final String ERROR_IF_NULL = "errorIfNull";

	/** The Constant PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCH_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_REQUEST_REQUIRED =
			"sendsolv.base.sdnmatchvalidator.request.required";

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		// By default, error out if there is no object
		boolean errorIfNull = true;

		SdnCheckerRequest<Member> request =
				(SdnCheckerRequest<Member>)validationContext.getObjectToBeValidated(Member.class
						.getSimpleName());

		// ValidationContext can override default behavior
		if (!ValidationUtil.isNull(validationContext.getValidationArguments().get(ERROR_IF_NULL)))
		{
			errorIfNull = (boolean)validationContext.getValidationArguments().get(ERROR_IF_NULL);
		}

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(request))
		{
			if (errorIfNull)
			{
				validationContext.getMessages().add(
						new MessageInfo(PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_REQUEST_REQUIRED,
								Message.MessageSeverity.Error,
								Message.MessageLevel.Field));
			}
			return;
		}

		validationContext.putValidationContextIndicator(ValidationContextIndicator.UPDATE);

		performValidation(validationContext, request.getPersonOrBusiness());
	}
}
