package com.prosperitasglobal.cbof.validation;

import java.util.List;

import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.sendsolv.validation.ChangeControlValidator;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class NoteListValidator.
 */
public class NoteListValidator extends ChangeControlValidator implements IValidator
{

	/** The Constant PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_REQUIRED =
			"sendsolv.base.notevalidator.notetext.required";
	/** The Constant NOTE_LIST_KEY. */
	private static final String NOTE_LIST_KEY = "NoteList";

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void validate(ValidationContext validationContext)
	{
		List<Note> noteList = (List<Note>)validationContext.getObjectToBeValidated(NOTE_LIST_KEY);

		if (ValidationUtil.isNullOrEmpty(noteList))
		{
			return;
		}

		for (Note note : noteList)
		{
			if (ValidationContextIndicator.FETCH_BY_ID.equals(validationContext.getValidationContextIndicator()))
			{
				validateNoteText(validationContext.getMessages(), note);
			}

			if (!ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
			{
				validateChangeControlFields(validationContext.getMessages(), note, validationContext);
			}
		}

		validationContext.getObjectsToBeValidated().put(NOTE_LIST_KEY, noteList);
	}

	/**
	 * Validate note text.
	 *
	 * @param list the list
	 * @param note the note
	 */
	private void validateNoteText(List<MessageInfo> list, Note note)
	{
		ValidationUtil.isNullOrEmpty(note.getNoteText(), PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_REQUIRED, list);
	}
}
