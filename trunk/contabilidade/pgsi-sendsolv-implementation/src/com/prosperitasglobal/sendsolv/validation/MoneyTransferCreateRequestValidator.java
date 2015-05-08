package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferCreateRequest;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.Message;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.UserContextHelper;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MoneyTransferCreateRequestValidator.
 */
public class MoneyTransferCreateRequestValidator implements IValidator
{

	private static final String MONEY_TRANSFER_CREATE_REQUEST_VALIDATOR_PAY_PREPARATION_DATE_OUT_OF_RANGE =
			"sendsolv.base.moneytransfercreaterequestvalidator.pay.preparation.date.out.of.range";

	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_USERCONTEXT_REQUIRED =
			"sendsolv.base.businessvalidator.usercontext.required";

	/** The Constant MONEY_TRANSFER_CREATE_REQUEST_EMPLOYEE_INFO_REQUIRED. */
	private static final String MONEY_TRANSFER_CREATE_REQUEST_EMPLOYEE_INFO_REQUIRED =
			"sendsolv.base.moneytransfercreaterequestvalidator.employeeinfo.required";

	/** The Constant MONEY_TRANSFER_CREATE_REQUEST_TRANSFER_SETTING_REQUIRED. */
	private static final String MONEY_TRANSFER_CREATE_REQUEST_TRANSFER_SETTING_REQUIRED =
			"sendsolv.base.moneytransfercreaterequestvalidator.transfersetting.required";

	/** The Constant MONEY_TRANSFER_CREATE_REQUEST_REQUIRED. */
	private static final String MONEY_TRANSFER_CREATE_REQUEST_REQUIRED =
			"sendsolv.base.moneytransfercreaterequestvalidator.moneytransfercreaterequest.required";

	/** The Constant MEMBER_NOT_ACTIVE. */
	private static final String MEMBER_NOT_ACTIVE =
			"sendsolv.base.moneytransfercreaterequestvalidator.member.not.active";

	/** The Constant NO_ACTIVE_ID_DOCUMENT_FOR_MEMBER. */
	private static final String NO_ACTIVE_ID_DOCUMENT_FOR_MEMBER =
			"sendsolv.base.processor.moneytransferbatchtransfersettingitemprocessor.no.active.iddocument.for.member";

	/** The Constant RECIPIENT_NOT_ACTIVE. */
	private static final String RECIPIENT_NOT_ACTIVE =
			"sendsolv.base.moneytransfercreaterequestvalidator.recipient.not.active";

	/** The Constant TRANSFER_SETTING_NOT_ACTIVE. */
	private static final String TRANSFER_SETTING_NOT_ACTIVE =
			"sendsolv.base.moneytransfercreaterequestvalidator.transfersetting.not.active";

	/** The Constant EMPLOYEE_INFO_NOT_ACTIVE. */
	private static final String EMPLOYEE_INFO_NOT_ACTIVE =
			"sendsolv.base.moneytransfercreaterequestvalidator.employeeinfo.not.active";

	/** The Constant LOCATION_NOT_ACTIVE. */
	private static final String LOCATION_NOT_ACTIVE =
			"sendsolv.base.moneytransfercreaterequestvalidator.location.not.active";

	/** The error code to set when the id used for a fetch is null. */
	private static final String NULL_OBJECT_ID_ERROR = "sendsolv.base.object.id.missing.for.fetch.error";

	/** The error code to set when a fetch by id error is encountered. */
	private static final String FETCH_FAILED_ERROR = "sendsolv.base.error.fetching.object.error";

	/** The person dac. */
	private IPersonDAC personDAC;

	/** The location dac. */
	private ILocationDAC locationDAC;

	/**
	 * Gets the person dac.
	 *
	 * @return the personDAC
	 */
	public IPersonDAC getPersonDAC()
	{
		return personDAC;
	}

	/**
	 * Sets the person dac.
	 *
	 * @param personDAC the personDAC to set
	 */
	public void setPersonDAC(IPersonDAC personDAC)
	{
		this.personDAC = personDAC;
	}

	/**
	 * Gets the location dac.
	 *
	 * @return the locationDAC
	 */
	public ILocationDAC getLocationDAC()
	{
		return locationDAC;
	}

	/**
	 * Sets the location dac.
	 *
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
		MoneyTransferCreateRequest moneyTransferCreateRequest =
				(MoneyTransferCreateRequest)context.getObjectToBeValidated(MoneyTransferCreateRequest.class
						.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(moneyTransferCreateRequest))
		{
			context.getMessages().add(
					new MessageInfo(MONEY_TRANSFER_CREATE_REQUEST_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			context.setStopProcessing(true);
			return;
		}

		if (ValidationUtil.isNull(moneyTransferCreateRequest.getUserContext()))
		{
			context.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_USERCONTEXT_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			context.setStopProcessing(true);
			return;
		}

		UserContextHelper.setUserContext(moneyTransferCreateRequest.getUserContext());

		// Validate the specific items for a money transfer batch create request.
		performValidation(context, moneyTransferCreateRequest);

	}

	/**
	 * Perform validation.
	 *
	 * @param context the context
	 * @param moneyTransferCreateRequest the money transfer create request
	 */
	private void performValidation(ValidationContext context,
			MoneyTransferCreateRequest moneyTransferCreateRequest)
	{
		switch (context.getValidationContextIndicator())
		{
			case INSERT:
				validationAll(context.getMessages(), moneyTransferCreateRequest);
				break;
			case UPDATE:
				break;
			case DELETE:
				break;
			default:
				break;
		}
	}

