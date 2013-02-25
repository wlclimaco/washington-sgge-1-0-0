package com.sensus.mlc.group.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.mlc.group.model.Group;

/**
 * The Class GroupListValidator.
 */
public class GroupListValidator extends GroupValidator implements IValidator
{
	/** The Constant SENSUS_MLC_GROUPVALIDATOR_GROUPLIST_REQUIRED. */
	private static final String SENSUS_MLC_GROUPVALIDATOR_GROUPLIST_REQUIRED =
			"sensus.mlc.groupvalidator.grouplist.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.validation.GroupValidator#validate(com.sensus.common.validation.ValidationContext)
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

		@SuppressWarnings("unchecked")
		List<Group> groupList =
				(List<Group>)validationContext.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.GROUP_LIST.getValue());

		List<MessageInfo> list = validationContext.getMessages();

		if (ValidationUtil.isNull(groupList))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_GROUPVALIDATOR_GROUPLIST_REQUIRED));
			return;
		}

		switch (action)
		{
			case FETCH_GROUPS_BY_ID_LIST:
				validateGroupListId(list, groupList);
				return;
			default:
				return;
		}
	}

	/**
	 * Validate group list id.
	 * 
	 * @param list the list
	 * @param groupList the group list
	 */
	private void validateGroupListId(List<MessageInfo> list, List<Group> groupList)
	{
		for (Group groupToValidate : groupList)
		{
			validateGroupId(list, groupToValidate);
		}
	}
}
