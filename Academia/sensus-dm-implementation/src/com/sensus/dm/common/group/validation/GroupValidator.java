package com.sensus.dm.common.group.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.GroupTypeEnum;

/**
 * The Class CustomSearchRequestValidator.
 * 
 * @author QAT Brazil
 * 
 */
public class GroupValidator implements IValidator
{

	/** The Constant ID_REQUIRED. */
	private static final String ID_REQUIRED = "sensus.dm.common.groupvalidator.id.required";

	/** The Constant GROUP_REQUIRED. */
	private static final String GROUP_REQUIRED = "sensus.dm.common.groupvalidator.group.required";

	/** The Constant NAME_INVALID. */
	private static final String NAME_INVALID = "sensus.dm.common.groupvalidator.group.name.invalid";

	/** The Constant NAME_REQUIRED. */
	private static final String NAME_REQUIRED = "sensus.dm.common.groupvalidator.name.required";

	/** The Constant SUBTYPE_REQUIRED. */
	private static final String SUBTYPE_REQUIRED = "sensus.dm.common.groupvalidator.subtype.required";

	/** The Constant DESCRIPTION_INVALID. */
	private static final String DESCRIPTION_INVALID = "sensus.dm.common.groupvalidator.description.invalid";

	/** The Constant OLD_NAME_REQUIRED. */
	private static final String OLD_NAME_REQUIRED = "sensus.dm.common.groupvalidator.old.name.required";

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

		Group group =
				(Group)validationContext.getObjectToBeValidated(Group.class.getSimpleName());

		if (isNull(group))
		{
			list.add(MessageInfo.createFieldValidationError(GROUP_REQUIRED));
			return;
		}

		switch (action)
		{
			case ADD_SM_FROM_FILE_TO_GRP:
			case FETCH_SCHEDULE_BY_GROUP:
			case FETCH_DEVICES:
			case DEL_SMP_FROM_GRP:
			case ADD_SMP_TO_GRP:
			case DELETE:
			case FETCH_BY_ID:
				validateGroupId(list, group);
				break;
			case FETCH_BY_NAME:
				validateGroupName(list, group);
				break;
			case INSERT:
			case INSERT_GROUP:
				validateGroupName(list, group);
				validateGroupDescription(list, group.getDescription());
				validateGroupSubtype(list, group.getGroupTypeEnum());
				break;
			case UPDATE:
				validateGroupName(list, group);
				validateGroupDescription(list, group.getDescription());
				validateGroupId(list, group);
				validateGroupOldName(list, group);
				break;
			default:
				break;
		}
	}

	/**
	 * Validate group id.
	 * 
	 * @param list the list
	 * @param group the group
	 */
	private void validateGroupId(List<MessageInfo> list, Group group)
	{
		ValidationUtil.isNullOrZero(group.getId(), ID_REQUIRED, list);
	}

	/**
	 * Validate group name.
	 * 
	 * @param list the list
	 * @param group the group
	 */
	private void validateGroupName(List<MessageInfo> list, Group group)
	{
		if (!ValidationUtil.isNullOrEmpty(group.getName()))
		{
			if (group.getName().length() > getNameLength())
			{
				list.add(MessageInfo.createFieldValidationError(NAME_INVALID, getNameLength()));
			}
		}
		else
		{
			list.add(MessageInfo.createFieldValidationError(NAME_REQUIRED));
		}
	}

	/**
	 * Validate group description.
	 * 
	 * @param list the list
	 * @param description the description
	 */
	private void validateGroupDescription(List<MessageInfo> list, String description)
	{
		if (!ValidationUtil.isNull(description) && description.length() > getDescriptionLength())
		{
			list.add(MessageInfo.createFieldValidationError(DESCRIPTION_INVALID,
					getDescriptionLength()));
		}
	}

	/**
	 * Validate group subtype.
	 * 
	 * @param list the list
	 * @param groupTypeEnum the group type enum
	 */
	private void validateGroupSubtype(List<MessageInfo> list, GroupTypeEnum groupTypeEnum)
	{
		ValidationUtil.isNull(groupTypeEnum, SUBTYPE_REQUIRED, list);
	}

	/**
	 * Validate group old name.
	 * 
	 * @param list the list
	 * @param group the group
	 */
	private void validateGroupOldName(List<MessageInfo> list, Group group)
	{
		ValidationUtil.isNull(group.getOldName(), OLD_NAME_REQUIRED, list);
	}

}