	/**
	 * Validation all.
	 *
	 * @param list the list
	 * @param moneyTransferCreateRequest the money transfer create request
	 */
	private void validationAll(List<MessageInfo> list, MoneyTransferCreateRequest moneyTransferCreateRequest)
	{
		validateTransferSetting(list, moneyTransferCreateRequest);
		if (!ValidationUtil.isNullOrEmpty(list))
		{
			return;
		}
		validateMember(list, moneyTransferCreateRequest);
		validateRecipient(list, moneyTransferCreateRequest);
		validateEmploymentInfo(list, moneyTransferCreateRequest);
		validatePaymentPreparationDateRange(list, moneyTransferCreateRequest);
	}

	/**
	 * Validate member.
	 *
	 * @param list the list
	 * @param moneyTransferCreateRequest the money transfer create request
	 */
	private void validateMember(List<MessageInfo> list, MoneyTransferCreateRequest moneyTransferCreateRequest)
	{

		if (ValidationUtil.isNull(moneyTransferCreateRequest.getTransferSetting().getMemberId()))
		{
			list.add(new MessageInfo(NULL_OBJECT_ID_ERROR, MessageSeverity.Fatal, MessageLevel.Object,
					new Object[] {"memberId", TransferSetting.class.getSimpleName(),
							moneyTransferCreateRequest.getTransferSetting().getKey()}));
			return;
		}

		InternalResultsResponse<Member> memberResponse =
				getPersonDAC().fetchMemberById(
						new FetchByIdRequest(moneyTransferCreateRequest.getTransferSetting().getMemberId()));

		if (memberResponse.isInError())
		{
			list.add(new MessageInfo(FETCH_FAILED_ERROR, MessageSeverity.Fatal, MessageLevel.Object,
					new Object[] {Member.class.getSimpleName(),
					moneyTransferCreateRequest.getTransferSetting().getMemberId()}));
			return;
		}

		Member member = memberResponse.getFirstResult();

		if (!StatusEnum.ACTIVE.equals(member.getPersonStatus()))
		{
			list.add(new MessageInfo(MEMBER_NOT_ACTIVE, MessageSeverity.Fatal,
					MessageLevel.Field,
					new Object[] {member.getParticipantId(), member.getPersonStatus(),
					moneyTransferCreateRequest.getTransferSetting().getKey()}));
			return;
		}

		boolean activeDocumentFound = false;
		if (!ValidationUtil.isNullOrEmpty(member.getDocumentList()))
		{
			Long expirationDate = null;
			Long currentDate = PGSiDateUtil.getCurrentDateMillisUTC();

			for (Document document : member.getDocumentList())
			{
				expirationDate = document.getExpirationDate();

				if (!ValidationUtil.isNull(expirationDate)
						&& (PGSiDateUtil.compareDates(expirationDate, currentDate) > 0))
				{
					activeDocumentFound = true;
					break;
				}
			}
		}

		if (!activeDocumentFound)
		{
			list.add(new MessageInfo(NO_ACTIVE_ID_DOCUMENT_FOR_MEMBER, MessageSeverity.Fatal,
					MessageLevel.Field,
					new Object[] {member.getParticipantId()}));
			return;
		}

	}

	/**
	 * Validate recipient.
	 *
	 * @param list the list
	 * @param moneyTransferCreateRequest the money transfer create request
	 */
	private void validateRecipient(List<MessageInfo> list, MoneyTransferCreateRequest moneyTransferCreateRequest)
	{

		if (ValidationUtil.isNull(moneyTransferCreateRequest.getTransferSetting().getRecipientId()))
		{
			list.add(new MessageInfo(NULL_OBJECT_ID_ERROR, MessageSeverity.Fatal, MessageLevel.Object,
					new Object[] {"recipientId", TransferSetting.class.getSimpleName(),
							moneyTransferCreateRequest.getTransferSetting().getKey()}));
			return;
		}

		InternalResultsResponse<Recipient> response =
				getPersonDAC().fetchRecipientById(
						new FetchByIdRequest(moneyTransferCreateRequest.getTransferSetting().getRecipientId()));

		if (response.isInError())
		{
			list.add(new MessageInfo(FETCH_FAILED_ERROR, MessageSeverity.Fatal, MessageLevel.Object,
					new Object[] {Recipient.class.getSimpleName(),
					moneyTransferCreateRequest.getTransferSetting().getRecipientId()}));
			return;
		}

		Recipient recipient = response.getFirstResult();

		if (!StatusEnum.ACTIVE.equals(recipient.getPersonStatus()))
		{
			list.add(new MessageInfo(RECIPIENT_NOT_ACTIVE, MessageSeverity.Fatal,
					MessageLevel.Field,
					new Object[] {recipient.getParticipantId()}));
			return;
		}
	}

