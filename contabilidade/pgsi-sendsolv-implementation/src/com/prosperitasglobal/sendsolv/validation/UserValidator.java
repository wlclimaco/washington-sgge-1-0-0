package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.user.model.User;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class UserValidator.
 */
public class UserValidator implements IValidator
{
	/** The Constant USER. */
	private static final String USER_NAME = User.class.getSimpleName();

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_NAME_REQUIRED = "sensus.mlc.validator.name.required";

	/** The Constant SENSUS_MLC_USERVALIDATOR_EMAIL_REQUIRED. */
	private static final String SENSUS_MLC_USERVALIDATOR_EMAIL_REQUIRED =
			"sensus.mlc.uservalidator.email.required";

	/** The Constant SENSUS_MLC_USERVALIDATOR_ROLE_REQUIRED. */
	private static final String SENSUS_MLC_USERVALIDATOR_ROLE_REQUIRED =
			"sensus.mlc.uservalidator.role.required";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		// MLCPersistanceActionEnum action =
		// (MLCPersistanceActionEnum)validationContext.getValidationArguments().get(
		// MLCPersistanceActionEnum.getSlcActionName());
		//
		// if (isNull(action))
		// {
		// return;
		// }
		//
		// User user = (User)validationContext.getObjectToBeValidated(USER_NAME);
		// List<MessageInfo> list = validationContext.getMessages();
		//
		// if (isNull(user))
		// {
		// list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_REQUIRED, User.class.getSimpleName()));
		// return;
		// }
		//
		// switch (action)
		// {
		// case INSERT:
		// case UPDATE:
		// validateUser(list, user);
		// break;
		//
		// case FETCH_BY_ID:
		// validateUserIds(list, user);
		// break;
		//
		// case FETCH_BY_NAME:
		// // We don't validate Tenant here because the super user.
		// if (!isNullOrEmpty(list))
		// {
		// list.remove(0);
		// }
		// validateUserName(list, user);
		// break;
		//
		// default:
		// return;
		// }
	}

	/**
	 * Validate user.
	 *
	 * @param list the list
	 * @param user the user
	 */
	private void validateUser(List<MessageInfo> list, User user)
	{
		// LCHelp.isNullLC(user.getFirstName(), SENSUS_MLC_VALIDATOR_NAME_REQUIRED, list, "First");
		// LCHelp.isNullLC(user.getLastName(), SENSUS_MLC_VALIDATOR_NAME_REQUIRED, list, "Last");
		// LCHelp.isNullLC(user.getUserName(), SENSUS_MLC_VALIDATOR_NAME_REQUIRED, list, USER_NAME);
		ValidationUtil.isNull(user.getEmail(), SENSUS_MLC_USERVALIDATOR_EMAIL_REQUIRED, list);
		ValidationUtil.isNull(user.getRole(), SENSUS_MLC_USERVALIDATOR_ROLE_REQUIRED, list);
	}

	/**
	 * Validate user ids.
	 *
	 * @param list the list
	 * @param user the user
	 */
	private void validateUserIds(List<MessageInfo> list, User user)
	{
		// LCHelp.isNullLC(user.getId(), SENSUS_MLC_VALIDATOR_ID_REQUIRED, list, USER_NAME);
	}

	/**
	 * Validate user name.
	 *
	 * @param list the list
	 * @param user the user
	 */
	private void validateUserName(List<MessageInfo> list, User user)
	{
		// LCHelp.isNullLC(user.getUserName(), SENSUS_MLC_VALIDATOR_NAME_REQUIRED, list, USER_NAME);
	}
}
