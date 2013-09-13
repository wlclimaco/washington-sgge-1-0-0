package com.sensus.dm.elec.action.validation;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;
import com.sensus.dm.elec.action.model.SendHanTextMessageAction;

/**
 * The Class ActionValidator.
 * 
 * @author QAT Global
 */
public class ActionValidator implements IValidator
{

	/** The Constant REQUIRED. */
	private static final String REQUIRED = "sensus.epm.actionvalidator.action.required";

	/** The Constant ID_REQUIRED. */
	private static final String ID_REQUIRED = "sensus.epm.actionvalidator.action.id.required";

	/** The Constant EPM_ACTION_TYPE_REQUIRED. */
	private static final String EPM_ACTION_TYPE_REQUIRED = "sensus.epm.actionvalidator.type.required";

	/** The Constant EPM_ACTION_TYPE_DESCRIPTION_REQUIRED. */
	private static final String EPM_ACTION_TYPE_DESCRIPTION_REQUIRED =
			"sensus.epm.actionvalidator.epm_action_type_description_required";

	/** The Constant EPM_ACTION_ON_DEMAND_REQUIRED. */
	private static final String EPM_ACTION_ON_DEMAND_REQUIRED = "sensus.epm.actionvalidator.is.on.demand.required";

	/** The Constant EPM_ACTION_MONITORED_REQUIRED. */
	private static final String EPM_ACTION_MONITORED_REQUIRED = "sensus.epm.actionvalidator.monitored.required";

	/** The Constant EPM_ACTION_TIME_REQUIRED. */
	private static final String EPM_ACTION_TIME_REQUIRED = "sensus.epm.actionvalidator.time.required";

	/** The Constant EPM_ACTION_ENROLLMENTCODE_REQUIRED. */
	private static final String EPM_ACTION_ENROLLMENTCODE_REQUIRED =
			"sensus.epm.actionvalidator.enrollmentcode.required";

	/** The Constant EPM_ACTION_DURATION_REQUIRED. */
	private static final String EPM_ACTION_DURATION_REQUIRED = "sensus.epm.actionvalidator.duration.required";

	/** The Constant EPM_ACTION_DURATION_INVALID. */
	private static final String EPM_ACTION_DURATION_INVALID = "sensus.epm.actionvalidator.duration.invalid";

	/** The Constant EPM_ACTION_SEND_TEXT_REQUIRED. */
	private static final String EPM_ACTION_SEND_TEXT_REQUIRED = "sensus.epm.actionvalidator.send.text.required";

	/** The Constant EPM_ACTION_SEND_TEXT_INVALID. */
	private static final String EPM_ACTION_SEND_TEXT_INVALID = "sensus.epm.actionvalidator.send.text.invalid";

	/** The Constant EPM_ACTION_LOAD_ADJUSTMENT_INVALID. */
	private static final String EPM_ACTION_LOAD_ADJUSTMENT_INVALID =
			"sensus.epm.actionvalidator.load.adjustment.invalid";

	/** The Constant EPM_ACTION_CRITICALITY_LEVEL_INVALID. */
	private static final String EPM_ACTION_CRITICALITY_LEVEL_INVALID =
			"sensus.epm.actionvalidator.criticality.level.invalid";

	/** The Constant EPM_ACTION_CRITICALITY_LEVEL_RIQUIRED. */
	private static final String EPM_ACTION_CRITICALITY_LEVEL_RIQUIRED =
			"sensus.epm.actionvalidator.criticality.level.required";

	/** The enrollment code length. */
	private int enrollmentCodeLength;

	/** The description length. */
	private int descriptionLength;

	/** The duration han text message max. */
	private int durationHanTextMessageMax;

	/** The duration han text message min. */
	private int durationHanTextMessageMin;

	/** The load adjustment max. */
	private int loadAdjustmentMax;

	/** The load adjustment min. */
	private int loadAdjustmentMin;

	/** The criticality level max. */
	private int criticalityLevelMax;

	/** The criticality level min. */
	private int criticalityLevelMin;

