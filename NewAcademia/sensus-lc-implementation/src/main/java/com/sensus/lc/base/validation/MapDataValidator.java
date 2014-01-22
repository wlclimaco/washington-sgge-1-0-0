package com.sensus.lc.base.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;
import java.util.Set;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.request.LightSelectionRequest;

/**
 * The Class MapDataValidator.
 */
public class MapDataValidator implements IValidator
{
	/** The Constant SENSUS_MLC_VALIDATOR_TOP_RIGHT_LATITUDE_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_TOP_RIGHT_LATITUDE_REQUIRED =
			"sensus.mlc.validator.top.right.lat.required";

	/** The Constant SENSUS_MLC_VALIDATOR_TOP_RIGHT_LONGITUDE_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_TOP_RIGHT_LONGITUDE_REQUIRED =
			"sensus.mlc.validator.top.right.lon.required";

	/** The Constant SENSUS_MLC_VALIDATOR_BOTTOM_LEFT_LATITUDE_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_BOTTOM_LEFT_LATITUDE_REQUIRED =
			"sensus.mlc.validator.bottom.left.lat.required";

	/** The Constant SENSUS_MLC_VALIDATOR_BOTTOM_LEFT_LONGITUDE_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_BOTTOM_LEFT_LONGITUDE_REQUIRED =
			"sensus.mlc.validator.bottom.left.lon.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
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

			if (isNull(target) || !LightSelectionRequest.class.isAssignableFrom(target.getClass()))
			{
				continue;
			}

			validationMapData((LightSelectionRequest)target, list);
		}
	}

	private void validationMapData(LightSelectionRequest request, List<MessageInfo> list)
	{
		validateBottomLeftLat(list, request.getBottomLeftLat());
		validateBottomLeftLon(list, request.getBottomLeftLon());
		validateTopRightLat(list, request.getTopRightLat());
		validateTopRightLon(list, request.getTopRightLon());
	}

	/**
	 * Validate bottom left lat.
	 * 
	 * @param list the list
	 * @param bottomLeftLat the bottom left lat
	 */
	private void validateBottomLeftLat(List<MessageInfo> list, Double bottomLeftLat)
	{
		ValidationUtil.isNull(bottomLeftLat, SENSUS_MLC_VALIDATOR_BOTTOM_LEFT_LATITUDE_REQUIRED,
				list);
	}

	/**
	 * Validate bottom left lon.
	 * 
	 * @param list the list
	 * @param bottomLeftLon the bottom left lon
	 */
	private void validateBottomLeftLon(List<MessageInfo> list, Double bottomLeftLon)
	{
		ValidationUtil.isNull(bottomLeftLon, SENSUS_MLC_VALIDATOR_BOTTOM_LEFT_LONGITUDE_REQUIRED,
				list);
	}

	/**
	 * Validate top right lat.
	 * 
	 * @param list the list
	 * @param topRightLat the top right lat
	 */
	private void validateTopRightLat(List<MessageInfo> list, Double topRightLat)
	{
		ValidationUtil.isNull(topRightLat, SENSUS_MLC_VALIDATOR_TOP_RIGHT_LATITUDE_REQUIRED, list);
	}

	/**
	 * Validate top right lon.
	 * 
	 * @param list the list
	 * @param topRightLon the top right lon
	 */
	private void validateTopRightLon(List<MessageInfo> list, Double topRightLon)
	{
		ValidationUtil
				.isNull(topRightLon, SENSUS_MLC_VALIDATOR_TOP_RIGHT_LONGITUDE_REQUIRED, list);
	}

}
