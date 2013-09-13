package com.sensus.dm.common.process.validation;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.process.model.DMProcess;

/**
 * The Class TagValidator.
 */
public class ProcessValidator implements IValidator
{
	private static final String SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED =
			"sensus.epm.processvalidator.process.required";

	/** The Constant SENSUS_EPM_DEVICEVALIDATOR_PROCESS_ID_REQUIRED. */
	private static final String SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED =
			"sensus.epm.processvalidator.id.required";

	/** The Constant SENSUS_EPM_PROCESSVALIDATOR_IS_MONITORED_REQUIRED. */
	private static final String SENSUS_EPM_PROCESSVALIDATOR_IS_MONITORED_REQUIRED =
			"sensus.epm.processvalidator.is.monitored.required";

	private static final String SENSUS_EPM_PROCESSVALIDATOR_DASHBOARD_REQUIRED =
			"sensus.epm.processvalidator.dashboard.required";

	private static final String SENSUS_EPM_PROCESSVALIDATOR_PROCESS_STATUS_REQUIRED =
			"sensus.epm.processvalidator.process.status.required";

	private static final String SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ITEMS_REQUIRED =
			"sensus.epm.processvalidator.process_items.required";

	@Override
	public void validate(ValidationContext validationContext)
	{
		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		DMProcess process = (DMProcess)validationContext.getObjectToBeValidated(DMProcess.class.getSimpleName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(process))
		{
			validationContext.getMessages().add(
					MessageInfo.createFieldValidationError(SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED));
			return;
		}

		switch (action)
		{
			case UPDATE:
				ValidationUtil.isNullOrZero(process.getId(),
						SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED, validationContext.getMessages());

				ValidationUtil.isNull(process.getIsMonitoredInstance(),
						SENSUS_EPM_PROCESSVALIDATOR_IS_MONITORED_REQUIRED, validationContext.getMessages());
				break;
			case UPDATE_STATUS:
				ValidationUtil.isNullOrZero(process.getId(),
						SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED, validationContext.getMessages());

				ValidationUtil.isNull(process.getProcessStatusEnum(),
						SENSUS_EPM_PROCESSVALIDATOR_PROCESS_STATUS_REQUIRED, validationContext.getMessages());
				break;
			case FETCH_MONITORED_PROCESS:
				ValidationUtil.isNull(process.getIsMonitoredInstance(),
						SENSUS_EPM_PROCESSVALIDATOR_IS_MONITORED_REQUIRED, validationContext.getMessages());

				ValidationUtil.isNull(process.getIsDashboardMonitored(),
						SENSUS_EPM_PROCESSVALIDATOR_DASHBOARD_REQUIRED, validationContext.getMessages());
				break;
			case FETCH_PROCESS_BY_ID:
			case UPDATE_CSV_DOWNLOADED:
			case GENERATE_FILE_CSV:
			case FETCH_COMMUNICATION_SUMMARY:
			case FETCH_DEMAND_RESPONSE_SUMMARY:
			case FETCH_IMPORT_HAN_SUMMARY:
			case FETCH_DEMAND_READ_PING_SUMMARY:
			case FETCH_PROCESS_ITEMS_BY_SCHEDULE:
			case FETCH_PROCESS_ITEMS_BY_PROCESS_ID:
			case FETCH_PROCESS_SEND_HAN_TEXT_MESSAGE:
			case FETCH_RELAYS_BY_PROCESS_ID:
				ValidationUtil.isNullOrZero(process.getId(),
						SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED, validationContext.getMessages());
				break;
			case FETCH_DEMAND_RESPONSE_PROGRAM_PARTICIPATION:
			case FETCH_DEMAND_RESPONSE_SETUP_BY_DEVICE:
			case EXPIRE_PROCESS_ITEMS:
			case FETCH_RELAY:
				ValidationUtil.isNullOrEmpty(process.getProcessItems(),
						SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ITEMS_REQUIRED, validationContext.getMessages());
				break;
			default:
				break;
		}
	}
}
