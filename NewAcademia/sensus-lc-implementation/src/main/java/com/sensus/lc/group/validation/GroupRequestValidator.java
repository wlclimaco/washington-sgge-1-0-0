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
import com.sensus.lc.group.model.request.GroupRequest;

/**
 * The Class GroupRequestValidator.
 */
public class GroupRequestValidator implements IValidator
{
	/** The Constant GROUP_NAME. */
	private static final String GROUP_NAME = Group.class.getSimpleName();

	/** The Constant OLD_GROUP_NAME. */
	private static final String OLD_GROUP_NAME = "Old " + GROUP_NAME;

	/** The Constant GROUP_REQUEST_NAME. */
	private static final String GROUP_REQUEST_NAME = GroupRequest.class.getSimpleName();

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_NAME_REQUIRED = "sensus.mlc.validator.name.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED =
			"sensus.mlc.lightvalidator.intensity.required";

	/** The Constant SENSUS_MLC_GROUPVALIDATOR_GROUPLIST_REQUIRED. */
	private static final String SENSUS_MLC_GROUPVALIDATOR_GROUPLIST_REQUIRED =
			"sensus.mlc.groupvalidator.grouplist.required";

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

		// Cast the object so it's easy to work with.
		GroupRequest groupRequest = (GroupRequest)validationContext.getObjectToBeValidated(GROUP_REQUEST_NAME);

		List<MessageInfo> list = validationContext.getMessages();

		switch (action)
		{
			case UPDATE:
				validateGroupOldName(list, groupRequest);
				return;

			case FETCH_GROUPS_BY_ID_LIST:
				validateGroupList(list, groupRequest);
				return;

			case UPDATE_LIGHT_INTENSITY:
				validateLightIntensity(list, groupRequest.getPercentage());
				return;

			case FETCH_LIGHT_BY_GROUP_TO_MAP:
				validateGroupList(list, groupRequest);
				return;

			case FETCH_COUNT_LIGHTS_FROM_GROUPS:
				validateGroupList(list, groupRequest);
				return;

			default:
				return;
		}
	}

	/**
	 * Validate group old name.
	 *
	 * @param list the list
	 * @param groupRequest the group request
	 */
	private void validateGroupOldName(List<MessageInfo> list, GroupRequest groupRequest)
	{
		LCHelp.isNullLC(groupRequest.getOldName(), SENSUS_MLC_VALIDATOR_NAME_REQUIRED, list, OLD_GROUP_NAME);
	}

	/**
	 * Validate light intensity.
	 *
	 * @param list the list
	 * @param percentage the percentage
	 */
	private void validateLightIntensity(List<MessageInfo> list, Integer percentage)
	{
		ValidationUtil.isNull(percentage, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED, list);
	}

	/**
	 * Validate group list.
	 *
	 * @param list the list
	 * @param groupRequest the group request
	 */
	private void validateGroupList(List<MessageInfo> list, GroupRequest groupRequest)
	{
		ValidationUtil.isNullOrEmpty(groupRequest.getGroupList(), SENSUS_MLC_GROUPVALIDATOR_GROUPLIST_REQUIRED, list);
	}
}
