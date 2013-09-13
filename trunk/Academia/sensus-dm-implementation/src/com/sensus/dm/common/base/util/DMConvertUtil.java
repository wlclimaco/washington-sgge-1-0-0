package com.sensus.dm.common.base.util;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;

import com.sensus.api.getmeterreadings.messages.ReadingTypeSelector;
import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.messagetypes.han.HanDeviceDetail;
import com.sensus.common.messagetypes.han.HanInterface;
import com.sensus.common.messagetypes.loadshed.MeterRelays;
import com.sensus.common.messagetypes.measurements.Multiplier;
import com.sensus.common.messagetypes.meter.MeterIdentity;
import com.sensus.common.messagetypes.meterread.Channel;
import com.sensus.common.messagetypes.meterread.UnitOfMeasure;
import com.sensus.common.messagetypes.service.ServiceType;
import com.sensus.common.model.Message;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.scheduler.model.DaysOfWeekEnum;
import com.sensus.common.scheduler.model.Frequency;
import com.sensus.common.scheduler.model.FrequencyTypeEnum;
import com.sensus.common.util.SensusMessageUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.device.model.DeviceColumnEnum;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.elec.action.model.CancelDemandResponseEventAction;
import com.sensus.dm.elec.action.model.CancelSendHanTextMessageAction;
import com.sensus.dm.elec.action.model.ConnectHanDeviceAction;
import com.sensus.dm.elec.action.model.DeleteHanDeviceAction;
import com.sensus.dm.elec.action.model.DemandResetEventAction;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;
import com.sensus.dm.elec.action.model.DemandResponseSetupAction;
import com.sensus.dm.elec.action.model.DisconnectHanDeviceAction;
import com.sensus.dm.elec.action.model.GetDemandResponseEventStatusAction;
import com.sensus.dm.elec.action.model.GetDemandResponseSetupStatusAction;
import com.sensus.dm.elec.action.model.GetHanConnectionStatusAction;
import com.sensus.dm.elec.action.model.GetRemoteConnectStatusAction;
import com.sensus.dm.elec.action.model.GetTamperDetectTimerAction;
import com.sensus.dm.elec.action.model.ImportHanDeviceAction;
import com.sensus.dm.elec.action.model.RemoteArmConnectAction;
import com.sensus.dm.elec.action.model.RemoteConnectAction;
import com.sensus.dm.elec.action.model.RetryImportHanDeviceAction;
import com.sensus.dm.elec.action.model.SendHanTextMessageAction;
import com.sensus.dm.elec.action.model.SetTamperDetectTimerAction;
import com.sensus.dm.elec.device.model.DeviceClassEnum;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanDeviceConfiguration;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMConfiguration;
import com.sensus.dm.elec.device.model.LCMRelay;
import com.sensus.dm.water.action.model.DemandReadAction;
import com.sensus.dm.water.device.model.WaterLeak;
import com.sensus.gateway.type.converter.XMLGregorianCalendarConverter;

/**
 * The Class DMConvertUtil.
 * 
 * @author QAT Global
 */
public final class DMConvertUtil
{

	/** The Constant FOUR. */
	private static final Integer FOUR = 4;

	/** The Constant STRING_ZERO. */
	private static final String STRING_ZERO = "0";

	/** The Constant THIRTEEN. */
	private static final int THIRTEEN = 13;

	/** The Constant COMMA_DELIMITER. */
	private static final String COMMA_DELIMITER = ",";

	/** The Constant POINT_DELIMITER. */
	private static final String POINT_DELIMITER = ".";

	/** The constant UTC_OFFSET. */
	private static final Integer UTC_OFFSET = 3600000;

	/** The Constant UTC_OFFSET_MINUTES. */
	private static final Integer UTC_OFFSET_MINUTES = 60000;

	/** The Constant SUCCESS. */
	private static final String SUCCESS = "Success";

	/** The MA x_ bits. */
	private static final Integer MAX_BITS = 64;

	/** The HEXDIGITS. */
	private static final Integer HEXDIGITS = MAX_BITS / FOUR;

	/** The HEXBASE. */
	private static final Integer HEXBASE = 16;

	/** The Constant STR_UTC. */
	public static final String STR_UTC = "UTC";

	/** The Constant ELEVEN. */
	private static final int ELEVEN = 11;

	/** The Constant TWELVE. */
	private static final int TWELVE = 12;

	/** The Constant THREE. */
	private static final int THREE = 3;

	/** The Constant TWO. */
	private static final int TWO = 2;

	/** The Constant ONE. */
	private static final int ONE = 1;

	/** The Constant ZERO. */
	private static final char ZERO_CHAR = '0';

	/** The Constant STR_ONE. */
	private static final String STR_ONE = "1";

	/** The Constant STR_B. */
	private static final String STR_B = "B";

	/** The Constant STR_R. */
	private static final String STR_R = "R";

	/** The Constant COLON. */
	private static final String COLON = ":";

	/** The Constant TEN. */
	private static final int TEN = 10;

	/** The Constant ZERO_INT. */
	private static final int ZERO_INT = 0;

	/** The Constant MULTIPLIER_10_POWER_2. */
	private static final Double MULTIPLIER_10_POWER_2_CONST = 100.0;

	/** The Constant MULTIPLIER_10_POWER_3. */
	private static final Double MULTIPLIER_10_POWER_3_CONST = 1000.0;

	/** The Constant MULTIPLIER_10_POWER_6. */
	private static final Double MULTIPLIER_10_POWER_6_CONST = 1000000.0;

	/** The Constant MULTIPLIER_10_POWER_9. */
	private static final Double MULTIPLIER_10_POWER_9_CONST = 1000000000.0;

	/** The Constant MULTIPLIER_10_POWER_NEG_2. */
	private static final Double MULTIPLIER_10_POWER_NEG_2_CONST = 0.01;

	/** The Constant MULTIPLIER_10_POWER_NEG_3. */
	private static final Double MULTIPLIER_10_POWER_NEG_3_CONST = 0.001;

	/** The Constant MULTIPLIER_10_POWER_NEG_6. */
	private static final Double MULTIPLIER_10_POWER_NEG_6_CONST = 0.000001;

	/** The Constant KILO. */
	private static final String KILO = "k";

	/** The Constant STR_CF. */
	private static final String STR_CF = "CF";

	/** The Constant STR_GALS. */
	private static final String STR_GALS = "Gal";

	/** The Constant NOT_AVAILABLE. */
	private static final String NOT_AVAILABLE = "N/A";

	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/** The Constant LEAK_REPORT. */
	private static final String LEAK_REPORT = "p_leak_report";

	/** The Constant SIXTY. */
	private static final int SIXTY = 60;

	/** The Constant RELAY. */
	private static final String RELAY = "RELAY";

	/** The Constant DEMAND_RESPONSE. */
	private static final String DEMAND_RESPONSE = "DEMAND_RESPONSE";

	/**
	 * Instantiates a new EPM convert util.
	 */
	private DMConvertUtil()
	{
	}

	/**
	 * Convert days of week to string.
	 * 
	 * @param days the days list.
	 * @return the days string
	 */
	public static String convertDaysOfWeekToString(List<DaysOfWeekEnum> days)
	{
		if (!ValidationUtil.isNullOrEmpty(days))
		{
			StringBuilder sb = new StringBuilder("");

			for (DaysOfWeekEnum day : days)
			{
				sb.append(day.getValue()).append(COMMA_DELIMITER);
			}
			return sb.toString().substring(0, sb.toString().length() - 1);
		}

		return null;
	}

	/**
	 * Convert the string to date.
	 * 
	 * @param strDate date string
	 * @param dateFormat date format
	 * @return the date object
	 */
	public static Date convertStringToUTCDate(String strDate, String dateFormat)
	{
		if (!ValidationUtil.isNullOrEmpty(strDate))
		{
			try
			{
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				Calendar cal = Calendar.getInstance();
				TimeZone tz = TimeZone.getTimeZone(STR_UTC);
				cal.setTimeZone(tz);
				cal.setTime(sdf.parse(strDate));
				Date date = cal.getTime();

				return convertDateToDefaultUTC(date);
			}
			catch (ParseException e)
			{
				return null;
			}
		}
		return null;
	}

	/**
	 * Converts a GregorianCalendar in XMLGregorianCalendar.
	 * 
	 * @param calendar the calendar
	 * @return XMLGregorianCalendar
	 */
	public static XMLGregorianCalendar convertCalendarToXMLGregorianCalendar(GregorianCalendar calendar)
	{
		if (!ValidationUtil.isNull(calendar))
		{
			try
			{
				return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
			}
			catch (Exception e)
			{
				return null;
			}
		}

		return null;

	}

