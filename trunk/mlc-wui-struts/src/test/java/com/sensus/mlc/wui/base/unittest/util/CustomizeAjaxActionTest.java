package com.sensus.mlc.wui.base.unittest.util;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.base.model.ListTypeEnum;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.wui.base.util.CustomizeAjaxAction;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.smartpoint.unittest.action.SmartPointAccessorBCFMockImpl;

/**
 * The FillFilterAction Tests.
 * 
 * @author Raphael Constantino
 */

public class CustomizeAjaxActionTest extends StrutsSpringTestCase
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

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	/**
	 * Test fill filter.
	 * 
	 * @throws Exception the exception
	 */
	public void testFetchCustomize() throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		ActionProxy proxy = getActionProxy("/util/fetchCustomize.action");

		CustomizeAjaxAction action = (CustomizeAjaxAction)proxy.getAction();

		ColumnFilterRequest columnFilterRequest = new ColumnFilterRequest();
		columnFilterRequest.setListTypeEnum(null);

		action.setColumnFilterRequest(columnFilterRequest);

		SessionAuthenticationTestUtil.setMockSession();

		SmartPointAccessorBCFMockImpl bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);

		/** Asserts **/
		String response = proxy.execute();
		assertEquals("Struts Response", Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Fail", false, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/util/fetchCustomize.action");

		action = (CustomizeAjaxAction)proxy.getAction();

		columnFilterRequest = new ColumnFilterRequest();
		columnFilterRequest.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);

		action.setColumnFilterRequest(columnFilterRequest);

		bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		response = proxy.execute();
		assertEquals("Struts Response", Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

	}
}
