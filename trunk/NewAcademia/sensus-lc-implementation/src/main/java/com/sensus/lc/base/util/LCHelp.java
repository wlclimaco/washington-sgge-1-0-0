package com.sensus.lc.base.util;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.common.validation.ValidationUtil.isNullOrZero;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.Response;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.base.model.request.LightSelectionRequest;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.LCActionParameter;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessItemStatusEnum;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.process.util.ProcessUtil;
import com.sensus.lc.tag.model.request.TagRequest;

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

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INVALID_HOUR. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INVALID_HOUR =
			"sensus.mlc.lightvalidator.invalid.hour";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INVALID_MINUTE. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INVALID_MINUTE =
			"sensus.mlc.lighttvalidator.invalid.minute";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INVALID_TIME_ZONE. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INVALID_TIME_ZONE =
			"sensus.mlc.lightvalidator.invalid.time.zone";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_HOUR_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_HOUR_REQUIRED =
			"sensus.mlc.lightvalidator.hour.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED =
			"sensus.mlc.lightvalidator.lightrniid.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_TEMPERATURE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_TEMPERATURE_REQUIRED =
			"sensus.mlc.lightvalidator.temperature.required";

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
	public static String generateRniCorrelationId()
	{
		return UUID.randomUUID().toString();
	}

	/**
	 * Creates the process request to csv.
	 * 
	 * @param request the request
	 * @param fileName the file name
	 * @param lightsAmount the lights amount
	 * @return the process request
	 */
	public static ProcessRequest createProcessRequestToCSV(InquiryPaginationRequest request, String fileName,
			Integer lightsAmount)
	{
		LCActionParameter lcActionParameter = new LCActionParameter();
		lcActionParameter.setProperty(com.sensus.lc.light.model.PropertyEnum.FILE_NAME);
		lcActionParameter.setActionValue(fileName);

		LCAction action = new LCAction(LCActionTypeEnum.GENERATE_CSV_FILE);
		action.getActionParameters().add(lcActionParameter);

		Process process = ProcessUtil.generateProcess(request.isMonitored(), action, lightsAmount);
		ProcessRequest processRequest = new ProcessRequest(request.getUserContext());
		processRequest.setProcess(process);
		return processRequest;
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
		processRequest.setMonitored(request.isMonitored());
		processRequest.setProcessItemStatusEnum(processItemStatusEnum);
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
		processRequest.setMonitored(request.isMonitored());
		processRequest.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);
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
	 * Checks if is success.
	 * 
	 * @param internalResponse the internal response
	 * @return true, if is success
	 */
	private static boolean isSuccess(InternalResponse internalResponse)
	{
		return internalResponse.getStatus() == Status.OperationSuccess
				|| internalResponse.getStatus() == Status.NoRowsFoundError;
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

		if (isNull(value) || value.trim().length() <= 0)
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

	/**
	 * TODO
	 * Temporary Method created as an alternative to Method "isValidLength" class "ValidationUtil".
	 * Used to unify messages in properties file.
	 * Checks if the length of string is valid and update message list with error
	 * and message if it fails.
	 * 
	 * @param field the field
	 * @param maxLength the max length
	 * @param msgCode the msg code
	 * @param msgList the msg list
	 * @return the list
	 */
	public static List<MessageInfo> isValidLength(String field, int maxLength, String msgCode, List<MessageInfo> msgList)
	{
		if (msgList == null)
		{
			msgList = new ArrayList<MessageInfo>();
		}

		if (!isNull(field) && field.length() > maxLength)
		{
			msgList.add(MessageInfo.createFieldValidationError(msgCode));
		}

		return msgList;
	}

}