	/**
	 * Validate transfer setting.
	 *
	 * @param list the list
	 * @param moneyTransferCreateRequest the money transfer create request
	 */
	private void validateTransferSetting(List<MessageInfo> list, MoneyTransferCreateRequest moneyTransferCreateRequest)
	{
		if (ValidationUtil.isNull(moneyTransferCreateRequest.getTransferSetting()))
		{
			list.add(new MessageInfo(MONEY_TRANSFER_CREATE_REQUEST_TRANSFER_SETTING_REQUIRED, MessageSeverity.Fatal,
					MessageLevel.Field));
			return;

		}

		if (!StatusEnum.ACTIVE.equals(moneyTransferCreateRequest.getTransferSetting().getStatus()))
		{
			list.add(new MessageInfo(TRANSFER_SETTING_NOT_ACTIVE, MessageSeverity.Fatal,
					MessageLevel.Field,
					new Object[] {moneyTransferCreateRequest.getTransferSetting().getKey()}));
			return;
		}

	}

	/**
	 * Validate employment info.
	 *
	 * @param list the list
	 * @param moneyTransferCreateRequest the money transfer create request
	 */
	private void validateEmploymentInfo(List<MessageInfo> list, MoneyTransferCreateRequest moneyTransferCreateRequest)
	{
		if (ValidationUtil.isNull(moneyTransferCreateRequest.getTransferSetting().getEmploymentInfo()))
		{
			list.add(new MessageInfo(MONEY_TRANSFER_CREATE_REQUEST_EMPLOYEE_INFO_REQUIRED, MessageSeverity.Fatal,
					MessageLevel.Field));
			return;
		}

		if (!StatusEnum.ACTIVE.equals(moneyTransferCreateRequest.getTransferSetting().getEmploymentInfo()
				.getEmploymentInfoStatus()))
		{
			list.add(new MessageInfo(EMPLOYEE_INFO_NOT_ACTIVE, MessageSeverity.Fatal,
					MessageLevel.Field,
					new Object[] {moneyTransferCreateRequest.getTransferSetting().getKey()}));
			return;
		}

		InternalResultsResponse<Location> locResponse =
				getLocationDAC().fetchLocationById(
						new FetchByIdRequest(moneyTransferCreateRequest.getTransferSetting().getEmploymentInfo()
								.getLocationId()));

		if (locResponse.isInError())
		{
			list.add(new MessageInfo(FETCH_FAILED_ERROR, MessageSeverity.Fatal, MessageLevel.Object,
					new Object[] {Location.class.getSimpleName(),
					moneyTransferCreateRequest.getTransferSetting().getEmploymentInfo().getLocationId()}));
			return;
		}

		if (!StatusEnum.ACTIVE.equals(locResponse.getFirstResult().getStatus()))
		{
			list.add(new MessageInfo(LOCATION_NOT_ACTIVE, MessageSeverity.Fatal,
					Message.MessageLevel.Field,
					new Object[] {locResponse.getFirstResult().getKey()}));
			return;
		}
	}

	/**
	 * Validate payment preparation date range.
	 *
	 * @param list the list
	 * @param moneyTransferCreateRequest the money transfer create request
	 */
	private void validatePaymentPreparationDateRange(List<MessageInfo> list,
			MoneyTransferCreateRequest moneyTransferCreateRequest)
	{
		if (!ValidationUtil.isNull(moneyTransferCreateRequest.getMoneyTransferBatch()))
		{
			if ((PGSiDateUtil.compareDates(moneyTransferCreateRequest.getTransferSetting().getEffectiveStartDate(),
					moneyTransferCreateRequest.getMoneyTransferBatch().getPayPreparationDate()) <= 0)
					&& (ValidationUtil.isNull(moneyTransferCreateRequest.getTransferSetting().getEffectiveEndDate()) ||
							(PGSiDateUtil.compareDates(
									moneyTransferCreateRequest.getTransferSetting().getEffectiveEndDate(),
									moneyTransferCreateRequest.getMoneyTransferBatch().getPayPreparationDate()) >= 0)))
			{
				return;
			}

			list.add(new MessageInfo(MONEY_TRANSFER_CREATE_REQUEST_VALIDATOR_PAY_PREPARATION_DATE_OUT_OF_RANGE,
					MessageSeverity.Fatal,
					Message.MessageLevel.Field));

		}
	}
}
