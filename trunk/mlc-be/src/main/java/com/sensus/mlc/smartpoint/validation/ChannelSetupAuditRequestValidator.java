package com.sensus.mlc.smartpoint.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.request.ChannelSetupAuditRequest;

/**
 * The Class ChannelSetupAuditRequestValidator.
 */
public class ChannelSetupAuditRequestValidator implements IValidator
{

	/** The Constant CHANNEL_SETUP_AUDIT_NAME. */
	private static final String CHANNEL_SETUP_AUDIT_NAME = ChannelSetupAuditRequest.class
			.getSimpleName();

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED =
			"sensus.mlc.smartpointvalidator.light.top.level.state.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.AbstractValidator#validate(java.lang.Object, java.util.List,
	 * java.lang.Object[])
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
		ChannelSetupAuditRequest channelSetupAuditRequest =
				(ChannelSetupAuditRequest)validationContext.getObjectToBeValidated(CHANNEL_SETUP_AUDIT_NAME);

		List<MessageInfo> list = validationContext.getMessages();

		switch (action)
		{
			case GATEWAY_CHANNEL_SETUP_AUDIT:
				validateLightStatus(channelSetupAuditRequest.getLightStatusEnum(), list);
				break;

			default:
				break;
		}
	}

	/**
	 * Validate light status.
	 * 
	 * @param lightTopLevelState the light top level state
	 * @param list the list
	 */
	private void validateLightStatus(LightStatusEnum lightTopLevelState, List<MessageInfo> list)
	{
		isNull(lightTopLevelState, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED, list);
	}
}
