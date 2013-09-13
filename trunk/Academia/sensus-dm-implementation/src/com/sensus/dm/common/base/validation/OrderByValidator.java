package com.sensus.dm.common.base.validation;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.SortExpression;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;

/**
 * The Class OrderByValidator.
 * 
 * @author QAT Global
 */
public class OrderByValidator implements IValidator
{

	/** The Constant SENSUS_EPM_ORDERBYVALIDATOR_SORT_REQUIRED. */
	private static final String SENSUS_EPM_ORDERBYVALIDATOR_SORT_REQUIRED =
			"sensus.epm.orderbyvalidator.sort.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void validate(ValidationContext validationContext)
	{
		List<SortExpression> sortExpressionList =
				(List<SortExpression>)validationContext.getObjectToBeValidated(SortExpression.class.getSimpleName());

		validateOrderBy(validationContext.getMessages(), sortExpressionList);

	}

	/**
	 * Validate order by.
	 * 
	 * @param list the list
	 * @param sortExpressionList the sort expression list
	 */
	private void validateOrderBy(List<MessageInfo> list, List<SortExpression> sortExpressionList)
	{
		ValidationUtil.isNullOrEmpty(sortExpressionList, SENSUS_EPM_ORDERBYVALIDATOR_SORT_REQUIRED, list);

		if (!ValidationUtil.isNullOrEmpty(sortExpressionList))
		{
			for (SortExpression sortExpression : sortExpressionList)
			{
				ValidationUtil.isNull(sortExpression, SENSUS_EPM_ORDERBYVALIDATOR_SORT_REQUIRED, list);
				ValidationUtil.isNullOrEmpty(sortExpression.getDirectionValue(),
						SENSUS_EPM_ORDERBYVALIDATOR_SORT_REQUIRED, list);
				ValidationUtil
						.isNullOrEmpty(sortExpression.getField(), SENSUS_EPM_ORDERBYVALIDATOR_SORT_REQUIRED, list);
			}
		}
	}
}
