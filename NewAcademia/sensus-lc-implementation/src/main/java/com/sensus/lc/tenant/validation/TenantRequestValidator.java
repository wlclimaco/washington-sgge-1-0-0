package com.sensus.lc.tenant.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.base.util.LCHelp;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * The Class TenantRequestValidator.
 */
public class TenantRequestValidator implements IValidator
{
	/** The Constant SERVER. */
	private static final String SERVER = "Server";

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_NAME_REQUIRED = "sensus.mlc.validator.name.required";

	private static final String SENSUS_MLC_TENANTVALIDATOR_REQUEST_REQUIRED =
			"sensus.mlc.tenantvalidator.request.required";

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

		TenantRequest tenantRequest =
				(TenantRequest)validationContext.getObjectToBeValidated(TenantRequest.class.getSimpleName());
		List<MessageInfo> list = validationContext.getMessages();

		if (isNull(tenantRequest))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_TENANTVALIDATOR_REQUEST_REQUIRED));
			return;
		}

		switch (action)
		{
			case FETCH_TENANT_BY_SERVER_NAME:
				validateServerName(list, tenantRequest);
				return;
			default:
				throw new IllegalArgumentException("Unexpected action value: " + action);
		}
	}

	/**
	 * Validate server name.
	 * 
	 * @param list the list
	 * @param tenantRequest the tenant request
	 */
	private void validateServerName(List<MessageInfo> list, TenantRequest tenantRequest)
	{
		LCHelp.isNullLC(tenantRequest.getServerName(), SENSUS_MLC_VALIDATOR_NAME_REQUIRED, list, SERVER);
	}
}
