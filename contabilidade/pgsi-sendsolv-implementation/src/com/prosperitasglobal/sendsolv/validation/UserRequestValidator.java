package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.security.util.LCSecurityHandler;
import com.prosperitasglobal.sendsolv.user.model.request.UserRequest;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class UserRequestValidator.
 */
public class UserRequestValidator implements IValidator
{
	/** The Constant USER_REQUEST_NAME. */
	private static final String USER_REQUEST_NAME = UserRequest.class.getSimpleName();

	/** The Constant SENSUS_MLC_USERVALIDATOR_ACTUAL_PASSWORD_REQUIRED. */
	private static final String SENSUS_MLC_USERVALIDATOR_ACTUAL_PASSWORD_REQUIRED =
			"sensus.mlc.uservalidator.actual.password.required";

	/** The Constant SENSUS_MLC_USERVALIDATOR_NEW_PASSWORD_REQUIRED. */
	private static final String SENSUS_MLC_USERVALIDATOR_NEW_PASSWORD_REQUIRED =
			"sensus.mlc.uservalidator.new.password.required";

	/** The Constant SENSUS_MLC_USERVALIDATOR_ACTUAL_PASSWORD_INVALID. */
	private static final String SENSUS_MLC_USERVALIDATOR_ACTUAL_PASSWORD_INVALID =
			"sensus.mlc.uservalidator.actual.password.invalid";

	/** The Constant SENSUS_MLC_USERVALIDATOR_USER_REQUEST_REQUIRED. */
	private static final String SENSUS_MLC_USERVALIDATOR_USER_REQUEST_REQUIRED =
			"sensus.mlc.uservalidator.user.request.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.AbstractValidator#validate(java.lang.Object, java.util.List,
	 * java.lang.Object[])
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
		// // Cast the object so it's easy to work with.
		// UserRequest userRequest = (UserRequest)validationContext.getObjectToBeValidated(USER_REQUEST_NAME);
		// List<MessageInfo> list = validationContext.getMessages();
		//
		// if (isNull(userRequest))
		// {
		// list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_USERVALIDATOR_USER_REQUEST_REQUIRED));
		// return;
		// }
		//
		// switch (action)
		// {
		// case CHANGE_PASSWORD:
		// validatePassword(list, userRequest);
		// break;
		//
		// default:
		// return;
		// }
	}

	/**
	 * Validate password.
	 *
	 * @param list the list
	 * @param userRequest the user request
	 */
	private void validatePassword(List<MessageInfo> list, UserRequest userRequest)
	{
		ValidationUtil.isNull(userRequest.getActualPassword(), SENSUS_MLC_USERVALIDATOR_ACTUAL_PASSWORD_REQUIRED, list);
		ValidationUtil.isNull(userRequest.getNewPassword(), SENSUS_MLC_USERVALIDATOR_NEW_PASSWORD_REQUIRED, list);

		if (!LCSecurityHandler.validePassword(userRequest.getUser().getPassword(), userRequest.getActualPassword(),
				userRequest.getUser().getUserName()))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_USERVALIDATOR_ACTUAL_PASSWORD_INVALID));
		}
	}
}
