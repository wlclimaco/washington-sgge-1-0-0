package com.prosperitasglobal.sendsolv.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.model.EmploymentInfo;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.PersonSecurityAnswer;
import com.prosperitasglobal.sendsolv.model.PersonTypeEnum;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MemberValidator.
 */
public class MemberValidator extends PersonValidator implements IValidator
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MemberValidator.class);

	/** The Constant SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_ID_REQUIRED. */
	private static final String SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_ID_REQUIRED =
			"sendsolv.base.membervalidator.securityanswer.id.required";

	/** The Constant SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_DUPLICATED_ANSWER. */
	private static final String SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_DUPLICATED_ANSWER =
			"sendsolv.base.membervalidator.securityanswer.duplicated.answer";

	/** The Constant SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_ANSWER_REQUIRED. */
	private static final String SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_ANSWER_REQUIRED =
			"sendsolv.base.membervalidator.securityanswer.answer.required";

	/** The Constant SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_NO_LESS_THAN_THREE_QUESTIONS_ALLOWED. */
	private static final String SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_NO_LESS_THAN_THREE_QUESTIONS_ALLOWED =
			"sendsolv.base.membervalidator.securityanswer.no.less.than.three.questions.allowed";

	/** The Constant PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_PINNUMBER_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_PINNUMBER_REQUIRED =
			"sendsolv.base.membervalidator.pinnumber.required";

	/** The Constant CBOF_BASE_APPLYSTATUS_STATUS_DENIED. */
	private static final String CBOF_BASE_APPLYSTATUS_STATUS_DENIED = "cbof.base.applystatus.status.denied";

	/** The Constant COUNTRY_USAGE_LIST_KEY. */
	protected static final String COUNTRY_USAGE_LIST_KEY = "CountryUsageList";

	/** The Constant DOCUMENT_LIST_KEY. */
	protected static final String DOCUMENT_LIST_KEY = "DocumentList";

	/** The Constant EMPLOYMENT_INFO_LIST_KEY. */
	protected static final String EMPLOYMENT_INFO_LIST_KEY = "EmploymentInfoList";

	/** The Constant TRANSFER_SETTING_LIST_KEY. */
	protected static final String TRANSFER_SETTING_LIST_KEY = "TransferSettingList";

	/** The Constant NOTE_LIST_KEY. */
	private static final String NOTE_LIST_KEY = "NoteList";

	/** The Constant FIFTY. */
	private static final Integer FIFTY = 50;

	/** The Constant PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_BESTTIMETOCALL_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_BESTTIMETOCALL_TOO_BIG =
			"sendsolv.base.membervalidator.besttimetocall.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_MEMBER_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_MEMBER_REQUIRED =
			"sendsolv.base.membervalidator.member.required";

	/** The Constant THREE. */
	private static final int THREE = 3;

	/** The location dac. */
	private ILocationDAC locationDAC;

	/**
	 * @return the locationDAC
	 */
	public ILocationDAC getLocationDAC()
	{
		return locationDAC;
	}

	/**
	 * @param locationDAC the locationDAC to set
	 */
	public void setLocationDAC(ILocationDAC locationDAC)
	{
		this.locationDAC = locationDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext context)
	{
		Member member =
				(Member)context.getObjectToBeValidated(Member.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(member))
		{
			context.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_MEMBER_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			context.setStopProcessing(true);
			return;
		}
		switch (context.getValidationContextIndicator())
		{
			case INSERT:
			case UPDATE:
				validateAdditionalFields(context, member);
				break;
			case FETCH_BY_ID:
				validateApplyStatus(context, member);
				break;
			default:
				break;
		}

		super.performValidation(context, member);

		context.getObjectsToBeValidated().put(COUNTRY_USAGE_LIST_KEY, member.getCountryUsageList());

		if (!ValidationUtil.isNullOrEmpty(member.getEmploymentInfoList()))
		{
			context.getObjectsToBeValidated().put(EMPLOYMENT_INFO_LIST_KEY, member.getEmploymentInfoList());
		}

		if (!ValidationUtil.isNullOrEmpty(member.getTransferSettingList()))
		{
			context.getObjectsToBeValidated().put(TRANSFER_SETTING_LIST_KEY, member.getTransferSettingList());

			for (TransferSetting transferSetting : member.getTransferSettingList())
			{
				if (!ValidationUtil.isNullOrEmpty(transferSetting.getNoteList()))
				{
					context.getObjectsToBeValidated().put(NOTE_LIST_KEY, transferSetting.getNoteList());
				}
			}
		}

	}

	/**
	 * Validate additional fields.
	 *
	 * @param context the context
	 * @param member the member
	 */
	private void validateAdditionalFields(ValidationContext context, Member member)
	{
		validateBestTimeToCall(context.getMessages(), member);

		if (!PersistanceActionEnum.NONE.equals(member.getModelAction()))
		{
			validateSecurityAnswerList(context, member);
		}
	}

	/**
	 * Validate security answer list.
	 *
	 * @param context the context
	 * @param member the member
	 */
	private void validateSecurityAnswerList(ValidationContext context, Member member)
	{

		if (ValidationUtil.isNullOrEmpty(member.getSecurityAnswerList())
				|| (member.getSecurityAnswerList().size() < THREE))
		{
			context.addMessage(new MessageInfo(
					SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_NO_LESS_THAN_THREE_QUESTIONS_ALLOWED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}

		Set<Integer> set = new HashSet<Integer>();

		for (PersonSecurityAnswer personSecurityAnswer : member.getSecurityAnswerList())
		{
			validateSecurityAnswer(context, personSecurityAnswer);

			set.add(personSecurityAnswer.getSecurityQuestion().getId());

			if (!PersistanceActionEnum.DELETE.equals(personSecurityAnswer.getModelAction()))
			{
				validateChangeControlFields(context.getMessages(), personSecurityAnswer, context);
			}
		}

		if (set.size() < member.getSecurityAnswerList().size())
		{
			context.addMessage(new MessageInfo(
					SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_DUPLICATED_ANSWER,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate security answer.
	 *
	 * @param context the context
	 * @param personSecurityAnswer the person security answer
	 */
	private void validateSecurityAnswer(ValidationContext context, PersonSecurityAnswer personSecurityAnswer)
	{
		switch (personSecurityAnswer.getModelAction())
		{
			case INSERT:
				validateAnswerText(context.getMessages(), personSecurityAnswer);
				break;
			case UPDATE:
				validateId(context.getMessages(), personSecurityAnswer);
				validateAnswerText(context.getMessages(), personSecurityAnswer);
				break;
			case DELETE:
				validateId(context.getMessages(), personSecurityAnswer);
				break;
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("Model Action for Person Security Answer Validator missing!");
				}
				break;
		}
	}

	/**
	 * Validate answer text.
	 *
	 * @param list the list
	 * @param personSecurityAnswer the person security answer
	 */
	private void validateAnswerText(List<MessageInfo> list, PersonSecurityAnswer personSecurityAnswer)
	{
		if (ValidationUtil.isNullOrEmpty(personSecurityAnswer.getAnswerText()))
		{
			list.add(new MessageInfo(SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_ANSWER_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}

	}

	/**
	 * Validate id.
	 *
	 * @param list the list
	 * @param personSecurityAnswer the person security answer
	 */
	private void validateId(List<MessageInfo> list, PersonSecurityAnswer personSecurityAnswer)
	{
		if (ValidationUtil.isNullOrZero(personSecurityAnswer.getId()))
		{
			list.add(new MessageInfo(SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_ID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate best time to call.
	 *
	 * @param list the list
	 * @param member the member
	 */
	private void validateBestTimeToCall(List<MessageInfo> list, Member member)
	{
		if (ValidationUtil.isNull(member.getBestTimeToCall()))
		{
			return;
		}
		if (member.getBestTimeToCall().length() > FIFTY)
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_BESTTIMETOCALL_TOO_BIG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate apply status.
	 *
	 * @param context the context
	 * @param member the member
	 */
	private void validateApplyStatus(ValidationContext context, Member member)
	{
		validatePinNumber(context.getMessages(), member);
		validateParentStatus(context.getMessages(), member);
	}

	/**
	 * Validate parent status.
	 *
	 * @param list the list
	 * @param member the member
	 */
	private void validateParentStatus(List<MessageInfo> list, Member member)
	{
		InternalResponse applyStatusResponse = new InternalResponse();
		FetchByIdRequest request = new FetchByIdRequest();

		for (EmploymentInfo employmentInfo : member.getEmploymentInfoList())
		{
			request.setId(employmentInfo.getLocationId());
			InternalResultsResponse<Location> response = getLocationDAC().fetchLocationById(request);

			if (response.isInError())
			{
				applyStatusResponse.merge(response);
				break;
			}

			if (StatusEnum.SETUP.equals(response.getFirstResult().getStatus()))
			{
				list.add(
						new MessageInfo(CBOF_BASE_APPLYSTATUS_STATUS_DENIED,
								Message.MessageSeverity.Error,
								Message.MessageLevel.Field,
								new Object[] {String.valueOf(PersonTypeEnum.MEMBER).toLowerCase(),
								member.getPersonStatus()}));
				return;
			}
		}

		if (!ValidationUtil.isNull(applyStatusResponse))
		{
			list.addAll(applyStatusResponse.getMessageInfoList());
		}
	}

	/**
	 * Validate pin number.
	 *
	 * @param list the list
	 * @param member the member
	 */
	private void validatePinNumber(List<MessageInfo> list, Member member)
	{
		if (ValidationUtil.isNullOrEmpty(member.getPinNumber()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_PINNUMBER_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {member.getPersonType() + " " + validateValueToBeReturn(member)}));
		}

	}
}
