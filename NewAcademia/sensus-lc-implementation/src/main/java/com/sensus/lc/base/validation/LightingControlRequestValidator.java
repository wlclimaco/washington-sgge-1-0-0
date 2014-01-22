package com.sensus.lc.base.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;
import java.util.Set;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.UserContext;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.base.util.LCHelp;
import com.sensus.lc.light.model.request.LightStatusNotificationRequest;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.user.model.User;
import com.sensus.lc.user.model.request.UserRequest;

public class LightingControlRequestValidator implements IValidator
{
	/** The super user name. */
	private String superUserName;

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

	/**
	 * Gets the super user name.
	 * 
	 * @return the super user name
	 */
	public String getSuperUserName()
	{
		return superUserName;
	}

	/**
	 * Sets the super user name.
	 * 
	 * @param superUserName the new super user name
	 */
	public void setSuperUserName(String superUserName)
	{
		this.superUserName = superUserName;
	}

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

			// Evaluate authentication case
			boolean isAuthenticationFlow = false;
			if (request instanceof UserRequest)
			{
				UserRequest userRequest = (UserRequest)request;
				User user = userRequest.getUser();

				isAuthenticationFlow = !isNull(user) && !isNull(user.getUserName()) && isNull(user.getId());
			}

			validateTenant(list, request, isAuthenticationFlow);
			validateUserContext(list, request.getUserContext(), isAuthenticationFlow);
		}
	}

	/**
	 * Validate tenant.
	 * 
	 * @param list the list
	 * @param tenant the tenant
	 */
	private void validateTenant(List<MessageInfo> list, LightingControlRequest request, boolean isAuthenticationFlow)
	{
		Tenant tenant = request.getTenant();
		if (isNull(tenant))
		{
			LCHelp.isNullLC(tenant, SENSUS_MLC_VALIDATOR_REQUIRED, list, TENANT_NAME);
			return;
		}

		if (isAuthenticationFlow)
		{
			// Case Super user not validation tenant
			User user = ((UserRequest)request).getUser();
			if (!isNull(user) && !isNull(getSuperUserName()) && getSuperUserName().equalsIgnoreCase(user.getUserName()))
			{
				return;
			}
		}

		LCHelp.isNullOrEmptyLC(tenant.getRniCode(), SENSUS_MLC_VALIDATOR_REQUIRED, list, TENANT_NAME);

		// Request sent by LightBindingNotification or LightStatusNotification ignore required id validation
		if (!LightStatusNotificationRequest.class.isAssignableFrom(request.getClass()))
		{
			LCHelp.isNullOrZeroLC(tenant.getId(), SENSUS_MLC_VALIDATOR_ID_REQUIRED, list, TENANT_NAME);
		}
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
