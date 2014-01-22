package com.sensus.lc.group.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.base.util.LCHelp;
import com.sensus.lc.group.model.Group;

/**
 * The Class GroupValidator.
 */
public class GroupValidator implements IValidator
{
	/** The Constant GROUP_NAME. */
	private static final String GROUP_NAME = Group.class.getSimpleName();

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_NAME_REQUIRED = "sensus.mlc.validator.name.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)validationContext.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		if (isNull(action))
		{
			return;
		}

		Group group = (Group)validationContext.getObjectToBeValidated(GROUP_NAME);

		List<MessageInfo> list = validationContext.getMessages();

		if (ValidationUtil.isNull(group))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_REQUIRED,
					GROUP_NAME));
			return;
		}

		switch (action)
		{
			case ADD_LIGHT_TO_GRP:
				validateGroupId(list, group);
				return;

			case INSERT:
				validateGroupName(list, group);
				return;

			case UPDATE:
				validateGroupId(list, group);
				validateGroupName(list, group);
				return;

			case DEL_LIGHT_FROM_GRP:
				validateGroupId(list, group);
				return;

			case FETCH_BY_ID:
				validateGroupId(list, group);
				return;

			case FETCH_BY_NAME:
				validateGroupName(list, group);
				return;

			case UPDATE_LIGHT_INTENSITY:
				validateGroupId(list, group);
				return;

			case UPDATE_LIGHT_PROTECTED:
				validateGroupId(list, group);
				return;

			default:
				return;
		}
	}

	/**
	 * Validate group id.
	 *
	 * @param list the list
	 * @param group the group
	 */
	protected void validateGroupId(List<MessageInfo> list, Group group)
	{
		LCHelp.isNullOrZeroLC(group.getId(), SENSUS_MLC_VALIDATOR_ID_REQUIRED, list, GROUP_NAME);
	}

	/**
	 * Validate group name.
	 *
	 * @param list the list
	 * @param group the group
	 */
	private void validateGroupName(List<MessageInfo> list, Group group)
	{
		LCHelp.isNullLC(group.getName(), SENSUS_MLC_VALIDATOR_NAME_REQUIRED, list, GROUP_NAME);
	}
}
