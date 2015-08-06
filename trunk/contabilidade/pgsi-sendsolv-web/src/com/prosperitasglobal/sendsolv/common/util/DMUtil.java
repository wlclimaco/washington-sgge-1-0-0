package com.prosperitasglobal.sendsolv.common.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.util.SensusAppContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.model.UserSettings;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;

public class DMUtil
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DMUtil.class);

	/** The Constant EXCEPTION_MSG. */
	private static final String EXCEPTION_MSG = "DMUtil";

	/** The Constant UTC_TZ. */
	private static final String UTC_TZ = "UTC";

	public static UserSettings createUserSettings(List<Property> settings)
	{
		if (ValidationUtil.isNullOrEmpty(settings))
		{
			return null;
		}

		UserSettings userSettings = new UserSettings();
		setPropertyToUserSettings(userSettings, settings);

		return userSettings;
	}

	public static Properties fetchProperties(String uiSettingPath)
	{
		Properties prop = new Properties();
		try
		{
			prop.load(SensusAppContext.getApplicationContext().getResource(uiSettingPath).getInputStream());
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(EXCEPTION_MSG).append(e).toString());
		}
		return prop;
	}

	private static void setPropertyToUserSettings(UserSettings userSettings, List<Property> properties)
	{

		for (Property property : properties)
		{
			if (ValidationUtil.isNull(property) || ValidationUtil.isNullOrEmpty(property.getPropertyValue()))
			{
				return;
			}

			String propertyName = property.getPropertyName();
			String setterMethodName = fetchSetterName(propertyName);
			try
			{
				Method setterMethod = null;
				Object value = property.getPropertyValue();

				if (PropertyEnum.DATE_FORMAT.toString().equals(propertyName))
				{
					value = property.getPropertyValue().toLowerCase().replace("yyyy", "yy");
				}

				setterMethod = UserSettings.class.getDeclaredMethod(setterMethodName, String.class);
				setterMethod.invoke(userSettings, value);
			}
			catch (Throwable e)
			{
				// Do not set property
			}
		}

	}

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
