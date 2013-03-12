package com.sensus.mlc.wui.util;

import java.util.Locale;

import org.springframework.mock.web.MockHttpSession;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.tenant.model.Tenant;

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

	/** The Constant LOCALE. */
	private static final String LOCALE = "en_US";

	/** The Constant I18NLOCALE. */
	private static final String I18NLOCALE = "WW_TRANS_I18N_LOCALE";

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
		userContext.setTenant(tenant);
		userContext.setUserId(USER_ROD);
		userContext.setUserRole(ROLE_ADMIN);
		userContext.setLocaleString(LOCALE);
		httpSession.setAttribute(SESSION_PROPERTY, userContext);
		httpSession.setAttribute(I18NLOCALE, Locale.ENGLISH);
		return httpSession;
	}

}
