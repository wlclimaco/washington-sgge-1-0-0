package com.sensus.mlc.base.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.mlc.base.model.Parameter;
import com.sensus.mlc.smartpoint.model.PropertyEnum;

public class GeoCodeValidator implements IValidator
{

	/** The Constant MAX_LONGITUDE. */
	private static final int MAX_LONGITUDE = 180;

	/** The Constant MIN_LONGITUDE. */
	private static final int MIN_LONGITUDE = -180;

	/** The Constant MIN_LATITUDE. */
	private static final int MIN_LATITUDE = -90;

	/** The Constant MAX_LATITUDE. */
	private static final int MAX_LATITUDE = 90;

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LATITUDE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LATITUDE_REQUIRED =
			"sensus.mlc.smartpointvalidator.latitude.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LONGITUDE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LONGITUDE_REQUIRED =
			"sensus.mlc.smartpointvalidator.longitude.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LATITUDE. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LATITUDE =
			"sensus.mlc.smartpointvalidator.invalid.latitude";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LONGITUDE. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LONGITUDE =
			"sensus.mlc.smartpointvalidator.invalid.longitude";

	@SuppressWarnings("unchecked")
	@Override
	public void validate(ValidationContext context)
	{
		List<MessageInfo> list = context.getMessages();

		String lat = (String)context.getObjectToBeValidated(PropertyEnum.LATITUDE.name());
		String lng = (String)context.getObjectToBeValidated(PropertyEnum.LONGITUDE.name());

		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)context.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		if (isNull(action))
		{
			validateLatitude(lat, list);
			validateLongitude(lng, list);
			return;
		}

		switch (action)
		{
			case UPDATE_LIGHT_LAT_LNG:
				List<Parameter> parameterList =
						(List<Parameter>)context.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.PARAMETERS
								.getValue());
				for (Parameter parameter : parameterList)
				{
					if (PropertyEnum.LATITUDE.equals(parameter.getPropertyEnum()))
					{
						lat = parameter.getValue();
					}
					if (PropertyEnum.LONGITUDE.equals(parameter.getPropertyEnum()))
					{
						lng = parameter.getValue();
					}
				}
				break;
			default:
				break;
		}

		validateLatitude(lat, list);
		validateLongitude(lng, list);
	}

	/**
	 * Validate longitude.
	 * 
	 * @param longitude the longitude
	 * @param list the list
	 */
	public void validateLongitude(String longitude, List<MessageInfo> list)
	{
		if (isNull(longitude) || !NumberUtils.isNumber(longitude))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_LONGITUDE_REQUIRED));
			return;
		}

		Double lng = Double.valueOf(longitude);
		if ((lng > MAX_LONGITUDE) || (lng < MIN_LONGITUDE))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LONGITUDE));
		}
	}

	/**
	 * Validate latitude.
	 * 
	 * @param latitude the latitude
	 * @param list the list
	 */
	public void validateLatitude(String latitude, List<MessageInfo> list)
	{
		if (isNull(latitude) || !NumberUtils.isNumber(latitude))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_LATITUDE_REQUIRED));
			return;
		}

		Double lat = Double.valueOf(latitude);
		if ((lat > MAX_LATITUDE) || (lat < MIN_LATITUDE))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LATITUDE));
		}
	}

}
