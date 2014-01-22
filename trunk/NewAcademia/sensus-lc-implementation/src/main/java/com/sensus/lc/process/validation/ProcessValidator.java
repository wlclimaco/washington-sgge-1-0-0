package com.sensus.lc.process.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.lc.base.util.LCHelp;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessItem;

/**
 * The Class ProcessValidator.
 */
public class ProcessValidator implements IValidator
{
	/** The Constant PROCESS_NAME. */
	private static final String PROCESS_NAME = Process.class.getSimpleName();

	/** The Constant PROCESS_RNI_NAME. */
	private static final String PROCESS_RNI_NAME = PROCESS_NAME + " RNI";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The Constant SENSUS_MLC_PROCESS_DESCRIPTION_REQUIRED. */
	private static final String SENSUS_MLC_PROCESS_DESCRIPTION_REQUIRED =
			"sensus.mlc.processvalidator.description.required";

	/** The Constant SENSUS_MLC_PROCESS_ACTION_DESCRIPTION_REQUIRED. */
	private static final String SENSUS_MLC_PROCESS_ACTION_DESCRIPTION_REQUIRED =
			"sensus.mlc.processvalidator.action.description.required";

	/** The Constant SENSUS_MLC_PROCESS_STARTDATETIME_REQUIRED. */
	private static final String SENSUS_MLC_PROCESS_STARTDATETIME_REQUIRED =
			"sensus.mlc.processvalidator.start.date.time.required";

	/** The Constant SENSUS_MLC_PROCESS_ENDDATETIME_REQUIRED. */
	private static final String SENSUS_MLC_PROCESS_ENDDATETIME_REQUIRED =
			"sensus.mlc.processvalidator.end.date.time.required";

	/** The Constant SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED =
			"sensus.mlc.validator.is.monitored.required";

	/** The Constant SENSUS_MLC_PROCESS_IS_PROCESS_COMPLETE. */
	private static final String SENSUS_MLC_PROCESS_IS_PROCESS_COMPLETE =
			"sensus.mlc.processvalidator.is.process.complete.required";

	/** The Constant SENSUS_MLC_PROCESS_IS_PARENT_REQUIRED. */
	private static final String SENSUS_MLC_PROCESS_IS_PARENT_REQUIRED =
			"sensus.mlc.processvalidator.is.parent.required";

	/** The Constant SENSUS_MLC_PROCESS_MLC_ACTION_REQUIRED. */
	private static final String SENSUS_MLC_PROCESS_MLC_ACTION_REQUIRED =
			"sensus.mlc.processvalidator.mlc.action.required";

	/** The ConstantSENSUS_MLC_PROCESS_CREATE_USER_REQUIRED. */
	private static final String SENSUS_MLC_PROCESS_CREATE_USER_REQUIRED =
			"sensus.mlc.processvalidator.create.user.required";

	/** The Constant SENSUS_MLC_PROCESS_MIN_ONE_PROCESS_REQUIRED. */
	private static final String SENSUS_MLC_PROCESS_MIN_ONE_PROCESS_REQUIRED =
			"sensus.mlc.processvalidator.min.one.process.required";

	/** The Constant SENSUS_MLC_PROCESS_RESULT_LIGHT_REQUIRED. */
	private static final String SENSUS_MLC_PROCESS_RESULT_LIGHT_REQUIRED =
			"sensus.mlc.processvalidator.processresult.light.required";

	/** The Constant SENSUS_MLC_PROCESS_RESULT_RESULT_REQUIRED. */
	private static final String SENSUS_MLC_PROCESS_RESULT_RESULT_REQUIRED =
			"sensus.mlc.processvalidator.processresult.result.required";

	private static final String SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED =
			"sensus.mlc.lightvalidator.intensity.required";

