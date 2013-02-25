package com.sensus.mlc.base.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;
import java.util.Set;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.UserContext;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.user.model.User;
import com.sensus.mlc.user.model.request.UserRequest;

public class LightingControlRequestValidator implements IValidator
{
	/** The Constant TENANT_NAME. */
	private static final String TENANT_NAME = Tenant.class.getSimpleName();

	/** The Constant USER_NAME. */
	private static final String USER_NAME = User.class.getSimpleName();

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant USER_CONTEXT_REQUIRED. */
	private static final String USER_CONTEXT_REQUIRED = "sensus.mlc.uservalidator.user.context.required";

	@Override
	public void validate(ValidationContext context)
	{
		if (isNull(context.getObjectsToBeValidated()))
		{
			return;
		}

		List<MessageInfo> list = context.getMessages();
		if (!isNullOrEmpty(list))
		{
			return;
		}

		Set<String> keys = context.getObjectsToBeValidated().keySet();
		for (String key : keys)
		{
			Object target = context.getObjectToBeValidated(key);

			if (isNull(target) || !LightingControlRequest.class.isAssignableFrom(target.getClass()))
			{
				continue;
			}

			LightingControlRequest request = (LightingControlRequest)target;
			validateTenant(list, request.getTenant());

			// Evaluate authentication case
			boolean isAuthenticationFlow = false;
			if (request instanceof UserRequest)
			{
				UserRequest userRequest = (UserRequest)request;
				User user = userRequest.getUser();

				isAuthenticationFlow = !isNull(user) && !isNull(user.getUserName()) && isNull(user.getId());
			}

			validateUserContext(list, request.getUserContext(), isAuthenticationFlow);
		}
	}

	/**
	 * Validate tenant.
	 * 
	 * @param list the list
	 * @param tenant the tenant
	 */
	private void validateTenant(List<MessageInfo> list, Tenant tenant)
	{
		if (!isNull(tenant))
		{
			LCHelp.isNullOrZeroLC(tenant.getId(), SENSUS_MLC_VALIDATOR_ID_REQUIRED, list, TENANT_NAME);
			LCHelp.isNullOrEmptyLC(tenant.getRniCode(), SENSUS_MLC_VALIDATOR_REQUIRED, list,
					TENANT_NAME);
			return;
		}

		LCHelp.isNullLC(tenant, SENSUS_MLC_VALIDATOR_REQUIRED, list, TENANT_NAME);
	}

	/**
	 * Validate user context.
	 * 
	 * @param list the list
	 * @param userContext the user context
	 * @param isAuthenticationFlow the is authentication flow
	 */
	private void validateUserContext(List<MessageInfo> list, UserContext userContext, boolean isAuthenticationFlow)
	{
		if (isNull(userContext))
		{
			isNull(userContext, USER_CONTEXT_REQUIRED, list);
			return;
		}

		if (!isAuthenticationFlow)
		{
			LCHelp.isNullLC(userContext.getUserId(), SENSUS_MLC_VALIDATOR_ID_REQUIRED, list, USER_NAME);
		}
	}
}
