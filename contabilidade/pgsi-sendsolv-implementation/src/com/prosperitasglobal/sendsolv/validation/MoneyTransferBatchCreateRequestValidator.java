package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.dac.IOrganizationDAC;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchCreateRequest;
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
 * The Class MoneyTransferBatchCreateRequestValidator.
 */
public class MoneyTransferBatchCreateRequestValidator implements IValidator
{

	/** The Constant MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_PAY_DATE_REQUIRED. */
	private static final String MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_PAY_DATE_REQUIRED =
			"sendsolv.base.moneytransferbatchcreaterequestvalidator.pay.date.required";

	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_USERCONTEXT_REQUIRED =
			"sendsolv.base.businessvalidator.usercontext.required";

	/** The Constant MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_PAYMENT_PREPARATION_DATE_REQUIRED. */
	private static final String MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_PAYMENT_PREPARATION_DATE_REQUIRED =
			"sendsolv.base.moneytransferbatchcreaterequestvalidator.payment.preparation.date.required";

	/** The Constant MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_LOCATION_REQUIRED. */
	private static final String MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_LOCATION_REQUIRED =
			"sendsolv.base.moneytransferbatchcreaterequestvalidator.location.required";

	/** The Constant MONEY_TRANSFER_BATCH_CREATE_REQUEST_REQUIRED. */
	private static final String MONEY_TRANSFER_BATCH_CREATE_REQUEST_REQUIRED =
			"sendsolv.base.moneytransferbatchcreaterequestvalidator.moneytransferbatchcreaterequest.required";

	/** The Constant LOCATION_NOT_ACTIVE. */
	private static final String LOCATION_NOT_ACTIVE =
			"sendsolv.base.moneytransfercreaterequestvalidator.location.not.active";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_LOCATION_PARENT_INACTIVE. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_LOCATION_PARENT_NOT_ACTIVE =
			"sendsolv.base.businessvalidator.location.parent.not.active";

	/** The error code to set when the id used for a fetch is null. */
	private static final String NULL_OBJECT_ID_ERROR = "sendsolv.base.object.id.missing.for.fetch.error";

	/** The error code to set when a fetch by id error is encountered. */
	private static final String FETCH_FAILED_ERROR = "sendsolv.base.error.fetching.object.error";

	/** The location dac. */
	private ILocationDAC locationDAC;

	/** The organization dac. */
	private IOrganizationDAC organizationDAC;

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

	/**
	 * Gets the organization dac.
	 *
	 * @return the organizationDAC
	 */
	public IOrganizationDAC getOrganizationDAC()
	{
		return organizationDAC;
	}

