package com.sensus.lc.base.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.END_DATE;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.INITIAL_DATE;

import java.util.Date;
import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;

/**
 * The Class RangeDateValidator.
 */
public class RangeDateValidator implements IValidator
{
	/** The Constant SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED. */
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED =
			"sensus.mlc.ecomodevalidator.rangedate.required";

	/** The Constant SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_INVALID. */
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_INVALID =
			"sensus.mlc.ecomodevalidator.rangedate.invalid";

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

		if (isNull(action))
		{
			return;
		}

		List<MessageInfo> list = context.getMessages();
		Date initialDate = (Date)context.getObjectToBeValidated(INITIAL_DATE.getValue());
		Date endDate = (Date)context.getObjectToBeValidated(END_DATE.getValue());

		switch (action)
		{
			case FETCH_AVERAGE_LIGHT_CONSUMPTION:
			case FETCH_AVERAGE_LIGHT_VOLTAGE:
			case FETCH_LIGHT_CONSUMPTION:
			case FETCH_ANALYTICS_BY_GROUP:
			case FETCH_ANALYTICS_BY_DATE:
			case FETCH_DASHBOARD_RESUME:
			case GENERATE_FILE_CSV:
			case FETCH:
				validateRangeDate(list, initialDate, endDate);
				return;
			default:
				return;
		}
	}

	/**
	 * Validate range date.
	 * 
	 * @param list the list
	 * @param initialDate the initial date
	 * @param endDate the end date
	 */
	private void validateRangeDate(List<MessageInfo> list, Date initialDate, Date endDate)
	{
		if (isNull(initialDate) || isNull(endDate))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED));
			return;
		}

		if (endDate.before(initialDate))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_INVALID));
		}
	}
}
