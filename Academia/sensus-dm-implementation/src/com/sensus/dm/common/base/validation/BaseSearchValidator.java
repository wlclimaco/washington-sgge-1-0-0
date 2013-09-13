package com.sensus.dm.common.base.validation;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.BaseSearch;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;

/**
 * The Class BaseSearchValidator.
 *
 * @author QAT Brazil
 */
public class BaseSearchValidator implements IValidator
{

	/** The Constant TEXT_INVALID. */
	private static final String TEXT_INVALID = "sensus.epm.basesearchvalidator.text.invalid";

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
		BaseSearch baseSearch =
				(BaseSearch)validationContext.getObjectToBeValidated(BaseSearch.class.getSimpleName());

		// base search can be null, isn't necessary validation message
		if (ValidationUtil.isNull(baseSearch))
		{
			return;
		}

		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		if (ValidationUtil.isNull(action))
		{
			return;
		}

		switch (action)
		{
			case GENERATE_GROUP_FILE_CSV:
			case FETCH_ALL_GROUPS:
				validateSearchTextLenght(validationContext.getMessages(), baseSearch);
				break;
			default:
				break;
		}
	}

	/**
	 * Validate search text lenght.
	 *
	 * @param list the list
	 * @param baseSearch the base search
	 */
	private void validateSearchTextLenght(List<MessageInfo> list, BaseSearch baseSearch)
	{

		if (!ValidationUtil.isNull(baseSearch.getSearchText())
				&& baseSearch.getSearchText().length() > getNameLength())
		{
			list.add(MessageInfo.createFieldValidationError(TEXT_INVALID, getNameLength()));
		}
	}
}
