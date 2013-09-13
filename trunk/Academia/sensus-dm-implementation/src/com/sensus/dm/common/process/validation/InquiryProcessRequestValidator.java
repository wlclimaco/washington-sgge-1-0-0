package com.sensus.dm.common.process.validation;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;

/**
 * The Class InquiryProcessRequestValidator.
 *
 * @author QAT Brazil.
 */
public class InquiryProcessRequestValidator implements IValidator
{

	/** The Constant SENSUS_EPM_PROCESSVALIDATOR_PROCESSSEARCH_REQUIRED. */
	private static final String SENSUS_EPM_PROCESSVALIDATOR_PROCESSSEARCH_REQUIRED =
			"sensus.epm.processvalidator.processsearch.required";

	/** The Constant STATUS_FIELD_NAME. */
	private static final String STATUS_FIELD_NAME = "status";

	/** The Constant ARRAY_BEGINNING. */
	private static final int ARRAY_BEGINNING = 0;

	@Override
	public void validate(ValidationContext validationContext)
	{
		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		if (ValidationUtil.isNull(action))
		{
			return;
		}

		InquiryProcessRequest inquiryProcessRequest =
				(InquiryProcessRequest)validationContext.getObjectToBeValidated(InquiryProcessRequest.class
						.getSimpleName());

		switch (action)
		{
			case GENERATE_TODAY_FILE_CSV:
			case FETCH_TODAY_PROCESSES:
				ensureSortOrderIsByStatusFirst(inquiryProcessRequest);

				ValidationUtil.isNull(inquiryProcessRequest.getProcessSearch(),
						SENSUS_EPM_PROCESSVALIDATOR_PROCESSSEARCH_REQUIRED, validationContext.getMessages());
				break;

			default:
				break;
		}
	}

	/**
	 * Ensure sort order is by status first.
	 *
	 * @param inquiryProcessRequest the inquiry process request
	 */
	private void ensureSortOrderIsByStatusFirst(InquiryProcessRequest inquiryProcessRequest)
	{
		if (inquiryProcessRequest.getSortExpressions().isEmpty()
				|| !inquiryProcessRequest.getSortExpressions().get(ARRAY_BEGINNING).getField().equals(STATUS_FIELD_NAME))
		{
			SortExpression se = new SortExpression(STATUS_FIELD_NAME, Direction.Ascending);
			inquiryProcessRequest.getSortExpressions().add(ARRAY_BEGINNING, se);
		}
	}
}