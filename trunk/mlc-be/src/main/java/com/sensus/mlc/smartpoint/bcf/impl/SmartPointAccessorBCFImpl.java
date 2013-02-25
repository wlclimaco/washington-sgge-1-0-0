package com.sensus.mlc.smartpoint.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.util.LCHelp.setLightProperties;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.mlc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.mlc.base.model.ListTypeEnum;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.model.MLCSortExpression;
import com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.CustomSearchOrderByEnum;
import com.sensus.mlc.smartpoint.model.GeocodeSmartpointInfo;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightHistory;
import com.sensus.mlc.smartpoint.model.LightOrderByEnum;
import com.sensus.mlc.smartpoint.model.PropertyValidValue;
import com.sensus.mlc.smartpoint.model.SensusPartNumberConfiguration;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightingConfigurationRequest;
import com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.CountLightResponse;
import com.sensus.mlc.smartpoint.model.response.CurrentAlarmWarningMessageResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryCustomSearchResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryGeocodeSmartpointInfoResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;
import com.sensus.mlc.smartpoint.model.response.LightingConfigurationsResponse;
import com.sensus.mlc.smartpoint.model.response.PropertyValidValuesResponse;
import com.sensus.mlc.smartpoint.model.response.StatusMessageResponse;

/**
 * The Class SmartPointAccessorBCFImpl.
 */