	/**
	 * Gets the enrollment code length.
	 * 
	 * @return the enrollment code length
	 */
	public int getEnrollmentCodeLength()
	{
		return enrollmentCodeLength;
	}

	/**
	 * Sets the enrollment code length.
	 * 
	 * @param enrollmentCodeLength the new enrollment code length
	 */
	public void setEnrollmentCodeLength(int enrollmentCodeLength)
	{
		this.enrollmentCodeLength = enrollmentCodeLength;
	}

	/**
	 * Gets the description length.
	 * 
	 * @return the description length
	 */
	public int getDescriptionLength()
	{
		return descriptionLength;
	}

	/**
	 * Sets the description length.
	 * 
	 * @param descriptionLength the new description length
	 */
	public void setDescriptionLength(int descriptionLength)
	{
		this.descriptionLength = descriptionLength;
	}

	/**
	 * Gets the duration han text message max.
	 * 
	 * @return the duration han text message max
	 */
	public int getDurationHanTextMessageMax()
	{
		return durationHanTextMessageMax;
	}

	/**
	 * Sets the duration han text message max.
	 * 
	 * @param durationHanTextMessageMax the new duration han text message max
	 */
	public void setDurationHanTextMessageMax(int durationHanTextMessageMax)
	{
		this.durationHanTextMessageMax = durationHanTextMessageMax;
	}

	/**
	 * Gets the duration han text message min.
	 * 
	 * @return the duration han text message min
	 */
	public int getDurationHanTextMessageMin()
	{
		return durationHanTextMessageMin;
	}

	/**
	 * Sets the duration han text message min.
	 * 
	 * @param durationHanTextMessageMin the new duration han text message min
	 */
	public void setDurationHanTextMessageMin(int durationHanTextMessageMin)
	{
		this.durationHanTextMessageMin = durationHanTextMessageMin;
	}

	/**
	 * Gets the load adjustment max.
	 * 
	 * @return the load adjustment max
	 */
	public int getLoadAdjustmentMax()
	{
		return loadAdjustmentMax;
	}

	/**
	 * Sets the load adjustment max.
	 * 
	 * @param loadAdjustmentMax the new load adjustment max
	 */
	public void setLoadAdjustmentMax(int loadAdjustmentMax)
	{
		this.loadAdjustmentMax = loadAdjustmentMax;
	}

	/**
	 * Gets the load adjustment min.
	 * 
	 * @return the load adjustment min
	 */
	public int getLoadAdjustmentMin()
	{
		return loadAdjustmentMin;
	}

	/**
	 * Sets the load adjustment min.
	 * 
	 * @param loadAdjustmentMin the new load adjustment min
	 */
	public void setLoadAdjustmentMin(int loadAdjustmentMin)
	{
		this.loadAdjustmentMin = loadAdjustmentMin;
	}

	/**
	 * Gets the criticality level max.
	 * 
	 * @return the criticality level max
	 */
	public int getCriticalityLevelMax()
	{
		return criticalityLevelMax;
	}

	/**
	 * Sets the criticality level max.
	 * 
	 * @param criticalityLevelMax the new criticality level max
	 */
	public void setCriticalityLevelMax(int criticalityLevelMax)
	{
		this.criticalityLevelMax = criticalityLevelMax;
	}

	/**
	 * Gets the criticality level min.
	 * 
	 * @return the criticality level min
	 */
	public int getCriticalityLevelMin()
	{
		return criticalityLevelMin;
	}

	/**
	 * Sets the criticality level min.
	 * 
	 * @param criticalityLevelMin the new criticality level min
	 */
	public void setCriticalityLevelMin(int criticalityLevelMin)
	{
		this.criticalityLevelMin = criticalityLevelMin;
	}

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

