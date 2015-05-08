package com.prosperitasglobal.sendsolv.sdn.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchTypeEnum;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryInquiryRequest;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class SdnHistoryValidator.
 */
public class SdnHistoryValidator implements IValidator
{

	/** The Constant PROSPERITASGLOBAL_BASE_SDNHISTORYVALIDATOR_CRITERIA_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_SDNHISTORYVALIDATOR_CRITERIA_REQUIRED =
			"sendsolv.base.sdnhistoryvalidator.criteria.required";

	/** The Constant PROSPERITASGLOBAL_BASE_SDNHISTORYVALIDATOR_REQUEST_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_SDNHISTORYVALIDATOR_REQUEST_REQUIRED =
			"sendsolv.base.sdnhistoryvalidator.request.required";

	/** The Constant PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCHTYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCHTYPE_REQUIRED =
			"sendsolv.base.sdnhistoryvalidator.sdnmatchtype.required";

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		SdnStatusHistoryInquiryRequest request =
				(SdnStatusHistoryInquiryRequest)validationContext
						.getObjectToBeValidated(SdnStatusHistoryInquiryRequest.class
								.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(request))
		{
			validationContext.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_SDNHISTORYVALIDATOR_REQUEST_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}

		if (ValidationUtil.isNull(request.getCriteria()))
		{
			validationContext.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_SDNHISTORYVALIDATOR_CRITERIA_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}

		performValidation(validationContext, request);
	}

	/**
	 * Perform validation.
	 *
	 * @param validationContext the validation context
	 * @param sdnMatch the sdn match
	 */
	private void performValidation(ValidationContext validationContext, SdnStatusHistoryInquiryRequest request)
	{
		switch (validationContext.getValidationContextIndicator())
		{
			case FETCH:
				validateMatchType(validationContext.getMessages(), request.getCriteria().getMatchType());
				break;
			default:
				break;
		}
	}

	/**
	 * Validate match type.
	 *
	 * @param messages the messages
	 * @param sdnMatchTypeEnum the sdn match type enum
	 */
	private void validateMatchType(List<MessageInfo> messages, SdnMatchTypeEnum sdnMatchTypeEnum)
	{
		ValidationUtil.isNull(sdnMatchTypeEnum, PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCHTYPE_REQUIRED,
				messages);
	}

}
