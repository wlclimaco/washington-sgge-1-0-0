package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.model.Liaison;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class LiaisonValidator.
 */
public class LiaisonValidator extends PersonValidator implements IValidator
{

	/** The Constant EIGHTY. */
	private static final Integer EIGHTY = 80;

	/** The Constant CONTACT_LIST_KEY. */
	protected static final String CONTACT_LIST_KEY = "ContactList";

	/** The Constant NOTE_LIST_KEY. */
	protected static final String NOTE_LIST_KEY = "NoteList";

	/** The Constant NAME_LIST_KEY. */
	protected static final String NAME_LIST_KEY = "NameList";

	/** The Constant DOCUMENT_LIST_KEY. */
	protected static final String DOCUMENT_LIST_KEY = "DocumentList";

	/** The Constant PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LIAISON_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LIAISON_REQUIRED =
			"sendsolv.base.liaisonvalidator.liaison.required";

	/** The Constant PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LIAISON_TYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LIAISON_TYPE_REQUIRED =
			"sendsolv.base.liaisonvalidator.pepstatus.required";

	/** The Constant PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LOCATION_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LOCATION_ID_REQUIRED =
			"sendsolv.base.liaisonvalidator.location.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_TITLE_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_TITLE_TOO_BIG =
			"sendsolv.base.liaisonvalidator.title.too.big";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.validation.PersonValidator#validate(com.qat.framework.validation.ValidationContext
	 * )
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		Liaison liaison =
				(Liaison)validationContext.getObjectToBeValidated(Liaison.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(liaison))
		{
			validationContext.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LIAISON_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}

		performValidation(validationContext, liaison);

		validateAdditionalFields(validationContext.getMessages(), liaison);
	}

	/**
	 * Validate additional fields.
	 *
	 * @param messages the messages
	 * @param liaison the liaison
	 */
	private void validateAdditionalFields(List<MessageInfo> messages, Liaison liaison)
	{
		validateLiaisonType(messages, liaison);
		validateTitle(messages, liaison);
		validateLocationId(messages, liaison);
	}

	/**
	 * Validate location id.
	 *
	 * @param messages the messages
	 * @param liaison the liaison
	 */
	private void validateLocationId(List<MessageInfo> messages, Liaison liaison)
	{
		ValidationUtil.isNullOrZero(liaison.getLiaisonTypeValue(),
				PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LOCATION_ID_REQUIRED, messages);
	}

	/**
	 * Validate title.
	 *
	 * @param messages the messages
	 * @param liaison the liaison
	 */
	private void validateTitle(List<MessageInfo> messages, Liaison liaison)
	{
		if (!ValidationUtil.isNullOrEmpty(liaison.getTitle()))
		{
			if (liaison.getTitle().length() > EIGHTY)
			{
				messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_TITLE_TOO_BIG,
						Message.MessageSeverity.Error,
						Message.MessageLevel.Field));
			}
		}
	}

	/**
	 * Validate liaison type.
	 *
	 * @param messages the messages
	 * @param liaison the liaison
	 */
	private void validateLiaisonType(List<MessageInfo> messages, Liaison liaison)
	{
		ValidationUtil.isNullOrZero(liaison.getLiaisonTypeValue(),
				PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LIAISON_TYPE_REQUIRED, messages);
	}

}
