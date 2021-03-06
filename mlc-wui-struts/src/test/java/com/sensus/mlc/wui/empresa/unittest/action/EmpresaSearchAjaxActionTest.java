package com.sensus.mlc.wui.empresa.unittest.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.analytics.action.AnalyticsSearchAjaxAction;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.ActionPaginationUtil;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;


public class EmpresaSearchAjaxActionTest extends StrutsSpringTestCase
{

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	public static final String JSON_OK = "OK";

	public static final String JSON_FAIL = "FAIL";

	private static final String MESSAGE_SUCCESS_STATUS = "Success Status";

	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	private static final String MESSAGE_RESPONSE_OBJECT_NOT_PRESENT = "No Response object";

	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	private static final String MESSAGE_MESSAGES_SET = "Has Response Messages";

	private static final String MESSAGE_MESSAGE_COUNT = "Count Response Messages";

	
}
