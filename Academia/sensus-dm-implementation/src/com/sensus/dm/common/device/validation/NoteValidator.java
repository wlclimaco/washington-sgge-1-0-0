package com.sensus.dm.common.device.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import com.sensus.cbof.model.Note;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;

/**
 * The Class NoteValidator.
 * 
 * @author QAT Global.
 */
public class NoteValidator implements IValidator
{

	/** The Constant TEXT_REQUIRED. */
	private static final String TEXT_REQUIRED = "sensus.epm.notevalidator.meternotetext.required";

	/** The Constant ID_REQUIRED. */
	private static final String ID_REQUIRED = "sensus.epm.notevalidator.meternoteid.required";

	/** The Constant FLEXNETID_REQUIRED. */
	private static final String FLEXNETID_REQUIRED = "sensus.epm.devicevalidator.meterflexnetid.required";

	/** The Constant TEXT_INVALID. */
	private static final String TEXT_INVALID = "sensus.epm.notevalidator.note.text.invalid";

	/** The Constant NOTE_REQUIRED. */
	private static final String NOTE_REQUIRED = "sensus.epm.notevalidator.note.required";

	/** The text length. */
	private int textLength;

	/**
	 * Gets the text length.
	 * 
	 * @return the text length
	 */
	public int getTextLength()
	{
		return textLength;
	}

	/**
	 * Sets the text length.
	 * 
	 * @param textLength the new text length
	 */
	public void setTextLength(int textLength)
	{
		this.textLength = textLength;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{

		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		if (isNull(action))
		{
			return;
		}

		Note note = (Note)validationContext.getObjectToBeValidated(Note.class.getSimpleName());

		if (isNull(note))
		{
			isNull(note, NOTE_REQUIRED, validationContext.getMessages());
			return;
		}

		switch (action)
		{
			case INSERT_METER_NOTES:
				validateNoteFlexnetId(validationContext.getMessages(), note);
				validateNoteText(validationContext.getMessages(), note);
				break;
			case DELETE_METER_NOTES:
				validateNoteId(validationContext.getMessages(), note);
				break;
			case FETCH_DEVICE_NOTES:
				validateNoteFlexnetId(validationContext.getMessages(), note);
				break;
			default:
				break;
		}
	}

	/**
	 * Validate note flexnet id.
	 * 
	 * @param list the list
	 * @param note the note
	 */
	private void validateNoteFlexnetId(List<MessageInfo> list, Note note)
	{
		ValidationUtil.isNull(note.getFlexNetId(), FLEXNETID_REQUIRED, list);
	}

	/**
	 * Validate note id.
	 * 
	 * @param list the list
	 * @param note the note
	 */
	private void validateNoteId(List<MessageInfo> list, Note note)
	{
		ValidationUtil.isNull(note.getId(), ID_REQUIRED, list);
	}

	/**
	 * Validate note text.
	 * 
	 * @param list the list
	 * @param note the note
	 */
	private void validateNoteText(List<MessageInfo> list, Note note)
	{
		ValidationUtil.isNullOrEmpty(note.getText(), TEXT_REQUIRED, list);

		if (!ValidationUtil.isNull(note.getText()) && (note.getText().length() > getTextLength()))
		{
			list.add(MessageInfo.createFieldValidationError(TEXT_INVALID, getTextLength()));
		}
	}

}
