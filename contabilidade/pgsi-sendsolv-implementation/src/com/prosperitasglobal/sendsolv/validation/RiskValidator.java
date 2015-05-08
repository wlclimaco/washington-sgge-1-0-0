package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.model.Risk;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * This class focuses on validating these fields by checking for Null and NullOrEmpty. The class utilizes the
 * ValidationUtil tool of the QAT framework to perform these checks. If any one of these fields is null or nullOrEmpty
 * an error message is set informing the user that the field is required.
 */
public class RiskValidator extends ChangeControlValidator implements IValidator
{

	/** The Constant SENDSOLV_BASE_NOTEVALIDATOR_ID_REQUIRED. */
	private static final String SENDSOLV_BASE_RISKVALIDATOR_RISKLEVELNOTE_TOO_BIG =
			"sendsolv.base.base.riskvalidator.risklevelnote.toobig";

	/** The Constant SENDSOLV_BASE_NOTEVALIDATOR_PARENTKEYTYPE_REQUIRED. */
	private static final String SENDSOLV_BASE_RISKVALIDATOR_PARENTKEYTYPE_REQUIRED =
			"sendsolv.base.riskvalidator.parentkeytype.required";

	/** The Constant SENDSOLV_BASE_NOTEVALIDATOR_PARENTKEY_REQUIRED. */
	private static final String SENDSOLV_BASE_RISKVALIDATOR_PARENTKEY_REQUIRED =
			"sendsolv.base.riskvalidator.parentkey.required";

	/** The Constant SENDSOLV_BASE_NOTEVALIDATOR_NOTETEXT_REQUIRED. */
	private static final String SENDSOLV_BASE_RISKVALIDATOR_RISKLEVEL_REQUIRED =
			"sendsolv.base.riskvalidator.risklevel.required";

	/** The Constant SENDSOLV_BASE_NOTEVALIDATOR_NOTE_REQUIRED. */
	private static final String SENDSOLV_BASE_RISKVALIDATOR_RISK_REQUIRED =
			"sendsolv.base.riskvalidator.risk.required";

	/** The Constant TWO_HUNDRED_FIFTY_FIVE. */
	private static final int TWO_HUNDRED_FIFTY_FIVE = 255;

	/** The Constant SENDSOLV_BASE_VALIDATOR_VERSION_REQUIRED. */
	private static final String SENDSOLV_BASE_VALIDATOR_VERSION_REQUIRED = "sendsolv.base.validator.version.required";

	/**
	 * Switches validations depending on the validation context indicator.
	 *
	 * @param validationContext the validation context
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		Risk risk =
				(Risk)validationContext.getObjectToBeValidated(Risk.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(risk))
		{
			validationContext.getMessages().add(
					new MessageInfo(SENDSOLV_BASE_RISKVALIDATOR_RISK_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}

		switch (validationContext.getValidationContextIndicator())
		{
			case INSERT:
				validateAll(validationContext.getMessages(), risk);
				break;
			case UPDATE:
				validateAll(validationContext.getMessages(), risk);
				break;
			default:
				validateAll(validationContext.getMessages(), risk);
				break;
		}

		if (!ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
		{
			validateChangeControlFields(validationContext.getMessages(), risk, validationContext);
		}

	}

	/**
	 * Validate all.
	 *
	 * @param list the list
	 * @param risk the risk
	 */
	private void validateAll(List<MessageInfo> list, Risk risk)
	{
		validateParentKey(list, risk);
		validateParentKeyType(list, risk);
		validateRiskLevel(list, risk);
		validateRiskLevelNote(list, risk);
		validateVersion(list, risk);
	}

	/**
	 * Checks if the NumberOfLocations is null, if true then sets a NumberOfLocations required message.
	 *
	 * @param list the list
	 * @param risk the risk
	 */
	private void validateParentKeyType(List<MessageInfo> list, Risk risk)
	{
		ValidationUtil.isNull(risk.getParentKeyType(),
				SENDSOLV_BASE_RISKVALIDATOR_PARENTKEYTYPE_REQUIRED, list);
	}

	/**
	 * Checks if the NumberOfLocations is null, if true then sets a NumberOfLocations required message.
	 *
	 * @param list the list
	 * @param risk the risk
	 */
	private void validateVersion(List<MessageInfo> list, Risk risk)
	{
		ValidationUtil.isNull(risk.getVersion(),
				SENDSOLV_BASE_VALIDATOR_VERSION_REQUIRED, list);
	}

	/**
	 * Checks if the DbaName is null, if true then sets a DbaName required message.
	 *
	 * @param list the list
	 * @param risk the risk
	 */
	private void validateParentKey(List<MessageInfo> list, Risk risk)
	{
		ValidationUtil.isNullOrZero(risk.getParentKey(),
				SENDSOLV_BASE_RISKVALIDATOR_PARENTKEY_REQUIRED, list);
	}

	/**
	 * Checks if the IsPayrollCentralized is null, if true then sets a IsPayrollCentralized required message.
	 *
	 * @param list the list
	 * @param risk the risk
	 */
	private void validateRiskLevel(List<MessageInfo> list, Risk risk)
	{
		ValidationUtil.isNull(risk.getRiskLevel(),
				SENDSOLV_BASE_RISKVALIDATOR_RISKLEVEL_REQUIRED, list);
	}

	/**
	 * Validate risk level note.
	 *
	 * @param list the list
	 * @param risk the risk
	 */
	private void validateRiskLevelNote(List<MessageInfo> list, Risk risk)
	{
		if (!ValidationUtil.isNull(risk.getRiskLevelNote()))
		{
			if (risk.getRiskLevelNote().length() > TWO_HUNDRED_FIFTY_FIVE)
			{
				list.add(new MessageInfo(SENDSOLV_BASE_RISKVALIDATOR_RISKLEVELNOTE_TOO_BIG,
						Message.MessageSeverity.Error,
						Message.MessageLevel.Field));
			}
		}
	}
}
