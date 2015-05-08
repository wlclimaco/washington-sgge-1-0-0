package com.prosperitasglobal.sendsolv.validation;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.PersonTypeEnum;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class RecipientValidator.
 */
public class RecipientValidator extends PersonValidator implements IValidator
{

	/** The Constant CBOF_BASE_APPLYSTATUS_STATUS_DENIED. */
	private static final String CBOF_BASE_APPLYSTATUS_STATUS_DENIED = "cbof.base.applystatus.status.denied";

	/** The Constant PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_RECIPIENT_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_RECIPIENT_REQUIRED =
			"sendsolv.base.recipientvalidator.recipient.required";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.validation.PersonValidator#validate(com.qat.framework.validation.ValidationContext
	 * )
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		Recipient recipient =
				(Recipient)validationContext.getObjectToBeValidated(Recipient.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(recipient))
		{
			validationContext.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_RECIPIENT_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}

		performValidation(validationContext, recipient);

		validateAdditionalFields(validationContext, recipient);
	}

	/**
	 * Validate additional fields.
	 *
	 * @param messages the messages
	 * @param recipient the recipient
	 */
	private void validateAdditionalFields(ValidationContext context, Recipient recipient)
	{
		if (!ValidationContextIndicator.FETCH_BY_ID.equals(context.getValidationContextIndicator()))
		{
			return;
		}

		InternalResponse applyStatusResponse = new InternalResponse();
		FetchByIdRequest fetchByIdRequest = new FetchByIdRequest();

		for (TransferSetting transferSetting : recipient.getTransferSettingList())
		{
			fetchByIdRequest.setId(transferSetting.getMemberId());
			InternalResultsResponse<Member> response = getPersonDAC().fetchMemberById(fetchByIdRequest);

			if (response.isInError())
			{
				applyStatusResponse.merge(response);
				break;
			}

			if (StatusEnum.SETUP.equals(response.getFirstResult().getPersonStatus()))
			{
				context.getMessages().add(
						new MessageInfo(CBOF_BASE_APPLYSTATUS_STATUS_DENIED,
								Message.MessageSeverity.Error,
								Message.MessageLevel.Field,
								new Object[] {String.valueOf(PersonTypeEnum.RECIPIENT).toLowerCase(),
								recipient.getPersonStatus()}));
				return;
			}
		}

		if (!ValidationUtil.isNull(applyStatusResponse))
		{
			context.getMessages().addAll(applyStatusResponse.getMessageInfoList());
		}
	}

}
