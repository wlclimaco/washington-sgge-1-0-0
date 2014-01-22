package com.sensus.lc.server.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.common.validation.ValidationUtil.isNullOrZero;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.base.util.LCHelp;
import com.sensus.mlc.mlcserver.type.Smartpoint;

/**
 * The Class MlcGatewaySupervisoryNotificationValidator.
 */
public class MlcGatewaySupervisoryNotificationValidator implements IValidator
{
	/** The Constant SMARTPOINT. */
	private static final String SMARTPOINT = "smartpoint";

	/** The Constant TRANSACTION_ID. */
	private static final String TRANSACTION_ID = "transactionId";

	/** The Constant CUSTUMER_ID. */
	private static final String CUSTUMER_ID = "custumerId";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED =
			"sensus.mlc.lightvalidator.lightrniid.required";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	protected static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The Constant SENSUS_MLC_TRANSACTION_ID_REQUIRED. */
	private static final String SENSUS_MLC_TRANSACTION_ID_REQUIRED = "sensus.mlc.transactionid.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext context)
	{

		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)context.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		List<MessageInfo> list = context.getMessages();
		if (isNull(context.getObjectsToBeValidated()) || isNull(action) || !isNullOrEmpty(list))
		{
			return;
		}

		String custumerId = (String)context.getObjectToBeValidated(CUSTUMER_ID);
		String transactionId = (String)context.getObjectToBeValidated(TRANSACTION_ID);
		Smartpoint smartpoint = (Smartpoint)context.getObjectToBeValidated(SMARTPOINT);

		switch (action)
		{
			case APPLY_DIMMING_CONFIGURATION:
			case UPDATE_LIGHT_STATUS:
				validateTenantCode(custumerId, list);
				break;
			case GATEWAY_FORCED_STATUS:
			case GATEWAY_SET_LIGHT_INTENSITY:
			case GATEWAY_CLEAR_SCHEDULE:
			case GATEWAY_APPLY_SCHEDULE:
			case GATEWAY_CLEAR_ALARMS:
			case GATEWAY_APPLY_LIGHT_PROPERTIES:
				validateTenantCode(custumerId, list);
				validateTransactionId(list, transactionId);
				break;

			default:
				validateTenantCode(custumerId, list);
				validateLightRniId(list, smartpoint.getRniId());
				break;
		}

	}

	/**
	 * Validate tenant code.
	 *
	 * @param tenantCode the tenant code
	 * @param list the list
	 */
	public void validateTenantCode(String tenantCode, List<MessageInfo> list)
	{
		LCHelp.isNullLC(tenantCode, SENSUS_MLC_VALIDATOR_REQUIRED, list, "Tenant");
	}

	/**
	 * Validate rni id.
	 *
	 * @param list the list
	 * @param rniId the rni id
	 */
	public void validateLightRniId(List<MessageInfo> list, Integer rniId)
	{
		isNullOrZero(rniId, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED, list);
	}

	/**
	 * Validate transaction id.
	 *
	 * @param list the list
	 * @param transactionId the transaction id
	 */
	private void validateTransactionId(List<MessageInfo> list, String transactionId)
	{
		ValidationUtil.isNullOrEmpty(transactionId, SENSUS_MLC_TRANSACTION_ID_REQUIRED, list);
	}

}
