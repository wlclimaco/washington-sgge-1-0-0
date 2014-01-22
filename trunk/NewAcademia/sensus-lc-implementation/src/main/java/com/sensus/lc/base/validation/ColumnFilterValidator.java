package com.sensus.lc.base.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.lc.light.model.Column;
import com.sensus.lc.light.model.Filter;

/**
 * The Class ColumnFilterValidator.
 */
public class ColumnFilterValidator implements IValidator
{
	/** The Constant SENSUS_MLC_SETTINGSVALIDATOR_FILTER_LIST_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_FILTER_LIST_REQUIRED =
			"sensus.mlc.validator.filter.list.required";

	/** The Constant SENSUS_MLC_VALIDATOR_COLUMNS_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_COLUMNS_REQUIRED =
			"sensus.mlc.validator.column.list.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.AbstractValidator#validate(java.lang.Object, java.util.List,
	 * java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
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

		List<Filter> filterList = (List<Filter>)validationContext
				.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.FILTER_LIST.getValue());

		List<Column> columnList = (List<Column>)validationContext
				.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.COLUMN_LIST.getValue());

		List<MessageInfo> list = validationContext.getMessages();

		switch (action)
		{
			case INSERT_USER_COLUMNS:
				validateColumns(list, columnList);
				validateFilters(list, filterList);
				return;
			case INSERT_CUSTOM_SEARCH:
				validateColumns(list, columnList);
				return;
			default:
				return;
		}
	}

	/**
	 * Validate columns.
	 *
	 * @param list the list
	 * @param request the request
	 */
	private void validateColumns(List<MessageInfo> list, List<Column> columnList)
	{
		ValidationUtil.isNull(columnList, SENSUS_MLC_VALIDATOR_COLUMNS_REQUIRED, list);
	}

	/**
	 * Validate columns.
	 *
	 * @param list the list
	 * @param request the request
	 */
	private void validateFilters(List<MessageInfo> list, List<Filter> filterList)
	{
		ValidationUtil.isNull(filterList, SENSUS_MLC_VALIDATOR_FILTER_LIST_REQUIRED, list);
	}
}
