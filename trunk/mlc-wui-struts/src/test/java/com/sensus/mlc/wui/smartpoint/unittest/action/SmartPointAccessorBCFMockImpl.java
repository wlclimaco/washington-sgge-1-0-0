package com.sensus.mlc.wui.smartpoint.unittest.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.ResultsSetInfo;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.TimeZoneInfo;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightTypeEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.DataTypeEnum;
import com.sensus.mlc.smartpoint.model.Filter;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightHistory;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.LightStateEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.PropertyValidValue;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.SensusPartNumberConfiguration;
import com.sensus.mlc.smartpoint.model.SmartPointColumnEnum;
import com.sensus.mlc.smartpoint.model.SmartPointFilterEnum;
import com.sensus.mlc.smartpoint.model.SmartPointTypeEnum;
import com.sensus.mlc.smartpoint.model.StatusException;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusRequest;
import com.sensus.mlc.smartpoint.model.request.LightingConfigurationRequest;
import com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.CountLightResponse;
import com.sensus.mlc.smartpoint.model.response.CurrentAlarmWarningMessageResponse;
import com.sensus.mlc.smartpoint.model.response.CustomSearchResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryCustomSearchResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;
import com.sensus.mlc.smartpoint.model.response.LightStatusResponse;
import com.sensus.mlc.smartpoint.model.response.LightingConfigurationsResponse;
import com.sensus.mlc.smartpoint.model.response.PropertyValidValuesResponse;
import com.sensus.mlc.smartpoint.model.response.StatusMessageResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;

public class SmartPointAccessorBCFMockImpl extends BaseMockImpl implements ISmartPointAccessorBCF
{

	/** The Constant SMARTPOINT_COUNT. */
	public static final Integer SMARTPOINT_COUNT = 100;

	/** The PAG e_ size. */
	private Integer pageSize = 100;

	/** The Constant SMARTPOINT_NAME. */
	public static final String SMARTPOINT_NAME = "SmartPoint %d";

	/** The Constant PROPERTY_NAME. */
	public static final String PROPERTY_NAME = "Property %d";

	/** The Constant PROPERTY_COUNT. */
	public static final Integer PROPERTY_COUNT = 20;

	/** The Constant GROUP_NAME. */
	public static final String CUSTOM_SEARCH = "Search %d";

	/** The Constant CITY_MOCK. */
	public static final String CITY_MOCK = "City !MOCK!";

	/** The Constant FILE_NAME. */
	public static final String FILE_NAME = "File Name";

