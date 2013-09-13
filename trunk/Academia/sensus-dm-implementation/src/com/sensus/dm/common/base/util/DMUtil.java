package com.sensus.dm.common.base.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.Optional;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.TimeZoneInfo;
import com.sensus.common.csv.CSVColumn;
import com.sensus.common.csv.CSVUtil;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.util.SensusMessageUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.action.model.GenerateCSVFileAction;
import com.sensus.dm.common.base.util.csv.cellprocessor.ConvertDateLeak;
import com.sensus.dm.common.base.util.csv.cellprocessor.ConvertDeviceClass;
import com.sensus.dm.common.base.util.csv.cellprocessor.ConvertEncryptionSupported;
import com.sensus.dm.common.base.util.csv.cellprocessor.ConvertInternationalization;
import com.sensus.dm.common.base.util.csv.cellprocessor.ConvertLifecycleState;
import com.sensus.dm.common.base.util.csv.cellprocessor.ConvertProcessAction;
import com.sensus.dm.common.base.util.csv.cellprocessor.ConvertProcessMessage;
import com.sensus.dm.common.base.util.csv.cellprocessor.ConvertQuarantine;
import com.sensus.dm.common.base.util.csv.cellprocessor.ConvertRandomizeDuration;
import com.sensus.dm.common.base.util.csv.cellprocessor.ConvertSmartpoints;
import com.sensus.dm.common.base.util.csv.cellprocessor.ConvertTimeZone;
import com.sensus.dm.common.base.util.csv.cellprocessor.ConvertTimeZoneDateTime;
import com.sensus.dm.common.base.util.csv.cellprocessor.CorvertDemandResponse;
import com.sensus.dm.common.base.util.csv.cellprocessor.CorvertProperty;
import com.sensus.dm.common.base.util.csv.model.CsvColumnEnum;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class DMUtil.
 * 
 * @author QAT Global
 */
public final class DMUtil
{
	/** The Constant SENSUS_RNI_REMOTE_ARMED_NOT_AVAILABLE. */
	private static final String SENSUS_RNI_REMOTE_ARMED_NOT_AVAILABLE = "sensus.rni.remote_armed_not_available";

	/** The Constant SENSUS_RNI_REMOTE_CONNECT_NOT_AVAILABLE. */
	private static final String SENSUS_RNI_REMOTE_CONNECT_NOT_AVAILABLE = "sensus.rni.remote_connect_not_available";

	/** The Constant SENSUS_RNI_REMOTE_CURRENT_STATE. */
	private static final String SENSUS_RNI_REMOTE_CURRENT_STATE = "sensus.rni.remote_current_state";

	/** The Constant SENSUS_RNI_REMOTE_DISCONNECT_NOT_AVAILABLE. */
	private static final String SENSUS_RNI_REMOTE_DISCONNECT_NOT_AVAILABLE =
			"sensus.rni.remote_disconnect_not_available";

	/** The Constant SENSUS_RNI_METER_NOT_FOUND. */
	private static final String SENSUS_RNI_METER_NOT_FOUND = "sensus.rni.meter_not_found";

	/** The Constant SENSUS_RNI_REQUEST_TIMED_OUT_WHILE_WAITING_FOR_RESPONSE. */
	private static final String SENSUS_RNI_REQUEST_TIMED_OUT_WHILE_WAITING_FOR_RESPONSE =
			"sensus.rni.Request_timed_out_while_waiting_for_response";

	/** The Constant SENSUS_RNI_FAILURE. */
	private static final String SENSUS_RNI_FAILURE = "sensus.rni.failure";

	/** The Constant SENSUS_RNI_NOT_FOUND. */
	private static final String SENSUS_RNI_NOT_FOUND = "sensus.rni.not_found";

	/** The Constant SENSUS_RNI_DUPLICATE_WITH_SQUARE_BRACKETS. */
	private static final String SENSUS_RNI_DUPLICATE_WITH_SQUARE_BRACKETS = "sensus.rni.duplicate_with_square_brackets";

	/** The Constant SENSUS_RNI_DUPLICATE. */
	private static final String SENSUS_RNI_DUPLICATE = "sensus.rni.duplicate";

	/** The Constant SENSUS_RNI_NO_ROUTES. */
	private static final String SENSUS_RNI_NO_ROUTES = "sensus.rni.no_routes";

	/** The Constant SENSUS_RNI_INVALID_ID. */
	private static final String SENSUS_RNI_INVALID_ID = "sensus.rni.invalid_id";

