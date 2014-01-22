package com.sensus.lc.light.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleException;
import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.common.validation.ValidationUtil.isNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.light.bcf.ILightCustomSearchBCF;
import com.sensus.lc.light.bcl.ILightCustomSearchBCL;
import com.sensus.lc.light.model.CustomSearch;
import com.sensus.lc.light.model.LightFilterValue;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.CustomSearchRequest;
import com.sensus.lc.light.model.request.LightFilterRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;
import com.sensus.lc.light.model.response.CustomSearchResponse;
import com.sensus.lc.light.model.response.LightFilterResponse;

/**
 * The Class LightCustomSearchBCFImpl.
 */
public class LightCustomSearchBCFImpl implements ILightCustomSearchBCF
{

	/** The light bcl. */
	private ILightCustomSearchBCL lightCustomSearchBCL;

	/** The request validation controller. */
	private ValidationController requestValidationController;

	/** The inquiry request validation controller. */

	private ValidationController customSearchRequestValidatorController;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightCustomSearchBCFImpl.class);

	/** The Constant DEFAULT_LIGHT_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LIGHT_CUSTOM_SEARCH_BCF_EXCEPTION_MSG =
			"sensus.mlc.lightCustombcfimpl.defaultexception";

	/**
	 * Gets the request validation controller.
	 * 
	 * @return the request validation controller
	 */
	public ValidationController getRequestValidationController()
	{
		return requestValidationController;
	}

	/**
	 * Sets the request validation controller.
	 * 
	 * @param requestValidationController the new request validation controller
	 */
	public void setRequestValidationController(ValidationController requestValidationController)
	{
		this.requestValidationController = requestValidationController;
	}

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

	/**
	 * Gets the custom search request validator controller.
	 * 
	 * @return the custom search request validator controller
	 */
	public ValidationController getCustomSearchRequestValidatorController()
	{
		return customSearchRequestValidatorController;
	}

	/**
	 * Sets the custom search request validator controller.
	 * 
	 * @param customSearchRequestValidatorController the new custom search request validator controller
	 */
	public void setCustomSearchRequestValidatorController(ValidationController customSearchRequestValidatorController)
	{
		this.customSearchRequestValidatorController = customSearchRequestValidatorController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightCustomSearchBCF#fetchAllCustomSearch(com.sensus.mlc.light.model.
	 * InquiryPaginationRequest)
	 */
	@Override
	public CustomSearchResponse fetchAllCustomSearch(LightRequest request)
	{
		InternalResultsResponse<CustomSearch> internalResponse = null;
		CustomSearchResponse response = new CustomSearchResponse();

		try
		{
			ValidationContext context = new ValidationContext(
					InquiryPaginationRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_ALL_CUSTOM_SEARCH);

			if (getRequestValidationController().validate(context)) // Validate Tenant and UserContext
			{
				internalResponse = getLightCustomSearchBCL().fetchAllCustomSearch(request);
				response.setCustomSearchList(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{

			handleException(LOG, response, ex, DEFAULT_LIGHT_CUSTOM_SEARCH_BCF_EXCEPTION_MSG);

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightCustomSearchBCF#fetchLightFilters(com.sensus.mlc.light.model.request.
	 * LightFilterRequest)
	 */
	@Override
	public LightFilterResponse fetchLightFilters(LightFilterRequest request)
	{
		InternalResultsResponse<LightFilterValue> internalResponse = null;
		LightFilterResponse response = new LightFilterResponse();

		try
		{
			ValidationContext context = new ValidationContext(
					LightFilterRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_LIGHT_FILTER);

			if (getRequestValidationController().validate(context)) // Validate Tenant and UserContext
			{
				internalResponse = getLightCustomSearchBCL().fetchLightFilters(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{

			handleException(LOG, response, ex, DEFAULT_LIGHT_CUSTOM_SEARCH_BCF_EXCEPTION_MSG);

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightCustomSearchBCF#fetchAllColumnFilters(com.sensus.mlc.light.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public ColumnFilterResponse fetchAllColumnFilters(ColumnFilterRequest request)
	{
		ColumnFilterResponse response = new ColumnFilterResponse();
		try
		{
			InternalResultsResponse<ColumnFilterResponse> internalResponse = null;

			ValidationContext context = new ValidationContext(
					ColumnFilterRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_COLUMN_FILTERS);

			if (getRequestValidationController().validate(context)) // Validate Tenant and UserContext
			{
				internalResponse = getLightCustomSearchBCL().fetchAllColumnFilters(request);
				if (!isNull(internalResponse.getFirstResult()))
				{
					response = internalResponse.getFirstResult();
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_CUSTOM_SEARCH_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightCustomSearchBCF#insertCustomSearch(com.sensus.mlc.light.model.CustomSearch)
	 */
	@Override
	public CustomSearchResponse insertCustomSearch(CustomSearchRequest request)
	{
		CustomSearchResponse response = new CustomSearchResponse();
		try
		{
			InternalResultsResponse<CustomSearch> internalResponse = null;

			ValidationContext context = new ValidationContext(
					CustomSearchRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.INSERT_CUSTOM_SEARCH);

			if (getRequestValidationController().validate(context) // Validate Tenant and UserContext
					&& getCustomSearchRequestValidatorController().validate(context))
			{
				internalResponse = getLightCustomSearchBCL().insertCustomSearch(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_CUSTOM_SEARCH_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightCustomSearchBCF#insertColumnFilters(com.sensus.mlc.light.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public ColumnFilterResponse insertColumnFilters(ColumnFilterRequest request)
	{
		ColumnFilterResponse response = new ColumnFilterResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext(
					ColumnFilterRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.INSERT_COLUMN_FILTERS);

			if (getRequestValidationController().validate(context)) // Validate Tenant and UserContext
			{
				internalResponse = getLightCustomSearchBCL().insertColumnFilters(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_CUSTOM_SEARCH_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightCustomSearchBCF#deleteCustomSearch(com.sensus.mlc.light.model.request.
	 * CustomSearchRequest)
	 */
	@Override
	public CustomSearchResponse deleteCustomSearch(CustomSearchRequest request)
	{
		CustomSearchResponse response = new CustomSearchResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext(
					CustomSearchRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.DELETE_CUSTOM_SEARCH);

			if (getRequestValidationController().validate(context)) // Validate Tenant and UserContext
			{
				internalResponse = getLightCustomSearchBCL().deleteCustomSearch(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_CUSTOM_SEARCH_BCF_EXCEPTION_MSG);
		}
		return response;
	}
}
