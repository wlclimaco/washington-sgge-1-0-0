package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.model.SuspiciousActivity;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.UserContext;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class SuspiciousActivityValidator.
 */
public class SuspiciousActivityValidator extends ChangeControlValidator implements IValidator
{
	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_ID_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_ID_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.id.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_SUMMARY_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_SUMMARY_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.summary.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_DETAIL_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_DETAIL_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.detail.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_STARTDATE_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_STARTDATE_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.startdate.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_STOPDATE_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_STOPDATE_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.stopdate.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_SARSTATUS_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_SARSTATUS_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.sarstatus.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_TYPE_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_TYPE_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.type.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_STATUS_DATE_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_STATUS_DATE_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.statusdate.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_PERSONORBUSINESSLIST_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_PERSONORBUSINESSLIST_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.personorbusinesslist.required";

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext context)
	{
		SuspiciousActivity suspiciousActivity =
				(SuspiciousActivity)context.getObjectToBeValidated(SuspiciousActivity.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(suspiciousActivity))
		{
			context.getMessages().add(
					new MessageInfo("sendsolv.base.liaisonvalidator.suspiciousactivity.required",
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			context.setStopProcessing(true);
			return;
		}

		// Validate the specific items for a suspicious activity.
		performValidation(context, suspiciousActivity);

	}

	/**
	 * Perform validation.
	 *
	 * @param context the context
	 * @param suspiciousActivity the suspicious activity
	 */
	protected void performValidation(ValidationContext context, SuspiciousActivity suspiciousActivity)
	{
		switch (context.getValidationContextIndicator())
		{
			case INSERT:
				validateAll(context.getMessages(), suspiciousActivity);
				break;
			case UPDATE:
				validateId(context.getMessages(), suspiciousActivity.getBusinessKey());
				validateAll(context.getMessages(), suspiciousActivity);
				break;
			case DELETE:
				validateId(context.getMessages(), suspiciousActivity.getId());
				break;
			default:
				break;
		}

		validateChangeControlFields(context.getMessages(), suspiciousActivity, context);

		if (ValidationContextIndicator.DELETE.equals(context.getValidationContextIndicator()))
		{
			context.setStopProcessing(true);
			return;
		}
	}

	/**
	 * Validate all.
	 *
	 * @param messages the messages
	 * @param suspiciousActivity the suspicious activity
	 */
	private void validateAll(List<MessageInfo> messages, SuspiciousActivity suspiciousActivity)
	{
		validateSummary(messages, suspiciousActivity);
		validateDetail(messages, suspiciousActivity);
		validateActivityDates(messages, suspiciousActivity);
		validateSarStatus(messages, suspiciousActivity);
		validateType(messages, suspiciousActivity);
		validateStatusDate(messages, suspiciousActivity);
		validatePersonAndBusinessList(messages, suspiciousActivity);
	}

	/**
	 * Validate id.
	 *
	 * @param messages the messages
	 * @param id the id
	 */
	private void validateId(List<MessageInfo> messages, Integer id)
	{
		ValidationUtil.isNullOrZero(id,
				SUSPICIOUS_ACTIVITY_VALIDATOR_ID_REQUIRED, messages);
	}

	/**
	 * Validate id.
	 *
	 * @param messages the messages
	 * @param stringId the string id
	 */
	private void validateId(List<MessageInfo> messages, String stringId)
	{
		ValidationUtil.isNullOrEmpty(stringId,
				SUSPICIOUS_ACTIVITY_VALIDATOR_ID_REQUIRED, messages);
	}

	/**
	 * Validate summary.
	 *
	 * @param messages the messages
	 * @param suspiciousActivity the suspicious activity
	 */
	private void validateSummary(List<MessageInfo> messages, SuspiciousActivity suspiciousActivity)
	{
		ValidationUtil.isNullOrEmpty(suspiciousActivity.getSummary(),
				SUSPICIOUS_ACTIVITY_VALIDATOR_SUMMARY_REQUIRED, messages);
	}

	/**
	 * Validate detail.
	 *
	 * @param messages the messages
	 * @param suspiciousActivity the suspicious activity
	 */
	private void validateDetail(List<MessageInfo> messages, SuspiciousActivity suspiciousActivity)
	{
		ValidationUtil.isNullOrEmpty(suspiciousActivity.getDetail(),
				SUSPICIOUS_ACTIVITY_VALIDATOR_DETAIL_REQUIRED, messages);
	}

	/**
	 * Validate activity dates.
	 *
	 * @param messages the messages
	 * @param suspiciousActivity the suspicious activity
	 */
	private void validateActivityDates(List<MessageInfo> messages, SuspiciousActivity suspiciousActivity)
	{
		ValidationUtil.isNull(suspiciousActivity.getActivityStartDateTimeUTC(),
				SUSPICIOUS_ACTIVITY_VALIDATOR_STARTDATE_REQUIRED, messages);
		ValidationUtil.isNull(suspiciousActivity.getActivityStopDateTimeUTC(),
				SUSPICIOUS_ACTIVITY_VALIDATOR_STOPDATE_REQUIRED, messages);
	}

	/**
	 * Validate sar status.
	 *
	 * @param messages the messages
	 * @param suspiciousActivity the suspicious activity
	 */
	private void validateSarStatus(List<MessageInfo> messages, SuspiciousActivity suspiciousActivity)
	{
		ValidationUtil.isNull(suspiciousActivity.getStatus(),
				SUSPICIOUS_ACTIVITY_VALIDATOR_SARSTATUS_REQUIRED, messages);
	}

	/**
	 * Validate type.
	 *
	 * @param messages the messages
	 * @param suspiciousActivity the suspicious activity
	 */
	private void validateType(List<MessageInfo> messages, SuspiciousActivity suspiciousActivity)
	{
		ValidationUtil.isNull(suspiciousActivity.getType(), SUSPICIOUS_ACTIVITY_VALIDATOR_TYPE_REQUIRED,
				messages);
	}

	/**
	 * Validate status date.
	 *
	 * @param messages the messages
	 * @param suspiciousActivity the suspicious activity
	 */
	private void validateStatusDate(List<MessageInfo> messages, SuspiciousActivity suspiciousActivity)
	{
		ValidationUtil.isNull(suspiciousActivity.getStatusDateTime(),
				SUSPICIOUS_ACTIVITY_VALIDATOR_STATUS_DATE_REQUIRED, messages);
	}

	/**
	 * Validate person and business list.
	 *
	 * @param messages the messages
	 * @param suspiciousActivity the suspicious activity
	 */
	private void validatePersonAndBusinessList(List<MessageInfo> messages, SuspiciousActivity suspiciousActivity)
	{
		if (ValidationUtil.isNullOrEmpty(suspiciousActivity.getPersonList())
				&& ValidationUtil.isNullOrEmpty(suspiciousActivity.getBusinessList()))
		{
			messages.add(new MessageInfo(SUSPICIOUS_ACTIVITY_VALIDATOR_PERSONORBUSINESSLIST_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.validation.ChangeControlValidator#validateChangeControlFields(java.util.List,
	 * com.qat.framework.model.QATModel, com.qat.framework.validation.ValidationContext)
	 */
	@Override
	protected void validateChangeControlFields(List<MessageInfo> list, QATModel model,
			ValidationContext validationContext)
	{
		UserContext userContext =
				(UserContext)validationContext.getObjectToBeValidated(UserContext.class.getSimpleName());

		// do I have UserContext ?
		if (ValidationUtil.isNull(userContext))
		{
			validationContext.getMessages().add(
					new MessageInfo(
							ChangeControlValidator.PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_USERCONTEXT_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}

		if (ValidationUtil.isNullOrEmpty(userContext.getUserId()))
		{
			list.add(new MessageInfo(
					ChangeControlValidator.PROSPERITASGLOBAL_BASE_USERCONTEXTVALIDATOR_USERID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}
		model.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		model.setCreateUser(userContext.getUserId());
	}

}