	/** The Constant SENSUS_RNI_INVALID_TARGET. */
	private static final String SENSUS_RNI_INVALID_TARGET = "sensus.rni.invalid_target";

	/** The Constant SENSUS_RNI_DEVICE_DOES_NOT_ACCEPT_FUTURE_TIME. */
	private static final String SENSUS_RNI_DEVICE_DOES_NOT_ACCEPT_FUTURE_TIME =
			"sensus.rni.device_does_not_accept_future_time";

	/** The Constant SENSUS_RNI_TOO_MANY_ATTEMPTS. */
	private static final String SENSUS_RNI_TOO_MANY_ATTEMPTS = "sensus.rni.too_many_attempts";

	/** The Constant SENSUS_RNI_DB_ERROR_DUPLICATE. */
	private static final String SENSUS_RNI_DB_ERROR_DUPLICATE = "sensus.rni.db_error_duplicate";

	/** The Constant SENSUS_RNI_DEVICE_NOT_FOUND. */
	private static final String SENSUS_RNI_DEVICE_NOT_FOUND = "sensus.rni.device_not_found";

	/** The Constant SENSUS_RNI_REPID_NOT_FOUND. */
	private static final String SENSUS_RNI_REPID_NOT_FOUND = "sensus.rni.repid_not_found";

	/** The Constant SIXTY. */
	private static final int SIXTY = 60;

	/** The Constant THOUSAND. */
	private static final int THOUSAND = 1000;

	/** The Constant MAX_HOUR. */
	private static final Integer MAX_HOUR = 24;

	/** The Constant MIN_HOUR. */
	private static final Integer MIN_HOUR = 0;

	/** The Constant MAX_MIN. */
	private static final Integer MAX_MIN = 59;

	/** The Constant ONE. */
	private static final int ONE = 1;

	/** The Constant MIN_MIN. */
	private static final Integer MIN_MIN = 0;

	/** The Constant HH_MM_SS_A. */
	private static final String HH_MM_SS_A = " hh:mm:ss a";

	/** The Constant SENSUS_EPM_SMARTPOINTVALIDATOR_INVALID_HOUR. */
	private static final String SENSUS_EPM_SMARTPOINTVALIDATOR_INVALID_HOUR =
			"sensus.epm.smartpointvalidator.invalid.hour";

	/** The Constant SENSUS_EPM_SMARTPOINTVALIDATOR_INVALID_MINUTE. */
	private static final String SENSUS_EPM_SMARTPOINTVALIDATOR_INVALID_MINUTE =
			"sensus.epm.smartpointvalidator.invalid.minute";

	/** The Constant SENSUS_EPM_SMARTPOINTVALIDATOR_INVALID_TIME_ZONE. */
	private static final String SENSUS_EPM_SMARTPOINTVALIDATOR_INVALID_TIME_ZONE =
			"sensus.epm.smartpointvalidator.invalid.time.zone";

	/** The Constant SENSUS_EPM_SMARTPOINTVALIDATOR_HOUR_REQUIRED. */
	private static final String SENSUS_EPM_SMARTPOINTVALIDATOR_HOUR_REQUIRED =
			"sensus.epm.smartpointvalidator.hour.required";

	/** The Constant SENSUS_EPM_SMARTPOINTVALIDATOR_MINUTE_REQUIRED. */
	private static final String SENSUS_EPM_SMARTPOINTVALIDATOR_MINUTE_REQUIRED =
			"sensus.epm.smartpointvalidator.minute.required";

	/** The Constant ERROR_READING_FILE. */
	public static final String ERROR_READING_FILE = "Error reading file";

	/** The Constant ERROR_COPYING_FILE. */
	public static final String ERROR_COPYING_FILE = "Error copying file";

	/** The Constant ERROR_CREATING_FILE. */
	public static final String ERROR_CREATING_FILE = "Error creating file";

	/** The Constant STR_SPACE. */
	private static final String STR_SPACE = " ";

	/** The Constant STR_STAR. */
	private static final String STR_STAR = "*";

	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(DMUtil.class);

	/** The column map. */
	private static final Map<String, String> MESSAGE_MAP = new HashMap<String, String>();

	/** The Constant EPM_ACTION_DURATION_INVALID. */
	private static final String EPM_ACTION_DURATION_INVALID = "sensus.epm.actionvalidator.duration.invalid";

	/** The Constant FILE_DOWNLOADED. */
	private static final String FILE_DOWNLOADED = "dm.common.process.csv.file.downloaded";

	/** The Constant ON_DEMAND. */
	private static final String ON_DEMAND = "[OnDemand|";

