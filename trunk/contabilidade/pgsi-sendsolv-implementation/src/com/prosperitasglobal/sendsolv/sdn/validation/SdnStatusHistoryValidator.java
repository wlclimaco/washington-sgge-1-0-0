package com.prosperitasglobal.sendsolv.sdn.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchTypeEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryRequest;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

public class SdnStatusHistoryValidator implements IValidator
{

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_ID_REQUIRED =
			"sendsolv.base.sdnmatchvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCH_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNHISTORY_REQUIRED =
			"sendsolv.base.sdnmatchvalidator.sdnhistory.required";

	/** The Constant PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCHTYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCHTYPE_REQUIRED =
			"sendsolv.base.sdnmatchvalidator.sdnmatchtype.required";

	/** The Constant PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCHTYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_HISTORY_ID_REQUIRED =
			"sendsolv.base.sdnmatchvalidator.historyid.required";

	/** The Constant PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCHTYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_HISTORY_STATUS_REQUIRED =
			"sendsolv.base.sdnmatchvalidator.historystatus.required";

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		SdnStatusHistoryRequest request =
				(SdnStatusHistoryRequest)validationContext.getObjectToBeValidated(SdnStatusHistoryRequest.class
						.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(request))
		{
			validationContext.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNHISTORY_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}

		if (ValidationUtil.isNull(request.getSdnStatusHistory()))
		{
			validationContext.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNHISTORY_REQUIRED,
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
	private void performValidation(ValidationContext validationContext, SdnStatusHistoryRequest request)
	{
		switch (validationContext.getValidationContextIndicator())
		{
			case FETCH_BY_ID:
				validateMatchType(validationContext.getMessages(), request.getMatchType());
				validateParentKey(validationContext.getMessages(), request.getSdnStatusHistory().getParentKey());
				break;
			case UPDATE:
				validateId(validationContext.getMessages(), request.getSdnStatusHistory());
				validateStatus(validationContext.getMessages(), request.getSdnStatusHistory());
				break;
			default:
				break;
		}
	}

	/**
	 * Validate business id.
	 *
	 * @param messages the messages
	 * @param sdnMatch the sdn match
	 */
	private void validateParentKey(List<MessageInfo> messages, Integer parentKey)
	{
		ValidationUtil.isNull(parentKey, PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_ID_REQUIRED,
				messages);
	}

	private void validateMatchType(List<MessageInfo> messages, SdnMatchTypeEnum sdnMatchTypeEnum)
	{
		ValidationUtil.isNull(sdnMatchTypeEnum, PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCHTYPE_REQUIRED,
				messages);
	}

	private void validateId(List<MessageInfo> messages, SdnStatusHistory sdnStatusHistory)
	{
		ValidationUtil.isNull(sdnStatusHistory.getId(), PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_HISTORY_ID_REQUIRED,
				messages);
	}

	private void validateStatus(List<MessageInfo> messages, SdnStatusHistory sdnStatusHistory)
	{
		ValidationUtil.isNull(sdnStatusHistory.getSdnStatus(),
				PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_HISTORY_STATUS_REQUIRED, messages);
	}
}
