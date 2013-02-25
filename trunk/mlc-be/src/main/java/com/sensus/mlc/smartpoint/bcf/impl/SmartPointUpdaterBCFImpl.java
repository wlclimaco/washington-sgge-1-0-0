package com.sensus.mlc.smartpoint.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.mlc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.mlc.base.model.ListTypeEnum;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF;
import com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.CustomSearchResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class SmartPointUpdaterBCFImpl.
 */
public class SmartPointUpdaterBCFImpl extends AbstractBaseBCF implements ISmartPointUpdaterBCF
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SmartPointUpdaterBCFImpl.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_LIGHT_BCF_EXCEPTION_MSG = "sensus.mlc.smartpointbcfimpl.defaultexception";

	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant CUSTOM_SEARCH_REQUEST_NAME. */
	private static final String CUSTOM_SEARCH_REQUEST_NAME = CustomSearchRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant CUSTOM_SEARCH_NAME. */
	private static final String CUSTOM_SEARCH_NAME = CustomSearch.class.getSimpleName();

	/** The smart point updater bcl. */
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter

	/** The process bcf. */
	private IProcessBCF processBCF; // injected by Spring through setter

	/** The custom search validation controller. */
	private ValidationController customSearchValidationController; // injected by Spring through setter

	/** The light validation controller. */
	private ValidationController lightValidationController; // injected by Spring through setter

	/** The column filter validation controller. */
	private ValidationController columnFilterValidationController; // injected by Spring through setter

	/**
	 * Gets the column filter validation controller.
	 *
	 * @return the column filter validation controller
	 */
	public ValidationController getColumnFilterValidationController()
	{
		return columnFilterValidationController;
	}

	/**
	 * Sets the column filter validation controller.
	 *
	 * @param columnFilterValidationController the new column filter validation controller
	 */
	public void setColumnFilterValidationController(ValidationController columnFilterValidationController)
	{
		this.columnFilterValidationController = columnFilterValidationController;
	}

	/**
	 * Gets the light validation controller.
	 *
	 * @return the light validation controller
	 */
	public ValidationController getLightValidationController()
	{
		return lightValidationController;
	}

	/**
	 * Sets the light validation controller.
	 *
	 * @param lightValidationController the new light validation controller
	 */
	public void setLightValidationController(ValidationController lightValidationController)
	{
		this.lightValidationController = lightValidationController;
	}

	/**
	 * Gets the smart point updater bcl.
	 *
	 * @return the smart point updater bcl
	 */
	public ISmartPointUpdaterBCL getSmartPointUpdaterBCL()
	{
		return smartPointUpdaterBCL;
	}

	/**
	 * Sets the smart point updater bcl.
	 *
	 * @param smartPointUpdaterBCL the new smart point updater bcl
	 */
	public void setSmartPointUpdaterBCL(ISmartPointUpdaterBCL smartPointUpdaterBCL)
	{
		this.smartPointUpdaterBCL = smartPointUpdaterBCL;
	}

	/**
	 * Gets the process bcf.
	 *
	 * @return the process bcf
	 */
	public IProcessBCF getProcessBCF()
	{
		return processBCF;
	}

	/**
	 * Sets the process bcf.
	 *
	 * @param processBCF the new process bcf
	 */
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/**
	 * Gets the custom search validation controller.
	 *
	 * @return the custom search validation controller
	 */
	public ValidationController getCustomSearchValidationController()
	{
		return customSearchValidationController;
	}

	/**
	 * Sets the custom search validation controller.
	 *
	 * @param customSearchRequestValidationController the new custom search validation controller
	 */
	public void setCustomSearchValidationController(ValidationController customSearchRequestValidationController)
	{
		customSearchValidationController = customSearchRequestValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF#upsertLightProperty(com.sensus.mlc.smartpoint.model.request
	 * .LightStatusNotificationRequest)
	 */
	@Override
	public LightResponse upsertLightProperty(LightStatusNotificationRequest lightStatusNotificationRequest)
	{
		LightResponse response = new LightResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(
					getSlcActionName(),
					MLCPersistanceActionEnum.GATEWAY_UPSERT_LIGHT_PROPERTY);
			context.putObjectToBeValidated(
					LightStatusNotificationRequest.class.getSimpleName(),
					lightStatusNotificationRequest);
			context.putObjectToBeValidated(LIGHT_NAME, lightStatusNotificationRequest.getLight());
			context.putObjectToBeValidated(
					ObjectToBeValidatedKeyEnum.LIGHT_PARAMETERS.getValue(),
					lightStatusNotificationRequest.getLightParameters());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightValidationController().validate(context))
			{

			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);

		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF#updateLightProtected(com.sensus.mlc.smartpoint.model.request
	 * .LightRequest)
	 */
	@Override
	public LightResponse updateLightProtected(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();

		try
		{
			InternalResultsResponse<Light> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(
					getSlcActionName(),
					MLCPersistanceActionEnum.UPDATE_LIGHT_PROTECTED);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);
			context.putObjectToBeValidated(
					ObjectToBeValidatedKeyEnum.LIGHT_PROTECTED.getValue(),
					lightRequest.getProtect());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightValidationController().validate(context)
					&& getLightSelectionRequestValidationController().validate(context))
			{
				internalResponse = getSmartPointUpdaterBCL().updateLightProtected(lightRequest);
				response.setLights(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF#insertCustomSearch(com.sensus.mlc.smartpoint.model.request
	 * .CustomSearchRequest)
	 */
	@Override
	public CustomSearchResponse insertCustomSearch(CustomSearchRequest customSearchRequest)
	{
		CustomSearchResponse response = new CustomSearchResponse();

		try
		{
			InternalResultsResponse<CustomSearch> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(
					getSlcActionName(),
					MLCPersistanceActionEnum.INSERT_CUSTOM_SEARCH);
			context.putObjectToBeValidated(CUSTOM_SEARCH_REQUEST_NAME, customSearchRequest);
			context.putObjectToBeValidated(CUSTOM_SEARCH_NAME, customSearchRequest.getCustomSearch());
			context.putObjectToBeValidated(Tenant.class.getSimpleName(), customSearchRequest.getTenant());
			context.putObjectToBeValidated(UserContext.class.getSimpleName(), customSearchRequest.getUserContext());
			context.putObjectToBeValidated(
					ObjectToBeValidatedKeyEnum.ALLOWED_GROUP_ID_LIST.getValue(),
					customSearchRequest.getAllowedGroupIdList());

			if (getLightingControlRequestValidationController().validate(context)
					&& getCustomSearchValidationController().validate(context))
			{
				context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.COLUMN_LIST.getValue(), customSearchRequest
						.getCustomSearch().getListColumn());
				if (getColumnFilterValidationController().validate(context))
				{
					internalResponse = getSmartPointUpdaterBCL().insertCustomSearch(customSearchRequest);
					response.setCustomSearches(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF#deleteCustomSearch(com.sensus.mlc.smartpoint.model.request
	 * .CustomSearchRequest)
	 */
	@Override
	public CustomSearchResponse deleteCustomSearch(CustomSearchRequest customSearchRequest)
	{
		CustomSearchResponse response = new CustomSearchResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(
					getSlcActionName(),
					MLCPersistanceActionEnum.DELETE_CUSTOM_SEARCH);
			context.putObjectToBeValidated(CUSTOM_SEARCH_REQUEST_NAME, customSearchRequest);
			context.putObjectToBeValidated(CUSTOM_SEARCH_NAME, customSearchRequest.getCustomSearch());

			if (getLightingControlRequestValidationController().validate(context)
					&& getCustomSearchValidationController().validate(context))
			{
				internalResponse = getSmartPointUpdaterBCL().deleteCustomSearch(customSearchRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF#updateLightLatLng(com.sensus.mlc.smartpoint.model.request
	 * .LightRequest)
	 */
	@Override
	public LightResponse updateLightLatLng(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(
					getSlcActionName(),
					MLCPersistanceActionEnum.UPDATE_LIGHT_LAT_LNG);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);
			context.putObjectToBeValidated(LIGHT_NAME, lightRequest.getFirstLight());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightValidationController().validate(context))
			{
				// FIXME - properties
				/*context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.PARAMETERS.getValue(), lightRequest
						.getFirstLight().getParameters());*/

				if (getGeoCodeValidationController().validate(context))
				{
					internalResponse = getSmartPointUpdaterBCL().updateLightLocation(lightRequest);
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF#insertCSVProcess(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest)
	{
		return getProcessBCF().insertCSVProcess(lightSelectionRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF#resetMinMaxValue(com.sensus.mlc.smartpoint.model.request.
	 * LightRequest)
	 */
	@Override
	public LightResponse resetMinMaxValue(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();

		try
		{
			InternalResultsResponse<Light> internalResponse = null;

			ValidationContext context = new ValidationContext(
					LIGHT_REQUEST_NAME,
					lightRequest,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.RESET_MIN_MAX);

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightSelectionRequestValidationController().validate(context))
			{
				internalResponse = getSmartPointUpdaterBCL().resetMinMaxValue(lightRequest);
				response.setLights(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF#insertColumnFilters(com.sensus.mlc.smartpoint.model.request
	 * .ColumnFilterRequest)
	 */
	@Override
	public ColumnFilterResponse insertColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		ColumnFilterResponse response = new ColumnFilterResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(
					getSlcActionName(),
					MLCPersistanceActionEnum.INSERT_COLUMN_FILTERS);
			context.putObjectToBeValidated(ColumnFilterRequest.class.getSimpleName(), columnFilterRequest);
			context.putObjectToBeValidated(ListTypeEnum.class.getSimpleName(), columnFilterRequest.getListTypeEnum());

			if (getLightingControlRequestValidationController().validate(context)
					&& getCustomSearchValidationController().validate(context))
			{
				internalResponse = getSmartPointUpdaterBCL().insertColumnFilters(columnFilterRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF#updateLightStatus(com.sensus.mlc.smartpoint.model.request
	 * .LightRequest)
	 */
	@Override
	public LightResponse updateLightStatus(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();

		try
		{
			InternalResultsResponse<Light> internalResponse = null;

			ValidationContext context = new ValidationContext(
					LIGHT_REQUEST_NAME,
					lightRequest,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.UPDATE_LIGHT_STATUS);

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightSelectionRequestValidationController().validate(context))
			{
				internalResponse = getSmartPointUpdaterBCL().updateLightStatus(lightRequest);
				response.setLights(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}
}
