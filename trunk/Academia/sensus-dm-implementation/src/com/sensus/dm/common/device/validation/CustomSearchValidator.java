package com.sensus.dm.common.device.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.device.model.CustomSearch;

/**
 * The Class CustomSearchRequestValidator.
 * 
 * @author QAT Brazil
 * 
 */
public class CustomSearchValidator implements IValidator
{

	/** The Constant CUSTOMSEARCH_REQUIRED. */
	private static final String CUSTOMSEARCH_REQUIRED = "sensus.epm.customsearchvalidator.customsearch.required";

	/** The Constant NAME_REQUIRED. */
	private static final String NAME_REQUIRED = "sensus.epm.customsearchvalidator.name.required";

	/** The Constant ID_REQUIRED. */
	private static final String ID_REQUIRED = "sensus.epm.customsearchvalidator.id.required";

	/** The Constant DEVICE_TYPE_ENUM_REQUIRED. */
	private static final String DEVICE_TYPE_ENUM_REQUIRED = "sensus.epm.customsearchvalidator.device.type.required";

	/** The Constant SEARCH_DESCRIPTION_INVALID. */
	private static final String SEARCH_DESCRIPTION_INVALID =
			"sensus.epm.devicevalidator.search.description.invalid";

	/** The Constant SEARCH_NAME_INVALID. */
	private static final String SEARCH_NAME_INVALID = "sensus.epm.devicevalidator.search.name.invalid";

	/** The name length. */
	private int nameLength;

	/** The description length. */
	private int descriptionLength;

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

	/**
	 * Gets the description length.
	 * 
	 * @return the description length
	 */
	public int getDescriptionLength()
	{
		return descriptionLength;
	}

	/**
	 * Sets the description length.
	 * 
	 * @param descriptionLength the new description length
	 */
	public void setDescriptionLength(int descriptionLength)
	{
		this.descriptionLength = descriptionLength;
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
			return;
		}

		List<MessageInfo> list = validationContext.getMessages();

		CustomSearch customSearch =
				(CustomSearch)validationContext.getObjectToBeValidated(CustomSearch.class.getSimpleName());

		if (isNull(customSearch))
		{
			list.add(MessageInfo.createFieldValidationError(CUSTOMSEARCH_REQUIRED));
			return;
		}

		switch (action)
		{
			case INSERT_CUSTOM_SEARCH:
				validateCustomSearchName(list, customSearch);
				validateCustomSearchDescription(list, customSearch);
				validateCustomSearchTypeEnum(list, customSearch);
				break;
			case DELETE_CUSTOM_SEARCH:
				validateCustomSearchId(list, customSearch);
				break;
			default:
				break;
		}
	}

	/**
	 * Validate custom search type enum.
	 * 
	 * @param list the list
	 * @param customSearch the custom search
	 */
	private void validateCustomSearchTypeEnum(List<MessageInfo> list, CustomSearch customSearch)
	{
		ValidationUtil.isNull(customSearch.getDeviceType(), DEVICE_TYPE_ENUM_REQUIRED, list);
	}

	/**
	 * Validate custom search name.
	 * 
	 * @param list the list
	 * @param customSearch the custom search
	 */
	private void validateCustomSearchName(List<MessageInfo> list, CustomSearch customSearch)
	{
		if (!isNull(customSearch.getName()))
		{
			if (customSearch.getName().length() > getNameLength())
			{
				list.add(MessageInfo.createFieldValidationError(SEARCH_NAME_INVALID, getNameLength()));
			}
		}
		else
		{
			list.add(MessageInfo.createFieldValidationError(NAME_REQUIRED));
		}
	}

	/**
	 * Validate custom search id.
	 * 
	 * @param list the list
	 * @param customSearch the custom search
	 */
	private void validateCustomSearchId(List<MessageInfo> list, CustomSearch customSearch)
	{
		ValidationUtil.isNull(customSearch.getId(), ID_REQUIRED, list);
	}

	/**
	 * Validate custom search description.
	 * 
	 * @param list the list
	 * @param customSearch the custom search
	 */
	private void validateCustomSearchDescription(List<MessageInfo> list, CustomSearch customSearch)
	{
		if (!ValidationUtil.isNull(customSearch.getDescription())
				&& (customSearch.getDescription().length() > getDescriptionLength()))
		{
			list.add(MessageInfo.createFieldValidationError(SEARCH_DESCRIPTION_INVALID,
					getDescriptionLength()));
		}
	}

}
