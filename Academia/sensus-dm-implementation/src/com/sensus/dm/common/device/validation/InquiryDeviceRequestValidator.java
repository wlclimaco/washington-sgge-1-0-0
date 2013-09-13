package com.sensus.dm.common.device.validation;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;

/**
 * The Class InquiryDeviceRequestValidator.
 * 
 * @author QAT Global.
 */
public class InquiryDeviceRequestValidator implements IValidator
{

	/** The Constant DEVICE_TYPE_REQUIRED. */
	private static final String DEVICE_TYPE_REQUIRED =
			"sensus.epm.devicevalidator.devicetype.required";

	/** The Constant GEOCODE_REQUIRED_REQUIRED. */
	private static final String GEOCODE_REQUIRED_REQUIRED =
			"sensus.epm.inquirydevicerequestvalidator.geocodetrunc.required";

	/** The Constant INQUIRY_DEVICE_REQUEST_REQUIRED. */
	private static final String INQUIRY_DEVICE_REQUEST_REQUIRED =
			"sensus.epm.inquirydevicerequestvalidator.inquirydevicerequest.required";

	/** The Constant DEVICE_SEARCH_REQUIRED. */
	private static final String DEVICE_SEARCH_REQUIRED = "sensus.epm.devicesearchvalidator.device.search.required";

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

		InquiryDeviceRequest inquiryDeviceRequest =
				(InquiryDeviceRequest)validationContext.getObjectToBeValidated(InquiryDeviceRequest.class
						.getSimpleName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(inquiryDeviceRequest))
		{
			validationContext.getMessages()
					.add(MessageInfo.createFieldValidationError(INQUIRY_DEVICE_REQUEST_REQUIRED));
			return;
		}

		switch (action)
		{
			case FETCH_DEVICES_BOUNDS_TO_MAP:
			case FETCH_ALL:
			case GENERATE_FILE_CSV:
			case ADD_SMP_TO_GRP:
			case DEL_SMP_FROM_GRP:
				validateDeviceType(validationContext, inquiryDeviceRequest);
				break;
			case FETCH_DEVICES_TO_MAP:
				validateDeviceType(validationContext, inquiryDeviceRequest);
				validateGeoCodeTrunc(validationContext, inquiryDeviceRequest.getGeoCodeTrunc());
				break;
			default:
				break;
		}
	}

	/**
	 * Validate device type.
	 * 
	 * @param validationContext the validation context
	 * @param deviceTypeEnum the device type enum
	 */
	private void validateDeviceType(ValidationContext validationContext, InquiryDeviceRequest inquiryDeviceRequest)
	{
		if (ValidationUtil.isNull(inquiryDeviceRequest.getDeviceType()))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(DEVICE_TYPE_REQUIRED));
		}

		if (DeviceTypeEnum.HAN_DEVICE.equals(inquiryDeviceRequest.getDeviceType()))
		{
			ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch(), DEVICE_SEARCH_REQUIRED,
					validationContext.getMessages());
		}

	}

	/**
	 * Validate geo code trunc.
	 * 
	 * @param validationContext the validation context
	 * @param geoCodeTrunc the geo code trunc
	 */
	private void validateGeoCodeTrunc(ValidationContext validationContext, Integer geoCodeTrunc)
	{
		ValidationUtil.isNull(geoCodeTrunc, GEOCODE_REQUIRED_REQUIRED, validationContext.getMessages());
	}
}