public class SmartPointAccessorBCFImpl extends AbstractBaseBCF implements ISmartPointAccessorBCF
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SmartPointAccessorBCFImpl.class);

	/** The Constant INQUIRY_LIGHT_REQUEST_NAME. */
	private static final String INQUIRY_LIGHT_REQUEST_NAME = InquiryLightRequest.class.getSimpleName();

	/** The Constant LIGHTING_CONTROL_REQUEST_NAME. */
	private static final String LIGHTING_CONTROL_REQUEST_NAME = LightingControlRequest.class.getSimpleName();

	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_LIGHT_BCF_EXCEPTION_MSG = "sensus.mlc.smartpointbcfimpl.defaultexception";

	/** The smart point accessor bcl. */
	private ISmartPointAccessorBCL smartPointAccessorBCL;

	/** The light controller. */
	private ValidationController lightValidationController;

	/** The properties validation controller. */
	private ValidationController propertiesValidationController;

	/** The custom search validation controller. */
	private ValidationController customSearchValidationController;

	/** The csv file controller. */
	private ValidationController csvFileValidationController;

	/**
	 * Gets the csv file validation controller.
	 * 
	 * @return the csv file validation controller
	 */
	public ValidationController getCsvFileValidationController()
	{
		return csvFileValidationController;
	}

	/**
	 * Sets the csv file validation controller.
	 * 
	 * @param csvFileValidationController the new csv file validation controller
	 */
	public void setCsvFileValidationController(ValidationController csvFileValidationController)
	{
		this.csvFileValidationController = csvFileValidationController;
	}

	/** The process validation controller. */
	private ValidationController processValidationController;

	/**
	 * Gets the process validation controller.
	 * 
	 * @return the process validation controller
	 */
	public ValidationController getProcessValidationController()
	{
		return processValidationController;
	}

	/**
	 * Sets the process validation controller.
	 * 
	 * @param processValidationController the new process validation controller
	 */
	public void setProcessValidationController(ValidationController processValidationController)
	{
		this.processValidationController = processValidationController;
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

	/**
	 * Gets the properties validation controller.
	 * 
	 * @return the properties validation controller
	 */
	public ValidationController getPropertiesValidationController()
	{
		return propertiesValidationController;
	}

	/**
	 * Sets the properties validation controller.
	 * 
	 * @param propertiesValidationController the new properties validation controller
	 */
	public void setPropertiesValidationController(ValidationController propertiesValidationController)
	{
		this.propertiesValidationController = propertiesValidationController;
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
	 * @see com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#fetchAllLights(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InquiryLightResponse fetchAllLights(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();

		try
		{
			InternalResultsResponse<Light> internalResponse = null;

			ValidationContext context =
					new ValidationContext(INQUIRY_LIGHT_REQUEST_NAME, inquiryLightRequest,
							MLCPersistanceActionEnum.getSlcActionName(),
							MLCPersistanceActionEnum.FETCH);

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context))
			{
				fetchDefaultsFetchAll(inquiryLightRequest);
				internalResponse = getSmartPointAccessorBCL().fetchAllLights(inquiryLightRequest);
				setLightProperties(response.getLights());
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
	 * @see com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#countLights(com.sensus.mlc.base.model.request.
	 * LightingControlRequest)
	 */
	@Override
	public CountLightResponse countLights(LightingControlRequest lightingControlRequest)
	{
		CountLightResponse response = new CountLightResponse();

		try
		{
			InternalResultsResponse<Integer> internalResponse = null;

			ValidationContext context =
					new ValidationContext(LIGHTING_CONTROL_REQUEST_NAME, lightingControlRequest,
							MLCPersistanceActionEnum.getSlcActionName(),
							MLCPersistanceActionEnum.COUNT_LIGHTS);

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context))
			{
				internalResponse = getSmartPointAccessorBCL().countLights(lightingControlRequest);
				response.setDeviceLightCount(internalResponse.getFirstResult());
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
	 * @see com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#fetchLightById(com.sensus.mlc.smartpoint.model.request.
	 * LightRequest)
	 */
	@Override
	public LightResponse fetchLightById(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();

		try
		{
			InternalResultsResponse<Light> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.FETCH_BY_LIGHT);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);
			context.putObjectToBeValidated(LIGHT_NAME, lightRequest.getFirstLight());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
					&& getLightValidationController().validate(context))
			{
				internalResponse = getSmartPointAccessorBCL().fetchLightById(lightRequest);
				response.setLights(internalResponse.getResultsList());
				setLightProperties(response.getLights());
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#fetchLightIdByRniId(com.sensus.mlc.smartpoint.model.request
	 * .LightRequest)
	 */
	@Override
	public LightResponse fetchLightIdByRniId(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();

		try
		{
			InternalResultsResponse<Light> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.FETCH_BY_RNI_ID);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);
			context.putObjectToBeValidated(LIGHT_NAME, lightRequest.getFirstLight());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
					&& getLightValidationController().validate(context))
			{
				internalResponse = getSmartPointAccessorBCL().fetchLightByRniId(lightRequest);
				response.setLights(internalResponse.getResultsList());
				setLightProperties(response.getLights());
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#fetchPropertyValidValues(com.sensus.mlc.smartpoint.model
	 * .request.PropertyValidValuesRequest)
	 */
	@Override
	public PropertyValidValuesResponse fetchPropertyValidValues(PropertyValidValuesRequest propertyValidValuesRequest)
	{
		PropertyValidValuesResponse response = new PropertyValidValuesResponse();

		try
		{
			InternalResultsResponse<PropertyValidValue> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.FETCH_BY_PROPERTY_ID);
			context.putObjectToBeValidated(PropertyValidValuesRequest.class.getSimpleName(), propertyValidValuesRequest);
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.PROPERTIES.getValue(),
					propertyValidValuesRequest.getProperties());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
					&& getPropertiesValidationController().validate(context))
			{
				internalResponse = getSmartPointAccessorBCL().fetchPropertyValidValues(propertyValidValuesRequest);
				response.setPropertyValidValues(internalResponse.getResultsList());
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#fetchUpdateLightStatus(com.sensus.mlc.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public LightResponse fetchUpdateLightStatus(ProcessRequest processRequest)
	{
		LightResponse response = new LightResponse();

		try
		{
			InternalResultsResponse<Light> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(
					getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_UPDATE_LIGHT_STATUS);
			context.putObjectToBeValidated(ProcessRequest.class.getSimpleName(), processRequest);
			context.putObjectToBeValidated(Process.class.getSimpleName(), processRequest.getProcess());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
					&& getProcessValidationController().validate(context))
			{
				internalResponse = getSmartPointAccessorBCL().fetchUpdateLightStatus(processRequest);
				response.setLights(internalResponse.getResultsList());
				setLightProperties(response.getLights());
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
	 * @see com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#fetchAllCustomSearch(com.sensus.mlc.base.model.request.
	 * InquiryPaginationRequest)
	 */
	@Override
	public InquiryCustomSearchResponse fetchAllCustomSearch(InquiryPaginationRequest inquiryPaginationRequest)
	{
		InquiryCustomSearchResponse response = new InquiryCustomSearchResponse();

		try
		{
			InternalResultsResponse<CustomSearch> internalResponse = null;

			ValidationContext context = new ValidationContext(
					INQUIRY_LIGHT_REQUEST_NAME,
					inquiryPaginationRequest,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_ALL_CUSTOM_SEARCH);

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context))
			{
				checkDefaultsFetchAll(inquiryPaginationRequest);
				internalResponse = getSmartPointAccessorBCL().fetchAllCustomSearch(inquiryPaginationRequest);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#fetchStatusMessageByLightID(com.sensus.mlc.smartpoint.model
	 * .request.LightRequest)
	 */
	@Override
	public StatusMessageResponse fetchStatusMessageByLightID(LightRequest lightRequest)
	{
		StatusMessageResponse response = new StatusMessageResponse();

		try
		{
			InternalResultsResponse<StatusMessage> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(
					getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_STATUS_MESSAGE_BY_LIGHT);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);
			context.putObjectToBeValidated(LIGHT_NAME, lightRequest.getFirstLight());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
					&& getLightValidationController().validate(context))
			{
				internalResponse = getSmartPointAccessorBCL().fetchStatusMessageByLightID(lightRequest);
				response.setStatusMessage(internalResponse.getFirstResult());
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#fetchStatusMessageById(com.sensus.mlc.smartpoint.model.request
	 * .LightRequest)
	 */
	@Override
	public StatusMessageResponse fetchStatusMessageById(LightRequest lightRequest)
	{
		StatusMessageResponse response = new StatusMessageResponse();

		try
		{
			InternalResultsResponse<StatusMessage> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(
					getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_STATUS_MESSAGE_BY_ID);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);
			context.putObjectToBeValidated(StatusMessage.class.getSimpleName(), lightRequest.getFirstMessage());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
					&& getLightValidationController().validate(context))
			{
				internalResponse = getSmartPointAccessorBCL().fetchStatusMessageById(lightRequest);
				response.setStatusMessage(internalResponse.getFirstResult());
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
	 * @see com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#fetchLigthingConfigurationsByPartNumber(com.sensus.mlc.
	 * smartpoint.model.request.LightingConfigurationRequest)
	 */
	@Override
	public LightingConfigurationsResponse fetchLigthingConfigurationsByPartNumber(
			LightingConfigurationRequest lightingConfigurationRequest)
	{
		LightingConfigurationsResponse response = new LightingConfigurationsResponse();

		try
		{
			InternalResultsResponse<SensusPartNumberConfiguration> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(
					getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_LIGTHING_CONFIGURATIONS_BY_PART_NUMBER);
			context.putObjectToBeValidated(
					LightingConfigurationRequest.class.getSimpleName(),
					lightingConfigurationRequest);
			context.putObjectToBeValidated(LIGHT_NAME, lightingConfigurationRequest.getLight());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
					&& getLightValidationController().validate(context))
			{
				internalResponse = getSmartPointAccessorBCL()
						.fetchLigthingConfigurationsByPartNumber(lightingConfigurationRequest);
				response.setLightingConfigurations(internalResponse.getResultsList());
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#fetchLightHistory(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@Override
	public InquiryLightResponse fetchLightHistory(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();

		try
		{
			InternalResultsResponse<LightHistory> internalResponse = null;

			ValidationContext context = new ValidationContext(
					INQUIRY_LIGHT_REQUEST_NAME,
					inquiryLightRequest,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH);

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context))
			{
				internalResponse = getSmartPointAccessorBCL().fetchLightHistory(inquiryLightRequest);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#fetchLightHistoryHeader(com.sensus.mlc.smartpoint.model.
	 * request.LightRequest)
	 */
	@Override
	public LightResponse fetchLightHistoryHeader(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();

		try
		{
			InternalResultsResponse<HashMap<String, Integer>> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.FETCH_BY_LIGHT);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);
			context.putObjectToBeValidated(LIGHT_NAME, lightRequest.getFirstLight());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
					&& getLightValidationController().validate(context))
			{
				internalResponse = getSmartPointAccessorBCL().fetchLightHistoryHeader(lightRequest);
				response.setLightHistoryHeader(internalResponse.getFirstResult());
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#fetchAllColumnFilters(com.sensus.mlc.smartpoint.model.request
	 * .ColumnFilterRequest)
	 */
	@Override
	public ColumnFilterResponse fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		ColumnFilterResponse response = new ColumnFilterResponse();
		try
		{
			InternalResultsResponse<ColumnFilterResponse> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.FETCH_COLUMN_FILTERS);
			context.putObjectToBeValidated(ColumnFilterRequest.class.getSimpleName(), columnFilterRequest);
			context.putObjectToBeValidated(ListTypeEnum.class.getSimpleName(), columnFilterRequest.getListTypeEnum());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
					&& getCustomSearchValidationController().validate(context))
			{
				internalResponse = getSmartPointAccessorBCL().fetchAllColumnFilters(columnFilterRequest);
				if (!isNull(internalResponse.getFirstResult()))
				{
					response = internalResponse.getFirstResult();
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#fetchCurrentAlarmStatusMessagesByLight(com.sensus.mlc.smartpoint
	 * .model.request.LightRequest)
	 */
	@Override
	public CurrentAlarmWarningMessageResponse fetchCurrentAlarmStatusMessagesByLight(LightRequest lightRequest)
	{
		CurrentAlarmWarningMessageResponse response = new CurrentAlarmWarningMessageResponse();

		try
		{
			InternalResultsResponse<CurrentAlarmWarningMessage> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_STATUS_MESSAGE_BY_LIGHT);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);
			context.putObjectToBeValidated(LIGHT_NAME, lightRequest.getFirstLight());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
					&& getLightValidationController().validate(context))
			{
				internalResponse = getSmartPointAccessorBCL().fetchCurrentAlarmStatusMessagesByLight(
						lightRequest.getFirstLight().getId());
				response.setCurrentAlarmWarningMessages(internalResponse.getResultsList());
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#generateFileCSV(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InquiryLightResponse generateFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(INQUIRY_LIGHT_REQUEST_NAME, inquiryLightRequest);
			context.putObjectToBeValidated(
					ObjectToBeValidatedKeyEnum.CSV_FILE_NAME.getValue(),
					inquiryLightRequest.getFileName());
			context.putObjectToBeValidated(
					ObjectToBeValidatedKeyEnum.PROCESS_ID.getValue(),
					inquiryLightRequest.getProcessId());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
					&& getProcessValidationController().validate(context)
					&& getCsvFileValidationController().validate(context))
			{
				response = getSmartPointAccessorBCL().generateFileCSV(inquiryLightRequest);
			}

			handleOperationStatusAndMessages(response, null, context.getMessages(), true);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#generateLightHistoryFileCSV(com.sensus.mlc.smartpoint.model
	 * .request.InquiryLightRequest)
	 */
	@Override
	public InquiryLightResponse generateLightHistoryFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(INQUIRY_LIGHT_REQUEST_NAME, inquiryLightRequest);
			context.putObjectToBeValidated(
					ObjectToBeValidatedKeyEnum.CSV_FILE_NAME.getValue(),
					inquiryLightRequest.getFileName());
			context.putObjectToBeValidated(
					ObjectToBeValidatedKeyEnum.PROCESS_ID.getValue(),
					inquiryLightRequest.getProcessId());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
					&& getProcessValidationController().validate(context)
					&& getCsvFileValidationController().validate(context))
			{
				response = getSmartPointAccessorBCL().generateLightHistoryFileCSV(inquiryLightRequest);
			}

			handleOperationStatusAndMessages(response, null, context.getMessages(), true);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF#fetchSmartpointsToMap(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@Override
	public InquiryGeocodeSmartpointInfoResponse fetchSmartpointsToMap(InquiryLightRequest inquiryLightRequest)
	{
		InquiryGeocodeSmartpointInfoResponse response = new InquiryGeocodeSmartpointInfoResponse();

		try
		{
			InternalResultsResponse<GeocodeSmartpointInfo> internalResponse = null;

			ValidationContext context = new ValidationContext(
					INQUIRY_LIGHT_REQUEST_NAME,
					inquiryLightRequest,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_SMARTPOINT_MAP);

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context))
			{
				fetchDefaultsFetchAll(inquiryLightRequest);
				internalResponse = getSmartPointAccessorBCL().fetchSmartpointsToMap(inquiryLightRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Validate fetch all.
	 * 
	 * @param inquiryLightRequest the inquiry light request
	 */
	private void fetchDefaultsFetchAll(InquiryLightRequest inquiryLightRequest)
	{
		if (isNull(inquiryLightRequest.getMlcSortExpression()))
		{
			MLCSortExpression sortExpression = new MLCSortExpression();
			sortExpression.setField(LightOrderByEnum.STATUS.getValue());
			sortExpression.setIsProperty(false);
			inquiryLightRequest.setSortExpression(sortExpression);
		}
		if (isNull(inquiryLightRequest.getMlcSortExpression().getField()))
		{
			inquiryLightRequest.getMlcSortExpression().setField(
					LightOrderByEnum.STATUS.getValue());
		}
		if (isNull(inquiryLightRequest.getMlcSortExpression().isProperty()))
		{
			inquiryLightRequest.getMlcSortExpression().setIsProperty(false);
		}
	}

	/**
	 * Validate fetch all.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 */
	private void checkDefaultsFetchAll(InquiryPaginationRequest inquiryPaginationRequest)
	{
		if (!isNullOrEmpty(inquiryPaginationRequest.getSortExpressions()))
		{
			return;
		}

		SortExpression sortExpression = new SortExpression();
		sortExpression.setField(CustomSearchOrderByEnum.CUSTOM_SEARCH_NAME.getValue());
		sortExpression.setDirection(Direction.Ascending);
		inquiryPaginationRequest.addSortExpressions(sortExpression);
	}

}
