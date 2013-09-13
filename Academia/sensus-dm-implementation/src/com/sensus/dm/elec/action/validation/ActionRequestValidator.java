package com.sensus.dm.elec.action.validation;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.elec.action.model.request.ActionRequest;

/**
 * The Class ActionRequestValidator.
 * 
 * @author QAT Brazil.
 */
public class ActionRequestValidator implements IValidator
{
	/** The Constant REQUIRED. */
	private static final String ACTION_REQUIRED = "sensus.epm.actionvalidator.action.required";

	/** The Constant REQUIRED. */
	private static final String REQUIRED = "sensus.epm.actionvalidator.action.id.required";

	/** The Constant SENSUS_EPM_ACTIONREQUESTVALIDATOR_HAN_DEVICES_REQUIRED. */
	private static final String EPM_ACTION_HAN_DEVICES_REQUIRED =
			"sensus.epm.actionvalidator.han.devices.required";

	/** The Constant SENSUS_EPM_ACTION_IDFILETYPE_REQUIRED. */
	private static final String EPM_ACTION_IDFILETYPE_REQUIRED = "sensus.epm.actionvalidator.id.file.type.required";

	@Override
	public void validate(ValidationContext validationContext)
	{
		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		ActionRequest request =
				(ActionRequest)validationContext.getObjectToBeValidated(ActionRequest.class.getSimpleName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(request))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(REQUIRED));
			return;
		}

		switch (action)
		{
			case ADD_SM_FROM_FILE:
				ValidationUtil.isNull(request.getIdFileType(), EPM_ACTION_IDFILETYPE_REQUIRED,
						validationContext.getMessages());
				break;
			case APPLY_ON_DEMAND:
				validateInsertHanDevice(request, validationContext.getMessages());
				break;
			default:
				break;
		}
	}

	/**
	 * Validate insert han device.
	 * 
	 * @param request the request
	 * @param list the list
	 */
	private void validateInsertHanDevice(ActionRequest request, List<MessageInfo> list)
	{
		if (ValidationUtil.isNull(request.getAction())
				|| ValidationUtil.isNull(request.getAction().getActionType()))
		{
			list.add(MessageInfo.createFieldValidationError(ACTION_REQUIRED));
			return;
		}

		if (request.getAction().isProcessRequired()
				&& ActionTypeEnum.IMPORT_HAN_DEVICE.equals(request.getAction().getActionType().getActionTypeEnum())
				&& ValidationUtil.isNullOrEmpty(request.getAction().getDevices()))
		{
			ValidationUtil.isNull(request.getHanDevicesFile(), EPM_ACTION_HAN_DEVICES_REQUIRED, list);
		}
	}
}