	/** The Constant SENSUS_MLC_PROCESS_MLC_ACTION_TYPE_REQUIRED. */
	private static final String SENSUS_MLC_PROCESS_MLC_ACTION_TYPE_REQUIRED =
			"sensus.mlc.processvalidator.mlc.action.type.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.AbstractValidator#validate(java.lang.Object, java.util.List,
	 * java.lang.Object[])
	 */
	@Override
	public void validate(ValidationContext context)
	{
		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)context.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		if (isNull(action))
		{
			return;
		}

		// Cast the object so it's easy to work with.
		Process process = (Process)context.getObjectToBeValidated(PROCESS_NAME);
		List<MessageInfo> list = context.getMessages();

		switch (action)
		{
			case INSERT:
				validateProcessDescription(list, process);
				validateProcessActionDescription(list, process);
				validateProcessStartTime(list, process);
				validateProcessIsMonitored(list, process);
				validateProcessMlcAction(list, process);
				validateProcessCreateUser(list, process);
				validateProcessIsProcessComplete(list, process);
				validateProcessIsParent(list, process);
				validateProcessForProcess(list, process);
				validateLightintensity(list, process);
				return;
			case UPDATE:
				validateProcessId(list, process);
				validateProcessEndTime(list, process);
				validateProcessIsMonitored(list, process);
				validateProcessIsProcessComplete(list, process);
				validateLightintensity(list, process);
				return;
			case FETCH_BY_RNI_ID:
				validateProcessRniId(list, process);
				return;
			case UNMONITOR:
			case FETCH_BY_ID:
			case UPDATE_CSV_DOWNLOADED:
			case FETCH_UPDATE_LIGHT_STATUS:
			case RETRY:
				validateProcessId(list, process);
				return;
			case GENERATE_FILE_CSV:
				Integer processId =
						(Integer)context.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.PROCESS_ID.getValue());
				validateProcessId(list, processId);
				return;
			default:
				break;
		}
	}

	/**
	 * Validate process id.
	 *
	 * @param list the list
	 * @param process the process
	 */
	private void validateProcessId(List<MessageInfo> list, Process process)
	{
		if (validateProcess(list, process))
		{
			validateProcessId(list, process.getId());
		}
	}

	/**
	 * Validate process id.
	 *
	 * @param list the list
	 * @param processId the process id
	 */
	public void validateProcessId(List<MessageInfo> list, Integer processId)
	{
		LCHelp.isNullOrZeroLC(processId, SENSUS_MLC_VALIDATOR_ID_REQUIRED, list, PROCESS_NAME);
	}

	/**
	 * Validate process description.
	 *
	 * @param list the list
	 * @param process the process
	 */
	private void validateProcessDescription(List<MessageInfo> list, Process process)
	{
		if (validateProcess(list, process))
		{
			ValidationUtil.isNull(process.getDescription(), SENSUS_MLC_PROCESS_DESCRIPTION_REQUIRED, list);
		}
	}

	/**
	 * Validate process action description.
	 *
	 * @param list the list
	 * @param process the process
	 */
	private void validateProcessActionDescription(List<MessageInfo> list, Process process)
	{
		if (validateProcess(list, process))
		{
			if (!ValidationUtil.isNull(process.getLcAction()))
			{
				ValidationUtil.isNull(process.getLcAction().getDescription(),
						SENSUS_MLC_PROCESS_ACTION_DESCRIPTION_REQUIRED, list);
			}
			else
			{
				ValidationUtil.isNull(process.getLcAction(),
						SENSUS_MLC_PROCESS_MLC_ACTION_REQUIRED, list);
			}
		}
	}

	/**
	 * Validate lightintensity.
	 *
	 * @param list the list
	 * @param process the process
	 */
	private void validateLightintensity(List<MessageInfo> list, Process process)
	{
		if (validateProcess(list, process))
		{
			if (!ValidationUtil.isNull(process.getLcAction())
					&& ((process.getLcAction().getActionType() == LCActionTypeEnum.SET_INTENSITY_BY_GRP)
							|| (process.getLcAction().getActionType() == LCActionTypeEnum.TURN_OFF)
							|| (process.getLcAction().getActionType() == LCActionTypeEnum.TURN_ON)
							|| (process.getLcAction().getActionType() == LCActionTypeEnum.DIM)))
			{

				LCAction lcAction = process.getLcAction();
				if ((lcAction.getActionType() == LCActionTypeEnum.TURN_OFF)
						|| (lcAction.getActionType() == LCActionTypeEnum.TURN_ON)
						|| (lcAction.getActionType() == LCActionTypeEnum.DIM))
				{
					ValidationUtil.isNull(lcAction.getActionParameters().get(0).getActionValue(),
							SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED, list);
					return;
				}

				ValidationUtil.isNull(lcAction.getActionParameters().get(1).getActionValue(),
						SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED, list);
			}
		}
	}

	/**
	 * Validate process rni id.
	 *
	 * @param list the list
	 * @param process the process
	 */
	private void validateProcessRniId(List<MessageInfo> list, Process process)
	{
		if (validateProcess(list, process))
		{
			LCHelp.isNullOrEmptyLC(process.getRniCorrelationId(), SENSUS_MLC_VALIDATOR_ID_REQUIRED, list,
					PROCESS_RNI_NAME);
		}
	}

	/**
	 * Validate process start time.
	 *
	 * @param list the list
	 * @param process the process
	 */
	private void validateProcessStartTime(List<MessageInfo> list, Process process)
	{
		if (validateProcess(list, process))
		{
			ValidationUtil.isNull(process.getStartTime(), SENSUS_MLC_PROCESS_STARTDATETIME_REQUIRED, list);
		}
	}

	/**
	 * Validate process end time.
	 *
	 * @param list the list
	 * @param process the process
	 */
	private void validateProcessEndTime(List<MessageInfo> list, Process process)
	{
		if (validateProcess(list, process))
		{
			ValidationUtil.isNull(process.getEndTime(), SENSUS_MLC_PROCESS_ENDDATETIME_REQUIRED, list);
		}
	}

	/**
	 * Validate process is monitored.
	 *
	 * @param list the list
	 * @param process the process
	 */
	private void validateProcessIsMonitored(List<MessageInfo> list, Process process)
	{
		if (validateProcess(list, process))
		{
			ValidationUtil.isNull(process.getIsMonitoredInstance(), SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED, list);
		}
	}

	/**
	 * Validate process is process complete.
	 *
	 * @param list the list
	 * @param process the process
	 */
	private void validateProcessIsProcessComplete(List<MessageInfo> list, Process process)
	{
		if (validateProcess(list, process))
		{
			ValidationUtil.isNull(process.getIsProcessComplete(), SENSUS_MLC_PROCESS_IS_PROCESS_COMPLETE, list);
		}
	}

	/**
	 * Validate process is parent.
	 *
	 * @param list the list
	 * @param process the process
	 */
	private void validateProcessIsParent(List<MessageInfo> list, Process process)
	{
		if (validateProcess(list, process))
		{
			ValidationUtil.isNull(process.getIsParent(), SENSUS_MLC_PROCESS_IS_PARENT_REQUIRED, list);
		}
	}

	/**
	 * Validate process mlc action.
	 *
	 * @param list the list
	 * @param process the process
	 */
	private void validateProcessMlcAction(List<MessageInfo> list, Process process)
	{
		if (validateProcess(list, process))
		{
			if (!ValidationUtil.isNull(process.getLcAction()))
			{
				ValidationUtil.isNullOrZero(process.getLcAction().getActionType().getValue(),
						SENSUS_MLC_PROCESS_MLC_ACTION_TYPE_REQUIRED, list);
			}
			else
			{
				ValidationUtil.isNull(process.getLcAction(), SENSUS_MLC_PROCESS_MLC_ACTION_REQUIRED, list);
			}
		}
	}

	/**
	 * Validate process create user.
	 *
	 * @param list the list
	 * @param process the process
	 */
	private void validateProcessCreateUser(List<MessageInfo> list, Process process)
	{
		if (validateProcess(list, process))
		{
			ValidationUtil.isNullOrEmpty(process.getCreateUser(), SENSUS_MLC_PROCESS_CREATE_USER_REQUIRED, list);
		}
	}

	/**
	 * Validate process for process.
	 *
	 * @param list the list
	 * @param process the process
	 */
	private void validateProcessForProcess(List<MessageInfo> list, Process process)
	{
		if (validateProcess(list, process))
		{
			if (ValidationUtil.isNullOrEmpty(process.getProcessItems()))
			{
				list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_PROCESS_MIN_ONE_PROCESS_REQUIRED));
			}
			else
			{
				validateLightForProcessResult(list, process);
			}
		}
	}

	/**
	 * Validate light for process result.
	 *
	 * @param list the list
	 * @param process the process
	 */
	private void validateLightForProcessResult(List<MessageInfo> list, Process process)
	{
		if (validateProcess(list, process))
		{
			for (ProcessItem processItem : process.getProcessItems())
			{
				if ((ValidationUtil.isNull(processItem.getLight()))
						|| (ValidationUtil.isNull(processItem.getLight().getRadio()) ||
								isNull(processItem.getLight().getRadio().getFlexNetId()) ||
						(processItem.getLight().getRadio().getFlexNetId().intValue() == 0)))
				{
					list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_PROCESS_RESULT_LIGHT_REQUIRED));
				}

				ValidationUtil.isNullOrZero(processItem.getProcessItemStatusEnumValue(),
						SENSUS_MLC_PROCESS_RESULT_RESULT_REQUIRED, list);
			}
		}
	}

	/**
	 * Validate process.
	 *
	 * @param list the list
	 * @param process the process
	 * @return the boolean
	 */
	private Boolean validateProcess(List<MessageInfo> list, Process process)
	{
		if (isNull(process))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_REQUIRED,
					Process.class.getSimpleName()));
			return false;
		}
		return true;
	}
}