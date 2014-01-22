package com.sensus.lc.server.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.common.validation.ValidationUtil.isNullOrZero;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.validation.LightValidator;
import com.sensus.lc.tenant.bcl.ITenantBCL;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.tenant.model.request.TenantRequest;
import com.sensus.mlc.mlcserver.type.MlcGatewayNotification;
import com.sensus.mlc.mlcserver.type.Smartpoint;

/**
 * The Class MlcServerSmartpointListValidator.
 */
public class MlcServerSmartpointListValidator extends LightValidator implements IValidator
{
	/** Constants messages errors. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED =
			"sensus.mlc.lightvalidator.lightrniid.required";
	private static final String LIGHT_NOT_IN_TENANT = "sensus.mlc.smartpointvalidator.light.not.in.tenant";
	private static final String LIGHT_NOT_FOUND = "sensus.mlc.smartpointvalidator.light.not.found";
	private static final String TENANT_NOT_FOUND = "sensus.mlc.smartpointvalidator.tenant.not.found";

	/** BCL Atributes. */
	private ILightBCL lightBCL; // injected by Spring through setter
	private ITenantBCL tenantBCL; // injected by Spring through setter

	/**
	 * @return the lightBCL
	 */
	public ILightBCL getLightBCL()
	{
		return lightBCL;
	}

	/**
	 * @param lightBCL the lightBCL to set
	 */
	public void setLightBCL(ILightBCL lightBCL)
	{
		this.lightBCL = lightBCL;
	}

	/**
	 * Gets the tenant bcl.
	 * 
	 * @return the tenant bcl
	 */
	public ITenantBCL getTenantBCL()
	{
		return tenantBCL;
	}

	/**
	 * Sets the tenant bcl.
	 * 
	 * @param tenantBCL the new tenant bcl
	 */
	public void setTenantBCL(ITenantBCL tenantBCL)
	{
		this.tenantBCL = tenantBCL;
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
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED));
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
				validateTenantLightAssociation(tenantRniCode, smartpoint.getRniId(), list);
			}

		}
	}

	/**
	 * Check and validate light rni id.
	 * 
	 * @param list the list
	 * @param rniId the rni id
	 * @return true, if successful
	 */
	public boolean checkAndValidateLightRniId(List<MessageInfo> list, Integer rniId)
	{
		if (isNullOrZero(rniId))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED));
			return false;
		}
		return true;
	}

	/**
	 * Validate tenant light association.
	 * 
	 * @param tenantRniCode the tenant rni code
	 * @param lightRniId the light rni id
	 * @param strict the strict
	 * @param list the list
	 */
	protected void validateTenantLightAssociation(String tenantRniCode, Integer lightRniId, List<MessageInfo> list)
	{
		TenantRequest tenantRequest = new TenantRequest();
		tenantRequest.setTenant(new Tenant(tenantRniCode));
		InternalResultsResponse<Tenant> tenantResponse = getTenantBCL().fetchTenantByRniCode(tenantRequest);

		// Check tenant exist
		Tenant tenant = tenantResponse.getFirstResult();
		if (ValidationUtil.isNull(tenant))
		{
			list.add(MessageInfo.createFieldValidationError(TENANT_NOT_FOUND, tenantRniCode));
			return;
		}

		// Check light exist
		FetchByIdRequest request = new FetchByIdRequest(null, BigInteger.valueOf(lightRniId));
		InternalResultsResponse<Light> lightResponse = getLightBCL().fetchById(request);
		if (!lightResponse.hasResults())
		{
			list.add(MessageInfo.createFieldValidationError(LIGHT_NOT_FOUND, lightRniId));
			return;
		}

		// Check light belong to tenant
		if (!tenant.getId().equals(lightResponse.getFirstResult().getTenant().getId()))
		{
			list.add(MessageInfo.createFieldValidationError(LIGHT_NOT_IN_TENANT, lightRniId, tenantRniCode));
		}
	}
}
