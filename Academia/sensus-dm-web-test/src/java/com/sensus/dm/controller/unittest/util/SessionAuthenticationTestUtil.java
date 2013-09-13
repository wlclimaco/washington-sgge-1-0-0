package com.sensus.dm.controller.unittest.util;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.model.UserSettings;

/**
 * The Class SessionAuthenticationTestUtil.
 */
public final class SessionAuthenticationTestUtil
{
	private static final String _240 = "240";

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

	/** The Constant SESSION_PROPERTY. */
	private static final String SESSION_PROPERTY = "userSettings";

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant I18NLOCALE. */
	private static final String I18NLOCALE = "WW_TRANS_I18N_LOCALE";

	/**
	 * Gets the authorities.
	 * 
	 * @return the authorities
	 */
	public static Collection<SimpleGrantedAuthority> getDefaultAuthorities()
	{
		// make everyone ROLE_USER
		Collection<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_Role.EPM_ADMIN"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_Role.CUSTOMER_ACME"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_EPM_SERVICE_ELECTRIC"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_EPM_SERVICE_GAS"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_EPM_SERVICE_WATER"));
		return grantedAuthorities;
	}

	/**
	 * Gets the default user locale settings.
	 * 
	 * @param serviceTypeEnum the service type enum
	 * @return the default user locale settings
	 */
	public static UserSettings getDefaultUserSettings(ServiceTypeEnum serviceTypeEnum)
	{

		if (ValidationUtil.isNull(serviceTypeEnum))
		{

			serviceTypeEnum = ServiceTypeEnum.ELECTRIC;
		}

		UserSettings UserSettings = new UserSettings();

		UserSettings.setDateFormat(DateFormat
				.getDateInstance(DateFormat.SHORT, new Locale(LANGUAGE_FORMAT, COUNTRY_FORMAT)));
		UserSettings.setDateFormatMask(DATE_FORMAT);
		UserSettings.setLanguage(LANGUAGE);
		UserSettings.setMonitor(THREE);
		UserSettings.setTimezone(TIME_ZONE);
		UserSettings.setTimeZoneMinutes(_240);
		UserSettings.setBaseLocale(LANGUAGE);
		UserSettings.setBaseLocaleObject(Locale.ENGLISH);
		UserSettings.setServiceType(serviceTypeEnum.toString());
		UserSettings.setTenant("ACME");
		UserSettings.setPageSize("25");

		return UserSettings;
	}

	/**
	 * Sets the authentication test.
	 * 
	 * @param grantedAuthorities the new authentication test
	 */
	public static void setAuthenticationTest(Collection<SimpleGrantedAuthority> grantedAuthorities)
	{
		UserDetails userDetails = new User("rod", "koala", true, false, false, false, grantedAuthorities);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(token);
	}

	/**
	 * Sets the session test.
	 * 
	 * @param serviceTypeEnum the service type enum
	 * @return the session test
	 */
	public static MockHttpSession getSessionTest(ServiceTypeEnum serviceTypeEnum)
	{
		setAuthenticationTest(getDefaultAuthorities());

		MockHttpSession httpSession = new MockHttpSession();

		httpSession.setAttribute(SESSION_PROPERTY, getDefaultUserSettings(serviceTypeEnum));
		httpSession.setAttribute(I18NLOCALE, Locale.ENGLISH);

		return httpSession;
	}
}