	/**
	 * Convert Date to default UTC.
	 * 
	 * @param date the date
	 * @return date
	 */
	public static Date convertDateToDefaultUTC(Date date)
	{
		if (!ValidationUtil.isNull(date))
		{
			int diff = TimeZone.getDefault().getOffset(date.getTime()) / UTC_OFFSET_MINUTES;
			try
			{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.MINUTE, -1 * diff);
				return calendar.getTime();
			}
			catch (Exception ex)
			{
				return null;
			}
		}
		return null;
	}

	/**
	 * Convert date to server time zone.
	 * 
	 * @param date the date
	 * @return the date
	 */
	public static Date convertDateToServerTimeZone(Date date)
	{
		if (!ValidationUtil.isNull(date))
		{
			int diff = TimeZone.getDefault().getOffset(date.getTime()) / UTC_OFFSET;
			try
			{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.HOUR_OF_DAY, diff);
				return calendar.getTime();
			}
			catch (Exception ex)
			{
				return null;
			}
		}
		return null;
	}

	/**
	 * Convert date to server time zone.
	 * 
	 * @param timeZone the time zone
	 * @param date the date
	 * @return the date
	 */
	public static Date convertDateToServerTimeZone(String timeZone, Date date)
	{
		int diff2 = 0;
		if (!ValidationUtil.isNull(timeZone))
		{
			TimeZone tz = TimeZone.getTimeZone(TimeZone.getAvailableIDs(new Integer(timeZone) * UTC_OFFSET)[0]);
			diff2 = tz.getOffset(date.getTime()) / UTC_OFFSET;
		}

		try
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, diff2);
			return calendar.getTime();
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	/**
	 * Convert calendar to date utc.
	 * 
	 * @param timeZone the time zone
	 * @param calendar the calendar
	 * @return the date
	 */
	public static Date convertCalendarToDateUTC(String timeZone, Calendar calendar)
	{

		// TimeZone tzMeter = TimeZone.getTimeZone("US/Central");
		TimeZone tzMeter = TimeZone.getTimeZone(timeZone);

		GregorianCalendar gcMeter = new GregorianCalendar(tzMeter);
		gcMeter.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		gcMeter.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		gcMeter.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
		gcMeter.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		gcMeter.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		gcMeter.set(Calendar.SECOND, calendar.get(Calendar.SECOND));

		int diff = tzMeter.getOffset(gcMeter.getTimeInMillis()) / UTC_OFFSET;

		Calendar cNew = Calendar.getInstance();
		cNew.set(Calendar.YEAR, gcMeter.get(Calendar.YEAR));
		cNew.set(Calendar.MONTH, gcMeter.get(Calendar.MONTH));
		cNew.set(Calendar.DAY_OF_MONTH, gcMeter.get(Calendar.DAY_OF_MONTH));
		cNew.set(Calendar.HOUR_OF_DAY, gcMeter.get(Calendar.HOUR_OF_DAY));
		cNew.set(Calendar.MINUTE, gcMeter.get(Calendar.MINUTE));
		cNew.set(Calendar.SECOND, gcMeter.get(Calendar.SECOND));
		cNew.add(Calendar.HOUR_OF_DAY, -1 * diff);

		return cNew.getTime();

	}

	/**
	 * Converts a Reading Hash map (sensus injection)
	 * to a List of ReadingTypeSelector used into Orchestration.
	 * 
	 * @param fieldHash the field hash
	 * @param fldNames the fld names
	 * @return List<ReadingTypeSelector>
	 * @see com.sensus.dm.elec.device.bcl.impl.ElectricMeterBCLImpl.fetchAllIntervalRead
	 */
	public static List<ReadingTypeSelector> convertReadingHashToTypeSelectorList(
			Map<String, ReadingTypeSelector> fieldHash,
			List<String> fldNames)
	{
		List<ReadingTypeSelector> readTypes = new ArrayList<ReadingTypeSelector>();

		if (!ValidationUtil.isNullOrEmpty(fldNames))
		{
			for (String field : fldNames)
			{
				if (!ValidationUtil.isNull(field))
				{
					ReadingTypeSelector readingTypeSelector = fieldHash.get(field);
					if (!ValidationUtil.isNull(readingTypeSelector))
					{
						readTypes.add(readingTypeSelector);
					}
					else
					{
						return null;
					}
				}
			}
		}
		return readTypes;
	}

	/**
	 * Convert string to date.
	 * 
	 * @param strDate the str date
	 * @param dateFormat the date format
	 * @return the date
	 */
	public static Date convertStringToDate(String strDate, String dateFormat)
	{
		if (!ValidationUtil.isNullOrEmpty(strDate))
		{
			try
			{
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				Calendar cal = Calendar.getInstance();
				cal.setTime(sdf.parse(strDate));
				Date date = cal.getTime();

				return date;
			}
			catch (ParseException e)
			{
				return null;
			}
		}
		return null;
	}

	/**
	 * Convert string to string date.
	 * 
	 * @param strDate the str date
	 * @param dateFormat the date format
	 * @return the string
	 */
	public static String convertStringToStringDate(String strDate, String dateFormat)
	{
		if (!ValidationUtil.isNullOrEmpty(strDate))
		{
			try
			{
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				Calendar cal = Calendar.getInstance();
				cal.setTime(sdf.parse(strDate));
				return sdf.format(cal.getTime());
			}
			catch (ParseException e)
			{
				return null;
			}
		}
		return null;
	}

	/**
	 * Convert date to string.
	 * 
	 * @param strDate the str date
	 * @param dateFormat the date format
	 * @return the string
	 */
	public static String convertDateToString(Date strDate, String dateFormat)
	{
		if (!ValidationUtil.isNull(strDate))
		{
			DateFormat sdf = new SimpleDateFormat(dateFormat);
			Calendar cal = Calendar.getInstance();
			cal.setTime(strDate);

			return sdf.format(cal.getTime());
		}
		return null;
	}

	/**
	 * Convert date to string with time zone info.
	 * 
	 * @param date the date
	 * @param timeZone the time zone.
	 * @param dateFormat the date format
	 * @return the string
	 */
	public static String convertDateToString(Date date, String timeZone, String dateFormat)
	{
		if (!ValidationUtil.isNull(date))
		{
			return convertDateToString(DMConvertUtil.convertDateToServerTimeZone(timeZone, date), dateFormat);
		}

		return null;
	}

	/**
	 * Convert date to string with time zone info.
	 * 
	 * @param date the date
	 * @param timeZone the time zone.
	 * @param dateFormat the date format
	 * @return the string
	 */
	public static String convertDateToStringTimeOnly(Date date, String timeZone, String dateFormat)
	{
		if (ValidationUtil.isNull(date))
		{
			return null;
		}

		StringBuffer stringBuffer =
				new StringBuffer(convertDateToString(DMConvertUtil.convertDateToServerTimeZone(timeZone, date),
						dateFormat));

		if (stringBuffer.charAt(ELEVEN) == ZERO_CHAR)
		{
			return stringBuffer.substring(TWELVE);
		}

		return stringBuffer.substring(ELEVEN);
	}

	/**
	 * Check result.
	 * 
	 * @param response the response
	 * @param paramMap the param map
	 * @param key the key
	 * @param errorStatus the error status
	 * @param errorCode the error code
	 * @param errorParams the error params
	 * @return the int
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static int checkResult(
			InternalResponse response, Map<String, Object> paramMap, String key,
			Status errorStatus, String errorCode, Object[] errorParams)
	{
		if (ValidationUtil.isNull(paramMap))
		{
			if (response.getStatus().equals(Status.NoRowsFoundError))
			{
				response.setStatus(Status.OperationSuccess);
			}
		}
		else if (paramMap.values().contains(SUCCESS))
		{
			response.setStatus(Status.OperationSuccess);

			if (!ValidationUtil.isNullOrEmpty(key) && paramMap.containsKey(key))
			{
				if (key.contains(LEAK_REPORT))
				{
					((InternalResultsResponse)response).addResults((List<WaterLeak>)paramMap.get(key));
					return 0;
				}

				return (Integer)paramMap.get(key);
			}
		}
		else
		{
			response.setStatus(Status.ExceptionError);

			response.addMessage(
					paramMap.get(errorCode).toString(),
					Message.MessageSeverity.Error,
					Message.MessageLevel.FieldValidation,
					errorParams);
		}

		return 0;
	}

	/**
	 * Check result.
	 * 
	 * @param response the response
	 * @param paramMap the param map
	 * @param key the key
	 * @return the int
	 */
	public static int checkResult(InternalResponse response, Map<String, Object> paramMap, String key)
	{
		return checkResult(response, paramMap, key, null, null, null);
	}

	/**
	 * Check result.
	 * 
	 * @param response the response
	 * @param paramMap the param map
	 * @param errorCode the error code
	 * @param errorParams the error params
	 * @return the int
	 */
	public static int checkResult(
			InternalResponse response, Map<String, Object> paramMap, String errorCode, Object[] errorParams)
	{
		return checkResult(response, paramMap, null, null, errorCode, errorParams);
	}

	/**
	 * Check result.
	 * 
	 * @param response the response
	 * @param paramMap the param map
	 * @return the int
	 */
	public static int checkResult(InternalResponse response, Map<String, Object> paramMap)
	{
		return checkResult(response, paramMap, null, null, null, null);
	}

	/**
	 * Check result.
	 * 
	 * @param response the response
	 * @return the int
	 */
	public static int checkResult(InternalResponse response)
	{
		return checkResult(response, null, null, null, null, null);
	}

	/**
	 * Convert date to integer.
	 * 
	 * @param date the date
	 * @return the long
	 */
	public static int convertDateToInteger(Date date)
	{
		return convertDateToInteger(date, Calendar.getInstance().getTimeZone().getID());
	}

	/**
	 * Convert date to integer.
	 * 
	 * @param date the date
	 * @param timeZone the time zone
	 * @return the long
	 */
	public static int convertDateToInteger(Date date, String timeZone)
	{
		long longDate = date.getTime();
		String timeStamp = String.valueOf(longDate + TimeZone.getTimeZone(timeZone).getOffset(longDate));

		return Integer.parseInt(StringUtils.mid(timeStamp, 0, TEN));
	}

	/**
	 * Convert integer to date.
	 * 
	 * @param value the value
	 * @return the date
	 */
	public static Date convertIntegerToDate(long value)
	{
		String str = String.valueOf(value);

		if (str.length() < THIRTEEN)
		{
			value = Long.parseLong(StringUtils.rightPad(str, THIRTEEN, STRING_ZERO));
		}

		XMLGregorianCalendarConverter calendarConverter = new XMLGregorianCalendarConverter();
		GregorianCalendar gregorianCalendar = calendarConverter.convert(value).toGregorianCalendar();

		return gregorianCalendar.getTime();
	}

	/**
	 * Convert action types description keys
	 * in the DB to an array of strings.
	 * 
	 * @param internalResultsResponse the InternalResultsResponse<ActionType>
	 * @param locale the locale
	 */
	public static void convertActionTypeDescriptions(InternalResultsResponse<ActionType> internalResultsResponse,
			Locale locale)
	{

		List<ActionType> types = new ArrayList<ActionType>();

		for (ActionType at : internalResultsResponse.getResultsList())
		{
			String valueMessage =
					SensusMessageUtil.getMessage(at.getActionTypeEnum().getActionTypeName(), null, null, locale);

			if (!ValidationUtil.isNullOrEmpty(valueMessage))
			{
				at.setActionTypeEnumDescription(valueMessage);
				types.add(at);
			}
		}
		// replace the old messages with the new ones
		internalResultsResponse.getResultsList().clear();
		internalResultsResponse.getResultsList().addAll(types);
	}

	/**
	 * Convert big integer to internal representation.
	 * 
	 * @param macNumber the mac number
	 * @return the big integer
	 */
	private static BigInteger convertBigIntegerToInternalRepresentation(BigInteger macNumber)
	{
		if (macNumber.signum() < 0)
		{
			// macNumber cannot be negative. Set it to a positive number
			macNumber = macNumber.negate();
		}

		if (macNumber.bitLength() > MAX_BITS)
		{
			// Mac number cannot be greater than 2 ^48, then convert to a 48 bit number
			String bigMacStr = macNumber.toString(HEXBASE);
			String macStr = bigMacStr.substring(bigMacStr.length() - 1 - HEXDIGITS, bigMacStr.length() - 1);
			macNumber = new BigInteger(macStr, HEXBASE);
		}
		return macNumber;
	}

	/**
	 * Gets the string representation.
	 * 
	 * @param mac the mac
	 * @return the string representation
	 */
	public static String convertMacAddress(BigInteger mac)
	{
		BigInteger macNumber = convertBigIntegerToInternalRepresentation(mac);

		String strRep = macNumber.toString(HEXBASE);

		// Add the 0 at the left to complete the string.
		if (strRep.length() < HEXDIGITS)
		{

			StringBuffer zeroStr = new StringBuffer();

			for (int i = 0; i < HEXDIGITS - strRep.length(); i++)
			{
				zeroStr.append(STRING_ZERO);
			}
			strRep = new String(zeroStr + strRep);
		}

		// For each pair of character, add a : character, except for the last one
		StringBuffer hexRep = new StringBuffer();
		for (int i = 0; i < HEXDIGITS - 2; i = i + 2)
		{
			hexRep.append(strRep.substring(i, i + 2) + COLON);
		}
		hexRep.append(strRep.substring(HEXDIGITS - 2, HEXDIGITS));

		return hexRep.toString();
	}

	/**
	 * Convert string to internal representation.
	 * 
	 * @param macString the mac string
	 * @return the big integer
	 */
	public static BigInteger convertMacAddress(String macString)
	{
		String trimmedMacString = macString.trim();
		boolean isValidMac = trimmedMacString.matches("^(\\p{XDigit}{2}:){7}\\p{XDigit}{2}$"); //$NON-NLS-1$

		if (!isValidMac)
		{
			return null;
		}
		// Remove all ':' from the String
		String validMacString = trimmedMacString.replaceAll(COLON, ""); //replace(':', '\0');  //$NON-NLS-1$

		// Convert to the internal representation
		return new BigInteger(validMacString, HEXBASE);

	}

	/**
	 * Convert action to property.
	 * 
	 * @param action the action
	 * @param properties the properties
	 */
	public static void convertActionToProperty(DMAction action, List<Property> properties)
	{
		if (!ValidationUtil.isNull(action.getActionType())
				&& !ValidationUtil.isNull(action.getActionType().getActionTypeEnum()))
		{
			switch (action.getActionType().getActionTypeEnum())
			{
				case INITIATE_DEMAND_RESPONSE_EVENT:
					convertDemandResponseToProperty((DemandResponseEventAction)action, properties);
					break;

				case INITIATE_DEMAND_RESPONSE_SETUP:

					// the enrollment code is null when set up Demand Response for an Entek LCM
					// from within the detail page and separate relays are chosen
					// in that case, the enrollment code is set within each LCMRelay
					if (!ValidationUtil.isNullOrEmpty(((DemandResponseSetupAction)action).getLcmRelays()))
					{
						convertLCMRelayToProperty(((DemandResponseSetupAction)action).getLcmRelays(), properties);
					}
					else
					{
						convertDemandResponseSetupToProperty((DemandResponseSetupAction)action, properties);
					}
					break;

				case SET_TAMPER_DETECT_TIMER:
					convertTamperTimerToProperty(((SetTamperDetectTimerAction)action).getLcmRelays(), properties);
					break;

				case SEND_HAN_TEXT_MESSAGE:
					convertSendHANTextMessageToProperty((SendHanTextMessageAction)action, properties);
					break;

				case DELETE_HAN_DEVICE:
					convertDeleteHanDeviceToProperty(action, properties);
					break;

				default:
					break;
			}
		}

		if (!ValidationUtil.isNull(action.getActionTime()))
		{
			properties.add(new Property(PropertyEnum.ACTION_DATE.getValue(), String.valueOf(convertDateToInteger(action
					.getActionTime()))));
		}

		if (!ValidationUtil.isNull(action.isOnDemand()) && action.isOnDemand())
		{
			properties.add(new Property(PropertyEnum.ON_DEMAND.getValue(), PropertyEnum.ON_DEMAND.getValue()));
		}

	}

	/**
	 * Convert delete han device to property.
	 * 
	 * @param action the action
	 * @param properties the properties
	 */
	private static void convertDeleteHanDeviceToProperty(DMAction action, List<Property> properties)
	{
		if (!ValidationUtil.isNull(action.getFirstDevice()))
		{
			properties.add(new Property(PropertyEnum.DEVICE_ID.getValue(), action.getFirstDevice().getDeviceId()));
		}
	}

	/**
	 * Convert tamper timer to property.
	 * 
	 * @param relays the relays
	 * @param properties the properties
	 */
	public static void convertTamperTimerToProperty(List<LCMRelay> relays, List<Property> properties)
	{
		for (LCMRelay lcmRelay : relays)
		{
			switch (lcmRelay.getRelay())
			{
				case ONE:
					properties.add(new Property(PropertyEnum.RELAY1_TAMPER_TIMEOUT.getValue(), lcmRelay
							.getTamperDetectTimer()));
					break;
				case TWO:
					properties.add(new Property(PropertyEnum.RELAY2_TAMPER_TIMEOUT.getValue(), lcmRelay
							.getTamperDetectTimer()));
					break;
				case THREE:
					properties.add(new Property(PropertyEnum.RELAY3_TAMPER_TIMEOUT.getValue(), lcmRelay
							.getTamperDetectTimer()));
					break;
				default:
					break;
			}
		}

	}

	/**
	 * Convert lcm relay to property.
	 * 
	 * @param relays the relays
	 * @param properties the properties
	 */
	public static void convertLCMRelayToProperty(List<LCMRelay> relays,
			List<Property> properties)
	{

		for (LCMRelay lcmRelay : relays)
		{
			HashMap<PropertyEnum, PropertyEnum> propertyEnumMap = new HashMap<PropertyEnum, PropertyEnum>();

			switch (lcmRelay.getRelay())
			{
				case ONE:
					propertyEnumMap.put(PropertyEnum.DEMAND_RESPONSE_ENROLLMENT_CODE,
							PropertyEnum.RELAY1_ENROLLMENT_CODE);
					propertyEnumMap.put(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_START,
							PropertyEnum.RELAY1_RANDOMIZE_START);
					propertyEnumMap.put(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_END, PropertyEnum.RELAY1_RANDOMIZE_END);
					propertyEnumMap.put(PropertyEnum.DEVICE_CLASS, PropertyEnum.RELAY1_DEVICE_CLASS);
					break;
				case TWO:
					propertyEnumMap.put(PropertyEnum.DEMAND_RESPONSE_ENROLLMENT_CODE,
							PropertyEnum.RELAY2_ENROLLMENT_CODE);
					propertyEnumMap.put(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_START,
							PropertyEnum.RELAY2_RANDOMIZE_START);
					propertyEnumMap.put(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_END, PropertyEnum.RELAY2_RANDOMIZE_END);
					propertyEnumMap.put(PropertyEnum.DEVICE_CLASS, PropertyEnum.RELAY2_DEVICE_CLASS);
					break;
				case THREE:
					propertyEnumMap.put(PropertyEnum.DEMAND_RESPONSE_ENROLLMENT_CODE,
							PropertyEnum.RELAY3_ENROLLMENT_CODE);
					propertyEnumMap.put(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_START,
							PropertyEnum.RELAY3_RANDOMIZE_START);
					propertyEnumMap.put(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_END, PropertyEnum.RELAY3_RANDOMIZE_END);
					propertyEnumMap.put(PropertyEnum.DEVICE_CLASS, PropertyEnum.RELAY3_DEVICE_CLASS);
					break;
				default:
					break;
			}

			if (!ValidationUtil.isNull(lcmRelay.getEnrollmentCode()))
			{
				properties.add(new Property(propertyEnumMap.get(PropertyEnum.DEMAND_RESPONSE_ENROLLMENT_CODE)
						.getValue(),
						lcmRelay.getEnrollmentCode().toString()));
			}

			if (!ValidationUtil.isNull(lcmRelay.getStartMinutes()))
			{
				properties.add(new Property(propertyEnumMap.get(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_START)
						.getValue(), lcmRelay.getStartMinutes().toString()));
			}

			if (!ValidationUtil.isNull(lcmRelay.getEndMinutes()))
			{
				properties.add(new Property(propertyEnumMap.get(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_END).getValue(),
						lcmRelay.getEndMinutes().toString()));
			}

			if (!ValidationUtil.isNull(lcmRelay.getDeviceClass()))
			{
				properties.add(new Property(propertyEnumMap.get(PropertyEnum.DEVICE_CLASS).getValue(),
						lcmRelay.getDeviceClass().getValue().toString()));
			}
		}

	}

	/**
	 * Convert property to lcm relay.
	 * 
	 * @param properties the properties
	 * @return the list
	 */
	public static List<LCMRelay> convertPropertyToLCMRelay(List<Property> properties)
	{

		List<LCMRelay> relays = new ArrayList<LCMRelay>();

		LCMRelay lcmRelay1 = new LCMRelay();
		LCMRelay lcmRelay2 = new LCMRelay();
		LCMRelay lcmRelay3 = new LCMRelay();

		for (Property property : properties)
		{
			switch (PropertyEnum.enumForValue(property.getPropertyName()))
			{
				case RELAY1_ENROLLMENT_CODE:
					lcmRelay1.setEnrollmentCode(Integer.parseInt(property.getPropertyValue()));
					lcmRelay1.setRelay(ONE);
					break;

				case RELAY1_RANDOMIZE_START:
					lcmRelay1.setStartMinutes(Integer.parseInt(property.getPropertyValue()));
					lcmRelay1.setRelay(ONE);
					break;

				case RELAY1_RANDOMIZE_END:
					lcmRelay1.setEndMinutes(Integer.parseInt(property.getPropertyValue()));
					lcmRelay1.setRelay(ONE);
					break;

				case RELAY1_DEVICE_CLASS:
					lcmRelay1.setDeviceClassValue(Integer.parseInt(property.getPropertyValue()));
					lcmRelay1.setRelay(ONE);
					break;

				case RELAY2_ENROLLMENT_CODE:
					lcmRelay2.setEnrollmentCode(Integer.parseInt(property.getPropertyValue()));
					lcmRelay2.setRelay(TWO);
					break;

				case RELAY2_RANDOMIZE_START:
					lcmRelay2.setStartMinutes(Integer.parseInt(property.getPropertyValue()));
					lcmRelay2.setRelay(TWO);
					break;

				case RELAY2_RANDOMIZE_END:
					lcmRelay2.setEndMinutes(Integer.parseInt(property.getPropertyValue()));
					lcmRelay2.setRelay(TWO);
					break;

				case RELAY2_DEVICE_CLASS:
					lcmRelay2.setDeviceClassValue(Integer.parseInt(property.getPropertyValue()));
					lcmRelay2.setRelay(TWO);
					break;

				case RELAY3_ENROLLMENT_CODE:
					lcmRelay3.setEnrollmentCode(Integer.parseInt(property.getPropertyValue()));
					lcmRelay3.setRelay(THREE);
					break;

				case RELAY3_RANDOMIZE_START:
					lcmRelay3.setStartMinutes(Integer.parseInt(property.getPropertyValue()));
					lcmRelay3.setRelay(THREE);
					break;

				case RELAY3_RANDOMIZE_END:
					lcmRelay3.setEndMinutes(Integer.parseInt(property.getPropertyValue()));
					lcmRelay3.setRelay(THREE);
					break;

				case RELAY3_DEVICE_CLASS:
					lcmRelay3.setDeviceClassValue(Integer.parseInt(property.getPropertyValue()));
					lcmRelay3.setRelay(THREE);
					break;

				default:
					break;
			}
		}

		if (!ValidationUtil.isNull(lcmRelay1.getRelay()))
		{
			relays.add(lcmRelay1);
		}
		if (!ValidationUtil.isNull(lcmRelay2.getRelay()))
		{
			relays.add(lcmRelay2);
		}
		if (!ValidationUtil.isNull(lcmRelay3.getRelay()))
		{
			relays.add(lcmRelay3);
		}

		return relays;
	}

	/**
	 * Convert property to entek relay demand response setup.
	 * 
	 * @param action the action
	 * @param properties the properties
	 */
	public static void convertPropertyToEntekRelayDemandResponseSetup(DemandResponseSetupAction action,
			List<Property> properties)
	{
		action.setLcmRelays(convertPropertyToLCMRelay(properties));

		for (Property property : properties)
		{
			switch (PropertyEnum.enumForValue(property.getPropertyName()))
			{
				case ACTION_DATE:
					action.setActionTime(convertDateToDefaultUTC(convertIntegerToDate(Integer
							.parseInt(property
									.getPropertyValue()))));
					break;

				default:
					break;
			}
		}

	}

	/**
	 * Convert demand response to property.
	 * 
	 * @param action the action
	 * @param properties the properties
	 */
	private static void convertDemandResponseToProperty(DemandResponseEventAction action, List<Property> properties)
	{

		properties
				.add(new Property(PropertyEnum.DEMAND_RESPONSE_ENROLLMENT_CODE.getValue(), action.getEnrollmentCode()
						.toString()));
		properties.add(new Property(PropertyEnum.DEMAND_RESPONSE_CRITICALITY_LEVEL.getValue(), action
				.getCriticalityLevel().toString()));
		properties.add(new Property(PropertyEnum.DEMAND_RESPONSE_DURATION.getValue(), action
				.getDemandResponseDuration().toString()));

		if (!ValidationUtil.isNull(action.getDutyCycleRate()))
		{
			properties.add(new Property(PropertyEnum.DEMAND_RESPONSE_DUTY_CYCLE_RATE.getValue(), action
					.getDutyCycleRate().toString()));
		}
		if (!ValidationUtil.isNull(action.getHeating()))
		{
			properties
					.add(new Property(PropertyEnum.DEMAND_RESPONSE_HEATING.getValue(), action.getHeating().toString()));
		}
		if (!ValidationUtil.isNull(action.getCooling()))
		{
			properties
					.add(new Property(PropertyEnum.DEMAND_RESPONSE_COOLING.getValue(), action.getCooling().toString()));
		}
		if (!ValidationUtil.isNull(action.getLoadAdjustment()))
		{
			properties.add(new Property(PropertyEnum.DEMAND_RESPONSE_LOAD_ADJUSTMENT.getValue(), action
					.getLoadAdjustment().toString()));
		}
		if (!ValidationUtil.isNull(action.getRandomizeStart()))
		{
			// two booleans are not part of the bit map...
			properties.add(new Property(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_START.getValue(), action
					.getRandomizeStart().toString()));
		}
		if (!ValidationUtil.isNull(action.getRandomizeEnd()))
		{
			properties.add(new Property(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_END.getValue(), action.getRandomizeEnd()
					.toString()));
		}

		convertDeviceClassToProperty(action, properties);
	}

	/**
	 * Convert device class to property.
	 * 
	 * @param action the action
	 * @param properties the properties
	 */
	private static void convertDeviceClassToProperty(DemandResponseEventAction action, List<Property> properties)
	{

		if (ValidationUtil.isNullOrEmpty(action.getDeviceClasses()))
		{
			return;
		}

		// add a property for each deviceClass in this action
		for (String deviceClass : action.getDeviceClasses())
		{
			// make sure all values in the device class list are really in the Device Class Enum
			DeviceClassEnum deviceClassProperty = DeviceClassEnum.enumForDescriptionValue(deviceClass);

			if (!ValidationUtil.isNull(deviceClassProperty))
			{
				// Key will be WATER_HEATER and value will be WaterHeater
				// key = POOL_PUMP and value = PoolPump
				properties.add(new Property(deviceClassProperty.toString(), deviceClass));
			}
		}
	}

	/**
	 * Convert demand response setup to property.
	 * 
	 * @param action the action
	 * @param properties the properties
	 */
	public static void convertDemandResponseSetupToProperty(DemandResponseSetupAction action,
			List<Property> properties)
	{

		if (!ValidationUtil.isNull(action.getEnrollmentCode()))
		{
			properties.add(new Property(PropertyEnum.DEMAND_RESPONSE_ENROLLMENT_CODE.getValue(), action
					.getEnrollmentCode().toString()));
		}

		if (!ValidationUtil.isNull(action.getStartMinutes()))
		{
			properties.add(new Property(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_START.getValue(), action
					.getStartMinutes().toString()));
		}

		if (!ValidationUtil.isNull(action.getEndMinutes()))
		{
			properties.add(new Property(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_END.getValue(), action
					.getEndMinutes().toString()));
		}
	}

	/**
	 * Convert import han device to property.
	 * 
	 * @param device the device
	 * @param properties the properties
	 */
	public static void convertHanDeviceToProperty(Device device, List<Property> properties)
	{
		HanDevice hanDevice = (HanDevice)device;

		if (!ValidationUtil.isNull(hanDevice.getConfiguration().getInstallCode()))
		{
			properties.add(new Property(PropertyEnum.INSTALL_CODE.getValue(), hanDevice.getConfiguration()
					.getInstallCode()));
		}
		if (!ValidationUtil.isNull(hanDevice.getDeviceId()))
		{
			properties.add(new Property(PropertyEnum.DEVICE_ID.getValue(), hanDevice.getDeviceId()));
		}
		if (!ValidationUtil.isNull(hanDevice.getDeviceModel()))
		{
			properties.add(new Property(PropertyEnum.MODEL.getValue(), hanDevice.getDeviceModel().getDescription()));
		}
		if (!ValidationUtil.isNull(hanDevice.getDeviceModel().getManufacture()))
		{
			properties.add(new Property(PropertyEnum.MANUFACTURER.getValue(), hanDevice.getDeviceModel()
					.getManufacture()));
		}
		if (!ValidationUtil.isNullOrEmpty(hanDevice.getRadio().getDevices())
				&& !ValidationUtil.isNull(hanDevice.getRadio().getFirstDevice().getRadio())
				&& !ValidationUtil.isNull(hanDevice.getRadio().getFirstDevice().getRadio().getFlexNetId()))
		{
			properties.add(new Property(PropertyEnum.FLEXNET_ID.getValue(), hanDevice.getRadio().getFirstDevice()
					.getRadio().getFlexNetId().toString()));
		}
		if (!ValidationUtil.isNull(hanDevice.getDeviceType()))
		{
			properties.add(new Property(PropertyEnum.DEVICE_TYPE.getValue(), hanDevice.getDeviceType().getValue()
					.toString()));
		}
		if (!ValidationUtil.isNull(hanDevice.getRadio().getFlexNetId()))
		{
			properties.add(new Property(PropertyEnum.NETWORK_ADDRESS.getValue(), hanDevice.getRadio().getFlexNetId()
					.toString()));
		}
	}

	/**
	 * Convert meter to property.
	 * 
	 * @param device the device
	 * @param properties the properties
	 */
	public static void convertMeterToProperty(Device device, List<Property> properties)
	{
		if (!ValidationUtil.isNull(device.getDeviceId()))
		{
			properties.add(new Property(PropertyEnum.METER_ID.getValue(), device.getDeviceId()));
		}
	}

	/**
	 * Convert send han text message to property.
	 * 
	 * @param action the action
	 * @param properties the properties
	 */
	private static void convertSendHANTextMessageToProperty(SendHanTextMessageAction action, List<Property> properties)
	{
		if (!ValidationUtil.isNull(action.getTextMessage()))
		{
			properties.add(new Property(PropertyEnum.HAN_TEXT_MESSAGE_TEXT.getValue(), action.getTextMessage()));
		}
		if (!ValidationUtil.isNull(action.getDurationHANTextMessage()))
		{
			properties.add(new Property(PropertyEnum.HAN_TEXT_MESSAGE_DURATION.getValue(), action
					.getDurationHANTextMessage().toString()));
		}

	}

	/**
	 * Convert frequency to property.
	 * 
	 * @param frequency the frequency
	 * @param properties the properties
	 */
	public static void convertFrequencyToProperty(Frequency frequency, List<Property> properties)
	{

		if (!ValidationUtil.isNull(frequency.getTimeToRepeat()))
		{
			properties.add(new Property(PropertyEnum.TIME_TO_REPEAT.getValue(), frequency
					.getTimeToRepeat().toString()));
		}

		if (!ValidationUtil.isNull(frequency.getStartOnDate()))
		{
			properties.add(new Property(PropertyEnum.START_ON_DATE.getValue(), String
					.valueOf(convertDateToInteger(frequency.getStartOnDate()))));
		}

		if (!ValidationUtil.isNull(frequency.getNeverEnds())
				&& frequency.getNeverEnds())
		{
			properties.add(new Property(PropertyEnum.NEVER_ENDS.getValue(), frequency.getNeverEnds()
					.toString()));
		}
		else if (!ValidationUtil.isNull(frequency.getEndsAfterOccurrences()))
		{
			properties.add(new Property(PropertyEnum.ENDS_AFTER_OCURRENCES.getValue(), frequency
					.getEndsAfterOccurrences().toString()));
			if (!ValidationUtil.isNull(frequency.getExecutedOccurrences()))
			{
				properties.add(new Property(PropertyEnum.EXECUTED_OCCURRENCES.getValue(), frequency
						.getExecutedOccurrences().toString()));
			}

		}
		else if (!ValidationUtil.isNull(frequency.getEndDate()))
		{
			properties.add(new Property(PropertyEnum.END_DATE.getValue(), String.valueOf(convertDateToInteger(frequency
					.getEndDate()))));
		}

		if (!ValidationUtil.isNull(frequency.getFrequencyTypeEnum()))
		{
			properties.add(new Property(PropertyEnum.FREQUENCY_TYPE.getValue(), frequency
					.getFrequencyTypeEnum().getValue()));
		}

		if (!ValidationUtil.isNull(frequency.getDayOfMonth()))
		{
			properties.add(new Property(PropertyEnum.DAY_OF_MONTH.getValue(), frequency
					.getDayOfMonth().toString()));
		}

		if (!ValidationUtil.isNull(frequency.getDayOfWeek()))
		{
			properties.add(new Property(PropertyEnum.DAY_OF_WEEK.getValue(), frequency.getDayOfWeek()
					.toString()));
		}

		if (!ValidationUtil.isNullOrEmpty(frequency.getDaysOfWeeks()))
		{
			properties.add(new Property(PropertyEnum.DAYS_OF_WEEKS.getValue(),
					DMConvertUtil.convertDaysOfWeekToString(frequency
							.getDaysOfWeeks())));
		}

	}

	/**
	 * Convert property to schedule parameter.
	 * 
	 * @param schedule the schedule
	 */
	public static void convertPropertyToScheduleParameter(DMSchedule schedule)
	{

		// gets the generic DMAction and transform into an specific action (DemandResponse, SendHan, etc)
		schedule.setDmAction(convertToSpecificAction(schedule.getDmAction()));

		// fills the Frequency object based on its properties
		convertPropertyToFrequency(schedule);

		// fills commons attributes based on its properties
		convertPropertyToSchedule(schedule);

		// specific parameters (from properties to attibutes) according specific action types
		switch (schedule.getDmAction().getActionType().getActionTypeEnum())
		{
			case INITIATE_DEMAND_RESPONSE_EVENT:
				convertPropertyToDemandResponse((DemandResponseEventAction)schedule.getDmAction(),
						schedule.getProperties());
				break;

			case INITIATE_DEMAND_RESPONSE_SETUP:
				convertPropertyToDemandResponseSetupAction((DemandResponseSetupAction)schedule.getDmAction(),
						schedule.getProperties());
				break;

			case SET_TAMPER_DETECT_TIMER:
				((SetTamperDetectTimerAction)schedule.getDmAction()).setLcmRelays(convertPropertyToTamperTimer(schedule
						.getProperties()));
				break;

			case GET_TAMPER_DETECT_TIMER:
				((GetTamperDetectTimerAction)schedule.getDmAction()).setLcmRelays(convertPropertyToTamperTimer(schedule
						.getProperties()));
				break;

			case SEND_HAN_TEXT_MESSAGE:
				convertPropertyToSendHANTextMessage((SendHanTextMessageAction)schedule.getDmAction(),
						schedule.getProperties());
				break;

			case GET_DEMAND_RESPONSE_SETUP_STATUS:

				if (ValidationUtil.isNullOrEmpty(((GetDemandResponseSetupStatusAction)schedule.getDmAction())
						.getLcmRelays()))
				{
					convertPropertyToDemandResponseSetup((GetDemandResponseSetupStatusAction)schedule.getDmAction(),
							schedule.getProperties());
				}
				else
				{
					convertPropertyToEntekRelayDemandResponseSetup(
							(GetDemandResponseSetupStatusAction)schedule.getDmAction(),
							schedule.getProperties());
				}

				break;

			case GET_DEMAND_RESPONSE_EVENT_STATUS:
				convertPropertyToGetDemandResponseStatus((GetDemandResponseEventStatusAction)schedule.getDmAction(),
						schedule.getProperties());
				break;

			default:
				break;
		}

	}

	/**
	 * Convert to specific action, according its action type.
	 * 
	 * @param action the action
	 * @return the dM action
	 */
	public static DMAction convertToSpecificAction(DMAction action)
	{
		switch (action.getActionType().getActionTypeEnum())
		{

			case INITIATE_DEMAND_RESET_EVENT:
				return new DemandResetEventAction(action);
			case INITIATE_DEMAND_RESPONSE_EVENT:
				return new DemandResponseEventAction(action);
			case INITIATE_DEMAND_RESPONSE_SETUP:
				return new DemandResponseSetupAction(action);
			case GET_DEMAND_RESPONSE_EVENT_STATUS:
				return new GetDemandResponseEventStatusAction(action);
			case DEMAND_READ:
				return new DemandReadAction(action);
			case SET_TAMPER_DETECT_TIMER:
				return new SetTamperDetectTimerAction(action);
			case IMPORT_HAN_DEVICE:
				return new ImportHanDeviceAction(action);
			case RETRY_IMPORT_HAN_DEVICE:
				return new RetryImportHanDeviceAction(action);
			case DELETE_HAN_DEVICE:
				return new DeleteHanDeviceAction(action);
			case SEND_HAN_TEXT_MESSAGE:
				return new SendHanTextMessageAction(action);
			case CONNECT_HAN_DEVICE:
				return new ConnectHanDeviceAction(action);
			case DISCONNECT_HAN_DEVICE:
				return new DisconnectHanDeviceAction(action);
			case GET_HAN_CONNECTION_STATUS:
				return new GetHanConnectionStatusAction(action);
			case GET_TAMPER_DETECT_TIMER:
				return new GetTamperDetectTimerAction(action);
			case GET_DEMAND_RESPONSE_SETUP_STATUS:
				return new GetDemandResponseSetupStatusAction(action);
			case REMOTE_CONNECT:
				return new RemoteConnectAction(action);
			case REMOTE_DISCONNECT:
				return new DisconnectHanDeviceAction(action);
			case REMOTE_ARM_CONNECT:
				return new RemoteArmConnectAction(action);
			case GET_REMOTE_CONNECT_STATUS:
				return new GetRemoteConnectStatusAction(action);
			case CANCEL_DEMAND_RESPONSE_EVENT:
				return new CancelDemandResponseEventAction(action);
			case CANCEL_SEND_HAN_TEXT_MESSAGE:
				return new CancelSendHanTextMessageAction(action);

			default:
				break;
		}

		return null;

	}

	/**
	 * Convert property to schedule.
	 * 
	 * @param schedule the schedule
	 */
	private static void convertPropertyToSchedule(DMSchedule schedule)
	{
		for (Property property : schedule.getProperties())
		{
			switch (PropertyEnum.enumForValue(property.getPropertyName()))
			{
				case IS_MONITORED:
					schedule.getDmAction().setIsMonitored(Boolean.valueOf(property.getPropertyValue()));
					break;
				case ON_DEMAND:
					schedule.getDmAction().setOnDemand(true);
					break;
				default:
					break;
			}
		}
	}

	/**
	 * Convert property to get demand response status.
	 * 
	 * @param getDemandResponseEventAction the get demand response event action
	 * @param properties the properties
	 */
	public static void convertPropertyToGetDemandResponseStatus(
			GetDemandResponseEventStatusAction getDemandResponseEventAction,
			List<Property> properties)
	{
		for (Property property : properties)
		{
			switch (PropertyEnum.enumForValue(property.getPropertyName()))
			{
				case PARENT_PROCESS:
					getDemandResponseEventAction.setProcessId(Integer.parseInt(property.getPropertyValue()));
					break;

				default:
					break;
			}
		}
	}

	/**
	 * Convert property to demand response.
	 * 
	 * @param demandResponseEventAction the demand response event action
	 * @param properties the properties
	 */
	public static void convertPropertyToDemandResponse(DemandResponseEventAction demandResponseEventAction,
			List<Property> properties)
	{
		for (Property property : properties)
		{
			switch (PropertyEnum.enumForValue(property.getPropertyName()))
			{
				case DEMAND_RESPONSE_ENROLLMENT_CODE:
					demandResponseEventAction.setEnrollmentCode(Integer.parseInt(property.getPropertyValue()));
					break;

				case DEMAND_RESPONSE_CRITICALITY_LEVEL:
					demandResponseEventAction.setCriticalityLevel(Integer.parseInt(property.getPropertyValue()));
					break;

				case DEMAND_RESPONSE_DURATION:
					demandResponseEventAction.setDemandResponseDuration(Integer.parseInt(property.getPropertyValue()));
					break;

				case DEMAND_RESPONSE_HEATING:
					demandResponseEventAction.setHeating(Integer.parseInt(property.getPropertyValue()));
					break;

				case DEMAND_RESPONSE_COOLING:
					demandResponseEventAction.setCooling(Integer.parseInt(property.getPropertyValue()));
					break;

				case DEMAND_RESPONSE_DUTY_CYCLE_RATE:
					demandResponseEventAction.setDutyCycleRate(Integer.parseInt(property.getPropertyValue()));
					break;

				case DEMAND_RESPONSE_LOAD_ADJUSTMENT:
					demandResponseEventAction.setLoadAdjustment(Integer.parseInt(property.getPropertyValue()));
					break;

				case DEMAND_RESPONSE_RANDOMIZE_START:
					demandResponseEventAction.setRandomizeStart(Boolean.valueOf(property.getPropertyValue()));
					break;

				case DEMAND_RESPONSE_RANDOMIZE_END:
					demandResponseEventAction.setRandomizeEnd(Boolean.valueOf(property.getPropertyValue()));
					break;

				default:
					break;
			}
		}

		demandResponseEventAction.setDeviceClasses(convertPropertyToDeviceClass(properties));

	}

	/**
	 * Convert property to demand response setup.
	 * 
	 * @param action the action
	 * @param properties the properties
	 */
	public static void convertPropertyToDemandResponseSetupAction(DemandResponseSetupAction action,
			List<Property> properties)
	{
		for (Property property : properties)
		{
			if (property.getPropertyName().startsWith(RELAY))
			{
				convertPropertyToEntekRelayDemandResponseSetup(action, properties);
				return;
			}

			if (property.getPropertyName().startsWith(DEMAND_RESPONSE))
			{
				convertPropertyToDemandResponseSetup(action, properties);
			}
		}
	}

	/**
	 * Convert property to demand response setup.
	 * 
	 * @param action the action
	 * @param properties the properties
	 */
	public static void convertPropertyToDemandResponseSetup(DemandResponseSetupAction action, List<Property> properties)
	{
		for (Property property : properties)
		{
			switch (PropertyEnum.enumForValue(property.getPropertyName()))
			{
				case DEMAND_RESPONSE_ENROLLMENT_CODE:
					action.setEnrollmentCode(Integer.parseInt(property.getPropertyValue()));
					break;

				case DEMAND_RESPONSE_RANDOMIZE_START:
					action.setStartMinutes(Integer.parseInt(property.getPropertyValue()));
					break;

				case DEMAND_RESPONSE_RANDOMIZE_END:
					action.setEndMinutes(Integer.parseInt(property.getPropertyValue()));
					break;

				case ACTION_DATE:
					action.setActionTime(convertDateToDefaultUTC(convertIntegerToDate(Integer
							.parseInt(property.getPropertyValue()))));
					break;

				default:
					break;
			}
		}
	}

	/**
	 * Convert property to device class.
	 * 
	 * @param properties the properties
	 * @return the list
	 */
	private static List<String> convertPropertyToDeviceClass(List<Property> properties)
	{
		List<String> deviceClasses = new ArrayList<String>();

		// go through all the properties, looking for only those that belong to the Device Class Enum
		// i.e. WATER_HEATER, POOL_PUMP, SMART_APPLIANCES, IRRIGATION_PUMP, EXTERIOR_LIGHTING etc.
		for (Property property : properties)
		{
			DeviceClassEnum deviceClassProperty = DeviceClassEnum.enumForDescriptionValue(property.getPropertyValue());

			// If not found in the Device Class Enum, then ignore (i.e. ACTION_DATE, DEMAND_RESPONSE_RANDOMIZE_END, etc)
			if (!ValidationUtil.isNull(deviceClassProperty))
			{
				// save the value (WaterHeater, PoolPump, SmartAppliances, ExteriorLighting, etc)
				// so we can fetch this later and save in our deviceClass list
				deviceClasses.add(deviceClassProperty.getDeviceClassDescription());
			}
		}

		return deviceClasses;
	}

	/**
	 * Convert property to frequency.
	 * 
	 * @param schedule the schedule
	 */
	private static void convertPropertyToFrequency(DMSchedule schedule)
	{
		Frequency frequency = schedule.getFrequency();

		for (Property property : schedule.getProperties())
		{
			switch (PropertyEnum.enumForValue(property.getPropertyName()))
			{
				case TIME_TO_REPEAT:
					frequency.setTimeToRepeat(Integer.parseInt(property.getPropertyValue()));
					break;

				case START_ON_DATE:
					frequency.setStartOnDate(convertDateToDefaultUTC(convertIntegerToDate(Integer.parseInt(property
							.getPropertyValue()))));
					break;

				case NEVER_ENDS:
					frequency.setNeverEnds(new Boolean(property.getPropertyValue()));
					break;

				case ENDS_AFTER_OCURRENCES:
					frequency.setEndsAfterOccurrences(Integer.parseInt(property.getPropertyValue()));
					break;

				case EXECUTED_OCCURRENCES:
					frequency.setExecutedOccurrences(Integer.parseInt(property.getPropertyValue()));
					break;

				case END_DATE:
					frequency.setEndDate(convertDateToDefaultUTC(convertIntegerToDate(Integer.parseInt(property
							.getPropertyValue()))));
					break;

				case FREQUENCY_TYPE:
					frequency.setFrequencyTypeEnum(FrequencyTypeEnum.enumForValue(property.getPropertyValue()));
					break;

				case DAY_OF_MONTH:
					frequency.setDayOfMonth(new Boolean(property.getPropertyValue()));
					break;

				case DAY_OF_WEEK:
					frequency.setDayOfWeek(new Boolean(property.getPropertyValue()));
					break;

				case DAYS_OF_WEEKS:
					frequency.setDaysOfWeeks(new ArrayList<Integer>());
					String[] days = property.getPropertyValue().split(COMMA_DELIMITER);

					for (String str : days)
					{
						frequency.getDaysOfWeeks().add(DaysOfWeekEnum.enumForValue(Integer.parseInt(str)));
					}

					break;

				case ACTION_DATE:
					schedule.getDmAction()
							.setActionTime(
									convertDateToDefaultUTC(convertIntegerToDate(Integer.parseInt(property
											.getPropertyValue()))));
					break;

				default:
					break;
			}
		}
	}

	/**
	 * Convert property to send han text message.
	 * 
	 * @param sendAction the send action
	 * @param properties the properties
	 */
	private static void convertPropertyToSendHANTextMessage(SendHanTextMessageAction sendAction,
			List<Property> properties)
	{
		for (Property property : properties)
		{
			switch (PropertyEnum.enumForValue(property.getPropertyName()))
			{
				case HAN_TEXT_MESSAGE_TEXT:
					sendAction.setTextMessage(property.getPropertyValue());
					break;

				case HAN_TEXT_MESSAGE_DURATION:
					sendAction.setDurationHANTextMessage(Integer.parseInt(property
							.getPropertyValue()));
					break;

				default:
					break;
			}
		}
	}

	/**
	 * Convert property to han device.
	 * 
	 * @param device the device
	 * @param properties the properties
	 */
	public static void convertPropertyToHanDevice(Device device, List<Property> properties)
	{

		HanDevice hanDevice = (HanDevice)device;

		for (Property property : properties)
		{
			switch (PropertyEnum.enumForValue(property.getPropertyName()))
			{
				case DEVICE_ID:
					hanDevice.setDeviceId(property.getPropertyValue());
					break;

				case MODEL:
					if (ValidationUtil.isNull(hanDevice.getDeviceModel()))
					{
						hanDevice.setDeviceModel(new DeviceModel());
					}
					hanDevice.getDeviceModel().setDescription(property.getPropertyValue());
					break;

				case MANUFACTURER:
					if (ValidationUtil.isNull(hanDevice.getDeviceModel()))
					{
						hanDevice.setDeviceModel(new DeviceModel());
					}
					hanDevice.getDeviceModel().setManufacture(property.getPropertyValue());
					break;

				case INSTALL_CODE:
					if (ValidationUtil.isNull(hanDevice.getConfiguration()))
					{
						hanDevice.setConfiguration(new HanDeviceConfiguration());
					}
					hanDevice.getConfiguration().setInstallCode(property.getPropertyValue());
					break;

				default:
					break;
			}
		}
	}

	/**
	 * Convert property to device.
	 * 
	 * @param device the device
	 * @param properties the properties
	 */
	public static void convertPropertyToDevice(Device device, List<Property> properties)
	{

		switch (device.getDeviceType())
		{
			case HAN_DEVICE:
				convertPropertyToHanDevice(device, properties);
				break;
			case LCM:
				convertPropertyToLCM(device, properties);
				break;
			default:
				break;
		}

	}

	/**
	 * Convert property to lcm.
	 * 
	 * @param device the device
	 * @param properties the properties
	 */
	public static void convertPropertyToLCM(Device device, List<Property> properties)
	{

		if (device.getDeviceType().equals(DeviceTypeEnum.LCM))
		{
			LCM lcm = (LCM)device;

			for (Property property : properties)
			{
				switch (PropertyEnum.enumForValue(property.getPropertyName()))
				{
					case DEVICE_ID:
						lcm.setDeviceId(property.getPropertyValue());
						break;
						
					case MODEL:
						if (ValidationUtil.isNull(lcm.getDeviceModel()))
						{
							lcm.setDeviceModel(new DeviceModel());
						}
						lcm.getDeviceModel().setDescription(property.getPropertyValue());
						break;

					case MANUFACTURER:
						if (ValidationUtil.isNull(lcm.getDeviceModel()))
						{
							lcm.setDeviceModel(new DeviceModel());
						}
						lcm.getDeviceModel().setManufacture(property.getPropertyValue());
						break;

					case INSTALL_CODE:
						if (ValidationUtil.isNull(lcm.getConfiguration()))
						{
							lcm.setConfiguration(new LCMConfiguration());
						}
						lcm.getConfiguration().setInstallCode(property.getPropertyValue());
						break;

					default:
						break;
				}
			}
		}

	}

	/**
	 * Convert.
	 * 
	 * @param channel the channel
	 * @return the reading type selector
	 */
	public static ReadingTypeSelector convert(Channel channel)
	{
		ReadingTypeSelector readingTypeSelector = new ReadingTypeSelector();
		readingTypeSelector.setNetFlowType(channel.getNetFlowType());
		readingTypeSelector.setQuantityType(channel.getQuantityType());
		readingTypeSelector.setChannelType(channel.getType());
		readingTypeSelector.setDataQualifier(channel.getQualifier());
		return readingTypeSelector;
	}

	/**
	 * Convert firmware.
	 * 
	 * @param firmwareFlexnet the firmware flexnet
	 * @return the string
	 */
	public static String convertFirmware(String firmwareFlexnet)
	{

		// converts from 5.3 to 005.003 for instance

		StringBuilder sb = new StringBuilder();

		String[] values = StringUtils.split(firmwareFlexnet, POINT_DELIMITER);

		for (String str : values)
		{
			sb.append(StringUtils.leftPad(str, THREE, ZERO_CHAR)).append(POINT_DELIMITER);
		}

		return sb.toString().substring(ZERO_INT, sb.length() - ONE);

	}

	/**
	 * Convert firmware display.
	 * 
	 * @param isFirmware the is firmware
	 * @param firmware the firmware
	 * @return the string
	 */
	public static String convertFirmwareDisplay(Boolean isFirmware, String firmware)
	{
		// apply the mask only if there is firmware value
		if (!ValidationUtil.isNullOrEmpty(firmware))
		{
			// and it is not formated yet
			if (!firmware.contains(STR_B) && !firmware.contains(STR_R))
			{
				StringBuffer sb = new StringBuffer();

				// add an "R" or "B" if it is a firmware conversion (flexnet or meter)
				// otherwise (bootloader or zigbee) just remove the 0 between the .
				if (!ValidationUtil.isNull(isFirmware) && isFirmware)
				{
					if (firmware.substring(firmware.length() - ONE, firmware.length()).equals(STR_ONE))
					{
						sb.append(STR_B);
					}
					else
					{
						sb.append(STR_R);
					}
				}

				for (String str : StringUtils.split(firmware, POINT_DELIMITER))
				{
					sb.append(Integer.valueOf(str)).append(POINT_DELIMITER);
				}

				if (sb.length() > 0)
				{
					if (sb.length() > THREE)
					{
						return sb.substring(0, sb.length() - THREE);
					}
					return sb.toString();
				}

			}
			return firmware;
		}
		return null;
	}

	/**
	 * Convert to sensus service type.
	 * 
	 * @param deviceTypeEnum the device type enum
	 * @return the service type
	 */
	public static ServiceType convertToSensusServiceType(DeviceTypeEnum deviceTypeEnum)
	{
		switch (deviceTypeEnum)
		{
			case HAN_DEVICE:
			case LCM:
			case ELECTRIC_METER:
				return ServiceType.ELECTRIC;
			case WATER_METER:
				return ServiceType.WATER;
			case GAS_METER:
				return ServiceType.GAS;
			default:
				return null;
		}
	}

	/**
	 * Convert to sensus service type.
	 * 
	 * @param serviceTypeEnum the service type enum
	 * @return the service type
	 */
	public static ServiceType convertToSensusServiceType(ServiceTypeEnum serviceTypeEnum)
	{
		switch (serviceTypeEnum)
		{
			case ELECTRIC:
				return ServiceType.ELECTRIC;
			case WATER:
				return ServiceType.WATER;
			case GAS:
				return ServiceType.GAS;
			default:
				return null;
		}
	}

	/**
	 * Convert multiplier.
	 * 
	 * @param strValue the str value
	 * @param multiplier the multiplier
	 * @param unit the unit
	 * @param onlyPositiveValues the only positive values
	 * @return the double
	 */
	public static Double convertMultiplier(String strValue, Multiplier multiplier, UnitOfMeasure unit,
			boolean onlyPositiveValues)
	{
		Double value = Double.parseDouble(strValue);

		if (value == null)
		{
			return null;
		}

		if (onlyPositiveValues && value < 0)
		{
			value *= -1;
		}

		Double multiplierValue = convertMultiplierValue(multiplier);

		if (unit.equals(UnitOfMeasure.WH) || unit.equals(UnitOfMeasure.V_AH) || unit.equals(UnitOfMeasure.VA_RH)
				|| unit.equals(UnitOfMeasure.W) || unit.equals(UnitOfMeasure.VA) || unit.equals(UnitOfMeasure.VAR))
		{
			// According to Venky, this field should be displayed only in KXX
			multiplierValue = multiplierValue / MULTIPLIER_10_POWER_3_CONST;
		}

		return value * multiplierValue;
	}

	/**
	 * Convert formatted value.
	 * 
	 * @param value the value
	 * @param unit the unit
	 * @param format the format
	 * @param roudingMode the rouding mode
	 * @return the string
	 */
	public static String convertFormattedValue(Double value, UnitOfMeasure unit, String format, RoundingMode roudingMode)
	{
		if (value == null)
		{
			return NOT_AVAILABLE;
		}

		DecimalFormat formatter = new DecimalFormat(format);

		if (!ValidationUtil.isNull(roudingMode))
		{
			formatter.setRoundingMode(roudingMode);
		}

		if (unit.equals(UnitOfMeasure.WH) || unit.equals(UnitOfMeasure.V_AH) || unit.equals(UnitOfMeasure.VA_RH)
				|| unit.equals(UnitOfMeasure.W) || unit.equals(UnitOfMeasure.VA) || unit.equals(UnitOfMeasure.VAR))
		{
			return formatter.format(value) + SPACE + KILO + unit.value();

		}
		else if (unit.equals(UnitOfMeasure.US_GALLONS))
		{
			return formatter.format(value) + SPACE + STR_GALS;
		}
		else if (unit.equals(UnitOfMeasure.UNCORRECTED_CUBIC_FEET))
		{
			return formatter.format(value) + SPACE + STR_CF;
		}

		return formatter.format(value) + SPACE + unit.value();
	}

	/**
	 * Convert list columns.
	 * 
	 * @param list the list
	 * @return the string[]
	 */
	public static String[] convertListToArrayColumns(List<DeviceColumnEnum> list)
	{
		List<String> listColumns = new ArrayList<String>();

		for (DeviceColumnEnum deviceColumnEnum : list)
		{
			listColumns.add(deviceColumnEnum.name());
		}

		String[] strarray = listColumns.toArray(new String[listColumns.size()]);
		return strarray;
	}

	/**
	 * Convert multiplier value.
	 * 
	 * @param multiplier the multiplier
	 * @return the double
	 */
	private static Double convertMultiplierValue(Multiplier multiplier)
	{
		switch (multiplier)
		{
			case MULTIPLIER_10_POWER_2:
				return MULTIPLIER_10_POWER_2_CONST;
			case MULTIPLIER_10_POWER_3:
				return MULTIPLIER_10_POWER_3_CONST;
			case MULTIPLIER_10_POWER_6:
				return MULTIPLIER_10_POWER_6_CONST;
			case MULTIPLIER_10_POWER_9:
				return MULTIPLIER_10_POWER_9_CONST;
			case MULTIPLIER_10_POWER_NEG_2:
				return MULTIPLIER_10_POWER_NEG_2_CONST;
			case MULTIPLIER_10_POWER_NEG_3:
				return MULTIPLIER_10_POWER_NEG_3_CONST;
			case MULTIPLIER_10_POWER_NEG_6:
				return MULTIPLIER_10_POWER_NEG_6_CONST;
			default:
				return 1.0;
		}
	}

	/**
	 * Convert property to tamper timer.
	 * 
	 * @param properties the properties
	 * @return the list
	 */
	public static List<LCMRelay> convertPropertyToTamperTimer(List<Property> properties)
	{

		List<LCMRelay> relays = new ArrayList<LCMRelay>();

		for (Property property : properties)
		{
			switch (PropertyEnum.enumForValue(property.getPropertyName()))
			{
				case RELAY1_TAMPER_TIMEOUT:
					relays.add(new LCMRelay(ONE, property.getPropertyValue()));
					break;

				case RELAY2_TAMPER_TIMEOUT:
					relays.add(new LCMRelay(TWO, property.getPropertyValue()));
					break;

				case RELAY3_TAMPER_TIMEOUT:
					relays.add(new LCMRelay(THREE, property.getPropertyValue()));
					break;

				default:
					break;
			}
		}

		return relays;

	}

	/**
	 * Convert to meter identity.
	 * 
	 * @param device the device
	 * @return the meter identity
	 */
	public static MeterIdentity convertToMeterIdentity(Device device)
	{
		MeterIdentity meter = new MeterIdentity();
		meter.setMeterNo(device.getDeviceId());
		meter.setCustomerId(device.getRadio().getCustomerId());
		meter.setServiceType(convertToSensusServiceType(device.getDeviceType()));
		return meter;
	}

	/**
	 * Convert to meter relays.
	 * 
	 * @param device the device
	 * @return the meter relays
	 */
	public static MeterRelays convertToMeterRelays(Device device)
	{
		MeterRelays relays = new MeterRelays();
		relays.setMeterIdentity(convertToMeterIdentity(device));
		return relays;
	}

	/**
	 * Convert to meter identities.
	 * 
	 * @param device the device
	 * @return the list
	 */
	public static List<MeterIdentity> convertToMeterIdentities(Device device)
	{
		List<MeterIdentity> list = new ArrayList<MeterIdentity>();
		list.add(convertToMeterIdentity(device));
		return list;
	}

	/**
	 * Convert to sensus han device.
	 * 
	 * @param device the device
	 * @return the com.sensus.common.messagetypes.han. han device
	 */
	public static com.sensus.common.messagetypes.han.HanDevice convertToSensusHanDevice(Device device)
	{
		com.sensus.common.messagetypes.han.HanDevice hanDevice = new com.sensus.common.messagetypes.han.HanDevice();
		hanDevice.setEndpointId(device.getDeviceId());
		hanDevice.setCustomerId(device.getRadio().getCustomerId());
		return hanDevice;
	}

	/**
	 * Convert to han interface.
	 * 
	 * @param device the device
	 * @return the han interface
	 */
	public static HanInterface convertToHanInterface(Device device)
	{
		HanInterface hanInterface = new HanInterface();
		hanInterface.setMeterId(device.getDeviceId());
		hanInterface.setCustomerId(device.getRadio().getCustomerId());
		return hanInterface;
	}

	/**
	 * Convert to han device detail.
	 * 
	 * @param device the device
	 * @param defaultFirmwareVersion the default firmware version
	 * @param defaultZigbeeProtocol the default zigbee protocol
	 * @return the han device detail
	 */
	public static HanDeviceDetail convertToHanDeviceDetail(Device device, String defaultFirmwareVersion,
			String defaultZigbeeProtocol)
	{
		com.sensus.common.messagetypes.han.HanDeviceDetail detail =
				new com.sensus.common.messagetypes.han.HanDeviceDetail();

		if (DeviceTypeEnum.HAN_DEVICE.equals(device.getDeviceType()))
		{
			detail.setManufacturer(((HanDevice)device).getDeviceModel().getManufacture());
			detail.setModel(((HanDevice)device).getDeviceModel().getDescription());
		}
		else
		{
			detail.setManufacturer(((LCM)device).getDeviceModel().getManufacture());
			detail.setModel(((LCM)device).getDeviceModel().getDescription());
		}

		detail.setMacAddress(device.getRadio().getFlexNetId());
		detail.setProtocol(defaultZigbeeProtocol);
		detail.setFirmwareVersion(defaultFirmwareVersion);
		return detail;
	}

}
