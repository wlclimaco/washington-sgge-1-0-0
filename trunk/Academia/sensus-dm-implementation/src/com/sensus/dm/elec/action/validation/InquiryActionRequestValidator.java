package com.sensus.dm.elec.action.validation;

import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.elec.action.model.request.InquiryActionRequest;

/**
 * The Class InquiryActionRequestValidator.
 * 
 * @author QAT Brazil.
 */
public class InquiryActionRequestValidator implements IValidator
{
	/** The Constant REQUIRED. */
	private static final String REQUIRED = "sensus.epm.actionvalidator.action.required";

	/** The Constant EPM_ACTION_TYPE_REQUIRED. */
	private static final String EPM_ACTION_DEVICE_TYPE_REQUIRED = "sensus.epm.actionvalidator.device.type.required";

	/** The Constant EPM_ACTION_DEVICE_REQUIRED. */
	private static final String EPM_ACTION_DEVICE_REQUIRED = "sensus.epm.actionvalidator.device.required";

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

		InquiryActionRequest request =
				(InquiryActionRequest)validationContext.getObjectToBeValidated(InquiryActionRequest.class
						.getSimpleName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(request))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(REQUIRED));
			return;
		}

		switch (action)
		{
			case APPLY_DEVICE_TO_ACTION:
				validationDeviceToAction(request, validationContext.getMessages());
				break;
			default:
				break;
		}
	}

	/**
	 * Validation device to action.
	 * 
	 * @param request the request
	 * @param list the list
	 */
	private void validationDeviceToAction(InquiryActionRequest request, List<MessageInfo> list)
	{
		if (ValidationUtil.isNullOrEmpty(request.getActions())
				|| ValidationUtil.isNull(request.getFirstAction().getActionType()))
		{
			list.add(MessageInfo.createFieldValidationError(REQUIRED));
			return;
		}

		if (ActionTypeEnum.GET_DEMAND_RESPONSE_EVENT_STATUS.equals(request.getFirstAction().getActionType()
				.getActionTypeEnum()))
		{
			return;
		}

		if (ValidationUtil.isNull(request.getPaginationAllSelected()) || !request.getPaginationAllSelected())
		{
			if (ValidationUtil.isNullOrEmpty(request.getActions()))
			{
				list.add(MessageInfo.createFieldValidationError(REQUIRED));
			}

			if (ValidationUtil.isNullOrEmpty(request.getFirstAction().getDevices()))
			{
				list.add(MessageInfo.createFieldValidationError(EPM_ACTION_DEVICE_REQUIRED));
				return;
			}
			validationDeviceType(request.getFirstAction().getDevices(), list);
		}
	}

	/**
	 * Validation device type.
	 * 
	 * @param deviceList the device list
	 * @param list the list
	 */
	private void validationDeviceType(List<Device> deviceList, List<MessageInfo> list)
	{
		for (Device device : deviceList)
		{

			ValidationUtil.isNull(device.getDeviceType(), EPM_ACTION_DEVICE_TYPE_REQUIRED, list);
		}
	}
}
