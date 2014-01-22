package com.sensus.lc.base.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.server.validation.MlcServerSmartpointListValidator;

/**
 * The Class TenantLightAssociationValidator.
 */
public class TenantLightAssociationValidator extends MlcServerSmartpointListValidator implements IValidator
{
	/** Others constants. */
	private static final String LIGHT_RNI_ID = "lightRniId";
	private static final String CUSTUMER_ID = "custumerId";

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

		validateTenantLightAssociation(tenantRniCode, lightRniId, list);

	}
}
