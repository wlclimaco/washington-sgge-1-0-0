package com.sensus.mlc.smartpoint.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.UserContext;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.ListTypeEnum;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class CustomSearchValidator.
 */
public class CustomSearchValidator implements IValidator
{

	/** The Constant SAVED_SEARCH. */
	private static final String SAVED_SEARCH = "Saved Search";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_CUSTOM_SEARCH_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_CUSTOM_SEARCH_REQUIRED =
			"sensus.mlc.smartpointvalidator.custom.search.required";

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_NAME_REQUIRED = "sensus.mlc.validator.name.required";

	/** The Constant SENSUS_MLC_USERVALIDATOR_LIST_TYPE_ENUM_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIST_TYPE_ENUM_REQUIRED =
			"sensus.mlc.smartpointvalidator.listtypeenum.required";

	/** The smart point accessor bcl. */
	private ISmartPointAccessorBCL smartPointAccessorBCL;

	/**
	 * Gets the smart point accessor bcl.
	 * 
	 * @return the smart point accessor bcl
	 */
	public ISmartPointAccessorBCL getSmartPointAccessorBCL()
	{
		return smartPointAccessorBCL;
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
	 * @see com.sensus.common.validation.AbstractValidator#validate(java.lang.Object, java.util.List,
	 * java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void validate(ValidationContext context)
	{
		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)context.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		if (isNull(action))
		{
			return;
		}

		CustomSearch customSearch =
				(CustomSearch)context.getObjectToBeValidated(CustomSearch.class.getSimpleName());

		List<MessageInfo> list = context.getMessages();

		switch (action)
		{
			case DELETE_CUSTOM_SEARCH:
			case FETCH_BY_ID:
				validateCustomSearchId(list, customSearch);
				return;
			case INSERT_CUSTOM_SEARCH:
				if (validateCustomSearch(list, customSearch))
				{
					Tenant tenant = (Tenant)context.getObjectToBeValidated(Tenant.class.getSimpleName());
					UserContext userContext =
							(UserContext)context.getObjectToBeValidated(UserContext.class.getSimpleName());
					List<Integer> allowedGroupIdList =
							(List<Integer>)context
									.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.ALLOWED_GROUP_ID_LIST.getValue());
					validateCustomSearchName(list, customSearch, tenant, userContext, allowedGroupIdList);
				}
				return;
			case FETCH_COLUMN_FILTERS:
			case INSERT_COLUMN_FILTERS:
				ListTypeEnum listTypeEnum =
						(ListTypeEnum)context.getObjectToBeValidated(ListTypeEnum.class.getSimpleName());
				validateListTypeEnum(list, listTypeEnum);
				return;
			default:
				break;
		}

	}

	/**
	 * Validate list type enum.
	 * 
	 * @param list the list
	 * @param listTypeEnum the list type enum
	 */
	private void validateListTypeEnum(List<MessageInfo> list, ListTypeEnum listTypeEnum)
	{
		isNull(listTypeEnum, SENSUS_MLC_SMARTPOINTVALIDATOR_LIST_TYPE_ENUM_REQUIRED, list);
	}

	/**
	 * Validate custom search id.
	 * 
	 * @param list the list
	 * @param customSearch the custom search
	 */
	private void validateCustomSearchId(List<MessageInfo> list, CustomSearch customSearch)
	{
		if (validateCustomSearch(list, customSearch))
		{
			validateCustomSearchId(list, customSearch.getId());
		}
	}

	/**
	 * Validate custom search id.
	 * 
	 * @param list the list
	 * @param customSearchId the custom search id
	 */
	private void validateCustomSearchId(List<MessageInfo> list, Integer customSearchId)
	{
		LCHelp.isNullOrZeroLC(customSearchId, SENSUS_MLC_VALIDATOR_ID_REQUIRED, list, SAVED_SEARCH);
	}

	/**
	 * Validate custom search name.
	 * 
	 * @param list the list
	 * @param customSearch the custom search
	 * @param tenant the tenant
	 * @param userContext the user context
	 * @param allowedGroupIdList the allowed group id list
	 */
	private void validateCustomSearchName(List<MessageInfo> list, CustomSearch customSearch, Tenant tenant,
			UserContext userContext, List<Integer> allowedGroupIdList)
	{
		if (validateCustomSearch(list, customSearch))
		{
			if (validateCustomSearchName(list, customSearch.getName()))
			{
				getSmartPointAccessorBCL().fetchCanInsertCustomSearch(tenant,
						customSearch, userContext.getId(), list, allowedGroupIdList);
			}
		}
	}

	/**
	 * Validate custom search.
	 * 
	 * @param list the list
	 * @param customSearch the custom search
	 * @return the boolean
	 */
	private Boolean validateCustomSearch(List<MessageInfo> list, CustomSearch customSearch)
	{
		if (isNull(customSearch))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_CUSTOM_SEARCH_REQUIRED));
			return false;
		}
		return true;
	}

	/**
	 * Validate custom search name.
	 * 
	 * @param list the list
	 * @param customSearchName the custom search name
	 * @return the boolean
	 */
	private Boolean validateCustomSearchName(List<MessageInfo> list, String customSearchName)
	{
		if (isNull(customSearchName))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_NAME_REQUIRED, SAVED_SEARCH));
			return false;
		}
		return true;
	}
}
