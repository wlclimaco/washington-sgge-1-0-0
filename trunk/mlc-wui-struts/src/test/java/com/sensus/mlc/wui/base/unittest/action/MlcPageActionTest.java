package com.sensus.mlc.wui.base.unittest.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;
import com.sensus.mlc.wui.base.action.MlcPageAction;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.ActionPaginationUtil;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.settings.unittest.action.SettingsBCFMockImpl;

public class MlcPageActionTest extends StrutsSpringTestCase
{

	/** The logger. */
	private final Logger logger = Logger.getLogger(this.getClass());

	/** The Constant STRUTS_OUTCOME. */
	private static final String STRUTS_OUTCOME = "Struts outcome";

	/** The Constant TESTING_SUCCESS. */
	private static final String TESTING_SUCCESS = "Testing success...";

	/** The Constant TESTING_EMPTY. */
	private static final String TESTING_EMPTY = "Testing empty...";

	/** The Constant NOTENANT. */
	private static final String NOTENANT = "notenant";

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts2.StrutsSpringTestCase#getContextLocations()
	 */
	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	/**
	 * Gets the action.
	 * 
	 * @return the action
	 * @throws Exception the exception
	 */
	private MlcPageAction getAction() throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		MlcPageAction action =
				(MlcPageAction)getActionProxy("/checkLogin.action")
						.getAction();

		action.setServletRequest(request);

		return action;

	}

	/**
	 * Test tag list.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testCreateUserProfile() throws Exception
	{

		MlcPageAction action = getAction();

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ActionPaginationUtil.DISPLAY_LENGTH, "1");
		action.setParameters(parameters);

		SessionAuthenticationTestUtil.setMockSession();
		createNewUserCredentials(action.getServletRequest(), (SettingsBCFMockImpl)action.getSettingsBCF());

		SettingsBCFMockImpl settingsBCF = (SettingsBCFMockImpl)action.getSettingsBCF();

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/
		logger.info(TESTING_SUCCESS);
		settingsBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.createUserProfile());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/
		logger.info("TESTING FAILURE");
		settingsBCF.setMode(BaseMockImpl.MODE_FAILURE);
		assertEquals(STRUTS_OUTCOME, NOTENANT, action.createUserProfile());

		/***********************************
		 * 
		 * TESTING EMPTY
		 * 
		 ***********************************/
		logger.info(TESTING_EMPTY);
		settingsBCF.setMode(BaseMockImpl.MODE_EMPTY);
		assertEquals(STRUTS_OUTCOME, NOTENANT, action.createUserProfile());

		/***********************************
		 * 
		 * TESTING EXCEPTION
		 * 
		 ***********************************/
		logger.info("TESTING EXCEPTION");
		settingsBCF.setMode(BaseMockImpl.MODE_EXCEPTION);
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.createUserProfile());

	}

	@SuppressWarnings("deprecation")
	private void createNewUserCredentials(HttpServletRequest httpServletRequest, SettingsBCFMockImpl settingsBCFMockImpl)
	{
		GrantedAuthority[] authorities = new GrantedAuthority[1];
		TenantRequest tenantRequest = new TenantRequest();
		tenantRequest.setServerName(httpServletRequest.getServerName());
		Tenant tenant = settingsBCFMockImpl.fetchTenantByServerName(tenantRequest).getTenant();

		authorities[0] = new GrantedAuthorityImpl("CUSTOMER_" + tenant.getRniCode());
		UserDetails userDetails = new User("scott", "wombat", true,
				false, false, false, authorities);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(token);
	}
}
