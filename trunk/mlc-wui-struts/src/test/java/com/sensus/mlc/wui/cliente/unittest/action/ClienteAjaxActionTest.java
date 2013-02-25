package com.sensus.mlc.wui.cliente.unittest.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.Constants;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;


/**
 * The Class GroupAjaxActionTest.
 */
public class ClienteAjaxActionTest extends StrutsSpringTestCase
{
	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/** The Constant MESSAGE_SUCCESS_STATUS. */
	private static final String MESSAGE_SUCCESS_STATUS = "Success Status";

	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant MESSAGE_RESPONSE_OBJECT_NOT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_NOT_PRESENT = "No Response object";

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	/** The Constant MESSAGE_MESSAGES_SET. */
	private static final String MESSAGE_MESSAGES_SET = "Has Response Messages";

	/** The Constant MESSAGE_MESSAGE_COUNT. */
	private static final String MESSAGE_MESSAGE_COUNT = "Count Response Messages";

	/** The Constant NAME_SPACE. */
	private static final String NAME_SPACE = "/scheduledevent/";

	/** The Constant ACTION. */
	private static final String ACTION = ".action";

	/** The Constant ACTION_REMOVE_METERS_GROUP. */
	private static final String ACTION_REMOVE_METERS_SCHEDULE = "clearEventSchedule";

	/** The Constant ACTION_ADD_METERS_GROUP. */
	private static final String ACTION_APPLY_METERS_SCHEDULE = "applyEventSchedule";

	private static final String ACTION_APPLY_SCHEDULE_GROUP = "ajax.applyScheduleEventToGroup";

	/** The Constant TEST_SUCESS. */
	private static final String TEST_SUCESS = "Testing success";

	/** The Constant TEST_FAILURE. */
	private static final String TEST_FAILURE = "Testing failure";

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Testing exception";

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts2.StrutsSpringTestCase#getContextLocations()
	 */
	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-epm-wui-unittest-context-test.xml";
	}



}
