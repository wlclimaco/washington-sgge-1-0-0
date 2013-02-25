package com.sensus.mlc.tag.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.TAG_LIST;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.tag.model.Tag;

/**
 * The Class TagListValidator.
 */
public class TagListValidator extends TagValidator implements IValidator
{

	/** The Constant TAG_NAME. */
	private static final String TAG_NAME = Tag.class.getSimpleName();

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.validation.TagValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void validate(ValidationContext context)
	{
		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)context.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		List<MessageInfo> list = context.getMessages();

		if (isNull(action) || !isNullOrEmpty(list))
		{
			return;
		}

		List<Tag> tags = (List<Tag>)context.getObjectToBeValidated(TAG_LIST.getValue());
		if (isNullOrEmpty(tags))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_ID_REQUIRED, Tag.class.getSimpleName()));
			return;
		}

		switch (action)
		{
			case ADD_SMP_TO_TAG:
				validateTags(context, list, tags);
				return;
			default:
		}
	}

	/**
	 * Validate tags.
	 * 
	 * @param context the context
	 * @param list the list
	 * @param tags the tags
	 */
	private void validateTags(ValidationContext context, List<MessageInfo> list, List<Tag> tags)
	{
		for (Tag tag : tags)
		{
			context.putObjectToBeValidated(TAG_NAME, tag);
			super.validate(context);
		}
	}

}
