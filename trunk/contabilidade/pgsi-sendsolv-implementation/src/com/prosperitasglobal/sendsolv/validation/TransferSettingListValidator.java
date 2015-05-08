package com.prosperitasglobal.sendsolv.validation;

import java.util.List;
import java.util.Map;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.model.CustomFee;
import com.prosperitasglobal.sendsolv.model.FrequencyBasedEvent;
import com.prosperitasglobal.sendsolv.model.FrequencyBasedEventTypeEnum;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class TransferSettingListValidator.
 */
public class TransferSettingListValidator extends ChangeControlValidator implements IValidator
{

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/** The Constant PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_MAXCYCLETOSKIP_INVALID. */
	private static final String PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_MAXCYCLETOSKIP_INVALID =
			"sendsolv.base.transfersettingvalidator.maxcycletoskip.invalid";

	/** The Constant PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_TRANSFER_TYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_TRANSFER_TYPE_REQUIRED =
			"sendsolv.base.transfersettingvalidator.transfer.type.required";

	/** The Constant TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_APPLICABILITY_PAYMENT_TYPE_REQUIRED. */
	private static final String TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_APPLICABILITY_PAYMENT_TYPE_REQUIRED =
			"sendsolv.base.transfersettingvalidator.product.plan.applicability.payment.type.required";

	/** The Constant PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_TRANSFER_AMOUNT_INVALID. */
	private static final String PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_TRANSFER_AMOUNT_INVALID =
			"sendsolv.base.transfersettingvalidator.transfer.amount.invalid";

	/** The Constant PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_APPLICABILITY_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_APPLICABILITY_ID_REQUIRED =
			"sendsolv.base.transfersettingvalidator.product.plan.applicability.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_APPLICABILITY_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_APPLICABILITY_REQUIRED =
			"sendsolv.base.transfersettingvalidator.product.plan.applicability.required";

	/** The Constant PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_CATEGORY_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_CATEGORY_ID_REQUIRED =
			"sendsolv.base.transfersettingvalidator.product.plan.category.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_CATEGORY_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_CATEGORY_REQUIRED =
			"sendsolv.base.transfersettingvalidator.product.plan.category.required";

	/** The Constant PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_REQUIRED =
			"sendsolv.base.employmentinfovalidator.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_STATUS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_STATUS_REQUIRED =
			"sendsolv.base.businessvalidator.status.required";

	/** The Constant PROSPERITASGLOBAL_ASE_EMPLOYMENTINFOVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_ASE_EMPLOYMENTINFOVALIDATOR_ID_REQUIRED =
			"sendsolv.base.employmentinfovalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_ID_REQUIRED =
			"sendsolv.base.recipientvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_ID_REQUIRED =
			"sendsolv.base.membervalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_ID_REQUIRED =
			"sendsolv.base.transfersettingvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_EXPIRESDATE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_EXPIRESDATE_REQUIRED =
			"sendsolv.base.customfeevalidator.expiresdate.required";

	/** The Constant PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_TRANSFERSETTING_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_TRANSFERSETTING_ID_REQUIRED =
			"sendsolv.base.customfeevalidator.transfersetting.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_STATUS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_STATUS_REQUIRED =
			"sendsolv.base.customfeevalidator.status.required";

	/** The Constant PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_ID_REQUIRED =
			"sendsolv.base.customfeevalidator.id.required";

	/** The Constant TRANSFER_SETTING_LIST_KEY. */
	private static final String TRANSFER_SETTING_LIST_KEY = "TransferSettingList";

	/** The frequency code event max cycle to skip. */
	private Map<Integer, Integer> frequencyCodeEventMaxCycleToSkip;

	/** The location dac. */
	private ILocationDAC locationDAC;

	/**
	 * Gets the frequency code event max cycle to skip.
	 *
	 * @return the frequency code event max cycle to skip
	 */
	public Map<Integer, Integer> getFrequencyCodeEventMaxCycleToSkip()
	{
		return frequencyCodeEventMaxCycleToSkip;
	}