	@Override
	public InquiryLightResponse fetchAllLights(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();

		if (inquiryLightRequest.getPageSize() > 0)
		{
			setPageSize(inquiryLightRequest.getPageSize());
		}

		Integer startRow = inquiryLightRequest.getStartRow();

		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setLights(new ArrayList<Light>());
			for (int i = startRow; i < (startRow + getPageSize()); i++)
			{
				Light light = new Light();

				OffsetSchedule offsetSchedule = new OffsetSchedule();
				offsetSchedule.setId(0);
				offsetSchedule.setName("Offset Schedule");
				offsetSchedule.setDescription("Schedule 15");

				EventSchedule eventSchedule = new EventSchedule();
				eventSchedule.setId(1);
				eventSchedule.setName("Event Schedule");
				eventSchedule.setDescription("Event Schedule");

				light.setEventSchedule(eventSchedule);
				light.setId((i + 1000));
				light.setRniId((i + 3000));
				light.setLightStateEnum(LightStateEnum.ON);
				light.setProtect(false);

				if ((i % 2) == 0)
				{
					light.setProtect(false);
				}
				else
				{
					light.setProtect(true);
				}

				light.setOffSetSchedule(offsetSchedule);
				light.setParameters(populateParam(i));
				light.setProtect(true);

				StatusMessage statusMessage = new StatusMessage();
				statusMessage.setCreateDate(new Date());
				statusMessage.setDate(new Date());
				statusMessage.setLightStatusEnum(LightStatusEnum.ACTIVE);

				if (i == 0)
				{
					statusMessage.setLightStatusEnum(null);

					StatusException statusException = new StatusException();
					statusException.setStatusExceptionTypeEnum(StatusExceptionTypeEnum.POWER_FAILURE);

					List<StatusException> listStatusException = new ArrayList<StatusException>();
					listStatusException.add(statusException);

					statusMessage.setStatusExceptions(listStatusException);
				}

				if (i == 1)
				{
					statusMessage.setLightStatusEnum(null);

					StatusException statusException = new StatusException();
					statusException.setStatusExceptionTypeEnum(StatusExceptionTypeEnum.BROWN_OUT_DETECTED);

					List<StatusException> listStatusException = new ArrayList<StatusException>();
					listStatusException.add(statusException);

					statusMessage.setStatusExceptions(listStatusException);
				}

				if (i == 2)
				{
					statusMessage.setLightStatusEnum(null);

					StatusException statusException = new StatusException();
					statusException.setStatusExceptionTypeEnum(StatusExceptionTypeEnum.COMMUNICATION_FAIL);

					List<StatusException> listStatusException = new ArrayList<StatusException>();
					listStatusException.add(statusException);

					statusMessage.setStatusExceptions(listStatusException);
				}

				if (i == 3)
				{
					statusMessage.setLightStatusEnum(LightStatusEnum.DEACTIVATED);
				}

				light.setCurrentStatusMessage(statusMessage);

				response.getLights().add(light);

			}
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			ResultsSetInfo resultSetInfo = new ResultsSetInfo();
			resultSetInfo.setTotalRowsAvailable(SMARTPOINT_COUNT);

			response.setResultsSetInfo(resultSetInfo);

			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public CountLightResponse countLights(LightingControlRequest request)
	{
		CountLightResponse response = new CountLightResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			// response.setAlertLightCount(SMARTPOINT_COUNT);
			response.setDeviceLightCount(SMARTPOINT_COUNT);

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		// Not accounting for this right now
		throw new RuntimeException("Kaboom");
	}

	@Override
	public LightResponse fetchLightById(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setLights(new ArrayList<Light>());

			List<SensusPartNumberConfiguration> lightIntensitylevels = new ArrayList<SensusPartNumberConfiguration>();

			SensusPartNumberConfiguration sensusPartNumberConfiguration = new SensusPartNumberConfiguration();
			sensusPartNumberConfiguration.setPercentage(20);
			lightIntensitylevels.add(sensusPartNumberConfiguration);

			sensusPartNumberConfiguration = new SensusPartNumberConfiguration();
			sensusPartNumberConfiguration.setPercentage(40);
			lightIntensitylevels.add(sensusPartNumberConfiguration);

			sensusPartNumberConfiguration = new SensusPartNumberConfiguration();
			sensusPartNumberConfiguration.setPercentage(55);
			lightIntensitylevels.add(sensusPartNumberConfiguration);

			sensusPartNumberConfiguration = new SensusPartNumberConfiguration();
			sensusPartNumberConfiguration.setPercentage(75);
			lightIntensitylevels.add(sensusPartNumberConfiguration);

			sensusPartNumberConfiguration = new SensusPartNumberConfiguration();
			sensusPartNumberConfiguration.setPercentage(100);
			lightIntensitylevels.add(sensusPartNumberConfiguration);

			Light light = new Light();
			light.setRniId(1);
			light.setId(1);
			light.setSmartPointId(1);
			light.setProtect(false);
			light.setLightIntensityEnum(LightIntensityEnum.LEVEL_5);
	//		light.setEcoModeBaseline(new EcoModeBaseline(light, getLightTypeRandom(), getWattageRandom()));
			light.setSmartPointTypeEnum(SmartPointTypeEnum.LIGHT);
			light.setTimeZoneInfo(new TimeZoneInfo("brst"));
			light.setLightIntensitylevels(lightIntensitylevels);

			List<CurrentAlarmWarningMessage> messages = new ArrayList<CurrentAlarmWarningMessage>();

			CurrentAlarmWarningMessage message = new CurrentAlarmWarningMessage();
			message.setMessageDate(new Date());
			message.setStatusMessage(LightStatusEnum.ALARM);
			message.setStatusMessageSubtype(StatusExceptionTypeEnum.POWER_FAILURE);

			messages.add(message);

			message = new CurrentAlarmWarningMessage();
			message.setMessageDate(new Date());
			message.setStatusMessage(LightStatusEnum.WARNING);
			message.setStatusMessageSubtype(StatusExceptionTypeEnum.BROWN_OUT_DETECTED);

			messages.add(message);

			light.setCurrentAlarmWarningMessageList(messages);

			List<Light> listLight = new ArrayList<Light>();
			listLight.add(light);

			OffsetSchedule offsetSchedule = new OffsetSchedule();

			offsetSchedule.setId(0);
			offsetSchedule.setName("OffsetSchedule");
			offsetSchedule.setDescription("Offset Schedule");
			offsetSchedule.setLights(listLight);
			light.setOffSetSchedule(offsetSchedule);

			EventSchedule eventSchedule = new EventSchedule();

			eventSchedule.setId(1);
			eventSchedule.setName("EventSchedule");
			eventSchedule.setDescription("Event Schedule");
			eventSchedule.setLights(listLight);
			light.setEventSchedule(eventSchedule);

			light.setParameters(populateParam(1));

			Calendar c = Calendar.getInstance();

			// Current Status Message
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setDate(c.getTime());
			statusMessage.setLightStatusEnum(LightStatusEnum.ALARM);

			StatusException statusException = new StatusException();
			statusException.setId(2);
			statusException.setLabelKey("FlexNet ID");
			statusException.setStatusExceptionTypeEnum(StatusExceptionTypeEnum.POWER_SURGE_DETECTED);

			List<StatusException> statusExceptions = new ArrayList<StatusException>();
			statusExceptions.add(statusException);

			statusMessage.setStatusExceptions(statusExceptions);
			light.setCurrentStatusMessage(statusMessage);

			// List History Alarms
			statusMessage = new StatusMessage();
			statusMessage.setDate(c.getTime());
			statusMessage.setLightStatusEnum(LightStatusEnum.ALARM);

			statusException = new StatusException();
			statusException.setId(1);
			statusException.setLabelKey("Alarm");
			statusException.setStatusExceptionTypeEnum(StatusExceptionTypeEnum.LAMP_FAILURE);
			statusExceptions = new ArrayList<StatusException>();
			statusExceptions.add(statusException);
			statusMessage.setStatusExceptions(statusExceptions);

			light.setStatusMessages(new ArrayList<StatusMessage>());
			light.getStatusMessages().add(statusMessage);

			// Set new Status Message
			statusMessage = new StatusMessage();
			statusMessage.setDate(c.getTime());
			statusMessage.setLightStatusEnum(LightStatusEnum.WARNING);

			statusException = new StatusException();
			statusException.setId(2);
			statusException.setLabelKey("FlexNet ID Test");
			statusException.setStatusExceptionTypeEnum(StatusExceptionTypeEnum.POWER_SURGE_DETECTED);
			statusExceptions = new ArrayList<StatusException>();
			statusExceptions.add(statusException);
			statusMessage.setStatusExceptions(statusExceptions);

			light.getStatusMessages().add(statusMessage);

			// Set new Status Message
			c.add(Calendar.DATE, -1);
			statusMessage = new StatusMessage();
			statusMessage.setDate(c.getTime());
			statusMessage.setLightStatusEnum(LightStatusEnum.MAINTENANCE);

			light.getStatusMessages().add(statusMessage);

			// Set new Status Message
			c.add(Calendar.DATE, -1);
			statusMessage = new StatusMessage();
			statusMessage.setDate(c.getTime());
			statusMessage.setLightStatusEnum(LightStatusEnum.ALARM);

			statusException = new StatusException();
			statusException.setId(1);
			statusException.setLabelKey("FlexNet ID Test");
			statusException.setStatusExceptionTypeEnumValue(2);
			statusExceptions = new ArrayList<StatusException>();
			statusExceptions.add(statusException);
			statusMessage.setStatusExceptions(statusExceptions);

			light.getStatusMessages().add(statusMessage);

			statusException = new StatusException();
			statusException.setId(3);
			statusException.setLabelKey("FlexNet ID Test");
			statusException.setStatusExceptionTypeEnumValue(3);
			statusExceptions = new ArrayList<StatusException>();
			statusExceptions.add(statusException);
			statusMessage.setStatusExceptions(statusExceptions);

			light.getStatusMessages().add(statusMessage);

			// Set new Status Message
			statusMessage = new StatusMessage();
			statusMessage.setDate(c.getTime());
			statusMessage.setLightStatusEnum(LightStatusEnum.MAINTENANCE);

			light.getStatusMessages().add(statusMessage);

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			response.getLights().add(light);

			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public LightResponse fetchLightIdByRniId(LightRequest lightRequest)
	{
		return null;
	}

	@Override
	public PropertyValidValuesResponse fetchPropertyValidValues(PropertyValidValuesRequest request)
	{
		PropertyValidValuesResponse response = new PropertyValidValuesResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setPropertyValidValues(new ArrayList<PropertyValidValue>());

			if (request.getProperties().get(0).equals(PropertyEnum.LAMP_TYPE))
			{
				response.getPropertyValidValues().add(new PropertyValidValue(1, "Induction"));
				response.getPropertyValidValues().add(new PropertyValidValue(2, "LED"));
			}
			else if (request.getProperties().get(0).equals(PropertyEnum.WATTAGE_RATING))
			{
				response.getPropertyValidValues().add(new PropertyValidValue(3, "40W"));
				response.getPropertyValidValues().add(new PropertyValidValue(4, "80W"));
				response.getPropertyValidValues().add(new PropertyValidValue(5, "120W"));
				response.getPropertyValidValues().add(new PropertyValidValue(6, "200W"));
				response.getPropertyValidValues().add(new PropertyValidValue(7, "300W"));
				response.getPropertyValidValues().add(new PropertyValidValue(8, "28W"));
				response.getPropertyValidValues().add(new PropertyValidValue(9, "56W"));
				response.getPropertyValidValues().add(new PropertyValidValue(10, "84W"));
				response.getPropertyValidValues().add(new PropertyValidValue(11, "112W"));
				response.getPropertyValidValues().add(new PropertyValidValue(12, "140W"));
				response.getPropertyValidValues().add(new PropertyValidValue(13, "168W"));
				response.getPropertyValidValues().add(new PropertyValidValue(14, "196W"));
				response.getPropertyValidValues().add(new PropertyValidValue(15, "224W"));
				response.getPropertyValidValues().add(new PropertyValidValue(16, "252W"));
			}
			else if (request.getProperties().get(0).equals(PropertyEnum.LAMP_TYPE_WATTAGE_DIMMABLE))
			{
				response.getPropertyValidValues().add(new PropertyValidValue(17, "40W Induction"));
				response.getPropertyValidValues().add(new PropertyValidValue(18, "80W Induction"));
				response.getPropertyValidValues().add(new PropertyValidValue(19, "120W Induction"));
				response.getPropertyValidValues().add(new PropertyValidValue(20, "200W Induction"));
				response.getPropertyValidValues().add(new PropertyValidValue(21, "300W Induction"));
				response.getPropertyValidValues().add(new PropertyValidValue(22, "28W LED"));
				response.getPropertyValidValues().add(new PropertyValidValue(23, "56W LED"));
				response.getPropertyValidValues().add(new PropertyValidValue(24, "84W LED"));
				response.getPropertyValidValues().add(new PropertyValidValue(25, "112W LED"));
				response.getPropertyValidValues().add(new PropertyValidValue(26, "140W LED"));
				response.getPropertyValidValues().add(new PropertyValidValue(27, "168W LED"));
				response.getPropertyValidValues().add(new PropertyValidValue(28, "196W LED"));
				response.getPropertyValidValues().add(new PropertyValidValue(29, "224W LED"));
				response.getPropertyValidValues().add(new PropertyValidValue(30, "252W LED"));
			}
			else
			{
				for (int i = 1; i <= PROPERTY_COUNT; i++)
				{
					PropertyValidValue propertyValidValue = new PropertyValidValue();
					propertyValidValue.setId(i);
					propertyValidValue.setValue(String.format(PROPERTY_NAME, i));
					response.getPropertyValidValues().add(propertyValidValue);
				}
			}
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public LightResponse fetchUpdateLightStatus(ProcessRequest processRequest)
	{
		// Takes care of failure scenario
		LightResponse response = new LightResponse();
		if (processRequest.getProcess().getId() <= 0)
		{
			response.setOperationSuccess(false);
			response.addFieldErrorMessage("error");
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			List<Light> lightList = new ArrayList<Light>();
			Light light = new Light();

			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setId(1);
			statusMessage.setDate(new Date());
			statusMessage.setLightStatusEnum(LightStatusEnum.WARNING);
			light.setCurrentStatusMessage(statusMessage);
			light.setLightStateEnum(LightStateEnum.ON);
			light.setParameters(populateParam(1));

			lightList.add(light);

			response.setLights(lightList);

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public LightStatusResponse fetchLightConsumption(LightStatusRequest lightStatusRequest)
	{
		LightStatusResponse response = new LightStatusResponse();
		if (ValidationUtil.isNull(lightStatusRequest.getInitialDate())
				|| ValidationUtil.isNull(lightStatusRequest.getEndDate())
				|| ValidationUtil.isNull(lightStatusRequest.getLightId()))
		{
			response.setOperationSuccess(false);
			response.addFieldErrorMessage("error");
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setResult(new Float(156.8904));
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public LightStatusResponse fetchAverageLightVoltage(LightStatusRequest lightStatusRequest)
	{
		LightStatusResponse response = new LightStatusResponse();
		if (ValidationUtil.isNull(lightStatusRequest.getInitialDate())
				|| ValidationUtil.isNull(lightStatusRequest.getEndDate())
				|| ValidationUtil.isNull(lightStatusRequest.getLightId()))
		{
			response.setOperationSuccess(false);
			response.addFieldErrorMessage("error");
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setResult(new Float(786.5104));
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public LightStatusResponse fetchAverageLightConsumption(LightStatusRequest lightStatusRequest)
	{
		LightStatusResponse response = new LightStatusResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{

			response.setResult(new Float(15.5));
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public LightStatusResponse fetchAverageLightCurrent(LightStatusRequest lightStatusRequest)
	{
		LightStatusResponse response = new LightStatusResponse();
		if (ValidationUtil.isNull(lightStatusRequest.getInitialDate())
				|| ValidationUtil.isNull(lightStatusRequest.getEndDate())
				|| ValidationUtil.isNull(lightStatusRequest.getLightId()))
		{
			response.setOperationSuccess(false);
			response.addFieldErrorMessage("error");
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setResult(new Float(160.99999));
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public InquiryCustomSearchResponse fetchAllCustomSearch(InquiryPaginationRequest inquiryPaginationRequest)
	{
		InquiryCustomSearchResponse response = new InquiryCustomSearchResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			List<CustomSearch> customSearches = new ArrayList<CustomSearch>();

			for (int i = 0; i < 5; i++)
			{

				List<Column> listColumns = new ArrayList<Column>();

				List<SmartPointFilterEnum> listFilters = new ArrayList<SmartPointFilterEnum>();

				listFilters.add(SmartPointFilterEnum.valueOf("GROUPS"));
				listFilters.add(SmartPointFilterEnum.STATUS);
				listFilters.add(SmartPointFilterEnum.ADDRESS);

				Column column = new Column();
				column.setColumnEnum(SmartPointColumnEnum.POLE_ID);
				column.setFieldName("POLE_ID");
				listColumns.add(column);

				column = new Column();
				column.setColumnEnum(SmartPointColumnEnum.RNI_ID);
				column.setFieldName("FLEXNET_ID");
				listColumns.add(column);

				column = new Column();
				column.setColumnEnum(SmartPointColumnEnum.LAMP_TYPE);
				column.setFieldName("LIGHT_TYPE");
				listColumns.add(column);

				column = new Column();
				column.setColumnEnum(SmartPointColumnEnum.DATE_ADDED);
				column.setFieldName("DATE_ADDED");
				listColumns.add(column);

				CustomSearch customSearch = new CustomSearch();
				customSearch.setId(i + 10);
				customSearch.setName(String.format("Saved Search %d", i + 10));
				customSearch.setDescription("Description " + (i + 10));
				customSearch.setCreateDate(new Date());
				customSearch.setListColumn(listColumns);
				// customSearch.setListFilters(listFilters);

				List<SearchParameter> searchParameters = new ArrayList<SearchParameter>();

				SearchParameter searchParameter = new SearchParameter();
				searchParameter.setPropertyEnum(PropertyEnum.ORDER_BY);
				searchParameter.setValue("LIGHT_TYPE Ascending");

				searchParameters.add(searchParameter);

				searchParameter = new SearchParameter();
				searchParameter.setPropertyEnum(PropertyEnum.POLE_ID);
				searchParameter.setValue("1");

				searchParameters.add(searchParameter);

				searchParameter = new SearchParameter();
				searchParameter.setPropertyEnum(PropertyEnum.GROUP_ID);
				searchParameter.setName("Group Name 01");
				searchParameter.setValue("1");

				searchParameters.add(searchParameter);

				searchParameter = new SearchParameter();
				searchParameter.setPropertyEnum(PropertyEnum.GROUP_ID);
				searchParameter.setName("Group Name 02");
				searchParameter.setValue("2");

				searchParameters.add(searchParameter);

				searchParameter = new SearchParameter();
				searchParameter.setPropertyEnum(PropertyEnum.TAG_ID);
				searchParameter.setName("Tag Name 02");
				searchParameter.setValue("2");

				searchParameters.add(searchParameter);

				searchParameter.setPropertyEnum(PropertyEnum.STATUS_MESSAGE_ID);
				searchParameter.setName("Alert");
				searchParameter.setValue("2");

				searchParameters.add(searchParameter);

				searchParameter = new SearchParameter();
				searchParameter.setPropertyEnum(PropertyEnum.STATUS_MESSAGE_ID);
				searchParameter.setName("Warning");
				searchParameter.setValue("2");

				searchParameters.add(searchParameter);

				customSearch.setSearchParameters(searchParameters);

				customSearches.add(customSearch);

			}

			ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
			resultsSetInfo.setTotalRowsAvailable(15);

			response.setCustomSearches(customSearches);
			response.setOperationSuccess(true);
			response.setResultsSetInfo(resultsSetInfo);

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public StatusMessageResponse fetchStatusMessageByLightID(LightRequest request)
	{
		return null;
	}

	@Override
	public LightingConfigurationsResponse fetchLigthingConfigurationsByPartNumber(LightingConfigurationRequest request)
	{
		return null;
	}

	@Override
	public InquiryLightResponse fetchLightHistory(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();

		if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);

			return response;
		}

		if (MODE_SUCCESS.equals(getMode()))
		{
			List<LightHistory> ligthsHistory = new ArrayList<LightHistory>();
			LightHistory lightHistory;
			for (int i = 0; i <= inquiryLightRequest.getPageSize(); i++)
			{
				lightHistory = new LightHistory();
				if ((i % 10) == 0)
				{
					lightHistory.setProcessId(i);
					lightHistory.setName("Add Smartpoint to Group");
					lightHistory.setDescription("Group 'PDX Airport'");
				}
				else if ((i % 2) == 0)
				{
					lightHistory.setName("Alarm");
					lightHistory.setDescription("Alert Description goes in this space here and can run long.");
				}
				else
				{
					lightHistory.setName("Warning");
					lightHistory.setDescription("Alert Description goes in this space here and can run long.");
				}
				lightHistory.setCreateUser("rod");
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, -i);
				lightHistory.setCreateDate(calendar.getTime());
				ligthsHistory.add(lightHistory);
			}
			for (int i = 0; i <= inquiryLightRequest.getPageSize(); i++)
			{
				lightHistory = new LightHistory();
				if ((i % 10) == 0)
				{
					lightHistory.setName("Add Smartpoint to Tat");
					lightHistory.setDescription("QAT Tag");
				}
				else if ((i % 2) == 0)
				{
					lightHistory.setName("Alarm");
					lightHistory.setDescription("Alert Description goes in this space here and can run long.");
				}
				else
				{
					lightHistory.setName("Warning");
					lightHistory.setDescription("Alert Description goes in this space here and can run long.");
				}
				lightHistory.setCreateUser("rod");
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, -i);
				lightHistory.setCreateDate(calendar.getTime());
				lightHistory.setProcessId(i);
				ligthsHistory.add(lightHistory);
			}

			response.setLightHistory(ligthsHistory);
			response.setOperationSuccess(true);

			return response;
		}

		throw new RuntimeException("Kaboom");
	}

	@Override
	public LightResponse fetchLightHistoryHeader(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("1", 1);
			map.put("2", 2);
			response.setLightHistoryHeader(map);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public ColumnFilterResponse fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		ColumnFilterResponse colFilResp = new ColumnFilterResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{
			// BEGIN ..:COLUMNS:.. DEFINITION
			List<Column> columns = new ArrayList<Column>();

			columns.add(setColumn(SmartPointColumnEnum.POLE_ID, "Pole ID"));
			columns.add(setColumn(SmartPointColumnEnum.RNI_ID, "FlexNet ID"));
			columns.add(setColumn(SmartPointColumnEnum.LAMP_TYPE, "Light Type"));
			columns.add(setColumn(SmartPointColumnEnum.DATE_ADDED, "Date Added"));
			columns.add(setColumn(SmartPointColumnEnum.CITY_NAME, "City"));
			columns.add(setColumn(SmartPointColumnEnum.MAP_IT, "Map It"));
			columns.add(setColumn(SmartPointColumnEnum.PROTECTED, "Protected"));
			columns.add(setColumn(SmartPointColumnEnum.ECOMODE, "Eco-Mode"));
			columns.add(setColumn(SmartPointColumnEnum.STATUS, "Status"));

			colFilResp.setListColumn(columns);

			List<Column> allColumns = new ArrayList<Column>();

			allColumns.add(setColumn(SmartPointColumnEnum.FIRMWARE_VERSION, "Firmware Version"));
			allColumns.add(setColumn(SmartPointColumnEnum.MODEL_NUMBER, "Model Number"));
			allColumns.add(setColumn(SmartPointColumnEnum.COLOR_TEMPERATURE, "Color Temperature"));
			allColumns.add(setColumn(SmartPointColumnEnum.HOUSING_COLOR, "Housing Color"));
			allColumns.add(setColumn(SmartPointColumnEnum.INPUT_VOLTAGE_RANGE, "Voltage Range"));
			allColumns.add(setColumn(SmartPointColumnEnum.BULB_SERIAL_NUMBER, "Bulb Serial Number"));
			allColumns.add(setColumn(SmartPointColumnEnum.SERIAL_NUMBER, "Light Driver Serial Number"));
			allColumns
					.add(setColumn(SmartPointColumnEnum.LOWER_ASSEMBLY_SERIAL_NUMBER, "Lower Assembly Serial Number"));
			allColumns
					.add(setColumn(SmartPointColumnEnum.UPPER_ASSEMBLY_SERIAL_NUMBER, "Upper Assembly Serial Number"));

			colFilResp.setAdditionalColumns(allColumns);
			// END COLUMNS DEFINITION

			// BEGIN ..:FILTER:.. DEFINITION
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(setFilter(SmartPointFilterEnum.GROUPS));
			filters.add(setFilter(SmartPointFilterEnum.STATUS));
			filters.add(setFilter(SmartPointFilterEnum.ALARM_TYPE));
			filters.add(setFilter(SmartPointFilterEnum.WARNING_TYPE));
			filters.add(setFilter(SmartPointFilterEnum.LIGHT_TYPES));
			filters.add(setFilter(SmartPointFilterEnum.EVENT_SCHEDULE));
			filters.add(setFilter(SmartPointFilterEnum.OFFSET_SCHEDULE));
			filters.add(setFilter(SmartPointFilterEnum.TAGS));
			filters.add(setFilter(SmartPointFilterEnum.ADDRESS));
			filters.add(setFilter(SmartPointFilterEnum.CONFIGURATION));
			filters.add(setFilter(SmartPointFilterEnum.ECOMODE));

			colFilResp.setFilters(filters);

			List<Filter> additionalFilters = new ArrayList<Filter>();

			additionalFilters.add(setFilter(SmartPointFilterEnum.FIRMWARE_VERSION));
			additionalFilters.add(setFilter(SmartPointFilterEnum.DATE_ADDED));
			additionalFilters.add(setFilter(SmartPointFilterEnum.MODEL_NUMBER));
			additionalFilters.add(setFilter(SmartPointFilterEnum.COLOR_TEMPERATURE));
			additionalFilters.add(setFilter(SmartPointFilterEnum.HOUSING_COLOR));
			additionalFilters.add(setFilter(SmartPointFilterEnum.VOLTAGE_RANGE));
			additionalFilters.add(setFilter(SmartPointFilterEnum.BULB_SERIAL_NUMBER));
			additionalFilters.add(setFilter(SmartPointFilterEnum.LIGHT_DRIVER_SERIAL_NUMBER));
			additionalFilters.add(setFilter(SmartPointFilterEnum.LOWER_ASSEMBLY_SERIAL_NUMBER));
			additionalFilters.add(setFilter(SmartPointFilterEnum.UPPER_ASSEMBLY_SERIAL_NUMBER));

			colFilResp.setAdditionalFilters(additionalFilters);
			// END ..:FILTER:.. DEFINITION

			return colFilResp;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			colFilResp.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			return colFilResp;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			colFilResp.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			colFilResp.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			colFilResp.setOperationSuccess(false);

			return colFilResp;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public CustomSearchResponse fetchCustomSearchById(CustomSearchRequest customSearchRequest)
	{
		return null;
	}

	@Override
	public CurrentAlarmWarningMessageResponse fetchCurrentAlarmStatusMessagesByLight(LightRequest lightRequest)
	{
		CurrentAlarmWarningMessageResponse resp = new CurrentAlarmWarningMessageResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{
			// BEGIN - CREATE MESSAGES
			List<CurrentAlarmWarningMessage> listAlarmsWarnings = new ArrayList<CurrentAlarmWarningMessage>();

			CurrentAlarmWarningMessage alarmWarningMessage = new CurrentAlarmWarningMessage();
			alarmWarningMessage.setStatusMessage(LightStatusEnum.WARNING);
			alarmWarningMessage.setMessageDate(new Date());

			// ADDING
			listAlarmsWarnings.add(alarmWarningMessage);

			alarmWarningMessage = new CurrentAlarmWarningMessage();
			alarmWarningMessage.setStatusMessage(LightStatusEnum.ALARM);
			alarmWarningMessage.setMessageDate(new Date());

			// ADDING
			listAlarmsWarnings.add(alarmWarningMessage);

			// END - CREATE MESSAGES

			resp.setCurrentAlarmWarningMessages(listAlarmsWarnings);
			resp.setOperationSuccess(true);
			resp.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			return resp;
		}

		if (MODE_FAILURE.equals(getMode()))
		{

			resp.setOperationSuccess(false);
			resp.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return resp;
		}

		throw new RuntimeException("Kaboom");
	}

	@Override
	public InquiryLightResponse generateFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{

			response.setFileName(FILE_NAME);
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	/**
	 * Sets the page size.
	 * 
	 * @param pageSize the new page size
	 */
	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * Gets the page size.
	 * 
	 * @return the page size
	 */
	public Integer getPageSize()
	{
		return pageSize;
	}

	/**
	 * Populate param.
	 * 
	 * @param i the i
	 * @return the list
	 */
	private List<LightParameter> populateParam(int i)
	{

		LightParameter latitude = new LightParameter();
		latitude.setValue("41.25816656");
		latitude.setDataTypeEnum(DataTypeEnum.DOUBLE);
		latitude.setPropertyEnum(PropertyEnum.LATITUDE);
		latitude.setLabelKey("");

		LightParameter longitude = new LightParameter();
		longitude.setValue("-95.93688238");
		longitude.setDataTypeEnum(DataTypeEnum.DOUBLE);
		longitude.setPropertyEnum(PropertyEnum.LONGITUDE);
		longitude.setLabelKey("");

		LightParameter firmware = new LightParameter();
		firmware.setValue(String.valueOf(2605 + (i * 3)));
		firmware.setDataTypeEnum(DataTypeEnum.INTEGER);
		firmware.setPropertyEnum(PropertyEnum.FIRMWARE_VERSION);
		firmware.setLabelKey("");

		LightParameter poleId = new LightParameter();
		poleId.setValue(String.valueOf(i));
		poleId.setDataTypeEnum(DataTypeEnum.STRING);
		poleId.setPropertyEnum(PropertyEnum.POLE_ID);
		poleId.setLabelKey("");

		LightParameter serial = new LightParameter();
		serial.setValue(String.valueOf(14587 + i));
		serial.setDataTypeEnum(DataTypeEnum.INTEGER);
		serial.setPropertyEnum(PropertyEnum.LIGHT_DRIVER_SERIAL_NUMBER);
		serial.setLabelKey("");

		LightParameter sensusPartNumber = new LightParameter();
		sensusPartNumber.setValue(String.valueOf(14700 + i));
		sensusPartNumber.setDataTypeEnum(DataTypeEnum.INTEGER);
		sensusPartNumber.setPropertyEnum(PropertyEnum.SENSUS_PART_NUMBER);
		sensusPartNumber.setLabelKey("");

		LightParameter lampType = new LightParameter();
		lampType.setValue("lampType" + i);
		lampType.setDataTypeEnum(DataTypeEnum.STRING);
		lampType.setPropertyEnum(PropertyEnum.LAMP_TYPE);
		lampType.setLabelKey("");

		LightParameter wattage = new LightParameter();
		wattage.setValue("w" + i + i);
		wattage.setDataTypeEnum(DataTypeEnum.STRING);
		wattage.setPropertyEnum(PropertyEnum.WATTAGE_RATING);
		wattage.setLabelKey("");

		LightParameter inputVoltageRange = new LightParameter();
		inputVoltageRange.setValue("ivr" + i);
		inputVoltageRange.setDataTypeEnum(DataTypeEnum.STRING);
		inputVoltageRange.setPropertyEnum(PropertyEnum.INPUT_VOLTAGE_RANGE);
		inputVoltageRange.setLabelKey("");

		LightParameter manufacturer = new LightParameter();
		manufacturer.setValue("manufacturer " + i);
		manufacturer.setDataTypeEnum(DataTypeEnum.STRING);
		manufacturer.setPropertyEnum(PropertyEnum.MANUFACTURER);
		manufacturer.setLabelKey("");

		LightParameter colorTemperature = new LightParameter();
		colorTemperature.setValue("7500");
		colorTemperature.setDataTypeEnum(DataTypeEnum.STRING);
		colorTemperature.setPropertyEnum(PropertyEnum.COLOR_TEMPERATURE);
		colorTemperature.setLabelKey("");

		LightParameter dateAdded = new LightParameter();
		dateAdded.setValue("10/15/2010");
		dateAdded.setDataTypeEnum(DataTypeEnum.DATE);
		dateAdded.setPropertyEnum(PropertyEnum.DATE_ADDED);
		dateAdded.setLabelKey("");

		LightParameter dateInstalled = new LightParameter();
		dateInstalled.setValue("10/15/2010");
		dateInstalled.setDataTypeEnum(DataTypeEnum.DATE);
		dateInstalled.setPropertyEnum(PropertyEnum.DATE_INSTALLED);
		dateInstalled.setLabelKey("");

		LightParameter sunriseTime = new LightParameter();
		sunriseTime.setValue("10/15/2010");
		sunriseTime.setDataTypeEnum(DataTypeEnum.DATE);
		sunriseTime.setPropertyEnum(PropertyEnum.SUNRISE_TIME);
		sunriseTime.setLabelKey("");

		LightParameter sunriseOffset = new LightParameter();
		sunriseOffset.setValue("-10");
		sunriseOffset.setDataTypeEnum(DataTypeEnum.STRING);
		sunriseOffset.setPropertyEnum(PropertyEnum.SUNRISE_OFFSET);
		sunriseOffset.setLabelKey("");

		LightParameter sunsetOffset = new LightParameter();
		sunsetOffset.setValue("-15");
		sunsetOffset.setDataTypeEnum(DataTypeEnum.STRING);
		sunsetOffset.setPropertyEnum(PropertyEnum.SUNSET_OFFSET);
		sunsetOffset.setLabelKey("");

		LightParameter sunsetTime = new LightParameter();
		sunsetTime.setValue("10/15/2010");
		sunsetTime.setDataTypeEnum(DataTypeEnum.DATE);
		sunsetTime.setPropertyEnum(PropertyEnum.SUNSET_TIME);
		sunsetTime.setLabelKey("");

		LightParameter city = new LightParameter();
		city.setValue("Beaverton");
		city.setDataTypeEnum(DataTypeEnum.STRING);
		city.setPropertyEnum(PropertyEnum.CITY_NAME);
		city.setLabelKey("");

		LightParameter modelNumber = new LightParameter();
		modelNumber.setValue("5393490123131");
		modelNumber.setDataTypeEnum(DataTypeEnum.STRING);
		modelNumber.setPropertyEnum(PropertyEnum.MODEL_NUMBER);
		modelNumber.setLabelKey("");

		LightParameter housingColor = new LightParameter();
		housingColor.setValue("Housing Color");
		housingColor.setDataTypeEnum(DataTypeEnum.STRING);
		housingColor.setPropertyEnum(PropertyEnum.HOUSING_COLOR);
		housingColor.setLabelKey("");

		LightParameter bulbSerialNumber = new LightParameter();
		bulbSerialNumber.setValue("Bulb Serial Number");
		bulbSerialNumber.setDataTypeEnum(DataTypeEnum.STRING);
		bulbSerialNumber.setPropertyEnum(PropertyEnum.BULB_SERIAL_NUMBER);
		bulbSerialNumber.setLabelKey("");

		LightParameter lowerAssemblySerialNumber = new LightParameter();
		lowerAssemblySerialNumber.setValue("Lower Assembly Serial Number");
		lowerAssemblySerialNumber.setDataTypeEnum(DataTypeEnum.STRING);
		lowerAssemblySerialNumber.setPropertyEnum(PropertyEnum.LOWER_ASSEMBLY_SERIAL_NUMBER);
		lowerAssemblySerialNumber.setLabelKey("");

		LightParameter upperAssemblySerialNumber = new LightParameter();
		upperAssemblySerialNumber.setValue("Upper Assembly Serial Number");
		upperAssemblySerialNumber.setDataTypeEnum(DataTypeEnum.STRING);
		upperAssemblySerialNumber.setPropertyEnum(PropertyEnum.UPPER_ASSEMBLY_SERIAL_NUMBER);
		upperAssemblySerialNumber.setLabelKey("");

		LightParameter dimmable = new LightParameter();
		dimmable.setValue("True");
		dimmable.setDataTypeEnum(DataTypeEnum.STRING);
		dimmable.setPropertyEnum(PropertyEnum.DIMMABLE);
		dimmable.setLabelKey("");

		LightParameter current = new LightParameter();
		current.setValue("3131");
		current.setDataTypeEnum(DataTypeEnum.INTEGER);
		current.setPropertyEnum(PropertyEnum.AC_CURRENT);
		current.setLabelKey("");

		LightParameter currentmin = new LightParameter();
		currentmin.setValue("3131");
		currentmin.setDataTypeEnum(DataTypeEnum.INTEGER);
		currentmin.setPropertyEnum(PropertyEnum.AC_CURRENT_MIN);
		currentmin.setLabelKey("");

		LightParameter currentmax = new LightParameter();
		currentmax.setValue("100131");
		currentmax.setDataTypeEnum(DataTypeEnum.INTEGER);
		currentmax.setPropertyEnum(PropertyEnum.AC_CURRENT_MAX);
		currentmax.setLabelKey("");

		LightParameter consumption = new LightParameter();
		consumption.setValue("3131");
		consumption.setDataTypeEnum(DataTypeEnum.INTEGER);
		consumption.setPropertyEnum(PropertyEnum.CONSUMPTION);
		consumption.setLabelKey("");

		LightParameter consumptionmin = new LightParameter();
		consumptionmin.setValue("3131");
		consumptionmin.setDataTypeEnum(DataTypeEnum.INTEGER);
		consumptionmin.setPropertyEnum(PropertyEnum.CONSUMPTION_MIN);
		consumptionmin.setLabelKey("");

		LightParameter consumptionmax = new LightParameter();
		consumptionmax.setValue("100131");
		consumptionmax.setDataTypeEnum(DataTypeEnum.INTEGER);
		consumptionmax.setPropertyEnum(PropertyEnum.CONSUMPTION_MAX);

		LightParameter voltage = new LightParameter();
		voltage.setValue("3131");
		voltage.setDataTypeEnum(DataTypeEnum.INTEGER);
		voltage.setPropertyEnum(PropertyEnum.AC_VOLTAGE);
		voltage.setLabelKey("");

		LightParameter voltagemin = new LightParameter();
		voltagemin.setValue("3131");
		voltagemin.setDataTypeEnum(DataTypeEnum.INTEGER);
		voltagemin.setPropertyEnum(PropertyEnum.AC_VOLTAGE_MIN);
		voltagemin.setLabelKey("");

		LightParameter voltagemax = new LightParameter();
		voltagemax.setValue("100131");
		voltagemax.setDataTypeEnum(DataTypeEnum.INTEGER);
		voltagemax.setPropertyEnum(PropertyEnum.AC_VOLTAGE_MAX);
		voltagemax.setLabelKey("");

		List<LightParameter> list = new ArrayList<LightParameter>();

		list.add(poleId);
		list.add(lampType);
		list.add(dateAdded);
		list.add(city);
		list.add(firmware);
		list.add(modelNumber);
		list.add(colorTemperature);
		list.add(housingColor);
		list.add(inputVoltageRange);
		list.add(bulbSerialNumber);
		list.add(lowerAssemblySerialNumber);
		list.add(upperAssemblySerialNumber);
		list.add(latitude);
		list.add(longitude);
		list.add(dimmable);
		list.add(consumption);
		list.add(consumptionmin);
		list.add(consumptionmax);
		list.add(sunsetTime);
		list.add(sunsetOffset);
		list.add(sunriseOffset);
		list.add(sunriseTime);
		list.add(dateInstalled);
		list.add(manufacturer);
		list.add(wattage);
		list.add(sensusPartNumber);
		list.add(serial);
		list.add(voltage);
		list.add(voltagemin);
		list.add(voltagemax);
		list.add(current);
		list.add(currentmin);
		list.add(currentmax);

		return list;
	}

	/**
	 * Sets the column.
	 * 
	 * @param propertyEnum the property enum
	 * @param text the text
	 * @return the column
	 */
	private Column setColumn(SmartPointColumnEnum propertyEnum, String text)
	{
		Column column = new Column();
		column.setColumnEnum(propertyEnum);
		column.setFieldName(text);
		column.setOrdered(true);

		return column;
	}

	/**
	 * Sets the filter.
	 * 
	 * @param filterEnum the filter enum
	 * @return the filter
	 */
	private Filter setFilter(SmartPointFilterEnum filterEnum)
	{
		Filter filter = new Filter();

		filter.setFilterEnum(filterEnum);

		return filter;
	}

	@Override
	public InquiryLightResponse generateLightHistoryFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		return new InquiryLightResponse();
	}

	@Override
	public InquiryLightResponse fetchSmartpointsToMap(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse inquiryLightResponse = new InquiryLightResponse();
		List<Light> lightHistory = new ArrayList<Light>();

		if (!ValidationUtil.isNullOrZero(inquiryLightRequest.getBottomLeftLat()) &&
				!ValidationUtil.isNullOrZero(inquiryLightRequest.getBottomLeftLon()) &&
				!ValidationUtil.isNullOrZero(inquiryLightRequest.getTopRightLat()) &&
				!ValidationUtil.isNullOrZero(inquiryLightRequest.getTopRightLon()) &&
				!ValidationUtil.isNullOrZero(inquiryLightRequest.getMaxSmartpointCount()) &&
				!ValidationUtil.isNullOrEmpty(inquiryLightRequest.getTimezone()))
		{

			for (int i = 0; i < inquiryLightRequest.getMaxSmartpointCount(); i++)
			{
				Light light = new Light();

				light.setId(i);
				light.setLightStateEnum(LightStateEnum.ON);
				light.setLightStateEnumValue(1);
				light.setSmartPointTypeEnum(SmartPointTypeEnum.LIGHT);
				light.setSmartPointTypeValue(1);
				lightHistory.add(light);

			}
			inquiryLightRequest.setLights(lightHistory);

			inquiryLightResponse.setOperationSuccess(true);
			inquiryLightResponse.setResponseTime(new Date());
		}
		else
		{

			inquiryLightResponse.setOperationSuccess(false);
		}

		return inquiryLightResponse;
	}

	@Override
	public StatusMessageResponse fetchStatusMessageById(LightRequest lightRequest)
	{
		return new StatusMessageResponse();
	}

	public static LightTypeEnum getLightTypeRandom()
	{
		int i = new Random().nextInt(3);
		return LightTypeEnum.enumForValue(i + 1);
	}

	/**
	 * Gets the wattage random.
	 * 
	 * @return the wattage random
	 */
	public static Double getWattageRandom()
	{
		return new Double(new Random().nextInt(150));
	}

}
