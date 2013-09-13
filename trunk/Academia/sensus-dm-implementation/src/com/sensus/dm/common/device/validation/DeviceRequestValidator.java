package com.sensus.dm.common.device.validation;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;

/**
 * The Class DeviceRequestValidator.
 * 
 * @author QAT Global.
 */
public class DeviceRequestValidator implements IValidator
{

	/** The Constant GRANTED_AUTHORITY_REQUIRED. */
	private static final String GRANTED_AUTHORITY_REQUIRED =
			"sensus.epm.devicerequestvalidator.granted.authority.required";

	/** The Constant DEVICEREQUEST_REQUIRED. */
	private static final String DEVICEREQUEST_REQUIRED =
			"sensus.epm.devicerequestvalidator.devicerequest.required";

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

		DeviceRequest deviceRequest =
				(DeviceRequest)validationContext.getObjectToBeValidated(DeviceRequest.class.getSimpleName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(deviceRequest))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(DEVICEREQUEST_REQUIRED));
			return;
		}

		switch (action)
		{
			case FETCH_SERVICES_BY_DEVICE_TYPE:
				ValidationUtil.isNullOrEmpty(deviceRequest.getGrantedAuthorityList(), GRANTED_AUTHORITY_REQUIRED,
						validationContext.getMessages());
				break;
			default:
				break;
		}

	}

}
