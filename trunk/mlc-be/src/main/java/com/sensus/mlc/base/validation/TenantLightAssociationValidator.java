package com.sensus.mlc.base.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;
import com.sensus.mlc.smartpoint.model.request.LightStatusRequest;

/**
 * The Class TenantLightAssociationValidator.
 */
public class TenantLightAssociationValidator implements IValidator
{

	/** The Constant LIGHT_RNI_ID. */
	private static final String LIGHT_RNI_ID = "lightRniId";

	/** The Constant CUSTUMER_ID. */
	private static final String CUSTUMER_ID = "custumerId";

	/** The smart point accessor bcl. */
	private ISmartPointAccessorBCL smartPointAccessorBCL;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext context)
	{
		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)context.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		if (isNull(context.getObjectsToBeValidated()) || isNull(action))
		{
			return;
		}

		List<MessageInfo> list = context.getMessages();
		if (!isNullOrEmpty(list))
		{
			return;
		}

		Integer lightRniId = (Integer)context.getObjectToBeValidated(LIGHT_RNI_ID);
		String tenantRniCode = (String)context.getObjectToBeValidated(CUSTUMER_ID);

		validateTenantLightAssociation(tenantRniCode, lightRniId, true, list);

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
		LightStatusRequest request = new LightStatusRequest(new UserContext());

		request.setLightRniId(lightRniId);
		request.setTenantRniCode(tenantRniCode);

		// Verify if tenant and rniId are valid and associated
		InternalResponse response = getSmartPointAccessorBCL().isLightInTenant(request, strict);

		if (response.isInError())
		{
			list.addAll(response.getMessageInfoList());
		}
	}

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

}
