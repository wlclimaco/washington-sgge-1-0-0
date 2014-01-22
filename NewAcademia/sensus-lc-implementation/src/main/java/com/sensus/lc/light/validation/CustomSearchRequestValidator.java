package com.sensus.lc.light.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.light.bcl.ILightCustomSearchBCL;
import com.sensus.lc.light.model.CustomSearch;
import com.sensus.lc.light.model.criteria.LightCustomSearchCriteria;
import com.sensus.lc.light.model.request.CustomSearchRequest;
import com.sensus.lc.light.model.request.LightRequest;

/**
 * Used to validate the LightRequest class.
 */
public class CustomSearchRequestValidator implements IValidator
{
	/** The Constant SAVED_SEARCH. */
	private static final String SAVED_SEARCH = "Saved Search";

	/** The Constant CUSTOM_SEARCH_REQUEST_NAME. */
	private static final String CUSTOM_SEARCH_REQUEST_NAME = CustomSearchRequest.class.getSimpleName();

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_NAME_REQUIRED = "sensus.mlc.validator.name.required";

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_ALREADY_EXIST. */
	private static final String SENSUS_MLC_VALIDATOR_NAME_ALREADY_EXIST =
			"sensus.mlc.customsearchvalidator.customsearch.already.exists";

	/** The light custom search bcl. */
	private ILightCustomSearchBCL lightCustomSearchBCL;

	/**
	 * Gets the light custom search bcl.
	 * 
	 * @return the light custom search bcl
	 */
	public ILightCustomSearchBCL getLightCustomSearchBCL()
	{
		return lightCustomSearchBCL;
	}

	/**
	 * Sets the light custom search bcl.
	 * 
	 * @param lightCustomSearchBCL the new light custom search bcl
	 */
	public void setLightCustomSearchBCL(ILightCustomSearchBCL lightCustomSearchBCL)
	{
		this.lightCustomSearchBCL = lightCustomSearchBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext context)
	{
		List<MessageInfo> list = context.getMessages();
		if (!isNullOrEmpty(list))
		{
			return;
		}

		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)context.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		CustomSearchRequest request =
				(CustomSearchRequest)context.getObjectToBeValidated(CUSTOM_SEARCH_REQUEST_NAME);

		if (isNull(action) || isNull(request))
		{
			return;
		}

		switch (action)
		{
			case INSERT_CUSTOM_SEARCH:
				validateCustomSearchName(list, request);
				break;
			default:
				break;
		}

	}

	/**
	 * Validate custom search name.
	 * 
	 * @param list the list
	 * @param request the request
	 */
	private void validateCustomSearchName(List<MessageInfo> list, CustomSearchRequest request)
	{
		if (!validateCustomSearchName(list, request.getCustomSearch().getName()))
		{
			return;
		}
		LightRequest lightRequest = new LightRequest();
		LightCustomSearchCriteria lightCustomSearchCriteria = new LightCustomSearchCriteria();
		lightCustomSearchCriteria.setCustomSearchName(request.getCustomSearch().getName());
		lightRequest.setUserContext(request.getUserContext());
		lightRequest.setLightCustomSearchCriteria(lightCustomSearchCriteria);
		InternalResultsResponse<CustomSearch> response = getLightCustomSearchBCL().fetchAllCustomSearch(lightRequest);

		if (response.hasResults())
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_NAME_ALREADY_EXIST, SAVED_SEARCH));
		}

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
