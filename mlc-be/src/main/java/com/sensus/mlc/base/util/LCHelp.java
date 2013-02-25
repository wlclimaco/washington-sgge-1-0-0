package com.sensus.mlc.base.util;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.common.validation.ValidationUtil.isNullOrZero;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.MLCSortExpression;
import com.sensus.mlc.base.model.TimeZoneInfo;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightOrderByEnum;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class LCHelp.
 */
public final class LCHelp
{

	/** The Constant MAX_HOUR. */
	private static final Integer MAX_HOUR = 24;

	/** The Constant MIN_HOUR. */
	private static final Integer MIN_HOUR = 0;

	/** The Constant MAX_MIN. */
	private static final Integer MAX_MIN = 59;

	/** The Constant MIN_MIN. */
	private static final Integer MIN_MIN = 0;

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_HOUR. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_HOUR =
			"sensus.mlc.smartpointvalidator.invalid.hour";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_MINUTE. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_MINUTE =
			"sensus.mlc.smartpointvalidator.invalid.minute";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_TIME_ZONE. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_TIME_ZONE =
			"sensus.mlc.smartpointvalidator.invalid.time.zone";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_HOUR_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_HOUR_REQUIRED =
			"sensus.mlc.smartpointvalidator.hour.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED =
			"sensus.mlc.smartpointvalidator.lightrniid.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_TEMPERATURE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_TEMPERATURE_REQUIRED =
			"sensus.mlc.smartpointvalidator.temperature.required";

	/**
	 * Instantiates a new lC help.
	 */
	public LCHelp()
	{
	}

	/**
	 * Generate rni correlation id.
	 *
	 * @return the string
	 */
	public String generateRniCorrelationId()
	{
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	/**
	 * Generate process.
	 *
	 * @param isMonitored the is monitored
	 * @param action the action
	 * @param lightList the light list
	 * @return the process
	 */
	public Process generateProcess(Boolean isMonitored, LCAction action, List<Light> lightList)
	{
		List<ProcessItem> processItems = new ArrayList<ProcessItem>();
		int processItemAmount = 0;

		if (!isNullOrEmpty(lightList))
		{
			for (Light light : lightList)
			{
				ProcessItem processItem = new ProcessItem();
				processItem.setLight(light);
				processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.PENDING);
				processItems.add(processItem);
			}

			processItemAmount = processItems.size();
		}

		Process process = generateProcess(isMonitored, action, processItemAmount);
		process.setProcessItems(processItems);
		return process;
	}

	/**
	 * Generate process.
	 *
	 * @param isMonitored the is monitored
	 * @param action the action
	 * @param processItemAmount the process item amount
	 * @return the process
	 */
	public static Process generateProcess(Boolean isMonitored, LCAction action, Integer processItemAmount)
	{
		Process process = new Process();
		process.setStartTime(LCDateUtil.getNewDateUTC());
		process.setIsMonitoredInstance(isMonitored);
		process.setIsParent(true);
		process.setIsProcessComplete(false);
		process.setLcAction(action);
		process.getLcAction().setDescription(LCActionUtil.generateActionDescription(process));
		process.setDescription(LCActionUtil.generateDescription(process, processItemAmount));
		process.setProcessItems(new ArrayList<ProcessItem>());
		if (!isNullOrZero(processItemAmount))
		{
			process.setProcessItems(null);
		}

		return process;
	}

	/**
	 * Creates the process request to csv.
	 *
	 * @param request the request
	 * @param fileName the file name
	 * @param lightsAmount the lights amount
	 * @return the process request
	 */
	public static ProcessRequest createProcessRequestToCSV(LightSelectionRequest request, String fileName,
			Integer lightsAmount)
	{
		LCActionParameter lcActionParameter = new LCActionParameter();
		lcActionParameter.setProperty(PropertyEnum.FILE_NAME);
		lcActionParameter.setActionValue(fileName);

		LCAction action = new LCAction(LCActionTypeEnum.GENERATE_CSV_FILE);
		action.getActionParameters().add(lcActionParameter);

		Process process = generateProcess(request.isMonitored(), action, lightsAmount);
		ProcessRequest processRequest = new ProcessRequest(request.getUserContext());
		processRequest.setProcess(process);
		return processRequest;
	}