	/**
	 * Sets the frequency code event max cycle to skip.
	 *
	 * @param frequencyCodeEventMaxCycleToSkip the frequency code event max cycle to skip
	 */
	public void setFrequencyCodeEventMaxCycleToSkip(Map<Integer, Integer> frequencyCodeEventMaxCycleToSkip)
	{
		this.frequencyCodeEventMaxCycleToSkip = frequencyCodeEventMaxCycleToSkip;
	}

	/**
	 * Gets the location dac.
	 *
	 * @return the location dac
	 */
	public ILocationDAC getLocationDAC()
	{
		return locationDAC;
	}

	/**
	 * Sets the location dac.
	 *
	 * @param locationDAC the location dac
	 */
	public void setLocationDAC(ILocationDAC locationDAC)
	{
		this.locationDAC = locationDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void validate(ValidationContext validationContext)
	{
		List<TransferSetting> transferSettingList =
				(List<TransferSetting>)validationContext.getObjectToBeValidated(TRANSFER_SETTING_LIST_KEY);

		if (ValidationUtil.isNullOrEmpty(transferSettingList))
		{
			return;
		}

		for (TransferSetting transferSetting : transferSettingList)
		{
			validateTransferSetting(transferSetting, validationContext);

			if (!ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
			{
				validateChangeControlFields(validationContext.getMessages(), transferSetting, validationContext);
			}
		}
	}

	/**
	 * Validate transfer setting.
	 *
	 * @param transferSetting the transfer setting
	 * @param context the context
	 */
	private void validateTransferSetting(TransferSetting transferSetting, ValidationContext context)
	{
		switch (transferSetting.getModelAction())
		{
			case INSERT:
				validateAll(context.getMessages(), transferSetting, context);
				break;
			case UPDATE:
				validateId(context.getMessages(), transferSetting);
				validateAll(context.getMessages(), transferSetting, context);
				break;
			case DELETE:
				validateId(context.getMessages(), transferSetting);
				break;
			default:
				validateAll(context.getMessages(), transferSetting, context);
				break;
		}
	}

	/**
	 * Validate all.
	 *
	 * @param list the list
	 * @param transferSetting the transfer setting
	 * @param context the context
	 */
	private void validateAll(List<MessageInfo> list, TransferSetting transferSetting, ValidationContext context)
	{
		if (PersistanceActionEnum.NONE.equals(transferSetting.getModelAction()))
		{
			return;
		}

		validateMemberId(list, transferSetting);
		validateRecipientId(list, transferSetting);
		validateProductPlanCategoryId(list, transferSetting);
		validateProductPlanApplicabilityId(list, transferSetting);
		validateEmploymentInfoId(list, transferSetting);
		validateTransferAmount(list, transferSetting);
		validatePaymentTypeCodeId(list, transferSetting);
		validateTransferTypeId(list, transferSetting);
		validateStatus(list, transferSetting);
		validateCustomFee(list, transferSetting, context);
		validateCyclesToSkip(list, transferSetting);
	}

	/**
	 * Validate id.
	 *
	 * @param list the list
	 * @param transferSetting the transfer setting
	 */
	private void validateId(List<MessageInfo> list, TransferSetting transferSetting)
	{
		if (ValidationUtil.isNullOrZero(transferSetting.getId()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_ID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate member id.
	 *
	 * @param list the list
	 * @param transferSetting the transfer setting
	 */
	private void validateMemberId(List<MessageInfo> list, TransferSetting transferSetting)
	{
		if (ValidationUtil.isNullOrZero(transferSetting.getMemberId()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_ID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate recipient id.
	 *
	 * @param list the list
	 * @param transferSetting the transfer setting
	 */
	private void validateRecipientId(List<MessageInfo> list, TransferSetting transferSetting)
	{
		if (ValidationUtil.isNullOrZero(transferSetting.getRecipientId()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_ID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate product plan category id.
	 *
	 * @param list the list
	 * @param transferSetting the transfer setting
	 */
	private void validateProductPlanCategoryId(List<MessageInfo> list, TransferSetting transferSetting)
	{
		if (ValidationUtil.isNull(transferSetting.getPlanCategory()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_CATEGORY_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}

		if (ValidationUtil.isNullOrZero(transferSetting.getPlanCategory().getId()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_CATEGORY_ID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate product plan applicability id.
	 *
	 * @param list the list
	 * @param transferSetting the transfer setting
	 */
	private void validateProductPlanApplicabilityId(List<MessageInfo> list, TransferSetting transferSetting)
	{
		if (ValidationUtil.isNull(transferSetting.getProductPlanApplicability()))
		{
			list.add(new MessageInfo(
					PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_APPLICABILITY_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}

		if (ValidationUtil.isNullOrZero(transferSetting.getProductPlanApplicability().getId()))
		{
			list.add(new MessageInfo(
					PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_APPLICABILITY_ID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate employment info id.
	 *
	 * @param list the list
	 * @param transferSetting the transfer setting
	 */
	private void validateEmploymentInfoId(List<MessageInfo> list, TransferSetting transferSetting)
	{
		if (ValidationUtil.isNull(transferSetting.getEmploymentInfo()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}

		if (ValidationUtil.isNullOrZero(transferSetting.getEmploymentInfo().getId()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_ASE_EMPLOYMENTINFOVALIDATOR_ID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate transfer amount.
	 *
	 * @param list the list
	 * @param transferSetting the transfer setting
	 */
	private void validateTransferAmount(List<MessageInfo> list, TransferSetting transferSetting)
	{
		if (ValidationUtil.isNullOrZero(transferSetting.getTransferAmount()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_TRANSFER_AMOUNT_INVALID,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate payment type code id.
	 *
	 * @param list the list
	 * @param transferSetting the transfer setting
	 */
	private void validatePaymentTypeCodeId(List<MessageInfo> list, TransferSetting transferSetting)
	{
		if (ValidationUtil.isNull(transferSetting.getProductPlanApplicability()))
		{
			list.add(new MessageInfo(
					PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_APPLICABILITY_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}

		if (ValidationUtil.isNullOrZero(transferSetting.getProductPlanApplicability().getPaymentTypeValue()))
		{
			list.add(new MessageInfo(
					TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_APPLICABILITY_PAYMENT_TYPE_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate transfer type id.
	 *
	 * @param list the list
	 * @param transferSetting the transfer setting
	 */
	private void validateTransferTypeId(List<MessageInfo> list, TransferSetting transferSetting)
	{
		ValidationUtil.isNullOrZero(transferSetting.getTransferTypeValue(),
				PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_TRANSFER_TYPE_REQUIRED, list);
	}

	/**
	 * Validate status.
	 *
	 * @param list the list
	 * @param transferSetting the transfer setting
	 */
	private void validateStatus(List<MessageInfo> list, TransferSetting transferSetting)
	{
		ValidationUtil.isNullOrZero(transferSetting.getStatusValue(),
				PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_STATUS_REQUIRED, list);
	}

	/**
	 * Validate custom fee.
	 *
	 * @param list the list
	 * @param transferSetting the transfer setting
	 * @param context the context
	 */
	private void validateCustomFee(List<MessageInfo> list, TransferSetting transferSetting, ValidationContext context)
	{
		if (ValidationUtil.isNullOrEmpty(transferSetting.getCustomFeeList()))
		{
			return;
		}

		for (CustomFee customFee : transferSetting.getCustomFeeList())
		{
			switch (customFee.getModelAction())
			{
				case INSERT:
					validateAllCustomFee(context.getMessages(), customFee, transferSetting);
					break;
				case UPDATE:
					validateCustomFeeId(context.getMessages(), customFee);
					validateAllCustomFee(context.getMessages(), customFee, transferSetting);
					break;
				case DELETE:
					validateCustomFeeId(context.getMessages(), customFee);
					break;
				default:
					validateAllCustomFee(context.getMessages(), customFee, transferSetting);
					break;
			}

			if (!ValidationContextIndicator.DELETE.equals(context.getValidationContextIndicator()))
			{
				validateChangeControlFields(context.getMessages(), customFee, context);
			}
		}

	}

	/**
	 * Validate all custom fee.
	 *
	 * @param list the list
	 * @param customFee the custom fee
	 * @param transferSetting the transfer setting
	 */
	private void validateAllCustomFee(List<MessageInfo> list, CustomFee customFee, TransferSetting transferSetting)
	{
		validateTransferSettingId(list, customFee, transferSetting);
		validateExpiresDate(list, customFee);
		validateCustomFeeStatus(list, customFee);
	}

	/**
	 * Validate id.
	 *
	 * @param list the list
	 * @param customFee the custom fee
	 */
	private void validateCustomFeeId(List<MessageInfo> list, CustomFee customFee)
	{
		if (ValidationUtil.isNullOrZero(customFee.getId()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_ID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate custom fee status.
	 *
	 * @param list the list
	 * @param customFee the custom fee
	 */
	private void validateCustomFeeStatus(List<MessageInfo> list, CustomFee customFee)
	{
		ValidationUtil.isNullOrZero(customFee.getStatusValue(),
				PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_STATUS_REQUIRED, list);
	}

	/**
	 * Validate transfer setting id.
	 *
	 * @param list the list
	 * @param customFee the custom fee
	 * @param transferSetting the transfer setting
	 */
	private void validateTransferSettingId(List<MessageInfo> list, CustomFee customFee, TransferSetting transferSetting)
	{
		if (!PersistanceActionEnum.INSERT.equals(transferSetting.getModelAction()))
		{
			ValidationUtil.isNullOrZero(customFee.getTransferSettingId(),
					PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_TRANSFERSETTING_ID_REQUIRED, list);

		}
	}

	/**
	 * Validate expires date.
	 *
	 * @param list the list
	 * @param customFee the custom fee
	 */
	private void validateExpiresDate(List<MessageInfo> list, CustomFee customFee)
	{
		ValidationUtil.isNullOrZero(customFee.getEffectiveEndDate(),
				PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_EXPIRESDATE_REQUIRED, list);
	}

	/**
	 * Validate cycles to skip.
	 *
	 * @param list the list
	 * @param transferSetting the transfer setting
	 */
	private void validateCyclesToSkip(List<MessageInfo> list, TransferSetting transferSetting)
	{
		if (ValidationUtil.isNull(transferSetting.getCyclesToSkip()))
		{
			return;
		}

		if (ValidationUtil.isNull(transferSetting.getEmploymentInfo()))
		{
			return;
		}

		InternalResultsResponse<Location> location = getLocationDAC()
				.fetchLocationById(new FetchByIdRequest(transferSetting.getEmploymentInfo().getLocationId()));

		if (location.hasResults())
		{
			for (FrequencyBasedEvent frequencyBasedEvent : location.getFirstResult().getFrequencyBasedEventList())
			{
				if (FrequencyBasedEventTypeEnum.PAY_DAY.equals(frequencyBasedEvent.getType()))
				{
					Integer frequencyCodeValue = frequencyBasedEvent.getFrequencyCode().getValue();

					Integer maxCyclesToSkip = getFrequencyCodeEventMaxCycleToSkip().get(frequencyCodeValue);

					if ((transferSetting.getCyclesToSkip() < ZERO)
							|| (transferSetting.getCyclesToSkip() > maxCyclesToSkip))
					{
						list.add(new MessageInfo(
								PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_MAXCYCLETOSKIP_INVALID,
								Message.MessageSeverity.Error,
								Message.MessageLevel.Field, new Object[] {maxCyclesToSkip}));
					}
					return;
				}
			}

		}
	}
}
