package com.prosperitasglobal.sendsolv.common.util;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DMUtil
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DMUtil.class);

	/** The Constant EXCEPTION_MSG. */
	private static final String EXCEPTION_MSG = "DMUtil";

	/** The Constant UTC_TZ. */
	private static final String UTC_TZ = "UTC";

	private static String fetchSetterName(String fieldName)
	{
		String cameCaseName = "";
		String[] names = StringUtils.splitByWholeSeparator(fieldName.toLowerCase(), "_");
		for (String name : names)
		{
			cameCaseName += StringUtils.capitalize(name);
		}
		return "set" + cameCaseName;
	}

	/** Date */

	public static Long createUTCStartDate(Date date, String timeZoneID)
	{
		DateTime dt =
				new DateTime(date, DateTimeZone.forID(timeZoneID));
		dt = dt.minusHours(dt.getHourOfDay());
		dt = dt.minusMinutes(dt.getMinuteOfHour());
		dt = dt.minusSeconds(dt.getSecondOfMinute());
		dt = dt.minusMillis(dt.getMillisOfSecond());
		dt = dt.toDateTime(DateTimeZone.forID(UTC_TZ));

		return dt.getMillis();
	}

	public static Long createUTCEndDate(Date date, String timeZoneID)
	{
		DateTime dt =
				new DateTime(date, DateTimeZone.forID(timeZoneID));
		dt = dt.minusHours(dt.getHourOfDay());
		dt = dt.plusHours(23);
		dt = dt.minusMinutes(dt.getMinuteOfHour());
		dt = dt.plusMinutes(59);
		dt = dt.minusSeconds(dt.getSecondOfMinute());
		dt = dt.plusSeconds(59);
		dt = dt.minusMillis(dt.getMillisOfSecond());
		dt = dt.plusMillis(59);
		dt = dt.toDateTime(DateTimeZone.forID(UTC_TZ));

		return dt.getMillis();
	}

}
