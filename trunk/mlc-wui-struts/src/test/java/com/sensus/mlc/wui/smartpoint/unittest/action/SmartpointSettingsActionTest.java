package com.sensus.mlc.wui.smartpoint.unittest.action;

import org.apache.struts2.StrutsSpringTestCase;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.smartpoint.action.SmartpointSettingsAction;

/**
 * Test SmartpointSettingsAction.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class SmartpointSettingsActionTest extends StrutsSpringTestCase
{

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
	 * Test settings.
	 * 
	 * @throws Exception the exception
	 */
	public void testSettings() throws Exception
	{

		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		ActionProxy proxy = getActionProxy("/smartpoint/settings.action");
		SmartpointSettingsAction action = (SmartpointSettingsAction)proxy.getAction();

		action.setServletRequest(request);

		SessionAuthenticationTestUtil.setMockSession();

		action.setServletRequest(request);

		SessionAuthenticationTestUtil.setMockSession();

		assertEquals("Struts Outcome", Action.SUCCESS, action.settings());
		assertNotNull("AddGroupInclude", action.getAddTag());
	}

}
