package com.sensus.mlc.tabela.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.tag.model.request.TagRequest;

/**
 * The Class TagRequestValidator.
 */
public class TagRequestValidator implements IValidator
{
	/** The Constant SENSUS_MLC_TAGVALIDATOR_INCLUDE_SMARTPOINTS_TO_GROUP_REQUIRED. */
	private static final String SENSUS_MLC_TAGVALIDATOR_INCLUDE_SMARTPOINTS_TO_GROUP_REQUIRED =
			"sensus.mlc.tagvalidator.includesmartpointstogroup.required";

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

		List<MessageInfo> list = validationContext.getMessages();

		// Validate Procedure
		if (isNull(action) || !isNullOrEmpty(list))
		{
			return;
		}

		TagRequest tagRequest =
				(TagRequest)validationContext.getObjectsToBeValidated().get(TagRequest.class.getSimpleName());

		switch (action)
		{
			case UPDATE:
				validateAutoGroup(list, tagRequest);
				return;

			default:
		}
	}

	/**
	 * Validate auto group.
	 *
	 * @param list the list
	 * @param tagRequest the tag request
	 */
	private void validateAutoGroup(List<MessageInfo> list, TagRequest tagRequest)
	{
		isNull(tagRequest.getIncludeSmartPointsToGroup(),
				SENSUS_MLC_TAGVALIDATOR_INCLUDE_SMARTPOINTS_TO_GROUP_REQUIRED, list);
	}
}