	/**
	 * Sets the organization dac.
	 *
	 * @param organizationDAC the organizationDAC to set
	 */
	public void setOrganizationDAC(IOrganizationDAC organizationDAC)
	{
		this.organizationDAC = organizationDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext context)
	{
		MoneyTransferBatchCreateRequest moneyTransferBatchCreate =
				(MoneyTransferBatchCreateRequest)context.getObjectToBeValidated(MoneyTransferBatchCreateRequest.class
						.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(moneyTransferBatchCreate))
		{
			context.getMessages().add(
					new MessageInfo(MONEY_TRANSFER_BATCH_CREATE_REQUEST_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			context.setStopProcessing(true);
			return;
		}

		if (ValidationUtil.isNull(moneyTransferBatchCreate.getUserContext()))
		{
			context.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_USERCONTEXT_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			context.setStopProcessing(true);
			return;
		}

		UserContextHelper.setUserContext(moneyTransferBatchCreate.getUserContext());

		// Validate the specific items for a money transfer batch create request.
		performValidation(context, moneyTransferBatchCreate);

	}

	/**
	 * Perform validation.
	 *
	 * @param context the context
	 * @param moneyTransferBatchCreateRequest the money transfer batch create request
	 */
	private void performValidation(ValidationContext context,
			MoneyTransferBatchCreateRequest moneyTransferBatchCreateRequest)
	{
		switch (context.getValidationContextIndicator())
		{
			case INSERT:
				validateAll(context.getMessages(), moneyTransferBatchCreateRequest);
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
	 * Validate location.
	 *
	 * @param list the list
	 * @param moneyTransferBatchCreateRequest the money transfer batch create request
	 */
	private void validateLocation(List<MessageInfo> list,
			MoneyTransferBatchCreateRequest moneyTransferBatchCreateRequest)
	{
		if (ValidationUtil.isNull(moneyTransferBatchCreateRequest.getLocation()))
		{
			list.add(
					new MessageInfo(MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_LOCATION_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}

		if (ValidationUtil.isNull(moneyTransferBatchCreateRequest.getLocation().getId()))
		{
			list.add(
					new MessageInfo(NULL_OBJECT_ID_ERROR, MessageSeverity.Fatal, MessageLevel.Object,
							new Object[] {"locationId", Location.class.getSimpleName(),
									moneyTransferBatchCreateRequest.getLocation().getKey()}));
			return;
		}

		FetchByIdRequest request = new FetchByIdRequest(moneyTransferBatchCreateRequest.getLocation().getId());
		InternalResultsResponse<Location> locResponse =
				getLocationDAC().fetchLocationById(request);

		if (locResponse.isInError())
		{
			list.add(new MessageInfo(FETCH_FAILED_ERROR, MessageSeverity.Fatal, MessageLevel.Object,
					new Object[] {Location.class.getSimpleName(),
							moneyTransferBatchCreateRequest.getLocation().getId()}));
			return;
		}

		if (!StatusEnum.ACTIVE.equals(locResponse.getFirstResult().getStatus()))
		{
			list.add(new MessageInfo(LOCATION_NOT_ACTIVE, MessageSeverity.Fatal,
					Message.MessageLevel.Field,
					new Object[] {locResponse.getFirstResult().getKey(), locResponse.getFirstResult().getStatus()}));
			return;
		}

		if (ValidationUtil.isNull(locResponse.getFirstResult().getParentOrganizationId()))
		{
			list.add(new MessageInfo(NULL_OBJECT_ID_ERROR, MessageSeverity.Fatal, MessageLevel.Object,
					new Object[] {"parentOrganizationId", Location.class.getSimpleName(),
							locResponse.getFirstResult().getKey()}));
			return;
		}
		request.setId(locResponse.getFirstResult().getParentOrganizationId());
		InternalResultsResponse<Organization> orgResponse =
				getOrganizationDAC().fetchOrganizationById(request);

		if (orgResponse.isInError())
		{
			list.add(new MessageInfo(FETCH_FAILED_ERROR, MessageSeverity.Fatal, MessageLevel.Object,
					new Object[] {Organization.class.getSimpleName(),
					locResponse.getFirstResult().getParentOrganizationId()}));
			return;
		}

		if (!StatusEnum.ACTIVE.equals(orgResponse.getFirstResult().getStatus()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_LOCATION_PARENT_NOT_ACTIVE,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}
	}

	/**
	 * Validate all.
	 *
	 * @param list the list
	 * @param moneyTransferBatchCreateRequest the money transfer batch create request
	 */
	private void validateAll(List<MessageInfo> list,
			MoneyTransferBatchCreateRequest moneyTransferBatchCreateRequest)
	{
		validateLocation(list, moneyTransferBatchCreateRequest);
		validatePaymentPreparationDate(list, moneyTransferBatchCreateRequest);
		validatePayDate(list, moneyTransferBatchCreateRequest);
	}

	/**
	 * Validate payment preparation date.
	 *
	 * @param list the list
	 * @param moneyTransferBatchCreateRequest the money transfer batch create request
	 */
	private void validatePaymentPreparationDate(List<MessageInfo> list,
			MoneyTransferBatchCreateRequest moneyTransferBatchCreateRequest)
	{
		ValidationUtil.isNull(moneyTransferBatchCreateRequest.getPaymentPreparationDate(),
				MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_PAYMENT_PREPARATION_DATE_REQUIRED, list);
	}

	/**
	 * Validate pay date.
	 *
	 * @param list the list
	 * @param moneyTransferBatchCreateRequest the money transfer batch create request
	 */
	private void validatePayDate(List<MessageInfo> list,
			MoneyTransferBatchCreateRequest moneyTransferBatchCreateRequest)
	{
		ValidationUtil.isNull(moneyTransferBatchCreateRequest.getPayDate(),
				MONEY_TRANSFER_BATCH_CREATE_REQUEST_VALIDATOR_PAY_DATE_REQUIRED, list);
	}
}