	/** The Constant EXPORTING_CSV. */
	private static final String EXPORTING_CSV = "dm.common.process.csv.exporting.csv";

	/** The Constant DOWNLOAD_CSV. */
	private static final String DOWNLOAD_CSV = "dm.common.process.csv.download.csv";

	/** The Constant PIPE. */
	private static final String PIPE = "|";

	/** The Constant NETWORK_ADDRESS. */
	private static final String NETWORK_ADDRESS = "Network Address";

	/** The Constant DEVICE. */
	private static final String DEVICE = "Device";

	/** The Constant DEVICE_SPACE. */
	private static final String DEVICE_SPACE = "Device ";

	/** The Constant SENSUS_DM_EXPORT_CSV_ERROR. */
	private static final String SENSUS_DM_EXPORT_CSV_ERROR = "sensus.common.csv.exception";

	/** The Constant DEFAULT_DATE. */
	private static final Long DEFAULT_DATE = 946684800L;

	/** The Constant UTC. */
	private static final String UTC = "UTC";

	static
	{
		MESSAGE_MAP.put("[Device not found]", SENSUS_RNI_DEVICE_NOT_FOUND);
		MESSAGE_MAP.put("[RepidNotFound, Device not found]", SENSUS_RNI_REPID_NOT_FOUND);
		MESSAGE_MAP.put("DB_ERROR_DUPLICATE", SENSUS_RNI_DB_ERROR_DUPLICATE);
		MESSAGE_MAP.put("TOO_MANY_ATTEMPTS", SENSUS_RNI_TOO_MANY_ATTEMPTS);
		MESSAGE_MAP.put("DEVICE_DOES_NOT_ACCEPT_FUTURE_TIME", SENSUS_RNI_DEVICE_DOES_NOT_ACCEPT_FUTURE_TIME);
		MESSAGE_MAP.put("INVALID_TARGET", SENSUS_RNI_INVALID_TARGET);
		MESSAGE_MAP.put("INVALID_ID", SENSUS_RNI_INVALID_ID);
		MESSAGE_MAP.put("NO_ROUTES", SENSUS_RNI_NO_ROUTES);
		MESSAGE_MAP.put("DUPLICATE", SENSUS_RNI_DUPLICATE);
		MESSAGE_MAP.put("[DUPLICATE]", SENSUS_RNI_DUPLICATE_WITH_SQUARE_BRACKETS);
		MESSAGE_MAP.put("NOT_FOUND", SENSUS_RNI_NOT_FOUND);
		MESSAGE_MAP.put("[Failure]", SENSUS_RNI_FAILURE);
		MESSAGE_MAP.put("[Request timed out while waiting for response(s).]",
				SENSUS_RNI_REQUEST_TIMED_OUT_WHILE_WAITING_FOR_RESPONSE);
		MESSAGE_MAP.put("[Meter Not Found, Device not found]", SENSUS_RNI_METER_NOT_FOUND);
		MESSAGE_MAP.put("Remote DISCONNECT capability not available for this device type",
				SENSUS_RNI_REMOTE_DISCONNECT_NOT_AVAILABLE);
		MESSAGE_MAP.put("Remote CURRENT_STATE capability not available for this device type",
				SENSUS_RNI_REMOTE_CURRENT_STATE);
		MESSAGE_MAP.put("Remote CONNECT capability not_available for this device type",
				SENSUS_RNI_REMOTE_CONNECT_NOT_AVAILABLE);
		MESSAGE_MAP.put("Remote ARMED capability not available for this device type",
				SENSUS_RNI_REMOTE_ARMED_NOT_AVAILABLE);
		MESSAGE_MAP.put("[summary.text.processStatus.NO_ROUTES]", SENSUS_RNI_NO_ROUTES);
	}

	/**
	 * Instantiates a new EPM util.
	 */
	private DMUtil()
	{

	}