	/**
	 * Creates the process item with failure.
	 *
	 * @param lights the lights
	 * @param itemStatus the item status
	 * @param statusReason the status reason
	 * @return the list
	 */
	public static List<ProcessItem> createProcessItemWithFailure(
			List<Light> lights,
			ProcessItemStatusEnum itemStatus,
			ProcessStatusReasonEnum statusReason)
	{
		if (ValidationUtil.isNullOrEmpty(lights))
		{
			return new ArrayList<ProcessItem>();
		}

		List<ProcessItem> processItemList = new ArrayList<ProcessItem>();
		for (Light light : lights)
		{
			processItemList.add(new ProcessItem(light, itemStatus, statusReason));
		}

		return processItemList;
	}

	/**
	 * Validate Temperature.
	 *
	 * @param temperature the temperature
	 * @param list the list
	 */
	public void validateTemperature(Integer temperature, List<MessageInfo> list)
	{
		isNull(temperature, SENSUS_MLC_SMARTPOINTVALIDATOR_TEMPERATURE_REQUIRED, list);
	}

	/**
	 * Validate tenant code.
	 *
	 * @param tenant the tenant
	 * @param list the list
	 */
	public static void validateTenantCode(Tenant tenant, List<MessageInfo> list)
	{
		if (!isNull(tenant))
		{
			validateTenantCode(tenant.getRniCode(), list);
		}
		else
		{
			list.add(new MessageInfo(SENSUS_MLC_VALIDATOR_REQUIRED, MessageSeverity.Error,
					MessageLevel.FieldValidation, Tenant.class.getSimpleName()));
		}
	}

	/**
	 * Validate tenant code.
	 *
	 * @param tenantCode the tenant code
	 * @param list the list
	 */
	public static void validateTenantCode(String tenantCode, List<MessageInfo> list)
	{
		isNullOrEmptyLC(tenantCode, SENSUS_MLC_VALIDATOR_REQUIRED, list, Tenant.class.getSimpleName());
	}

	/**
	 * Validate light rni id.
	 *
	 * @param list the list
	 * @param light the light
	 */
	public static void validateLightRniId(List<MessageInfo> list, Light light)
	{
		if (!isNull(light))
		{
			validateLightRniId(list, light.getRniId());
		}
		else
		{
			list.add(new MessageInfo(SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED, MessageSeverity.Error,
					MessageLevel.FieldValidation));
		}
	}

	/**
	 * Validate rni id.
	 *
	 * @param list the list
	 * @param rniId the rni id
	 */
	public static void validateLightRniId(List<MessageInfo> list, Integer rniId)
	{
		isNullOrZero(rniId, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED, list);
	}

