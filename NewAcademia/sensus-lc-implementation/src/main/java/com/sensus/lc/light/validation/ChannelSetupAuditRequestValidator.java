package com.sensus.lc.light.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.lc.base.validation.util.LCValidationUtil.validateLifeCycleState;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.light.model.request.ChannelSetupAuditRequest;

/**
 * The Class ChannelSetupAuditRequestValidator.
 */
public class ChannelSetupAuditRequestValidator implements IValidator
{

	/** The Constant CHANNEL_SETUP_AUDIT_NAME. */
	private static final String CHANNEL_SETUP_AUDIT_NAME = ChannelSetupAuditRequest.class
			.getSimpleName();

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.AbstractValidator#validate(java.lang.Object, java.util.List,
	 * java.lang.Object[])
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		List<MessageInfo> list = validationContext.getMessages();
		if (!isNullOrEmpty(list))
		{
			return;
		}

		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)validationContext.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		ChannelSetupAuditRequest request =
				(ChannelSetupAuditRequest)validationContext.getObjectToBeValidated(CHANNEL_SETUP_AUDIT_NAME);

		if (isNull(action) || isNull(request))
		{
			return;
		}

		switch (action)
		{
			case GATEWAY_CHANNEL_SETUP_AUDIT:
				validateLifeCycleState(request.getLifeCycleState(), list);
				break;

			default:
				break;
		}
	}
}
