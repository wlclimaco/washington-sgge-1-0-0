package com.sensus.dm.common.device.validation;

import com.sensus.cbof.model.Radio;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;

/**
 * The Class DeviceValidator.
 * 
 * @author QAT Global
 */
public class RadioValidator implements IValidator
{

	/** The Constant DEVICE_REQUIRED. */
	private static final String RADIO_REQUIRED = "sensus.epm.radiovalidator.radio.required";

	/** The Constant FLEXNET_ID_REQUIRED. */
	private static final String FLEXNET_ID_REQUIRED = "sensus.epm.radiovalidator.flexnet.id.required";

	/** The Constant CUSTOMER_ID_REQUIRED. */
	private static final String CUSTOMER_ID_REQUIRED = "sensus.epm.radiovalidator.customer.id.required";

	/**
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		Radio radio = (Radio)validationContext.getObjectToBeValidated(Radio.class.getSimpleName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(radio))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(RADIO_REQUIRED));
			return;
		}

		switch (action)
		{
			case FETCH_UPDATED_LOAD_PROFILE:
			case FETCH_ALL_WATER_GAS_DATA_READ:
			case FETCH_ALL_PEAK_DEMAND:
			case FETCH_ALL_INTERVAL_READ:
			case FETCH_ALL_TOU_READ:
			case FETCH_ALL_SNAPSHOT:
			case GENERATE_FILE_CSV_WATER_GAS_DATA_READ:
			case GENERATE_FILE_CSV_INTERVAL_READ:
			case GENERATE_FILE_CSV_SNAPSHOT:
			case GENERATE_FILE_CSV_TOU:
				ValidationUtil.isNullOrEmpty(radio.getCustomerId(), CUSTOMER_ID_REQUIRED,
						validationContext.getMessages());
				ValidationUtil.isNull(radio.getFlexNetId(), FLEXNET_ID_REQUIRED, validationContext.getMessages());
				break;
			case FETCH_ALARM_HISTORY:
			case FETCH_ALL_GROUPS_BY_DEVICE:
			case FETCH_BY_DEVICE:
			case FETCH_SCHEDULE_BY_DEVICE:
			case FETCH_ALL_HAN_DECICES_BY_METER:
			case FETCH_DEMAND_RESPONSE_SETUP_BY_DEVICE:
			case FETCH_LCM_RELAYS_BY_DEVICE:
				ValidationUtil.isNull(radio.getFlexNetId(), FLEXNET_ID_REQUIRED, validationContext.getMessages());
				break;
			default:
				break;
		}
	}
}
