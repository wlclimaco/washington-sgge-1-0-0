package com.sensus.dm.common.process.validation;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.process.model.ProcessItem;

/**
 * The Class ProcessItemValidator.
 */
public class ProcessItemValidator implements IValidator
{

	/** The Constant SENSUS_EPM_PROCESSVALIDATOR_ITEM_REQUIRED. */
	private static final String SENSUS_EPM_PROCESSVALIDATOR_ITEM_REQUIRED = "sensus.epm.processvalidator.item.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		ProcessItem processItem =
				(ProcessItem)validationContext.getObjectToBeValidated(ProcessItem.class.getSimpleName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(processItem))
		{
			validationContext.getMessages().add(
					MessageInfo.createFieldValidationError(SENSUS_EPM_PROCESSVALIDATOR_ITEM_REQUIRED));
			return;
		}
	}
}
