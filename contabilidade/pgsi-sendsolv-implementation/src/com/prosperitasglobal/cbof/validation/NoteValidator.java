package com.prosperitasglobal.cbof.validation;

import java.util.List;

import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.sendsolv.validation.ChangeControlValidator;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
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
public class NoteValidator extends ChangeControlValidator implements IValidator
{

	/** The Constant PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_TOO_BIG =
			"sendsolv.base.notevalidator.notetext.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_ID_REQUIRED =
			"sendsolv.base.base.notevalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_PARENTKEYTYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_PARENTKEYTYPE_REQUIRED =
			"sendsolv.base.notevalidator.parentkeytype.required";

	/** The Constant PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_PARENTKEY_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_PARENTKEY_REQUIRED =
			"sendsolv.base.notevalidator.parentkey.required";

	/** The Constant PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_REQUIRED =
			"sendsolv.base.notevalidator.notetext.required";

	/** The Constant PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTE_REQUIRED =
			"sendsolv.base.notevalidator.note.required";

	/** The Constant TWO_HUNDRED_FIFTY_FIVE. */
	private static final Integer TWO_HUNDRED_FIFTY_FIVE = 255;

	/**
	 * Switches validations depending on the validation context indicator.
	 *
	 * @param validationContext the validation context
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		Note note =
				(Note)validationContext.getObjectToBeValidated(Note.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(note))
		{
			validationContext.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTE_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}

		switch (validationContext.getValidationContextIndicator())
		{
			case DELETE:
				validateId(validationContext.getMessages(), note);
				break;
			case INSERT:
				validateAll(validationContext.getMessages(), note);
				break;
			case UPDATE:
				validateId(validationContext.getMessages(), note);
				validateAll(validationContext.getMessages(), note);
				break;
			default:
				validateAll(validationContext.getMessages(), note);
				break;
		}

		if (!ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
		{
			validateChangeControlFields(validationContext.getMessages(), note, validationContext);
		}

	}

	/**
	 * Validate all.
	 *
	 * @param list the list
	 * @param note the note
	 */
	private void validateAll(List<MessageInfo> list, Note note)
	{
		validateNoteText(list, note);
		validateParentKey(list, note);
		validateParentKeyType(list, note);
	}

	/**
	 * Checks if the NumberOfLocations is null, if true then sets a NumberOfLocations required message.
	 *
	 * @param list the list
	 * @param note the note
	 */
	private void validateParentKeyType(List<MessageInfo> list, Note note)
	{
		ValidationUtil.isNull(note.getParentKeyType(),
				PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_PARENTKEYTYPE_REQUIRED, list);
	}

	/**
	 * Checks if the DbaName is null, if true then sets a DbaName required message.
	 *
	 * @param list the list
	 * @param note the note
	 */
	private void validateParentKey(List<MessageInfo> list, Note note)
	{
		ValidationUtil.isNullOrZero(note.getParentKey(),
				PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_PARENTKEY_REQUIRED, list);
	}

	/**
	 * Checks if the IsPayrollCentralized is null, if true then sets a IsPayrollCentralized required message.
	 *
	 * @param list the list
	 * @param note the note
	 */
	private void validateNoteText(List<MessageInfo> list, Note note)
	{
		if (ValidationUtil.isNull(note.getNoteText()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_REQUIRED,
					Message.MessageSeverity.Error, Message.MessageLevel.Field));
			return;
		}

		if (note.getNoteText().length() > TWO_HUNDRED_FIFTY_FIVE)
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_TOO_BIG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate id.
	 *
	 * @param list the list
	 * @param note the note
	 */
	private void validateId(List<MessageInfo> list, Note note)
	{
		ValidationUtil.isNullOrZero(note.getId(),
				PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_ID_REQUIRED, list);
	}

}
