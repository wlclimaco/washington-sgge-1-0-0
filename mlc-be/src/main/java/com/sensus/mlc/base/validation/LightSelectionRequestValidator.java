package com.sensus.mlc.base.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;

import java.util.List;
import java.util.Set;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.model.request.LightSelectionRequest;

/**
 * The Class SelectionPaginationValidator.
 */
public class LightSelectionRequestValidator implements IValidator
{
	/** The Constant SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED =
			"sensus.mlc.validator.is.monitored.required";

	/** The Constant SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED =
			"sensus.mlc.validator.selection.pagination.id.required";

	/** The Constant SENSUS_MLC_SELECTION_PAGINATION_ALLSELECTED_REQUIRED. */
	private static final String SENSUS_MLC_SELECTION_PAGINATION_ALLSELECTED_REQUIRED =
			"sensus.mlc.validator.selection.pagination.allselected.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext context)
	{
		if (isNull(context.getObjectsToBeValidated()))
		{
			return;
		}

		List<MessageInfo> list = context.getMessages();
		if (!isNullOrEmpty(list))
		{
			return;
		}

		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)context.getValidationArguments().get(getSlcActionName());

		Set<String> keys = context.getObjectsToBeValidated().keySet();
		for (String key : keys)
		{
			Object target = context.getObjectToBeValidated(key);

			if (isNull(target) || !LightSelectionRequest.class.isAssignableFrom(target.getClass()))
			{
				continue;
			}

			validationLightSelectionRequest((LightSelectionRequest)target, action, list);

		}

	}

	/**
	 * Validation light selection request.
	 * 
	 * @param request the request
	 * @param action the action
	 * @param list the list
	 */
	private void validationLightSelectionRequest(LightSelectionRequest request, MLCPersistanceActionEnum action,
			List<MessageInfo> list)
	{
		if (isNull(action))
		{
			validateSelectionPagination(list, request);
			return;
		}

		switch (action)
		{
			case INITIATE_UPDATE:
			case GATEWAY_CLEAR_SCHEDULE:
			case GATEWAY_APPLY_SCHEDULE:
				validateIsMonitored(list, request.isMonitored());
				break;
			case UPDATE_LIGHT_INTENSITY:
			case UPDATE_LIGHT_STATUS:
				validateIsMonitored(list, request.isMonitored());
				validateSelectionPagination(list, request);
				break;
			default:
				validateSelectionPagination(list, request);
				break;
		}
	}

	/**
	 * Validate schedule Is Monitored.
	 * 
	 * @param list the list
	 * @param isMonitored the is monitored
	 */
	private void validateIsMonitored(List<MessageInfo> list, Boolean isMonitored)
	{
		ValidationUtil.isNull(isMonitored, SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED,
				list);
	}

	/**
	 * Validate selection pagination.
	 * 
	 * @param list the list
	 * @param request the request
	 */
	private void validateSelectionPagination(List<MessageInfo> list, LightSelectionRequest request)
	{
		if (isNull(request.getPaginationAllSelected()) ||
				!request.getPaginationAllSelected())
		{

			isNullOrEmpty(request.getSelectionPaginationIds(), SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED,
					list);
			return;
		}

		isNull(request.getPaginationAllSelected(), SENSUS_MLC_SELECTION_PAGINATION_ALLSELECTED_REQUIRED, list);
	}
}
