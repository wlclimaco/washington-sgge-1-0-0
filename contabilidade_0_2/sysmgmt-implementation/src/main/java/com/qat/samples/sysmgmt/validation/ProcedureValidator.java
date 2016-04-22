package com.qat.samples.sysmgmt.validation;

import java.util.List;

import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.model.Procedure;

/**
 * The Class ProcedureDValidator.
 */
public class ProcedureValidator implements IValidator
{

	private static final String SYSMGMT_BASE_PROCEDUREVALIDATOR_PROCDESC_REQUIRED =
			"sysmgmt.base.procedurevalidator.procdesc.required";
	private static final String SYSMGMT_BASE_PROCEDUREVALIDATOR_PROCCODE_REQUIRED =
			"sysmgmt.base.procedurevalidator.proccode.required";
	private static final String SYSMGMT_BASE_PROCEDUREVALIDATOR_ID_REQUIRED =
			"sysmgmt.base.procedurevalidator.id.required";

	@Override
	public void validate(ValidationContext validationContext)
	{
		Procedure procedure = (Procedure)validationContext.getObjectToBeValidated(Procedure.class.getSimpleName());

		switch (validationContext.getValidationContextIndicator())
		{
			case DELETE:
				validateProcId(validationContext.getMessages(), procedure);
				break;
			case UPDATE:
				validateProcId(validationContext.getMessages(), procedure);
				validateProcCode(validationContext.getMessages(), procedure);
				validateProcDesc(validationContext.getMessages(), procedure);
				break;
			default:
				validateProcCode(validationContext.getMessages(), procedure);
				validateProcDesc(validationContext.getMessages(), procedure);
				break;
		}
	}

	private void validateProcId(List<MessageInfo> list, Procedure procedure)
	{
		ValidationUtil.isNullOrZero(procedure.getId(), SYSMGMT_BASE_PROCEDUREVALIDATOR_ID_REQUIRED, list);
	}

	private void validateProcCode(List<MessageInfo> list, Procedure procedure)
	{
		ValidationUtil.isNullOrEmpty(procedure.getCode(), SYSMGMT_BASE_PROCEDUREVALIDATOR_PROCCODE_REQUIRED, list);
	}

	private void validateProcDesc(List<MessageInfo> list, Procedure procedure)
	{
		ValidationUtil.isNullOrEmpty(procedure.getDescription(), SYSMGMT_BASE_PROCEDUREVALIDATOR_PROCDESC_REQUIRED,
				list);
	}
}
