package com.sensus.dm.common.base.validation;

import java.util.Date;
import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.model.ObjectToBeValidatedKeyEnum;

/**
 * The Class RangeDateValidator.
 * 
 * @author QAT Global
 */
public class RangeDateValidator implements IValidator
{
	private static final String STARTDATETIME_REQUIRED =
			"sensus.epm.rangedatevalidator.start.date.time.required";

	private static final String ENDDATETIME_REQUIRED =
			"sensus.epm.rangedatevalidator.end.date.time.required";

	/** The Constant RANGEDATE_INVALID. */
	private static final String RANGEDATE_INVALID =
			"sensus.epm.rangedatevalidator.rangedate.invalid";

	/** The Constant DATE_REQUIRED. */
	private static final String DATE_REQUIRED =
			"sensus.epm.rangedatevalidator.date.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		Date initialDate =
				(Date)validationContext.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue());
		Date endDate = (Date)validationContext.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue());

		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		if (ValidationUtil.isNull(action))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(DATE_REQUIRED));
			return;
		}

		switch (action)
		{
			case INSERT_SCHEDULE:
				ValidationUtil.isNull(initialDate, STARTDATETIME_REQUIRED, validationContext.getMessages());
				validateRangeDate(validationContext.getMessages(), initialDate, endDate);
				break;
			case UPDATE_SCHEDULE:
				ValidationUtil.isNull(initialDate, STARTDATETIME_REQUIRED, validationContext.getMessages());
				validateRangeDate(validationContext.getMessages(), initialDate, endDate);
				break;
			default:
				ValidationUtil.isNull(initialDate, STARTDATETIME_REQUIRED, validationContext.getMessages());
				ValidationUtil.isNull(endDate, ENDDATETIME_REQUIRED, validationContext.getMessages());
				break;
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
		if (!ValidationUtil.isNull(initialDate) && !ValidationUtil.isNull(endDate))
		{
			if (endDate.before(initialDate))
			{
				list.add(MessageInfo.createFieldValidationError(RANGEDATE_INVALID));
			}
		}
	}

}
