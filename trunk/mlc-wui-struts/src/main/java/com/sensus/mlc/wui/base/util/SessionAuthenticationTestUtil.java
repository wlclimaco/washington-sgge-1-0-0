package com.sensus.mlc.wui.base.util;

import java.text.DateFormat;
import java.util.Locale;

import org.apache.struts.mock.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.wui.base.model.UserSettings;

/**
 * The Class SessionAuthenticationTestUtil.
 */
public final class SessionAuthenticationTestUtil
{

	/** The Constant CUSTOMER. */
	private static final String CUSTOMER = "PECO";

	/** The Constant DATE_FORMAT. */
	private static final String DATE_FORMAT = "MM/DD/YYYY";

	/** The Constant LANGUAGE. */
	private static final String LANGUAGE = "en_US";

	/** The Constant TIME_ZONE. */
	private static final String TIME_ZONE = "-10";

	/** The Constant LANGUAGE_FORMAT. */
	private static final String LANGUAGE_FORMAT = "en";

	/** The Constant COUNTRY_FORMAT. */
	private static final String COUNTRY_FORMAT = "US";

	/** The Constant SESSION_PROPERTY. */
	private static final String SESSION_PROPERTY = "userLocaleSettings";

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant TRUE. */
	private static final String TRUE = "true";

	/** The Constant I18NLOCALE. */
	private static final String I18NLOCALE = "WW_TRANS_I18N_LOCALE";

	/** The session. */
	private static MockHttpSession session;

	/**
	 * Instantiates a new session authentication test util.
	 */
	private SessionAuthenticationTestUtil()
	{
	}

	/**
	 * Sets the authentication test.
	 */
	public static void setAuthenticationTest()
	{
		GrantedAuthority[] authorities = new GrantedAuthority[1];
		authorities[0] = new GrantedAuthorityImpl("ROLE_Role.Admin");
		authorities[0] = new GrantedAuthorityImpl("CUSTOMER_PECO");
		UserDetails userDetails = new User("rod", "koala", true,
				false, false, false, authorities);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(token);
	}

	/**
	 * Sets the session test.
	 * 
	 * @return the mock http session
	 */
	public static MockHttpSession setSessionTest()
	{

		SessionAuthenticationTestUtil.setAuthenticationTest();

		MockHttpSession httpSession = getSession();

		UserSettings userLocaleSettings = new UserSettings();
		Tenant tenant = new Tenant(1, CUSTOMER, CUSTOMER, CUSTOMER);
		userLocaleSettings.setDateFormat(DateFormat
				.getDateInstance(DateFormat.SHORT, new Locale(LANGUAGE_FORMAT, COUNTRY_FORMAT)));
		userLocaleSettings.setDateFormatMask(DATE_FORMAT);
		userLocaleSettings.setMonitor(THREE);
		userLocaleSettings.setTimezone(TIME_ZONE);
		userLocaleSettings.setBaseLocale(LANGUAGE);
		userLocaleSettings.setConvertUnit(TRUE);
		userLocaleSettings.setBaseLocaleObject(Locale.ENGLISH);
		httpSession.setAttribute(SESSION_PROPERTY, userLocaleSettings);
		httpSession.setAttribute(I18NLOCALE, Locale.ENGLISH);

		return httpSession;

	}

	/**
	 * Sets the mock session.
	 */
	public static void setMockSession()
	{
		UserSettings userLocaleSettings = new UserSettings();
		Tenant tenant = new Tenant(1, CUSTOMER, CUSTOMER, CUSTOMER);
		userLocaleSettings.setDateFormat(DateFormat
				.getDateInstance(DateFormat.SHORT, new Locale(LANGUAGE_FORMAT, COUNTRY_FORMAT)));
		userLocaleSettings.setDateFormatMask(DATE_FORMAT);
		userLocaleSettings.setMonitor(THREE);
		userLocaleSettings.setTimezone(TIME_ZONE);
		userLocaleSettings.setBaseLocale(LANGUAGE);
		userLocaleSettings.setConvertUnit(TRUE);
		userLocaleSettings.setBaseLocaleObject(Locale.ENGLISH);
		getSession().setAttribute(SESSION_PROPERTY, userLocaleSettings);
		getSession().setAttribute(I18NLOCALE, Locale.ENGLISH);

	}

	/**
	 * Gets the session.
	 * 
	 * @return the session
	 */
	public static MockHttpSession getSession()
	{

		session = new MockHttpSession();
		return session;
	}

}