		DMAction dmAction = (DMAction)validationContext.getObjectToBeValidated(DMAction.class.getSimpleName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(dmAction))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(REQUIRED));
			return;
		}

		switch (action)
		{
			case INSERT_SCHEDULE:
			case UPDATE_SCHEDULE:
				validationAction(dmAction, validationContext.getMessages());
				break;
			case FETCH_BY_ID:
			case FETCH_SCHEDULE_BY_ACTION:
			case ADD_SM_FROM_FILE:
			case INSERT_OPT_OUT_LIST:
			case DELETE_OPT_OUT_LIST:
				ValidationUtil.isNullOrZero(dmAction.getId(), ID_REQUIRED, validationContext.getMessages());
				break;
			case FETCH_ACTION_TYPE_BY_DESCRIPTION:
				validateActionTypeDescription(validationContext.getMessages(), dmAction);
				break;
			case FETCH_ALL_RNI_ACTION_TYPES:
				validateActionTypeDescription(validationContext.getMessages(), dmAction);
				break;
			case APPLY_DEVICE_TO_ACTION:
				validationAction(dmAction, validationContext.getMessages());
				break;
			case APPLY_ON_DEMAND:
				ValidationUtil.isNull(dmAction.isOnDemand(), EPM_ACTION_ON_DEMAND_REQUIRED,
						validationContext.getMessages());
				ValidationUtil.isNull(dmAction.getIsMonitored(), EPM_ACTION_MONITORED_REQUIRED,
						validationContext.getMessages());
				ValidationUtil.isNull(dmAction.getActionTime(), EPM_ACTION_TIME_REQUIRED,
						validationContext.getMessages());

				if (ValidationUtil.isNull(dmAction.getActionType())
						|| ValidationUtil.isNull(dmAction.getActionType().getActionTypeEnum()))
				{
					validationContext.getMessages().add(
							MessageInfo.createFieldValidationError(EPM_ACTION_TYPE_DESCRIPTION_REQUIRED));
					return;
				}

			default:
				break;
		}

	}

	/**
	 * Validate action type description.
	 * 
	 * @param list the list
	 * @param action the action view
	 */
	private void validateActionTypeDescription(List<MessageInfo> list,
			DMAction action)
	{
		if (ValidationUtil.isNull(action.getActionType())
				&& ValidationUtil.isNull(action.getActionType().getActionTypeEnum()))
		{
			list.add(MessageInfo.createFieldValidationError(EPM_ACTION_TYPE_REQUIRED));
			return;
		}

		ValidationUtil.isNullOrEmpty(action.getActionType().getActionTypeEnum().getActionTypeName(),
				EPM_ACTION_TYPE_DESCRIPTION_REQUIRED,
				list);
	}

	/**
	 * Validation action.
	 * 
	 * @param action the epm action
	 * @param list the list
	 */
	private void validationAction(DMAction action, List<MessageInfo> list)
	{
		ValidationUtil.isNull(action.isOnDemand(), EPM_ACTION_ON_DEMAND_REQUIRED, list);
		ValidationUtil.isNull(action.getIsMonitored(), EPM_ACTION_MONITORED_REQUIRED, list);
		ValidationUtil.isNull(action.getActionTime(), EPM_ACTION_TIME_REQUIRED, list);

		if (ValidationUtil.isNull(action.getActionType())
				|| ValidationUtil.isNull(action.getActionType().getActionTypeEnum()))
		{
			list.add(MessageInfo.createFieldValidationError(EPM_ACTION_TYPE_DESCRIPTION_REQUIRED));
			return;
		}

		if (ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT.equals(action.getActionType().getActionTypeEnum()))
		{
			validateEnrollmentCode(((DemandResponseEventAction)action).getEnrollmentCode(), list);

			validateCriticalityLevel(((DemandResponseEventAction)action).getCriticalityLevel(), list);

			validateLoadAdjustment(((DemandResponseEventAction)action).getLoadAdjustment(), list);

			validateDemandResponseDuration((DemandResponseEventAction)action, list);

		}
		else if (ActionTypeEnum.SEND_HAN_TEXT_MESSAGE.equals(action.getActionType().getActionTypeEnum()))
		{
			validateTextMessage(((SendHanTextMessageAction)action).getTextMessage(), list);

			validateDurationHanTextMessage(((SendHanTextMessageAction)action).getDurationHANTextMessage(), list);
		}
	}

	/**
	 * Validate demand response duration.
	 * 
	 * @param demandResponse the demand response
	 * @param list the list
	 */
	private void validateDemandResponseDuration(DemandResponseEventAction demandResponse, List<MessageInfo> list)
	{
		if (ValidationUtil.isNullOrZero(demandResponse.getDemandResponseDuration()))
		{
			list.add(MessageInfo.createFieldValidationError(EPM_ACTION_DURATION_REQUIRED));
			return;
		}

		if (demandResponse.getDemandResponseDuration() <= getDurationHanTextMessageMin()
				|| demandResponse.getDemandResponseDuration() > getDurationHanTextMessageMax())
		{
			list.add(MessageInfo.createFieldValidationError(EPM_ACTION_DURATION_INVALID,
					getDurationHanTextMessageMin(), getDurationHanTextMessageMax()));
			return;
		}
	}

	/**
	 * Validate load adjustment.
	 * 
	 * @param loadAdjustment the load adjustment
	 * @param list the list
	 */
	private void validateLoadAdjustment(Integer loadAdjustment, List<MessageInfo> list)
	{
		if (!ValidationUtil.isNull(loadAdjustment) && (loadAdjustment < getLoadAdjustmentMin()
				|| loadAdjustment > getLoadAdjustmentMax()))
		{
			list.add(MessageInfo.createFieldValidationError(EPM_ACTION_LOAD_ADJUSTMENT_INVALID));
		}

	}

	/**
	 * Validate criticality level.
	 * 
	 * @param criticalityLevel the criticality level
	 * @param list the list
	 */
	private void validateCriticalityLevel(Integer criticalityLevel, List<MessageInfo> list)
	{
		if (ValidationUtil.isNull(criticalityLevel))
		{
			list.add(MessageInfo.createFieldValidationError(EPM_ACTION_CRITICALITY_LEVEL_RIQUIRED));
			return;
		}

		if (criticalityLevel < getCriticalityLevelMin()
				|| criticalityLevel > getCriticalityLevelMax())
		{
			list.add(MessageInfo.createFieldValidationError(EPM_ACTION_CRITICALITY_LEVEL_INVALID));
		}

	}

	/**
	 * Validate duration han text message.
	 * 
	 * @param durationHanTextMessage the duration han text message
	 * @param list the list
	 */
	private void validateDurationHanTextMessage(Integer durationHanTextMessage, List<MessageInfo> list)
	{
		if (ValidationUtil.isNull(durationHanTextMessage))
		{
			list.add(MessageInfo.createFieldValidationError(EPM_ACTION_DURATION_REQUIRED));
			return;
		}

		if (durationHanTextMessage <= getDurationHanTextMessageMin()
				|| durationHanTextMessage > getDurationHanTextMessageMax())
		{
			list.add(MessageInfo.createFieldValidationError(EPM_ACTION_DURATION_INVALID,
					getDurationHanTextMessageMin(), getDurationHanTextMessageMax()));
		}
	}

	/**
	 * Validate text message.
	 * 
	 * @param textMessage the text message
	 * @param list the list
	 */
	private void validateTextMessage(String textMessage, List<MessageInfo> list)
	{
		if (ValidationUtil.isNull(textMessage))
		{
			list.add(MessageInfo.createFieldValidationError(EPM_ACTION_SEND_TEXT_REQUIRED));
			return;
		}

		if (textMessage.length() > getDescriptionLength())
		{
			list.add(MessageInfo.createFieldValidationError(EPM_ACTION_SEND_TEXT_INVALID, getDescriptionLength()));
		}
	}

	/**
	 * Validate enrollment code.
	 * 
	 * @param enrollmentCode the enrollment code
	 * @param list the list
	 */
	private void validateEnrollmentCode(Integer enrollmentCode, List<MessageInfo> list)
	{
		if (ValidationUtil.isNull(enrollmentCode))
		{
			list.add(MessageInfo.createFieldValidationError(EPM_ACTION_ENROLLMENTCODE_REQUIRED));
			return;
		}
	}
}
