package com.sensus.lc.controller.util;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.springframework.mock.web.MockHttpSession;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.UserSettings;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class SessionAuthenticationTestUtil.
 */
public final class SessionAuthenticationTestUtil
{

	/** The Constant SESSION_PROPERTY. */
	private static final String SESSION_PROPERTY = "userContext";

	/** The Constant USER_ROD. */
	private static final String USER_ROD = "rod";

	/** The Constant ROLE_ADMIN. */
	private static final String ROLE_ADMIN = "ROLE_Role.Admin";

	/** The Constant I18NLOCALE. */
	private static final String I18NLOCALE = "WW_TRANS_I18N_LOCALE";

	/** The Constant SESSION_PROPERTY. */
	private static final String USER_SETTINGS = "userSettings";

	/** The Constant PAGE_SIZE. */
	private static final int PAGE_SIZE = 25;

	/** The Constant DATE_FORMAT. */
	private static final String DATE_FORMAT = "MM/dd/yyyy";

	/** The Constant LANGUAGE. */
	private static final String LANGUAGE = "en_US";

	/** The Constant TIME_ZONE. */
	private static final String TIME_ZONE = "-10";

	/** The Constant LANGUAGE_FORMAT. */
	private static final String LANGUAGE_FORMAT = "en";

	/** The Constant COUNTRY_FORMAT. */
	private static final String COUNTRY_FORMAT = "US";

	/**
	 * Gets the default user locale settings.
	 * 
	 * @param serviceTypeEnum the service type enum
	 * @return the default user locale settings
	 */
	public static UserSettings getDefaultUserSettings()
	{

		UserSettings userSettings = new UserSettings();

		userSettings.setDateFormat(DateFormat
				.getDateInstance(DateFormat.SHORT, new Locale(LANGUAGE_FORMAT, COUNTRY_FORMAT)));
		userSettings.setDateFormatMask(DATE_FORMAT);
		userSettings.setLanguage(LANGUAGE);
		userSettings.setTimezone(TIME_ZONE);
		userSettings.setBaseLocale(LANGUAGE);
		userSettings.setBaseLocaleObject(Locale.ENGLISH);
		userSettings.setDefaultLat("-231312321");
		userSettings.setDefaultLng("3092432");
		userSettings.setMonitor(0);
		userSettings.setPageSize(25);
		userSettings.setRoles(new ArrayList<String>());
		userSettings.setTimezone("America");
		userSettings.setTimeZoneHours("3");
		userSettings.setTimeZoneMinutes("30");
		userSettings.setTimezoneShort("UTC");
		userSettings.setTenant("ACME");
		userSettings.setPageSize(PAGE_SIZE);

		return userSettings;
	}

	/**
	 * Sets the session test.
	 * 
	 * @return the mock HTTP session
	 */
	public static MockHttpSession getSessionTest()
	{
		MockHttpSession httpSession = new MockHttpSession();

		UserContext userContext = new UserContext();
		Tenant tenant = new Tenant(1);
		tenant.setLightTimeZone(TIME_ZONE);
		userContext.setTenant(tenant);
		userContext.setUserId(USER_ROD);
		userContext.setUserRole(ROLE_ADMIN);
		userContext.setLocaleString(LANGUAGE);
		httpSession.setAttribute(SESSION_PROPERTY, userContext);
		httpSession.setAttribute(USER_SETTINGS, getDefaultUserSettings());
		httpSession.setAttribute(I18NLOCALE, Locale.ENGLISH);
		return httpSession;
	}
}
