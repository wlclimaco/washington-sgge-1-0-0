package com.sensus.dm.common.tag.validation;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.tag.model.Tag;

/**
 * The Class TagValidator.
 * 
 * @author QAT Global
 */
public class TagValidator implements IValidator
{
	/** The Constant NAME_INVALID. */
	private static final String NAME_INVALID = "sensus.dm.common.tagvalidator.name.invalid";

	/** The Constant NAME_REQUIRED. */
	private static final String NAME_REQUIRED = "sensus.dm.common.tagvalidator.name.required";

	/** The Constant ID_REQUIRED. */
	private static final String ID_REQUIRED = "sensus.dm.common.tagvalidator.id.required";

	/** The Constant TAG_REQUIRED. */
	private static final String TAG_REQUIRED = "sensus.dm.common.tagvalidator.tag.required";

	/** The Constant TAG_NAME_OR_ID_REQUIRED. */
	private static final String TAG_NAME_OR_ID_REQUIRED = "sensus.dm.common.tagvalidator.name.id.required";

	/** The name length. */
	private int nameLength;

	/**
	 * Gets the name length.
	 * 
	 * @return the name length
	 */
	public int getNameLength()
	{
		return nameLength;
	}

	/**
	 * Sets the name length.
	 * 
	 * @param nameLength the new name length
	 */
	public void setNameLength(int nameLength)
	{
		this.nameLength = nameLength;
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

		if (ValidationUtil.isNull(action))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(TAG_REQUIRED));
			return;
		}

		Tag tag = (Tag)validationContext.getObjectToBeValidated(Tag.class.getSimpleName());

		if (ValidationUtil.isNull(tag))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(ID_REQUIRED));
			return;
		}

		switch (action)
		{
			case ADD_SMP_TO_TAG:
				validateNameOrIdRequired(validationContext.getMessages(), tag);
				break;
			case DELETE:
			case DEL_SMP_FROM_TAG:
				ValidationUtil.isNullOrZero(tag.getId(), ID_REQUIRED, validationContext.getMessages());
				break;
			case INSERT:
			case INSERT_TAG:
				ValidationUtil.isNullOrEmpty(tag.getName(), NAME_REQUIRED, validationContext.getMessages());
				validateNameLength(validationContext.getMessages(), tag);
				break;
			default:
				break;
		}
	}

	/**
	 * Validate tag name length.
	 * 
	 * @param list the list
	 * @param tag the tag
	 */
	private void validateNameLength(List<MessageInfo> list, Tag tag)
	{
		if (!ValidationUtil.isNull(tag.getName()) && (tag.getName().length() > getNameLength()))
		{
			list.add(MessageInfo.createFieldValidationError(NAME_INVALID, getNameLength()));
		}
	}

	/**
	 * Validate tag name or id is required.
	 * 
	 * @param list the list
	 * @param tag the tag
	 */
	private void validateNameOrIdRequired(List<MessageInfo> list, Tag tag)
	{
		if (ValidationUtil.isNullOrZero(tag.getId()) && ValidationUtil.isNull(tag.getName()))
		{
			ValidationUtil.isNullOrZero(tag.getId(), TAG_NAME_OR_ID_REQUIRED, list);
		}
	}
}
