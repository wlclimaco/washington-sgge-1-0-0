package com.sensus.mlc.wui.base.unittest.util;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.wui.base.util.ControlLightsAjaxAction;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.smartpoint.unittest.action.SmartPointProcessorBCFMockImpl;

/**
 * The ControlLights Tests.
 * 
 * @author Vinicius Scalon Ferreira
 */

public class ControlLightsAjaxActionTest extends StrutsSpringTestCase
{

	/**
	 * Load the Spring configuration.
	 * 
	 * @return the context locations
	 */
	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/** The Constant MESSAGE_RESPONSE_OBJECT_NOT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_NOT_PRESENT = "Response object not present";

	/**
	 * Test update light status.
	 */
	@Test
	public void testControlLights()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		LightRequest lightRequest = new LightRequest();

		ControlLightsAjaxAction action =
				(ControlLightsAjaxAction)getActionProxy("/util/ajax.controlLights.action").getAction();
		action.setServletRequest(request);

		SessionAuthenticationTestUtil.setMockSession();

		SmartPointProcessorBCFMockImpl smartpointBCF =
				(SmartPointProcessorBCFMockImpl)action.getSmartPointProcessorBCF();

		logger.info("Testing reset values error...");
		smartpointBCF.setMode(BaseMockImpl.MODE_FAILURE);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getResponse());

		logger.info("Testing reset values for light success...");
		smartpointBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		action.setLightRequest(lightRequest);
		assertEquals(Action.SUCCESS, action.controlLights());
	}
}
