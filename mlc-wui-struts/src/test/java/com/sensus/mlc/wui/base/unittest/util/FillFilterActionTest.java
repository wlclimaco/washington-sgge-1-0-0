package com.sensus.mlc.wui.base.unittest.util;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.wui.base.util.Constants;
import com.sensus.mlc.wui.base.util.FillFilterAction;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.group.unittest.action.GroupBCFMockImpl;
import com.sensus.mlc.wui.schedule.unittest.action.ScheduleBCFMockImpl;
import com.sensus.mlc.wui.settings.unittest.action.SettingsBCFMockImpl;
import com.sensus.mlc.wui.smartpoint.unittest.action.SmartPointAccessorBCFMockImpl;
import com.sensus.mlc.wui.tag.unittest.action.TagBCFMockImpl;

/**
 * The FillFilterAction Tests.
 * 
 * @author Raphael Constantino
 */

public class FillFilterActionTest extends StrutsSpringTestCase
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

	/** The Constant MESSAGE_SUCCESS_STATUS. */
	private static final String MESSAGE_SUCCESS_STATUS = "Success Status";

	/** The Constant MESSAGE_MESSAGE_COUNT. */
	private static final String MESSAGE_MESSAGE_COUNT = "Message Count";

	/** The Constant MESSAGE_MESSAGES_SET. */
	private static final String MESSAGE_MESSAGES_SET = "Messages Set";

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant MESSAGE_RESULT_COUNT. */
	private static final String RESULT_COUNT = "Result Count";

	/** The Constant MESSAGE_RESPONSE_OBJECT_NOT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_NOT_PRESENT = "Response object not present";

	/**
	 * Test fill filter.
	 * 
	 * @throws Exception the exception
	 */
	public void testFillFilter() throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("Testing failure due to missing request parameters");

		ActionProxy proxy = getActionProxy("/util/include.fillFilter.action");

		FillFilterAction action = (FillFilterAction)proxy.getAction();

		SessionAuthenticationTestUtil.setMockSession();

		GroupBCFMockImpl groupBcf = (GroupBCFMockImpl)action.getGroupBCF();
		groupBcf.setMode(BaseMockImpl.MODE_FAILURE);
		TagBCFMockImpl tagBcf = (TagBCFMockImpl)action.getTagBCF();
		tagBcf.setMode(BaseMockImpl.MODE_FAILURE);
		ScheduleBCFMockImpl scheduleBcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
		scheduleBcf.setMode(BaseMockImpl.MODE_FAILURE);
		SmartPointAccessorBCFMockImpl smartPointBcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		smartPointBcf.setMode(BaseMockImpl.MODE_FAILURE);
		SettingsBCFMockImpl settingsBcf = (SettingsBCFMockImpl)action.getSettingsBCF();
		settingsBcf.setMode(BaseMockImpl.MODE_FAILURE);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getFilterResult());
		String response = proxy.execute();

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("Testing success");

		request.setParameter("data",
				"groups,status,tags,eventSchedule,offsetSchedule,alarmType,warningType,configuration,search,lightType,eventType,user");

		proxy = getActionProxy("/util/include.fillFilter.action");

		action = (FillFilterAction)proxy.getAction();

		action.setServletRequest(request);

		groupBcf = (GroupBCFMockImpl)action.getGroupBCF();
		groupBcf.setMode(BaseMockImpl.MODE_SUCCESS);
		tagBcf = (TagBCFMockImpl)action.getTagBCF();
		tagBcf.setMode(BaseMockImpl.MODE_SUCCESS);
		scheduleBcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
		scheduleBcf.setMode(BaseMockImpl.MODE_SUCCESS);
		smartPointBcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		smartPointBcf.setMode(BaseMockImpl.MODE_SUCCESS);
		settingsBcf = (SettingsBCFMockImpl)action.getSettingsBCF();
		settingsBcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getFilterResult());
		response = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getFilterResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_OK, action.getFilterResult().getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getFilterResult().getMessages());
		assertEquals(RESULT_COUNT, GroupBCFMockImpl.GROUP_COUNT.intValue(), action.getFilterResult().getGroups().length);
		assertEquals(RESULT_COUNT, 5, action.getFilterResult().getStatus().length);
		assertEquals(RESULT_COUNT, TagBCFMockImpl.TAG_FILTER_COUNT.intValue() + 1,
				action.getFilterResult().getTags().length);

		assertEquals(RESULT_COUNT, 2, action.getFilterResult().getConfiguration().length);
		assertEquals(RESULT_COUNT, 2, action.getFilterResult().getConfiguration().length);
		assertEquals(RESULT_COUNT, 6, action.getFilterResult().getQueryType().length);
	}
}
