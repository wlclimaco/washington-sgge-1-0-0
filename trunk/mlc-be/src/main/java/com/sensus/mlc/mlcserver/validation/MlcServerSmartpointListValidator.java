package com.sensus.mlc.mlcserver.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;
import java.util.Set;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.mlcserver.type.MlcGatewayNotification;
import com.sensus.mlc.mlcserver.type.Smartpoint;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;
import com.sensus.mlc.smartpoint.model.request.LightStatusRequest;
import com.sensus.mlc.smartpoint.validation.LightValidator;

/**
 * The Class MlcServerSmartpointListValidator.
 */
public class MlcServerSmartpointListValidator extends LightValidator implements IValidator
{
	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED =
			"sensus.mlc.smartpointvalidator.lightrniid.required";

	/** The smart point accessor bcl. */
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter

	/**
	 * Gets the smart point accessor bcl.
	 * 
	 * @return the smart point accessor bcl
	 */
	public ISmartPointAccessorBCL getSmartPointAccessorBCL()
	{
		return this.smartPointAccessorBCL;
	}

	/**
	 * Sets the smart point accessor bcl.
	 * 
	 * @param smartPointAccessorBCL the new smart point accessor bcl
	 */
	public void setSmartPointAccessorBCL(ISmartPointAccessorBCL smartPointAccessorBCL)
	{
		this.smartPointAccessorBCL = smartPointAccessorBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.validation.LightValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext context)
	{
		List<MessageInfo> list = context.getMessages();
		if (isNull(context.getObjectsToBeValidated()) || !isNullOrEmpty(list))
		{
			return;
		}

		Set<String> keys = context.getObjectsToBeValidated().keySet();
		for (String key : keys)
		{
			Object target = context.getObjectToBeValidated(key);
			if (isNull(target) || !MlcGatewayNotification.class.isAssignableFrom(target.getClass()))
			{
				continue;
			}

			MlcGatewayNotification notification = (MlcGatewayNotification)target;
			validateSmartpointList(list, notification);
			validateSmartpointList(list, notification.getSuccededSmartpoint(), notification.getCustomerID());
			validateSmartpointList(list, notification.getFailedSmartpoint(), notification.getCustomerID());
		}
	}

	/**
	 * Validate smartpoint list.
	 * 
	 * @param list the list
	 * @param notification the notification
	 */
	private void validateSmartpointList(List<MessageInfo> list, MlcGatewayNotification notification)
	{
		if (isNullOrEmpty(notification.getSuccededSmartpoint()) && isNullOrEmpty(notification.getFailedSmartpoint()))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED));
		}
	}

	/**
	 * Validate smartpoint list.
	 * 
	 * @param list the list
	 * @param smartpoints the smartpoints
	 * @param tenantRniCode the tenant rni code
	 */
	private void validateSmartpointList(List<MessageInfo> list, List<Smartpoint> smartpoints, String tenantRniCode)
	{
		if (isNullOrEmpty(smartpoints))
		{
			return;
		}

		for (Smartpoint smartpoint : smartpoints)
		{
			if (checkAndValidateLightRniId(list, smartpoint.getRniId()))
			{
				validateTenantLightAssociation(tenantRniCode, smartpoint.getRniId(), true, list);
			}
		}
	}

	/**
	 * Validate tenant light association.
	 * 
	 * @param tenantRniCode the tenant rni code
	 * @param lightRniId the light rni id
	 * @param strict the strict
	 * @param list the list
	 */
	private void validateTenantLightAssociation(String tenantRniCode, Integer lightRniId,
			Boolean strict, // If strict is false, light not found will not create a problem.
			List<MessageInfo> list)
	{
		LightStatusRequest request = new LightStatusRequest();
		request.setLightRniId(lightRniId);
		request.setTenantRniCode(tenantRniCode);

		// Verify if tenant and rniId are valid and associated
		InternalResponse response = getSmartPointAccessorBCL().isLightInTenant(request, strict);

		if (response.isInError())
		{
			list.addAll(response.getMessageInfoList());
		}
	}
}
