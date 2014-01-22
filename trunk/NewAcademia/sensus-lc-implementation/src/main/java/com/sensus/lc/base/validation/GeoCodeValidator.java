package com.sensus.lc.base.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.lc.base.validation.util.LightLocationValidationUtil.validateLatitude;
import static com.sensus.lc.base.validation.util.LightLocationValidationUtil.validateLongitude;

import java.util.List;

import com.sensus.cbof.model.Radio;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;

public class GeoCodeValidator implements IValidator
{
	/** Messages. */
	private static final String SENSUS_MLC_GEOCODEVALIDATOR_LIGHT_LOCATION_REQUIRED =
			"sensus.mlc.geocodevalidator.light.location.required";

	/**
	 * Objects name.
	 */
	private static final String RADIO_NAME = Radio.class.getSimpleName();

	@Override
	public void validate(ValidationContext context)
	{
		List<MessageInfo> list = context.getMessages();
		if (!isNullOrEmpty(list))
		{
			return;
		}

		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)context.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		Radio radio = (Radio)context.getObjectToBeValidated(RADIO_NAME);
		if (isNull(action) || isNull(radio) || isNull(radio.getLocation()))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_GEOCODEVALIDATOR_LIGHT_LOCATION_REQUIRED));
			return;
		}

		validateLatitude(radio.getLocation().getLatitude(), list);
		validateLongitude(radio.getLocation().getLongitude(), list);
	}

}