	/**
	 * Generate UUID.
	 * 
	 * @return the string
	 */
	public static String generateUUId()
	{
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	/**
	 * Generate rni event id.
	 * 
	 * @return the integer
	 */
	public static Integer generateRNIEventId()
	{
		return (int)(DMConvertUtil.convertDateToInteger(new Date(), UTC) - DEFAULT_DATE);
	}

	/**
	 * Generate process.
	 * 
	 * @param isMonitored the is monitored
	 * @param isDashboardMonitored the is dashboard monitored
	 * @param action the action
	 * @param processType the process type
	 * @param processItems the process items list
	 * @param processStatusEnum the process status enum
	 * @param properties the properties
	 * @return the process
	 */
	public static DMProcess generateProcess(Boolean isMonitored, Boolean isDashboardMonitored, DMAction action,
			ProcessType processType, List<ProcessItem> processItems, ProcessStatusEnum processStatusEnum,
			List<Property> properties)
	{
		DMProcess process = new DMProcess();
		process.setStartTime(DMConvertUtil.convertDateToDefaultUTC(Calendar.getInstance().getTime()));
		process.setIsMonitoredInstance(isMonitored);
		process.setIsDashboardMonitored(isDashboardMonitored);
		process.setProcessItems(processItems);
		process.setAction(action);
		process.setProcessType(processType);
		process.setProcessStatusEnum(processStatusEnum);
		process.setProperties(properties);

		if (ProcessStatusEnum.COMPLETED.equals(processStatusEnum))
		{
			process.setEndTime(DMConvertUtil.convertDateToDefaultUTC(Calendar.getInstance().getTime()));
			process.setIsProcessComplete(true);
		}

		return process;
	}

	/**
	 * Generate process.
	 * 
	 * @param processId the process id
	 * @param processStatusEnum the process status enum
	 * @return the dM process
	 */
	public static DMProcess generateProcess(Integer processId, ProcessStatusEnum processStatusEnum)
	{

		DMProcess process = new DMProcess(processId, processStatusEnum);

		if (ProcessStatusEnum.COMPLETED.equals(processStatusEnum))
		{
			process.setEndTime(DMConvertUtil.convertDateToDefaultUTC(Calendar.getInstance().getTime()));
			process.setIsProcessComplete(true);
		}

		return process;

	}

	/**
	 * Generate process.
	 * 
	 * @param processId the process id
	 * @param processStatusEnum the process status enum
	 * @param properties the properties
	 * @return the dM process
	 */
	public static DMProcess generateProcess(Integer processId, ProcessStatusEnum processStatusEnum,
			List<Property> properties)
	{
		DMProcess process = generateProcess(processId, processStatusEnum);
		process.setProperties(properties);
		return process;
	}

	/**
	 * Generate process items list.
	 * 
	 * @param deviceList list of device with same status
	 * @param status status of the process items
	 * @return list of process items
	 */
	public static List<ProcessItem> generateProcessItems(List<Device> deviceList, ProcessItemStatusEnum status)
	{
		List<ProcessItem> processItems = new ArrayList<ProcessItem>();

		for (Device device : deviceList)
		{
			processItems.add(new ProcessItem(device, status));
		}

		return processItems;
	}

	/**
	 * Validate hour.
	 * 
	 * @param hour the hour
	 * @param parmName the parm name
	 * @param list the list
	 */
	public static void validateHour(Integer hour, String parmName, List<MessageInfo> list)
	{
		if (ValidationUtil.isNull(hour))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_EPM_SMARTPOINTVALIDATOR_HOUR_REQUIRED, parmName));
		}
		else if (hour > MAX_HOUR || hour < MIN_HOUR)
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_EPM_SMARTPOINTVALIDATOR_INVALID_HOUR, new Object[] {hour,
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
	public static void validateMinute(Integer minute, String parmName, List<MessageInfo> list)
	{
		if (ValidationUtil.isNull(minute))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_EPM_SMARTPOINTVALIDATOR_MINUTE_REQUIRED, parmName));
		}
		else if (minute > MAX_MIN || minute < MIN_MIN)
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_EPM_SMARTPOINTVALIDATOR_INVALID_MINUTE, new Object[] {minute,
							parmName}));
		}
	}

	/**
	 * Gets the time zone info list.
	 * 
	 * @return the time zone info list
	 */
	public static List<TimeZoneInfo> getTimeZoneInfoList()
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
		if (ValidationUtil.isNull(timeZoneRegion))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_EPM_SMARTPOINTVALIDATOR_INVALID_TIME_ZONE, timeZoneRegion));
		}
		else
		{

			TimeZone timeZone = TimeZone.getTimeZone(timeZoneRegion);
			if (ValidationUtil.isNull(timeZone))
			{
				list.add(MessageInfo
						.createFieldValidationError(SENSUS_EPM_SMARTPOINTVALIDATOR_INVALID_TIME_ZONE, timeZone));
			}
		}
	}

	/**
	 * Read file.
	 * 
	 * @param file the file
	 * @return the string
	 */
	public static String readFile(File file)
	{
		try
		{
			return FileUtils.readFileToString(file);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ERROR_READING_FILE, e);
			}
		}

		return null;
	}

	/**
	 * Read file lines.
	 * 
	 * @param file the file
	 * @return the list
	 */
	public static List<String> readFileLines(File file)
	{
		try
		{
			return FileUtils.readLines(file);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ERROR_READING_FILE, e);
			}
		}

		return null;
	}

	/**
	 * Read file.
	 * 
	 * @param fileName the file name
	 * @return the string
	 */
	public static String readFile(String fileName)
	{
		try
		{
			return FileUtils.readFileToString(new File(fileName));
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ERROR_READING_FILE, e);
			}
		}
		return null;
	}

	/**
	 * Copy file.
	 * 
	 * @param srcFile the src file
	 * @param destFile the dest file
	 * @return true, if successful
	 */
	public static boolean copyFile(File srcFile, File destFile)
	{
		try
		{
			FileUtils.copyFile(srcFile, destFile);
			return true;
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ERROR_COPYING_FILE, e);
			}
		}
		return false;
	}

	/**
	 * Creates the file.
	 * 
	 * @param listIds the list ids
	 * @param fileName the file name
	 * @return true, if successful
	 */
	public static boolean createFile(List<String> listIds, String fileName)
	{
		FileWriter fStream = null;
		BufferedWriter out = null;

		try
		{
			fStream = new FileWriter(fileName);
			out = new BufferedWriter(fStream);
			StringBuilder output = new StringBuilder();
			final String comma = ",";

			for (String id : listIds)
			{
				output.append(id).append(comma);
			}

			out.write(output.toString());

			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();

			if (LOG.isErrorEnabled())
			{
				LOG.error(ERROR_CREATING_FILE, e);
			}
		}
		finally
		{
			try
			{

				if (!ValidationUtil.isNull(out))
				{
					out.close();
				}

				if (!ValidationUtil.isNull(fStream))
				{
					fStream.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();

				if (LOG.isErrorEnabled())
				{
					LOG.error(ERROR_CREATING_FILE, e);
				}
			}
		}

		return false;
	}

	/**
	 * Delete file.
	 * 
	 * @param fileName the file name
	 */
	public static void deleteFile(String fileName)
	{
		new File(fileName).delete();
	}

	/**
	 * Check whether <code>devices</code> list contains a {@link Device} object with
	 * the given <code>flexNetID</code>.
	 * 
	 * @param flexNetID the FlexNet ID
	 * @param devices the list of devices
	 * @return true, if successful
	 */
	public static boolean containsFlexNetIDOnDeviceList(final BigInteger flexNetID, final List<Device> devices)
	{
		for (final Device device : devices)
		{
			if (device.getRadio().getFlexNetId().equals(flexNetID))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Creates a list with the given object as first element. This method is just
	 * a shortcut to create a list with a single object inside.
	 * 
	 * @param <T> the generic type
	 * @param t the t
	 * @return the a list with the object
	 */
	public static <T> List<T> getAListWithTheObject(T t)
	{
		List<T> ts = new ArrayList<T>();
		ts.add(t);
		return ts;
	}

	/**
	 * Creates the xml gregorian calendar.
	 * 
	 * @param time the time
	 * @return the xML gregorian calendar
	 */
	public static XMLGregorianCalendar createXMLGregorianCalendar(Date time)
	{

		if (!ValidationUtil.isNull(time))
		{
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(time);

			return DMConvertUtil.convertCalendarToXMLGregorianCalendar(gc);
		}

		return null;

	}

	/**
	 * Creates the xml gregorian calendar.
	 * 
	 * @param time the time
	 * @param hour the hour
	 * @param minute the minute
	 * @param second the second
	 * @return the xML gregorian calendar
	 */
	public static XMLGregorianCalendar createXMLGregorianCalendar(Date time, Integer hour, Integer minute,
			Integer second)
	{
		return DMConvertUtil.convertCalendarToXMLGregorianCalendar(createGregorianCalendar(time, hour, minute, second));
	}

	/**
	 * Creates the xml gregorian calendar.
	 * 
	 * @param time the time
	 * @param hour the hour
	 * @param minute the minute
	 * @param second the second
	 * @param timeZone the time zone
	 * @return the xML gregorian calendar
	 */
	public static XMLGregorianCalendar createXMLGregorianCalendar(Date time, Integer hour, Integer minute,
			Integer second, TimeZone timeZone)
	{
		GregorianCalendar gc = createGregorianCalendar(time, hour, minute, second);
		gc.setTimeZone(timeZone);

		return DMConvertUtil.convertCalendarToXMLGregorianCalendar(gc);
	}

	/**
	 * Gets the return message.
	 * 
	 * @param message the message inside a StringBuilder
	 * @param locale the Locale
	 * @return the return message
	 */
	public static String getReturnMessage(String message, Locale locale)
	{
		// the message passed in is null, so quit
		if (ValidationUtil.isNull(message))
		{
			return "";
		}

		// try to internationalize the message passed in
		String returnMessage = getLocalizedValueAsString(message.toString(), locale);

		// the message was found in the i18n message files
		if (!ValidationUtil.isNullOrEmpty(returnMessage))
		{
			return returnMessage;
		}

		// no space was found in the message...quit
		if (message.indexOf(STR_STAR) < 0)
		{
			return message;
		}

		// instantiate a new StringBuilder for working with our message
		StringBuilder sbMessage = new StringBuilder(message);

		// the message was not found, so search for the first empty space and search for that
		// in the i18n message files
		returnMessage = getLocalizedValueAsString(sbMessage.substring(0, sbMessage.indexOf(STR_STAR)), locale);

		// the message was found in the i18n message files
		if (!ValidationUtil.isNullOrEmpty(returnMessage))
		{
			sbMessage.replace(0, sbMessage.indexOf(STR_STAR), returnMessage);
			sbMessage.replace(sbMessage.indexOf(STR_STAR), sbMessage.indexOf(STR_STAR) + 1, STR_SPACE);

		}

		// return the string value of the current message
		// if no space was found, will be the original message
		return sbMessage.toString();

	}

	/**
	 * Generate message rni.
	 * 
	 * @param messageError the message error
	 * @return the string
	 */
	public static String generateMessageRni(String messageError)
	{
		String messageInter = MESSAGE_MAP.get(messageError);

		if (ValidationUtil.isNullOrEmpty(messageInter))
		{
			return messageError;
		}

		return messageInter;
	}

	/**
	 * Gets the localized value as string.
	 * 
	 * @param val the val
	 * @param locale the locale
	 * @return the localized value as string
	 */
	private static String getLocalizedValueAsString(String val, Locale locale)
	{
		if (val != null)
		{
			return SensusMessageUtil.getMessage(val, null, null, locale);
		}

		return null;
	}

	/**
	 * Calculate duration.
	 * 
	 * @param actionDate the action date
	 * @param now the now
	 * @return the integer
	 */
	public static Integer calculateDuration(Date actionDate, Date now)
	{
		if (actionDate.before(now))
		{
			Calendar cActionTime = Calendar.getInstance();
			cActionTime.setTime(actionDate);
			return (int)((now.getTime() - actionDate.getTime()) / THOUSAND / SIXTY) + ONE;
		}
		return MIN_MIN;
	}

	/**
	 * Validate duration.
	 * 
	 * @param list the list
	 * @param actionTime the action time
	 * @param nowDate the now date
	 * @param duration the duration
	 * @return true, if successful
	 */
	public static boolean validateDuration(List<MessageInfo> list, Date actionTime, Date nowDate, Integer duration)
	{
		if (calculateDuration(actionTime, nowDate) > duration)
		{
			list.add(MessageInfo.createFieldValidationError(EPM_ACTION_DURATION_INVALID));
			return false;
		}

		return true;
	}

	/**
	 * Format description.
	 * 
	 * @param process the process
	 * @return the string
	 */
	public static String formatDescription(DMProcess process)
	{
		String descriptionFormat = process.getDescription();

		if (!ValidationUtil.isNullOrEmpty(process.getDescription())
				&& !getLocalizedValueAsString(FILE_DOWNLOADED, null).equalsIgnoreCase(process.getDescription()))
		{
			descriptionFormat = process.getDescription().substring(process.getDescription().indexOf(']') + 1,
					process.getDescription().length());

			if (descriptionFormat.contains(NETWORK_ADDRESS))
			{
				if (descriptionFormat.contains(PIPE))
				{
					descriptionFormat =
							descriptionFormat.replace(descriptionFormat.substring(
									descriptionFormat.indexOf(NETWORK_ADDRESS), descriptionFormat.indexOf(PIPE) + 1),
									DEVICE_SPACE);

					String description =
							descriptionFormat.substring(descriptionFormat.indexOf(PIPE), descriptionFormat.length());

					descriptionFormat = descriptionFormat.substring(0, descriptionFormat.indexOf(PIPE));

					if (description.contains(STR_SPACE))
					{
						descriptionFormat +=
								description.substring(description.indexOf(STR_SPACE), description.length());
					}
				}
				else
				{

					descriptionFormat = descriptionFormat.replace(NETWORK_ADDRESS, DEVICE);
				}

			}

			if (descriptionFormat.contains(ON_DEMAND))
			{
				descriptionFormat = descriptionFormat.substring(0, descriptionFormat.indexOf(ON_DEMAND));
			}

			if (!ValidationUtil.isNull(process.getProcessType())
					&& GenerateCSVFileAction.ACTION.equals(process.getProcessType().getDescription()))
			{
				if (process.getProcessStatusEnum().equals(ProcessStatusEnum.IN_PROCESS))
				{
					descriptionFormat = getLocalizedValueAsString(EXPORTING_CSV, null);
				}
				else
				{
					descriptionFormat = getLocalizedValueAsString(DOWNLOAD_CSV, null);
				}
			}
		}

		return descriptionFormat;
	}

	/**
	 * Generate csv.
	 * 
	 * @param allPossibleColumns the all possible columns
	 * @param columnsToExport the columns to export
	 * @param sourceList the source list
	 * @param request the request
	 * @param response the response
	 */
	public static void generateCSV(List<CSVColumn> allPossibleColumns, String[] columnsToExport, List<?> sourceList,
			TenantRequest request, InternalResponse response)
	{
		// Filter out just the selected columns.
		List<CSVColumn> selectedColumns = CSVUtil.filterColumns(allPossibleColumns, columnsToExport);

		Locale locale = getLocale(request);

		prepareCSV(selectedColumns, request, locale);

		// Invoke utility method to create CSV
		validateExport(CSVUtil.exportFile(selectedColumns, request.getFileName(), sourceList, locale), response);

	}

	/**
	 * Generate csv.
	 * 
	 * @param allPossibleColumns the all possible columns
	 * @param sourceList the source list
	 * @param request the request
	 * @param response the response
	 */
	public static void generateCSV(List<CSVColumn> allPossibleColumns, List<?> sourceList,
			TenantRequest request, InternalResponse response)
	{
		// Invoke utility method to create CSV
		validateExport(CSVUtil.exportFile(allPossibleColumns, request.getFileName(), sourceList, null), response);
	}

	/**
	 * Gets the locale.
	 * 
	 * @param request the request
	 * @return the locale
	 */
	private static Locale getLocale(TenantRequest request)
	{
		if (!ValidationUtil.isNull(request.getUserContext()))
		{
			return new Locale(request.getUserContext().getLocaleString());
		}
		return null;
	}

	/**
	 * Gets the unreachables ids.
	 * 
	 * @param request the request
	 * @return the unreachables ids
	 */
	private static Integer getUnreachablesIds(TenantRequest request)
	{
		if (!ValidationUtil.isNull(((InquiryProcessRequest)request).getUnreachablesIds()))
		{

			return Integer.parseInt(((InquiryProcessRequest)request).getUnreachablesIds());

		}
		return new Integer(0);
	}

	/**
	 * Prepare csv.
	 * 
	 * @param selectedColumns the selected columns
	 * @param request the request
	 * @param locale the locale
	 */
	private static void prepareCSV(List<CSVColumn> selectedColumns, TenantRequest request, Locale locale)
	{
		if (ValidationUtil.isNullOrEmpty(selectedColumns))
		{
			return;
		}

		// Find my date column
		for (CSVColumn col : selectedColumns)
		{
			if (CsvColumnEnum.DATE_ADDED.getValue().equals(col.getName())
					|| CsvColumnEnum.INSTALL_DATE.getValue().equals(col.getName())
					|| CsvColumnEnum.DELIVER_ON.getValue().equals(col.getName()))
			{
				if (ValidationUtil.isNull(request.getTimeZone()))
				{
					// Replace the write cell processor
					col.setWriteCellProcessor(new Optional(new FmtDate(request.getDateFormat())));
				}
				else
				{
					// Replace the write cell processor
					col.setWriteCellProcessor(new Optional(new ConvertTimeZone(request.getDateFormat(), request
							.getTimeZone())));
				}
			}
			else if (CsvColumnEnum.PEAK_TIME.getValue().equals(col.getName())
					|| CsvColumnEnum.READING_DATE.getValue().equals(col.getName())
					|| CsvColumnEnum.RESET_DATE.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new Optional(new ConvertTimeZone(request.getDateFormat() + HH_MM_SS_A,
						request.getTimeZone())));
			}

			else if (CsvColumnEnum.START_TIME.getValue().equals(col.getName()))
			{
				if (ValidationUtil.isNull(request.getTimeZone()))
				{
					// Replace the write cell processor
					col.setWriteCellProcessor(new Optional(new FmtDate(request.getDateFormat())));
				}
				else
				{
					// Replace the write cell processor
					col.setWriteCellProcessor(new Optional(new ConvertTimeZoneDateTime(request.getDateFormat(),
							request
									.getTimeZone())));
				}
			}
			else if (CsvColumnEnum.LAST_HEARD.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new Optional(new ConvertTimeZone(request.getDateFormat(), request
						.getTimeZone())));
			}
			else if (CsvColumnEnum.DEVICE_TYPE.getValue().equals(col.getName())
					|| CsvColumnEnum.REMOTE_DISCONNECT.getValue().equals(col.getName())
					|| CsvColumnEnum.GROUP_TYPE.getValue().equals(col.getName())
					|| CsvColumnEnum.ALARM.getValue().equals(col.getName())
					|| CsvColumnEnum.FREQUENCY.getValue().equals(col.getName())
					|| CsvColumnEnum.SCHEDULE_STATUS.getValue().equals(col.getName())
					|| CsvColumnEnum.PROCESS_ITEM_STATE.getValue().equals(col.getName())
					|| CsvColumnEnum.ACTION_NAME_SCH.getValue().equals(col.getName())
					|| CsvColumnEnum.ACTION_TYPE_SCH.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new Optional(new ConvertInternationalization(locale)));
			}
			else if (CsvColumnEnum.QUARANTINE.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new Optional(new ConvertQuarantine(locale)));
			}
			else if (CsvColumnEnum.ENCRYPTION_SUPPORT.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new Optional(new ConvertEncryptionSupported(locale)));
			}
			else if (CsvColumnEnum.ACTION_DATE.getValue().equals(col.getName())
					|| CsvColumnEnum.READ_VALUE.getValue().equals(col.getName())
					|| CsvColumnEnum.READ_TIME.getValue().equals(col.getName())
					|| CsvColumnEnum.ACTION_DATE_SCH.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new Optional(new CorvertProperty(request.getDateFormat(), request
						.getTimeZone(), col.getName())));
			}
			else if (CsvColumnEnum.FULL_PARTICIPATION.getValue().equals(col.getName())
					|| CsvColumnEnum.PARTICIPATION.getValue().equals(col.getName())
					|| CsvColumnEnum.OPT_OUT.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new Optional(new CorvertDemandResponse(col.getName(), locale)));
			}
			else if (CsvColumnEnum.ACTION_NAME.getValue().equals(col.getName())
					|| CsvColumnEnum.ACTION_TYPE.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new Optional(new ConvertProcessAction(locale, col.getName())));
			}
			else if (CsvColumnEnum.DEVICES_TOTAL.getValue().equals(col.getName())
					|| CsvColumnEnum.DEVICES_FAILED.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new ConvertSmartpoints(getUnreachablesIds(request), col.getName()));

			}
			else if (CsvColumnEnum.ENCRYPTION_SUPPORT.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new Optional(new ConvertEncryptionSupported(locale)));
			}
			else if (CsvColumnEnum.LEAK_TIME.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new Optional(new ConvertDateLeak(request.getDateFormat())));
			}
			else if (CsvColumnEnum.MESSAGE_ERROR.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new Optional(new ConvertProcessMessage(locale)));
			}
			else if (CsvColumnEnum.DEVICE_CLASS.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new Optional(new ConvertDeviceClass(locale)));
			}
			else if (CsvColumnEnum.RANDOMIZE_DURATION.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new Optional(new ConvertRandomizeDuration(locale)));
			}
			else if (CsvColumnEnum.LIFECYCLE_STATE.getValue().equals(col.getName()))
			{
				// Replace the write cell processor
				col.setWriteCellProcessor(new Optional(new ConvertLifecycleState(locale)));
			}
		}
	}

	/**
	 * Validate export.
	 * 
	 * @param exportFile the export file
	 * @param response the response
	 */
	private static void validateExport(boolean exportFile, InternalResponse response)
	{
		if (!exportFile)
		{
			response.setStatus(Status.ExceptionError);
			response.getMessageInfoList().add(MessageInfo
					.createFieldValidationError(SENSUS_DM_EXPORT_CSV_ERROR));
		}
	}

	/**
	 * Creates the gregorian calendar.
	 * 
	 * @param time the time
	 * @param hour the hour
	 * @param minute the minute
	 * @param second the second
	 * @return the gregorian calendar
	 */
	private static GregorianCalendar createGregorianCalendar(Date time, Integer hour, Integer minute, Integer second)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(time);
		gc.set(Calendar.HOUR_OF_DAY, hour);
		gc.set(Calendar.MINUTE, minute);
		gc.set(Calendar.SECOND, second);
		return gc;
	}
}