	/**
	 * Validate consumption.
	 *
	 * @param hour the hour
	 * @param parmName the parm name
	 * @param list the list
	 */
	public void validateHour(Integer hour, String parmName, List<MessageInfo> list)
	{
		if (isNull(hour))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_HOUR_REQUIRED, new Object[] {hour,
							parmName}));
			return;
		}

		if ((hour > MAX_HOUR) || (hour < MIN_HOUR))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_HOUR, new Object[] {hour,
							parmName}));
		}
	}

	/**
	 * Validate minute.
	 *
	 * @param minute the minute
	 * @param parmName the parm name
	 * @param list the list
	 */
	public void validateMinute(Integer minute, String parmName, List<MessageInfo> list)
	{
		if (isNull(minute))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_HOUR_REQUIRED, new Object[] {minute,
							parmName}));
			return;
		}

		if ((minute > MAX_MIN) || (minute < MIN_MIN))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_MINUTE, new Object[] {minute,
							parmName}));
		}
	}

	/**
	 * Gets the total value.
	 *
	 * @param value the value
	 * @return the total value
	 */
	public static Double convertToDouble(Integer value)
	{
		if (isNull(value))
		{
			return null;
		}
		return new Double(value);
	}

	/**
	 * Gets the time zone info list.
	 *
	 * @return the time zone info list
	 */
	public List<TimeZoneInfo> getTimeZoneInfoList()
	{
		List<TimeZoneInfo> timeZoneInfo = new ArrayList<TimeZoneInfo>();
		String[] tz = TimeZone.getAvailableIDs();

		for (String javaTz : tz)
		{
			TimeZone timeZone = TimeZone.getTimeZone(javaTz);
			timeZoneInfo.add(new TimeZoneInfo(timeZone));
		}

		Collections.sort(timeZoneInfo, new TimeZoneInfo());

		return timeZoneInfo;
	}

	/**
	 * Validate time zone region.
	 *
	 * @param timeZoneRegion the time zone region
	 * @param list the list
	 */
	public static void validateTimeZoneRegion(String timeZoneRegion, List<MessageInfo> list)
	{
		if (isNull(timeZoneRegion))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_TIME_ZONE, timeZoneRegion));
		}
		else
		{

			TimeZone timeZone = TimeZone.getTimeZone(timeZoneRegion);
			if (isNull(timeZone))
			{
				list.add(MessageInfo
						.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_TIME_ZONE, timeZone));
			}
		}
	}

	/**
	 * Treat return from bcl.
	 *
	 * @param response the response
	 * @param internalResponse the internal response
	 * @param message the message
	 */
	public static void treatReturnFromBCL(Response response,
			InternalResponse internalResponse, String message)
	{
		if (!isNullOrEmpty(internalResponse.getMessageInfoList()))
		{
			// If there is something on the message list, add to the response
			response.addOperationFailedMessage(internalResponse.getMessageInfoList());
			response.setOperationSuccess(isSuccess(internalResponse));
		}
		else if (!isSuccess(internalResponse))
		{
			// add a generic exception message in case the message list is empty
			response.addOperationFailedMessage(message, (Object[])null);
		}
	}

	/**
	 * Checks for errors.
	 *
	 * @param messages the messages
	 * @param checkFatal the check fatal
	 * @return the boolean
	 */
	public static Boolean hasErrors(List<MessageInfo> messages, Boolean checkFatal)
	{
		for (MessageInfo message : messages)
		{
			if (message.getSeverity() == MessageSeverity.Error)
			{
				return true;
			}

			if (checkFatal)
			{
				if (message.getSeverity() == MessageSeverity.Fatal)
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Creates the inquiry light request.
	 *
	 * @param request the request
	 * @return the inquiry light request
	 */
	public static InquiryLightRequest createInquiryLightRequest(LightSelectionRequest request)
	{
		InquiryLightRequest inquiryLightRequest = new InquiryLightRequest(request.getUserContext());
		inquiryLightRequest.setSearch(request.getSearchLight());
		inquiryLightRequest.setSelectionPaginationIds(request.getSelectionPaginationIds());
		inquiryLightRequest.setUnselectionPaginationIds(request.getUnselectionPaginationIds());
		inquiryLightRequest.setPaginationAllSelected(request.getPaginationAllSelected());
		inquiryLightRequest.setIsMonitored(request.isMonitored());
		inquiryLightRequest.setSortExpression(new MLCSortExpression());
		inquiryLightRequest.getMlcSortExpression().setField(LightOrderByEnum.LIGHT_ID.getValue());
		inquiryLightRequest.getMlcSortExpression().setIsProperty(false);
		inquiryLightRequest.setPageSize(0);
		inquiryLightRequest.setCurrentLightStatus(request.isCurrentLightStatus());
		return inquiryLightRequest;
	}

	/**
	 * Creates the process request.
	 *
	 * @param request the schedule request
	 * @param processItemStatusEnum the process item status enum
	 * @return the process request
	 */
	public static ProcessRequest createProcessRequest(LightSelectionRequest request,
			ProcessItemStatusEnum processItemStatusEnum)
	{
		ProcessRequest processRequest = new ProcessRequest(request.getUserContext());
		processRequest.setSearchLight(request.getSearchLight());
		processRequest.setSelectionPaginationIds(request.getSelectionPaginationIds());
		processRequest.setUnselectionPaginationIds(request.getUnselectionPaginationIds());
		processRequest.setPaginationAllSelected(request.getPaginationAllSelected());
		processRequest.setIsMonitored(request.isMonitored());
		processRequest.setProcessItemStatusEnum(processItemStatusEnum);
		processRequest.setCurrentLightStatus(request.isCurrentLightStatus());
		return processRequest;
	}

	/**
	 * Creates the process request.
	 *
	 * @param request the request
	 * @param process the process
	 * @return the process request
	 */
	public static ProcessRequest createProcessRequest(LightSelectionRequest request, Process process)
	{
		ProcessRequest processRequest = new ProcessRequest(request.getUserContext());
		processRequest.setProcess(process);
		processRequest.setSearchLight(request.getSearchLight());
		processRequest.setSelectionPaginationIds(request.getSelectionPaginationIds());
		processRequest.setUnselectionPaginationIds(request.getUnselectionPaginationIds());
		processRequest.setPaginationAllSelected(request.getPaginationAllSelected());
		processRequest.setIsMonitored(request.isMonitored());
		processRequest.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);
		processRequest.setCurrentLightStatus(request.isCurrentLightStatus());
		return processRequest;
	}

	/**
	 * Creates the tag request.
	 *
	 * @param request the request
	 * @return the tag request
	 */
	public static TagRequest createTagRequest(LightSelectionRequest request)
	{
		TagRequest tagRequest = new TagRequest(request.getUserContext());
		tagRequest.setSearchLight(request.getSearchLight());
		tagRequest.setSelectionPaginationIds(request.getSelectionPaginationIds());
		tagRequest.setUnselectionPaginationIds(request.getUnselectionPaginationIds());
		tagRequest.setPaginationAllSelected(request.getPaginationAllSelected());
		tagRequest.setIsMonitored(request.isMonitored());
		tagRequest.setCurrentLightStatus(request.isCurrentLightStatus());
		return tagRequest;
	}

	/**
	 * Return all light status not deactivated.<br/>
	 * Check and remove deactivate status.
	 *
	 * @param statusList the status list
	 * @return the list
	 */
	public static List<LightStatusEnum> returnAllLightStatusNotDeactivated(List<LightStatusEnum> statusList)
	{
		if (isNullOrEmpty(statusList))
		{
			List<LightStatusEnum> allLightStatusNotDeactivated =
					new ArrayList<LightStatusEnum>(Arrays.asList(LightStatusEnum.values()));
			allLightStatusNotDeactivated.remove(LightStatusEnum.DEACTIVATED);
			return allLightStatusNotDeactivated;
		}

		statusList.remove(LightStatusEnum.DEACTIVATED);
		return statusList;
	}

	/**
	 * Checks if is success.
	 *
	 * @param internalResponse the internal response
	 * @return true, if is success
	 */
	private static boolean isSuccess(InternalResponse internalResponse)
	{
		return (internalResponse.getStatus() == Status.OperationSuccess)
				|| (internalResponse.getStatus() == Status.NoRowsFoundError);
	}

	/**
	 * Sets the light properties.
	 *
	 * @param lights the new light properties
	 */
	public static void setLightProperties(List<Light> lights)
	{
		if (isNullOrEmpty(lights))
		{
			return;
		}

		for (Light light : lights)
		{
			// FIXME - properties
			/*if (isNullOrEmpty(light.getParameters()))
			{
				continue;
			}*/

			setPropertiesToLight(light);
		}
	}

	/**
	 * Sets the properties to light.
	 *
	 * @param light the new properties to light
	 */
	private static void setPropertiesToLight(Light light)
	{
		// FIXME - properties
		/*for (LightParameter parameter : light.getParameters())
		{
			setPropertyToLight(light, parameter);
		}*/
	}

	/**
	 * Sets the property to light.
	 *
	 * @param light the light
	 * @param parameter the parameter
	 */
	private static void setPropertyToLight(Light light, LightParameter parameter)
	{
		if (isNull(parameter) || isNullOrEmpty(parameter.getValue()))
		{
			return;
		}

		PropertyEnum property = parameter.getPropertyEnum();
		String setterMethodName = getSetterName(property.name());

		try
		{
			Method setterMethod = Light.class.getDeclaredMethod(setterMethodName, String.class);
			setterMethod.invoke(light, parameter.getValue());

			// Properties with exceptions
			if ((property == PropertyEnum.AC_CURRENT_MAX)
					|| (property == PropertyEnum.AC_CURRENT_MIN)
					|| (property == PropertyEnum.AC_VOLTAGE_MAX)
					|| (property == PropertyEnum.AC_VOLTAGE_MIN))
			{
				setterMethod = Light.class.getDeclaredMethod(setterMethodName + "Date", Date.class);
				setterMethod.invoke(light, parameter.getCreateDate());
			}
		}
		catch (Throwable e)
		{
			// Do not set property
			return;
		}
	}

	/**
	 * Gets the setter name.
	 *
	 * @param fieldName the field name
	 * @return the setter name
	 */
	private static String getSetterName(String fieldName)
	{
		String cameCaseName = "";
		String[] names = StringUtils.splitByWholeSeparator(fieldName.toLowerCase(), "_");
		for (String name : names)
		{
			cameCaseName += StringUtils.capitalize(name);
		}
		return "set" + cameCaseName;
	}

	/**
	 * TODO
	 * Temporary Method created as an alternative to Method "IsNullOrEmpty" class "ValidationUtil".
	 * Used to unify messages in properties file.
	 * Tip: Creating a new overload, adding the parameter "Object ... args".
	 * Remove this method after adding it to the framework.
	 *
	 * @param value the value
	 * @param msgCode the msg code
	 * @param msgList the msg list
	 * @param args the args
	 * @return the list
	 */
	public static List<MessageInfo> isNullOrEmptyLC(String value, String msgCode, List<MessageInfo> msgList,
			Object... args)
	{
		if (msgList == null)
		{
			msgList = new ArrayList<MessageInfo>();
		}

		if (isNull(value) || (value.trim().length() <= 0))
		{
			msgList.add(MessageInfo.createFieldValidationError(msgCode, args));
		}

		return msgList;
	}

	/**
	 * TODO
	 * Temporary Method created as an alternative to Method "IsNullOrEmpty" class "ValidationUtil".
	 * Used to unify messages in properties file.
	 * Tip: Creating a new overload, adding the parameter "Object ... args".
	 * Remove this method after adding it to the framework.
	 *
	 * @param aList the a list
	 * @param msgCode the msg code
	 * @param msgList the msg list
	 * @return the list
	 */
	@SuppressWarnings("rawtypes")
	public static List<MessageInfo> isNullOrEmptyLC(List aList, String msgCode, List<MessageInfo> msgList,
			Object... args)
	{
		if (msgList == null)
		{
			msgList = new ArrayList<MessageInfo>();
		}

		if (isNullOrEmpty(aList))
		{
			msgList.add(MessageInfo.createFieldValidationError(msgCode, args));
		}

		return msgList;
	}

	/**
	 * TODO
	 * Temporary Method created as an alternative to Method "isNull" class "ValidationUtil".
	 * Used to unify messages in properties file.
	 * Tip: Creating a new overload, adding the parameter "Object ... args".
	 * Remove this method after adding it to the framework.
	 *
	 * @param object the object
	 * @param msgCode the msg code
	 * @param msgList the msg list
	 * @return the list
	 */
	public static List<MessageInfo> isNullLC(Object object, String msgCode, List<MessageInfo> msgList, Object... args)
	{
		if (msgList == null)
		{
			msgList = new ArrayList<MessageInfo>();
		}

		if (isNull(object))
		{
			msgList.add(MessageInfo.createFieldValidationError(msgCode, args));
		}

		return msgList;
	}

	/**
	 * TODO
	 * Temporary Method created as an alternative to Method "isNullOrZero" class "ValidationUtil".
	 * Used to unify messages in properties file.
	 * Tip: Creating a new overload, adding the parameter "Object ... args".
	 * Remove this method after adding it to the framework.
	 *
	 * @param value the value
	 * @param msgCode the msg code
	 * @param msgList the msg list
	 * @param args the args
	 * @return the list
	 */
	public static List<MessageInfo> isNullOrZeroLC(Integer value, String msgCode, List<MessageInfo> msgList,
			Object... args)
	{
		if (msgList == null)
		{
			msgList = new ArrayList<MessageInfo>();
		}

		if (isNullOrZero(value))
		{
			msgList.add(MessageInfo.createFieldValidationError(msgCode, args));
		}
		return msgList;
	}
}
